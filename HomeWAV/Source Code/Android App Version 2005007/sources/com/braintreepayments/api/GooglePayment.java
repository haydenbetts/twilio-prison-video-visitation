package com.braintreepayments.api;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.text.TextUtils;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.exceptions.GoogleApiClientException;
import com.braintreepayments.api.exceptions.GooglePaymentException;
import com.braintreepayments.api.googlepayment.BuildConfig;
import com.braintreepayments.api.googlepayment.R;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.TokenizationParametersListener;
import com.braintreepayments.api.internal.ManifestValidator;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.BraintreeRequestCodes;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.GooglePaymentConfiguration;
import com.braintreepayments.api.models.GooglePaymentRequest;
import com.braintreepayments.api.models.MetadataBuilder;
import com.braintreepayments.api.models.PaymentMethodNonceFactory;
import com.braintreepayments.api.models.ReadyForGooglePaymentRequest;
import com.braintreepayments.api.models.TokenizationKey;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.stripe.android.model.Card;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.json.matchers.VersionMatcher;
import java.util.ArrayList;
import java.util.Iterator;
import lib.android.paypal.com.magnessdk.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePayment {
    private static final String AMEX_NETWORK = "amex";
    private static final String CARD_PAYMENT_TYPE = "CARD";
    private static final String DISCOVER_NETWORK = "discover";
    private static final String MASTERCARD_NETWORK = "mastercard";
    private static final String PAYPAL_PAYMENT_TYPE = "PAYPAL";
    private static final String VISA_NETWORK = "visa";

    public static void isReadyToPay(BraintreeFragment braintreeFragment, BraintreeResponseListener<Boolean> braintreeResponseListener) {
        isReadyToPay(braintreeFragment, (ReadyForGooglePaymentRequest) null, braintreeResponseListener);
    }

    public static void isReadyToPay(final BraintreeFragment braintreeFragment, final ReadyForGooglePaymentRequest readyForGooglePaymentRequest, final BraintreeResponseListener<Boolean> braintreeResponseListener) {
        try {
            Class.forName(PaymentsClient.class.getName());
            braintreeFragment.waitForConfiguration(new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    if (!configuration.getGooglePayment().isEnabled(braintreeFragment.getApplicationContext())) {
                        braintreeResponseListener.onResponse(false);
                        return;
                    }
                    if (braintreeFragment.getActivity() == null) {
                        braintreeFragment.postCallback((Exception) new GoogleApiClientException(GoogleApiClientException.ErrorType.NotAttachedToActivity, 1));
                    }
                    PaymentsClient paymentsClient = Wallet.getPaymentsClient(braintreeFragment.getActivity(), new Wallet.WalletOptions.Builder().setEnvironment(GooglePayment.getEnvironment(configuration.getGooglePayment())).build());
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("apiVersion", 2).put("apiVersionMinor", 0).put("allowedPaymentMethods", new JSONArray().put(new JSONObject().put("type", GooglePayment.CARD_PAYMENT_TYPE).put("parameters", new JSONObject().put("allowedAuthMethods", new JSONArray().put("PAN_ONLY").put("CRYPTOGRAM_3DS")).put("allowedCardNetworks", GooglePayment.buildCardNetworks(braintreeFragment)))));
                        ReadyForGooglePaymentRequest readyForGooglePaymentRequest = readyForGooglePaymentRequest;
                        if (readyForGooglePaymentRequest != null) {
                            jSONObject.put("existingPaymentMethodRequired", readyForGooglePaymentRequest.isExistingPaymentMethodRequired());
                        }
                    } catch (JSONException unused) {
                    }
                    paymentsClient.isReadyToPay(IsReadyToPayRequest.fromJson(jSONObject.toString())).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                        public void onComplete(Task<Boolean> task) {
                            try {
                                braintreeResponseListener.onResponse(task.getResult(ApiException.class));
                            } catch (ApiException unused) {
                                braintreeResponseListener.onResponse(false);
                            }
                        }
                    });
                }
            });
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            braintreeResponseListener.onResponse(false);
        }
    }

    public static void getTokenizationParameters(final BraintreeFragment braintreeFragment, final TokenizationParametersListener tokenizationParametersListener) {
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                tokenizationParametersListener.onResult(GooglePayment.getTokenizationParameters(braintreeFragment), GooglePayment.getAllowedCardNetworks(braintreeFragment));
            }
        });
    }

    public static void requestPayment(final BraintreeFragment braintreeFragment, final GooglePaymentRequest googlePaymentRequest) {
        braintreeFragment.sendAnalyticsEvent("google-payment.selected");
        if (!validateManifest(braintreeFragment.getApplicationContext())) {
            braintreeFragment.postCallback((Exception) new BraintreeException("GooglePaymentActivity was not found in the Android manifest, or did not have a theme of R.style.bt_transparent_activity"));
            braintreeFragment.sendAnalyticsEvent("google-payment.failed");
        } else if (googlePaymentRequest == null) {
            braintreeFragment.postCallback((Exception) new BraintreeException("Cannot pass null GooglePaymentRequest to requestPayment"));
            braintreeFragment.sendAnalyticsEvent("google-payment.failed");
        } else if (googlePaymentRequest.getTransactionInfo() == null) {
            braintreeFragment.postCallback((Exception) new BraintreeException("Cannot pass null TransactionInfo to requestPayment"));
            braintreeFragment.sendAnalyticsEvent("google-payment.failed");
        } else {
            braintreeFragment.waitForConfiguration(new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    if (!configuration.getGooglePayment().isEnabled(braintreeFragment.getApplicationContext())) {
                        braintreeFragment.postCallback((Exception) new BraintreeException("Google Pay enabled is not enabled for your Braintree account, or Google Play Services are not configured correctly."));
                        return;
                    }
                    GooglePayment.setGooglePaymentRequestDefaults(braintreeFragment, configuration, googlePaymentRequest);
                    braintreeFragment.sendAnalyticsEvent("google-payment.started");
                    braintreeFragment.startActivityForResult(new Intent(braintreeFragment.getApplicationContext(), GooglePaymentActivity.class).putExtra("com.braintreepayments.api.EXTRA_ENVIRONMENT", GooglePayment.getEnvironment(configuration.getGooglePayment())).putExtra("com.braintreepayments.api.EXTRA_PAYMENT_DATA_REQUEST", PaymentDataRequest.fromJson(googlePaymentRequest.toJson())), BraintreeRequestCodes.GOOGLE_PAYMENT);
                }
            });
        }
    }

    public static void tokenize(BraintreeFragment braintreeFragment, PaymentData paymentData) {
        try {
            braintreeFragment.postCallback(PaymentMethodNonceFactory.fromString(paymentData.toJson()));
            braintreeFragment.sendAnalyticsEvent("google-payment.nonce-received");
        } catch (NullPointerException | JSONException unused) {
            braintreeFragment.sendAnalyticsEvent("google-payment.failed");
            try {
                braintreeFragment.postCallback((Exception) ErrorWithResponse.fromJson(new JSONObject(paymentData.toJson()).getJSONObject("paymentMethodData").getJSONObject("tokenizationData").getString("token")));
            } catch (NullPointerException | JSONException e) {
                braintreeFragment.postCallback(e);
            }
        }
    }

    static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        if (i == -1) {
            braintreeFragment.sendAnalyticsEvent("google-payment.authorized");
            tokenize(braintreeFragment, PaymentData.getFromIntent(intent));
        } else if (i == 1) {
            braintreeFragment.sendAnalyticsEvent("google-payment.failed");
            braintreeFragment.postCallback((Exception) new GooglePaymentException("An error was encountered during the Google Payments flow. See the status object in this exception for more details.", AutoResolveHelper.getStatusFromIntent(intent)));
        } else if (i == 0) {
            braintreeFragment.sendAnalyticsEvent("google-payment.canceled");
        }
    }

    static int getEnvironment(GooglePaymentConfiguration googlePaymentConfiguration) {
        return a.d.equals(googlePaymentConfiguration.getEnvironment()) ? 1 : 3;
    }

    static PaymentMethodTokenizationParameters getTokenizationParameters(BraintreeFragment braintreeFragment) {
        String str;
        JSONObject build = new MetadataBuilder().integration(braintreeFragment.getIntegrationType()).sessionId(braintreeFragment.getSessionId()).version().build();
        try {
            str = build.getString(VersionMatcher.ALTERNATE_VERSION_KEY);
        } catch (JSONException unused) {
            str = "3.7.0";
        }
        PaymentMethodTokenizationParameters.Builder addParameter = PaymentMethodTokenizationParameters.newBuilder().setPaymentMethodTokenizationType(1).addParameter("gateway", "braintree").addParameter("braintree:merchantId", braintreeFragment.getConfiguration().getMerchantId()).addParameter("braintree:authorizationFingerprint", braintreeFragment.getConfiguration().getGooglePayment().getGoogleAuthorizationFingerprint()).addParameter("braintree:apiVersion", "v1").addParameter("braintree:sdkVersion", str).addParameter("braintree:metadata", build.toString());
        if (braintreeFragment.getAuthorization() instanceof TokenizationKey) {
            addParameter.addParameter("braintree:clientKey", braintreeFragment.getAuthorization().getBearer());
        }
        return addParameter.build();
    }

    static ArrayList<Integer> getAllowedCardNetworks(BraintreeFragment braintreeFragment) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (String str : braintreeFragment.getConfiguration().getGooglePayment().getSupportedNetworks()) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2038717326:
                    if (str.equals(MASTERCARD_NETWORK)) {
                        c = 0;
                        break;
                    }
                    break;
                case 2997727:
                    if (str.equals(AMEX_NETWORK)) {
                        c = 1;
                        break;
                    }
                    break;
                case 3619905:
                    if (str.equals(VISA_NETWORK)) {
                        c = 2;
                        break;
                    }
                    break;
                case 273184745:
                    if (str.equals(DISCOVER_NETWORK)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    arrayList.add(4);
                    break;
                case 1:
                    arrayList.add(1);
                    break;
                case 2:
                    arrayList.add(5);
                    break;
                case 3:
                    arrayList.add(2);
                    break;
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static JSONArray buildCardNetworks(BraintreeFragment braintreeFragment) {
        JSONArray jSONArray = new JSONArray();
        Iterator<Integer> it = getAllowedCardNetworks(braintreeFragment).iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue == 1) {
                jSONArray.put("AMEX");
            } else if (intValue == 2) {
                jSONArray.put("DISCOVER");
            } else if (intValue == 3) {
                jSONArray.put(Card.JCB);
            } else if (intValue == 4) {
                jSONArray.put("MASTERCARD");
            } else if (intValue == 5) {
                jSONArray.put("VISA");
            }
        }
        return jSONArray;
    }

    private static JSONObject buildCardPaymentMethodParameters(GooglePaymentRequest googlePaymentRequest, BraintreeFragment braintreeFragment) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (googlePaymentRequest.getAllowedCardNetworksForType(CARD_PAYMENT_TYPE) == null) {
                JSONArray buildCardNetworks = buildCardNetworks(braintreeFragment);
                if (googlePaymentRequest.getAllowedAuthMethodsForType(CARD_PAYMENT_TYPE) == null) {
                    googlePaymentRequest.setAllowedAuthMethods(CARD_PAYMENT_TYPE, new JSONArray().put("PAN_ONLY").put("CRYPTOGRAM_3DS"));
                } else {
                    googlePaymentRequest.setAllowedAuthMethods(CARD_PAYMENT_TYPE, googlePaymentRequest.getAllowedAuthMethodsForType(CARD_PAYMENT_TYPE));
                }
                googlePaymentRequest.setAllowedCardNetworks(CARD_PAYMENT_TYPE, buildCardNetworks);
            }
            jSONObject.put("billingAddressRequired", googlePaymentRequest.isBillingAddressRequired()).put("allowPrepaidCards", googlePaymentRequest.getAllowPrepaidCards()).put("allowedAuthMethods", googlePaymentRequest.getAllowedAuthMethodsForType(CARD_PAYMENT_TYPE)).put("allowedCardNetworks", googlePaymentRequest.getAllowedCardNetworksForType(CARD_PAYMENT_TYPE));
            if (googlePaymentRequest.isBillingAddressRequired().booleanValue()) {
                jSONObject.put("billingAddressParameters", new JSONObject().put("format", googlePaymentRequest.billingAddressFormatToString()).put("phoneNumberRequired", googlePaymentRequest.isPhoneNumberRequired()));
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    private static JSONObject buildPayPalPaymentMethodParameters(BraintreeFragment braintreeFragment) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("purchase_context", new JSONObject().put("purchase_units", new JSONArray().put(new JSONObject().put("payee", new JSONObject().put("client_id", braintreeFragment.getConfiguration().getGooglePayment().getPaypalClientId())).put("recurring_payment", "true"))));
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    private static JSONObject buildCardTokenizationSpecification(BraintreeFragment braintreeFragment) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("gateway", "braintree").put("braintree:apiVersion", "v1").put("braintree:sdkVersion", BuildConfig.VERSION_NAME).put("braintree:merchantId", braintreeFragment.getConfiguration().getMerchantId()).put("braintree:metadata", new JSONObject().put("source", "client").put("integration", braintreeFragment.getIntegrationType()).put("sessionId", braintreeFragment.getSessionId()).put(VersionMatcher.ALTERNATE_VERSION_KEY, BuildConfig.VERSION_NAME).put("platform", ChannelRegistrationPayload.ANDROID_DEVICE_TYPE).toString());
            if (Authorization.isTokenizationKey(braintreeFragment.getAuthorization().toString())) {
                jSONObject2.put("braintree:clientKey", braintreeFragment.getAuthorization().toString());
            } else {
                jSONObject2.put("braintree:authorizationFingerprint", braintreeFragment.getConfiguration().getGooglePayment().getGoogleAuthorizationFingerprint());
            }
        } catch (JSONException unused) {
        }
        try {
            jSONObject.put("type", "PAYMENT_GATEWAY").put("parameters", jSONObject2);
        } catch (JSONException unused2) {
        }
        return jSONObject;
    }

    private static JSONObject buildPayPalTokenizationSpecification(BraintreeFragment braintreeFragment) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "PAYMENT_GATEWAY").put("parameters", new JSONObject().put("gateway", "braintree").put("braintree:apiVersion", "v1").put("braintree:sdkVersion", BuildConfig.VERSION_NAME).put("braintree:merchantId", braintreeFragment.getConfiguration().getMerchantId()).put("braintree:paypalClientId", braintreeFragment.getConfiguration().getGooglePayment().getPaypalClientId()).put("braintree:metadata", new JSONObject().put("source", "client").put("integration", braintreeFragment.getIntegrationType()).put("sessionId", braintreeFragment.getSessionId()).put(VersionMatcher.ALTERNATE_VERSION_KEY, BuildConfig.VERSION_NAME).put("platform", ChannelRegistrationPayload.ANDROID_DEVICE_TYPE).toString()));
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public static void setGooglePaymentRequestDefaults(BraintreeFragment braintreeFragment, Configuration configuration, GooglePaymentRequest googlePaymentRequest) {
        boolean z = false;
        if (googlePaymentRequest.isEmailRequired() == null) {
            googlePaymentRequest.emailRequired(false);
        }
        if (googlePaymentRequest.isPhoneNumberRequired() == null) {
            googlePaymentRequest.phoneNumberRequired(false);
        }
        if (googlePaymentRequest.isBillingAddressRequired() == null) {
            googlePaymentRequest.billingAddressRequired(false);
        }
        if (googlePaymentRequest.isBillingAddressRequired().booleanValue() && googlePaymentRequest.getBillingAddressFormat() == null) {
            googlePaymentRequest.billingAddressFormat(0);
        }
        if (googlePaymentRequest.isShippingAddressRequired() == null) {
            googlePaymentRequest.shippingAddressRequired(false);
        }
        if (googlePaymentRequest.getAllowPrepaidCards() == null) {
            googlePaymentRequest.allowPrepaidCards(true);
        }
        if (googlePaymentRequest.getAllowedPaymentMethod(CARD_PAYMENT_TYPE) == null) {
            googlePaymentRequest.setAllowedPaymentMethod(CARD_PAYMENT_TYPE, buildCardPaymentMethodParameters(googlePaymentRequest, braintreeFragment));
        }
        if (googlePaymentRequest.getTokenizationSpecificationForType(CARD_PAYMENT_TYPE) == null) {
            googlePaymentRequest.setTokenizationSpecificationForType(CARD_PAYMENT_TYPE, buildCardTokenizationSpecification(braintreeFragment));
        }
        if (googlePaymentRequest.isPayPalEnabled().booleanValue() && !TextUtils.isEmpty(configuration.getGooglePayment().getPaypalClientId())) {
            z = true;
        }
        if (z) {
            if (googlePaymentRequest.getAllowedPaymentMethod(PAYPAL_PAYMENT_TYPE) == null) {
                googlePaymentRequest.setAllowedPaymentMethod(PAYPAL_PAYMENT_TYPE, buildPayPalPaymentMethodParameters(braintreeFragment));
            }
            if (googlePaymentRequest.getTokenizationSpecificationForType(PAYPAL_PAYMENT_TYPE) == null) {
                googlePaymentRequest.setTokenizationSpecificationForType(PAYPAL_PAYMENT_TYPE, buildPayPalTokenizationSpecification(braintreeFragment));
            }
        }
        googlePaymentRequest.environment(configuration.getGooglePayment().getEnvironment());
    }

    private static boolean validateManifest(Context context) {
        ActivityInfo activityInfo = ManifestValidator.getActivityInfo(context, GooglePaymentActivity.class);
        return activityInfo != null && activityInfo.getThemeResource() == R.style.bt_transparent_activity;
    }
}
