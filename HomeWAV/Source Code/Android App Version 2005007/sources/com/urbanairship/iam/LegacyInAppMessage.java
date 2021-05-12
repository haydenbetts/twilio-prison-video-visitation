package com.urbanairship.iam;

import android.graphics.Color;
import com.urbanairship.iam.banner.BannerDisplayContent;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.push.PushMessage;
import com.urbanairship.util.Checks;
import com.urbanairship.util.DateUtils;
import com.urbanairship.util.UAStringUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LegacyInAppMessage {
    private static final String ACTIONS_KEY = "actions";
    private static final String ALERT_KEY = "alert";
    private static final String BANNER_TYPE = "banner";
    private static final String BUTTON_ACTIONS_KEY = "button_actions";
    private static final String BUTTON_GROUP_KEY = "button_group";
    private static final long DEFAULT_EXPIRY_MS = 2592000000L;
    private static final String DISPLAY_KEY = "display";
    private static final String DURATION_KEY = "duration";
    private static final String EXPIRY_KEY = "expiry";
    private static final String EXTRA_KEY = "extra";
    private static final String MESSAGE_CENTER_ACTION = "^mc";
    private static final String ON_CLICK_KEY = "on_click";
    private static final String POSITION_KEY = "position";
    private static final String PRIMARY_COLOR_KEY = "primary_color";
    private static final String SECONDARY_COLOR_KEY = "secondary_color";
    private static final String TYPE_KEY = "type";
    private final String alert;
    private final Map<String, Map<String, JsonValue>> buttonActionValues;
    private final String buttonGroupId;
    private final Map<String, JsonValue> clickActionValues;
    private final Long durationMilliseconds;
    private final long expiryMS;
    private final JsonMap extras;
    private final String id;
    private final String placement;
    private final Integer primaryColor;
    private final Integer secondaryColor;

    private LegacyInAppMessage(Builder builder) {
        this.expiryMS = builder.expiryMS == null ? System.currentTimeMillis() + DEFAULT_EXPIRY_MS : builder.expiryMS.longValue();
        this.extras = builder.extras == null ? JsonMap.EMPTY_MAP : builder.extras;
        this.alert = builder.alert;
        this.durationMilliseconds = builder.durationMilliseconds;
        this.buttonGroupId = builder.buttonGroupId;
        this.buttonActionValues = builder.buttonActionValues;
        this.clickActionValues = builder.clickActionValues;
        this.placement = builder.placement;
        this.primaryColor = builder.primaryColor;
        this.secondaryColor = builder.secondaryColor;
        this.id = builder.id == null ? UUID.randomUUID().toString() : builder.id;
    }

    public long getExpiry() {
        return this.expiryMS;
    }

    public JsonMap getExtras() {
        return this.extras;
    }

    public String getAlert() {
        return this.alert;
    }

    public Map<String, JsonValue> getClickActionValues() {
        return Collections.unmodifiableMap(this.clickActionValues);
    }

    public Map<String, JsonValue> getButtonActionValues(String str) {
        Map map = this.buttonActionValues.get(str);
        if (map != null) {
            return Collections.unmodifiableMap(map);
        }
        return null;
    }

    public String getButtonGroupId() {
        return this.buttonGroupId;
    }

    public Long getDuration() {
        return this.durationMilliseconds;
    }

    public String getPlacement() {
        return this.placement;
    }

    public Integer getPrimaryColor() {
        return this.primaryColor;
    }

    public Integer getSecondaryColor() {
        return this.secondaryColor;
    }

    public String getId() {
        return this.id;
    }

    public static LegacyInAppMessage fromPush(PushMessage pushMessage) throws JsonException {
        if (!pushMessage.containsKey(PushMessage.EXTRA_IN_APP_MESSAGE)) {
            return null;
        }
        JsonValue parseString = JsonValue.parseString(pushMessage.getExtra(PushMessage.EXTRA_IN_APP_MESSAGE, ""));
        JsonMap optMap = parseString.optMap().opt("display").optMap();
        JsonMap optMap2 = parseString.optMap().opt("actions").optMap();
        if ("banner".equals(optMap.opt("type").getString())) {
            Builder newBuilder = newBuilder();
            newBuilder.setExtras(parseString.optMap().opt("extra").optMap()).setAlert(optMap.opt(ALERT_KEY).getString());
            if (optMap.containsKey(PRIMARY_COLOR_KEY)) {
                try {
                    newBuilder.setPrimaryColor(Integer.valueOf(Color.parseColor(optMap.opt(PRIMARY_COLOR_KEY).optString())));
                } catch (IllegalArgumentException e) {
                    throw new JsonException("Invalid primary color: " + optMap.opt(PRIMARY_COLOR_KEY), e);
                }
            }
            if (optMap.containsKey(SECONDARY_COLOR_KEY)) {
                try {
                    newBuilder.setSecondaryColor(Integer.valueOf(Color.parseColor(optMap.opt(SECONDARY_COLOR_KEY).optString())));
                } catch (IllegalArgumentException e2) {
                    throw new JsonException("Invalid secondary color: " + optMap.opt(SECONDARY_COLOR_KEY), e2);
                }
            }
            if (optMap.containsKey("duration")) {
                newBuilder.setDuration(Long.valueOf(TimeUnit.SECONDS.toMillis(optMap.opt("duration").getLong(0))));
            }
            long currentTimeMillis = System.currentTimeMillis() + DEFAULT_EXPIRY_MS;
            if (parseString.optMap().containsKey(EXPIRY_KEY)) {
                newBuilder.setExpiry(Long.valueOf(DateUtils.parseIso8601(parseString.optMap().opt(EXPIRY_KEY).optString(), currentTimeMillis)));
            } else {
                newBuilder.setExpiry(Long.valueOf(currentTimeMillis));
            }
            if (BannerDisplayContent.PLACEMENT_TOP.equalsIgnoreCase(optMap.opt(POSITION_KEY).getString())) {
                newBuilder.setPlacement(BannerDisplayContent.PLACEMENT_TOP);
            } else {
                newBuilder.setPlacement(BannerDisplayContent.PLACEMENT_BOTTOM);
            }
            Map<String, JsonValue> map = optMap2.opt(ON_CLICK_KEY).optMap().getMap();
            if (!UAStringUtil.isEmpty(pushMessage.getRichPushMessageId())) {
                map.put("^mc", JsonValue.wrap(pushMessage.getRichPushMessageId()));
            }
            newBuilder.setClickActionValues(map);
            newBuilder.setButtonGroupId(optMap2.opt(BUTTON_GROUP_KEY).getString());
            JsonMap optMap3 = optMap2.opt(BUTTON_ACTIONS_KEY).optMap();
            for (Map.Entry<String, JsonValue> key : optMap3.entrySet()) {
                String str = (String) key.getKey();
                newBuilder.setButtonActionValues(str, optMap3.opt(str).optMap().getMap());
            }
            newBuilder.setId(pushMessage.getSendId());
            try {
                return newBuilder.build();
            } catch (IllegalArgumentException e3) {
                throw new JsonException("Invalid legacy in-app message" + parseString, e3);
            }
        } else {
            throw new JsonException("Only banner types are supported.");
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String alert;
        /* access modifiers changed from: private */
        public final Map<String, Map<String, JsonValue>> buttonActionValues;
        /* access modifiers changed from: private */
        public String buttonGroupId;
        /* access modifiers changed from: private */
        public final Map<String, JsonValue> clickActionValues;
        /* access modifiers changed from: private */
        public Long durationMilliseconds;
        /* access modifiers changed from: private */
        public Long expiryMS;
        /* access modifiers changed from: private */
        public JsonMap extras;
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public String placement;
        /* access modifiers changed from: private */
        public Integer primaryColor;
        /* access modifiers changed from: private */
        public Integer secondaryColor;

        private Builder() {
            this.clickActionValues = new HashMap();
            this.buttonActionValues = new HashMap();
            this.placement = BannerDisplayContent.PLACEMENT_BOTTOM;
        }

        public Builder setExpiry(Long l) {
            this.expiryMS = l;
            return this;
        }

        public Builder setId(String str) {
            this.id = str;
            return this;
        }

        public Builder setExtras(JsonMap jsonMap) {
            this.extras = jsonMap;
            return this;
        }

        public Builder setClickActionValues(Map<String, JsonValue> map) {
            this.clickActionValues.clear();
            if (map != null) {
                this.clickActionValues.putAll(map);
            }
            return this;
        }

        public Builder setButtonActionValues(String str, Map<String, JsonValue> map) {
            if (map == null) {
                this.buttonActionValues.remove(str);
            } else {
                this.buttonActionValues.put(str, new HashMap(map));
            }
            return this;
        }

        public Builder setButtonGroupId(String str) {
            this.buttonGroupId = str;
            return this;
        }

        public Builder setAlert(String str) {
            this.alert = str;
            return this;
        }

        public Builder setDuration(Long l) {
            this.durationMilliseconds = l;
            return this;
        }

        public Builder setPlacement(String str) {
            this.placement = str;
            return this;
        }

        public Builder setPrimaryColor(Integer num) {
            this.primaryColor = num;
            return this;
        }

        public Builder setSecondaryColor(Integer num) {
            this.secondaryColor = num;
            return this;
        }

        public LegacyInAppMessage build() {
            Long l = this.durationMilliseconds;
            Checks.checkArgument(l == null || l.longValue() > 0, "Duration must be greater than 0");
            return new LegacyInAppMessage(this);
        }
    }
}
