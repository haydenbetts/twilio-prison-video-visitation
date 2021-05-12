package com.urbanairship.push;

import android.content.Intent;

public class NotificationInfo {
    private PushMessage message;
    private int notificationId;
    private String notificationTag;

    public NotificationInfo(PushMessage pushMessage, int i, String str) {
        this.message = pushMessage;
        this.notificationTag = str;
        this.notificationId = i;
    }

    static NotificationInfo fromIntent(Intent intent) {
        PushMessage fromIntent = PushMessage.fromIntent(intent);
        if (fromIntent == null) {
            return null;
        }
        return new NotificationInfo(fromIntent, intent.getIntExtra(PushManager.EXTRA_NOTIFICATION_ID, -1), intent.getStringExtra(PushManager.EXTRA_NOTIFICATION_TAG));
    }

    public PushMessage getMessage() {
        return this.message;
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    public String getNotificationTag() {
        return this.notificationTag;
    }

    public String toString() {
        return "NotificationInfo{alert=" + this.message.getAlert() + ", notificationId=" + this.notificationId + ", notificationTag='" + this.notificationTag + '\'' + '}';
    }
}
