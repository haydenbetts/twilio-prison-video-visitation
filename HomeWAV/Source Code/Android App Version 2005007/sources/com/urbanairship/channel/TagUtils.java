package com.urbanairship.channel;

import com.urbanairship.Logger;
import com.urbanairship.json.JsonValue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class TagUtils {
    private static final int MAX_TAG_LENGTH = 127;

    TagUtils() {
    }

    static Set<String> normalizeTags(Set<String> set) {
        HashSet hashSet = new HashSet();
        for (String next : set) {
            if (next == null) {
                Logger.debug("Null tag was removed from set.", new Object[0]);
            } else {
                String trim = next.trim();
                if (trim.length() <= 0 || trim.length() > 127) {
                    Logger.error("Tag with zero or greater than max length was removed from set: %s", trim);
                } else {
                    hashSet.add(trim);
                }
            }
        }
        return hashSet;
    }

    static Map<String, Set<String>> convertToTagsMap(JsonValue jsonValue) {
        if (jsonValue == null || jsonValue.isNull()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        if (jsonValue.isJsonMap()) {
            Iterator<Map.Entry<String, JsonValue>> it = jsonValue.optMap().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                HashSet hashSet = new HashSet();
                Iterator<JsonValue> it2 = ((JsonValue) next.getValue()).optList().iterator();
                while (it2.hasNext()) {
                    JsonValue next2 = it2.next();
                    if (next2.isString()) {
                        hashSet.add(next2.getString());
                    }
                }
                hashMap.put(next.getKey(), hashSet);
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return hashMap;
    }
}
