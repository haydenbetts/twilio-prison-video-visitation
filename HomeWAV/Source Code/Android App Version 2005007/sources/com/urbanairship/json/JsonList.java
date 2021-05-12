package com.urbanairship.json;

import com.urbanairship.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONStringer;

public class JsonList implements Iterable<JsonValue>, JsonSerializable {
    public static final JsonList EMPTY_LIST = new JsonList((List<JsonValue>) null);
    private final List<JsonValue> list;

    public JsonList(List<JsonValue> list2) {
        this.list = list2 == null ? new ArrayList() : new ArrayList(list2);
    }

    public boolean contains(JsonValue jsonValue) {
        return this.list.contains(jsonValue);
    }

    public JsonValue get(int i) {
        return this.list.get(i);
    }

    public int indexOf(JsonValue jsonValue) {
        return this.list.indexOf(jsonValue);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public Iterator<JsonValue> iterator() {
        return this.list.iterator();
    }

    public int lastIndexOf(JsonValue jsonValue) {
        return this.list.indexOf(jsonValue);
    }

    public int size() {
        return this.list.size();
    }

    public List<JsonValue> getList() {
        return new ArrayList(this.list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof JsonList) {
            return this.list.equals(((JsonList) obj).list);
        }
        return false;
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public String toString() {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            write(jSONStringer);
            return jSONStringer.toString();
        } catch (JSONException e) {
            Logger.error(e, "JsonList - Failed to create JSON String.", new Object[0]);
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public void write(JSONStringer jSONStringer) throws JSONException {
        jSONStringer.array();
        Iterator<JsonValue> it = iterator();
        while (it.hasNext()) {
            it.next().write(jSONStringer);
        }
        jSONStringer.endArray();
    }

    public JsonValue toJsonValue() {
        return JsonValue.wrap((JsonSerializable) this);
    }
}
