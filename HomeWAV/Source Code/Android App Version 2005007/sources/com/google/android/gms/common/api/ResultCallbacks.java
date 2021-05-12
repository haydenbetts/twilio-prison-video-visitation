package com.google.android.gms.common.api;

import android.util.Log;
import com.google.android.gms.common.api.Result;

/* compiled from: com.google.android.gms:play-services-basement@@17.4.0 */
public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(Status status);

    public abstract void onSuccess(R r);

    public final void onResult(R r) {
        Status status = r.getStatus();
        if (status.isSuccess()) {
            onSuccess(r);
            return;
        }
        onFailure(status);
        if (r instanceof Releasable) {
            try {
                ((Releasable) r).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(r);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append("Unable to release ");
                sb.append(valueOf);
                Log.w("ResultCallbacks", sb.toString(), e);
            }
        }
    }
}
