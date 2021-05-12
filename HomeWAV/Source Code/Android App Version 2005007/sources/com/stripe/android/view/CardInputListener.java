package com.stripe.android.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface CardInputListener {

    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusField {
        public static final String FOCUS_CARD = "focus_card";
        public static final String FOCUS_CVC = "focus_cvc";
        public static final String FOCUS_EXPIRY = "focus_expiry";
        public static final String FOCUS_POSTAL = "focus_postal";
    }

    void onCardComplete();

    void onCvcComplete();

    void onExpirationComplete();

    void onFocusChange(String str);

    void onPostalCodeComplete();
}
