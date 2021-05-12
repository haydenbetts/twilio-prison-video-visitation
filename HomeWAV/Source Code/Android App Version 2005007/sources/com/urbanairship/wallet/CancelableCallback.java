package com.urbanairship.wallet;

import android.os.Looper;
import com.urbanairship.CancelableOperation;

class CancelableCallback extends CancelableOperation {
    private Callback callback;
    private Pass pass;
    private int status;

    public CancelableCallback(Callback callback2, Looper looper) {
        super(looper);
        this.callback = callback2;
    }

    /* access modifiers changed from: protected */
    public void onRun() {
        Callback callback2 = this.callback;
        if (callback2 != null) {
            Pass pass2 = this.pass;
            if (pass2 != null) {
                callback2.onResult(pass2);
            } else {
                callback2.onError(this.status);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
        this.callback = null;
        this.pass = null;
    }

    /* access modifiers changed from: package-private */
    public void setResult(int i, Pass pass2) {
        if (!isCancelled()) {
            this.status = i;
            this.pass = pass2;
        }
    }
}
