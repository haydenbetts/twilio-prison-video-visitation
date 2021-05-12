package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
final class zau implements zabm {
    private final /* synthetic */ zaq zaa;

    private zau(zaq zaq) {
        this.zaa = zaq;
    }

    public final void zaa(Bundle bundle) {
        this.zaa.zam.lock();
        try {
            ConnectionResult unused = this.zaa.zak = ConnectionResult.RESULT_SUCCESS;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zaa(ConnectionResult connectionResult) {
        this.zaa.zam.lock();
        try {
            ConnectionResult unused = this.zaa.zak = connectionResult;
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    public final void zaa(int i, boolean z) {
        this.zaa.zam.lock();
        try {
            if (this.zaa.zal) {
                boolean unused = this.zaa.zal = false;
                this.zaa.zaa(i, z);
                return;
            }
            boolean unused2 = this.zaa.zal = true;
            this.zaa.zad.onConnectionSuspended(i);
            this.zaa.zam.unlock();
        } finally {
            this.zaa.zam.unlock();
        }
    }

    /* synthetic */ zau(zaq zaq, zat zat) {
        this(zaq);
    }
}
