package com.stripe.android.view.i18n;

import com.stripe.android.StripeError;

public interface ErrorMessageTranslator {

    public static class Default implements ErrorMessageTranslator {
        public String translate(int i, String str, StripeError stripeError) {
            return str == null ? "" : str;
        }
    }

    String translate(int i, String str, StripeError stripeError);
}
