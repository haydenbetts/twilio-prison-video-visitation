package com.urbanairship.analytics;

import android.os.Bundle;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.push.NotificationActionButtonInfo;
import com.urbanairship.push.NotificationInfo;

public class InteractiveNotificationEvent extends Event {
    private static final String BUTTON_DESCRIPTION_KEY = "button_description";
    private static final String BUTTON_GROUP_KEY = "button_group";
    private static final String BUTTON_ID_KEY = "button_id";
    private static final String FOREGROUND_KEY = "foreground";
    private static final String SEND_ID_KEY = "send_id";
    private static final String TYPE = "interactive_notification_action";
    private static final String USER_INPUT = "user_input";
    private final String buttonDescription;
    private final String buttonGroupId;
    private final String buttonId;
    private final boolean isForeground;
    private final Bundle remoteInput;
    private final String sendId;

    public final String getType() {
        return TYPE;
    }

    public InteractiveNotificationEvent(NotificationInfo notificationInfo, NotificationActionButtonInfo notificationActionButtonInfo) {
        this.sendId = notificationInfo.getMessage().getSendId();
        this.buttonGroupId = notificationInfo.getMessage().getInteractiveNotificationType();
        this.buttonId = notificationActionButtonInfo.getButtonId();
        this.buttonDescription = notificationActionButtonInfo.getDescription();
        this.isForeground = notificationActionButtonInfo.isForeground();
        this.remoteInput = notificationActionButtonInfo.getRemoteInput();
    }

    public final JsonMap getEventData() {
        JsonMap.Builder put = JsonMap.newBuilder().put(SEND_ID_KEY, this.sendId).put(BUTTON_GROUP_KEY, this.buttonGroupId).put(BUTTON_ID_KEY, this.buttonId).put(BUTTON_DESCRIPTION_KEY, this.buttonDescription).put(FOREGROUND_KEY, this.isForeground);
        Bundle bundle = this.remoteInput;
        if (bundle != null && !bundle.isEmpty()) {
            JsonMap.Builder newBuilder = JsonMap.newBuilder();
            for (String str : this.remoteInput.keySet()) {
                newBuilder.put(str, this.remoteInput.getString(str));
            }
            put.put(USER_INPUT, (JsonSerializable) newBuilder.build());
        }
        return put.build();
    }
}
