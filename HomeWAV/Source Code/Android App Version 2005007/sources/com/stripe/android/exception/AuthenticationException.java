package com.stripe.android.exception;

import com.stripe.android.StripeError;

public class AuthenticationException extends StripeException {
    public AuthenticationException(String str, String str2, Integer num, StripeError stripeError) {
        super(stripeError, str, str2, num);
    }
}
