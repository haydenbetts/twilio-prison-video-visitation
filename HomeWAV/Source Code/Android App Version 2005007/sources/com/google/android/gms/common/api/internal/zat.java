package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
final class zat implements Runnable {
    private final /* synthetic */ zaq zaa;

    zat(zaq zaq) {
        this.zaa = zaq;
    }

    public final void run() {
        this.zaa.zam.lock();
        try {
            this.zaa.zah();
        } finally {
            this.zaa.zam.unlock();
        }
    }
}
