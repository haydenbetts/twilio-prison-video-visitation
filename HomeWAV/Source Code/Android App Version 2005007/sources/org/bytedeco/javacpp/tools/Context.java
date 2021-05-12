package org.bytedeco.javacpp.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Context {
    String baseType;
    String constBaseName;
    String constName;
    String cppName;
    boolean inaccessible;
    InfoMap infoMap;
    String javaName;
    String namespace;
    Map<String, String> namespaceMap;
    boolean objectify;
    TemplateMap templateMap;
    List<String> usingList;
    Declarator variable;
    boolean virtualize;

    Context() {
        this.namespace = null;
        this.baseType = null;
        this.cppName = null;
        this.javaName = null;
        this.constName = null;
        this.constBaseName = null;
        this.inaccessible = false;
        this.objectify = false;
        this.virtualize = false;
        this.variable = null;
        this.infoMap = null;
        this.templateMap = null;
        this.usingList = null;
        this.namespaceMap = null;
        this.usingList = new ArrayList();
        this.namespaceMap = new HashMap();
    }

    Context(Context context) {
        this.namespace = null;
        this.baseType = null;
        this.cppName = null;
        this.javaName = null;
        this.constName = null;
        this.constBaseName = null;
        this.inaccessible = false;
        this.objectify = false;
        this.virtualize = false;
        this.variable = null;
        this.infoMap = null;
        this.templateMap = null;
        this.usingList = null;
        this.namespaceMap = null;
        this.namespace = context.namespace;
        this.baseType = context.baseType;
        this.cppName = context.cppName;
        this.javaName = context.javaName;
        this.constName = context.constName;
        this.constBaseName = context.constBaseName;
        this.inaccessible = context.inaccessible;
        this.objectify = context.objectify;
        this.virtualize = context.virtualize;
        this.variable = context.variable;
        this.infoMap = context.infoMap;
        this.templateMap = context.templateMap;
        this.usingList = context.usingList;
        this.namespaceMap = context.namespaceMap;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006c, code lost:
        if (r5 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006f, code lost:
        r5 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0070, code lost:
        if (r5 == null) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0076, code lost:
        if (r5.length() <= 0) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0078, code lost:
        r7 = r5 + "::" + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008b, code lost:
        r7 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008c, code lost:
        r8 = r13.templateMap;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008e, code lost:
        if (r8 == null) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0098, code lost:
        if (r7.equals(r8.getName()) == false) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009a, code lost:
        r8 = r8.values().iterator();
        r9 = "<";
        r10 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a9, code lost:
        if (r8.hasNext() == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ab, code lost:
        r11 = (org.bytedeco.javacpp.tools.Type) r8.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b1, code lost:
        if (r11 == null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b3, code lost:
        r9 = r9 + r10 + r11.cppName;
        r10 = ",";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ca, code lost:
        r8 = new java.lang.StringBuilder();
        r8.append(r7);
        r8.append(r9);
        r10 = ">";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00db, code lost:
        if (r9.endsWith(r10) == false) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00dd, code lost:
        r10 = " >";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00df, code lost:
        r8.append(r10);
        r2.add(r8.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ea, code lost:
        r8 = r8.parent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ed, code lost:
        r2.add(r7);
        r7 = r13.usingList.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00fa, code lost:
        if (r7.hasNext() == false) goto L_0x0151;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fc, code lost:
        r8 = r7.next();
        r9 = r13.infoMap.normalize(r14, false, true);
        r10 = r8.lastIndexOf("::") + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0111, code lost:
        if (r5.length() <= 0) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0113, code lost:
        r11 = r5 + "::" + r8.substring(0, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x012a, code lost:
        r11 = r8.substring(0, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x012e, code lost:
        r8 = r8.substring(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0136, code lost:
        if (r8.length() == 0) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x013c, code lost:
        if (r9.equals(r8) == false) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x013e, code lost:
        r2.add(r11 + r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0151, code lost:
        r5 = r13.infoMap.normalize(r5, false, true);
        r7 = r5.lastIndexOf("::");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x015b, code lost:
        if (r7 < 0) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x015d, code lost:
        r5 = r5.substring(0, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0167, code lost:
        if (r5.length() <= 0) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x016b, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x017a, code lost:
        return (java.lang.String[]) r2.toArray(new java.lang.String[r2.size()]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String[] qualify(java.lang.String r14) {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L_0x017b
            int r1 = r14.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x017b
        L_0x000b:
            java.lang.String r1 = "::"
            boolean r2 = r14.startsWith(r1)
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x001e
            java.lang.String[] r1 = new java.lang.String[r4]
            java.lang.String r14 = r14.substring(r3)
            r1[r0] = r14
            return r1
        L_0x001e:
            java.util.Map<java.lang.String, java.lang.String> r2 = r13.namespaceMap
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0028:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0063
            java.lang.Object r5 = r2.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.Object r7 = r5.getKey()
            java.lang.String r7 = (java.lang.String) r7
            r6.append(r7)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            r7.append(r5)
            r7.append(r1)
            java.lang.String r5 = r7.toString()
            java.lang.String r14 = r14.replaceAll(r6, r5)
            goto L_0x0028
        L_0x0063:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r5 = r13.namespace
            java.lang.String r6 = ""
            if (r5 == 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r5 = r6
        L_0x0070:
            if (r5 == 0) goto L_0x016e
            int r7 = r5.length()
            if (r7 <= 0) goto L_0x008b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r5)
            r7.append(r1)
            r7.append(r14)
            java.lang.String r7 = r7.toString()
            goto L_0x008c
        L_0x008b:
            r7 = r14
        L_0x008c:
            org.bytedeco.javacpp.tools.TemplateMap r8 = r13.templateMap
        L_0x008e:
            if (r8 == 0) goto L_0x00ed
            java.lang.String r9 = r8.getName()
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x00ea
            java.util.Collection r8 = r8.values()
            java.util.Iterator r8 = r8.iterator()
            java.lang.String r9 = "<"
            r10 = r6
        L_0x00a5:
            boolean r11 = r8.hasNext()
            if (r11 == 0) goto L_0x00ca
            java.lang.Object r11 = r8.next()
            org.bytedeco.javacpp.tools.Type r11 = (org.bytedeco.javacpp.tools.Type) r11
            if (r11 == 0) goto L_0x00a5
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r9)
            r12.append(r10)
            java.lang.String r9 = r11.cppName
            r12.append(r9)
            java.lang.String r9 = r12.toString()
            java.lang.String r10 = ","
            goto L_0x00a5
        L_0x00ca:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r7)
            r8.append(r9)
            java.lang.String r10 = ">"
            boolean r9 = r9.endsWith(r10)
            if (r9 == 0) goto L_0x00df
            java.lang.String r10 = " >"
        L_0x00df:
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            r2.add(r8)
            goto L_0x00ed
        L_0x00ea:
            org.bytedeco.javacpp.tools.TemplateMap r8 = r8.parent
            goto L_0x008e
        L_0x00ed:
            r2.add(r7)
            java.util.List<java.lang.String> r7 = r13.usingList
            java.util.Iterator r7 = r7.iterator()
        L_0x00f6:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0151
            java.lang.Object r8 = r7.next()
            java.lang.String r8 = (java.lang.String) r8
            org.bytedeco.javacpp.tools.InfoMap r9 = r13.infoMap
            java.lang.String r9 = r9.normalize(r14, r0, r4)
            int r10 = r8.lastIndexOf(r1)
            int r10 = r10 + r3
            int r11 = r5.length()
            if (r11 <= 0) goto L_0x012a
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r5)
            r11.append(r1)
            java.lang.String r12 = r8.substring(r0, r10)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            goto L_0x012e
        L_0x012a:
            java.lang.String r11 = r8.substring(r0, r10)
        L_0x012e:
            java.lang.String r8 = r8.substring(r10)
            int r10 = r8.length()
            if (r10 == 0) goto L_0x013e
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x00f6
        L_0x013e:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r11)
            r8.append(r14)
            java.lang.String r8 = r8.toString()
            r2.add(r8)
            goto L_0x00f6
        L_0x0151:
            org.bytedeco.javacpp.tools.InfoMap r7 = r13.infoMap
            java.lang.String r5 = r7.normalize(r5, r0, r4)
            int r7 = r5.lastIndexOf(r1)
            if (r7 < 0) goto L_0x0163
            java.lang.String r5 = r5.substring(r0, r7)
            goto L_0x0070
        L_0x0163:
            int r5 = r5.length()
            if (r5 <= 0) goto L_0x016b
            goto L_0x006f
        L_0x016b:
            r5 = 0
            goto L_0x0070
        L_0x016e:
            int r14 = r2.size()
            java.lang.String[] r14 = new java.lang.String[r14]
            java.lang.Object[] r14 = r2.toArray(r14)
            java.lang.String[] r14 = (java.lang.String[]) r14
            return r14
        L_0x017b:
            java.lang.String[] r14 = new java.lang.String[r0]
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Context.qualify(java.lang.String):java.lang.String[]");
    }

    /* access modifiers changed from: package-private */
    public String shorten(String str) {
        if (this.javaName == null) {
            return str;
        }
        String str2 = this.javaName + '.';
        int i = 0;
        int i2 = 0;
        while (i < str.length() && i < str2.length() && str.charAt(i) == str2.charAt(i)) {
            if (str.charAt(i) == '.') {
                i2 = i;
            }
            i++;
        }
        return i2 > 0 ? str.substring(i2 + 1) : str;
    }
}
