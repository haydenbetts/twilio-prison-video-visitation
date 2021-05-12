package com.braintreepayments.api;

import android.content.Intent;
import android.net.Uri;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ConfigurationException;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.models.BraintreeRequestCodes;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.LocalPaymentRequest;
import com.braintreepayments.api.models.LocalPaymentResult;
import com.braintreepayments.api.models.MetadataBuilder;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.paypal.android.sdk.data.collector.PayPalDataCollector;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalPayment {
    private static final String LOCAL_PAYMENT_CANCEL = "local-payment-cancel";
    private static final String LOCAL_PAYMENT_SUCCESSS = "local-payment-success";
    /* access modifiers changed from: private */
    public static String sMerchantAccountId;
    /* access modifiers changed from: private */
    public static String sPaymentType;

    public static void startPayment(final BraintreeFragment braintreeFragment, final LocalPaymentRequest localPaymentRequest, final BraintreeResponseListener<LocalPaymentRequest> braintreeResponseListener) {
        if (localPaymentRequest == null) {
            braintreeFragment.postCallback((Exception) new BraintreeException("A LocalPaymentRequest is required."));
        } else if (localPaymentRequest.getApprovalUrl() != null || localPaymentRequest.getPaymentId() != null) {
            braintreeFragment.postCallback((Exception) new BraintreeException("LocalPaymentRequest is invalid, appovalUrl and paymentId should not be set."));
        } else if (localPaymentRequest.getPaymentType() == null || localPaymentRequest.getAmount() == null) {
            braintreeFragment.postCallback((Exception) new BraintreeException("LocalPaymentRequest is invalid, paymentType and amount are required."));
        } else if (braintreeResponseListener == null) {
            braintreeFragment.postCallback((Exception) new BraintreeException("BraintreeResponseListener<LocalPaymentRequest> is required."));
        } else {
            braintreeFragment.waitForConfiguration(new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    if (!configuration.getPayPal().isEnabled()) {
                        braintreeFragment.postCallback((Exception) new ConfigurationException("Local payments are not enabled for this merchant."));
                        return;
                    }
                    String unused = LocalPayment.sMerchantAccountId = localPaymentRequest.getMerchantAccountId();
                    String unused2 = LocalPayment.sPaymentType = localPaymentRequest.getPaymentType();
                    braintreeFragment.sendAnalyticsEvent(LocalPayment.paymentTypeForAnalytics() + ".local-payment.start-payment.selected");
                    braintreeFragment.getHttpClient().post("/v1/local_payments/create", localPaymentRequest.build(braintreeFragment.getReturnUrlScheme() + "://" + LocalPayment.LOCAL_PAYMENT_SUCCESSS, braintreeFragment.getReturnUrlScheme() + "://" + LocalPayment.LOCAL_PAYMENT_CANCEL), new HttpResponseCallback() {
                        public void success(String str) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                localPaymentRequest.approvalUrl(jSONObject.getJSONObject("paymentResource").getString("redirectUrl"));
                                localPaymentRequest.paymentId(jSONObject.getJSONObject("paymentResource").getString("paymentToken"));
                                BraintreeFragment braintreeFragment = braintreeFragment;
                                braintreeFragment.sendAnalyticsEvent(LocalPayment.paymentTypeForAnalytics() + ".local-payment.create.succeeded");
                                braintreeResponseListener.onResponse(localPaymentRequest);
                            } catch (JSONException e) {
                                failure(e);
                            }
                        }

                        public void failure(Exception exc) {
                            BraintreeFragment braintreeFragment = braintreeFragment;
                            braintreeFragment.sendAnalyticsEvent(LocalPayment.paymentTypeForAnalytics() + ".local-payment.webswitch.initiate.failed");
                            braintreeFragment.postCallback(exc);
                        }
                    });
                }
            });
        }
    }

    public static void approvePayment(BraintreeFragment braintreeFragment, LocalPaymentRequest localPaymentRequest) {
        braintreeFragment.browserSwitch((int) BraintreeRequestCodes.LOCAL_PAYMENT, localPaymentRequest.getApprovalUrl());
        braintreeFragment.sendAnalyticsEvent(paymentTypeForAnalytics() + ".local-payment.webswitch.initiate.succeeded");
    }

    static void onActivityResult(final BraintreeFragment braintreeFragment, int i, Intent intent) {
        Uri uri;
        if (i == 0) {
            postCancelCallbackAndSendAnalytics(braintreeFragment);
            return;
        }
        if (intent == null) {
            uri = null;
        } else {
            uri = intent.getData();
        }
        if (uri == null) {
            postErrorCallbackAndSendAnalytics(braintreeFragment);
            return;
        }
        String uri2 = uri.toString();
        if (uri2.toLowerCase().contains(LOCAL_PAYMENT_CANCEL.toLowerCase())) {
            postCancelCallbackAndSendAnalytics(braintreeFragment);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("merchant_account_id", sMerchantAccountId);
            jSONObject.put("paypal_account", new JSONObject().put("intent", PayPalRequest.INTENT_SALE).put("response", new JSONObject().put("webURL", uri2)).put("options", new JSONObject().put("validate", false)).put("response_type", "web").put("correlation_id", PayPalDataCollector.getClientMetadataId(braintreeFragment.getApplicationContext())));
            jSONObject.put(MetadataBuilder.META_KEY, new JSONObject().put("source", "client").put("integration", braintreeFragment.getIntegrationType()).put("sessionId", braintreeFragment.getSessionId()));
            braintreeFragment.getHttpClient().post("/v1/payment_methods/paypal_accounts", jSONObject.toString(), new HttpResponseCallback() {
                public void success(String str) {
                    try {
                        LocalPaymentResult fromJson = LocalPaymentResult.fromJson(str);
                        BraintreeFragment braintreeFragment = braintreeFragment;
                        braintreeFragment.sendAnalyticsEvent(LocalPayment.paymentTypeForAnalytics() + ".local-payment.tokenize.succeeded");
                        braintreeFragment.postCallback((PaymentMethodNonce) fromJson);
                    } catch (JSONException e) {
                        failure(e);
                    }
                }

                public void failure(Exception exc) {
                    BraintreeFragment braintreeFragment = braintreeFragment;
                    braintreeFragment.sendAnalyticsEvent(LocalPayment.paymentTypeForAnalytics() + ".local-payment.tokenize.failed");
                    braintreeFragment.postCallback(exc);
                }
            });
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: private */
    public static String paymentTypeForAnalytics() {
        String str = sPaymentType;
        return str != null ? str : "unknown";
    }

    private static void postCancelCallbackAndSendAnalytics(BraintreeFragment braintreeFragment) {
        braintreeFragment.sendAnalyticsEvent(paymentTypeForAnalytics() + ".local-payment.webswitch.canceled");
        braintreeFragment.postCancelCallback(BraintreeRequestCodes.LOCAL_PAYMENT);
    }

    private static void postErrorCallbackAndSendAnalytics(BraintreeFragment braintreeFragment) {
        braintreeFragment.sendAnalyticsEvent(paymentTypeForAnalytics() + ".local-payment.webswitch-response.invalid");
        braintreeFragment.postCallback((Exception) new BraintreeException("LocalPayment encountered an error, return URL is invalid."));
    }
}
