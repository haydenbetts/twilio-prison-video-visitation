package com.urbanairship.channel;

import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagGroupsMutation implements JsonSerializable {
    private static final String ADD_KEY = "add";
    private static final String REMOVE_KEY = "remove";
    private static final String SET_KEY = "set";
    private final Map<String, Set<String>> addTags;
    private final Map<String, Set<String>> removeTags;
    private final Map<String, Set<String>> setTags;

    private TagGroupsMutation(Map<String, Set<String>> map, Map<String, Set<String>> map2, Map<String, Set<String>> map3) {
        this.addTags = map == null ? Collections.emptyMap() : map;
        this.removeTags = map2 == null ? Collections.emptyMap() : map2;
        this.setTags = map3 == null ? Collections.emptyMap() : map3;
    }

    public static TagGroupsMutation newAddTagsMutation(String str, Set<String> set) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, new HashSet(set));
        return new TagGroupsMutation(hashMap, (Map<String, Set<String>>) null, (Map<String, Set<String>>) null);
    }

    public static TagGroupsMutation newRemoveTagsMutation(String str, Set<String> set) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, new HashSet(set));
        return new TagGroupsMutation((Map<String, Set<String>>) null, hashMap, (Map<String, Set<String>>) null);
    }

    public static TagGroupsMutation newSetTagsMutation(String str, Set<String> set) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, new HashSet(set));
        return new TagGroupsMutation((Map<String, Set<String>>) null, (Map<String, Set<String>>) null, hashMap);
    }

    public static List<TagGroupsMutation> collapseMutations(List<TagGroupsMutation> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        for (TagGroupsMutation next : list) {
            Map<String, Set<String>> map = next.addTags;
            if (map != null) {
                for (Map.Entry next2 : map.entrySet()) {
                    Set set = (Set) next2.getValue();
                    String trim = ((String) next2.getKey()).trim();
                    if (!trim.isEmpty() && set != null && !set.isEmpty()) {
                        Set set2 = (Set) hashMap3.get(trim);
                        if (set2 != null) {
                            set2.addAll(set);
                        } else {
                            Set set3 = (Set) hashMap2.get(trim);
                            if (set3 != null) {
                                set3.removeAll(set);
                                if (set3.isEmpty()) {
                                    hashMap2.remove(trim);
                                }
                            }
                            Set set4 = (Set) hashMap.get(trim);
                            if (set4 == null) {
                                set4 = new HashSet();
                                hashMap.put(trim, set4);
                            }
                            set4.addAll(set);
                        }
                    }
                }
            }
            Map<String, Set<String>> map2 = next.removeTags;
            if (map2 != null) {
                for (Map.Entry next3 : map2.entrySet()) {
                    Set set5 = (Set) next3.getValue();
                    String trim2 = ((String) next3.getKey()).trim();
                    if (!trim2.isEmpty() && set5 != null && !set5.isEmpty()) {
                        Set set6 = (Set) hashMap3.get(trim2);
                        if (set6 != null) {
                            set6.removeAll(set5);
                        } else {
                            Set set7 = (Set) hashMap.get(trim2);
                            if (set7 != null) {
                                set7.removeAll(set5);
                                if (set7.isEmpty()) {
                                    hashMap.remove(trim2);
                                }
                            }
                            Set set8 = (Set) hashMap2.get(trim2);
                            if (set8 == null) {
                                set8 = new HashSet();
                                hashMap2.put(trim2, set8);
                            }
                            set8.addAll(set5);
                        }
                    }
                }
            }
            Map<String, Set<String>> map3 = next.setTags;
            if (map3 != null) {
                for (Map.Entry next4 : map3.entrySet()) {
                    Set set9 = (Set) next4.getValue();
                    String trim3 = ((String) next4.getKey()).trim();
                    if (!trim3.isEmpty()) {
                        hashMap3.put(trim3, set9 == null ? new HashSet() : new HashSet(set9));
                        hashMap2.remove(trim3);
                        hashMap.remove(trim3);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        if (!hashMap3.isEmpty()) {
            arrayList.add(new TagGroupsMutation((Map<String, Set<String>>) null, (Map<String, Set<String>>) null, hashMap3));
        }
        if (!hashMap.isEmpty() || !hashMap2.isEmpty()) {
            arrayList.add(new TagGroupsMutation(hashMap, hashMap2, (Map<String, Set<String>>) null));
        }
        return arrayList;
    }

    public JsonValue toJsonValue() {
        JsonMap.Builder newBuilder = JsonMap.newBuilder();
        Map<String, Set<String>> map = this.addTags;
        if (map != null && !map.isEmpty()) {
            newBuilder.put(ADD_KEY, (JsonSerializable) JsonValue.wrapOpt(this.addTags));
        }
        Map<String, Set<String>> map2 = this.removeTags;
        if (map2 != null && !map2.isEmpty()) {
            newBuilder.put(REMOVE_KEY, (JsonSerializable) JsonValue.wrapOpt(this.removeTags));
        }
        Map<String, Set<String>> map3 = this.setTags;
        if (map3 != null && !map3.isEmpty()) {
            newBuilder.put(SET_KEY, (JsonSerializable) JsonValue.wrapOpt(this.setTags));
        }
        return newBuilder.build().toJsonValue();
    }

    public static TagGroupsMutation fromJsonValue(JsonValue jsonValue) {
        JsonMap optMap = jsonValue.optMap();
        return new TagGroupsMutation(TagUtils.convertToTagsMap(optMap.opt(ADD_KEY)), TagUtils.convertToTagsMap(optMap.opt(REMOVE_KEY)), TagUtils.convertToTagsMap(optMap.opt(SET_KEY)));
    }

    public static List<TagGroupsMutation> fromJsonList(JsonList jsonList) {
        ArrayList arrayList = new ArrayList();
        Iterator<JsonValue> it = jsonList.iterator();
        while (it.hasNext()) {
            arrayList.add(fromJsonValue(it.next()));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        Map<String, Set<String>> map = this.addTags;
        if (map != null && !map.isEmpty()) {
            return false;
        }
        Map<String, Set<String>> map2 = this.removeTags;
        if (map2 != null && !map2.isEmpty()) {
            return false;
        }
        Map<String, Set<String>> map3 = this.setTags;
        if (map3 == null || map3.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TagGroupsMutation tagGroupsMutation = (TagGroupsMutation) obj;
        Map<String, Set<String>> map = this.addTags;
        if (map == null ? tagGroupsMutation.addTags != null : !map.equals(tagGroupsMutation.addTags)) {
            return false;
        }
        Map<String, Set<String>> map2 = this.removeTags;
        if (map2 == null ? tagGroupsMutation.removeTags != null : !map2.equals(tagGroupsMutation.removeTags)) {
            return false;
        }
        Map<String, Set<String>> map3 = this.setTags;
        Map<String, Set<String>> map4 = tagGroupsMutation.setTags;
        if (map3 != null) {
            return map3.equals(map4);
        }
        if (map4 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Map<String, Set<String>> map = this.addTags;
        int i = 0;
        int hashCode = (map != null ? map.hashCode() : 0) * 31;
        Map<String, Set<String>> map2 = this.removeTags;
        int hashCode2 = (hashCode + (map2 != null ? map2.hashCode() : 0)) * 31;
        Map<String, Set<String>> map3 = this.setTags;
        if (map3 != null) {
            i = map3.hashCode();
        }
        return hashCode2 + i;
    }

    public void apply(Map<String, Set<String>> map) {
        Map<String, Set<String>> map2 = this.addTags;
        if (map2 != null) {
            for (Map.Entry next : map2.entrySet()) {
                Set set = map.get(next.getKey());
                if (set == null) {
                    set = new HashSet();
                    map.put(next.getKey(), set);
                }
                set.addAll((Collection) next.getValue());
            }
        }
        Map<String, Set<String>> map3 = this.removeTags;
        if (map3 != null) {
            for (Map.Entry next2 : map3.entrySet()) {
                Set set2 = map.get(next2.getKey());
                if (set2 != null) {
                    set2.removeAll((Collection) next2.getValue());
                }
            }
        }
        Map<String, Set<String>> map4 = this.setTags;
        if (map4 != null) {
            for (Map.Entry next3 : map4.entrySet()) {
                map.put(next3.getKey(), next3.getValue());
            }
        }
    }
}
