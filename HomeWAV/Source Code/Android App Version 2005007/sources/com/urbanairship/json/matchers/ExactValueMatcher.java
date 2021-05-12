package com.urbanairship.json.matchers;

import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.json.ValueMatcher;

public class ExactValueMatcher extends ValueMatcher {
    public static final String EQUALS_VALUE_KEY = "equals";
    private final JsonValue expected;

    public ExactValueMatcher(JsonValue jsonValue) {
        this.expected = jsonValue;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(EQUALS_VALUE_KEY, this.expected).build().toJsonValue();
    }

    /* access modifiers changed from: protected */
    public boolean apply(JsonValue jsonValue, boolean z) {
        return isEquals(this.expected, jsonValue, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEquals(com.urbanairship.json.JsonValue r6, com.urbanairship.json.JsonValue r7, boolean r8) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0004
            com.urbanairship.json.JsonValue r6 = com.urbanairship.json.JsonValue.NULL
        L_0x0004:
            if (r7 != 0) goto L_0x0008
            com.urbanairship.json.JsonValue r7 = com.urbanairship.json.JsonValue.NULL
        L_0x0008:
            if (r8 != 0) goto L_0x000f
            boolean r6 = r6.equals(r7)
            return r6
        L_0x000f:
            boolean r0 = r6.isString()
            r1 = 0
            if (r0 == 0) goto L_0x002a
            boolean r8 = r7.isString()
            if (r8 != 0) goto L_0x001d
            return r1
        L_0x001d:
            java.lang.String r6 = r6.optString()
            java.lang.String r7 = r7.getString()
            boolean r6 = r6.equalsIgnoreCase(r7)
            return r6
        L_0x002a:
            boolean r0 = r6.isJsonList()
            r2 = 1
            if (r0 == 0) goto L_0x0065
            boolean r0 = r7.isJsonList()
            if (r0 != 0) goto L_0x0038
            return r1
        L_0x0038:
            com.urbanairship.json.JsonList r6 = r6.optList()
            com.urbanairship.json.JsonList r7 = r7.optList()
            int r0 = r6.size()
            int r3 = r7.size()
            if (r0 == r3) goto L_0x004b
            return r1
        L_0x004b:
            r0 = 0
        L_0x004c:
            int r3 = r6.size()
            if (r0 >= r3) goto L_0x0064
            com.urbanairship.json.JsonValue r3 = r6.get(r0)
            com.urbanairship.json.JsonValue r4 = r7.get(r0)
            boolean r3 = r5.isEquals(r3, r4, r8)
            if (r3 != 0) goto L_0x0061
            return r1
        L_0x0061:
            int r0 = r0 + 1
            goto L_0x004c
        L_0x0064:
            return r2
        L_0x0065:
            boolean r0 = r6.isJsonMap()
            if (r0 == 0) goto L_0x00ba
            boolean r0 = r7.isJsonMap()
            if (r0 != 0) goto L_0x0072
            return r1
        L_0x0072:
            com.urbanairship.json.JsonMap r6 = r6.optMap()
            com.urbanairship.json.JsonMap r7 = r7.optMap()
            int r0 = r6.size()
            int r3 = r7.size()
            if (r0 == r3) goto L_0x0085
            return r1
        L_0x0085:
            java.util.Iterator r6 = r6.iterator()
        L_0x0089:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x00b9
            java.lang.Object r0 = r6.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r3 = r0.getKey()
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r7.containsKey(r3)
            if (r3 != 0) goto L_0x00a2
            return r1
        L_0x00a2:
            java.lang.Object r3 = r0.getKey()
            java.lang.String r3 = (java.lang.String) r3
            com.urbanairship.json.JsonValue r3 = r7.get(r3)
            java.lang.Object r0 = r0.getValue()
            com.urbanairship.json.JsonValue r0 = (com.urbanairship.json.JsonValue) r0
            boolean r0 = r5.isEquals(r3, r0, r8)
            if (r0 != 0) goto L_0x0089
            return r1
        L_0x00b9:
            return r2
        L_0x00ba:
            boolean r6 = r6.equals(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.json.matchers.ExactValueMatcher.isEquals(com.urbanairship.json.JsonValue, com.urbanairship.json.JsonValue, boolean):boolean");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.expected.equals(((ExactValueMatcher) obj).expected);
    }

    public int hashCode() {
        return this.expected.hashCode();
    }
}
