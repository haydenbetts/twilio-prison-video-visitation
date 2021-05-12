package com.stripe.android.exception;

import com.stripe.android.StripeError;

public class RateLimitException extends InvalidRequestException {
    public RateLimitException(String str, String str2, String str3, Integer num, StripeError stripeError) {
        super(str, str2, str3, num, (String) null, (String) null, stripeError, (Throwable) null);
    }
}
