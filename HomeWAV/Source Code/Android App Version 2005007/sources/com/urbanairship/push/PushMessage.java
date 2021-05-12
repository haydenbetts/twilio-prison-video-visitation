package com.urbanairship.push;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.BuildConfig;
import com.urbanairship.Logger;
import com.urbanairship.actions.ActionValue;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAMathUtil;
import com.urbanairship.util.UAStringUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PushMessage implements Parcelable, JsonSerializable {
    private static final String ACCENGAGE_CONTENT_KEY = "a4scontent";
    public static final Parcelable.Creator<PushMessage> CREATOR = new Parcelable.Creator<PushMessage>() {
        public PushMessage createFromParcel(Parcel parcel) {
            Bundle readBundle = parcel.readBundle(PushMessage.class.getClassLoader());
            if (readBundle == null) {
                readBundle = new Bundle();
            }
            return new PushMessage(readBundle);
        }

        public PushMessage[] newArray(int i) {
            return new PushMessage[i];
        }
    };
    private static final String DEFAULT_SOUND_NAME = "default";
    public static final String EXTRA_ACTIONS = "com.urbanairship.actions";
    public static final String EXTRA_ALERT = "com.urbanairship.push.ALERT";
    public static final String EXTRA_CATEGORY = "com.urbanairship.category";
    public static final String EXTRA_DELIVERY_PRIORITY = "com.urbanairship.priority";
    public static final String EXTRA_EXPIRATION = "com.urbanairship.push.EXPIRATION";
    public static final String EXTRA_ICON = "com.urbanairship.icon";
    public static final String EXTRA_ICON_COLOR = "com.urbanairship.icon_color";
    public static final String EXTRA_INTERACTIVE_ACTIONS = "com.urbanairship.interactive_actions";
    public static final String EXTRA_INTERACTIVE_TYPE = "com.urbanairship.interactive_type";
    public static final String EXTRA_IN_APP_MESSAGE = "com.urbanairship.in_app";
    public static final String EXTRA_LOCAL_ONLY = "com.urbanairship.local_only";
    public static final String EXTRA_METADATA = "com.urbanairship.metadata";
    public static final String EXTRA_NOTIFICATION_CHANNEL = "com.urbanairship.notification_channel";
    public static final String EXTRA_NOTIFICATION_TAG = "com.urbanairship.notification_tag";
    static final String EXTRA_PING = "com.urbanairship.push.PING";
    public static final String EXTRA_PRIORITY = "com.urbanairship.priority";
    public static final String EXTRA_PUBLIC_NOTIFICATION = "com.urbanairship.public_notification";
    public static final String EXTRA_PUSH_ID = "com.urbanairship.push.CANONICAL_PUSH_ID";
    public static final String EXTRA_RICH_PUSH_ID = "_uamid";
    public static final String EXTRA_SEND_ID = "com.urbanairship.push.PUSH_ID";
    @Deprecated
    public static final String EXTRA_SOUND = "com.urbanairship.sound";
    public static final String EXTRA_STYLE = "com.urbanairship.style";
    public static final String EXTRA_SUMMARY = "com.urbanairship.summary";
    public static final String EXTRA_TITLE = "com.urbanairship.title";
    public static final String EXTRA_VISIBILITY = "com.urbanairship.visibility";
    public static final String EXTRA_WEARABLE = "com.urbanairship.wearable";
    static final int MAX_PRIORITY = 2;
    static final int MAX_VISIBILITY = 1;
    private static final String MESSAGE_CENTER_ACTION = "^mc";
    static final int MIN_PRIORITY = -2;
    static final int MIN_VISIBILITY = -1;
    public static final String PRIORITY_HIGH = "high";
    static final int VISIBILITY_PUBLIC = 1;
    private final Map<String, String> data;
    private Bundle pushBundle;
    private Uri sound = null;

    public int describeContents() {
        return 0;
    }

    public PushMessage(Bundle bundle) {
        this.pushBundle = bundle;
        this.data = new HashMap();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                this.data.put(str, String.valueOf(obj));
            }
        }
    }

    public PushMessage(Map<String, String> map) {
        this.data = new HashMap(map);
    }

    /* access modifiers changed from: package-private */
    public boolean isExpired() {
        String str = this.data.get(EXTRA_EXPIRATION);
        if (!UAStringUtil.isEmpty(str)) {
            Logger.verbose("Notification expiration time is \"%s\"", str);
            try {
                if (Long.parseLong(str) * 1000 < System.currentTimeMillis()) {
                    return true;
                }
            } catch (NumberFormatException e) {
                Logger.debug(e, "Ignoring malformed expiration time.", new Object[0]);
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isPing() {
        return this.data.containsKey(EXTRA_PING);
    }

    public String getExtra(String str) {
        return this.data.get(str);
    }

    public String getExtra(String str, String str2) {
        String extra = getExtra(str);
        return extra == null ? str2 : extra;
    }

    public boolean containsAirshipKeys() {
        for (String startsWith : this.data.keySet()) {
            if (startsWith.startsWith(BuildConfig.LIBRARY_PACKAGE_NAME)) {
                return true;
            }
        }
        return false;
    }

    public String getCanonicalPushId() {
        return this.data.get(EXTRA_PUSH_ID);
    }

    public String getRichPushMessageId() {
        return this.data.get(EXTRA_RICH_PUSH_ID);
    }

    public String getAlert() {
        return this.data.get(EXTRA_ALERT);
    }

    public String getSendId() {
        return this.data.get(EXTRA_SEND_ID);
    }

    public String getMetadata() {
        return this.data.get(EXTRA_METADATA);
    }

    public Bundle getPushBundle() {
        if (this.pushBundle == null) {
            this.pushBundle = new Bundle();
            for (Map.Entry next : this.data.entrySet()) {
                this.pushBundle.putString((String) next.getKey(), (String) next.getValue());
            }
        }
        return this.pushBundle;
    }

    public boolean isAccengagePush() {
        return this.data.containsKey(ACCENGAGE_CONTENT_KEY);
    }

    public boolean isAirshipPush() {
        return this.data.containsKey(EXTRA_SEND_ID) || this.data.containsKey(EXTRA_PUSH_ID) || this.data.containsKey(EXTRA_METADATA);
    }

    public Map<String, ActionValue> getActions() {
        String str = this.data.get(EXTRA_ACTIONS);
        HashMap hashMap = new HashMap();
        try {
            JsonMap map = JsonValue.parseString(str).getMap();
            if (map != null) {
                Iterator<Map.Entry<String, JsonValue>> it = map.iterator();
                while (it.hasNext()) {
                    Map.Entry next = it.next();
                    hashMap.put(next.getKey(), new ActionValue((JsonValue) next.getValue()));
                }
            }
            if (!UAStringUtil.isEmpty(getRichPushMessageId())) {
                hashMap.put("^mc", ActionValue.wrap(getRichPushMessageId()));
            }
            return hashMap;
        } catch (JsonException unused) {
            Logger.error("Unable to parse action payload: %s", str);
            return hashMap;
        }
    }

    public String getInteractiveActionsPayload() {
        return this.data.get(EXTRA_INTERACTIVE_ACTIONS);
    }

    public String getInteractiveNotificationType() {
        return this.data.get(EXTRA_INTERACTIVE_TYPE);
    }

    public String getTitle() {
        return this.data.get(EXTRA_TITLE);
    }

    public String getSummary() {
        return this.data.get(EXTRA_SUMMARY);
    }

    public String getWearablePayload() {
        return this.data.get(EXTRA_WEARABLE);
    }

    public String getStylePayload() {
        return this.data.get(EXTRA_STYLE);
    }

    public boolean isLocalOnly() {
        return Boolean.parseBoolean(this.data.get(EXTRA_LOCAL_ONLY));
    }

    public int getPriority() {
        try {
            String str = this.data.get("com.urbanairship.priority");
            if (UAStringUtil.isEmpty(str)) {
                return 0;
            }
            return UAMathUtil.constrain(Integer.parseInt(str), -2, 2);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public int getVisibility() {
        try {
            String str = this.data.get(EXTRA_VISIBILITY);
            if (UAStringUtil.isEmpty(str)) {
                return 1;
            }
            return UAMathUtil.constrain(Integer.parseInt(str), -1, 1);
        } catch (NumberFormatException unused) {
            return 1;
        }
    }

    public String getPublicNotificationPayload() {
        return this.data.get(EXTRA_PUBLIC_NOTIFICATION);
    }

    public String getCategory() {
        return this.data.get(EXTRA_CATEGORY);
    }

    @Deprecated
    public Uri getSound(Context context) {
        if (this.sound == null && this.data.get(EXTRA_SOUND) != null) {
            String str = this.data.get(EXTRA_SOUND);
            int identifier = context.getResources().getIdentifier(str, "raw", context.getPackageName());
            if (identifier != 0) {
                this.sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + identifier);
            } else if (!"default".equals(str)) {
                Logger.warn("PushMessage - unable to find notification sound with name: %s", str);
            }
        }
        return this.sound;
    }

    public int getIconColor(int i) {
        String str = this.data.get(EXTRA_ICON_COLOR);
        if (str != null) {
            try {
                return Color.parseColor(str);
            } catch (IllegalArgumentException unused) {
                Logger.warn("Unrecognized icon color string: %s. Using default color: %s", str, Integer.valueOf(i));
            }
        }
        return i;
    }

    public int getIcon(Context context, int i) {
        String str = this.data.get(EXTRA_ICON);
        if (str != null) {
            int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
            if (identifier != 0) {
                return identifier;
            }
            Logger.warn("PushMessage - unable to find icon drawable with name: %s. Using default icon: %s", str, Integer.valueOf(i));
        }
        return i;
    }

    public String getNotificationTag() {
        return this.data.get(EXTRA_NOTIFICATION_TAG);
    }

    public String getNotificationChannel() {
        return this.data.get(EXTRA_NOTIFICATION_CHANNEL);
    }

    public String getNotificationChannel(String str) {
        String str2 = this.data.get(EXTRA_NOTIFICATION_CHANNEL);
        return str2 == null ? str : str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.data.equals(((PushMessage) obj).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public static PushMessage fromIntent(Intent intent) {
        if (intent == null) {
            return null;
        }
        try {
            Bundle bundleExtra = intent.getBundleExtra(PushManager.EXTRA_PUSH_MESSAGE_BUNDLE);
            if (bundleExtra == null) {
                return null;
            }
            return new PushMessage(bundleExtra);
        } catch (BadParcelableException e) {
            Logger.error(e, "Failed to parse push message from intent.", new Object[0]);
            return null;
        }
    }

    public String toString() {
        return this.data.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(getPushBundle());
    }

    public JsonValue toJsonValue() {
        return JsonValue.wrapOpt(this.data);
    }

    public static PushMessage fromJsonValue(JsonValue jsonValue) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : jsonValue.optMap().entrySet()) {
            if (((JsonValue) next.getValue()).isString()) {
                hashMap.put(next.getKey(), ((JsonValue) next.getValue()).optString());
            } else {
                hashMap.put(next.getKey(), ((JsonValue) next.getValue()).toString());
            }
        }
        return new PushMessage((Map<String, String>) hashMap);
    }

    public boolean containsKey(String str) {
        return this.data.containsKey(str);
    }
}
