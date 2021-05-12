package fm.liveswitch;

import androidx.exifinterface.media.ExifInterface;
import com.microsoft.appcenter.Constants;
import java.util.Date;
import org.slf4j.Marker;

public class Iso8601Timestamp {
    public static String dateTimeToIso8601(Date date) {
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getYear(date)));
        String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMonth(date)));
        String integerExtensions3 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getDay(date)));
        String integerExtensions4 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getHour(date)));
        String integerExtensions5 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMinute(date)));
        String integerExtensions6 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getSecond(date)));
        String integerExtensions7 = IntegerExtensions.toString(Integer.valueOf(DateExtensions.getMillisecond(date)));
        while (StringExtensions.getLength(integerExtensions) < 4) {
            integerExtensions = StringExtensions.concat("0", integerExtensions);
        }
        while (StringExtensions.getLength(integerExtensions2) < 2) {
            integerExtensions2 = StringExtensions.concat("0", integerExtensions2);
        }
        while (StringExtensions.getLength(integerExtensions3) < 2) {
            integerExtensions3 = StringExtensions.concat("0", integerExtensions3);
        }
        while (StringExtensions.getLength(integerExtensions4) < 2) {
            integerExtensions4 = StringExtensions.concat("0", integerExtensions4);
        }
        while (StringExtensions.getLength(integerExtensions5) < 2) {
            integerExtensions5 = StringExtensions.concat("0", integerExtensions5);
        }
        while (StringExtensions.getLength(integerExtensions6) < 2) {
            integerExtensions6 = StringExtensions.concat("0", integerExtensions6);
        }
        while (StringExtensions.getLength(integerExtensions7) < 3) {
            integerExtensions7 = StringExtensions.concat("0", integerExtensions7);
        }
        return StringExtensions.format("{0}-{1}-{2}T{3}:{4}:{5}.{6}Z", new Object[]{integerExtensions, integerExtensions2, integerExtensions3, integerExtensions4, integerExtensions5, integerExtensions6, integerExtensions7});
    }

    private static int getMinimumNeedleIndex(String str, String[] strArr) {
        int i = -1;
        for (String indexOf : strArr) {
            int indexOf2 = StringExtensions.indexOf(str, indexOf);
            if (indexOf2 != -1) {
                if (i == -1) {
                    i = indexOf2;
                } else {
                    i = MathAssistant.min(i, indexOf2);
                }
            }
        }
        return i;
    }

    public static String getUtcNow() {
        return dateTimeToIso8601(DateExtensions.getUtcNow());
    }

    public static Date iso8601ToDateTime(String str) {
        int i;
        String str2 = str;
        int i2 = 0;
        IntegerHolder integerHolder = new IntegerHolder(0);
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        IntegerHolder integerHolder3 = new IntegerHolder(0);
        IntegerHolder integerHolder4 = new IntegerHolder(0);
        boolean tryParseDate = tryParseDate(str, 0, integerHolder, integerHolder2, integerHolder3, integerHolder4);
        int value = integerHolder.getValue();
        int value2 = integerHolder2.getValue();
        int value3 = integerHolder3.getValue();
        int value4 = integerHolder4.getValue();
        IntegerHolder integerHolder5 = new IntegerHolder(value);
        boolean tryRead = tryRead(str2, value, ExifInterface.GPS_DIRECTION_TRUE, integerHolder5);
        int value5 = integerHolder5.getValue();
        IntegerHolder integerHolder6 = new IntegerHolder(value5);
        IntegerHolder integerHolder7 = new IntegerHolder(0);
        IntegerHolder integerHolder8 = new IntegerHolder(0);
        IntegerHolder integerHolder9 = new IntegerHolder(0);
        IntegerHolder integerHolder10 = new IntegerHolder(0);
        IntegerHolder integerHolder11 = integerHolder9;
        boolean tryParseTime = tryParseTime(str, value5, integerHolder6, integerHolder7, integerHolder8, integerHolder9, integerHolder10);
        int value6 = integerHolder6.getValue();
        int value7 = integerHolder7.getValue();
        int value8 = integerHolder8.getValue();
        int value9 = integerHolder11.getValue();
        int value10 = integerHolder10.getValue();
        if (!tryParseDate || !tryRead || !tryParseTime) {
            i = 0;
        } else {
            IntegerHolder integerHolder12 = new IntegerHolder(value6);
            IntegerHolder integerHolder13 = new IntegerHolder(0);
            IntegerHolder integerHolder14 = new IntegerHolder(0);
            tryParseTimezone(str2, value6, integerHolder12, integerHolder13, integerHolder14);
            integerHolder12.getValue();
            i2 = integerHolder13.getValue();
            i = integerHolder14.getValue();
        }
        return DateExtensions.addMinutes(DateExtensions.addHours(DateExtensions.createDate(value2, value3, value4, value7, value8, value9, value10), (double) i2), (double) i);
    }

    private static boolean tryParseDate(String str, int i, IntegerHolder integerHolder, IntegerHolder integerHolder2, IntegerHolder integerHolder3, IntegerHolder integerHolder4) {
        String str2;
        integerHolder2.setValue(1970);
        integerHolder3.setValue(1);
        integerHolder4.setValue(1);
        String str3 = null;
        Holder holder = new Holder(null);
        IntegerHolder integerHolder5 = new IntegerHolder(i);
        boolean tryRead = tryRead(str, i, 4, (Holder<String>) holder, integerHolder5);
        String str4 = (String) holder.getValue();
        int value = integerHolder5.getValue();
        if (tryRead) {
            IntegerHolder integerHolder6 = new IntegerHolder(value);
            tryRead(str, value, "-", integerHolder6);
            int value2 = integerHolder6.getValue();
            Holder holder2 = new Holder(null);
            IntegerHolder integerHolder7 = new IntegerHolder(value2);
            boolean tryRead2 = tryRead(str, value2, 2, (Holder<String>) holder2, integerHolder7);
            String str5 = (String) holder2.getValue();
            int value3 = integerHolder7.getValue();
            if (tryRead2) {
                IntegerHolder integerHolder8 = new IntegerHolder(value3);
                tryRead(str, value3, "-", integerHolder8);
                int value4 = integerHolder8.getValue();
                Holder holder3 = new Holder(null);
                IntegerHolder integerHolder9 = new IntegerHolder(value4);
                tryRead(str, value4, 2, (Holder<String>) holder3, integerHolder9);
                str2 = (String) holder3.getValue();
                int value5 = integerHolder9.getValue();
                str3 = str5;
                value = value5;
            } else {
                str2 = null;
                str3 = str5;
                value = value3;
            }
        } else {
            str2 = null;
        }
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str4, integerHolder2);
        if (str4 == null || tryParseIntegerValue) {
            boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(str3, integerHolder3);
            if (str3 == null || tryParseIntegerValue2) {
                boolean tryParseIntegerValue3 = ParseAssistant.tryParseIntegerValue(str2, integerHolder4);
                if (str2 == null || tryParseIntegerValue3) {
                    integerHolder.setValue(value);
                    return true;
                }
                integerHolder.setValue(value);
                return false;
            }
            integerHolder.setValue(value);
            return false;
        }
        integerHolder.setValue(value);
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean tryParseTime(java.lang.String r18, int r19, fm.liveswitch.IntegerHolder r20, fm.liveswitch.IntegerHolder r21, fm.liveswitch.IntegerHolder r22, fm.liveswitch.IntegerHolder r23, fm.liveswitch.IntegerHolder r24) {
        /*
            r6 = r18
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = 0
            r8.setValue(r12)
            r9.setValue(r12)
            r10.setValue(r12)
            r11.setValue(r12)
            java.lang.String r0 = "Z"
            java.lang.String r1 = "+"
            java.lang.String r2 = "-"
            java.lang.String[] r13 = new java.lang.String[]{r0, r1, r2}
            fm.liveswitch.Holder r14 = new fm.liveswitch.Holder
            r15 = 0
            r14.<init>(r15)
            fm.liveswitch.IntegerHolder r5 = new fm.liveswitch.IntegerHolder
            r1 = r19
            r5.<init>(r1)
            r2 = 2
            r0 = r18
            r3 = r13
            r4 = r14
            r16 = r5
            boolean r0 = tryRead(r0, r1, r2, r3, r4, r5)
            java.lang.Object r1 = r14.getValue()
            r14 = r1
            java.lang.String r14 = (java.lang.String) r14
            int r1 = r16.getValue()
            if (r0 == 0) goto L_0x00fb
            fm.liveswitch.IntegerHolder r0 = new fm.liveswitch.IntegerHolder
            r0.<init>(r1)
            java.lang.String r5 = ":"
            tryRead(r6, r1, r5, r0)
            int r1 = r0.getValue()
            fm.liveswitch.Holder r4 = new fm.liveswitch.Holder
            r4.<init>(r15)
            fm.liveswitch.IntegerHolder r3 = new fm.liveswitch.IntegerHolder
            r3.<init>(r1)
            r2 = 2
            r0 = r18
            r16 = r3
            r3 = r13
            r17 = r4
            r12 = r5
            r5 = r16
            boolean r0 = tryRead(r0, r1, r2, r3, r4, r5)
            java.lang.Object r1 = r17.getValue()
            r17 = r1
            java.lang.String r17 = (java.lang.String) r17
            int r1 = r16.getValue()
            if (r0 == 0) goto L_0x00f6
            fm.liveswitch.IntegerHolder r0 = new fm.liveswitch.IntegerHolder
            r0.<init>(r1)
            tryRead(r6, r1, r12, r0)
            int r1 = r0.getValue()
            fm.liveswitch.Holder r12 = new fm.liveswitch.Holder
            r12.<init>(r15)
            fm.liveswitch.IntegerHolder r5 = new fm.liveswitch.IntegerHolder
            r5.<init>(r1)
            r2 = 2
            r0 = r18
            r3 = r13
            r4 = r12
            r16 = r5
            boolean r0 = tryRead(r0, r1, r2, r3, r4, r5)
            java.lang.Object r1 = r12.getValue()
            java.lang.String r1 = (java.lang.String) r1
            int r2 = r16.getValue()
            fm.liveswitch.IntegerHolder r3 = new fm.liveswitch.IntegerHolder
            r3.<init>(r2)
            java.lang.String r4 = "."
            boolean r2 = tryRead(r6, r2, r4, r3)
            int r3 = r3.getValue()
            if (r0 == 0) goto L_0x00f2
            if (r2 == 0) goto L_0x00f2
            fm.liveswitch.Holder r0 = new fm.liveswitch.Holder
            r0.<init>(r15)
            fm.liveswitch.IntegerHolder r2 = new fm.liveswitch.IntegerHolder
            r2.<init>(r3)
            boolean r3 = tryRead((java.lang.String) r6, (int) r3, (java.lang.String[]) r13, (fm.liveswitch.Holder<java.lang.String>) r0, (fm.liveswitch.IntegerHolder) r2)
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            int r2 = r2.getValue()
            if (r3 == 0) goto L_0x00ef
            int r3 = fm.liveswitch.StringExtensions.getLength(r0)
            r4 = 3
            if (r3 >= r4) goto L_0x00ea
            r15 = r0
        L_0x00dd:
            int r0 = fm.liveswitch.StringExtensions.getLength(r15)
            if (r0 >= r4) goto L_0x00ef
            java.lang.String r0 = "0"
            java.lang.String r15 = fm.liveswitch.StringExtensions.concat(r15, r0)
            goto L_0x00dd
        L_0x00ea:
            r3 = 0
            java.lang.String r15 = fm.liveswitch.StringExtensions.substring(r0, r3, r4)
        L_0x00ef:
            r0 = r1
            r1 = r2
            goto L_0x00f4
        L_0x00f2:
            r0 = r1
            r1 = r3
        L_0x00f4:
            r2 = r15
            goto L_0x00f8
        L_0x00f6:
            r0 = r15
            r2 = r0
        L_0x00f8:
            r15 = r17
            goto L_0x00fd
        L_0x00fb:
            r0 = r15
            r2 = r0
        L_0x00fd:
            boolean r3 = fm.liveswitch.ParseAssistant.tryParseIntegerValue(r14, r8)
            if (r14 == 0) goto L_0x010a
            if (r3 != 0) goto L_0x010a
            r7.setValue(r1)
            r3 = 0
            return r3
        L_0x010a:
            r3 = 0
            boolean r4 = fm.liveswitch.ParseAssistant.tryParseIntegerValue(r15, r9)
            if (r15 == 0) goto L_0x0117
            if (r4 != 0) goto L_0x0117
            r7.setValue(r1)
            return r3
        L_0x0117:
            boolean r4 = fm.liveswitch.ParseAssistant.tryParseIntegerValue(r0, r10)
            if (r0 == 0) goto L_0x0123
            if (r4 != 0) goto L_0x0123
            r7.setValue(r1)
            return r3
        L_0x0123:
            boolean r0 = fm.liveswitch.ParseAssistant.tryParseIntegerValue(r2, r11)
            if (r2 == 0) goto L_0x012f
            if (r0 != 0) goto L_0x012f
            r7.setValue(r1)
            return r3
        L_0x012f:
            r7.setValue(r1)
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Iso8601Timestamp.tryParseTime(java.lang.String, int, fm.liveswitch.IntegerHolder, fm.liveswitch.IntegerHolder, fm.liveswitch.IntegerHolder, fm.liveswitch.IntegerHolder, fm.liveswitch.IntegerHolder):boolean");
    }

    private static boolean tryParseTimezone(String str, int i, IntegerHolder integerHolder, IntegerHolder integerHolder2, IntegerHolder integerHolder3) {
        boolean z;
        integerHolder2.setValue(0);
        integerHolder3.setValue(0);
        IntegerHolder integerHolder4 = new IntegerHolder(i);
        boolean tryRead = tryRead(str, i, "Z", integerHolder4);
        int value = integerHolder4.getValue();
        if (tryRead) {
            integerHolder.setValue(value);
            return true;
        }
        IntegerHolder integerHolder5 = new IntegerHolder(value);
        boolean tryRead2 = tryRead(str, value, Marker.ANY_NON_NULL_MARKER, integerHolder5);
        int value2 = integerHolder5.getValue();
        if (tryRead2) {
            z = false;
        } else {
            IntegerHolder integerHolder6 = new IntegerHolder(value2);
            boolean tryRead3 = tryRead(str, value2, "-", integerHolder6);
            int value3 = integerHolder6.getValue();
            if (tryRead3) {
                value2 = value3;
                z = true;
            } else {
                integerHolder.setValue(value3);
                return false;
            }
        }
        String str2 = null;
        Holder holder = new Holder(null);
        IntegerHolder integerHolder7 = new IntegerHolder(value2);
        boolean tryRead4 = tryRead(str, value2, 2, (Holder<String>) holder, integerHolder7);
        String str3 = (String) holder.getValue();
        int value4 = integerHolder7.getValue();
        if (tryRead4) {
            IntegerHolder integerHolder8 = new IntegerHolder(value4);
            tryRead(str, value4, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, integerHolder8);
            int value5 = integerHolder8.getValue();
            Holder holder2 = new Holder(null);
            IntegerHolder integerHolder9 = new IntegerHolder(value5);
            tryRead(str, value5, 2, (Holder<String>) holder2, integerHolder9);
            value4 = integerHolder9.getValue();
            str2 = (String) holder2.getValue();
        }
        boolean tryParseIntegerValue = ParseAssistant.tryParseIntegerValue(str3, integerHolder2);
        if (str3 == null || tryParseIntegerValue) {
            boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(str2, integerHolder3);
            if (str2 == null || tryParseIntegerValue2) {
                if (z) {
                    integerHolder2.setValue(-integerHolder2.getValue());
                    integerHolder3.setValue(-integerHolder3.getValue());
                }
                integerHolder.setValue(value4);
                return true;
            }
            integerHolder.setValue(value4);
            return false;
        }
        integerHolder.setValue(value4);
        return false;
    }

    private static boolean tryRead(String str, int i, int i2, String[] strArr, Holder<String> holder, IntegerHolder integerHolder) {
        int minimumNeedleIndex;
        if (i2 == -1) {
            if (i > StringExtensions.getLength(str)) {
                holder.setValue(null);
                integerHolder.setValue(i);
                return false;
            }
            holder.setValue(str.substring(i));
            integerHolder.setValue(StringExtensions.getLength(holder.getValue()) + i);
            if (strArr == null || ArrayExtensions.getLength((Object[]) strArr) == 0 || (minimumNeedleIndex = getMinimumNeedleIndex(holder.getValue(), strArr)) == -1) {
                return true;
            }
            holder.setValue(StringExtensions.substring(str, i, minimumNeedleIndex));
            integerHolder.setValue(i + StringExtensions.getLength(holder.getValue()));
            return true;
        } else if (i + i2 > StringExtensions.getLength(str)) {
            holder.setValue(null);
            integerHolder.setValue(i);
            return false;
        } else {
            holder.setValue(StringExtensions.substring(str, i, i2));
            integerHolder.setValue(i + StringExtensions.getLength(holder.getValue()));
            if (strArr == null || ArrayExtensions.getLength((Object[]) strArr) == 0 || getMinimumNeedleIndex(holder.getValue(), strArr) == -1) {
                return true;
            }
            return false;
        }
    }

    private static boolean tryRead(String str, int i, int i2, Holder<String> holder, IntegerHolder integerHolder) {
        return tryRead(str, i, i2, (String[]) null, holder, integerHolder);
    }

    private static boolean tryRead(String str, int i, String[] strArr, Holder<String> holder, IntegerHolder integerHolder) {
        return tryRead(str, i, -1, strArr, holder, integerHolder);
    }

    private static boolean tryRead(String str, int i, String str2, IntegerHolder integerHolder) {
        if (StringExtensions.getLength(str2) + i > StringExtensions.getLength(str)) {
            integerHolder.setValue(i);
            return false;
        } else if (!Global.equals(StringExtensions.substring(str, i, StringExtensions.getLength(str2)), str2)) {
            integerHolder.setValue(i);
            return false;
        } else {
            integerHolder.setValue(i + StringExtensions.getLength(str2));
            return true;
        }
    }
}
