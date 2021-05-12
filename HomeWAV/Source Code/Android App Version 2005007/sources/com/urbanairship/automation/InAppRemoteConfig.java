package com.urbanairship.automation;

import com.urbanairship.automation.tags.TagGroupLookupResponseCache;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import java.util.concurrent.TimeUnit;

class InAppRemoteConfig {
    private static final String TAG_GROUPS_CONFIG_KEY = "tag_groups";
    public final TagGroupsConfig tagGroupsConfig;

    private InAppRemoteConfig(TagGroupsConfig tagGroupsConfig2) {
        this.tagGroupsConfig = tagGroupsConfig2;
    }

    static InAppRemoteConfig defaultConfig() {
        return new InAppRemoteConfig(TagGroupsConfig.defaultConfig());
    }

    public static InAppRemoteConfig fromJsonMap(JsonMap jsonMap) {
        if (jsonMap == null) {
            return defaultConfig();
        }
        TagGroupsConfig tagGroupsConfig2 = null;
        if (jsonMap.containsKey(TAG_GROUPS_CONFIG_KEY)) {
            tagGroupsConfig2 = TagGroupsConfig.fromJsonValue(jsonMap.opt(TAG_GROUPS_CONFIG_KEY));
        }
        return tagGroupsConfig2 != null ? new InAppRemoteConfig(tagGroupsConfig2) : defaultConfig();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.tagGroupsConfig.equals(((InAppRemoteConfig) obj).tagGroupsConfig);
    }

    public int hashCode() {
        return this.tagGroupsConfig.hashCode();
    }

    public static class TagGroupsConfig {
        private static final long DEFAULT_CACHE_MAX_AGE_TIME_SEC = TimeUnit.MILLISECONDS.toSeconds(600000);
        private static final long DEFAULT_CACHE_STALE_READ_TIME_SEC = TimeUnit.MILLISECONDS.toSeconds(TagGroupLookupResponseCache.DEFAULT_STALE_READ_TIME_MS);
        private static final boolean DEFAULT_FETCH_ENABLED = true;
        private static final long DEFAULT_PREFER_LOCAL_DATA_TIME_SEC = TimeUnit.MILLISECONDS.toSeconds(600000);
        private static final String TAG_GROUP_CACHE_MAX_AGE_SECONDS = "cache_max_age_seconds";
        private static final String TAG_GROUP_CACHE_PREFER_LOCAL_UNTIL_SECONDS = "cache_prefer_local_until_seconds";
        private static final String TAG_GROUP_CACHE_STALE_READ_TIME_SECONDS = "cache_stale_read_age_seconds";
        private static final String TAG_GROUP_FETCH_ENABLED_KEY = "enabled";
        public final long cacheMaxAgeInSeconds;
        public final long cachePreferLocalTagDataTimeSeconds;
        public final long cacheStaleReadTimeSeconds;
        public final boolean isEnabled;

        private TagGroupsConfig(boolean z, long j, long j2, long j3) {
            this.isEnabled = z;
            this.cacheMaxAgeInSeconds = j;
            this.cacheStaleReadTimeSeconds = j2;
            this.cachePreferLocalTagDataTimeSeconds = j3;
        }

        private TagGroupsConfig combine(TagGroupsConfig tagGroupsConfig) {
            return new TagGroupsConfig(this.isEnabled && tagGroupsConfig.isEnabled, Math.max(this.cacheMaxAgeInSeconds, tagGroupsConfig.cacheMaxAgeInSeconds), Math.max(this.cacheStaleReadTimeSeconds, tagGroupsConfig.cacheStaleReadTimeSeconds), Math.max(this.cachePreferLocalTagDataTimeSeconds, tagGroupsConfig.cachePreferLocalTagDataTimeSeconds));
        }

        /* access modifiers changed from: private */
        public static TagGroupsConfig defaultConfig() {
            return new TagGroupsConfig(true, DEFAULT_CACHE_MAX_AGE_TIME_SEC, DEFAULT_CACHE_STALE_READ_TIME_SEC, DEFAULT_PREFER_LOCAL_DATA_TIME_SEC);
        }

        /* access modifiers changed from: private */
        public static TagGroupsConfig fromJsonValue(JsonValue jsonValue) {
            JsonMap optMap = jsonValue.optMap();
            return new TagGroupsConfig(optMap.opt("enabled").getBoolean(true), optMap.opt(TAG_GROUP_CACHE_MAX_AGE_SECONDS).getLong(DEFAULT_CACHE_MAX_AGE_TIME_SEC), optMap.opt(TAG_GROUP_CACHE_STALE_READ_TIME_SECONDS).getLong(DEFAULT_CACHE_STALE_READ_TIME_SEC), optMap.opt(TAG_GROUP_CACHE_PREFER_LOCAL_UNTIL_SECONDS).getLong(DEFAULT_PREFER_LOCAL_DATA_TIME_SEC));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TagGroupsConfig tagGroupsConfig = (TagGroupsConfig) obj;
            if (this.isEnabled == tagGroupsConfig.isEnabled && this.cacheMaxAgeInSeconds == tagGroupsConfig.cacheMaxAgeInSeconds && this.cacheStaleReadTimeSeconds == tagGroupsConfig.cacheStaleReadTimeSeconds && this.cachePreferLocalTagDataTimeSeconds == tagGroupsConfig.cachePreferLocalTagDataTimeSeconds) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            long j = this.cacheMaxAgeInSeconds;
            long j2 = this.cacheStaleReadTimeSeconds;
            long j3 = this.cachePreferLocalTagDataTimeSeconds;
            return ((((((this.isEnabled ? 1 : 0) * true) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)));
        }
    }
}
