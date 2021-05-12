package com.urbanairship.push.notifications;

import android.app.Notification;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.NotificationIdGenerator;
import com.urbanairship.util.UAStringUtil;

public class AirshipNotificationProvider implements NotificationProvider {
    public static final int TAG_NOTIFICATION_ID = 100;
    private int accentColor;
    private String defaultNotificationChannelId;
    private int largeIcon;
    private int smallIconId;
    private int titleId;

    public void onNotificationCreated(Context context, Notification notification, NotificationArguments notificationArguments) {
    }

    public void setDefaultTitle(int i) {
        this.titleId = i;
    }

    public int getDefaultTitle() {
        return this.titleId;
    }

    public void setSmallIcon(int i) {
        this.smallIconId = i;
    }

    public int getSmallIcon() {
        return this.smallIconId;
    }

    public void setLargeIcon(int i) {
        this.largeIcon = i;
    }

    public int getLargeIcon() {
        return this.largeIcon;
    }

    public void setDefaultAccentColor(int i) {
        this.accentColor = i;
    }

    public int getDefaultAccentColor() {
        return this.accentColor;
    }

    public void setDefaultNotificationChannelId(String str) {
        this.defaultNotificationChannelId = str;
    }

    public String getDefaultNotificationChannelId() {
        return this.defaultNotificationChannelId;
    }

    public AirshipNotificationProvider(Context context, AirshipConfigOptions airshipConfigOptions) {
        this.titleId = context.getApplicationInfo().labelRes;
        this.smallIconId = airshipConfigOptions.notificationIcon;
        this.largeIcon = airshipConfigOptions.notificationLargeIcon;
        this.accentColor = airshipConfigOptions.notificationAccentColor;
        if (airshipConfigOptions.notificationChannel != null) {
            this.defaultNotificationChannelId = airshipConfigOptions.notificationChannel;
        } else {
            this.defaultNotificationChannelId = NotificationProvider.DEFAULT_NOTIFICATION_CHANNEL;
        }
        if (this.smallIconId == 0) {
            this.smallIconId = context.getApplicationInfo().icon;
        }
        this.titleId = context.getApplicationInfo().labelRes;
    }

    public NotificationArguments onCreateNotificationArguments(Context context, PushMessage pushMessage) {
        return NotificationArguments.newBuilder(pushMessage).setNotificationChannelId(NotificationChannelUtils.getActiveChannel(pushMessage.getNotificationChannel(getDefaultNotificationChannelId()), NotificationProvider.DEFAULT_NOTIFICATION_CHANNEL)).setNotificationId(pushMessage.getNotificationTag(), getNextId(context, pushMessage)).build();
    }

    public NotificationResult onCreateNotification(Context context, NotificationArguments notificationArguments) {
        if (UAStringUtil.isEmpty(notificationArguments.getMessage().getAlert())) {
            return NotificationResult.cancel();
        }
        PushMessage message = notificationArguments.getMessage();
        NotificationCompat.Builder defaults = new NotificationCompat.Builder(context, notificationArguments.getNotificationChannelId()).setContentTitle(getTitle(context, message)).setContentText(message.getAlert()).setAutoCancel(true).setLocalOnly(message.isLocalOnly()).setColor(message.getIconColor(getDefaultAccentColor())).setSmallIcon(message.getIcon(context, getSmallIcon())).setPriority(message.getPriority()).setCategory(message.getCategory()).setVisibility(message.getVisibility()).setDefaults(-1);
        int largeIcon2 = getLargeIcon();
        if (largeIcon2 != 0) {
            defaults.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon2));
        }
        if (message.getSummary() != null) {
            defaults.setSubText(message.getSummary());
        }
        if (Build.VERSION.SDK_INT < 26) {
            applyDeprecatedSettings(context, message, defaults);
        }
        return NotificationResult.notification(onExtendBuilder(context, defaults, notificationArguments).build());
    }

    private void applyDeprecatedSettings(Context context, PushMessage pushMessage, NotificationCompat.Builder builder) {
        int i;
        if (pushMessage.getSound(context) != null) {
            builder.setSound(pushMessage.getSound(context));
            i = 2;
        } else {
            i = 3;
        }
        builder.setDefaults(i);
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Builder onExtendBuilder(Context context, NotificationCompat.Builder builder, NotificationArguments notificationArguments) {
        PushMessage message = notificationArguments.getMessage();
        builder.extend(new PublicNotificationExtender(context, notificationArguments).setAccentColor(getDefaultAccentColor()).setLargeIcon(getLargeIcon()).setSmallIcon(message.getIcon(context, getSmallIcon())));
        builder.extend(new WearableNotificationExtender(context, notificationArguments));
        builder.extend(new ActionsNotificationExtender(context, notificationArguments));
        builder.extend(new StyleNotificationExtender(context, message).setDefaultStyle(new NotificationCompat.BigTextStyle().bigText(notificationArguments.getMessage().getAlert())));
        return builder;
    }

    /* access modifiers changed from: protected */
    public int getNextId(Context context, PushMessage pushMessage) {
        if (pushMessage.getNotificationTag() != null) {
            return 100;
        }
        return NotificationIdGenerator.nextID();
    }

    /* access modifiers changed from: protected */
    public String getTitle(Context context, PushMessage pushMessage) {
        if (pushMessage.getTitle() != null) {
            return pushMessage.getTitle();
        }
        int i = this.titleId;
        if (i != 0) {
            return context.getString(i);
        }
        return null;
    }
}
