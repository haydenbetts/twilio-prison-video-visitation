package com.braintreepayments.api;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.interfaces.ThreeDSecureLookupListener;
import com.braintreepayments.api.interfaces.ThreeDSecurePrepareLookupListener;
import com.braintreepayments.api.internal.BraintreeHttpClient;
import com.braintreepayments.api.internal.ManifestValidator;
import com.braintreepayments.api.internal.ThreeDSecureV1BrowserSwitchHelper;
import com.braintreepayments.api.models.BraintreeRequestCodes;
import com.braintreepayments.api.models.CardBuilder;
import com.braintreepayments.api.models.CardNonce;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.ThreeDSecureAuthenticationResponse;
import com.braintreepayments.api.models.ThreeDSecureInfo;
import com.braintreepayments.api.models.ThreeDSecureLookup;
import com.braintreepayments.api.models.ThreeDSecureRequest;
import com.cardinalcommerce.cardinalmobilesdk.Cardinal;
import com.cardinalcommerce.cardinalmobilesdk.enums.CardinalEnvironment;
import com.cardinalcommerce.cardinalmobilesdk.models.CardinalConfigurationParameters;
import com.cardinalcommerce.cardinalmobilesdk.models.ValidateResponse;
import com.cardinalcommerce.cardinalmobilesdk.services.CardinalInitService;
import lib.android.paypal.com.magnessdk.a;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecure {
    /* access modifiers changed from: private */
    public static String sDFReferenceId;

    @Deprecated
    public static void performVerification(final BraintreeFragment braintreeFragment, CardBuilder cardBuilder, final String str) {
        TokenizationClient.tokenize(braintreeFragment, cardBuilder, new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                ThreeDSecure.performVerification(braintreeFragment, paymentMethodNonce.getNonce(), str);
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
            }
        });
    }

    @Deprecated
    public static void performVerification(BraintreeFragment braintreeFragment, String str, String str2) {
        performVerification(braintreeFragment, new ThreeDSecureRequest().nonce(str).amount(str2));
    }

    @Deprecated
    public static void performVerification(final BraintreeFragment braintreeFragment, CardBuilder cardBuilder, final ThreeDSecureRequest threeDSecureRequest) {
        if (threeDSecureRequest.getAmount() == null) {
            braintreeFragment.postCallback((Exception) new InvalidArgumentException("The ThreeDSecureRequest amount cannot be null"));
        } else {
            TokenizationClient.tokenize(braintreeFragment, cardBuilder, new PaymentMethodNonceCallback() {
                public void success(PaymentMethodNonce paymentMethodNonce) {
                    threeDSecureRequest.nonce(paymentMethodNonce.getNonce());
                    ThreeDSecure.performVerification(braintreeFragment, threeDSecureRequest);
                }

                public void failure(Exception exc) {
                    braintreeFragment.postCallback(exc);
                }
            });
        }
    }

    public static void performVerification(final BraintreeFragment braintreeFragment, ThreeDSecureRequest threeDSecureRequest) {
        performVerification(braintreeFragment, threeDSecureRequest, (ThreeDSecureLookupListener) new ThreeDSecureLookupListener() {
            public void onLookupComplete(ThreeDSecureRequest threeDSecureRequest, ThreeDSecureLookup threeDSecureLookup) {
                braintreeFragment.sendAnalyticsEvent("three-d-secure.perform-verification.default-lookup-listener");
                ThreeDSecure.continuePerformVerification(braintreeFragment, threeDSecureRequest, threeDSecureLookup);
            }
        });
    }

    public static void performVerification(final BraintreeFragment braintreeFragment, final ThreeDSecureRequest threeDSecureRequest, final ThreeDSecureLookupListener threeDSecureLookupListener) {
        if (threeDSecureRequest.getAmount() == null || threeDSecureRequest.getNonce() == null) {
            braintreeFragment.postCallback((Exception) new InvalidArgumentException("The ThreeDSecureRequest nonce and amount cannot be null"));
        } else {
            braintreeFragment.waitForConfiguration(new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    if (!configuration.isThreeDSecureEnabled()) {
                        braintreeFragment.postCallback((Exception) new BraintreeException("Three D Secure is not enabled for this account. Please contact Braintree Support for assistance."));
                    } else if (!ManifestValidator.isUrlSchemeDeclaredInAndroidManifest(braintreeFragment.getApplicationContext(), braintreeFragment.getReturnUrlScheme(), BraintreeBrowserSwitchActivity.class)) {
                        braintreeFragment.sendAnalyticsEvent("three-d-secure.invalid-manifest");
                        braintreeFragment.postCallback((Exception) new BraintreeException("BraintreeBrowserSwitchActivity missing, incorrectly configured in AndroidManifest.xml or another app defines the same browser switch url as this app. See https://developers.braintreepayments.com/guides/client-sdk/android/v2#browser-switch for the correct configuration"));
                    } else if (configuration.getCardinalAuthenticationJwt() != null || !"2".equals(threeDSecureRequest.getVersionRequested())) {
                        braintreeFragment.sendAnalyticsEvent("three-d-secure.initialized");
                        if ("1".equals(threeDSecureRequest.getVersionRequested())) {
                            ThreeDSecure.performThreeDSecureLookup(braintreeFragment, threeDSecureRequest, threeDSecureLookupListener);
                            return;
                        }
                        ThreeDSecure.configureCardinal(braintreeFragment, configuration, threeDSecureRequest);
                        Cardinal.getInstance().init(configuration.getCardinalAuthenticationJwt(), new CardinalInitService() {
                            public void onSetupCompleted(String str) {
                                String unused = ThreeDSecure.sDFReferenceId = str;
                                ThreeDSecure.performThreeDSecureLookup(braintreeFragment, threeDSecureRequest, threeDSecureLookupListener);
                                braintreeFragment.sendAnalyticsEvent("three-d-secure.cardinal-sdk.init.setup-completed");
                            }

                            public void onValidated(ValidateResponse validateResponse, String str) {
                                ThreeDSecure.performThreeDSecureLookup(braintreeFragment, threeDSecureRequest, threeDSecureLookupListener);
                                braintreeFragment.sendAnalyticsEvent("three-d-secure.cardinal-sdk.init.setup-failed");
                            }
                        });
                    } else {
                        braintreeFragment.postCallback((Exception) new BraintreeException("Merchant is not configured for 3DS 2.0. Please contact Braintree Support for assistance."));
                    }
                }
            });
        }
    }

    public static void continuePerformVerification(BraintreeFragment braintreeFragment, ThreeDSecureRequest threeDSecureRequest, ThreeDSecureLookup threeDSecureLookup) {
        boolean z = threeDSecureLookup.getAcsUrl() != null;
        String threeDSecureVersion = threeDSecureLookup.getThreeDSecureVersion();
        braintreeFragment.sendAnalyticsEvent(String.format("three-d-secure.verification-flow.challenge-presented.%b", new Object[]{Boolean.valueOf(z)}));
        braintreeFragment.sendAnalyticsEvent(String.format("three-d-secure.verification-flow.3ds-version.%s", new Object[]{threeDSecureVersion}));
        if (!z) {
            completeVerificationFlowWithNoncePayload(braintreeFragment, threeDSecureLookup.getCardNonce());
        } else if (!threeDSecureVersion.startsWith("2.")) {
            braintreeFragment.browserSwitch((int) BraintreeRequestCodes.THREE_D_SECURE, ThreeDSecureV1BrowserSwitchHelper.getUrl(braintreeFragment.getReturnUrlScheme(), braintreeFragment.getConfiguration().getAssetsUrl(), threeDSecureRequest, threeDSecureLookup));
        } else {
            performCardinalAuthentication(braintreeFragment, threeDSecureLookup);
        }
    }

    public static void prepareLookup(final BraintreeFragment braintreeFragment, final ThreeDSecureRequest threeDSecureRequest, final ThreeDSecurePrepareLookupListener threeDSecurePrepareLookupListener) {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("authorizationFingerprint", braintreeFragment.getAuthorization().getBearer()).put("braintreeLibraryVersion", "Android-3.14.2").put("nonce", threeDSecureRequest.getNonce()).put("clientMetadata", new JSONObject().put("requestedThreeDSecureVersion", "2").put("sdkVersion", "3.14.2"));
        } catch (JSONException unused) {
        }
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (configuration.getCardinalAuthenticationJwt() == null) {
                    braintreeFragment.postCallback((Exception) new BraintreeException("Merchant is not configured for 3DS 2.0. Please contact Braintree Support for assistance."));
                    return;
                }
                ThreeDSecure.configureCardinal(braintreeFragment, configuration, threeDSecureRequest);
                Cardinal.getInstance().init(configuration.getCardinalAuthenticationJwt(), new CardinalInitService() {
                    public void onSetupCompleted(String str) {
                        String unused = ThreeDSecure.sDFReferenceId = str;
                        try {
                            jSONObject.put("dfReferenceId", ThreeDSecure.sDFReferenceId);
                        } catch (JSONException unused2) {
                        }
                        threeDSecurePrepareLookupListener.onPrepareLookupComplete(threeDSecureRequest, jSONObject.toString());
                    }

                    public void onValidated(ValidateResponse validateResponse, String str) {
                        threeDSecurePrepareLookupListener.onPrepareLookupComplete(threeDSecureRequest, jSONObject.toString());
                    }
                });
            }
        });
    }

    public static void initializeChallengeWithLookupResponse(BraintreeFragment braintreeFragment, String str) {
        initializeChallengeWithLookupResponse(braintreeFragment, (ThreeDSecureRequest) null, str);
    }

    public static void initializeChallengeWithLookupResponse(BraintreeFragment braintreeFragment, ThreeDSecureRequest threeDSecureRequest, String str) {
        try {
            ThreeDSecureLookup fromJson = ThreeDSecureLookup.fromJson(str);
            boolean z = fromJson.getAcsUrl() != null;
            String threeDSecureVersion = fromJson.getThreeDSecureVersion();
            if (!z) {
                completeVerificationFlowWithNoncePayload(braintreeFragment, fromJson.getCardNonce());
            } else if (!threeDSecureVersion.startsWith("2.")) {
                braintreeFragment.browserSwitch((int) BraintreeRequestCodes.THREE_D_SECURE, ThreeDSecureV1BrowserSwitchHelper.getUrl(braintreeFragment.getReturnUrlScheme(), braintreeFragment.getConfiguration().getAssetsUrl(), threeDSecureRequest, fromJson));
            } else {
                performCardinalAuthentication(braintreeFragment, fromJson);
            }
        } catch (JSONException e) {
            braintreeFragment.postCallback((Exception) e);
        }
    }

    /* access modifiers changed from: private */
    public static void configureCardinal(BraintreeFragment braintreeFragment, Configuration configuration, ThreeDSecureRequest threeDSecureRequest) {
        CardinalEnvironment cardinalEnvironment = CardinalEnvironment.STAGING;
        if (a.d.equalsIgnoreCase(configuration.getEnvironment())) {
            cardinalEnvironment = CardinalEnvironment.PRODUCTION;
        }
        CardinalConfigurationParameters cardinalConfigurationParameters = new CardinalConfigurationParameters();
        cardinalConfigurationParameters.setEnvironment(cardinalEnvironment);
        cardinalConfigurationParameters.setRequestTimeout(8000);
        cardinalConfigurationParameters.setEnableQuickAuth(false);
        cardinalConfigurationParameters.setEnableDFSync(true);
        cardinalConfigurationParameters.setUICustomization(threeDSecureRequest.getUiCustomization());
        Cardinal.getInstance().configure(braintreeFragment.getApplicationContext(), cardinalConfigurationParameters);
    }

    static void authenticateCardinalJWT(final BraintreeFragment braintreeFragment, ThreeDSecureLookup threeDSecureLookup, String str) {
        final CardNonce cardNonce = threeDSecureLookup.getCardNonce();
        braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.upgrade-payment-method.started");
        String nonce = cardNonce.getNonce();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("jwt", str);
            jSONObject.put("paymentMethodNonce", nonce);
        } catch (JSONException unused) {
        }
        BraintreeHttpClient httpClient = braintreeFragment.getHttpClient();
        httpClient.post(TokenizationClient.versionedPath("payment_methods/" + nonce + "/three_d_secure/authenticate_from_jwt"), jSONObject.toString(), new HttpResponseCallback() {
            public void success(String str) {
                ThreeDSecureAuthenticationResponse fromJson = ThreeDSecureAuthenticationResponse.fromJson(str);
                CardNonce nonceWithAuthenticationDetails = ThreeDSecureAuthenticationResponse.getNonceWithAuthenticationDetails(str, cardNonce);
                if (fromJson.getErrors() != null) {
                    braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.upgrade-payment-method.failure.returned-lookup-nonce");
                    nonceWithAuthenticationDetails.getThreeDSecureInfo().setErrorMessage(fromJson.getErrors());
                    ThreeDSecure.completeVerificationFlowWithNoncePayload(braintreeFragment, nonceWithAuthenticationDetails);
                    return;
                }
                braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.upgrade-payment-method.succeeded");
                ThreeDSecure.completeVerificationFlowWithNoncePayload(braintreeFragment, nonceWithAuthenticationDetails);
            }

            public void failure(Exception exc) {
                braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.upgrade-payment-method.errored");
                braintreeFragment.postCallback(exc);
            }
        });
    }

    protected static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        if (i == -1) {
            Uri data = intent.getData();
            if (data != null) {
                String queryParameter = data.getQueryParameter("auth_response");
                ThreeDSecureAuthenticationResponse fromJson = ThreeDSecureAuthenticationResponse.fromJson(queryParameter);
                if (fromJson.isSuccess()) {
                    completeVerificationFlowWithNoncePayload(braintreeFragment, fromJson.getCardNonce());
                } else {
                    braintreeFragment.postCallback((Exception) new ErrorWithResponse(422, queryParameter));
                }
            } else {
                ThreeDSecureLookup threeDSecureLookup = (ThreeDSecureLookup) intent.getParcelableExtra("com.braintreepayments.api.ThreeDSecureActivity.EXTRA_THREE_D_SECURE_LOOKUP");
                ValidateResponse serializableExtra = intent.getSerializableExtra("com.braintreepayments.api.ThreeDSecureActivity.EXTRA_VALIDATION_RESPONSE");
                String stringExtra = intent.getStringExtra("com.braintreepayments.api.ThreeDSecureActivity.EXTRA_JWT");
                braintreeFragment.sendAnalyticsEvent(String.format("three-d-secure.verification-flow.cardinal-sdk.action-code.%s", new Object[]{serializableExtra.getActionCode().name().toLowerCase()}));
                switch (AnonymousClass8.$SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode[serializableExtra.getActionCode().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        authenticateCardinalJWT(braintreeFragment, threeDSecureLookup, stringExtra);
                        braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.completed");
                        return;
                    case 4:
                    case 5:
                        braintreeFragment.postCallback((Exception) new BraintreeException(serializableExtra.getErrorDescription()));
                        braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.failed");
                        return;
                    case 6:
                        braintreeFragment.postCancelCallback(BraintreeRequestCodes.THREE_D_SECURE);
                        braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.canceled");
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* renamed from: com.braintreepayments.api.ThreeDSecure$8  reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode[] r0 = com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode = r0
                com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode r1 = com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode.FAILURE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode r1 = com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode.SUCCESS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode r1 = com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode.NOACTION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode r1 = com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode.ERROR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode r1 = com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode.TIMEOUT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$cardinalcommerce$cardinalmobilesdk$models$CardinalActionCode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode r1 = com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode.CANCEL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.ThreeDSecure.AnonymousClass8.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static void completeVerificationFlowWithNoncePayload(BraintreeFragment braintreeFragment, CardNonce cardNonce) {
        ThreeDSecureInfo threeDSecureInfo = cardNonce.getThreeDSecureInfo();
        braintreeFragment.sendAnalyticsEvent(String.format("three-d-secure.verification-flow.liability-shifted.%b", new Object[]{Boolean.valueOf(threeDSecureInfo.isLiabilityShifted())}));
        braintreeFragment.sendAnalyticsEvent(String.format("three-d-secure.verification-flow.liability-shift-possible.%b", new Object[]{Boolean.valueOf(threeDSecureInfo.isLiabilityShiftPossible())}));
        braintreeFragment.postCallback((PaymentMethodNonce) cardNonce);
    }

    /* access modifiers changed from: private */
    public static void performThreeDSecureLookup(final BraintreeFragment braintreeFragment, final ThreeDSecureRequest threeDSecureRequest, final ThreeDSecureLookupListener threeDSecureLookupListener) {
        BraintreeHttpClient httpClient = braintreeFragment.getHttpClient();
        httpClient.post(TokenizationClient.versionedPath("payment_methods/" + threeDSecureRequest.getNonce() + "/three_d_secure/lookup"), threeDSecureRequest.build(sDFReferenceId), new HttpResponseCallback() {
            public void success(String str) {
                try {
                    threeDSecureLookupListener.onLookupComplete(threeDSecureRequest, ThreeDSecureLookup.fromJson(str));
                } catch (JSONException e) {
                    braintreeFragment.postCallback((Exception) e);
                }
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
            }
        });
    }

    private static void performCardinalAuthentication(BraintreeFragment braintreeFragment, ThreeDSecureLookup threeDSecureLookup) {
        braintreeFragment.sendAnalyticsEvent("three-d-secure.verification-flow.started");
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.braintreepayments.api.ThreeDSecureActivity.EXTRA_THREE_D_SECURE_LOOKUP", threeDSecureLookup);
        Intent intent = new Intent(braintreeFragment.getApplicationContext(), ThreeDSecureActivity.class);
        intent.putExtras(bundle);
        braintreeFragment.startActivityForResult(intent, BraintreeRequestCodes.THREE_D_SECURE);
    }
}
