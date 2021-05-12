package com.urbanairship.push;

public interface NotificationListener {
    void onNotificationBackgroundAction(NotificationInfo notificationInfo, NotificationActionButtonInfo notificationActionButtonInfo);

    void onNotificationDismissed(NotificationInfo notificationInfo);

    boolean onNotificationForegroundAction(NotificationInfo notificationInfo, NotificationActionButtonInfo notificationActionButtonInfo);

    boolean onNotificationOpened(NotificationInfo notificationInfo);

    void onNotificationPosted(NotificationInfo notificationInfo);
}
