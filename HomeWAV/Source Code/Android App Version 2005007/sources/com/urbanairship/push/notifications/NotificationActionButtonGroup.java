package com.urbanairship.push.notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationActionButtonGroup {
    private final List<NotificationActionButton> actionButtons;

    private NotificationActionButtonGroup(List<NotificationActionButton> list) {
        this.actionButtons = new ArrayList(list);
    }

    public List<NotificationActionButton> getNotificationActionButtons() {
        return new ArrayList(this.actionButtons);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.core.app.NotificationCompat.Action> createAndroidActions(android.content.Context r6, com.urbanairship.push.notifications.NotificationArguments r7, java.lang.String r8) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = com.urbanairship.util.UAStringUtil.isEmpty(r8)
            r2 = 0
            if (r1 != 0) goto L_0x0021
            com.urbanairship.json.JsonValue r1 = com.urbanairship.json.JsonValue.parseString(r8)     // Catch:{ JsonException -> 0x0015 }
            com.urbanairship.json.JsonMap r8 = r1.optMap()     // Catch:{ JsonException -> 0x0015 }
            goto L_0x0022
        L_0x0015:
            r1 = move-exception
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = 0
            r3[r4] = r8
            java.lang.String r8 = "Failed to parse notification actions payload: %s"
            com.urbanairship.Logger.error(r1, r8, r3)
        L_0x0021:
            r8 = r2
        L_0x0022:
            java.util.List r1 = r5.getNotificationActionButtons()
            java.util.Iterator r1 = r1.iterator()
        L_0x002a:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = r1.next()
            com.urbanairship.push.notifications.NotificationActionButton r3 = (com.urbanairship.push.notifications.NotificationActionButton) r3
            if (r8 != 0) goto L_0x003a
            r4 = r2
            goto L_0x0046
        L_0x003a:
            java.lang.String r4 = r3.getId()
            com.urbanairship.json.JsonValue r4 = r8.opt(r4)
            java.lang.String r4 = r4.toString()
        L_0x0046:
            androidx.core.app.NotificationCompat$Action r3 = r3.createAndroidNotificationAction(r6, r4, r7)
            r0.add(r3)
            goto L_0x002a
        L_0x004e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.push.notifications.NotificationActionButtonGroup.createAndroidActions(android.content.Context, com.urbanairship.push.notifications.NotificationArguments, java.lang.String):java.util.List");
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final List<NotificationActionButton> actionButtons = new ArrayList();

        public Builder addNotificationActionButton(NotificationActionButton notificationActionButton) {
            this.actionButtons.add(notificationActionButton);
            return this;
        }

        public NotificationActionButtonGroup build() {
            return new NotificationActionButtonGroup(this.actionButtons);
        }
    }
}
