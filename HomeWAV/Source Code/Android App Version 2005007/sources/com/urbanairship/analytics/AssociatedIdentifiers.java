package com.urbanairship.analytics;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AssociatedIdentifiers implements JsonSerializable {
    private static final String ADVERTISING_ID_KEY = "com.urbanairship.aaid";
    private static final String LIMITED_AD_TRACKING_ENABLED_KEY = "com.urbanairship.limited_ad_tracking_enabled";
    public static final int MAX_CHARACTER_COUNT = 255;
    public static final int MAX_IDS = 100;
    private final Map<String, String> ids;

    AssociatedIdentifiers() {
        this.ids = new HashMap();
    }

    AssociatedIdentifiers(Map<String, String> map) {
        this.ids = new HashMap(map);
    }

    public Map<String, String> getIds() {
        return Collections.unmodifiableMap(this.ids);
    }

    public String getAdvertisingId() {
        return this.ids.get(ADVERTISING_ID_KEY);
    }

    public boolean isLimitAdTrackingEnabled() {
        String str = this.ids.get(LIMITED_AD_TRACKING_ENABLED_KEY);
        return str != null && str.equalsIgnoreCase("true");
    }

    public JsonValue toJsonValue() {
        return JsonValue.wrapOpt(this.ids);
    }

    public static AssociatedIdentifiers fromJson(JsonValue jsonValue) throws JsonException {
        HashMap hashMap = new HashMap();
        if (jsonValue.isJsonMap()) {
            Iterator<Map.Entry<String, JsonValue>> it = jsonValue.optMap().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                hashMap.put(next.getKey(), ((JsonValue) next.getValue()).optString());
            }
            return new AssociatedIdentifiers(hashMap);
        }
        throw new JsonException("Associated identifiers not found in JsonValue: " + jsonValue);
    }

    public static abstract class Editor {
        private boolean clear = false;
        private final Map<String, String> idsToAdd = new HashMap();
        private final List<String> idsToRemove = new ArrayList();

        /* access modifiers changed from: package-private */
        public abstract void onApply(boolean z, Map<String, String> map, List<String> list);

        public Editor setAdvertisingId(String str, boolean z) {
            addIdentifier(AssociatedIdentifiers.ADVERTISING_ID_KEY, str);
            addIdentifier(AssociatedIdentifiers.LIMITED_AD_TRACKING_ENABLED_KEY, z ? "true" : "false");
            return this;
        }

        public Editor removeAdvertisingId() {
            removeIdentifier(AssociatedIdentifiers.ADVERTISING_ID_KEY);
            removeIdentifier(AssociatedIdentifiers.LIMITED_AD_TRACKING_ENABLED_KEY);
            return this;
        }

        public Editor addIdentifier(String str, String str2) {
            this.idsToRemove.remove(str);
            this.idsToAdd.put(str, str2);
            return this;
        }

        public Editor removeIdentifier(String str) {
            this.idsToAdd.remove(str);
            this.idsToRemove.add(str);
            return this;
        }

        public Editor clear() {
            this.clear = true;
            return this;
        }

        public void apply() {
            onApply(this.clear, this.idsToAdd, this.idsToRemove);
        }
    }
}
