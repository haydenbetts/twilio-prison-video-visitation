package com.urbanairship.json;

import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.Logger;
import com.urbanairship.util.UAStringUtil;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

public class JsonValue implements Parcelable, JsonSerializable {
    public static final Parcelable.Creator<JsonValue> CREATOR = new Parcelable.Creator<JsonValue>() {
        public JsonValue createFromParcel(Parcel parcel) {
            try {
                return JsonValue.parseString(parcel.readString());
            } catch (JsonException e) {
                Logger.error(e, "JsonValue - Unable to create JsonValue from parcel.", new Object[0]);
                return JsonValue.NULL;
            }
        }

        public JsonValue[] newArray(int i) {
            return new JsonValue[i];
        }
    };
    public static final JsonValue NULL = new JsonValue((Object) null);
    private final Object value;

    public int describeContents() {
        return 0;
    }

    public JsonValue toJsonValue() {
        return this;
    }

    private JsonValue(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public String getString() {
        if (this.value != null && isString()) {
            return (String) this.value;
        }
        return null;
    }

    public String getString(String str) {
        String string = getString();
        return string == null ? str : string;
    }

    public String optString() {
        return getString("");
    }

    public int getInt(int i) {
        if (this.value == null) {
            return i;
        }
        if (isInteger()) {
            return ((Integer) this.value).intValue();
        }
        return isNumber() ? ((Number) this.value).intValue() : i;
    }

    public float getFloat(float f) {
        if (this.value == null) {
            return f;
        }
        if (isFloat()) {
            return ((Float) this.value).floatValue();
        }
        return isNumber() ? ((Number) this.value).floatValue() : f;
    }

    public double getDouble(double d) {
        if (this.value == null) {
            return d;
        }
        if (isDouble()) {
            return ((Double) this.value).doubleValue();
        }
        return isNumber() ? ((Number) this.value).doubleValue() : d;
    }

    public long getLong(long j) {
        if (this.value == null) {
            return j;
        }
        if (isLong()) {
            return ((Long) this.value).longValue();
        }
        return isNumber() ? ((Number) this.value).longValue() : j;
    }

    public Number getNumber() {
        if (this.value != null && isNumber()) {
            return (Number) this.value;
        }
        return null;
    }

    public boolean getBoolean(boolean z) {
        return (this.value != null && isBoolean()) ? ((Boolean) this.value).booleanValue() : z;
    }

    public JsonList getList() {
        if (this.value != null && isJsonList()) {
            return (JsonList) this.value;
        }
        return null;
    }

    public JsonList optList() {
        JsonList list = getList();
        return list == null ? JsonList.EMPTY_LIST : list;
    }

    public JsonMap getMap() {
        if (this.value != null && isJsonMap()) {
            return (JsonMap) this.value;
        }
        return null;
    }

    public JsonMap optMap() {
        JsonMap map = getMap();
        return map == null ? JsonMap.EMPTY_MAP : map;
    }

    public boolean isNull() {
        return this.value == null;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public boolean isInteger() {
        return this.value instanceof Integer;
    }

    public boolean isDouble() {
        return this.value instanceof Double;
    }

    public boolean isFloat() {
        return this.value instanceof Float;
    }

    public boolean isLong() {
        return this.value instanceof Long;
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    public boolean isJsonMap() {
        return this.value instanceof JsonMap;
    }

    public boolean isJsonList() {
        return this.value instanceof JsonList;
    }

    public static JsonValue parseString(String str) throws JsonException {
        if (UAStringUtil.isEmpty(str)) {
            return NULL;
        }
        try {
            return wrap(new JSONTokener(str).nextValue());
        } catch (JSONException e) {
            throw new JsonException("Unable to parse string", e);
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JsonValue)) {
            return false;
        }
        JsonValue jsonValue = (JsonValue) obj;
        if (this.value == null) {
            return jsonValue.isNull();
        }
        if (!isNumber() || !jsonValue.isNumber()) {
            return this.value.equals(jsonValue.value);
        }
        if (isDouble() || jsonValue.isDouble()) {
            if (Double.compare(getDouble(0.0d), jsonValue.getDouble(0.0d)) == 0) {
                return true;
            }
            return false;
        } else if (isFloat() || jsonValue.isFloat()) {
            if (Float.compare(getFloat(0.0f), jsonValue.getFloat(0.0f)) == 0) {
                return true;
            }
            return false;
        } else if (getLong(0) == jsonValue.getLong(0)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        Object obj = this.value;
        if (obj != null) {
            return 527 + obj.hashCode();
        }
        return 17;
    }

    public String toString() {
        if (isNull()) {
            return "null";
        }
        try {
            Object obj = this.value;
            if (obj instanceof String) {
                return JSONObject.quote((String) obj);
            }
            if (obj instanceof Number) {
                return JSONObject.numberToString((Number) obj);
            }
            if (!(obj instanceof JsonMap)) {
                if (!(obj instanceof JsonList)) {
                    return String.valueOf(obj);
                }
            }
            return obj.toString();
        } catch (JSONException e) {
            Logger.error(e, "JsonValue - Failed to create JSON String.", new Object[0]);
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public void write(JSONStringer jSONStringer) throws JSONException {
        if (isNull()) {
            jSONStringer.value((Object) null);
            return;
        }
        Object obj = this.value;
        if (obj instanceof JsonList) {
            ((JsonList) obj).write(jSONStringer);
        } else if (obj instanceof JsonMap) {
            ((JsonMap) obj).write(jSONStringer);
        } else {
            jSONStringer.value(obj);
        }
    }

    public static JsonValue wrap(String str) {
        return wrapOpt(str);
    }

    public static JsonValue wrap(char c) {
        return wrapOpt(Character.valueOf(c));
    }

    public static JsonValue wrap(int i) {
        return wrapOpt(Integer.valueOf(i));
    }

    public static JsonValue wrap(long j) {
        return wrapOpt(Long.valueOf(j));
    }

    public static JsonValue wrap(boolean z) {
        return wrapOpt(Boolean.valueOf(z));
    }

    public static JsonValue wrap(double d) {
        Double valueOf = Double.valueOf(d);
        if (valueOf.isInfinite() || valueOf.isNaN()) {
            return NULL;
        }
        return wrapOpt(Double.valueOf(d));
    }

    public static JsonValue wrap(JsonSerializable jsonSerializable) {
        return wrapOpt(jsonSerializable);
    }

    public static JsonValue wrapOpt(Object obj) {
        return wrap(obj, NULL);
    }

    public static JsonValue wrap(Object obj, JsonValue jsonValue) {
        try {
            return wrap(obj);
        } catch (JsonException unused) {
            return jsonValue;
        }
    }

    public static JsonValue wrap(Object obj) throws JsonException {
        if (obj == null || obj == JSONObject.NULL) {
            return NULL;
        }
        if (obj instanceof JsonValue) {
            return (JsonValue) obj;
        }
        if ((obj instanceof JsonMap) || (obj instanceof JsonList) || (obj instanceof Boolean) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof String)) {
            return new JsonValue(obj);
        }
        if (obj instanceof JsonSerializable) {
            return ((JsonSerializable) obj).toJsonValue();
        }
        if ((obj instanceof Byte) || (obj instanceof Short)) {
            return new JsonValue(Integer.valueOf(((Number) obj).intValue()));
        }
        if (obj instanceof Character) {
            return new JsonValue(((Character) obj).toString());
        }
        if (obj instanceof Float) {
            return new JsonValue(Double.valueOf(((Number) obj).doubleValue()));
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (!d.isInfinite() && !d.isNaN()) {
                return new JsonValue(obj);
            }
            throw new JsonException("Invalid Double value: " + d);
        }
        try {
            if (obj instanceof JSONArray) {
                return wrapJSONArray((JSONArray) obj);
            }
            if (obj instanceof JSONObject) {
                return wrapJSONObject((JSONObject) obj);
            }
            if (obj instanceof Collection) {
                return wrapCollection((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return wrapArray(obj);
            }
            if (obj instanceof Map) {
                return wrapMap((Map) obj);
            }
            throw new JsonException("Illegal object: " + obj);
        } catch (JsonException e) {
            throw e;
        } catch (Exception e2) {
            throw new JsonException("Failed to wrap value.", e2);
        }
    }

    private static JsonValue wrapArray(Object obj) throws JsonException {
        int length = Array.getLength(obj);
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                arrayList.add(wrap(obj2));
            }
        }
        return new JsonValue(new JsonList(arrayList));
    }

    private static JsonValue wrapCollection(Collection collection) throws JsonException {
        ArrayList arrayList = new ArrayList();
        for (Object next : collection) {
            if (next != null) {
                arrayList.add(wrap(next));
            }
        }
        return new JsonValue(new JsonList(arrayList));
    }

    private static JsonValue wrapMap(Map<?, ?> map) throws JsonException {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            if (!(next.getKey() instanceof String)) {
                throw new JsonException("Only string map keys are accepted.");
            } else if (next.getValue() != null) {
                hashMap.put((String) next.getKey(), wrap(next.getValue()));
            }
        }
        return new JsonValue(new JsonMap(hashMap));
    }

    private static JsonValue wrapJSONArray(JSONArray jSONArray) throws JsonException {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!jSONArray.isNull(i)) {
                arrayList.add(wrap(jSONArray.opt(i)));
            }
        }
        return new JsonValue(new JsonList(arrayList));
    }

    private static JsonValue wrapJSONObject(JSONObject jSONObject) throws JsonException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!jSONObject.isNull(next)) {
                hashMap.put(next, wrap(jSONObject.opt(next)));
            }
        }
        return new JsonValue(new JsonMap(hashMap));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(toString());
    }
}
