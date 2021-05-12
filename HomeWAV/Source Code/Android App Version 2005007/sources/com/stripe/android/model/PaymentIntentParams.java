package com.stripe.android.model;

import java.util.HashMap;
import java.util.Map;

public class PaymentIntentParams {
    static final String API_PARAM_CLIENT_SECRET = "client_secret";
    static final String API_PARAM_PAYMENT_METHOD_DATA = "payment_method_data";
    static final String API_PARAM_PAYMENT_METHOD_ID = "payment_method";
    static final String API_PARAM_RETURN_URL = "return_url";
    static final String API_PARAM_SAVE_PAYMENT_METHOD = "save_payment_method";
    static final String API_PARAM_SOURCE_DATA = "source_data";
    static final String API_PARAM_SOURCE_ID = "source";
    private String mClientSecret;
    private Map<String, Object> mExtraParams;
    private PaymentMethodCreateParams mPaymentMethodCreateParams;
    private String mPaymentMethodId;
    private String mReturnUrl;
    private boolean mSavePaymentMethod;
    private String mSourceId;
    private SourceParams mSourceParams;

    private PaymentIntentParams() {
    }

    public static PaymentIntentParams createCustomParams() {
        return new PaymentIntentParams();
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithPaymentMethodId(String str, String str2, String str3, boolean z) {
        return new PaymentIntentParams().setPaymentMethodId(str).setClientSecret(str2).setReturnUrl(str3).setSavePaymentMethod(z);
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithPaymentMethodId(String str, String str2, String str3) {
        return createConfirmPaymentIntentWithPaymentMethodId(str, str2, str3, false);
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithPaymentMethodCreateParams(PaymentMethodCreateParams paymentMethodCreateParams, String str, String str2, boolean z) {
        return new PaymentIntentParams().setPaymentMethodCreateParams(paymentMethodCreateParams).setClientSecret(str).setReturnUrl(str2).setSavePaymentMethod(z);
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithPaymentMethodCreateParams(PaymentMethodCreateParams paymentMethodCreateParams, String str, String str2) {
        return createConfirmPaymentIntentWithPaymentMethodCreateParams(paymentMethodCreateParams, str, str2, false);
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithSourceIdParams(String str, String str2, String str3, boolean z) {
        return new PaymentIntentParams().setSourceId(str).setClientSecret(str2).setReturnUrl(str3).setSavePaymentMethod(z);
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithSourceIdParams(String str, String str2, String str3) {
        return createConfirmPaymentIntentWithSourceIdParams(str, str2, str3, false);
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithSourceDataParams(SourceParams sourceParams, String str, String str2, boolean z) {
        return new PaymentIntentParams().setSourceParams(sourceParams).setClientSecret(str).setReturnUrl(str2).setSavePaymentMethod(z);
    }

    public static PaymentIntentParams createConfirmPaymentIntentWithSourceDataParams(SourceParams sourceParams, String str, String str2) {
        return createConfirmPaymentIntentWithSourceDataParams(sourceParams, str, str2, false);
    }

    public static PaymentIntentParams createRetrievePaymentIntentParams(String str) {
        return new PaymentIntentParams().setClientSecret(str);
    }

    public PaymentIntentParams setPaymentMethodCreateParams(PaymentMethodCreateParams paymentMethodCreateParams) {
        this.mPaymentMethodCreateParams = paymentMethodCreateParams;
        return this;
    }

    public PaymentIntentParams setPaymentMethodId(String str) {
        this.mPaymentMethodId = str;
        return this;
    }

    public PaymentIntentParams setSourceParams(SourceParams sourceParams) {
        this.mSourceParams = sourceParams;
        return this;
    }

    public PaymentIntentParams setSourceId(String str) {
        this.mSourceId = str;
        return this;
    }

    public PaymentIntentParams setClientSecret(String str) {
        this.mClientSecret = str;
        return this;
    }

    public PaymentIntentParams setReturnUrl(String str) {
        this.mReturnUrl = str;
        return this;
    }

    public PaymentIntentParams setExtraParams(Map<String, Object> map) {
        this.mExtraParams = map;
        return this;
    }

    public PaymentIntentParams setSavePaymentMethod(boolean z) {
        this.mSavePaymentMethod = z;
        return this;
    }

    public Map<String, Object> toParamMap() {
        HashMap hashMap = new HashMap();
        PaymentMethodCreateParams paymentMethodCreateParams = this.mPaymentMethodCreateParams;
        if (paymentMethodCreateParams != null) {
            hashMap.put(API_PARAM_PAYMENT_METHOD_DATA, paymentMethodCreateParams.toParamMap());
        } else {
            String str = this.mPaymentMethodId;
            if (str != null) {
                hashMap.put(API_PARAM_PAYMENT_METHOD_ID, str);
            } else {
                SourceParams sourceParams = this.mSourceParams;
                if (sourceParams != null) {
                    hashMap.put(API_PARAM_SOURCE_DATA, sourceParams.toParamMap());
                } else {
                    String str2 = this.mSourceId;
                    if (str2 != null) {
                        hashMap.put("source", str2);
                    }
                }
            }
        }
        String str3 = this.mReturnUrl;
        if (str3 != null) {
            hashMap.put(API_PARAM_RETURN_URL, str3);
        }
        hashMap.put(API_PARAM_CLIENT_SECRET, this.mClientSecret);
        Map<String, Object> map = this.mExtraParams;
        if (map != null) {
            hashMap.putAll(map);
        }
        if (this.mSavePaymentMethod) {
            hashMap.put(API_PARAM_SAVE_PAYMENT_METHOD, true);
        }
        return hashMap;
    }

    public String getClientSecret() {
        return this.mClientSecret;
    }

    public Map<String, Object> getExtraParams() {
        return this.mExtraParams;
    }

    public SourceParams getSourceParams() {
        return this.mSourceParams;
    }

    public String getSourceId() {
        return this.mSourceId;
    }

    public PaymentMethodCreateParams getPaymentMethodCreateParams() {
        return this.mPaymentMethodCreateParams;
    }

    public String getPaymentMethodId() {
        return this.mPaymentMethodId;
    }

    public String getReturnUrl() {
        return this.mReturnUrl;
    }

    public boolean shouldSavePaymentMethod() {
        return this.mSavePaymentMethod;
    }
}
