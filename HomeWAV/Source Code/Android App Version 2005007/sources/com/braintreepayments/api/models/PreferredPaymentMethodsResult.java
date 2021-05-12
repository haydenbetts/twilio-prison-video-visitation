package com.braintreepayments.api.models;

import org.json.JSONException;
import org.json.JSONObject;

public class PreferredPaymentMethodsResult {
    private boolean mPayPalPreferred;
    private boolean mVenmoPreferred;

    public static PreferredPaymentMethodsResult fromJSON(String str, boolean z) {
        boolean z2 = false;
        try {
            JSONObject objectAtKeyPath = getObjectAtKeyPath(new JSONObject(str), "data.preferredPaymentMethods");
            if (objectAtKeyPath != null) {
                z2 = objectAtKeyPath.getBoolean("paypalPreferred");
            }
        } catch (JSONException unused) {
        }
        return new PreferredPaymentMethodsResult().isPayPalPreferred(z2).isVenmoPreferred(z);
    }

    public PreferredPaymentMethodsResult isPayPalPreferred(boolean z) {
        this.mPayPalPreferred = z;
        return this;
    }

    public PreferredPaymentMethodsResult isVenmoPreferred(boolean z) {
        this.mVenmoPreferred = z;
        return this;
    }

    public boolean isPayPalPreferred() {
        return this.mPayPalPreferred;
    }

    public boolean isVenmoPreferred() {
        return this.mVenmoPreferred;
    }

    private static JSONObject getObjectAtKeyPath(JSONObject jSONObject, String str) throws JSONException {
        for (String jSONObject2 : str.split("\\.")) {
            jSONObject = jSONObject.getJSONObject(jSONObject2);
        }
        return jSONObject;
    }
}
