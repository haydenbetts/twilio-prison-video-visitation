package com.urbanairship.automation.tags;

import com.urbanairship.PreferenceDataStore;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Clock;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TagGroupLookupResponseCache {
    private static final String CREATE_DATE_KEY = "com.urbanairship.iam.tags.TAG_CACHE_CREATE_DATE";
    public static final long DEFAULT_MAX_AGE_TIME_MS = 600000;
    public static final long DEFAULT_STALE_READ_TIME_MS = 3600000;
    private static final String MAX_AGE_TIME_KEY = "com.urbanairship.iam.tags.TAG_CACHE_MAX_AGE_TIME";
    public static final long MIN_MAX_AGE_TIME_MS = 60000;
    private static final String REQUESTED_TAGS_KEY = "com.urbanairship.iam.tags.TAG_CACHE_REQUESTED_TAGS";
    private static final String RESPONSE_KEY = "com.urbanairship.iam.tags.TAG_CACHE_RESPONSE";
    private static final String STALE_READ_TIME_KEY = "com.urbanairship.iam.tags.TAG_STALE_READ_TIME";
    private final Clock clock;
    private final PreferenceDataStore dataStore;

    public TagGroupLookupResponseCache(PreferenceDataStore preferenceDataStore, Clock clock2) {
        this.dataStore = preferenceDataStore;
        this.clock = clock2;
    }

    public void setMaxAgeTime(long j, TimeUnit timeUnit) {
        this.dataStore.put(MAX_AGE_TIME_KEY, timeUnit.toMillis(j));
    }

    public long getMaxAgeTimeMilliseconds() {
        return Math.max(this.dataStore.getLong(MAX_AGE_TIME_KEY, 600000), 60000);
    }

    public void setStaleReadTime(long j, TimeUnit timeUnit) {
        this.dataStore.put(STALE_READ_TIME_KEY, timeUnit.toMillis(j));
    }

    public long getStaleReadTimeMilliseconds() {
        return this.dataStore.getLong(STALE_READ_TIME_KEY, DEFAULT_STALE_READ_TIME_MS);
    }

    public void setResponse(TagGroupResponse tagGroupResponse, Map<String, Set<String>> map) {
        this.dataStore.put(RESPONSE_KEY, (JsonSerializable) tagGroupResponse);
        this.dataStore.put(CREATE_DATE_KEY, this.clock.currentTimeMillis());
        this.dataStore.put(REQUESTED_TAGS_KEY, JsonValue.wrapOpt(map));
    }

    public void clear() {
        this.dataStore.remove(RESPONSE_KEY);
        this.dataStore.remove(CREATE_DATE_KEY);
        this.dataStore.remove(REQUESTED_TAGS_KEY);
    }

    public TagGroupResponse getResponse() {
        JsonValue jsonValue = this.dataStore.getJsonValue(RESPONSE_KEY);
        if (jsonValue.isNull()) {
            return null;
        }
        return TagGroupResponse.fromJsonValue(jsonValue);
    }

    public long getCreateDate() {
        return this.dataStore.getLong(CREATE_DATE_KEY, -1);
    }

    public Map<String, Set<String>> getRequestTags() {
        return TagGroupUtils.parseTags(this.dataStore.getJsonValue(REQUESTED_TAGS_KEY));
    }
}
