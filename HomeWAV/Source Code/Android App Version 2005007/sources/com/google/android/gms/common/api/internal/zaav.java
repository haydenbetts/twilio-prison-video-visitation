package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
final class zaav extends zabl {
    private WeakReference<zaap> zaa;

    zaav(zaap zaap) {
        this.zaa = new WeakReference<>(zaap);
    }

    public final void zaa() {
        zaap zaap = (zaap) this.zaa.get();
        if (zaap != null) {
            zaap.zae();
        }
    }
}
