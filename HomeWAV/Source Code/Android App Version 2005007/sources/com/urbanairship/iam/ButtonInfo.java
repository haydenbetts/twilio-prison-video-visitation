package com.urbanairship.iam;

import android.graphics.Color;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.ColorUtils;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ButtonInfo implements JsonSerializable {
    private static final String ACTIONS_KEY = "actions";
    private static final String BACKGROUND_COLOR_KEY = "background_color";
    public static final String BEHAVIOR_CANCEL = "cancel";
    public static final String BEHAVIOR_DISMISS = "dismiss";
    private static final String BEHAVIOR_KEY = "behavior";
    private static final String BORDER_COLOR_KEY = "border_color";
    private static final String BORDER_RADIUS_KEY = "border_radius";
    private static final String ID_KEY = "id";
    private static final String LABEL_KEY = "label";
    public static final int MAX_ID_LENGTH = 100;
    /* access modifiers changed from: private */
    public final Map<String, JsonValue> actions;
    /* access modifiers changed from: private */
    public final Integer backgroundColor;
    /* access modifiers changed from: private */
    public final String behavior;
    /* access modifiers changed from: private */
    public final Integer borderColor;
    /* access modifiers changed from: private */
    public final Float borderRadius;
    /* access modifiers changed from: private */
    public final String id;
    /* access modifiers changed from: private */
    public final TextInfo label;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Behavior {
    }

    private ButtonInfo(Builder builder) {
        this.label = builder.label;
        this.id = builder.id;
        this.behavior = builder.behavior;
        this.borderRadius = Float.valueOf(builder.borderRadius);
        this.backgroundColor = builder.backgroundColor;
        this.borderColor = builder.borderColor;
        this.actions = builder.actions;
    }

    public JsonValue toJsonValue() {
        String str;
        JsonMap.Builder putOpt = JsonMap.newBuilder().put("label", (JsonSerializable) this.label).put("id", this.id).put(BEHAVIOR_KEY, this.behavior).putOpt("border_radius", this.borderRadius);
        Integer num = this.backgroundColor;
        String str2 = null;
        if (num == null) {
            str = null;
        } else {
            str = ColorUtils.convertToString(num.intValue());
        }
        JsonMap.Builder putOpt2 = putOpt.putOpt("background_color", str);
        Integer num2 = this.borderColor;
        if (num2 != null) {
            str2 = ColorUtils.convertToString(num2.intValue());
        }
        return putOpt2.putOpt(BORDER_COLOR_KEY, str2).put("actions", (JsonSerializable) JsonValue.wrapOpt(this.actions)).build().toJsonValue();
    }

    public static ButtonInfo fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        Builder newBuilder = newBuilder();
        if (optMap.containsKey("label")) {
            newBuilder.setLabel(TextInfo.fromJson(optMap.opt("label")));
        }
        if (optMap.opt("id").isString()) {
            newBuilder.setId(optMap.opt("id").optString());
        }
        if (optMap.containsKey(BEHAVIOR_KEY)) {
            String optString = optMap.opt(BEHAVIOR_KEY).optString();
            optString.hashCode();
            if (optString.equals("cancel")) {
                newBuilder.setBehavior("cancel");
            } else if (!optString.equals(BEHAVIOR_DISMISS)) {
                throw new JsonException("Unexpected behavior: " + optMap.opt(BEHAVIOR_KEY));
            } else {
                newBuilder.setBehavior(BEHAVIOR_DISMISS);
            }
        }
        if (optMap.containsKey("border_radius")) {
            if (optMap.opt("border_radius").isNumber()) {
                newBuilder.setBorderRadius(optMap.opt("border_radius").getFloat(0.0f));
            } else {
                throw new JsonException("Border radius must be a number: " + optMap.opt("border_radius"));
            }
        }
        if (optMap.containsKey("background_color")) {
            try {
                newBuilder.setBackgroundColor(Color.parseColor(optMap.opt("background_color").optString()));
            } catch (IllegalArgumentException e) {
                throw new JsonException("Invalid background button color: " + optMap.opt("background_color"), e);
            }
        }
        if (optMap.containsKey(BORDER_COLOR_KEY)) {
            try {
                newBuilder.setBorderColor(Color.parseColor(optMap.opt(BORDER_COLOR_KEY).optString()));
            } catch (IllegalArgumentException e2) {
                throw new JsonException("Invalid border color: " + optMap.opt(BORDER_COLOR_KEY), e2);
            }
        }
        if (optMap.containsKey("actions")) {
            JsonMap map = optMap.opt("actions").getMap();
            if (map != null) {
                newBuilder.setActions(map.getMap());
            } else {
                throw new JsonException("Actions must be a JSON object: " + optMap.opt("actions"));
            }
        }
        try {
            return newBuilder.build();
        } catch (IllegalArgumentException e3) {
            throw new JsonException("Invalid button JSON: " + optMap, e3);
        }
    }

    public static List<ButtonInfo> fromJson(JsonList jsonList) throws JsonException {
        if (jsonList.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<JsonValue> it = jsonList.iterator();
        while (it.hasNext()) {
            arrayList.add(fromJson(it.next()));
        }
        return arrayList;
    }

    public String getId() {
        return this.id;
    }

    public TextInfo getLabel() {
        return this.label;
    }

    public String getBehavior() {
        return this.behavior;
    }

    public Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    public Integer getBorderColor() {
        return this.borderColor;
    }

    public Float getBorderRadius() {
        return this.borderRadius;
    }

    public Map<String, JsonValue> getActions() {
        return this.actions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ButtonInfo buttonInfo = (ButtonInfo) obj;
        TextInfo textInfo = this.label;
        if (textInfo == null ? buttonInfo.label != null : !textInfo.equals(buttonInfo.label)) {
            return false;
        }
        String str = this.id;
        if (str == null ? buttonInfo.id != null : !str.equals(buttonInfo.id)) {
            return false;
        }
        String str2 = this.behavior;
        if (str2 == null ? buttonInfo.behavior != null : !str2.equals(buttonInfo.behavior)) {
            return false;
        }
        if (!this.borderRadius.equals(buttonInfo.borderRadius)) {
            return false;
        }
        Integer num = this.backgroundColor;
        if (num == null ? buttonInfo.backgroundColor != null : !num.equals(buttonInfo.backgroundColor)) {
            return false;
        }
        Integer num2 = this.borderColor;
        if (num2 == null ? buttonInfo.borderColor != null : !num2.equals(buttonInfo.borderColor)) {
            return false;
        }
        Map<String, JsonValue> map = this.actions;
        Map<String, JsonValue> map2 = buttonInfo.actions;
        if (map != null) {
            return map.equals(map2);
        }
        if (map2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        TextInfo textInfo = this.label;
        int i = 0;
        int hashCode = (textInfo != null ? textInfo.hashCode() : 0) * 31;
        String str = this.id;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.behavior;
        int hashCode3 = (((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.borderRadius.hashCode()) * 31;
        Integer num = this.backgroundColor;
        int hashCode4 = (hashCode3 + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.borderColor;
        int hashCode5 = (hashCode4 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Map<String, JsonValue> map = this.actions;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode5 + i;
    }

    public String toString() {
        return toJsonValue().toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ButtonInfo buttonInfo) {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final Map<String, JsonValue> actions;
        /* access modifiers changed from: private */
        public Integer backgroundColor;
        /* access modifiers changed from: private */
        public String behavior;
        /* access modifiers changed from: private */
        public Integer borderColor;
        /* access modifiers changed from: private */
        public float borderRadius;
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public TextInfo label;

        private Builder() {
            this.behavior = ButtonInfo.BEHAVIOR_DISMISS;
            this.borderRadius = 0.0f;
            this.actions = new HashMap();
        }

        private Builder(ButtonInfo buttonInfo) {
            this.behavior = ButtonInfo.BEHAVIOR_DISMISS;
            this.borderRadius = 0.0f;
            HashMap hashMap = new HashMap();
            this.actions = hashMap;
            this.label = buttonInfo.label;
            this.id = buttonInfo.id;
            this.behavior = buttonInfo.behavior;
            this.borderRadius = buttonInfo.borderRadius.floatValue();
            this.backgroundColor = buttonInfo.backgroundColor;
            this.borderColor = buttonInfo.borderColor;
            hashMap.putAll(buttonInfo.actions);
        }

        public Builder setLabel(TextInfo textInfo) {
            this.label = textInfo;
            return this;
        }

        public Builder setId(String str) {
            this.id = str;
            return this;
        }

        public Builder setBehavior(String str) {
            this.behavior = str;
            return this;
        }

        public Builder setBorderRadius(float f) {
            this.borderRadius = f;
            return this;
        }

        public Builder setBorderColor(int i) {
            this.borderColor = Integer.valueOf(i);
            return this;
        }

        public Builder setBackgroundColor(int i) {
            this.backgroundColor = Integer.valueOf(i);
            return this;
        }

        public Builder setActions(Map<String, JsonValue> map) {
            this.actions.clear();
            if (map != null) {
                this.actions.putAll(map);
            }
            return this;
        }

        public Builder addAction(String str, JsonSerializable jsonSerializable) {
            this.actions.put(str, jsonSerializable.toJsonValue());
            return this;
        }

        public ButtonInfo build() {
            boolean z = true;
            Checks.checkArgument(!UAStringUtil.isEmpty(this.id), "Missing ID.");
            Checks.checkArgument(this.id.length() <= 100, "Id exceeds max ID length: 100");
            if (this.label == null) {
                z = false;
            }
            Checks.checkArgument(z, "Missing label.");
            return new ButtonInfo(this);
        }
    }
}
