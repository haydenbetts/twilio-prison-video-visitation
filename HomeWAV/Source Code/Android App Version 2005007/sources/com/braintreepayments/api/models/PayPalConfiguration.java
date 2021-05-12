package com.braintreepayments.api.models;

import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class PayPalConfiguration {
    private static final String CLIENT_ID_KEY = "clientId";
    private static final String CURRENCY_ISO_CODE_KEY = "currencyIsoCode";
    private static final String DIRECT_BASE_URL_KEY = "directBaseUrl";
    private static final String DISPLAY_NAME_KEY = "displayName";
    private static final String ENVIRONMENT_KEY = "environment";
    private static final String PRIVACY_URL_KEY = "privacyUrl";
    private static final String TOUCH_DISABLED_KEY = "touchDisabled";
    private static final String USER_AGREEMENT_URL_KEY = "userAgreementUrl";
    private String mClientId;
    private String mCurrencyIsoCode;
    private String mDirectBaseUrl;
    private String mDisplayName;
    private String mEnvironment;
    private String mPrivacyUrl;
    private boolean mTouchDisabled;
    private String mUserAgreementUrl;

    public static PayPalConfiguration fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        PayPalConfiguration payPalConfiguration = new PayPalConfiguration();
        payPalConfiguration.mDisplayName = Json.optString(jSONObject, DISPLAY_NAME_KEY, (String) null);
        payPalConfiguration.mClientId = Json.optString(jSONObject, CLIENT_ID_KEY, (String) null);
        payPalConfiguration.mPrivacyUrl = Json.optString(jSONObject, PRIVACY_URL_KEY, (String) null);
        payPalConfiguration.mUserAgreementUrl = Json.optString(jSONObject, USER_AGREEMENT_URL_KEY, (String) null);
        payPalConfiguration.mDirectBaseUrl = Json.optString(jSONObject, DIRECT_BASE_URL_KEY, (String) null);
        payPalConfiguration.mEnvironment = Json.optString(jSONObject, ENVIRONMENT_KEY, (String) null);
        payPalConfiguration.mTouchDisabled = jSONObject.optBoolean(TOUCH_DISABLED_KEY, true);
        payPalConfiguration.mCurrencyIsoCode = Json.optString(jSONObject, CURRENCY_ISO_CODE_KEY, (String) null);
        return payPalConfiguration;
    }

    @Deprecated
    public boolean isEnabled() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.mEnvironment) && !TextUtils.isEmpty(this.mDisplayName) && !TextUtils.isEmpty(this.mPrivacyUrl) && !TextUtils.isEmpty(this.mUserAgreementUrl);
        if ("offline".equals(this.mEnvironment)) {
            return z2;
        }
        if (!z2 || TextUtils.isEmpty(this.mClientId)) {
            z = false;
        }
        return z;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public String getPrivacyUrl() {
        return this.mPrivacyUrl;
    }

    public String getUserAgreementUrl() {
        return this.mUserAgreementUrl;
    }

    public String getDirectBaseUrl() {
        if (TextUtils.isEmpty(this.mDirectBaseUrl)) {
            return null;
        }
        return this.mDirectBaseUrl + "/v1/";
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public boolean isTouchDisabled() {
        return this.mTouchDisabled;
    }

    public String getCurrencyIsoCode() {
        return this.mCurrencyIsoCode;
    }
}
