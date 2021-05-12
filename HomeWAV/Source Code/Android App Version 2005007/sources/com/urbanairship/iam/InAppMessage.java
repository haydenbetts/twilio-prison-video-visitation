package com.urbanairship.iam;

import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.Logger;
import com.urbanairship.automation.ScheduleData;
import com.urbanairship.iam.banner.BannerDisplayContent;
import com.urbanairship.iam.custom.CustomDisplayContent;
import com.urbanairship.iam.fullscreen.FullScreenDisplayContent;
import com.urbanairship.iam.html.HtmlDisplayContent;
import com.urbanairship.iam.modal.ModalDisplayContent;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

public class InAppMessage implements Parcelable, ScheduleData {
    private static final String ACTIONS_KEY = "actions";
    private static final String CAMPAIGNS_KEY = "campaigns";
    public static final Parcelable.Creator<InAppMessage> CREATOR = new Parcelable.Creator<InAppMessage>() {
        public InAppMessage createFromParcel(Parcel parcel) {
            try {
                return InAppMessage.fromJson(JsonValue.parseString(parcel.readString()));
            } catch (JsonException e) {
                Logger.error("InAppMessage - Invalid parcel: %s", e);
                return null;
            }
        }

        public InAppMessage[] newArray(int i) {
            return new InAppMessage[i];
        }
    };
    public static final String DISPLAY_BEHAVIOR_DEFAULT = "default";
    public static final String DISPLAY_BEHAVIOR_IMMEDIATE = "immediate";
    private static final String DISPLAY_BEHAVIOR_KEY = "display_behavior";
    private static final String DISPLAY_CONTENT_KEY = "display";
    private static final String DISPLAY_TYPE_KEY = "display_type";
    private static final String EXTRA_KEY = "extra";
    public static final int MAX_NAME_LENGTH = 1024;
    private static final String NAME_KEY = "name";
    private static final String RENDERED_LOCALE_COUNTRY_KEY = "country";
    private static final String RENDERED_LOCALE_KEY = "rendered_locale";
    private static final String RENDERED_LOCALE_LANGUAGE_KEY = "language";
    private static final String REPORTING_ENABLED_KEY = "reporting_enabled";
    public static final String SOURCE_APP_DEFINED = "app-defined";
    private static final String SOURCE_KEY = "source";
    public static final String SOURCE_LEGACY_PUSH = "legacy-push";
    public static final String SOURCE_REMOTE_DATA = "remote-data";
    public static final String TYPE_BANNER = "banner";
    public static final String TYPE_CUSTOM = "custom";
    public static final String TYPE_FULLSCREEN = "fullscreen";
    public static final String TYPE_HTML = "html";
    public static final String TYPE_MODAL = "modal";
    /* access modifiers changed from: private */
    public final Map<String, JsonValue> actions;
    /* access modifiers changed from: private */
    public final JsonValue campaigns;
    /* access modifiers changed from: private */
    public final JsonSerializable content;
    /* access modifiers changed from: private */
    public final String displayBehavior;
    /* access modifiers changed from: private */
    public final JsonMap extras;
    /* access modifiers changed from: private */
    public final boolean isReportingEnabled;
    /* access modifiers changed from: private */
    public final String name;
    /* access modifiers changed from: private */
    public final Map<String, JsonValue> renderedLocale;
    /* access modifiers changed from: private */
    public final String source;
    /* access modifiers changed from: private */
    public final String type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Source {
    }

    public int describeContents() {
        return 0;
    }

    private InAppMessage(Builder builder) {
        this.type = builder.type;
        this.content = builder.content;
        this.name = builder.name;
        this.extras = builder.extras == null ? JsonMap.EMPTY_MAP : builder.extras;
        this.actions = builder.actions;
        this.source = builder.source;
        this.campaigns = builder.campaigns;
        this.displayBehavior = builder.displayBehavior;
        this.isReportingEnabled = builder.isReportingEnabled;
        this.renderedLocale = builder.renderedLocale;
    }

