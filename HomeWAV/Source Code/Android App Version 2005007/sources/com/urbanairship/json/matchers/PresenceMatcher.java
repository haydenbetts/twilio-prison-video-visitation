package com.urbanairship.json.matchers;

import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;

public class PresenceMatcher extends ValueMatcher {
    public static final String IS_PRESENT_VALUE_KEY = "is_present";
    private final boolean isPresent;

    public PresenceMatcher(boolean z) {
        this.isPresent = z;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(IS_PRESENT_VALUE_KEY, Boolean.valueOf(this.isPresent)).build().toJsonValue();
    }

    /* access modifiers changed from: protected */
    public boolean apply(JsonValue jsonValue, boolean z) {
        if (this.isPresent) {
            return !jsonValue.isNull();
        }
        return jsonValue.isNull();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && this.isPresent == ((PresenceMatcher) obj).isPresent) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.isPresent ? 1 : 0;
    }
}
