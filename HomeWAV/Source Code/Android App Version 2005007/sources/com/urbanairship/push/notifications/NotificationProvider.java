package com.urbanairship.push.notifications;

import android.app.Notification;
import android.content.Context;
import com.urbanairship.push.PushMessage;

public interface NotificationProvider {
    public static final String DEFAULT_NOTIFICATION_CHANNEL = "com.urbanairship.default";

    NotificationResult onCreateNotification(Context context, NotificationArguments notificationArguments);

    NotificationArguments onCreateNotificationArguments(Context context, PushMessage pushMessage);

    void onNotificationCreated(Context context, Notification notification, NotificationArguments notificationArguments);
}
