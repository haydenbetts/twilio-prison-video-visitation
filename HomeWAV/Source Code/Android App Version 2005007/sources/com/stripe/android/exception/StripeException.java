package com.stripe.android.exception;

import com.stripe.android.StripeError;

public abstract class StripeException extends Exception {
    protected static final long serialVersionUID = 1;
    private final String mRequestId;
    private final Integer mStatusCode;
    private final StripeError mStripeError;

    public StripeException(String str, String str2, Integer num) {
        this((StripeError) null, str, str2, num);
    }

    public StripeException(StripeError stripeError, String str, String str2, Integer num) {
        this(stripeError, str, str2, num, (Throwable) null);
    }

    public StripeException(String str, String str2, Integer num, Throwable th) {
        this((StripeError) null, str, str2, num, th);
    }

    public StripeException(StripeError stripeError, String str, String str2, Integer num, Throwable th) {
        super(str, th);
        this.mStripeError = stripeError;
        this.mStatusCode = num;
        this.mRequestId = str2;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public Integer getStatusCode() {
        return this.mStatusCode;
    }

    public StripeError getStripeError() {
        return this.mStripeError;
    }

    public String toString() {
        String str;
        if (this.mRequestId != null) {
            str = "; request-id: " + this.mRequestId;
        } else {
            str = "";
        }
        return super.toString() + str;
    }
}
