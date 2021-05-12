package com.google.android.gms.gcm;

import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.gcm.zzj;

final class zzf extends zzj {
    private final /* synthetic */ GoogleCloudMessaging zzak;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(GoogleCloudMessaging googleCloudMessaging, Looper looper) {
        super(looper);
        this.zzak = googleCloudMessaging;
    }

    public final void handleMessage(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("GCM", "Dropping invalid message");
        }
        Intent intent = (Intent) message.obj;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            this.zzak.zzai.add(intent);
        } else if (!this.zzak.zzd(intent)) {
            intent.setPackage(this.zzak.zzl.getPackageName());
            this.zzak.zzl.sendBroadcast(intent);
        }
    }
}
