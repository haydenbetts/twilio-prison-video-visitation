package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
final class zaaf implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final WeakReference<zaad> zaa;
    private final Api<?> zab;
    /* access modifiers changed from: private */
    public final boolean zac;

    public zaaf(zaad zaad, Api<?> api, boolean z) {
        this.zaa = new WeakReference<>(zaad);
        this.zab = api;
        this.zac = z;
    }

    public final void onReportServiceBinding(ConnectionResult connectionResult) {
        zaad zaad = (zaad) this.zaa.get();
        if (zaad != null) {
            Preconditions.checkState(Looper.myLooper() == zaad.zaa.zad.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaad.zab.lock();
            try {
                if (zaad.zab(0)) {
                    if (!connectionResult.isSuccess()) {
                        zaad.zab(connectionResult, this.zab, this.zac);
                    }
                    if (zaad.zad()) {
                        zaad.zae();
                    }
                    zaad.zab.unlock();
                }
            } finally {
                zaad.zab.unlock();
            }
        }
    }
}
