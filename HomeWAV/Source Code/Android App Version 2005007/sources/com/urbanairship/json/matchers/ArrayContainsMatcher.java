package com.urbanairship.json.matchers;

import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;
import java.util.Iterator;

public class ArrayContainsMatcher extends ValueMatcher {
    public static final String ARRAY_CONTAINS_KEY = "array_contains";
    public static final String INDEX_KEY = "index";
    private final Integer index;
    private final JsonPredicate predicate;

    public ArrayContainsMatcher(JsonPredicate jsonPredicate, Integer num) {
        this.predicate = jsonPredicate;
        this.index = num;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(ARRAY_CONTAINS_KEY, this.predicate).putOpt(INDEX_KEY, this.index).build().toJsonValue();
    }

    /* access modifiers changed from: protected */
    public boolean apply(JsonValue jsonValue, boolean z) {
        if (!jsonValue.isJsonList()) {
            return false;
        }
        JsonList optList = jsonValue.optList();
        Integer num = this.index;
        if (num == null) {
            Iterator<JsonValue> it = optList.iterator();
            while (it.hasNext()) {
                if (this.predicate.apply((JsonSerializable) it.next())) {
                    return true;
                }
            }
            return false;
        } else if (num.intValue() < 0 || this.index.intValue() >= optList.size()) {
            return false;
        } else {
            return this.predicate.apply((JsonSerializable) optList.get(this.index.intValue()));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArrayContainsMatcher arrayContainsMatcher = (ArrayContainsMatcher) obj;
        Integer num = this.index;
        if (num == null ? arrayContainsMatcher.index == null : num.equals(arrayContainsMatcher.index)) {
            return this.predicate.equals(arrayContainsMatcher.predicate);
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.index;
        return ((num != null ? num.hashCode() : 0) * 31) + this.predicate.hashCode();
    }
}
