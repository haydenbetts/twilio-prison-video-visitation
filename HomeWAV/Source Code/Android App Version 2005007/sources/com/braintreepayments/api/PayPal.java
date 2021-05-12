package com.braintreepayments.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.BrowserSwitchException;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PayPalApprovalCallback;
import com.braintreepayments.api.interfaces.PayPalApprovalHandler;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.internal.ManifestValidator;
import com.braintreepayments.api.models.BraintreeRequestCodes;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalAccountBuilder;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PayPalConfiguration;
import com.braintreepayments.api.models.PayPalPaymentResource;
import com.braintreepayments.api.models.PayPalProductAttributes;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.PostalAddress;
import com.braintreepayments.api.models.PostalAddressParser;
import com.paypal.android.sdk.onetouch.core.BillingAgreementRequest;
import com.paypal.android.sdk.onetouch.core.CheckoutRequest;
import com.paypal.android.sdk.onetouch.core.PayPalLineItem;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.Result;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import com.paypal.android.sdk.onetouch.core.sdk.PendingRequest;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPal {
    private static final String ADDRESS_OVERRIDE_KEY = "address_override";
    private static final String AMOUNT_KEY = "amount";
    private static final String AUTHORIZATION_FINGERPRINT_KEY = "authorization_fingerprint";
    private static final String CANCEL_URL_KEY = "cancel_url";
    public static final String CHARGE_PATTERN = "charge_pattern";
    private static final String CREATE_SINGLE_PAYMENT_ENDPOINT = "paypal_hermes/create_payment_resource";
    private static final String CURRENCY_ISO_CODE_KEY = "currency_iso_code";
    private static final String DESCRIPTION_KEY = "description";
    private static final String DISPLAY_NAME_KEY = "brand_name";
    private static final String EXPERIENCE_PROFILE_KEY = "experience_profile";
    private static final String INTENT_KEY = "intent";
    private static final String LANDING_PAGE_TYPE_KEY = "landing_page_type";
    private static final String LINE_ITEMS_KEY = "line_items";
    private static final String LOCALE_CODE_KEY = "locale_code";
    private static final String MERCHANT_ACCOUNT_ID = "merchant_account_id";
    public static final String NAME = "name";
    private static final String NO_SHIPPING_KEY = "no_shipping";
    private static final String OFFER_CREDIT_KEY = "offer_paypal_credit";
    private static final String PAYPAL_REQUEST_KEY = "com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY";
    public static final String PRODUCT_ATTRIBUTES = "product_attributes";
    public static final String PRODUCT_CODE = "product_code";
    private static final String REQUEST_KEY = "com.braintreepayments.api.PayPal.REQUEST_KEY";
    private static final String REQUEST_TYPE_KEY = "com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY";
    private static final String RETURN_URL_KEY = "return_url";
    private static final String SETUP_BILLING_AGREEMENT_ENDPOINT = "paypal_hermes/setup_billing_agreement";
    private static final String SHIPPING_ADDRESS_KEY = "shipping_address";
    private static final String TOKENIZATION_KEY = "client_key";
    private static final String USER_ACTION_KEY = "useraction";

    public static void requestBillingAgreement(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest) {
        requestBillingAgreement(braintreeFragment, payPalRequest, (PayPalApprovalHandler) null);
    }

    public static void requestBillingAgreement(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest, PayPalApprovalHandler payPalApprovalHandler) {
        if (payPalRequest.getAmount() == null) {
            braintreeFragment.sendAnalyticsEvent("paypal.billing-agreement.selected");
            if (payPalRequest.shouldOfferCredit()) {
                braintreeFragment.sendAnalyticsEvent("paypal.billing-agreement.credit.offered");
            }
            requestOneTimePayment(braintreeFragment, payPalRequest, true, payPalApprovalHandler);
            return;
        }
        braintreeFragment.postCallback((Exception) new BraintreeException("There must be no amount specified for the Billing Agreement flow"));
    }

    public static void requestOneTimePayment(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest) {
        requestOneTimePayment(braintreeFragment, payPalRequest, (PayPalApprovalHandler) null);
    }

    public static void requestOneTimePayment(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest, PayPalApprovalHandler payPalApprovalHandler) {
        if (payPalRequest.getAmount() != null) {
            braintreeFragment.sendAnalyticsEvent("paypal.single-payment.selected");
            if (payPalRequest.shouldOfferCredit()) {
                braintreeFragment.sendAnalyticsEvent("paypal.single-payment.credit.offered");
            }
            requestOneTimePayment(braintreeFragment, payPalRequest, false, payPalApprovalHandler);
            return;
        }
        braintreeFragment.postCallback((Exception) new BraintreeException("An amount must be specified for the Single Payment flow."));
    }

    private static void requestOneTimePayment(final BraintreeFragment braintreeFragment, final PayPalRequest payPalRequest, final boolean z, final PayPalApprovalHandler payPalApprovalHandler) {
        final AnonymousClass1 r0 = new HttpResponseCallback() {
            public void success(String str) {
                Request request;
                try {
                    String builder = Uri.parse(PayPalPaymentResource.fromJson(str).getRedirectUrl()).buildUpon().appendQueryParameter(PayPal.USER_ACTION_KEY, payPalRequest.getUserAction()).toString();
                    if (z) {
                        request = PayPal.getBillingAgreementRequest(braintreeFragment, builder);
                    } else {
                        request = PayPal.getCheckoutRequest(braintreeFragment, builder);
                    }
                    PayPal.startPayPal(braintreeFragment, request, payPalApprovalHandler);
                } catch (JSONException e) {
                    braintreeFragment.postCallback((Exception) e);
                }
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
            }
        };
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (!configuration.isPayPalEnabled()) {
                    braintreeFragment.postCallback((Exception) new BraintreeException("PayPal is not enabled. See https://developers.braintreepayments.com/guides/paypal/overview/android/ for more information."));
                } else if (!PayPal.isManifestValid(braintreeFragment)) {
                    braintreeFragment.sendAnalyticsEvent("paypal.invalid-manifest");
                    braintreeFragment.postCallback((Exception) new BraintreeException("BraintreeBrowserSwitchActivity missing, incorrectly configured in AndroidManifest.xml or another app defines the same browser switch url as this app. See https://developers.braintreepayments.com/guides/client-sdk/android/#browser-switch for the correct configuration"));
                } else {
                    try {
                        PayPal.persistPayPalRequest(braintreeFragment.getApplicationContext(), payPalRequest);
                        PayPal.createPaymentResource(braintreeFragment, payPalRequest, z, r0);
                    } catch (JSONException e) {
                        braintreeFragment.postCallback((Exception) e);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void createPaymentResource(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest, boolean z, HttpResponseCallback httpResponseCallback) throws JSONException {
        JSONObject jSONObject;
        String currencyCode = payPalRequest.getCurrencyCode();
        if (currencyCode == null) {
            currencyCode = braintreeFragment.getConfiguration().getPayPal().getCurrencyIsoCode();
        }
        CheckoutRequest checkoutRequest = getCheckoutRequest(braintreeFragment, (String) null);
        JSONObject put = new JSONObject().put(RETURN_URL_KEY, checkoutRequest.getSuccessUrl()).put(CANCEL_URL_KEY, checkoutRequest.getCancelUrl()).put(OFFER_CREDIT_KEY, payPalRequest.shouldOfferCredit());
        if (braintreeFragment.getAuthorization() instanceof ClientToken) {
            put.put(AUTHORIZATION_FINGERPRINT_KEY, braintreeFragment.getAuthorization().getBearer());
        } else {
            put.put(TOKENIZATION_KEY, braintreeFragment.getAuthorization().getBearer());
        }
        if (!z) {
            put.put(AMOUNT_KEY, payPalRequest.getAmount()).put(CURRENCY_ISO_CODE_KEY, currencyCode).put(INTENT_KEY, payPalRequest.getIntent());
            if (!payPalRequest.getLineItems().isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                Iterator<PayPalLineItem> it = payPalRequest.getLineItems().iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().toJson());
                }
                put.put(LINE_ITEMS_KEY, jSONArray);
            }
        } else {
            if (!TextUtils.isEmpty(payPalRequest.getBillingAgreementDescription())) {
                put.put(DESCRIPTION_KEY, payPalRequest.getBillingAgreementDescription());
            }
            PayPalProductAttributes productAttributes = payPalRequest.getProductAttributes();
            if (productAttributes != null) {
                put.put(PRODUCT_ATTRIBUTES, new JSONObject().put(CHARGE_PATTERN, productAttributes.getChargePattern()).put("name", productAttributes.getName()).put(PRODUCT_CODE, productAttributes.getProductCode()));
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(NO_SHIPPING_KEY, !payPalRequest.isShippingAddressRequired());
        jSONObject2.put(LANDING_PAGE_TYPE_KEY, payPalRequest.getLandingPageType());
        String displayName = payPalRequest.getDisplayName();
        if (TextUtils.isEmpty(displayName)) {
            displayName = braintreeFragment.getConfiguration().getPayPal().getDisplayName();
        }
        jSONObject2.put(DISPLAY_NAME_KEY, displayName);
        if (payPalRequest.getLocaleCode() != null) {
            jSONObject2.put(LOCALE_CODE_KEY, payPalRequest.getLocaleCode());
        }
        if (payPalRequest.getShippingAddressOverride() != null) {
            jSONObject2.put(ADDRESS_OVERRIDE_KEY, !payPalRequest.isShippingAddressEditable());
            if (z) {
                jSONObject = new JSONObject();
                put.put(SHIPPING_ADDRESS_KEY, jSONObject);
            } else {
                jSONObject = put;
            }
            PostalAddress shippingAddressOverride = payPalRequest.getShippingAddressOverride();
            jSONObject.put(PostalAddressParser.LINE_1_KEY, shippingAddressOverride.getStreetAddress());
            jSONObject.put(PostalAddressParser.LINE_2_KEY, shippingAddressOverride.getExtendedAddress());
            jSONObject.put("city", shippingAddressOverride.getLocality());
            jSONObject.put("state", shippingAddressOverride.getRegion());
            jSONObject.put("postal_code", shippingAddressOverride.getPostalCode());
            jSONObject.put(PostalAddressParser.COUNTRY_CODE_UNDERSCORE_KEY, shippingAddressOverride.getCountryCodeAlpha2());
            jSONObject.put(PostalAddressParser.RECIPIENT_NAME_UNDERSCORE_KEY, shippingAddressOverride.getRecipientName());
        } else {
            jSONObject2.put(ADDRESS_OVERRIDE_KEY, false);
        }
        if (payPalRequest.getMerchantAccountId() != null) {
            put.put(MERCHANT_ACCOUNT_ID, payPalRequest.getMerchantAccountId());
        }
        put.put(EXPERIENCE_PROFILE_KEY, jSONObject2);
        String str = z ? SETUP_BILLING_AGREEMENT_ENDPOINT : CREATE_SINGLE_PAYMENT_ENDPOINT;
        braintreeFragment.getHttpClient().post("/v1/" + str, put.toString(), httpResponseCallback);
    }

    /* access modifiers changed from: private */
    public static void startPayPal(final BraintreeFragment braintreeFragment, Request request, PayPalApprovalHandler payPalApprovalHandler) {
        AnonymousClass3 r1;
        persistRequest(braintreeFragment.getApplicationContext(), request);
        if (payPalApprovalHandler == null) {
            payPalApprovalHandler = getDefaultApprovalHandler(braintreeFragment);
            r1 = null;
        } else {
            r1 = new PayPalApprovalCallback() {
                public void onComplete(Intent intent) {
                    PayPal.onActivityResult(braintreeFragment, -1, intent);
                }

                public void onCancel() {
                    braintreeFragment.postCancelCallback(BraintreeRequestCodes.PAYPAL);
                }
            };
        }
        payPalApprovalHandler.handleApproval(request, r1);
    }

    private static PayPalApprovalHandler getDefaultApprovalHandler(final BraintreeFragment braintreeFragment) {
        return new PayPalApprovalHandler() {
            public void handleApproval(Request request, PayPalApprovalCallback payPalApprovalCallback) {
                if (braintreeFragment.isActive()) {
                    PendingRequest startIntent = PayPalOneTouchCore.getStartIntent(braintreeFragment.getApplicationContext(), request);
                    String access$300 = PayPal.paymentTypeForRequest(request);
                    if (startIntent.isSuccess() && startIntent.getRequestTarget() == RequestTarget.wallet) {
                        BraintreeFragment braintreeFragment = braintreeFragment;
                        braintreeFragment.sendAnalyticsEvent(access$300 + ".app-switch.started");
                        braintreeFragment.startActivityForResult(startIntent.getIntent(), BraintreeRequestCodes.PAYPAL);
                    } else if (!startIntent.isSuccess() || startIntent.getRequestTarget() != RequestTarget.browser) {
                        BraintreeFragment braintreeFragment2 = braintreeFragment;
                        braintreeFragment2.sendAnalyticsEvent(access$300 + ".initiate.failed");
                    } else {
                        BraintreeFragment braintreeFragment3 = braintreeFragment;
                        braintreeFragment3.sendAnalyticsEvent(access$300 + ".browser-switch.started");
                        braintreeFragment.browserSwitch((int) BraintreeRequestCodes.PAYPAL, startIntent.getIntent());
                    }
                }
            }
        };
    }

    protected static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        Request persistedRequest = getPersistedRequest(braintreeFragment.getApplicationContext());
        String str = paymentTypeForRequest(persistedRequest) + "." + switchTypeForIntent(intent);
        if (i != -1 || intent == null || persistedRequest == null) {
            braintreeFragment.sendAnalyticsEvent(str + ".canceled");
            if (i != 0) {
                braintreeFragment.postCancelCallback(BraintreeRequestCodes.PAYPAL);
                return;
            }
            return;
        }
        Result parseResponse = PayPalOneTouchCore.parseResponse(braintreeFragment.getApplicationContext(), persistedRequest, intent);
        int i2 = AnonymousClass6.$SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType[parseResponse.getResultType().ordinal()];
        if (i2 == 1) {
            braintreeFragment.postCallback((Exception) new BrowserSwitchException(parseResponse.getError().getMessage()));
            braintreeFragment.sendAnalyticsEvent(str + ".failed");
        } else if (i2 == 2) {
            braintreeFragment.postCancelCallback(BraintreeRequestCodes.PAYPAL);
            braintreeFragment.sendAnalyticsEvent(str + ".canceled");
        } else if (i2 == 3) {
            onSuccess(braintreeFragment, intent, persistedRequest, parseResponse);
            braintreeFragment.sendAnalyticsEvent(str + ".succeeded");
        }
    }

    /* renamed from: com.braintreepayments.api.PayPal$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.paypal.android.sdk.onetouch.core.enums.ResultType[] r0 = com.paypal.android.sdk.onetouch.core.enums.ResultType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType = r0
                com.paypal.android.sdk.onetouch.core.enums.ResultType r1 = com.paypal.android.sdk.onetouch.core.enums.ResultType.Error     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.paypal.android.sdk.onetouch.core.enums.ResultType r1 = com.paypal.android.sdk.onetouch.core.enums.ResultType.Cancel     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.paypal.android.sdk.onetouch.core.enums.ResultType r1 = com.paypal.android.sdk.onetouch.core.enums.ResultType.Success     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.PayPal.AnonymousClass6.<clinit>():void");
        }
    }

    private static void onSuccess(final BraintreeFragment braintreeFragment, Intent intent, Request request, Result result) {
        TokenizationClient.tokenize(braintreeFragment, parseResponse(getPersistedPayPalRequest(braintreeFragment.getApplicationContext()), request, result, intent), new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                if ((paymentMethodNonce instanceof PayPalAccountNonce) && ((PayPalAccountNonce) paymentMethodNonce).getCreditFinancing() != null) {
                    braintreeFragment.sendAnalyticsEvent("paypal.credit.accepted");
                }
                braintreeFragment.postCallback(paymentMethodNonce);
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
            }
        });
    }

    private static PayPalAccountBuilder parseResponse(PayPalRequest payPalRequest, Request request, Result result, Intent intent) {
        PayPalAccountBuilder clientMetadataId = new PayPalAccountBuilder().clientMetadataId(request.getClientMetadataId());
        if (!(payPalRequest == null || payPalRequest.getMerchantAccountId() == null)) {
            clientMetadataId.merchantAccountId(payPalRequest.getMerchantAccountId());
        }
        if ((request instanceof CheckoutRequest) && payPalRequest != null) {
            clientMetadataId.intent(payPalRequest.getIntent());
        }
        if (isAppSwitch(intent)) {
            clientMetadataId.source("paypal-app");
        } else {
            clientMetadataId.source("paypal-browser");
        }
        clientMetadataId.oneTouchCoreData(result.getResponse());
        return clientMetadataId;
    }

    static CheckoutRequest getCheckoutRequest(BraintreeFragment braintreeFragment, String str) {
        String queryParameter;
        CheckoutRequest approvalURL = ((CheckoutRequest) populateRequestData(braintreeFragment, new CheckoutRequest())).approvalURL(str);
        if (!(str == null || (queryParameter = Uri.parse(str).getQueryParameter("token")) == null)) {
            approvalURL.pairingId(braintreeFragment.getApplicationContext(), queryParameter);
        }
        return approvalURL;
    }

    static BillingAgreementRequest getBillingAgreementRequest(BraintreeFragment braintreeFragment, String str) {
        String queryParameter;
        BillingAgreementRequest approvalURL = ((BillingAgreementRequest) populateRequestData(braintreeFragment, new BillingAgreementRequest())).approvalURL(str);
        if (!(str == null || (queryParameter = Uri.parse(str).getQueryParameter("ba_token")) == null)) {
            approvalURL.pairingId(braintreeFragment.getApplicationContext(), queryParameter);
        }
        return approvalURL;
    }

    private static <T extends Request> T populateRequestData(BraintreeFragment braintreeFragment, T t) {
        PayPalConfiguration payPal = braintreeFragment.getConfiguration().getPayPal();
        String environment = payPal.getEnvironment();
        environment.hashCode();
        boolean equals = environment.equals("offline");
        String str = EnvironmentManager.LIVE;
        if (equals) {
            str = EnvironmentManager.MOCK;
        } else if (!environment.equals(str)) {
            str = payPal.getEnvironment();
        }
        String clientId = payPal.getClientId();
        if (clientId == null && EnvironmentManager.MOCK.equals(str)) {
            clientId = "FAKE-PAYPAL-CLIENT-ID";
        }
        t.environment(str).clientId(clientId).cancelUrl(braintreeFragment.getReturnUrlScheme(), "cancel").successUrl(braintreeFragment.getReturnUrlScheme(), "success");
        return t;
    }

    private static boolean isAppSwitch(Intent intent) {
        return intent.getData() == null;
    }

    /* access modifiers changed from: private */
    public static void persistPayPalRequest(Context context, PayPalRequest payPalRequest) {
        Parcel obtain = Parcel.obtain();
        payPalRequest.writeToParcel(obtain, 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString(PAYPAL_REQUEST_KEY, Base64.encodeToString(obtain.marshall(), 0)).apply();
    }

    private static void persistRequest(Context context, Request request) {
        Parcel obtain = Parcel.obtain();
        request.writeToParcel(obtain, 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString(REQUEST_KEY, Base64.encodeToString(obtain.marshall(), 0)).putString(REQUEST_TYPE_KEY, request.getClass().getSimpleName()).apply();
    }

    /* JADX INFO: finally extract failed */
    private static PayPalRequest getPersistedPayPalRequest(Context context) {
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(context);
        try {
            byte[] decode = Base64.decode(sharedPreferences.getString(PAYPAL_REQUEST_KEY, ""), 0);
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            PayPalRequest createFromParcel = PayPalRequest.CREATOR.createFromParcel(obtain);
            sharedPreferences.edit().remove(PAYPAL_REQUEST_KEY).apply();
            return createFromParcel;
        } catch (Exception unused) {
            sharedPreferences.edit().remove(PAYPAL_REQUEST_KEY).apply();
            return null;
        } catch (Throwable th) {
            sharedPreferences.edit().remove(PAYPAL_REQUEST_KEY).apply();
            throw th;
        }
    }

    private static Request getPersistedRequest(Context context) {
        Request createFromParcel;
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(context);
        try {
            byte[] decode = Base64.decode(sharedPreferences.getString(REQUEST_KEY, ""), 0);
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            String string = sharedPreferences.getString(REQUEST_TYPE_KEY, "");
            if (BillingAgreementRequest.class.getSimpleName().equals(string)) {
                createFromParcel = BillingAgreementRequest.CREATOR.createFromParcel(obtain);
            } else {
                if (CheckoutRequest.class.getSimpleName().equals(string)) {
                    createFromParcel = CheckoutRequest.CREATOR.createFromParcel(obtain);
                }
                sharedPreferences.edit().remove(REQUEST_KEY).remove(REQUEST_TYPE_KEY).apply();
                return null;
            }
            sharedPreferences.edit().remove(REQUEST_KEY).remove(REQUEST_TYPE_KEY).apply();
            return createFromParcel;
        } catch (Exception unused) {
        } catch (Throwable th) {
            sharedPreferences.edit().remove(REQUEST_KEY).remove(REQUEST_TYPE_KEY).apply();
            throw th;
        }
    }

    static boolean isManifestValid(BraintreeFragment braintreeFragment) {
        return ManifestValidator.isUrlSchemeDeclaredInAndroidManifest(braintreeFragment.getApplicationContext(), braintreeFragment.getReturnUrlScheme(), BraintreeBrowserSwitchActivity.class);
    }

    private static String switchTypeForIntent(Intent intent) {
        if (intent != null) {
            return (intent.getData() != null || intent.getBooleanExtra(BraintreeFragment.EXTRA_WAS_BROWSER_SWITCH_RESULT, false)) ? "browser-switch" : "app-switch";
        }
        return "unknown";
    }

    /* access modifiers changed from: private */
    public static String paymentTypeForRequest(Request request) {
        if (request instanceof BillingAgreementRequest) {
            return "paypal.billing-agreement";
        }
        return request instanceof CheckoutRequest ? "paypal.single-payment" : "paypal.unknown";
    }
}
