package com.stripe.android;

public class StripeError {
    public final String charge;
    public final String code;
    public final String declineCode;
    public final String message;
    public final String param;
    public final String type;

    StripeError(String str, String str2, String str3, String str4, String str5, String str6) {
        this.type = str;
        this.message = str2;
        this.code = str3;
        this.param = str4;
        this.declineCode = str5;
        this.charge = str6;
    }
}
