package com.urbanairship.push.fcm;

import android.content.Context;
import com.google.firebase.messaging.RemoteMessage;
import com.urbanairship.PendingResult;
import com.urbanairship.push.PushMessage;
import com.urbanairship.push.PushProviderBridge;
import java.util.concurrent.Future;

public class AirshipFirebaseIntegration {
    public static Future<Void> processMessage(Context context, RemoteMessage remoteMessage) {
        final PendingResult pendingResult = new PendingResult();
        PushProviderBridge.processPush(FcmPushProvider.class, new PushMessage(remoteMessage.getData())).execute(context, new Runnable() {
            public void run() {
                pendingResult.setResult(null);
            }
        });
        return pendingResult;
    }

    public static void processMessageSync(Context context, RemoteMessage remoteMessage) {
        PushProviderBridge.processPush(FcmPushProvider.class, new PushMessage(remoteMessage.getData())).executeSync(context);
    }

    public static void processNewToken(Context context) {
        PushProviderBridge.requestRegistrationUpdate(context);
    }
}
