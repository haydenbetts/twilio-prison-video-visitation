package com.stripe.android.exception;

import com.stripe.android.StripeError;

public class APIException extends StripeException {
    public APIException(String str, String str2, Integer num, StripeError stripeError, Throwable th) {
        super(stripeError, str, str2, num, th);
    }
}
