package com.braintreepayments.api.models;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentMethodNonceFactory {
    public static JSONObject extractPaymentMethodToken(String str) throws JSONException {
        return new JSONObject(new JSONObject(str).getJSONObject("paymentMethodData").getJSONObject("tokenizationData").getString("token"));
    }

    public static PaymentMethodNonce fromString(String str) throws JSONException {
        Iterator<String> keys = extractPaymentMethodToken(str).keys();
        while (keys.hasNext()) {
            String next = keys.next();
            next.hashCode();
            if (next.equals("paypalAccounts")) {
                return PayPalAccountNonce.fromJson(str);
            }
            if (next.equals("androidPayCards")) {
                return GooglePaymentCardNonce.fromJson(str);
            }
        }
        throw new JSONException("Could not parse JSON for a payment method nonce");
    }
}
