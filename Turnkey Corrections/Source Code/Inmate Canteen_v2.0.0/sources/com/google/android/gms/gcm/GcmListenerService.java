package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import androidx.annotation.CallSuper;
import com.google.android.gms.iid.zze;
import com.google.android.gms.internal.gcm.zzl;
import com.google.android.gms.internal.gcm.zzm;
import java.util.Iterator;
import java.util.List;

@Deprecated
public class GcmListenerService extends zze {
    private zzl zzg = zzm.zzdk;

    public void onDeletedMessages() {
    }

    public void onMessageReceived(String str, Bundle bundle) {
    }

    public void onMessageSent(String str) {
    }

    public void onSendError(String str, String str2) {
    }

    @CallSuper
    public void onCreate() {
        super.onCreate();
        zzm.zzab();
        getClass();
        this.zzg = zzm.zzdk;
    }

    public void handleIntent(Intent intent) {
        String str;
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            String valueOf = String.valueOf(intent.getAction());
            Log.w("GcmListenerService", valueOf.length() != 0 ? "Unknown intent action: ".concat(valueOf) : new String("Unknown intent action: "));
            return;
        }
        String stringExtra = intent.getStringExtra("message_type");
        if (stringExtra == null) {
            stringExtra = GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE;
        }
        char c = 65535;
        int hashCode = stringExtra.hashCode();
        boolean z = false;
        if (hashCode != -2062414158) {
            if (hashCode != 102161) {
                if (hashCode != 814694033) {
                    if (hashCode == 814800675 && stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_EVENT)) {
                        c = 2;
                    }
                } else if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR)) {
                    c = 3;
                }
            } else if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE)) {
                c = 0;
            }
        } else if (stringExtra.equals(GoogleCloudMessaging.MESSAGE_TYPE_DELETED)) {
            c = 1;
        }
        switch (c) {
            case 0:
                Bundle extras = intent.getExtras();
                extras.remove("message_type");
                extras.remove("androidx.contentpager.content.wakelockid");
                if ("1".equals(zzd.zzd(extras, "gcm.n.e")) || zzd.zzd(extras, "gcm.n.icon") != null) {
                    if (!((KeyguardManager) getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
                        int myPid = Process.myPid();
                        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) getSystemService("activity")).getRunningAppProcesses();
                        if (runningAppProcesses != null) {
                            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    ActivityManager.RunningAppProcessInfo next = it.next();
                                    if (next.pid == myPid) {
                                        if (next.importance == 100) {
                                            z = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!z) {
                        zzd.zzd(this).zze(extras);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    Iterator it2 = extras.keySet().iterator();
                    while (it2.hasNext()) {
                        String str2 = (String) it2.next();
                        String string = extras.getString(str2);
                        if (str2.startsWith("gcm.notification.")) {
                            str2 = str2.replace("gcm.notification.", "gcm.n.");
                        }
                        if (str2.startsWith("gcm.n.")) {
                            if (!"gcm.n.e".equals(str2)) {
                                bundle.putString(str2.substring(6), string);
                            }
                            it2.remove();
                        }
                    }
                    String string2 = bundle.getString("sound2");
                    if (string2 != null) {
                        bundle.remove("sound2");
                        bundle.putString("sound", string2);
                    }
                    if (!bundle.isEmpty()) {
                        extras.putBundle("notification", bundle);
                    }
                }
                String string3 = extras.getString("from");
                extras.remove("from");
                zzd(extras);
                this.zzg.zzl("onMessageReceived");
                onMessageReceived(string3, extras);
                return;
            case 1:
                onDeletedMessages();
                return;
            case 2:
                onMessageSent(intent.getStringExtra("google.message_id"));
                return;
            case 3:
                String stringExtra2 = intent.getStringExtra("google.message_id");
                if (stringExtra2 == null) {
                    stringExtra2 = intent.getStringExtra("message_id");
                }
                onSendError(stringExtra2, intent.getStringExtra("error"));
                return;
            default:
                String valueOf2 = String.valueOf(stringExtra);
                if (valueOf2.length() != 0) {
                    str = "Received message with unknown type: ".concat(valueOf2);
                } else {
                    str = new String("Received message with unknown type: ");
                }
                Log.w("GcmListenerService", str);
                return;
        }
    }

    static void zzd(Bundle bundle) {
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && str.startsWith("google.c.")) {
                it.remove();
            }
        }
    }
}
