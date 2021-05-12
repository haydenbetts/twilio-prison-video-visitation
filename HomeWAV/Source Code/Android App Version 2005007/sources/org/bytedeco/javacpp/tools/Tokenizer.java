package org.bytedeco.javacpp.tools;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

class Tokenizer implements Closeable {
    StringBuilder buffer;
    File file;
    int lastChar;
    int lineNumber;
    String lineSeparator;
    Reader reader;
    String text;

    Tokenizer(Reader reader2, File file2, int i) {
        this.file = null;
        this.text = null;
        this.reader = null;
        this.lineSeparator = null;
        this.lastChar = -1;
        this.lineNumber = 1;
        this.buffer = new StringBuilder();
        this.reader = reader2;
        this.file = file2;
        this.lineNumber = i;
    }

    Tokenizer(String str, File file2, int i) {
        this.file = null;
        this.text = null;
        this.reader = null;
        this.lineSeparator = null;
        this.lastChar = -1;
        this.lineNumber = 1;
        this.buffer = new StringBuilder();
        this.text = str;
        this.reader = new StringReader(str);
        this.file = file2;
        this.lineNumber = i;
    }

    Tokenizer(File file2) throws IOException {
        this(file2, (String) null);
    }

    Tokenizer(File file2, String str) throws IOException {
        InputStreamReader inputStreamReader;
        this.file = null;
        this.text = null;
        this.reader = null;
        this.lineSeparator = null;
        this.lastChar = -1;
        this.lineNumber = 1;
        this.buffer = new StringBuilder();
        this.file = file2;
        FileInputStream fileInputStream = new FileInputStream(file2);
        if (str == null) {
            inputStreamReader = new InputStreamReader(fileInputStream);
        }
        this.reader = new BufferedReader(inputStreamReader);
    }

