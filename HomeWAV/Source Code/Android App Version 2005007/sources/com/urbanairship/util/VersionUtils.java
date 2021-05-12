package com.urbanairship.util;

import com.urbanairship.UAirship;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonMatcher;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.ValueMatcher;

public class VersionUtils {
    static final String AMAZON_VERSION_KEY = "amazon";
    static final String ANDROID_VERSION_KEY = "android";
    static final String VERSION_KEY = "version";

    public static JsonSerializable createVersionObject() {
        return createVersionObject(UAirship.shared().getApplicationMetrics().getCurrentAppVersion());
    }

    public static JsonSerializable createVersionObject(long j) {
        return JsonMap.newBuilder().put(UAirship.shared().getPlatformType() == 1 ? "amazon" : "android", (JsonSerializable) JsonMap.newBuilder().put("version", j).build()).build().toJsonValue();
    }

    public static JsonPredicate createVersionPredicate(ValueMatcher valueMatcher) {
        return JsonPredicate.newBuilder().addMatcher(JsonMatcher.newBuilder().setScope(UAirship.shared().getPlatformType() == 1 ? "amazon" : "android").setKey("version").setValueMatcher(valueMatcher).build()).build();
    }
}
