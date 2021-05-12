package com.urbanairship.automation.storage;

import com.urbanairship.Logger;
import com.urbanairship.automation.Audience;
import com.urbanairship.automation.TriggerContext;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonPredicate;
import com.urbanairship.json.JsonValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Converters {
    public JsonValue jsonValueFromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return JsonValue.parseString(str);
        } catch (JsonException e) {
            Logger.error(e, "Unable to parse json value: " + str, new Object[0]);
            return null;
        }
    }

    public String jsonValueToString(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return jsonValue.toString();
    }

    public JsonMap jsonMapFromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return JsonValue.parseString(str).optMap();
        } catch (JsonException e) {
            Logger.error(e, "Unable to parse json value: " + str, new Object[0]);
            return null;
        }
    }

    public String jsonMapToString(JsonMap jsonMap) {
        if (jsonMap == null) {
            return null;
        }
        return jsonMap.toJsonValue().toString();
    }

    public String triggerContextToString(TriggerContext triggerContext) {
        if (triggerContext == null) {
            return null;
        }
        return triggerContext.toJsonValue().toString();
    }

    public TriggerContext triggerContextFromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return TriggerContext.fromJson(JsonValue.parseString(str));
        } catch (JsonException e) {
            Logger.error(e, "Unable to parse trigger context: " + str, new Object[0]);
            return null;
        }
    }

    public String audienceToString(Audience audience) {
        if (audience == null) {
            return null;
        }
        return audience.toJsonValue().toString();
    }

    public Audience audienceFromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Audience.fromJson(JsonValue.parseString(str));
        } catch (JsonException e) {
            Logger.error(e, "Unable to parse audience: " + str, new Object[0]);
            return null;
        }
    }

    public String jsonPredicateToString(JsonPredicate jsonPredicate) {
        if (jsonPredicate == null) {
            return null;
        }
        return jsonPredicate.toJsonValue().toString();
    }

    public JsonPredicate jsonPredicateFromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return JsonPredicate.parse(JsonValue.parseString(str));
        } catch (JsonException e) {
            Logger.error(e, "Unable to parse trigger context: " + str, new Object[0]);
            return null;
        }
    }

    public static List<String> stringArrayFromString(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<JsonValue> it = JsonValue.parseString(str).optList().iterator();
            while (it.hasNext()) {
                JsonValue next = it.next();
                if (next.getString() != null) {
                    arrayList.add(next.optString());
                }
            }
            return arrayList;
        } catch (JsonException e) {
            Logger.error(e, "Unable to parse string array from string: " + str, new Object[0]);
            return null;
        }
    }

    public static String fromArrayList(List<String> list) {
        return JsonValue.wrapOpt(list).toString();
    }
}
