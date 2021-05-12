package com.braintreepayments.api.models;

public class ReadyForGooglePaymentRequest {
    private boolean mExistingPaymentMethodRequired;

    public ReadyForGooglePaymentRequest existingPaymentMethodRequired(boolean z) {
        this.mExistingPaymentMethodRequired = z;
        return this;
    }

    public boolean isExistingPaymentMethodRequired() {
        return this.mExistingPaymentMethodRequired;
    }
}
