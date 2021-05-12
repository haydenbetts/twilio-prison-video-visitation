package com.urbanairship.json.matchers;

import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;

public class NumberRangeMatcher extends ValueMatcher {
    public static final String MAX_VALUE_KEY = "at_most";
    public static final String MIN_VALUE_KEY = "at_least";
    private final Double max;
    private final Double min;

    public NumberRangeMatcher(Double d, Double d2) {
        this.min = d;
        this.max = d2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NumberRangeMatcher numberRangeMatcher = (NumberRangeMatcher) obj;
        Double d = this.min;
        if (d == null ? numberRangeMatcher.min != null : !d.equals(numberRangeMatcher.min)) {
            return false;
        }
        Double d2 = this.max;
        Double d3 = numberRangeMatcher.max;
        if (d2 != null) {
            return d2.equals(d3);
        }
        if (d3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Double d = this.min;
        int i = 0;
        int hashCode = (d != null ? d.hashCode() : 0) * 31;
        Double d2 = this.max;
        if (d2 != null) {
            i = d2.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public boolean apply(JsonValue jsonValue, boolean z) {
        if (this.min != null && (!jsonValue.isNumber() || jsonValue.getDouble(0.0d) < this.min.doubleValue())) {
            return false;
        }
        if (this.max == null || (jsonValue.isNumber() && jsonValue.getDouble(0.0d) <= this.max.doubleValue())) {
            return true;
        }
        return false;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(MIN_VALUE_KEY, this.min).putOpt(MAX_VALUE_KEY, this.max).build().toJsonValue();
    }
}
