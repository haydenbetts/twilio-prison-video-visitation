package com.google.android.gms.internal.firebase_messaging;

import java.util.Objects;

/* compiled from: com.google.firebase:firebase-messaging@@20.2.4 */
final class zzo extends zzn {
    private final zzm zza = new zzm();

    zzo() {
    }

    public final void zza(Throwable th, Throwable th2) {
        if (th2 != th) {
            Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
            this.zza.zza(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}
