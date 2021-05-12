package com.stripe.android;

public interface PaymentCompletionProvider {
    void completePayment(PaymentSessionData paymentSessionData, PaymentResultListener paymentResultListener);
}
