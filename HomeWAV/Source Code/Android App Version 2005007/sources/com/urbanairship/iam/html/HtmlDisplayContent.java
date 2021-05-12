package com.urbanairship.iam.html;

import android.graphics.Color;
import com.urbanairship.iam.DisplayContent;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.ColorUtils;

public class HtmlDisplayContent implements DisplayContent {
    public static final String ASPECT_LOCK_KEY = "aspect_lock";
    public static final String HEIGHT_KEY = "height";
    public static final String REQUIRE_CONNECTIVITY = "require_connectivity";
    public static final String WIDTH_KEY = "width";
    /* access modifiers changed from: private */
    public final int backgroundColor;
    private final float borderRadius;
    /* access modifiers changed from: private */
    public final int dismissButtonColor;
    /* access modifiers changed from: private */
    public final int height;
    private final boolean isFullscreenDisplayAllowed;
    /* access modifiers changed from: private */
    public final boolean keepAspectRatio;
    private final boolean requireConnectivity;
    /* access modifiers changed from: private */
    public final String url;
    /* access modifiers changed from: private */
    public final int width;

    private HtmlDisplayContent(Builder builder) {
        this.url = builder.url;
        this.dismissButtonColor = builder.dismissButtonColor;
        this.backgroundColor = builder.backgroundColor;
        this.borderRadius = builder.borderRadius;
        this.isFullscreenDisplayAllowed = builder.isFullscreenDisplayAllowed;
        this.width = builder.width;
        this.height = builder.height;
        this.keepAspectRatio = builder.keepAspectRatio;
        this.requireConnectivity = builder.requireConnectivity;
    }

