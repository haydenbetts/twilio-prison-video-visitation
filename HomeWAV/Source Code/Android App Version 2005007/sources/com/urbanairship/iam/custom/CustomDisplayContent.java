package com.urbanairship.iam.custom;

import com.urbanairship.iam.DisplayContent;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;

public class CustomDisplayContent implements DisplayContent {
    private static final String CUSTOM_KEY = "custom";
    private final JsonValue value;

    public CustomDisplayContent(JsonValue jsonValue) {
        this.value = jsonValue;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put("custom", (JsonSerializable) this.value).build().toJsonValue();
    }

    public static CustomDisplayContent fromJson(JsonValue jsonValue) throws JsonException {
        if (jsonValue.isJsonMap()) {
            return new CustomDisplayContent(jsonValue.optMap().opt("custom"));
        }
        throw new JsonException("Invalid custom display content: " + jsonValue);
    }

    public JsonValue getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.value.equals(((CustomDisplayContent) obj).value);
    }

    public int hashCode() {
        return this.value.hashCode();
    }
}
