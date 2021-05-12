package org.bytedeco.javacpp.tools;

import java.util.LinkedHashMap;

class TemplateMap extends LinkedHashMap<String, Type> {
    Declarator declarator = null;
    TemplateMap parent = null;
    Type type = null;
    boolean variadic = false;

    TemplateMap(TemplateMap templateMap) {
        this.parent = templateMap;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        Type type2 = this.type;
        if (type2 != null) {
            return type2.cppName;
        }
        Declarator declarator2 = this.declarator;
        if (declarator2 != null) {
            return declarator2.cppName;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean empty() {
        for (Type type2 : values()) {
            if (type2 != null) {
                return false;
            }
        }
        return !isEmpty();
    }

    /* access modifiers changed from: package-private */
    public boolean full() {
        for (Type type2 : values()) {
            if (type2 == null) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r1 = r2.parent;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.Type get(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.Object r0 = super.get(r3)
            org.bytedeco.javacpp.tools.Type r0 = (org.bytedeco.javacpp.tools.Type) r0
            if (r0 != 0) goto L_0x0011
            org.bytedeco.javacpp.tools.TemplateMap r1 = r2.parent
            if (r1 == 0) goto L_0x0011
            org.bytedeco.javacpp.tools.Type r3 = r1.get(r3)
            return r3
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.TemplateMap.get(java.lang.String):org.bytedeco.javacpp.tools.Type");
    }
}
