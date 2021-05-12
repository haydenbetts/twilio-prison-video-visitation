package com.braintreepayments.api.models;

import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalTwoFactorAuthResponse {
    private static final String AUTHENTICATE_URL_KEY = "authenticateUrl";
    private static final String INTENT_KEY = "intent";
    private static final String PAYMENT_TOKEN_KEY = "paymentToken";
    private static final String REDIRECT_URL_KEY = "redirectUrl";
    private String mAuthenticateUrl;
    private String mAuthorizationFingerprint;
    private String mIntent;
    private String mPaymentToken;
    private String mRedirectUrl;

    public static PayPalTwoFactorAuthResponse fromJson(String str, String str2) throws JSONException {
        PayPalTwoFactorAuthResponse payPalTwoFactorAuthResponse = new PayPalTwoFactorAuthResponse();
        JSONObject jSONObject = new JSONObject(str).getJSONObject("paymentResource");
        payPalTwoFactorAuthResponse.mPaymentToken = Json.optString(jSONObject, PAYMENT_TOKEN_KEY, (String) null);
        payPalTwoFactorAuthResponse.mIntent = Json.optString(jSONObject, INTENT_KEY, (String) null);
        payPalTwoFactorAuthResponse.mRedirectUrl = Json.optString(jSONObject, REDIRECT_URL_KEY, (String) null);
        payPalTwoFactorAuthResponse.mAuthenticateUrl = Json.optString(jSONObject, AUTHENTICATE_URL_KEY, (String) null);
        payPalTwoFactorAuthResponse.mAuthorizationFingerprint = str2;
        return payPalTwoFactorAuthResponse;
    }

    public String getAuthenticateUrl() {
        return this.mAuthenticateUrl;
    }

    public String getPaymentToken() {
        return this.mPaymentToken;
    }

    public String getResourceIntent() {
        return this.mIntent;
    }

    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    public String toJson(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("paypal_account", new JSONObject().put("correlation_id", str).put("payment_token", this.mPaymentToken).put("options", new JSONObject().put("sca_authentication_complete", this.mAuthenticateUrl == null))).put("authorization_fingerprint", this.mAuthorizationFingerprint);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }
}
