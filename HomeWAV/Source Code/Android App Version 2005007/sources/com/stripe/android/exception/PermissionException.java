package com.stripe.android.exception;

import com.stripe.android.StripeError;

public class PermissionException extends AuthenticationException {
    public PermissionException(String str, String str2, Integer num, StripeError stripeError) {
        super(str, str2, num, stripeError);
    }
}
