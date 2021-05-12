package com.stripe.android.exception;

import com.stripe.android.StripeError;

public class InvalidRequestException extends StripeException {
    private final String mErrorCode;
    private final String mErrorDeclineCode;
    private final String mParam;

    public InvalidRequestException(String str, String str2, String str3, Integer num, String str4, String str5, StripeError stripeError, Throwable th) {
        super(stripeError, str, str3, num, th);
        this.mParam = str2;
        this.mErrorCode = str4;
        this.mErrorDeclineCode = str5;
    }

    public String getParam() {
        return this.mParam;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorDeclineCode() {
        return this.mErrorDeclineCode;
    }
}
