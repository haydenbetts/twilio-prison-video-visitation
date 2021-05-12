package org.bytedeco.javacpp.tools;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.slf4j.Marker;

public class Parser {
    final String encoding;
    InfoMap infoMap;
    InfoMap leafInfoMap;
    String lineSeparator;
    final Logger logger;
    final Properties properties;
    TokenIndexer tokens;

    public Parser(Logger logger2, Properties properties2) {
        this(logger2, properties2, (String) null, (String) null);
    }

    public Parser(Logger logger2, Properties properties2, String str, String str2) {
        this.infoMap = null;
        this.leafInfoMap = null;
        this.tokens = null;
        this.lineSeparator = null;
        this.logger = logger2;
        this.properties = properties2;
        this.encoding = str;
        this.lineSeparator = str2;
    }

    Parser(Parser parser, String str) {
        this.infoMap = null;
        this.leafInfoMap = null;
        this.tokens = null;
        this.lineSeparator = null;
        this.logger = parser.logger;
        this.properties = parser.properties;
        this.encoding = parser.encoding;
        this.infoMap = parser.infoMap;
        TokenIndexer tokenIndexer = parser.tokens;
        Token token = tokenIndexer != null ? tokenIndexer.get() : Token.EOF;
        this.tokens = new TokenIndexer(this.infoMap, new Tokenizer(str, token.file, token.lineNumber).tokenize(), false);
        this.lineSeparator = parser.lineSeparator;
    }

    /* access modifiers changed from: package-private */
    public String translate(String str) {
        Info first = this.infoMap.getFirst(str);
        if (first != null && first.javaNames != null && first.javaNames.length > 0) {
            return first.javaNames[0];
        }
        int lastIndexOf = str.lastIndexOf("::");
        if (lastIndexOf >= 0) {
            Info first2 = this.infoMap.getFirst(str.substring(0, lastIndexOf));
            String substring = str.substring(lastIndexOf + 2);
            if (first2 != null && first2.pointerTypes != null) {
                str = first2.pointerTypes[0] + "." + substring;
            } else if (substring.length() > 0 && Character.isJavaIdentifierStart(substring.charAt(0))) {
                char[] charArray = substring.toCharArray();
                int length = charArray.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (!Character.isJavaIdentifierPart(charArray[i])) {
                        substring = null;
                        break;
                    } else {
                        i++;
                    }
                }
                if (substring != null) {
                    str = substring;
                }
            }
        }
        int lastIndexOf2 = str.lastIndexOf(40);
        int indexOf = str.indexOf(41, lastIndexOf2);
        if (lastIndexOf2 < 0 || lastIndexOf2 >= indexOf) {
            return str;
        }
        int i2 = lastIndexOf2 + 1;
        Info first3 = this.infoMap.getFirst(str.substring(i2, indexOf));
        if (first3 == null || first3.valueTypes == null || first3.valueTypes.length <= 0) {
            return str;
        }
        return str.substring(0, i2) + first3.valueTypes[0] + str.substring(indexOf);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03a0, code lost:
        if (r14 != null) goto L_0x03a5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x04f5  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x04f8  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0518  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x051b  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x0525  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x055c  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x06b1  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x06b8  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x07a1 A[LOOP:14: B:236:0x0796->B:241:0x07a1, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x07f0 A[LOOP:15: B:243:0x07e5->B:248:0x07f0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x0c10 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x0df3  */
    /* JADX WARNING: Removed duplicated region for block: B:358:0x0df8  */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x0e05  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void containers(org.bytedeco.javacpp.tools.Context r40, org.bytedeco.javacpp.tools.DeclarationList r41) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r39 = this;
            r0 = r39
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.bytedeco.javacpp.tools.InfoMap r2 = r0.infoMap
            java.lang.String r3 = "basic/containers"
            java.util.List r2 = r2.get(r3)
            java.util.Iterator r2 = r2.iterator()
        L_0x0013:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0029
            java.lang.Object r3 = r2.next()
            org.bytedeco.javacpp.tools.Info r3 = (org.bytedeco.javacpp.tools.Info) r3
            java.lang.String[] r3 = r3.cppTypes
            java.util.List r3 = java.util.Arrays.asList(r3)
            r1.addAll(r3)
            goto L_0x0013
        L_0x0029:
            java.util.Iterator r1 = r1.iterator()
        L_0x002d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x1018
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet
            r3.<init>()
            org.bytedeco.javacpp.tools.InfoMap r4 = r0.leafInfoMap
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "const "
            r5.append(r6)
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            java.util.List r4 = r4.get(r5)
            r3.addAll(r4)
            org.bytedeco.javacpp.tools.InfoMap r4 = r0.leafInfoMap
            java.util.List r4 = r4.get(r2)
            r3.addAll(r4)
            java.util.Iterator r3 = r3.iterator()
        L_0x0065:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x1012
            java.lang.Object r4 = r3.next()
            org.bytedeco.javacpp.tools.Info r4 = (org.bytedeco.javacpp.tools.Info) r4
            org.bytedeco.javacpp.tools.Declaration r5 = new org.bytedeco.javacpp.tools.Declaration
            r5.<init>()
            if (r4 == 0) goto L_0x100c
            boolean r7 = r4.skip
            if (r7 != 0) goto L_0x100c
            boolean r7 = r4.define
            if (r7 != 0) goto L_0x0081
            goto L_0x0065
        L_0x0081:
            java.lang.String r7 = r2.toLowerCase()
            java.lang.String r8 = "pair"
            boolean r7 = r7.endsWith(r8)
            r9 = 1
            r7 = r7 ^ r9
            java.lang.String[] r10 = r4.cppNames
            r11 = 0
            r10 = r10[r11]
            boolean r10 = r10.startsWith(r6)
            r12 = r10 ^ 1
            org.bytedeco.javacpp.tools.Parser r13 = new org.bytedeco.javacpp.tools.Parser
            java.lang.String[] r4 = r4.cppNames
            r4 = r4[r11]
            r13.<init>((org.bytedeco.javacpp.tools.Parser) r0, (java.lang.String) r4)
            r4 = r40
            org.bytedeco.javacpp.tools.Type r13 = r13.type(r4)
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            if (r14 == 0) goto L_0x100c
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            int r14 = r14.length
            if (r14 == 0) goto L_0x100c
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            r14 = r14[r11]
            if (r14 == 0) goto L_0x100c
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            org.bytedeco.javacpp.tools.Type[] r15 = r13.arguments
            int r15 = r15.length
            int r15 = r15 - r9
            r14 = r14[r15]
            if (r14 != 0) goto L_0x00c1
            goto L_0x0065
        L_0x00c1:
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            int r14 = r14.length
            if (r14 <= r9) goto L_0x00de
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            r14 = r14[r9]
            java.lang.String r14 = r14.javaName
            int r14 = r14.length()
            if (r14 <= 0) goto L_0x00de
            org.bytedeco.javacpp.tools.Type[] r12 = r13.arguments
            r12 = r12[r11]
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            r14 = r14[r9]
            r15 = r14
            r14 = r12
            r12 = 0
            goto L_0x00fa
        L_0x00de:
            org.bytedeco.javacpp.tools.Type[] r14 = r13.arguments
            int r14 = r14.length
            if (r14 != r9) goto L_0x00e5
            r14 = 1
            goto L_0x00e6
        L_0x00e5:
            r14 = 0
        L_0x00e6:
            r12 = r12 & r14
            org.bytedeco.javacpp.tools.Type r14 = new org.bytedeco.javacpp.tools.Type
            r14.<init>()
            r14.value = r9
            java.lang.String r15 = "size_t"
            r14.cppName = r15
            java.lang.String r15 = "long"
            r14.javaName = r15
            org.bytedeco.javacpp.tools.Type[] r15 = r13.arguments
            r15 = r15[r11]
        L_0x00fa:
            java.lang.String r9 = r15.javaName
            r17 = 0
            java.lang.String r18 = ""
            java.lang.String r19 = "(function = \"at\")"
            if (r9 == 0) goto L_0x0153
            java.lang.String r9 = r15.javaName
            int r9 = r9.length()
            if (r9 == 0) goto L_0x0153
            java.lang.String r9 = r2.toLowerCase()
            java.lang.String r11 = "bitset"
            boolean r9 = r9.endsWith(r11)
            if (r9 == 0) goto L_0x0119
            goto L_0x0153
        L_0x0119:
            java.lang.String r9 = r2.toLowerCase()
            java.lang.String r11 = "list"
            boolean r9 = r9.endsWith(r11)
            if (r9 != 0) goto L_0x0140
            java.lang.String r9 = r2.toLowerCase()
            java.lang.String r11 = "set"
            boolean r9 = r9.endsWith(r11)
            if (r9 == 0) goto L_0x0132
            goto L_0x0140
        L_0x0132:
            if (r10 != 0) goto L_0x013c
            if (r12 != 0) goto L_0x013c
            r19 = r1
            r9 = r12
            r11 = r18
            goto L_0x015d
        L_0x013c:
            r9 = r12
            r11 = r19
            goto L_0x0150
        L_0x0140:
            java.lang.String r9 = r2.toLowerCase()
            java.lang.String r11 = "list"
            boolean r12 = r9.endsWith(r11)
            r9 = r12
            r14 = r17
            r11 = r19
            r12 = 0
        L_0x0150:
            r19 = r1
            goto L_0x015d
        L_0x0153:
            java.lang.String r9 = "boolean"
            r15.javaName = r9
            r19 = r1
            r9 = r12
            r11 = r18
            r12 = 0
        L_0x015d:
            java.lang.String r1 = r15.cppName
            boolean r1 = r1.startsWith(r2)
            if (r1 == 0) goto L_0x0181
            org.bytedeco.javacpp.tools.InfoMap r1 = r0.leafInfoMap
            r21 = r3
            java.lang.String r3 = r15.cppName
            r4 = 0
            java.util.List r1 = r1.get((java.lang.String) r3, (boolean) r4)
            int r1 = r1.size()
            if (r1 != 0) goto L_0x0184
            int r7 = r7 + 1
            org.bytedeco.javacpp.tools.Type[] r1 = r15.arguments
            r15 = r1[r4]
            r4 = r40
            r3 = r21
            goto L_0x015d
        L_0x0181:
            r21 = r3
            r4 = 0
        L_0x0184:
            java.lang.String r1 = r15.cppName
            java.lang.String r3 = "<"
            int r1 = r1.indexOf(r3)
            java.lang.String r3 = r2.toLowerCase()
            boolean r3 = r3.endsWith(r8)
            if (r3 == 0) goto L_0x01a1
            org.bytedeco.javacpp.tools.Type[] r1 = r13.arguments
            r1 = r1[r4]
            org.bytedeco.javacpp.tools.Type[] r3 = r13.arguments
            r16 = 1
            r3 = r3[r16]
            goto L_0x01c1
        L_0x01a1:
            r16 = 1
            if (r1 < 0) goto L_0x01be
            java.lang.String r3 = r15.cppName
            java.lang.String r1 = r3.substring(r4, r1)
            java.lang.String r1 = r1.toLowerCase()
            boolean r1 = r1.endsWith(r8)
            if (r1 == 0) goto L_0x01be
            org.bytedeco.javacpp.tools.Type[] r1 = r15.arguments
            r1 = r1[r4]
            org.bytedeco.javacpp.tools.Type[] r3 = r15.arguments
            r3 = r3[r16]
            goto L_0x01c1
        L_0x01be:
            r1 = r17
            r3 = r1
        L_0x01c1:
            r8 = 4
            r22 = r9
            org.bytedeco.javacpp.tools.Type[] r9 = new org.bytedeco.javacpp.tools.Type[r8]
            r9[r4] = r1
            r9[r16] = r3
            r4 = 2
            r9[r4] = r14
            r23 = 3
            r9[r23] = r15
            r4 = 0
        L_0x01d2:
            if (r4 >= r8) goto L_0x01f1
            int r24 = r4 + 1
            r25 = r2
            r2 = r24
        L_0x01da:
            if (r2 >= r8) goto L_0x01ec
            r8 = r9[r2]
            r26 = r11
            r11 = r9[r4]
            if (r8 != r11) goto L_0x01e6
            r9[r2] = r17
        L_0x01e6:
            int r2 = r2 + 1
            r11 = r26
            r8 = 4
            goto L_0x01da
        L_0x01ec:
            r4 = r24
            r2 = r25
            goto L_0x01d2
        L_0x01f1:
            r25 = r2
            r26 = r11
            r2 = 0
        L_0x01f6:
            java.lang.String r4 = "@Cast"
            java.lang.String r8 = "@Const "
            r11 = 4
            if (r2 >= r11) goto L_0x02fc
            r11 = r9[r2]
            if (r11 != 0) goto L_0x0205
            r17 = r9
            goto L_0x02f6
        L_0x0205:
            r17 = r9
            java.lang.String r9 = r11.annotations
            if (r9 == 0) goto L_0x0213
            java.lang.String r9 = r11.annotations
            int r9 = r9.length()
            if (r9 != 0) goto L_0x023c
        L_0x0213:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r24 = r8
            boolean r8 = r11.constValue
            if (r8 == 0) goto L_0x0221
            r8 = r24
            goto L_0x0223
        L_0x0221:
            r8 = r18
        L_0x0223:
            r9.append(r8)
            int r8 = r11.indirections
            if (r8 != 0) goto L_0x0231
            boolean r8 = r11.value
            if (r8 != 0) goto L_0x0231
            java.lang.String r8 = "@ByRef "
            goto L_0x0233
        L_0x0231:
            r8 = r18
        L_0x0233:
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r11.annotations = r8
        L_0x023c:
            org.bytedeco.javacpp.tools.InfoMap r8 = r0.infoMap
            java.lang.String r9 = r11.cppName
            org.bytedeco.javacpp.tools.Info r8 = r8.getFirst(r9)
            if (r8 == 0) goto L_0x02f6
            boolean r8 = r8.cast
            if (r8 == 0) goto L_0x02f6
            java.lang.String r8 = r11.annotations
            boolean r8 = r8.contains(r4)
            if (r8 != 0) goto L_0x02f6
            java.lang.String r8 = r11.javaName
            boolean r4 = r8.contains(r4)
            if (r4 != 0) goto L_0x02f6
            java.lang.String r4 = r11.cppName
            boolean r8 = r11.constValue
            if (r8 == 0) goto L_0x0275
            boolean r8 = r4.startsWith(r6)
            if (r8 != 0) goto L_0x0275
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r6)
            r8.append(r4)
            java.lang.String r4 = r8.toString()
        L_0x0275:
            int r8 = r11.indirections
            if (r8 <= 0) goto L_0x0292
            r8 = 0
        L_0x027a:
            int r9 = r11.indirections
            if (r8 >= r9) goto L_0x02a7
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            java.lang.String r4 = "*"
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            int r8 = r8 + 1
            goto L_0x027a
        L_0x0292:
            boolean r8 = r11.value
            if (r8 != 0) goto L_0x02a7
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r4)
            java.lang.String r4 = "*"
            r8.append(r4)
            java.lang.String r4 = r8.toString()
        L_0x02a7:
            boolean r8 = r11.reference
            if (r8 == 0) goto L_0x02bc
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r4)
            java.lang.String r4 = "&"
            r8.append(r4)
            java.lang.String r4 = r8.toString()
        L_0x02bc:
            boolean r8 = r11.constPointer
            if (r8 == 0) goto L_0x02d9
            java.lang.String r8 = " const"
            boolean r8 = r4.endsWith(r8)
            if (r8 != 0) goto L_0x02d9
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r4)
            java.lang.String r4 = " const"
            r8.append(r4)
            java.lang.String r4 = r8.toString()
        L_0x02d9:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "@Cast(\""
            r8.append(r9)
            r8.append(r4)
            java.lang.String r4 = "\") "
            r8.append(r4)
            java.lang.String r4 = r11.annotations
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r11.annotations = r4
        L_0x02f6:
            int r2 = r2 + 1
            r9 = r17
            goto L_0x01f6
        L_0x02fc:
            r24 = r8
            r8 = r18
            r2 = 0
        L_0x0301:
            int r9 = r7 + -1
            java.lang.String r11 = "[]"
            if (r2 >= r9) goto L_0x0319
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            r9.append(r11)
            java.lang.String r8 = r9.toString()
            int r2 = r2 + 1
            goto L_0x0301
        L_0x0319:
            java.lang.String r2 = r13.javaName
            r9 = 32
            int r2 = r2.lastIndexOf(r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = r13.annotations
            r9.append(r0)
            java.lang.String r0 = r13.javaName
            r16 = 1
            int r2 = r2 + 1
            r27 = r6
            r6 = 0
            java.lang.String r0 = r0.substring(r6, r2)
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            r13.annotations = r0
            java.lang.String r0 = r13.javaName
            java.lang.String r0 = r0.substring(r2)
            r13.javaName = r0
            org.bytedeco.javacpp.tools.Type r0 = new org.bytedeco.javacpp.tools.Type
            java.lang.String r2 = r13.javaName
            r0.<init>(r2)
            r5.type = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.text
            r0.append(r2)
            java.lang.String r2 = "\n"
            if (r7 != 0) goto L_0x0363
            java.lang.String r6 = "\n@NoOffset "
            goto L_0x0364
        L_0x0363:
            r6 = r2
        L_0x0364:
            r0.append(r6)
            java.lang.String r6 = "@Name(\""
            r0.append(r6)
            java.lang.String r6 = r13.cppName
            r0.append(r6)
            java.lang.String r6 = "\") public static class "
            r0.append(r6)
            java.lang.String r6 = r13.javaName
            r0.append(r6)
            java.lang.String r6 = " extends Pointer {\n    static { Loader.load(); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public "
            r0.append(r6)
            java.lang.String r6 = r13.javaName
            r0.append(r6)
            java.lang.String r6 = "(Pointer p) { super(p); }\n"
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            java.lang.String r0 = "("
            java.lang.String r6 = "    public "
            if (r10 != 0) goto L_0x0457
            if (r7 == 0) goto L_0x03a3
            org.bytedeco.javacpp.tools.Type[] r9 = r13.arguments
            int r9 = r9.length
            r28 = r11
            r11 = 1
            if (r9 != r11) goto L_0x0450
            if (r14 == 0) goto L_0x0450
            goto L_0x03a5
        L_0x03a3:
            r28 = r11
        L_0x03a5:
            if (r1 == 0) goto L_0x0450
            if (r3 == 0) goto L_0x0450
            java.lang.String[] r9 = r1.javaNames
            if (r9 == 0) goto L_0x03b2
            java.lang.String[] r9 = r1.javaNames
            r20 = 0
            goto L_0x03bc
        L_0x03b2:
            r9 = 1
            java.lang.String[] r11 = new java.lang.String[r9]
            java.lang.String r9 = r1.javaName
            r20 = 0
            r11[r20] = r9
            r9 = r11
        L_0x03bc:
            java.lang.String[] r11 = r3.javaNames
            if (r11 == 0) goto L_0x03c5
            java.lang.String[] r11 = r3.javaNames
            r29 = r4
            goto L_0x03cf
        L_0x03c5:
            r29 = r4
            r11 = 1
            java.lang.String[] r4 = new java.lang.String[r11]
            java.lang.String r11 = r3.javaName
            r4[r20] = r11
            r11 = r4
        L_0x03cf:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r8)
            r30 = r2
            if (r7 <= 0) goto L_0x03de
            r2 = r28
            goto L_0x03e0
        L_0x03de:
            r2 = r18
        L_0x03e0:
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r31 = r14
            r4 = 0
        L_0x03ea:
            int r14 = r9.length
            if (r4 < r14) goto L_0x03f5
            int r14 = r11.length
            if (r4 >= r14) goto L_0x03f1
            goto L_0x03f5
        L_0x03f1:
            r32 = r10
            goto L_0x04dc
        L_0x03f5:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r32 = r10
            java.lang.String r10 = r5.text
            r14.append(r10)
            r14.append(r6)
            java.lang.String r10 = r13.javaName
            r14.append(r10)
            r14.append(r0)
            int r10 = r9.length
            r16 = 1
            int r10 = r10 + -1
            int r10 = java.lang.Math.min(r4, r10)
            r10 = r9[r10]
            r14.append(r10)
            r14.append(r2)
            java.lang.String r10 = " firstValue, "
            r14.append(r10)
            int r10 = r11.length
            int r10 = r10 + -1
            int r10 = java.lang.Math.min(r4, r10)
            r10 = r11[r10]
            r14.append(r10)
            r14.append(r2)
            java.lang.String r10 = " secondValue) { this("
            r14.append(r10)
            if (r7 <= 0) goto L_0x043b
            java.lang.String r10 = "Math.min(firstValue.length, secondValue.length)"
            goto L_0x043d
        L_0x043b:
            r10 = r18
        L_0x043d:
            r14.append(r10)
            java.lang.String r10 = "); put(firstValue, secondValue); }\n"
            r14.append(r10)
            java.lang.String r10 = r14.toString()
            r5.text = r10
            int r4 = r4 + 1
            r10 = r32
            goto L_0x03ea
        L_0x0450:
            r30 = r2
            r29 = r4
            r32 = r10
            goto L_0x045f
        L_0x0457:
            r30 = r2
            r29 = r4
            r32 = r10
            r28 = r11
        L_0x045f:
            r31 = r14
            if (r12 == 0) goto L_0x04dc
            if (r1 != 0) goto L_0x04dc
            if (r3 != 0) goto L_0x04dc
            java.lang.String[] r2 = r15.javaNames
            if (r2 == 0) goto L_0x046e
            java.lang.String[] r2 = r15.javaNames
            goto L_0x0477
        L_0x046e:
            r2 = 1
            java.lang.String[] r4 = new java.lang.String[r2]
            java.lang.String r2 = r15.javaName
            r9 = 0
            r4[r9] = r2
            r2 = r4
        L_0x0477:
            int r4 = r2.length
            r9 = 0
        L_0x0479:
            if (r9 >= r4) goto L_0x04dc
            r10 = r2[r9]
            r11 = 2
            if (r7 >= r11) goto L_0x04b3
            java.lang.String r11 = "int"
            boolean r11 = r10.equals(r11)
            if (r11 != 0) goto L_0x04b3
            java.lang.String r11 = "long"
            boolean r11 = r10.equals(r11)
            if (r11 != 0) goto L_0x04b3
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r14 = r5.text
            r11.append(r14)
            r11.append(r6)
            java.lang.String r14 = r13.javaName
            r11.append(r14)
            r11.append(r0)
            r11.append(r10)
            java.lang.String r14 = " value) { this(1); put(0, value); }\n"
            r11.append(r14)
            java.lang.String r11 = r11.toString()
            r5.text = r11
        L_0x04b3:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r14 = r5.text
            r11.append(r14)
            r11.append(r6)
            java.lang.String r14 = r13.javaName
            r11.append(r14)
            r11.append(r0)
            r11.append(r10)
            r11.append(r8)
            java.lang.String r10 = " ... array) { this(array.length); put(array); }\n"
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r5.text = r10
            int r9 = r9 + 1
            goto L_0x0479
        L_0x04dc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r5.text
            r0.append(r2)
            r0.append(r6)
            java.lang.String r2 = r13.javaName
            r0.append(r2)
            java.lang.String r2 = "()       { allocate();  }\n"
            r0.append(r2)
            if (r12 != 0) goto L_0x04f8
            r2 = r18
            goto L_0x050e
        L_0x04f8:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r6)
            java.lang.String r4 = r13.javaName
            r2.append(r4)
            java.lang.String r4 = "(long n) { allocate(n); }\n"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
        L_0x050e:
            r0.append(r2)
            java.lang.String r2 = "    private native void allocate();\n"
            r0.append(r2)
            if (r12 != 0) goto L_0x051b
            r2 = r18
            goto L_0x051d
        L_0x051b:
            java.lang.String r2 = "    private native void allocate(@Cast(\"size_t\") long n);\n"
        L_0x051d:
            r0.append(r2)
            if (r32 == 0) goto L_0x0525
            java.lang.String r2 = "\n\n"
            goto L_0x054c
        L_0x0525:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "    public native @Name(\"operator =\") @ByRef "
            r2.append(r4)
            java.lang.String r4 = r13.javaName
            r2.append(r4)
            java.lang.String r4 = " put(@ByRef "
            r2.append(r4)
            java.lang.String r4 = r13.annotations
            r2.append(r4)
            java.lang.String r4 = r13.javaName
            r2.append(r4)
            java.lang.String r4 = " x);\n\n"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
        L_0x054c:
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            r0 = 0
        L_0x0556:
            java.lang.String r2 = ");\n"
            java.lang.String r4 = ", "
            if (r0 >= r7) goto L_0x0667
            if (r0 <= 0) goto L_0x058f
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "@Index("
            r9.append(r10)
            r10 = 1
            if (r0 <= r10) goto L_0x0580
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "value = "
            r10.append(r11)
            r10.append(r0)
            r10.append(r4)
            java.lang.String r10 = r10.toString()
            goto L_0x0582
        L_0x0580:
            r10 = r18
        L_0x0582:
            r9.append(r10)
            java.lang.String r10 = "function = \"at\") "
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            goto L_0x0591
        L_0x058f:
            r9 = r18
        L_0x0591:
            r33 = r6
            r6 = r18
            r11 = r6
            r14 = r11
            r10 = 0
        L_0x0598:
            if (r31 == 0) goto L_0x05e6
            if (r10 >= r0) goto L_0x05e6
            r34 = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r11)
            r8.append(r6)
            r11 = r31
            r31 = r15
            java.lang.String r15 = r11.annotations
            r8.append(r15)
            java.lang.String r15 = r11.javaName
            r8.append(r15)
            java.lang.String r15 = " "
            r8.append(r15)
            int r15 = r10 + 105
            char r15 = (char) r15
            r8.append(r15)
            java.lang.String r8 = r8.toString()
            r35 = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r14)
            r8.append(r6)
            r8.append(r15)
            java.lang.String r14 = r8.toString()
            int r10 = r10 + 1
            r6 = r4
            r15 = r31
            r8 = r34
            r31 = r11
            r11 = r35
            goto L_0x0598
        L_0x05e6:
            r34 = r8
            r8 = r31
            r31 = r15
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r10 = r5.text
            r4.append(r10)
            java.lang.String r10 = "    public boolean empty("
            r4.append(r10)
            r4.append(r11)
            java.lang.String r10 = ") { return size("
            r4.append(r10)
            r4.append(r14)
            java.lang.String r10 = ") == 0; }\n    public native "
            r4.append(r10)
            r4.append(r9)
            java.lang.String r10 = "long size("
            r4.append(r10)
            r4.append(r11)
            r4.append(r2)
            if (r12 != 0) goto L_0x061e
            r2 = r18
            goto L_0x0652
        L_0x061e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r10 = "    public void clear("
            r2.append(r10)
            r2.append(r11)
            java.lang.String r10 = ") { resize("
            r2.append(r10)
            r2.append(r14)
            r2.append(r6)
            java.lang.String r10 = "0); }\n    public native "
            r2.append(r10)
            r2.append(r9)
            java.lang.String r9 = "void resize("
            r2.append(r9)
            r2.append(r11)
            r2.append(r6)
            java.lang.String r6 = "@Cast(\"size_t\") long n);\n"
            r2.append(r6)
            java.lang.String r2 = r2.toString()
        L_0x0652:
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r5.text = r2
            int r0 = r0 + 1
            r15 = r31
            r6 = r33
            r31 = r8
            r8 = r34
            goto L_0x0556
        L_0x0667:
            r33 = r6
            r34 = r8
            r8 = r31
            r31 = r15
            r6 = r18
            r9 = r6
            r0 = 0
        L_0x0673:
            if (r8 == 0) goto L_0x069f
            if (r0 >= r7) goto L_0x069f
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r6)
            r10.append(r9)
            java.lang.String r6 = r8.annotations
            r10.append(r6)
            java.lang.String r6 = r8.javaName
            r10.append(r6)
            java.lang.String r6 = " "
            r10.append(r6)
            int r6 = r0 + 105
            char r6 = (char) r6
            r10.append(r6)
            java.lang.String r6 = r10.toString()
            int r0 = r0 + 1
            r9 = r4
            goto L_0x0673
        L_0x069f:
            java.lang.String r0 = "array"
            java.lang.String r10 = "}\n"
            java.lang.String r11 = " public native "
            java.lang.String r14 = " put("
            if (r7 == 0) goto L_0x06ab
            if (r8 == 0) goto L_0x0839
        L_0x06ab:
            if (r1 == 0) goto L_0x0839
            if (r3 == 0) goto L_0x0839
            if (r7 != 0) goto L_0x06b8
            java.lang.String r15 = "@MemberGetter "
            r36 = r0
            r35 = r10
            goto L_0x06ee
        L_0x06b8:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r35 = r10
            java.lang.String r10 = "@Index("
            r15.append(r10)
            r10 = 1
            if (r7 <= r10) goto L_0x06de
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r36 = r0
            java.lang.String r0 = "value = "
            r10.append(r0)
            r10.append(r7)
            r10.append(r4)
            java.lang.String r0 = r10.toString()
            goto L_0x06e2
        L_0x06de:
            r36 = r0
            r0 = r18
        L_0x06e2:
            r15.append(r0)
            java.lang.String r0 = "function = \"at\") "
            r15.append(r0)
            java.lang.String r15 = r15.toString()
        L_0x06ee:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r10 = r5.text
            r0.append(r10)
            java.lang.String r10 = "\n    "
            r0.append(r10)
            r0.append(r15)
            java.lang.String r10 = "public native "
            r0.append(r10)
            java.lang.String r10 = r1.annotations
            r0.append(r10)
            java.lang.String r10 = r1.javaName
            r0.append(r10)
            java.lang.String r10 = " first("
            r0.append(r10)
            r0.append(r6)
            java.lang.String r10 = "); public native "
            r0.append(r10)
            java.lang.String r10 = r13.javaName
            r0.append(r10)
            java.lang.String r10 = " first("
            r0.append(r10)
            r0.append(r6)
            r0.append(r9)
            java.lang.String r10 = r1.javaName
            r37 = r4
            java.lang.String r4 = r1.javaName
            r38 = r12
            r12 = 32
            int r4 = r4.lastIndexOf(r12)
            r12 = 1
            int r4 = r4 + r12
            java.lang.String r4 = r10.substring(r4)
            r0.append(r4)
            java.lang.String r4 = " first);\n    "
            r0.append(r4)
            r0.append(r15)
            java.lang.String r4 = "public native "
            r0.append(r4)
            java.lang.String r4 = r3.annotations
            r0.append(r4)
            java.lang.String r4 = r3.javaName
            r0.append(r4)
            java.lang.String r4 = " second("
            r0.append(r4)
            r0.append(r6)
            java.lang.String r4 = ");  public native "
            r0.append(r4)
            java.lang.String r4 = r13.javaName
            r0.append(r4)
            java.lang.String r4 = " second("
            r0.append(r4)
            r0.append(r6)
            r0.append(r9)
            java.lang.String r4 = r3.javaName
            java.lang.String r10 = r3.javaName
            r12 = 32
            int r10 = r10.lastIndexOf(r12)
            r12 = 1
            int r10 = r10 + r12
            java.lang.String r4 = r4.substring(r10)
            r0.append(r4)
            java.lang.String r4 = " second);\n"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            r0 = 1
        L_0x0796:
            if (r32 != 0) goto L_0x07e2
            java.lang.String[] r4 = r1.javaNames
            if (r4 == 0) goto L_0x07e2
            java.lang.String[] r4 = r1.javaNames
            int r4 = r4.length
            if (r0 >= r4) goto L_0x07e2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r10 = r5.text
            r4.append(r10)
            java.lang.String r10 = "    @MemberSetter @Index"
            r4.append(r10)
            r10 = r26
            r4.append(r10)
            r4.append(r11)
            java.lang.String r12 = r13.javaName
            r4.append(r12)
            java.lang.String r12 = " first("
            r4.append(r12)
            r4.append(r6)
            r4.append(r9)
            java.lang.String r12 = r1.annotations
            r4.append(r12)
            java.lang.String[] r12 = r1.javaNames
            r12 = r12[r0]
            r4.append(r12)
            java.lang.String r12 = " first);\n"
            r4.append(r12)
            java.lang.String r4 = r4.toString()
            r5.text = r4
            int r0 = r0 + 1
            goto L_0x0796
        L_0x07e2:
            r10 = r26
            r0 = 1
        L_0x07e5:
            if (r32 != 0) goto L_0x082f
            java.lang.String[] r4 = r3.javaNames
            if (r4 == 0) goto L_0x082f
            java.lang.String[] r4 = r3.javaNames
            int r4 = r4.length
            if (r0 >= r4) goto L_0x082f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r12 = r5.text
            r4.append(r12)
            java.lang.String r12 = "    @MemberSetter @Index"
            r4.append(r12)
            r4.append(r10)
            r4.append(r11)
            java.lang.String r12 = r13.javaName
            r4.append(r12)
            java.lang.String r12 = " second("
            r4.append(r12)
            r4.append(r6)
            r4.append(r9)
            java.lang.String r12 = r3.annotations
            r4.append(r12)
            java.lang.String[] r12 = r3.javaNames
            r12 = r12[r0]
            r4.append(r12)
            java.lang.String r12 = " second);\n"
            r4.append(r12)
            java.lang.String r4 = r4.toString()
            r5.text = r4
            int r0 = r0 + 1
            goto L_0x07e5
        L_0x082f:
            r24 = r1
            r26 = r3
            r22 = r14
            r15 = r31
            goto L_0x0c0a
        L_0x0839:
            r36 = r0
            r37 = r4
            r35 = r10
            r38 = r12
            r10 = r26
            if (r8 == 0) goto L_0x0902
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r5.text
            r0.append(r4)
            java.lang.String r4 = "\n    @Index"
            r0.append(r4)
            r0.append(r10)
            r0.append(r11)
            r15 = r31
            java.lang.String r4 = r15.annotations
            r0.append(r4)
            java.lang.String r4 = r15.javaName
            r0.append(r4)
            java.lang.String r4 = " get("
            r0.append(r4)
            r0.append(r6)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            if (r32 != 0) goto L_0x08b7
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r5.text
            r0.append(r4)
            java.lang.String r4 = "    public native "
            r0.append(r4)
            java.lang.String r4 = r13.javaName
            r0.append(r4)
            r0.append(r14)
            r0.append(r6)
            r0.append(r9)
            java.lang.String r4 = r15.javaName
            java.lang.String r12 = r15.javaName
            r26 = r3
            r3 = 32
            int r12 = r12.lastIndexOf(r3)
            r3 = 1
            int r12 = r12 + r3
            java.lang.String r3 = r4.substring(r12)
            r0.append(r3)
            java.lang.String r3 = " value);\n"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            goto L_0x08b9
        L_0x08b7:
            r26 = r3
        L_0x08b9:
            r0 = 1
        L_0x08ba:
            if (r32 != 0) goto L_0x0906
            java.lang.String[] r3 = r15.javaNames
            if (r3 == 0) goto L_0x0906
            java.lang.String[] r3 = r15.javaNames
            int r3 = r3.length
            if (r0 >= r3) goto L_0x0906
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.text
            r3.append(r4)
            java.lang.String r4 = "    @ValueSetter @Index"
            r3.append(r4)
            r3.append(r10)
            r3.append(r11)
            java.lang.String r4 = r13.javaName
            r3.append(r4)
            r3.append(r14)
            r3.append(r6)
            r3.append(r9)
            java.lang.String r4 = r15.annotations
            r3.append(r4)
            java.lang.String[] r4 = r15.javaNames
            r4 = r4[r0]
            r3.append(r4)
            java.lang.String r4 = " value);\n"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            int r0 = r0 + 1
            goto L_0x08ba
        L_0x0902:
            r26 = r3
            r15 = r31
        L_0x0906:
            r0 = 1
            if (r7 != r0) goto L_0x0a57
            java.lang.String r3 = r25.toLowerCase()
            java.lang.String r4 = "bitset"
            boolean r3 = r3.endsWith(r4)
            if (r3 != 0) goto L_0x0a57
            org.bytedeco.javacpp.tools.Type[] r3 = r13.arguments
            int r3 = r3.length
            if (r3 < r0) goto L_0x0a57
            org.bytedeco.javacpp.tools.Type[] r3 = r13.arguments
            org.bytedeco.javacpp.tools.Type[] r4 = r13.arguments
            int r4 = r4.length
            int r4 = r4 - r0
            r0 = r3[r4]
            java.lang.String r0 = r0.javaName
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0a57
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = r5.text
            r0.append(r3)
            r3 = r30
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            if (r32 != 0) goto L_0x099d
            if (r22 == 0) goto L_0x0968
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r5.text
            r0.append(r4)
            java.lang.String r4 = "    public native @ByVal Iterator insert(@ByVal Iterator pos, "
            r0.append(r4)
            java.lang.String r4 = r15.annotations
            r0.append(r4)
            java.lang.String r4 = r15.javaName
            r0.append(r4)
            java.lang.String r4 = " value);\n    public native @ByVal Iterator erase(@ByVal Iterator pos);\n"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            goto L_0x099d
        L_0x0968:
            if (r8 != 0) goto L_0x099d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r5.text
            r0.append(r4)
            java.lang.String r4 = "    public native void insert("
            r0.append(r4)
            java.lang.String r4 = r15.annotations
            r0.append(r4)
            java.lang.String r4 = r15.javaName
            r0.append(r4)
            java.lang.String r4 = " value);\n    public native void erase("
            r0.append(r4)
            java.lang.String r4 = r15.annotations
            r0.append(r4)
            java.lang.String r4 = r15.javaName
            r0.append(r4)
            java.lang.String r4 = " value);\n"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r5.text = r0
        L_0x099d:
            if (r8 == 0) goto L_0x09cd
            java.lang.String r0 = r8.annotations
            java.lang.String r4 = "@Const"
            boolean r0 = r0.contains(r4)
            if (r0 != 0) goto L_0x09cd
            java.lang.String r0 = r8.annotations
            r4 = r29
            boolean r0 = r0.contains(r4)
            if (r0 != 0) goto L_0x09cd
            boolean r0 = r8.value
            if (r0 != 0) goto L_0x09cd
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r8.annotations
            r0.append(r4)
            r4 = r24
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r8.annotations = r0
            goto L_0x09cf
        L_0x09cd:
            r4 = r24
        L_0x09cf:
            java.lang.String r0 = r15.annotations
            java.lang.String r6 = "@Const"
            boolean r0 = r0.contains(r6)
            if (r0 != 0) goto L_0x09f0
            boolean r0 = r15.value
            if (r0 != 0) goto L_0x09f0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = r15.annotations
            r0.append(r6)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r15.annotations = r0
        L_0x09f0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r5.text
            r0.append(r4)
            java.lang.String r4 = "    public native @ByVal Iterator begin();\n    public native @ByVal Iterator end();\n    @NoOffset @Name(\"iterator\") public static class Iterator extends Pointer {\n        public Iterator(Pointer p) { super(p); }\n        public Iterator() { }\n\n        public native @Name(\"operator ++\") @ByRef Iterator increment();\n        public native @Name(\"operator ==\") boolean equals(@ByRef Iterator it);\n"
            r0.append(r4)
            org.bytedeco.javacpp.tools.Type[] r4 = r13.arguments
            int r4 = r4.length
            r6 = 1
            if (r4 <= r6) goto L_0x0a2b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "        public native @Name(\"operator *().first\") @MemberGetter "
            r4.append(r6)
            java.lang.String r6 = r8.annotations
            r4.append(r6)
            java.lang.String r6 = r8.javaName
            r4.append(r6)
            java.lang.String r6 = " first();\n        public native @Name(\"operator *().second\") @MemberGetter "
            r4.append(r6)
            java.lang.String r6 = r15.annotations
            r4.append(r6)
            java.lang.String r6 = r15.javaName
            r4.append(r6)
            java.lang.String r6 = " second();\n"
            goto L_0x0a41
        L_0x0a2b:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "        public native @Name(\"operator *\") "
            r4.append(r6)
            java.lang.String r6 = r15.annotations
            r4.append(r6)
            java.lang.String r6 = r15.javaName
            r4.append(r6)
            java.lang.String r6 = " get();\n"
        L_0x0a41:
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r4 = "    }\n"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            goto L_0x0a59
        L_0x0a57:
            r3 = r30
        L_0x0a59:
            if (r38 == 0) goto L_0x0c04
            java.lang.String r0 = r15.javaName
            java.lang.String r4 = r15.javaName
            r6 = 32
            int r4 = r4.lastIndexOf(r6)
            r6 = 1
            int r4 = r4 + r6
            java.lang.String r0 = r0.substring(r4)
            r15.javaName = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = r5.text
            r0.append(r4)
            java.lang.String r4 = "\n    public "
            r0.append(r4)
            java.lang.String r4 = r15.javaName
            r0.append(r4)
            r4 = r34
            r0.append(r4)
            java.lang.String r6 = "[] get() {\n"
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            java.lang.String r0 = "        "
            r11 = r4
            r9 = r18
            r10 = r9
            r12 = r10
            r6 = 0
        L_0x0a99:
            if (r6 >= r7) goto L_0x0b8b
            r30 = r3
            int r3 = r6 + 105
            char r3 = (char) r3
            r22 = r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r34 = r4
            java.lang.String r4 = r5.text
            r14.append(r4)
            r14.append(r0)
            if (r6 != 0) goto L_0x0acc
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r24 = r1
            java.lang.String r1 = r15.javaName
            r4.append(r1)
            r4.append(r11)
            java.lang.String r1 = "[] "
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            goto L_0x0ad0
        L_0x0acc:
            r24 = r1
            r1 = r18
        L_0x0ad0:
            r14.append(r1)
            r1 = r36
            r14.append(r1)
            r14.append(r9)
            java.lang.String r4 = " = new "
            r14.append(r4)
            java.lang.String r4 = r15.javaName
            r14.append(r4)
            java.lang.String r4 = "[size("
            r14.append(r4)
            r14.append(r10)
            java.lang.String r4 = ") < Integer.MAX_VALUE ? (int)size("
            r14.append(r4)
            r14.append(r10)
            java.lang.String r4 = ") : Integer.MAX_VALUE]"
            r14.append(r4)
            r14.append(r11)
            java.lang.String r4 = ";\n"
            r14.append(r4)
            r14.append(r0)
            java.lang.String r4 = "for (int "
            r14.append(r4)
            r14.append(r3)
            java.lang.String r4 = " = 0; "
            r14.append(r4)
            r14.append(r3)
            java.lang.String r4 = " < array"
            r14.append(r4)
            r14.append(r9)
            java.lang.String r4 = ".length; "
            r14.append(r4)
            r14.append(r3)
            java.lang.String r4 = "++) {\n"
            r14.append(r4)
            java.lang.String r4 = r14.toString()
            r5.text = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r0)
            java.lang.String r0 = "    "
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            java.lang.String r9 = "["
            r4.append(r9)
            r4.append(r3)
            java.lang.String r9 = "]"
            r4.append(r9)
            java.lang.String r9 = r4.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r10)
            r4.append(r12)
            r4.append(r3)
            java.lang.String r10 = r4.toString()
            int r3 = r11.length()
            r4 = 2
            if (r3 >= r4) goto L_0x0b76
            r11 = r18
            goto L_0x0b7b
        L_0x0b76:
            java.lang.String r3 = r11.substring(r4)
            r11 = r3
        L_0x0b7b:
            int r6 = r6 + 1
            r36 = r1
            r14 = r22
            r1 = r24
            r3 = r30
            r4 = r34
            r12 = r37
            goto L_0x0a99
        L_0x0b8b:
            r24 = r1
            r30 = r3
            r34 = r4
            r22 = r14
            r1 = r36
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.text
            r3.append(r4)
            r3.append(r0)
            r3.append(r1)
            r3.append(r9)
            java.lang.String r4 = " = get("
            r3.append(r4)
            r3.append(r10)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            r3 = 0
        L_0x0bba:
            if (r3 >= r7) goto L_0x0bdc
            r4 = 4
            java.lang.String r0 = r0.substring(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = r5.text
            r4.append(r6)
            r4.append(r0)
            r6 = r35
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r5.text = r4
            int r3 = r3 + 1
            goto L_0x0bba
        L_0x0bdc:
            r6 = r35
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = r5.text
            r0.append(r3)
            java.lang.String r3 = "        return array;\n    }\n    @Override public String toString() {\n        return java.util.Arrays."
            r0.append(r3)
            r3 = 2
            if (r7 >= r3) goto L_0x0bf3
            java.lang.String r3 = "toString"
            goto L_0x0bf5
        L_0x0bf3:
            java.lang.String r3 = "deepToString"
        L_0x0bf5:
            r0.append(r3)
            java.lang.String r3 = "(get());\n    }\n"
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            goto L_0x0c0e
        L_0x0c04:
            r24 = r1
            r30 = r3
            r22 = r14
        L_0x0c0a:
            r6 = r35
            r1 = r36
        L_0x0c0e:
            if (r32 != 0) goto L_0x0de1
            if (r7 == 0) goto L_0x0c1a
            org.bytedeco.javacpp.tools.Type[] r0 = r13.arguments
            int r0 = r0.length
            r3 = 1
            if (r0 != r3) goto L_0x0de1
            if (r8 == 0) goto L_0x0de1
        L_0x0c1a:
            if (r24 == 0) goto L_0x0de1
            if (r26 == 0) goto L_0x0de1
            r0 = r24
            java.lang.String[] r1 = r0.javaNames
            if (r1 == 0) goto L_0x0c2c
            java.lang.String[] r0 = r0.javaNames
            r3 = r0
            r8 = r26
            r1 = 1
            r4 = 0
            goto L_0x0c36
        L_0x0c2c:
            r1 = 1
            java.lang.String[] r3 = new java.lang.String[r1]
            java.lang.String r0 = r0.javaName
            r4 = 0
            r3[r4] = r0
            r8 = r26
        L_0x0c36:
            java.lang.String[] r0 = r8.javaNames
            if (r0 == 0) goto L_0x0c3d
            java.lang.String[] r0 = r8.javaNames
            goto L_0x0c43
        L_0x0c3d:
            java.lang.String[] r0 = new java.lang.String[r1]
            java.lang.String r1 = r8.javaName
            r0[r4] = r1
        L_0x0c43:
            r4 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r9 = r34
            r0.append(r9)
            if (r7 <= 0) goto L_0x0c53
            r11 = r28
            goto L_0x0c55
        L_0x0c53:
            r11 = r18
        L_0x0c55:
            r0.append(r11)
            java.lang.String r10 = r0.toString()
            r0 = 0
        L_0x0c5d:
            int r1 = r3.length
            if (r0 < r1) goto L_0x0c63
            int r1 = r4.length
            if (r0 >= r1) goto L_0x0fe8
        L_0x0c63:
            int r1 = r3.length
            r8 = 1
            int r1 = r1 - r8
            int r1 = java.lang.Math.min(r0, r1)
            r1 = r3[r1]
            int r9 = r4.length
            int r9 = r9 - r8
            int r9 = java.lang.Math.min(r0, r9)
            r9 = r4[r9]
            r11 = 32
            int r12 = r1.lastIndexOf(r11)
            int r12 = r12 + r8
            java.lang.String r1 = r1.substring(r12)
            int r12 = r9.lastIndexOf(r11)
            int r12 = r12 + r8
            java.lang.String r8 = r9.substring(r12)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = r5.text
            r9.append(r11)
            java.lang.String r11 = "\n    public "
            r9.append(r11)
            java.lang.String r11 = r13.javaName
            r9.append(r11)
            r11 = r22
            r9.append(r11)
            r9.append(r1)
            r9.append(r10)
            java.lang.String r1 = " firstValue, "
            r9.append(r1)
            r9.append(r8)
            r9.append(r10)
            java.lang.String r1 = " secondValue) {\n"
            r9.append(r1)
            java.lang.String r1 = r9.toString()
            r5.text = r1
            java.lang.String r1 = "        "
            r9 = r18
            r12 = r9
            r14 = r12
            r8 = 0
        L_0x0cc4:
            if (r8 >= r7) goto L_0x0d5b
            int r15 = r8 + 105
            char r15 = (char) r15
            r22 = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r24 = r4
            java.lang.String r4 = r5.text
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = "for (int "
            r3.append(r4)
            r3.append(r15)
            java.lang.String r4 = " = 0; "
            r3.append(r4)
            r3.append(r15)
            java.lang.String r4 = " < firstValue"
            r3.append(r4)
            r3.append(r14)
            java.lang.String r4 = ".length && "
            r3.append(r4)
            r3.append(r15)
            java.lang.String r4 = " < secondValue"
            r3.append(r4)
            r3.append(r14)
            java.lang.String r4 = ".length; "
            r3.append(r4)
            r3.append(r15)
            java.lang.String r4 = "++) {\n"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = "    "
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            java.lang.String r4 = "["
            r3.append(r4)
            r3.append(r15)
            java.lang.String r4 = "]"
            r3.append(r4)
            java.lang.String r14 = r3.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r9)
            r3.append(r12)
            r3.append(r15)
            java.lang.String r9 = r3.toString()
            int r8 = r8 + 1
            r3 = r22
            r4 = r24
            r12 = r37
            goto L_0x0cc4
        L_0x0d5b:
            r22 = r3
            r24 = r4
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.text
            r3.append(r4)
            r3.append(r1)
            java.lang.String r4 = "first("
            r3.append(r4)
            r3.append(r9)
            r3.append(r12)
            java.lang.String r4 = "firstValue"
            r3.append(r4)
            r3.append(r14)
            r3.append(r2)
            r3.append(r1)
            java.lang.String r4 = "second("
            r3.append(r4)
            r3.append(r9)
            r3.append(r12)
            java.lang.String r4 = "secondValue"
            r3.append(r4)
            r3.append(r14)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            r4 = 0
        L_0x0da2:
            if (r4 >= r7) goto L_0x0dc2
            r3 = 4
            java.lang.String r1 = r1.substring(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r8 = r5.text
            r3.append(r8)
            r3.append(r1)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            int r4 = r4 + 1
            goto L_0x0da2
        L_0x0dc2:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r5.text
            r1.append(r3)
            java.lang.String r3 = "        return this;\n    }\n"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r5.text = r1
            int r0 = r0 + 1
            r3 = r22
            r4 = r24
            r22 = r11
            goto L_0x0c5d
        L_0x0de1:
            r11 = r22
            r0 = r24
            r8 = r26
            r9 = r34
            if (r38 == 0) goto L_0x0fe8
            if (r0 != 0) goto L_0x0fe8
            if (r8 != 0) goto L_0x0fe8
            java.lang.String[] r0 = r15.javaNames
            if (r0 == 0) goto L_0x0df8
            java.lang.String[] r0 = r15.javaNames
            r4 = 1
            r8 = 0
            goto L_0x0e00
        L_0x0df8:
            r4 = 1
            java.lang.String[] r0 = new java.lang.String[r4]
            java.lang.String r3 = r15.javaName
            r8 = 0
            r0[r8] = r3
        L_0x0e00:
            int r3 = r0.length
            r10 = 0
            r12 = 1
        L_0x0e03:
            if (r10 >= r3) goto L_0x0fe8
            r14 = r0[r10]
            r15 = 32
            int r16 = r14.lastIndexOf(r15)
            int r8 = r16 + 1
            java.lang.String r8 = r14.substring(r8)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r4 = r5.text
            r14.append(r4)
            r4 = r30
            r14.append(r4)
            java.lang.String r14 = r14.toString()
            r5.text = r14
            r14 = 2
            if (r7 >= r14) goto L_0x0e8b
            if (r12 == 0) goto L_0x0e53
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r14 = r5.text
            r12.append(r14)
            r14 = r33
            r12.append(r14)
            r12.append(r8)
            java.lang.String r15 = " pop_back() {\n        long size = size();\n        "
            r12.append(r15)
            r12.append(r8)
            java.lang.String r15 = " value = get(size - 1);\n        resize(size - 1);\n        return value;\n    }\n"
            r12.append(r15)
            java.lang.String r12 = r12.toString()
            r5.text = r12
            goto L_0x0e55
        L_0x0e53:
            r14 = r33
        L_0x0e55:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r15 = r5.text
            r12.append(r15)
            r12.append(r14)
            java.lang.String r15 = r13.javaName
            r12.append(r15)
            java.lang.String r15 = " push_back("
            r12.append(r15)
            r12.append(r8)
            java.lang.String r15 = " value) {\n        long size = size();\n        resize(size + 1);\n        return put(size, value);\n    }\n    public "
            r12.append(r15)
            java.lang.String r15 = r13.javaName
            r12.append(r15)
            r12.append(r11)
            r12.append(r8)
            java.lang.String r15 = " value) {\n        if (size() != 1) { resize(1); }\n        return put(0, value);\n    }\n"
            r12.append(r15)
            java.lang.String r12 = r12.toString()
            r5.text = r12
            goto L_0x0e8d
        L_0x0e8b:
            r14 = r33
        L_0x0e8d:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r15 = r5.text
            r12.append(r15)
            r12.append(r14)
            java.lang.String r15 = r13.javaName
            r12.append(r15)
            r12.append(r11)
            r12.append(r8)
            r12.append(r9)
            java.lang.String r8 = " ... array) {\n"
            r12.append(r8)
            java.lang.String r8 = r12.toString()
            r5.text = r8
            java.lang.String r8 = "        "
            r22 = r0
            r24 = r3
            r12 = r8
            r0 = r18
            r3 = r0
            r15 = r3
            r8 = 0
        L_0x0ebf:
            if (r8 >= r7) goto L_0x0f73
            r30 = r4
            int r4 = r8 + 105
            char r4 = (char) r4
            r34 = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r26 = r11
            java.lang.String r11 = r5.text
            r9.append(r11)
            r9.append(r12)
            java.lang.String r11 = "if (size("
            r9.append(r11)
            r9.append(r15)
            java.lang.String r11 = ") != array"
            r9.append(r11)
            r9.append(r3)
            java.lang.String r11 = ".length) { resize("
            r9.append(r11)
            r9.append(r15)
            r9.append(r0)
            r9.append(r1)
            r9.append(r3)
            java.lang.String r11 = ".length); }\n"
            r9.append(r11)
            r9.append(r12)
            java.lang.String r11 = "for (int "
            r9.append(r11)
            r9.append(r4)
            java.lang.String r11 = " = 0; "
            r9.append(r11)
            r9.append(r4)
            java.lang.String r11 = " < array"
            r9.append(r11)
            r9.append(r3)
            java.lang.String r11 = ".length; "
            r9.append(r11)
            r9.append(r4)
            java.lang.String r11 = "++) {\n"
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            r5.text = r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r12)
            java.lang.String r11 = "    "
            r9.append(r11)
            java.lang.String r12 = r9.toString()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r3)
            java.lang.String r3 = "["
            r9.append(r3)
            r9.append(r4)
            java.lang.String r3 = "]"
            r9.append(r3)
            java.lang.String r3 = r9.toString()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r15)
            r9.append(r0)
            r9.append(r4)
            java.lang.String r15 = r9.toString()
            int r8 = r8 + 1
            r11 = r26
            r4 = r30
            r9 = r34
            r0 = r37
            goto L_0x0ebf
        L_0x0f73:
            r30 = r4
            r34 = r9
            r26 = r11
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = r5.text
            r4.append(r8)
            r4.append(r12)
            java.lang.String r8 = "put("
            r4.append(r8)
            r4.append(r15)
            r4.append(r0)
            r4.append(r1)
            r4.append(r3)
            r4.append(r2)
            java.lang.String r0 = r4.toString()
            r5.text = r0
            r4 = 0
        L_0x0fa1:
            if (r4 >= r7) goto L_0x0fc1
            r0 = 4
            java.lang.String r12 = r12.substring(r0)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r8 = r5.text
            r3.append(r8)
            r3.append(r12)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            int r4 = r4 + 1
            goto L_0x0fa1
        L_0x0fc1:
            r0 = 4
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.text
            r3.append(r4)
            java.lang.String r4 = "        return this;\n    }\n"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            int r10 = r10 + 1
            r33 = r14
            r0 = r22
            r3 = r24
            r11 = r26
            r9 = r34
            r4 = 1
            r8 = 0
            r12 = 0
            goto L_0x0e03
        L_0x0fe8:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r5.text
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r5.text = r0
            r0 = r41
            r0.add((org.bytedeco.javacpp.tools.Declaration) r5)
            r0 = r39
            r1 = r19
            r3 = r21
            r2 = r25
            r6 = r27
            goto L_0x0065
        L_0x100c:
            r0 = r41
            r0 = r39
            goto L_0x0065
        L_0x1012:
            r0 = r41
            r0 = r39
            goto L_0x002d
        L_0x1018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.containers(org.bytedeco.javacpp.tools.Context, org.bytedeco.javacpp.tools.DeclarationList):void");
    }

    /* access modifiers changed from: package-private */
    public TemplateMap template(Context context) throws ParserException {
        Token token;
        if (!this.tokens.get().match(Token.TEMPLATE)) {
            return null;
        }
        TemplateMap templateMap = new TemplateMap(context.templateMap);
        this.tokens.next().expect(Character.valueOf(Typography.less));
        while (true) {
            Token next = this.tokens.next();
            if (next.match(Token.EOF)) {
                break;
            }
            if (next.match(Token.CLASS, Token.TYPENAME)) {
                Token next2 = this.tokens.next();
                if (next2.match("...")) {
                    templateMap.variadic = true;
                    next2 = this.tokens.next();
                }
                if (next2.match(5)) {
                    String str = next2.value;
                    templateMap.put(str, templateMap.get(str));
                    next = this.tokens.next();
                }
            } else {
                if (next.match(5)) {
                    Type type = type(context);
                    Token token2 = this.tokens.get();
                    if (token2.match(5)) {
                        String str2 = token2.value;
                        templateMap.put(str2, templateMap.get(str2));
                        next = this.tokens.next();
                    } else {
                        String str3 = type.cppName;
                        templateMap.put(str3, templateMap.get(str3));
                    }
                }
            }
            if (!token.match(',', Character.valueOf(Typography.greater))) {
                token = this.tokens.get();
                int i = 0;
                while (true) {
                    if (token.match(Token.EOF)) {
                        break;
                    }
                    if (i == 0) {
                        if (token.match(',', Character.valueOf(Typography.greater))) {
                            break;
                        }
                    }
                    if (token.match(Character.valueOf(Typography.less), '(')) {
                        i++;
                    } else {
                        if (token.match(Character.valueOf(Typography.greater), ')')) {
                            i--;
                        }
                    }
                    token = this.tokens.next();
                }
            }
            if (token.expect(',', Character.valueOf(Typography.greater)).match(Character.valueOf(Typography.greater))) {
                if (!this.tokens.next().match(Token.TEMPLATE)) {
                    break;
                }
                this.tokens.next().expect(Character.valueOf(Typography.less));
            }
        }
        return templateMap;
    }

    /* access modifiers changed from: package-private */
    public Type[] templateArguments(Context context) throws ParserException {
        Token token;
        Token token2 = this.tokens.get();
        Character valueOf = Character.valueOf(Typography.less);
        if (!token2.match(valueOf)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        do {
            if (this.tokens.next().match(Token.EOF)) {
                break;
            }
            Type type = type(context);
            arrayList.add(type);
            token = this.tokens.get();
            if (!token.match(',', Character.valueOf(Typography.greater))) {
                token = this.tokens.get();
                int i = 0;
                while (true) {
                    if (token.match(Token.EOF)) {
                        break;
                    }
                    if (i == 0) {
                        if (token.match(',', Character.valueOf(Typography.greater))) {
                            break;
                        }
                    }
                    if (token.match(valueOf, '(')) {
                        i++;
                    } else {
                        if (token.match(Character.valueOf(Typography.greater), ')')) {
                            i--;
                        }
                    }
                    type.cppName += token;
                    if (token.match(Token.CONST, Token.__CONST)) {
                        type.cppName += " ";
                    }
                    token = this.tokens.next();
                }
                if (type.cppName.endsWith(Marker.ANY_MARKER)) {
                    type.javaName = "PointerPointer";
                    type.annotations += "@Cast(\"" + type.cppName + "*\") ";
                }
            }
        } while (!token.expect(',', Character.valueOf(Typography.greater)).match(Character.valueOf(Typography.greater)));
        return (Type[]) arrayList.toArray(new Type[arrayList.size()]);
    }

    /* access modifiers changed from: package-private */
    public Type type(Context context) throws ParserException {
        return type(context, false);
    }

    /* JADX WARNING: type inference failed for: r5v125 */
    /* JADX WARNING: type inference failed for: r5v127 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x052d, code lost:
        r22 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x0a5b, code lost:
        if (r6.startsWith(r3 + "<") != false) goto L_0x0a5d;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r5v124, types: [boolean, int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.Type type(org.bytedeco.javacpp.tools.Context r28, boolean r29) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            org.bytedeco.javacpp.tools.Type r2 = new org.bytedeco.javacpp.tools.Type
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
        L_0x000e:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get()
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.EOF
            r8 = 0
            r6[r8] = r7
            boolean r6 = r4.match(r6)
            java.lang.String r7 = "<"
            java.lang.String r9 = " "
            java.lang.String r14 = "&"
            java.lang.String r15 = ""
            java.lang.String r13 = "const "
            java.lang.String r10 = "*"
            java.lang.String r12 = " const"
            java.lang.String r11 = "::"
            if (r6 != 0) goto L_0x052d
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r6[r8] = r11
            boolean r6 = r4.match(r6)
            if (r6 == 0) goto L_0x0076
            org.bytedeco.javacpp.tools.InfoMap r6 = r0.infoMap
            java.lang.String r5 = r2.cppName
            org.bytedeco.javacpp.tools.Info r5 = r6.getFirst(r5, r8)
            if (r5 == 0) goto L_0x0061
            java.lang.String[] r6 = r5.pointerTypes
            if (r6 == 0) goto L_0x0061
            java.lang.String[] r5 = r5.pointerTypes
            int r5 = r5.length
            if (r5 <= 0) goto L_0x0061
            java.lang.String r5 = r2.cppName
            boolean r5 = r5.contains(r11)
            if (r5 != 0) goto L_0x0061
            java.lang.String r5 = r4.spacing
            int r5 = r5.length()
            if (r5 <= 0) goto L_0x0061
            goto L_0x052d
        L_0x0061:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r2.cppName
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r2.cppName = r4
            goto L_0x050c
        L_0x0076:
            java.lang.Object[] r6 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.DECLTYPE
            r6[r8] = r5
            boolean r5 = r4.match(r6)
            if (r5 == 0) goto L_0x0110
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r2.cppName
            r5.append(r6)
            java.lang.String r4 = r4.toString()
            r5.append(r4)
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.next()
            r22 = r15
            r6 = 1
            java.lang.Object[] r15 = new java.lang.Object[r6]
            r19 = 40
            java.lang.Character r21 = java.lang.Character.valueOf(r19)
            r15[r8] = r21
            org.bytedeco.javacpp.tools.Token r4 = r4.expect(r15)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r2.cppName = r4
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.next()
            r5 = 1
        L_0x00ba:
            java.lang.Object[] r15 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.EOF
            r15[r8] = r21
            boolean r15 = r4.match(r15)
            if (r15 != 0) goto L_0x0109
            java.lang.Object[] r15 = new java.lang.Object[r6]
            r19 = 40
            java.lang.Character r21 = java.lang.Character.valueOf(r19)
            r15[r8] = r21
            boolean r15 = r4.match(r15)
            if (r15 == 0) goto L_0x00d9
            int r5 = r5 + 1
            goto L_0x00eb
        L_0x00d9:
            java.lang.Object[] r15 = new java.lang.Object[r6]
            r6 = 41
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            r15[r8] = r6
            boolean r6 = r4.match(r15)
            if (r6 == 0) goto L_0x00eb
            int r5 = r5 + -1
        L_0x00eb:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r15 = r2.cppName
            r6.append(r15)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r2.cppName = r4
            if (r5 != 0) goto L_0x0101
            goto L_0x0109
        L_0x0101:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.next()
            r6 = 1
            goto L_0x00ba
        L_0x0109:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x052f
        L_0x0110:
            r22 = r15
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r5 = 60
            java.lang.Character r15 = java.lang.Character.valueOf(r5)
            r6[r8] = r15
            boolean r5 = r4.match(r6)
            if (r5 == 0) goto L_0x0265
            org.bytedeco.javacpp.tools.Type[] r4 = r27.templateArguments(r28)
            r2.arguments = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r2.cppName
            r4.append(r5)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r2.cppName = r4
            org.bytedeco.javacpp.tools.Type[] r4 = r2.arguments
            int r5 = r4.length
            r15 = r22
            r6 = 0
        L_0x0142:
            if (r6 >= r5) goto L_0x0244
            r7 = r4[r6]
            if (r7 != 0) goto L_0x014e
            r16 = r4
            r17 = r5
            goto L_0x023b
        L_0x014e:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = r2.cppName
            r9.append(r11)
            r9.append(r15)
            java.lang.String r9 = r9.toString()
            r2.cppName = r9
            org.bytedeco.javacpp.tools.InfoMap r9 = r0.infoMap
            java.lang.String r11 = r7.cppName
            org.bytedeco.javacpp.tools.Info r9 = r9.getFirst(r11)
            if (r9 == 0) goto L_0x0174
            java.lang.String[] r11 = r9.cppTypes
            if (r11 == 0) goto L_0x0174
            java.lang.String[] r9 = r9.cppTypes
            r9 = r9[r8]
            goto L_0x0176
        L_0x0174:
            java.lang.String r9 = r7.cppName
        L_0x0176:
            boolean r11 = r7.constValue
            if (r11 == 0) goto L_0x018f
            boolean r11 = r9.startsWith(r13)
            if (r11 != 0) goto L_0x018f
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r13)
            r11.append(r9)
            java.lang.String r9 = r11.toString()
        L_0x018f:
            r11 = 40
            int r15 = r9.indexOf(r11)
            r11 = 0
        L_0x0196:
            int r8 = r7.indirections
            if (r11 >= r8) goto L_0x01d7
            if (r15 < 0) goto L_0x01bc
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r16 = r4
            r17 = r5
            r4 = 0
            java.lang.String r5 = r9.substring(r4, r15)
            r8.append(r5)
            r8.append(r10)
            java.lang.String r4 = r9.substring(r15)
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            goto L_0x01cf
        L_0x01bc:
            r16 = r4
            r17 = r5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
        L_0x01cf:
            r9 = r4
            int r11 = r11 + 1
            r4 = r16
            r5 = r17
            goto L_0x0196
        L_0x01d7:
            r16 = r4
            r17 = r5
            boolean r4 = r7.reference
            if (r4 == 0) goto L_0x020c
            if (r15 < 0) goto L_0x01fd
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = 0
            java.lang.String r8 = r9.substring(r5, r15)
            r4.append(r8)
            r4.append(r14)
            java.lang.String r5 = r9.substring(r15)
            r4.append(r5)
            java.lang.String r9 = r4.toString()
            goto L_0x020c
        L_0x01fd:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r4.append(r14)
            java.lang.String r9 = r4.toString()
        L_0x020c:
            boolean r4 = r7.constPointer
            if (r4 == 0) goto L_0x0225
            boolean r4 = r9.endsWith(r12)
            if (r4 != 0) goto L_0x0225
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r4.append(r12)
            java.lang.String r9 = r4.toString()
        L_0x0225:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r2.cppName
            r4.append(r5)
            r4.append(r9)
            java.lang.String r4 = r4.toString()
            r2.cppName = r4
            java.lang.String r4 = ","
            r15 = r4
        L_0x023b:
            int r6 = r6 + 1
            r4 = r16
            r5 = r17
            r8 = 0
            goto L_0x0142
        L_0x0244:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r2.cppName
            r4.append(r5)
            java.lang.String r5 = r2.cppName
            java.lang.String r6 = ">"
            boolean r5 = r5.endsWith(r6)
            if (r5 == 0) goto L_0x025a
            java.lang.String r6 = " >"
        L_0x025a:
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r2.cppName = r4
            goto L_0x050c
        L_0x0265:
            r5 = 3
            java.lang.Object[] r6 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.CONST
            r8 = 0
            r6[r8] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.__CONST
            r15 = 1
            r6[r15] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.CONSTEXPR
            r15 = 2
            r6[r15] = r5
            boolean r5 = r4.match(r6)
            if (r5 == 0) goto L_0x02b7
            java.lang.String r5 = r2.cppName
            r6 = 60
            int r5 = r5.lastIndexOf(r6)
            if (r5 < 0) goto L_0x028e
            java.lang.String r6 = r2.cppName
            java.lang.String r5 = r6.substring(r8, r5)
            goto L_0x0290
        L_0x028e:
            java.lang.String r5 = r2.cppName
        L_0x0290:
            java.lang.String r5 = r5.trim()
            boolean r5 = r5.contains(r9)
            if (r5 == 0) goto L_0x02a3
            boolean r5 = r2.simple
            if (r5 == 0) goto L_0x029f
            goto L_0x02a3
        L_0x029f:
            r5 = 1
            r2.constPointer = r5
            goto L_0x02a6
        L_0x02a3:
            r5 = 1
            r2.constValue = r5
        L_0x02a6:
            java.lang.Object[] r6 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.CONSTEXPR
            r8 = 0
            r6[r8] = r7
            boolean r4 = r4.match(r6)
            if (r4 == 0) goto L_0x050c
            r2.constExpr = r5
            goto L_0x050c
        L_0x02b7:
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r15 = 42
            java.lang.Character r21 = java.lang.Character.valueOf(r15)
            r6[r8] = r21
            boolean r6 = r4.match(r6)
            if (r6 == 0) goto L_0x02d4
            int r4 = r2.indirections
            int r4 = r4 + r5
            r2.indirections = r4
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x052f
        L_0x02d4:
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r8 = 38
            java.lang.Character r21 = java.lang.Character.valueOf(r8)
            r23 = 0
            r6[r23] = r21
            boolean r6 = r4.match(r6)
            if (r6 == 0) goto L_0x02ef
            r2.reference = r5
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x052f
        L_0x02ef:
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.String r21 = "&&"
            r6[r23] = r21
            boolean r6 = r4.match(r6)
            if (r6 == 0) goto L_0x02fd
            goto L_0x050c
        L_0x02fd:
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r5 = 126(0x7e, float:1.77E-43)
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r6[r23] = r5
            boolean r5 = r4.match(r6)
            java.lang.String r6 = "~"
            if (r5 == 0) goto L_0x0327
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r2.cppName
            r4.append(r5)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r2.cppName = r4
            r5 = 1
            r2.destructor = r5
            goto L_0x050c
        L_0x0327:
            r5 = 1
            java.lang.Object[] r8 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.STATIC
            r23 = 0
            r8[r23] = r21
            boolean r8 = r4.match(r8)
            if (r8 == 0) goto L_0x033a
            r2.staticMember = r5
            goto L_0x050c
        L_0x033a:
            java.lang.Object[] r8 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.OPERATOR
            r8[r23] = r21
            boolean r8 = r4.match(r8)
            if (r8 == 0) goto L_0x0368
            java.lang.String r4 = r2.cppName
            int r4 = r4.length()
            if (r4 != 0) goto L_0x0357
            r2.operator = r5
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x000e
        L_0x0357:
            java.lang.String r4 = r2.cppName
            boolean r4 = r4.endsWith(r11)
            if (r4 == 0) goto L_0x052f
            r2.operator = r5
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x052f
        L_0x0368:
            java.lang.Object[] r8 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.USING
            r23 = 0
            r8[r23] = r21
            boolean r8 = r4.match(r8)
            if (r8 == 0) goto L_0x037a
            r2.using = r5
            goto L_0x050c
        L_0x037a:
            java.lang.Object[] r8 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.FRIEND
            r8[r23] = r21
            boolean r8 = r4.match(r8)
            if (r8 == 0) goto L_0x038a
            r2.friend = r5
            goto L_0x050c
        L_0x038a:
            java.lang.Object[] r8 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.TYPEDEF
            r8[r23] = r21
            boolean r8 = r4.match(r8)
            if (r8 == 0) goto L_0x039a
            r2.typedef = r5
            goto L_0x050c
        L_0x039a:
            java.lang.Object[] r8 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.VIRTUAL
            r8[r23] = r21
            boolean r8 = r4.match(r8)
            if (r8 == 0) goto L_0x03aa
            r2.virtual = r5
            goto L_0x050c
        L_0x03aa:
            r8 = 16
            java.lang.Object[] r8 = new java.lang.Object[r8]
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.ENUM
            r8[r23] = r21
            org.bytedeco.javacpp.tools.Token r21 = org.bytedeco.javacpp.tools.Token.EXPLICIT
            r8[r5] = r21
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.EXTERN
            r20 = 2
            r8[r20] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.INLINE
            r17 = 3
            r8[r17] = r5
            r5 = 4
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.CLASS
            r8[r5] = r25
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.FINAL
            r16 = 5
            r8[r16] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.INTERFACE
            r18 = 6
            r8[r18] = r5
            r5 = 7
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.__INTERFACE
            r8[r5] = r25
            r5 = 8
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.MUTABLE
            r8[r5] = r25
            r5 = 9
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.NAMESPACE
            r8[r5] = r25
            r5 = 10
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.STRUCT
            r8[r5] = r25
            r5 = 11
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.UNION
            r8[r5] = r25
            r5 = 12
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.TYPENAME
            r8[r5] = r25
            r5 = 13
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.REGISTER
            r8[r5] = r25
            r5 = 14
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.THREAD_LOCAL
            r8[r5] = r25
            r5 = 15
            org.bytedeco.javacpp.tools.Token r25 = org.bytedeco.javacpp.tools.Token.VOLATILE
            r8[r5] = r25
            boolean r5 = r4.match(r8)
            if (r5 == 0) goto L_0x0415
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x000e
        L_0x0415:
            org.bytedeco.javacpp.tools.InfoMap r5 = r0.infoMap
            java.lang.String r8 = "basic/types"
            org.bytedeco.javacpp.tools.Info r5 = r5.getFirst(r8)
            java.lang.String[] r5 = r5.cppTypes
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            boolean r5 = r4.match(r5)
            if (r5 == 0) goto L_0x0450
            java.lang.String r5 = r2.cppName
            int r5 = r5.length()
            if (r5 == 0) goto L_0x0433
            boolean r5 = r2.simple
            if (r5 == 0) goto L_0x0450
        L_0x0433:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r2.cppName
            r5.append(r6)
            java.lang.String r4 = r4.value
            r5.append(r4)
            r5.append(r9)
            java.lang.String r4 = r5.toString()
            r2.cppName = r4
            r5 = 1
            r2.simple = r5
            goto L_0x050c
        L_0x0450:
            r5 = 1
            java.lang.Object[] r8 = new java.lang.Object[r5]
            r5 = 5
            java.lang.Integer r25 = java.lang.Integer.valueOf(r5)
            r5 = 0
            r8[r5] = r25
            boolean r5 = r4.match(r8)
            if (r5 == 0) goto L_0x0513
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            int r5 = r5.index
            org.bytedeco.javacpp.tools.Attribute r8 = r27.attribute()
            if (r8 == 0) goto L_0x0489
            boolean r15 = r8.annotation
            if (r15 == 0) goto L_0x0489
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r2.annotations
            r4.append(r5)
            java.lang.String r5 = r8.javaName
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.annotations = r4
            r3.add(r8)
            goto L_0x000e
        L_0x0489:
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            r8.index = r5
            java.lang.String r5 = r2.cppName
            int r5 = r5.length()
            if (r5 == 0) goto L_0x04f7
            java.lang.String r5 = r2.cppName
            boolean r5 = r5.endsWith(r11)
            if (r5 != 0) goto L_0x04f7
            java.lang.String r5 = r2.cppName
            boolean r5 = r5.endsWith(r6)
            if (r5 == 0) goto L_0x04a6
            goto L_0x04f7
        L_0x04a6:
            org.bytedeco.javacpp.tools.InfoMap r4 = r0.infoMap
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            r6 = 1
            org.bytedeco.javacpp.tools.Token r5 = r5.get(r6)
            java.lang.String r5 = r5.value
            org.bytedeco.javacpp.tools.Info r4 = r4.getFirst(r5)
            if (r4 == 0) goto L_0x04bb
            java.lang.String[] r4 = r4.annotations
            if (r4 != 0) goto L_0x052f
        L_0x04bb:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get(r6)
            r5 = 7
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r8 = 42
            java.lang.Character r8 = java.lang.Character.valueOf(r8)
            r15 = 0
            r5[r15] = r8
            r8 = 38
            java.lang.Character r8 = java.lang.Character.valueOf(r8)
            r5[r6] = r8
            r6 = 5
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            r15 = 2
            r5[r15] = r8
            org.bytedeco.javacpp.tools.Token r8 = org.bytedeco.javacpp.tools.Token.CONST
            r15 = 3
            r5[r15] = r8
            r8 = 4
            org.bytedeco.javacpp.tools.Token r15 = org.bytedeco.javacpp.tools.Token.__CONST
            r5[r8] = r15
            org.bytedeco.javacpp.tools.Token r8 = org.bytedeco.javacpp.tools.Token.CONSTEXPR
            r5[r6] = r8
            org.bytedeco.javacpp.tools.Token r6 = org.bytedeco.javacpp.tools.Token.FINAL
            r8 = 6
            r5[r8] = r6
            boolean r4 = r4.match(r5)
            if (r4 != 0) goto L_0x050c
            goto L_0x052f
        L_0x04f7:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r2.cppName
            r5.append(r6)
            java.lang.String r4 = r4.value
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r2.cppName = r4
        L_0x050c:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x000e
        L_0x0513:
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r8 = 125(0x7d, float:1.75E-43)
            java.lang.Character r8 = java.lang.Character.valueOf(r8)
            r15 = 0
            r6[r15] = r8
            boolean r4 = r4.match(r6)
            if (r4 == 0) goto L_0x052f
            r2.anonymous = r5
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.next()
            goto L_0x052f
        L_0x052d:
            r22 = r15
        L_0x052f:
            int r4 = r3.size()
            if (r4 <= 0) goto L_0x0543
            int r4 = r3.size()
            org.bytedeco.javacpp.tools.Attribute[] r4 = new org.bytedeco.javacpp.tools.Attribute[r4]
            java.lang.Object[] r3 = r3.toArray(r4)
            org.bytedeco.javacpp.tools.Attribute[] r3 = (org.bytedeco.javacpp.tools.Attribute[]) r3
            r2.attributes = r3
        L_0x0543:
            java.lang.String r3 = r2.cppName
            java.lang.String r3 = r3.trim()
            r2.cppName = r3
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.String r6 = "..."
            r8 = 0
            r5[r8] = r6
            boolean r3 = r3.match(r5)
            r5 = 0
            if (r3 == 0) goto L_0x0580
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r1.next()
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
            java.lang.Object[] r2 = new java.lang.Object[r4]
            r3 = 5
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r8] = r3
            boolean r1 = r1.match(r2)
            if (r1 == 0) goto L_0x057f
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r1.next()
        L_0x057f:
            return r5
        L_0x0580:
            boolean r3 = r2.operator
            if (r3 == 0) goto L_0x05c4
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r4 = 3
        L_0x058b:
            java.lang.Object[] r6 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r8 = org.bytedeco.javacpp.tools.Token.EOF
            r15 = 0
            r6[r15] = r8
            r8 = 40
            java.lang.Character r15 = java.lang.Character.valueOf(r8)
            r8 = 1
            r6[r8] = r15
            r8 = 59
            java.lang.Character r8 = java.lang.Character.valueOf(r8)
            r15 = 2
            r6[r15] = r8
            boolean r6 = r3.match(r6)
            if (r6 != 0) goto L_0x05c4
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r2.cppName
            r6.append(r8)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            r2.cppName = r3
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            goto L_0x058b
        L_0x05c4:
            java.lang.String r3 = r2.cppName
            boolean r3 = r3.endsWith(r10)
            if (r3 == 0) goto L_0x05e3
            int r3 = r2.indirections
            r4 = 1
            int r3 = r3 + r4
            r2.indirections = r3
            java.lang.String r3 = r2.cppName
            java.lang.String r6 = r2.cppName
            int r6 = r6.length()
            int r6 = r6 - r4
            r8 = 0
            java.lang.String r3 = r3.substring(r8, r6)
            r2.cppName = r3
            goto L_0x05e4
        L_0x05e3:
            r4 = 1
        L_0x05e4:
            java.lang.String r3 = r2.cppName
            boolean r3 = r3.endsWith(r14)
            if (r3 == 0) goto L_0x05fe
            r2.reference = r4
            java.lang.String r3 = r2.cppName
            java.lang.String r6 = r2.cppName
            int r6 = r6.length()
            int r6 = r6 - r4
            r4 = 0
            java.lang.String r3 = r3.substring(r4, r6)
            r2.cppName = r3
        L_0x05fe:
            org.bytedeco.javacpp.tools.TemplateMap r3 = r1.templateMap
            if (r3 == 0) goto L_0x0672
            java.lang.String r3 = r2.cppName
            java.lang.String[] r3 = r3.split(r11)
            r4 = r22
            r2.cppName = r4
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            int r8 = r3.length
            r5 = r4
            r15 = 0
        L_0x0614:
            if (r15 >= r8) goto L_0x065c
            r22 = r4
            r4 = r3[r15]
            r17 = r3
            org.bytedeco.javacpp.tools.TemplateMap r3 = r1.templateMap
            org.bytedeco.javacpp.tools.Type r3 = r3.get(r4)
            r24 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r25 = r8
            java.lang.String r8 = r2.cppName
            r4.append(r8)
            r4.append(r5)
            if (r3 == 0) goto L_0x0638
            java.lang.String r5 = r3.cppName
            goto L_0x063a
        L_0x0638:
            r5 = r24
        L_0x063a:
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.cppName = r4
            if (r3 == 0) goto L_0x0652
            org.bytedeco.javacpp.tools.Type[] r4 = r3.arguments
            if (r4 == 0) goto L_0x0652
            org.bytedeco.javacpp.tools.Type[] r3 = r3.arguments
            java.util.List r3 = java.util.Arrays.asList(r3)
            r6.addAll(r3)
        L_0x0652:
            int r15 = r15 + 1
            r5 = r11
            r3 = r17
            r4 = r22
            r8 = r25
            goto L_0x0614
        L_0x065c:
            r22 = r4
            int r3 = r6.size()
            if (r3 <= 0) goto L_0x0672
            int r3 = r6.size()
            org.bytedeco.javacpp.tools.Type[] r3 = new org.bytedeco.javacpp.tools.Type[r3]
            java.lang.Object[] r3 = r6.toArray(r3)
            org.bytedeco.javacpp.tools.Type[] r3 = (org.bytedeco.javacpp.tools.Type[]) r3
            r2.arguments = r3
        L_0x0672:
            java.lang.String r3 = r2.cppName
            boolean r3 = r3.startsWith(r13)
            if (r3 == 0) goto L_0x0687
            r3 = 1
            r2.constValue = r3
            java.lang.String r4 = r2.cppName
            r5 = 6
            java.lang.String r4 = r4.substring(r5)
            r2.cppName = r4
            goto L_0x0689
        L_0x0687:
            r3 = 1
            r5 = 6
        L_0x0689:
            java.lang.String r4 = r2.cppName
            boolean r4 = r4.endsWith(r12)
            if (r4 == 0) goto L_0x06a3
            r2.constPointer = r3
            java.lang.String r3 = r2.cppName
            java.lang.String r4 = r2.cppName
            int r4 = r4.length()
            int r4 = r4 - r5
            r5 = 0
            java.lang.String r3 = r3.substring(r5, r4)
            r2.cppName = r3
        L_0x06a3:
            java.lang.String r3 = r2.cppName
            boolean r3 = r3.endsWith(r10)
            if (r3 == 0) goto L_0x06ca
            int r3 = r2.indirections
            r4 = 1
            int r3 = r3 + r4
            r2.indirections = r3
            boolean r3 = r2.reference
            if (r3 == 0) goto L_0x06b9
            r3 = 0
            r2.constValue = r3
            goto L_0x06ba
        L_0x06b9:
            r3 = 0
        L_0x06ba:
            java.lang.String r5 = r2.cppName
            java.lang.String r6 = r2.cppName
            int r6 = r6.length()
            int r6 = r6 - r4
            java.lang.String r5 = r5.substring(r3, r6)
            r2.cppName = r5
            goto L_0x06cb
        L_0x06ca:
            r4 = 1
        L_0x06cb:
            java.lang.String r3 = r2.cppName
            boolean r3 = r3.endsWith(r14)
            if (r3 == 0) goto L_0x06e5
            r2.reference = r4
            java.lang.String r3 = r2.cppName
            java.lang.String r5 = r2.cppName
            int r5 = r5.length()
            int r5 = r5 - r4
            r6 = 0
            java.lang.String r3 = r3.substring(r6, r5)
            r2.cppName = r3
        L_0x06e5:
            java.lang.String r3 = r2.cppName
            boolean r3 = r3.endsWith(r12)
            if (r3 == 0) goto L_0x0700
            r2.constValue = r4
            java.lang.String r3 = r2.cppName
            java.lang.String r4 = r2.cppName
            int r4 = r4.length()
            r5 = 6
            int r4 = r4 - r5
            r5 = 0
            java.lang.String r3 = r3.substring(r5, r4)
            r2.cppName = r3
        L_0x0700:
            java.lang.String r3 = r2.cppName
            java.lang.String r4 = r2.cppName
            java.lang.String[] r4 = r1.qualify(r4)
            if (r29 == 0) goto L_0x0747
            int r6 = r4.length
            if (r6 <= 0) goto L_0x0747
            boolean r3 = r2.constValue
            if (r3 == 0) goto L_0x0724
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r13)
            r6 = 0
            r8 = r4[r6]
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            goto L_0x0727
        L_0x0724:
            r6 = 0
            r3 = r4[r6]
        L_0x0727:
            boolean r6 = r2.constPointer
            if (r6 == 0) goto L_0x073a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            r6.append(r12)
            java.lang.String r3 = r6.toString()
        L_0x073a:
            org.bytedeco.javacpp.tools.InfoMap r6 = r0.infoMap
            r8 = 0
            org.bytedeco.javacpp.tools.Info r3 = r6.getFirst(r3, r8)
            r4 = r4[r8]
            r2.cppName = r4
            goto L_0x07f2
        L_0x0747:
            r8 = 0
            java.lang.String r6 = r1.cppName
            if (r6 == 0) goto L_0x0755
            r15 = 60
            int r17 = r6.lastIndexOf(r15)
            r15 = r17
            goto L_0x0756
        L_0x0755:
            r15 = -1
        L_0x0756:
            if (r15 < 0) goto L_0x075d
            java.lang.String r15 = r6.substring(r8, r15)
            goto L_0x075e
        L_0x075d:
            r15 = r6
        L_0x075e:
            int r8 = r4.length
            r5 = 0
            r16 = 0
        L_0x0762:
            if (r5 >= r8) goto L_0x07f0
            r24 = r8
            r8 = r4[r5]
            r25 = r4
            if (r15 == 0) goto L_0x079e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r11)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            boolean r4 = r15.endsWith(r4)
            if (r4 == 0) goto L_0x079e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r6)
            r4.append(r11)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            boolean r4 = r8.equals(r4)
            if (r4 == 0) goto L_0x079e
            r26 = r3
            r29 = r6
            goto L_0x07e4
        L_0x079e:
            boolean r4 = r2.constValue
            if (r4 == 0) goto L_0x07b4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r13)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            r26 = r3
            goto L_0x07b7
        L_0x07b4:
            r26 = r3
            r4 = r8
        L_0x07b7:
            boolean r3 = r2.constPointer
            if (r3 == 0) goto L_0x07ca
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r4)
            r3.append(r12)
            java.lang.String r4 = r3.toString()
        L_0x07ca:
            org.bytedeco.javacpp.tools.InfoMap r3 = r0.infoMap
            r29 = r6
            r6 = 0
            org.bytedeco.javacpp.tools.Info r3 = r3.getFirst(r4, r6)
            if (r3 == 0) goto L_0x07d8
            r2.cppName = r8
            goto L_0x07f2
        L_0x07d8:
            org.bytedeco.javacpp.tools.InfoMap r6 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r4 = r6.getFirst(r4)
            if (r4 == 0) goto L_0x07e2
            r2.cppName = r8
        L_0x07e2:
            r16 = r3
        L_0x07e4:
            int r5 = r5 + 1
            r6 = r29
            r8 = r24
            r4 = r25
            r3 = r26
            goto L_0x0762
        L_0x07f0:
            r3 = r16
        L_0x07f2:
            if (r3 == 0) goto L_0x0804
            java.lang.String[] r4 = r3.cppTypes
            if (r4 == 0) goto L_0x0804
            java.lang.String[] r4 = r3.cppTypes
            int r4 = r4.length
            if (r4 <= 0) goto L_0x0804
            java.lang.String[] r4 = r3.cppTypes
            r5 = 0
            r4 = r4[r5]
            r2.cppName = r4
        L_0x0804:
            java.lang.String r4 = r2.cppName
            boolean r4 = r4.startsWith(r13)
            if (r4 == 0) goto L_0x0819
            r4 = 1
            r2.constValue = r4
            java.lang.String r5 = r2.cppName
            r6 = 6
            java.lang.String r5 = r5.substring(r6)
            r2.cppName = r5
            goto L_0x081b
        L_0x0819:
            r4 = 1
            r6 = 6
        L_0x081b:
            java.lang.String r5 = r2.cppName
            boolean r5 = r5.endsWith(r12)
            if (r5 == 0) goto L_0x0835
            r2.constPointer = r4
            java.lang.String r4 = r2.cppName
            java.lang.String r5 = r2.cppName
            int r5 = r5.length()
            int r5 = r5 - r6
            r6 = 0
            java.lang.String r4 = r4.substring(r6, r5)
            r2.cppName = r4
        L_0x0835:
            java.lang.String r4 = r2.cppName
            boolean r4 = r4.endsWith(r10)
            if (r4 == 0) goto L_0x085c
            int r4 = r2.indirections
            r5 = 1
            int r4 = r4 + r5
            r2.indirections = r4
            boolean r4 = r2.reference
            if (r4 == 0) goto L_0x084b
            r4 = 0
            r2.constValue = r4
            goto L_0x084c
        L_0x084b:
            r4 = 0
        L_0x084c:
            java.lang.String r6 = r2.cppName
            java.lang.String r8 = r2.cppName
            int r8 = r8.length()
            int r8 = r8 - r5
            java.lang.String r6 = r6.substring(r4, r8)
            r2.cppName = r6
            goto L_0x085d
        L_0x085c:
            r5 = 1
        L_0x085d:
            java.lang.String r4 = r2.cppName
            boolean r4 = r4.endsWith(r14)
            if (r4 == 0) goto L_0x0877
            r2.reference = r5
            java.lang.String r4 = r2.cppName
            java.lang.String r6 = r2.cppName
            int r6 = r6.length()
            int r6 = r6 - r5
            r8 = 0
            java.lang.String r4 = r4.substring(r8, r6)
            r2.cppName = r4
        L_0x0877:
            java.lang.String r4 = r2.cppName
            boolean r4 = r4.endsWith(r12)
            if (r4 == 0) goto L_0x0892
            r2.constValue = r5
            java.lang.String r4 = r2.cppName
            java.lang.String r5 = r2.cppName
            int r5 = r5.length()
            r6 = 6
            int r5 = r5 - r6
            r6 = 0
            java.lang.String r4 = r4.substring(r6, r5)
            r2.cppName = r4
        L_0x0892:
            java.lang.String r4 = r2.cppName
            int r4 = r4.lastIndexOf(r11)
            java.lang.String r5 = r2.cppName
            r6 = 60
            int r5 = r5.lastIndexOf(r6)
            if (r4 < 0) goto L_0x08ad
            if (r5 >= 0) goto L_0x08ad
            java.lang.String r6 = r2.cppName
            int r8 = r4 + 2
            java.lang.String r6 = r6.substring(r8)
            goto L_0x08af
        L_0x08ad:
            java.lang.String r6 = r2.cppName
        L_0x08af:
            r2.javaName = r6
            if (r3 == 0) goto L_0x08fc
            int r6 = r2.indirections
            if (r6 != 0) goto L_0x08d3
            boolean r6 = r2.reference
            if (r6 != 0) goto L_0x08d3
            java.lang.String[] r6 = r3.valueTypes
            if (r6 == 0) goto L_0x08d3
            java.lang.String[] r6 = r3.valueTypes
            int r6 = r6.length
            if (r6 <= 0) goto L_0x08d3
            java.lang.String[] r6 = r3.valueTypes
            r8 = 0
            r6 = r6[r8]
            r2.javaName = r6
            java.lang.String[] r6 = r3.valueTypes
            r2.javaNames = r6
            r6 = 1
            r2.value = r6
            goto L_0x08fc
        L_0x08d3:
            java.lang.String[] r6 = r3.pointerTypes
            if (r6 == 0) goto L_0x08e8
            java.lang.String[] r6 = r3.pointerTypes
            int r6 = r6.length
            if (r6 <= 0) goto L_0x08e8
            java.lang.String[] r6 = r3.pointerTypes
            r8 = 0
            r6 = r6[r8]
            r2.javaName = r6
            java.lang.String[] r6 = r3.pointerTypes
            r2.javaNames = r6
            goto L_0x08fc
        L_0x08e8:
            java.lang.String[] r6 = r3.javaNames
            if (r6 == 0) goto L_0x08fc
            java.lang.String[] r6 = r3.javaNames
            int r6 = r6.length
            if (r6 <= 0) goto L_0x08fc
            java.lang.String[] r6 = r3.javaNames
            r8 = 0
            r6 = r6[r8]
            r2.javaName = r6
            java.lang.String[] r6 = r3.javaNames
            r2.javaNames = r6
        L_0x08fc:
            boolean r6 = r2.operator
            if (r6 == 0) goto L_0x09cd
            boolean r6 = r2.constValue
            if (r6 == 0) goto L_0x091d
            boolean r6 = r2.constExpr
            if (r6 != 0) goto L_0x091d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r2.annotations
            r6.append(r8)
            java.lang.String r8 = "@Const "
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r2.annotations = r6
        L_0x091d:
            int r6 = r2.indirections
            if (r6 != 0) goto L_0x093f
            boolean r6 = r2.reference
            if (r6 != 0) goto L_0x093f
            boolean r6 = r2.value
            if (r6 != 0) goto L_0x093f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r2.annotations
            r6.append(r8)
            java.lang.String r8 = "@ByVal "
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r2.annotations = r6
            goto L_0x0960
        L_0x093f:
            int r6 = r2.indirections
            if (r6 != 0) goto L_0x0960
            boolean r6 = r2.reference
            if (r6 == 0) goto L_0x0960
            boolean r6 = r2.value
            if (r6 != 0) goto L_0x0960
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r2.annotations
            r6.append(r8)
            java.lang.String r8 = "@ByRef "
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r2.annotations = r6
        L_0x0960:
            if (r3 == 0) goto L_0x0990
            boolean r6 = r3.cast
            if (r6 == 0) goto L_0x0990
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r2.annotations
            r6.append(r8)
            java.lang.String r8 = "@Cast(\""
            r6.append(r8)
            java.lang.String r8 = r2.cppName
            r6.append(r8)
            boolean r8 = r2.value
            if (r8 != 0) goto L_0x0980
            r8 = r10
            goto L_0x0982
        L_0x0980:
            r8 = r22
        L_0x0982:
            r6.append(r8)
            java.lang.String r8 = "\") "
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r2.annotations = r6
        L_0x0990:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r2.annotations
            r6.append(r8)
            java.lang.String r8 = "@Name(\"operator "
            r6.append(r8)
            boolean r8 = r2.constValue
            if (r8 == 0) goto L_0x09a8
            boolean r8 = r2.constExpr
            if (r8 != 0) goto L_0x09a8
            goto L_0x09aa
        L_0x09a8:
            r13 = r22
        L_0x09aa:
            r6.append(r13)
            java.lang.String r8 = r2.cppName
            r6.append(r8)
            int r8 = r2.indirections
            if (r8 <= 0) goto L_0x09b8
            r14 = r10
            goto L_0x09bf
        L_0x09b8:
            boolean r8 = r2.reference
            if (r8 == 0) goto L_0x09bd
            goto L_0x09bf
        L_0x09bd:
            r14 = r22
        L_0x09bf:
            r6.append(r14)
            java.lang.String r8 = "\") "
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r2.annotations = r6
        L_0x09cd:
            if (r3 == 0) goto L_0x09f4
            java.lang.String[] r6 = r3.annotations
            if (r6 == 0) goto L_0x09f4
            java.lang.String[] r3 = r3.annotations
            int r6 = r3.length
            r8 = 0
        L_0x09d7:
            if (r8 >= r6) goto L_0x09f4
            r10 = r3[r8]
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = r2.annotations
            r12.append(r13)
            r12.append(r10)
            r12.append(r9)
            java.lang.String r10 = r12.toString()
            r2.annotations = r10
            int r8 = r8 + 1
            goto L_0x09d7
        L_0x09f4:
            java.lang.String r3 = r1.cppName
            if (r3 == 0) goto L_0x0a9c
            java.lang.String r3 = r2.javaName
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x0a9c
            java.lang.String r3 = r2.cppName
            java.lang.String r6 = r1.cppName
            if (r6 == 0) goto L_0x0a0d
            r8 = 60
            int r8 = r6.lastIndexOf(r8)
            goto L_0x0a0e
        L_0x0a0d:
            r8 = -1
        L_0x0a0e:
            if (r5 >= 0) goto L_0x0a18
            if (r8 < 0) goto L_0x0a18
            r9 = 0
            java.lang.String r6 = r6.substring(r9, r8)
            goto L_0x0a25
        L_0x0a18:
            r9 = 0
            if (r5 < 0) goto L_0x0a25
            if (r8 >= 0) goto L_0x0a25
            java.lang.String r3 = r3.substring(r9, r5)
            int r4 = r3.lastIndexOf(r11)
        L_0x0a25:
            if (r6 == 0) goto L_0x0a2c
            int r5 = r6.lastIndexOf(r11)
            goto L_0x0a2d
        L_0x0a2c:
            r5 = -1
        L_0x0a2d:
            if (r4 >= 0) goto L_0x0a38
            if (r5 < 0) goto L_0x0a38
            r8 = 2
            int r5 = r5 + r8
            java.lang.String r6 = r6.substring(r5)
            goto L_0x0a42
        L_0x0a38:
            r8 = 2
            if (r4 < 0) goto L_0x0a42
            if (r5 >= 0) goto L_0x0a42
            int r4 = r4 + r8
            java.lang.String r3 = r3.substring(r4)
        L_0x0a42:
            boolean r4 = r3.equals(r6)
            if (r4 != 0) goto L_0x0a5d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            r4.append(r7)
            java.lang.String r3 = r4.toString()
            boolean r3 = r6.startsWith(r3)
            if (r3 == 0) goto L_0x0a94
        L_0x0a5d:
            boolean r3 = r2.destructor
            if (r3 != 0) goto L_0x0a90
            boolean r3 = r2.operator
            if (r3 != 0) goto L_0x0a90
            int r3 = r2.indirections
            if (r3 != 0) goto L_0x0a90
            boolean r3 = r2.reference
            if (r3 != 0) goto L_0x0a90
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r5 = 40
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r6 = 0
            r4[r6] = r5
            r5 = 58
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r7 = 1
            r4[r7] = r5
            boolean r3 = r3.match(r4)
            if (r3 == 0) goto L_0x0a91
            r5 = 1
            goto L_0x0a92
        L_0x0a90:
            r6 = 0
        L_0x0a91:
            r5 = 0
        L_0x0a92:
            r2.constructor = r5
        L_0x0a94:
            java.lang.String r3 = r2.javaName
            java.lang.String r1 = r1.shorten(r3)
            r2.javaName = r1
        L_0x0a9c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.type(org.bytedeco.javacpp.tools.Context, boolean):org.bytedeco.javacpp.tools.Type");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v98, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v102, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v85, resolved type: org.bytedeco.javacpp.tools.Attribute} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v111, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v112, resolved type: boolean} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x032c, code lost:
        if (r0.tokens.get(1).match('(') != false) goto L_0x05b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0482, code lost:
        r9.cppName += "operator " + r0.tokens.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x04a0, code lost:
        r3 = r0.tokens.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x04bb, code lost:
        if (r3.match(org.bytedeco.javacpp.tools.Token.EOF, '(') != false) goto L_0x059f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x04bd, code lost:
        r9.cppName += r3;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x07b3  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x07b8  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x07d4  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x084f  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x08e7  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x08f0  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0913  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x092f  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x0991  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x09d5  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x09f0  */
    /* JADX WARNING: Removed duplicated region for block: B:329:0x0a32  */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x0a34  */
    /* JADX WARNING: Removed duplicated region for block: B:337:0x0a60  */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x0a64  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0a9e  */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x0aae  */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x0ac3  */
    /* JADX WARNING: Removed duplicated region for block: B:399:0x0c0a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:434:0x0c5f  */
    /* JADX WARNING: Removed duplicated region for block: B:435:0x0c63  */
    /* JADX WARNING: Removed duplicated region for block: B:438:0x0c6d  */
    /* JADX WARNING: Removed duplicated region for block: B:442:0x0c7d  */
    /* JADX WARNING: Removed duplicated region for block: B:450:0x0ca4  */
    /* JADX WARNING: Removed duplicated region for block: B:451:0x0ca6  */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x0cbd  */
    /* JADX WARNING: Removed duplicated region for block: B:458:0x0cc3  */
    /* JADX WARNING: Removed duplicated region for block: B:512:0x0e01  */
    /* JADX WARNING: Removed duplicated region for block: B:539:0x0eaa  */
    /* JADX WARNING: Removed duplicated region for block: B:540:0x0ead  */
    /* JADX WARNING: Removed duplicated region for block: B:543:0x0eb3  */
    /* JADX WARNING: Removed duplicated region for block: B:553:0x0ee0  */
    /* JADX WARNING: Removed duplicated region for block: B:556:0x0ee7  */
    /* JADX WARNING: Removed duplicated region for block: B:557:0x0eed  */
    /* JADX WARNING: Removed duplicated region for block: B:579:0x0f2c  */
    /* JADX WARNING: Removed duplicated region for block: B:590:0x0f6c  */
    /* JADX WARNING: Removed duplicated region for block: B:593:0x0f7c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:597:0x0f9a  */
    /* JADX WARNING: Removed duplicated region for block: B:598:0x0fbc  */
    /* JADX WARNING: Removed duplicated region for block: B:601:0x0fc2  */
    /* JADX WARNING: Removed duplicated region for block: B:621:0x1046  */
    /* JADX WARNING: Removed duplicated region for block: B:624:0x1064  */
    /* JADX WARNING: Removed duplicated region for block: B:649:0x110e  */
    /* JADX WARNING: Removed duplicated region for block: B:652:0x112f  */
    /* JADX WARNING: Removed duplicated region for block: B:654:0x1139  */
    /* JADX WARNING: Removed duplicated region for block: B:655:0x1157  */
    /* JADX WARNING: Removed duplicated region for block: B:661:0x1163  */
    /* JADX WARNING: Removed duplicated region for block: B:662:0x1169  */
    /* JADX WARNING: Removed duplicated region for block: B:680:0x11de A[LOOP:26: B:679:0x11dc->B:680:0x11de, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:682:0x11fb  */
    /* JADX WARNING: Removed duplicated region for block: B:685:0x1211  */
    /* JADX WARNING: Removed duplicated region for block: B:722:0x13b3  */
    /* JADX WARNING: Removed duplicated region for block: B:725:0x13c7  */
    /* JADX WARNING: Removed duplicated region for block: B:727:0x13ca  */
    /* JADX WARNING: Removed duplicated region for block: B:730:0x13d1  */
    /* JADX WARNING: Removed duplicated region for block: B:731:0x1404  */
    /* JADX WARNING: Removed duplicated region for block: B:743:0x1432  */
    /* JADX WARNING: Removed duplicated region for block: B:763:0x14ba A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:823:0x0835 A[EDGE_INSN: B:823:0x0835->B:277:0x0835 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:858:0x14c4 A[ADDED_TO_REGION, EDGE_INSN: B:858:0x14c4->B:765:0x14c4 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.Declarator declarator(org.bytedeco.javacpp.tools.Context r36, java.lang.String r37, int r38, boolean r39, int r40, boolean r41, boolean r42) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r35 = this;
            r0 = r35
            r1 = r36
            r2 = r37
            r3 = r38
            r4 = r40
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            org.bytedeco.javacpp.tools.Token r5 = r5.get()
            r6 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r6)
            java.lang.Object[] r8 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r9 = org.bytedeco.javacpp.tools.Token.TYPEDEF
            r10 = 0
            r8[r10] = r9
            boolean r5 = r5.match(r8)
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            org.bytedeco.javacpp.tools.Token r8 = r8.get()
            java.lang.Object[] r9 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r11 = org.bytedeco.javacpp.tools.Token.USING
            r9[r10] = r11
            boolean r8 = r8.match(r9)
            org.bytedeco.javacpp.tools.Declarator r9 = new org.bytedeco.javacpp.tools.Declarator
            r9.<init>()
            org.bytedeco.javacpp.tools.Type r11 = r35.type(r36)
            r12 = 0
            if (r11 != 0) goto L_0x003d
            return r12
        L_0x003d:
            boolean r13 = r11.typedef
            r5 = r5 | r13
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.get()
            r14 = 0
            r15 = 0
        L_0x0048:
            r16 = 91
            if (r14 >= r4) goto L_0x00de
            java.lang.Object[] r12 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r19 = org.bytedeco.javacpp.tools.Token.EOF
            r12[r10] = r19
            boolean r12 = r13.match(r12)
            if (r12 != 0) goto L_0x00de
            r12 = 3
            java.lang.Object[] r6 = new java.lang.Object[r12]
            r12 = 40
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r6[r10] = r12
            java.lang.Character r12 = java.lang.Character.valueOf(r16)
            r16 = 1
            r6[r16] = r12
            r12 = 123(0x7b, float:1.72E-43)
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r16 = 2
            r6[r16] = r12
            boolean r6 = r13.match(r6)
            if (r6 == 0) goto L_0x007e
            int r15 = r15 + 1
            goto L_0x00d4
        L_0x007e:
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r12 = 41
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r6[r10] = r12
            r12 = 93
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r10 = 1
            r6[r10] = r12
            r12 = 125(0x7d, float:1.75E-43)
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r16 = 2
            r6[r16] = r12
            boolean r6 = r13.match(r6)
            if (r6 == 0) goto L_0x00a5
            int r15 = r15 + -1
            goto L_0x00d4
        L_0x00a5:
            if (r15 <= 0) goto L_0x00a8
            goto L_0x00d4
        L_0x00a8:
            java.lang.Object[] r6 = new java.lang.Object[r10]
            r12 = 44
            java.lang.Character r12 = java.lang.Character.valueOf(r12)
            r16 = 0
            r6[r16] = r12
            boolean r6 = r13.match(r6)
            if (r6 == 0) goto L_0x00bd
            int r14 = r14 + 1
            goto L_0x00d4
        L_0x00bd:
            java.lang.Object[] r6 = new java.lang.Object[r10]
            r10 = 59
            java.lang.Character r10 = java.lang.Character.valueOf(r10)
            r6[r16] = r10
            boolean r6 = r13.match(r6)
            if (r6 == 0) goto L_0x00d4
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r1.next()
            r1 = 0
            return r1
        L_0x00d4:
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r6.next()
            r6 = 1
            r10 = 0
            goto L_0x0048
        L_0x00de:
            java.lang.String r6 = r11.cppName
            java.lang.String r10 = "*"
            if (r4 != 0) goto L_0x0106
            int r12 = r11.indirections
            if (r12 <= 0) goto L_0x0106
            int r12 = r9.indirections
            int r13 = r11.indirections
            int r12 = r12 + r13
            r9.indirections = r12
            r12 = 0
        L_0x00f0:
            int r13 = r11.indirections
            if (r12 >= r13) goto L_0x0106
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r6)
            r13.append(r10)
            java.lang.String r6 = r13.toString()
            int r12 = r12 + 1
            goto L_0x00f0
        L_0x0106:
            boolean r12 = r11.constValue
            java.lang.String r13 = "const "
            if (r12 == 0) goto L_0x011b
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r13)
            r12.append(r6)
            java.lang.String r6 = r12.toString()
        L_0x011b:
            boolean r12 = r11.constPointer
            if (r12 == 0) goto L_0x0123
            r12 = 1
            r9.constPointer = r12
            goto L_0x0124
        L_0x0123:
            r12 = 1
        L_0x0124:
            java.lang.String r14 = "&"
            if (r4 != 0) goto L_0x013d
            boolean r4 = r11.reference
            if (r4 == 0) goto L_0x013d
            r9.reference = r12
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r6)
            r4.append(r14)
            java.lang.String r6 = r4.toString()
        L_0x013d:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get()
        L_0x0143:
            r12 = 1
            java.lang.Object[] r15 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r19 = org.bytedeco.javacpp.tools.Token.EOF
            r20 = 0
            r15[r20] = r19
            boolean r15 = r4.match(r15)
            r21 = r8
            r8 = 42
            if (r15 != 0) goto L_0x01bb
            java.lang.Object[] r15 = new java.lang.Object[r12]
            java.lang.Character r19 = java.lang.Character.valueOf(r8)
            r15[r20] = r19
            boolean r15 = r4.match(r15)
            if (r15 == 0) goto L_0x016a
            int r8 = r9.indirections
            int r8 = r8 + r12
            r9.indirections = r8
            goto L_0x01a3
        L_0x016a:
            java.lang.Object[] r15 = new java.lang.Object[r12]
            r19 = 38
            java.lang.Character r19 = java.lang.Character.valueOf(r19)
            r15[r20] = r19
            boolean r15 = r4.match(r15)
            if (r15 == 0) goto L_0x017d
            r9.reference = r12
            goto L_0x01a3
        L_0x017d:
            java.lang.Object[] r15 = new java.lang.Object[r12]
            java.lang.String r19 = "&&"
            r15[r20] = r19
            boolean r15 = r4.match(r15)
            if (r15 == 0) goto L_0x018a
            goto L_0x01a3
        L_0x018a:
            r15 = 3
            java.lang.Object[] r8 = new java.lang.Object[r15]
            org.bytedeco.javacpp.tools.Token r15 = org.bytedeco.javacpp.tools.Token.CONST
            r8[r20] = r15
            org.bytedeco.javacpp.tools.Token r15 = org.bytedeco.javacpp.tools.Token.__CONST
            r8[r12] = r15
            org.bytedeco.javacpp.tools.Token r15 = org.bytedeco.javacpp.tools.Token.CONSTEXPR
            r18 = 2
            r8[r18] = r15
            boolean r8 = r4.match(r8)
            if (r8 == 0) goto L_0x01bb
            r9.constPointer = r12
        L_0x01a3:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r6)
            r8.append(r4)
            java.lang.String r6 = r8.toString()
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.next()
            r8 = r21
            goto L_0x0143
        L_0x01bb:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            org.bytedeco.javacpp.tools.Attribute[] r8 = r11.attributes
            if (r8 == 0) goto L_0x01cd
            org.bytedeco.javacpp.tools.Attribute[] r8 = r11.attributes
            java.util.List r8 = java.util.Arrays.asList(r8)
            r4.addAll(r8)
        L_0x01cd:
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            int r8 = r8.index
            org.bytedeco.javacpp.tools.Attribute r12 = r35.attribute()
        L_0x01d5:
            if (r12 == 0) goto L_0x01fc
            boolean r15 = r12.annotation
            if (r15 == 0) goto L_0x01fc
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r15 = r11.annotations
            r8.append(r15)
            java.lang.String r15 = r12.javaName
            r8.append(r15)
            java.lang.String r8 = r8.toString()
            r11.annotations = r8
            r4.add(r12)
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            int r8 = r8.index
            org.bytedeco.javacpp.tools.Attribute r12 = r35.attribute()
            goto L_0x01d5
        L_0x01fc:
            org.bytedeco.javacpp.tools.TokenIndexer r12 = r0.tokens
            r12.index = r8
            java.util.Iterator r8 = r4.iterator()
            r12 = 0
        L_0x0205:
            boolean r15 = r8.hasNext()
            r22 = r12
            java.lang.String r12 = ""
            if (r15 == 0) goto L_0x026f
            java.lang.Object r15 = r8.next()
            org.bytedeco.javacpp.tools.Attribute r15 = (org.bytedeco.javacpp.tools.Attribute) r15
            r23 = r8
            java.lang.String r8 = r15.javaName
            if (r8 == 0) goto L_0x0259
            java.lang.String r8 = r15.javaName
            r24 = r14
            java.lang.String r14 = "@Name "
            boolean r8 = r8.contains(r14)
            if (r8 == 0) goto L_0x025b
            java.lang.String r8 = r15.arguments
            int r8 = r8.length()
            if (r8 <= 0) goto L_0x025b
            java.lang.String r8 = r15.arguments
            r14 = 0
            char r8 = r8.charAt(r14)
            boolean r8 = java.lang.Character.isJavaIdentifierStart(r8)
            if (r8 == 0) goto L_0x025b
            java.lang.String r8 = r15.arguments
            char[] r8 = r8.toCharArray()
            int r14 = r8.length
            r25 = r15
            r15 = 0
        L_0x0246:
            if (r15 >= r14) goto L_0x0256
            char r22 = r8[r15]
            boolean r22 = java.lang.Character.isJavaIdentifierPart(r22)
            if (r22 != 0) goto L_0x0253
            r22 = 0
            goto L_0x025b
        L_0x0253:
            int r15 = r15 + 1
            goto L_0x0246
        L_0x0256:
            r22 = r25
            goto L_0x025b
        L_0x0259:
            r24 = r14
        L_0x025b:
            if (r22 == 0) goto L_0x0268
            java.lang.String r8 = r11.annotations
            java.lang.String r14 = "@Name "
            java.lang.String r8 = r8.replace(r14, r12)
            r11.annotations = r8
            goto L_0x0271
        L_0x0268:
            r12 = r22
            r8 = r23
            r14 = r24
            goto L_0x0205
        L_0x026f:
            r24 = r14
        L_0x0271:
            r8 = r22
            r14 = 0
        L_0x0274:
            org.bytedeco.javacpp.tools.TokenIndexer r15 = r0.tokens
            org.bytedeco.javacpp.tools.Token r15 = r15.get()
            r22 = r8
            r23 = r13
            r8 = 1
            java.lang.Object[] r13 = new java.lang.Object[r8]
            r17 = 40
            java.lang.Character r19 = java.lang.Character.valueOf(r17)
            r20 = 0
            r13[r20] = r19
            boolean r13 = r15.match(r13)
            if (r13 == 0) goto L_0x02b1
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.get(r8)
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Character r8 = java.lang.Character.valueOf(r17)
            r15[r20] = r8
            boolean r8 = r13.match(r15)
            if (r8 == 0) goto L_0x02b1
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            r8.next()
            int r14 = r14 + 1
            r8 = r22
            r13 = r23
            goto L_0x0274
        L_0x02b1:
            r8 = 256(0x100, float:3.59E-43)
            int[] r8 = new int[r8]
            r9.cppName = r12
            org.bytedeco.javacpp.tools.Declaration r13 = new org.bytedeco.javacpp.tools.Declaration
            r13.<init>()
            java.util.Iterator r15 = r4.iterator()
            r25 = 0
        L_0x02c2:
            boolean r26 = r15.hasNext()
            if (r26 == 0) goto L_0x02ef
            java.lang.Object r26 = r15.next()
            r27 = r14
            r14 = r26
            org.bytedeco.javacpp.tools.Attribute r14 = (org.bytedeco.javacpp.tools.Attribute) r14
            r26 = r15
            boolean r15 = r14.annotation
            if (r15 == 0) goto L_0x02ea
            java.lang.String r15 = r14.javaName
            int r15 = r15.length()
            if (r15 != 0) goto L_0x02ea
            java.lang.String r15 = r14.arguments
            int r15 = r15.length()
            if (r15 != 0) goto L_0x02ea
            r25 = r14
        L_0x02ea:
            r15 = r26
            r14 = r27
            goto L_0x02c2
        L_0x02ef:
            r27 = r14
            org.bytedeco.javacpp.tools.TokenIndexer r14 = r0.tokens
            org.bytedeco.javacpp.tools.Token r14 = r14.get()
            r15 = 1
            java.lang.Object[] r3 = new java.lang.Object[r15]
            r17 = 40
            java.lang.Character r19 = java.lang.Character.valueOf(r17)
            r20 = 0
            r3[r20] = r19
            boolean r3 = r14.match(r3)
            java.lang.String r14 = "::"
            java.lang.String r15 = "\") "
            r26 = 5
            r28 = r10
            if (r3 != 0) goto L_0x05b5
            if (r5 == 0) goto L_0x0330
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            r10 = 1
            org.bytedeco.javacpp.tools.Token r3 = r3.get(r10)
            r29 = r5
            java.lang.Object[] r5 = new java.lang.Object[r10]
            r10 = 40
            java.lang.Character r30 = java.lang.Character.valueOf(r10)
            r10 = 0
            r5[r10] = r30
            boolean r3 = r3.match(r5)
            if (r3 == 0) goto L_0x0333
            goto L_0x05b7
        L_0x0330:
            r29 = r5
            r10 = 0
        L_0x0333:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r26)
            r5[r10] = r4
            r4 = 1
            r5[r4] = r14
            boolean r3 = r3.match(r5)
            if (r3 == 0) goto L_0x05ab
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r5 = r3
            r30 = 0
            r31 = 0
            r32 = 0
        L_0x0358:
            java.lang.Object[] r3 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r19 = org.bytedeco.javacpp.tools.Token.EOF
            r3[r10] = r19
            boolean r3 = r5.match(r3)
            if (r3 != 0) goto L_0x059f
            java.lang.String r3 = r9.cppName
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x0425
            java.lang.Object[] r3 = new java.lang.Object[r4]
            r4 = 42
            java.lang.Character r20 = java.lang.Character.valueOf(r4)
            r3[r10] = r20
            boolean r3 = r5.match(r3)
            if (r3 == 0) goto L_0x0425
            java.lang.String r3 = r9.cppName
            java.lang.String r4 = r9.cppName
            int r4 = r4.length()
            r5 = 2
            int r4 = r4 - r5
            java.lang.String r3 = r3.substring(r10, r4)
            r9.cppName = r3
            java.lang.String r3 = r9.cppName
            java.lang.String[] r3 = r1.qualify(r3)
            int r4 = r3.length
            r5 = 0
        L_0x0394:
            if (r5 >= r4) goto L_0x03b9
            r31 = r4
            r4 = r3[r5]
            r33 = r3
            org.bytedeco.javacpp.tools.InfoMap r3 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r32 = r3.getFirst(r4, r10)
            if (r32 == 0) goto L_0x03a7
            r9.cppName = r4
            goto L_0x03b9
        L_0x03a7:
            org.bytedeco.javacpp.tools.InfoMap r3 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r3 = r3.getFirst(r4)
            if (r3 == 0) goto L_0x03b1
            r9.cppName = r4
        L_0x03b1:
            int r5 = r5 + 1
            r4 = r31
            r3 = r33
            r10 = 0
            goto L_0x0394
        L_0x03b9:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r13.text
            r3.append(r4)
            java.lang.String r4 = "@Namespace(\""
            r3.append(r4)
            java.lang.String r4 = r9.cppName
            r3.append(r4)
            r3.append(r15)
            java.lang.String r3 = r3.toString()
            r13.text = r3
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
        L_0x03dc:
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r10 = org.bytedeco.javacpp.tools.Token.EOF
            r20 = 0
            r5[r20] = r10
            boolean r5 = r3.match(r5)
            if (r5 != 0) goto L_0x0405
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r4 = 42
            java.lang.Character r10 = java.lang.Character.valueOf(r4)
            r5[r20] = r10
            boolean r4 = r3.match(r5)
            if (r4 == 0) goto L_0x0404
            int r30 = r30 + 1
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            goto L_0x03dc
        L_0x0404:
            r4 = 1
        L_0x0405:
            java.lang.Object[] r5 = new java.lang.Object[r4]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r26)
            r10 = 0
            r5[r10] = r4
            boolean r4 = r3.match(r5)
            if (r4 == 0) goto L_0x0419
            java.lang.String r3 = r3.toString()
            goto L_0x041a
        L_0x0419:
            r3 = r12
        L_0x041a:
            r9.cppName = r3
            if (r32 == 0) goto L_0x0420
            r3 = 1
            goto L_0x0421
        L_0x0420:
            r3 = 0
        L_0x0421:
            r31 = r3
            goto L_0x0595
        L_0x0425:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r3 = 0
            r4[r3] = r14
            boolean r3 = r5.match(r4)
            if (r3 == 0) goto L_0x0446
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r9.cppName
            r3.append(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r9.cppName = r3
            goto L_0x0595
        L_0x0446:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r10 = org.bytedeco.javacpp.tools.Token.OPERATOR
            r19 = 0
            r4[r19] = r10
            boolean r4 = r5.match(r4)
            if (r4 == 0) goto L_0x04d1
            r9.operator = r3
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get(r3)
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r26)
            r5[r19] = r10
            boolean r4 = r4.match(r5)
            if (r4 == 0) goto L_0x0482
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get(r3)
            r5 = 2
            java.lang.Object[] r10 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.NEW
            r10[r19] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.DELETE
            r10[r3] = r5
            boolean r3 = r4.match(r10)
            if (r3 == 0) goto L_0x0595
        L_0x0482:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r9.cppName
            r3.append(r4)
            java.lang.String r4 = "operator "
            r3.append(r4)
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.next()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r9.cppName = r3
        L_0x04a0:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r4 = org.bytedeco.javacpp.tools.Token.EOF
            r10 = 0
            r5[r10] = r4
            r4 = 40
            java.lang.Character r10 = java.lang.Character.valueOf(r4)
            r4 = 1
            r5[r4] = r10
            boolean r4 = r3.match(r5)
            if (r4 != 0) goto L_0x059f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r9.cppName
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r9.cppName = r3
            goto L_0x04a0
        L_0x04d1:
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r3 = 60
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r10 = 0
            r4[r10] = r3
            boolean r3 = r5.match(r4)
            if (r3 == 0) goto L_0x0562
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r9.cppName
            r3.append(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r9.cppName = r3
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            r4 = 0
        L_0x04fc:
            r5 = 1
            java.lang.Object[] r10 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.EOF
            r20 = 0
            r10[r20] = r5
            boolean r5 = r3.match(r10)
            if (r5 != 0) goto L_0x0595
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r10 = r9.cppName
            r5.append(r10)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            r9.cppName = r5
            r5 = 1
            if (r4 != 0) goto L_0x0534
            java.lang.Object[] r10 = new java.lang.Object[r5]
            r19 = 62
            java.lang.Character r19 = java.lang.Character.valueOf(r19)
            r20 = 0
            r10[r20] = r19
            boolean r10 = r3.match(r10)
            if (r10 == 0) goto L_0x0536
            goto L_0x0595
        L_0x0534:
            r20 = 0
        L_0x0536:
            java.lang.Object[] r10 = new java.lang.Object[r5]
            r19 = 60
            java.lang.Character r19 = java.lang.Character.valueOf(r19)
            r10[r20] = r19
            boolean r10 = r3.match(r10)
            if (r10 == 0) goto L_0x0549
            int r4 = r4 + 1
            goto L_0x055b
        L_0x0549:
            java.lang.Object[] r10 = new java.lang.Object[r5]
            r5 = 62
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r10[r20] = r5
            boolean r3 = r3.match(r10)
            if (r3 == 0) goto L_0x055b
            int r4 = r4 + -1
        L_0x055b:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            goto L_0x04fc
        L_0x0562:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            java.lang.Integer r3 = java.lang.Integer.valueOf(r26)
            r10 = 0
            r4[r10] = r3
            boolean r3 = r5.match(r4)
            if (r3 == 0) goto L_0x059f
            java.lang.String r3 = r9.cppName
            int r3 = r3.length()
            if (r3 == 0) goto L_0x0582
            java.lang.String r3 = r9.cppName
            boolean r3 = r3.endsWith(r14)
            if (r3 == 0) goto L_0x059f
        L_0x0582:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r9.cppName
            r3.append(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r9.cppName = r3
        L_0x0595:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r5 = r3.next()
            r4 = 1
            r10 = 0
            goto L_0x0358
        L_0x059f:
            r4 = r25
            r25 = r30
            r3 = r31
            r2 = r32
            r31 = r6
            goto L_0x07ab
        L_0x05ab:
            r31 = r6
            r4 = r25
            r2 = 0
            r3 = 0
            r25 = 0
            goto L_0x07ab
        L_0x05b5:
            r29 = r5
        L_0x05b7:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r5 = 1
            java.lang.Object[] r10 = new java.lang.Object[r5]
            r5 = 40
            java.lang.Character r30 = java.lang.Character.valueOf(r5)
            r5 = 0
            r10[r5] = r30
            boolean r3 = r3.match(r10)
            if (r3 == 0) goto L_0x05d4
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            r3.next()
        L_0x05d4:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r10 = r3
            r31 = r6
            r3 = r25
            r5 = 1
            r25 = 0
            r30 = 0
        L_0x05e4:
            java.lang.Object[] r6 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r19 = org.bytedeco.javacpp.tools.Token.EOF
            r20 = 0
            r6[r20] = r19
            boolean r6 = r10.match(r6)
            if (r6 != 0) goto L_0x078a
            r6 = 3
            java.lang.Object[] r5 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r6 = org.bytedeco.javacpp.tools.Token.CONST
            r5[r20] = r6
            org.bytedeco.javacpp.tools.Token r6 = org.bytedeco.javacpp.tools.Token.__CONST
            r2 = 1
            r5[r2] = r6
            org.bytedeco.javacpp.tools.Token r6 = org.bytedeco.javacpp.tools.Token.CONSTEXPR
            r2 = 2
            r5[r2] = r6
            boolean r5 = r10.match(r5)
            if (r5 == 0) goto L_0x0610
            r5 = 1
            r9.constPointer = r5
        L_0x060c:
            r32 = r4
            goto L_0x0774
        L_0x0610:
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r26)
            r6[r20] = r2
            r6[r5] = r14
            boolean r2 = r10.match(r6)
            if (r2 == 0) goto L_0x0664
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            int r2 = r2.index
            org.bytedeco.javacpp.tools.Attribute r5 = r35.attribute()
            if (r5 == 0) goto L_0x064c
            boolean r6 = r5.annotation
            if (r6 == 0) goto L_0x064c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r11.annotations
            r2.append(r3)
            java.lang.String r3 = r5.javaName
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r11.annotations = r2
            r4.add(r5)
            r32 = r4
            r3 = r5
            goto L_0x077d
        L_0x064c:
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            r5.index = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = r9.cppName
            r2.append(r5)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            r9.cppName = r2
            goto L_0x060c
        L_0x0664:
            r2 = 1
            java.lang.Object[] r5 = new java.lang.Object[r2]
            r2 = 42
            java.lang.Character r6 = java.lang.Character.valueOf(r2)
            r2 = 0
            r5[r2] = r6
            boolean r2 = r10.match(r5)
            if (r2 == 0) goto L_0x0716
            int r25 = r25 + 1
            java.lang.String r2 = r9.cppName
            boolean r2 = r2.endsWith(r14)
            if (r2 == 0) goto L_0x06de
            java.lang.String r2 = r9.cppName
            java.lang.String r5 = r9.cppName
            int r5 = r5.length()
            r6 = 2
            int r5 = r5 - r6
            r6 = 0
            java.lang.String r2 = r2.substring(r6, r5)
            r9.cppName = r2
            java.lang.String r2 = r9.cppName
            java.lang.String[] r2 = r1.qualify(r2)
            int r5 = r2.length
            r10 = 0
        L_0x0699:
            r32 = r4
            if (r10 >= r5) goto L_0x06be
            r4 = r2[r10]
            r33 = r2
            org.bytedeco.javacpp.tools.InfoMap r2 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r30 = r2.getFirst(r4, r6)
            if (r30 == 0) goto L_0x06ac
            r9.cppName = r4
            goto L_0x06be
        L_0x06ac:
            org.bytedeco.javacpp.tools.InfoMap r2 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r2 = r2.getFirst(r4)
            if (r2 == 0) goto L_0x06b6
            r9.cppName = r4
        L_0x06b6:
            int r10 = r10 + 1
            r4 = r32
            r2 = r33
            r6 = 0
            goto L_0x0699
        L_0x06be:
            r5 = r30
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r13.text
            r2.append(r4)
            java.lang.String r4 = "@Namespace(\""
            r2.append(r4)
            java.lang.String r4 = r9.cppName
            r2.append(r4)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r13.text = r2
            goto L_0x0713
        L_0x06de:
            r32 = r4
            if (r3 != 0) goto L_0x06ee
            java.lang.String r2 = r9.cppName
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x06eb
            goto L_0x06ee
        L_0x06eb:
            r5 = r30
            goto L_0x0713
        L_0x06ee:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r13.text
            r2.append(r4)
            java.lang.String r4 = "@Convention(\""
            r2.append(r4)
            if (r3 == 0) goto L_0x0702
            java.lang.String r3 = r3.cppName
            goto L_0x0704
        L_0x0702:
            java.lang.String r3 = r9.cppName
        L_0x0704:
            r2.append(r3)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r13.text = r2
            r5 = r30
            r3 = 0
        L_0x0713:
            r9.cppName = r12
            goto L_0x0776
        L_0x0716:
            r32 = r4
            r2 = 1
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.Character r5 = java.lang.Character.valueOf(r16)
            r6 = 0
            r4[r6] = r5
            boolean r4 = r10.match(r4)
            if (r4 == 0) goto L_0x0758
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get(r2)
            int r5 = r9.indices     // Catch:{ NumberFormatException -> 0x074e }
            int r6 = r5 + 1
            r9.indices = r6     // Catch:{ NumberFormatException -> 0x074e }
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ NumberFormatException -> 0x074e }
            r2 = 0
            r6[r2] = r7     // Catch:{ NumberFormatException -> 0x074e }
            boolean r2 = r4.match(r6)     // Catch:{ NumberFormatException -> 0x074e }
            if (r2 == 0) goto L_0x074a
            java.lang.String r2 = r4.value     // Catch:{ NumberFormatException -> 0x074e }
            java.lang.Integer r2 = java.lang.Integer.decode(r2)     // Catch:{ NumberFormatException -> 0x074e }
            int r2 = r2.intValue()     // Catch:{ NumberFormatException -> 0x074e }
            goto L_0x074b
        L_0x074a:
            r2 = -1
        L_0x074b:
            r8[r5] = r2     // Catch:{ NumberFormatException -> 0x074e }
            goto L_0x0774
        L_0x074e:
            int r2 = r9.indices
            int r4 = r2 + 1
            r9.indices = r4
            r4 = -1
            r8[r2] = r4
            goto L_0x0774
        L_0x0758:
            r2 = 2
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r2 = 40
            java.lang.Character r5 = java.lang.Character.valueOf(r2)
            r2 = 0
            r4[r2] = r5
            r2 = 41
            java.lang.Character r5 = java.lang.Character.valueOf(r2)
            r2 = 1
            r4[r2] = r5
            boolean r2 = r10.match(r4)
            if (r2 == 0) goto L_0x0774
            goto L_0x078a
        L_0x0774:
            r5 = r30
        L_0x0776:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            r2.next()
            r30 = r5
        L_0x077d:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r10 = r2.get()
            r2 = r37
            r4 = r32
            r5 = 1
            goto L_0x05e4
        L_0x078a:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r4 = 41
            java.lang.Character r6 = java.lang.Character.valueOf(r4)
            r4 = 0
            r5[r4] = r6
            boolean r2 = r2.match(r5)
            if (r2 == 0) goto L_0x07a7
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            r2.next()
        L_0x07a7:
            r4 = r3
            r2 = r30
            r3 = 0
        L_0x07ab:
            java.lang.String r5 = r9.cppName
            int r5 = r5.length()
            if (r5 != 0) goto L_0x07b8
            r5 = r37
            r9.cppName = r5
            goto L_0x07ba
        L_0x07b8:
            r5 = r37
        L_0x07ba:
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.get()
            r32 = r13
            r30 = r14
            r10 = 0
        L_0x07c5:
            r14 = 1
            java.lang.Object[] r13 = new java.lang.Object[r14]
            org.bytedeco.javacpp.tools.Token r19 = org.bytedeco.javacpp.tools.Token.EOF
            r20 = 0
            r13[r20] = r19
            boolean r13 = r6.match(r13)
            if (r13 != 0) goto L_0x0835
            if (r10 != 0) goto L_0x0815
            java.lang.Object[] r13 = new java.lang.Object[r14]
            java.lang.Character r19 = java.lang.Character.valueOf(r16)
            r13[r20] = r19
            boolean r13 = r6.match(r13)
            if (r13 == 0) goto L_0x0815
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.get(r14)
            int r10 = r9.indices     // Catch:{ NumberFormatException -> 0x080a }
            int r13 = r10 + 1
            r9.indices = r13     // Catch:{ NumberFormatException -> 0x080a }
            java.lang.Object[] r13 = new java.lang.Object[r14]     // Catch:{ NumberFormatException -> 0x080a }
            r14 = 0
            r13[r14] = r7     // Catch:{ NumberFormatException -> 0x080a }
            boolean r13 = r6.match(r13)     // Catch:{ NumberFormatException -> 0x080a }
            if (r13 == 0) goto L_0x0806
            java.lang.String r6 = r6.value     // Catch:{ NumberFormatException -> 0x080a }
            java.lang.Integer r6 = java.lang.Integer.decode(r6)     // Catch:{ NumberFormatException -> 0x080a }
            int r6 = r6.intValue()     // Catch:{ NumberFormatException -> 0x080a }
            goto L_0x0807
        L_0x0806:
            r6 = -1
        L_0x0807:
            r8[r10] = r6     // Catch:{ NumberFormatException -> 0x080a }
            goto L_0x0813
        L_0x080a:
            int r6 = r9.indices
            int r10 = r6 + 1
            r9.indices = r10
            r10 = -1
            r8[r6] = r10
        L_0x0813:
            r10 = 1
            goto L_0x082e
        L_0x0815:
            if (r10 != 0) goto L_0x0818
            goto L_0x0835
        L_0x0818:
            if (r10 == 0) goto L_0x082e
            r13 = 1
            java.lang.Object[] r14 = new java.lang.Object[r13]
            r13 = 93
            java.lang.Character r13 = java.lang.Character.valueOf(r13)
            r20 = 0
            r14[r20] = r13
            boolean r6 = r6.match(r14)
            if (r6 == 0) goto L_0x082e
            r10 = 0
        L_0x082e:
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.next()
            goto L_0x07c5
        L_0x0835:
            r6 = r25
        L_0x0837:
            int r10 = r9.indices
            if (r10 <= 0) goto L_0x0849
            if (r6 <= 0) goto L_0x0849
            int r10 = r9.indices
            int r13 = r10 + 1
            r9.indices = r13
            r13 = -1
            r8[r10] = r13
            int r6 = r6 + -1
            goto L_0x0837
        L_0x0849:
            if (r41 == 0) goto L_0x08e7
            int r10 = r9.indices
            if (r10 <= 0) goto L_0x08e7
            int r10 = r9.indirections
            r13 = 1
            int r10 = r10 + r13
            r9.indirections = r10
            r13 = r12
            r10 = 1
        L_0x0857:
            int r14 = r9.indices
            if (r10 >= r14) goto L_0x087d
            r14 = r8[r10]
            if (r14 <= 0) goto L_0x087a
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r13)
            java.lang.String r13 = "["
            r14.append(r13)
            r13 = r8[r10]
            r14.append(r13)
            java.lang.String r13 = "]"
            r14.append(r13)
            java.lang.String r13 = r14.toString()
        L_0x087a:
            int r10 = r10 + 1
            goto L_0x0857
        L_0x087d:
            boolean r10 = r13.isEmpty()
            if (r10 != 0) goto L_0x08d1
            r10 = 0
            r14 = r8[r10]
            r10 = -1
            if (r14 == r10) goto L_0x08aa
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r14 = r31
            r10.append(r14)
            java.lang.String r14 = "(* /*["
            r10.append(r14)
            r25 = r4
            r14 = 0
            r4 = r8[r14]
            r10.append(r4)
            java.lang.String r4 = "]*/ )"
            r10.append(r4)
            java.lang.String r4 = r10.toString()
            goto L_0x08bf
        L_0x08aa:
            r25 = r4
            r14 = r31
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            java.lang.String r10 = "(*)"
            r4.append(r10)
            java.lang.String r4 = r4.toString()
        L_0x08bf:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r4)
            r10.append(r13)
            java.lang.String r4 = r10.toString()
            r10 = r28
            goto L_0x08ee
        L_0x08d1:
            r25 = r4
            r14 = r31
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            r10 = r28
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            goto L_0x08ee
        L_0x08e7:
            r25 = r4
            r10 = r28
            r14 = r31
            r4 = r14
        L_0x08ee:
            if (r42 == 0) goto L_0x0913
            int r13 = r9.indirections
            boolean r14 = r11.anonymous
            r28 = r2
            r2 = 1
            r14 = r14 ^ r2
            if (r13 <= r14) goto L_0x0916
            int r13 = r9.indices
            int r14 = r13 + 1
            r9.indices = r14
            r14 = -1
            r8[r13] = r14
            int r13 = r9.indirections
            int r13 = r13 - r2
            r9.indirections = r13
            int r13 = r4.length()
            int r13 = r13 - r2
            r14 = 0
            java.lang.String r4 = r4.substring(r14, r13)
            goto L_0x0917
        L_0x0913:
            r28 = r2
            r2 = 1
        L_0x0916:
            r14 = 0
        L_0x0917:
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.get()
            java.lang.Object[] r14 = new java.lang.Object[r2]
            r2 = 58
            java.lang.Character r2 = java.lang.Character.valueOf(r2)
            r20 = 0
            r14[r20] = r2
            boolean r2 = r13.match(r14)
            if (r2 == 0) goto L_0x097d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r13 = r11.annotations
            r2.append(r13)
            java.lang.String r13 = "@NoOffset "
            r2.append(r13)
            java.lang.String r2 = r2.toString()
            r11.annotations = r2
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            r13 = 2
            java.lang.Object[] r14 = new java.lang.Object[r13]
            r20 = 0
            r14[r20] = r7
            java.lang.Integer r7 = java.lang.Integer.valueOf(r26)
            r19 = 1
            r14[r19] = r7
            r2.expect(r14)
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            java.lang.Object[] r7 = new java.lang.Object[r13]
            r13 = 44
            java.lang.Character r13 = java.lang.Character.valueOf(r13)
            r7[r20] = r13
            r13 = 59
            java.lang.Character r13 = java.lang.Character.valueOf(r13)
            r7[r19] = r13
            r2.expect(r7)
            java.lang.String r2 = r9.cppName
            if (r2 != 0) goto L_0x097d
            r9.cppName = r12
        L_0x097d:
            r2 = r38
            r7 = r39
            org.bytedeco.javacpp.tools.Parameters r7 = r0.parameters(r1, r2, r7)
            r9.parameters = r7
            java.lang.String r7 = r11.cppName
            java.lang.String r13 = "void"
            boolean r7 = r7.equals(r13)
            if (r7 == 0) goto L_0x09d5
            r7 = 1
            if (r6 != r7) goto L_0x09d6
            if (r29 != 0) goto L_0x09d6
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.get(r7)
            java.lang.Object[] r14 = new java.lang.Object[r7]
            r17 = 40
            java.lang.Character r19 = java.lang.Character.valueOf(r17)
            r20 = 0
            r14[r20] = r19
            boolean r13 = r13.match(r14)
            if (r13 == 0) goto L_0x09d6
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.next()
            java.lang.Object[] r13 = new java.lang.Object[r7]
            java.lang.Character r14 = java.lang.Character.valueOf(r17)
            r13[r20] = r14
            r6.expect(r13)
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.next()
            java.lang.Object[] r13 = new java.lang.Object[r7]
            java.lang.Integer r14 = java.lang.Integer.valueOf(r26)
            r13[r20] = r14
            r6.expect(r13)
            r35.type(r36)
            r6 = 0
            goto L_0x0a2c
        L_0x09d5:
            r7 = 1
        L_0x09d6:
            if (r6 != r7) goto L_0x0a2c
            if (r29 != 0) goto L_0x0a2c
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.get(r7)
            java.lang.Object[] r14 = new java.lang.Object[r7]
            java.lang.Character r19 = java.lang.Character.valueOf(r16)
            r20 = 0
            r14[r20] = r19
            boolean r13 = r13.match(r14)
            if (r13 == 0) goto L_0x0a2c
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.next()
            java.lang.Object[] r14 = new java.lang.Object[r7]
            java.lang.Character r16 = java.lang.Character.valueOf(r16)
            r14[r20] = r16
            r13.expect(r14)
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.next()
            java.lang.Object[] r14 = new java.lang.Object[r7]
            java.lang.Integer r16 = java.lang.Integer.valueOf(r26)
            r14[r20] = r16
            r13.expect(r14)
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.next()
            java.lang.Object[] r14 = new java.lang.Object[r7]
            r16 = 93
            java.lang.Character r16 = java.lang.Character.valueOf(r16)
            r14[r20] = r16
            r13.expect(r14)
            int r13 = r9.indirections
            int r13 = r13 + r7
            r9.indirections = r13
            int r6 = r6 + -1
        L_0x0a2c:
            if (r41 == 0) goto L_0x0a34
            int r13 = r9.indices
            if (r13 <= r7) goto L_0x0a34
            r7 = 1
            goto L_0x0a35
        L_0x0a34:
            r7 = 0
        L_0x0a35:
            org.bytedeco.javacpp.tools.InfoMap r13 = r0.infoMap
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r16 = r4
            r4 = r23
            r14.append(r4)
            r39 = r7
            java.lang.String r7 = r11.cppName
            r14.append(r7)
            java.lang.String r7 = r14.toString()
            r14 = 0
            org.bytedeco.javacpp.tools.Info r7 = r13.getFirst(r7, r14)
            boolean r13 = r11.constValue
            if (r13 == 0) goto L_0x0a64
            int r13 = r9.indirections
            r14 = 2
            if (r13 >= r14) goto L_0x0a64
            boolean r13 = r9.reference
            if (r13 != 0) goto L_0x0a64
            r13 = r7
            r23 = r8
            goto L_0x0a6f
        L_0x0a64:
            org.bytedeco.javacpp.tools.InfoMap r13 = r0.infoMap
            java.lang.String r14 = r11.cppName
            r23 = r8
            r8 = 0
            org.bytedeco.javacpp.tools.Info r13 = r13.getFirst(r14, r8)
        L_0x0a6f:
            java.lang.String r8 = " "
            java.lang.String r14 = " const"
            r26 = r12
            if (r29 == 0) goto L_0x0a86
            org.bytedeco.javacpp.tools.Parameters r12 = r9.parameters
            if (r12 == 0) goto L_0x0a7c
            goto L_0x0a86
        L_0x0a7c:
            r33 = r6
            r41 = r13
        L_0x0a80:
            r34 = r14
            r12 = r24
            goto L_0x0c01
        L_0x0a86:
            if (r7 == 0) goto L_0x0a91
            java.lang.String[] r12 = r7.cppTypes
            if (r12 == 0) goto L_0x0a7c
            java.lang.String[] r7 = r7.cppTypes
            int r7 = r7.length
            if (r7 <= 0) goto L_0x0a7c
        L_0x0a91:
            if (r13 == 0) goto L_0x0a9c
            java.lang.String[] r7 = r13.cppTypes
            if (r7 == 0) goto L_0x0a7c
            java.lang.String[] r7 = r13.cppTypes
            int r7 = r7.length
            if (r7 <= 0) goto L_0x0a7c
        L_0x0a9c:
            if (r13 == 0) goto L_0x0aae
            org.bytedeco.javacpp.tools.Parser r7 = new org.bytedeco.javacpp.tools.Parser
            java.lang.String[] r12 = r13.cppTypes
            r20 = 0
            r12 = r12[r20]
            r7.<init>((org.bytedeco.javacpp.tools.Parser) r0, (java.lang.String) r12)
            org.bytedeco.javacpp.tools.Type r7 = r7.type(r1)
            goto L_0x0aaf
        L_0x0aae:
            r7 = r11
        L_0x0aaf:
            org.bytedeco.javacpp.tools.InfoMap r12 = r0.infoMap
            r41 = r13
            java.lang.String r13 = r7.cppName
            java.util.List r12 = r12.get(r13)
            java.util.Iterator r12 = r12.iterator()
        L_0x0abd:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x0bfd
            java.lang.Object r13 = r12.next()
            org.bytedeco.javacpp.tools.Info r13 = (org.bytedeco.javacpp.tools.Info) r13
            r31 = r12
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            if (r12 == 0) goto L_0x0bed
            java.lang.String[] r12 = r13.annotations
            if (r12 == 0) goto L_0x0bed
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            r33 = r6
            r6 = 0
            r12 = r12[r6]
            boolean r12 = r12.constPointer
            r11.constPointer = r12
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            r12 = r12[r6]
            boolean r12 = r12.constValue
            r11.constValue = r12
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            r12 = r12[r6]
            boolean r12 = r12.simple
            r11.simple = r12
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            r12 = r12[r6]
            int r12 = r12.indirections
            r11.indirections = r12
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            r12 = r12[r6]
            boolean r12 = r12.reference
            r11.reference = r12
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            r12 = r12[r6]
            java.lang.String r12 = r12.annotations
            r11.annotations = r12
            org.bytedeco.javacpp.tools.Type[] r12 = r7.arguments
            r12 = r12[r6]
            java.lang.String r12 = r12.cppName
            r11.cppName = r12
            org.bytedeco.javacpp.tools.Type[] r7 = r7.arguments
            r7 = r7[r6]
            java.lang.String r7 = r7.javaName
            r11.javaName = r7
            r7 = 1
            r9.indirections = r7
            r9.reference = r6
            boolean r6 = r1.virtualize
            if (r6 == 0) goto L_0x0b21
            r6 = 1
            goto L_0x0b25
        L_0x0b21:
            r6 = r39
            r16 = 0
        L_0x0b25:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r12 = r11.cppName
            r7.append(r12)
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            boolean r12 = r11.constValue
            if (r12 == 0) goto L_0x0b4f
            boolean r12 = r7.startsWith(r4)
            if (r12 != 0) goto L_0x0b4f
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r4)
            r12.append(r7)
            java.lang.String r7 = r12.toString()
        L_0x0b4f:
            int r12 = r11.indirections
            if (r12 <= 0) goto L_0x0b73
            int r12 = r9.indirections
            r39 = r6
            int r6 = r11.indirections
            int r12 = r12 + r6
            r9.indirections = r12
            r6 = 0
        L_0x0b5d:
            int r12 = r11.indirections
            if (r6 >= r12) goto L_0x0b75
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r7)
            r12.append(r10)
            java.lang.String r7 = r12.toString()
            int r6 = r6 + 1
            goto L_0x0b5d
        L_0x0b73:
            r39 = r6
        L_0x0b75:
            boolean r6 = r11.reference
            if (r6 == 0) goto L_0x0b8e
            r6 = 1
            r9.reference = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r12 = r24
            r6.append(r12)
            java.lang.String r7 = r6.toString()
            goto L_0x0b90
        L_0x0b8e:
            r12 = r24
        L_0x0b90:
            boolean r6 = r11.constPointer
            if (r6 == 0) goto L_0x0baa
            boolean r6 = r7.endsWith(r14)
            if (r6 != 0) goto L_0x0baa
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r7)
            r6.append(r14)
            java.lang.String r6 = r6.toString()
            goto L_0x0bab
        L_0x0baa:
            r6 = r7
        L_0x0bab:
            java.lang.String[] r7 = r13.annotations
            int r13 = r7.length
            r41 = r6
            r6 = 0
        L_0x0bb1:
            if (r6 >= r13) goto L_0x0bda
            r24 = r13
            r13 = r7[r6]
            r31 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r34 = r14
            java.lang.String r14 = r11.annotations
            r7.append(r14)
            r7.append(r13)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11.annotations = r7
            int r6 = r6 + 1
            r13 = r24
            r7 = r31
            r14 = r34
            goto L_0x0bb1
        L_0x0bda:
            r34 = r14
            org.bytedeco.javacpp.tools.InfoMap r6 = r0.infoMap
            java.lang.String r7 = r11.cppName
            r13 = 0
            org.bytedeco.javacpp.tools.Info r6 = r6.getFirst(r7, r13)
            r13 = r39
            r7 = r41
            r14 = r6
            r6 = r16
            goto L_0x0c08
        L_0x0bed:
            r33 = r6
            r34 = r14
            r12 = r24
            r24 = r12
            r12 = r31
            r6 = r33
            r14 = r34
            goto L_0x0abd
        L_0x0bfd:
            r33 = r6
            goto L_0x0a80
        L_0x0c01:
            r13 = r39
            r14 = r41
            r7 = r16
            r6 = 0
        L_0x0c08:
            if (r21 != 0) goto L_0x0cad
            if (r14 == 0) goto L_0x0cad
            r24 = r12
            boolean r12 = r14.enumerate
            if (r12 != 0) goto L_0x0c16
            java.lang.String[] r12 = r14.valueTypes
            if (r12 == 0) goto L_0x0c32
        L_0x0c16:
            boolean r12 = r11.constValue
            if (r12 == 0) goto L_0x0c22
            int r12 = r9.indirections
            if (r12 != 0) goto L_0x0c22
            boolean r12 = r9.reference
            if (r12 != 0) goto L_0x0c2e
        L_0x0c22:
            int r12 = r9.indirections
            if (r12 != 0) goto L_0x0c2a
            boolean r12 = r9.reference
            if (r12 == 0) goto L_0x0c2e
        L_0x0c2a:
            java.lang.String[] r12 = r14.pointerTypes
            if (r12 != 0) goto L_0x0c32
        L_0x0c2e:
            r16 = r10
            r12 = 1
            goto L_0x0c35
        L_0x0c32:
            r16 = r10
            r12 = 0
        L_0x0c35:
            java.lang.String[] r10 = r14.cppNames
            r20 = 0
            r10 = r10[r20]
            boolean r10 = r10.startsWith(r4)
            if (r10 == 0) goto L_0x0c47
            boolean r10 = r14.define
            if (r10 != 0) goto L_0x0c47
            r10 = 1
            goto L_0x0c48
        L_0x0c47:
            r10 = 0
        L_0x0c48:
            r39 = r10
            if (r12 == 0) goto L_0x0c54
            java.lang.String[] r10 = r14.valueTypes
            if (r10 == 0) goto L_0x0c5c
            java.lang.String[] r10 = r14.valueTypes
            int r10 = r10.length
            goto L_0x0c5d
        L_0x0c54:
            java.lang.String[] r10 = r14.pointerTypes
            if (r10 == 0) goto L_0x0c5c
            java.lang.String[] r10 = r14.pointerTypes
            int r10 = r10.length
            goto L_0x0c5d
        L_0x0c5c:
            r10 = 1
        L_0x0c5d:
            if (r2 >= 0) goto L_0x0c63
            r41 = r10
            r10 = 0
            goto L_0x0c69
        L_0x0c63:
            int r21 = r2 % r10
            r41 = r10
            r10 = r21
        L_0x0c69:
            r9.infoNumber = r10
            if (r12 == 0) goto L_0x0c7d
            java.lang.String[] r10 = r14.valueTypes
            if (r10 == 0) goto L_0x0c7a
            java.lang.String[] r10 = r14.valueTypes
            r21 = r12
            int r12 = r9.infoNumber
            r10 = r10[r12]
            goto L_0x0c8c
        L_0x0c7a:
            r21 = r12
            goto L_0x0c8a
        L_0x0c7d:
            r21 = r12
            java.lang.String[] r10 = r14.pointerTypes
            if (r10 == 0) goto L_0x0c8a
            java.lang.String[] r10 = r14.pointerTypes
            int r12 = r9.infoNumber
            r10 = r10[r12]
            goto L_0x0c8c
        L_0x0c8a:
            java.lang.String r10 = r11.javaName
        L_0x0c8c:
            r11.javaName = r10
            java.lang.String r10 = r11.javaName
            java.lang.String r10 = r1.shorten(r10)
            r11.javaName = r10
            boolean r10 = r14.cast
            if (r10 == 0) goto L_0x0ca6
            java.lang.String r10 = r11.cppName
            java.lang.String r12 = r11.javaName
            boolean r10 = r10.equals(r12)
            if (r10 != 0) goto L_0x0ca6
            r10 = 1
            goto L_0x0ca7
        L_0x0ca6:
            r10 = 0
        L_0x0ca7:
            r10 = r10 | r13
            r13 = r39
            r12 = r41
            goto L_0x0cb6
        L_0x0cad:
            r16 = r10
            r24 = r12
            r10 = r13
            r12 = 1
            r13 = 0
            r21 = 0
        L_0x0cb6:
            if (r21 == 0) goto L_0x0cc3
            boolean r14 = r1.virtualize
            if (r14 == 0) goto L_0x0cbd
            goto L_0x0cc3
        L_0x0cbd:
            r31 = r3
            r39 = r8
            goto L_0x0dfd
        L_0x0cc3:
            if (r21 != 0) goto L_0x0ce8
            int r14 = r9.indirections
            if (r14 != 0) goto L_0x0ce8
            boolean r14 = r9.reference
            if (r14 != 0) goto L_0x0ce8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r12 = r11.annotations
            r2.append(r12)
            java.lang.String r12 = "@ByVal "
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            r11.annotations = r2
            r31 = r3
            r39 = r8
            goto L_0x0dd4
        L_0x0ce8:
            int r14 = r9.indirections
            r39 = r8
            java.lang.String r8 = "@ByPtrRef "
            r31 = r3
            java.lang.String r3 = "@ByPtrPtr "
            if (r14 != 0) goto L_0x0d21
            boolean r14 = r9.reference
            if (r14 == 0) goto L_0x0d21
            java.lang.String r2 = r11.javaName
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x0d0a
            java.lang.String r2 = r11.javaName
            java.lang.String r2 = r2.replace(r3, r8)
            r11.javaName = r2
            goto L_0x0dd4
        L_0x0d0a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r11.annotations
            r2.append(r3)
            java.lang.String r3 = "@ByRef "
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r11.annotations = r2
            goto L_0x0dd4
        L_0x0d21:
            java.lang.String r14 = r11.javaName
            boolean r14 = r14.contains(r8)
            if (r14 != 0) goto L_0x0d47
            int r14 = r9.indirections
            r0 = 1
            if (r14 != r0) goto L_0x0d47
            boolean r0 = r9.reference
            if (r0 == 0) goto L_0x0d47
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r11.annotations
            r0.append(r2)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            r11.annotations = r0
            goto L_0x0dd4
        L_0x0d47:
            java.lang.String r0 = r11.javaName
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x0d82
            int r0 = r9.indirections
            r14 = 2
            if (r0 != r14) goto L_0x0d82
            boolean r0 = r9.reference
            if (r0 != 0) goto L_0x0d82
            if (r2 >= 0) goto L_0x0d64
            java.lang.String r0 = r11.javaName
            java.lang.String r2 = "PointerPointer"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0d82
        L_0x0d64:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r11.annotations
            r0.append(r2)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r11.annotations = r0
            java.lang.String r0 = r11.cppName
            java.lang.String r2 = "void"
            boolean r0 = r0.equals(r2)
            r0 = r0 | r10
            r10 = r0
            goto L_0x0dd4
        L_0x0d82:
            int r0 = r9.indirections
            r2 = 2
            if (r0 < r2) goto L_0x0dd4
            int r0 = r9.infoNumber
            int r0 = r0 + r12
            r9.infoNumber = r0
            java.lang.String r0 = r11.javaName
            boolean r0 = r0.contains(r8)
            if (r0 != 0) goto L_0x0dba
            boolean r0 = r9.reference
            if (r0 == 0) goto L_0x0d99
            goto L_0x0dba
        L_0x0d99:
            java.lang.String r0 = r11.javaName
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x0da6
            int r0 = r9.indirections
            r2 = 3
            if (r0 < r2) goto L_0x0dcf
        L_0x0da6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r11.annotations
            r0.append(r2)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r11.annotations = r0
            goto L_0x0dcf
        L_0x0dba:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r11.annotations
            r0.append(r2)
            java.lang.String r2 = "@ByRef "
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r11.annotations = r0
        L_0x0dcf:
            java.lang.String r0 = "PointerPointer"
            r11.javaName = r0
            r10 = 1
        L_0x0dd4:
            if (r10 != 0) goto L_0x0dfd
            java.lang.String r0 = r11.javaName
            java.lang.String r2 = "@Cast"
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L_0x0dfd
            boolean r0 = r11.constValue
            if (r0 == 0) goto L_0x0dfb
            if (r13 != 0) goto L_0x0dfb
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "@Const "
            r0.append(r2)
            java.lang.String r2 = r11.annotations
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r11.annotations = r0
        L_0x0dfb:
            boolean r0 = r11.constPointer
        L_0x0dfd:
            java.lang.String r0 = "@Cast(\""
            if (r10 == 0) goto L_0x0ea6
            int r2 = r9.indirections
            if (r2 != 0) goto L_0x0e11
            boolean r2 = r9.reference
            if (r2 == 0) goto L_0x0e11
            r2 = 38
            r3 = 42
            java.lang.String r7 = r7.replace(r2, r3)
        L_0x0e11:
            if (r21 == 0) goto L_0x0e26
            boolean r2 = r11.constValue
            if (r2 == 0) goto L_0x0e26
            boolean r2 = r9.reference
            if (r2 == 0) goto L_0x0e26
            int r2 = r7.length()
            r3 = 1
            int r2 = r2 - r3
            r3 = 0
            java.lang.String r7 = r7.substring(r3, r2)
        L_0x0e26:
            boolean r2 = r11.constValue
            if (r2 == 0) goto L_0x0e3f
            boolean r2 = r7.startsWith(r4)
            if (r2 != 0) goto L_0x0e3f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
        L_0x0e3f:
            if (r6 == 0) goto L_0x0e67
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "@Cast({\""
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = "\", \""
            r2.append(r3)
            r2.append(r6)
            java.lang.String r3 = "\"}) "
            r2.append(r3)
            java.lang.String r3 = r11.annotations
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r11.annotations = r2
            goto L_0x0ea6
        L_0x0e67:
            if (r21 != 0) goto L_0x0e8d
            int r2 = r9.indirections
            if (r2 != 0) goto L_0x0e8d
            boolean r2 = r9.reference
            if (r2 != 0) goto L_0x0e8d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r11.annotations
            r2.append(r3)
            r2.append(r0)
            r2.append(r7)
            java.lang.String r3 = "*\") "
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r11.annotations = r2
            goto L_0x0ea6
        L_0x0e8d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r7)
            r2.append(r15)
            java.lang.String r3 = r11.annotations
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r11.annotations = r2
        L_0x0ea6:
            r2 = r22
            if (r22 == 0) goto L_0x0ead
            java.lang.String r3 = r2.arguments
            goto L_0x0eaf
        L_0x0ead:
            java.lang.String r3 = r9.cppName
        L_0x0eaf:
            r9.javaName = r3
            if (r5 != 0) goto L_0x0ee0
            java.lang.String r3 = r9.cppName
            java.lang.String[] r3 = r1.qualify(r3)
            int r6 = r3.length
            r7 = 0
            r8 = 0
        L_0x0ebc:
            if (r8 >= r6) goto L_0x0edc
            r7 = r3[r8]
            r10 = r35
            org.bytedeco.javacpp.tools.InfoMap r12 = r10.infoMap
            r13 = 0
            org.bytedeco.javacpp.tools.Info r12 = r12.getFirst(r7, r13)
            if (r12 == 0) goto L_0x0ece
            r9.cppName = r7
            goto L_0x0ee3
        L_0x0ece:
            org.bytedeco.javacpp.tools.InfoMap r13 = r10.infoMap
            org.bytedeco.javacpp.tools.Info r13 = r13.getFirst(r7)
            if (r13 == 0) goto L_0x0ed8
            r9.cppName = r7
        L_0x0ed8:
            int r8 = r8 + 1
            r7 = r12
            goto L_0x0ebc
        L_0x0edc:
            r10 = r35
            r12 = r7
            goto L_0x0ee3
        L_0x0ee0:
            r10 = r35
            r12 = 0
        L_0x0ee3:
            r3 = r28
            if (r31 == 0) goto L_0x0eed
            java.lang.String[] r6 = r3.pointerTypes
            r7 = 0
            r6 = r6[r7]
            goto L_0x0eef
        L_0x0eed:
            java.lang.String r6 = r9.javaName
        L_0x0eef:
            if (r2 != 0) goto L_0x0f20
            if (r5 != 0) goto L_0x0f20
            if (r12 == 0) goto L_0x0f20
            java.lang.String[] r2 = r12.javaNames
            if (r2 == 0) goto L_0x0f20
            java.lang.String[] r2 = r12.javaNames
            int r2 = r2.length
            if (r2 <= 0) goto L_0x0f20
            boolean r2 = r9.operator
            if (r2 != 0) goto L_0x0f19
            java.lang.String[] r2 = r12.cppNames
            r5 = 0
            r2 = r2[r5]
            java.lang.String r5 = "<"
            boolean r2 = r2.contains(r5)
            if (r2 == 0) goto L_0x0f19
            org.bytedeco.javacpp.tools.TemplateMap r2 = r1.templateMap
            if (r2 == 0) goto L_0x0f20
            org.bytedeco.javacpp.tools.TemplateMap r2 = r1.templateMap
            org.bytedeco.javacpp.tools.Type r2 = r2.type
            if (r2 != 0) goto L_0x0f20
        L_0x0f19:
            java.lang.String[] r2 = r12.javaNames
            r5 = 0
            r2 = r2[r5]
            r9.javaName = r2
        L_0x0f20:
            if (r12 == 0) goto L_0x0f56
            java.lang.String[] r2 = r12.annotations
            if (r2 == 0) goto L_0x0f56
            java.lang.String[] r2 = r12.annotations
            int r5 = r2.length
            r7 = 0
        L_0x0f2a:
            if (r7 >= r5) goto L_0x0f56
            r8 = r2[r7]
            java.lang.String r12 = r11.annotations
            boolean r12 = r12.contains(r8)
            if (r12 != 0) goto L_0x0f4f
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = r11.annotations
            r12.append(r13)
            r12.append(r8)
            r8 = r39
            r12.append(r8)
            java.lang.String r12 = r12.toString()
            r11.annotations = r12
            goto L_0x0f51
        L_0x0f4f:
            r8 = r39
        L_0x0f51:
            int r7 = r7 + 1
            r39 = r8
            goto L_0x0f2a
        L_0x0f56:
            r8 = r39
            r9.type = r11
            java.lang.String r2 = r9.javaName
            r9.signature = r2
            org.bytedeco.javacpp.tools.Parameters r2 = r9.parameters
            if (r2 != 0) goto L_0x0f68
            if (r31 == 0) goto L_0x0f65
            goto L_0x0f68
        L_0x0f65:
            r4 = r15
            goto L_0x142e
        L_0x0f68:
            org.bytedeco.javacpp.tools.Parameters r2 = r9.parameters
            if (r2 == 0) goto L_0x0f78
            int r2 = r9.infoNumber
            org.bytedeco.javacpp.tools.Parameters r5 = r9.parameters
            int r5 = r5.infoNumber
            int r2 = java.lang.Math.max(r2, r5)
            r9.infoNumber = r2
        L_0x0f78:
            org.bytedeco.javacpp.tools.Parameters r2 = r9.parameters
            if (r2 == 0) goto L_0x0f98
            if (r33 != 0) goto L_0x0f98
            if (r29 != 0) goto L_0x0f98
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r9.signature
            r0.append(r2)
            org.bytedeco.javacpp.tools.Parameters r2 = r9.parameters
            java.lang.String r2 = r2.signature
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r9.signature = r0
            goto L_0x0f65
        L_0x0f98:
            if (r25 == 0) goto L_0x0fbc
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r5 = r32
            java.lang.String r7 = r5.text
            r2.append(r7)
            java.lang.String r7 = "@Convention(\""
            r2.append(r7)
            r7 = r25
            java.lang.String r7 = r7.cppName
            r2.append(r7)
            r2.append(r15)
            java.lang.String r2 = r2.toString()
            r5.text = r2
            goto L_0x0fbe
        L_0x0fbc:
            r5 = r32
        L_0x0fbe:
            org.bytedeco.javacpp.tools.Type r2 = r9.type
            if (r2 == 0) goto L_0x1046
            org.bytedeco.javacpp.tools.Type r2 = r9.type
            java.lang.String r2 = r2.cppName
            org.bytedeco.javacpp.tools.Type r7 = r9.type
            boolean r7 = r7.constValue
            if (r7 == 0) goto L_0x0fe1
            boolean r7 = r2.startsWith(r4)
            if (r7 != 0) goto L_0x0fe1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r4)
            r7.append(r2)
            java.lang.String r2 = r7.toString()
        L_0x0fe1:
            r7 = 0
        L_0x0fe2:
            int r12 = r9.indirections
            if (r7 >= r12) goto L_0x0ffa
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r2)
            r13 = r16
            r12.append(r13)
            java.lang.String r2 = r12.toString()
            int r7 = r7 + 1
            goto L_0x0fe2
        L_0x0ffa:
            r13 = r16
            boolean r7 = r9.reference
            if (r7 == 0) goto L_0x1012
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r2)
            r12 = r24
            r7.append(r12)
            java.lang.String r2 = r7.toString()
            goto L_0x1014
        L_0x1012:
            r12 = r24
        L_0x1014:
            org.bytedeco.javacpp.tools.Type r7 = r9.type
            boolean r7 = r7.constPointer
            if (r7 == 0) goto L_0x1032
            r7 = r34
            boolean r14 = r2.endsWith(r7)
            if (r14 != 0) goto L_0x1034
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r2)
            r14.append(r7)
            java.lang.String r2 = r14.toString()
            goto L_0x1034
        L_0x1032:
            r7 = r34
        L_0x1034:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r1 = r26
            r14.append(r1)
            r14.append(r2)
            java.lang.String r2 = r14.toString()
            goto L_0x104f
        L_0x1046:
            r13 = r16
            r12 = r24
            r1 = r26
            r7 = r34
            r2 = r1
        L_0x104f:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r2)
            java.lang.String r2 = " (*)("
            r14.append(r2)
            java.lang.String r2 = r14.toString()
            org.bytedeco.javacpp.tools.Parameters r14 = r9.parameters
            if (r14 == 0) goto L_0x110e
            org.bytedeco.javacpp.tools.Parameters r14 = r9.parameters
            org.bytedeco.javacpp.tools.Declarator[] r14 = r14.declarators
            r26 = r1
            int r1 = r14.length
            r38 = r0
            r40 = r15
            r0 = r26
            r15 = 0
        L_0x1072:
            if (r15 >= r1) goto L_0x110b
            r16 = r1
            r1 = r14[r15]
            if (r1 == 0) goto L_0x10f9
            r21 = r14
            org.bytedeco.javacpp.tools.Type r14 = r1.type
            java.lang.String r14 = r14.cppName
            r28 = r3
            org.bytedeco.javacpp.tools.Type r3 = r1.type
            boolean r3 = r3.constValue
            if (r3 == 0) goto L_0x109d
            boolean r3 = r14.startsWith(r4)
            if (r3 != 0) goto L_0x109d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r4)
            r3.append(r14)
            java.lang.String r14 = r3.toString()
        L_0x109d:
            r22 = r4
            r3 = 0
        L_0x10a0:
            int r4 = r1.indirections
            if (r3 >= r4) goto L_0x10b6
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            r4.append(r13)
            java.lang.String r14 = r4.toString()
            int r3 = r3 + 1
            goto L_0x10a0
        L_0x10b6:
            boolean r3 = r1.reference
            if (r3 == 0) goto L_0x10c9
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r12)
            java.lang.String r14 = r3.toString()
        L_0x10c9:
            org.bytedeco.javacpp.tools.Type r1 = r1.type
            boolean r1 = r1.constPointer
            if (r1 == 0) goto L_0x10e4
            boolean r1 = r14.endsWith(r7)
            if (r1 != 0) goto L_0x10e4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r14)
            r1.append(r7)
            java.lang.String r14 = r1.toString()
        L_0x10e4:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            r1.append(r0)
            r1.append(r14)
            java.lang.String r2 = r1.toString()
            java.lang.String r0 = ", "
            goto L_0x10ff
        L_0x10f9:
            r28 = r3
            r22 = r4
            r21 = r14
        L_0x10ff:
            int r15 = r15 + 1
            r1 = r16
            r14 = r21
            r4 = r22
            r3 = r28
            goto L_0x1072
        L_0x110b:
            r28 = r3
            goto L_0x1116
        L_0x110e:
            r38 = r0
            r26 = r1
            r28 = r3
            r40 = r15
        L_0x1116:
            org.bytedeco.javacpp.tools.InfoMap r0 = r10.infoMap
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            org.bytedeco.javacpp.tools.Info r0 = r0.getFirst(r1)
            if (r0 != 0) goto L_0x1137
            org.bytedeco.javacpp.tools.InfoMap r0 = r10.infoMap
            java.lang.String r2 = r9.cppName
            org.bytedeco.javacpp.tools.Info r0 = r0.getFirst(r2)
        L_0x1137:
            if (r6 == 0) goto L_0x1157
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r3 = 0
            char r4 = r6.charAt(r3)
            char r3 = java.lang.Character.toUpperCase(r4)
            r2.append(r3)
            r3 = 1
            java.lang.String r4 = r6.substring(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            goto L_0x1158
        L_0x1157:
            r2 = 0
        L_0x1158:
            if (r0 == 0) goto L_0x1169
            java.lang.String[] r3 = r0.pointerTypes
            if (r3 == 0) goto L_0x1169
            java.lang.String[] r3 = r0.pointerTypes
            int r3 = r3.length
            if (r3 <= 0) goto L_0x1169
            java.lang.String[] r2 = r0.pointerTypes
            r3 = 0
            r6 = r2[r3]
            goto L_0x11d2
        L_0x1169:
            if (r29 == 0) goto L_0x116c
            goto L_0x11d2
        L_0x116c:
            org.bytedeco.javacpp.tools.Parameters r3 = r9.parameters
            if (r3 == 0) goto L_0x118e
            org.bytedeco.javacpp.tools.Parameters r3 = r9.parameters
            java.lang.String r3 = r3.signature
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x118e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            org.bytedeco.javacpp.tools.Parameters r2 = r9.parameters
            java.lang.String r2 = r2.signature
            r3.append(r2)
            java.lang.String r6 = r3.toString()
            goto L_0x11d2
        L_0x118e:
            java.lang.String r3 = r11.javaName
            java.lang.String r4 = "void"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x11d1
            java.lang.String r3 = r11.javaName
            java.lang.String r3 = r3.trim()
            r4 = 32
            int r4 = r3.lastIndexOf(r4)
            r6 = 1
            if (r4 <= 0) goto L_0x11ac
            int r4 = r4 + r6
            java.lang.String r3 = r3.substring(r4)
        L_0x11ac:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r7 = 0
            char r12 = r3.charAt(r7)
            char r7 = java.lang.Character.toUpperCase(r12)
            r4.append(r7)
            java.lang.String r3 = r3.substring(r6)
            r4.append(r3)
            java.lang.String r3 = "_"
            r4.append(r3)
            r4.append(r2)
            java.lang.String r6 = r4.toString()
            goto L_0x11d2
        L_0x11d1:
            r6 = r2
        L_0x11d2:
            if (r0 == 0) goto L_0x11f9
            java.lang.String[] r2 = r0.annotations
            if (r2 == 0) goto L_0x11f9
            java.lang.String[] r2 = r0.annotations
            int r3 = r2.length
            r4 = 0
        L_0x11dc:
            if (r4 >= r3) goto L_0x11f9
            r7 = r2[r4]
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r14 = r5.text
            r12.append(r14)
            r12.append(r7)
            r12.append(r8)
            java.lang.String r7 = r12.toString()
            r5.text = r7
            int r4 = r4 + 1
            goto L_0x11dc
        L_0x11f9:
            if (r6 != 0) goto L_0x11fd
            java.lang.String r6 = "null"
        L_0x11fd:
            r2 = 32
            int r2 = r6.lastIndexOf(r2)
            r3 = 1
            int r2 = r2 + r3
            java.lang.String r2 = r6.substring(r2)
            java.lang.String r3 = "Pointer"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x1396
            org.bytedeco.javacpp.tools.Type r3 = new org.bytedeco.javacpp.tools.Type
            r3.<init>(r2)
            r5.type = r3
            org.bytedeco.javacpp.tools.InfoMap r3 = r10.infoMap
            java.lang.String r4 = "function/pointers"
            java.util.List r3 = r3.get(r4)
            java.util.Iterator r3 = r3.iterator()
        L_0x1224:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x1257
            java.lang.Object r4 = r3.next()
            org.bytedeco.javacpp.tools.Info r4 = (org.bytedeco.javacpp.tools.Info) r4
            if (r4 == 0) goto L_0x1224
            java.lang.String[] r6 = r4.annotations
            if (r6 == 0) goto L_0x1224
            java.lang.String[] r4 = r4.annotations
            int r6 = r4.length
            r7 = 0
        L_0x123a:
            if (r7 >= r6) goto L_0x1224
            r12 = r4[r7]
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = r5.text
            r14.append(r15)
            r14.append(r12)
            r14.append(r8)
            java.lang.String r12 = r14.toString()
            r5.text = r12
            int r7 = r7 + 1
            goto L_0x123a
        L_0x1257:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.text
            r3.append(r4)
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r10.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get()
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.CONST
            r8 = 0
            r6[r8] = r7
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.__CONST
            r8 = 1
            r6[r8] = r7
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.CONSTEXPR
            r8 = 2
            r6[r8] = r7
            boolean r4 = r4.match(r6)
            if (r4 == 0) goto L_0x1282
            java.lang.String r4 = "@Const "
            goto L_0x1284
        L_0x1282:
            r4 = r26
        L_0x1284:
            r3.append(r4)
            java.lang.String r4 = "public static class "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = " extends FunctionPointer {\n    static { Loader.load(); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public    "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r4 = "(Pointer p) { super(p); }\n"
            r3.append(r4)
            if (r28 == 0) goto L_0x12a1
            r4 = r26
            goto L_0x12b7
        L_0x12a1:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "    protected "
            r4.append(r6)
            r4.append(r2)
            java.lang.String r6 = "() { allocate(); }\n    private native void allocate();\n"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
        L_0x12b7:
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            if (r31 == 0) goto L_0x131a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r5.text
            r3.append(r4)
            java.lang.String r4 = "    public native "
            r3.append(r4)
            java.lang.String r4 = r11.annotations
            r3.append(r4)
            java.lang.String r4 = r11.javaName
            r3.append(r4)
            java.lang.String r4 = " get("
            r3.append(r4)
            r4 = r28
            java.lang.String[] r6 = r4.pointerTypes
            r7 = 0
            r6 = r6[r7]
            r3.append(r6)
            java.lang.String r6 = " o);\n    public native "
            r3.append(r6)
            r3.append(r2)
            java.lang.String r6 = " put("
            r3.append(r6)
            java.lang.String[] r4 = r4.pointerTypes
            r4 = r4[r7]
            r3.append(r4)
            java.lang.String r4 = " o, "
            r3.append(r4)
            java.lang.String r4 = r11.annotations
            r3.append(r4)
            java.lang.String r4 = r11.javaName
            r3.append(r4)
            java.lang.String r4 = " v);\n}\n"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r5.text = r3
            goto L_0x1396
        L_0x131a:
            r4 = r28
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = r5.text
            r3.append(r6)
            java.lang.String r6 = "    public native "
            r3.append(r6)
            java.lang.String r6 = r11.annotations
            r3.append(r6)
            java.lang.String r6 = r11.javaName
            r3.append(r6)
            java.lang.String r6 = " call"
            r3.append(r6)
            if (r4 == 0) goto L_0x1384
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "("
            r6.append(r7)
            java.lang.String[] r4 = r4.pointerTypes
            r7 = 0
            r4 = r4[r7]
            r6.append(r4)
            java.lang.String r4 = " o"
            r6.append(r4)
            org.bytedeco.javacpp.tools.Parameters r4 = r9.parameters
            java.lang.String r4 = r4.list
            r7 = 1
            char r4 = r4.charAt(r7)
            r8 = 41
            if (r4 != r8) goto L_0x1363
            java.lang.String r4 = ")"
            goto L_0x137c
        L_0x1363:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = ", "
            r4.append(r8)
            org.bytedeco.javacpp.tools.Parameters r8 = r9.parameters
            java.lang.String r8 = r8.list
            java.lang.String r8 = r8.substring(r7)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
        L_0x137c:
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            goto L_0x1388
        L_0x1384:
            org.bytedeco.javacpp.tools.Parameters r4 = r9.parameters
            java.lang.String r4 = r4.list
        L_0x1388:
            r3.append(r4)
            java.lang.String r4 = ";\n}\n"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r5.text = r3
        L_0x1396:
            r5.signature = r2
            org.bytedeco.javacpp.tools.Declarator r3 = new org.bytedeco.javacpp.tools.Declarator
            r3.<init>()
            r5.declarator = r3
            org.bytedeco.javacpp.tools.Declarator r3 = r5.declarator
            org.bytedeco.javacpp.tools.Parameters r4 = r9.parameters
            r3.parameters = r4
            if (r0 == 0) goto L_0x13ab
            boolean r3 = r0.skip
            if (r3 != 0) goto L_0x13ad
        L_0x13ab:
            r9.definition = r5
        L_0x13ad:
            r6 = r33
            r9.indirections = r6
            if (r42 == 0) goto L_0x13c7
            int r3 = r9.indirections
            r4 = 1
            if (r3 <= r4) goto L_0x13c8
            int r3 = r9.indices
            int r5 = r3 + 1
            r9.indices = r5
            r5 = -1
            r23[r3] = r5
            int r3 = r9.indirections
            int r3 = r3 - r4
            r9.indirections = r3
            goto L_0x13c8
        L_0x13c7:
            r4 = 1
        L_0x13c8:
            if (r31 != 0) goto L_0x13cd
            r3 = 0
            r9.parameters = r3
        L_0x13cd:
            int r3 = r9.indirections
            if (r3 <= r4) goto L_0x1404
            r3 = 40
            int r0 = r1.indexOf(r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r3 = r38
            r2.append(r3)
            int r0 = r0 + r4
            r3 = 0
            java.lang.String r4 = r1.substring(r3, r0)
            r2.append(r4)
            r2.append(r13)
            java.lang.String r0 = r1.substring(r0)
            r2.append(r0)
            r4 = r40
            r2.append(r4)
            java.lang.String r0 = r2.toString()
            r11.annotations = r0
            java.lang.String r0 = "PointerPointer"
            r11.javaName = r0
            goto L_0x142e
        L_0x1404:
            r3 = r38
            r4 = r40
            if (r0 == 0) goto L_0x1421
            boolean r0 = r0.cast
            if (r0 == 0) goto L_0x1421
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            r0.append(r1)
            r0.append(r4)
            java.lang.String r12 = r0.toString()
            goto L_0x142a
        L_0x1421:
            boolean r0 = r9.constPointer
            if (r0 == 0) goto L_0x1428
            java.lang.String r12 = "@Const "
            goto L_0x142a
        L_0x1428:
            r12 = r26
        L_0x142a:
            r11.annotations = r12
            r11.javaName = r2
        L_0x142e:
            java.lang.String r0 = r9.cppName
            if (r0 == 0) goto L_0x14a0
            java.lang.String r0 = r9.cppName
            r1 = r36
            java.lang.String r2 = r1.namespace
            if (r2 == 0) goto L_0x1462
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r1.namespace
            r2.append(r3)
            r3 = r30
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x1464
            java.lang.String r0 = r9.cppName
            java.lang.String r2 = r1.namespace
            int r2 = r2.length()
            r5 = 2
            int r2 = r2 + r5
            java.lang.String r0 = r0.substring(r2)
            goto L_0x1464
        L_0x1462:
            r3 = r30
        L_0x1464:
            r2 = 60
            int r2 = r0.lastIndexOf(r2)
            if (r2 < 0) goto L_0x1472
            r5 = 0
            java.lang.String r2 = r0.substring(r5, r2)
            goto L_0x1473
        L_0x1472:
            r2 = r0
        L_0x1473:
            java.lang.String r5 = r9.javaName
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L_0x14a0
            boolean r2 = r0.contains(r3)
            if (r2 == 0) goto L_0x1485
            java.lang.String r1 = r1.javaName
            if (r1 != 0) goto L_0x14a0
        L_0x1485:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r11.annotations
            r1.append(r2)
            java.lang.String r2 = "@Name(\""
            r1.append(r2)
            r1.append(r0)
            r1.append(r4)
            java.lang.String r0 = r1.toString()
            r11.annotations = r0
        L_0x14a0:
            r14 = r27
        L_0x14a2:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r10.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 41
            java.lang.Character r4 = java.lang.Character.valueOf(r3)
            r5 = 0
            r2[r5] = r4
            boolean r0 = r0.match(r2)
            if (r0 == 0) goto L_0x14c4
            if (r14 <= 0) goto L_0x14c4
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r10.tokens
            r0.next()
            int r14 = r14 + -1
            goto L_0x14a2
        L_0x14c4:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.declarator(org.bytedeco.javacpp.tools.Context, java.lang.String, int, boolean, int, boolean, boolean):org.bytedeco.javacpp.tools.Declarator");
    }

    /* access modifiers changed from: package-private */
    public String commentDoc(String str, int i) {
        if (i < 0 || i > str.length()) {
            return str;
        }
        int indexOf = str.indexOf("/**", i);
        StringBuilder sb = new StringBuilder(str);
        while (indexOf < sb.length()) {
            char charAt = sb.charAt(indexOf);
            int i2 = indexOf + 1;
            String substring = sb.substring(i2);
            String str2 = " ";
            String str3 = "";
            if (charAt == '`' && substring.startsWith("``") && sb.length() - indexOf > 3) {
                int i3 = indexOf + 3;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("<pre>{@code");
                if (Character.isWhitespace(sb.charAt(i3))) {
                    str2 = str3;
                }
                sb2.append(str2);
                sb.replace(indexOf, i3, sb2.toString());
                indexOf = sb.indexOf("```", indexOf);
                if (indexOf < 0) {
                    break;
                }
                sb.replace(indexOf, indexOf + 3, "}</pre>");
            } else if (charAt == '`') {
                sb.replace(indexOf, i2, "{@code ");
                indexOf = sb.indexOf("`", indexOf);
                if (indexOf < 0) {
                    break;
                }
                sb.replace(indexOf, indexOf + 1, "}");
            } else if ((charAt == '\\' || charAt == '@') && substring.startsWith("code")) {
                int i4 = indexOf + 5;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("<pre>{@code");
                if (Character.isWhitespace(sb.charAt(i4))) {
                    str2 = str3;
                }
                sb3.append(str2);
                sb.replace(indexOf, i4, sb3.toString());
                indexOf = sb.indexOf(charAt + "endcode", indexOf);
                if (indexOf < 0) {
                    break;
                }
                sb.replace(indexOf, indexOf + 8, "}</pre>");
            } else if ((charAt == '\\' || charAt == '@') && substring.startsWith("verbatim")) {
                int i5 = indexOf + 9;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("<pre>{@literal");
                if (Character.isWhitespace(sb.charAt(i5))) {
                    str2 = str3;
                }
                sb4.append(str2);
                sb.replace(indexOf, i5, sb4.toString());
                indexOf = sb.indexOf(charAt + "endverbatim", indexOf);
                if (indexOf < 0) {
                    break;
                }
                sb.replace(indexOf, indexOf + 12, "}</pre>");
            } else {
                int i6 = 0;
                if (charAt != 10 || substring.length() <= 0 || substring.charAt(0) != 10) {
                    if (charAt != '\\' && charAt != '@') {
                        if (charAt == '*' && substring.charAt(0) == '/' && (indexOf = sb.indexOf("/**", indexOf)) < 0) {
                            break;
                        }
                    } else {
                        DocTag[] docTagArr = DocTag.docTags;
                        int length = docTagArr.length;
                        int i7 = 0;
                        while (true) {
                            if (i7 >= length) {
                                break;
                            }
                            DocTag docTag = docTagArr[i7];
                            Matcher matcher = docTag.pattern.matcher(substring);
                            if (matcher.lookingAt()) {
                                StringBuffer stringBuffer = new StringBuffer();
                                matcher.appendReplacement(stringBuffer, docTag.replacement);
                                if (stringBuffer.charAt(0) == '@' && !Character.isWhitespace(sb.charAt(matcher.end() + indexOf + 1))) {
                                    stringBuffer.append(' ');
                                }
                                sb.replace(matcher.start() + indexOf, i2 + matcher.end(), stringBuffer.toString());
                                indexOf += stringBuffer.length() - 1;
                                i6 = 1;
                            } else {
                                i7++;
                            }
                        }
                        if (i6 == 0) {
                            sb.setCharAt(indexOf, '\\');
                        }
                    }
                } else {
                    while (i6 < substring.length() && substring.charAt(i6) == 10) {
                        i6++;
                    }
                    while (i6 < substring.length() && Character.isWhitespace(substring.charAt(i6))) {
                        str3 = str3 + substring.charAt(i6);
                        i6++;
                    }
                    sb.insert(i2, str3 + "<p>");
                }
            }
            indexOf++;
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public String commentBefore() throws ParserException {
        int i;
        String str;
        int i2 = 1;
        this.tokens.raw = true;
        while (true) {
            i = -1;
            if (this.tokens.index <= 0) {
                break;
            }
            if (!this.tokens.get(-1).match(4)) {
                break;
            }
            this.tokens.index--;
        }
        Token token = this.tokens.get();
        String str2 = "";
        boolean z = false;
        while (true) {
            Object[] objArr = new Object[i2];
            objArr[0] = 4;
            if (!token.match(objArr)) {
                break;
            }
            String str3 = token.value;
            if (str3.startsWith("/**") || str3.startsWith("/*!") || str3.startsWith("///") || str3.startsWith("//!")) {
                if (str3.startsWith("//") && str3.contains("*/") && str3.indexOf("*/") < str3.length() - 2) {
                    str3 = str3.replace("*/", "* /");
                }
                if (str3.length() > 3 && str3.charAt(3) == '<') {
                    token = this.tokens.next();
                    i2 = 1;
                } else if (str3.length() >= 3 && ((str3.startsWith("///") || str3.startsWith("//!")) && !str3.startsWith("////") && !str3.startsWith("///*"))) {
                    String trim = str2.trim();
                    int indexOf = trim.indexOf(10);
                    while (!trim.startsWith("/*") && indexOf > 0) {
                        int i3 = indexOf + 1;
                        if (i3 < trim.length()) {
                            trim = trim.substring(i3).trim();
                        } else {
                            trim = "";
                        }
                        indexOf = trim.indexOf(10);
                    }
                    StringBuilder sb = new StringBuilder();
                    if (str2.length() == 0 || str2.contains("*/") || !trim.startsWith("/*")) {
                        str = "/**";
                    } else {
                        str = " * ";
                    }
                    sb.append(str);
                    sb.append(str3.substring(3));
                    str3 = sb.toString();
                    z = true;
                } else if (str3.length() > 3 && !str3.startsWith("///")) {
                    str3 = "/**" + str3.substring(3);
                }
            } else if (z && !str2.endsWith("*/")) {
                str2 = str2 + " */";
                z = false;
            }
            if (i < 0 && str3.startsWith("/**")) {
                i = str2.length();
            }
            str2 = str2 + token.spacing + str3;
            token = this.tokens.next();
            i2 = 1;
        }
        if (z && !str2.endsWith("*/")) {
            str2 = str2 + " */";
        }
        this.tokens.raw = false;
        return commentDoc(str2, i);
    }

    /* access modifiers changed from: package-private */
    public String commentAfter() throws ParserException {
        int i;
        char c;
        String str;
        int i2 = 1;
        this.tokens.raw = true;
        while (true) {
            i = -1;
            c = 0;
            if (this.tokens.index <= 0) {
                break;
            }
            if (!this.tokens.get(-1).match(4)) {
                break;
            }
            this.tokens.index--;
        }
        Token token = this.tokens.get();
        String str2 = "";
        boolean z = false;
        while (true) {
            Object[] objArr = new Object[i2];
            objArr[c] = 4;
            if (!token.match(objArr)) {
                break;
            }
            String str3 = token.value;
            String str4 = token.spacing;
            int lastIndexOf = str4.lastIndexOf(10) + i2;
            if ((str3.startsWith("/**") || str3.startsWith("/*!") || str3.startsWith("///") || str3.startsWith("//!")) && (str3.length() <= 3 || str3.charAt(3) == '<')) {
                if (str3.length() > 4 && (str3.startsWith("///") || str3.startsWith("//!"))) {
                    String trim = str2.trim();
                    int indexOf = trim.indexOf(10);
                    while (!trim.startsWith("/*") && indexOf > 0) {
                        int i3 = indexOf + 1;
                        if (i3 < trim.length()) {
                            trim = trim.substring(i3).trim();
                        } else {
                            trim = "";
                        }
                        indexOf = trim.indexOf(10);
                    }
                    StringBuilder sb = new StringBuilder();
                    if (str2.length() == 0 || str2.contains("*/") || !trim.startsWith("/*")) {
                        str = "/**";
                    } else {
                        str = " * ";
                    }
                    sb.append(str);
                    sb.append(str3.substring(4));
                    str3 = sb.toString();
                    z = true;
                } else if (str3.length() > 4) {
                    str3 = "/**" + str3.substring(4);
                }
                if (i < 0 && str3.startsWith("/**")) {
                    i = str2.length();
                }
                str2 = str2 + str4.substring(0, lastIndexOf) + str3;
            }
            token = this.tokens.next();
            i2 = 1;
            c = 0;
        }
        if (z && !str2.endsWith("*/")) {
            str2 = str2 + " */";
        }
        if (str2.length() > 0) {
            str2 = str2 + "\n";
        }
        this.tokens.raw = false;
        return commentDoc(str2, i);
    }

    /* access modifiers changed from: package-private */
    public Attribute attribute() throws ParserException {
        return attribute(false);
    }

    /* access modifiers changed from: package-private */
    public Attribute attribute(boolean z) throws ParserException {
        boolean z2;
        if (this.tokens.get().match("[[")) {
            this.tokens.next();
            z2 = true;
        } else {
            if (this.tokens.get().match(5)) {
                if (!this.tokens.get(1).match(Character.valueOf(Typography.less))) {
                    z2 = false;
                }
            }
            return null;
        }
        Attribute attribute = new Attribute();
        InfoMap infoMap2 = this.infoMap;
        String str = this.tokens.get().value;
        attribute.cppName = str;
        Info first = infoMap2.getFirst(str);
        boolean z3 = first != null && first.annotations != null && first.javaNames == null && first.valueTypes == null && first.pointerTypes == null;
        attribute.annotation = z3;
        if (z3) {
            for (String str2 : first.annotations) {
                attribute.javaName += str2 + " ";
            }
        }
        if (!z2 && z && !attribute.annotation) {
            return null;
        }
        int match = this.tokens.next().match('(');
        if (!z2 && match <= 0) {
            return attribute;
        }
        this.tokens.raw = true;
        while (true) {
            Token next = this.tokens.next();
            if (next.match(Token.EOF) || (!z2 && match <= 0)) {
                this.tokens.raw = false;
            } else {
                if (next.match('(')) {
                    match++;
                } else {
                    if (next.match(')')) {
                        match--;
                    } else {
                        if (next.match("]]")) {
                            z2 = false;
                        } else if (first == null || !first.skip) {
                            attribute.arguments += next.value;
                        }
                    }
                }
            }
        }
        this.tokens.raw = false;
        return attribute;
    }

    /* access modifiers changed from: package-private */
    public String body() throws ParserException {
        if (!this.tokens.get().match('{')) {
            return null;
        }
        this.tokens.raw = true;
        Token next = this.tokens.next();
        String str = "";
        int i = 1;
        while (true) {
            if (next.match(Token.EOF) || i <= 0) {
                this.tokens.raw = false;
            } else {
                if (next.match('{')) {
                    i++;
                } else {
                    if (next.match('}')) {
                        i--;
                    }
                }
                if (i > 0) {
                    str = str + next.spacing + next;
                }
                next = this.tokens.next();
            }
        }
        this.tokens.raw = false;
        return str;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x023c, code lost:
        r2 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bytedeco.javacpp.tools.Parameters parameters(org.bytedeco.javacpp.tools.Context r37, int r38, boolean r39) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r36 = this;
            r8 = r36
            r9 = r37
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            int r10 = r0.index
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r11 = 1
            java.lang.Object[] r1 = new java.lang.Object[r11]
            r2 = 40
            java.lang.Character r12 = java.lang.Character.valueOf(r2)
            r13 = 0
            r1[r13] = r12
            boolean r0 = r0.match(r1)
            r14 = 0
            if (r0 != 0) goto L_0x0022
            return r14
        L_0x0022:
            org.bytedeco.javacpp.tools.Parameters r15 = new org.bytedeco.javacpp.tools.Parameters
            r15.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.lang.String r6 = "("
            r15.list = r6
            r15.names = r6
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            r1 = -1
            r1 = 0
            r5 = -1
        L_0x003b:
            java.lang.Object[] r2 = new java.lang.Object[r11]
            org.bytedeco.javacpp.tools.Token r3 = org.bytedeco.javacpp.tools.Token.EOF
            r2[r13] = r3
            boolean r2 = r0.match(r2)
            if (r2 != 0) goto L_0x04d4
            java.lang.Object[] r2 = new java.lang.Object[r11]
            java.lang.String r4 = "..."
            r2[r13] = r4
            boolean r2 = r0.match(r2)
            if (r2 == 0) goto L_0x0059
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
        L_0x0059:
            java.lang.String r3 = r0.spacing
            java.lang.Object[] r2 = new java.lang.Object[r11]
            r16 = 41
            java.lang.Character r17 = java.lang.Character.valueOf(r16)
            r2[r13] = r17
            boolean r0 = r0.match(r2)
            java.lang.String r2 = ")"
            if (r0 == 0) goto L_0x009d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r15.list
            r0.append(r1)
            r0.append(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r15.list = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r15.names
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r15.names = r0
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            goto L_0x04d4
        L_0x009d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r14 = "arg"
            r0.append(r14)
            int r11 = r1 + 1
            r0.append(r1)
            java.lang.String r19 = r0.toString()
            r20 = 0
            r21 = 1
            r22 = 0
            r0 = r36
            r1 = r37
            r23 = r2
            r2 = r19
            r24 = r3
            r3 = r38
            r25 = r4
            r4 = r39
            r26 = r5
            r5 = r20
            r27 = r6
            r6 = r21
            r28 = r7
            r7 = r22
            org.bytedeco.javacpp.tools.Declarator r0 = r0.declarator(r1, r2, r3, r4, r5, r6, r7)
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 44
            java.lang.Character r5 = java.lang.Character.valueOf(r4)
            r3[r13] = r5
            java.lang.Character r5 = java.lang.Character.valueOf(r16)
            r6 = 1
            r3[r6] = r5
            boolean r1 = r1.match(r3)
            r1 = r1 ^ r6
            r3 = 3
            java.lang.String r5 = ""
            if (r0 == 0) goto L_0x031b
            if (r1 == 0) goto L_0x031b
            org.bytedeco.javacpp.tools.TokenIndexer r7 = r8.tokens
            org.bytedeco.javacpp.tools.Token r7 = r7.get()
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            r2.spacing = r5
            r29 = r5
            r20 = 0
        L_0x010c:
            java.lang.Object[] r4 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.EOF
            r4[r13] = r18
            boolean r4 = r2.match(r4)
            if (r4 != 0) goto L_0x0252
            if (r20 != 0) goto L_0x013b
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r18 = 44
            java.lang.Character r30 = java.lang.Character.valueOf(r18)
            r4[r13] = r30
            java.lang.Character r18 = java.lang.Character.valueOf(r16)
            r4[r6] = r18
            r6 = 125(0x7d, float:1.75E-43)
            java.lang.Character r30 = java.lang.Character.valueOf(r6)
            r6 = 2
            r4[r6] = r30
            boolean r4 = r2.match(r4)
            if (r4 == 0) goto L_0x013c
            goto L_0x0252
        L_0x013b:
            r6 = 2
        L_0x013c:
            java.lang.Object[] r4 = new java.lang.Object[r6]
            r4[r13] = r12
            r19 = 123(0x7b, float:1.72E-43)
            java.lang.Character r19 = java.lang.Character.valueOf(r19)
            r18 = 1
            r4[r18] = r19
            boolean r4 = r2.match(r4)
            if (r4 == 0) goto L_0x0153
            int r20 = r20 + 1
            goto L_0x016b
        L_0x0153:
            java.lang.Object[] r4 = new java.lang.Object[r6]
            java.lang.Character r6 = java.lang.Character.valueOf(r16)
            r4[r13] = r6
            r6 = 125(0x7d, float:1.75E-43)
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            r4[r18] = r6
            boolean r4 = r2.match(r4)
            if (r4 == 0) goto L_0x016b
            int r20 = r20 + -1
        L_0x016b:
            java.lang.String r4 = r2.value
            org.bytedeco.javacpp.tools.TemplateMap r6 = r9.templateMap
            java.lang.String r3 = "::"
            if (r6 == 0) goto L_0x01b3
            java.lang.String[] r4 = r4.split(r3)
            int r6 = r4.length
            r13 = r5
            r31 = r13
            r32 = r7
            r7 = r31
            r5 = 0
        L_0x0180:
            if (r5 >= r6) goto L_0x01b1
            r33 = r6
            r6 = r4[r5]
            r34 = r4
            org.bytedeco.javacpp.tools.TemplateMap r4 = r9.templateMap
            org.bytedeco.javacpp.tools.Type r4 = r4.get(r6)
            r35 = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r13)
            r6.append(r7)
            if (r4 == 0) goto L_0x01a0
            java.lang.String r4 = r4.cppName
            goto L_0x01a2
        L_0x01a0:
            r4 = r35
        L_0x01a2:
            r6.append(r4)
            java.lang.String r13 = r6.toString()
            int r5 = r5 + 1
            r7 = r3
            r6 = r33
            r4 = r34
            goto L_0x0180
        L_0x01b1:
            r4 = r13
            goto L_0x01b7
        L_0x01b3:
            r31 = r5
            r32 = r7
        L_0x01b7:
            java.lang.String[] r5 = r9.qualify(r4)
            int r6 = r5.length
            r7 = 0
        L_0x01bd:
            if (r7 >= r6) goto L_0x01e1
            r13 = r5[r7]
            r33 = r4
            org.bytedeco.javacpp.tools.InfoMap r4 = r8.infoMap
            r34 = r5
            r5 = 0
            org.bytedeco.javacpp.tools.Info r4 = r4.getFirst(r13, r5)
            if (r4 == 0) goto L_0x01d0
            r4 = r13
            goto L_0x01e3
        L_0x01d0:
            org.bytedeco.javacpp.tools.InfoMap r4 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r4 = r4.getFirst(r13)
            if (r4 == 0) goto L_0x01da
            r4 = r13
            goto L_0x01dc
        L_0x01da:
            r4 = r33
        L_0x01dc:
            int r7 = r7 + 1
            r5 = r34
            goto L_0x01bd
        L_0x01e1:
            r33 = r4
        L_0x01e3:
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r7 = 5
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r13 = 0
            r6[r13] = r7
            boolean r6 = r2.match(r6)
            if (r6 == 0) goto L_0x0224
        L_0x01f4:
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r8.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.get(r5)
            boolean r5 = r6.equals(r3)
            if (r5 == 0) goto L_0x0224
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r8.tokens
            r5.next()
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r8.tokens
            org.bytedeco.javacpp.tools.Token r5 = r5.next()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r4)
            r6.append(r3)
            java.lang.String r4 = r5.spacing
            r6.append(r4)
            r6.append(r5)
            java.lang.String r4 = r6.toString()
            r5 = 1
            goto L_0x01f4
        L_0x0224:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r5 = r29
            r3.append(r5)
            java.lang.String r5 = r2.spacing
            r3.append(r5)
            if (r4 == 0) goto L_0x023c
            int r5 = r4.length()
            if (r5 <= 0) goto L_0x023c
            r2 = r4
        L_0x023c:
            r3.append(r2)
            java.lang.String r29 = r3.toString()
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            r5 = r31
            r7 = r32
            r3 = 3
            r6 = 1
            r13 = 0
            goto L_0x010c
        L_0x0252:
            r31 = r5
            r32 = r7
            r5 = r29
            java.lang.String[] r2 = r9.qualify(r5)
            int r3 = r2.length
            r29 = r5
            r4 = 0
        L_0x0260:
            if (r4 >= r3) goto L_0x027b
            r5 = r2[r4]
            org.bytedeco.javacpp.tools.InfoMap r6 = r8.infoMap
            r7 = 0
            org.bytedeco.javacpp.tools.Info r6 = r6.getFirst(r5, r7)
            if (r6 == 0) goto L_0x026e
            goto L_0x027d
        L_0x026e:
            org.bytedeco.javacpp.tools.InfoMap r6 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r6 = r6.getFirst(r5)
            if (r6 == 0) goto L_0x0278
            r29 = r5
        L_0x0278:
            int r4 = r4 + 1
            goto L_0x0260
        L_0x027b:
            r5 = r29
        L_0x027d:
            org.bytedeco.javacpp.tools.Type r2 = r0.type
            java.lang.String r2 = r2.annotations
            java.lang.String r3 = "@ByVal "
            int r3 = r2.indexOf(r3)
            if (r3 >= 0) goto L_0x028f
            java.lang.String r3 = "@ByRef "
            int r3 = r2.indexOf(r3)
        L_0x028f:
            if (r3 < 0) goto L_0x0312
            org.bytedeco.javacpp.tools.Type r4 = r0.type
            java.lang.String r4 = r4.cppName
            boolean r4 = r5.startsWith(r4)
            if (r4 != 0) goto L_0x02b9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            org.bytedeco.javacpp.tools.Type r6 = r0.type
            java.lang.String r6 = r6.cppName
            r4.append(r6)
            r6 = r27
            r4.append(r6)
            r4.append(r5)
            r5 = r23
            r4.append(r5)
            java.lang.String r5 = r4.toString()
            goto L_0x02bb
        L_0x02b9:
            r6 = r27
        L_0x02bb:
            org.bytedeco.javacpp.tools.InfoMap r4 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r4 = r4.getFirst(r5)
            if (r4 == 0) goto L_0x02d8
            boolean r4 = r4.skip
            if (r4 == 0) goto L_0x02d8
            if (r39 == 0) goto L_0x02d5
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.index = r10
            r4 = r38
            r0 = 0
            org.bytedeco.javacpp.tools.Parameters r0 = r8.parameters(r9, r4, r0)
            return r0
        L_0x02d5:
            r4 = r38
            goto L_0x0314
        L_0x02d8:
            r4 = r38
            java.lang.String r7 = "\""
            java.lang.String r13 = "\\\\\""
            java.lang.String r5 = r5.replaceAll(r7, r13)
            java.lang.String r7 = "\n(\\s*)"
            java.lang.String r13 = "\"\n$1 + \""
            java.lang.String r5 = r5.replaceAll(r7, r13)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            int r3 = r3 + 6
            r13 = 0
            java.lang.String r4 = r2.substring(r13, r3)
            r7.append(r4)
            java.lang.String r4 = "(nullValue = \""
            r7.append(r4)
            r7.append(r5)
            java.lang.String r4 = "\")"
            r7.append(r4)
            java.lang.String r2 = r2.substring(r3)
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            goto L_0x0314
        L_0x0312:
            r6 = r27
        L_0x0314:
            org.bytedeco.javacpp.tools.Type r3 = r0.type
            r3.annotations = r2
            r7 = r32
            goto L_0x0322
        L_0x031b:
            r31 = r5
            r6 = r27
            r5 = r31
            r7 = 0
        L_0x0322:
            if (r0 == 0) goto L_0x0487
            org.bytedeco.javacpp.tools.Type r2 = r0.type
            java.lang.String r2 = r2.javaName
            java.lang.String r3 = "void"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0487
            if (r1 == 0) goto L_0x0334
            if (r39 != 0) goto L_0x0487
        L_0x0334:
            r2 = r26
            if (r2 < 0) goto L_0x035d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r15.list
            r13 = 0
            java.lang.String r4 = r4.substring(r13, r2)
            r3.append(r4)
            java.lang.String r4 = "[]"
            r3.append(r4)
            java.lang.String r4 = r15.list
            int r2 = r2 + 3
            java.lang.String r2 = r4.substring(r2)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r15.list = r2
        L_0x035d:
            java.lang.String r2 = r15.list
            int r2 = r2.length()
            org.bytedeco.javacpp.tools.InfoMap r3 = r8.infoMap
            java.lang.String r4 = r0.javaName
            org.bytedeco.javacpp.tools.Info r3 = r3.getFirst(r4)
            if (r3 == 0) goto L_0x037c
            java.lang.String[] r4 = r3.javaNames
            if (r4 == 0) goto L_0x037c
            java.lang.String[] r4 = r3.javaNames
            int r4 = r4.length
            if (r4 <= 0) goto L_0x037c
            java.lang.String[] r3 = r3.javaNames
            r4 = 0
            r3 = r3[r4]
            goto L_0x037e
        L_0x037c:
            java.lang.String r3 = r0.javaName
        L_0x037e:
            int r4 = r15.infoNumber
            int r13 = r0.infoNumber
            int r4 = java.lang.Math.max(r4, r13)
            r15.infoNumber = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r13 = r15.list
            r4.append(r13)
            r13 = 1
            if (r11 <= r13) goto L_0x0398
            java.lang.String r13 = ","
            goto L_0x039a
        L_0x0398:
            r13 = r31
        L_0x039a:
            r4.append(r13)
            r13 = r24
            r4.append(r13)
            org.bytedeco.javacpp.tools.Type r13 = r0.type
            java.lang.String r13 = r13.annotations
            r4.append(r13)
            org.bytedeco.javacpp.tools.Type r13 = r0.type
            java.lang.String r13 = r13.javaName
            r4.append(r13)
            java.lang.String r13 = " "
            r4.append(r13)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            r15.list = r4
            java.lang.String r4 = r15.list
            r13 = r25
            int r2 = r4.indexOf(r13, r2)
            if (r1 == 0) goto L_0x03f4
            org.bytedeco.javacpp.tools.Type r4 = r0.type
            java.lang.String r4 = r4.annotations
            java.lang.String r13 = "(nullValue = "
            boolean r4 = r4.contains(r13)
            if (r4 != 0) goto L_0x03f4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r13 = r15.list
            r4.append(r13)
            java.lang.String r13 = "/*"
            r4.append(r13)
            r4.append(r7)
            r4.append(r5)
            java.lang.String r5 = "*/"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r15.list = r4
        L_0x03f4:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r15.signature
            r4.append(r5)
            r5 = 95
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r15.signature = r4
            org.bytedeco.javacpp.tools.Type r4 = r0.type
            java.lang.String r4 = r4.javaName
            org.bytedeco.javacpp.tools.Type r7 = r0.type
            java.lang.String r7 = r7.javaName
            r13 = 32
            int r7 = r7.lastIndexOf(r13)
            r13 = 1
            int r7 = r7 + r13
            java.lang.String r4 = r4.substring(r7)
            char[] r4 = r4.toCharArray()
            int r7 = r4.length
            r13 = 0
        L_0x0423:
            if (r13 >= r7) goto L_0x044e
            char r20 = r4[r13]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r24 = r2
            java.lang.String r2 = r15.signature
            r5.append(r2)
            boolean r2 = java.lang.Character.isJavaIdentifierPart(r20)
            if (r2 == 0) goto L_0x043c
            r2 = r20
            goto L_0x043e
        L_0x043c:
            r2 = 95
        L_0x043e:
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r15.signature = r2
            int r13 = r13 + 1
            r2 = r24
            r5 = 95
            goto L_0x0423
        L_0x044e:
            r24 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r15.names
            r2.append(r4)
            r4 = 1
            if (r11 <= r4) goto L_0x0460
            java.lang.String r5 = ", "
            goto L_0x0462
        L_0x0460:
            r5 = r31
        L_0x0462:
            r2.append(r5)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r15.names = r2
            java.lang.String r2 = r0.javaName
            boolean r2 = r2.startsWith(r14)
            if (r2 == 0) goto L_0x0484
            java.lang.String r2 = r0.javaName     // Catch:{ NumberFormatException -> 0x0484 }
            r3 = 3
            java.lang.String r2 = r2.substring(r3)     // Catch:{ NumberFormatException -> 0x0484 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0484 }
            r3 = 1
            int r2 = r2 + r3
            r11 = r2
        L_0x0484:
            r5 = r24
            goto L_0x048a
        L_0x0487:
            r2 = r26
            r5 = r2
        L_0x048a:
            if (r1 == 0) goto L_0x0492
            if (r39 != 0) goto L_0x048f
            goto L_0x0492
        L_0x048f:
            r1 = r28
            goto L_0x0497
        L_0x0492:
            r1 = r28
            r1.add(r0)
        L_0x0497:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 44
            java.lang.Character r4 = java.lang.Character.valueOf(r3)
            r7 = 0
            r2[r7] = r4
            java.lang.Character r4 = java.lang.Character.valueOf(r16)
            r13 = 1
            r2[r13] = r4
            org.bytedeco.javacpp.tools.Token r0 = r0.expect(r2)
            java.lang.Object[] r2 = new java.lang.Object[r13]
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r2[r7] = r3
            boolean r0 = r0.match(r2)
            if (r0 == 0) goto L_0x04c7
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
        L_0x04c7:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r7 = r1
            r1 = r11
            r11 = 1
            r13 = 0
            r14 = 0
            goto L_0x003b
        L_0x04d4:
            r1 = r7
            org.bytedeco.javacpp.tools.TemplateMap r0 = r9.templateMap
            if (r0 != 0) goto L_0x0513
            int r0 = r1.size()
            r2 = 1
            if (r0 != r2) goto L_0x0513
            r0 = 0
            java.lang.Object r2 = r1.get(r0)
            if (r2 == 0) goto L_0x050d
            java.lang.Object r2 = r1.get(r0)
            org.bytedeco.javacpp.tools.Declarator r2 = (org.bytedeco.javacpp.tools.Declarator) r2
            org.bytedeco.javacpp.tools.Type r2 = r2.type
            if (r2 == 0) goto L_0x050d
            java.lang.Object r2 = r1.get(r0)
            org.bytedeco.javacpp.tools.Declarator r2 = (org.bytedeco.javacpp.tools.Declarator) r2
            org.bytedeco.javacpp.tools.Type r2 = r2.type
            java.lang.String r2 = r2.cppName
            if (r2 == 0) goto L_0x050d
            java.lang.Object r0 = r1.get(r0)
            org.bytedeco.javacpp.tools.Declarator r0 = (org.bytedeco.javacpp.tools.Declarator) r0
            org.bytedeco.javacpp.tools.Type r0 = r0.type
            java.lang.String r0 = r0.cppName
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0513
        L_0x050d:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.index = r10
            r0 = 0
            return r0
        L_0x0513:
            int r0 = r1.size()
            org.bytedeco.javacpp.tools.Declarator[] r0 = new org.bytedeco.javacpp.tools.Declarator[r0]
            java.lang.Object[] r0 = r1.toArray(r0)
            org.bytedeco.javacpp.tools.Declarator[] r0 = (org.bytedeco.javacpp.tools.Declarator[]) r0
            r15.declarators = r0
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.parameters(org.bytedeco.javacpp.tools.Context, int, boolean):org.bytedeco.javacpp.tools.Parameters");
    }

    static String incorporateConstAnnotation(String str, int i, boolean z) {
        int indexOf = str.indexOf("@Const");
        int indexOf2 = str.indexOf("@", indexOf + 1);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf, indexOf2);
        String str2 = " " + str.substring(indexOf2, str.length());
        Matcher matcher = Pattern.compile("(true|false)").matcher(substring2);
        boolean[] zArr = {true, false, false};
        int i2 = 0;
        while (matcher.find()) {
            zArr[i2] = Boolean.parseBoolean(matcher.group(1));
            i2++;
        }
        zArr[i] = z;
        return substring + ("@Const({" + zArr[0] + ", " + zArr[1] + ", " + zArr[2] + "})") + str2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x04a8  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x04bd  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x04e8  */
    /* JADX WARNING: Removed duplicated region for block: B:399:0x0b3a A[LOOP:13: B:397:0x0b12->B:399:0x0b3a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0b72  */
    /* JADX WARNING: Removed duplicated region for block: B:427:0x0c07  */
    /* JADX WARNING: Removed duplicated region for block: B:430:0x0c24  */
    /* JADX WARNING: Removed duplicated region for block: B:431:0x0c2b A[LOOP:16: B:431:0x0c2b->B:433:0x0c45, LOOP_START, PHI: r5 
      PHI: (r5v10 char) = (r5v9 char), (r5v12 char) binds: [B:429:0x0c22, B:433:0x0c45] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:452:0x04e1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:485:0x0b41 A[EDGE_INSN: B:485:0x0b41->B:400:0x0b41 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean function(org.bytedeco.javacpp.tools.Context r42, org.bytedeco.javacpp.tools.DeclarationList r43) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r41 = this;
            r8 = r41
            r9 = r42
            r10 = r43
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            int r11 = r0.index
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.String r12 = r0.spacing
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            int r13 = r0.index
            org.bytedeco.javacpp.tools.Type r0 = r41.type(r42)
            r14 = 0
            org.bytedeco.javacpp.tools.Parameters r15 = r8.parameters(r9, r14, r14)
            org.bytedeco.javacpp.tools.Declarator r1 = new org.bytedeco.javacpp.tools.Declarator
            r1.<init>()
            org.bytedeco.javacpp.tools.Declaration r7 = new org.bytedeco.javacpp.tools.Declaration
            r7.<init>()
            java.lang.String r2 = r0.javaName
            int r2 = r2.length()
            if (r2 != 0) goto L_0x0036
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.index = r11
            return r14
        L_0x0036:
            java.lang.String r2 = r9.javaName
            r16 = 5
            r17 = 41
            r18 = 40
            java.lang.String r19 = "->"
            r6 = 62
            r5 = 3
            r20 = 58
            r21 = 59
            r22 = 123(0x7b, float:1.72E-43)
            r4 = 2
            r3 = 1
            if (r2 != 0) goto L_0x017c
            boolean r2 = r0.operator
            if (r2 != 0) goto L_0x017c
            if (r15 == 0) goto L_0x017c
        L_0x0053:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Character r2 = java.lang.Character.valueOf(r20)
            r1[r14] = r2
            java.lang.Character r2 = java.lang.Character.valueOf(r22)
            r1[r3] = r2
            java.lang.Character r2 = java.lang.Character.valueOf(r21)
            r1[r4] = r2
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.EOF
            r1[r5] = r2
            boolean r0 = r0.match(r1)
            if (r0 != 0) goto L_0x007e
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            goto L_0x0053
        L_0x007e:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.Object[] r1 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.EOF
            r1[r14] = r2
            boolean r0 = r0.match(r1)
            if (r0 != 0) goto L_0x0096
            org.bytedeco.javacpp.tools.Attribute r0 = r41.attribute()
            if (r0 != 0) goto L_0x007e
        L_0x0096:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.Character r2 = java.lang.Character.valueOf(r20)
            r1[r14] = r2
            boolean r0 = r0.match(r1)
            if (r0 == 0) goto L_0x0126
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            r1 = 0
        L_0x00b1:
            java.lang.Object[] r2 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.EOF
            r2[r14] = r5
            boolean r2 = r0.match(r2)
            if (r2 != 0) goto L_0x0126
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.Character r5 = java.lang.Character.valueOf(r18)
            r2[r14] = r5
            boolean r2 = r0.match(r2)
            if (r2 == 0) goto L_0x00ce
            int r1 = r1 + 1
            goto L_0x00de
        L_0x00ce:
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.Character r5 = java.lang.Character.valueOf(r17)
            r2[r14] = r5
            boolean r2 = r0.match(r2)
            if (r2 == 0) goto L_0x00de
            int r1 = r1 + -1
        L_0x00de:
            if (r1 != 0) goto L_0x010e
            java.lang.Object[] r2 = new java.lang.Object[r4]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r16)
            r2[r14] = r5
            java.lang.Character r5 = java.lang.Character.valueOf(r6)
            r2[r3] = r5
            boolean r2 = r0.match(r2)
            if (r2 != 0) goto L_0x010e
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get(r3)
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.Character r11 = java.lang.Character.valueOf(r22)
            r5[r14] = r11
            boolean r2 = r2.match(r5)
            if (r2 == 0) goto L_0x010e
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            goto L_0x0126
        L_0x010e:
            if (r1 != 0) goto L_0x011f
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.Character r5 = java.lang.Character.valueOf(r21)
            r2[r14] = r5
            boolean r0 = r0.match(r2)
            if (r0 == 0) goto L_0x011f
            goto L_0x0126
        L_0x011f:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            goto L_0x00b1
        L_0x0126:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.Object[] r1 = new java.lang.Object[r3]
            r1[r14] = r19
            boolean r0 = r0.match(r1)
            if (r0 == 0) goto L_0x013e
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            r41.type(r42)
        L_0x013e:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.Character r2 = java.lang.Character.valueOf(r22)
            r1[r14] = r2
            boolean r0 = r0.match(r1)
            if (r0 == 0) goto L_0x0156
            r41.body()
            goto L_0x0174
        L_0x0156:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.Object[] r1 = new java.lang.Object[r4]
            java.lang.Character r2 = java.lang.Character.valueOf(r21)
            r1[r14] = r2
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.EOF
            r1[r3] = r2
            boolean r0 = r0.match(r1)
            if (r0 != 0) goto L_0x0174
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            goto L_0x0156
        L_0x0174:
            r7.text = r12
            r7.function = r3
            r10.add((org.bytedeco.javacpp.tools.Declaration) r7)
            return r3
        L_0x017c:
            boolean r2 = r0.constructor
            r23 = r7
            r7 = 32
            java.lang.String r6 = "&"
            r25 = r6
            java.lang.String r6 = "*"
            r26 = r6
            java.lang.String r6 = "const "
            r27 = r6
            java.lang.String r6 = ""
            if (r2 != 0) goto L_0x019a
            boolean r2 = r0.destructor
            if (r2 != 0) goto L_0x019a
            boolean r2 = r0.operator
            if (r2 == 0) goto L_0x023d
        L_0x019a:
            if (r15 == 0) goto L_0x023d
            r1.type = r0
            r1.parameters = r15
            java.lang.String r2 = r0.cppName
            r1.cppName = r2
            java.lang.String r2 = r0.javaName
            java.lang.String r4 = r0.javaName
            int r4 = r4.lastIndexOf(r7)
            int r4 = r4 + r3
            java.lang.String r2 = r2.substring(r4)
            r1.javaName = r2
            boolean r2 = r0.operator
            if (r2 == 0) goto L_0x0217
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "operator "
            r2.append(r4)
            org.bytedeco.javacpp.tools.Type r4 = r1.type
            boolean r4 = r4.constValue
            if (r4 == 0) goto L_0x01ca
            r4 = r27
            goto L_0x01cb
        L_0x01ca:
            r4 = r6
        L_0x01cb:
            r2.append(r4)
            org.bytedeco.javacpp.tools.Type r4 = r1.type
            java.lang.String r4 = r4.cppName
            r2.append(r4)
            org.bytedeco.javacpp.tools.Type r4 = r1.type
            int r4 = r4.indirections
            if (r4 <= 0) goto L_0x01de
            r4 = r26
            goto L_0x01e8
        L_0x01de:
            org.bytedeco.javacpp.tools.Type r4 = r1.type
            boolean r4 = r4.reference
            if (r4 == 0) goto L_0x01e7
            r4 = r25
            goto L_0x01e8
        L_0x01e7:
            r4 = r6
        L_0x01e8:
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.cppName = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "as"
            r2.append(r4)
            java.lang.String r4 = r1.javaName
            char r4 = r4.charAt(r14)
            char r4 = java.lang.Character.toUpperCase(r4)
            r2.append(r4)
            java.lang.String r4 = r1.javaName
            java.lang.String r4 = r4.substring(r3)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.javaName = r2
        L_0x0217:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r1.javaName
            r2.append(r4)
            java.lang.String r4 = r15.signature
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.signature = r2
            r35 = r6
            r24 = r15
            r15 = r23
            r14 = r25
            r37 = r26
            r34 = r27
            r23 = r11
            r11 = 32
            goto L_0x026e
        L_0x023d:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.index = r13
            r2 = 0
            r4 = -1
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r0 = r41
            r1 = r42
            r3 = r4
            r4 = r29
            r5 = r30
            r35 = r6
            r14 = r25
            r37 = r26
            r34 = r27
            r6 = r31
            r24 = r15
            r15 = r23
            r23 = r11
            r11 = 32
            r7 = r32
            org.bytedeco.javacpp.tools.Declarator r1 = r0.declarator(r1, r2, r3, r4, r5, r6, r7)
            org.bytedeco.javacpp.tools.Type r0 = r1.type
        L_0x026e:
            java.lang.String r2 = r1.cppName
            if (r2 == 0) goto L_0x0c56
            java.lang.String r2 = r0.javaName
            int r2 = r2.length()
            if (r2 == 0) goto L_0x0c56
            org.bytedeco.javacpp.tools.Parameters r2 = r1.parameters
            if (r2 != 0) goto L_0x0280
            goto L_0x0c56
        L_0x0280:
            java.lang.String r2 = r1.cppName
            java.lang.String r7 = "::"
            int r2 = r2.lastIndexOf(r7)
            java.lang.String r3 = r9.namespace
            if (r3 == 0) goto L_0x02a6
            if (r2 >= 0) goto L_0x02a6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r9.namespace
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = r1.cppName
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.cppName = r2
        L_0x02a6:
            java.lang.String r2 = r1.cppName
            java.lang.String r3 = r1.cppName
            org.bytedeco.javacpp.tools.Parameters r4 = r1.parameters
            if (r4 == 0) goto L_0x0409
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.String r2 = "("
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = "("
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            org.bytedeco.javacpp.tools.Parameters r4 = r1.parameters
            org.bytedeco.javacpp.tools.Declarator[] r4 = r4.declarators
            int r5 = r4.length
            r6 = r35
            r11 = 0
        L_0x02d8:
            if (r11 >= r5) goto L_0x03cb
            r26 = r5
            r5 = r4[r11]
            r27 = r4
            if (r5 == 0) goto L_0x03b5
            org.bytedeco.javacpp.tools.Type r4 = r5.type
            java.lang.String r4 = r4.cppName
            r29 = r15
            org.bytedeco.javacpp.tools.Type r15 = r5.type
            java.lang.String r15 = r15.cppName
            r30 = r15
            org.bytedeco.javacpp.tools.Type r15 = r5.type
            boolean r15 = r15.constValue
            if (r15 == 0) goto L_0x030c
            r15 = r34
            boolean r31 = r4.startsWith(r15)
            if (r31 != 0) goto L_0x030e
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r15)
            r10.append(r4)
            java.lang.String r4 = r10.toString()
            goto L_0x030e
        L_0x030c:
            r15 = r34
        L_0x030e:
            int r10 = r5.indirections
            r31 = r12
            r34 = r15
            if (r10 <= 0) goto L_0x0348
            r10 = r30
            r15 = 0
        L_0x0319:
            int r12 = r5.indirections
            if (r15 >= r12) goto L_0x0344
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r4)
            r4 = r37
            r12.append(r4)
            java.lang.String r12 = r12.toString()
            r30 = r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r10)
            r12.append(r4)
            java.lang.String r10 = r12.toString()
            int r15 = r15 + 1
            r4 = r30
            goto L_0x0319
        L_0x0344:
            r12 = r37
            r15 = r10
            goto L_0x034c
        L_0x0348:
            r12 = r37
            r15 = r30
        L_0x034c:
            boolean r10 = r5.reference
            if (r10 == 0) goto L_0x036e
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r4)
            r10.append(r14)
            java.lang.String r4 = r10.toString()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r15)
            r10.append(r14)
            java.lang.String r15 = r10.toString()
        L_0x036e:
            org.bytedeco.javacpp.tools.Type r5 = r5.type
            boolean r5 = r5.constPointer
            if (r5 == 0) goto L_0x038d
            java.lang.String r5 = " const"
            boolean r5 = r4.endsWith(r5)
            if (r5 != 0) goto L_0x038d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            java.lang.String r4 = " const"
            r5.append(r4)
            java.lang.String r4 = r5.toString()
        L_0x038d:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            r5.append(r6)
            r5.append(r4)
            java.lang.String r2 = r5.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            r4.append(r6)
            r4.append(r15)
            java.lang.String r3 = r4.toString()
            java.lang.String r4 = ", "
            r6 = r4
            goto L_0x03bb
        L_0x03b5:
            r31 = r12
            r29 = r15
            r12 = r37
        L_0x03bb:
            int r11 = r11 + 1
            r10 = r43
            r37 = r12
            r5 = r26
            r4 = r27
            r15 = r29
            r12 = r31
            goto L_0x02d8
        L_0x03cb:
            r31 = r12
            r29 = r15
            r12 = r37
            org.bytedeco.javacpp.tools.InfoMap r4 = r8.infoMap
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            java.lang.String r2 = ")"
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            org.bytedeco.javacpp.tools.Info r4 = r4.getFirst(r2)
            if (r4 != 0) goto L_0x0407
            org.bytedeco.javacpp.tools.InfoMap r5 = r8.infoMap
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            java.lang.String r3 = ")"
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            org.bytedeco.javacpp.tools.Info r3 = r5.getFirst(r3)
            r40 = r4
            r4 = r3
            r3 = r40
            goto L_0x0411
        L_0x0407:
            r3 = r4
            goto L_0x0411
        L_0x0409:
            r31 = r12
            r29 = r15
            r12 = r37
            r3 = 0
            r4 = 0
        L_0x0411:
            if (r4 != 0) goto L_0x048b
            boolean r5 = r0.constructor
            if (r5 == 0) goto L_0x044e
            java.lang.String r3 = r1.cppName
            r4 = 60
            int r4 = r3.lastIndexOf(r4)
            if (r4 < 0) goto L_0x0426
            r5 = 0
            java.lang.String r3 = r3.substring(r5, r4)
        L_0x0426:
            int r4 = r3.lastIndexOf(r7)
            r10 = 2
            if (r4 < 0) goto L_0x0432
            int r4 = r4 + r10
            java.lang.String r3 = r3.substring(r4)
        L_0x0432:
            org.bytedeco.javacpp.tools.InfoMap r4 = r8.infoMap
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r1.cppName
            r5.append(r6)
            r5.append(r7)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            org.bytedeco.javacpp.tools.Info r4 = r4.getFirst(r3)
            r3 = r4
            goto L_0x044f
        L_0x044e:
            r10 = 2
        L_0x044f:
            if (r4 != 0) goto L_0x0459
            org.bytedeco.javacpp.tools.InfoMap r4 = r8.infoMap
            java.lang.String r5 = r1.cppName
            org.bytedeco.javacpp.tools.Info r4 = r4.getFirst(r5)
        L_0x0459:
            boolean r5 = r0.constructor
            if (r5 != 0) goto L_0x048c
            boolean r5 = r0.destructor
            if (r5 != 0) goto L_0x048c
            boolean r5 = r0.operator
            if (r5 != 0) goto L_0x048c
            org.bytedeco.javacpp.tools.InfoMap r5 = r8.infoMap
            if (r4 == 0) goto L_0x047a
            org.bytedeco.javacpp.tools.Info r6 = new org.bytedeco.javacpp.tools.Info
            r6.<init>((org.bytedeco.javacpp.tools.Info) r4)
            r11 = 1
            java.lang.String[] r15 = new java.lang.String[r11]
            r26 = 0
            r15[r26] = r2
            org.bytedeco.javacpp.tools.Info r2 = r6.cppNames(r15)
            goto L_0x0487
        L_0x047a:
            r11 = 1
            r26 = 0
            org.bytedeco.javacpp.tools.Info r6 = new org.bytedeco.javacpp.tools.Info
            java.lang.String[] r15 = new java.lang.String[r11]
            r15[r26] = r2
            r6.<init>((java.lang.String[]) r15)
            r2 = r6
        L_0x0487:
            r5.put(r2)
            goto L_0x048d
        L_0x048b:
            r10 = 2
        L_0x048c:
            r11 = 1
        L_0x048d:
            r15 = r3
            r6 = r4
            java.lang.String r2 = r1.cppName
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r9.namespace
            r3.append(r4)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            boolean r3 = r2.startsWith(r3)
            if (r3 == 0) goto L_0x04b5
            java.lang.String r1 = r1.cppName
            java.lang.String r2 = r9.namespace
            int r2 = r2.length()
            int r2 = r2 + r10
            java.lang.String r2 = r1.substring(r2)
        L_0x04b5:
            r1 = 0
            r3 = 0
        L_0x04b7:
            int r4 = r2.length()
            if (r1 >= r4) goto L_0x04e1
            char r4 = r2.charAt(r1)
            r5 = 60
            if (r4 != r5) goto L_0x04ca
            int r3 = r3 + 1
            r5 = 62
            goto L_0x04de
        L_0x04ca:
            r5 = 62
            if (r4 != r5) goto L_0x04d1
            int r3 = r3 + -1
            goto L_0x04de
        L_0x04d1:
            if (r3 != 0) goto L_0x04de
            java.lang.String r4 = r2.substring(r1)
            boolean r4 = r4.startsWith(r7)
            if (r4 == 0) goto L_0x04de
            goto L_0x04e4
        L_0x04de:
            int r1 = r1 + 1
            goto L_0x04b7
        L_0x04e1:
            r5 = 62
            r1 = 0
        L_0x04e4:
            boolean r2 = r0.friend
            if (r2 != 0) goto L_0x0b0b
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.Object[] r3 = new java.lang.Object[r11]
            java.lang.String r4 = "&&"
            r26 = 0
            r3[r26] = r4
            boolean r2 = r2.match(r3)
            if (r2 != 0) goto L_0x0b0b
            java.lang.String r2 = r9.javaName
            if (r2 != 0) goto L_0x0502
            if (r1 > 0) goto L_0x0b0b
        L_0x0502:
            if (r6 == 0) goto L_0x050a
            boolean r1 = r6.skip
            if (r1 == 0) goto L_0x050a
            goto L_0x0b0b
        L_0x050a:
            boolean r1 = r0.staticMember
            java.lang.String r26 = "public native "
            if (r1 != 0) goto L_0x0518
            java.lang.String r1 = r9.javaName
            if (r1 != 0) goto L_0x0515
            goto L_0x0518
        L_0x0515:
            r1 = r26
            goto L_0x0554
        L_0x0518:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "public "
            r1.append(r2)
            if (r6 == 0) goto L_0x0528
            boolean r2 = r6.objectify
            if (r2 != 0) goto L_0x052c
        L_0x0528:
            boolean r2 = r9.objectify
            if (r2 == 0) goto L_0x052f
        L_0x052c:
            r2 = r35
            goto L_0x0531
        L_0x052f:
            java.lang.String r2 = "static "
        L_0x0531:
            r1.append(r2)
            java.lang.String r2 = "native "
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            boolean r2 = r2.isCFile
            if (r2 == 0) goto L_0x0554
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "@NoException "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x0554:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r2 = -2
            r29 = r24
            r3 = -2
            r27 = 1
            r24 = r1
        L_0x0561:
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r3 >= r1) goto L_0x0b04
            org.bytedeco.javacpp.tools.Declaration r2 = new org.bytedeco.javacpp.tools.Declaration
            r2.<init>()
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            r1.index = r13
            if (r6 == 0) goto L_0x0575
            boolean r1 = r6.skipDefaults
            if (r1 != 0) goto L_0x057b
        L_0x0575:
            int r1 = r3 % 2
            if (r1 == 0) goto L_0x057b
            r1 = 1
            goto L_0x057c
        L_0x057b:
            r1 = 0
        L_0x057c:
            boolean r5 = r0.constructor
            if (r5 != 0) goto L_0x0588
            boolean r5 = r0.destructor
            if (r5 != 0) goto L_0x0588
            boolean r0 = r0.operator
            if (r0 == 0) goto L_0x0737
        L_0x0588:
            if (r29 == 0) goto L_0x0737
            org.bytedeco.javacpp.tools.Type r0 = r41.type(r42)
            int r5 = r3 / 2
            org.bytedeco.javacpp.tools.Parameters r5 = r8.parameters(r9, r5, r1)
            org.bytedeco.javacpp.tools.Declarator r10 = new org.bytedeco.javacpp.tools.Declarator
            r10.<init>()
            r10.type = r0
            r10.parameters = r5
            java.lang.String r11 = r0.cppName
            r10.cppName = r11
            java.lang.String r11 = r0.javaName
            r30 = r1
            java.lang.String r1 = r0.javaName
            r32 = r7
            r7 = 32
            int r1 = r1.lastIndexOf(r7)
            r25 = 1
            int r1 = r1 + 1
            java.lang.String r1 = r11.substring(r1)
            r10.javaName = r1
            boolean r1 = r0.operator
            if (r1 == 0) goto L_0x061f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r11 = "operator "
            r1.append(r11)
            org.bytedeco.javacpp.tools.Type r11 = r10.type
            boolean r11 = r11.constValue
            if (r11 == 0) goto L_0x05d0
            r11 = r34
            goto L_0x05d2
        L_0x05d0:
            r11 = r35
        L_0x05d2:
            r1.append(r11)
            org.bytedeco.javacpp.tools.Type r11 = r10.type
            java.lang.String r11 = r11.cppName
            r1.append(r11)
            org.bytedeco.javacpp.tools.Type r11 = r10.type
            int r11 = r11.indirections
            if (r11 <= 0) goto L_0x05e4
            r11 = r12
            goto L_0x05ee
        L_0x05e4:
            org.bytedeco.javacpp.tools.Type r11 = r10.type
            boolean r11 = r11.reference
            if (r11 == 0) goto L_0x05ec
            r11 = r14
            goto L_0x05ee
        L_0x05ec:
            r11 = r35
        L_0x05ee:
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            r10.cppName = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r11 = "as"
            r1.append(r11)
            java.lang.String r11 = r10.javaName
            r7 = 0
            char r11 = r11.charAt(r7)
            char r7 = java.lang.Character.toUpperCase(r11)
            r1.append(r7)
            java.lang.String r7 = r10.javaName
            r11 = 1
            java.lang.String r7 = r7.substring(r11)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r10.javaName = r1
        L_0x061f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r7 = r10.javaName
            r1.append(r7)
            java.lang.String r7 = r5.signature
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r10.signature = r1
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
        L_0x063a:
            r7 = 1
            java.lang.Object[] r11 = new java.lang.Object[r7]
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.EOF
            r28 = 0
            r11[r28] = r7
            boolean r1 = r1.match(r11)
            if (r1 != 0) goto L_0x067b
            org.bytedeco.javacpp.tools.Attribute r1 = r41.attribute()
            if (r1 == 0) goto L_0x066d
            boolean r7 = r1.annotation
            if (r7 == 0) goto L_0x066d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            org.bytedeco.javacpp.tools.Type r11 = r10.type
            r29 = r0
            java.lang.String r0 = r11.annotations
            r7.append(r0)
            java.lang.String r0 = r1.javaName
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r11.annotations = r0
            goto L_0x0672
        L_0x066d:
            r29 = r0
            if (r1 != 0) goto L_0x0672
            goto L_0x067d
        L_0x0672:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r0.get()
            r0 = r29
            goto L_0x063a
        L_0x067b:
            r29 = r0
        L_0x067d:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r1 = 1
            java.lang.Object[] r7 = new java.lang.Object[r1]
            java.lang.Character r11 = java.lang.Character.valueOf(r20)
            r28 = 0
            r7[r28] = r11
            boolean r0 = r0.match(r7)
            if (r0 == 0) goto L_0x0722
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            r7 = 0
        L_0x069b:
            java.lang.Object[] r11 = new java.lang.Object[r1]
            org.bytedeco.javacpp.tools.Token r33 = org.bytedeco.javacpp.tools.Token.EOF
            r11[r28] = r33
            boolean r11 = r0.match(r11)
            if (r11 != 0) goto L_0x0722
            java.lang.Object[] r11 = new java.lang.Object[r1]
            java.lang.Character r33 = java.lang.Character.valueOf(r18)
            r11[r28] = r33
            boolean r11 = r0.match(r11)
            if (r11 == 0) goto L_0x06b8
            int r7 = r7 + 1
            goto L_0x06c8
        L_0x06b8:
            java.lang.Object[] r11 = new java.lang.Object[r1]
            java.lang.Character r1 = java.lang.Character.valueOf(r17)
            r11[r28] = r1
            boolean r1 = r0.match(r11)
            if (r1 == 0) goto L_0x06c8
            int r7 = r7 + -1
        L_0x06c8:
            if (r7 != 0) goto L_0x06fe
            r1 = 2
            java.lang.Object[] r11 = new java.lang.Object[r1]
            java.lang.Integer r1 = java.lang.Integer.valueOf(r16)
            r11[r28] = r1
            r36 = 62
            java.lang.Character r1 = java.lang.Character.valueOf(r36)
            r37 = r2
            r2 = 1
            r11[r2] = r1
            boolean r1 = r0.match(r11)
            if (r1 != 0) goto L_0x0702
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get(r2)
            java.lang.Object[] r11 = new java.lang.Object[r2]
            java.lang.Character r2 = java.lang.Character.valueOf(r22)
            r11[r28] = r2
            boolean r1 = r1.match(r11)
            if (r1 == 0) goto L_0x0702
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            goto L_0x0726
        L_0x06fe:
            r37 = r2
            r36 = 62
        L_0x0702:
            if (r7 != 0) goto L_0x0715
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.Character r1 = java.lang.Character.valueOf(r21)
            r11 = 0
            r2[r11] = r1
            boolean r0 = r0.match(r2)
            if (r0 == 0) goto L_0x0715
            goto L_0x0726
        L_0x0715:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            r2 = r37
            r1 = 1
            r28 = 0
            goto L_0x069b
        L_0x0722:
            r37 = r2
            r36 = 62
        L_0x0726:
            r11 = r4
            r1 = r10
            r25 = r12
            r0 = r29
            r12 = r32
            r39 = r37
            r23 = 32
            r37 = r3
            r10 = r6
            goto L_0x079c
        L_0x0737:
            r30 = r1
            r37 = r2
            r32 = r7
            r36 = 62
            r2 = 0
            int r5 = r3 / 2
            if (r6 == 0) goto L_0x0748
            boolean r0 = r6.skipDefaults
            if (r0 != 0) goto L_0x074e
        L_0x0748:
            int r0 = r3 % 2
            if (r0 == 0) goto L_0x074e
            r7 = 1
            goto L_0x074f
        L_0x074e:
            r7 = 0
        L_0x074f:
            r10 = 0
            r11 = 0
            r38 = 0
            r0 = r41
            r1 = r42
            r39 = r37
            r37 = r3
            r3 = r5
            r5 = r4
            r4 = r7
            r7 = r5
            r5 = r10
            r10 = r6
            r6 = r11
            r11 = r7
            r25 = r12
            r12 = r32
            r23 = 32
            r7 = r38
            org.bytedeco.javacpp.tools.Declarator r0 = r0.declarator(r1, r2, r3, r4, r5, r6, r7)
            org.bytedeco.javacpp.tools.Type r1 = r0.type
            java.lang.String r2 = r0.cppName
            int r2 = r2.lastIndexOf(r12)
            java.lang.String r3 = r9.namespace
            if (r3 == 0) goto L_0x0795
            if (r2 >= 0) goto L_0x0795
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r9.namespace
            r2.append(r3)
            r2.append(r12)
            java.lang.String r3 = r0.cppName
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.cppName = r2
        L_0x0795:
            r5 = r29
            r40 = r1
            r1 = r0
            r0 = r40
        L_0x079c:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
        L_0x07a2:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r6 = org.bytedeco.javacpp.tools.Token.EOF
            r7 = 0
            r4[r7] = r6
            boolean r4 = r2.match(r4)
            if (r4 != 0) goto L_0x0830
            r4 = 3
            java.lang.Object[] r6 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r28 = org.bytedeco.javacpp.tools.Token.CONST
            r6[r7] = r28
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.__CONST
            r6[r3] = r7
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.CONSTEXPR
            r29 = 2
            r6[r29] = r7
            boolean r6 = r2.match(r6)
            if (r6 == 0) goto L_0x07d5
            r6 = r39
            r6.constMember = r3
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            r7 = 2
            r28 = 0
            goto L_0x07e8
        L_0x07d5:
            r6 = r39
            java.lang.Object[] r7 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r29 = org.bytedeco.javacpp.tools.Token.OVERRIDE
            r28 = 0
            r7[r28] = r29
            boolean r7 = r2.match(r7)
            if (r7 == 0) goto L_0x07e7
            r0.virtual = r3
        L_0x07e7:
            r7 = 2
        L_0x07e8:
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r7 = 38
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            r4[r28] = r7
            java.lang.String r7 = "&&"
            r4[r3] = r7
            boolean r2 = r2.match(r4)
            if (r2 == 0) goto L_0x0801
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            r2.next()
        L_0x0801:
            org.bytedeco.javacpp.tools.Attribute r2 = r41.attribute()
            if (r2 == 0) goto L_0x0823
            boolean r3 = r2.annotation
            if (r3 == 0) goto L_0x0823
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            org.bytedeco.javacpp.tools.Type r4 = r1.type
            java.lang.String r7 = r4.annotations
            r3.append(r7)
            java.lang.String r2 = r2.javaName
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r4.annotations = r2
            goto L_0x0826
        L_0x0823:
            if (r2 != 0) goto L_0x0826
            goto L_0x0832
        L_0x0826:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            r39 = r6
            goto L_0x07a2
        L_0x0830:
            r6 = r39
        L_0x0832:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r7 = 0
            r4[r7] = r19
            boolean r2 = r2.match(r4)
            if (r2 == 0) goto L_0x084d
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            org.bytedeco.javacpp.tools.Type r0 = r41.type(r42)
        L_0x084d:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.Object[] r4 = new java.lang.Object[r3]
            java.lang.Character r7 = java.lang.Character.valueOf(r22)
            r28 = 0
            r4[r28] = r7
            boolean r2 = r2.match(r4)
            if (r2 == 0) goto L_0x086e
            r41.body()
            r3 = r43
            r32 = r12
            r2 = r31
            goto L_0x08e8
        L_0x086e:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r3 = 61
            java.lang.Character r3 = java.lang.Character.valueOf(r3)
            r4[r28] = r3
            boolean r2 = r2.match(r4)
            if (r2 == 0) goto L_0x08dd
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            r3 = 3
            java.lang.Object[] r4 = new java.lang.Object[r3]
            java.lang.String r3 = "0"
            r4[r28] = r3
            org.bytedeco.javacpp.tools.Token r3 = org.bytedeco.javacpp.tools.Token.DELETE
            r7 = 1
            r4[r7] = r3
            org.bytedeco.javacpp.tools.Token r3 = org.bytedeco.javacpp.tools.Token.DEFAULT
            r29 = 2
            r4[r29] = r3
            org.bytedeco.javacpp.tools.Token r2 = r2.expect(r4)
            java.lang.Object[] r3 = new java.lang.Object[r7]
            java.lang.String r4 = "0"
            r3[r28] = r4
            boolean r3 = r2.match(r3)
            if (r3 == 0) goto L_0x08af
            r6.abstractMember = r7
            goto L_0x08c5
        L_0x08af:
            java.lang.Object[] r3 = new java.lang.Object[r7]
            org.bytedeco.javacpp.tools.Token r4 = org.bytedeco.javacpp.tools.Token.DELETE
            r3[r28] = r4
            boolean r2 = r2.match(r3)
            if (r2 == 0) goto L_0x08c5
            r2 = r31
            r6.text = r2
            r3 = r43
            r3.add((org.bytedeco.javacpp.tools.Declaration) r6)
            return r7
        L_0x08c5:
            r3 = r43
            r2 = r31
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r8.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.next()
            r32 = r12
            java.lang.Object[] r12 = new java.lang.Object[r7]
            java.lang.Character r7 = java.lang.Character.valueOf(r21)
            r12[r28] = r7
            r4.expect(r12)
            goto L_0x08e3
        L_0x08dd:
            r3 = r43
            r32 = r12
            r2 = r31
        L_0x08e3:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r8.tokens
            r4.next()
        L_0x08e8:
            boolean r4 = r6.constMember
            if (r4 != 0) goto L_0x08f7
            java.lang.String r4 = r9.constName
            if (r4 == 0) goto L_0x08f7
            r6.text = r2
            r3.add((org.bytedeco.javacpp.tools.Declaration) r6)
            r0 = 1
            return r0
        L_0x08f7:
            boolean r4 = r6.constMember
            if (r4 == 0) goto L_0x092d
            boolean r4 = r0.virtual
            if (r4 == 0) goto L_0x092d
            boolean r4 = r9.virtualize
            if (r4 == 0) goto L_0x092d
            java.lang.String r4 = r0.annotations
            java.lang.String r7 = "@Const"
            boolean r4 = r4.contains(r7)
            if (r4 == 0) goto L_0x0918
            java.lang.String r4 = r0.annotations
            r7 = 2
            r12 = 1
            java.lang.String r4 = incorporateConstAnnotation(r4, r7, r12)
            r0.annotations = r4
            goto L_0x092d
        L_0x0918:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = r0.annotations
            r4.append(r7)
            java.lang.String r7 = "@Const({false, false, true}) "
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            r0.annotations = r4
        L_0x092d:
            boolean r4 = r0.virtual
            java.lang.String r7 = " "
            if (r4 == 0) goto L_0x095d
            boolean r4 = r9.virtualize
            if (r4 == 0) goto L_0x095d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r12 = "@Virtual"
            r4.append(r12)
            boolean r12 = r6.abstractMember
            if (r12 == 0) goto L_0x0948
            java.lang.String r12 = "(true) "
            goto L_0x0949
        L_0x0948:
            r12 = r7
        L_0x0949:
            r4.append(r12)
            boolean r12 = r9.inaccessible
            if (r12 == 0) goto L_0x0953
            java.lang.String r12 = "protected native "
            goto L_0x0955
        L_0x0953:
            r12 = r26
        L_0x0955:
            r4.append(r12)
            java.lang.String r4 = r4.toString()
            goto L_0x095f
        L_0x095d:
            r4 = r24
        L_0x095f:
            r6.declarator = r1
            java.lang.String r12 = r9.namespace
            if (r12 == 0) goto L_0x098b
            java.lang.String r12 = r9.javaName
            if (r12 != 0) goto L_0x098b
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r24 = r13
            java.lang.String r13 = r6.text
            r12.append(r13)
            java.lang.String r13 = "@Namespace(\""
            r12.append(r13)
            java.lang.String r13 = r9.namespace
            r12.append(r13)
            java.lang.String r13 = "\") "
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r6.text = r12
            goto L_0x098d
        L_0x098b:
            r24 = r13
        L_0x098d:
            if (r15 == 0) goto L_0x09c2
            java.lang.String[] r12 = r15.annotations
            if (r12 == 0) goto L_0x09c2
            java.lang.String[] r12 = r15.annotations
            int r13 = r12.length
            r31 = r14
            r14 = 0
        L_0x0999:
            if (r14 >= r13) goto L_0x09c4
            r29 = r13
            r13 = r12[r14]
            r38 = r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r39 = r15
            java.lang.String r15 = r0.annotations
            r12.append(r15)
            r12.append(r13)
            r12.append(r7)
            java.lang.String r12 = r12.toString()
            r0.annotations = r12
            int r14 = r14 + 1
            r13 = r29
            r12 = r38
            r15 = r39
            goto L_0x0999
        L_0x09c2:
            r31 = r14
        L_0x09c4:
            r39 = r15
            boolean r12 = r0.constructor
            if (r12 == 0) goto L_0x0a17
            if (r5 == 0) goto L_0x0a17
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r12 = r6.text
            r7.append(r12)
            java.lang.String r12 = "public "
            r7.append(r12)
            java.lang.String r12 = r9.javaName
            java.lang.String r12 = r9.shorten(r12)
            r7.append(r12)
            org.bytedeco.javacpp.tools.Parameters r12 = r1.parameters
            java.lang.String r12 = r12.list
            r7.append(r12)
            java.lang.String r12 = " { super((Pointer)null); allocate"
            r7.append(r12)
            java.lang.String r12 = r5.names
            r7.append(r12)
            java.lang.String r12 = "; }\n"
            r7.append(r12)
            java.lang.String r12 = r0.annotations
            r7.append(r12)
            java.lang.String r12 = "private native void allocate"
            r7.append(r12)
            org.bytedeco.javacpp.tools.Parameters r12 = r1.parameters
            java.lang.String r12 = r12.list
            r7.append(r12)
            java.lang.String r12 = ";\n"
            r7.append(r12)
            java.lang.String r7 = r7.toString()
            r6.text = r7
            goto L_0x0a4c
        L_0x0a17:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = r6.text
            r12.append(r13)
            r12.append(r4)
            java.lang.String r13 = r0.annotations
            r12.append(r13)
            java.lang.String r13 = r0.javaName
            java.lang.String r13 = r9.shorten(r13)
            r12.append(r13)
            r12.append(r7)
            java.lang.String r7 = r1.javaName
            r12.append(r7)
            org.bytedeco.javacpp.tools.Parameters r7 = r1.parameters
            java.lang.String r7 = r7.list
            r12.append(r7)
            java.lang.String r7 = ";\n"
            r12.append(r7)
            java.lang.String r7 = r12.toString()
            r6.text = r7
        L_0x0a4c:
            java.lang.String r7 = r1.signature
            r6.signature = r7
            if (r30 == 0) goto L_0x0a5f
            java.lang.String r7 = r6.text
            java.lang.String r12 = "@Override "
            r13 = r35
            java.lang.String r7 = r7.replaceAll(r12, r13)
            r6.text = r7
            goto L_0x0a61
        L_0x0a5f:
            r13 = r35
        L_0x0a61:
            if (r10 == 0) goto L_0x0a6f
            java.lang.String r7 = r10.javaText
            if (r7 == 0) goto L_0x0a6f
            if (r27 == 0) goto L_0x0b06
            java.lang.String r7 = r10.javaText
            r6.text = r7
            r6.signature = r7
        L_0x0a6f:
            java.lang.String r7 = r41.commentAfter()
            if (r27 == 0) goto L_0x0a8a
            r3.spacing = r2
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r7)
            java.lang.String r7 = r6.text
            r12.append(r7)
            java.lang.String r7 = r12.toString()
            r6.text = r7
        L_0x0a8a:
            r7 = 1
            r6.function = r7
            java.util.Iterator r7 = r11.iterator()
            r12 = 0
        L_0x0a92:
            boolean r14 = r7.hasNext()
            if (r14 == 0) goto L_0x0aa8
            java.lang.Object r14 = r7.next()
            org.bytedeco.javacpp.tools.Declarator r14 = (org.bytedeco.javacpp.tools.Declarator) r14
            java.lang.String r15 = r1.signature
            java.lang.String r14 = r14.signature
            boolean r14 = r15.equals(r14)
            r12 = r12 | r14
            goto L_0x0a92
        L_0x0aa8:
            java.lang.String r7 = r1.javaName
            int r7 = r7.length()
            if (r7 <= 0) goto L_0x0acd
            if (r12 != 0) goto L_0x0acd
            boolean r7 = r0.destructor
            if (r7 == 0) goto L_0x0abc
            if (r10 == 0) goto L_0x0acd
            java.lang.String r7 = r10.javaText
            if (r7 == 0) goto L_0x0acd
        L_0x0abc:
            boolean r6 = r3.add((org.bytedeco.javacpp.tools.Declaration) r6)
            if (r6 == 0) goto L_0x0ac4
            r27 = 0
        L_0x0ac4:
            boolean r6 = r0.virtual
            if (r6 == 0) goto L_0x0ae4
            boolean r6 = r9.virtualize
            if (r6 == 0) goto L_0x0ae4
            goto L_0x0b06
        L_0x0acd:
            if (r12 == 0) goto L_0x0ae4
            int r6 = r37 / 2
            if (r6 <= 0) goto L_0x0ae4
            int r7 = r37 % 2
            if (r7 != 0) goto L_0x0ae4
            int r7 = r1.infoNumber
            org.bytedeco.javacpp.tools.Parameters r12 = r1.parameters
            int r12 = r12.infoNumber
            int r7 = java.lang.Math.max(r7, r12)
            if (r6 <= r7) goto L_0x0ae4
            goto L_0x0b06
        L_0x0ae4:
            r11.add(r1)
            int r1 = r37 + 1
            r3 = r1
            r29 = r5
            r6 = r10
            r35 = r13
            r13 = r24
            r12 = r25
            r14 = r31
            r7 = r32
            r15 = r39
            r5 = 62
            r10 = 2
            r31 = r2
            r24 = r4
            r4 = r11
            r11 = 1
            goto L_0x0561
        L_0x0b04:
            r3 = r43
        L_0x0b06:
            r0 = 0
            r3.spacing = r0
            r0 = 1
            return r0
        L_0x0b0b:
            r3 = r43
            r2 = r31
            r0 = 1
            r36 = 62
        L_0x0b12:
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Character r5 = java.lang.Character.valueOf(r20)
            r6 = 0
            r4[r6] = r5
            java.lang.Character r5 = java.lang.Character.valueOf(r22)
            r4[r0] = r5
            java.lang.Character r0 = java.lang.Character.valueOf(r21)
            r5 = 2
            r4[r5] = r0
            org.bytedeco.javacpp.tools.Token r0 = org.bytedeco.javacpp.tools.Token.EOF
            r5 = 3
            r4[r5] = r0
            boolean r0 = r1.match(r4)
            if (r0 != 0) goto L_0x0b41
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            r0 = 1
            goto L_0x0b12
        L_0x0b41:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r1 = 1
            java.lang.Object[] r4 = new java.lang.Object[r1]
            org.bytedeco.javacpp.tools.Token r1 = org.bytedeco.javacpp.tools.Token.EOF
            r5 = 0
            r4[r5] = r1
            boolean r0 = r0.match(r4)
            if (r0 != 0) goto L_0x0b5b
            org.bytedeco.javacpp.tools.Attribute r0 = r41.attribute()
            if (r0 != 0) goto L_0x0b41
        L_0x0b5b:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r1 = 1
            java.lang.Object[] r4 = new java.lang.Object[r1]
            java.lang.Character r5 = java.lang.Character.valueOf(r20)
            r28 = 0
            r4[r28] = r5
            boolean r0 = r0.match(r4)
            if (r0 == 0) goto L_0x0bf5
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            r4 = 0
        L_0x0b79:
            java.lang.Object[] r5 = new java.lang.Object[r1]
            org.bytedeco.javacpp.tools.Token r6 = org.bytedeco.javacpp.tools.Token.EOF
            r5[r28] = r6
            boolean r5 = r0.match(r5)
            if (r5 != 0) goto L_0x0bf5
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.Character r6 = java.lang.Character.valueOf(r18)
            r5[r28] = r6
            boolean r5 = r0.match(r5)
            if (r5 == 0) goto L_0x0b96
            int r4 = r4 + 1
            goto L_0x0ba6
        L_0x0b96:
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.Character r1 = java.lang.Character.valueOf(r17)
            r5[r28] = r1
            boolean r1 = r0.match(r5)
            if (r1 == 0) goto L_0x0ba6
            int r4 = r4 + -1
        L_0x0ba6:
            if (r4 != 0) goto L_0x0bd8
            r1 = 2
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.Integer r1 = java.lang.Integer.valueOf(r16)
            r5[r28] = r1
            java.lang.Character r1 = java.lang.Character.valueOf(r36)
            r6 = 1
            r5[r6] = r1
            boolean r1 = r0.match(r5)
            if (r1 != 0) goto L_0x0bd8
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get(r6)
            java.lang.Object[] r5 = new java.lang.Object[r6]
            java.lang.Character r6 = java.lang.Character.valueOf(r22)
            r5[r28] = r6
            boolean r1 = r1.match(r5)
            if (r1 == 0) goto L_0x0bd8
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            goto L_0x0bf5
        L_0x0bd8:
            if (r4 != 0) goto L_0x0beb
            r1 = 1
            java.lang.Object[] r5 = new java.lang.Object[r1]
            java.lang.Character r1 = java.lang.Character.valueOf(r21)
            r6 = 0
            r5[r6] = r1
            boolean r0 = r0.match(r5)
            if (r0 == 0) goto L_0x0beb
            goto L_0x0bf5
        L_0x0beb:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            r1 = 1
            r28 = 0
            goto L_0x0b79
        L_0x0bf5:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r1 = 1
            java.lang.Object[] r4 = new java.lang.Object[r1]
            r5 = 0
            r4[r5] = r19
            boolean r0 = r0.match(r4)
            if (r0 == 0) goto L_0x0c0f
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            r41.type(r42)
        L_0x0c0f:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.Object[] r4 = new java.lang.Object[r1]
            java.lang.Character r1 = java.lang.Character.valueOf(r22)
            r5 = 0
            r4[r5] = r1
            boolean r0 = r0.match(r4)
            if (r0 == 0) goto L_0x0c2b
            r41.body()
            r0 = r29
            r6 = 1
            goto L_0x0c4e
        L_0x0c2b:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r1 = 2
            java.lang.Object[] r4 = new java.lang.Object[r1]
            java.lang.Character r6 = java.lang.Character.valueOf(r21)
            r4[r5] = r6
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.EOF
            r6 = 1
            r4[r6] = r5
            boolean r0 = r0.match(r4)
            if (r0 != 0) goto L_0x0c4c
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
            r5 = 0
            goto L_0x0c2b
        L_0x0c4c:
            r0 = r29
        L_0x0c4e:
            r0.text = r2
            r0.function = r6
            r3.add((org.bytedeco.javacpp.tools.Declaration) r0)
            return r6
        L_0x0c56:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r1 = r23
            r0.index = r1
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.function(org.bytedeco.javacpp.tools.Context, org.bytedeco.javacpp.tools.DeclarationList):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean variable(Context context, DeclarationList declarationList) throws ParserException {
        String str;
        String str2;
        DeclarationList declarationList2;
        String str3;
        String str4;
        String str5;
        String str6;
        CharSequence charSequence;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        Info info;
        String str16;
        String str17;
        String str18;
        Object obj;
        boolean z;
        int i;
        String str19;
        String str20;
        StringBuilder sb;
        String str21;
        String str22;
        String str23;
        String str24;
        Parser parser = this;
        Context context2 = context;
        DeclarationList declarationList3 = declarationList;
        int i2 = parser.tokens.index;
        String str25 = parser.tokens.get().spacing;
        Declarator declarator = declarator(context, (String) null, -1, false, 0, false, true);
        Declaration declaration = new Declaration();
        String str26 = declarator.cppName;
        String str27 = declarator.javaName;
        Attribute attribute = attribute();
        if (attribute != null && attribute.annotation) {
            StringBuilder sb2 = new StringBuilder();
            Type type = declarator.type;
            sb2.append(type.annotations);
            sb2.append(attribute.javaName);
            type.annotations = sb2.toString();
        }
        if (!(str26 == null || str27 == null)) {
            if (parser.tokens.get().match('(', '[', '=', ',', ':', ';', '{')) {
                if (declarator.type.staticMember || context2.javaName == null) {
                    str = "public static native ";
                    str2 = "void ";
                } else {
                    str2 = context2.shorten(context2.javaName) + " ";
                    str = "public native ";
                }
                String str28 = str2;
                String str29 = str;
                String str30 = "::";
                int lastIndexOf = str26.lastIndexOf(str30);
                if (context2.namespace != null && lastIndexOf < 0) {
                    str26 = context2.namespace + str30 + str26;
                }
                Info first = parser.infoMap.getFirst(str26);
                String str31 = " ";
                Info first2 = context2.variable != null ? parser.infoMap.getFirst(context2.variable.cppName) : null;
                if (declarator.cppName.length() == 0 || ((first != null && first.skip) || (first2 != null && first2.skip))) {
                    declaration.text = str25;
                    declarationList3.add(declaration);
                    while (true) {
                        if (!parser.tokens.get().match(Token.EOF, ';')) {
                            parser.tokens.next();
                        } else {
                            parser.tokens.next();
                            return true;
                        }
                    }
                } else {
                    if (first == null) {
                        Info first3 = parser.infoMap.getFirst(declarator.cppName);
                        parser.infoMap.put(first3 != null ? new Info(first3).cppNames(str26) : new Info(str26));
                    }
                    Declarator declarator2 = context2.variable;
                    int i3 = 0;
                    boolean z2 = true;
                    while (true) {
                        if (i3 >= Integer.MAX_VALUE) {
                            declarationList2 = declarationList3;
                            break;
                        }
                        Declaration declaration2 = new Declaration();
                        parser.tokens.index = i2;
                        Declaration declaration3 = declaration2;
                        String str32 = str30;
                        String str33 = str29;
                        String str34 = str28;
                        String str35 = str31;
                        int i4 = i3;
                        Declarator declarator3 = declarator(context, (String) null, -1, false, i3, false, true);
                        if (declarator3 == null || declarator3.cppName == null) {
                            declarationList2 = declarationList;
                        } else {
                            declaration3.declarator = declarator3;
                            String str36 = declarator3.cppName;
                            int lastIndexOf2 = str36.lastIndexOf(str32);
                            if (context2.namespace != null && lastIndexOf2 < 0) {
                                str36 = context2.namespace + str32 + str36;
                            }
                            Info first4 = parser.infoMap.getFirst(str36);
                            int lastIndexOf3 = str36.lastIndexOf(str32);
                            if (lastIndexOf3 >= 0) {
                                str36 = str36.substring(lastIndexOf3 + 2);
                            }
                            String str37 = str36;
                            Info info2 = first4;
                            String str38 = str32;
                            String str39 = str25;
                            String str40 = declarator3.javaName;
                            int i5 = i2;
                            String str41 = ");";
                            String str42 = "(";
                            String str43 = str35;
                            CharSequence charSequence2 = "@ByVal ";
                            String str44 = "";
                            CharSequence charSequence3 = "@ByRef ";
                            if (declarator2 == null || declarator2.indices == 0 || declarator3.indices == 0) {
                                String str45 = "@MemberGetter ";
                                String str46 = str44;
                                int i6 = 0;
                                while (true) {
                                    str21 = str44;
                                    if (i6 >= ((declarator2 == null || declarator2.indices == 0) ? declarator3.indices : declarator2.indices)) {
                                        break;
                                    }
                                    if (i6 > 0) {
                                        str46 = str46 + ", ";
                                    }
                                    str46 = str46 + "int " + ((char) (i6 + 105));
                                    i6++;
                                    str44 = str21;
                                }
                                if (context2.namespace != null && context2.javaName == null) {
                                    declaration3.text += "@Namespace(\"" + context2.namespace + "\") ";
                                }
                                if (declarator2 == null || declarator2.cppName == null || declarator2.cppName.length() <= 0) {
                                    str4 = "@Name(\"";
                                    str3 = "\") ";
                                    str22 = str21;
                                    str23 = str40;
                                } else {
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append(declaration3.text);
                                    if (declarator2.indices == 0) {
                                        StringBuilder sb4 = new StringBuilder();
                                        sb4.append("@Name(\"");
                                        str4 = "@Name(\"";
                                        sb4.append(declarator2.cppName);
                                        sb4.append(".");
                                        sb4.append(str37);
                                        sb4.append("\") ");
                                        str24 = sb4.toString();
                                    } else {
                                        str4 = "@Name(\"";
                                        str24 = "@Name({\"" + declarator2.cppName + "\", \"." + str37 + "\"}) ";
                                    }
                                    sb3.append(str24);
                                    declaration3.text = sb3.toString();
                                    str3 = "\") ";
                                    str22 = str21;
                                    declarator3.type.annotations = declarator3.type.annotations.replaceAll("@Name\\(.*\\) ", str22);
                                    str23 = declarator2.javaName + "_" + str37;
                                }
                                if ((!declarator3.type.constValue || declarator3.indirections != 0) && !declarator3.constPointer && !declarator3.type.constExpr) {
                                    str10 = str45;
                                } else {
                                    StringBuilder sb5 = new StringBuilder();
                                    sb5.append(declaration3.text);
                                    str10 = str45;
                                    sb5.append(str10);
                                    declaration3.text = sb5.toString();
                                }
                                StringBuilder sb6 = new StringBuilder();
                                str8 = "@Name({\"";
                                sb6.append(declaration3.text);
                                str15 = str33;
                                sb6.append(str15);
                                str9 = "\", \".";
                                str5 = str22;
                                CharSequence charSequence4 = charSequence3;
                                CharSequence charSequence5 = charSequence2;
                                str6 = str37;
                                CharSequence charSequence6 = charSequence5;
                                sb6.append(declarator3.type.annotations.replace(charSequence6, charSequence4));
                                sb6.append(declarator3.type.javaName);
                                str14 = str43;
                                sb6.append(str14);
                                sb6.append(str23);
                                charSequence = charSequence6;
                                str13 = str42;
                                sb6.append(str13);
                                sb6.append(str46);
                                str7 = "\"}) ";
                                String str47 = str41;
                                sb6.append(str47);
                                declaration3.text = sb6.toString();
                                if ((!declarator3.type.constValue || declarator3.indirections != 0) && !declarator3.constPointer && !declarator3.type.constExpr) {
                                    if (str46.length() > 0) {
                                        str46 = str46 + ", ";
                                    }
                                    str41 = str47;
                                    String substring = declarator3.type.javaName.substring(declarator3.type.javaName.lastIndexOf(str14) + 1);
                                    StringBuilder sb7 = new StringBuilder();
                                    charSequence3 = charSequence4;
                                    sb7.append(declaration3.text);
                                    sb7.append(str14);
                                    sb7.append(str15);
                                    str12 = str34;
                                    sb7.append(str12);
                                    sb7.append(str23);
                                    sb7.append(str13);
                                    sb7.append(str46);
                                    sb7.append(substring);
                                    sb7.append(" setter);");
                                    declaration3.text = sb7.toString();
                                } else {
                                    str41 = str47;
                                    charSequence3 = charSequence4;
                                    str12 = str34;
                                }
                                declaration3.text += "\n";
                                if ((declarator3.type.constValue || declarator3.constPointer || declarator3.type.constExpr) && declarator3.type.staticMember && str46.length() == 0) {
                                    String substring2 = declarator3.type.javaName.substring(declarator3.type.javaName.lastIndexOf(32) + 1);
                                    if ("byte".equals(substring2) || "short".equals(substring2) || "int".equals(substring2) || LongTypedProperty.TYPE.equals(substring2) || "float".equals(substring2) || DoubleTypedProperty.TYPE.equals(substring2) || "char".equals(substring2) || BooleanTypedProperty.TYPE.equals(substring2)) {
                                        declaration3.text += "public static final " + substring2 + str14 + str23 + " = " + str23 + "();\n";
                                    }
                                }
                                str11 = str23;
                            } else {
                                str10 = "@MemberGetter ";
                                str4 = "@Name(\"";
                                str3 = "\") ";
                                str5 = str44;
                                str12 = str34;
                                str11 = str40;
                                str8 = "@Name({\"";
                                str15 = str33;
                                str9 = "\", \".";
                                str14 = str43;
                                charSequence = charSequence2;
                                str6 = str37;
                                str13 = str42;
                                str7 = "\"}) ";
                            }
                            if (declarator3.indices > 0) {
                                parser = this;
                                int i7 = i5;
                                parser.tokens.index = i7;
                                String str48 = str15;
                                String str49 = str8;
                                String str50 = str14;
                                String str51 = str9;
                                int i8 = i7;
                                String str52 = str7;
                                String str53 = "int ";
                                String str54 = str4;
                                CharSequence charSequence7 = charSequence;
                                String str55 = str6;
                                String str56 = str12;
                                String str57 = str11;
                                String str58 = str10;
                                info = info2;
                                String str59 = str41;
                                String str60 = str13;
                                CharSequence charSequence8 = charSequence3;
                                String str61 = str5;
                                String str62 = str3;
                                Declarator declarator4 = declarator(context, (String) null, -1, false, i4, true, false);
                                String str63 = str61;
                                int i9 = 0;
                                while (true) {
                                    if (declarator2 == null) {
                                        i = 0;
                                    } else {
                                        i = declarator2.indices;
                                    }
                                    if (i9 >= i) {
                                        break;
                                    }
                                    if (i9 > 0) {
                                        str63 = str63 + ", ";
                                    }
                                    str63 = str63 + str53 + ((char) (i9 + 105));
                                    i9++;
                                }
                                if (context2.namespace != null && context2.javaName == null) {
                                    declaration3.text += "@Namespace(\"" + context2.namespace + str62;
                                }
                                if (declarator2 == null || declarator2.cppName.length() <= 0) {
                                    str19 = str57;
                                } else {
                                    StringBuilder sb8 = new StringBuilder();
                                    sb8.append(declaration3.text);
                                    if (declarator2.indices == 0) {
                                        sb = new StringBuilder();
                                        sb.append(str54);
                                        sb.append(declarator2.cppName);
                                        sb.append(".");
                                        str20 = str55;
                                        sb.append(str20);
                                        sb.append(str62);
                                    } else {
                                        str20 = str55;
                                        sb = new StringBuilder();
                                        sb.append(str49);
                                        sb.append(declarator2.cppName);
                                        sb.append(str51);
                                        sb.append(str20);
                                        sb.append(str52);
                                    }
                                    sb8.append(sb.toString());
                                    declaration3.text = sb8.toString();
                                    declarator4.type.annotations = declarator4.type.annotations.replaceAll("@Name\\(.*\\) ", str61);
                                    str19 = declarator2.javaName + "_" + str20;
                                }
                                i2 = i8;
                                parser.tokens.index = i2;
                                String str64 = str63;
                                Declarator declarator5 = declarator4;
                                Declarator declarator6 = declarator(context, (String) null, -1, false, i4, false, false);
                                if (declarator6.type.constValue || declarator6.constPointer || declarator6.indirections < 2 || declarator6.type.constExpr) {
                                    declaration3.text += str58;
                                }
                                StringBuilder sb9 = new StringBuilder();
                                sb9.append(declaration3.text);
                                str16 = str48;
                                sb9.append(str16);
                                sb9.append(declarator5.type.annotations.replace(charSequence7, charSequence8));
                                sb9.append(declarator5.type.javaName);
                                str17 = str50;
                                sb9.append(str17);
                                sb9.append(str19);
                                String str65 = str60;
                                sb9.append(str65);
                                String str66 = str64;
                                sb9.append(str66);
                                sb9.append(str59);
                                declaration3.text = sb9.toString();
                                if (declarator5.type.constValue || declarator5.constPointer || declarator6.indirections < 2 || declarator6.type.constExpr) {
                                    str18 = str56;
                                } else {
                                    if (str66.length() > 0) {
                                        str66 = str66 + ", ";
                                    }
                                    StringBuilder sb10 = new StringBuilder();
                                    sb10.append(declaration3.text);
                                    sb10.append(str17);
                                    sb10.append(str16);
                                    str18 = str56;
                                    sb10.append(str18);
                                    sb10.append(str19);
                                    sb10.append(str65);
                                    sb10.append(str66);
                                    sb10.append(declarator5.type.javaName);
                                    sb10.append(" setter);");
                                    declaration3.text = sb10.toString();
                                }
                                declaration3.text += "\n";
                                declarator3 = declarator5;
                            } else {
                                parser = this;
                                str16 = str15;
                                str17 = str14;
                                str18 = str12;
                                info = info2;
                                i2 = i5;
                            }
                            declaration3.signature = declarator3.signature;
                            Info info3 = info;
                            if (info3 == null || info3.javaText == null) {
                                obj = null;
                            } else {
                                String str67 = info3.javaText;
                                declaration3.text = str67;
                                declaration3.signature = str67;
                                obj = null;
                                declaration3.declarator = null;
                            }
                            while (true) {
                                if (parser.tokens.get().match(Token.EOF, ';')) {
                                    break;
                                }
                                parser.tokens.next();
                            }
                            parser.tokens.next();
                            String commentAfter = commentAfter();
                            Object obj2 = obj;
                            String str68 = str39;
                            DeclarationList declarationList4 = declarationList;
                            if (z2) {
                                declarationList4.spacing = str68;
                                declaration3.text = commentAfter + declaration3.text;
                                z = true;
                                z2 = false;
                            } else {
                                z = true;
                            }
                            declaration3.variable = z;
                            declarationList4.add(declaration3);
                            i3 = i4 + 1;
                            declarationList3 = declarationList4;
                            str28 = str18;
                            str31 = str17;
                            str25 = str68;
                            str29 = str16;
                            str30 = str38;
                        }
                    }
                    declarationList2 = declarationList;
                    declarationList2.spacing = null;
                    return true;
                }
            }
        }
        parser.tokens.index = i2;
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0770, code lost:
        r33 = r3;
        r34 = r5;
        r29 = r6;
        r3 = r10;
        r32 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0180, code lost:
        r2 = r2.spacing + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x0180, code lost:
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0139, code lost:
        r4 = new org.bytedeco.javacpp.tools.Info(r13).cppText("");
        r0.tokens.index = r2;
        r2 = r0.tokens.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0156, code lost:
        if (r0.tokens.index >= r9) goto L_0x0190;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0158, code lost:
        r7 = new java.lang.StringBuilder();
        r7.append(r4.cppText);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x016c, code lost:
        if (r2.match(r15) == false) goto L_0x016f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0180, code lost:
        r7.append(r2);
        r4.cppText = r7.toString();
        r2 = r0.tokens.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0190, code lost:
        r0.infoMap.put(r4);
     */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x03b0  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x06d9  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x06e5  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0758 A[LOOP:2: B:30:0x010a->B:251:0x0758, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0751 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean macro(org.bytedeco.javacpp.tools.Context r46, org.bytedeco.javacpp.tools.DeclarationList r47) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r45 = this;
            r0 = r45
            r1 = r47
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            int r2 = r2.index
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r6 = 35
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            r7 = 0
            r5[r7] = r6
            boolean r3 = r3.match(r5)
            if (r3 != 0) goto L_0x0021
            return r7
        L_0x0021:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            r3.raw = r4
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            java.lang.String r3 = r3.spacing
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            org.bytedeco.javacpp.tools.Token r5 = r5.next()
            java.lang.String r6 = r5.spacing
            r8 = 10
            int r6 = r6.indexOf(r8)
            if (r6 < 0) goto L_0x005d
            org.bytedeco.javacpp.tools.Declaration r2 = new org.bytedeco.javacpp.tools.Declaration
            r2.<init>()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r3)
            java.lang.String r3 = "// #"
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r2.text = r3
            r1.add((org.bytedeco.javacpp.tools.Declaration) r2)
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r1.raw = r7
            return r4
        L_0x005d:
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            r6.next()
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            int r6 = r6.index
            org.bytedeco.javacpp.tools.TokenIndexer r9 = r0.tokens
            org.bytedeco.javacpp.tools.Token r9 = r9.get()
        L_0x006c:
            java.lang.Object[] r10 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r11 = org.bytedeco.javacpp.tools.Token.EOF
            r10[r7] = r11
            boolean r10 = r9.match(r10)
            if (r10 != 0) goto L_0x0088
            java.lang.String r9 = r9.spacing
            int r9 = r9.indexOf(r8)
            if (r9 < 0) goto L_0x0081
            goto L_0x0088
        L_0x0081:
            org.bytedeco.javacpp.tools.TokenIndexer r9 = r0.tokens
            org.bytedeco.javacpp.tools.Token r9 = r9.next()
            goto L_0x006c
        L_0x0088:
            org.bytedeco.javacpp.tools.TokenIndexer r9 = r0.tokens
            int r9 = r9.index
        L_0x008c:
            org.bytedeco.javacpp.tools.TokenIndexer r10 = r0.tokens
            r11 = -1
            org.bytedeco.javacpp.tools.Token r10 = r10.get(r11)
            java.lang.Object[] r12 = new java.lang.Object[r4]
            r13 = 4
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12[r7] = r13
            boolean r10 = r10.match(r12)
            if (r10 == 0) goto L_0x00aa
            org.bytedeco.javacpp.tools.TokenIndexer r10 = r0.tokens
            int r11 = r10.index
            int r11 = r11 - r4
            r10.index = r11
            goto L_0x008c
        L_0x00aa:
            org.bytedeco.javacpp.tools.TokenIndexer r10 = r0.tokens
            int r10 = r10.index
            org.bytedeco.javacpp.tools.Declaration r12 = new org.bytedeco.javacpp.tools.Declaration
            r12.<init>()
            java.lang.Object[] r13 = new java.lang.Object[r4]
            org.bytedeco.javacpp.tools.Token r14 = org.bytedeco.javacpp.tools.Token.DEFINE
            r13[r7] = r14
            boolean r13 = r5.match(r13)
            r14 = 0
            java.lang.String r15 = "\n"
            if (r13 == 0) goto L_0x077e
            if (r6 >= r9) goto L_0x077e
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            r13.index = r6
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.get()
            java.lang.String r13 = r13.value
            org.bytedeco.javacpp.tools.TokenIndexer r11 = r0.tokens
            org.bytedeco.javacpp.tools.Token r11 = r11.next()
            java.lang.String r8 = r11.spacing
            int r8 = r8.length()
            r16 = 40
            if (r8 != 0) goto L_0x00f0
            java.lang.Object[] r8 = new java.lang.Object[r4]
            java.lang.Character r17 = java.lang.Character.valueOf(r16)
            r8[r7] = r17
            boolean r8 = r11.match(r8)
            if (r8 == 0) goto L_0x00f0
            r8 = 1
            goto L_0x00f1
        L_0x00f0:
            r8 = 0
        L_0x00f1:
            org.bytedeco.javacpp.tools.InfoMap r11 = r0.infoMap
            java.util.List r11 = r11.get(r13)
            int r17 = r11.size()
            if (r17 <= 0) goto L_0x00fe
            goto L_0x0106
        L_0x00fe:
            org.bytedeco.javacpp.tools.Info[] r11 = new org.bytedeco.javacpp.tools.Info[r4]
            r11[r7] = r14
            java.util.List r11 = java.util.Arrays.asList(r11)
        L_0x0106:
            java.util.Iterator r11 = r11.iterator()
        L_0x010a:
            boolean r17 = r11.hasNext()
            if (r17 == 0) goto L_0x0770
            java.lang.Object r17 = r11.next()
            r14 = r17
            org.bytedeco.javacpp.tools.Info r14 = (org.bytedeco.javacpp.tools.Info) r14
            if (r14 == 0) goto L_0x0120
            boolean r7 = r14.skip
            if (r7 == 0) goto L_0x0120
            goto L_0x0770
        L_0x0120:
            java.lang.String r7 = ""
            if (r14 != 0) goto L_0x012a
            if (r8 != 0) goto L_0x0139
            int r4 = r6 + 1
            if (r4 == r9) goto L_0x0139
        L_0x012a:
            if (r14 == 0) goto L_0x0197
            java.lang.String r4 = r14.cppText
            if (r4 != 0) goto L_0x0197
            java.lang.String[] r4 = r14.cppTypes
            if (r4 == 0) goto L_0x0197
            java.lang.String[] r4 = r14.cppTypes
            int r4 = r4.length
            if (r4 != 0) goto L_0x0197
        L_0x0139:
            org.bytedeco.javacpp.tools.Info r4 = new org.bytedeco.javacpp.tools.Info
            r8 = 1
            java.lang.String[] r11 = new java.lang.String[r8]
            r8 = 0
            r11[r8] = r13
            r4.<init>((java.lang.String[]) r11)
            org.bytedeco.javacpp.tools.Info r4 = r4.cppText(r7)
            org.bytedeco.javacpp.tools.TokenIndexer r7 = r0.tokens
            r7.index = r2
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
        L_0x0152:
            org.bytedeco.javacpp.tools.TokenIndexer r7 = r0.tokens
            int r7 = r7.index
            if (r7 >= r9) goto L_0x0190
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = r4.cppText
            r7.append(r8)
            r8 = 1
            java.lang.Object[] r11 = new java.lang.Object[r8]
            r8 = 0
            r11[r8] = r15
            boolean r8 = r2.match(r11)
            if (r8 == 0) goto L_0x016f
            goto L_0x0180
        L_0x016f:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r11 = r2.spacing
            r8.append(r11)
            r8.append(r2)
            java.lang.String r2 = r8.toString()
        L_0x0180:
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            r4.cppText = r2
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            goto L_0x0152
        L_0x0190:
            org.bytedeco.javacpp.tools.InfoMap r2 = r0.infoMap
            r2.put(r4)
            goto L_0x0770
        L_0x0197:
            java.lang.String r4 = "\") "
            r19 = r2
            java.lang.String r2 = "@Name(\""
            r20 = r7
            java.lang.String r7 = ";\n"
            r21 = 5
            r22 = r9
            java.lang.String r9 = " "
            if (r14 == 0) goto L_0x03b0
            r23 = r11
            java.lang.String r11 = r14.cppText
            if (r11 != 0) goto L_0x03a7
            java.lang.String[] r11 = r14.cppTypes
            if (r11 == 0) goto L_0x03a7
            java.lang.String[] r11 = r14.cppTypes
            int r11 = r11.length
            r1 = r8 ^ 1
            if (r11 <= r1) goto L_0x03a7
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r32 = r15
            r11 = -1
        L_0x01c2:
            r15 = 2147483647(0x7fffffff, float:NaN)
            if (r11 >= r15) goto L_0x0399
            org.bytedeco.javacpp.tools.TokenIndexer r15 = r0.tokens
            r33 = r3
            int r3 = r6 + 2
            r15.index = r3
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            java.lang.String r15 = "("
            r34 = r5
            r5 = 1
        L_0x01da:
            r35 = r8
            if (r8 == 0) goto L_0x026a
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            int r8 = r8.index
            if (r8 >= r10) goto L_0x026a
            java.lang.String[] r8 = r14.cppTypes
            int r8 = r8.length
            if (r5 >= r8) goto L_0x026a
            r36 = r10
            r8 = 1
            java.lang.Object[] r10 = new java.lang.Object[r8]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r21)
            r17 = 0
            r10[r17] = r8
            boolean r8 = r3.match(r10)
            if (r8 == 0) goto L_0x024a
            java.lang.String[] r8 = r14.cppTypes
            r8 = r8[r5]
            java.lang.String r3 = r3.value
            java.lang.String r10 = "..."
            boolean r10 = r3.equals(r10)
            if (r10 == 0) goto L_0x021b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r10 = "arg"
            r3.append(r10)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
        L_0x021b:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r15)
            r10.append(r8)
            r10.append(r9)
            r10.append(r3)
            java.lang.String r3 = r10.toString()
            int r5 = r5 + 1
            java.lang.String[] r8 = r14.cppTypes
            int r8 = r8.length
            if (r5 >= r8) goto L_0x0248
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r3)
            java.lang.String r3 = ", "
            r8.append(r3)
            java.lang.String r3 = r8.toString()
        L_0x0248:
            r15 = r3
            goto L_0x025e
        L_0x024a:
            r8 = 1
            java.lang.Object[] r10 = new java.lang.Object[r8]
            r8 = 41
            java.lang.Character r8 = java.lang.Character.valueOf(r8)
            r17 = 0
            r10[r17] = r8
            boolean r3 = r3.match(r10)
            if (r3 == 0) goto L_0x025e
            goto L_0x026c
        L_0x025e:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            r8 = r35
            r10 = r36
            goto L_0x01da
        L_0x026a:
            r36 = r10
        L_0x026c:
            java.lang.String[] r3 = r14.cppTypes
            int r3 = r3.length
            if (r5 >= r3) goto L_0x02b5
            java.lang.String[] r3 = r14.cppTypes
            r3 = r3[r5]
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r10 = "arg"
            r8.append(r10)
            r8.append(r5)
            java.lang.String r8 = r8.toString()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r15)
            r10.append(r3)
            r10.append(r9)
            r10.append(r8)
            java.lang.String r3 = r10.toString()
            int r5 = r5 + 1
            java.lang.String[] r8 = r14.cppTypes
            int r8 = r8.length
            if (r5 >= r8) goto L_0x02b3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r3)
            java.lang.String r3 = ", "
            r8.append(r3)
            java.lang.String r3 = r8.toString()
        L_0x02b3:
            r15 = r3
            goto L_0x026c
        L_0x02b5:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r15)
            java.lang.String r5 = ")"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            org.bytedeco.javacpp.tools.Parser r5 = new org.bytedeco.javacpp.tools.Parser
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String[] r10 = r14.cppTypes
            r15 = 0
            r10 = r10[r15]
            r8.append(r10)
            r8.append(r9)
            r8.append(r13)
            r8.append(r3)
            java.lang.String r3 = r8.toString()
            r5.<init>((org.bytedeco.javacpp.tools.Parser) r0, (java.lang.String) r3)
            r26 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r24 = r5
            r25 = r46
            r27 = r11
            org.bytedeco.javacpp.tools.Declarator r3 = r24.declarator(r25, r26, r27, r28, r29, r30, r31)
            r5 = 0
        L_0x02fa:
            java.lang.String[] r8 = r14.cppNames
            int r8 = r8.length
            if (r5 >= r8) goto L_0x0330
            java.lang.String[] r8 = r14.cppNames
            r8 = r8[r5]
            boolean r8 = r13.equals(r8)
            if (r8 == 0) goto L_0x032d
            java.lang.String[] r8 = r14.javaNames
            if (r8 == 0) goto L_0x032d
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r2)
            java.lang.String[] r10 = r14.cppNames
            r13 = 0
            r10 = r10[r13]
            r8.append(r10)
            r8.append(r4)
            java.lang.String[] r10 = r14.javaNames
            r5 = r10[r5]
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            r13 = r5
            goto L_0x0330
        L_0x032d:
            int r5 = r5 + 1
            goto L_0x02fa
        L_0x0330:
            java.util.Iterator r5 = r1.iterator()
            r8 = 0
        L_0x0335:
            boolean r10 = r5.hasNext()
            if (r10 == 0) goto L_0x034b
            java.lang.Object r10 = r5.next()
            org.bytedeco.javacpp.tools.Declarator r10 = (org.bytedeco.javacpp.tools.Declarator) r10
            java.lang.String r15 = r3.signature
            java.lang.String r10 = r10.signature
            boolean r10 = r15.equals(r10)
            r8 = r8 | r10
            goto L_0x0335
        L_0x034b:
            if (r8 != 0) goto L_0x0385
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = r12.text
            r5.append(r8)
            java.lang.String r8 = "public static native "
            r5.append(r8)
            org.bytedeco.javacpp.tools.Type r8 = r3.type
            java.lang.String r8 = r8.annotations
            r5.append(r8)
            org.bytedeco.javacpp.tools.Type r8 = r3.type
            java.lang.String r8 = r8.javaName
            r5.append(r8)
            r5.append(r9)
            r5.append(r13)
            org.bytedeco.javacpp.tools.Parameters r8 = r3.parameters
            java.lang.String r8 = r8.list
            r5.append(r8)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r12.text = r5
            java.lang.String r5 = r3.signature
            r12.signature = r5
            goto L_0x038a
        L_0x0385:
            if (r8 == 0) goto L_0x038a
            if (r11 <= 0) goto L_0x038a
            goto L_0x03a1
        L_0x038a:
            r1.add(r3)
            int r11 = r11 + 1
            r3 = r33
            r5 = r34
            r8 = r35
            r10 = r36
            goto L_0x01c2
        L_0x0399:
            r33 = r3
            r34 = r5
            r35 = r8
            r36 = r10
        L_0x03a1:
            r29 = r6
            r3 = r36
            goto L_0x074b
        L_0x03a7:
            r33 = r3
            r34 = r5
            r35 = r8
            r36 = r10
            goto L_0x03ba
        L_0x03b0:
            r33 = r3
            r34 = r5
            r35 = r8
            r36 = r10
            r23 = r11
        L_0x03ba:
            r32 = r15
            int r1 = r6 + 1
            r3 = r36
            if (r3 <= r1) goto L_0x0749
            if (r14 == 0) goto L_0x03d2
            java.lang.String r5 = r14.cppText
            if (r5 != 0) goto L_0x0749
            java.lang.String[] r5 = r14.cppTypes
            if (r5 == 0) goto L_0x03d2
            java.lang.String[] r5 = r14.cppTypes
            int r5 = r5.length
            r8 = 1
            if (r5 != r8) goto L_0x0749
        L_0x03d2:
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            r5.index = r1
            org.bytedeco.javacpp.tools.Token r5 = new org.bytedeco.javacpp.tools.Token
            r5.<init>()
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            org.bytedeco.javacpp.tools.Token r8 = r8.get()
            r10 = 1
            r44 = r8
            r8 = r5
            r5 = r44
        L_0x03e7:
            org.bytedeco.javacpp.tools.TokenIndexer r11 = r0.tokens
            int r11 = r11.index
            java.lang.String r15 = "const char*"
            java.lang.String r24 = "long"
            java.lang.String r25 = "long long"
            r26 = r10
            java.lang.String r10 = "String"
            r27 = r15
            java.lang.String r15 = "L"
            java.lang.String r28 = "double"
            r29 = r6
            java.lang.String r6 = "int"
            r30 = r7
            if (r11 >= r3) goto L_0x04cb
            r11 = 1
            java.lang.Object[] r7 = new java.lang.Object[r11]
            r18 = 3
            java.lang.Integer r18 = java.lang.Integer.valueOf(r18)
            r17 = 0
            r7[r17] = r18
            boolean r7 = r5.match(r7)
            if (r7 == 0) goto L_0x041d
            java.lang.String r5 = " + "
            r7 = r10
            r8 = r27
            goto L_0x04cf
        L_0x041d:
            java.lang.Object[] r7 = new java.lang.Object[r11]
            r11 = 2
            java.lang.Integer r31 = java.lang.Integer.valueOf(r11)
            r7[r17] = r31
            boolean r7 = r5.match(r7)
            if (r7 == 0) goto L_0x0433
            r5 = r20
            r7 = r28
            r8 = r7
            goto L_0x04cf
        L_0x0433:
            r7 = 1
            java.lang.Object[] r11 = new java.lang.Object[r7]
            java.lang.Integer r36 = java.lang.Integer.valueOf(r7)
            r11[r17] = r36
            boolean r7 = r5.match(r11)
            if (r7 == 0) goto L_0x0452
            java.lang.String r7 = r5.value
            boolean r7 = r7.endsWith(r15)
            if (r7 == 0) goto L_0x0452
            r5 = r20
            r7 = r24
            r8 = r25
            goto L_0x04cf
        L_0x0452:
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r21)
            r7[r17] = r10
            r10 = 62
            java.lang.Character r10 = java.lang.Character.valueOf(r10)
            r11 = 1
            r7[r11] = r10
            boolean r7 = r8.match(r7)
            if (r7 == 0) goto L_0x0481
            java.lang.Object[] r7 = new java.lang.Object[r6]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r21)
            r7[r17] = r8
            java.lang.Character r8 = java.lang.Character.valueOf(r16)
            r7[r11] = r8
            boolean r7 = r5.match(r7)
            if (r7 != 0) goto L_0x047f
            goto L_0x0481
        L_0x047f:
            r8 = 1
            goto L_0x049a
        L_0x0481:
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 123(0x7b, float:1.72E-43)
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            r6[r17] = r7
            r7 = 125(0x7d, float:1.75E-43)
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            r8 = 1
            r6[r8] = r7
            boolean r6 = r5.match(r6)
            if (r6 == 0) goto L_0x049c
        L_0x049a:
            r10 = 0
            goto L_0x04bd
        L_0x049c:
            java.lang.Object[] r6 = new java.lang.Object[r8]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r21)
            r6[r17] = r7
            boolean r6 = r5.match(r6)
            if (r6 == 0) goto L_0x04bb
            org.bytedeco.javacpp.tools.InfoMap r6 = r0.infoMap
            java.lang.String r7 = r5.value
            org.bytedeco.javacpp.tools.Info r6 = r6.getFirst(r7)
            if (r14 != 0) goto L_0x04bb
            if (r6 == 0) goto L_0x04bb
            java.lang.String[] r7 = r6.cppTypes
            if (r7 == 0) goto L_0x04bb
            r14 = r6
        L_0x04bb:
            r10 = r26
        L_0x04bd:
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r0.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.next()
            r8 = r5
            r5 = r6
            r6 = r29
            r7 = r30
            goto L_0x03e7
        L_0x04cb:
            r7 = r6
            r8 = r7
            r5 = r20
        L_0x04cf:
            if (r14 == 0) goto L_0x0568
            java.lang.String[] r11 = r14.cppTypes
            if (r11 == 0) goto L_0x052c
            java.lang.String[] r11 = r14.cppTypes
            int r11 = r11.length
            if (r11 <= 0) goto L_0x052c
            org.bytedeco.javacpp.tools.Parser r11 = new org.bytedeco.javacpp.tools.Parser
            r21 = r5
            java.lang.String[] r5 = r14.cppTypes
            r17 = 0
            r5 = r5[r17]
            r11.<init>((org.bytedeco.javacpp.tools.Parser) r0, (java.lang.String) r5)
            r38 = 0
            r39 = -1
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 1
            r36 = r11
            r37 = r46
            org.bytedeco.javacpp.tools.Declarator r5 = r36.declarator(r37, r38, r39, r40, r41, r42, r43)
            org.bytedeco.javacpp.tools.Type r11 = r5.type
            java.lang.String r11 = r11.javaName
            boolean r11 = r11.equals(r6)
            if (r11 != 0) goto L_0x052e
            org.bytedeco.javacpp.tools.Type r7 = r5.type
            java.lang.String r8 = r7.cppName
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            org.bytedeco.javacpp.tools.Type r11 = r5.type
            java.lang.String r11 = r11.annotations
            r7.append(r11)
            java.lang.String[] r11 = r14.pointerTypes
            if (r11 == 0) goto L_0x051f
            java.lang.String[] r5 = r14.pointerTypes
            r11 = 0
            r5 = r5[r11]
            goto L_0x0523
        L_0x051f:
            org.bytedeco.javacpp.tools.Type r5 = r5.type
            java.lang.String r5 = r5.javaName
        L_0x0523:
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r7 = r5
            goto L_0x052e
        L_0x052c:
            r21 = r5
        L_0x052e:
            r5 = 0
        L_0x052f:
            java.lang.String[] r11 = r14.cppNames
            int r11 = r11.length
            if (r5 >= r11) goto L_0x0565
            java.lang.String[] r11 = r14.cppNames
            r11 = r11[r5]
            boolean r11 = r13.equals(r11)
            if (r11 == 0) goto L_0x0562
            java.lang.String[] r11 = r14.javaNames
            if (r11 == 0) goto L_0x0562
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r2)
            java.lang.String[] r2 = r14.cppNames
            r13 = 0
            r2 = r2[r13]
            r11.append(r2)
            r11.append(r4)
            java.lang.String[] r2 = r14.javaNames
            r2 = r2[r5]
            r11.append(r2)
            java.lang.String r2 = r11.toString()
            r13 = r2
            goto L_0x0565
        L_0x0562:
            int r5 = r5 + 1
            goto L_0x052f
        L_0x0565:
            boolean r2 = r14.translate
            goto L_0x056c
        L_0x0568:
            r21 = r5
            r2 = r26
        L_0x056c:
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            r4.index = r1
            if (r2 == 0) goto L_0x0672
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get()
            r5 = r20
        L_0x057a:
            org.bytedeco.javacpp.tools.TokenIndexer r11 = r0.tokens
            int r11 = r11.index
            if (r11 >= r3) goto L_0x05d7
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r5)
            java.lang.String r5 = r4.spacing
            r11.append(r5)
            java.lang.String r5 = r11.toString()
            boolean r11 = r7.equals(r10)
            if (r11 == 0) goto L_0x05a4
            r11 = 1
            java.lang.Object[] r1 = new java.lang.Object[r11]
            r11 = 0
            r1[r11] = r15
            boolean r1 = r4.match(r1)
            if (r1 == 0) goto L_0x05a4
            goto L_0x05d0
        L_0x05a4:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            r1.append(r4)
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            int r5 = r5.index
            r11 = 1
            int r5 = r5 + r11
            if (r5 >= r3) goto L_0x05c6
            java.lang.String r4 = r4.value
            java.lang.String r4 = r4.trim()
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x05c6
            r4 = r21
            goto L_0x05c8
        L_0x05c6:
            r4 = r20
        L_0x05c8:
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r5 = r1
        L_0x05d0:
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r1.next()
            goto L_0x057a
        L_0x05d7:
            java.lang.String r1 = r0.translate(r5)
            boolean r4 = r7.equals(r6)
            if (r4 == 0) goto L_0x06d0
            java.lang.String r4 = "(String)"
            boolean r4 = r1.contains(r4)
            if (r4 == 0) goto L_0x05ee
            r7 = r10
            r15 = r27
            goto L_0x06d1
        L_0x05ee:
            java.lang.String r4 = "(float)"
            boolean r4 = r1.contains(r4)
            if (r4 != 0) goto L_0x066e
            java.lang.String r4 = "(double)"
            boolean r4 = r1.contains(r4)
            if (r4 == 0) goto L_0x0600
            goto L_0x066e
        L_0x0600:
            java.lang.String r4 = "(long)"
            boolean r4 = r1.contains(r4)
            if (r4 == 0) goto L_0x060e
            r7 = r24
            r15 = r25
            goto L_0x06d1
        L_0x060e:
            java.lang.String r4 = r1.trim()     // Catch:{ NumberFormatException -> 0x0668 }
            long r5 = java.lang.Long.parseLong(r4)     // Catch:{ NumberFormatException -> 0x0668 }
            r10 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r20 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r20 <= 0) goto L_0x064d
            r10 = 32
            long r27 = r5 >>> r10
            r10 = 0
            int r21 = (r27 > r10 ? 1 : (r27 == r10 ? 0 : -1))
            if (r21 != 0) goto L_0x064d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0668 }
            r5.<init>()     // Catch:{ NumberFormatException -> 0x0668 }
            int r6 = r1.length()     // Catch:{ NumberFormatException -> 0x0668 }
            int r10 = r4.length()     // Catch:{ NumberFormatException -> 0x0668 }
            int r6 = r6 - r10
            r10 = 0
            java.lang.String r6 = r1.substring(r10, r6)     // Catch:{ NumberFormatException -> 0x0668 }
            r5.append(r6)     // Catch:{ NumberFormatException -> 0x0668 }
            java.lang.String r6 = "(int)"
            r5.append(r6)     // Catch:{ NumberFormatException -> 0x0668 }
            r5.append(r4)     // Catch:{ NumberFormatException -> 0x0668 }
            r5.append(r15)     // Catch:{ NumberFormatException -> 0x0668 }
            java.lang.String r1 = r5.toString()     // Catch:{ NumberFormatException -> 0x0668 }
            goto L_0x0668
        L_0x064d:
            if (r20 > 0) goto L_0x0656
            r10 = -2147483648(0xffffffff80000000, double:NaN)
            int r4 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r4 >= 0) goto L_0x0668
        L_0x0656:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0665 }
            r4.<init>()     // Catch:{ NumberFormatException -> 0x0665 }
            r4.append(r1)     // Catch:{ NumberFormatException -> 0x0665 }
            r4.append(r15)     // Catch:{ NumberFormatException -> 0x0665 }
            java.lang.String r1 = r4.toString()     // Catch:{ NumberFormatException -> 0x0665 }
        L_0x0665:
            r15 = r25
            goto L_0x066b
        L_0x0668:
            r24 = r7
            r15 = r8
        L_0x066b:
            r7 = r24
            goto L_0x06d1
        L_0x066e:
            r7 = r28
            r15 = r7
            goto L_0x06d1
        L_0x0672:
            if (r14 == 0) goto L_0x0699
            java.lang.String[] r1 = r14.annotations
            if (r1 == 0) goto L_0x0699
            java.lang.String[] r1 = r14.annotations
            int r4 = r1.length
            r5 = 0
        L_0x067c:
            if (r5 >= r4) goto L_0x0699
            r6 = r1[r5]
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = r12.text
            r10.append(r11)
            r10.append(r6)
            r10.append(r9)
            java.lang.String r6 = r10.toString()
            r12.text = r6
            int r5 = r5 + 1
            goto L_0x067c
        L_0x0699:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = r12.text
            r1.append(r4)
            java.lang.String r4 = "public static native @MemberGetter "
            r1.append(r4)
            r1.append(r7)
            r1.append(r9)
            r1.append(r13)
            java.lang.String r4 = "();\n"
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r12.text = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            r1.append(r13)
            java.lang.String r4 = "()"
            r1.append(r4)
            java.lang.String r1 = r1.toString()
        L_0x06d0:
            r15 = r8
        L_0x06d1:
            r4 = 32
            int r4 = r7.lastIndexOf(r4)
            if (r4 < 0) goto L_0x06df
            int r4 = r4 + 1
            java.lang.String r7 = r7.substring(r4)
        L_0x06df:
            int r4 = r1.length()
            if (r4 <= 0) goto L_0x0710
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r12.text
            r4.append(r5)
            java.lang.String r5 = "public static final "
            r4.append(r5)
            r4.append(r7)
            r4.append(r9)
            r4.append(r13)
            java.lang.String r5 = " ="
            r4.append(r5)
            r4.append(r1)
            r1 = r30
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r12.text = r1
        L_0x0710:
            r12.signature = r13
            if (r14 == 0) goto L_0x0720
            java.lang.String[] r1 = r14.cppNames
            java.util.List r1 = java.util.Arrays.asList(r1)
            boolean r1 = r1.contains(r13)
            if (r1 != 0) goto L_0x074b
        L_0x0720:
            org.bytedeco.javacpp.tools.InfoMap r1 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r4 = new org.bytedeco.javacpp.tools.Info
            r5 = 1
            java.lang.String[] r6 = new java.lang.String[r5]
            r8 = 0
            r6[r8] = r13
            r4.<init>((java.lang.String[]) r6)
            org.bytedeco.javacpp.tools.Info r4 = r4.define(r5)
            java.lang.String[] r6 = new java.lang.String[r5]
            r6[r8] = r15
            org.bytedeco.javacpp.tools.Info r4 = r4.cppTypes(r6)
            java.lang.String[] r6 = new java.lang.String[r5]
            r6[r8] = r7
            org.bytedeco.javacpp.tools.Info r4 = r4.pointerTypes(r6)
            org.bytedeco.javacpp.tools.Info r2 = r4.translate(r2)
            r1.put(r2)
            goto L_0x074b
        L_0x0749:
            r29 = r6
        L_0x074b:
            if (r14 == 0) goto L_0x0758
            java.lang.String r1 = r14.javaText
            if (r1 == 0) goto L_0x0758
            java.lang.String r1 = r14.javaText
            r12.text = r1
            r12.signature = r1
            goto L_0x0779
        L_0x0758:
            r1 = r47
            r10 = r3
            r2 = r19
            r9 = r22
            r11 = r23
            r6 = r29
            r15 = r32
            r3 = r33
            r5 = r34
            r8 = r35
            r4 = 1
            r7 = 0
            r14 = 0
            goto L_0x010a
        L_0x0770:
            r33 = r3
            r34 = r5
            r29 = r6
            r3 = r10
            r32 = r15
        L_0x0779:
            r4 = r29
            r1 = r34
            goto L_0x07d2
        L_0x077e:
            r33 = r3
            r34 = r5
            r29 = r6
            r3 = r10
            r32 = r15
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            org.bytedeco.javacpp.tools.Token r1 = org.bytedeco.javacpp.tools.Token.UNDEF
            r4 = 0
            r2[r4] = r1
            r1 = r34
            boolean r2 = r1.match(r2)
            if (r2 == 0) goto L_0x07d0
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            r4 = r29
            r2.index = r4
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.String r2 = r2.value
            org.bytedeco.javacpp.tools.InfoMap r5 = r0.infoMap
            java.util.List r2 = r5.get(r2)
            java.util.Iterator r5 = r2.iterator()
        L_0x07af:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x07d2
            java.lang.Object r6 = r5.next()
            org.bytedeco.javacpp.tools.Info r6 = (org.bytedeco.javacpp.tools.Info) r6
            if (r6 == 0) goto L_0x07c2
            boolean r7 = r6.skip
            if (r7 == 0) goto L_0x07c2
            goto L_0x07d2
        L_0x07c2:
            if (r6 == 0) goto L_0x07af
            java.lang.String r7 = r6.cppText
            if (r7 == 0) goto L_0x07af
            java.lang.String[] r7 = r6.cppTypes
            if (r7 != 0) goto L_0x07af
            r2.remove(r6)
            goto L_0x07d2
        L_0x07d0:
            r4 = r29
        L_0x07d2:
            java.lang.String r2 = r12.text
            int r2 = r2.length()
            if (r2 != 0) goto L_0x086d
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            r2.index = r4
            r2 = r33
            r4 = 10
            int r4 = r2.lastIndexOf(r4)
            r5 = 1
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r12.text
            r5.append(r6)
            java.lang.String r6 = "// "
            r5.append(r6)
            java.lang.String r6 = r2.substring(r4)
            r5.append(r6)
            java.lang.String r6 = "#"
            r5.append(r6)
            java.lang.String r6 = r1.spacing
            r5.append(r6)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            r12.text = r1
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
        L_0x0817:
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            int r5 = r5.index
            if (r5 >= r3) goto L_0x0867
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r12.text
            r5.append(r6)
            r6 = 1
            java.lang.Object[] r7 = new java.lang.Object[r6]
            r6 = 0
            r7[r6] = r32
            boolean r6 = r1.match(r7)
            if (r6 == 0) goto L_0x0838
            java.lang.String r1 = "\n// "
            r8 = r32
            goto L_0x0855
        L_0x0838:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r1.spacing
            r6.append(r7)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = "\n//"
            r8 = r32
            java.lang.String r1 = r1.replace(r8, r7)
            r6.append(r1)
            java.lang.String r1 = r6.toString()
        L_0x0855:
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            r12.text = r1
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.next()
            r32 = r8
            goto L_0x0817
        L_0x0867:
            r1 = 0
            java.lang.String r2 = r2.substring(r1, r4)
            goto L_0x086f
        L_0x086d:
            r2 = r33
        L_0x086f:
            java.lang.String r1 = r12.text
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x0892
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r1.index = r3
            java.lang.String r1 = r45.commentAfter()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r1 = r12.text
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r12.text = r1
        L_0x0892:
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r3 = 0
            r1.raw = r3
            r1 = r47
            r1.spacing = r2
            r1.add((org.bytedeco.javacpp.tools.Declaration) r12)
            r2 = 0
            r1.spacing = r2
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.macro(org.bytedeco.javacpp.tools.Context, org.bytedeco.javacpp.tools.DeclarationList):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean typedef(org.bytedeco.javacpp.tools.Context r21, org.bytedeco.javacpp.tools.DeclarationList r22) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            r10 = r22
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.String r11 = r0.spacing
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            r12 = 1
            java.lang.Object[] r1 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.USING
            r13 = 0
            r1[r13] = r2
            boolean r0 = r0.match(r1)
            r1 = 61
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            r14 = 2
            r2 = 5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            if (r0 == 0) goto L_0x0058
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get(r12)
            java.lang.Object[] r3 = new java.lang.Object[r12]
            r3[r13] = r2
            boolean r0 = r0.match(r3)
            if (r0 == 0) goto L_0x0058
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get(r14)
            java.lang.Object[] r3 = new java.lang.Object[r12]
            r3[r13] = r1
            boolean r0 = r0.match(r3)
            if (r0 == 0) goto L_0x0058
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get(r12)
            java.lang.String r0 = r0.value
            r7 = r0
            goto L_0x0059
        L_0x0058:
            r7 = 0
        L_0x0059:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get()
            java.lang.Object[] r3 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r4 = org.bytedeco.javacpp.tools.Token.TYPEDEF
            r3[r13] = r4
            boolean r0 = r0.match(r3)
            if (r0 != 0) goto L_0x0080
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.get(r12)
            java.lang.Object[] r3 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r4 = org.bytedeco.javacpp.tools.Token.TYPEDEF
            r3[r13] = r4
            boolean r0 = r0.match(r3)
            if (r0 != 0) goto L_0x0080
            if (r7 != 0) goto L_0x0080
            return r13
        L_0x0080:
            org.bytedeco.javacpp.tools.Declaration r0 = new org.bytedeco.javacpp.tools.Declaration
            r0.<init>()
            if (r7 == 0) goto L_0x00a6
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            java.lang.Object[] r3 = new java.lang.Object[r12]
            r3[r13] = r2
            r0.expect(r3)
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            java.lang.Object[] r2 = new java.lang.Object[r12]
            r2[r13] = r1
            r0.expect(r2)
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.next()
        L_0x00a6:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            int r6 = r0.index
            r5 = 0
        L_0x00ab:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r5 >= r0) goto L_0x0400
            org.bytedeco.javacpp.tools.Declaration r4 = new org.bytedeco.javacpp.tools.Declaration
            r4.<init>()
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.index = r6
            r2 = 0
            r3 = -1
            r16 = 0
            r17 = 1
            r18 = 0
            r0 = r20
            r1 = r21
            r15 = r4
            r4 = r16
            r16 = r5
            r19 = r6
            r6 = r17
            r14 = r7
            r7 = r18
            org.bytedeco.javacpp.tools.Declarator r0 = r0.declarator(r1, r2, r3, r4, r5, r6, r7)
            if (r0 != 0) goto L_0x00d9
            goto L_0x0400
        L_0x00d9:
            if (r14 == 0) goto L_0x00dd
            r0.cppName = r14
        L_0x00dd:
            org.bytedeco.javacpp.tools.Attribute r1 = r20.attribute()
            if (r1 != 0) goto L_0x00e8
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            r1.next()
        L_0x00e8:
            org.bytedeco.javacpp.tools.Type r1 = r0.type
            java.lang.String r1 = r1.cppName
            java.lang.String r2 = r0.cppName
            if (r2 != 0) goto L_0x00f3
            r0.cppName = r1
            r2 = r1
        L_0x00f3:
            java.lang.String r3 = r0.javaName
            if (r3 != 0) goto L_0x00fb
            java.lang.String r3 = r0.cppName
            r0.javaName = r3
        L_0x00fb:
            java.lang.String r3 = "::"
            int r4 = r2.lastIndexOf(r3)
            java.lang.String r5 = r9.namespace
            if (r5 == 0) goto L_0x011b
            if (r4 >= 0) goto L_0x011b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r9.namespace
            r4.append(r5)
            r4.append(r3)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
        L_0x011b:
            org.bytedeco.javacpp.tools.InfoMap r3 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r3 = r3.getFirst(r2)
            org.bytedeco.javacpp.tools.Declaration r4 = r0.definition
            java.lang.String r5 = "@ByPtrPtr "
            if (r4 == 0) goto L_0x01b4
            org.bytedeco.javacpp.tools.Declaration r4 = r0.definition
            if (r14 == 0) goto L_0x013f
            java.lang.String r1 = r4.text
            java.lang.String r6 = r4.signature
            java.lang.String r1 = r1.replace(r6, r14)
            r4.text = r1
            org.bytedeco.javacpp.tools.Type r1 = r4.type
            org.bytedeco.javacpp.tools.Type r6 = r4.type
            r6.cppName = r14
            r1.javaName = r14
            r4.signature = r14
        L_0x013f:
            java.lang.String r1 = r0.javaName
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x0165
            java.lang.String r1 = r9.javaName
            if (r1 == 0) goto L_0x0165
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r6 = r9.javaName
            r1.append(r6)
            java.lang.String r6 = "."
            r1.append(r6)
            java.lang.String r6 = r0.javaName
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r0.javaName = r1
        L_0x0165:
            if (r3 == 0) goto L_0x016b
            boolean r1 = r3.skip
            if (r1 != 0) goto L_0x0298
        L_0x016b:
            org.bytedeco.javacpp.tools.Info r1 = new org.bytedeco.javacpp.tools.Info
            if (r3 == 0) goto L_0x017b
            r1.<init>((org.bytedeco.javacpp.tools.Info) r3)
            java.lang.String[] r3 = new java.lang.String[r12]
            r3[r13] = r2
            org.bytedeco.javacpp.tools.Info r1 = r1.cppNames(r3)
            goto L_0x0182
        L_0x017b:
            java.lang.String[] r3 = new java.lang.String[r12]
            r3[r13] = r2
            r1.<init>((java.lang.String[]) r3)
        L_0x0182:
            r3 = r1
            org.bytedeco.javacpp.tools.InfoMap r1 = r8.infoMap
            java.lang.String[] r2 = new java.lang.String[r12]
            java.lang.String r6 = r0.javaName
            r2[r13] = r6
            org.bytedeco.javacpp.tools.Info r2 = r3.valueTypes(r2)
            java.lang.String[] r6 = new java.lang.String[r12]
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            int r15 = r0.indirections
            if (r15 <= 0) goto L_0x019b
            goto L_0x019d
        L_0x019b:
            java.lang.String r5 = ""
        L_0x019d:
            r7.append(r5)
            java.lang.String r0 = r0.javaName
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r6[r13] = r0
            org.bytedeco.javacpp.tools.Info r0 = r2.pointerTypes(r6)
            r1.put(r0)
            goto L_0x0298
        L_0x01b4:
            java.lang.String r4 = "void"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x028a
            if (r3 == 0) goto L_0x01c2
            boolean r1 = r3.skip
            if (r1 != 0) goto L_0x0297
        L_0x01c2:
            java.lang.String r1 = r0.javaName
            java.lang.String r4 = "Pointer"
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0297
            int r1 = r0.indirections
            if (r1 <= 0) goto L_0x0226
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = r15.text
            r1.append(r4)
            java.lang.String r4 = "@Namespace @Name(\"void\") "
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r15.text = r1
            org.bytedeco.javacpp.tools.Info r1 = new org.bytedeco.javacpp.tools.Info
            if (r3 == 0) goto L_0x01f5
            r1.<init>((org.bytedeco.javacpp.tools.Info) r3)
            java.lang.String[] r3 = new java.lang.String[r12]
            r3[r13] = r2
            org.bytedeco.javacpp.tools.Info r1 = r1.cppNames(r3)
            goto L_0x01fc
        L_0x01f5:
            java.lang.String[] r3 = new java.lang.String[r12]
            r3[r13] = r2
            r1.<init>((java.lang.String[]) r3)
        L_0x01fc:
            r3 = r1
            org.bytedeco.javacpp.tools.InfoMap r1 = r8.infoMap
            java.lang.String[] r2 = new java.lang.String[r12]
            java.lang.String r4 = r0.javaName
            r2[r13] = r4
            org.bytedeco.javacpp.tools.Info r2 = r3.valueTypes(r2)
            java.lang.String[] r4 = new java.lang.String[r12]
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r5 = r0.javaName
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r4[r13] = r5
            org.bytedeco.javacpp.tools.Info r2 = r2.pointerTypes(r4)
            r1.put(r2)
            goto L_0x024d
        L_0x0226:
            java.lang.String r1 = r9.namespace
            if (r1 == 0) goto L_0x024d
            java.lang.String r1 = r9.javaName
            if (r1 != 0) goto L_0x024d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r15.text
            r1.append(r2)
            java.lang.String r2 = "@Namespace(\""
            r1.append(r2)
            java.lang.String r2 = r9.namespace
            r1.append(r2)
            java.lang.String r2 = "\") "
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r15.text = r1
        L_0x024d:
            org.bytedeco.javacpp.tools.Type r1 = new org.bytedeco.javacpp.tools.Type
            java.lang.String r2 = r0.javaName
            r1.<init>(r2)
            r15.type = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r15.text
            r1.append(r2)
            java.lang.String r2 = "@Opaque public static class "
            r1.append(r2)
            java.lang.String r2 = r0.javaName
            r1.append(r2)
            java.lang.String r2 = " extends Pointer {\n    /** Empty constructor. Calls {@code super((Pointer)null)}. */\n    public "
            r1.append(r2)
            java.lang.String r2 = r0.javaName
            r1.append(r2)
            java.lang.String r2 = "() { super((Pointer)null); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public "
            r1.append(r2)
            java.lang.String r0 = r0.javaName
            r1.append(r0)
            java.lang.String r0 = "(Pointer p) { super(p); }\n}"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r15.text = r0
            goto L_0x0297
        L_0x028a:
            org.bytedeco.javacpp.tools.InfoMap r3 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r3 = r3.getFirst(r1)
            if (r3 == 0) goto L_0x029b
            boolean r4 = r3.skip
            if (r4 != 0) goto L_0x0297
            goto L_0x029b
        L_0x0297:
            r4 = r15
        L_0x0298:
            r5 = 2
            goto L_0x03cc
        L_0x029b:
            if (r3 == 0) goto L_0x02ab
            org.bytedeco.javacpp.tools.Info r4 = new org.bytedeco.javacpp.tools.Info
            r4.<init>((org.bytedeco.javacpp.tools.Info) r3)
            java.lang.String[] r3 = new java.lang.String[r12]
            r3[r13] = r2
            org.bytedeco.javacpp.tools.Info r3 = r4.cppNames(r3)
            goto L_0x02b4
        L_0x02ab:
            org.bytedeco.javacpp.tools.Info r3 = new org.bytedeco.javacpp.tools.Info
            java.lang.String[] r4 = new java.lang.String[r12]
            r4[r13] = r2
            r3.<init>((java.lang.String[]) r4)
        L_0x02b4:
            java.lang.String[] r4 = r3.cppTypes
            if (r4 != 0) goto L_0x0343
            java.lang.String[] r4 = r3.annotations
            if (r4 == 0) goto L_0x0343
            org.bytedeco.javacpp.tools.Type r4 = r0.type
            boolean r4 = r4.constValue
            if (r4 == 0) goto L_0x02da
            java.lang.String r4 = "const "
            boolean r5 = r1.startsWith(r4)
            if (r5 != 0) goto L_0x02da
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            r5.append(r1)
            java.lang.String r4 = r5.toString()
            goto L_0x02db
        L_0x02da:
            r4 = r1
        L_0x02db:
            org.bytedeco.javacpp.tools.Type r5 = r0.type
            int r5 = r5.indirections
            if (r5 <= 0) goto L_0x02fc
            r5 = 0
        L_0x02e2:
            org.bytedeco.javacpp.tools.Type r6 = r0.type
            int r6 = r6.indirections
            if (r5 >= r6) goto L_0x02fc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r4)
            java.lang.String r4 = "*"
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            int r5 = r5 + 1
            goto L_0x02e2
        L_0x02fc:
            org.bytedeco.javacpp.tools.Type r5 = r0.type
            boolean r5 = r5.reference
            if (r5 == 0) goto L_0x0313
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r4)
            java.lang.String r4 = "&"
            r5.append(r4)
            java.lang.String r4 = r5.toString()
        L_0x0313:
            org.bytedeco.javacpp.tools.Type r5 = r0.type
            boolean r5 = r5.constPointer
            if (r5 == 0) goto L_0x0330
            java.lang.String r5 = " const"
            boolean r6 = r4.endsWith(r5)
            if (r6 != 0) goto L_0x0330
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r4)
            r6.append(r5)
            java.lang.String r4 = r6.toString()
        L_0x0330:
            r5 = 2
            java.lang.String[] r6 = new java.lang.String[r5]
            r6[r13] = r2
            r6[r12] = r4
            org.bytedeco.javacpp.tools.Info r2 = r3.cppNames(r6)
            java.lang.String[] r6 = new java.lang.String[r12]
            r6[r13] = r4
            r2.cppTypes(r6)
            goto L_0x0344
        L_0x0343:
            r5 = 2
        L_0x0344:
            java.lang.String[] r2 = r3.valueTypes
            if (r2 != 0) goto L_0x0365
            int r2 = r0.indirections
            if (r2 <= 0) goto L_0x0365
            java.lang.String[] r2 = r3.pointerTypes
            if (r2 == 0) goto L_0x0353
            java.lang.String[] r1 = r3.pointerTypes
            goto L_0x0358
        L_0x0353:
            java.lang.String[] r2 = new java.lang.String[r12]
            r2[r13] = r1
            r1 = r2
        L_0x0358:
            r3.valueTypes(r1)
            java.lang.String r1 = "PointerPointer"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            r3.pointerTypes(r1)
            goto L_0x0370
        L_0x0365:
            java.lang.String[] r2 = r3.pointerTypes
            if (r2 != 0) goto L_0x0370
            java.lang.String[] r2 = new java.lang.String[r12]
            r2[r13] = r1
            r3.pointerTypes(r2)
        L_0x0370:
            java.lang.String[] r1 = r3.annotations
            if (r1 != 0) goto L_0x03c6
            org.bytedeco.javacpp.tools.Type r1 = r0.type
            java.lang.String r1 = r1.annotations
            if (r1 == 0) goto L_0x03b8
            org.bytedeco.javacpp.tools.Type r1 = r0.type
            java.lang.String r1 = r1.annotations
            int r1 = r1.length()
            if (r1 <= 0) goto L_0x03b8
            org.bytedeco.javacpp.tools.Type r1 = r0.type
            java.lang.String r1 = r1.annotations
            java.lang.String r2 = "@ByVal "
            boolean r1 = r1.startsWith(r2)
            if (r1 != 0) goto L_0x03b8
            org.bytedeco.javacpp.tools.Type r1 = r0.type
            java.lang.String r1 = r1.annotations
            java.lang.String r2 = "@Cast("
            boolean r1 = r1.startsWith(r2)
            if (r1 != 0) goto L_0x03b8
            org.bytedeco.javacpp.tools.Type r1 = r0.type
            java.lang.String r1 = r1.annotations
            java.lang.String r2 = "@Const "
            boolean r1 = r1.startsWith(r2)
            if (r1 != 0) goto L_0x03b8
            java.lang.String[] r1 = new java.lang.String[r12]
            org.bytedeco.javacpp.tools.Type r0 = r0.type
            java.lang.String r0 = r0.annotations
            java.lang.String r0 = r0.trim()
            r1[r13] = r0
            r3.annotations(r1)
            goto L_0x03c6
        L_0x03b8:
            java.lang.String r0 = r0.cppName
            java.lang.String[] r1 = r3.pointerTypes
            r1 = r1[r13]
            boolean r0 = r0.equals(r1)
            r0 = r0 ^ r12
            r3.cast(r0)
        L_0x03c6:
            org.bytedeco.javacpp.tools.InfoMap r0 = r8.infoMap
            r0.put(r3)
            r4 = r15
        L_0x03cc:
            if (r3 == 0) goto L_0x03d8
            java.lang.String r0 = r3.javaText
            if (r0 == 0) goto L_0x03d8
            java.lang.String r0 = r3.javaText
            r4.text = r0
            r4.signature = r0
        L_0x03d8:
            java.lang.String r0 = r20.commentAfter()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = r4.text
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.text = r0
            r10.spacing = r11
            r10.add((org.bytedeco.javacpp.tools.Declaration) r4)
            r0 = 0
            r10.spacing = r0
            int r1 = r16 + 1
            r5 = r1
            r7 = r14
            r6 = r19
            r14 = 2
            goto L_0x00ab
        L_0x0400:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.typedef(org.bytedeco.javacpp.tools.Context, org.bytedeco.javacpp.tools.DeclarationList):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean using(Context context, DeclarationList declarationList) throws ParserException {
        if (!this.tokens.get().match(Token.USING)) {
            return false;
        }
        String str = this.tokens.get().spacing;
        boolean match = this.tokens.get(1).match(Token.NAMESPACE);
        Declarator declarator = declarator(context, (String) null, -1, false, 0, true, false);
        this.tokens.next();
        List<String> list = context.usingList;
        StringBuilder sb = new StringBuilder();
        sb.append(declarator.type.cppName);
        sb.append(match ? "::" : "");
        list.add(sb.toString());
        Declaration declaration = new Declaration();
        if (declarator.definition != null) {
            declaration = declarator.definition;
        }
        String str2 = declarator.type.cppName;
        String str3 = context.baseType;
        int lastIndexOf = str2.lastIndexOf(60);
        int lastIndexOf2 = str3 != null ? str3.lastIndexOf(60) : -1;
        if (lastIndexOf < 0 && lastIndexOf2 >= 0 && str2.startsWith(str3.substring(0, lastIndexOf2))) {
            str2 = str3 + str2.substring(lastIndexOf2);
        }
        Info first = this.infoMap.getFirst(str2);
        if (!(context.inaccessible || first == null || first.javaText == null)) {
            String str4 = first.javaText;
            declaration.text = str4;
            declaration.signature = str4;
            declaration.declarator = declarator;
        }
        declaration.text = commentAfter() + declaration.text;
        declarationList.spacing = str;
        declarationList.add(declaration);
        declarationList.spacing = null;
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: org.bytedeco.javacpp.tools.Type} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00b8, code lost:
        r16 = r4;
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x0a95, code lost:
        if (r3.equals("void") != false) goto L_0x0a9a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0131, code lost:
        if (r8.tokens.get(2).match(';') == false) goto L_0x0133;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x059a  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x05ce  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0653  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0672  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0687  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0692  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0820  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x082d  */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x0838  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x083e  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x085a  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0864  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x08d1  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x08d3  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x08f0  */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x0ab1  */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x0ab3  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0ac2  */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x0ac4  */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x0b04  */
    /* JADX WARNING: Removed duplicated region for block: B:375:0x0b06  */
    /* JADX WARNING: Removed duplicated region for block: B:381:0x0b30  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x0b33  */
    /* JADX WARNING: Removed duplicated region for block: B:384:0x0b37  */
    /* JADX WARNING: Removed duplicated region for block: B:438:0x0d05  */
    /* JADX WARNING: Removed duplicated region for block: B:442:0x0d15  */
    /* JADX WARNING: Removed duplicated region for block: B:464:0x0d93  */
    /* JADX WARNING: Removed duplicated region for block: B:488:0x0dff  */
    /* JADX WARNING: Removed duplicated region for block: B:489:0x0e05  */
    /* JADX WARNING: Removed duplicated region for block: B:492:0x0e0e  */
    /* JADX WARNING: Removed duplicated region for block: B:495:0x0e30  */
    /* JADX WARNING: Removed duplicated region for block: B:497:0x0e5a  */
    /* JADX WARNING: Removed duplicated region for block: B:500:0x0e8e  */
    /* JADX WARNING: Removed duplicated region for block: B:507:0x0ebe  */
    /* JADX WARNING: Removed duplicated region for block: B:508:0x0ec5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:520:0x00b8 A[EDGE_INSN: B:520:0x00b8->B:21:0x00b8 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:528:0x024c A[EDGE_INSN: B:528:0x024c->B:72:0x024c ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:542:0x05c2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:546:0x06e3 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:553:0x0b20 A[EDGE_INSN: B:553:0x0b20->B:377:0x0b20 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:577:0x0eb1 A[EDGE_INSN: B:577:0x0eb1->B:503:0x0eb1 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0246 A[LOOP:2: B:69:0x0229->B:71:0x0246, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean group(org.bytedeco.javacpp.tools.Context r48, org.bytedeco.javacpp.tools.DeclarationList r49) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r47 = this;
            r8 = r47
            r9 = r48
            r10 = r49
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            int r0 = r0.index
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
            java.lang.String r11 = r1.spacing
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
            r12 = 1
            java.lang.Object[] r2 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r3 = org.bytedeco.javacpp.tools.Token.TYPEDEF
            r13 = 0
            r2[r13] = r3
            boolean r1 = r1.match(r2)
            if (r1 != 0) goto L_0x003b
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get(r12)
            java.lang.Object[] r2 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r3 = org.bytedeco.javacpp.tools.Token.TYPEDEF
            r2[r13] = r3
            boolean r1 = r1.match(r2)
            if (r1 == 0) goto L_0x0039
            goto L_0x003b
        L_0x0039:
            r1 = 0
            goto L_0x003c
        L_0x003b:
            r1 = 1
        L_0x003c:
            org.bytedeco.javacpp.tools.Context r14 = new org.bytedeco.javacpp.tools.Context
            r14.<init>(r9)
            r15 = 5
            org.bytedeco.javacpp.tools.Token[] r7 = new org.bytedeco.javacpp.tools.Token[r15]
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.CLASS
            r7[r13] = r2
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.INTERFACE
            r7[r12] = r2
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.__INTERFACE
            r6 = 2
            r7[r6] = r2
            org.bytedeco.javacpp.tools.Token r2 = org.bytedeco.javacpp.tools.Token.STRUCT
            r3 = 3
            r7[r3] = r2
            r2 = 4
            org.bytedeco.javacpp.tools.Token r4 = org.bytedeco.javacpp.tools.Token.UNION
            r7[r2] = r4
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            r4 = 0
        L_0x0062:
            java.lang.Object[] r5 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r16 = org.bytedeco.javacpp.tools.Token.EOF
            r5[r13] = r16
            boolean r5 = r2.match(r5)
            if (r5 != 0) goto L_0x00b8
            boolean r5 = r2.match(r7)
            if (r5 == 0) goto L_0x0084
            java.lang.Object[] r5 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r16 = org.bytedeco.javacpp.tools.Token.CLASS
            r5[r13] = r16
            boolean r2 = r2.match(r5)
            r14.inaccessible = r2
            r16 = r4
            r2 = 1
            goto L_0x00bb
        L_0x0084:
            java.lang.Object[] r5 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r16 = org.bytedeco.javacpp.tools.Token.FRIEND
            r5[r13] = r16
            boolean r5 = r2.match(r5)
            if (r5 == 0) goto L_0x00a2
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get(r12)
            boolean r2 = r2.match(r7)
            if (r2 != 0) goto L_0x00a0
            r2 = 1
            r16 = 1
            goto L_0x00bb
        L_0x00a0:
            r4 = 1
            goto L_0x00b1
        L_0x00a2:
            java.lang.Object[] r5 = new java.lang.Object[r12]
            java.lang.Integer r16 = java.lang.Integer.valueOf(r15)
            r5[r13] = r16
            boolean r2 = r2.match(r5)
            if (r2 != 0) goto L_0x00b1
            goto L_0x00b8
        L_0x00b1:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            goto L_0x0062
        L_0x00b8:
            r16 = r4
            r2 = 0
        L_0x00bb:
            if (r2 == 0) goto L_0x0ede
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            java.lang.Object[] r4 = new java.lang.Object[r3]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r15)
            r4[r13] = r5
            r5 = 123(0x7b, float:1.72E-43)
            java.lang.Character r17 = java.lang.Character.valueOf(r5)
            r4[r12] = r17
            r17 = r7
            java.lang.String r7 = "::"
            r4[r6] = r7
            boolean r2 = r2.match(r4)
            if (r2 != 0) goto L_0x00e1
            goto L_0x0ede
        L_0x00e1:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.Object[] r4 = new java.lang.Object[r12]
            java.lang.Character r18 = java.lang.Character.valueOf(r5)
            r4[r13] = r18
            boolean r2 = r2.match(r4)
            r18 = 59
            if (r2 != 0) goto L_0x0138
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get(r12)
            java.lang.Object[] r4 = new java.lang.Object[r12]
            java.lang.Integer r19 = java.lang.Integer.valueOf(r15)
            r4[r13] = r19
            boolean r2 = r2.match(r4)
            if (r2 == 0) goto L_0x0138
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get(r12)
            java.lang.Object[] r4 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r19 = org.bytedeco.javacpp.tools.Token.FINAL
            r4[r13] = r19
            boolean r2 = r2.match(r4)
            if (r2 != 0) goto L_0x0138
            if (r1 != 0) goto L_0x0133
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get(r6)
            java.lang.Object[] r4 = new java.lang.Object[r12]
            java.lang.Character r19 = java.lang.Character.valueOf(r18)
            r4[r13] = r19
            boolean r2 = r2.match(r4)
            if (r2 != 0) goto L_0x0138
        L_0x0133:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            r2.next()
        L_0x0138:
            org.bytedeco.javacpp.tools.Type r4 = r8.type(r9, r12)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r19 = r7
            org.bytedeco.javacpp.tools.Declaration r7 = new org.bytedeco.javacpp.tools.Declaration
            r7.<init>()
            java.lang.String r15 = r4.annotations
            r7.text = r15
            java.lang.String r15 = r4.javaName
            if (r1 != 0) goto L_0x015a
            java.lang.String r5 = r4.cppName
            int r5 = r5.length()
            if (r5 != 0) goto L_0x015a
            r5 = 1
            goto L_0x015b
        L_0x015a:
            r5 = 0
        L_0x015b:
            java.lang.String r6 = r4.cppName
            int r6 = r6.length()
            r23 = 44
            if (r6 <= 0) goto L_0x021f
            org.bytedeco.javacpp.tools.TokenIndexer r6 = r8.tokens
            org.bytedeco.javacpp.tools.Token r6 = r6.get()
            java.lang.Object[] r3 = new java.lang.Object[r12]
            r25 = 58
            java.lang.Character r25 = java.lang.Character.valueOf(r25)
            r3[r13] = r25
            boolean r3 = r6.match(r3)
            if (r3 == 0) goto L_0x021f
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            r25 = r5
            r6 = 0
        L_0x0184:
            java.lang.Object[] r5 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r26 = org.bytedeco.javacpp.tools.Token.EOF
            r5[r13] = r26
            boolean r5 = r3.match(r5)
            if (r5 != 0) goto L_0x021c
            boolean r5 = r14.inaccessible
            r5 = r5 ^ r12
            r26 = r5
            java.lang.Object[] r5 = new java.lang.Object[r12]
            org.bytedeco.javacpp.tools.Token r27 = org.bytedeco.javacpp.tools.Token.VIRTUAL
            r5[r13] = r27
            boolean r5 = r3.match(r5)
            if (r5 == 0) goto L_0x01a2
            goto L_0x0212
        L_0x01a2:
            r5 = 3
            java.lang.Object[] r12 = new java.lang.Object[r5]
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.PRIVATE
            r12[r13] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.PROTECTED
            r13 = 1
            r12[r13] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.PUBLIC
            r22 = 2
            r12[r22] = r5
            boolean r5 = r3.match(r12)
            if (r5 == 0) goto L_0x01cb
            java.lang.Object[] r5 = new java.lang.Object[r13]
            org.bytedeco.javacpp.tools.Token r12 = org.bytedeco.javacpp.tools.Token.PUBLIC
            r13 = 0
            r5[r13] = r12
            boolean r5 = r3.match(r5)
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            r3.next()
            goto L_0x01cd
        L_0x01cb:
            r5 = r26
        L_0x01cd:
            org.bytedeco.javacpp.tools.Type r3 = r47.type(r48)
            org.bytedeco.javacpp.tools.InfoMap r12 = r8.infoMap
            java.lang.String r13 = r3.cppName
            org.bytedeco.javacpp.tools.Info r12 = r12.getFirst(r13)
            if (r12 == 0) goto L_0x01e0
            boolean r12 = r12.skip
            if (r12 == 0) goto L_0x01e0
            r6 = 1
        L_0x01e0:
            if (r5 == 0) goto L_0x01e5
            r2.add(r3)
        L_0x01e5:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r5 = 2
            java.lang.Object[] r12 = new java.lang.Object[r5]
            java.lang.Character r5 = java.lang.Character.valueOf(r23)
            r13 = 0
            r12[r13] = r5
            r5 = 123(0x7b, float:1.72E-43)
            java.lang.Character r21 = java.lang.Character.valueOf(r5)
            r13 = 1
            r12[r13] = r21
            org.bytedeco.javacpp.tools.Token r3 = r3.expect(r12)
            java.lang.Object[] r12 = new java.lang.Object[r13]
            java.lang.Character r13 = java.lang.Character.valueOf(r5)
            r5 = 0
            r12[r5] = r13
            boolean r3 = r3.match(r12)
            if (r3 == 0) goto L_0x0212
            goto L_0x021c
        L_0x0212:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            r12 = 1
            r13 = 0
            goto L_0x0184
        L_0x021c:
            r12 = r6
            r13 = 1
            goto L_0x0223
        L_0x021f:
            r25 = r5
            r12 = 0
            r13 = 0
        L_0x0223:
            if (r1 == 0) goto L_0x024c
            int r3 = r4.indirections
            if (r3 <= 0) goto L_0x024c
        L_0x0229:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.Character r5 = java.lang.Character.valueOf(r18)
            r26 = 0
            r6[r26] = r5
            org.bytedeco.javacpp.tools.Token r5 = org.bytedeco.javacpp.tools.Token.EOF
            r26 = 1
            r6[r26] = r5
            boolean r3 = r3.match(r6)
            if (r3 != 0) goto L_0x024c
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            r3.next()
            goto L_0x0229
        L_0x024c:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 123(0x7b, float:1.72E-43)
            java.lang.Character r21 = java.lang.Character.valueOf(r6)
            r24 = 0
            r5[r24] = r21
            java.lang.Character r21 = java.lang.Character.valueOf(r23)
            r23 = 1
            r5[r23] = r21
            java.lang.Character r21 = java.lang.Character.valueOf(r18)
            r22 = 2
            r5[r22] = r21
            boolean r3 = r3.match(r5)
            if (r3 != 0) goto L_0x027a
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            r1.index = r0
            return r24
        L_0x027a:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            int r5 = r0.index
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.lang.String r0 = r4.cppName
            java.lang.String r21 = r47.body()
            if (r21 == 0) goto L_0x0295
            int r21 = r21.length()
            if (r21 <= 0) goto L_0x0295
            r6 = 1
            r21 = 1
            goto L_0x0298
        L_0x0295:
            r6 = 1
            r21 = 0
        L_0x0298:
            org.bytedeco.javacpp.tools.Attribute r24 = r8.attribute(r6)
            r6 = r24
        L_0x029e:
            r24 = r2
            if (r6 == 0) goto L_0x02c7
            boolean r2 = r6.annotation
            if (r2 == 0) goto L_0x02c7
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r26 = r3
            java.lang.String r3 = r4.annotations
            r2.append(r3)
            java.lang.String r3 = r6.javaName
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r4.annotations = r2
            r2 = 1
            org.bytedeco.javacpp.tools.Attribute r6 = r8.attribute(r2)
            r2 = r24
            r3 = r26
            goto L_0x029e
        L_0x02c7:
            r26 = r3
            r2 = 1
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r8.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.get()
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.Character r2 = java.lang.Character.valueOf(r18)
            r28 = 0
            r6[r28] = r2
            boolean r2 = r3.match(r6)
            java.lang.String r3 = " "
            if (r2 != 0) goto L_0x04a7
            if (r1 == 0) goto L_0x0408
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.get()
            r2 = 2
        L_0x02eb:
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.Character r29 = java.lang.Character.valueOf(r18)
            r6[r28] = r29
            org.bytedeco.javacpp.tools.Token r29 = org.bytedeco.javacpp.tools.Token.EOF
            r2 = 1
            r6[r2] = r29
            boolean r6 = r1.match(r6)
            if (r6 != 0) goto L_0x03d7
            r29 = r5
            r6 = 0
        L_0x0301:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            r27 = 42
            java.lang.Character r27 = java.lang.Character.valueOf(r27)
            r5[r28] = r27
            boolean r5 = r1.match(r5)
            if (r5 == 0) goto L_0x031a
            int r6 = r6 + 1
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r1.next()
            goto L_0x0301
        L_0x031a:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            r20 = 5
            java.lang.Integer r27 = java.lang.Integer.valueOf(r20)
            r5[r28] = r27
            boolean r5 = r1.match(r5)
            if (r5 == 0) goto L_0x03c2
            java.lang.String r5 = r1.value
            if (r6 <= 0) goto L_0x0358
            org.bytedeco.javacpp.tools.InfoMap r1 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r6 = new org.bytedeco.javacpp.tools.Info
            r31 = r13
            java.lang.String[] r13 = new java.lang.String[r2]
            r13[r28] = r5
            r6.<init>((java.lang.String[]) r13)
            org.bytedeco.javacpp.tools.Info r5 = r6.cast()
            java.lang.String[] r6 = new java.lang.String[r2]
            r6[r28] = r15
            org.bytedeco.javacpp.tools.Info r2 = r5.valueTypes(r6)
            java.lang.String r5 = "PointerPointer"
            java.lang.String[] r5 = new java.lang.String[]{r5}
            org.bytedeco.javacpp.tools.Info r2 = r2.pointerTypes(r5)
            r1.put(r2)
            r32 = r0
            goto L_0x03c6
        L_0x0358:
            r31 = r13
            java.lang.String r2 = r4.cppName
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x039d
            java.lang.String r15 = r1.value
            r4.cppName = r15
            r4.javaName = r15
            org.bytedeco.javacpp.tools.InfoMap r1 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r1 = r1.getFirst(r15)
            if (r1 == 0) goto L_0x039d
            java.lang.String[] r2 = r1.annotations
            if (r2 == 0) goto L_0x039d
            java.lang.String[] r1 = r1.annotations
            int r2 = r1.length
            r6 = 0
        L_0x0378:
            if (r6 >= r2) goto L_0x039d
            r13 = r1[r6]
            r32 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r33 = r1
            java.lang.String r1 = r7.text
            r0.append(r1)
            r0.append(r13)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            r7.text = r0
            int r6 = r6 + 1
            r0 = r32
            r1 = r33
            goto L_0x0378
        L_0x039d:
            r32 = r0
            boolean r0 = r5.equals(r15)
            if (r0 != 0) goto L_0x03c6
            org.bytedeco.javacpp.tools.InfoMap r0 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r1 = new org.bytedeco.javacpp.tools.Info
            r2 = 1
            java.lang.String[] r6 = new java.lang.String[r2]
            r13 = 0
            r6[r13] = r5
            r1.<init>((java.lang.String[]) r6)
            org.bytedeco.javacpp.tools.Info r1 = r1.cast()
            java.lang.String[] r5 = new java.lang.String[r2]
            r5[r13] = r15
            org.bytedeco.javacpp.tools.Info r1 = r1.pointerTypes(r5)
            r0.put(r1)
            goto L_0x03c6
        L_0x03c2:
            r32 = r0
            r31 = r13
        L_0x03c6:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            org.bytedeco.javacpp.tools.Token r1 = r0.next()
            r5 = r29
            r13 = r31
            r0 = r32
            r2 = 2
            r28 = 0
            goto L_0x02eb
        L_0x03d7:
            r32 = r0
            r29 = r5
            r31 = r13
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r7.text
            r0.append(r2)
            java.lang.String r1 = r1.spacing
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r7.text = r0
            r38 = r3
            r2 = r4
            r13 = r7
            r5 = r25
            r1 = r26
            r40 = r29
            r36 = r32
            r0 = 10
            r23 = 123(0x7b, float:1.72E-43)
            r26 = r15
            r15 = r19
            goto L_0x04bd
        L_0x0408:
            r32 = r0
            r29 = r5
            r31 = r13
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            int r0 = r0.index
            r1 = 1
            int r13 = r0 + -1
            r6 = 0
        L_0x0416:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r6 >= r0) goto L_0x0471
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r8.tokens
            r0.index = r13
            r2 = 0
            r5 = -1
            r33 = 0
            r34 = 0
            r35 = 1
            r1 = r32
            r0 = r47
            r36 = r1
            r1 = r48
            r30 = 2
            r38 = r3
            r37 = r26
            r3 = r5
            r5 = r4
            r4 = r33
            r39 = r5
            r40 = r29
            r23 = 123(0x7b, float:1.72E-43)
            r5 = r6
            r22 = r6
            r6 = r34
            r26 = r15
            r15 = r19
            r19 = r13
            r13 = r7
            r7 = r35
            org.bytedeco.javacpp.tools.Declarator r0 = r0.declarator(r1, r2, r3, r4, r5, r6, r7)
            if (r0 != 0) goto L_0x0456
            r1 = r37
            goto L_0x0482
        L_0x0456:
            r1 = r37
            r1.add(r0)
            int r6 = r22 + 1
            r7 = r13
            r13 = r19
            r32 = r36
            r3 = r38
            r4 = r39
            r29 = r40
            r25 = 1
            r19 = r15
            r15 = r26
            r26 = r1
            goto L_0x0416
        L_0x0471:
            r38 = r3
            r39 = r4
            r13 = r7
            r1 = r26
            r40 = r29
            r36 = r32
            r23 = 123(0x7b, float:1.72E-43)
            r26 = r15
            r15 = r19
        L_0x0482:
            r0 = 10
            int r2 = r11.lastIndexOf(r0)
            if (r2 < 0) goto L_0x04a2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r13.text
            r3.append(r4)
            r4 = 0
            java.lang.String r2 = r11.substring(r4, r2)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r13.text = r2
        L_0x04a2:
            r5 = r25
            r2 = r39
            goto L_0x04bd
        L_0x04a7:
            r36 = r0
            r38 = r3
            r40 = r5
            r31 = r13
            r1 = r26
            r0 = 10
            r23 = 123(0x7b, float:1.72E-43)
            r13 = r7
            r26 = r15
            r15 = r19
            r2 = r4
            r5 = r25
        L_0x04bd:
            java.lang.String r3 = r2.cppName
            int r3 = r3.lastIndexOf(r15)
            java.lang.String r4 = r9.namespace
            if (r4 == 0) goto L_0x04f8
            if (r3 >= 0) goto L_0x04f8
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = r9.namespace
            r4.append(r6)
            r4.append(r15)
            java.lang.String r6 = r2.cppName
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r2.cppName = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = r9.namespace
            r4.append(r6)
            r4.append(r15)
            r6 = r36
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            goto L_0x04fb
        L_0x04f8:
            r6 = r36
            r4 = r6
        L_0x04fb:
            org.bytedeco.javacpp.tools.InfoMap r6 = r8.infoMap
            java.lang.String r7 = r2.cppName
            org.bytedeco.javacpp.tools.Info r6 = r6.getFirst(r7)
            java.lang.String r7 = ""
            if (r6 == 0) goto L_0x050b
            java.lang.String r0 = r6.base
            if (r0 != 0) goto L_0x050d
        L_0x050b:
            if (r12 != 0) goto L_0x0ed4
        L_0x050d:
            if (r6 == 0) goto L_0x0515
            boolean r0 = r6.skip
            if (r0 == 0) goto L_0x0515
            goto L_0x0ed4
        L_0x0515:
            java.lang.String r0 = "."
            if (r6 == 0) goto L_0x053c
            java.lang.String[] r12 = r6.pointerTypes
            if (r12 == 0) goto L_0x053c
            java.lang.String[] r12 = r6.pointerTypes
            int r12 = r12.length
            if (r12 <= 0) goto L_0x053c
            java.lang.String r12 = r9.constName
            if (r12 == 0) goto L_0x0529
            java.lang.String r12 = r9.constName
            goto L_0x052f
        L_0x0529:
            java.lang.String[] r12 = r6.pointerTypes
            r19 = 0
            r12 = r12[r19]
        L_0x052f:
            r2.javaName = r12
            java.lang.String r12 = r2.javaName
            java.lang.String r26 = r9.shorten(r12)
        L_0x0537:
            r19 = r0
            r37 = r1
            goto L_0x0587
        L_0x053c:
            if (r6 != 0) goto L_0x0537
            if (r16 != 0) goto L_0x0537
            java.lang.String r6 = r2.javaName
            int r6 = r6.length()
            if (r6 <= 0) goto L_0x0564
            java.lang.String r6 = r9.javaName
            if (r6 == 0) goto L_0x0564
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r12 = r9.javaName
            r6.append(r12)
            r6.append(r0)
            java.lang.String r12 = r2.javaName
            r6.append(r12)
            java.lang.String r6 = r6.toString()
            r2.javaName = r6
        L_0x0564:
            org.bytedeco.javacpp.tools.InfoMap r6 = r8.infoMap
            org.bytedeco.javacpp.tools.Info r12 = new org.bytedeco.javacpp.tools.Info
            r19 = r0
            r37 = r1
            r0 = 1
            java.lang.String[] r1 = new java.lang.String[r0]
            java.lang.String r0 = r2.cppName
            r22 = 0
            r1[r22] = r0
            r12.<init>((java.lang.String[]) r1)
            r0 = 1
            java.lang.String[] r1 = new java.lang.String[r0]
            java.lang.String r0 = r2.javaName
            r1[r22] = r0
            org.bytedeco.javacpp.tools.Info r0 = r12.pointerTypes(r1)
            r6.put(r0)
            r6 = r0
        L_0x0587:
            r0 = r26
            org.bytedeco.javacpp.tools.Type r1 = new org.bytedeco.javacpp.tools.Type
            java.lang.String r12 = "Pointer"
            r1.<init>(r12)
            java.util.Iterator r22 = r24.iterator()
        L_0x0594:
            boolean r25 = r22.hasNext()
            if (r25 == 0) goto L_0x05c2
            java.lang.Object r25 = r22.next()
            r26 = r1
            r1 = r25
            org.bytedeco.javacpp.tools.Type r1 = (org.bytedeco.javacpp.tools.Type) r1
            r25 = r5
            org.bytedeco.javacpp.tools.InfoMap r5 = r8.infoMap
            r29 = r4
            java.lang.String r4 = r1.cppName
            org.bytedeco.javacpp.tools.Info r4 = r5.getFirst(r4)
            if (r4 == 0) goto L_0x05be
            boolean r4 = r4.flatten
            if (r4 != 0) goto L_0x05b7
            goto L_0x05be
        L_0x05b7:
            r5 = r25
            r1 = r26
            r4 = r29
            goto L_0x0594
        L_0x05be:
            r22.remove()
            goto L_0x05c8
        L_0x05c2:
            r26 = r1
            r29 = r4
            r25 = r5
        L_0x05c8:
            int r4 = r24.size()
            if (r4 <= 0) goto L_0x0653
            java.util.Iterator r4 = r24.iterator()
            r5 = r7
        L_0x05d3:
            boolean r22 = r4.hasNext()
            if (r22 == 0) goto L_0x064e
            java.lang.Object r22 = r4.next()
            r26 = r4
            r4 = r22
            org.bytedeco.javacpp.tools.Type r4 = (org.bytedeco.javacpp.tools.Type) r4
            r22 = r14
            java.lang.String r14 = r4.javaName
            boolean r14 = r14.equals(r12)
            if (r14 != 0) goto L_0x0645
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r5)
            java.lang.String r5 = "    public "
            r14.append(r5)
            java.lang.String r5 = r4.javaName
            r14.append(r5)
            java.lang.String r5 = " as"
            r14.append(r5)
            r30 = r11
            java.lang.String r11 = r4.javaName
            r14.append(r11)
            java.lang.String r11 = "() { return as"
            r14.append(r11)
            java.lang.String r11 = r4.javaName
            r14.append(r11)
            java.lang.String r11 = "(this); }\n    @Namespace public static native @Name(\"static_cast<"
            r14.append(r11)
            java.lang.String r11 = r4.cppName
            r14.append(r11)
            java.lang.String r11 = "*>\") "
            r14.append(r11)
            java.lang.String r11 = r4.javaName
            r14.append(r11)
            r14.append(r5)
            java.lang.String r4 = r4.javaName
            r14.append(r4)
            java.lang.String r4 = "("
            r14.append(r4)
            java.lang.String r4 = r2.javaName
            r14.append(r4)
            java.lang.String r4 = " pointer);\n"
            r14.append(r4)
            java.lang.String r5 = r14.toString()
            goto L_0x0647
        L_0x0645:
            r30 = r11
        L_0x0647:
            r14 = r22
            r4 = r26
            r11 = r30
            goto L_0x05d3
        L_0x064e:
            r30 = r11
            r22 = r14
            goto L_0x0658
        L_0x0653:
            r30 = r11
            r22 = r14
            r5 = r7
        L_0x0658:
            java.lang.String r4 = r2.javaName
            r13.signature = r4
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r8.tokens
            r11 = r40
            r4.index = r11
            r4 = 46
            int r11 = r0.lastIndexOf(r4)
            r14 = 1
            int r11 = r11 + r14
            java.lang.String r11 = r0.substring(r11)
            java.lang.String r14 = r9.namespace
            if (r14 == 0) goto L_0x0687
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r4 = r9.namespace
            r14.append(r4)
            r14.append(r15)
            r14.append(r0)
            java.lang.String r4 = r14.toString()
            goto L_0x0688
        L_0x0687:
            r4 = r0
        L_0x0688:
            java.lang.String r14 = r2.cppName
            r32 = r5
            r20 = r11
            r5 = 0
        L_0x068f:
            r11 = 5
            if (r5 >= r11) goto L_0x06e3
            r11 = r17[r5]
            if (r6 == 0) goto L_0x06d0
            r34 = r15
            java.lang.String[] r15 = r6.cppNames
            r28 = 0
            r15 = r15[r28]
            r39 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r35 = r3
            java.lang.String r3 = r11.value
            r2.append(r3)
            r3 = r38
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            boolean r2 = r15.startsWith(r2)
            if (r2 == 0) goto L_0x06d8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = r11.value
            r2.append(r5)
            r2.append(r3)
            r2.append(r14)
            java.lang.String r14 = r2.toString()
            goto L_0x06eb
        L_0x06d0:
            r39 = r2
            r35 = r3
            r34 = r15
            r3 = r38
        L_0x06d8:
            int r5 = r5 + 1
            r38 = r3
            r15 = r34
            r3 = r35
            r2 = r39
            goto L_0x068f
        L_0x06e3:
            r39 = r2
            r35 = r3
            r34 = r15
            r3 = r38
        L_0x06eb:
            int r2 = r0.length()
            java.lang.String r5 = "@Namespace(\""
            java.lang.String r11 = " extends "
            java.lang.String r15 = "@Name(\""
            r38 = r3
            java.lang.String r3 = "\") "
            r17 = r11
            if (r2 <= 0) goto L_0x0804
            if (r21 != 0) goto L_0x0804
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            r33 = r5
            r11 = 1
            java.lang.Object[] r5 = new java.lang.Object[r11]
            java.lang.Character r11 = java.lang.Character.valueOf(r18)
            r18 = 0
            r5[r18] = r11
            boolean r2 = r2.match(r5)
            if (r2 != 0) goto L_0x0722
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            r2.next()
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            r2.next()
        L_0x0722:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            r2.next()
            if (r16 == 0) goto L_0x0730
            r13.text = r7
            r10.add((org.bytedeco.javacpp.tools.Declaration) r13)
            r0 = 1
            return r0
        L_0x0730:
            if (r6 == 0) goto L_0x0741
            java.lang.String r2 = r6.base
            if (r2 == 0) goto L_0x0741
            java.lang.String r2 = r9.constName
            if (r2 == 0) goto L_0x073d
            java.lang.String r2 = r9.constBaseName
            goto L_0x073f
        L_0x073d:
            java.lang.String r2 = r6.base
        L_0x073f:
            r1.javaName = r2
        L_0x0741:
            boolean r2 = r0.equals(r12)
            if (r2 == 0) goto L_0x0749
            r2 = 1
            return r2
        L_0x0749:
            boolean r2 = r4.equals(r14)
            if (r2 != 0) goto L_0x0777
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r13.text
            r2.append(r4)
            r2.append(r15)
            java.lang.String r4 = r9.javaName
            if (r4 == 0) goto L_0x076a
            if (r35 >= 0) goto L_0x0763
            goto L_0x076a
        L_0x0763:
            r5 = 2
            int r4 = r35 + 2
            java.lang.String r14 = r14.substring(r4)
        L_0x076a:
            r2.append(r14)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r13.text = r2
            goto L_0x079c
        L_0x0777:
            java.lang.String r2 = r9.namespace
            if (r2 == 0) goto L_0x079c
            java.lang.String r2 = r9.javaName
            if (r2 != 0) goto L_0x079c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = r13.text
            r2.append(r4)
            r11 = r33
            r2.append(r11)
            java.lang.String r4 = r9.namespace
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r13.text = r2
        L_0x079c:
            org.bytedeco.javacpp.tools.Type r2 = new org.bytedeco.javacpp.tools.Type
            r2.<init>(r0)
            r13.type = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r13.text
            r2.append(r3)
            java.lang.String r3 = "@Opaque public static class "
            r2.append(r3)
            r2.append(r0)
            r3 = r17
            r2.append(r3)
            java.lang.String r1 = r1.javaName
            r2.append(r1)
            java.lang.String r1 = " {\n    /** Empty constructor. Calls {@code super((Pointer)null)}. */\n    public "
            r2.append(r1)
            r2.append(r0)
            java.lang.String r1 = "() { super((Pointer)null); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public "
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = "(Pointer p) { super(p); }\n}"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r13.text = r0
            r0 = r39
            r13.type = r0
            r0 = 1
            r13.incomplete = r0
            java.lang.String r0 = r47.commentAfter()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = r13.text
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r13.text = r0
            r2 = r30
            r10.spacing = r2
            r10.add((org.bytedeco.javacpp.tools.Declaration) r13)
            r0 = 0
            r10.spacing = r0
            r0 = 1
            return r0
        L_0x0804:
            r11 = r5
            r41 = r17
            r0 = r39
            r5 = 1
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.Object[] r10 = new java.lang.Object[r5]
            java.lang.Character r5 = java.lang.Character.valueOf(r23)
            r16 = 0
            r10[r16] = r5
            boolean r2 = r2.match(r10)
            if (r2 == 0) goto L_0x0825
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r8.tokens
            r2.next()
        L_0x0825:
            java.lang.String r2 = r0.cppName
            int r2 = r2.length()
            if (r2 <= 0) goto L_0x0838
            java.lang.String r2 = r0.cppName
            r5 = r22
            r5.namespace = r2
            r2 = r29
            r5.cppName = r2
            goto L_0x083c
        L_0x0838:
            r5 = r22
            r2 = r29
        L_0x083c:
            if (r25 != 0) goto L_0x0842
            java.lang.String r10 = r0.javaName
            r5.javaName = r10
        L_0x0842:
            if (r6 == 0) goto L_0x084b
            boolean r10 = r6.virtualize
            if (r10 == 0) goto L_0x084b
            r10 = 1
            r5.virtualize = r10
        L_0x084b:
            java.lang.String r10 = r1.cppName
            r5.baseType = r10
            org.bytedeco.javacpp.tools.DeclarationList r10 = new org.bytedeco.javacpp.tools.DeclarationList
            r10.<init>()
            int r16 = r37.size()
            if (r16 != 0) goto L_0x0864
            r8.declarations(r5, r10)
        L_0x085d:
            r39 = r0
            r29 = r2
            r33 = r11
            goto L_0x08c7
        L_0x0864:
            java.util.Iterator r16 = r37.iterator()
        L_0x0868:
            boolean r17 = r16.hasNext()
            if (r17 == 0) goto L_0x085d
            java.lang.Object r17 = r16.next()
            r29 = r2
            r2 = r17
            org.bytedeco.javacpp.tools.Declarator r2 = (org.bytedeco.javacpp.tools.Declarator) r2
            r39 = r0
            org.bytedeco.javacpp.tools.Declarator r0 = r9.variable
            if (r0 == 0) goto L_0x08b9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r33 = r11
            org.bytedeco.javacpp.tools.Declarator r11 = r9.variable
            java.lang.String r11 = r11.cppName
            r0.append(r11)
            r11 = r19
            r0.append(r11)
            java.lang.String r11 = r2.cppName
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            r2.cppName = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            org.bytedeco.javacpp.tools.Declarator r11 = r9.variable
            java.lang.String r11 = r11.javaName
            r0.append(r11)
            java.lang.String r11 = "_"
            r0.append(r11)
            java.lang.String r11 = r2.javaName
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            r2.javaName = r0
            goto L_0x08bb
        L_0x08b9:
            r33 = r11
        L_0x08bb:
            r5.variable = r2
            r8.declarations(r5, r10)
            r2 = r29
            r11 = r33
            r0 = r39
            goto L_0x0868
        L_0x08c7:
            if (r6 == 0) goto L_0x08d3
            boolean r0 = r6.purify
            if (r0 == 0) goto L_0x08d3
            boolean r0 = r5.virtualize
            if (r0 != 0) goto L_0x08d3
            r0 = 1
            goto L_0x08d4
        L_0x08d3:
            r0 = 0
        L_0x08d4:
            java.util.Iterator r2 = r10.iterator()
            r40 = r10
            r11 = 1
            r16 = 1
            r17 = 0
            r19 = 0
            r22 = 0
            r36 = 0
            r37 = 0
            r10 = r7
        L_0x08e8:
            boolean r42 = r2.hasNext()
            r43 = r6
            if (r42 == 0) goto L_0x0b20
            java.lang.Object r42 = r2.next()
            r6 = r42
            org.bytedeco.javacpp.tools.Declaration r6 = (org.bytedeco.javacpp.tools.Declaration) r6
            r42 = r2
            org.bytedeco.javacpp.tools.Declarator r2 = r6.declarator
            if (r2 == 0) goto L_0x0a27
            org.bytedeco.javacpp.tools.Declarator r2 = r6.declarator
            org.bytedeco.javacpp.tools.Type r2 = r2.type
            if (r2 == 0) goto L_0x0a27
            org.bytedeco.javacpp.tools.Declarator r2 = r6.declarator
            org.bytedeco.javacpp.tools.Type r2 = r2.type
            boolean r2 = r2.using
            if (r2 == 0) goto L_0x0a27
            java.lang.String r2 = r13.text
            if (r2 == 0) goto L_0x0a27
            java.lang.String r2 = r6.text
            r44 = r3
            java.lang.String r3 = "private native void allocate();"
            boolean r2 = r2.contains(r3)
            r2 = r17 | r2
            java.lang.String r3 = r6.text
            r17 = r2
            java.lang.String r2 = "private native void allocate(long"
            boolean r2 = r3.contains(r2)
            r2 = r19 | r2
            java.lang.String r3 = r6.text
            r19 = r2
            java.lang.String r2 = "private native void allocate(Pointer"
            boolean r2 = r3.contains(r2)
            r2 = r22 | r2
            java.lang.String r3 = r6.text
            r22 = r2
            java.lang.String r2 = "private native void allocate("
            boolean r2 = r3.contains(r2)
            r3 = 1
            r2 = r2 ^ r3
            r2 = r16 & r2
            org.bytedeco.javacpp.tools.Declarator r3 = r6.declarator
            org.bytedeco.javacpp.tools.Type r3 = r3.type
            java.lang.String r3 = r3.cppName
            r16 = r2
            r2 = r34
            int r9 = r3.lastIndexOf(r2)
            r34 = r15
            r15 = 0
            java.lang.String r3 = r3.substring(r15, r9)
            r9 = 60
            int r28 = r3.lastIndexOf(r9)
            java.lang.String r15 = r1.cppName
            int r9 = r15.lastIndexOf(r9)
            if (r28 >= 0) goto L_0x0979
            if (r9 < 0) goto L_0x0979
            java.lang.String r15 = r1.cppName
            r45 = r13
            r13 = 0
            java.lang.String r9 = r15.substring(r13, r9)
            boolean r9 = r3.equals(r9)
            if (r9 == 0) goto L_0x097b
            java.lang.String r3 = r1.cppName
            goto L_0x097b
        L_0x0979:
            r45 = r13
        L_0x097b:
            org.bytedeco.javacpp.tools.InfoMap r9 = r8.infoMap
            java.util.List r9 = r9.get(r3)
            java.util.Iterator r9 = r9.iterator()
        L_0x0985:
            boolean r13 = r9.hasNext()
            if (r13 == 0) goto L_0x099f
            java.lang.Object r13 = r9.next()
            org.bytedeco.javacpp.tools.Info r13 = (org.bytedeco.javacpp.tools.Info) r13
            if (r13 == 0) goto L_0x0985
            java.lang.String[] r15 = r13.pointerTypes
            if (r15 == 0) goto L_0x0985
            java.lang.String[] r15 = r13.pointerTypes
            int r15 = r15.length
            if (r15 <= 0) goto L_0x0985
            java.lang.String[] r9 = r13.pointerTypes
            goto L_0x09a0
        L_0x099f:
            r9 = 0
        L_0x09a0:
            int r13 = r3.lastIndexOf(r2)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r10)
            java.lang.String r10 = r6.text
            if (r9 == 0) goto L_0x09da
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r13 = r38
            r3.append(r13)
            r28 = 0
            r13 = r9[r28]
            r9 = r9[r28]
            r46 = r2
            r2 = 46
            int r9 = r9.lastIndexOf(r2)
            r26 = 1
            int r9 = r9 + 1
            java.lang.String r9 = r13.substring(r9)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            r9 = r38
            goto L_0x0a00
        L_0x09da:
            r46 = r2
            r9 = r38
            r2 = 46
            if (r13 < 0) goto L_0x09f1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r9)
            int r13 = r13 + 2
            java.lang.String r3 = r3.substring(r13)
            goto L_0x09f9
        L_0x09f1:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r9)
        L_0x09f9:
            r2.append(r3)
            java.lang.String r3 = r2.toString()
        L_0x0a00:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r9)
            r13 = r20
            r2.append(r13)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = r10.replace(r3, r2)
            r15.append(r2)
            java.lang.String r2 = "\n"
            r15.append(r2)
            java.lang.String r10 = r15.toString()
            r6.text = r7
        L_0x0a23:
            r38 = r9
            goto L_0x0af9
        L_0x0a27:
            r44 = r3
            r45 = r13
            r13 = r20
            r46 = r34
            r9 = r38
            r34 = r15
            org.bytedeco.javacpp.tools.Declarator r2 = r6.declarator
            if (r2 == 0) goto L_0x0a23
            org.bytedeco.javacpp.tools.Declarator r2 = r6.declarator
            org.bytedeco.javacpp.tools.Type r2 = r2.type
            if (r2 == 0) goto L_0x0a23
            org.bytedeco.javacpp.tools.Declarator r2 = r6.declarator
            org.bytedeco.javacpp.tools.Type r2 = r2.type
            boolean r2 = r2.constructor
            if (r2 == 0) goto L_0x0a23
            org.bytedeco.javacpp.tools.Declarator r2 = r6.declarator
            org.bytedeco.javacpp.tools.Parameters r2 = r2.parameters
            org.bytedeco.javacpp.tools.Declarator[] r2 = r2.declarators
            int r3 = r2.length
            if (r3 <= 0) goto L_0x0a56
            r3 = 0
            r15 = r2[r3]
            org.bytedeco.javacpp.tools.Type r3 = r15.type
            java.lang.String r3 = r3.javaName
            goto L_0x0a57
        L_0x0a56:
            r3 = 0
        L_0x0a57:
            int r15 = r2.length
            r38 = r9
            r9 = 1
            if (r15 != r9) goto L_0x0a83
            java.lang.String r9 = "int"
            boolean r9 = r3.equals(r9)
            if (r9 != 0) goto L_0x0a7d
            java.lang.String r9 = "long"
            boolean r9 = r3.equals(r9)
            if (r9 != 0) goto L_0x0a7d
            java.lang.String r9 = "float"
            boolean r9 = r3.equals(r9)
            if (r9 != 0) goto L_0x0a7d
            java.lang.String r9 = "double"
            boolean r9 = r3.equals(r9)
            if (r9 == 0) goto L_0x0a83
        L_0x0a7d:
            boolean r9 = r6.inaccessible
            if (r9 != 0) goto L_0x0a83
            r9 = 1
            goto L_0x0a84
        L_0x0a83:
            r9 = 0
        L_0x0a84:
            r9 = r37 | r9
            int r15 = r2.length
            if (r15 == 0) goto L_0x0a98
            int r15 = r2.length
            r16 = r9
            r9 = 1
            if (r15 != r9) goto L_0x0aa0
            java.lang.String r9 = "void"
            boolean r9 = r3.equals(r9)
            if (r9 == 0) goto L_0x0aa0
            goto L_0x0a9a
        L_0x0a98:
            r16 = r9
        L_0x0a9a:
            boolean r9 = r6.inaccessible
            if (r9 != 0) goto L_0x0aa0
            r9 = 1
            goto L_0x0aa1
        L_0x0aa0:
            r9 = 0
        L_0x0aa1:
            int r15 = r2.length
            r8 = 1
            if (r15 != r8) goto L_0x0ab3
            java.lang.String r8 = "long"
            boolean r8 = r3.equals(r8)
            if (r8 == 0) goto L_0x0ab3
            boolean r8 = r6.inaccessible
            if (r8 != 0) goto L_0x0ab3
            r8 = 1
            goto L_0x0ab4
        L_0x0ab3:
            r8 = 0
        L_0x0ab4:
            int r2 = r2.length
            r15 = 1
            if (r2 != r15) goto L_0x0ac4
            boolean r2 = r3.equals(r12)
            if (r2 == 0) goto L_0x0ac4
            boolean r2 = r6.inaccessible
            if (r2 != 0) goto L_0x0ac4
            r2 = 1
            goto L_0x0ac5
        L_0x0ac4:
            r2 = 0
        L_0x0ac5:
            java.lang.String r3 = r6.text
            java.lang.String r15 = "private native void allocate"
            int r3 = r3.indexOf(r15)
            if (r17 == 0) goto L_0x0ad1
            if (r9 != 0) goto L_0x0ae7
        L_0x0ad1:
            if (r19 == 0) goto L_0x0ad5
            if (r8 != 0) goto L_0x0ae7
        L_0x0ad5:
            if (r22 == 0) goto L_0x0ad9
            if (r2 != 0) goto L_0x0ae7
        L_0x0ad9:
            if (r3 < 0) goto L_0x0ae9
            java.lang.String r15 = r6.text
            java.lang.String r3 = r15.substring(r3)
            boolean r3 = r10.contains(r3)
            if (r3 == 0) goto L_0x0ae9
        L_0x0ae7:
            r6.text = r7
        L_0x0ae9:
            r3 = r17 | r9
            r8 = r19 | r8
            r2 = r22 | r2
            r22 = r2
            r17 = r3
            r19 = r8
            r37 = r16
            r16 = 0
        L_0x0af9:
            boolean r2 = r6.abstractMember
            r0 = r0 | r2
            boolean r2 = r6.constMember
            if (r2 == 0) goto L_0x0b06
            boolean r2 = r6.abstractMember
            if (r2 == 0) goto L_0x0b06
            r2 = 1
            goto L_0x0b07
        L_0x0b06:
            r2 = 0
        L_0x0b07:
            r11 = r11 & r2
            boolean r2 = r6.variable
            r36 = r36 | r2
            r8 = r47
            r9 = r48
            r20 = r13
            r15 = r34
            r2 = r42
            r6 = r43
            r3 = r44
            r13 = r45
            r34 = r46
            goto L_0x08e8
        L_0x0b20:
            r44 = r3
            r45 = r13
            r13 = r20
            r46 = r34
            r34 = r15
            if (r11 == 0) goto L_0x0b33
            boolean r2 = r5.virtualize
            if (r2 == 0) goto L_0x0b33
            java.lang.String r2 = "@Const public static "
            goto L_0x0b35
        L_0x0b33:
            java.lang.String r2 = "public static "
        L_0x0b35:
            if (r25 != 0) goto L_0x0d05
            boolean r3 = r4.equals(r14)
            if (r3 != 0) goto L_0x0b6d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = r45
            java.lang.String r6 = r4.text
            r3.append(r6)
            r6 = r34
            r3.append(r6)
            r6 = r48
            java.lang.String r8 = r6.javaName
            if (r8 == 0) goto L_0x0b5e
            if (r35 >= 0) goto L_0x0b57
            goto L_0x0b5e
        L_0x0b57:
            r8 = 2
            int r9 = r35 + 2
            java.lang.String r14 = r14.substring(r9)
        L_0x0b5e:
            r3.append(r14)
            r8 = r44
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r4.text = r3
            goto L_0x0b98
        L_0x0b6d:
            r6 = r48
            r8 = r44
            r4 = r45
            java.lang.String r3 = r6.namespace
            if (r3 == 0) goto L_0x0b98
            java.lang.String r3 = r6.javaName
            if (r3 != 0) goto L_0x0b98
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r9 = r4.text
            r3.append(r9)
            r9 = r33
            r3.append(r9)
            java.lang.String r9 = r6.namespace
            r3.append(r9)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r4.text = r3
        L_0x0b98:
            if (r16 == 0) goto L_0x0b9c
            if (r31 == 0) goto L_0x0bb3
        L_0x0b9c:
            if (r36 == 0) goto L_0x0bb3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r8 = r4.text
            r3.append(r8)
            java.lang.String r8 = "@NoOffset "
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            r4.text = r3
        L_0x0bb3:
            r3 = r43
            if (r43 == 0) goto L_0x0bc6
            java.lang.String r8 = r3.base
            if (r8 == 0) goto L_0x0bc6
            java.lang.String r8 = r6.constName
            if (r8 == 0) goto L_0x0bc2
            java.lang.String r6 = r6.constBaseName
            goto L_0x0bc4
        L_0x0bc2:
            java.lang.String r6 = r3.base
        L_0x0bc4:
            r1.javaName = r6
        L_0x0bc6:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = r4.text
            r6.append(r8)
            r6.append(r2)
            java.lang.String r2 = "class "
            r6.append(r2)
            r6.append(r13)
            r2 = r41
            r6.append(r2)
            java.lang.String r1 = r1.javaName
            r6.append(r1)
            java.lang.String r1 = " {\n    static { Loader.load(); }\n"
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            r4.text = r1
            if (r16 == 0) goto L_0x0c49
            if (r3 == 0) goto L_0x0bf8
            boolean r1 = r3.purify
            if (r1 != 0) goto L_0x0c49
        L_0x0bf8:
            if (r0 == 0) goto L_0x0bfe
            boolean r1 = r5.virtualize
            if (r1 == 0) goto L_0x0c49
        L_0x0bfe:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r2 = "    /** Default native constructor. */\n    public "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = "() { super((Pointer)null); allocate(); }\n    /** Native array allocator. Access with {@link Pointer#position(long)}. */\n    public "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = "(long size) { super((Pointer)null); allocateArray(size); }\n    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = "(Pointer p) { super(p); }\n    private native void allocate();\n    private native void allocateArray(long size);\n    @Override public "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = " position(long position) {\n        return ("
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = ")super.position(position);\n    }\n    @Override public "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = " getPointer(long i) {\n        return new "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = "(this).position(position + i);\n    }\n"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            goto L_0x0cca
        L_0x0c49:
            if (r3 == 0) goto L_0x0c4f
            boolean r1 = r3.purify
            if (r1 != 0) goto L_0x0c64
        L_0x0c4f:
            if (r0 == 0) goto L_0x0c55
            boolean r1 = r5.virtualize
            if (r1 == 0) goto L_0x0c64
        L_0x0c55:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            r1.append(r10)
            java.lang.String r7 = r1.toString()
        L_0x0c64:
            if (r22 != 0) goto L_0x0c80
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            java.lang.String r2 = "    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */\n    public "
            r1.append(r2)
            r1.append(r13)
            java.lang.String r2 = "(Pointer p) { super(p); }\n"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            goto L_0x0c81
        L_0x0c80:
            r1 = r7
        L_0x0c81:
            if (r17 == 0) goto L_0x0cca
            if (r3 == 0) goto L_0x0c89
            boolean r2 = r3.purify
            if (r2 != 0) goto L_0x0cca
        L_0x0c89:
            if (r0 == 0) goto L_0x0c8f
            boolean r2 = r5.virtualize
            if (r2 == 0) goto L_0x0cca
        L_0x0c8f:
            if (r37 != 0) goto L_0x0cca
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r1 = "    /** Native array allocator. Access with {@link Pointer#position(long)}. */\n    public "
            r2.append(r1)
            r2.append(r13)
            java.lang.String r1 = "(long size) { super((Pointer)null); allocateArray(size); }\n    private native void allocateArray(long size);\n    @Override public "
            r2.append(r1)
            r2.append(r13)
            java.lang.String r1 = " position(long position) {\n        return ("
            r2.append(r1)
            r2.append(r13)
            java.lang.String r1 = ")super.position(position);\n    }\n    @Override public "
            r2.append(r1)
            r2.append(r13)
            java.lang.String r1 = " getPointer(long i) {\n        return new "
            r2.append(r1)
            r2.append(r13)
            java.lang.String r1 = "(this).position(position + i);\n    }\n"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x0cca:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = r4.text
            r2.append(r5)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r4.text = r1
            r1 = r49
            r2 = r30
            r1.spacing = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = r4.text
            r2.append(r5)
            r5 = r32
            r2.append(r5)
            java.lang.String r5 = "\n"
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = r1.rescan(r2)
            r4.text = r2
            r2 = 0
            r1.spacing = r2
            goto L_0x0d0b
        L_0x0d05:
            r1 = r49
            r3 = r43
            r4 = r45
        L_0x0d0b:
            java.util.Iterator r2 = r24.iterator()
        L_0x0d0f:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0d85
            java.lang.Object r5 = r2.next()
            org.bytedeco.javacpp.tools.Type r5 = (org.bytedeco.javacpp.tools.Type) r5
            r6 = r47
            org.bytedeco.javacpp.tools.InfoMap r7 = r6.infoMap
            java.lang.String r8 = r5.cppName
            org.bytedeco.javacpp.tools.Info r7 = r7.getFirst(r8)
            if (r7 == 0) goto L_0x0d7a
            boolean r8 = r7.flatten
            if (r8 == 0) goto L_0x0d7a
            java.lang.String r8 = r7.javaText
            if (r8 == 0) goto L_0x0d7a
            java.lang.String r7 = r7.javaText
            r8 = 123(0x7b, float:1.72E-43)
            int r9 = r7.indexOf(r8)
            r11 = 0
        L_0x0d38:
            r12 = 2
            if (r11 >= r12) goto L_0x0d50
            char r12 = r7.charAt(r9)
            r13 = 10
            if (r12 != r13) goto L_0x0d46
            int r11 = r11 + 1
            goto L_0x0d4d
        L_0x0d46:
            boolean r12 = java.lang.Character.isWhitespace(r12)
            if (r12 != 0) goto L_0x0d4d
            r11 = 0
        L_0x0d4d:
            int r9 = r9 + 1
            goto L_0x0d38
        L_0x0d50:
            r13 = 10
            r11 = 125(0x7d, float:1.75E-43)
            int r11 = r7.lastIndexOf(r11)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r14 = r4.text
            r12.append(r14)
            java.lang.String r7 = r7.substring(r9, r11)
            java.lang.String r5 = r5.javaName
            r9 = r39
            java.lang.String r11 = r9.javaName
            java.lang.String r5 = r7.replace(r5, r11)
            r12.append(r5)
            java.lang.String r5 = r12.toString()
            r4.text = r5
            goto L_0x0d80
        L_0x0d7a:
            r9 = r39
            r8 = 123(0x7b, float:1.72E-43)
            r13 = 10
        L_0x0d80:
            r39 = r9
            r23 = 123(0x7b, float:1.72E-43)
            goto L_0x0d0f
        L_0x0d85:
            r6 = r47
            r9 = r39
            java.util.Iterator r2 = r40.iterator()
        L_0x0d8d:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0df5
            java.lang.Object r5 = r2.next()
            org.bytedeco.javacpp.tools.Declaration r5 = (org.bytedeco.javacpp.tools.Declaration) r5
            boolean r7 = r5.inaccessible
            if (r7 != 0) goto L_0x0dcc
            org.bytedeco.javacpp.tools.Declarator r7 = r5.declarator
            if (r7 == 0) goto L_0x0db7
            org.bytedeco.javacpp.tools.Declarator r7 = r5.declarator
            org.bytedeco.javacpp.tools.Type r7 = r7.type
            if (r7 == 0) goto L_0x0db7
            org.bytedeco.javacpp.tools.Declarator r7 = r5.declarator
            org.bytedeco.javacpp.tools.Type r7 = r7.type
            boolean r7 = r7.constructor
            if (r7 == 0) goto L_0x0db7
            if (r0 == 0) goto L_0x0db7
            if (r3 == 0) goto L_0x0dcc
            boolean r7 = r3.virtualize
            if (r7 == 0) goto L_0x0dcc
        L_0x0db7:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = r4.text
            r7.append(r8)
            java.lang.String r8 = r5.text
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r4.text = r7
        L_0x0dcc:
            boolean r7 = r5.inaccessible
            if (r7 != 0) goto L_0x0d8d
            org.bytedeco.javacpp.tools.Declarator r7 = r5.declarator
            if (r7 == 0) goto L_0x0d8d
            org.bytedeco.javacpp.tools.Declarator r7 = r5.declarator
            org.bytedeco.javacpp.tools.Type r7 = r7.type
            if (r7 == 0) goto L_0x0d8d
            org.bytedeco.javacpp.tools.Declarator r7 = r5.declarator
            org.bytedeco.javacpp.tools.Type r7 = r7.type
            boolean r7 = r7.constructor
            if (r7 == 0) goto L_0x0d8d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r10)
            java.lang.String r5 = r5.text
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r10 = r5
            goto L_0x0d8d
        L_0x0df5:
            r5 = r29
            r7 = 60
            int r0 = r5.lastIndexOf(r7)
            if (r0 < 0) goto L_0x0e05
            r2 = 0
            java.lang.String r0 = r5.substring(r2, r0)
            goto L_0x0e06
        L_0x0e05:
            r0 = r5
        L_0x0e06:
            r2 = r46
            int r5 = r0.lastIndexOf(r2)
            if (r5 < 0) goto L_0x0e14
            r7 = 2
            int r5 = r5 + r7
            java.lang.String r0 = r0.substring(r5)
        L_0x0e14:
            org.bytedeco.javacpp.tools.InfoMap r5 = r6.infoMap
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = r9.cppName
            r7.append(r8)
            r7.append(r2)
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            org.bytedeco.javacpp.tools.Info r5 = r5.getFirst(r7)
            if (r5 != 0) goto L_0x0e58
            org.bytedeco.javacpp.tools.InfoMap r5 = r6.infoMap
            org.bytedeco.javacpp.tools.Info r7 = new org.bytedeco.javacpp.tools.Info
            r8 = 1
            java.lang.String[] r11 = new java.lang.String[r8]
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r12 = r9.cppName
            r8.append(r12)
            r8.append(r2)
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            r2 = 0
            r11[r2] = r0
            r7.<init>((java.lang.String[]) r11)
            org.bytedeco.javacpp.tools.Info r0 = r7.javaText(r10)
            r5.put(r0)
        L_0x0e58:
            if (r25 != 0) goto L_0x0e7a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r4.text
            r0.append(r2)
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r6.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.String r2 = r2.spacing
            r0.append(r2)
            r2 = 125(0x7d, float:1.75E-43)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r4.text = r0
        L_0x0e7a:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r6.tokens
            org.bytedeco.javacpp.tools.Token r0 = r0.next()
            r2 = 1
            java.lang.Object[] r5 = new java.lang.Object[r2]
            org.bytedeco.javacpp.tools.Token r7 = org.bytedeco.javacpp.tools.Token.EOF
            r8 = 0
            r5[r8] = r7
            boolean r5 = r0.match(r5)
            if (r5 != 0) goto L_0x0eb1
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.Character r2 = java.lang.Character.valueOf(r18)
            r5[r8] = r2
            boolean r2 = r0.match(r5)
            if (r2 == 0) goto L_0x0e7a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = r4.text
            r2.append(r5)
            java.lang.String r0 = r0.spacing
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r4.text = r0
        L_0x0eb1:
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r6.tokens
            r0.next()
            r4.type = r9
            if (r3 == 0) goto L_0x0ec5
            java.lang.String r0 = r3.javaText
            if (r0 == 0) goto L_0x0ec5
            java.lang.String r0 = r3.javaText
            r4.text = r0
            r4.signature = r0
            goto L_0x0ecf
        L_0x0ec5:
            if (r3 == 0) goto L_0x0ecf
            boolean r0 = r3.flatten
            if (r0 == 0) goto L_0x0ecf
            java.lang.String r0 = r4.text
            r3.javaText = r0
        L_0x0ecf:
            r1.add((org.bytedeco.javacpp.tools.Declaration) r4)
            r0 = 1
            return r0
        L_0x0ed4:
            r6 = r8
            r1 = r10
            r4 = r13
            r0 = 1
            r4.text = r7
            r1.add((org.bytedeco.javacpp.tools.Declaration) r4)
            return r0
        L_0x0ede:
            r6 = r8
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r6.tokens
            r1.index = r0
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.group(org.bytedeco.javacpp.tools.Context, org.bytedeco.javacpp.tools.DeclarationList):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v14, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v21, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r34v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v22, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v59, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v69, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v51, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v52, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v70, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r46v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v76, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r46v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v69, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v83, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v13, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v74, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v79, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v81, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v14, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v84, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v82, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v88, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v85, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v86, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v91, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v15, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v16, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r19v4, types: [boolean] */
    /* JADX WARNING: type inference failed for: r19v12 */
    /* JADX WARNING: type inference failed for: r19v13 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x05a5  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x05f5  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x05fb  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x065b  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x06a2  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0709  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0714  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0796  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x07da  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x087a  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0891  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x08a5  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x08a8  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x08b4  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x08bd  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x0872 A[EDGE_INSN: B:306:0x0872->B:225:0x0872 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enumeration(org.bytedeco.javacpp.tools.Context r49, org.bytedeco.javacpp.tools.DeclarationList r50) throws org.bytedeco.javacpp.tools.ParserException {
        /*
            r48 = this;
            r0 = r48
            r1 = r49
            r2 = r50
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            int r3 = r3.index
            org.bytedeco.javacpp.tools.TokenIndexer r4 = r0.tokens
            org.bytedeco.javacpp.tools.Token r4 = r4.get()
            java.lang.String r4 = r4.spacing
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            org.bytedeco.javacpp.tools.Token r5 = r5.get()
            r6 = 1
            java.lang.Object[] r7 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r8 = org.bytedeco.javacpp.tools.Token.TYPEDEF
            r9 = 0
            r7[r9] = r8
            boolean r5 = r5.match(r7)
            org.bytedeco.javacpp.tools.TokenIndexer r7 = r0.tokens
            org.bytedeco.javacpp.tools.Token r7 = r7.get()
        L_0x002a:
            java.lang.Object[] r8 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r10 = org.bytedeco.javacpp.tools.Token.EOF
            r8[r9] = r10
            boolean r8 = r7.match(r8)
            r10 = 5
            if (r8 != 0) goto L_0x005b
            java.lang.Object[] r8 = new java.lang.Object[r6]
            org.bytedeco.javacpp.tools.Token r11 = org.bytedeco.javacpp.tools.Token.ENUM
            r8[r9] = r11
            boolean r8 = r7.match(r8)
            if (r8 == 0) goto L_0x0045
            r7 = 1
            goto L_0x005c
        L_0x0045:
            java.lang.Object[] r8 = new java.lang.Object[r6]
            java.lang.Integer r11 = java.lang.Integer.valueOf(r10)
            r8[r9] = r11
            boolean r7 = r7.match(r8)
            if (r7 != 0) goto L_0x0054
            goto L_0x005b
        L_0x0054:
            org.bytedeco.javacpp.tools.TokenIndexer r7 = r0.tokens
            org.bytedeco.javacpp.tools.Token r7 = r7.next()
            goto L_0x002a
        L_0x005b:
            r7 = 0
        L_0x005c:
            if (r7 != 0) goto L_0x0063
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r1.index = r3
            return r9
        L_0x0063:
            java.lang.String r7 = "enum"
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            org.bytedeco.javacpp.tools.Token r8 = r8.get(r6)
            r11 = 2
            java.lang.Object[] r12 = new java.lang.Object[r11]
            org.bytedeco.javacpp.tools.Token r13 = org.bytedeco.javacpp.tools.Token.CLASS
            r12[r9] = r13
            org.bytedeco.javacpp.tools.Token r13 = org.bytedeco.javacpp.tools.Token.STRUCT
            r12[r6] = r13
            boolean r8 = r8.match(r12)
            java.lang.String r12 = " "
            if (r8 == 0) goto L_0x0098
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r7)
            r8.append(r12)
            org.bytedeco.javacpp.tools.TokenIndexer r7 = r0.tokens
            org.bytedeco.javacpp.tools.Token r7 = r7.next()
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r8 = 1
            goto L_0x0099
        L_0x0098:
            r8 = 0
        L_0x0099:
            r13 = 123(0x7b, float:1.72E-43)
            if (r5 == 0) goto L_0x00ca
            org.bytedeco.javacpp.tools.TokenIndexer r14 = r0.tokens
            org.bytedeco.javacpp.tools.Token r14 = r14.get(r6)
            java.lang.Object[] r15 = new java.lang.Object[r6]
            java.lang.Character r16 = java.lang.Character.valueOf(r13)
            r15[r9] = r16
            boolean r14 = r14.match(r15)
            if (r14 != 0) goto L_0x00ca
            org.bytedeco.javacpp.tools.TokenIndexer r14 = r0.tokens
            org.bytedeco.javacpp.tools.Token r14 = r14.get(r11)
            java.lang.Object[] r15 = new java.lang.Object[r6]
            java.lang.Integer r16 = java.lang.Integer.valueOf(r10)
            r15[r9] = r16
            boolean r14 = r14.match(r15)
            if (r14 == 0) goto L_0x00ca
            org.bytedeco.javacpp.tools.TokenIndexer r14 = r0.tokens
            r14.next()
        L_0x00ca:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "public static final "
            r14.append(r15)
            java.lang.String r11 = "int"
            r14.append(r11)
            java.lang.String r14 = r14.toString()
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.next()
            r9 = 4
            r20 = r11
            java.lang.Object[] r11 = new java.lang.Object[r9]
            java.lang.Integer r21 = java.lang.Integer.valueOf(r10)
            r19 = 0
            r11[r19] = r21
            r18 = 123(0x7b, float:1.72E-43)
            java.lang.Character r21 = java.lang.Character.valueOf(r18)
            r9 = 1
            r11[r9] = r21
            r21 = 58
            java.lang.Character r17 = java.lang.Character.valueOf(r21)
            r16 = 2
            r11[r16] = r17
            r23 = 59
            java.lang.Character r17 = java.lang.Character.valueOf(r23)
            r24 = 3
            r11[r24] = r17
            org.bytedeco.javacpp.tools.Token r11 = r13.expect(r11)
            java.lang.Object[] r13 = new java.lang.Object[r9]
            java.lang.Integer r17 = java.lang.Integer.valueOf(r10)
            r19 = 0
            r13[r19] = r17
            boolean r13 = r11.match(r13)
            java.lang.String r10 = ""
            if (r13 == 0) goto L_0x014d
            org.bytedeco.javacpp.tools.Attribute r11 = r0.attribute(r9)
        L_0x012c:
            if (r11 == 0) goto L_0x0137
            boolean r11 = r11.annotation
            if (r11 == 0) goto L_0x0137
            org.bytedeco.javacpp.tools.Attribute r11 = r0.attribute(r9)
            goto L_0x012c
        L_0x0137:
            org.bytedeco.javacpp.tools.TokenIndexer r11 = r0.tokens
            org.bytedeco.javacpp.tools.Token r11 = r11.get()
            java.lang.String r11 = r11.value
            org.bytedeco.javacpp.tools.TokenIndexer r13 = r0.tokens
            org.bytedeco.javacpp.tools.Token r13 = r13.next()
            r25 = r14
            r47 = r13
            r13 = r11
            r11 = r47
            goto L_0x0150
        L_0x014d:
            r13 = r10
            r25 = r14
        L_0x0150:
            java.lang.Object[] r14 = new java.lang.Object[r9]
            java.lang.Character r9 = java.lang.Character.valueOf(r21)
            r19 = 0
            r14[r19] = r9
            boolean r9 = r11.match(r14)
            if (r9 == 0) goto L_0x018e
            org.bytedeco.javacpp.tools.TokenIndexer r9 = r0.tokens
            r9.next()
            org.bytedeco.javacpp.tools.Type r9 = r48.type(r49)
            java.lang.String r11 = r9.cppName
            java.lang.String r9 = r9.javaName
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r15)
            r14.append(r9)
            java.lang.String r14 = r14.toString()
            r20 = r9
            org.bytedeco.javacpp.tools.TokenIndexer r9 = r0.tokens
            org.bytedeco.javacpp.tools.Token r9 = r9.get()
            r21 = r14
            r47 = r11
            r11 = r9
            r9 = r20
            r20 = r47
            goto L_0x0192
        L_0x018e:
            r9 = r20
            r21 = r25
        L_0x0192:
            java.lang.String r14 = "("
            r26 = r7
            java.lang.String r7 = "::"
            r27 = r4
            java.lang.String r4 = ";"
            r28 = r4
            java.lang.String r4 = "\") "
            r29 = r6
            if (r5 != 0) goto L_0x01c0
            r5 = 1
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.Character r17 = java.lang.Character.valueOf(r23)
            r19 = 0
            r6[r19] = r17
            boolean r6 = r11.match(r6)
            if (r6 == 0) goto L_0x01c3
            r41 = r7
            r2 = r10
            r5 = r2
            r11 = r5
            r40 = r13
            r30 = 0
            goto L_0x0794
        L_0x01c0:
            r5 = 1
            r19 = 0
        L_0x01c3:
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r5 = 123(0x7b, float:1.72E-43)
            java.lang.Character r30 = java.lang.Character.valueOf(r5)
            r6[r19] = r30
            boolean r5 = r11.match(r6)
            if (r5 != 0) goto L_0x01d8
            org.bytedeco.javacpp.tools.TokenIndexer r1 = r0.tokens
            r1.index = r3
            return r19
        L_0x01d8:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            r5 = r10
            r6 = r5
            r32 = r6
            r33 = r32
            r34 = r33
            r35 = r14
            r11 = 2
            r30 = 0
            r31 = 0
        L_0x01ed:
            java.lang.Object[] r14 = new java.lang.Object[r11]
            org.bytedeco.javacpp.tools.Token r11 = org.bytedeco.javacpp.tools.Token.EOF
            r14[r19] = r11
            r11 = 125(0x7d, float:1.75E-43)
            java.lang.Character r36 = java.lang.Character.valueOf(r11)
            r17 = 1
            r14[r17] = r36
            boolean r14 = r3.match(r14)
            if (r14 != 0) goto L_0x0788
            java.lang.String r14 = r48.commentBefore()
            boolean r36 = r48.macro(r49, r50)
            java.lang.String r11 = ","
            if (r36 == 0) goto L_0x026d
            int r3 = r50.size()
            int r3 = r3 + -1
            java.lang.Object r3 = r2.remove(r3)
            org.bytedeco.javacpp.tools.Declaration r3 = (org.bytedeco.javacpp.tools.Declaration) r3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r5)
            r2.append(r14)
            java.lang.String r5 = r3.text
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            boolean r2 = r6.equals(r11)
            if (r2 == 0) goto L_0x025f
            java.lang.String r2 = r3.text
            java.lang.String r2 = r2.trim()
            java.lang.String r3 = "//"
            boolean r2 = r2.startsWith(r3)
            if (r2 != 0) goto L_0x025f
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r15)
            r2.append(r9)
            java.lang.String r2 = r2.toString()
            r21 = r2
            r41 = r7
            r38 = r8
            r40 = r13
            r43 = r15
            r6 = r28
            goto L_0x0267
        L_0x025f:
            r41 = r7
            r38 = r8
            r40 = r13
            r43 = r15
        L_0x0267:
            r3 = r29
            r14 = r35
            goto L_0x076e
        L_0x026d:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            r36 = r3
            java.lang.String r3 = r2.value
            if (r3 == 0) goto L_0x074a
            int r38 = r3.length()
            if (r38 != 0) goto L_0x0281
            goto L_0x074a
        L_0x0281:
            r38 = r8
            if (r8 == 0) goto L_0x029a
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r13)
            r8.append(r7)
            r8.append(r3)
            java.lang.String r8 = r8.toString()
            r39 = r3
            goto L_0x029d
        L_0x029a:
            r8 = r3
            r39 = r8
        L_0x029d:
            java.lang.String r3 = r1.namespace
            if (r3 == 0) goto L_0x02b8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r40 = r13
            java.lang.String r13 = r1.namespace
            r3.append(r13)
            r3.append(r7)
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            goto L_0x02ba
        L_0x02b8:
            r40 = r13
        L_0x02ba:
            org.bytedeco.javacpp.tools.InfoMap r3 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r3 = r3.getFirst(r8)
            if (r3 == 0) goto L_0x02d7
            java.lang.String[] r13 = r3.javaNames
            if (r13 == 0) goto L_0x02d7
            java.lang.String[] r13 = r3.javaNames
            int r13 = r13.length
            if (r13 <= 0) goto L_0x02d7
            java.lang.String[] r13 = r3.javaNames
            r19 = 0
            r13 = r13[r19]
            r42 = r2
            r41 = r7
            r7 = 1
            goto L_0x02ff
        L_0x02d7:
            r19 = 0
            if (r3 != 0) goto L_0x02f8
            org.bytedeco.javacpp.tools.InfoMap r3 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r13 = new org.bytedeco.javacpp.tools.Info
            r42 = r2
            r41 = r7
            r7 = 1
            java.lang.String[] r2 = new java.lang.String[r7]
            r2[r19] = r8
            r13.<init>((java.lang.String[]) r2)
            org.bytedeco.javacpp.tools.Info r2 = r13.cppText(r10)
            org.bytedeco.javacpp.tools.Info r2 = r2.translate()
            r3.put(r2)
            r3 = r2
            goto L_0x02fd
        L_0x02f8:
            r42 = r2
            r41 = r7
            r7 = 1
        L_0x02fd:
            r13 = r39
        L_0x02ff:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            r39 = r14
            java.lang.Object[] r14 = new java.lang.Object[r7]
            r7 = 61
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            r43 = r15
            r15 = 0
            r14[r15] = r7
            boolean r2 = r2.match(r14)
            java.lang.String r7 = "\n"
            if (r2 == 0) goto L_0x0502
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            java.lang.String r2 = r2.spacing
            int r14 = r2.length()
            if (r14 <= 0) goto L_0x0337
            char r14 = r2.charAt(r15)
            r15 = 32
            if (r14 != r15) goto L_0x0337
            r14 = 1
            java.lang.String r2 = r2.substring(r14)
        L_0x0337:
            org.bytedeco.javacpp.tools.Token r14 = new org.bytedeco.javacpp.tools.Token
            r14.<init>()
            if (r3 == 0) goto L_0x0343
            boolean r15 = r3.translate
            r36 = r2
            goto L_0x0346
        L_0x0343:
            r36 = r2
            r15 = 1
        L_0x0346:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.next()
            r44 = r3
            r31 = r10
            r32 = r14
            r22 = r15
            r3 = 4
            r15 = 0
            r14 = r2
        L_0x0357:
            java.lang.Object[] r2 = new java.lang.Object[r3]
            org.bytedeco.javacpp.tools.Token r45 = org.bytedeco.javacpp.tools.Token.EOF
            r19 = 0
            r2[r19] = r45
            r45 = 35
            java.lang.Character r45 = java.lang.Character.valueOf(r45)
            r17 = 1
            r2[r17] = r45
            r45 = 44
            java.lang.Character r45 = java.lang.Character.valueOf(r45)
            r16 = 2
            r2[r16] = r45
            r37 = 125(0x7d, float:1.75E-43)
            java.lang.Character r45 = java.lang.Character.valueOf(r37)
            r2[r24] = r45
            boolean r2 = r14.match(r2)
            if (r2 == 0) goto L_0x0454
            if (r15 <= 0) goto L_0x0389
            r46 = r5
            r2 = r31
            goto L_0x0458
        L_0x0389:
            java.lang.String r2 = r31.trim()     // Catch:{ NumberFormatException -> 0x039b }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x039b }
            r8 = r2
            r15 = r10
            r14 = r21
            r3 = r36
            r2 = 123(0x7b, float:1.72E-43)
            goto L_0x050f
        L_0x039b:
            if (r22 == 0) goto L_0x03c2
            r2 = r31
            java.lang.String r2 = r0.translate(r2)
            int r8 = r2.length()
            if (r8 <= 0) goto L_0x03b8
            r8 = 0
            char r14 = r2.charAt(r8)
            r8 = 32
            if (r14 != r8) goto L_0x03b8
            r8 = 1
            java.lang.String r2 = r2.substring(r8)
        L_0x03b8:
            r15 = r2
            r14 = r21
            r3 = r36
            r2 = 123(0x7b, float:1.72E-43)
            r8 = 0
            goto L_0x050f
        L_0x03c2:
            boolean r2 = r6.equals(r11)
            if (r2 == 0) goto L_0x03ca
            r6 = r28
        L_0x03ca:
            boolean r2 = r13.equals(r8)
            if (r2 != 0) goto L_0x03e8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r14 = "@Name(\""
            r2.append(r14)
            r2.append(r8)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            goto L_0x040b
        L_0x03e8:
            java.lang.String r2 = r1.namespace
            if (r2 == 0) goto L_0x040a
            java.lang.String r2 = r1.javaName
            if (r2 != 0) goto L_0x040a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r8 = "@Namespace(\""
            r2.append(r8)
            java.lang.String r8 = r1.namespace
            r2.append(r8)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            goto L_0x040b
        L_0x040a:
            r2 = r10
        L_0x040b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r5)
            r8.append(r7)
            r8.append(r2)
            java.lang.String r2 = "public static native @MemberGetter "
            r8.append(r2)
            r8.append(r9)
            r8.append(r12)
            r8.append(r13)
            java.lang.String r2 = "();\n"
            r8.append(r2)
            java.lang.String r5 = r8.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r8 = r43
            r2.append(r8)
            r2.append(r9)
            java.lang.String r21 = r2.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r13)
            java.lang.String r14 = "()"
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            goto L_0x03b8
        L_0x0454:
            r2 = r31
            r46 = r5
        L_0x0458:
            r3 = 1
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.Integer r31 = java.lang.Integer.valueOf(r3)
            r3 = 0
            r5[r3] = r31
            boolean r3 = r14.match(r5)
            if (r3 == 0) goto L_0x0474
            java.lang.String r3 = r14.value
            java.lang.String r5 = "L"
            boolean r3 = r3.endsWith(r5)
            if (r3 == 0) goto L_0x0474
            r30 = 1
        L_0x0474:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = r14.spacing
            r3.append(r2)
            r3.append(r14)
            java.lang.String r31 = r3.toString()
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r5 = 40
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r19 = 0
            r3[r19] = r5
            boolean r3 = r14.match(r3)
            if (r3 == 0) goto L_0x049e
            int r15 = r15 + 1
            goto L_0x04b0
        L_0x049e:
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r5 = 41
            java.lang.Character r5 = java.lang.Character.valueOf(r5)
            r3[r19] = r5
            boolean r3 = r14.match(r3)
            if (r3 == 0) goto L_0x04b0
            int r15 = r15 + -1
        L_0x04b0:
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r5 = 5
            java.lang.Integer r17 = java.lang.Integer.valueOf(r5)
            r3[r19] = r17
            r5 = r32
            boolean r3 = r5.match(r3)
            if (r3 == 0) goto L_0x04d7
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r2 = 40
            java.lang.Character r2 = java.lang.Character.valueOf(r2)
            r3[r19] = r2
            boolean r2 = r14.match(r3)
            if (r2 != 0) goto L_0x04d2
            goto L_0x04d7
        L_0x04d2:
            r2 = 123(0x7b, float:1.72E-43)
            r5 = 125(0x7d, float:1.75E-43)
            goto L_0x04f2
        L_0x04d7:
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r2 = 123(0x7b, float:1.72E-43)
            java.lang.Character r5 = java.lang.Character.valueOf(r2)
            r3[r19] = r5
            r5 = 125(0x7d, float:1.75E-43)
            java.lang.Character r18 = java.lang.Character.valueOf(r5)
            r17 = 1
            r3[r17] = r18
            boolean r3 = r14.match(r3)
            if (r3 == 0) goto L_0x04f4
        L_0x04f2:
            r22 = 0
        L_0x04f4:
            org.bytedeco.javacpp.tools.TokenIndexer r3 = r0.tokens
            org.bytedeco.javacpp.tools.Token r3 = r3.next()
            r32 = r14
            r5 = r46
            r14 = r3
            r3 = 4
            goto L_0x0357
        L_0x0502:
            r44 = r3
            r46 = r5
            r2 = 123(0x7b, float:1.72E-43)
            r3 = r10
            r14 = r21
            r8 = r31
            r15 = r32
        L_0x050f:
            int r18 = r5.length()
            if (r18 <= 0) goto L_0x0538
            boolean r18 = r5.endsWith(r7)
            if (r18 != 0) goto L_0x0538
            int r18 = r14.length()
            if (r18 <= 0) goto L_0x0538
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r5)
            r5 = r39
            r2.append(r5)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            r5 = r2
            r2 = r10
            goto L_0x053a
        L_0x0538:
            r2 = r39
        L_0x053a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r6)
            r7.append(r5)
            r7.append(r14)
            r7.append(r2)
            java.lang.String r7 = r7.toString()
            r21 = r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            java.lang.String r5 = r48.commentAfter()
            int r22 = r5.length()
            if (r22 != 0) goto L_0x0593
            r22 = r5
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            org.bytedeco.javacpp.tools.Token r5 = r5.get()
            r31 = r6
            r32 = r14
            r6 = 1
            java.lang.Object[] r14 = new java.lang.Object[r6]
            r6 = 44
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            r19 = 0
            r14[r19] = r6
            boolean r5 = r5.match(r14)
            if (r5 == 0) goto L_0x0599
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            r5.next()
            java.lang.String r5 = r48.commentAfter()
            goto L_0x059b
        L_0x0593:
            r22 = r5
            r31 = r6
            r32 = r14
        L_0x0599:
            r5 = r22
        L_0x059b:
            r6 = r42
            java.lang.String r6 = r6.spacing
            int r14 = r5.length()
            if (r14 <= 0) goto L_0x05d7
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r7)
            r14.append(r6)
            r14.append(r5)
            java.lang.String r7 = r14.toString()
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r2)
            r14.append(r6)
            r14.append(r5)
            java.lang.String r2 = r14.toString()
            r5 = 10
            int r14 = r6.lastIndexOf(r5)
            if (r14 < 0) goto L_0x05d7
            int r14 = r14 + 1
            java.lang.String r6 = r6.substring(r14)
        L_0x05d7:
            int r5 = r6.length()
            if (r5 != 0) goto L_0x05e4
            boolean r5 = r7.endsWith(r11)
            if (r5 != 0) goto L_0x05e4
            r6 = r12
        L_0x05e4:
            java.lang.String r5 = "byte"
            boolean r5 = r9.equals(r5)
            if (r5 != 0) goto L_0x05fb
            java.lang.String r5 = "short"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x05f5
            goto L_0x05fb
        L_0x05f5:
            r5 = r10
            r22 = r11
            r14 = r35
            goto L_0x0613
        L_0x05fb:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r14 = r35
            r5.append(r14)
            r5.append(r9)
            r22 = r11
            java.lang.String r11 = ")"
            r5.append(r11)
            java.lang.String r5 = r5.toString()
        L_0x0613:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r7)
            r11.append(r6)
            r11.append(r13)
            r11.append(r3)
            java.lang.String r7 = " = "
            r11.append(r7)
            r11.append(r5)
            r11.append(r15)
            java.lang.String r7 = r11.toString()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r2)
            r11.append(r6)
            r11.append(r13)
            r11.append(r3)
            r11.append(r14)
            r11.append(r5)
            r11.append(r15)
            java.lang.String r2 = r11.toString()
            java.lang.String r3 = r15.trim()
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x06a2
            if (r8 <= 0) goto L_0x069f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r7)
            java.lang.String r5 = " + "
            r3.append(r5)
            r3.append(r8)
            java.lang.String r7 = r3.toString()
            r3 = r29
            boolean r5 = r3.containsKey(r15)
            if (r5 == 0) goto L_0x068a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            java.lang.String r2 = ".value"
            r5.append(r2)
            java.lang.String r2 = r5.toString()
        L_0x068a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            java.lang.String r2 = " + "
            r5.append(r2)
            r5.append(r8)
            java.lang.String r2 = r5.toString()
            goto L_0x06c2
        L_0x069f:
            r3 = r29
            goto L_0x06c2
        L_0x06a2:
            r3 = r29
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r7)
            r5.append(r8)
            java.lang.String r7 = r5.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            r5.append(r8)
            java.lang.String r2 = r5.toString()
        L_0x06c2:
            java.lang.String r5 = "boolean"
            boolean r5 = r9.equals(r5)
            if (r5 == 0) goto L_0x06fe
            java.lang.String r5 = "true"
            boolean r5 = r15.equals(r5)
            if (r5 != 0) goto L_0x06da
            java.lang.String r5 = "false"
            boolean r5 = r15.equals(r5)
            if (r5 == 0) goto L_0x06dc
        L_0x06da:
            if (r8 <= 0) goto L_0x06fe
        L_0x06dc:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r7)
            java.lang.String r6 = " != 0"
            r5.append(r6)
            java.lang.String r7 = r5.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r2)
            java.lang.String r2 = " != 0"
            r5.append(r2)
            java.lang.String r2 = r5.toString()
        L_0x06fe:
            int r8 = r8 + 1
            if (r44 == 0) goto L_0x0714
            r5 = r44
            boolean r5 = r5.skip
            if (r5 != 0) goto L_0x0709
            goto L_0x0714
        L_0x0709:
            r5 = r21
            r6 = r31
            r21 = r32
            r31 = r8
            r32 = r15
            goto L_0x076e
        L_0x0714:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r11 = r34
            r5.append(r11)
            r5.append(r7)
            java.lang.String r11 = r5.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r6 = r33
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = ")"
            r5.append(r2)
            java.lang.String r33 = r5.toString()
            r3.put(r13, r7)
            r31 = r8
            r5 = r10
            r21 = r5
            r34 = r11
            r32 = r15
            r6 = r22
            goto L_0x076e
        L_0x074a:
            r46 = r5
            r41 = r7
            r38 = r8
            r40 = r13
            r43 = r15
            r3 = r29
            r2 = r33
            r11 = r34
            r14 = r35
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            org.bytedeco.javacpp.tools.Token r5 = r5.next()
            r7 = r36
            java.lang.String r7 = r7.spacing
            r5.spacing = r7
            r33 = r2
            r34 = r11
            r5 = r46
        L_0x076e:
            org.bytedeco.javacpp.tools.TokenIndexer r2 = r0.tokens
            org.bytedeco.javacpp.tools.Token r2 = r2.get()
            r29 = r3
            r35 = r14
            r8 = r38
            r13 = r40
            r7 = r41
            r15 = r43
            r11 = 2
            r19 = 0
            r3 = r2
            r2 = r50
            goto L_0x01ed
        L_0x0788:
            r46 = r5
            r41 = r7
            r40 = r13
            r2 = r33
            r11 = r34
            r14 = r35
        L_0x0794:
            if (r30 == 0) goto L_0x07af
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r12)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            java.lang.String r6 = " long"
            java.lang.String r11 = r11.replace(r3, r6)
            java.lang.String r20 = "long long"
            java.lang.String r9 = "long"
        L_0x07af:
            r3 = r20
            java.lang.String r6 = r48.commentBefore()
            org.bytedeco.javacpp.tools.Declaration r7 = new org.bytedeco.javacpp.tools.Declaration
            r7.<init>()
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            org.bytedeco.javacpp.tools.Token r8 = r8.get()
            r16 = r5
            r13 = r40
        L_0x07c4:
            r15 = 2
            java.lang.Object[] r5 = new java.lang.Object[r15]
            java.lang.Character r18 = java.lang.Character.valueOf(r23)
            r19 = 0
            r5[r19] = r18
            org.bytedeco.javacpp.tools.Token r18 = org.bytedeco.javacpp.tools.Token.EOF
            r15 = 1
            r5[r15] = r18
            boolean r5 = r8.match(r5)
            if (r5 != 0) goto L_0x0872
            r18 = r11
            r5 = 0
        L_0x07dd:
            java.lang.Object[] r11 = new java.lang.Object[r15]
            r17 = 42
            java.lang.Character r17 = java.lang.Character.valueOf(r17)
            r11[r19] = r17
            boolean r11 = r8.match(r11)
            if (r11 == 0) goto L_0x07f6
            int r5 = r5 + 1
            org.bytedeco.javacpp.tools.TokenIndexer r8 = r0.tokens
            org.bytedeco.javacpp.tools.Token r8 = r8.next()
            goto L_0x07dd
        L_0x07f6:
            java.lang.Object[] r11 = new java.lang.Object[r15]
            r15 = 5
            java.lang.Integer r21 = java.lang.Integer.valueOf(r15)
            r11[r19] = r21
            boolean r11 = r8.match(r11)
            if (r11 == 0) goto L_0x0864
            java.lang.String r8 = r8.value
            if (r13 == 0) goto L_0x080f
            int r11 = r13.length()
            if (r11 != 0) goto L_0x0810
        L_0x080f:
            r13 = r8
        L_0x0810:
            org.bytedeco.javacpp.tools.InfoMap r11 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r11 = r11.getFirst(r3)
            if (r5 <= 0) goto L_0x0845
            org.bytedeco.javacpp.tools.InfoMap r5 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r15 = new org.bytedeco.javacpp.tools.Info
            r15.<init>((org.bytedeco.javacpp.tools.Info) r11)
            org.bytedeco.javacpp.tools.Info r15 = r15.cast()
            r22 = r12
            r21 = r13
            r13 = 1
            java.lang.String[] r12 = new java.lang.String[r13]
            r13 = 0
            r12[r13] = r8
            org.bytedeco.javacpp.tools.Info r8 = r15.cppNames(r12)
            java.lang.String[] r11 = r11.pointerTypes
            org.bytedeco.javacpp.tools.Info r8 = r8.valueTypes(r11)
            java.lang.String r11 = "PointerPointer"
            java.lang.String[] r11 = new java.lang.String[]{r11}
            org.bytedeco.javacpp.tools.Info r8 = r8.pointerTypes(r11)
            r5.put(r8)
            goto L_0x0861
        L_0x0845:
            r22 = r12
            r21 = r13
            org.bytedeco.javacpp.tools.InfoMap r5 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r12 = new org.bytedeco.javacpp.tools.Info
            r12.<init>((org.bytedeco.javacpp.tools.Info) r11)
            org.bytedeco.javacpp.tools.Info r11 = r12.cast()
            r12 = 1
            java.lang.String[] r13 = new java.lang.String[r12]
            r12 = 0
            r13[r12] = r8
            org.bytedeco.javacpp.tools.Info r8 = r11.cppNames(r13)
            r5.put(r8)
        L_0x0861:
            r13 = r21
            goto L_0x0866
        L_0x0864:
            r22 = r12
        L_0x0866:
            org.bytedeco.javacpp.tools.TokenIndexer r5 = r0.tokens
            org.bytedeco.javacpp.tools.Token r8 = r5.next()
            r11 = r18
            r12 = r22
            goto L_0x07c4
        L_0x0872:
            r18 = r11
            r22 = r12
            java.lang.String r5 = r1.namespace
            if (r5 == 0) goto L_0x0891
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r11 = r1.namespace
            r5.append(r11)
            r11 = r41
            r5.append(r11)
            r5.append(r13)
            java.lang.String r5 = r5.toString()
            goto L_0x0894
        L_0x0891:
            r11 = r41
            r5 = r13
        L_0x0894:
            org.bytedeco.javacpp.tools.InfoMap r12 = r0.infoMap
            org.bytedeco.javacpp.tools.Info r12 = r12.getFirst(r5)
            org.bytedeco.javacpp.tools.InfoMap r15 = r0.infoMap
            r20 = r3
            r3 = 0
            org.bytedeco.javacpp.tools.Info r3 = r15.getFirst(r3)
            if (r12 == 0) goto L_0x08a8
            boolean r3 = r12.enumerate
            goto L_0x08ae
        L_0x08a8:
            if (r3 == 0) goto L_0x08ad
            boolean r3 = r3.enumerate
            goto L_0x08ae
        L_0x08ad:
            r3 = 0
        L_0x08ae:
            if (r12 == 0) goto L_0x08bd
            boolean r15 = r12.skip
            if (r15 == 0) goto L_0x08bd
            r15 = r27
            r7.text = r15
            r2 = r0
        L_0x08b9:
            r0 = r50
            goto L_0x0b61
        L_0x08bd:
            r15 = r27
            r0 = 10
            int r0 = r15.lastIndexOf(r0)
            if (r0 >= 0) goto L_0x08c9
            r0 = r15
            goto L_0x08d1
        L_0x08c9:
            r17 = 1
            int r0 = r0 + 1
            java.lang.String r0 = r15.substring(r0)
        L_0x08d1:
            r35 = r14
            if (r12 == 0) goto L_0x08e4
            java.lang.String[] r14 = r12.valueTypes
            if (r14 == 0) goto L_0x08e4
            java.lang.String[] r14 = r12.valueTypes
            int r14 = r14.length
            if (r14 <= 0) goto L_0x08e4
            java.lang.String[] r12 = r12.valueTypes
            r14 = 0
            r12 = r12[r14]
            goto L_0x08e5
        L_0x08e4:
            r12 = r13
        L_0x08e5:
            if (r3 == 0) goto L_0x0ad5
            if (r12 == 0) goto L_0x0ad5
            int r3 = r12.length()
            if (r3 <= 0) goto L_0x0ad5
            boolean r3 = r12.equals(r9)
            if (r3 != 0) goto L_0x0ad5
            java.lang.String r3 = r1.namespace
            if (r3 == 0) goto L_0x090d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r14 = r1.namespace
            r3.append(r14)
            r3.append(r11)
            r3.append(r13)
            java.lang.String r13 = r3.toString()
        L_0x090d:
            boolean r3 = r13.equals(r5)
            if (r3 != 0) goto L_0x092b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            java.lang.String r10 = "@Name(\""
            r3.append(r10)
            r3.append(r5)
            r3.append(r4)
            java.lang.String r10 = r3.toString()
            goto L_0x094c
        L_0x092b:
            java.lang.String r3 = r1.namespace
            if (r3 == 0) goto L_0x094c
            java.lang.String r3 = r1.javaName
            if (r3 != 0) goto L_0x094c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            java.lang.String r10 = "@Namespace(\""
            r3.append(r10)
            java.lang.String r10 = r1.namespace
            r3.append(r10)
            r3.append(r4)
            java.lang.String r10 = r3.toString()
        L_0x094c:
            r3 = 46
            int r3 = r12.lastIndexOf(r3)
            r11 = 1
            int r3 = r3 + r11
            java.lang.String r3 = r12.substring(r3)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r13 = r7.text
            r11.append(r13)
            r11.append(r15)
            r11.append(r10)
            java.lang.String r10 = "public enum "
            r11.append(r10)
            r11.append(r3)
            java.lang.String r10 = " {"
            r11.append(r10)
            r11.append(r2)
            r2 = 1
            java.lang.Object[] r10 = new java.lang.Object[r2]
            java.lang.Character r13 = java.lang.Character.valueOf(r23)
            r14 = 0
            r10[r14] = r13
            org.bytedeco.javacpp.tools.Token r8 = r8.expect(r10)
            java.lang.String r8 = r8.spacing
            r11.append(r8)
            r10 = r28
            r11.append(r10)
            int r8 = r6.length()
            if (r8 <= 0) goto L_0x09a2
            char r8 = r6.charAt(r14)
            r10 = 32
            if (r8 != r10) goto L_0x09a2
            java.lang.String r6 = r6.substring(r2)
        L_0x09a2:
            r11.append(r6)
            java.lang.String r2 = "\n\n"
            r11.append(r2)
            r11.append(r0)
            java.lang.String r2 = "    public final "
            r11.append(r2)
            r11.append(r9)
            java.lang.String r2 = " value;\n"
            r11.append(r2)
            r11.append(r0)
            java.lang.String r2 = "    private "
            r11.append(r2)
            r11.append(r3)
            r2 = r35
            r11.append(r2)
            r11.append(r9)
            java.lang.String r6 = " v) { this.value = v; }\n"
            r11.append(r6)
            r11.append(r0)
            java.lang.String r6 = "    private "
            r11.append(r6)
            r11.append(r3)
            r11.append(r2)
            r11.append(r3)
            java.lang.String r2 = " e) { this.value = e.value; }\n"
            r11.append(r2)
            r11.append(r0)
            java.lang.String r2 = "    public "
            r11.append(r2)
            r11.append(r3)
            java.lang.String r2 = " intern() { for ("
            r11.append(r2)
            r11.append(r3)
            java.lang.String r2 = " e : values()) if (e.value == value) return e; return this; }\n"
            r11.append(r2)
            r11.append(r0)
            java.lang.String r2 = "    @Override public String toString() { return intern().name(); }\n"
            r11.append(r2)
            r11.append(r0)
            java.lang.String r0 = "}"
            r11.append(r0)
            java.lang.String r0 = r11.toString()
            r7.text = r0
            org.bytedeco.javacpp.tools.Info r0 = new org.bytedeco.javacpp.tools.Info
            r2 = r48
            org.bytedeco.javacpp.tools.InfoMap r3 = r2.infoMap
            r9 = r20
            org.bytedeco.javacpp.tools.Info r3 = r3.getFirst(r9)
            r0.<init>((org.bytedeco.javacpp.tools.Info) r3)
            r3 = 1
            java.lang.String[] r6 = new java.lang.String[r3]
            r8 = 0
            r6[r8] = r5
            org.bytedeco.javacpp.tools.Info r0 = r0.cppNames(r6)
            java.lang.String[] r6 = r0.valueTypes
            java.lang.String[] r8 = r0.valueTypes
            int r8 = r8.length
            int r8 = r8 + r3
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r6, r8)
            java.lang.String[] r3 = (java.lang.String[]) r3
            r0.valueTypes = r3
            r3 = 1
        L_0x0a3e:
            java.lang.String[] r6 = r0.valueTypes
            int r6 = r6.length
            if (r3 >= r6) goto L_0x0a67
            java.lang.String[] r6 = r0.valueTypes
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "@Cast(\""
            r8.append(r9)
            r8.append(r5)
            r8.append(r4)
            java.lang.String[] r9 = r0.valueTypes
            int r10 = r3 + -1
            r9 = r9[r10]
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r6[r3] = r8
            int r3 = r3 + 1
            goto L_0x0a3e
        L_0x0a67:
            java.lang.String[] r3 = r0.valueTypes
            java.lang.String r4 = r1.javaName
            if (r4 == 0) goto L_0x0a8b
            java.lang.String r4 = r1.javaName
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x0a8b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r1 = r1.javaName
            r4.append(r1)
            java.lang.String r1 = "."
            r4.append(r1)
            r4.append(r12)
            java.lang.String r12 = r4.toString()
        L_0x0a8b:
            r1 = 0
            r3[r1] = r12
            java.lang.String[] r1 = r0.pointerTypes
            java.lang.String[] r3 = r0.pointerTypes
            int r3 = r3.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)
            java.lang.String[] r1 = (java.lang.String[]) r1
            r0.pointerTypes = r1
            r1 = 0
        L_0x0a9c:
            java.lang.String[] r3 = r0.pointerTypes
            int r3 = r3.length
            if (r1 >= r3) goto L_0x0ac5
            java.lang.String[] r3 = r0.pointerTypes
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "@Cast(\""
            r4.append(r6)
            r4.append(r5)
            java.lang.String r6 = "*\") "
            r4.append(r6)
            java.lang.String[] r6 = r0.pointerTypes
            r6 = r6[r1]
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r3[r1] = r4
            int r1 = r1 + 1
            goto L_0x0a9c
        L_0x0ac5:
            org.bytedeco.javacpp.tools.InfoMap r1 = r2.infoMap
            r3 = 0
            org.bytedeco.javacpp.tools.Info r0 = r0.cast(r3)
            org.bytedeco.javacpp.tools.Info r0 = r0.enumerate()
            r1.put(r0)
            goto L_0x08b9
        L_0x0ad5:
            r2 = r48
            r9 = r20
            r10 = r28
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r7.text
            r1.append(r3)
            r1.append(r15)
            java.lang.String r3 = "/** "
            r1.append(r3)
            r3 = r26
            r1.append(r3)
            r3 = r22
            r1.append(r3)
            r1.append(r5)
            java.lang.String r3 = " */\n"
            r1.append(r3)
            r1.append(r0)
            r11 = r18
            r1.append(r11)
            r0 = 1
            java.lang.Object[] r3 = new java.lang.Object[r0]
            java.lang.Character r0 = java.lang.Character.valueOf(r23)
            r4 = 0
            r3[r4] = r0
            org.bytedeco.javacpp.tools.Token r0 = r8.expect(r3)
            java.lang.String r0 = r0.spacing
            r1.append(r0)
            r1.append(r10)
            java.lang.String r0 = r1.toString()
            r7.text = r0
            int r0 = r5.length()
            if (r0 <= 0) goto L_0x0b47
            org.bytedeco.javacpp.tools.InfoMap r0 = r2.infoMap
            org.bytedeco.javacpp.tools.Info r0 = r0.getFirst(r9)
            org.bytedeco.javacpp.tools.InfoMap r1 = r2.infoMap
            org.bytedeco.javacpp.tools.Info r3 = new org.bytedeco.javacpp.tools.Info
            r3.<init>((org.bytedeco.javacpp.tools.Info) r0)
            org.bytedeco.javacpp.tools.Info r0 = r3.cast()
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]
            r3 = 0
            r4[r3] = r5
            org.bytedeco.javacpp.tools.Info r0 = r0.cppNames(r4)
            r1.put(r0)
        L_0x0b47:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r7.text
            r0.append(r1)
            r10 = r16
            r0.append(r10)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            r7.text = r0
            goto L_0x08b9
        L_0x0b61:
            r0.add((org.bytedeco.javacpp.tools.Declaration) r7)
            org.bytedeco.javacpp.tools.TokenIndexer r0 = r2.tokens
            r0.next()
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.enumeration(org.bytedeco.javacpp.tools.Context, org.bytedeco.javacpp.tools.DeclarationList):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean namespace(Context context, DeclarationList declarationList) throws ParserException {
        if (!this.tokens.get().match(Token.NAMESPACE)) {
            return false;
        }
        Declaration declaration = new Declaration();
        String str = this.tokens.get().spacing;
        String str2 = null;
        this.tokens.next();
        if (this.tokens.get().match(5)) {
            str2 = this.tokens.get().value;
            this.tokens.next();
        }
        if (this.tokens.get().match('=')) {
            this.tokens.next();
            context.namespaceMap.put(str2, type(context).cppName);
            this.tokens.get().expect(';');
            this.tokens.next();
            return true;
        }
        this.tokens.get().expect('{');
        this.tokens.next();
        if (this.tokens.get().spacing.indexOf(10) < 0) {
            this.tokens.get().spacing = str;
        }
        Context context2 = new Context(context);
        if (str2 == null) {
            str2 = context2.namespace;
        } else if (context2.namespace != null) {
            str2 = context2.namespace + "::" + str2;
        }
        context2.namespace = str2;
        declarations(context2, declarationList);
        declaration.text += this.tokens.get().expect('}').spacing;
        this.tokens.next();
        declarationList.add(declaration);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean extern(Context context, DeclarationList declarationList) throws ParserException {
        if (this.tokens.get().match(Token.EXTERN)) {
            if (this.tokens.get(1).match(3)) {
                String str = this.tokens.get().spacing;
                Declaration declaration = new Declaration();
                this.tokens.next().expect("\"C\"", "\"C++\"");
                if (!this.tokens.next().match('{')) {
                    this.tokens.get().spacing = str;
                    declarationList.add(declaration);
                    return true;
                }
                this.tokens.next();
                declarations(context, declarationList);
                this.tokens.get().expect('}');
                this.tokens.next();
                declarationList.add(declaration);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void declarations(Context context, DeclarationList declarationList) throws ParserException {
        Context context2;
        Context context3 = context;
        DeclarationList declarationList2 = declarationList;
        while (true) {
            Token token = this.tokens.get();
            char c = 0;
            String str = "";
            if (!token.match(Token.EOF, '}')) {
                if (token.match(Token.PRIVATE, Token.PROTECTED, Token.PUBLIC)) {
                    if (this.tokens.get(1).match(':')) {
                        context3.inaccessible = !token.match(Token.PUBLIC);
                        this.tokens.next();
                        this.tokens.next();
                    }
                }
                String commentBefore = commentBefore();
                Token token2 = this.tokens.get();
                String str2 = token2.spacing;
                TemplateMap template = template(context);
                if (template != null) {
                    token2 = this.tokens.get();
                    token2.spacing = str2;
                    context2 = new Context(context3);
                    context2.templateMap = template;
                } else {
                    context2 = context3;
                }
                Declaration declaration = new Declaration();
                if (commentBefore != null && commentBefore.length() > 0) {
                    declaration.inaccessible = context2.inaccessible;
                    declaration.text = commentBefore;
                    declaration.comment = true;
                    declarationList2.add(declaration);
                }
                int i = this.tokens.index;
                declarationList2.infoMap = this.infoMap;
                declarationList2.context = context2;
                declarationList2.templateMap = template;
                declarationList2.infoIterator = null;
                declarationList2.spacing = null;
                while (true) {
                    if (template == null || declarationList2.infoIterator == null || !declarationList2.infoIterator.hasNext()) {
                        if (declarationList2.infoIterator != null && declarationList2.infoIterator.hasNext()) {
                            Info next = declarationList2.infoIterator.next();
                            if (next != null) {
                                if (next.cppNames != null && next.cppNames.length > 0 && next.cppNames[0].startsWith("const ") && next.pointerTypes != null && next.pointerTypes.length > 0) {
                                    Context context4 = new Context(context2);
                                    context4.constName = next.pointerTypes[0].substring(next.pointerTypes[0].lastIndexOf(" ") + 1);
                                    context4.constBaseName = next.base != null ? next.base : "Pointer";
                                    context2 = context4;
                                }
                                this.tokens.index = i;
                            }
                            if (declarationList2.infoIterator == null || !declarationList2.infoIterator.hasNext()) {
                                break;
                            }
                            c = 0;
                        }
                    } else {
                        Info next2 = declarationList2.infoIterator.next();
                        if (next2 != null) {
                            Type type = new Parser(this, next2.cppNames[c]).type(context3);
                            if (type.arguments != null) {
                                int i2 = 0;
                                for (Map.Entry entry : template.entrySet()) {
                                    if (i2 < type.arguments.length) {
                                        int i3 = i2 + 1;
                                        Type type2 = type.arguments[i2];
                                        String str3 = type2.cppName;
                                        if (type2.constValue && !str3.startsWith("const ")) {
                                            str3 = "const " + str3;
                                        }
                                        if (type2.indirections > 0) {
                                            for (int i4 = 0; i4 < type2.indirections; i4++) {
                                                str3 = str3 + Marker.ANY_MARKER;
                                            }
                                        }
                                        if (type2.reference) {
                                            str3 = str3 + "&";
                                        }
                                        if (type2.constPointer && !str3.endsWith(" const")) {
                                            str3 = str3 + " const";
                                        }
                                        type2.cppName = str3;
                                        entry.setValue(type2);
                                        i2 = i3;
                                    }
                                }
                                this.tokens.index = i;
                            }
                        }
                        c = 0;
                    }
                    if (!this.tokens.get().match(';') && !macro(context2, declarationList2) && !extern(context2, declarationList2) && !namespace(context2, declarationList2) && !enumeration(context2, declarationList2) && !group(context2, declarationList2) && !typedef(context2, declarationList2) && !using(context2, declarationList2) && !function(context2, declarationList2) && !variable(context2, declarationList2)) {
                        String str4 = this.tokens.get().spacing;
                        if (attribute() != null) {
                            this.tokens.get().spacing = str4;
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append(token2.file);
                            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
                            sb.append(token2.lineNumber);
                            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
                            if (token2.text != null) {
                                str = "\"" + token2.text + "\": ";
                            }
                            sb.append(str);
                            sb.append("Could not parse declaration at '");
                            sb.append(token2);
                            sb.append("'");
                            throw new ParserException(sb.toString());
                        }
                    }
                    while (true) {
                        if (!this.tokens.get().match(';')) {
                            break;
                        }
                        if (this.tokens.get().match(Token.EOF)) {
                            break;
                        }
                        this.tokens.next();
                    }
                    c = 0;
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(commentBefore());
                if (this.tokens.get().match(Token.EOF)) {
                    str = this.tokens.get().spacing;
                }
                sb2.append(str);
                String sb3 = sb2.toString();
                Declaration declaration2 = new Declaration();
                if (sb3 != null && sb3.length() > 0) {
                    declaration2.text = sb3;
                    declaration2.comment = true;
                    declarationList2.add(declaration2);
                    return;
                }
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void parse(Context context, DeclarationList declarationList, String[] strArr, String str, boolean z) throws IOException, ParserException {
        String str2;
        ArrayList arrayList = new ArrayList();
        if (str != null && str.length() != 0) {
            boolean z2 = true;
            File file = null;
            if (!str.startsWith("<") || !str.endsWith(">")) {
                File file2 = new File(str);
                if (file2.exists()) {
                    file = file2;
                }
                str2 = str;
            } else {
                str2 = str.substring(1, str.length() - 1);
            }
            if (file == null && strArr != null) {
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    File canonicalFile = new File(strArr[i], str2).getCanonicalFile();
                    if (canonicalFile.exists()) {
                        file = canonicalFile;
                        break;
                    }
                    i++;
                }
            }
            if (file == null) {
                file = new File(str2);
            }
            Info first = this.infoMap.getFirst(file.getName());
            if (first != null && first.skip && first.linePatterns == null) {
                return;
            }
            if (file.exists()) {
                Logger logger2 = this.logger;
                logger2.info("Parsing " + file);
                Token token = new Token();
                token.type = 4;
                token.value = "\n// Parsed from " + str + "\n\n";
                arrayList.add(token);
                Tokenizer tokenizer = new Tokenizer(file, this.encoding);
                if (!(first == null || first.linePatterns == null)) {
                    tokenizer.filterLines(first.linePatterns, first.skip);
                }
                while (true) {
                    Token nextToken = tokenizer.nextToken();
                    if (nextToken.isEmpty()) {
                        break;
                    }
                    if (nextToken.type == -1) {
                        nextToken.type = 4;
                    }
                    arrayList.add(nextToken);
                }
                if (this.lineSeparator == null) {
                    this.lineSeparator = tokenizer.lineSeparator;
                }
                tokenizer.close();
                Token token2 = new Token(Token.EOF);
                token2.spacing = "\n";
                token2.file = file;
                token2.lineNumber = ((Token) arrayList.get(arrayList.size() - 1)).lineNumber;
                arrayList.add(token2);
                this.tokens = new TokenIndexer(this.infoMap, (Token[]) arrayList.toArray(new Token[arrayList.size()]), z);
                boolean z3 = context.objectify;
                if (first == null || !first.objectify) {
                    z2 = false;
                }
                context.objectify = z3 | z2;
                declarations(context, declarationList);
                return;
            }
            throw new FileNotFoundException("Could not parse \"" + file + "\": File does not exist");
        }
    }

    public File[] parse(String str, String[] strArr, Class cls) throws IOException, ParserException {
        return parse(new File(str), strArr, cls);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: java.lang.String[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x065c A[Catch:{ all -> 0x06b6, all -> 0x06b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0661 A[Catch:{ all -> 0x06b6, all -> 0x06b9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.File[] parse(java.io.File r33, java.lang.String[] r34, java.lang.Class r35) throws java.io.IOException, org.bytedeco.javacpp.tools.ParserException {
        /*
            r32 = this;
            r7 = r32
            r0 = r33
            r8 = r35
            java.lang.String r9 = " "
            java.util.Properties r1 = r7.properties
            r10 = 1
            org.bytedeco.javacpp.ClassProperties r1 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class) r8, (java.util.Properties) r1, (boolean) r10)
            java.util.Properties r2 = r7.properties
            r11 = 0
            org.bytedeco.javacpp.ClassProperties r2 = org.bytedeco.javacpp.Loader.loadProperties((java.lang.Class) r8, (java.util.Properties) r2, (boolean) r11)
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.lang.String r3 = "platform.cinclude"
            java.util.List r4 = r2.get(r3)
            r12.addAll(r4)
            java.util.List r4 = r1.get(r3)
            r12.addAll(r4)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.lang.String r4 = "platform.include"
            java.util.List r5 = r2.get(r4)
            r13.addAll(r5)
            java.util.List r5 = r2.get(r3)
            r13.addAll(r5)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.List r4 = r1.get(r4)
            r14.addAll(r4)
            java.util.List r3 = r1.get(r3)
            r14.addAll(r3)
            java.lang.String r3 = "target"
            java.util.List r4 = r1.get(r3)
            java.lang.String r5 = "global"
            java.util.List r6 = r1.get(r5)
            java.util.List r3 = r2.get(r3)
            java.util.List r5 = r2.get(r5)
            java.lang.String r15 = "helper"
            java.util.List r2 = r2.get(r15)
            int r15 = r3.size()
            int r15 = r15 - r10
            java.lang.Object r3 = r3.get(r15)
            r15 = r3
            java.lang.String r15 = (java.lang.String) r15
            int r3 = r5.size()
            int r3 = r3 - r10
            java.lang.Object r3 = r5.get(r3)
            r5 = r3
            java.lang.String r5 = (java.lang.String) r5
            java.util.List r3 = r1.getInheritedClasses()
            org.bytedeco.javacpp.tools.InfoMap r10 = new org.bytedeco.javacpp.tools.InfoMap
            r10.<init>()
            r7.infoMap = r10
            java.util.Iterator r3 = r3.iterator()
        L_0x0094:
            boolean r10 = r3.hasNext()
            if (r10 == 0) goto L_0x00d1
            java.lang.Object r10 = r3.next()
            java.lang.Class r10 = (java.lang.Class) r10
            java.lang.Object r10 = r10.newInstance()     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c5 }
            org.bytedeco.javacpp.tools.InfoMapper r10 = (org.bytedeco.javacpp.tools.InfoMapper) r10     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c5 }
            boolean r11 = r10 instanceof org.bytedeco.javacpp.tools.BuildEnabled     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c5 }
            if (r11 == 0) goto L_0x00bb
            r11 = r10
            org.bytedeco.javacpp.tools.BuildEnabled r11 = (org.bytedeco.javacpp.tools.BuildEnabled) r11     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c5 }
            r16 = r3
            org.bytedeco.javacpp.tools.Logger r3 = r7.logger     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c7 }
            java.util.Properties r8 = r7.properties     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c7 }
            r17 = r9
            java.lang.String r9 = r7.encoding     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c9 }
            r11.init(r3, r8, r9)     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c9 }
            goto L_0x00bf
        L_0x00bb:
            r16 = r3
            r17 = r9
        L_0x00bf:
            org.bytedeco.javacpp.tools.InfoMap r3 = r7.infoMap     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c9 }
            r10.map(r3)     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00c9 }
            goto L_0x00c9
        L_0x00c5:
            r16 = r3
        L_0x00c7:
            r17 = r9
        L_0x00c9:
            r8 = r35
            r3 = r16
            r9 = r17
            r11 = 0
            goto L_0x0094
        L_0x00d1:
            r17 = r9
            org.bytedeco.javacpp.tools.InfoMap r3 = new org.bytedeco.javacpp.tools.InfoMap
            r3.<init>()
            r7.leafInfoMap = r3
            java.lang.Object r3 = r35.newInstance()     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            org.bytedeco.javacpp.tools.InfoMapper r3 = (org.bytedeco.javacpp.tools.InfoMapper) r3     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            boolean r8 = r3 instanceof org.bytedeco.javacpp.tools.BuildEnabled     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            if (r8 == 0) goto L_0x00f0
            r8 = r3
            org.bytedeco.javacpp.tools.BuildEnabled r8 = (org.bytedeco.javacpp.tools.BuildEnabled) r8     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            org.bytedeco.javacpp.tools.Logger r9 = r7.logger     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            java.util.Properties r10 = r7.properties     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            java.lang.String r11 = r7.encoding     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            r8.init(r9, r10, r11)     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
        L_0x00f0:
            org.bytedeco.javacpp.tools.InfoMap r8 = r7.leafInfoMap     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            r3.map(r8)     // Catch:{ ClassCastException | IllegalAccessException | InstantiationException -> 0x00f6 }
            goto L_0x00f7
        L_0x00f6:
        L_0x00f7:
            org.bytedeco.javacpp.tools.InfoMap r3 = r7.infoMap
            org.bytedeco.javacpp.tools.InfoMap r8 = r7.leafInfoMap
            r3.putAll(r8)
            java.lang.Class<org.bytedeco.javacpp.tools.Parser> r3 = org.bytedeco.javacpp.tools.Parser.class
            java.lang.Package r3 = r3.getPackage()
            java.lang.String r3 = r3.getImplementationVersion()
            if (r3 != 0) goto L_0x010c
            java.lang.String r3 = "unknown"
        L_0x010c:
            r8 = 46
            int r9 = r5.lastIndexOf(r8)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "// Targeted by JavaCPP version "
            r10.append(r11)
            r10.append(r3)
            java.lang.String r3 = ": DO NOT EDIT THIS FILE\n\n"
            r10.append(r3)
            java.lang.String r3 = r10.toString()
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            java.lang.String r11 = "package "
            r10.append(r11)
            r10.append(r15)
            java.lang.String r8 = ";\n\n"
            r10.append(r8)
            java.lang.String r10 = r10.toString()
            r18 = r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r3)
            java.lang.String r19 = ""
            if (r9 < 0) goto L_0x0169
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r11)
            r20 = r14
            r11 = 0
            java.lang.String r14 = r5.substring(r11, r9)
            r3.append(r14)
            r3.append(r8)
            java.lang.String r3 = r3.toString()
            goto L_0x016d
        L_0x0169:
            r20 = r14
            r3 = r19
        L_0x016d:
            r12.append(r3)
            java.lang.String r3 = r12.toString()
            org.bytedeco.javacpp.tools.InfoMap r8 = r7.leafInfoMap
            r11 = 0
            java.util.List r8 = r8.get(r11)
            java.util.Iterator r12 = r8.iterator()
            r11 = r19
            r14 = 0
        L_0x0182:
            boolean r21 = r12.hasNext()
            r22 = r8
            java.lang.String r8 = "import"
            r23 = r1
            java.lang.String r1 = "\n"
            if (r21 == 0) goto L_0x01cd
            java.lang.Object r21 = r12.next()
            r24 = r12
            r12 = r21
            org.bytedeco.javacpp.tools.Info r12 = (org.bytedeco.javacpp.tools.Info) r12
            if (r12 == 0) goto L_0x01a2
            boolean r7 = r12.objectify
            if (r7 == 0) goto L_0x01a2
            r7 = 1
            goto L_0x01a3
        L_0x01a2:
            r7 = 0
        L_0x01a3:
            r14 = r14 | r7
            java.lang.String r7 = r12.javaText
            if (r7 == 0) goto L_0x01c4
            java.lang.String r7 = r12.javaText
            boolean r7 = r7.startsWith(r8)
            if (r7 == 0) goto L_0x01c4
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r11)
            java.lang.String r8 = r12.javaText
            r7.append(r8)
            r7.append(r1)
            java.lang.String r11 = r7.toString()
        L_0x01c4:
            r7 = r32
            r8 = r22
            r1 = r23
            r12 = r24
            goto L_0x0182
        L_0x01cd:
            boolean r7 = r15.equals(r5)
            java.lang.String r12 = "import "
            if (r7 != 0) goto L_0x01f2
            boolean r7 = r10.equals(r3)
            if (r7 != 0) goto L_0x01f2
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            r7.append(r12)
            r7.append(r15)
            java.lang.String r3 = ".*;\n\n"
            r7.append(r3)
            java.lang.String r3 = r7.toString()
        L_0x01f2:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r11)
            java.lang.String r11 = "import java.nio.*;\nimport org.bytedeco.javacpp.*;\nimport org.bytedeco.javacpp.annotation.*;\n\n"
            r7.append(r11)
            java.lang.String r7 = r7.toString()
            r21 = r10
            r11 = 0
        L_0x0206:
            int r10 = r4.size()
            r24 = r8
            java.lang.String r8 = "import static "
            r25 = r14
            java.lang.String r14 = ".*;\n"
            if (r11 >= r10) goto L_0x027d
            java.lang.Object r10 = r4.get(r11)
            boolean r10 = r15.equals(r10)
            if (r10 != 0) goto L_0x0274
            java.lang.Object r10 = r4.get(r11)
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r0 = r6.get(r11)
            boolean r0 = r10.equals(r0)
            if (r0 == 0) goto L_0x024a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            r0.append(r8)
            java.lang.Object r7 = r4.get(r11)
            java.lang.String r7 = (java.lang.String) r7
            r0.append(r7)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            goto L_0x0273
        L_0x024a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            r0.append(r12)
            java.lang.Object r7 = r4.get(r11)
            java.lang.String r7 = (java.lang.String) r7
            r0.append(r7)
            java.lang.String r7 = ".*;\nimport static "
            r0.append(r7)
            java.lang.Object r7 = r6.get(r11)
            java.lang.String r7 = (java.lang.String) r7
            r0.append(r7)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
        L_0x0273:
            r7 = r0
        L_0x0274:
            int r11 = r11 + 1
            r0 = r33
            r8 = r24
            r14 = r25
            goto L_0x0206
        L_0x027d:
            int r0 = r4.size()
            r4 = 1
            if (r0 <= r4) goto L_0x0293
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            r0.append(r1)
            java.lang.String r7 = r0.toString()
        L_0x0293:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            r0.append(r7)
            java.lang.String r3 = "public class "
            r0.append(r3)
            r3 = 1
            int r9 = r9 + r3
            java.lang.String r3 = r5.substring(r9)
            r0.append(r3)
            java.lang.String r3 = " extends "
            r0.append(r3)
            int r3 = r2.size()
            if (r3 <= 0) goto L_0x02c5
            int r3 = r13.size()
            if (r3 <= 0) goto L_0x02c5
            r3 = 0
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x02c9
        L_0x02c5:
            java.lang.String r2 = r35.getCanonicalName()
        L_0x02c9:
            r0.append(r2)
            java.lang.String r2 = " {\n    static { Loader.load(); }\n"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            char r2 = java.io.File.separatorChar
            r3 = 46
            java.lang.String r2 = r15.replace(r3, r2)
            char r4 = java.io.File.separatorChar
            java.lang.String r4 = r5.replace(r3, r4)
            java.io.File r9 = new java.io.File
            r3 = r33
            r9.<init>(r3, r2)
            java.io.File r10 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r4)
            java.lang.String r11 = ".java"
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r10.<init>(r3, r2)
            r12 = r32
            org.bytedeco.javacpp.tools.Logger r2 = r12.logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "Targeting "
            r3.append(r6)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            r2.info(r3)
            org.bytedeco.javacpp.tools.Context r3 = new org.bytedeco.javacpp.tools.Context
            r3.<init>()
            org.bytedeco.javacpp.tools.InfoMap r2 = r12.infoMap
            r3.infoMap = r2
            r2 = r25
            r3.objectify = r2
            char r2 = java.io.File.separatorChar
            int r2 = r4.lastIndexOf(r2)
            if (r2 < 0) goto L_0x036c
            java.lang.Object r25 = r34.clone()
            r26 = r1
            r1 = r25
            java.lang.String[] r1 = (java.lang.String[]) r1
            r25 = r5
            r33 = r6
            r5 = 0
        L_0x033c:
            int r6 = r1.length
            if (r5 >= r6) goto L_0x0367
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r27 = r14
            r14 = r1[r5]
            r6.append(r14)
            java.lang.String r14 = java.io.File.separator
            r6.append(r14)
            r28 = r8
            r14 = 0
            java.lang.String r8 = r4.substring(r14, r2)
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r1[r5] = r6
            int r5 = r5 + 1
            r14 = r27
            r8 = r28
            goto L_0x033c
        L_0x0367:
            r28 = r8
            r27 = r14
            goto L_0x0378
        L_0x036c:
            r26 = r1
            r25 = r5
            r33 = r6
            r28 = r8
            r27 = r14
            r1 = r34
        L_0x0378:
            java.lang.String r2 = "platform.includepath"
            r4 = r23
            java.util.List r2 = r4.get(r2)
            java.lang.String r5 = "platform.includeresource"
            java.util.List r4 = r4.get(r5)
            java.util.Iterator r4 = r4.iterator()
        L_0x038a:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x03aa
            java.lang.Object r5 = r4.next()
            java.lang.String r5 = (java.lang.String) r5
            java.io.File[] r5 = org.bytedeco.javacpp.Loader.cacheResources(r5)
            int r6 = r5.length
            r8 = 0
        L_0x039c:
            if (r8 >= r6) goto L_0x038a
            r14 = r5[r8]
            java.lang.String r14 = r14.getCanonicalPath()
            r2.add(r14)
            int r8 = r8 + 1
            goto L_0x039c
        L_0x03aa:
            int r4 = r13.size()
            java.lang.String r8 = "Nothing targeted for "
            if (r4 != 0) goto L_0x03c8
            org.bytedeco.javacpp.tools.Logger r0 = r12.logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r8)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            r0.info(r1)
            r0 = 0
            return r0
        L_0x03c8:
            int r4 = r2.size()
            int r5 = r1.length
            int r4 = r4 + r5
            java.lang.String[] r4 = new java.lang.String[r4]
            java.lang.Object[] r4 = r2.toArray(r4)
            r14 = r4
            java.lang.String[] r14 = (java.lang.String[]) r14
            int r2 = r2.size()
            int r4 = r1.length
            r5 = 0
            java.lang.System.arraycopy(r1, r5, r14, r2, r4)
            org.bytedeco.javacpp.tools.DeclarationList r6 = new org.bytedeco.javacpp.tools.DeclarationList
            r6.<init>()
            java.util.Iterator r23 = r20.iterator()
        L_0x03e9:
            boolean r1 = r23.hasNext()
            if (r1 == 0) goto L_0x0446
            java.lang.Object r1 = r23.next()
            r5 = r1
            java.lang.String r5 = (java.lang.String) r5
            boolean r1 = r13.contains(r5)
            if (r1 != 0) goto L_0x0421
            r4 = r18
            boolean r18 = r4.contains(r5)
            r2 = r26
            r1 = r32
            r26 = r7
            r7 = r2
            r2 = r3
            r29 = r11
            r11 = r3
            r3 = r6
            r34 = r7
            r7 = r4
            r4 = r14
            r30 = r0
            r0 = r25
            r31 = r33
            r25 = r9
            r9 = r6
            r6 = r18
            r1.parse(r2, r3, r4, r5, r6)
            goto L_0x0433
        L_0x0421:
            r31 = r33
            r30 = r0
            r29 = r11
            r0 = r25
            r34 = r26
            r11 = r3
            r26 = r7
            r25 = r9
            r7 = r18
            r9 = r6
        L_0x0433:
            r18 = r7
            r6 = r9
            r3 = r11
            r9 = r25
            r7 = r26
            r11 = r29
            r33 = r31
            r26 = r34
            r25 = r0
            r0 = r30
            goto L_0x03e9
        L_0x0446:
            r31 = r33
            r30 = r0
            r29 = r11
            r0 = r25
            r34 = r26
            r11 = r3
            r26 = r7
            r25 = r9
            r7 = r18
            r9 = r6
            org.bytedeco.javacpp.tools.DeclarationList r6 = new org.bytedeco.javacpp.tools.DeclarationList
            r6.<init>(r9)
            int r1 = r13.size()
            if (r1 <= 0) goto L_0x0497
            r12.containers(r11, r6)
            java.util.Iterator r9 = r13.iterator()
        L_0x046a:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L_0x0497
            java.lang.Object r1 = r9.next()
            r5 = r1
            java.lang.String r5 = (java.lang.String) r5
            r13 = r20
            boolean r1 = r13.contains(r5)
            if (r1 == 0) goto L_0x0490
            boolean r18 = r7.contains(r5)
            r1 = r32
            r2 = r11
            r3 = r6
            r4 = r14
            r20 = r6
            r6 = r18
            r1.parse(r2, r3, r4, r5, r6)
            goto L_0x0492
        L_0x0490:
            r20 = r6
        L_0x0492:
            r6 = r20
            r20 = r13
            goto L_0x046a
        L_0x0497:
            r20 = r6
            int r1 = r20.size()
            if (r1 != 0) goto L_0x04b5
            org.bytedeco.javacpp.tools.Logger r0 = r12.logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r8)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            r0.info(r1)
            r1 = 0
            return r1
        L_0x04b5:
            r1 = 0
            java.io.File r2 = r10.getParentFile()
            boolean r3 = r15.equals(r0)
            if (r3 != 0) goto L_0x04c3
            r25.mkdirs()
        L_0x04c3:
            if (r2 == 0) goto L_0x04c8
            r2.mkdirs()
        L_0x04c8:
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 1
            java.io.File[] r5 = new java.io.File[r4]
            r4 = 0
            r5[r4] = r10
            java.util.List r4 = java.util.Arrays.asList(r5)
            r3.<init>(r4)
            java.lang.String r4 = r12.encoding
            if (r4 == 0) goto L_0x04e5
            org.bytedeco.javacpp.tools.EncodingFileWriter r4 = new org.bytedeco.javacpp.tools.EncodingFileWriter
            java.lang.String r5 = r12.encoding
            java.lang.String r6 = r12.lineSeparator
            r4.<init>(r10, r5, r6)
            goto L_0x04ec
        L_0x04e5:
            org.bytedeco.javacpp.tools.EncodingFileWriter r4 = new org.bytedeco.javacpp.tools.EncodingFileWriter
            java.lang.String r5 = r12.lineSeparator
            r4.<init>(r10, r5)
        L_0x04ec:
            r5 = r30
            r4.append(r5)     // Catch:{ all -> 0x06b6 }
            java.util.Iterator r5 = r22.iterator()     // Catch:{ all -> 0x06b6 }
        L_0x04f5:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x06b6 }
            if (r6 == 0) goto L_0x0532
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x06b6 }
            org.bytedeco.javacpp.tools.Info r6 = (org.bytedeco.javacpp.tools.Info) r6     // Catch:{ all -> 0x06b6 }
            java.lang.String r7 = r6.javaText     // Catch:{ all -> 0x06b6 }
            if (r7 == 0) goto L_0x0529
            java.lang.String r7 = r6.javaText     // Catch:{ all -> 0x06b6 }
            r8 = r24
            boolean r7 = r7.startsWith(r8)     // Catch:{ all -> 0x06b6 }
            if (r7 != 0) goto L_0x0526
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x06b6 }
            r7.<init>()     // Catch:{ all -> 0x06b6 }
            java.lang.String r6 = r6.javaText     // Catch:{ all -> 0x06b6 }
            r7.append(r6)     // Catch:{ all -> 0x06b6 }
            r6 = r34
            r7.append(r6)     // Catch:{ all -> 0x06b6 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x06b6 }
            r4.append(r7)     // Catch:{ all -> 0x06b6 }
            goto L_0x052d
        L_0x0526:
            r6 = r34
            goto L_0x052d
        L_0x0529:
            r6 = r34
            r8 = r24
        L_0x052d:
            r34 = r6
            r24 = r8
            goto L_0x04f5
        L_0x0532:
            r6 = r34
            java.util.Iterator r5 = r20.iterator()     // Catch:{ all -> 0x06b6 }
            r7 = r1
        L_0x0539:
            boolean r8 = r5.hasNext()     // Catch:{ all -> 0x06b6 }
            if (r8 == 0) goto L_0x0696
            java.lang.Object r8 = r5.next()     // Catch:{ all -> 0x06b6 }
            org.bytedeco.javacpp.tools.Declaration r8 = (org.bytedeco.javacpp.tools.Declaration) r8     // Catch:{ all -> 0x06b6 }
            boolean r9 = r15.equals(r0)     // Catch:{ all -> 0x06b6 }
            if (r9 != 0) goto L_0x066e
            org.bytedeco.javacpp.tools.Type r9 = r8.type     // Catch:{ all -> 0x06b6 }
            if (r9 == 0) goto L_0x066e
            org.bytedeco.javacpp.tools.Type r9 = r8.type     // Catch:{ all -> 0x06b6 }
            java.lang.String r9 = r9.javaName     // Catch:{ all -> 0x06b6 }
            if (r9 == 0) goto L_0x066e
            org.bytedeco.javacpp.tools.Type r9 = r8.type     // Catch:{ all -> 0x06b6 }
            java.lang.String r9 = r9.javaName     // Catch:{ all -> 0x06b6 }
            int r9 = r9.length()     // Catch:{ all -> 0x06b6 }
            if (r9 <= 0) goto L_0x066e
            org.bytedeco.javacpp.tools.Type r9 = r8.type     // Catch:{ all -> 0x06b6 }
            java.lang.String r9 = r9.javaName     // Catch:{ all -> 0x06b6 }
            org.bytedeco.javacpp.tools.Type r10 = r8.type     // Catch:{ all -> 0x06b6 }
            java.lang.String r10 = r10.javaName     // Catch:{ all -> 0x06b6 }
            r11 = 46
            int r10 = r10.lastIndexOf(r11)     // Catch:{ all -> 0x06b6 }
            r13 = 1
            int r10 = r10 + r13
            java.lang.String r9 = r9.substring(r10)     // Catch:{ all -> 0x06b6 }
            java.io.File r10 = new java.io.File     // Catch:{ all -> 0x06b6 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x06b6 }
            r14.<init>()     // Catch:{ all -> 0x06b6 }
            r14.append(r9)     // Catch:{ all -> 0x06b6 }
            r1 = r29
            r14.append(r1)     // Catch:{ all -> 0x06b6 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x06b6 }
            r11 = r25
            r10.<init>(r11, r14)     // Catch:{ all -> 0x06b6 }
            if (r7 == 0) goto L_0x0596
            boolean r14 = r7.comment     // Catch:{ all -> 0x06b6 }
            if (r14 != 0) goto L_0x0596
            java.lang.String r14 = r7.text     // Catch:{ all -> 0x06b6 }
            r4.append(r14)     // Catch:{ all -> 0x06b6 }
        L_0x0596:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x06b6 }
            r14.<init>()     // Catch:{ all -> 0x06b6 }
            java.lang.String r13 = "\n// Targeting "
            r14.append(r13)     // Catch:{ all -> 0x06b6 }
            java.nio.file.Path r13 = r2.toPath()     // Catch:{ all -> 0x06b6 }
            r29 = r1
            java.nio.file.Path r1 = r10.toPath()     // Catch:{ all -> 0x06b6 }
            java.nio.file.Path r1 = r13.relativize(r1)     // Catch:{ all -> 0x06b6 }
            r14.append(r1)     // Catch:{ all -> 0x06b6 }
            java.lang.String r1 = "\n\n"
            r14.append(r1)     // Catch:{ all -> 0x06b6 }
            java.lang.String r1 = r14.toString()     // Catch:{ all -> 0x06b6 }
            r4.append(r1)     // Catch:{ all -> 0x06b6 }
            org.bytedeco.javacpp.tools.Logger r1 = r12.logger     // Catch:{ all -> 0x06b6 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x06b6 }
            r13.<init>()     // Catch:{ all -> 0x06b6 }
            r14 = r31
            r13.append(r14)     // Catch:{ all -> 0x06b6 }
            r13.append(r10)     // Catch:{ all -> 0x06b6 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x06b6 }
            r1.info(r13)     // Catch:{ all -> 0x06b6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x06b6 }
            r1.<init>()     // Catch:{ all -> 0x06b6 }
            r13 = r21
            r1.append(r13)     // Catch:{ all -> 0x06b6 }
            r33 = r2
            r2 = r26
            r1.append(r2)     // Catch:{ all -> 0x06b6 }
            r26 = r2
            r2 = r28
            r1.append(r2)     // Catch:{ all -> 0x06b6 }
            r1.append(r0)     // Catch:{ all -> 0x06b6 }
            r25 = r0
            r0 = r27
            r1.append(r0)     // Catch:{ all -> 0x06b6 }
            if (r7 == 0) goto L_0x0600
            r27 = r0
            boolean r0 = r7.comment     // Catch:{ all -> 0x06b6 }
            if (r0 == 0) goto L_0x0602
            java.lang.String r0 = r7.text     // Catch:{ all -> 0x06b6 }
            goto L_0x0604
        L_0x0600:
            r27 = r0
        L_0x0602:
            r0 = r19
        L_0x0604:
            r1.append(r0)     // Catch:{ all -> 0x06b6 }
            java.lang.String r0 = r8.text     // Catch:{ all -> 0x06b6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x06b6 }
            r7.<init>()     // Catch:{ all -> 0x06b6 }
            java.lang.String r8 = "public static class "
            r7.append(r8)     // Catch:{ all -> 0x06b6 }
            r7.append(r9)     // Catch:{ all -> 0x06b6 }
            r8 = r17
            r7.append(r8)     // Catch:{ all -> 0x06b6 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x06b6 }
            r28 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x06b6 }
            r2.<init>()     // Catch:{ all -> 0x06b6 }
            r34 = r5
            java.lang.String r5 = "@Properties(inherit = "
            r2.append(r5)     // Catch:{ all -> 0x06b6 }
            java.lang.String r5 = r35.getCanonicalName()     // Catch:{ all -> 0x06b6 }
            r2.append(r5)     // Catch:{ all -> 0x06b6 }
            java.lang.String r5 = ".class)\npublic class "
            r2.append(r5)     // Catch:{ all -> 0x06b6 }
            r2.append(r9)     // Catch:{ all -> 0x06b6 }
            r2.append(r8)     // Catch:{ all -> 0x06b6 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x06b6 }
            java.lang.String r0 = r0.replace(r7, r2)     // Catch:{ all -> 0x06b6 }
            r1.append(r0)     // Catch:{ all -> 0x06b6 }
            r1.append(r6)     // Catch:{ all -> 0x06b6 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x06b6 }
            r3.add(r10)     // Catch:{ all -> 0x06b6 }
            java.nio.file.Path r1 = r10.toPath()     // Catch:{ all -> 0x06b6 }
            java.lang.String r2 = r12.encoding     // Catch:{ all -> 0x06b6 }
            if (r2 == 0) goto L_0x0661
            byte[] r0 = r0.getBytes(r2)     // Catch:{ all -> 0x06b6 }
            goto L_0x0665
        L_0x0661:
            byte[] r0 = r0.getBytes()     // Catch:{ all -> 0x06b6 }
        L_0x0665:
            r2 = 0
            java.nio.file.OpenOption[] r5 = new java.nio.file.OpenOption[r2]     // Catch:{ all -> 0x06b6 }
            java.nio.file.Files.write(r1, r0, r5)     // Catch:{ all -> 0x06b6 }
            r0 = r8
            r7 = 0
            goto L_0x0685
        L_0x066e:
            r33 = r2
            r34 = r5
            r13 = r21
            r11 = r25
            r14 = r31
            r2 = 0
            r25 = r0
            r0 = r17
            if (r7 == 0) goto L_0x0684
            java.lang.String r1 = r7.text     // Catch:{ all -> 0x06b6 }
            r4.append(r1)     // Catch:{ all -> 0x06b6 }
        L_0x0684:
            r7 = r8
        L_0x0685:
            r2 = r33
            r5 = r34
            r17 = r0
            r21 = r13
            r31 = r14
            r0 = r25
            r1 = 0
            r25 = r11
            goto L_0x0539
        L_0x0696:
            if (r7 == 0) goto L_0x069d
            java.lang.String r0 = r7.text     // Catch:{ all -> 0x06b6 }
            r4.append(r0)     // Catch:{ all -> 0x06b6 }
        L_0x069d:
            java.lang.String r0 = "\n}\n"
            java.io.Writer r0 = r4.append(r0)     // Catch:{ all -> 0x06b6 }
            r0.close()     // Catch:{ all -> 0x06b6 }
            r4.close()
            int r0 = r3.size()
            java.io.File[] r0 = new java.io.File[r0]
            java.lang.Object[] r0 = r3.toArray(r0)
            java.io.File[] r0 = (java.io.File[]) r0
            return r0
        L_0x06b6:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x06b9 }
        L_0x06b9:
            r0 = move-exception
            r2 = r0
            r4.close()     // Catch:{ all -> 0x06bf }
            goto L_0x06c4
        L_0x06bf:
            r0 = move-exception
            r3 = r0
            r1.addSuppressed(r3)
        L_0x06c4:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bytedeco.javacpp.tools.Parser.parse(java.io.File, java.lang.String[], java.lang.Class):java.io.File[]");
    }
}
