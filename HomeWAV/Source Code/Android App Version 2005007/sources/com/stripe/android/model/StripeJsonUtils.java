package com.stripe.android.model;

import com.stripe.android.StripeTextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StripeJsonUtils {
    private static final String EMPTY = "";
    private static final String NULL = "null";

    static String getString(JSONObject jSONObject, String str) throws JSONException {
        return nullIfNullOrEmpty(jSONObject.getString(str));
    }

    static Boolean optBoolean(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        return Boolean.valueOf(jSONObject.optBoolean(str));
    }

    static Integer optInteger(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        return Integer.valueOf(jSONObject.optInt(str));
    }

    static Long optLong(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str)) {
            return null;
        }
        return Long.valueOf(jSONObject.optLong(str));
    }

    public static String optString(JSONObject jSONObject, String str) {
        return nullIfNullOrEmpty(jSONObject.optString(str));
    }

    static String optCountryCode(JSONObject jSONObject, String str) {
        String nullIfNullOrEmpty = nullIfNullOrEmpty(jSONObject.optString(str));
        if (nullIfNullOrEmpty == null || nullIfNullOrEmpty.length() != 2) {
            return null;
        }
        return nullIfNullOrEmpty;
    }

    static String optCurrency(JSONObject jSONObject, String str) {
        String nullIfNullOrEmpty = nullIfNullOrEmpty(jSONObject.optString(str));
        if (nullIfNullOrEmpty == null || nullIfNullOrEmpty.length() != 3) {
            return null;
        }
        return nullIfNullOrEmpty;
    }

    static Map<String, Object> optMap(JSONObject jSONObject, String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        return jsonObjectToMap(optJSONObject);
    }

    static Map<String, String> optHash(JSONObject jSONObject, String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        return jsonObjectToStringMap(optJSONObject);
    }

    static Map<String, Object> jsonObjectToMap(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (!NULL.equals(opt) && opt != null) {
                if (opt instanceof JSONObject) {
                    hashMap.put(next, jsonObjectToMap((JSONObject) opt));
                } else if (opt instanceof JSONArray) {
                    hashMap.put(next, jsonArrayToList((JSONArray) opt));
                } else {
                    hashMap.put(next, opt);
                }
            }
        }
        return hashMap;
    }

    static Map<String, String> jsonObjectToStringMap(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (!NULL.equals(opt) && opt != null) {
                hashMap.put(next, opt.toString());
            }
        }
        return hashMap;
    }

    static List<Object> jsonArrayToList(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONArray) {
                    arrayList.add(jsonArrayToList((JSONArray) obj));
                } else if (obj instanceof JSONObject) {
                    Map<String, Object> jsonObjectToMap = jsonObjectToMap((JSONObject) obj);
                    if (jsonObjectToMap != null) {
                        arrayList.add(jsonObjectToMap);
                    }
                } else if (!NULL.equals(obj)) {
                    arrayList.add(obj);
                }
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.json.JSONObject mapToJsonObject(java.util.Map<java.lang.String, ? extends java.lang.Object> r5) {
        /*
            if (r5 != 0) goto L_0x0004
            r5 = 0
            return r5
        L_0x0004:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.util.Set r1 = r5.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0011:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r5.get(r2)
            if (r3 != 0) goto L_0x0024
            goto L_0x0011
        L_0x0024:
            boolean r4 = r3 instanceof java.util.Map     // Catch:{  }
            if (r4 == 0) goto L_0x0034
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ JSONException -> 0x0032 }
            org.json.JSONObject r3 = mapToJsonObject(r3)     // Catch:{ JSONException -> 0x0032 }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0032 }
            goto L_0x0011
        L_0x0032:
            goto L_0x0011
        L_0x0034:
            boolean r4 = r3 instanceof java.util.List     // Catch:{  }
            if (r4 == 0) goto L_0x0042
            java.util.List r3 = (java.util.List) r3     // Catch:{  }
            org.json.JSONArray r3 = listToJsonArray(r3)     // Catch:{  }
            r0.put(r2, r3)     // Catch:{  }
            goto L_0x0011
        L_0x0042:
            boolean r4 = r3 instanceof java.lang.Number     // Catch:{  }
            if (r4 != 0) goto L_0x0053
            boolean r4 = r3 instanceof java.lang.Boolean     // Catch:{  }
            if (r4 == 0) goto L_0x004b
            goto L_0x0053
        L_0x004b:
            java.lang.String r3 = r3.toString()     // Catch:{  }
            r0.put(r2, r3)     // Catch:{  }
            goto L_0x0011
        L_0x0053:
            r0.put(r2, r3)     // Catch:{  }
            goto L_0x0011
        L_0x0057:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.model.StripeJsonUtils.mapToJsonObject(java.util.Map):org.json.JSONObject");
    }

    static JSONObject stringHashToJsonObject(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String next : map.keySet()) {
            try {
                jSONObject.put(next, map.get(next));
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    static JSONArray listToJsonArray(List list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object next : list) {
            if (next instanceof Map) {
                try {
                    jSONArray.put(mapToJsonObject((Map) next));
                } catch (ClassCastException unused) {
                }
            } else if (next instanceof List) {
                jSONArray.put(listToJsonArray((List) next));
            } else if ((next instanceof Number) || (next instanceof Boolean)) {
                jSONArray.put(next);
            } else {
                jSONArray.put(next.toString());
            }
        }
        return jSONArray;
    }

    static void putStringIfNotNull(JSONObject jSONObject, String str, String str2) {
        if (!StripeTextUtils.isBlank(str2)) {
            try {
                jSONObject.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    static void putIntegerIfNotNull(JSONObject jSONObject, String str, Integer num) {
        if (num != null) {
            try {
                jSONObject.put(str, num.intValue());
            } catch (JSONException unused) {
            }
        }
    }

    static void putLongIfNotNull(JSONObject jSONObject, String str, Long l) {
        if (l != null) {
            try {
                jSONObject.put(str, l.longValue());
            } catch (JSONException unused) {
            }
        }
    }

    static void putDoubleIfNotNull(JSONObject jSONObject, String str, Double d) {
        if (d != null) {
            try {
                jSONObject.put(str, d.doubleValue());
            } catch (JSONException unused) {
            }
        }
    }

    static void putBooleanIfNotNull(JSONObject jSONObject, String str, Boolean bool) {
        if (bool != null) {
            try {
                jSONObject.put(str, bool.booleanValue());
            } catch (JSONException unused) {
            }
        }
    }

    static void putStringHashIfNotNull(JSONObject jSONObject, String str, Map<String, String> map) {
        JSONObject stringHashToJsonObject;
        if (map != null && (stringHashToJsonObject = stringHashToJsonObject(map)) != null) {
            try {
                jSONObject.put(str, stringHashToJsonObject);
            } catch (JSONException unused) {
            }
        }
    }

    static void putMapIfNotNull(JSONObject jSONObject, String str, Map<String, Object> map) {
        JSONObject mapToJsonObject;
        if (map != null && (mapToJsonObject = mapToJsonObject(map)) != null) {
            try {
                jSONObject.put(str, mapToJsonObject);
            } catch (JSONException unused) {
            }
        }
    }

    static void putObjectIfNotNull(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        if (jSONObject2 != null) {
            try {
                jSONObject.put(str, jSONObject2);
            } catch (JSONException unused) {
            }
        }
    }

    static void putArrayIfNotNull(JSONObject jSONObject, String str, JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                jSONObject.put(str, jSONArray);
            } catch (JSONException unused) {
            }
        }
    }

    static String nullIfNullOrEmpty(String str) {
        if (NULL.equals(str) || "".equals(str)) {
            return null;
        }
        return str;
    }
}
