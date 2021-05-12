package fm.liveswitch;

import com.microsoft.appcenter.Constants;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import okhttp3.HttpUrl;

public class JsonSerializer {
    private static char intToHex(int i) {
        return (char) (i <= 9 ? i + 48 : (i - 10) + 97);
    }

    public static String serializeRaw(String str) {
        return str;
    }

    private static String charToUnicodeString(int i) {
        return StringExtensions.concat((Object[]) new String[]{"\\u", CharacterExtensions.toString(Character.valueOf(intToHex((i >> 12) & 15))), CharacterExtensions.toString(Character.valueOf(intToHex((i >> 8) & 15))), CharacterExtensions.toString(Character.valueOf(intToHex((i >> 4) & 15))), CharacterExtensions.toString(Character.valueOf(intToHex(i & 15)))});
    }

    public static NullableBoolean deserializeBoolean(String str) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            String trim = str.trim();
            if (Global.equals(trim, "null")) {
                return new NullableBoolean((Boolean) null);
            }
            BooleanHolder booleanHolder = new BooleanHolder(false);
            boolean tryParseBooleanValue = ParseAssistant.tryParseBooleanValue(trim, booleanHolder);
            boolean value = booleanHolder.getValue();
            if (tryParseBooleanValue) {
                return new NullableBoolean(value);
            }
        }
        return new NullableBoolean((Boolean) null);
    }

    public static boolean[] deserializeBooleanArray(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        if (trim.charAt(0) != '[') {
            return null;
        }
        String substring = StringExtensions.substring(trim, 1, StringExtensions.getLength(trim) - 2);
        if (StringExtensions.isNullOrEmpty(substring)) {
            return new boolean[0];
        }
        String[] split = StringExtensions.split(substring, new char[]{','});
        boolean[] zArr = new boolean[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            zArr[i] = deserializeBoolean(split[i].trim()).getValue();
        }
        return zArr;
    }

    public static NullableBigDecimal deserializeDecimal(String str) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            String trim = str.trim();
            if (Global.equals(trim, "null")) {
                return new NullableBigDecimal((BigDecimal) null);
            }
            Holder holder = new Holder(new BigDecimal("0"));
            boolean tryParseDecimalValue = ParseAssistant.tryParseDecimalValue(trim, holder);
            BigDecimal bigDecimal = (BigDecimal) holder.getValue();
            if (tryParseDecimalValue) {
                return new NullableBigDecimal(bigDecimal);
            }
        }
        return new NullableBigDecimal((BigDecimal) null);
    }

    public static BigDecimal[] deserializeDecimalArray(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        if (trim.charAt(0) != '[') {
            return null;
        }
        String substring = StringExtensions.substring(trim, 1, StringExtensions.getLength(trim) - 2);
        if (StringExtensions.isNullOrEmpty(substring)) {
            return new BigDecimal[0];
        }
        String[] split = StringExtensions.split(substring, new char[]{','});
        BigDecimal[] bigDecimalArr = new BigDecimal[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            bigDecimalArr[i] = deserializeDecimal(split[i].trim()).getValue();
        }
        return bigDecimalArr;
    }

    public static <T> HashMap<String, T> deserializeDictionary(String str, IFunction0<HashMap<String, T>> iFunction0, final IFunction1<String, T> iFunction1) {
        return (HashMap) deserializeObject(str, iFunction0, new IAction3<HashMap<String, T>, String, String>() {
            public void invoke(HashMap<String, T> hashMap, String str, String str2) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), str, iFunction1.invoke(str2));
            }
        });
    }

    public static NullableDouble deserializeDouble(String str) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            String trim = str.trim();
            if (Global.equals(trim, "null")) {
                return new NullableDouble((Double) null);
            }
            DoubleHolder doubleHolder = new DoubleHolder(0.0d);
            boolean tryParseDoubleValue = ParseAssistant.tryParseDoubleValue(trim, doubleHolder);
            double value = doubleHolder.getValue();
            if (tryParseDoubleValue) {
                return new NullableDouble(value);
            }
        }
        return new NullableDouble((Double) null);
    }

    public static double[] deserializeDoubleArray(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        if (trim.charAt(0) != '[') {
            return null;
        }
        String substring = StringExtensions.substring(trim, 1, StringExtensions.getLength(trim) - 2);
        if (StringExtensions.isNullOrEmpty(substring)) {
            return new double[0];
        }
        String[] split = StringExtensions.split(substring, new char[]{','});
        double[] dArr = new double[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            dArr[i] = deserializeDouble(split[i].trim()).getValue();
        }
        return dArr;
    }

    public static NullableFloat deserializeFloat(String str) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            String trim = str.trim();
            if (Global.equals(trim, "null")) {
                return new NullableFloat((Float) null);
            }
            FloatHolder floatHolder = new FloatHolder(0.0f);
            boolean tryParseFloatValue = ParseAssistant.tryParseFloatValue(trim, floatHolder);
            float value = floatHolder.getValue();
            if (tryParseFloatValue) {
                return new NullableFloat(value);
            }
        }
        return new NullableFloat((Float) null);
    }

    public static float[] deserializeFloatArray(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        if (trim.charAt(0) != '[') {
            return null;
        }
        String substring = StringExtensions.substring(trim, 1, StringExtensions.getLength(trim) - 2);
        if (StringExtensions.isNullOrEmpty(substring)) {
            return new float[0];
        }
        String[] split = StringExtensions.split(substring, new char[]{','});
        float[] fArr = new float[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            fArr[i] = deserializeFloat(split[i].trim()).getValue();
        }
        return fArr;
    }

    public static NullableGuid deserializeGuid(String str) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            String trim = str.trim();
            if (Global.equals(trim, "null")) {
                return new NullableGuid((Guid) null);
            }
            Holder holder = new Holder(Guid.empty);
            boolean tryParseGuidValue = ParseAssistant.tryParseGuidValue(deserializeString(trim), holder);
            Guid guid = (Guid) holder.getValue();
            if (tryParseGuidValue) {
                return new NullableGuid(guid);
            }
        }
        return new NullableGuid((Guid) null);
    }

    public static Guid[] deserializeGuidArray(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        if (trim.charAt(0) != '[') {
            return null;
        }
        String substring = StringExtensions.substring(trim, 1, StringExtensions.getLength(trim) - 2);
        if (StringExtensions.isNullOrEmpty(substring)) {
            return new Guid[0];
        }
        String[] split = StringExtensions.split(substring, new char[]{','});
        Guid[] guidArr = new Guid[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            guidArr[i] = deserializeGuid(split[i].trim()).getValue();
        }
        return guidArr;
    }

    public static NullableInteger deserializeInteger(String str) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            String trim = str.trim();
            if (Global.equals(trim, "null")) {
                return new NullableInteger((Integer) null);
            }
            IntegerHolder integerHolder = new IntegerHolder(0);
            boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(trim, integerHolder);
            int value = integerHolder.getValue();
            if (tryParseIntegerValue) {
                return new NullableInteger(value);
            }
        }
        return new NullableInteger((Integer) null);
    }

    public static int[] deserializeIntegerArray(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        if (trim.charAt(0) != '[') {
            return null;
        }
        String substring = StringExtensions.substring(trim, 1, StringExtensions.getLength(trim) - 2);
        if (StringExtensions.isNullOrEmpty(substring)) {
            return new int[0];
        }
        String[] split = StringExtensions.split(substring, new char[]{','});
        int[] iArr = new int[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            iArr[i] = deserializeInteger(split[i].trim()).getValue();
        }
        return iArr;
    }

    public static NullableLong deserializeLong(String str) {
        if (!StringExtensions.isNullOrEmpty(str)) {
            String trim = str.trim();
            if (Global.equals(trim, "null")) {
                return new NullableLong((Long) null);
            }
            LongHolder longHolder = new LongHolder(0);
            boolean tryParseLongValue = ParseAssistant.tryParseLongValue(trim, longHolder);
            long value = longHolder.getValue();
            if (tryParseLongValue) {
                return new NullableLong(value);
            }
        }
        return new NullableLong((Long) null);
    }

    public static long[] deserializeLongArray(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        if (trim.charAt(0) != '[') {
            return null;
        }
        String substring = StringExtensions.substring(trim, 1, StringExtensions.getLength(trim) - 2);
        if (StringExtensions.isNullOrEmpty(substring)) {
            return new long[0];
        }
        String[] split = StringExtensions.split(substring, new char[]{','});
        long[] jArr = new long[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) split); i++) {
            jArr[i] = deserializeLong(split[i].trim()).getValue();
        }
        return jArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00e7 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T deserializeObject(java.lang.String r16, fm.liveswitch.IFunction0<T> r17, fm.liveswitch.IAction3<T, java.lang.String, java.lang.String> r18) {
        /*
            r0 = 0
            if (r16 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = r16.trim()
            java.lang.String r2 = "null"
            boolean r2 = fm.liveswitch.Global.equals(r1, r2)
            if (r2 != 0) goto L_0x00ef
            int r2 = fm.liveswitch.StringExtensions.getLength(r1)
            r3 = 2
            if (r2 >= r3) goto L_0x0019
            goto L_0x00ef
        L_0x0019:
            java.lang.Object r0 = r17.invoke()
            r2 = 0
            char r4 = r1.charAt(r2)
            r5 = 123(0x7b, float:1.72E-43)
            if (r4 != r5) goto L_0x00ef
            int r4 = fm.liveswitch.StringExtensions.getLength(r1)
            r6 = 1
            int r4 = r4 - r6
            char r4 = r1.charAt(r4)
            r7 = 125(0x7d, float:1.75E-43)
            if (r4 != r7) goto L_0x00ef
            int r4 = fm.liveswitch.StringExtensions.getLength(r1)
            if (r4 <= r3) goto L_0x00ef
            int r4 = fm.liveswitch.StringExtensions.getLength(r1)
            int r4 = r4 - r3
            java.lang.String r1 = fm.liveswitch.StringExtensions.substring(r1, r6, r4)
            java.lang.String r3 = ","
            java.lang.String r1 = fm.liveswitch.StringExtensions.concat(r1, r3)
            r3 = -2
            fm.liveswitch.StringType r4 = fm.liveswitch.StringType.None
            java.lang.String r8 = fm.liveswitch.StringExtensions.empty
            java.lang.String r9 = fm.liveswitch.StringExtensions.empty
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0056:
            int r15 = fm.liveswitch.StringExtensions.getLength(r1)
            if (r9 >= r15) goto L_0x00ef
            char r15 = r1.charAt(r9)
            int r2 = r9 + -1
            if (r3 != r2) goto L_0x0066
            r2 = 1
            goto L_0x0067
        L_0x0066:
            r2 = 0
        L_0x0067:
            if (r10 != 0) goto L_0x0082
            r6 = 91
            if (r15 != r6) goto L_0x0071
            int r11 = r11 + 1
        L_0x006f:
            r6 = 1
            goto L_0x0083
        L_0x0071:
            r6 = 93
            if (r15 != r6) goto L_0x0078
            int r11 = r11 + -1
            goto L_0x006f
        L_0x0078:
            if (r15 != r5) goto L_0x007d
            int r12 = r12 + 1
            goto L_0x006f
        L_0x007d:
            if (r15 != r7) goto L_0x0082
            int r12 = r12 + -1
            goto L_0x006f
        L_0x0082:
            r6 = 0
        L_0x0083:
            if (r6 != 0) goto L_0x00e7
            if (r10 == 0) goto L_0x008f
            r5 = 92
            if (r15 != r5) goto L_0x008f
            if (r2 != 0) goto L_0x008f
            r3 = r9
            r6 = 1
        L_0x008f:
            if (r6 != 0) goto L_0x00e7
            if (r12 != 0) goto L_0x00c6
            if (r11 != 0) goto L_0x00c6
            r5 = 44
            if (r15 != r5) goto L_0x00b3
            if (r10 != 0) goto L_0x00b1
            int r5 = r9 - r13
            java.lang.String r5 = fm.liveswitch.StringExtensions.substring(r1, r13, r5)
            java.lang.String r5 = r5.trim()
            java.lang.String r6 = deserializeString(r8)
            r14 = r18
            r14.invoke(r0, r6, r5)
            int r5 = r9 + 1
            r14 = r5
        L_0x00b1:
            r6 = 1
            goto L_0x00c6
        L_0x00b3:
            r5 = 58
            if (r15 != r5) goto L_0x00c6
            if (r10 != 0) goto L_0x00b1
            int r5 = r9 - r14
            java.lang.String r5 = fm.liveswitch.StringExtensions.substring(r1, r14, r5)
            java.lang.String r8 = r5.trim()
            int r13 = r9 + 1
            goto L_0x00b1
        L_0x00c6:
            if (r6 != 0) goto L_0x00e7
            r5 = 39
            r6 = 34
            if (r15 == r5) goto L_0x00d0
            if (r15 != r6) goto L_0x00e7
        L_0x00d0:
            if (r2 != 0) goto L_0x00e7
            if (r15 != r6) goto L_0x00d7
            fm.liveswitch.StringType r2 = fm.liveswitch.StringType.Double
            goto L_0x00d9
        L_0x00d7:
            fm.liveswitch.StringType r2 = fm.liveswitch.StringType.Single
        L_0x00d9:
            if (r10 != 0) goto L_0x00de
            r4 = r2
            r10 = 1
            goto L_0x00e7
        L_0x00de:
            boolean r2 = fm.liveswitch.Global.equals(r4, r2)
            if (r2 == 0) goto L_0x00e7
            fm.liveswitch.StringType r4 = fm.liveswitch.StringType.None
            r10 = 0
        L_0x00e7:
            int r9 = r9 + 1
            r2 = 0
            r5 = 123(0x7b, float:1.72E-43)
            r6 = 1
            goto L_0x0056
        L_0x00ef:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.JsonSerializer.deserializeObject(java.lang.String, fm.liveswitch.IFunction0, fm.liveswitch.IAction3):java.lang.Object");
    }

    public static <T> ArrayList<T> deserializeObjectArray(String str, IFunction1<String, T> iFunction1) {
        ArrayList<String> deserializeRawArray = deserializeRawArray(str);
        if (deserializeRawArray == null) {
            return null;
        }
        ArrayList<T> arrayList = new ArrayList<>(ArrayListExtensions.getCount(deserializeRawArray));
        Iterator<String> it = deserializeRawArray.iterator();
        while (it.hasNext()) {
            arrayList.add(iFunction1.invoke(it.next()));
        }
        return arrayList;
    }

    public static <T extends Serializable> T deserializeObjectFast(String str, IFunction0<T> iFunction0, IAction3<T, String, String> iAction3) {
        T t = (Serializable) deserializeObject(str, iFunction0, iAction3);
        if (t != null) {
            t.setSerialized(str);
        }
        return t;
    }

    public static String deserializeRaw(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        return str.trim();
    }

    public static ArrayList<String> deserializeRawArray(String str) {
        ArrayList<String> arrayList = null;
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (!Global.equals(trim, "null") && StringExtensions.getLength(trim) >= 2) {
            arrayList = new ArrayList<>();
            if (trim.charAt(0) == '[' && trim.charAt(StringExtensions.getLength(trim) - 1) == ']' && StringExtensions.getLength(trim) > 2) {
                int i = -2;
                StringType stringType = StringType.None;
                boolean z = false;
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 1; i4 < StringExtensions.getLength(trim) - 1; i4++) {
                    char charAt = trim.charAt(i4);
                    boolean z2 = i == i4 + -1;
                    if (!z) {
                        if (charAt == '{') {
                            if (i2 == 0) {
                                i3 = i4;
                            }
                            i2++;
                        } else if (charAt == '}' && i2 - 1 == 0) {
                            arrayList.add(StringExtensions.substring(trim, i3, (i4 - i3) + 1));
                        }
                    }
                    if (z && charAt == '\\' && !z2) {
                        i = i4;
                    } else if ((charAt == '\'' || charAt == '\"') && !z2) {
                        StringType stringType2 = charAt == '\"' ? StringType.Double : StringType.Single;
                        if (!z) {
                            stringType = stringType2;
                            z = true;
                        } else if (Global.equals(stringType, stringType2)) {
                            stringType = StringType.None;
                            z = false;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static String deserializeString(String str) {
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        String trim = str.trim();
        if (Global.equals(trim, "null")) {
            return null;
        }
        boolean z = false;
        boolean z2 = trim.startsWith("\"") || trim.startsWith("'");
        if (trim.endsWith("\"") || trim.endsWith("'")) {
            z = true;
        }
        if (!z2 || !z) {
            return null;
        }
        return unescapeString(trimQuotes(trim));
    }

    public static String[] deserializeStringArray(String str) {
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String str3 = null;
        String str4 = "array-start";
        int i = 0;
        while (i < StringExtensions.getLength(str)) {
            char charAt = str2.charAt(i);
            if (!str4.equals("array-start")) {
                if (!str4.equals("value-start")) {
                    if (str4.equals("in-null-n")) {
                        if (charAt != 'u') {
                            return null;
                        }
                        str4 = "in-null-nu";
                    } else if (str4.equals("in-null-nu")) {
                        if (charAt != 'l') {
                            return null;
                        }
                        str4 = "in-null-nul";
                    } else if (str4.equals("in-null-nul")) {
                        if (charAt != 'l') {
                            return null;
                        }
                        arrayList.add((Object) null);
                        str4 = "value-end";
                    } else if (str4.equals("in-string")) {
                        if (charAt == '\"') {
                            arrayList.add(unescapeString(trimQuotes(str3.trim())));
                            str4 = "value-end";
                            str3 = null;
                        } else if (charAt == '\\') {
                            str3 = StringExtensions.concat(str3, "\\");
                            str4 = "in-string-escape";
                        } else {
                            str3 = StringExtensions.concat(str3, CharacterExtensions.toString(Character.valueOf(charAt)));
                        }
                    } else if (str4.equals("in-string-escape")) {
                        str3 = StringExtensions.concat(str3, CharacterExtensions.toString(Character.valueOf(charAt)));
                        str4 = "in-string";
                    } else if (str4.equals("value-end")) {
                        if (charAt != ',') {
                            if (charAt != ']') {
                                if (!(charAt == ' ' || charAt == 9 || charAt == 10 || charAt == 13)) {
                                    return null;
                                }
                            }
                        }
                    } else if (!(!Global.equals(str4, "array-end") || charAt == ' ' || charAt == 9 || charAt == 10 || charAt == 13)) {
                        return null;
                    }
                    i++;
                    str2 = str;
                } else if (charAt != ']') {
                    if (charAt == '\"') {
                        str3 = "";
                        str4 = "in-string";
                        i++;
                        str2 = str;
                    } else if (charAt == 'n') {
                        str4 = "in-null-n";
                        str3 = null;
                        i++;
                        str2 = str;
                    } else {
                        if (!(charAt == ' ' || charAt == 9 || charAt == 10 || charAt == 13)) {
                            return null;
                        }
                        i++;
                        str2 = str;
                    }
                }
                str4 = "array-end";
                i++;
                str2 = str;
            } else if (charAt != '[') {
                if (!(charAt == ' ' || charAt != 9 || charAt == 10 || charAt == 13)) {
                    return null;
                }
                i++;
                str2 = str;
            }
            str4 = "value-start";
            i++;
            str2 = str;
        }
        if (!Global.equals(str4, "array-end")) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static String escapeString(String str) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        for (int i2 = 0; i2 < StringExtensions.getLength(str); i2++) {
            char charAt = str.charAt(i2);
            if (charAt > '~') {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, charToUnicodeString(charAt));
            } else if (charAt == 8) {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\b");
            } else if (charAt == 12) {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\f");
            } else if (charAt == 10) {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\n");
            } else if (charAt == 13) {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\r");
            } else if (charAt == 9) {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\t");
            } else if (charAt == '\"') {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\\"");
            } else if (charAt == '/') {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\/");
            } else if (charAt == '\\') {
                if (i != -1) {
                    StringBuilderExtensions.append(sb, str, i, i2 - i);
                    i = -1;
                }
                StringBuilderExtensions.append(sb, "\\\\");
            } else if (i == -1) {
                i = i2;
            }
        }
        if (i != -1) {
            StringBuilderExtensions.append(sb, str, i, StringExtensions.getLength(str) - i);
        }
        return sb.toString();
    }

    public static boolean isValidJson(String str) {
        return new JsonChecker().checkString(str);
    }

    public static String serializeBoolean(NullableBoolean nullableBoolean) {
        if (!nullableBoolean.getHasValue()) {
            return "null";
        }
        return nullableBoolean.getValue() ? "true" : "false";
    }

    public static String serializeBooleanArray(boolean[] zArr) {
        if (zArr == null) {
            return "null";
        }
        String[] strArr = new String[ArrayExtensions.getLength(zArr)];
        for (int i = 0; i < ArrayExtensions.getLength(zArr); i++) {
            strArr[i] = serializeBoolean(new NullableBoolean(zArr[i]));
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static String serializeDecimal(NullableBigDecimal nullableBigDecimal) {
        if (!nullableBigDecimal.getHasValue()) {
            return "null";
        }
        return BigDecimalExtensions.toString(nullableBigDecimal.getValue());
    }

    public static String serializeDecimalArray(BigDecimal[] bigDecimalArr) {
        if (bigDecimalArr == null) {
            return "null";
        }
        String[] strArr = new String[ArrayExtensions.getLength((Object[]) bigDecimalArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) bigDecimalArr); i++) {
            strArr[i] = serializeDecimal(new NullableBigDecimal(bigDecimalArr[i]));
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static <T> String serializeDictionary(HashMap<String, T> hashMap, final IFunction1<T, String> iFunction1) {
        return serializeObject(hashMap, new IAction2<HashMap<String, T>, HashMap<String, String>>() {
            public void invoke(HashMap<String, T> hashMap, HashMap<String, String> hashMap2) {
                for (String next : HashMapExtensions.getKeys(hashMap)) {
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap2), next, iFunction1.invoke(HashMapExtensions.getItem(hashMap).get(next)));
                }
            }
        });
    }

    public static String serializeDouble(NullableDouble nullableDouble) {
        if (!nullableDouble.getHasValue()) {
            return "null";
        }
        return DoubleExtensions.toString(Double.valueOf(nullableDouble.getValue()));
    }

    public static String serializeDoubleArray(double[] dArr) {
        if (dArr == null) {
            return "null";
        }
        String[] strArr = new String[ArrayExtensions.getLength(dArr)];
        for (int i = 0; i < ArrayExtensions.getLength(dArr); i++) {
            strArr[i] = serializeDouble(new NullableDouble(dArr[i]));
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static String serializeFloat(NullableFloat nullableFloat) {
        if (!nullableFloat.getHasValue()) {
            return "null";
        }
        return FloatExtensions.toString(Float.valueOf(nullableFloat.getValue()));
    }

    public static String serializeFloatArray(float[] fArr) {
        if (fArr == null) {
            return "null";
        }
        String[] strArr = new String[ArrayExtensions.getLength(fArr)];
        for (int i = 0; i < ArrayExtensions.getLength(fArr); i++) {
            strArr[i] = serializeFloat(new NullableFloat(fArr[i]));
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static String serializeGuid(NullableGuid nullableGuid) {
        if (!nullableGuid.getHasValue()) {
            return "null";
        }
        return StringExtensions.concat("\"", nullableGuid.getValue().toString(), "\"");
    }

    public static String serializeGuidArray(Guid[] guidArr) {
        if (guidArr == null) {
            return "null";
        }
        String[] strArr = new String[ArrayExtensions.getLength((Object[]) guidArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) guidArr); i++) {
            strArr[i] = serializeGuid(new NullableGuid(guidArr[i]));
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static String serializeInteger(NullableInteger nullableInteger) {
        if (!nullableInteger.getHasValue()) {
            return "null";
        }
        return IntegerExtensions.toString(Integer.valueOf(nullableInteger.getValue()));
    }

    public static String serializeIntegerArray(int[] iArr) {
        if (iArr == null) {
            return "null";
        }
        String[] strArr = new String[ArrayExtensions.getLength(iArr)];
        for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
            strArr[i] = serializeInteger(new NullableInteger(iArr[i]));
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static String serializeLong(NullableLong nullableLong) {
        if (!nullableLong.getHasValue()) {
            return "null";
        }
        return LongExtensions.toString(Long.valueOf(nullableLong.getValue()));
    }

    public static String serializeLongArray(long[] jArr) {
        if (jArr == null) {
            return "null";
        }
        String[] strArr = new String[ArrayExtensions.getLength(jArr)];
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            strArr[i] = serializeLong(new NullableLong(jArr[i]));
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static <T> String serializeObject(T t, IAction2<T, HashMap<String, String>> iAction2) {
        if (t == null) {
            return "null";
        }
        HashMap hashMap = new HashMap();
        iAction2.invoke(t, hashMap);
        ArrayList arrayList = new ArrayList(HashMapExtensions.getCount(hashMap));
        for (String str : HashMapExtensions.getKeys(hashMap)) {
            arrayList.add(StringExtensions.concat(serializeString(str), Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, (String) HashMapExtensions.getItem(hashMap).get(str)));
        }
        return StringExtensions.concat("{", StringExtensions.join(",", (String[]) arrayList.toArray(new String[0])), "}");
    }

    public static <T> String serializeObjectArray(T[] tArr, IFunction1<T, String> iFunction1) {
        if (tArr == null) {
            return "null";
        }
        if (ArrayExtensions.getLength((Object[]) tArr) == 0) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        String[] strArr = new String[ArrayExtensions.getLength((Object[]) tArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) tArr); i++) {
            strArr[i] = iFunction1.invoke(tArr[i]);
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static <T extends Serializable> String serializeObjectFast(T t, IAction2<T, HashMap<String, String>> iAction2) {
        if (t == null) {
            return "null";
        }
        if (!t.getIsSerialized() || t.getIsDirty()) {
            t.setSerialized(serializeObject(t, iAction2));
        }
        return t.getSerialized();
    }

    public static String serializeRawArray(String[] strArr) {
        return strArr == null ? "null" : StringExtensions.concat("[", StringExtensions.join(",", strArr), "]");
    }

    public static String serializeString(String str) {
        return str == null ? "null" : StringExtensions.concat("\"", escapeString(str), "\"");
    }

    public static String serializeStringArray(String[] strArr) {
        if (strArr == null) {
            return "null";
        }
        String[] strArr2 = new String[ArrayExtensions.getLength((Object[]) strArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
            strArr2[i] = serializeString(strArr[i]);
        }
        return StringExtensions.concat("[", StringExtensions.join(",", strArr2), "]");
    }

    public static String trimQuotes(String str) {
        char charAt;
        if (StringExtensions.getLength(str) <= 1 || (charAt = str.charAt(0)) != str.charAt(StringExtensions.getLength(str) - 1)) {
            return str;
        }
        return (charAt == '\'' || charAt == '\"') ? StringExtensions.substring(str, 1, StringExtensions.getLength(str) - 2) : str;
    }

    public static String unescapeString(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = -1;
        while (i < StringExtensions.getLength(str)) {
            if (str.charAt(i) == '\\') {
                if (i2 != -1) {
                    StringBuilderExtensions.append(sb, str, i2, i - i2);
                    i2 = -1;
                }
                if (i != StringExtensions.getLength(str) - 1) {
                    char charAt = str.charAt(i + 1);
                    if (charAt == 'b') {
                        StringBuilderExtensions.append(sb, "\b");
                    } else if (charAt == 'f') {
                        StringBuilderExtensions.append(sb, "\f");
                    } else if (charAt == 'n') {
                        StringBuilderExtensions.append(sb, "\n");
                    } else if (charAt == 'r') {
                        StringBuilderExtensions.append(sb, "\r");
                    } else if (charAt == 't') {
                        StringBuilderExtensions.append(sb, "\t");
                    } else if (charAt == '/') {
                        StringBuilderExtensions.append(sb, "/");
                    } else if (charAt == 'u') {
                        if (i < StringExtensions.getLength(str) - 5) {
                            StringBuilderExtensions.append(sb, CharacterExtensions.toString(Character.valueOf(unicodeStringToChar(StringExtensions.substring(str, i, 6)))));
                            i += 4;
                        } else {
                            StringBuilderExtensions.append(sb, "u");
                        }
                    } else if (charAt != 'x') {
                        StringBuilderExtensions.append(sb, CharacterExtensions.toString(Character.valueOf(charAt)));
                    } else if (i < StringExtensions.getLength(str) - 3) {
                        StringBuilderExtensions.append(sb, CharacterExtensions.toString(Character.valueOf(unicodeStringToChar(StringExtensions.substring(str, i, 4)))));
                        i += 2;
                    } else {
                        StringBuilderExtensions.append(sb, "x");
                    }
                    i++;
                }
            } else if (i2 == -1) {
                i2 = i;
            }
            i++;
        }
        if (i2 != -1) {
            StringBuilderExtensions.append(sb, str, i2, StringExtensions.getLength(str) - i2);
        }
        return sb.toString();
    }

    private static char unicodeStringToChar(String str) {
        if (StringExtensions.getLength(str) >= 2) {
            return (char) Convert.toInt32(str.substring(2), 16);
        }
        throw new RuntimeException(new Exception("Unicode string has invalid length."));
    }
}
