package com.urbanairship.push.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.provider.Settings;
import android.util.Xml;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.AttributeSetConfigParser;
import com.urbanairship.util.UAStringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

public class NotificationChannelCompat implements JsonSerializable {
    private static final String CAN_BYPASS_DND_KEY = "can_bypass_dnd";
    private static final String CAN_SHOW_BADGE_KEY = "can_show_badge";
    private static final String DESCRIPTION_KEY = "description";
    private static final String GROUP_KEY = "group";
    private static final String ID_KEY = "id";
    private static final String IMPORTANCE_KEY = "importance";
    private static final String LIGHT_COLOR_KEY = "light_color";
    private static final int LOCKSCREEN_VISIBILITY_DEFAULT_VALUE = -1000;
    private static final String LOCKSCREEN_VISIBILITY_KEY = "lockscreen_visibility";
    private static final String NAME_KEY = "name";
    private static final String NOTIFICATION_CHANNEL_TAG = "NotificationChannel";
    private static final String SHOULD_SHOW_LIGHTS_KEY = "should_show_lights";
    private static final String SHOULD_VIBRATE_KEY = "should_vibrate";
    private static final String SOUND_KEY = "sound";
    private static final String VIBRATION_PATTERN_KEY = "vibration_pattern";
    private boolean bypassDnd = false;
    private String description = null;
    private String group = null;
    private String identifier;
    private int importance;
    private int lightColor = 0;
    private int lockscreenVisibility = -1000;
    private CharSequence name;
    private boolean shouldVibrate = false;
    private boolean showBadge = true;
    private boolean showLights = false;
    private Uri sound = Settings.System.DEFAULT_NOTIFICATION_URI;
    private long[] vibrationPattern = null;

    public NotificationChannelCompat(NotificationChannel notificationChannel) {
        this.bypassDnd = notificationChannel.canBypassDnd();
        this.showBadge = notificationChannel.canShowBadge();
        this.showLights = notificationChannel.shouldShowLights();
        this.shouldVibrate = notificationChannel.shouldVibrate();
        this.description = notificationChannel.getDescription();
        this.group = notificationChannel.getGroup();
        this.identifier = notificationChannel.getId();
        this.name = notificationChannel.getName();
        this.sound = notificationChannel.getSound();
        this.importance = notificationChannel.getImportance();
        this.lightColor = notificationChannel.getLightColor();
        this.lockscreenVisibility = notificationChannel.getLockscreenVisibility();
        this.vibrationPattern = notificationChannel.getVibrationPattern();
    }

    public NotificationChannelCompat(String str, CharSequence charSequence, int i) {
        this.identifier = str;
        this.name = charSequence;
        this.importance = i;
    }

