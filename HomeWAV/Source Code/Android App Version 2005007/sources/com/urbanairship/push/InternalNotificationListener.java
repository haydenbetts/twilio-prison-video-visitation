package com.urbanairship.push;

public interface InternalNotificationListener {
    void onNotificationResponse(NotificationInfo notificationInfo, NotificationActionButtonInfo notificationActionButtonInfo);
}
