package com.braintreepayments.api.interfaces;

import com.braintreepayments.api.models.PreferredPaymentMethodsResult;

public interface PreferredPaymentMethodsListener extends BraintreeListener {
    void onPreferredPaymentMethodsFetched(PreferredPaymentMethodsResult preferredPaymentMethodsResult);
}
