package com.stripe.android.exception;

import com.stripe.android.StripeError;

public class APIConnectionException extends StripeException {
    public APIConnectionException(String str) {
        this(str, (Throwable) null);
    }

    public APIConnectionException(String str, Throwable th) {
        super((StripeError) null, str, (String) null, 0, th);
    }
}
