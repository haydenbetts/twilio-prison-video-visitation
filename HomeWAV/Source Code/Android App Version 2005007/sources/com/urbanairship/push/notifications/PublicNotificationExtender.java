package com.urbanairship.push.notifications;

import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;

public class PublicNotificationExtender implements NotificationCompat.Extender {
    static final String ALERT_KEY = "alert";
    static final String SUMMARY_KEY = "summary";
    static final String TITLE_KEY = "title";
    private int accentColor;
    private final NotificationArguments arguments;
    private final Context context;
    private int largeIconId;
    private int smallIconId;

    public PublicNotificationExtender(Context context2, NotificationArguments notificationArguments) {
        this.context = context2;
        this.arguments = notificationArguments;
        this.smallIconId = context2.getApplicationInfo().icon;
    }

    public PublicNotificationExtender setAccentColor(int i) {
        this.accentColor = i;
        return this;
    }

    public PublicNotificationExtender setSmallIcon(int i) {
        this.smallIconId = i;
        return this;
    }

    public PublicNotificationExtender setLargeIcon(int i) {
        this.largeIconId = i;
        return this;
    }

    public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
        if (UAStringUtil.isEmpty(this.arguments.getMessage().getPublicNotificationPayload())) {
            return builder;
        }
        try {
            JsonMap optMap = JsonValue.parseString(this.arguments.getMessage().getPublicNotificationPayload()).optMap();
            NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this.context, this.arguments.getNotificationChannelId()).setContentTitle(optMap.opt("title").optString()).setContentText(optMap.opt(ALERT_KEY).optString()).setColor(this.accentColor).setAutoCancel(true).setSmallIcon(this.smallIconId);
            if (this.largeIconId != 0) {
                smallIcon.setLargeIcon(BitmapFactory.decodeResource(this.context.getResources(), this.largeIconId));
            }
            if (optMap.containsKey("summary")) {
                smallIcon.setSubText(optMap.opt("summary").optString());
            }
            builder.setPublicVersion(smallIcon.build());
        } catch (JsonException e) {
            Logger.error(e, "Failed to parse public notification.", new Object[0]);
        }
        return builder;
    }
}
