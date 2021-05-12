package com.urbanairship.push.notifications;

import com.urbanairship.push.PushMessage;

public class NotificationArguments {
    private boolean longRunningTask;
    private PushMessage message;
    private String notificationChannelId;
    private int notificationId;
    private String notificationTag;

    private NotificationArguments(Builder builder) {
        this.notificationId = builder.notificationId;
        this.notificationChannelId = builder.notificationChannelId;
        this.longRunningTask = builder.longRunningTask;
        this.message = builder.message;
        this.notificationTag = builder.notificationTag;
    }

    public int getNotificationId() {
        return this.notificationId;
    }

    public String getNotificationTag() {
        return this.notificationTag;
    }

    public String getNotificationChannelId() {
        return this.notificationChannelId;
    }

    public boolean getRequiresLongRunningTask() {
        return this.longRunningTask;
    }

    public PushMessage getMessage() {
        return this.message;
    }

    public static Builder newBuilder(PushMessage pushMessage) {
        return new Builder(pushMessage);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean longRunningTask;
        /* access modifiers changed from: private */
        public PushMessage message;
        /* access modifiers changed from: private */
        public String notificationChannelId;
        /* access modifiers changed from: private */
        public int notificationId;
        /* access modifiers changed from: private */
        public String notificationTag;

        private Builder(PushMessage pushMessage) {
            this.notificationId = -1;
            this.notificationChannelId = NotificationProvider.DEFAULT_NOTIFICATION_CHANNEL;
            this.message = pushMessage;
        }

        public Builder setNotificationId(String str, int i) {
            this.notificationTag = str;
            this.notificationId = i;
            return this;
        }

        public Builder setNotificationChannelId(String str) {
            this.notificationChannelId = str;
            return this;
        }

        public Builder setRequiresLongRunningTask(boolean z) {
            this.longRunningTask = z;
            return this;
        }

        public NotificationArguments build() {
            return new NotificationArguments(this);
        }
    }
}
