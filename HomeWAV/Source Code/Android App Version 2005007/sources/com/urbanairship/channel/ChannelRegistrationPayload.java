package com.urbanairship.channel;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChannelRegistrationPayload implements JsonSerializable {
    static final String ACCENGAGE_DEVICE_ID = "accengage_device_id";
    public static final String AMAZON_DEVICE_TYPE = "amazon";
    static final String ANDROID_DELIVERY_TYPE = "delivery_type";
    public static final String ANDROID_DEVICE_TYPE = "android";
    static final String ANDROID_EXTRAS_KEY = "android";
    static final String APID_KEY = "apid";
    static final String API_VERSION_KEY = "android_api_version";
    static final String APP_VERSION_KEY = "app_version";
    static final String BACKGROUND_ENABLED_KEY = "background";
    static final String CARRIER_KEY = "carrier";
    static final String CHANNEL_KEY = "channel";
    static final String COUNTRY_KEY = "locale_country";
    static final String DEVICE_MODEL_KEY = "device_model";
    static final String DEVICE_TYPE_KEY = "device_type";
    static final String IDENTITY_HINTS_KEY = "identity_hints";
    static final String LANGUAGE_KEY = "locale_language";
    static final String LOCATION_SETTINGS_KEY = "location_settings";
    static final String NAMED_USER_ID_KEY = "named_user_id";
    static final String OPT_IN_KEY = "opt_in";
    static final String PUSH_ADDRESS_KEY = "push_address";
    static final String SDK_VERSION_KEY = "sdk_version";
    static final String SET_TAGS_KEY = "set_tags";
    static final String TAGS_KEY = "tags";
    static final String TIMEZONE_KEY = "timezone";
    static final String USER_ID_KEY = "user_id";
    public final String accengageDeviceId;
    public final Integer apiVersion;
    public final String apid;
    public final String appVersion;
    public final boolean backgroundEnabled;
    public final String carrier;
    public final String country;
    public final String deliveryType;
    public final String deviceModel;
    public final String deviceType;
    public final String language;
    public final Boolean locationSettings;
    public final String namedUserId;
    public final boolean optIn;
    public final String pushAddress;
    public final String sdkVersion;
    public final boolean setTags;
    public final Set<String> tags;
    public final String timezone;
    public final String userId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DeviceType {
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String accengageDeviceId;
        /* access modifiers changed from: private */
        public Integer apiVersion;
        /* access modifiers changed from: private */
        public String apid;
        /* access modifiers changed from: private */
        public String appVersion;
        /* access modifiers changed from: private */
        public boolean backgroundEnabled;
        /* access modifiers changed from: private */
        public String carrier;
        /* access modifiers changed from: private */
        public String country;
        /* access modifiers changed from: private */
        public String deliveryType;
        /* access modifiers changed from: private */
        public String deviceModel;
        /* access modifiers changed from: private */
        public String deviceType;
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public Boolean locationSettings;
        /* access modifiers changed from: private */
        public String namedUserId;
        /* access modifiers changed from: private */
        public boolean optIn;
        /* access modifiers changed from: private */
        public String pushAddress;
        /* access modifiers changed from: private */
        public String sdkVersion;
        /* access modifiers changed from: private */
        public boolean setTags;
        /* access modifiers changed from: private */
        public Set<String> tags;
        /* access modifiers changed from: private */
        public String timezone;
        /* access modifiers changed from: private */
        public String userId;

        public Builder() {
        }

        public Builder(ChannelRegistrationPayload channelRegistrationPayload) {
            this.optIn = channelRegistrationPayload.optIn;
            this.backgroundEnabled = channelRegistrationPayload.backgroundEnabled;
            this.deviceType = channelRegistrationPayload.deviceType;
            this.pushAddress = channelRegistrationPayload.pushAddress;
            this.setTags = channelRegistrationPayload.setTags;
            this.tags = channelRegistrationPayload.tags;
            this.userId = channelRegistrationPayload.userId;
            this.apid = channelRegistrationPayload.apid;
            this.timezone = channelRegistrationPayload.timezone;
            this.language = channelRegistrationPayload.language;
            this.country = channelRegistrationPayload.country;
            this.locationSettings = channelRegistrationPayload.locationSettings;
            this.appVersion = channelRegistrationPayload.appVersion;
            this.sdkVersion = channelRegistrationPayload.sdkVersion;
            this.deviceModel = channelRegistrationPayload.deviceModel;
            this.apiVersion = channelRegistrationPayload.apiVersion;
            this.carrier = channelRegistrationPayload.carrier;
            this.accengageDeviceId = channelRegistrationPayload.accengageDeviceId;
            this.deliveryType = channelRegistrationPayload.deliveryType;
            this.namedUserId = channelRegistrationPayload.namedUserId;
        }

        public Builder setOptIn(boolean z) {
            this.optIn = z;
            return this;
        }

        public Builder setBackgroundEnabled(boolean z) {
            this.backgroundEnabled = z;
            return this;
        }

        public Builder setDeviceType(String str) {
            this.deviceType = str;
            return this;
        }

        public Builder setNamedUserId(String str) {
            this.namedUserId = str;
            return this;
        }

        public Builder setTimezone(String str) {
            this.timezone = str;
            return this;
        }

        public Builder setLanguage(String str) {
            this.language = str;
            return this;
        }

        public Builder setCountry(String str) {
            this.country = str;
            return this;
        }

        public Builder setPushAddress(String str) {
            this.pushAddress = str;
            return this;
        }

        public Builder setTags(boolean z, Set<String> set) {
            this.setTags = z;
            this.tags = set;
            return this;
        }

        public Builder setUserId(String str) {
            if (UAStringUtil.isEmpty(str)) {
                str = null;
            }
            this.userId = str;
            return this;
        }

        public Builder setApid(String str) {
            this.apid = str;
            return this;
        }

        public Builder setLocationSettings(Boolean bool) {
            this.locationSettings = bool;
            return this;
        }

        public Builder setAppVersion(String str) {
            this.appVersion = str;
            return this;
        }

        public Builder setSdkVersion(String str) {
            this.sdkVersion = str;
            return this;
        }

        public Builder setDeviceModel(String str) {
            this.deviceModel = str;
            return this;
        }

        public Builder setApiVersion(Integer num) {
            this.apiVersion = num;
            return this;
        }

        public Builder setCarrier(String str) {
            this.carrier = str;
            return this;
        }

        public Builder setAccengageDeviceId(String str) {
            this.accengageDeviceId = str;
            return this;
        }

        public Builder setDeliveryType(String str) {
            this.deliveryType = str;
            return this;
        }

        public ChannelRegistrationPayload build() {
            return new ChannelRegistrationPayload(this);
        }
    }

    private ChannelRegistrationPayload(Builder builder) {
        this.optIn = builder.optIn;
        this.backgroundEnabled = builder.backgroundEnabled;
        this.deviceType = builder.deviceType;
        this.pushAddress = builder.pushAddress;
        this.setTags = builder.setTags;
        this.tags = builder.setTags ? builder.tags : null;
        this.userId = builder.userId;
        this.apid = builder.apid;
        this.timezone = builder.timezone;
        this.language = builder.language;
        this.country = builder.country;
        this.locationSettings = builder.locationSettings;
        this.appVersion = builder.appVersion;
        this.sdkVersion = builder.sdkVersion;
        this.deviceModel = builder.deviceModel;
        this.apiVersion = builder.apiVersion;
        this.carrier = builder.carrier;
        this.accengageDeviceId = builder.accengageDeviceId;
        this.deliveryType = builder.deliveryType;
        this.namedUserId = builder.namedUserId;
    }

    public ChannelRegistrationPayload minimizedPayload(ChannelRegistrationPayload channelRegistrationPayload) {
        Set<String> set;
        if (channelRegistrationPayload == null) {
            return this;
        }
        Builder builder = new Builder(this);
        builder.setApid((String) null);
        builder.setUserId((String) null);
        builder.setAccengageDeviceId((String) null);
        if (channelRegistrationPayload.setTags && this.setTags && (set = channelRegistrationPayload.tags) != null && set.equals(this.tags)) {
            builder.setTags(false, (Set<String>) null);
        }
        String str = this.namedUserId;
        if (str == null || UAStringUtil.equals(channelRegistrationPayload.namedUserId, str)) {
            if (UAStringUtil.equals(channelRegistrationPayload.country, this.country)) {
                builder.setCountry((String) null);
            }
            if (UAStringUtil.equals(channelRegistrationPayload.language, this.language)) {
                builder.setLanguage((String) null);
            }
            if (UAStringUtil.equals(channelRegistrationPayload.timezone, this.timezone)) {
                builder.setTimezone((String) null);
            }
            Boolean bool = channelRegistrationPayload.locationSettings;
            if (bool != null && bool.equals(this.locationSettings)) {
                builder.setLocationSettings((Boolean) null);
            }
            if (UAStringUtil.equals(channelRegistrationPayload.appVersion, this.appVersion)) {
                builder.setAppVersion((String) null);
            }
            if (UAStringUtil.equals(channelRegistrationPayload.sdkVersion, this.sdkVersion)) {
                builder.setSdkVersion((String) null);
            }
            if (UAStringUtil.equals(channelRegistrationPayload.deviceModel, this.deviceModel)) {
                builder.setDeviceModel((String) null);
            }
            if (UAStringUtil.equals(channelRegistrationPayload.carrier, this.carrier)) {
                builder.setCarrier((String) null);
            }
            Integer num = channelRegistrationPayload.apiVersion;
            if (num != null && num.equals(this.apiVersion)) {
                builder.setApiVersion((Integer) null);
            }
        }
        return builder.build();
    }

    public JsonValue toJsonValue() {
        Set<String> set;
        JsonMap.Builder put = JsonMap.newBuilder().put(DEVICE_TYPE_KEY, this.deviceType).put(SET_TAGS_KEY, this.setTags).put(OPT_IN_KEY, this.optIn).put(PUSH_ADDRESS_KEY, this.pushAddress).put(BACKGROUND_ENABLED_KEY, this.backgroundEnabled).put(TIMEZONE_KEY, this.timezone).put(LANGUAGE_KEY, this.language).put(COUNTRY_KEY, this.country).put(APP_VERSION_KEY, this.appVersion).put("sdk_version", this.sdkVersion).put(DEVICE_MODEL_KEY, this.deviceModel).put(CARRIER_KEY, this.carrier).put(NAMED_USER_ID_KEY, this.namedUserId);
        if ("android".equals(this.deviceType) && this.deliveryType != null) {
            put.put("android", (JsonSerializable) JsonMap.newBuilder().put(ANDROID_DELIVERY_TYPE, this.deliveryType).build());
        }
        Boolean bool = this.locationSettings;
        if (bool != null) {
            put.put(LOCATION_SETTINGS_KEY, bool.booleanValue());
        }
        Integer num = this.apiVersion;
        if (num != null) {
            put.put(API_VERSION_KEY, num.intValue());
        }
        if (this.setTags && (set = this.tags) != null) {
            put.put("tags", (JsonSerializable) JsonValue.wrapOpt(set).getList());
        }
        JsonMap.Builder put2 = JsonMap.newBuilder().put(USER_ID_KEY, this.userId).put(APID_KEY, this.apid).put(ACCENGAGE_DEVICE_ID, this.accengageDeviceId);
        JsonMap.Builder put3 = JsonMap.newBuilder().put("channel", (JsonSerializable) put.build());
        JsonMap build = put2.build();
        if (!build.isEmpty()) {
            put3.put(IDENTITY_HINTS_KEY, (JsonSerializable) build);
        }
        return put3.build().toJsonValue();
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
        ChannelRegistrationPayload channelRegistrationPayload = (ChannelRegistrationPayload) obj;
        if (this.optIn != channelRegistrationPayload.optIn || this.backgroundEnabled != channelRegistrationPayload.backgroundEnabled || this.setTags != channelRegistrationPayload.setTags) {
            return false;
        }
        String str = this.deviceType;
        if (str == null ? channelRegistrationPayload.deviceType != null : !str.equals(channelRegistrationPayload.deviceType)) {
            return false;
        }
        String str2 = this.pushAddress;
        if (str2 == null ? channelRegistrationPayload.pushAddress != null : !str2.equals(channelRegistrationPayload.pushAddress)) {
            return false;
        }
        Set<String> set = this.tags;
        if (set == null ? channelRegistrationPayload.tags != null : !set.equals(channelRegistrationPayload.tags)) {
            return false;
        }
        String str3 = this.userId;
        if (str3 == null ? channelRegistrationPayload.userId != null : !str3.equals(channelRegistrationPayload.userId)) {
            return false;
        }
        String str4 = this.apid;
        if (str4 == null ? channelRegistrationPayload.apid != null : !str4.equals(channelRegistrationPayload.apid)) {
            return false;
        }
        String str5 = this.timezone;
        if (str5 == null ? channelRegistrationPayload.timezone != null : !str5.equals(channelRegistrationPayload.timezone)) {
            return false;
        }
        String str6 = this.language;
        if (str6 == null ? channelRegistrationPayload.language != null : !str6.equals(channelRegistrationPayload.language)) {
            return false;
        }
        String str7 = this.country;
        if (str7 == null ? channelRegistrationPayload.country != null : !str7.equals(channelRegistrationPayload.country)) {
            return false;
        }
        Boolean bool = this.locationSettings;
        if (bool == null ? channelRegistrationPayload.locationSettings != null : !bool.equals(channelRegistrationPayload.locationSettings)) {
            return false;
        }
        String str8 = this.appVersion;
        if (str8 == null ? channelRegistrationPayload.appVersion != null : !str8.equals(channelRegistrationPayload.appVersion)) {
            return false;
        }
        String str9 = this.sdkVersion;
        if (str9 == null ? channelRegistrationPayload.sdkVersion != null : !str9.equals(channelRegistrationPayload.sdkVersion)) {
            return false;
        }
        String str10 = this.deviceModel;
        if (str10 == null ? channelRegistrationPayload.deviceModel != null : !str10.equals(channelRegistrationPayload.deviceModel)) {
            return false;
        }
        Integer num = this.apiVersion;
        if (num == null ? channelRegistrationPayload.apiVersion != null : !num.equals(channelRegistrationPayload.apiVersion)) {
            return false;
        }
        String str11 = this.carrier;
        if (str11 == null ? channelRegistrationPayload.carrier != null : !str11.equals(channelRegistrationPayload.carrier)) {
            return false;
        }
        String str12 = this.accengageDeviceId;
        if (str12 == null ? channelRegistrationPayload.accengageDeviceId != null : !str12.equals(channelRegistrationPayload.accengageDeviceId)) {
            return false;
        }
        String str13 = this.namedUserId;
        if (str13 == null ? channelRegistrationPayload.namedUserId != null : !str13.equals(channelRegistrationPayload.namedUserId)) {
            return false;
        }
        String str14 = this.deliveryType;
        String str15 = channelRegistrationPayload.deliveryType;
        if (str14 != null) {
            return str14.equals(str15);
        }
        if (str15 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = (((this.optIn ? 1 : 0) * true) + (this.backgroundEnabled ? 1 : 0)) * 31;
        String str = this.deviceType;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.pushAddress;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + (this.setTags ? 1 : 0)) * 31;
        Set<String> set = this.tags;
        int hashCode3 = (hashCode2 + (set != null ? set.hashCode() : 0)) * 31;
        String str3 = this.userId;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.apid;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.timezone;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.language;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.country;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        Boolean bool = this.locationSettings;
        int hashCode9 = (hashCode8 + (bool != null ? bool.hashCode() : 0)) * 31;
        String str8 = this.appVersion;
        int hashCode10 = (hashCode9 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.sdkVersion;
        int hashCode11 = (hashCode10 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.deviceModel;
        int hashCode12 = (hashCode11 + (str10 != null ? str10.hashCode() : 0)) * 31;
        Integer num = this.apiVersion;
        int hashCode13 = (hashCode12 + (num != null ? num.hashCode() : 0)) * 31;
        String str11 = this.carrier;
        int hashCode14 = (hashCode13 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.accengageDeviceId;
        int hashCode15 = (hashCode14 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.namedUserId;
        int hashCode16 = (hashCode15 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.deliveryType;
        if (str14 != null) {
            i2 = str14.hashCode();
        }
        return hashCode16 + i2;
    }

    static ChannelRegistrationPayload fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        JsonMap optMap2 = optMap.opt("channel").optMap();
        JsonMap optMap3 = optMap.opt(IDENTITY_HINTS_KEY).optMap();
        if (!optMap2.isEmpty() || !optMap3.isEmpty()) {
            HashSet hashSet = new HashSet();
            Iterator<JsonValue> it = optMap2.opt("tags").optList().iterator();
            while (it.hasNext()) {
                JsonValue next = it.next();
                if (next.isString()) {
                    hashSet.add(next.getString());
                } else {
                    throw new JsonException("Invalid tag: " + next);
                }
            }
            Integer num = null;
            Boolean valueOf = optMap2.containsKey(LOCATION_SETTINGS_KEY) ? Boolean.valueOf(optMap2.opt(LOCATION_SETTINGS_KEY).getBoolean(false)) : null;
            if (optMap2.containsKey(API_VERSION_KEY)) {
                num = Integer.valueOf(optMap2.opt(API_VERSION_KEY).getInt(-1));
            }
            return new Builder().setOptIn(optMap2.opt(OPT_IN_KEY).getBoolean(false)).setBackgroundEnabled(optMap2.opt(BACKGROUND_ENABLED_KEY).getBoolean(false)).setDeviceType(optMap2.opt(DEVICE_TYPE_KEY).getString()).setPushAddress(optMap2.opt(PUSH_ADDRESS_KEY).getString()).setLanguage(optMap2.opt(LANGUAGE_KEY).getString()).setCountry(optMap2.opt(COUNTRY_KEY).getString()).setTimezone(optMap2.opt(TIMEZONE_KEY).getString()).setTags(optMap2.opt(SET_TAGS_KEY).getBoolean(false), hashSet).setUserId(optMap3.opt(USER_ID_KEY).getString()).setApid(optMap3.opt(APID_KEY).getString()).setAccengageDeviceId(optMap3.opt(ACCENGAGE_DEVICE_ID).getString()).setLocationSettings(valueOf).setAppVersion(optMap2.opt(APP_VERSION_KEY).getString()).setSdkVersion(optMap2.opt("sdk_version").getString()).setDeviceModel(optMap2.opt(DEVICE_MODEL_KEY).getString()).setApiVersion(num).setCarrier(optMap2.opt(CARRIER_KEY).getString()).setDeliveryType(optMap2.opt("android").optMap().opt(ANDROID_DELIVERY_TYPE).getString()).setNamedUserId(optMap2.opt(NAMED_USER_ID_KEY).getString()).build();
        }
        throw new JsonException("Invalid channel payload: " + jsonValue);
    }
}
