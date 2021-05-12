package com.braintreepayments.api.interfaces;

import com.braintreepayments.api.models.PaymentMethodNonce;

public interface PayPalTwoFactorAuthCallback {
    void onLookupFailure(Exception exc);

    void onLookupResult(PaymentMethodNonce paymentMethodNonce);
}
