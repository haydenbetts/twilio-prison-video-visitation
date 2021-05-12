package com.urbanairship.analytics;

import android.app.NotificationChannelGroup;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.push.PushMessage;
import com.urbanairship.push.notifications.NotificationChannelCompat;
import com.urbanairship.util.UAStringUtil;

public class PushArrivedEvent extends Event {
    private static final String DEFAULT_SEND_ID = "MISSING_SEND_ID";
    private static final String NOTIFICATION_CHANNEL_GROUP_BLOCKED = "blocked";
    private static final String NOTIFICATION_CHANNEL_GROUP_KEY = "group";
    private static final String NOTIFICATION_CHANNEL_ID_KEY = "identifier";
    private static final String NOTIFICATION_CHANNEL_IMPORTANCE_KEY = "importance";
    private static final String NOTIFICATION_CHANNEL_KEY = "notification_channel";
    static final String TYPE = "push_arrived";
    private final PushMessage message;
    private NotificationChannelCompat notificationChannel;

    private String importanceString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN" : "MAX" : "HIGH" : "DEFAULT" : "LOW" : "MIN" : "NONE";
    }

    public final String getType() {
        return TYPE;
    }

    public PushArrivedEvent(PushMessage pushMessage) {
        this(pushMessage, (NotificationChannelCompat) null);
    }

    public PushArrivedEvent(PushMessage pushMessage, NotificationChannelCompat notificationChannelCompat) {
        this.message = pushMessage;
        this.notificationChannel = notificationChannelCompat;
    }

    private void addNotificationChannelData(JsonMap.Builder builder) {
        JsonMap jsonMap;
        String importanceString = importanceString(this.notificationChannel.getImportance());
        String group = this.notificationChannel.getGroup();
        if (Build.VERSION.SDK_INT < 28 || group == null) {
            jsonMap = null;
        } else {
            NotificationChannelGroup notificationChannelGroup = NotificationManagerCompat.from(UAirship.getApplicationContext()).getNotificationChannelGroup(group);
            jsonMap = JsonMap.newBuilder().put(NOTIFICATION_CHANNEL_GROUP_KEY, (JsonSerializable) JsonMap.newBuilder().putOpt(NOTIFICATION_CHANNEL_GROUP_BLOCKED, String.valueOf(notificationChannelGroup != null && notificationChannelGroup.isBlocked())).build()).build();
        }
        builder.put(NOTIFICATION_CHANNEL_KEY, (JsonSerializable) JsonMap.newBuilder().put(NOTIFICATION_CHANNEL_ID_KEY, this.notificationChannel.getId()).put(NOTIFICATION_CHANNEL_IMPORTANCE_KEY, importanceString).putOpt(NOTIFICATION_CHANNEL_GROUP_KEY, jsonMap).build());
    }

    public final JsonMap getEventData() {
        JsonMap.Builder put = JsonMap.newBuilder().put("push_id", !UAStringUtil.isEmpty(this.message.getSendId()) ? this.message.getSendId() : DEFAULT_SEND_ID).put(TtmlNode.TAG_METADATA, this.message.getMetadata()).put("connection_type", getConnectionType()).put("connection_subtype", getConnectionSubType()).put("carrier", getCarrier());
        if (this.notificationChannel != null) {
            addNotificationChannelData(put);
        }
        return put.build();
    }
}
