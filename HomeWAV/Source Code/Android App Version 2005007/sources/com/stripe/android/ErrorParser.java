package com.stripe.android;

import org.json.JSONException;
import org.json.JSONObject;

class ErrorParser {
    private static final String FIELD_CHARGE = "charge";
    private static final String FIELD_CODE = "code";
    private static final String FIELD_DECLINE_CODE = "decline_code";
    private static final String FIELD_ERROR = "error";
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_PARAM = "param";
    private static final String FIELD_TYPE = "type";
    static final String MALFORMED_RESPONSE_MESSAGE = "An improperly formatted error response was found.";

    ErrorParser() {
    }

    static StripeError parseError(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = null;
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("error");
            str6 = jSONObject.optString(FIELD_CHARGE);
            try {
                str5 = jSONObject.optString(FIELD_CODE);
            } catch (JSONException unused) {
                str5 = null;
                str4 = str5;
                str2 = str4;
                str3 = MALFORMED_RESPONSE_MESSAGE;
                return new StripeError(str7, str3, str5, str2, str4, str6);
            }
            try {
                str4 = jSONObject.optString(FIELD_DECLINE_CODE);
            } catch (JSONException unused2) {
                str4 = null;
                str2 = str4;
                str3 = MALFORMED_RESPONSE_MESSAGE;
                return new StripeError(str7, str3, str5, str2, str4, str6);
            }
            try {
                str3 = jSONObject.optString("message");
                str2 = jSONObject.optString(FIELD_PARAM);
                try {
                    str7 = jSONObject.optString("type");
                } catch (JSONException unused3) {
                    str3 = MALFORMED_RESPONSE_MESSAGE;
                    return new StripeError(str7, str3, str5, str2, str4, str6);
                }
            } catch (JSONException unused4) {
                str2 = null;
                str3 = MALFORMED_RESPONSE_MESSAGE;
                return new StripeError(str7, str3, str5, str2, str4, str6);
            }
        } catch (JSONException unused5) {
            str6 = null;
            str5 = null;
            str4 = str5;
            str2 = str4;
            str3 = MALFORMED_RESPONSE_MESSAGE;
            return new StripeError(str7, str3, str5, str2, str4, str6);
        }
        return new StripeError(str7, str3, str5, str2, str4, str6);
    }
}
