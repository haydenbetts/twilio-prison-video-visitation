package com.braintreepayments.api.internal;

import android.content.Context;

public class IntegrationType {
    public static String get(Context context) {
        try {
            if (Class.forName("com.braintreepayments.api.BraintreePaymentActivity").isInstance(context)) {
                return "dropin";
            }
        } catch (ClassNotFoundException unused) {
        }
        try {
            return Class.forName("com.braintreepayments.api.dropin.DropInActivity").isInstance(context) ? "dropin2" : "custom";
        } catch (ClassNotFoundException unused2) {
            return "custom";
        }
    }
}
