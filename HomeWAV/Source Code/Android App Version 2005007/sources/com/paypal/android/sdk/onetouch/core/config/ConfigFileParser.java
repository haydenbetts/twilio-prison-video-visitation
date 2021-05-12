package com.paypal.android.sdk.onetouch.core.config;

import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

class ConfigFileParser {
    ConfigFileParser() {
    }

    /* access modifiers changed from: package-private */
    public OtcConfiguration getParsedConfig(JSONObject jSONObject) throws JSONException {
        OtcConfiguration otcConfiguration = new OtcConfiguration();
        otcConfiguration.fileTimestamp(jSONObject.getString("file_timestamp"));
        JSONObject jSONObject2 = jSONObject.getJSONObject("1.0");
        JSONArray jSONArray = jSONObject2.getJSONArray("oauth2_recipes_in_decreasing_priority_order");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            if (jSONObject3 != null) {
                otcConfiguration.withOauth2Recipe(getOAuth2Recipe(jSONObject3));
            }
        }
        JSONArray jSONArray2 = jSONObject2.getJSONArray("checkout_recipes_in_decreasing_priority_order");
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            JSONObject jSONObject4 = jSONArray2.getJSONObject(i2);
            if (jSONObject4 != null) {
                otcConfiguration.withCheckoutRecipe(getCheckoutRecipe(jSONObject4));
            }
        }
        JSONArray jSONArray3 = jSONObject2.getJSONArray("billing_agreement_recipes_in_decreasing_priority_order");
        for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
            JSONObject jSONObject5 = jSONArray3.getJSONObject(i3);
            if (jSONObject5 != null) {
                otcConfiguration.withBillingAgreementRecipe(getBillingAgreementRecipe(jSONObject5));
            }
        }
        return otcConfiguration;
    }

    private CheckoutRecipe getCheckoutRecipe(JSONObject jSONObject) throws JSONException {
        CheckoutRecipe checkoutRecipe = new CheckoutRecipe();
        populateCommonData(checkoutRecipe, jSONObject);
        return checkoutRecipe;
    }

    private BillingAgreementRecipe getBillingAgreementRecipe(JSONObject jSONObject) throws JSONException {
        BillingAgreementRecipe billingAgreementRecipe = new BillingAgreementRecipe();
        populateCommonData(billingAgreementRecipe, jSONObject);
        return billingAgreementRecipe;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.paypal.android.sdk.onetouch.core.config.Recipe, com.paypal.android.sdk.onetouch.core.config.Recipe<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void populateCommonData(com.paypal.android.sdk.onetouch.core.config.Recipe<?> r5, org.json.JSONObject r6) throws org.json.JSONException {
        /*
            r4 = this;
            java.lang.String r0 = "target"
            java.lang.String r0 = r6.getString(r0)
            com.paypal.android.sdk.onetouch.core.enums.RequestTarget r0 = com.paypal.android.sdk.onetouch.core.enums.RequestTarget.valueOf(r0)
            com.paypal.android.sdk.onetouch.core.config.Recipe r0 = r5.target(r0)
            java.lang.String r1 = "protocol"
            java.lang.String r1 = r6.getString(r1)
            r0.protocol(r1)
            java.lang.String r0 = "intent_action"
            boolean r1 = r6.has(r0)
            if (r1 == 0) goto L_0x0026
            java.lang.String r0 = r6.getString(r0)
            r5.targetIntentAction(r0)
        L_0x0026:
            java.lang.String r0 = "packages"
            org.json.JSONArray r0 = r6.getJSONArray(r0)
            r1 = 0
            r2 = 0
        L_0x002e:
            int r3 = r0.length()
            if (r2 >= r3) goto L_0x003e
            java.lang.String r3 = r0.getString(r2)
            r5.targetPackage(r3)
            int r2 = r2 + 1
            goto L_0x002e
        L_0x003e:
            java.lang.String r0 = "supported_locales"
            boolean r2 = r6.has(r0)
            if (r2 == 0) goto L_0x005a
            org.json.JSONArray r6 = r6.getJSONArray(r0)
        L_0x004a:
            int r0 = r6.length()
            if (r1 >= r0) goto L_0x005a
            java.lang.String r0 = r6.getString(r1)
            r5.supportedLocale(r0)
            int r1 = r1 + 1
            goto L_0x004a
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.config.ConfigFileParser.populateCommonData(com.paypal.android.sdk.onetouch.core.config.Recipe, org.json.JSONObject):void");
    }

    private OAuth2Recipe getOAuth2Recipe(JSONObject jSONObject) throws JSONException {
        OAuth2Recipe oAuth2Recipe = new OAuth2Recipe();
        populateCommonData(oAuth2Recipe, jSONObject);
        JSONArray jSONArray = jSONObject.getJSONArray("scope");
        for (int i = 0; i < jSONArray.length(); i++) {
            String string = jSONArray.getString(i);
            if (Marker.ANY_MARKER.equals(string)) {
                oAuth2Recipe.validForAllScopes();
            } else {
                oAuth2Recipe.validForScope(string);
            }
        }
        if (jSONObject.has("endpoints")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("endpoints");
            if (jSONObject2.has(EnvironmentManager.LIVE)) {
                addEnvironment(oAuth2Recipe, EnvironmentManager.LIVE, jSONObject2.getJSONObject(EnvironmentManager.LIVE));
            }
            if (jSONObject2.has("develop")) {
                addEnvironment(oAuth2Recipe, "develop", jSONObject2.getJSONObject("develop"));
            }
            if (jSONObject2.has(EnvironmentManager.MOCK)) {
                addEnvironment(oAuth2Recipe, EnvironmentManager.MOCK, jSONObject2.getJSONObject(EnvironmentManager.MOCK));
            }
        }
        return oAuth2Recipe;
    }

    private void addEnvironment(OAuth2Recipe oAuth2Recipe, String str, JSONObject jSONObject) throws JSONException {
        oAuth2Recipe.withEndpoint(str, new ConfigEndpoint(str, jSONObject.getString("url"), jSONObject.getString("certificate")));
    }
}
