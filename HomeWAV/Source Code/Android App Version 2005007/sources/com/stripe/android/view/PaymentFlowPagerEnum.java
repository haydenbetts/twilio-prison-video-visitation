package com.stripe.android.view;

import com.stripe.android.R;

enum PaymentFlowPagerEnum {
    SHIPPING_INFO(R.string.title_add_an_address, R.layout.activity_enter_shipping_info),
    SHIPPING_METHOD(R.string.title_select_shipping_method, R.layout.activity_select_shipping_method);
    
    private final int mLayoutResId;
    private final int mTitleResId;

    private PaymentFlowPagerEnum(int i, int i2) {
        this.mTitleResId = i;
        this.mLayoutResId = i2;
    }

    /* access modifiers changed from: package-private */
    public int getTitleResId() {
        return this.mTitleResId;
    }

    /* access modifiers changed from: package-private */
    public int getLayoutResId() {
        return this.mLayoutResId;
    }
}
