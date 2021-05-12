package com.urbanairship.push.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class AirshipFirebaseMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        AirshipFirebaseIntegration.processMessageSync(getApplicationContext(), remoteMessage);
    }

    public void onNewToken(String str) {
        AirshipFirebaseIntegration.processNewToken(getApplicationContext());
    }
}