    public NotificationChannel toNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel(this.identifier, this.name, this.importance);
        notificationChannel.setBypassDnd(this.bypassDnd);
        notificationChannel.setShowBadge(this.showBadge);
        notificationChannel.enableLights(this.showLights);
        notificationChannel.enableVibration(this.shouldVibrate);
        notificationChannel.setDescription(this.description);
        notificationChannel.setGroup(this.group);
        notificationChannel.setLightColor(this.lightColor);
        notificationChannel.setVibrationPattern(this.vibrationPattern);
        notificationChannel.setLockscreenVisibility(this.lockscreenVisibility);
        notificationChannel.setSound(this.sound, Notification.AUDIO_ATTRIBUTES_DEFAULT);
        return notificationChannel;
    }

    public boolean getBypassDnd() {
        return this.bypassDnd;
    }

    public void setBypassDnd(boolean z) {
        this.bypassDnd = z;
    }

    public boolean getShowBadge() {
        return this.showBadge;
    }

    public void setShowBadge(boolean z) {
        this.showBadge = z;
    }

    public boolean shouldShowLights() {
        return this.showLights;
    }

    public void enableLights(boolean z) {
        this.showLights = z;
    }

    public boolean shouldVibrate() {
        return this.shouldVibrate;
    }

    public void enableVibration(boolean z) {
        this.shouldVibrate = z;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public String getId() {
        return this.identifier;
    }

    public int getImportance() {
        return this.importance;
    }

    public void setImportance(int i) {
        this.importance = i;
    }

    public int getLightColor() {
        return this.lightColor;
    }

    public void setLightColor(int i) {
        this.lightColor = i;
    }

    public int getLockscreenVisibility() {
        return this.lockscreenVisibility;
    }

    public void setLockscreenVisibility(int i) {
        this.lockscreenVisibility = i;
    }

    public CharSequence getName() {
        return this.name;
    }

    public void setName(CharSequence charSequence) {
        this.name = charSequence;
    }

    public Uri getSound() {
        return this.sound;
    }

    public void setSound(Uri uri) {
        this.sound = uri;
    }

    public long[] getVibrationPattern() {
        return this.vibrationPattern;
    }

    public void setVibrationPattern(long[] jArr) {
        this.vibrationPattern = jArr;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(CAN_BYPASS_DND_KEY, Boolean.valueOf(getBypassDnd())).putOpt(CAN_SHOW_BADGE_KEY, Boolean.valueOf(getShowBadge())).putOpt(SHOULD_SHOW_LIGHTS_KEY, Boolean.valueOf(shouldShowLights())).putOpt(SHOULD_VIBRATE_KEY, Boolean.valueOf(shouldVibrate())).putOpt(DESCRIPTION_KEY, getDescription()).putOpt(GROUP_KEY, getGroup()).putOpt("id", getId()).putOpt(IMPORTANCE_KEY, Integer.valueOf(getImportance())).putOpt(LIGHT_COLOR_KEY, Integer.valueOf(getLightColor())).putOpt(LOCKSCREEN_VISIBILITY_KEY, Integer.valueOf(getLockscreenVisibility())).putOpt("name", getName().toString()).putOpt(SOUND_KEY, getSound() != null ? getSound().toString() : null).putOpt(VIBRATION_PATTERN_KEY, JsonValue.wrapOpt(getVibrationPattern())).build().toJsonValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NotificationChannelCompat notificationChannelCompat = (NotificationChannelCompat) obj;
        if (this.bypassDnd != notificationChannelCompat.bypassDnd || this.showBadge != notificationChannelCompat.showBadge || this.showLights != notificationChannelCompat.showLights || this.shouldVibrate != notificationChannelCompat.shouldVibrate || this.importance != notificationChannelCompat.importance || this.lightColor != notificationChannelCompat.lightColor || this.lockscreenVisibility != notificationChannelCompat.lockscreenVisibility) {
            return false;
        }
        String str = this.description;
        if (str == null ? notificationChannelCompat.description != null : !str.equals(notificationChannelCompat.description)) {
            return false;
        }
        String str2 = this.group;
        if (str2 == null ? notificationChannelCompat.group != null : !str2.equals(notificationChannelCompat.group)) {
            return false;
        }
        String str3 = this.identifier;
        if (str3 == null ? notificationChannelCompat.identifier != null : !str3.equals(notificationChannelCompat.identifier)) {
            return false;
        }
        CharSequence charSequence = this.name;
        if (charSequence == null ? notificationChannelCompat.name != null : !charSequence.equals(notificationChannelCompat.name)) {
            return false;
        }
        Uri uri = this.sound;
        if (uri == null ? notificationChannelCompat.sound == null : uri.equals(notificationChannelCompat.sound)) {
            return Arrays.equals(this.vibrationPattern, notificationChannelCompat.vibrationPattern);
        }
        return false;
    }

    public int hashCode() {
        int i = (((((((this.bypassDnd ? 1 : 0) * true) + (this.showBadge ? 1 : 0)) * 31) + (this.showLights ? 1 : 0)) * 31) + (this.shouldVibrate ? 1 : 0)) * 31;
        String str = this.description;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.group;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.identifier;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        CharSequence charSequence = this.name;
        int hashCode4 = (hashCode3 + (charSequence != null ? charSequence.hashCode() : 0)) * 31;
        Uri uri = this.sound;
        if (uri != null) {
            i2 = uri.hashCode();
        }
        return ((((((((hashCode4 + i2) * 31) + this.importance) * 31) + this.lightColor) * 31) + this.lockscreenVisibility) * 31) + Arrays.hashCode(this.vibrationPattern);
    }

    public String toString() {
        return "NotificationChannelCompat{bypassDnd=" + this.bypassDnd + ", showBadge=" + this.showBadge + ", showLights=" + this.showLights + ", shouldVibrate=" + this.shouldVibrate + ", description='" + this.description + '\'' + ", group='" + this.group + '\'' + ", identifier='" + this.identifier + '\'' + ", name=" + this.name + ", sound=" + this.sound + ", importance=" + this.importance + ", lightColor=" + this.lightColor + ", lockscreenVisibility=" + this.lockscreenVisibility + ", vibrationPattern=" + Arrays.toString(this.vibrationPattern) + '}';
    }

    public static NotificationChannelCompat fromJson(JsonValue jsonValue) {
        JsonMap map = jsonValue.getMap();
        if (map != null) {
            String string = map.opt("id").getString();
            String string2 = map.opt("name").getString();
            int i = map.opt(IMPORTANCE_KEY).getInt(-1);
            if (!(string == null || string2 == null || i == -1)) {
                NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat(string, string2, i);
                notificationChannelCompat.setBypassDnd(map.opt(CAN_BYPASS_DND_KEY).getBoolean(false));
                notificationChannelCompat.setShowBadge(map.opt(CAN_SHOW_BADGE_KEY).getBoolean(true));
                notificationChannelCompat.enableLights(map.opt(SHOULD_SHOW_LIGHTS_KEY).getBoolean(false));
                notificationChannelCompat.enableVibration(map.opt(SHOULD_VIBRATE_KEY).getBoolean(false));
                notificationChannelCompat.setDescription(map.opt(DESCRIPTION_KEY).getString());
                notificationChannelCompat.setGroup(map.opt(GROUP_KEY).getString());
                notificationChannelCompat.setLightColor(map.opt(LIGHT_COLOR_KEY).getInt(0));
                notificationChannelCompat.setLockscreenVisibility(map.opt(LOCKSCREEN_VISIBILITY_KEY).getInt(-1000));
                notificationChannelCompat.setName(map.opt("name").optString());
                String string3 = map.opt(SOUND_KEY).getString();
                if (!UAStringUtil.isEmpty(string3)) {
                    notificationChannelCompat.setSound(Uri.parse(string3));
                }
                JsonList list = map.opt(VIBRATION_PATTERN_KEY).getList();
                if (list != null) {
                    long[] jArr = new long[list.size()];
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        jArr[i2] = list.get(i2).getLong(0);
                    }
                    notificationChannelCompat.setVibrationPattern(jArr);
                }
                return notificationChannelCompat;
            }
        }
        Logger.error("Unable to deserialize notification channel: %s", jsonValue);
        return null;
    }

    /* JADX INFO: finally extract failed */
    public static List<NotificationChannelCompat> fromXml(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            List<NotificationChannelCompat> parseChannels = parseChannels(context, xml);
            xml.close();
            return parseChannels;
        } catch (Exception e) {
            Logger.error(e, "Failed to parse channels", new Object[0]);
            xml.close();
            return Collections.emptyList();
        } catch (Throwable th) {
            xml.close();
            throw th;
        }
    }

    private static List<NotificationChannelCompat> parseChannels(Context context, XmlResourceParser xmlResourceParser) throws IOException, XmlPullParserException {
        ArrayList arrayList = new ArrayList();
        while (1 != xmlResourceParser.next()) {
            if (2 == xmlResourceParser.getEventType() && NOTIFICATION_CHANNEL_TAG.equals(xmlResourceParser.getName())) {
                AttributeSetConfigParser attributeSetConfigParser = new AttributeSetConfigParser(context, Xml.asAttributeSet(xmlResourceParser));
                String string = attributeSetConfigParser.getString("name");
                String string2 = attributeSetConfigParser.getString("id");
                int i = attributeSetConfigParser.getInt(IMPORTANCE_KEY, -1);
                if (UAStringUtil.isEmpty(string) || UAStringUtil.isEmpty(string2) || i == -1) {
                    Logger.error("Invalid notification channel. Missing name (%s), id (%s), or importance (%s)", string, string2, Integer.valueOf(i));
                } else {
                    NotificationChannelCompat notificationChannelCompat = new NotificationChannelCompat(string2, string, i);
                    notificationChannelCompat.setBypassDnd(attributeSetConfigParser.getBoolean(CAN_BYPASS_DND_KEY, false));
                    notificationChannelCompat.setShowBadge(attributeSetConfigParser.getBoolean(CAN_SHOW_BADGE_KEY, true));
                    notificationChannelCompat.enableLights(attributeSetConfigParser.getBoolean(SHOULD_SHOW_LIGHTS_KEY, false));
                    notificationChannelCompat.enableVibration(attributeSetConfigParser.getBoolean(SHOULD_VIBRATE_KEY, false));
                    notificationChannelCompat.setDescription(attributeSetConfigParser.getString(DESCRIPTION_KEY));
                    notificationChannelCompat.setGroup(attributeSetConfigParser.getString(GROUP_KEY));
                    notificationChannelCompat.setLightColor(attributeSetConfigParser.getColor(LIGHT_COLOR_KEY, 0));
                    notificationChannelCompat.setLockscreenVisibility(attributeSetConfigParser.getInt(LOCKSCREEN_VISIBILITY_KEY, -1000));
                    int rawResourceId = attributeSetConfigParser.getRawResourceId(SOUND_KEY);
                    if (rawResourceId != 0) {
                        notificationChannelCompat.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + context.getResources().getResourceEntryName(rawResourceId)));
                    } else {
                        String string3 = attributeSetConfigParser.getString(SOUND_KEY);
                        if (!UAStringUtil.isEmpty(string3)) {
                            notificationChannelCompat.setSound(Uri.parse(string3));
                        }
                    }
                    String string4 = attributeSetConfigParser.getString(VIBRATION_PATTERN_KEY);
                    if (!UAStringUtil.isEmpty(string4)) {
                        String[] split = string4.split(",");
                        long[] jArr = new long[split.length];
                        for (int i2 = 0; i2 < split.length; i2++) {
                            jArr[i2] = Long.parseLong(split[i2]);
                        }
                        notificationChannelCompat.setVibrationPattern(jArr);
                    }
                    arrayList.add(notificationChannelCompat);
                }
            }
        }
        return arrayList;
    }
}
