package com.urbanairship.push.notifications;

import android.app.Notification;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;

public class WearableNotificationExtender implements NotificationCompat.Extender {
    static final String ALERT_KEY = "alert";
    static final String INTERACTIVE_ACTIONS_KEY = "interactive_actions";
    static final String INTERACTIVE_TYPE_KEY = "interactive_type";
    static final String TITLE_KEY = "title";
    private final NotificationArguments arguments;
    private final Context context;

    public WearableNotificationExtender(Context context2, NotificationArguments notificationArguments) {
        this.context = context2.getApplicationContext();
        this.arguments = notificationArguments;
    }

    public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
        NotificationActionButtonGroup notificationActionGroup;
        String wearablePayload = this.arguments.getMessage().getWearablePayload();
        if (wearablePayload == null) {
            return builder;
        }
        try {
            JsonMap optMap = JsonValue.parseString(wearablePayload).optMap();
            NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();
            String string = optMap.opt(INTERACTIVE_TYPE_KEY).getString();
            String jsonValue = optMap.opt(INTERACTIVE_ACTIONS_KEY).toString();
            if (UAStringUtil.isEmpty(jsonValue)) {
                jsonValue = this.arguments.getMessage().getInteractiveActionsPayload();
            }
            if (!UAStringUtil.isEmpty(string) && (notificationActionGroup = UAirship.shared().getPushManager().getNotificationActionGroup(string)) != null) {
                wearableExtender.addActions(notificationActionGroup.createAndroidActions(this.context, this.arguments, jsonValue));
            }
            builder.extend(wearableExtender);
            return builder;
        } catch (JsonException e) {
            Logger.error(e, "Failed to parse wearable payload.", new Object[0]);
            return builder;
        }
    }

    private Notification createWearPage(JsonMap jsonMap) {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        String string = jsonMap.opt("title").getString();
        if (!UAStringUtil.isEmpty(string)) {
            bigTextStyle.setBigContentTitle(string);
        }
        String string2 = jsonMap.opt(ALERT_KEY).getString();
        if (!UAStringUtil.isEmpty(string2)) {
            bigTextStyle.bigText(string2);
        }
        return new NotificationCompat.Builder(this.context, this.arguments.getNotificationChannelId()).setAutoCancel(true).setStyle(bigTextStyle).build();
    }
}
