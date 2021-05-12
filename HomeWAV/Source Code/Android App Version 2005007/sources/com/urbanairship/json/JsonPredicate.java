package com.urbanairship.json;

import com.urbanairship.Predicate;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonPredicate implements JsonSerializable, Predicate<JsonSerializable> {
    public static final String AND_PREDICATE_TYPE = "and";
    public static final String NOT_PREDICATE_TYPE = "not";
    public static final String OR_PREDICATE_TYPE = "or";
    private final List<Predicate<JsonSerializable>> items;
    private final String type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PredicateType {
    }

    private JsonPredicate(Builder builder) {
        this.items = builder.items;
        this.type = builder.type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(this.type, (JsonSerializable) JsonValue.wrapOpt(this.items)).build().toJsonValue();
    }

    public static JsonPredicate parse(JsonValue jsonValue) throws JsonException {
        if (jsonValue == null || !jsonValue.isJsonMap() || jsonValue.optMap().isEmpty()) {
            throw new JsonException("Unable to parse empty JsonValue: " + jsonValue);
        }
        JsonMap optMap = jsonValue.optMap();
        Builder newBuilder = newBuilder();
        String predicateType = getPredicateType(optMap);
        if (predicateType != null) {
            newBuilder.setPredicateType(predicateType);
            Iterator<JsonValue> it = optMap.opt(predicateType).optList().iterator();
            while (it.hasNext()) {
                JsonValue next = it.next();
                if (next.isJsonMap()) {
                    if (getPredicateType(next.optMap()) != null) {
                        newBuilder.addPredicate(parse(next));
                    } else {
                        newBuilder.addMatcher(JsonMatcher.parse(next));
                    }
                }
            }
        } else {
            newBuilder.addMatcher(JsonMatcher.parse(jsonValue));
        }
        try {
            return newBuilder.build();
        } catch (IllegalArgumentException e) {
            throw new JsonException("Unable to parse JsonPredicate.", e);
        }
    }

    private static String getPredicateType(JsonMap jsonMap) {
        if (jsonMap.containsKey(AND_PREDICATE_TYPE)) {
            return AND_PREDICATE_TYPE;
        }
        if (jsonMap.containsKey(OR_PREDICATE_TYPE)) {
            return OR_PREDICATE_TYPE;
        }
        if (jsonMap.containsKey(NOT_PREDICATE_TYPE)) {
            return NOT_PREDICATE_TYPE;
        }
        return null;
    }

    public boolean apply(JsonSerializable jsonSerializable) {
        if (this.items.size() == 0) {
            return true;
        }
        String str = this.type;
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 3555) {
            if (hashCode != 96727) {
                if (hashCode == 109267 && str.equals(NOT_PREDICATE_TYPE)) {
                    c = 0;
                }
            } else if (str.equals(AND_PREDICATE_TYPE)) {
                c = 1;
            }
        } else if (str.equals(OR_PREDICATE_TYPE)) {
            c = 2;
        }
        if (c == 0) {
            return !this.items.get(0).apply(jsonSerializable);
        }
        if (c != 1) {
            for (Predicate<JsonSerializable> apply : this.items) {
                if (apply.apply(jsonSerializable)) {
                    return true;
                }
            }
            return false;
        }
        for (Predicate<JsonSerializable> apply2 : this.items) {
            if (!apply2.apply(jsonSerializable)) {
                return false;
            }
        }
        return true;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public final List<Predicate<JsonSerializable>> items = new ArrayList();
        /* access modifiers changed from: private */
        public String type = JsonPredicate.OR_PREDICATE_TYPE;

        public Builder setPredicateType(String str) {
            this.type = str;
            return this;
        }

        public Builder addMatcher(JsonMatcher jsonMatcher) {
            this.items.add(jsonMatcher);
            return this;
        }

        public Builder addPredicate(JsonPredicate jsonPredicate) {
            this.items.add(jsonPredicate);
            return this;
        }

        public JsonPredicate build() {
            if (this.type.equals(JsonPredicate.NOT_PREDICATE_TYPE) && this.items.size() > 1) {
                throw new IllegalArgumentException("`NOT` predicate type only supports a single matcher or predicate.");
            } else if (!this.items.isEmpty()) {
                return new JsonPredicate(this);
            } else {
                throw new IllegalArgumentException("Predicate must contain at least 1 matcher or child predicate.");
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonPredicate jsonPredicate = (JsonPredicate) obj;
        List<Predicate<JsonSerializable>> list = this.items;
        if (list == null ? jsonPredicate.items != null : !list.equals(jsonPredicate.items)) {
            return false;
        }
        String str = this.type;
        String str2 = jsonPredicate.type;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        List<Predicate<JsonSerializable>> list = this.items;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.type;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }
}
