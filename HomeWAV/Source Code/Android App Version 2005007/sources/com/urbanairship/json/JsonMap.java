package com.urbanairship.json;

import com.urbanairship.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONStringer;

public class JsonMap implements Iterable<Map.Entry<String, JsonValue>>, JsonSerializable {
    public static final JsonMap EMPTY_MAP = new JsonMap((Map<String, JsonValue>) null);
    private final Map<String, JsonValue> map;

    public JsonMap(Map<String, JsonValue> map2) {
        this.map = map2 == null ? new HashMap() : new HashMap(map2);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean containsKey(String str) {
        return this.map.containsKey(str);
    }

    public boolean containsValue(JsonValue jsonValue) {
        return this.map.containsValue(jsonValue);
    }

    public Set<Map.Entry<String, JsonValue>> entrySet() {
        return this.map.entrySet();
    }

    public JsonValue get(String str) {
        return this.map.get(str);
    }

    public JsonValue opt(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue != null) {
            return jsonValue;
        }
        return JsonValue.NULL;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    public int size() {
        return this.map.size();
    }

    public Collection<JsonValue> values() {
        return new ArrayList(this.map.values());
    }

    public Map<String, JsonValue> getMap() {
        return new HashMap(this.map);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof JsonMap) {
            return this.map.equals(((JsonMap) obj).map);
        }
        if (obj instanceof JsonValue) {
            return this.map.equals(((JsonValue) obj).optMap().map);
        }
        return false;
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public String toString() {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            write(jSONStringer);
            return jSONStringer.toString();
        } catch (JSONException e) {
            Logger.error(e, "JsonMap - Failed to create JSON String.", new Object[0]);
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public void write(JSONStringer jSONStringer) throws JSONException {
        jSONStringer.object();
        for (Map.Entry next : entrySet()) {
            jSONStringer.key((String) next.getKey());
            ((JsonValue) next.getValue()).write(jSONStringer);
        }
        jSONStringer.endObject();
    }

    public Iterator<Map.Entry<String, JsonValue>> iterator() {
        return entrySet().iterator();
    }

    public JsonValue toJsonValue() {
        return JsonValue.wrap((JsonSerializable) this);
    }

    public static class Builder {
        private final Map<String, JsonValue> map;

        private Builder() {
            this.map = new HashMap();
        }

        public Builder putAll(JsonMap jsonMap) {
            for (Map.Entry next : jsonMap.entrySet()) {
                put((String) next.getKey(), (JsonSerializable) next.getValue());
            }
            return this;
        }

        public Builder put(String str, JsonSerializable jsonSerializable) {
            if (jsonSerializable == null || jsonSerializable.toJsonValue().isNull()) {
                this.map.remove(str);
            } else {
                this.map.put(str, jsonSerializable.toJsonValue());
            }
            return this;
        }

        public Builder putOpt(String str, Object obj) {
            put(str, (JsonSerializable) JsonValue.wrapOpt(obj));
            return this;
        }

        public Builder put(String str, String str2) {
            if (str2 != null) {
                put(str, (JsonSerializable) JsonValue.wrap(str2));
            } else {
                this.map.remove(str);
            }
            return this;
        }

        public Builder put(String str, boolean z) {
            return put(str, (JsonSerializable) JsonValue.wrap(z));
        }

        public Builder put(String str, int i) {
            return put(str, (JsonSerializable) JsonValue.wrap(i));
        }

        public Builder put(String str, long j) {
            return put(str, (JsonSerializable) JsonValue.wrap(j));
        }

        public Builder put(String str, double d) {
            return put(str, (JsonSerializable) JsonValue.wrap(d));
        }

        public Builder put(String str, char c) {
            return put(str, (JsonSerializable) JsonValue.wrap(c));
        }

        public JsonMap build() {
            return new JsonMap(this.map);
        }
    }
}
