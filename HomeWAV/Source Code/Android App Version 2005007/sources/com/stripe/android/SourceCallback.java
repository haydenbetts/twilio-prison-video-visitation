package com.stripe.android;

import com.stripe.android.model.Source;

public interface SourceCallback {
    void onError(Exception exc);

    void onSuccess(Source source);
}
