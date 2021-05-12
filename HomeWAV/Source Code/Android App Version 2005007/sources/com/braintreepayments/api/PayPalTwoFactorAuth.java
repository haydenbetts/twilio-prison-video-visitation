package com.braintreepayments.api;

import android.content.Intent;
import android.net.Uri;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PayPalTwoFactorAuthCallback;
import com.braintreepayments.api.models.BraintreeRequestCodes;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PayPalTwoFactorAuthRequest;
import com.braintreepayments.api.models.PayPalTwoFactorAuthResponse;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.paypal.android.sdk.data.collector.PayPalDataCollector;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalTwoFactorAuth {
    public static final String CANCEL_PATH = "cancel";
    private static final String CREATE_SINGLE_PAYMENT_PATH = "paypal_hermes/create_payment_resource";
    private static final String PAYPAL_ACCOUNT_PATH = "payment_methods/paypal_accounts";
    public static final String SUCCESS_PATH = "success";

    public static void performTwoFactorLookup(final BraintreeFragment braintreeFragment, final PayPalTwoFactorAuthRequest payPalTwoFactorAuthRequest, final PayPalTwoFactorAuthCallback payPalTwoFactorAuthCallback) {
        braintreeFragment.sendAnalyticsEvent("paypal-two-factor.perform-two-factor-lookup.started");
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (!configuration.isPayPalEnabled()) {
                    braintreeFragment.postCallback((Exception) new BraintreeException("PayPal is not enabled. See https://developers.braintreepayments.com/guides/paypal/overview/android/ for more information."));
                } else if (!PayPal.isManifestValid(braintreeFragment)) {
                    braintreeFragment.postCallback((Exception) new BraintreeException("BraintreeBrowserSwitchActivity missing, incorrectly configured in AndroidManifest.xml or another app defines the same browser switch url as this app. See https://developers.braintreepayments.com/guides/client-sdk/android/#browser-switch for the correct configuration"));
                } else {
                    PayPalTwoFactorAuth.paypalHermesLookup(braintreeFragment, payPalTwoFactorAuthRequest, payPalTwoFactorAuthCallback);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void paypalHermesLookup(final BraintreeFragment braintreeFragment, PayPalTwoFactorAuthRequest payPalTwoFactorAuthRequest, final PayPalTwoFactorAuthCallback payPalTwoFactorAuthCallback) {
        braintreeFragment.getHttpClient().post("/v1/paypal_hermes/create_payment_resource", payPalTwoFactorAuthRequest.toJson(braintreeFragment.getAuthorization().getBearer(), braintreeFragment.getReturnUrlScheme()), new HttpResponseCallback() {
            public void success(String str) {
                try {
                    PayPalTwoFactorAuthResponse fromJson = PayPalTwoFactorAuthResponse.fromJson(str, braintreeFragment.getAuthorization().getBearer());
                    braintreeFragment.sendAnalyticsEvent("paypal-two-factor.paypal-hermes-lookup.succeeded");
                    PayPalTwoFactorAuth.fetchPayPalAccount(braintreeFragment, fromJson, payPalTwoFactorAuthCallback);
                } catch (JSONException e) {
                    braintreeFragment.sendAnalyticsEvent("paypal-two-factor.paypal-hermes-lookup.failed");
                    payPalTwoFactorAuthCallback.onLookupFailure(e);
                }
            }

            public void failure(Exception exc) {
                braintreeFragment.sendAnalyticsEvent("paypal-two-factor.paypal-hermes-lookup.failed");
                payPalTwoFactorAuthCallback.onLookupFailure(exc);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void fetchPayPalAccount(final BraintreeFragment braintreeFragment, final PayPalTwoFactorAuthResponse payPalTwoFactorAuthResponse, final PayPalTwoFactorAuthCallback payPalTwoFactorAuthCallback) {
        braintreeFragment.getHttpClient().post("/v1/payment_methods/paypal_accounts", payPalTwoFactorAuthResponse.toJson(PayPalDataCollector.getClientMetadataId(braintreeFragment.getApplicationContext())), new HttpResponseCallback() {
            public void success(String str) {
                try {
                    String authenticateUrl = payPalTwoFactorAuthResponse.getAuthenticateUrl();
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.getJSONArray("paypalAccounts").getJSONObject(0).put("authenticateUrl", authenticateUrl);
                    PayPalAccountNonce fromJson = PayPalAccountNonce.fromJson(jSONObject.toString());
                    braintreeFragment.sendAnalyticsEvent("paypal-two-factor.fetch-paypal-account.succeeded");
                    payPalTwoFactorAuthCallback.onLookupResult(fromJson);
                } catch (JSONException e) {
                    braintreeFragment.sendAnalyticsEvent("paypal-two-factor.fetch-paypal-account.failed");
                    braintreeFragment.postCallback((Exception) e);
                }
            }

            public void failure(Exception exc) {
                braintreeFragment.sendAnalyticsEvent("paypal-two-factor.fetch-paypal-account.failed");
                braintreeFragment.postCallback(exc);
            }
        });
    }

    public static void continueTwoFactorAuthentication(BraintreeFragment braintreeFragment, PaymentMethodNonce paymentMethodNonce) {
        braintreeFragment.sendAnalyticsEvent("paypal-two-factor.continue-two-factor-authentication.started");
        PayPalAccountNonce payPalAccountNonce = (PayPalAccountNonce) paymentMethodNonce;
        PayPalTwoFactorAuthSharedPreferences.persistPayPalAccountNonce(braintreeFragment, payPalAccountNonce);
        String authenticateUrl = payPalAccountNonce.getAuthenticateUrl();
        if (authenticateUrl == null) {
            braintreeFragment.sendAnalyticsEvent("paypal-two-factor.continue-two-factor-authentication.no-two-factor-required");
            braintreeFragment.postCallback((PaymentMethodNonce) payPalAccountNonce);
            return;
        }
        braintreeFragment.sendAnalyticsEvent("paypal-two-factor.browser-switch.started");
        braintreeFragment.browserSwitch((int) BraintreeRequestCodes.PAYPAL_TWO_FACTOR_AUTH, authenticateUrl);
    }

    protected static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        PayPalAccountNonce persistedPayPalAccountNonce = PayPalTwoFactorAuthSharedPreferences.getPersistedPayPalAccountNonce(braintreeFragment);
        if (i != -1 || intent == null || persistedPayPalAccountNonce == null) {
            braintreeFragment.sendAnalyticsEvent("paypal-two-factor.browser-switch.canceled");
            braintreeFragment.postCancelCallback(BraintreeRequestCodes.PAYPAL_TWO_FACTOR_AUTH);
            return;
        }
        String str = null;
        Uri data = intent.getData();
        if (data != null) {
            str = data.getHost();
        }
        if (str != null) {
            str.hashCode();
            if (str.equals("success")) {
                braintreeFragment.sendAnalyticsEvent("paypal-two-factor.browser-switch.succeeded");
                braintreeFragment.postCallback((PaymentMethodNonce) persistedPayPalAccountNonce);
            } else if (!str.equals("cancel")) {
                braintreeFragment.sendAnalyticsEvent("paypal-two-factor.browser-switch.failed");
                braintreeFragment.postCallback((Exception) new BraintreeException("Host path unknown: " + str));
            } else {
                braintreeFragment.sendAnalyticsEvent("paypal-two-factor.browser-switch.canceled");
                braintreeFragment.postCancelCallback(BraintreeRequestCodes.PAYPAL_TWO_FACTOR_AUTH);
            }
        } else {
            braintreeFragment.sendAnalyticsEvent("paypal-two-factor.browser-switch.failed");
            braintreeFragment.postCallback((Exception) new BraintreeException("Host missing from browser switch response."));
        }
    }
}
