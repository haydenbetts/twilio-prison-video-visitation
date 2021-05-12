package com.stripe.android;

import com.stripe.android.model.Token;

public interface TokenCallback {
    void onError(Exception exc);

    void onSuccess(Token token);
}
