package com.urbanairship.json;

import com.urbanairship.Predicate;
import java.util.ArrayList;
import java.util.List;

public class JsonMatcher implements JsonSerializable, Predicate<JsonSerializable> {
    private static final String FIELD_KEY = "key";
    private static final String IGNORE_CASE_KEY = "ignore_case";
    private static final String SCOPE_KEY = "scope";
    private static final String VALUE_KEY = "value";
    private final Boolean ignoreCase;
    private final String key;
    private final List<String> scopeList;
    private final ValueMatcher value;

    private JsonMatcher(Builder builder) {
        this.key = builder.key;
        this.scopeList = builder.scope;
        this.value = builder.valueMatcher == null ? ValueMatcher.newIsPresentMatcher() : builder.valueMatcher;
        this.ignoreCase = builder.ignoreCase;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(FIELD_KEY, this.key).putOpt(SCOPE_KEY, this.scopeList).put("value", (JsonSerializable) this.value).putOpt(IGNORE_CASE_KEY, this.ignoreCase).build().toJsonValue();
    }

    public boolean apply(JsonSerializable jsonSerializable) {
        JsonValue jsonValue = jsonSerializable == null ? JsonValue.NULL : jsonSerializable.toJsonValue();
        for (String opt : this.scopeList) {
            jsonValue = jsonValue.optMap().opt(opt);
            if (jsonValue.isNull()) {
                break;
            }
        }
        if (this.key != null) {
            jsonValue = jsonValue.optMap().opt(this.key);
        }
        ValueMatcher valueMatcher = this.value;
        Boolean bool = this.ignoreCase;
        return valueMatcher.apply(jsonValue, bool != null && bool.booleanValue());
    }

    public static JsonMatcher parse(JsonValue jsonValue) throws JsonException {
        if (jsonValue == null || !jsonValue.isJsonMap() || jsonValue.optMap().isEmpty()) {
            throw new JsonException("Unable to parse empty JsonValue: " + jsonValue);
        }
        JsonMap optMap = jsonValue.optMap();
        if (optMap.containsKey("value")) {
            Builder valueMatcher = newBuilder().setKey(optMap.opt(FIELD_KEY).getString()).setValueMatcher(ValueMatcher.parse(optMap.get("value")));
            JsonValue opt = optMap.opt(SCOPE_KEY);
            if (opt.isString()) {
                valueMatcher.setScope(opt.optString());
            } else if (opt.isJsonList()) {
                ArrayList arrayList = new ArrayList();
                for (JsonValue string : opt.optList().getList()) {
                    arrayList.add(string.getString());
                }
                valueMatcher.setScope((List<String>) arrayList);
            }
            if (optMap.containsKey(IGNORE_CASE_KEY)) {
                valueMatcher.setIgnoreCase(optMap.opt(IGNORE_CASE_KEY).getBoolean(false));
            }
            return valueMatcher.build();
        }
        throw new JsonException("JsonMatcher must contain a value matcher.");
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Boolean ignoreCase;
        /* access modifiers changed from: private */
        public String key;
        /* access modifiers changed from: private */
        public List<String> scope;
        /* access modifiers changed from: private */
        public ValueMatcher valueMatcher;

        private Builder() {
            this.scope = new ArrayList(1);
        }

        public Builder setValueMatcher(ValueMatcher valueMatcher2) {
            this.valueMatcher = valueMatcher2;
            return this;
        }

        public Builder setScope(List<String> list) {
            ArrayList arrayList = new ArrayList();
            this.scope = arrayList;
            if (list != null) {
                arrayList.addAll(list);
            }
            return this;
        }

        public Builder setScope(String str) {
            ArrayList arrayList = new ArrayList();
            this.scope = arrayList;
            arrayList.add(str);
            return this;
        }

        public Builder setKey(String str) {
            this.key = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setIgnoreCase(boolean z) {
            this.ignoreCase = Boolean.valueOf(z);
            return this;
        }

        public JsonMatcher build() {
            return new JsonMatcher(this);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonMatcher jsonMatcher = (JsonMatcher) obj;
        String str = this.key;
        if (str == null ? jsonMatcher.key != null : !str.equals(jsonMatcher.key)) {
            return false;
        }
        if (!this.scopeList.equals(jsonMatcher.scopeList)) {
            return false;
        }
        Boolean bool = this.ignoreCase;
        if (bool == null ? jsonMatcher.ignoreCase == null : bool.equals(jsonMatcher.ignoreCase)) {
            return this.value.equals(jsonMatcher.value);
        }
        return false;
    }

    public int hashCode() {
        String str = this.key;
        int i = 0;
        int hashCode = (((((str != null ? str.hashCode() : 0) * 31) + this.scopeList.hashCode()) * 31) + this.value.hashCode()) * 31;
        Boolean bool = this.ignoreCase;
        if (bool != null) {
            i = bool.hashCode();
        }
        return hashCode + i;
    }
}
