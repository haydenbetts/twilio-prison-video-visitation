package com.braintreepayments.api.models;

import android.content.Context;
import com.braintreepayments.api.Json;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.wallet.Wallet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePaymentConfiguration {
    private static final String DISPLAY_NAME_KEY = "displayName";
    private static final String ENABLED_KEY = "enabled";
    private static final String ENVIRONMENT_KEY = "environment";
    private static final String GOOGLE_AUTHORIZATION_FINGERPRINT_KEY = "googleAuthorizationFingerprint";
    private static final String PAYPAL_CLIENT_ID_KEY = "paypalClientId";
    private static final String SUPPORTED_NETWORKS_KEY = "supportedNetworks";
    String mDisplayName;
    boolean mEnabled;
    String mEnvironment;
    String mGoogleAuthorizationFingerprint;
    String mPayPalClientId;
    String[] mSupportedNetworks;

    public static GooglePaymentConfiguration fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        GooglePaymentConfiguration googlePaymentConfiguration = new GooglePaymentConfiguration();
        googlePaymentConfiguration.mEnabled = jSONObject.optBoolean("enabled", false);
        googlePaymentConfiguration.mGoogleAuthorizationFingerprint = Json.optString(jSONObject, GOOGLE_AUTHORIZATION_FINGERPRINT_KEY, (String) null);
        googlePaymentConfiguration.mEnvironment = Json.optString(jSONObject, ENVIRONMENT_KEY, (String) null);
        googlePaymentConfiguration.mDisplayName = Json.optString(jSONObject, DISPLAY_NAME_KEY, "");
        googlePaymentConfiguration.mPayPalClientId = Json.optString(jSONObject, PAYPAL_CLIENT_ID_KEY, "");
        JSONArray optJSONArray = jSONObject.optJSONArray(SUPPORTED_NETWORKS_KEY);
        if (optJSONArray != null) {
            googlePaymentConfiguration.mSupportedNetworks = new String[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    googlePaymentConfiguration.mSupportedNetworks[i] = optJSONArray.getString(i);
                } catch (JSONException unused) {
                }
            }
        } else {
            googlePaymentConfiguration.mSupportedNetworks = new String[0];
        }
        return googlePaymentConfiguration;
    }

    public boolean isEnabled(Context context) {
        try {
            Class.forName(Wallet.class.getName());
            if (!this.mEnabled || GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) != 0) {
                return false;
            }
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public String getGoogleAuthorizationFingerprint() {
        return this.mGoogleAuthorizationFingerprint;
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String[] getSupportedNetworks() {
        return this.mSupportedNetworks;
    }

    public String getPaypalClientId() {
        return this.mPayPalClientId;
    }
}
