package com.urbanairship.iam.modal;

import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.DisplayContent;
import com.urbanairship.iam.MediaInfo;
import com.urbanairship.iam.TextInfo;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.ColorUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class ModalDisplayContent implements DisplayContent {
    public static final int MAX_BUTTONS = 2;
    public static final String TEMPLATE_HEADER_BODY_MEDIA = "header_body_media";
    public static final String TEMPLATE_HEADER_MEDIA_BODY = "header_media_body";
    public static final String TEMPLATE_MEDIA_HEADER_BODY = "media_header_body";
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
    public final ButtonInfo footer;
    /* access modifiers changed from: private */
    public final TextInfo heading;
    /* access modifiers changed from: private */
    public final boolean isFullscreenDisplayAllowed;
    /* access modifiers changed from: private */
    public final MediaInfo media;
    /* access modifiers changed from: private */
    public final String template;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Template {
    }

    private ModalDisplayContent(Builder builder) {
        this.heading = builder.heading;
        this.body = builder.body;
        this.media = builder.media;
        this.buttonLayout = builder.buttonLayout;
        this.buttons = builder.buttons;
        this.template = builder.template;
        this.backgroundColor = builder.backgroundColor;
        this.dismissButtonColor = builder.dismissButtonColor;
        this.footer = builder.footer;
        this.borderRadius = builder.borderRadius;
        this.isFullscreenDisplayAllowed = builder.isFullscreenDisplayAllowed;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0105, code lost:
        if (r2.equals("header_media_body") == false) goto L_0x00ff;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.urbanairship.iam.modal.ModalDisplayContent fromJson(com.urbanairship.json.JsonValue r11) throws com.urbanairship.json.JsonException {
        /*
            com.urbanairship.json.JsonMap r11 = r11.optMap()
            com.urbanairship.iam.modal.ModalDisplayContent$Builder r0 = newBuilder()
            java.lang.String r1 = "heading"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x001b
            com.urbanairship.json.JsonValue r1 = r11.opt(r1)
            com.urbanairship.iam.TextInfo r1 = com.urbanairship.iam.TextInfo.fromJson(r1)
            r0.setHeading(r1)
        L_0x001b:
            java.lang.String r1 = "body"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x002e
            com.urbanairship.json.JsonValue r1 = r11.opt(r1)
            com.urbanairship.iam.TextInfo r1 = com.urbanairship.iam.TextInfo.fromJson(r1)
            r0.setBody(r1)
        L_0x002e:
            java.lang.String r1 = "media"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x0041
            com.urbanairship.json.JsonValue r1 = r11.opt(r1)
            com.urbanairship.iam.MediaInfo r1 = com.urbanairship.iam.MediaInfo.fromJson(r1)
            r0.setMedia(r1)
        L_0x0041:
            java.lang.String r1 = "buttons"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x0063
            com.urbanairship.json.JsonValue r1 = r11.opt(r1)
            com.urbanairship.json.JsonList r1 = r1.getList()
            if (r1 == 0) goto L_0x005b
            java.util.List r1 = com.urbanairship.iam.ButtonInfo.fromJson((com.urbanairship.json.JsonList) r1)
            r0.setButtons(r1)
            goto L_0x0063
        L_0x005b:
            com.urbanairship.json.JsonException r11 = new com.urbanairship.json.JsonException
            java.lang.String r0 = "Buttons must be an array of button objects."
            r11.<init>(r0)
            throw r11
        L_0x0063:
            java.lang.String r1 = "button_layout"
            boolean r2 = r11.containsKey(r1)
            r3 = 2
            r4 = 1
            r5 = -1
            r6 = 0
            if (r2 == 0) goto L_0x00cc
            com.urbanairship.json.JsonValue r2 = r11.opt(r1)
            java.lang.String r2 = r2.optString()
            r2.hashCode()
            int r7 = r2.hashCode()
            java.lang.String r8 = "separate"
            java.lang.String r9 = "joined"
            java.lang.String r10 = "stacked"
            switch(r7) {
                case -1897640665: goto L_0x009b;
                case -1154529463: goto L_0x0092;
                case 1302823715: goto L_0x0089;
                default: goto L_0x0087;
            }
        L_0x0087:
            r2 = -1
            goto L_0x00a3
        L_0x0089:
            boolean r2 = r2.equals(r8)
            if (r2 != 0) goto L_0x0090
            goto L_0x0087
        L_0x0090:
            r2 = 2
            goto L_0x00a3
        L_0x0092:
            boolean r2 = r2.equals(r9)
            if (r2 != 0) goto L_0x0099
            goto L_0x0087
        L_0x0099:
            r2 = 1
            goto L_0x00a3
        L_0x009b:
            boolean r2 = r2.equals(r10)
            if (r2 != 0) goto L_0x00a2
            goto L_0x0087
        L_0x00a2:
            r2 = 0
        L_0x00a3:
            switch(r2) {
                case 0: goto L_0x00c9;
                case 1: goto L_0x00c5;
                case 2: goto L_0x00c1;
                default: goto L_0x00a6;
            }
        L_0x00a6:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected button layout: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r11 = r11.opt(r1)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r0.<init>(r11)
            throw r0
        L_0x00c1:
            r0.setButtonLayout(r8)
            goto L_0x00cc
        L_0x00c5:
            r0.setButtonLayout(r9)
            goto L_0x00cc
        L_0x00c9:
            r0.setButtonLayout(r10)
        L_0x00cc:
            java.lang.String r1 = "footer"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x00df
            com.urbanairship.json.JsonValue r1 = r11.opt(r1)
            com.urbanairship.iam.ButtonInfo r1 = com.urbanairship.iam.ButtonInfo.fromJson((com.urbanairship.json.JsonValue) r1)
            r0.setFooter(r1)
        L_0x00df:
            java.lang.String r1 = "template"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x0142
            com.urbanairship.json.JsonValue r2 = r11.opt(r1)
            java.lang.String r2 = r2.optString()
            r2.hashCode()
            int r7 = r2.hashCode()
            java.lang.String r8 = "header_media_body"
            java.lang.String r9 = "header_body_media"
            java.lang.String r10 = "media_header_body"
            switch(r7) {
                case -1783908295: goto L_0x0111;
                case -589491207: goto L_0x0108;
                case 1167596047: goto L_0x0101;
                default: goto L_0x00ff;
            }
        L_0x00ff:
            r3 = -1
            goto L_0x0119
        L_0x0101:
            boolean r2 = r2.equals(r8)
            if (r2 != 0) goto L_0x0119
            goto L_0x00ff
        L_0x0108:
            boolean r2 = r2.equals(r9)
            if (r2 != 0) goto L_0x010f
            goto L_0x00ff
        L_0x010f:
            r3 = 1
            goto L_0x0119
        L_0x0111:
            boolean r2 = r2.equals(r10)
            if (r2 != 0) goto L_0x0118
            goto L_0x00ff
        L_0x0118:
            r3 = 0
        L_0x0119:
            switch(r3) {
                case 0: goto L_0x013f;
                case 1: goto L_0x013b;
                case 2: goto L_0x0137;
                default: goto L_0x011c;
            }
        L_0x011c:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected template: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r11 = r11.opt(r1)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r0.<init>(r11)
            throw r0
        L_0x0137:
            r0.setTemplate(r8)
            goto L_0x0142
        L_0x013b:
            r0.setTemplate(r9)
            goto L_0x0142
        L_0x013f:
            r0.setTemplate(r10)
        L_0x0142:
            java.lang.String r1 = "background_color"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x0176
            com.urbanairship.json.JsonValue r2 = r11.opt(r1)     // Catch:{ IllegalArgumentException -> 0x015a }
            java.lang.String r2 = r2.optString()     // Catch:{ IllegalArgumentException -> 0x015a }
            int r2 = android.graphics.Color.parseColor(r2)     // Catch:{ IllegalArgumentException -> 0x015a }
            r0.setBackgroundColor(r2)     // Catch:{ IllegalArgumentException -> 0x015a }
            goto L_0x0176
        L_0x015a:
            r0 = move-exception
            com.urbanairship.json.JsonException r2 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid background color: "
            r3.append(r4)
            com.urbanairship.json.JsonValue r11 = r11.opt(r1)
            r3.append(r11)
            java.lang.String r11 = r3.toString()
            r2.<init>(r11, r0)
            throw r2
        L_0x0176:
            java.lang.String r1 = "dismiss_button_color"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x01aa
            com.urbanairship.json.JsonValue r2 = r11.opt(r1)     // Catch:{ IllegalArgumentException -> 0x018e }
            java.lang.String r2 = r2.optString()     // Catch:{ IllegalArgumentException -> 0x018e }
            int r2 = android.graphics.Color.parseColor(r2)     // Catch:{ IllegalArgumentException -> 0x018e }
            r0.setDismissButtonColor(r2)     // Catch:{ IllegalArgumentException -> 0x018e }
            goto L_0x01aa
        L_0x018e:
            r0 = move-exception
            com.urbanairship.json.JsonException r2 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid dismiss button color: "
            r3.append(r4)
            com.urbanairship.json.JsonValue r11 = r11.opt(r1)
            r3.append(r11)
            java.lang.String r11 = r3.toString()
            r2.<init>(r11, r0)
            throw r2
        L_0x01aa:
            java.lang.String r1 = "border_radius"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x01e4
            com.urbanairship.json.JsonValue r2 = r11.opt(r1)
            boolean r2 = r2.isNumber()
            if (r2 == 0) goto L_0x01c9
            com.urbanairship.json.JsonValue r1 = r11.opt(r1)
            r2 = 0
            float r1 = r1.getFloat(r2)
            r0.setBorderRadius(r1)
            goto L_0x01e4
        L_0x01c9:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Border radius must be a number "
            r2.append(r3)
            com.urbanairship.json.JsonValue r11 = r11.opt(r1)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r0.<init>(r11)
            throw r0
        L_0x01e4:
            java.lang.String r1 = "allow_fullscreen_display"
            boolean r2 = r11.containsKey(r1)
            if (r2 == 0) goto L_0x021d
            com.urbanairship.json.JsonValue r2 = r11.opt(r1)
            boolean r2 = r2.isBoolean()
            if (r2 == 0) goto L_0x0202
            com.urbanairship.json.JsonValue r1 = r11.opt(r1)
            boolean r1 = r1.getBoolean(r6)
            r0.setAllowFullscreenDisplay(r1)
            goto L_0x021d
        L_0x0202:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Allow fullscreen display must be a boolean "
            r2.append(r3)
            com.urbanairship.json.JsonValue r11 = r11.opt(r1)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r0.<init>(r11)
            throw r0
        L_0x021d:
            com.urbanairship.iam.modal.ModalDisplayContent r11 = r0.build()     // Catch:{ IllegalArgumentException -> 0x0222 }
            return r11
        L_0x0222:
            r0 = move-exception
            com.urbanairship.json.JsonException r1 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid in-app message modal JSON: "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r1.<init>(r11, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.modal.ModalDisplayContent.fromJson(com.urbanairship.json.JsonValue):com.urbanairship.iam.modal.ModalDisplayContent");
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(DisplayContent.HEADING_KEY, (JsonSerializable) this.heading).put("body", (JsonSerializable) this.body).put("media", (JsonSerializable) this.media).put(DisplayContent.BUTTONS_KEY, (JsonSerializable) JsonValue.wrapOpt(this.buttons)).put(DisplayContent.BUTTON_LAYOUT_KEY, this.buttonLayout).put(DisplayContent.TEMPLATE_KEY, this.template).put(DisplayContent.BACKGROUND_COLOR_KEY, ColorUtils.convertToString(this.backgroundColor)).put(DisplayContent.DISMISS_BUTTON_COLOR_KEY, ColorUtils.convertToString(this.dismissButtonColor)).put(DisplayContent.FOOTER_KEY, (JsonSerializable) this.footer).put(DisplayContent.BORDER_RADIUS_KEY, (double) this.borderRadius).put(DisplayContent.ALLOW_FULLSCREEN_DISPLAY_KEY, this.isFullscreenDisplayAllowed).build().toJsonValue();
    }

    public boolean isFullscreenDisplayAllowed() {
        return this.isFullscreenDisplayAllowed;
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

    public String getTemplate() {
        return this.template;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getDismissButtonColor() {
        return this.dismissButtonColor;
    }

    public ButtonInfo getFooter() {
        return this.footer;
    }

    public float getBorderRadius() {
        return this.borderRadius;
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
        ModalDisplayContent modalDisplayContent = (ModalDisplayContent) obj;
        if (this.backgroundColor != modalDisplayContent.backgroundColor || this.dismissButtonColor != modalDisplayContent.dismissButtonColor || Float.compare(modalDisplayContent.borderRadius, this.borderRadius) != 0 || this.isFullscreenDisplayAllowed != modalDisplayContent.isFullscreenDisplayAllowed) {
            return false;
        }
        TextInfo textInfo = this.heading;
        if (textInfo == null ? modalDisplayContent.heading != null : !textInfo.equals(modalDisplayContent.heading)) {
            return false;
        }
        TextInfo textInfo2 = this.body;
        if (textInfo2 == null ? modalDisplayContent.body != null : !textInfo2.equals(modalDisplayContent.body)) {
            return false;
        }
        MediaInfo mediaInfo = this.media;
        if (mediaInfo == null ? modalDisplayContent.media != null : !mediaInfo.equals(modalDisplayContent.media)) {
            return false;
        }
        List<ButtonInfo> list = this.buttons;
        if (list == null ? modalDisplayContent.buttons != null : !list.equals(modalDisplayContent.buttons)) {
            return false;
        }
        if (!this.buttonLayout.equals(modalDisplayContent.buttonLayout) || !this.template.equals(modalDisplayContent.template)) {
            return false;
        }
        ButtonInfo buttonInfo = this.footer;
        ButtonInfo buttonInfo2 = modalDisplayContent.footer;
        if (buttonInfo != null) {
            return buttonInfo.equals(buttonInfo2);
        }
        if (buttonInfo2 == null) {
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
        int hashCode4 = (((((((((hashCode3 + (list != null ? list.hashCode() : 0)) * 31) + this.buttonLayout.hashCode()) * 31) + this.template.hashCode()) * 31) + this.backgroundColor) * 31) + this.dismissButtonColor) * 31;
        ButtonInfo buttonInfo = this.footer;
        int hashCode5 = (hashCode4 + (buttonInfo != null ? buttonInfo.hashCode() : 0)) * 31;
        float f = this.borderRadius;
        if (f != 0.0f) {
            i = Float.floatToIntBits(f);
        }
        return ((hashCode5 + i) * 31) + (this.isFullscreenDisplayAllowed ? 1 : 0);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ModalDisplayContent modalDisplayContent) {
        return new Builder();
    }

    public static class Builder {
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
        public ButtonInfo footer;
        /* access modifiers changed from: private */
        public TextInfo heading;
        /* access modifiers changed from: private */
        public boolean isFullscreenDisplayAllowed;
        /* access modifiers changed from: private */
        public MediaInfo media;
        /* access modifiers changed from: private */
        public String template;

        private Builder() {
            this.buttons = new ArrayList();
            this.buttonLayout = DisplayContent.BUTTON_LAYOUT_SEPARATE;
            this.template = "header_media_body";
            this.backgroundColor = -1;
            this.dismissButtonColor = -16777216;
        }

        private Builder(ModalDisplayContent modalDisplayContent) {
            this.buttons = new ArrayList();
            this.buttonLayout = DisplayContent.BUTTON_LAYOUT_SEPARATE;
            this.template = "header_media_body";
            this.backgroundColor = -1;
            this.dismissButtonColor = -16777216;
            this.heading = modalDisplayContent.heading;
            this.body = modalDisplayContent.body;
            this.media = modalDisplayContent.media;
            this.buttonLayout = modalDisplayContent.buttonLayout;
            this.buttons = modalDisplayContent.buttons;
            this.template = modalDisplayContent.template;
            this.backgroundColor = modalDisplayContent.backgroundColor;
            this.dismissButtonColor = modalDisplayContent.dismissButtonColor;
            this.footer = modalDisplayContent.footer;
            this.borderRadius = modalDisplayContent.borderRadius;
            this.isFullscreenDisplayAllowed = modalDisplayContent.isFullscreenDisplayAllowed;
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

        public Builder setFooter(ButtonInfo buttonInfo) {
            this.footer = buttonInfo;
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

        public ModalDisplayContent build() {
            float f = this.borderRadius;
            boolean z = true;
            Checks.checkArgument(f >= 0.0f && ((double) f) <= 20.0d, "Border radius must be between 0 and 20.");
            Checks.checkArgument(this.buttons.size() <= 2, "Modal allows a max of 2 buttons");
            if (this.heading == null && this.body == null) {
                z = false;
            }
            Checks.checkArgument(z, "Either the body or heading must be defined.");
            return new ModalDisplayContent(this);
        }
    }
}
