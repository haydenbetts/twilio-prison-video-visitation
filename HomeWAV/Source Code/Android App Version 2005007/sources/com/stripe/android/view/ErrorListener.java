package com.stripe.android.view;

import com.google.android.material.textfield.TextInputLayout;
import com.stripe.android.view.StripeEditText;

class ErrorListener implements StripeEditText.ErrorMessageListener {
    private final TextInputLayout mTextInputLayout;

    ErrorListener(TextInputLayout textInputLayout) {
        this.mTextInputLayout = textInputLayout;
    }

    public void displayErrorMessage(String str) {
        if (str == null) {
            this.mTextInputLayout.setErrorEnabled(false);
        } else {
            this.mTextInputLayout.setError(str);
        }
    }
}
