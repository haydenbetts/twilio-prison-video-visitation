package com.urbanairship.iam;

import android.content.Context;
import android.content.res.Resources;
import com.urbanairship.Logger;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.ColorUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class TextInfo implements JsonSerializable {
    public static final String ALIGNMENT_CENTER = "center";
    private static final String ALIGNMENT_KEY = "alignment";
    public static final String ALIGNMENT_LEFT = "left";
    public static final String ALIGNMENT_RIGHT = "right";
    private static final String ANDROID_DRAWABLE_RES_NAME_KEY = "android_drawable_res_name";
    private static final String COLOR_KEY = "color";
    private static final String FONT_FAMILY_KEY = "font_family";
    private static final String SIZE_KEY = "size";
    public static final String STYLE_BOLD = "bold";
    public static final String STYLE_ITALIC = "italic";
    private static final String STYLE_KEY = "style";
    public static final String STYLE_UNDERLINE = "underline";
    private static final String TEXT_KEY = "text";
    /* access modifiers changed from: private */
    public final String alignment;
    /* access modifiers changed from: private */
    public final Integer color;
    /* access modifiers changed from: private */
    public final String drawableName;
    /* access modifiers changed from: private */
    public final List<String> fontFamilies;
    /* access modifiers changed from: private */
    public final Float size;
    /* access modifiers changed from: private */
    public final List<String> styles;
    /* access modifiers changed from: private */
    public final String text;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Alignment {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    private TextInfo(Builder builder) {
        this.text = builder.text;
        this.color = builder.color;
        this.size = builder.size;
        this.alignment = builder.alignment;
        this.styles = new ArrayList(builder.styles);
        this.drawableName = builder.drawableName;
        this.fontFamilies = new ArrayList(builder.fontFamilies);
    }

    public JsonValue toJsonValue() {
        String str;
        JsonMap.Builder put = JsonMap.newBuilder().put("text", this.text);
        Integer num = this.color;
        if (num == null) {
            str = null;
        } else {
            str = ColorUtils.convertToString(num.intValue());
        }
        return put.putOpt("color", str).putOpt(SIZE_KEY, this.size).put(ALIGNMENT_KEY, this.alignment).put("style", (JsonSerializable) JsonValue.wrapOpt(this.styles)).put(FONT_FAMILY_KEY, (JsonSerializable) JsonValue.wrapOpt(this.fontFamilies)).putOpt(ANDROID_DRAWABLE_RES_NAME_KEY, this.drawableName).build().toJsonValue();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.urbanairship.iam.TextInfo fromJson(com.urbanairship.json.JsonValue r13) throws com.urbanairship.json.JsonException {
        /*
            com.urbanairship.json.JsonMap r13 = r13.optMap()
            com.urbanairship.iam.TextInfo$Builder r0 = newBuilder()
            java.lang.String r1 = "text"
            boolean r2 = r13.containsKey(r1)
            if (r2 == 0) goto L_0x001b
            com.urbanairship.json.JsonValue r1 = r13.opt(r1)
            java.lang.String r1 = r1.optString()
            r0.setText(r1)
        L_0x001b:
            java.lang.String r1 = "color"
            boolean r2 = r13.containsKey(r1)
            if (r2 == 0) goto L_0x004f
            com.urbanairship.json.JsonValue r2 = r13.opt(r1)     // Catch:{ IllegalArgumentException -> 0x0033 }
            java.lang.String r2 = r2.optString()     // Catch:{ IllegalArgumentException -> 0x0033 }
            int r2 = android.graphics.Color.parseColor(r2)     // Catch:{ IllegalArgumentException -> 0x0033 }
            r0.setColor(r2)     // Catch:{ IllegalArgumentException -> 0x0033 }
            goto L_0x004f
        L_0x0033:
            r0 = move-exception
            com.urbanairship.json.JsonException r2 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Invalid color: "
            r3.append(r4)
            com.urbanairship.json.JsonValue r13 = r13.opt(r1)
            r3.append(r13)
            java.lang.String r13 = r3.toString()
            r2.<init>(r13, r0)
            throw r2
        L_0x004f:
            java.lang.String r1 = "size"
            boolean r2 = r13.containsKey(r1)
            if (r2 == 0) goto L_0x0089
            com.urbanairship.json.JsonValue r2 = r13.opt(r1)
            boolean r2 = r2.isNumber()
            if (r2 == 0) goto L_0x006e
            com.urbanairship.json.JsonValue r1 = r13.opt(r1)
            r2 = 0
            float r1 = r1.getFloat(r2)
            r0.setFontSize(r1)
            goto L_0x0089
        L_0x006e:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Size must be a number: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r13 = r13.opt(r1)
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r0.<init>(r13)
            throw r0
        L_0x0089:
            java.lang.String r1 = "alignment"
            boolean r2 = r13.containsKey(r1)
            r3 = 2
            r4 = 1
            r5 = 0
            r6 = -1
            if (r2 == 0) goto L_0x00f2
            com.urbanairship.json.JsonValue r2 = r13.opt(r1)
            java.lang.String r2 = r2.optString()
            r2.hashCode()
            int r7 = r2.hashCode()
            java.lang.String r8 = "right"
            java.lang.String r9 = "left"
            java.lang.String r10 = "center"
            switch(r7) {
                case -1364013995: goto L_0x00c1;
                case 3317767: goto L_0x00b8;
                case 108511772: goto L_0x00af;
                default: goto L_0x00ad;
            }
        L_0x00ad:
            r2 = -1
            goto L_0x00c9
        L_0x00af:
            boolean r2 = r2.equals(r8)
            if (r2 != 0) goto L_0x00b6
            goto L_0x00ad
        L_0x00b6:
            r2 = 2
            goto L_0x00c9
        L_0x00b8:
            boolean r2 = r2.equals(r9)
            if (r2 != 0) goto L_0x00bf
            goto L_0x00ad
        L_0x00bf:
            r2 = 1
            goto L_0x00c9
        L_0x00c1:
            boolean r2 = r2.equals(r10)
            if (r2 != 0) goto L_0x00c8
            goto L_0x00ad
        L_0x00c8:
            r2 = 0
        L_0x00c9:
            switch(r2) {
                case 0: goto L_0x00ef;
                case 1: goto L_0x00eb;
                case 2: goto L_0x00e7;
                default: goto L_0x00cc;
            }
        L_0x00cc:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected alignment: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r13 = r13.opt(r1)
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r0.<init>(r13)
            throw r0
        L_0x00e7:
            r0.setAlignment(r8)
            goto L_0x00f2
        L_0x00eb:
            r0.setAlignment(r9)
            goto L_0x00f2
        L_0x00ef:
            r0.setAlignment(r10)
        L_0x00f2:
            java.lang.String r1 = "style"
            boolean r2 = r13.containsKey(r1)
            if (r2 == 0) goto L_0x0194
            com.urbanairship.json.JsonValue r2 = r13.opt(r1)
            boolean r2 = r2.isJsonList()
            if (r2 == 0) goto L_0x0179
            com.urbanairship.json.JsonValue r2 = r13.opt(r1)
            com.urbanairship.json.JsonList r2 = r2.optList()
            java.util.Iterator r2 = r2.iterator()
        L_0x0110:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0194
            java.lang.Object r7 = r2.next()
            com.urbanairship.json.JsonValue r7 = (com.urbanairship.json.JsonValue) r7
            java.lang.String r8 = r7.optString()
            java.util.Locale r9 = java.util.Locale.ROOT
            java.lang.String r8 = r8.toLowerCase(r9)
            r8.hashCode()
            int r9 = r8.hashCode()
            java.lang.String r10 = "bold"
            java.lang.String r11 = "underline"
            java.lang.String r12 = "italic"
            switch(r9) {
                case -1178781136: goto L_0x014b;
                case -1026963764: goto L_0x0142;
                case 3029637: goto L_0x0139;
                default: goto L_0x0137;
            }
        L_0x0137:
            r8 = -1
            goto L_0x0153
        L_0x0139:
            boolean r8 = r8.equals(r10)
            if (r8 != 0) goto L_0x0140
            goto L_0x0137
        L_0x0140:
            r8 = 2
            goto L_0x0153
        L_0x0142:
            boolean r8 = r8.equals(r11)
            if (r8 != 0) goto L_0x0149
            goto L_0x0137
        L_0x0149:
            r8 = 1
            goto L_0x0153
        L_0x014b:
            boolean r8 = r8.equals(r12)
            if (r8 != 0) goto L_0x0152
            goto L_0x0137
        L_0x0152:
            r8 = 0
        L_0x0153:
            switch(r8) {
                case 0: goto L_0x0175;
                case 1: goto L_0x0171;
                case 2: goto L_0x016d;
                default: goto L_0x0156;
            }
        L_0x0156:
            com.urbanairship.json.JsonException r13 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid style: "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            r13.<init>(r0)
            throw r13
        L_0x016d:
            r0.addStyle(r10)
            goto L_0x0110
        L_0x0171:
            r0.addStyle(r11)
            goto L_0x0110
        L_0x0175:
            r0.addStyle(r12)
            goto L_0x0110
        L_0x0179:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Style must be an array: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r13 = r13.opt(r1)
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r0.<init>(r13)
            throw r0
        L_0x0194:
            java.lang.String r2 = "font_family"
            boolean r3 = r13.containsKey(r2)
            if (r3 == 0) goto L_0x01fe
            com.urbanairship.json.JsonValue r3 = r13.opt(r2)
            boolean r3 = r3.isJsonList()
            if (r3 == 0) goto L_0x01e3
            com.urbanairship.json.JsonValue r1 = r13.opt(r2)
            com.urbanairship.json.JsonList r1 = r1.optList()
            java.util.Iterator r1 = r1.iterator()
        L_0x01b2:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x01fe
            java.lang.Object r2 = r1.next()
            com.urbanairship.json.JsonValue r2 = (com.urbanairship.json.JsonValue) r2
            boolean r3 = r2.isString()
            if (r3 == 0) goto L_0x01cc
            java.lang.String r2 = r2.optString()
            r0.addFontFamily(r2)
            goto L_0x01b2
        L_0x01cc:
            com.urbanairship.json.JsonException r13 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid font: "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r13.<init>(r0)
            throw r13
        L_0x01e3:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Fonts must be an array: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r13 = r13.opt(r1)
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r0.<init>(r13)
            throw r0
        L_0x01fe:
            java.lang.String r1 = "android_drawable_res_name"
            com.urbanairship.json.JsonValue r1 = r13.opt(r1)
            java.lang.String r1 = r1.getString()
            r0.setDrawableName(r1)
            com.urbanairship.iam.TextInfo r13 = r0.build()     // Catch:{ IllegalArgumentException -> 0x0210 }
            return r13
        L_0x0210:
            r0 = move-exception
            com.urbanairship.json.JsonException r1 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid text object JSON: "
            r2.append(r3)
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r1.<init>(r13, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.TextInfo.fromJson(com.urbanairship.json.JsonValue):com.urbanairship.iam.TextInfo");
    }

    public String getText() {
        return this.text;
    }

    public Float getFontSize() {
        return this.size;
    }

    public Integer getColor() {
        return this.color;
    }

    public String getAlignment() {
        return this.alignment;
    }

    public List<String> getStyles() {
        return this.styles;
    }

    public List<String> getFontFamilies() {
        return this.fontFamilies;
    }

    public int getDrawable(Context context) {
        if (this.drawableName != null) {
            try {
                return context.getResources().getIdentifier(this.drawableName, "drawable", context.getPackageName());
            } catch (Resources.NotFoundException unused) {
                Logger.debug("Drawable " + this.drawableName + " no longer exists.", new Object[0]);
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TextInfo textInfo = (TextInfo) obj;
        String str = this.drawableName;
        if (str == null ? textInfo.drawableName != null : !str.equals(textInfo.drawableName)) {
            return false;
        }
        String str2 = this.text;
        if (str2 == null ? textInfo.text != null : !str2.equals(textInfo.text)) {
            return false;
        }
        Integer num = this.color;
        if (num == null ? textInfo.color != null : !num.equals(textInfo.color)) {
            return false;
        }
        Float f = this.size;
        if (f == null ? textInfo.size != null : !f.equals(textInfo.size)) {
            return false;
        }
        String str3 = this.alignment;
        if (str3 == null ? textInfo.alignment != null : !str3.equals(textInfo.alignment)) {
            return false;
        }
        if (!this.styles.equals(textInfo.styles)) {
            return false;
        }
        return this.fontFamilies.equals(textInfo.fontFamilies);
    }

    public int hashCode() {
        String str = this.text;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.color;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Float f = this.size;
        int hashCode3 = (hashCode2 + (f != null ? f.hashCode() : 0)) * 31;
        String str2 = this.alignment;
        int hashCode4 = (((((hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.styles.hashCode()) * 31) + this.fontFamilies.hashCode()) * 31;
        String str3 = this.drawableName;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return toJsonValue().toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(TextInfo textInfo) {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String alignment;
        /* access modifiers changed from: private */
        public Integer color;
        /* access modifiers changed from: private */
        public String drawableName;
        /* access modifiers changed from: private */
        public List<String> fontFamilies;
        /* access modifiers changed from: private */
        public Float size;
        /* access modifiers changed from: private */
        public List<String> styles;
        /* access modifiers changed from: private */
        public String text;

        private Builder() {
            this.styles = new ArrayList();
            this.fontFamilies = new ArrayList();
        }

        private Builder(TextInfo textInfo) {
            this.styles = new ArrayList();
            this.fontFamilies = new ArrayList();
            this.text = textInfo.text;
            this.color = textInfo.color;
            this.size = textInfo.size;
            this.alignment = textInfo.alignment;
            this.styles = textInfo.styles;
            this.drawableName = textInfo.drawableName;
            this.fontFamilies = textInfo.fontFamilies;
        }

        public Builder setText(String str) {
            this.text = str;
            return this;
        }

        public Builder setDrawable(Context context, int i) {
            try {
                this.drawableName = context.getResources().getResourceName(i);
            } catch (Resources.NotFoundException unused) {
                Logger.debug("Drawable " + i + " no longer exists or has a new identifier.", new Object[0]);
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setDrawableName(String str) {
            this.drawableName = str;
            return this;
        }

        public Builder setColor(int i) {
            this.color = Integer.valueOf(i);
            return this;
        }

        public Builder setFontSize(float f) {
            this.size = Float.valueOf(f);
            return this;
        }

        public Builder setAlignment(String str) {
            this.alignment = str;
            return this;
        }

        public Builder addStyle(String str) {
            if (!this.styles.contains(str)) {
                this.styles.add(str);
            }
            return this;
        }

        public Builder addFontFamily(String str) {
            this.fontFamilies.add(str);
            return this;
        }

        public TextInfo build() {
            Checks.checkArgument((this.drawableName == null && this.text == null) ? false : true, "Missing text.");
            return new TextInfo(this);
        }
    }
}
