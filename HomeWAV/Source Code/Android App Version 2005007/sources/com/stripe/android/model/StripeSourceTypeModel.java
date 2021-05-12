package com.stripe.android.model;

import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class StripeSourceTypeModel extends StripeJsonModel {
    private static final String NULL = "null";
    private final Map<String, Object> mAdditionalFields;

    StripeSourceTypeModel(BaseBuilder baseBuilder) {
        this.mAdditionalFields = baseBuilder.mAdditionalFields != null ? baseBuilder.mAdditionalFields : new HashMap<>();
    }

    public Map<String, Object> getAdditionalFields() {
        return this.mAdditionalFields;
    }

    public Map<String, Object> toMap() {
        return new HashMap(this.mAdditionalFields);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        putAdditionalFieldsIntoJsonObject(jSONObject, this.mAdditionalFields);
        return jSONObject;
    }

    static Map<String, Object> jsonObjectToMapWithoutKeys(JSONObject jSONObject, Set<String> set) {
        if (jSONObject == null) {
            return null;
        }
        if (set == null) {
            set = new HashSet<>();
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (!NULL.equals(opt) && opt != null && !set.contains(next)) {
                hashMap.put(next, opt);
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return hashMap;
    }

    static void putAdditionalFieldsIntoJsonObject(JSONObject jSONObject, Map<String, Object> map) {
        if (jSONObject != null && map != null && !map.isEmpty()) {
            for (String next : map.keySet()) {
                try {
                    if (map.get(next) != null) {
                        jSONObject.put(next, map.get(next));
                    }
                } catch (JSONException unused) {
                }
            }
        }
    }

    static void putAdditionalFieldsIntoMap(Map<String, Object> map, Map<String, Object> map2) {
        if (map != null && map2 != null && !map2.isEmpty()) {
            map.putAll(map2);
        }
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof StripeSourceTypeModel) && typedEquals((StripeSourceTypeModel) obj));
    }

    /* access modifiers changed from: package-private */
    public boolean typedEquals(StripeSourceTypeModel stripeSourceTypeModel) {
        return ObjectUtils.equals(this.mAdditionalFields, stripeSourceTypeModel.mAdditionalFields);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mAdditionalFields);
    }

    static abstract class BaseBuilder {
        /* access modifiers changed from: private */
        public Map<String, Object> mAdditionalFields;

        BaseBuilder() {
        }

        /* access modifiers changed from: package-private */
        public BaseBuilder setAdditionalFields(Map<String, Object> map) {
            this.mAdditionalFields = map;
            return this;
        }
    }
}
