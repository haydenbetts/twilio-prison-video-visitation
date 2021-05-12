package com.braintreepayments.api.models;

import org.json.JSONException;
import org.json.JSONObject;

public class PayPalTwoFactorAuthRequest {
    private String mAmount;
    private String mCurrencyCode;
    private String mNonce;

    public PayPalTwoFactorAuthRequest nonce(String str) {
        this.mNonce = str;
        return this;
    }

    public PayPalTwoFactorAuthRequest amount(String str) {
        this.mAmount = str;
        return this;
    }

    public PayPalTwoFactorAuthRequest currencyCode(String str) {
        this.mCurrencyCode = str;
        return this;
    }

    public String getNonce() {
        return this.mNonce;
    }

    public String getAmount() {
        return this.mAmount;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public String toJson(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            String str3 = str2 + "://";
            jSONObject.put("authorization_fingerprint", str).put("amount", this.mAmount).put("currency_iso_code", this.mCurrencyCode).put("return_url", str3 + "success").put("cancel_url", str3 + "cancel").put("vault_initiated_checkout_payment_method_token", this.mNonce);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }
}
