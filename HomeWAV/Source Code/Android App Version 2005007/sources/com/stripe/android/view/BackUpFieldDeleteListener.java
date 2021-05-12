package com.stripe.android.view;

import com.stripe.android.view.StripeEditText;

class BackUpFieldDeleteListener implements StripeEditText.DeleteEmptyListener {
    private StripeEditText backUpTarget;

    BackUpFieldDeleteListener(StripeEditText stripeEditText) {
        this.backUpTarget = stripeEditText;
    }

    public void onDeleteEmpty() {
        String obj = this.backUpTarget.getText().toString();
        if (obj.length() > 1) {
            this.backUpTarget.setText(obj.substring(0, obj.length() - 1));
        }
        this.backUpTarget.requestFocus();
        StripeEditText stripeEditText = this.backUpTarget;
        stripeEditText.setSelection(stripeEditText.length());
    }
}
