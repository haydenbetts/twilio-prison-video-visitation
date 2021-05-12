package com.stripe.android;

import java.util.Currency;

public class PaymentConfiguration {
    private static PaymentConfiguration mInstance;
    private Currency mCurrency;
    private ClassLoader mEphemeralKeyProviderClassLoader;
    private String mPublishableKey;
    private int mRequiredBillingAddressFields;
    private boolean mShouldUseSourcesForCards;

    private PaymentConfiguration(String str) {
        this.mPublishableKey = str;
    }

    public static PaymentConfiguration getInstance() {
        PaymentConfiguration paymentConfiguration = mInstance;
        if (paymentConfiguration != null) {
            return paymentConfiguration;
        }
        throw new IllegalStateException("Attempted to get instance of PaymentConfiguration without initialization.");
    }

    public static void init(String str) {
        PaymentConfiguration paymentConfiguration = new PaymentConfiguration(str);
        mInstance = paymentConfiguration;
        paymentConfiguration.mRequiredBillingAddressFields = 0;
        paymentConfiguration.mShouldUseSourcesForCards = true;
    }

    public String getPublishableKey() {
        return this.mPublishableKey;
    }

    public int getRequiredBillingAddressFields() {
        return this.mRequiredBillingAddressFields;
    }

    public PaymentConfiguration setRequiredBillingAddressFields(int i) {
        this.mRequiredBillingAddressFields = i;
        return this;
    }

    @Deprecated
    public boolean getShouldUseSourcesForCards() {
        return this.mShouldUseSourcesForCards;
    }

    @Deprecated
    public PaymentConfiguration setShouldUseSourcesForCards(boolean z) {
        this.mShouldUseSourcesForCards = z;
        return this;
    }

    static void setInstance(PaymentConfiguration paymentConfiguration) {
        mInstance = paymentConfiguration;
    }
}
