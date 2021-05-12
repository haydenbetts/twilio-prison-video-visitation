package com.urbanairship.json.matchers;

import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;
import com.urbanairship.util.IvyVersionMatcher;

public class VersionMatcher extends ValueMatcher {
    public static final String ALTERNATE_VERSION_KEY = "version";
    public static final String VERSION_KEY = "version_matches";
    private final IvyVersionMatcher versionMatcher;

    public VersionMatcher(IvyVersionMatcher ivyVersionMatcher) {
        this.versionMatcher = ivyVersionMatcher;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(VERSION_KEY, this.versionMatcher).build().toJsonValue();
    }

    /* access modifiers changed from: protected */
    public boolean apply(JsonValue jsonValue, boolean z) {
        return jsonValue.isString() && this.versionMatcher.apply(jsonValue.getString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.versionMatcher.equals(((VersionMatcher) obj).versionMatcher);
    }

    public int hashCode() {
        return this.versionMatcher.hashCode();
    }
}
