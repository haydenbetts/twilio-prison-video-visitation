package com.urbanairship.channel;

import com.urbanairship.Logger;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.DateUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AttributeMutation implements JsonSerializable {
    static final String ATTRIBUTE_ACTION_KEY = "action";
    private static final String ATTRIBUTE_ACTION_REMOVE = "remove";
    private static final String ATTRIBUTE_ACTION_SET = "set";
    static final String ATTRIBUTE_NAME_KEY = "key";
    static final String ATTRIBUTE_TIMESTAMP_KEY = "timestamp";
    static final String ATTRIBUTE_VALUE_KEY = "value";
    private final String action;
    private final String name;
    private final String timestamp;
    private final JsonValue value;

    AttributeMutation(String str, String str2, JsonValue jsonValue, String str3) {
        this.action = str;
        this.name = str2;
        this.value = jsonValue;
        this.timestamp = str3;
    }

    public static AttributeMutation newSetAttributeMutation(String str, JsonValue jsonValue, long j) {
        if (!jsonValue.isNull() && !jsonValue.isJsonList() && !jsonValue.isJsonMap() && !jsonValue.isBoolean()) {
            return new AttributeMutation(ATTRIBUTE_ACTION_SET, str, jsonValue, DateUtils.createIso8601TimeStamp(j));
        }
        throw new IllegalArgumentException("Invalid attribute value: " + jsonValue);
    }

    public static AttributeMutation newRemoveAttributeMutation(String str, long j) {
        return new AttributeMutation(ATTRIBUTE_ACTION_REMOVE, str, (JsonValue) null, DateUtils.createIso8601TimeStamp(j));
    }

    static AttributeMutation fromJsonValue(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        String string = optMap.opt(ATTRIBUTE_ACTION_KEY).getString();
        String string2 = optMap.opt(ATTRIBUTE_NAME_KEY).getString();
        JsonValue jsonValue2 = optMap.get("value");
        String string3 = optMap.opt("timestamp").getString();
        if (string != null && string2 != null && (jsonValue2 == null || isValidValue(jsonValue2))) {
            return new AttributeMutation(string, string2, jsonValue2, string3);
        }
        throw new JsonException("Invalid attribute mutation: " + optMap);
    }

    static List<AttributeMutation> fromJsonList(JsonList jsonList) {
        ArrayList arrayList = new ArrayList();
        Iterator<JsonValue> it = jsonList.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(fromJsonValue(it.next()));
            } catch (JsonException e) {
                Logger.error(e, "Invalid attribute.", new Object[0]);
            }
        }
        return arrayList;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(ATTRIBUTE_ACTION_KEY, this.action).put(ATTRIBUTE_NAME_KEY, this.name).put("value", (JsonSerializable) this.value).put("timestamp", this.timestamp).build().toJsonValue();
    }

    public static List<AttributeMutation> collapseMutations(List<AttributeMutation> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList<AttributeMutation> arrayList2 = new ArrayList<>(list);
        Collections.reverse(arrayList2);
        HashSet hashSet = new HashSet();
        for (AttributeMutation attributeMutation : arrayList2) {
            if (!hashSet.contains(attributeMutation.name)) {
                arrayList.add(0, attributeMutation);
                hashSet.add(attributeMutation.name);
            }
        }
        return arrayList;
    }

    private static boolean isValidValue(JsonValue jsonValue) {
        return !jsonValue.isNull() && !jsonValue.isJsonList() && !jsonValue.isJsonMap() && !jsonValue.isBoolean();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AttributeMutation attributeMutation = (AttributeMutation) obj;
        if (!this.action.equals(attributeMutation.action) || !this.name.equals(attributeMutation.name)) {
            return false;
        }
        JsonValue jsonValue = this.value;
        if (jsonValue == null ? attributeMutation.value == null : jsonValue.equals(attributeMutation.value)) {
            return this.timestamp.equals(attributeMutation.timestamp);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.action.hashCode() * 31) + this.name.hashCode()) * 31;
        JsonValue jsonValue = this.value;
        return ((hashCode + (jsonValue != null ? jsonValue.hashCode() : 0)) * 31) + this.timestamp.hashCode();
    }

    public String toString() {
        return "AttributeMutation{action='" + this.action + '\'' + ", name='" + this.name + '\'' + ", value=" + this.value + ", timestamp='" + this.timestamp + '\'' + '}';
    }
}
