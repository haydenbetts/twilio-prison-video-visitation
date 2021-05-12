package com.stripe.android;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface PaymentResultListener {
    public static final String ERROR = "error";
    public static final String INCOMPLETE = "incomplete";
    public static final String SUCCESS = "success";
    public static final String USER_CANCELLED = "user_cancelled";

    @Retention(RetentionPolicy.SOURCE)
    public @interface PaymentResult {
    }

    void onPaymentResult(String str);
}
