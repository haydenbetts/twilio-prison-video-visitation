package com.urbanairship.iam.banner;

import android.graphics.Color;
import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.DisplayContent;
import com.urbanairship.iam.MediaInfo;
import com.urbanairship.iam.TextInfo;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.ColorUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BannerDisplayContent implements DisplayContent {
    private static final String ACTIONS_KEY = "actions";
    public static final long DEFAULT_DURATION_MS = 15000;
    public static final int MAX_BUTTONS = 2;
    public static final String PLACEMENT_BOTTOM = "bottom";
    public static final String PLACEMENT_TOP = "top";
    public static final String TEMPLATE_LEFT_MEDIA = "media_left";
    public static final String TEMPLATE_RIGHT_MEDIA = "media_right";
    /* access modifiers changed from: private */
    public final Map<String, JsonValue> actions;
    /* access modifiers changed from: private */
    public final int backgroundColor;
    /* access modifiers changed from: private */
    public final TextInfo body;
    /* access modifiers changed from: private */
    public final float borderRadius;
    /* access modifiers changed from: private */
    public final String buttonLayout;
    /* access modifiers changed from: private */
    public final List<ButtonInfo> buttons;
    /* access modifiers changed from: private */
    public final int dismissButtonColor;
    /* access modifiers changed from: private */
    public final long duration;
    /* access modifiers changed from: private */
    public final TextInfo heading;
    /* access modifiers changed from: private */
    public final MediaInfo media;
    /* access modifiers changed from: private */
    public final String placement;
    /* access modifiers changed from: private */
    public final String template;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Placement {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Template {
    }

    private BannerDisplayContent(Builder builder) {
        this.heading = builder.heading;
        this.body = builder.body;
        this.media = builder.media;
        this.buttonLayout = builder.buttonLayout;
        this.buttons = builder.buttons;
        this.placement = builder.placement;
        this.template = builder.template;
        this.duration = builder.duration;
        this.backgroundColor = builder.backgroundColor;
        this.dismissButtonColor = builder.dismissButtonColor;
        this.borderRadius = builder.borderRadius;
        this.actions = builder.actions;
    }

    public static BannerDisplayContent fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        Builder newBuilder = newBuilder();
        if (optMap.containsKey(DisplayContent.HEADING_KEY)) {
            newBuilder.setHeading(TextInfo.fromJson(optMap.opt(DisplayContent.HEADING_KEY)));
        }
        if (optMap.containsKey("body")) {
            newBuilder.setBody(TextInfo.fromJson(optMap.opt("body")));
        }
        if (optMap.containsKey("media")) {
            newBuilder.setMedia(MediaInfo.fromJson(optMap.opt("media")));
        }
        if (optMap.containsKey(DisplayContent.BUTTONS_KEY)) {
            JsonList list = optMap.opt(DisplayContent.BUTTONS_KEY).getList();
            if (list != null) {
                newBuilder.setButtons(ButtonInfo.fromJson(list));
            } else {
                throw new JsonException("Buttons must be an array of button objects.");
            }
        }
        if (optMap.containsKey(DisplayContent.BUTTON_LAYOUT_KEY)) {
            String optString = optMap.opt(DisplayContent.BUTTON_LAYOUT_KEY).optString();
            optString.hashCode();
            char c = 65535;
            switch (optString.hashCode()) {
                case -1897640665:
                    if (optString.equals(DisplayContent.BUTTON_LAYOUT_STACKED)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1154529463:
                    if (optString.equals(DisplayContent.BUTTON_LAYOUT_JOINED)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1302823715:
                    if (optString.equals(DisplayContent.BUTTON_LAYOUT_SEPARATE)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    newBuilder.setButtonLayout(DisplayContent.BUTTON_LAYOUT_STACKED);
                    break;
                case 1:
                    newBuilder.setButtonLayout(DisplayContent.BUTTON_LAYOUT_JOINED);
                    break;
                case 2:
                    newBuilder.setButtonLayout(DisplayContent.BUTTON_LAYOUT_SEPARATE);
                    break;
                default:
                    throw new JsonException("Unexpected button layout: " + optMap.opt(DisplayContent.BUTTON_LAYOUT_KEY));
            }
        }
        if (optMap.containsKey(DisplayContent.PLACEMENT_KEY)) {
            String optString2 = optMap.opt(DisplayContent.PLACEMENT_KEY).optString();
            optString2.hashCode();
            if (optString2.equals(PLACEMENT_BOTTOM)) {
                newBuilder.setPlacement(PLACEMENT_BOTTOM);
            } else if (!optString2.equals(PLACEMENT_TOP)) {
                throw new JsonException("Unexpected placement: " + optMap.opt(DisplayContent.PLACEMENT_KEY));
            } else {
                newBuilder.setPlacement(PLACEMENT_TOP);
            }
        }
        if (optMap.containsKey(DisplayContent.TEMPLATE_KEY)) {
            String optString3 = optMap.opt(DisplayContent.TEMPLATE_KEY).optString();
            optString3.hashCode();
            if (optString3.equals(TEMPLATE_RIGHT_MEDIA)) {
                newBuilder.setTemplate(TEMPLATE_RIGHT_MEDIA);
            } else if (!optString3.equals(TEMPLATE_LEFT_MEDIA)) {
                throw new JsonException("Unexpected template: " + optMap.opt(DisplayContent.TEMPLATE_KEY));
            } else {
                newBuilder.setTemplate(TEMPLATE_LEFT_MEDIA);
            }
        }
        if (optMap.containsKey("duration")) {
            long j = optMap.opt("duration").getLong(0);
            if (j != 0) {
                newBuilder.setDuration(j, TimeUnit.SECONDS);
            } else {
                throw new JsonException("Invalid duration: " + optMap.opt("duration"));
            }
        }
        if (optMap.containsKey(DisplayContent.BACKGROUND_COLOR_KEY)) {
            try {
                newBuilder.setBackgroundColor(Color.parseColor(optMap.opt(DisplayContent.BACKGROUND_COLOR_KEY).optString()));
            } catch (IllegalArgumentException e) {
                throw new JsonException("Invalid background color: " + optMap.opt(DisplayContent.BACKGROUND_COLOR_KEY), e);
            }
        }
        if (optMap.containsKey(DisplayContent.DISMISS_BUTTON_COLOR_KEY)) {
            try {
                newBuilder.setDismissButtonColor(Color.parseColor(optMap.opt(DisplayContent.DISMISS_BUTTON_COLOR_KEY).optString()));
            } catch (IllegalArgumentException e2) {
                throw new JsonException("Invalid dismiss button color: " + optMap.opt(DisplayContent.DISMISS_BUTTON_COLOR_KEY), e2);
            }
        }
        if (optMap.containsKey(DisplayContent.BORDER_RADIUS_KEY)) {
            if (optMap.opt(DisplayContent.BORDER_RADIUS_KEY).isNumber()) {
                newBuilder.setBorderRadius(optMap.opt(DisplayContent.BORDER_RADIUS_KEY).getFloat(0.0f));
            } else {
                throw new JsonException("Border radius must be a number " + optMap.opt(DisplayContent.BORDER_RADIUS_KEY));
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
            throw new JsonException("Invalid banner JSON: " + optMap, e3);
        }
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(DisplayContent.HEADING_KEY, (JsonSerializable) this.heading).put("body", (JsonSerializable) this.body).put("media", (JsonSerializable) this.media).put(DisplayContent.BUTTONS_KEY, (JsonSerializable) JsonValue.wrapOpt(this.buttons)).put(DisplayContent.BUTTON_LAYOUT_KEY, this.buttonLayout).put(DisplayContent.PLACEMENT_KEY, this.placement).put(DisplayContent.TEMPLATE_KEY, this.template).put("duration", TimeUnit.MILLISECONDS.toSeconds(this.duration)).put(DisplayContent.BACKGROUND_COLOR_KEY, ColorUtils.convertToString(this.backgroundColor)).put(DisplayContent.DISMISS_BUTTON_COLOR_KEY, ColorUtils.convertToString(this.dismissButtonColor)).put(DisplayContent.BORDER_RADIUS_KEY, (double) this.borderRadius).put("actions", (JsonSerializable) JsonValue.wrapOpt(this.actions)).build().toJsonValue();
    }

    public TextInfo getHeading() {
        return this.heading;
    }

    public TextInfo getBody() {
        return this.body;
    }

    public MediaInfo getMedia() {
        return this.media;
    }

    public List<ButtonInfo> getButtons() {
        return this.buttons;
    }

    public String getButtonLayout() {
        return this.buttonLayout;
    }

    public String getPlacement() {
        return this.placement;
    }

    public String getTemplate() {
        return this.template;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getDismissButtonColor() {
        return this.dismissButtonColor;
    }

    public float getBorderRadius() {
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
        BannerDisplayContent bannerDisplayContent = (BannerDisplayContent) obj;
        if (this.duration != bannerDisplayContent.duration || this.backgroundColor != bannerDisplayContent.backgroundColor || this.dismissButtonColor != bannerDisplayContent.dismissButtonColor || Float.compare(bannerDisplayContent.borderRadius, this.borderRadius) != 0) {
            return false;
        }
        TextInfo textInfo = this.heading;
        if (textInfo == null ? bannerDisplayContent.heading != null : !textInfo.equals(bannerDisplayContent.heading)) {
            return false;
        }
        TextInfo textInfo2 = this.body;
        if (textInfo2 == null ? bannerDisplayContent.body != null : !textInfo2.equals(bannerDisplayContent.body)) {
            return false;
        }
        MediaInfo mediaInfo = this.media;
        if (mediaInfo == null ? bannerDisplayContent.media != null : !mediaInfo.equals(bannerDisplayContent.media)) {
            return false;
        }
        List<ButtonInfo> list = this.buttons;
        if (list == null ? bannerDisplayContent.buttons != null : !list.equals(bannerDisplayContent.buttons)) {
            return false;
        }
        String str = this.buttonLayout;
        if (str == null ? bannerDisplayContent.buttonLayout != null : !str.equals(bannerDisplayContent.buttonLayout)) {
            return false;
        }
        String str2 = this.placement;
        if (str2 == null ? bannerDisplayContent.placement != null : !str2.equals(bannerDisplayContent.placement)) {
            return false;
        }
        String str3 = this.template;
        if (str3 == null ? bannerDisplayContent.template != null : !str3.equals(bannerDisplayContent.template)) {
            return false;
        }
        Map<String, JsonValue> map = this.actions;
        Map<String, JsonValue> map2 = bannerDisplayContent.actions;
        if (map != null) {
            return map.equals(map2);
        }
        if (map2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        TextInfo textInfo = this.heading;
        int i = 0;
        int hashCode = (textInfo != null ? textInfo.hashCode() : 0) * 31;
        TextInfo textInfo2 = this.body;
        int hashCode2 = (hashCode + (textInfo2 != null ? textInfo2.hashCode() : 0)) * 31;
        MediaInfo mediaInfo = this.media;
        int hashCode3 = (hashCode2 + (mediaInfo != null ? mediaInfo.hashCode() : 0)) * 31;
        List<ButtonInfo> list = this.buttons;
        int hashCode4 = (hashCode3 + (list != null ? list.hashCode() : 0)) * 31;
        String str = this.buttonLayout;
        int hashCode5 = (hashCode4 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.placement;
        int hashCode6 = (hashCode5 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.template;
        int hashCode7 = str3 != null ? str3.hashCode() : 0;
        long j = this.duration;
        int i2 = (((((((hashCode6 + hashCode7) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.backgroundColor) * 31) + this.dismissButtonColor) * 31;
        float f = this.borderRadius;
        int floatToIntBits = (i2 + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31;
        Map<String, JsonValue> map = this.actions;
        if (map != null) {
            i = map.hashCode();
        }
        return floatToIntBits + i;
    }

    public String toString() {
        return toJsonValue().toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BannerDisplayContent bannerDisplayContent) {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final Map<String, JsonValue> actions;
        /* access modifiers changed from: private */
        public int backgroundColor;
        /* access modifiers changed from: private */
        public TextInfo body;
        /* access modifiers changed from: private */
        public float borderRadius;
        /* access modifiers changed from: private */
        public String buttonLayout;
        /* access modifiers changed from: private */
        public List<ButtonInfo> buttons;
        /* access modifiers changed from: private */
        public int dismissButtonColor;
        /* access modifiers changed from: private */
        public long duration;
        /* access modifiers changed from: private */
        public TextInfo heading;
        /* access modifiers changed from: private */
        public MediaInfo media;
        /* access modifiers changed from: private */
        public String placement;
        /* access modifiers changed from: private */
        public String template;

        private Builder() {
            this.buttons = new ArrayList();
            this.buttonLayout = DisplayContent.BUTTON_LAYOUT_SEPARATE;
            this.placement = BannerDisplayContent.PLACEMENT_BOTTOM;
            this.template = BannerDisplayContent.TEMPLATE_LEFT_MEDIA;
            this.duration = BannerDisplayContent.DEFAULT_DURATION_MS;
            this.backgroundColor = -1;
            this.dismissButtonColor = -16777216;
            this.borderRadius = 0.0f;
            this.actions = new HashMap();
        }

        private Builder(BannerDisplayContent bannerDisplayContent) {
            this.buttons = new ArrayList();
            this.buttonLayout = DisplayContent.BUTTON_LAYOUT_SEPARATE;
            this.placement = BannerDisplayContent.PLACEMENT_BOTTOM;
            this.template = BannerDisplayContent.TEMPLATE_LEFT_MEDIA;
            this.duration = BannerDisplayContent.DEFAULT_DURATION_MS;
            this.backgroundColor = -1;
            this.dismissButtonColor = -16777216;
            this.borderRadius = 0.0f;
            HashMap hashMap = new HashMap();
            this.actions = hashMap;
            this.heading = bannerDisplayContent.heading;
            this.body = bannerDisplayContent.body;
            this.media = bannerDisplayContent.media;
            this.buttonLayout = bannerDisplayContent.buttonLayout;
            this.buttons = bannerDisplayContent.buttons;
            this.placement = bannerDisplayContent.placement;
            this.template = bannerDisplayContent.template;
            this.duration = bannerDisplayContent.duration;
            this.backgroundColor = bannerDisplayContent.backgroundColor;
            this.dismissButtonColor = bannerDisplayContent.dismissButtonColor;
            this.borderRadius = bannerDisplayContent.borderRadius;
            hashMap.putAll(bannerDisplayContent.actions);
        }

        public Builder setHeading(TextInfo textInfo) {
            this.heading = textInfo;
            return this;
        }

        public Builder setBody(TextInfo textInfo) {
            this.body = textInfo;
            return this;
        }

        public Builder addButton(ButtonInfo buttonInfo) {
            this.buttons.add(buttonInfo);
            return this;
        }

        public Builder setButtons(List<ButtonInfo> list) {
            this.buttons.clear();
            if (list != null) {
                this.buttons.addAll(list);
            }
            return this;
        }

        public Builder setMedia(MediaInfo mediaInfo) {
            this.media = mediaInfo;
            return this;
        }

        public Builder setButtonLayout(String str) {
            this.buttonLayout = str;
            return this;
        }

        public Builder setPlacement(String str) {
            this.placement = str;
            return this;
        }

        public Builder setTemplate(String str) {
            this.template = str;
            return this;
        }

        public Builder setBackgroundColor(int i) {
            this.backgroundColor = i;
            return this;
        }

        public Builder setDismissButtonColor(int i) {
            this.dismissButtonColor = i;
            return this;
        }

        public Builder setBorderRadius(float f) {
            this.borderRadius = f;
            return this;
        }

        public Builder setDuration(long j, TimeUnit timeUnit) {
            this.duration = timeUnit.toMillis(j);
            return this;
        }

        public Builder setActions(Map<String, JsonValue> map) {
            this.actions.clear();
            if (map != null) {
                this.actions.putAll(map);
            }
            return this;
        }

        public Builder addAction(String str, JsonValue jsonValue) {
            this.actions.put(str, jsonValue);
            return this;
        }

        public BannerDisplayContent build() {
            float f = this.borderRadius;
            boolean z = true;
            Checks.checkArgument(f >= 0.0f && ((double) f) <= 20.0d, "Border radius must be between 0 and 20.");
            Checks.checkArgument((this.heading == null && this.body == null) ? false : true, "Either the body or heading must be defined.");
            Checks.checkArgument(this.buttons.size() <= 2, "Banner allows a max of 2 buttons");
            MediaInfo mediaInfo = this.media;
            if (mediaInfo != null && !mediaInfo.getType().equals("image")) {
                z = false;
            }
            Checks.checkArgument(z, "Banner only supports image media");
            return new BannerDisplayContent(this);
        }
    }
}
