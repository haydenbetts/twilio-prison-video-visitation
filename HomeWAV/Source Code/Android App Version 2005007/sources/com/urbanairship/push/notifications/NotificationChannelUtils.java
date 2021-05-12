package com.urbanairship.push.notifications;

import android.app.Notification;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;

public class NotificationChannelUtils {
    static int priorityForImportance(int i) {
        if (i == 1) {
            return -2;
        }
        if (i == 3) {
            return 0;
        }
        if (i != 4) {
            return i != 5 ? -1 : 2;
        }
        return 1;
    }

    public static String getActiveChannel(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2.equals(str) || UAirship.shared().getPushManager().getNotificationChannelRegistry().getNotificationChannelSync(str) != null) {
            return str;
        }
        Logger.error("Notification channel %s does not exist. Falling back to %s", str, str2);
        return str2;
    }

    public static void applyLegacySettings(Notification notification, NotificationChannelCompat notificationChannelCompat) {
        notification.priority = priorityForImportance(notificationChannelCompat.getImportance());
        if (notificationChannelCompat.getImportance() < 3) {
            notification.vibrate = null;
            notification.sound = null;
            notification.ledARGB = 0;
            notification.flags &= -2;
            notification.defaults = 0;
            return;
        }
        if (notificationChannelCompat.getSound() != null) {
            notification.sound = notificationChannelCompat.getSound();
            notification.defaults &= -2;
        }
        if (notificationChannelCompat.shouldShowLights()) {
            notification.flags |= 1;
            if (notificationChannelCompat.getLightColor() != 0) {
                notification.ledARGB = notificationChannelCompat.getLightColor();
                notification.defaults &= -5;
            } else {
                notification.defaults |= 4;
            }
        }
        if (!notificationChannelCompat.shouldVibrate()) {
            return;
        }
        if (notificationChannelCompat.getVibrationPattern() != null) {
            notification.vibrate = notificationChannelCompat.getVibrationPattern();
            notification.defaults &= -3;
            return;
        }
        notification.defaults |= 2;
    }
}
