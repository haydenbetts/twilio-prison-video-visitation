package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
public final class zag extends zae<Void> {
    private final zabs zac;

    public zag(zabs zabs, TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        this.zac = zabs;
    }

    public final /* bridge */ /* synthetic */ void zaa(zaw zaw, boolean z) {
    }

    public final void zad(GoogleApiManager.zaa<?> zaa) throws RemoteException {
        this.zac.zaa.registerListener(zaa.zab(), this.zab);
        ListenerHolder.ListenerKey<?> listenerKey = this.zac.zaa.getListenerKey();
        if (listenerKey != null) {
            zaa.zac().put(listenerKey, this.zac);
        }
    }

    public final Feature[] zaa(GoogleApiManager.zaa<?> zaa) {
        return this.zac.zaa.getRequiredFeatures();
    }

    public final boolean zab(GoogleApiManager.zaa<?> zaa) {
        return this.zac.zaa.zaa();
    }

    public final /* bridge */ /* synthetic */ void zaa(Exception exc) {
        super.zaa(exc);
    }

    public final /* bridge */ /* synthetic */ void zaa(Status status) {
        super.zaa(status);
    }
}
