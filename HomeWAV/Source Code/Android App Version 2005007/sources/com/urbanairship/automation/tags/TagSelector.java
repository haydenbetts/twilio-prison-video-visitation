package com.urbanairship.automation.tags;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagSelector implements JsonSerializable {
    private static final String AND = "and";
    public static final Map<String, Set<String>> EMPTY_TAG_GROUPS = Collections.unmodifiableMap(new HashMap());
    private static final String GROUP = "group";
    private static final String NOT = "not";
    private static final String OR = "or";
    private static final String TAG = "tag";
    private String group;
    private List<TagSelector> selectors;
    private String tag;
    private final String type;

    private TagSelector(String str, String str2) {
        this.type = TAG;
        this.tag = str;
        this.group = str2;
    }

    private TagSelector(String str, List<TagSelector> list) {
        this.type = str;
        this.selectors = new ArrayList(list);
    }

    public static TagSelector and(List<TagSelector> list) {
        return new TagSelector("and", list);
    }

    public static TagSelector and(TagSelector... tagSelectorArr) {
        return new TagSelector("and", (List<TagSelector>) Arrays.asList(tagSelectorArr));
    }

    public static TagSelector or(List<TagSelector> list) {
        return new TagSelector("or", list);
    }

    public static TagSelector or(TagSelector... tagSelectorArr) {
        return new TagSelector("or", (List<TagSelector>) Arrays.asList(tagSelectorArr));
    }

    public static TagSelector not(TagSelector tagSelector) {
        return new TagSelector("not", (List<TagSelector>) Collections.singletonList(tagSelector));
    }

    public static TagSelector tag(String str) {
        return new TagSelector(str, (String) null);
    }

    public static TagSelector tag(String str, String str2) {
        return new TagSelector(str, str2);
    }

    public static TagSelector fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        if (optMap.containsKey(TAG)) {
            String string = optMap.opt(TAG).getString();
            if (string != null) {
                return tag(string, optMap.opt(GROUP).getString());
            }
            throw new JsonException("Tag selector expected a tag: " + optMap.opt(TAG));
        } else if (optMap.containsKey("or")) {
            JsonList list = optMap.opt("or").getList();
            if (list != null) {
                return or(parseSelectors(list));
            }
            throw new JsonException("OR selector expected array of tag selectors: " + optMap.opt("or"));
        } else if (optMap.containsKey("and")) {
            JsonList list2 = optMap.opt("and").getList();
            if (list2 != null) {
                return and(parseSelectors(list2));
            }
            throw new JsonException("AND selector expected array of tag selectors: " + optMap.opt("and"));
        } else if (optMap.containsKey("not")) {
            return not(fromJson(optMap.opt("not")));
        } else {
            throw new JsonException("Json value did not contain a valid selector: " + jsonValue);
        }
    }

    private static List<TagSelector> parseSelectors(JsonList jsonList) throws JsonException {
        ArrayList arrayList = new ArrayList();
        Iterator<JsonValue> it = jsonList.iterator();
        while (it.hasNext()) {
            arrayList.add(fromJson(it.next()));
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        throw new JsonException("Expected 1 or more selectors");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.urbanairship.json.JsonValue toJsonValue() {
        /*
            r6 = this;
            com.urbanairship.json.JsonMap$Builder r0 = com.urbanairship.json.JsonMap.newBuilder()
            java.lang.String r1 = r6.type
            int r2 = r1.hashCode()
            r3 = 3555(0xde3, float:4.982E-42)
            r4 = 0
            r5 = 1
            if (r2 == r3) goto L_0x003e
            r3 = 96727(0x179d7, float:1.35543E-40)
            if (r2 == r3) goto L_0x0034
            r3 = 109267(0x1aad3, float:1.53116E-40)
            if (r2 == r3) goto L_0x002a
            r3 = 114586(0x1bf9a, float:1.60569E-40)
            if (r2 == r3) goto L_0x0020
            goto L_0x0048
        L_0x0020:
            java.lang.String r2 = "tag"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0048
            r1 = 0
            goto L_0x0049
        L_0x002a:
            java.lang.String r2 = "not"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0048
            r1 = 1
            goto L_0x0049
        L_0x0034:
            java.lang.String r2 = "and"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0048
            r1 = 3
            goto L_0x0049
        L_0x003e:
            java.lang.String r2 = "or"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0048
            r1 = 2
            goto L_0x0049
        L_0x0048:
            r1 = -1
        L_0x0049:
            if (r1 == 0) goto L_0x0067
            if (r1 == r5) goto L_0x0059
            java.lang.String r1 = r6.type
            java.util.List<com.urbanairship.automation.tags.TagSelector> r2 = r6.selectors
            com.urbanairship.json.JsonValue r2 = com.urbanairship.json.JsonValue.wrapOpt(r2)
            r0.put((java.lang.String) r1, (com.urbanairship.json.JsonSerializable) r2)
            goto L_0x0076
        L_0x0059:
            java.lang.String r1 = r6.type
            java.util.List<com.urbanairship.automation.tags.TagSelector> r2 = r6.selectors
            java.lang.Object r2 = r2.get(r4)
            com.urbanairship.json.JsonSerializable r2 = (com.urbanairship.json.JsonSerializable) r2
            r0.put((java.lang.String) r1, (com.urbanairship.json.JsonSerializable) r2)
            goto L_0x0076
        L_0x0067:
            java.lang.String r1 = r6.type
            java.lang.String r2 = r6.tag
            com.urbanairship.json.JsonMap$Builder r1 = r0.put((java.lang.String) r1, (java.lang.String) r2)
            java.lang.String r2 = r6.group
            java.lang.String r3 = "group"
            r1.putOpt(r3, r2)
        L_0x0076:
            com.urbanairship.json.JsonMap r0 = r0.build()
            com.urbanairship.json.JsonValue r0 = r0.toJsonValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.tags.TagSelector.toJsonValue():com.urbanairship.json.JsonValue");
    }

    public boolean apply(Collection<String> collection) {
        return apply(collection, EMPTY_TAG_GROUPS);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean apply(java.util.Collection<java.lang.String> r7, java.util.Map<java.lang.String, java.util.Set<java.lang.String>> r8) {
        /*
            r6 = this;
            java.lang.String r0 = r6.type
            int r1 = r0.hashCode()
            r2 = 3555(0xde3, float:4.982E-42)
            r3 = 2
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L_0x003b
            r2 = 96727(0x179d7, float:1.35543E-40)
            if (r1 == r2) goto L_0x0031
            r2 = 109267(0x1aad3, float:1.53116E-40)
            if (r1 == r2) goto L_0x0027
            r2 = 114586(0x1bf9a, float:1.60569E-40)
            if (r1 == r2) goto L_0x001d
            goto L_0x0045
        L_0x001d:
            java.lang.String r1 = "tag"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0045
            r0 = 0
            goto L_0x0046
        L_0x0027:
            java.lang.String r1 = "not"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0045
            r0 = 1
            goto L_0x0046
        L_0x0031:
            java.lang.String r1 = "and"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0045
            r0 = 2
            goto L_0x0046
        L_0x003b:
            java.lang.String r1 = "or"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0045
            r0 = 3
            goto L_0x0046
        L_0x0045:
            r0 = -1
        L_0x0046:
            if (r0 == 0) goto L_0x008e
            if (r0 == r5) goto L_0x0080
            if (r0 == r3) goto L_0x0066
            java.util.List<com.urbanairship.automation.tags.TagSelector> r0 = r6.selectors
            java.util.Iterator r0 = r0.iterator()
        L_0x0052:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0065
            java.lang.Object r1 = r0.next()
            com.urbanairship.automation.tags.TagSelector r1 = (com.urbanairship.automation.tags.TagSelector) r1
            boolean r1 = r1.apply(r7, r8)
            if (r1 == 0) goto L_0x0052
            return r5
        L_0x0065:
            return r4
        L_0x0066:
            java.util.List<com.urbanairship.automation.tags.TagSelector> r0 = r6.selectors
            java.util.Iterator r0 = r0.iterator()
        L_0x006c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x007f
            java.lang.Object r1 = r0.next()
            com.urbanairship.automation.tags.TagSelector r1 = (com.urbanairship.automation.tags.TagSelector) r1
            boolean r1 = r1.apply(r7, r8)
            if (r1 != 0) goto L_0x006c
            return r4
        L_0x007f:
            return r5
        L_0x0080:
            java.util.List<com.urbanairship.automation.tags.TagSelector> r0 = r6.selectors
            java.lang.Object r0 = r0.get(r4)
            com.urbanairship.automation.tags.TagSelector r0 = (com.urbanairship.automation.tags.TagSelector) r0
            boolean r7 = r0.apply(r7, r8)
            r7 = r7 ^ r5
            return r7
        L_0x008e:
            java.lang.String r0 = r6.group
            if (r0 == 0) goto L_0x00a4
            java.lang.Object r7 = r8.get(r0)
            java.util.Set r7 = (java.util.Set) r7
            if (r7 == 0) goto L_0x00a3
            java.lang.String r8 = r6.tag
            boolean r7 = r7.contains(r8)
            if (r7 == 0) goto L_0x00a3
            r4 = 1
        L_0x00a3:
            return r4
        L_0x00a4:
            java.lang.String r8 = r6.tag
            boolean r7 = r7.contains(r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.automation.tags.TagSelector.apply(java.util.Collection, java.util.Map):boolean");
    }

    public boolean containsTagGroups() {
        if (this.group != null && this.tag != null) {
            return true;
        }
        List<TagSelector> list = this.selectors;
        if (list == null) {
            return false;
        }
        for (TagSelector containsTagGroups : list) {
            if (containsTagGroups.containsTagGroups()) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Set<String>> getTagGroups() {
        HashMap hashMap = new HashMap();
        if (this.group == null || this.tag == null) {
            List<TagSelector> list = this.selectors;
            if (list != null) {
                for (TagSelector tagGroups : list) {
                    TagGroupUtils.addAll(hashMap, tagGroups.getTagGroups());
                }
            }
            return hashMap;
        }
        HashSet hashSet = new HashSet();
        hashSet.add(this.tag);
        hashMap.put(this.group, hashSet);
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TagSelector tagSelector = (TagSelector) obj;
        if (!this.type.equals(tagSelector.type)) {
            return false;
        }
        String str = this.tag;
        if (str == null ? tagSelector.tag != null : !str.equals(tagSelector.tag)) {
            return false;
        }
        String str2 = this.group;
        if (str2 == null ? tagSelector.group != null : !str2.equals(tagSelector.group)) {
            return false;
        }
        List<TagSelector> list = this.selectors;
        List<TagSelector> list2 = tagSelector.selectors;
        if (list != null) {
            return list.equals(list2);
        }
        if (list2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        String str = this.tag;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.group;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        List<TagSelector> list = this.selectors;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return toJsonValue().toString();
    }
}
