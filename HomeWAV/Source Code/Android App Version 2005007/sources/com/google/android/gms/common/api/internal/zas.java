package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
final class zas implements zabm {
    private final /* synthetic */ zaq zaa;

    private zas(zaq zaq) {
        this.zaa = zaq;
    }

    public final void zaa(Bundle bundle) {
        this.zaa.zam.lock();
        try {
            this.zaa.zaa(bundle);
            ConnectionResult unused = this.zaa.zaj = ConnectionResult.RESULT_SUCCESS;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zaa(ConnectionResult connectionResult) {
        this.zaa.zam.lock();
        try {
            ConnectionResult unused = this.zaa.zaj = connectionResult;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zaa(int i, boolean z) {
        this.zaa.zam.lock();
        try {
            if (!this.zaa.zal && this.zaa.zak != null) {
                if (this.zaa.zak.isSuccess()) {
                    boolean unused = this.zaa.zal = true;
                    this.zaa.zae.onConnectionSuspended(i);
                    this.zaa.zam.unlock();
                    return;
                }
            }
            boolean unused2 = this.zaa.zal = false;
            this.zaa.zaa(i, z);
        } finally {
            this.zaa.zam.unlock();
        }
    }

    /* synthetic */ zas(zaq zaq, zat zat) {
        this(zaq);
    }
}
