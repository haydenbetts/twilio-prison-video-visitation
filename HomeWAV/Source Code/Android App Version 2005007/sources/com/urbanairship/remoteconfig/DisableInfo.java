package com.urbanairship.remoteconfig;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.IvyVersionMatcher;
import com.urbanairship.util.VersionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

class DisableInfo implements JsonSerializable {
    private static final String APP_VERSIONS_KEY = "app_versions";
    private static final String MODULES_ALL_KEY = "all";
    private static final String MODULES_KEY = "modules";
    private static final String REMOTE_DATA_REFRESH_INTERVAL_KEY = "remote_data_refresh_interval";
    private static final String SDK_VERSIONS_KEY = "sdk_versions";
    private final JsonPredicate appVersionPredicate;
    private final Set<String> disabledModules;
    private final long remoteDataInterval;
    private final Set<String> sdkVersionConstraints;

    private DisableInfo(Builder builder) {
        this.disabledModules = builder.disabledModules;
        this.remoteDataInterval = builder.remoteDataInterval;
        this.sdkVersionConstraints = builder.sdkVersionConstraints;
        this.appVersionPredicate = builder.appVersionPredicate;
    }

    public static List<DisableInfo> filter(Collection<DisableInfo> collection, String str, long j) {
        JsonSerializable createVersionObject = VersionUtils.createVersionObject(j);
        ArrayList arrayList = new ArrayList();
        for (DisableInfo next : collection) {
            Set<String> set = next.sdkVersionConstraints;
            if (set != null) {
                boolean z = false;
                Iterator<String> it = set.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (IvyVersionMatcher.newMatcher(it.next()).apply(str)) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                }
            }
            JsonPredicate jsonPredicate = next.appVersionPredicate;
            if (jsonPredicate == null || jsonPredicate.apply(createVersionObject)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(MODULES_KEY, this.disabledModules).putOpt(REMOTE_DATA_REFRESH_INTERVAL_KEY, Long.valueOf(this.remoteDataInterval)).putOpt(SDK_VERSIONS_KEY, this.sdkVersionConstraints).putOpt(APP_VERSIONS_KEY, this.appVersionPredicate).build().toJsonValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DisableInfo disableInfo = (DisableInfo) obj;
        if (this.remoteDataInterval != disableInfo.remoteDataInterval || !this.disabledModules.equals(disableInfo.disabledModules)) {
            return false;
        }
        Set<String> set = this.sdkVersionConstraints;
        if (set == null ? disableInfo.sdkVersionConstraints != null : !set.equals(disableInfo.sdkVersionConstraints)) {
            return false;
        }
        JsonPredicate jsonPredicate = this.appVersionPredicate;
        JsonPredicate jsonPredicate2 = disableInfo.appVersionPredicate;
        if (jsonPredicate != null) {
            return jsonPredicate.equals(jsonPredicate2);
        }
        if (jsonPredicate2 == null) {
            return true;
        }
        return false;
    }

    public static DisableInfo fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        Builder newBuilder = newBuilder();
        if (optMap.containsKey(MODULES_KEY)) {
            HashSet hashSet = new HashSet();
            if ("all".equals(optMap.opt(MODULES_KEY).getString())) {
                hashSet.addAll(Modules.ALL_MODULES);
            } else {
                JsonList list = optMap.opt(MODULES_KEY).getList();
                if (list != null) {
                    Iterator<JsonValue> it = list.iterator();
                    while (it.hasNext()) {
                        JsonValue next = it.next();
                        if (next.isString()) {
                            if (Modules.ALL_MODULES.contains(next.getString())) {
                                hashSet.add(next.getString());
                            }
                        } else {
                            throw new JsonException("Modules must be an array of strings: " + optMap.opt(MODULES_KEY));
                        }
                    }
                } else {
                    throw new JsonException("Modules must be an array of strings: " + optMap.opt(MODULES_KEY));
                }
            }
            newBuilder.setDisabledModules(hashSet);
        }
        if (optMap.containsKey(REMOTE_DATA_REFRESH_INTERVAL_KEY)) {
            if (optMap.opt(REMOTE_DATA_REFRESH_INTERVAL_KEY).isNumber()) {
                newBuilder.setRemoteDataInterval(TimeUnit.SECONDS.toMillis(optMap.opt(REMOTE_DATA_REFRESH_INTERVAL_KEY).getLong(0)));
            } else {
                throw new IllegalArgumentException("Remote data refresh interval must be a number: " + optMap.get(REMOTE_DATA_REFRESH_INTERVAL_KEY));
            }
        }
        if (optMap.containsKey(SDK_VERSIONS_KEY)) {
            HashSet hashSet2 = new HashSet();
            JsonList list2 = optMap.opt(SDK_VERSIONS_KEY).getList();
            if (list2 != null) {
                Iterator<JsonValue> it2 = list2.iterator();
                while (it2.hasNext()) {
                    JsonValue next2 = it2.next();
                    if (next2.isString()) {
                        hashSet2.add(next2.getString());
                    } else {
                        throw new JsonException("SDK Versions must be an array of strings: " + optMap.opt(SDK_VERSIONS_KEY));
                    }
                }
                newBuilder.setSDKVersionConstraints(hashSet2);
            } else {
                throw new JsonException("SDK Versions must be an array of strings: " + optMap.opt(SDK_VERSIONS_KEY));
            }
        }
        if (optMap.containsKey(APP_VERSIONS_KEY)) {
            newBuilder.setAppVersionPredicate(JsonPredicate.parse(optMap.get(APP_VERSIONS_KEY)));
        }
        return newBuilder.build();
    }

    public Set<String> getSdkVersionConstraints() {
        return this.sdkVersionConstraints;
    }

    public JsonPredicate getAppVersionPredicate() {
        return this.appVersionPredicate;
    }

    public Set<String> getDisabledModules() {
        return this.disabledModules;
    }

    public long getRemoteDataRefreshInterval() {
        return this.remoteDataInterval;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public JsonPredicate appVersionPredicate;
        /* access modifiers changed from: private */
        public final Set<String> disabledModules;
        /* access modifiers changed from: private */
        public long remoteDataInterval;
        /* access modifiers changed from: private */
        public Set<String> sdkVersionConstraints;

        private Builder() {
            this.disabledModules = new HashSet();
        }

        public Builder setDisabledModules(Collection<String> collection) {
            this.disabledModules.clear();
            if (collection != null) {
                this.disabledModules.addAll(collection);
            }
            return this;
        }

        public Builder setRemoteDataInterval(long j) {
            this.remoteDataInterval = j;
            return this;
        }

        public Builder setSDKVersionConstraints(Collection<String> collection) {
            this.sdkVersionConstraints = collection == null ? null : new HashSet(collection);
            return this;
        }

        public Builder setAppVersionPredicate(JsonPredicate jsonPredicate) {
            this.appVersionPredicate = jsonPredicate;
            return this;
        }

        public DisableInfo build() {
            return new DisableInfo(this);
        }
    }
}