    public String getType() {
        return this.type;
    }

    public <T extends DisplayContent> T getDisplayContent() {
        T t = this.content;
        if (t == null) {
            return null;
        }
        try {
            return (DisplayContent) t;
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public String getName() {
        return this.name;
    }

    public JsonMap getExtras() {
        return this.extras;
    }

    public Map<String, JsonValue> getActions() {
        return this.actions;
    }

    public String getSource() {
        return this.source;
    }

    /* access modifiers changed from: package-private */
    public JsonValue getCampaigns() {
        return this.campaigns;
    }

    /* access modifiers changed from: package-private */
    public Map<String, JsonValue> getRenderedLocale() {
        return this.renderedLocale;
    }

    public String getDisplayBehavior() {
        return this.displayBehavior;
    }

    public boolean isReportingEnabled() {
        return this.isReportingEnabled;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt("name", this.name).putOpt("extra", this.extras).putOpt("display", this.content).putOpt(DISPLAY_TYPE_KEY, this.type).putOpt("actions", this.actions).putOpt("source", this.source).putOpt(CAMPAIGNS_KEY, this.campaigns).putOpt(DISPLAY_BEHAVIOR_KEY, this.displayBehavior).putOpt(REPORTING_ENABLED_KEY, Boolean.valueOf(this.isReportingEnabled)).putOpt(RENDERED_LOCALE_KEY, this.renderedLocale).build().toJsonValue();
    }

    public static InAppMessage fromJson(JsonValue jsonValue, String str) throws JsonException {
        String optString = jsonValue.optMap().opt(DISPLAY_TYPE_KEY).optString();
        JsonValue opt = jsonValue.optMap().opt("display");
        String string = jsonValue.optMap().opt("name").getString();
        if (string == null || string.length() <= 1024) {
            Builder access$1000 = newBuilder().setName(string).setExtras(jsonValue.optMap().opt("extra").optMap()).setDisplayContent(optString, opt);
            String string2 = jsonValue.optMap().opt("source").getString();
            if (string2 != null) {
                access$1000.setSource(string2);
            } else if (str != null) {
                access$1000.setSource(str);
            }
            if (jsonValue.optMap().containsKey("actions")) {
                JsonMap map = jsonValue.optMap().opt("actions").getMap();
                if (map != null) {
                    access$1000.setActions(map.getMap());
                } else {
                    throw new JsonException("Actions must be a JSON object: " + jsonValue.optMap().opt("actions"));
                }
            }
            if (jsonValue.optMap().containsKey(CAMPAIGNS_KEY)) {
                access$1000.setCampaigns(jsonValue.optMap().opt(CAMPAIGNS_KEY));
            }
            if (jsonValue.optMap().containsKey(DISPLAY_BEHAVIOR_KEY)) {
                String optString2 = jsonValue.optMap().opt(DISPLAY_BEHAVIOR_KEY).optString();
                optString2.hashCode();
                if (optString2.equals(DISPLAY_BEHAVIOR_IMMEDIATE)) {
                    access$1000.setDisplayBehavior(DISPLAY_BEHAVIOR_IMMEDIATE);
                } else if (!optString2.equals(DISPLAY_BEHAVIOR_DEFAULT)) {
                    throw new JsonException("Unexpected display behavior: " + jsonValue.optMap().get(DISPLAY_BEHAVIOR_IMMEDIATE));
                } else {
                    access$1000.setDisplayBehavior(DISPLAY_BEHAVIOR_DEFAULT);
                }
            }
            if (jsonValue.optMap().containsKey(REPORTING_ENABLED_KEY)) {
                access$1000.setReportingEnabled(jsonValue.optMap().opt(REPORTING_ENABLED_KEY).getBoolean(true));
            }
            if (jsonValue.optMap().containsKey(RENDERED_LOCALE_KEY)) {
                JsonMap map2 = jsonValue.optMap().opt(RENDERED_LOCALE_KEY).getMap();
                if (map2 == null) {
                    throw new JsonException("Rendered locale must be a JSON object: " + jsonValue.optMap().opt(RENDERED_LOCALE_KEY));
                } else if (map2.containsKey("language") || map2.containsKey("country")) {
                    JsonValue opt2 = map2.opt("language");
                    if (opt2.isNull() || opt2.isString()) {
                        JsonValue opt3 = map2.opt("country");
                        if (opt3.isNull() || opt3.isString()) {
                            access$1000.setRenderedLocale(map2.getMap());
                        } else {
                            throw new JsonException("Country must be a string: " + opt3);
                        }
                    } else {
                        throw new JsonException("Language must be a string: " + opt2);
                    }
                } else {
                    throw new JsonException("Rendered locale must contain one of \"language\" or \"country\" fields :" + map2);
                }
            }
            try {
                return access$1000.build();
            } catch (IllegalArgumentException e) {
                throw new JsonException("Invalid InAppMessage json.", e);
            }
        } else {
            throw new JsonException("Invalid message name. Must be less than or equal to 1024 characters.");
        }
    }

    public static InAppMessage fromJson(JsonValue jsonValue) throws JsonException {
        return fromJson(jsonValue, (String) null);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(InAppMessage inAppMessage) {
        return new Builder(inAppMessage);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(toJsonValue().toString());
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
        InAppMessage inAppMessage = (InAppMessage) obj;
        if (!this.displayBehavior.equals(inAppMessage.displayBehavior) || this.isReportingEnabled != inAppMessage.isReportingEnabled || !this.type.equals(inAppMessage.type) || !this.extras.equals(inAppMessage.extras)) {
            return false;
        }
        String str = this.name;
        if (str == null ? inAppMessage.name != null : !str.equals(inAppMessage.name)) {
            return false;
        }
        if (!this.content.equals(inAppMessage.content) || !this.actions.equals(inAppMessage.actions)) {
            return false;
        }
        JsonValue jsonValue = this.campaigns;
        if (jsonValue == null ? inAppMessage.campaigns != null : !jsonValue.equals(inAppMessage.campaigns)) {
            return false;
        }
        Map<String, JsonValue> map = this.renderedLocale;
        if (map == null ? inAppMessage.renderedLocale == null : map.equals(inAppMessage.renderedLocale)) {
            return this.source.equals(inAppMessage.source);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.type.hashCode() * 31) + this.extras.hashCode()) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (((((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.content.hashCode()) * 31) + this.actions.hashCode()) * 31;
        Map<String, JsonValue> map = this.renderedLocale;
        int hashCode3 = (hashCode2 + (map != null ? map.hashCode() : 0)) * 31;
        JsonValue jsonValue = this.campaigns;
        if (jsonValue != null) {
            i = jsonValue.hashCode();
        }
        return ((((((hashCode3 + i) * 31) + this.displayBehavior.hashCode()) * 31) + (this.isReportingEnabled ? 1 : 0)) * 31) + this.source.hashCode();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Map<String, JsonValue> actions;
        /* access modifiers changed from: private */
        public JsonValue campaigns;
        /* access modifiers changed from: private */
        public JsonSerializable content;
        /* access modifiers changed from: private */
        public String displayBehavior;
        /* access modifiers changed from: private */
        public JsonMap extras;
        /* access modifiers changed from: private */
        public boolean isReportingEnabled;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public Map<String, JsonValue> renderedLocale;
        /* access modifiers changed from: private */
        public String source;
        /* access modifiers changed from: private */
        public String type;

        private Builder() {
            this.actions = new HashMap();
            this.source = InAppMessage.SOURCE_APP_DEFINED;
            this.displayBehavior = InAppMessage.DISPLAY_BEHAVIOR_DEFAULT;
            this.isReportingEnabled = true;
        }

        public Builder(InAppMessage inAppMessage) {
            this.actions = new HashMap();
            this.source = InAppMessage.SOURCE_APP_DEFINED;
            this.displayBehavior = InAppMessage.DISPLAY_BEHAVIOR_DEFAULT;
            this.isReportingEnabled = true;
            this.type = inAppMessage.type;
            this.content = inAppMessage.content;
            this.name = inAppMessage.name;
            this.extras = inAppMessage.extras;
            this.actions = inAppMessage.actions;
            this.source = inAppMessage.source;
            this.campaigns = inAppMessage.campaigns;
            this.displayBehavior = inAppMessage.displayBehavior;
            this.isReportingEnabled = inAppMessage.isReportingEnabled;
            this.renderedLocale = inAppMessage.renderedLocale;
        }

        /* access modifiers changed from: private */
        public Builder setDisplayContent(String str, JsonValue jsonValue) throws JsonException {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1396342996:
                    if (str.equals(InAppMessage.TYPE_BANNER)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1349088399:
                    if (str.equals("custom")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3213227:
                    if (str.equals(InAppMessage.TYPE_HTML)) {
                        c = 2;
                        break;
                    }
                    break;
                case 104069805:
                    if (str.equals(InAppMessage.TYPE_MODAL)) {
                        c = 3;
                        break;
                    }
                    break;
                case 110066619:
                    if (str.equals(InAppMessage.TYPE_FULLSCREEN)) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setDisplayContent(BannerDisplayContent.fromJson(jsonValue));
                    break;
                case 1:
                    setDisplayContent(CustomDisplayContent.fromJson(jsonValue));
                    break;
                case 2:
                    setDisplayContent(HtmlDisplayContent.fromJson(jsonValue));
                    break;
                case 3:
                    setDisplayContent(ModalDisplayContent.fromJson(jsonValue));
                    break;
                case 4:
                    setDisplayContent(FullScreenDisplayContent.fromJson(jsonValue));
                    break;
            }
            return this;
        }

        public Builder setDisplayContent(ModalDisplayContent modalDisplayContent) {
            this.type = InAppMessage.TYPE_MODAL;
            this.content = modalDisplayContent;
            return this;
        }

        public Builder setDisplayContent(FullScreenDisplayContent fullScreenDisplayContent) {
            this.type = InAppMessage.TYPE_FULLSCREEN;
            this.content = fullScreenDisplayContent;
            return this;
        }

        public Builder setDisplayContent(BannerDisplayContent bannerDisplayContent) {
            this.type = InAppMessage.TYPE_BANNER;
            this.content = bannerDisplayContent;
            return this;
        }

        public Builder setDisplayContent(HtmlDisplayContent htmlDisplayContent) {
            this.type = InAppMessage.TYPE_HTML;
            this.content = htmlDisplayContent;
            return this;
        }

        public Builder setDisplayBehavior(String str) {
            this.displayBehavior = str;
            return this;
        }

        public Builder setSource(String str) {
            this.source = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setCampaigns(JsonValue jsonValue) {
            this.campaigns = jsonValue;
            return this;
        }

        public Builder setDisplayContent(CustomDisplayContent customDisplayContent) {
            this.type = "custom";
            this.content = customDisplayContent;
            return this;
        }

        public Builder setExtras(JsonMap jsonMap) {
            this.extras = jsonMap;
            return this;
        }

        public Builder setName(String str) {
            this.name = str;
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

        public Builder setReportingEnabled(boolean z) {
            this.isReportingEnabled = z;
            return this;
        }

        public Builder setRenderedLocale(Map<String, JsonValue> map) {
            this.renderedLocale = map;
            return this;
        }

        public InAppMessage build() {
            String str = this.name;
            Checks.checkArgument(str == null || str.length() <= 1024, "Name exceeds max name length: 1024");
            Checks.checkNotNull(this.type, "Missing type.");
            Checks.checkNotNull(this.content, "Missing content.");
            return new InAppMessage(this);
        }
    }
}