    public static HtmlDisplayContent fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        Builder newBuilder = newBuilder();
        if (optMap.containsKey(DisplayContent.DISMISS_BUTTON_COLOR_KEY)) {
            try {
                newBuilder.setDismissButtonColor(Color.parseColor(optMap.opt(DisplayContent.DISMISS_BUTTON_COLOR_KEY).optString()));
            } catch (IllegalArgumentException e) {
                throw new JsonException("Invalid dismiss button color: " + optMap.opt(DisplayContent.DISMISS_BUTTON_COLOR_KEY), e);
            }
        }
        if (optMap.containsKey("url")) {
            String string = optMap.opt("url").getString();
            if (string != null) {
                newBuilder.setUrl(string);
            } else {
                throw new JsonException("Invalid url: " + optMap.opt("url"));
            }
        }
        if (optMap.containsKey(DisplayContent.BACKGROUND_COLOR_KEY)) {
            try {
                newBuilder.setBackgroundColor(Color.parseColor(optMap.opt(DisplayContent.BACKGROUND_COLOR_KEY).optString()));
            } catch (IllegalArgumentException e2) {
                throw new JsonException("Invalid background color: " + optMap.opt(DisplayContent.BACKGROUND_COLOR_KEY), e2);
            }
        }
        if (optMap.containsKey(DisplayContent.BORDER_RADIUS_KEY)) {
            if (optMap.opt(DisplayContent.BORDER_RADIUS_KEY).isNumber()) {
                newBuilder.setBorderRadius(optMap.opt(DisplayContent.BORDER_RADIUS_KEY).getFloat(0.0f));
            } else {
                throw new JsonException("Border radius must be a number " + optMap.opt(DisplayContent.BORDER_RADIUS_KEY));
            }
        }
        if (optMap.containsKey(DisplayContent.ALLOW_FULLSCREEN_DISPLAY_KEY)) {
            if (optMap.opt(DisplayContent.ALLOW_FULLSCREEN_DISPLAY_KEY).isBoolean()) {
                newBuilder.setAllowFullscreenDisplay(optMap.opt(DisplayContent.ALLOW_FULLSCREEN_DISPLAY_KEY).getBoolean(false));
            } else {
                throw new JsonException("Allow fullscreen display must be a boolean " + optMap.opt(DisplayContent.ALLOW_FULLSCREEN_DISPLAY_KEY));
            }
        }
        if (optMap.containsKey(REQUIRE_CONNECTIVITY)) {
            if (optMap.opt(REQUIRE_CONNECTIVITY).isBoolean()) {
                newBuilder.setRequireConnectivity(optMap.opt(REQUIRE_CONNECTIVITY).getBoolean(true));
            } else {
                throw new JsonException("Require connectivity must be a boolean " + optMap.opt(REQUIRE_CONNECTIVITY));
            }
        }
        if (optMap.containsKey("width") && !optMap.opt("width").isNumber()) {
            throw new JsonException("Width must be a number " + optMap.opt("width"));
        } else if (optMap.containsKey("height") && !optMap.opt("height").isNumber()) {
            throw new JsonException("Height must be a number " + optMap.opt("height"));
        } else if (!optMap.containsKey(ASPECT_LOCK_KEY) || optMap.opt(ASPECT_LOCK_KEY).isBoolean()) {
            newBuilder.setSize(optMap.opt("width").getInt(0), optMap.opt("height").getInt(0), optMap.opt(ASPECT_LOCK_KEY).getBoolean(false));
            try {
                return newBuilder.build();
            } catch (IllegalArgumentException e3) {
                throw new JsonException("Invalid html message JSON: " + optMap, e3);
            }
        } else {
            throw new JsonException("Aspect lock must be a boolean " + optMap.opt(ASPECT_LOCK_KEY));
        }
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(DisplayContent.DISMISS_BUTTON_COLOR_KEY, ColorUtils.convertToString(this.dismissButtonColor)).put("url", this.url).put(DisplayContent.BACKGROUND_COLOR_KEY, ColorUtils.convertToString(this.backgroundColor)).put(DisplayContent.BORDER_RADIUS_KEY, (double) this.borderRadius).put(DisplayContent.ALLOW_FULLSCREEN_DISPLAY_KEY, this.isFullscreenDisplayAllowed).put("width", this.width).put("height", this.height).put(ASPECT_LOCK_KEY, this.keepAspectRatio).put(REQUIRE_CONNECTIVITY, this.requireConnectivity).build().toJsonValue();
    }

    public String getUrl() {
        return this.url;
    }

    public int getDismissButtonColor() {
        return this.dismissButtonColor;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public String toString() {
        return toJsonValue().toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HtmlDisplayContent htmlDisplayContent = (HtmlDisplayContent) obj;
        if (this.dismissButtonColor == htmlDisplayContent.dismissButtonColor && this.backgroundColor == htmlDisplayContent.backgroundColor && Float.compare(htmlDisplayContent.borderRadius, this.borderRadius) == 0 && this.isFullscreenDisplayAllowed == htmlDisplayContent.isFullscreenDisplayAllowed && this.width == htmlDisplayContent.width && this.height == htmlDisplayContent.height && this.keepAspectRatio == htmlDisplayContent.keepAspectRatio && this.requireConnectivity == htmlDisplayContent.requireConnectivity) {
            return this.url.equals(htmlDisplayContent.url);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((((this.url.hashCode() * 31) + this.dismissButtonColor) * 31) + this.backgroundColor) * 31;
        float f = this.borderRadius;
        return ((((((((((hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + (this.isFullscreenDisplayAllowed ? 1 : 0)) * 31) + this.width) * 31) + this.height) * 31) + (this.keepAspectRatio ? 1 : 0)) * 31) + (this.requireConnectivity ? 1 : 0);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(HtmlDisplayContent htmlDisplayContent) {
        return new Builder();
    }

    public float getBorderRadius() {
        return this.borderRadius;
    }

    public long getWidth() {
        return (long) this.width;
    }

    public long getHeight() {
        return (long) this.height;
    }

    public boolean getAspectRatioLock() {
        return this.keepAspectRatio;
    }

    public boolean getRequireConnectivity() {
        return this.requireConnectivity;
    }

    public boolean isFullscreenDisplayAllowed() {
        return this.isFullscreenDisplayAllowed;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int backgroundColor;
        /* access modifiers changed from: private */
        public float borderRadius;
        /* access modifiers changed from: private */
        public int dismissButtonColor;
        /* access modifiers changed from: private */
        public int height;
        /* access modifiers changed from: private */
        public boolean isFullscreenDisplayAllowed;
        /* access modifiers changed from: private */
        public boolean keepAspectRatio;
        /* access modifiers changed from: private */
        public boolean requireConnectivity;
        /* access modifiers changed from: private */
        public String url;
        /* access modifiers changed from: private */
        public int width;

        private Builder() {
            this.dismissButtonColor = -16777216;
            this.backgroundColor = -1;
            this.requireConnectivity = true;
        }

        private Builder(HtmlDisplayContent htmlDisplayContent) {
            this.dismissButtonColor = -16777216;
            this.backgroundColor = -1;
            this.requireConnectivity = true;
            this.url = htmlDisplayContent.url;
            this.dismissButtonColor = htmlDisplayContent.dismissButtonColor;
            this.backgroundColor = htmlDisplayContent.backgroundColor;
            this.width = htmlDisplayContent.width;
            this.height = htmlDisplayContent.height;
            this.keepAspectRatio = htmlDisplayContent.keepAspectRatio;
        }

        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }

        public Builder setDismissButtonColor(int i) {
            this.dismissButtonColor = i;
            return this;
        }

        public Builder setBackgroundColor(int i) {
            this.backgroundColor = i;
            return this;
        }

        public Builder setBorderRadius(float f) {
            this.borderRadius = f;
            return this;
        }

        public Builder setAllowFullscreenDisplay(boolean z) {
            this.isFullscreenDisplayAllowed = z;
            return this;
        }

        public Builder setSize(int i, int i2, boolean z) {
            this.width = i;
            this.height = i2;
            this.keepAspectRatio = z;
            return this;
        }

        public Builder setRequireConnectivity(boolean z) {
            this.requireConnectivity = z;
            return this;
        }

        public HtmlDisplayContent build() {
            Checks.checkArgument(this.url != null, "Missing URL");
            return new HtmlDisplayContent(this);
        }
    }
}
