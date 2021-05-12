package com.stripe.android.view;

import android.app.Activity;
import android.content.Intent;

public class PaymentMethodsActivityStarter {
    private final Activity mActivity;

    public PaymentMethodsActivityStarter(Activity activity) {
        this.mActivity = activity;
    }

    public void startForResult(int i) {
        this.mActivity.startActivityForResult(newIntent(), i);
    }

    public Intent newIntent() {
        return new Intent(this.mActivity, PaymentMethodsActivity.class);
    }
}
