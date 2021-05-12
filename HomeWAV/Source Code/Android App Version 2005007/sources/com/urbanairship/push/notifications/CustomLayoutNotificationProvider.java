package com.urbanairship.push.notifications;

import android.content.Context;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushMessage;

public class CustomLayoutNotificationProvider extends AirshipNotificationProvider {
    private final int layoutId;

    public CustomLayoutNotificationProvider(Context context, AirshipConfigOptions airshipConfigOptions, int i) {
        super(context, airshipConfigOptions);
        this.layoutId = i;
    }

    /* access modifiers changed from: protected */
    public NotificationCompat.Builder onExtendBuilder(Context context, NotificationCompat.Builder builder, NotificationArguments notificationArguments) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), this.layoutId);
        onBindContentView(remoteViews, notificationArguments);
        return builder.setCustomContentView(remoteViews);
    }

    /* access modifiers changed from: protected */
    public void onBindContentView(RemoteViews remoteViews, NotificationArguments notificationArguments) {
        PushMessage message = notificationArguments.getMessage();
        remoteViews.setTextViewText(16908310, message.getTitle() != null ? message.getTitle() : UAirship.getAppName());
        remoteViews.setTextViewText(16908299, message.getAlert());
        remoteViews.setTextViewText(16908304, message.getSummary());
        remoteViews.setImageViewResource(16908294, getSmallIcon());
    }
}
