package org.bytedeco.javacpp.tools;

import java.util.ArrayList;
import java.util.ListIterator;

class DeclarationList extends ArrayList<Declaration> {
    Context context = null;
    ListIterator<Info> infoIterator = null;
    InfoMap infoMap = null;
    DeclarationList inherited = null;
    String spacing = null;
    TemplateMap templateMap = null;

    DeclarationList() {
    }

    DeclarationList(DeclarationList declarationList) {
        this.inherited = declarationList;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0046, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004f, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String rescan(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = r3.spacing
            if (r0 != 0) goto L_0x0005
            return r4
        L_0x0005:
            java.util.Scanner r0 = new java.util.Scanner
            r0.<init>(r4)
            java.lang.String r4 = ""
        L_0x000c:
            boolean r1 = r0.hasNextLine()     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0040
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r1.<init>()     // Catch:{ all -> 0x0044 }
            r1.append(r4)     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = r3.spacing     // Catch:{ all -> 0x0044 }
            r1.append(r4)     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = r0.nextLine()     // Catch:{ all -> 0x0044 }
            r1.append(r4)     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = r3.spacing     // Catch:{ all -> 0x0044 }
            r2 = 10
            int r1 = r1.lastIndexOf(r2)     // Catch:{ all -> 0x0044 }
            if (r1 < 0) goto L_0x003b
            java.lang.String r2 = r3.spacing     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = r2.substring(r1)     // Catch:{ all -> 0x0044 }
            goto L_0x003d
        L_0x003b:
            java.lang.String r1 = "\n"
        L_0x003d:
            r3.spacing = r1     // Catch:{ all -> 0x0044 }
            goto L_0x000c
        L_0x0040:
            r0.close()
            return r4
        L_0x0044:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r0 = move-exception
            r4.addSuppressed(r0)
        L_0x004f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.DeclarationList.rescan(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x0191 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0150  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean add(org.bytedeco.javacpp.tools.Declaration r11) {
        /*
            r10 = this;
            org.bytedeco.javacpp.tools.TemplateMap r0 = r10.templateMap
            r1 = 0
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0066
            boolean r0 = r0.empty()
            if (r0 == 0) goto L_0x0066
            org.bytedeco.javacpp.tools.Type r0 = r11.type
            if (r0 != 0) goto L_0x0015
            org.bytedeco.javacpp.tools.Declarator r0 = r11.declarator
            if (r0 == 0) goto L_0x0066
        L_0x0015:
            java.util.ListIterator<org.bytedeco.javacpp.tools.Info> r0 = r10.infoIterator
            if (r0 != 0) goto L_0x0063
            org.bytedeco.javacpp.tools.TemplateMap r0 = r10.templateMap
            org.bytedeco.javacpp.tools.Type r4 = r11.type
            r0.type = r4
            org.bytedeco.javacpp.tools.TemplateMap r0 = r10.templateMap
            org.bytedeco.javacpp.tools.Declarator r5 = r11.declarator
            r0.declarator = r5
            org.bytedeco.javacpp.tools.InfoMap r0 = r10.infoMap
            if (r5 == 0) goto L_0x002c
            java.lang.String r4 = r5.cppName
            goto L_0x002e
        L_0x002c:
            java.lang.String r4 = r4.cppName
        L_0x002e:
            java.util.List r0 = r0.get(r4)
            java.util.Iterator r4 = r0.iterator()
            r5 = 0
        L_0x0037:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0051
            java.lang.Object r6 = r4.next()
            org.bytedeco.javacpp.tools.Info r6 = (org.bytedeco.javacpp.tools.Info) r6
            java.lang.String[] r7 = r6.javaNames
            if (r7 == 0) goto L_0x004e
            java.lang.String[] r6 = r6.javaNames
            int r6 = r6.length
            if (r6 <= 0) goto L_0x004e
            r6 = 1
            goto L_0x004f
        L_0x004e:
            r6 = 0
        L_0x004f:
            r5 = r5 | r6
            goto L_0x0037
        L_0x0051:
            boolean r4 = r11.function
            if (r4 == 0) goto L_0x0057
            if (r5 == 0) goto L_0x0063
        L_0x0057:
            int r4 = r0.size()
            if (r4 <= 0) goto L_0x0061
            java.util.ListIterator r1 = r0.listIterator()
        L_0x0061:
            r10.infoIterator = r1
        L_0x0063:
            r0 = 0
            goto L_0x0124
        L_0x0066:
            org.bytedeco.javacpp.tools.InfoMap r0 = r10.infoMap
            if (r0 == 0) goto L_0x0123
            boolean r0 = r11.incomplete
            if (r0 != 0) goto L_0x0123
            org.bytedeco.javacpp.tools.Type r0 = r11.type
            if (r0 == 0) goto L_0x0123
            org.bytedeco.javacpp.tools.Type r0 = r11.type
            java.lang.String r0 = r0.cppName
            if (r0 == 0) goto L_0x0123
            java.util.ListIterator<org.bytedeco.javacpp.tools.Info> r0 = r10.infoIterator
            if (r0 != 0) goto L_0x0123
            org.bytedeco.javacpp.tools.InfoMap r0 = r10.infoMap
            org.bytedeco.javacpp.tools.Type r4 = r11.type
            java.lang.String r4 = r4.cppName
            java.util.List r0 = r0.get(r4)
            org.bytedeco.javacpp.tools.InfoMap r4 = r10.infoMap
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "const "
            r5.append(r6)
            org.bytedeco.javacpp.tools.Type r6 = r11.type
            java.lang.String r6 = r6.cppName
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            java.util.List r4 = r4.get(r5)
            if (r0 == 0) goto L_0x0106
            if (r4 == 0) goto L_0x0106
            boolean r5 = r0.equals(r4)
            if (r5 != 0) goto L_0x0106
            java.util.Iterator r5 = r0.iterator()
        L_0x00af:
            boolean r6 = r5.hasNext()
            java.lang.String r7 = " "
            if (r6 == 0) goto L_0x00d8
            java.lang.Object r6 = r5.next()
            org.bytedeco.javacpp.tools.Info r6 = (org.bytedeco.javacpp.tools.Info) r6
            java.lang.String[] r8 = r6.pointerTypes
            if (r8 == 0) goto L_0x00af
            java.lang.String[] r8 = r6.pointerTypes
            int r8 = r8.length
            if (r8 <= 0) goto L_0x00af
            java.lang.String[] r5 = r6.pointerTypes
            r5 = r5[r3]
            java.lang.String[] r6 = r6.pointerTypes
            r6 = r6[r3]
            int r6 = r6.lastIndexOf(r7)
            int r6 = r6 + r2
            java.lang.String r5 = r5.substring(r6)
            goto L_0x00d9
        L_0x00d8:
            r5 = r1
        L_0x00d9:
            java.util.Iterator r6 = r4.iterator()
        L_0x00dd:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x0104
            java.lang.Object r8 = r6.next()
            org.bytedeco.javacpp.tools.Info r8 = (org.bytedeco.javacpp.tools.Info) r8
            java.lang.String[] r9 = r8.pointerTypes
            if (r9 == 0) goto L_0x00dd
            java.lang.String[] r9 = r8.pointerTypes
            int r9 = r9.length
            if (r9 <= 0) goto L_0x00dd
            java.lang.String[] r6 = r8.pointerTypes
            r6 = r6[r3]
            java.lang.String[] r8 = r8.pointerTypes
            r8 = r8[r3]
            int r7 = r8.lastIndexOf(r7)
            int r7 = r7 + r2
            java.lang.String r6 = r6.substring(r7)
            goto L_0x0108
        L_0x0104:
            r6 = r1
            goto L_0x0108
        L_0x0106:
            r5 = r1
            r6 = r5
        L_0x0108:
            if (r6 == 0) goto L_0x0123
            if (r5 == 0) goto L_0x0123
            boolean r5 = r6.equals(r5)
            if (r5 != 0) goto L_0x0123
            r0.addAll(r4)
            int r4 = r0.size()
            if (r4 <= 0) goto L_0x011f
            java.util.ListIterator r1 = r0.listIterator()
        L_0x011f:
            r10.infoIterator = r1
            goto L_0x0063
        L_0x0123:
            r0 = 1
        L_0x0124:
            org.bytedeco.javacpp.tools.Declarator r1 = r11.declarator
            if (r1 == 0) goto L_0x017e
            org.bytedeco.javacpp.tools.Declarator r1 = r11.declarator
            org.bytedeco.javacpp.tools.Type r1 = r1.type
            if (r1 == 0) goto L_0x017e
            org.bytedeco.javacpp.tools.InfoMap r1 = r10.infoMap
            org.bytedeco.javacpp.tools.Declarator r4 = r11.declarator
            org.bytedeco.javacpp.tools.Type r4 = r4.type
            java.lang.String r4 = r4.cppName
            org.bytedeco.javacpp.tools.Info r1 = r1.getFirst(r4)
            if (r1 == 0) goto L_0x014a
            boolean r4 = r1.skip
            if (r4 == 0) goto L_0x014a
            java.lang.String[] r4 = r1.valueTypes
            if (r4 != 0) goto L_0x014a
            java.lang.String[] r1 = r1.pointerTypes
            if (r1 != 0) goto L_0x014a
        L_0x0148:
            r0 = 0
            goto L_0x017e
        L_0x014a:
            org.bytedeco.javacpp.tools.Declarator r1 = r11.declarator
            org.bytedeco.javacpp.tools.Parameters r1 = r1.parameters
            if (r1 == 0) goto L_0x017e
            org.bytedeco.javacpp.tools.Declarator r1 = r11.declarator
            org.bytedeco.javacpp.tools.Parameters r1 = r1.parameters
            org.bytedeco.javacpp.tools.Declarator[] r1 = r1.declarators
            int r4 = r1.length
            r5 = 0
        L_0x0158:
            if (r5 >= r4) goto L_0x017e
            r6 = r1[r5]
            if (r6 == 0) goto L_0x017b
            org.bytedeco.javacpp.tools.Type r7 = r6.type
            if (r7 == 0) goto L_0x017b
            org.bytedeco.javacpp.tools.InfoMap r7 = r10.infoMap
            org.bytedeco.javacpp.tools.Type r6 = r6.type
            java.lang.String r6 = r6.cppName
            org.bytedeco.javacpp.tools.Info r6 = r7.getFirst(r6)
            if (r6 == 0) goto L_0x017b
            boolean r7 = r6.skip
            if (r7 == 0) goto L_0x017b
            java.lang.String[] r7 = r6.valueTypes
            if (r7 != 0) goto L_0x017b
            java.lang.String[] r6 = r6.pointerTypes
            if (r6 != 0) goto L_0x017b
            goto L_0x0148
        L_0x017b:
            int r5 = r5 + 1
            goto L_0x0158
        L_0x017e:
            org.bytedeco.javacpp.tools.Type r1 = r11.type
            if (r1 == 0) goto L_0x018f
            org.bytedeco.javacpp.tools.Type r1 = r11.type
            java.lang.String r1 = r1.javaName
            java.lang.String r4 = "Pointer"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x018f
            r0 = 0
        L_0x018f:
            if (r0 != 0) goto L_0x0192
            return r3
        L_0x0192:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ListIterator r1 = r0.listIterator()
            r1.add(r11)
            r1.previous()
        L_0x01a1:
            boolean r11 = r1.hasNext()
            if (r11 == 0) goto L_0x01e4
            java.lang.Object r11 = r1.next()
            org.bytedeco.javacpp.tools.Declaration r11 = (org.bytedeco.javacpp.tools.Declaration) r11
            org.bytedeco.javacpp.tools.Declarator r11 = r11.declarator
            if (r11 == 0) goto L_0x01bd
            org.bytedeco.javacpp.tools.Declaration r4 = r11.definition
            if (r4 == 0) goto L_0x01bd
            org.bytedeco.javacpp.tools.Declaration r4 = r11.definition
            r1.add(r4)
            r1.previous()
        L_0x01bd:
            if (r11 == 0) goto L_0x01a1
            org.bytedeco.javacpp.tools.Parameters r4 = r11.parameters
            if (r4 == 0) goto L_0x01a1
            org.bytedeco.javacpp.tools.Parameters r4 = r11.parameters
            org.bytedeco.javacpp.tools.Declarator[] r4 = r4.declarators
            if (r4 == 0) goto L_0x01a1
            org.bytedeco.javacpp.tools.Parameters r11 = r11.parameters
            org.bytedeco.javacpp.tools.Declarator[] r11 = r11.declarators
            int r4 = r11.length
            r5 = 0
        L_0x01cf:
            if (r5 >= r4) goto L_0x01a1
            r6 = r11[r5]
            if (r6 == 0) goto L_0x01e1
            org.bytedeco.javacpp.tools.Declaration r7 = r6.definition
            if (r7 == 0) goto L_0x01e1
            org.bytedeco.javacpp.tools.Declaration r6 = r6.definition
            r1.add(r6)
            r1.previous()
        L_0x01e1:
            int r5 = r5 + 1
            goto L_0x01cf
        L_0x01e4:
            r11 = 0
        L_0x01e5:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x02ab
            int r1 = r0.size()
            int r1 = r1 - r2
            java.lang.Object r1 = r0.remove(r1)
            org.bytedeco.javacpp.tools.Declaration r1 = (org.bytedeco.javacpp.tools.Declaration) r1
            org.bytedeco.javacpp.tools.Context r4 = r10.context
            if (r4 == 0) goto L_0x021b
            boolean r4 = r4.inaccessible
            if (r4 == 0) goto L_0x0218
            org.bytedeco.javacpp.tools.Context r4 = r10.context
            boolean r4 = r4.virtualize
            if (r4 == 0) goto L_0x0216
            org.bytedeco.javacpp.tools.Declarator r4 = r1.declarator
            if (r4 == 0) goto L_0x0216
            org.bytedeco.javacpp.tools.Declarator r4 = r1.declarator
            org.bytedeco.javacpp.tools.Type r4 = r4.type
            if (r4 == 0) goto L_0x0216
            org.bytedeco.javacpp.tools.Declarator r4 = r1.declarator
            org.bytedeco.javacpp.tools.Type r4 = r4.type
            boolean r4 = r4.virtual
            if (r4 != 0) goto L_0x0218
        L_0x0216:
            r4 = 1
            goto L_0x0219
        L_0x0218:
            r4 = 0
        L_0x0219:
            r1.inaccessible = r4
        L_0x021b:
            java.lang.String r4 = r1.text
            int r4 = r4.length()
            if (r4 != 0) goto L_0x0225
            r1.inaccessible = r2
        L_0x0225:
            java.util.ListIterator r4 = r10.listIterator()
            r5 = 0
        L_0x022a:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x026c
            java.lang.Object r6 = r4.next()
            org.bytedeco.javacpp.tools.Declaration r6 = (org.bytedeco.javacpp.tools.Declaration) r6
            if (r6 == 0) goto L_0x022a
            java.lang.String r7 = r6.signature
            if (r7 == 0) goto L_0x022a
            java.lang.String r7 = r6.signature
            int r7 = r7.length()
            if (r7 <= 0) goto L_0x022a
            java.lang.String r7 = r6.signature
            java.lang.String r8 = r1.signature
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x022a
            boolean r7 = r6.constMember
            if (r7 == 0) goto L_0x0256
            boolean r7 = r1.constMember
            if (r7 == 0) goto L_0x0266
        L_0x0256:
            boolean r7 = r6.inaccessible
            if (r7 == 0) goto L_0x025e
            boolean r7 = r1.inaccessible
            if (r7 == 0) goto L_0x0266
        L_0x025e:
            boolean r6 = r6.incomplete
            if (r6 == 0) goto L_0x026a
            boolean r6 = r1.incomplete
            if (r6 != 0) goto L_0x026a
        L_0x0266:
            r4.remove()
            goto L_0x022a
        L_0x026a:
            r5 = 1
            goto L_0x022a
        L_0x026c:
            org.bytedeco.javacpp.tools.DeclarationList r4 = r10.inherited
            if (r4 == 0) goto L_0x029b
            java.util.ListIterator r4 = r4.listIterator()
        L_0x0274:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x029b
            java.lang.Object r6 = r4.next()
            org.bytedeco.javacpp.tools.Declaration r6 = (org.bytedeco.javacpp.tools.Declaration) r6
            java.lang.String r7 = r6.signature
            int r7 = r7.length()
            if (r7 <= 0) goto L_0x0274
            java.lang.String r7 = r6.signature
            java.lang.String r8 = r1.signature
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0274
            boolean r6 = r6.incomplete
            if (r6 != 0) goto L_0x0274
            boolean r6 = r1.incomplete
            if (r6 == 0) goto L_0x0274
            r5 = 1
        L_0x029b:
            if (r5 != 0) goto L_0x01e5
            java.lang.String r11 = r1.text
            java.lang.String r11 = r10.rescan(r11)
            r1.text = r11
            super.add(r1)
            r11 = 1
            goto L_0x01e5
        L_0x02ab:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.DeclarationList.add(org.bytedeco.javacpp.tools.Declaration):boolean");
    }
}
