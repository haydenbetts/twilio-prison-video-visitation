package com.urbanairship.push.notifications;

import android.app.Notification;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NotificationResult {
    public static final int CANCEL = 2;
    public static final int OK = 0;
    public static final int RETRY = 1;
    private final Notification notification;
    private final int status;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    private NotificationResult(Notification notification2, int i) {
        this.notification = notification2;
        if (notification2 == null && i == 0) {
            this.status = 2;
        } else {
            this.status = i;
        }
    }

    public static NotificationResult notification(Notification notification2) {
        return new NotificationResult(notification2, 0);
    }

    public static NotificationResult cancel() {
        return new NotificationResult((Notification) null, 2);
    }

    public static NotificationResult retry() {
        return new NotificationResult((Notification) null, 1);
    }

    public Notification getNotification() {
        return this.notification;
    }

    public int getStatus() {
        return this.status;
    }
}
