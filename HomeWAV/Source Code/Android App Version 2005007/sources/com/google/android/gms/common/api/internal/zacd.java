package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
final class zacd implements Runnable {
    private final /* synthetic */ zacb zaa;

    zacd(zacb zacb) {
        this.zaa = zacb;
    }

    public final void run() {
        this.zaa.zah.zaa(new ConnectionResult(4));
    }
}
