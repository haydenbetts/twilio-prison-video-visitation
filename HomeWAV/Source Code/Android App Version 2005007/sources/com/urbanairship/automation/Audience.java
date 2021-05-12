package com.urbanairship.automation;

import com.urbanairship.automation.tags.TagSelector;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;
import com.urbanairship.util.VersionUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class Audience implements JsonSerializable {
    private static final String APP_VERSION_KEY = "app_version";
    private static final String LOCALE_KEY = "locale";
    private static final String LOCATION_OPT_IN_KEY = "location_opt_in";
    public static final String MISS_BEHAVIOR_CANCEL = "cancel";
    private static final String MISS_BEHAVIOR_KEY = "miss_behavior";
    public static final String MISS_BEHAVIOR_PENALIZE = "penalize";
    public static final String MISS_BEHAVIOR_SKIP = "skip";
    private static final String NEW_USER_KEY = "new_user";
    private static final String NOTIFICATION_OPT_IN_KEY = "notification_opt_in";
    private static final String TAGS_KEY = "tags";
    private static final String TEST_DEVICES_KEY = "test_devices";
    private final List<String> languageTags;
    private final Boolean locationOptIn;
    private final String missBehavior;
    private final Boolean newUser;
    private final Boolean notificationsOptIn;
    private final TagSelector tagSelector;
    private final List<String> testDevices;
    private final JsonPredicate versionPredicate;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MissBehavior {
    }

    private Audience(Builder builder) {
        this.newUser = builder.newUser;
        this.notificationsOptIn = builder.notificationsOptIn;
        this.locationOptIn = builder.locationOptIn;
        this.languageTags = builder.languageTags;
        this.tagSelector = builder.tagSelector;
        this.versionPredicate = builder.versionPredicate;
        this.testDevices = builder.testDevices;
        this.missBehavior = builder.missBehavior;
    }

    public JsonValue toJsonValue() {
        JsonValue jsonValue = null;
        JsonMap.Builder put = JsonMap.newBuilder().putOpt(NEW_USER_KEY, this.newUser).putOpt(NOTIFICATION_OPT_IN_KEY, this.notificationsOptIn).putOpt(LOCATION_OPT_IN_KEY, this.locationOptIn).put(LOCALE_KEY, (JsonSerializable) this.languageTags.isEmpty() ? null : JsonValue.wrapOpt(this.languageTags));
        if (!this.testDevices.isEmpty()) {
            jsonValue = JsonValue.wrapOpt(this.testDevices);
        }
        return put.put(TEST_DEVICES_KEY, (JsonSerializable) jsonValue).put("tags", (JsonSerializable) this.tagSelector).put(APP_VERSION_KEY, (JsonSerializable) this.versionPredicate).put(MISS_BEHAVIOR_KEY, this.missBehavior).build().toJsonValue();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01ed, code lost:
        if (r2.equals("cancel") == false) goto L_0x01d5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.urbanairship.automation.Audience fromJson(com.urbanairship.json.JsonValue r9) throws com.urbanairship.json.JsonException {
        /*
            com.urbanairship.json.JsonMap r9 = r9.optMap()
            com.urbanairship.automation.Audience$Builder r0 = newBuilder()
            java.lang.String r1 = "new_user"
            boolean r2 = r9.containsKey(r1)
            r3 = 0
            if (r2 == 0) goto L_0x0042
            com.urbanairship.json.JsonValue r2 = r9.opt(r1)
            boolean r2 = r2.isBoolean()
            if (r2 == 0) goto L_0x0027
            com.urbanairship.json.JsonValue r1 = r9.opt(r1)
            boolean r1 = r1.getBoolean(r3)
            r0.setNewUser(r1)
            goto L_0x0042
        L_0x0027:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "new_user must be a boolean: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r9 = r9.get(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r0.<init>(r9)
            throw r0
        L_0x0042:
            java.lang.String r1 = "notification_opt_in"
            boolean r2 = r9.containsKey(r1)
            if (r2 == 0) goto L_0x007b
            com.urbanairship.json.JsonValue r2 = r9.opt(r1)
            boolean r2 = r2.isBoolean()
            if (r2 == 0) goto L_0x0060
            com.urbanairship.json.JsonValue r1 = r9.opt(r1)
            boolean r1 = r1.getBoolean(r3)
            r0.setNotificationsOptIn(r1)
            goto L_0x007b
        L_0x0060:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "notification_opt_in must be a boolean: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r9 = r9.get(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r0.<init>(r9)
            throw r0
        L_0x007b:
            java.lang.String r1 = "location_opt_in"
            boolean r2 = r9.containsKey(r1)
            if (r2 == 0) goto L_0x00b4
            com.urbanairship.json.JsonValue r2 = r9.opt(r1)
            boolean r2 = r2.isBoolean()
            if (r2 == 0) goto L_0x0099
            com.urbanairship.json.JsonValue r1 = r9.opt(r1)
            boolean r1 = r1.getBoolean(r3)
            r0.setLocationOptIn(r1)
            goto L_0x00b4
        L_0x0099:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "location_opt_in must be a boolean: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r9 = r9.get(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r0.<init>(r9)
            throw r0
        L_0x00b4:
            java.lang.String r1 = "locale"
            boolean r2 = r9.containsKey(r1)
            if (r2 == 0) goto L_0x011a
            com.urbanairship.json.JsonValue r2 = r9.opt(r1)
            boolean r2 = r2.isJsonList()
            if (r2 == 0) goto L_0x00ff
            com.urbanairship.json.JsonValue r2 = r9.opt(r1)
            com.urbanairship.json.JsonList r2 = r2.optList()
            java.util.Iterator r2 = r2.iterator()
        L_0x00d2:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x011a
            java.lang.Object r4 = r2.next()
            com.urbanairship.json.JsonValue r4 = (com.urbanairship.json.JsonValue) r4
            java.lang.String r5 = r4.getString()
            if (r5 == 0) goto L_0x00e8
            r0.addLanguageTag(r5)
            goto L_0x00d2
        L_0x00e8:
            com.urbanairship.json.JsonException r9 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid locale: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        L_0x00ff:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "locales must be an array: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r9 = r9.get(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r0.<init>(r9)
            throw r0
        L_0x011a:
            java.lang.String r2 = "app_version"
            boolean r4 = r9.containsKey(r2)
            if (r4 == 0) goto L_0x012d
            com.urbanairship.json.JsonValue r2 = r9.get(r2)
            com.urbanairship.json.JsonPredicate r2 = com.urbanairship.json.JsonPredicate.parse(r2)
            com.urbanairship.automation.Audience.Builder unused = r0.setVersionPredicate(r2)
        L_0x012d:
            java.lang.String r2 = "tags"
            boolean r4 = r9.containsKey(r2)
            if (r4 == 0) goto L_0x0140
            com.urbanairship.json.JsonValue r2 = r9.opt(r2)
            com.urbanairship.automation.tags.TagSelector r2 = com.urbanairship.automation.tags.TagSelector.fromJson(r2)
            r0.setTagSelector(r2)
        L_0x0140:
            java.lang.String r2 = "test_devices"
            boolean r4 = r9.containsKey(r2)
            if (r4 == 0) goto L_0x01aa
            com.urbanairship.json.JsonValue r4 = r9.opt(r2)
            boolean r4 = r4.isJsonList()
            if (r4 == 0) goto L_0x018f
            com.urbanairship.json.JsonValue r1 = r9.opt(r2)
            com.urbanairship.json.JsonList r1 = r1.optList()
            java.util.Iterator r1 = r1.iterator()
        L_0x015e:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x01aa
            java.lang.Object r2 = r1.next()
            com.urbanairship.json.JsonValue r2 = (com.urbanairship.json.JsonValue) r2
            boolean r4 = r2.isString()
            if (r4 == 0) goto L_0x0178
            java.lang.String r2 = r2.getString()
            r0.addTestDevice(r2)
            goto L_0x015e
        L_0x0178:
            com.urbanairship.json.JsonException r9 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid test device: "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        L_0x018f:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "test devices must be an array: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r9 = r9.get(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r0.<init>(r9)
            throw r0
        L_0x01aa:
            java.lang.String r1 = "miss_behavior"
            boolean r2 = r9.containsKey(r1)
            if (r2 == 0) goto L_0x0235
            com.urbanairship.json.JsonValue r2 = r9.opt(r1)
            boolean r2 = r2.isString()
            if (r2 == 0) goto L_0x021a
            com.urbanairship.json.JsonValue r2 = r9.opt(r1)
            java.lang.String r2 = r2.optString()
            r2.hashCode()
            r4 = -1
            int r5 = r2.hashCode()
            java.lang.String r6 = "penalize"
            java.lang.String r7 = "skip"
            java.lang.String r8 = "cancel"
            switch(r5) {
                case -1367724422: goto L_0x01e9;
                case 3532159: goto L_0x01e0;
                case 311930832: goto L_0x01d7;
                default: goto L_0x01d5;
            }
        L_0x01d5:
            r3 = -1
            goto L_0x01f0
        L_0x01d7:
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L_0x01de
            goto L_0x01d5
        L_0x01de:
            r3 = 2
            goto L_0x01f0
        L_0x01e0:
            boolean r2 = r2.equals(r7)
            if (r2 != 0) goto L_0x01e7
            goto L_0x01d5
        L_0x01e7:
            r3 = 1
            goto L_0x01f0
        L_0x01e9:
            boolean r2 = r2.equals(r8)
            if (r2 != 0) goto L_0x01f0
            goto L_0x01d5
        L_0x01f0:
            switch(r3) {
                case 0: goto L_0x0216;
                case 1: goto L_0x0212;
                case 2: goto L_0x020e;
                default: goto L_0x01f3;
            }
        L_0x01f3:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Invalid miss behavior: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r9 = r9.opt(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r0.<init>(r9)
            throw r0
        L_0x020e:
            r0.setMissBehavior(r6)
            goto L_0x0235
        L_0x0212:
            r0.setMissBehavior(r7)
            goto L_0x0235
        L_0x0216:
            r0.setMissBehavior(r8)
            goto L_0x0235
        L_0x021a:
            com.urbanairship.json.JsonException r0 = new com.urbanairship.json.JsonException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "miss_behavior must be a string: "
            r2.append(r3)
            com.urbanairship.json.JsonValue r9 = r9.get(r1)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            r0.<init>(r9)
            throw r0
        L_0x0235:
            com.urbanairship.automation.Audience r9 = r0.build()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.Audience.fromJson(com.urbanairship.json.JsonValue):com.urbanairship.automation.Audience");
    }

    public List<String> getLanguageTags() {
        return this.languageTags;
    }

    public List<String> getTestDevices() {
        return this.testDevices;
    }

    public Boolean getNotificationsOptIn() {
        return this.notificationsOptIn;
    }

    public Boolean getLocationOptIn() {
        return this.locationOptIn;
    }

    public Boolean getNewUser() {
        return this.newUser;
    }

    public TagSelector getTagSelector() {
        return this.tagSelector;
    }

    public JsonPredicate getVersionPredicate() {
        return this.versionPredicate;
    }

    public String getMissBehavior() {
        return this.missBehavior;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Audience audience = (Audience) obj;
        Boolean bool = this.newUser;
        if (bool == null ? audience.newUser != null : !bool.equals(audience.newUser)) {
            return false;
        }
        Boolean bool2 = this.notificationsOptIn;
        if (bool2 == null ? audience.notificationsOptIn != null : !bool2.equals(audience.notificationsOptIn)) {
            return false;
        }
        Boolean bool3 = this.locationOptIn;
        if (bool3 == null ? audience.locationOptIn != null : !bool3.equals(audience.locationOptIn)) {
            return false;
        }
        List<String> list = this.languageTags;
        if (list == null ? audience.languageTags != null : !list.equals(audience.languageTags)) {
            return false;
        }
        TagSelector tagSelector2 = this.tagSelector;
        if (tagSelector2 == null ? audience.tagSelector != null : !tagSelector2.equals(audience.tagSelector)) {
            return false;
        }
        String str = this.missBehavior;
        if (str == null ? audience.missBehavior != null : !str.equals(audience.missBehavior)) {
            return false;
        }
        JsonPredicate jsonPredicate = this.versionPredicate;
        JsonPredicate jsonPredicate2 = audience.versionPredicate;
        if (jsonPredicate != null) {
            return jsonPredicate.equals(jsonPredicate2);
        }
        if (jsonPredicate2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Boolean bool = this.newUser;
        int i = 0;
        int hashCode = (bool != null ? bool.hashCode() : 0) * 31;
        Boolean bool2 = this.notificationsOptIn;
        int hashCode2 = (hashCode + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        Boolean bool3 = this.locationOptIn;
        int hashCode3 = (hashCode2 + (bool3 != null ? bool3.hashCode() : 0)) * 31;
        List<String> list = this.languageTags;
        int hashCode4 = (hashCode3 + (list != null ? list.hashCode() : 0)) * 31;
        TagSelector tagSelector2 = this.tagSelector;
        int hashCode5 = (hashCode4 + (tagSelector2 != null ? tagSelector2.hashCode() : 0)) * 31;
        JsonPredicate jsonPredicate = this.versionPredicate;
        int hashCode6 = (hashCode5 + (jsonPredicate != null ? jsonPredicate.hashCode() : 0)) * 31;
        String str = this.missBehavior;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode6 + i;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final List<String> languageTags;
        /* access modifiers changed from: private */
        public Boolean locationOptIn;
        /* access modifiers changed from: private */
        public String missBehavior;
        /* access modifiers changed from: private */
        public Boolean newUser;
        /* access modifiers changed from: private */
        public Boolean notificationsOptIn;
        /* access modifiers changed from: private */
        public TagSelector tagSelector;
        /* access modifiers changed from: private */
        public final List<String> testDevices;
        /* access modifiers changed from: private */
        public JsonPredicate versionPredicate;

        private Builder() {
            this.languageTags = new ArrayList();
            this.testDevices = new ArrayList();
            this.missBehavior = Audience.MISS_BEHAVIOR_PENALIZE;
        }

        /* access modifiers changed from: package-private */
        public Builder setNewUser(boolean z) {
            this.newUser = Boolean.valueOf(z);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addTestDevice(String str) {
            this.testDevices.add(str);
            return this;
        }

        public Builder setLocationOptIn(boolean z) {
            this.locationOptIn = Boolean.valueOf(z);
            return this;
        }

        public Builder setNotificationsOptIn(boolean z) {
            this.notificationsOptIn = Boolean.valueOf(z);
            return this;
        }

        public Builder addLanguageTag(String str) {
            this.languageTags.add(str);
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setVersionPredicate(JsonPredicate jsonPredicate) {
            this.versionPredicate = jsonPredicate;
            return this;
        }

        public Builder setVersionMatcher(ValueMatcher valueMatcher) {
            return setVersionPredicate(valueMatcher == null ? null : VersionUtils.createVersionPredicate(valueMatcher));
        }

        public Builder setTagSelector(TagSelector tagSelector2) {
            this.tagSelector = tagSelector2;
            return this;
        }

        public Builder setMissBehavior(String str) {
            this.missBehavior = str;
            return this;
        }

        public Audience build() {
            return new Audience(this);
        }
    }
}
