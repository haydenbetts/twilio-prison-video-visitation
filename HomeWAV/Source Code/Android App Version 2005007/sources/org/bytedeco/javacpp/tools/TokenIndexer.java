package org.bytedeco.javacpp.tools;

class TokenIndexer {
    Token[] array = null;
    int counter = 0;
    int index = 0;
    InfoMap infoMap = null;
    final boolean isCFile;
    boolean raw = false;

    TokenIndexer(InfoMap infoMap2, Token[] tokenArr, boolean z) {
        this.infoMap = infoMap2;
        this.array = tokenArr;
        this.isCFile = z;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01b8, code lost:
        if (r10.define == false) goto L_0x01ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01bc, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01ce, code lost:
        if (java.lang.Integer.decode(r3.trim()).intValue() != 0) goto L_0x01ba;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.Token[] filter(org.bytedeco.javacpp.tools.Token[] r21, int r22) {
        /*
            r20 = this;
            r0 = r21
            r1 = r22
            int r2 = r1 + 1
            int r3 = r0.length
            if (r2 >= r3) goto L_0x0238
            r3 = r0[r1]
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r6 = 35
            java.lang.Character r7 = java.lang.Character.valueOf(r6)
            r8 = 0
            r5[r8] = r7
            boolean r3 = r3.match(r5)
            if (r3 == 0) goto L_0x0238
            r2 = r0[r2]
            r3 = 3
            java.lang.Object[] r5 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.IF
            r5[r8] = r7
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.IFDEF
            r5[r4] = r7
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.IFNDEF
            r9 = 2
            r5[r9] = r7
            boolean r2 = r2.match(r5)
            if (r2 == 0) goto L_0x0238
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r5 = 0
        L_0x003b:
            if (r5 >= r1) goto L_0x0045
            r7 = r0[r5]
            r2.add(r7)
            int r5 = r5 + 1
            goto L_0x003b
        L_0x0045:
            r7 = 0
            r10 = 1
            r11 = 0
            r12 = 0
        L_0x0049:
            int r13 = r0.length
            if (r1 >= r13) goto L_0x021e
            r13 = r0[r1]
            java.lang.String r13 = r13.spacing
            r14 = 10
            int r15 = r13.lastIndexOf(r14)
            int r15 = r15 + r4
            r5 = r0[r1]
            java.lang.Object[] r14 = new java.lang.Object[r4]
            java.lang.Character r16 = java.lang.Character.valueOf(r6)
            r14[r8] = r16
            boolean r5 = r5.match(r14)
            if (r5 == 0) goto L_0x00bf
            int r5 = r1 + 1
            r6 = r0[r5]
            java.lang.Object[] r14 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.IF
            r14[r8] = r18
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.IFDEF
            r14[r4] = r18
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.IFNDEF
            r14[r9] = r18
            boolean r6 = r6.match(r14)
            if (r6 == 0) goto L_0x0081
            int r7 = r7 + 1
        L_0x0081:
            if (r7 != r4) goto L_0x00ad
            r6 = r0[r5]
            r14 = 6
            java.lang.Object[] r14 = new java.lang.Object[r14]
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.IF
            r14[r8] = r18
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.IFDEF
            r14[r4] = r18
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.IFNDEF
            r14[r9] = r18
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.ELIF
            r14[r3] = r18
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.ELSE
            r17 = 4
            r14[r17] = r18
            r18 = 5
            org.bytedeco.javacpp.tools.Token r19 = org.bytedeco.javacpp.tools.Token.ENDIF
            r14[r18] = r19
            boolean r6 = r6.match(r14)
            if (r6 == 0) goto L_0x00ad
            r6 = r0[r5]
            goto L_0x00ae
        L_0x00ad:
            r6 = 0
        L_0x00ae:
            r5 = r0[r5]
            java.lang.Object[] r14 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.ENDIF
            r14[r8] = r18
            boolean r5 = r5.match(r14)
            if (r5 == 0) goto L_0x00c0
            int r7 = r7 + -1
            goto L_0x00c0
        L_0x00bf:
            r6 = 0
        L_0x00c0:
            if (r6 == 0) goto L_0x01ff
            int r1 = r1 + 2
            org.bytedeco.javacpp.tools.Token r5 = new org.bytedeco.javacpp.tools.Token
            r5.<init>()
            r14 = 4
            r5.type = r14
            java.lang.String r14 = r13.substring(r8, r15)
            r5.spacing = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r3 = "// "
            r14.append(r3)
            java.lang.String r3 = r13.substring(r15)
            r14.append(r3)
            java.lang.String r3 = "#"
            r14.append(r3)
            java.lang.String r3 = r6.spacing
            r14.append(r3)
            r14.append(r6)
            java.lang.String r3 = r14.toString()
            r5.value = r3
            r2.add(r5)
            java.lang.String r3 = ""
        L_0x00fb:
            int r13 = r0.length
            if (r1 >= r13) goto L_0x017b
            r13 = r0[r1]
            java.lang.String r13 = r13.spacing
            r14 = 10
            int r13 = r13.indexOf(r14)
            if (r13 < 0) goto L_0x010b
            goto L_0x017b
        L_0x010b:
            r13 = r0[r1]
            java.lang.Object[] r15 = new java.lang.Object[r4]
            r17 = 4
            java.lang.Integer r19 = java.lang.Integer.valueOf(r17)
            r15[r8] = r19
            boolean r13 = r13.match(r15)
            if (r13 != 0) goto L_0x0135
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r3)
            r3 = r0[r1]
            java.lang.String r3 = r3.spacing
            r13.append(r3)
            r3 = r0[r1]
            r13.append(r3)
            java.lang.String r3 = r13.toString()
        L_0x0135:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r15 = r5.value
            r13.append(r15)
            r15 = r0[r1]
            java.lang.Object[] r14 = new java.lang.Object[r4]
            java.lang.String r9 = "\n"
            r14[r8] = r9
            boolean r14 = r15.match(r14)
            java.lang.String r15 = "\n// "
            if (r14 == 0) goto L_0x0150
            goto L_0x016d
        L_0x0150:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r4 = r0[r1]
            java.lang.String r4 = r4.spacing
            r14.append(r4)
            r4 = r0[r1]
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r4.replaceAll(r9, r15)
            r14.append(r4)
            java.lang.String r15 = r14.toString()
        L_0x016d:
            r13.append(r15)
            java.lang.String r4 = r13.toString()
            r5.value = r4
            int r1 = r1 + 1
            r4 = 1
            r9 = 2
            goto L_0x00fb
        L_0x017b:
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.IF
            r4[r8] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.IFDEF
            r9 = 1
            r4[r9] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.IFNDEF
            r9 = 2
            r4[r9] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.ELIF
            r13 = 3
            r4[r13] = r5
            boolean r4 = r6.match(r4)
            if (r4 == 0) goto L_0x01d5
            if (r12 == 0) goto L_0x019e
            if (r11 != 0) goto L_0x019c
            goto L_0x019e
        L_0x019c:
            r4 = 0
            goto L_0x019f
        L_0x019e:
            r4 = 1
        L_0x019f:
            r5 = r20
            org.bytedeco.javacpp.tools.InfoMap r10 = r5.infoMap
            org.bytedeco.javacpp.tools.Info r10 = r10.getFirst(r3)
            if (r10 == 0) goto L_0x01c2
            r12 = 1
            java.lang.Object[] r3 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r4 = org.bytedeco.javacpp.tools.Token.IFNDEF
            r3[r8] = r4
            boolean r3 = r6.match(r3)
            if (r3 == 0) goto L_0x01be
            boolean r3 = r10.define
            if (r3 != 0) goto L_0x01bc
        L_0x01ba:
            r3 = 1
            goto L_0x01c0
        L_0x01bc:
            r3 = 0
            goto L_0x01c0
        L_0x01be:
            boolean r3 = r10.define
        L_0x01c0:
            r12 = r10
            goto L_0x01ec
        L_0x01c2:
            java.lang.String r3 = r3.trim()     // Catch:{ NumberFormatException -> 0x01d1 }
            java.lang.Integer r3 = java.lang.Integer.decode(r3)     // Catch:{ NumberFormatException -> 0x01d1 }
            int r3 = r3.intValue()     // Catch:{ NumberFormatException -> 0x01d1 }
            if (r3 == 0) goto L_0x01bc
            goto L_0x01ba
        L_0x01d1:
            r12 = r10
            r3 = 1
            r10 = r4
            goto L_0x0210
        L_0x01d5:
            r5 = r20
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r3 = org.bytedeco.javacpp.tools.Token.ELSE
            r4[r8] = r3
            boolean r3 = r6.match(r4)
            if (r3 == 0) goto L_0x01ef
            if (r12 == 0) goto L_0x01eb
            if (r10 != 0) goto L_0x01e9
            goto L_0x01eb
        L_0x01e9:
            r3 = 0
            goto L_0x01ec
        L_0x01eb:
            r3 = 1
        L_0x01ec:
            r10 = r3
            r3 = 1
            goto L_0x0210
        L_0x01ef:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r14 = org.bytedeco.javacpp.tools.Token.ENDIF
            r4[r8] = r14
            boolean r4 = r6.match(r4)
            if (r4 == 0) goto L_0x0210
            if (r7 != 0) goto L_0x0210
            goto L_0x0220
        L_0x01ff:
            r5 = r20
            r3 = 1
            r13 = 3
            if (r10 == 0) goto L_0x020e
            int r4 = r1 + 1
            r1 = r0[r1]
            r2.add(r1)
            r1 = r4
            goto L_0x0210
        L_0x020e:
            int r1 = r1 + 1
        L_0x0210:
            if (r10 != 0) goto L_0x0217
            if (r11 == 0) goto L_0x0215
            goto L_0x0217
        L_0x0215:
            r11 = 0
            goto L_0x0218
        L_0x0217:
            r11 = 1
        L_0x0218:
            r3 = 3
            r4 = 1
            r6 = 35
            goto L_0x0049
        L_0x021e:
            r5 = r20
        L_0x0220:
            int r3 = r0.length
            if (r1 >= r3) goto L_0x022b
            r3 = r0[r1]
            r2.add(r3)
            int r1 = r1 + 1
            goto L_0x0220
        L_0x022b:
            int r0 = r2.size()
            org.bytedeco.javacpp.tools.Token[] r0 = new org.bytedeco.javacpp.tools.Token[r0]
            java.lang.Object[] r0 = r2.toArray(r0)
            org.bytedeco.javacpp.tools.Token[] r0 = (org.bytedeco.javacpp.tools.Token[]) r0
            goto L_0x023a
        L_0x0238:
            r5 = r20
        L_0x023a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.TokenIndexer.filter(org.bytedeco.javacpp.tools.Token[], int):org.bytedeco.javacpp.tools.Token[]");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0132, code lost:
        if (r0[r13].match('(') == false) goto L_0x0134;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.Token[] expand(org.bytedeco.javacpp.tools.Token[] r24, int r25) {
        /*
            r23 = this;
            r1 = r23
            r0 = r24
            r2 = r25
            int r3 = r0.length
            if (r2 >= r3) goto L_0x0359
            org.bytedeco.javacpp.tools.InfoMap r3 = r1.infoMap
            r4 = r0[r2]
            java.lang.String r4 = r4.value
            boolean r3 = r3.containsKey(r4)
            if (r3 == 0) goto L_0x0359
            org.bytedeco.javacpp.tools.InfoMap r3 = r1.infoMap
            r4 = r0[r2]
            java.lang.String r4 = r4.value
            java.util.List r3 = r3.get(r4)
            java.util.Iterator r3 = r3.iterator()
            r5 = 0
        L_0x0024:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x0038
            java.lang.Object r6 = r3.next()
            org.bytedeco.javacpp.tools.Info r6 = (org.bytedeco.javacpp.tools.Info) r6
            if (r6 == 0) goto L_0x0024
            java.lang.String r7 = r6.cppText
            if (r7 == 0) goto L_0x0024
            r5 = r6
            goto L_0x0024
        L_0x0038:
            if (r5 == 0) goto L_0x0359
            java.lang.String r3 = r5.cppText
            if (r3 == 0) goto L_0x0359
            org.bytedeco.javacpp.tools.Tokenizer r3 = new org.bytedeco.javacpp.tools.Tokenizer     // Catch:{ IOException -> 0x0352 }
            java.lang.String r6 = r5.cppText     // Catch:{ IOException -> 0x0352 }
            r7 = r0[r2]     // Catch:{ IOException -> 0x0352 }
            java.io.File r7 = r7.file     // Catch:{ IOException -> 0x0352 }
            r8 = r0[r2]     // Catch:{ IOException -> 0x0352 }
            int r8 = r8.lineNumber     // Catch:{ IOException -> 0x0352 }
            r3.<init>((java.lang.String) r6, (java.io.File) r7, (int) r8)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r6 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            r7 = 1
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            r9 = 35
            java.lang.Character r9 = java.lang.Character.valueOf(r9)     // Catch:{ IOException -> 0x0352 }
            r10 = 0
            r8[r10] = r9     // Catch:{ IOException -> 0x0352 }
            boolean r6 = r6.match(r8)     // Catch:{ IOException -> 0x0352 }
            if (r6 == 0) goto L_0x0351
            org.bytedeco.javacpp.tools.Token r6 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r9 = org.bytedeco.javacpp.tools.Token.DEFINE     // Catch:{ IOException -> 0x0352 }
            r8[r10] = r9     // Catch:{ IOException -> 0x0352 }
            boolean r6 = r6.match(r8)     // Catch:{ IOException -> 0x0352 }
            if (r6 == 0) goto L_0x0351
            org.bytedeco.javacpp.tools.Token r6 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            java.lang.String[] r9 = r5.cppNames     // Catch:{ IOException -> 0x0352 }
            r9 = r9[r10]     // Catch:{ IOException -> 0x0352 }
            r8[r10] = r9     // Catch:{ IOException -> 0x0352 }
            boolean r6 = r6.match(r8)     // Catch:{ IOException -> 0x0352 }
            if (r6 != 0) goto L_0x0087
            goto L_0x0351
        L_0x0087:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ IOException -> 0x0352 }
            r6.<init>()     // Catch:{ IOException -> 0x0352 }
            r8 = 0
        L_0x008d:
            if (r8 >= r2) goto L_0x0097
            r9 = r0[r8]     // Catch:{ IOException -> 0x0352 }
            r6.add(r9)     // Catch:{ IOException -> 0x0352 }
            int r8 = r8 + 1
            goto L_0x008d
        L_0x0097:
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ IOException -> 0x0352 }
            r8.<init>()     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r9 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            java.lang.String[] r5 = r5.cppNames     // Catch:{ IOException -> 0x0352 }
            r5 = r5[r10]     // Catch:{ IOException -> 0x0352 }
            java.lang.String r11 = "__COUNTER__"
            boolean r5 = r5.equals(r11)     // Catch:{ IOException -> 0x0352 }
            if (r5 == 0) goto L_0x00b8
            int r5 = r1.counter     // Catch:{ IOException -> 0x0352 }
            int r11 = r5 + 1
            r1.counter = r11     // Catch:{ IOException -> 0x0352 }
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch:{ IOException -> 0x0352 }
            r9.value = r5     // Catch:{ IOException -> 0x0352 }
        L_0x00b8:
            r5 = r0[r2]     // Catch:{ IOException -> 0x0352 }
            java.lang.String r5 = r5.value     // Catch:{ IOException -> 0x0352 }
            java.lang.Object[] r11 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            r12 = 40
            java.lang.Character r13 = java.lang.Character.valueOf(r12)     // Catch:{ IOException -> 0x0352 }
            r11[r10] = r13     // Catch:{ IOException -> 0x0352 }
            boolean r11 = r9.match(r11)     // Catch:{ IOException -> 0x0352 }
            if (r11 == 0) goto L_0x0254
            org.bytedeco.javacpp.tools.Token r9 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            r11 = 0
        L_0x00d1:
            boolean r13 = r9.isEmpty()     // Catch:{ IOException -> 0x0352 }
            r14 = 41
            if (r13 != 0) goto L_0x0119
            java.lang.Object[] r13 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            r15 = 5
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ IOException -> 0x0352 }
            r13[r10] = r15     // Catch:{ IOException -> 0x0352 }
            boolean r13 = r9.match(r13)     // Catch:{ IOException -> 0x0352 }
            if (r13 == 0) goto L_0x00ee
            java.lang.String r9 = r9.value     // Catch:{ IOException -> 0x0352 }
            r8.add(r9)     // Catch:{ IOException -> 0x0352 }
            goto L_0x0114
        L_0x00ee:
            java.lang.Object[] r13 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            java.lang.String r15 = "..."
            r13[r10] = r15     // Catch:{ IOException -> 0x0352 }
            boolean r13 = r9.match(r13)     // Catch:{ IOException -> 0x0352 }
            if (r13 == 0) goto L_0x0101
            java.lang.String r9 = "__VA_ARGS__"
            r8.add(r9)     // Catch:{ IOException -> 0x0352 }
            r11 = 1
            goto L_0x0114
        L_0x0101:
            java.lang.Object[] r13 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            java.lang.Character r15 = java.lang.Character.valueOf(r14)     // Catch:{ IOException -> 0x0352 }
            r13[r10] = r15     // Catch:{ IOException -> 0x0352 }
            boolean r9 = r9.match(r13)     // Catch:{ IOException -> 0x0352 }
            if (r9 == 0) goto L_0x0114
            org.bytedeco.javacpp.tools.Token r9 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            goto L_0x0119
        L_0x0114:
            org.bytedeco.javacpp.tools.Token r9 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            goto L_0x00d1
        L_0x0119:
            int r13 = r2 + 1
            int r15 = r8.size()     // Catch:{ IOException -> 0x0352 }
            if (r15 <= 0) goto L_0x0135
            int r15 = r0.length     // Catch:{ IOException -> 0x0352 }
            if (r13 >= r15) goto L_0x0134
            r15 = r0[r13]     // Catch:{ IOException -> 0x0352 }
            java.lang.Object[] r4 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            java.lang.Character r16 = java.lang.Character.valueOf(r12)     // Catch:{ IOException -> 0x0352 }
            r4[r10] = r16     // Catch:{ IOException -> 0x0352 }
            boolean r4 = r15.match(r4)     // Catch:{ IOException -> 0x0352 }
            if (r4 != 0) goto L_0x0135
        L_0x0134:
            return r0
        L_0x0135:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0352 }
            r4.<init>()     // Catch:{ IOException -> 0x0352 }
            r4.append(r5)     // Catch:{ IOException -> 0x0352 }
            r5 = r0[r13]     // Catch:{ IOException -> 0x0352 }
            java.lang.String r5 = r5.spacing     // Catch:{ IOException -> 0x0352 }
            r4.append(r5)     // Catch:{ IOException -> 0x0352 }
            r5 = r0[r13]     // Catch:{ IOException -> 0x0352 }
            r4.append(r5)     // Catch:{ IOException -> 0x0352 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0352 }
            int r5 = r8.size()     // Catch:{ IOException -> 0x0352 }
            java.util.List[] r15 = new java.util.List[r5]     // Catch:{ IOException -> 0x0352 }
            int r13 = r13 + r7
            r12 = 0
            r16 = 0
        L_0x0157:
            int r10 = r0.length     // Catch:{ IOException -> 0x0352 }
            if (r13 >= r10) goto L_0x0210
            r10 = r0[r13]     // Catch:{ IOException -> 0x0352 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0352 }
            r14.<init>()     // Catch:{ IOException -> 0x0352 }
            r14.append(r4)     // Catch:{ IOException -> 0x0352 }
            java.lang.String r4 = r10.spacing     // Catch:{ IOException -> 0x0352 }
            r14.append(r4)     // Catch:{ IOException -> 0x0352 }
            r14.append(r10)     // Catch:{ IOException -> 0x0352 }
            java.lang.String r4 = r14.toString()     // Catch:{ IOException -> 0x0352 }
            if (r16 != 0) goto L_0x0186
            java.lang.Object[] r14 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            r19 = 41
            java.lang.Character r20 = java.lang.Character.valueOf(r19)     // Catch:{ IOException -> 0x0352 }
            r18 = 0
            r14[r18] = r20     // Catch:{ IOException -> 0x0352 }
            boolean r14 = r10.match(r14)     // Catch:{ IOException -> 0x0352 }
            if (r14 == 0) goto L_0x0186
            goto L_0x0210
        L_0x0186:
            if (r16 != 0) goto L_0x01a7
            java.lang.Object[] r14 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x0352 }
            r20 = 44
            java.lang.Character r20 = java.lang.Character.valueOf(r20)     // Catch:{ IOException -> 0x0352 }
            r18 = 0
            r14[r18] = r20     // Catch:{ IOException -> 0x0352 }
            boolean r14 = r10.match(r14)     // Catch:{ IOException -> 0x0352 }
            if (r14 == 0) goto L_0x01a7
            if (r11 == 0) goto L_0x01a0
            int r14 = r12 + 1
            if (r14 >= r5) goto L_0x01a7
        L_0x01a0:
            int r12 = r12 + 1
            r14 = 41
            r17 = 40
            goto L_0x020b
        L_0x01a7:
            r14 = 3
            java.lang.Object[] r7 = new java.lang.Object[r14]     // Catch:{ IOException -> 0x0352 }
            r17 = 40
            java.lang.Character r21 = java.lang.Character.valueOf(r17)     // Catch:{ IOException -> 0x0352 }
            r18 = 0
            r7[r18] = r21     // Catch:{ IOException -> 0x0352 }
            r21 = 91
            java.lang.Character r21 = java.lang.Character.valueOf(r21)     // Catch:{ IOException -> 0x0352 }
            r20 = 1
            r7[r20] = r21     // Catch:{ IOException -> 0x0352 }
            r21 = 123(0x7b, float:1.72E-43)
            java.lang.Character r21 = java.lang.Character.valueOf(r21)     // Catch:{ IOException -> 0x0352 }
            r22 = 2
            r7[r22] = r21     // Catch:{ IOException -> 0x0352 }
            boolean r7 = r10.match(r7)     // Catch:{ IOException -> 0x0352 }
            if (r7 == 0) goto L_0x01d3
            int r16 = r16 + 1
            r14 = 41
            goto L_0x01f9
        L_0x01d3:
            java.lang.Object[] r7 = new java.lang.Object[r14]     // Catch:{ IOException -> 0x0352 }
            r14 = 41
            java.lang.Character r19 = java.lang.Character.valueOf(r14)     // Catch:{ IOException -> 0x0352 }
            r18 = 0
            r7[r18] = r19     // Catch:{ IOException -> 0x0352 }
            r19 = 93
            java.lang.Character r19 = java.lang.Character.valueOf(r19)     // Catch:{ IOException -> 0x0352 }
            r20 = 1
            r7[r20] = r19     // Catch:{ IOException -> 0x0352 }
            r19 = 125(0x7d, float:1.75E-43)
            java.lang.Character r19 = java.lang.Character.valueOf(r19)     // Catch:{ IOException -> 0x0352 }
            r7[r22] = r19     // Catch:{ IOException -> 0x0352 }
            boolean r7 = r10.match(r7)     // Catch:{ IOException -> 0x0352 }
            if (r7 == 0) goto L_0x01f9
            int r16 = r16 + -1
        L_0x01f9:
            if (r12 >= r5) goto L_0x020b
            r7 = r15[r12]     // Catch:{ IOException -> 0x0352 }
            if (r7 != 0) goto L_0x0206
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ IOException -> 0x0352 }
            r7.<init>()     // Catch:{ IOException -> 0x0352 }
            r15[r12] = r7     // Catch:{ IOException -> 0x0352 }
        L_0x0206:
            r7 = r15[r12]     // Catch:{ IOException -> 0x0352 }
            r7.add(r10)     // Catch:{ IOException -> 0x0352 }
        L_0x020b:
            int r13 = r13 + 1
            r7 = 1
            goto L_0x0157
        L_0x0210:
            r7 = 0
        L_0x0211:
            if (r7 >= r5) goto L_0x0252
            r10 = r15[r7]     // Catch:{ IOException -> 0x0352 }
            if (r10 != 0) goto L_0x0221
            r10 = 0
            org.bytedeco.javacpp.tools.Token[] r11 = new org.bytedeco.javacpp.tools.Token[r10]     // Catch:{ IOException -> 0x0352 }
            java.util.List r10 = java.util.Arrays.asList(r11)     // Catch:{ IOException -> 0x0352 }
            r15[r7] = r10     // Catch:{ IOException -> 0x0352 }
            goto L_0x024f
        L_0x0221:
            org.bytedeco.javacpp.tools.InfoMap r10 = r1.infoMap     // Catch:{ IOException -> 0x0352 }
            r11 = r15[r7]     // Catch:{ IOException -> 0x0352 }
            r12 = 0
            java.lang.Object r11 = r11.get(r12)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r11 = (org.bytedeco.javacpp.tools.Token) r11     // Catch:{ IOException -> 0x0352 }
            java.lang.String r11 = r11.value     // Catch:{ IOException -> 0x0352 }
            boolean r10 = r10.containsKey(r11)     // Catch:{ IOException -> 0x0352 }
            if (r10 == 0) goto L_0x024f
            r10 = r15[r7]     // Catch:{ IOException -> 0x0352 }
            r11 = r15[r7]     // Catch:{ IOException -> 0x0352 }
            int r11 = r11.size()     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token[] r11 = new org.bytedeco.javacpp.tools.Token[r11]     // Catch:{ IOException -> 0x0352 }
            java.lang.Object[] r10 = r10.toArray(r11)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token[] r10 = (org.bytedeco.javacpp.tools.Token[]) r10     // Catch:{ IOException -> 0x0352 }
            r11 = 0
            org.bytedeco.javacpp.tools.Token[] r10 = r1.expand(r10, r11)     // Catch:{ IOException -> 0x0352 }
            java.util.List r10 = java.util.Arrays.asList(r10)     // Catch:{ IOException -> 0x0352 }
            r15[r7] = r10     // Catch:{ IOException -> 0x0352 }
        L_0x024f:
            int r7 = r7 + 1
            goto L_0x0211
        L_0x0252:
            r5 = r4
            goto L_0x0256
        L_0x0254:
            r13 = r2
            r15 = 0
        L_0x0256:
            int r4 = r6.size()     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.InfoMap r7 = r1.infoMap     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Info r5 = r7.getFirst(r5)     // Catch:{ IOException -> 0x0352 }
        L_0x0260:
            if (r5 == 0) goto L_0x0266
            boolean r7 = r5.skip     // Catch:{ IOException -> 0x0352 }
            if (r7 != 0) goto L_0x02cc
        L_0x0266:
            boolean r7 = r9.isEmpty()     // Catch:{ IOException -> 0x0352 }
            if (r7 != 0) goto L_0x02cc
            r7 = 0
        L_0x026d:
            int r10 = r8.size()     // Catch:{ IOException -> 0x0352 }
            if (r7 >= r10) goto L_0x02b9
            java.lang.Object r10 = r8.get(r7)     // Catch:{ IOException -> 0x0352 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ IOException -> 0x0352 }
            java.lang.String r11 = r9.value     // Catch:{ IOException -> 0x0352 }
            boolean r10 = r10.equals(r11)     // Catch:{ IOException -> 0x0352 }
            if (r10 == 0) goto L_0x02b6
            java.lang.String r10 = r9.spacing     // Catch:{ IOException -> 0x0352 }
            r7 = r15[r7]     // Catch:{ IOException -> 0x0352 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ IOException -> 0x0352 }
        L_0x0289:
            boolean r11 = r7.hasNext()     // Catch:{ IOException -> 0x0352 }
            if (r11 == 0) goto L_0x02b4
            java.lang.Object r11 = r7.next()     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r11 = (org.bytedeco.javacpp.tools.Token) r11     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r12 = new org.bytedeco.javacpp.tools.Token     // Catch:{ IOException -> 0x0352 }
            r12.<init>(r11)     // Catch:{ IOException -> 0x0352 }
            if (r10 == 0) goto L_0x02af
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0352 }
            r11.<init>()     // Catch:{ IOException -> 0x0352 }
            java.lang.String r14 = r12.spacing     // Catch:{ IOException -> 0x0352 }
            r11.append(r14)     // Catch:{ IOException -> 0x0352 }
            r11.append(r10)     // Catch:{ IOException -> 0x0352 }
            java.lang.String r10 = r11.toString()     // Catch:{ IOException -> 0x0352 }
            r12.spacing = r10     // Catch:{ IOException -> 0x0352 }
        L_0x02af:
            r6.add(r12)     // Catch:{ IOException -> 0x0352 }
            r10 = 0
            goto L_0x0289
        L_0x02b4:
            r7 = 1
            goto L_0x02ba
        L_0x02b6:
            int r7 = r7 + 1
            goto L_0x026d
        L_0x02b9:
            r7 = 0
        L_0x02ba:
            if (r7 != 0) goto L_0x02c7
            int r7 = r9.type     // Catch:{ IOException -> 0x0352 }
            r10 = -1
            if (r7 != r10) goto L_0x02c4
            r7 = 4
            r9.type = r7     // Catch:{ IOException -> 0x0352 }
        L_0x02c4:
            r6.add(r9)     // Catch:{ IOException -> 0x0352 }
        L_0x02c7:
            org.bytedeco.javacpp.tools.Token r9 = r3.nextToken()     // Catch:{ IOException -> 0x0352 }
            goto L_0x0260
        L_0x02cc:
            r3 = r4
        L_0x02cd:
            int r7 = r6.size()     // Catch:{ IOException -> 0x0352 }
            if (r3 >= r7) goto L_0x031f
            java.lang.Object r7 = r6.get(r3)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r7 = (org.bytedeco.javacpp.tools.Token) r7     // Catch:{ IOException -> 0x0352 }
            r8 = 1
            java.lang.Object[] r9 = new java.lang.Object[r8]     // Catch:{ IOException -> 0x0352 }
            java.lang.String r8 = "##"
            r10 = 0
            r9[r10] = r8     // Catch:{ IOException -> 0x0352 }
            boolean r7 = r7.match(r9)     // Catch:{ IOException -> 0x0352 }
            if (r7 == 0) goto L_0x031c
            if (r3 <= 0) goto L_0x031c
            int r7 = r3 + 1
            int r8 = r6.size()     // Catch:{ IOException -> 0x0352 }
            if (r7 >= r8) goto L_0x031c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0352 }
            r8.<init>()     // Catch:{ IOException -> 0x0352 }
            int r9 = r3 + -1
            java.lang.Object r9 = r6.get(r9)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r9 = (org.bytedeco.javacpp.tools.Token) r9     // Catch:{ IOException -> 0x0352 }
            java.lang.String r11 = r9.value     // Catch:{ IOException -> 0x0352 }
            r8.append(r11)     // Catch:{ IOException -> 0x0352 }
            java.lang.Object r7 = r6.get(r7)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r7 = (org.bytedeco.javacpp.tools.Token) r7     // Catch:{ IOException -> 0x0352 }
            java.lang.String r7 = r7.value     // Catch:{ IOException -> 0x0352 }
            r8.append(r7)     // Catch:{ IOException -> 0x0352 }
            java.lang.String r7 = r8.toString()     // Catch:{ IOException -> 0x0352 }
            r9.value = r7     // Catch:{ IOException -> 0x0352 }
            r6.remove(r3)     // Catch:{ IOException -> 0x0352 }
            r6.remove(r3)     // Catch:{ IOException -> 0x0352 }
            int r3 = r3 + -1
        L_0x031c:
            r7 = 1
            int r3 = r3 + r7
            goto L_0x02cd
        L_0x031f:
            r3 = 1
            int r13 = r13 + r3
        L_0x0321:
            int r3 = r0.length     // Catch:{ IOException -> 0x0352 }
            if (r13 >= r3) goto L_0x032c
            r3 = r0[r13]     // Catch:{ IOException -> 0x0352 }
            r6.add(r3)     // Catch:{ IOException -> 0x0352 }
            int r13 = r13 + 1
            goto L_0x0321
        L_0x032c:
            if (r5 == 0) goto L_0x0332
            boolean r3 = r5.skip     // Catch:{ IOException -> 0x0352 }
            if (r3 != 0) goto L_0x0344
        L_0x0332:
            int r3 = r6.size()     // Catch:{ IOException -> 0x0352 }
            if (r4 >= r3) goto L_0x0344
            java.lang.Object r3 = r6.get(r4)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token r3 = (org.bytedeco.javacpp.tools.Token) r3     // Catch:{ IOException -> 0x0352 }
            r0 = r0[r2]     // Catch:{ IOException -> 0x0352 }
            java.lang.String r0 = r0.spacing     // Catch:{ IOException -> 0x0352 }
            r3.spacing = r0     // Catch:{ IOException -> 0x0352 }
        L_0x0344:
            int r0 = r6.size()     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token[] r0 = new org.bytedeco.javacpp.tools.Token[r0]     // Catch:{ IOException -> 0x0352 }
            java.lang.Object[] r0 = r6.toArray(r0)     // Catch:{ IOException -> 0x0352 }
            org.bytedeco.javacpp.tools.Token[] r0 = (org.bytedeco.javacpp.tools.Token[]) r0     // Catch:{ IOException -> 0x0352 }
            goto L_0x0359
        L_0x0351:
            return r0
        L_0x0352:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r0)
            throw r2
        L_0x0359:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.TokenIndexer.expand(org.bytedeco.javacpp.tools.Token[], int):org.bytedeco.javacpp.tools.Token[]");
    }

    /* access modifiers changed from: package-private */
    public int preprocess(int i, int i2) {
        Token[] tokenArr;
        Token[] tokenArr2;
        while (true) {
            tokenArr = null;
            if (i >= this.array.length) {
                break;
            }
            Token[] tokenArr3 = null;
            while (true) {
                tokenArr2 = this.array;
                if (tokenArr3 == tokenArr2) {
                    break;
                }
                Token[] filter = filter(tokenArr2, i);
                this.array = filter;
                this.array = expand(filter, i);
                tokenArr3 = tokenArr2;
            }
            if (!tokenArr2[i].match(4) && i2 - 1 < 0) {
                break;
            }
            i++;
        }
        while (true) {
            Token[] tokenArr4 = this.array;
            if (tokenArr == tokenArr4) {
                return i;
            }
            Token[] filter2 = filter(tokenArr4, i);
            this.array = filter2;
            this.array = expand(filter2, i);
            tokenArr = tokenArr4;
        }
    }

    /* access modifiers changed from: package-private */
    public Token get() {
        return get(0);
    }

    /* access modifiers changed from: package-private */
    public Token get(int i) {
        int preprocess = this.raw ? this.index + i : preprocess(this.index, i);
        Token[] tokenArr = this.array;
        if (preprocess < tokenArr.length) {
            return tokenArr[preprocess];
        }
        if (!tokenArr[tokenArr.length - 1].match(Token.EOF)) {
            return Token.EOF;
        }
        Token[] tokenArr2 = this.array;
        return tokenArr2[tokenArr2.length - 1];
    }

    /* access modifiers changed from: package-private */
    public Token next() {
        int preprocess = this.raw ? this.index + 1 : preprocess(this.index, 1);
        this.index = preprocess;
        Token[] tokenArr = this.array;
        if (preprocess < tokenArr.length) {
            return tokenArr[preprocess];
        }
        if (!tokenArr[tokenArr.length - 1].match(Token.EOF)) {
            return Token.EOF;
        }
        Token[] tokenArr2 = this.array;
        return tokenArr2[tokenArr2.length - 1];
    }
}
