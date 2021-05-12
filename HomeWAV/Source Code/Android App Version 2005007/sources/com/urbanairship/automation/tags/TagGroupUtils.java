package com.urbanairship.automation.tags;

import com.urbanairship.json.JsonValue;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TagGroupUtils {
    public static Map<String, Set<String>> union(Map<String, Set<String>> map, Map<String, Set<String>> map2) {
        HashMap hashMap = new HashMap();
        addAll(hashMap, map);
        addAll(hashMap, map2);
        return hashMap;
    }

    public static void addAll(Map<String, Set<String>> map, Map<String, Set<String>> map2) {
        for (Map.Entry next : map2.entrySet()) {
            Set set = map.get(next.getKey());
            if (set == null) {
                set = new HashSet();
                map.put(next.getKey(), set);
            }
            set.addAll((Collection) next.getValue());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean containsAll(java.util.Map<java.lang.String, java.util.Set<java.lang.String>> r2, java.util.Map<java.lang.String, java.util.Set<java.lang.String>> r3) {
        /*
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0008:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x002e
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.Object r1 = r2.get(r1)
            java.util.Set r1 = (java.util.Set) r1
            if (r1 == 0) goto L_0x002c
            java.lang.Object r0 = r0.getValue()
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r1.containsAll(r0)
            if (r0 != 0) goto L_0x0008
        L_0x002c:
            r2 = 0
            return r2
        L_0x002e:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.tags.TagGroupUtils.containsAll(java.util.Map, java.util.Map):boolean");
    }

    public static Map<String, Set<String>> intersect(Map<String, Set<String>> map, Map<String, Set<String>> map2) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            Set set = map2.get(str);
            if (set != null) {
                HashSet hashSet = new HashSet(set);
                hashSet.retainAll((Collection) next.getValue());
                hashMap.put(str, hashSet);
            }
        }
        return hashMap;
    }

    public static Map<String, Set<String>> parseTags(JsonValue jsonValue) {
        HashMap hashMap = new HashMap();
        if (jsonValue == null) {
            return hashMap;
        }
        Iterator<Map.Entry<String, JsonValue>> it = jsonValue.optMap().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            Set set = (Set) hashMap.get(next.getKey());
            if (set == null) {
                set = new HashSet();
                hashMap.put(next.getKey(), set);
            }
            Iterator<JsonValue> it2 = ((JsonValue) next.getValue()).optList().iterator();
            while (it2.hasNext()) {
                JsonValue next2 = it2.next();
                if (next2.isString()) {
                    set.add(next2.getString());
                }
            }
        }
        return hashMap;
    }
}