    public void filterLines(String[] strArr, boolean z) throws IOException {
        int i;
        String readLine;
        if (strArr != null) {
            StringBuilder sb = new StringBuilder();
            Reader reader2 = this.reader;
            BufferedReader bufferedReader = reader2 instanceof BufferedReader ? (BufferedReader) reader2 : new BufferedReader(this.reader);
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 != null) {
                    int i2 = 0;
                    while (i2 < strArr.length && !readLine2.matches(strArr[i2])) {
                        i2 += 2;
                    }
                    if (i2 < strArr.length) {
                        if (!z) {
                            sb.append(readLine2 + "\n");
                        }
                        do {
                            i = i2 + 1;
                            if (i >= strArr.length || (readLine = bufferedReader.readLine()) == null) {
                                break;
                            } else if (!z) {
                                sb.append(readLine + "\n");
                            }
                        } while (!readLine.matches(strArr[i]));
                    } else if (z) {
                        sb.append(readLine2 + "\n");
                    }
                } else {
                    this.reader.close();
                    this.reader = new StringReader(sb.toString());
                    return;
                }
            }
        }
    }

    public void close() throws IOException {
        this.reader.close();
    }

    /* access modifiers changed from: package-private */
    public int readChar() throws IOException {
        int i = this.lastChar;
        int i2 = -1;
        if (i != -1) {
            this.lastChar = -1;
            return i;
        }
        int read = this.reader.read();
        if (read != 13 && read != 10) {
            return read;
        }
        if (this.text == null) {
            this.lineNumber++;
        }
        if (read == 13) {
            i2 = this.reader.read();
        }
        if (this.lineSeparator == null) {
            this.lineSeparator = (read == 13 && i2 == 10) ? "\r\n" : read == 13 ? "\r" : "\n";
        }
        if (i2 != 10) {
            this.lastChar = i2;
        }
        return 10;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0272, code lost:
        if (r15 != 120) goto L_0x0280;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.Token nextToken() throws java.io.IOException {
        /*
            r18 = this;
            r0 = r18
            org.bytedeco.javacpp.tools.Token r1 = new org.bytedeco.javacpp.tools.Token
            r1.<init>()
            int r2 = r18.readChar()
            java.lang.StringBuilder r3 = r0.buffer
            r4 = 0
            r3.setLength(r4)
            boolean r3 = java.lang.Character.isWhitespace(r2)
            r5 = -1
            if (r3 == 0) goto L_0x0031
            java.lang.StringBuilder r3 = r0.buffer
            char r2 = (char) r2
            r3.append(r2)
        L_0x001e:
            int r2 = r18.readChar()
            if (r2 == r5) goto L_0x0031
            boolean r3 = java.lang.Character.isWhitespace(r2)
            if (r3 == 0) goto L_0x0031
            java.lang.StringBuilder r3 = r0.buffer
            char r2 = (char) r2
            r3.append(r2)
            goto L_0x001e
        L_0x0031:
            java.io.File r3 = r0.file
            r1.file = r3
            java.lang.String r3 = r0.text
            r1.text = r3
            int r3 = r0.lineNumber
            r1.lineNumber = r3
            java.lang.StringBuilder r3 = r0.buffer
            java.lang.String r3 = r3.toString()
            r1.spacing = r3
            java.lang.StringBuilder r3 = r0.buffer
            r3.setLength(r4)
            boolean r3 = java.lang.Character.isLetter(r2)
            r6 = 95
            if (r3 != 0) goto L_0x0314
            if (r2 != r6) goto L_0x0056
            goto L_0x0314
        L_0x0056:
            boolean r3 = java.lang.Character.isDigit(r2)
            r6 = 3
            r7 = 43
            r8 = 45
            r9 = 6
            r10 = 46
            r11 = 1
            if (r3 != 0) goto L_0x01da
            if (r2 == r10) goto L_0x01da
            if (r2 == r8) goto L_0x01da
            if (r2 != r7) goto L_0x006d
            goto L_0x01da
        L_0x006d:
            r3 = 92
            r7 = 39
            if (r2 != r7) goto L_0x00a4
            r1.type = r11
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r7)
        L_0x007a:
            int r2 = r18.readChar()
            if (r2 == r5) goto L_0x0095
            if (r2 == r7) goto L_0x0095
            java.lang.StringBuilder r4 = r0.buffer
            char r6 = (char) r2
            r4.append(r6)
            if (r2 != r3) goto L_0x007a
            int r2 = r18.readChar()
            java.lang.StringBuilder r4 = r0.buffer
            char r2 = (char) r2
            r4.append(r2)
            goto L_0x007a
        L_0x0095:
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r7)
            java.lang.StringBuilder r2 = r0.buffer
            java.lang.String r2 = r2.toString()
            r1.value = r2
            goto L_0x0343
        L_0x00a4:
            r7 = 34
            if (r2 != r7) goto L_0x00d9
            r1.type = r6
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r7)
        L_0x00af:
            int r2 = r18.readChar()
            if (r2 == r5) goto L_0x00ca
            if (r2 == r7) goto L_0x00ca
            java.lang.StringBuilder r4 = r0.buffer
            char r6 = (char) r2
            r4.append(r6)
            if (r2 != r3) goto L_0x00af
            int r2 = r18.readChar()
            java.lang.StringBuilder r4 = r0.buffer
            char r2 = (char) r2
            r4.append(r2)
            goto L_0x00af
        L_0x00ca:
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r7)
            java.lang.StringBuilder r2 = r0.buffer
            java.lang.String r2 = r2.toString()
            r1.value = r2
            goto L_0x0343
        L_0x00d9:
            r6 = 4
            r7 = 47
            if (r2 != r7) goto L_0x0143
            int r2 = r18.readChar()
            if (r2 != r7) goto L_0x010e
            r1.type = r6
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r7)
            r2.append(r7)
        L_0x00ee:
            int r2 = r18.readChar()
            if (r2 == r5) goto L_0x0102
            if (r4 == r3) goto L_0x00fa
            r4 = 10
            if (r2 == r4) goto L_0x0102
        L_0x00fa:
            java.lang.StringBuilder r4 = r0.buffer
            char r6 = (char) r2
            r4.append(r6)
            r4 = r2
            goto L_0x00ee
        L_0x0102:
            java.lang.StringBuilder r3 = r0.buffer
            java.lang.String r3 = r3.toString()
            r1.value = r3
            r0.lastChar = r2
            goto L_0x0343
        L_0x010e:
            r3 = 42
            if (r2 != r3) goto L_0x013d
            r1.type = r6
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r7)
            r2.append(r3)
        L_0x011c:
            int r2 = r18.readChar()
            if (r2 == r5) goto L_0x012e
            if (r4 != r3) goto L_0x0126
            if (r2 == r7) goto L_0x012e
        L_0x0126:
            java.lang.StringBuilder r4 = r0.buffer
            char r6 = (char) r2
            r4.append(r6)
            r4 = r2
            goto L_0x011c
        L_0x012e:
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r7)
            java.lang.StringBuilder r2 = r0.buffer
            java.lang.String r2 = r2.toString()
            r1.value = r2
            goto L_0x0343
        L_0x013d:
            r0.lastChar = r2
            r1.type = r7
            goto L_0x0343
        L_0x0143:
            r4 = 58
            if (r2 != r4) goto L_0x015b
            int r3 = r18.readChar()
            if (r3 != r4) goto L_0x0155
            r1.type = r9
            java.lang.String r2 = "::"
            r1.value = r2
            goto L_0x0343
        L_0x0155:
            r1.type = r2
            r0.lastChar = r3
            goto L_0x0343
        L_0x015b:
            r4 = 38
            if (r2 != r4) goto L_0x0175
            int r3 = r18.readChar()
            r4 = 38
            if (r3 != r4) goto L_0x016f
            r1.type = r9
            java.lang.String r2 = "&&"
            r1.value = r2
            goto L_0x0343
        L_0x016f:
            r1.type = r2
            r0.lastChar = r3
            goto L_0x0343
        L_0x0175:
            r4 = 35
            if (r2 != r4) goto L_0x018f
            int r3 = r18.readChar()
            r4 = 35
            if (r3 != r4) goto L_0x0189
            r1.type = r9
            java.lang.String r2 = "##"
            r1.value = r2
            goto L_0x0343
        L_0x0189:
            r1.type = r2
            r0.lastChar = r3
            goto L_0x0343
        L_0x018f:
            r4 = 91
            if (r2 != r4) goto L_0x01a9
            int r3 = r18.readChar()
            r4 = 91
            if (r3 != r4) goto L_0x01a3
            r1.type = r9
            java.lang.String r2 = "[["
            r1.value = r2
            goto L_0x0343
        L_0x01a3:
            r1.type = r2
            r0.lastChar = r3
            goto L_0x0343
        L_0x01a9:
            r4 = 93
            if (r2 != r4) goto L_0x01c3
            int r3 = r18.readChar()
            r4 = 93
            if (r3 != r4) goto L_0x01bd
            r1.type = r9
            java.lang.String r2 = "]]"
            r1.value = r2
            goto L_0x0343
        L_0x01bd:
            r1.type = r2
            r0.lastChar = r3
            goto L_0x0343
        L_0x01c3:
            if (r2 != r3) goto L_0x01d6
            int r3 = r18.readChar()
            r4 = 10
            if (r3 != r4) goto L_0x01d4
            r1.type = r6
            java.lang.String r2 = "\n"
            r1.value = r2
            return r1
        L_0x01d4:
            r0.lastChar = r3
        L_0x01d6:
            r1.type = r2
            goto L_0x0343
        L_0x01da:
            if (r2 != r10) goto L_0x01f5
            int r3 = r18.readChar()
            if (r3 != r10) goto L_0x01f2
            int r3 = r18.readChar()
            if (r3 != r10) goto L_0x01ef
            r1.type = r9
            java.lang.String r2 = "..."
            r1.value = r2
            return r1
        L_0x01ef:
            r0.lastChar = r3
            goto L_0x0208
        L_0x01f2:
            r0.lastChar = r3
            goto L_0x0208
        L_0x01f5:
            if (r2 != r8) goto L_0x0208
            int r3 = r18.readChar()
            r12 = 62
            if (r3 != r12) goto L_0x0206
            r1.type = r9
            java.lang.String r2 = "->"
            r1.value = r2
            return r1
        L_0x0206:
            r0.lastChar = r3
        L_0x0208:
            if (r2 != r10) goto L_0x020c
            r9 = 2
            goto L_0x020d
        L_0x020c:
            r9 = 1
        L_0x020d:
            r1.type = r9
            java.lang.StringBuilder r9 = r0.buffer
            char r2 = (char) r2
            r9.append(r2)
            r2 = 0
            r9 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x021a:
            int r15 = r18.readChar()
            r4 = 76
            if (r15 == r5) goto L_0x0296
            boolean r17 = java.lang.Character.isDigit(r15)
            r5 = 88
            r6 = 117(0x75, float:1.64E-43)
            r11 = 108(0x6c, float:1.51E-43)
            r3 = 85
            if (r17 != 0) goto L_0x025c
            if (r15 == r10) goto L_0x025c
            if (r15 == r8) goto L_0x025c
            if (r15 == r7) goto L_0x025c
            r7 = 97
            if (r15 < r7) goto L_0x023e
            r7 = 102(0x66, float:1.43E-43)
            if (r15 <= r7) goto L_0x025c
        L_0x023e:
            r7 = 105(0x69, float:1.47E-43)
            if (r15 == r7) goto L_0x025c
            if (r15 == r11) goto L_0x025c
            if (r15 == r6) goto L_0x025c
            r7 = 120(0x78, float:1.68E-43)
            if (r15 == r7) goto L_0x025c
            r7 = 65
            if (r15 < r7) goto L_0x0252
            r7 = 70
            if (r15 <= r7) goto L_0x025c
        L_0x0252:
            r7 = 73
            if (r15 == r7) goto L_0x025c
            if (r15 == r4) goto L_0x025c
            if (r15 == r3) goto L_0x025c
            if (r15 != r5) goto L_0x0296
        L_0x025c:
            if (r15 == r10) goto L_0x027d
            r2 = 69
            if (r15 == r2) goto L_0x027b
            if (r15 == r4) goto L_0x0279
            if (r15 == r3) goto L_0x0277
            if (r15 == r5) goto L_0x0275
            r2 = 101(0x65, float:1.42E-43)
            if (r15 == r2) goto L_0x027b
            if (r15 == r11) goto L_0x0279
            if (r15 == r6) goto L_0x0277
            r2 = 120(0x78, float:1.68E-43)
            if (r15 == r2) goto L_0x0275
            goto L_0x0280
        L_0x0275:
            r14 = 1
            goto L_0x0280
        L_0x0277:
            r13 = 1
            goto L_0x0280
        L_0x0279:
            r12 = 1
            goto L_0x0280
        L_0x027b:
            r9 = 1
            goto L_0x0280
        L_0x027d:
            r2 = 2
            r1.type = r2
        L_0x0280:
            if (r15 == r11) goto L_0x028e
            if (r15 == r4) goto L_0x028e
            if (r15 == r6) goto L_0x028e
            if (r15 == r3) goto L_0x028e
            java.lang.StringBuilder r2 = r0.buffer
            char r3 = (char) r15
            r2.append(r3)
        L_0x028e:
            r2 = r15
            r4 = 0
            r5 = -1
            r6 = 3
            r7 = 43
            r11 = 1
            goto L_0x021a
        L_0x0296:
            if (r14 != 0) goto L_0x02a5
            if (r9 != 0) goto L_0x02a2
            r3 = 102(0x66, float:1.43E-43)
            if (r2 == r3) goto L_0x02a2
            r3 = 70
            if (r2 != r3) goto L_0x02a5
        L_0x02a2:
            r2 = 2
            r1.type = r2
        L_0x02a5:
            int r2 = r1.type
            r3 = 1
            if (r2 != r3) goto L_0x02dd
            if (r12 != 0) goto L_0x02dd
            java.lang.StringBuilder r2 = r0.buffer     // Catch:{ NumberFormatException -> 0x02d1 }
            java.lang.String r2 = r2.toString()     // Catch:{ NumberFormatException -> 0x02d1 }
            java.lang.Long r2 = java.lang.Long.decode(r2)     // Catch:{ NumberFormatException -> 0x02d1 }
            long r2 = r2.longValue()     // Catch:{ NumberFormatException -> 0x02d1 }
            r5 = 32
            long r2 = r2 >> r5
            r5 = 0
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x02cc
            r5 = -1
            int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x02cc
            r16 = 1
            goto L_0x02ce
        L_0x02cc:
            r16 = 0
        L_0x02ce:
            r12 = r16
            goto L_0x02dd
        L_0x02d1:
            java.lang.StringBuilder r2 = r0.buffer
            int r2 = r2.length()
            r3 = 16
            if (r2 < r3) goto L_0x02dd
            r12 = 1
        L_0x02dd:
            java.lang.StringBuilder r2 = r0.buffer
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "i64"
            boolean r2 = r2.endsWith(r3)
            if (r2 == 0) goto L_0x02f8
            java.lang.StringBuilder r2 = r0.buffer
            int r3 = r2.length()
            r5 = 3
            int r3 = r3 - r5
            r2.setLength(r3)
            r3 = 1
            goto L_0x02f9
        L_0x02f8:
            r3 = r12
        L_0x02f9:
            int r2 = r1.type
            r5 = 1
            if (r2 != r5) goto L_0x0309
            if (r3 != 0) goto L_0x0304
            if (r13 == 0) goto L_0x0309
            if (r14 != 0) goto L_0x0309
        L_0x0304:
            java.lang.StringBuilder r2 = r0.buffer
            r2.append(r4)
        L_0x0309:
            java.lang.StringBuilder r2 = r0.buffer
            java.lang.String r2 = r2.toString()
            r1.value = r2
            r0.lastChar = r15
            goto L_0x0343
        L_0x0314:
            r3 = 5
            r1.type = r3
            java.lang.StringBuilder r3 = r0.buffer
            char r2 = (char) r2
            r3.append(r2)
        L_0x031d:
            int r2 = r18.readChar()
            r3 = -1
            if (r2 == r3) goto L_0x0339
            boolean r4 = java.lang.Character.isDigit(r2)
            if (r4 != 0) goto L_0x0332
            boolean r4 = java.lang.Character.isLetter(r2)
            if (r4 != 0) goto L_0x0332
            if (r2 != r6) goto L_0x0339
        L_0x0332:
            java.lang.StringBuilder r4 = r0.buffer
            char r2 = (char) r2
            r4.append(r2)
            goto L_0x031d
        L_0x0339:
            java.lang.StringBuilder r3 = r0.buffer
            java.lang.String r3 = r3.toString()
            r1.value = r3
            r0.lastChar = r2
        L_0x0343:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Tokenizer.nextToken():org.bytedeco.javacpp.tools.Token");
    }

    /* access modifiers changed from: package-private */
    public Token[] tokenize() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            try {
                Token nextToken = nextToken();
                if (nextToken.isEmpty()) {
                    return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
                }
                arrayList.add(nextToken);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
