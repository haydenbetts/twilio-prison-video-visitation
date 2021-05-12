package com.stripe.android.exception;

import com.stripe.android.StripeError;

public class CardException extends StripeException {
    private final String mCharge;
    private final String mCode;
    private final String mDeclineCode;
    private final String mParam;

    public CardException(String str, String str2, String str3, String str4, String str5, String str6, Integer num, StripeError stripeError) {
        super(stripeError, str, str2, num);
        this.mCode = str3;
        this.mParam = str4;
        this.mDeclineCode = str5;
        this.mCharge = str6;
    }

    public String getCode() {
        return this.mCode;
    }

    public String getParam() {
        return this.mParam;
    }

    public String getDeclineCode() {
        return this.mDeclineCode;
    }

    public String getCharge() {
        return this.mCharge;
    }
}
