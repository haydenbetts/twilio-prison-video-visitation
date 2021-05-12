package com.stripe.android;

import android.app.Activity;
import java.lang.ref.WeakReference;

public abstract class ActivitySourceCallback<A extends Activity> implements SourceCallback {
    private final WeakReference<A> mActivityRef;

    public ActivitySourceCallback(A a) {
        this.mActivityRef = new WeakReference<>(a);
    }

    public A getActivity() {
        return (Activity) this.mActivityRef.get();
    }
}
