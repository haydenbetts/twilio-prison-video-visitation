package com.microsoft.appcenter.ingestion.models;

import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class CustomPropertiesLog extends AbstractLog {
    private static final String PROPERTIES = "properties";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_TYPE = "type";
    private static final String PROPERTY_TYPE_BOOLEAN = "boolean";
    private static final String PROPERTY_TYPE_CLEAR = "clear";
    private static final String PROPERTY_TYPE_DATETIME = "dateTime";
    private static final String PROPERTY_TYPE_NUMBER = "number";
    private static final String PROPERTY_TYPE_STRING = "string";
    private static final String PROPERTY_VALUE = "value";
    public static final String TYPE = "customProperties";
    private Map<String, Object> properties;

    public String getType() {
        return TYPE;
    }

    private static Map<String, Object> readProperties(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("properties");
        HashMap hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            hashMap.put(jSONObject2.getString("name"), readPropertyValue(jSONObject2));
        }
        return hashMap;
    }

    private static Object readPropertyValue(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("type");
        if (string.equals(PROPERTY_TYPE_CLEAR)) {
            return null;
        }
        if (string.equals("boolean")) {
            return Boolean.valueOf(jSONObject.getBoolean("value"));
        }
        if (string.equals(PROPERTY_TYPE_NUMBER)) {
            Object obj = jSONObject.get("value");
            if (obj instanceof Number) {
                return obj;
            }
            throw new JSONException("Invalid value type");
        } else if (string.equals("dateTime")) {
            return JSONDateUtils.toDate(jSONObject.getString("value"));
        } else {
            if (string.equals("string")) {
                return jSONObject.getString("value");
            }
            throw new JSONException("Invalid value type");
        }
    }

    private static void writeProperties(JSONStringer jSONStringer, Map<String, Object> map) throws JSONException {
        if (map != null) {
            jSONStringer.key("properties").array();
            for (Map.Entry next : map.entrySet()) {
                jSONStringer.object();
                JSONUtils.write(jSONStringer, "name", next.getKey());
                writePropertyValue(jSONStringer, next.getValue());
                jSONStringer.endObject();
            }
            jSONStringer.endArray();
            return;
        }
        throw new JSONException("Properties cannot be null");
    }

    private static void writePropertyValue(JSONStringer jSONStringer, Object obj) throws JSONException {
        if (obj == null) {
            JSONUtils.write(jSONStringer, "type", PROPERTY_TYPE_CLEAR);
        } else if (obj instanceof Boolean) {
            JSONUtils.write(jSONStringer, "type", "boolean");
            JSONUtils.write(jSONStringer, "value", obj);
        } else if (obj instanceof Number) {
            JSONUtils.write(jSONStringer, "type", PROPERTY_TYPE_NUMBER);
            JSONUtils.write(jSONStringer, "value", obj);
        } else if (obj instanceof Date) {
            JSONUtils.write(jSONStringer, "type", "dateTime");
            JSONUtils.write(jSONStringer, "value", JSONDateUtils.toString((Date) obj));
        } else if (obj instanceof String) {
            JSONUtils.write(jSONStringer, "type", "string");
            JSONUtils.write(jSONStringer, "value", obj);
        } else {
            throw new JSONException("Invalid value type");
        }
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, Object> map) {
        this.properties = map;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setProperties(readProperties(jSONObject));
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        writeProperties(jSONStringer, getProperties());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        Map<String, Object> map = this.properties;
        Map<String, Object> map2 = ((CustomPropertiesLog) obj).properties;
        if (map != null) {
            return map.equals(map2);
        }
        if (map2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        Map<String, Object> map = this.properties;
        return hashCode + (map != null ? map.hashCode() : 0);
    }
}
