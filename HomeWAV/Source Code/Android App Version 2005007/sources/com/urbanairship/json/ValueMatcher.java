package com.urbanairship.json;

import com.urbanairship.Predicate;
import com.urbanairship.json.matchers.ArrayContainsMatcher;
import com.urbanairship.json.matchers.ExactValueMatcher;
import com.urbanairship.json.matchers.NumberRangeMatcher;
import com.urbanairship.json.matchers.PresenceMatcher;
import com.urbanairship.json.matchers.VersionMatcher;
import com.urbanairship.util.IvyVersionMatcher;

public abstract class ValueMatcher implements JsonSerializable, Predicate<JsonSerializable> {
    /* access modifiers changed from: protected */
    public abstract boolean apply(JsonValue jsonValue, boolean z);

    protected ValueMatcher() {
    }

    public static ValueMatcher newNumberRangeMatcher(Double d, Double d2) {
        if (d == null || d2 == null || d2.doubleValue() >= d.doubleValue()) {
            return new NumberRangeMatcher(d, d2);
        }
        throw new IllegalArgumentException();
    }

    public static ValueMatcher newValueMatcher(JsonValue jsonValue) {
        return new ExactValueMatcher(jsonValue);
    }

    public static ValueMatcher newIsPresentMatcher() {
        return new PresenceMatcher(true);
    }

    public static ValueMatcher newIsAbsentMatcher() {
        return new PresenceMatcher(false);
    }

    public static ValueMatcher newVersionMatcher(String str) {
        return new VersionMatcher(IvyVersionMatcher.newMatcher(str));
    }

    public static ValueMatcher newArrayContainsMatcher(JsonPredicate jsonPredicate, int i) {
        return new ArrayContainsMatcher(jsonPredicate, Integer.valueOf(i));
    }

    public static ValueMatcher newArrayContainsMatcher(JsonPredicate jsonPredicate) {
        return new ArrayContainsMatcher(jsonPredicate, (Integer) null);
    }

    public static ValueMatcher parse(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue == null ? JsonMap.EMPTY_MAP : jsonValue.optMap();
        if (optMap.containsKey(ExactValueMatcher.EQUALS_VALUE_KEY)) {
            return newValueMatcher(optMap.opt(ExactValueMatcher.EQUALS_VALUE_KEY));
        }
        if (optMap.containsKey(NumberRangeMatcher.MIN_VALUE_KEY) || optMap.containsKey(NumberRangeMatcher.MAX_VALUE_KEY)) {
            Double d = null;
            Double valueOf = optMap.containsKey(NumberRangeMatcher.MIN_VALUE_KEY) ? Double.valueOf(optMap.opt(NumberRangeMatcher.MIN_VALUE_KEY).getDouble(0.0d)) : null;
            if (optMap.containsKey(NumberRangeMatcher.MAX_VALUE_KEY)) {
                d = Double.valueOf(optMap.opt(NumberRangeMatcher.MAX_VALUE_KEY).getDouble(0.0d));
            }
            try {
                return newNumberRangeMatcher(valueOf, d);
            } catch (IllegalArgumentException e) {
                throw new JsonException("Invalid range matcher: " + jsonValue, e);
            }
        } else if (optMap.containsKey(PresenceMatcher.IS_PRESENT_VALUE_KEY)) {
            return optMap.opt(PresenceMatcher.IS_PRESENT_VALUE_KEY).getBoolean(false) ? newIsPresentMatcher() : newIsAbsentMatcher();
        } else {
            if (optMap.containsKey(VersionMatcher.VERSION_KEY)) {
                try {
                    return newVersionMatcher(optMap.opt(VersionMatcher.VERSION_KEY).optString());
                } catch (NumberFormatException e2) {
                    throw new JsonException("Invalid version constraint: " + optMap.opt(VersionMatcher.VERSION_KEY), e2);
                }
            } else if (optMap.containsKey(VersionMatcher.ALTERNATE_VERSION_KEY)) {
                try {
                    return newVersionMatcher(optMap.opt(VersionMatcher.ALTERNATE_VERSION_KEY).optString());
                } catch (NumberFormatException e3) {
                    throw new JsonException("Invalid version constraint: " + optMap.opt(VersionMatcher.ALTERNATE_VERSION_KEY), e3);
                }
            } else if (optMap.containsKey(ArrayContainsMatcher.ARRAY_CONTAINS_KEY)) {
                JsonPredicate parse = JsonPredicate.parse(optMap.get(ArrayContainsMatcher.ARRAY_CONTAINS_KEY));
                if (!optMap.containsKey(ArrayContainsMatcher.INDEX_KEY)) {
                    return newArrayContainsMatcher(parse);
                }
                int i = optMap.opt(ArrayContainsMatcher.INDEX_KEY).getInt(-1);
                if (i != -1) {
                    return newArrayContainsMatcher(parse, i);
                }
                throw new JsonException("Invalid index for array_contains matcher: " + optMap.get(ArrayContainsMatcher.INDEX_KEY));
            } else {
                throw new JsonException("Unknown value matcher: " + jsonValue);
            }
        }
    }

    public boolean apply(JsonSerializable jsonSerializable) {
        return apply(jsonSerializable, false);
    }

    /* access modifiers changed from: package-private */
    public boolean apply(JsonSerializable jsonSerializable, boolean z) {
        return apply(jsonSerializable == null ? JsonValue.NULL : jsonSerializable.toJsonValue(), z);
    }

    public String toString() {
        return toJsonValue().toString();
    }
}
