package com.google.gson.typeadapters;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

public final class UtcDateTypeAdapter extends TypeAdapter<Date> {
    private static final String GMT_ID = "GMT";
    private final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(format(date, true, this.UTC_TIME_ZONE));
        }
    }

    /* renamed from: com.google.gson.typeadapters.UtcDateTypeAdapter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public Date read(JsonReader jsonReader) throws IOException {
        try {
            if (AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader.peek().ordinal()] != 1) {
                return parse(jsonReader.nextString(), new ParsePosition(0));
            }
            jsonReader.nextNull();
            return null;
        } catch (ParseException e) {
            throw new JsonParseException((Throwable) e);
        }
    }

    private static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append(Matrix.MATRIX_TYPE_ZERO);
        }
        return sb.toString();
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0140  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Date parse(java.lang.String r17, java.text.ParsePosition r18) throws java.text.ParseException {
        /*
            r1 = r17
            int r0 = r18.getIndex()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            int r2 = r0 + 4
            int r0 = parseInt(r1, r0, r2)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r3 = 45
            boolean r4 = checkOffset(r1, r2, r3)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r4 == 0) goto L_0x0016
            int r2 = r2 + 1
        L_0x0016:
            int r4 = r2 + 2
            int r2 = parseInt(r1, r2, r4)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            boolean r5 = checkOffset(r1, r4, r3)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r5 == 0) goto L_0x0024
            int r4 = r4 + 1
        L_0x0024:
            int r5 = r4 + 2
            int r4 = parseInt(r1, r4, r5)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r6 = 84
            boolean r6 = checkOffset(r1, r5, r6)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r7 = 90
            r8 = 43
            r9 = 0
            if (r6 == 0) goto L_0x0093
            int r5 = r5 + 1
            int r6 = r5 + 2
            int r5 = parseInt(r1, r5, r6)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r10 = 58
            boolean r11 = checkOffset(r1, r6, r10)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r11 == 0) goto L_0x0049
            int r6 = r6 + 1
        L_0x0049:
            int r11 = r6 + 2
            int r6 = parseInt(r1, r6, r11)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            boolean r10 = checkOffset(r1, r11, r10)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r10 == 0) goto L_0x0057
            int r11 = r11 + 1
        L_0x0057:
            int r10 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r10 <= r11) goto L_0x008d
            char r10 = r1.charAt(r11)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r10 == r7) goto L_0x008d
            if (r10 == r8) goto L_0x008d
            if (r10 == r3) goto L_0x008d
            int r10 = r11 + 2
            int r11 = parseInt(r1, r11, r10)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r12 = 46
            boolean r12 = checkOffset(r1, r10, r12)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r12 == 0) goto L_0x0084
            int r10 = r10 + 1
            int r12 = r10 + 3
            int r10 = parseInt(r1, r10, r12)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r16 = r6
            r6 = r5
            r5 = r12
            r12 = r11
            r11 = r10
            goto L_0x008a
        L_0x0084:
            r12 = r11
            r11 = 0
            r16 = r6
            r6 = r5
            r5 = r10
        L_0x008a:
            r10 = r16
            goto L_0x0097
        L_0x008d:
            r10 = r6
            r12 = 0
            r6 = r5
            r5 = r11
            r11 = 0
            goto L_0x0097
        L_0x0093:
            r6 = 0
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x0097:
            int r13 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r13 <= r5) goto L_0x0124
            char r13 = r1.charAt(r5)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            java.lang.String r14 = "GMT"
            r15 = 1
            if (r13 == r8) goto L_0x00c4
            if (r13 != r3) goto L_0x00a9
            goto L_0x00c4
        L_0x00a9:
            if (r13 != r7) goto L_0x00ad
            int r5 = r5 + r15
            goto L_0x00dc
        L_0x00ad:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r2.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            java.lang.String r3 = "Invalid time zone indicator "
            r2.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r2.append(r13)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            java.lang.String r2 = r2.toString()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r0.<init>(r2)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
        L_0x00c4:
            java.lang.String r3 = r1.substring(r5)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r7.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r7.append(r14)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r7.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            java.lang.String r14 = r7.toString()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            int r3 = r3.length()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            int r5 = r5 + r3
        L_0x00dc:
            java.util.TimeZone r3 = java.util.TimeZone.getTimeZone(r14)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            java.lang.String r7 = r3.getID()     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            boolean r7 = r7.equals(r14)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            if (r7 == 0) goto L_0x011c
            java.util.GregorianCalendar r7 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r7.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r7.setLenient(r9)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r7.set(r15, r0)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            int r2 = r2 - r15
            r0 = 2
            r7.set(r0, r2)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r0 = 5
            r7.set(r0, r4)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r0 = 11
            r7.set(r0, r6)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r0 = 12
            r7.set(r0, r10)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r0 = 13
            r7.set(r0, r12)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r0 = 14
            r7.set(r0, r11)     // Catch:{ IndexOutOfBoundsException -> 0x013a, NumberFormatException -> 0x0138, IllegalArgumentException -> 0x0134 }
            r2 = r18
            r2.setIndex(r5)     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
            java.util.Date r0 = r7.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
            return r0
        L_0x011c:
            r2 = r18
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
            r0.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
        L_0x0124:
            r2 = r18
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
            java.lang.String r3 = "No time zone indicator"
            r0.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x0132, NumberFormatException -> 0x0130, IllegalArgumentException -> 0x012e }
        L_0x012e:
            r0 = move-exception
            goto L_0x013c
        L_0x0130:
            r0 = move-exception
            goto L_0x013c
        L_0x0132:
            r0 = move-exception
            goto L_0x013c
        L_0x0134:
            r0 = move-exception
        L_0x0135:
            r2 = r18
            goto L_0x013c
        L_0x0138:
            r0 = move-exception
            goto L_0x0135
        L_0x013a:
            r0 = move-exception
            goto L_0x0135
        L_0x013c:
            if (r1 != 0) goto L_0x0140
            r1 = 0
            goto L_0x0154
        L_0x0140:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "'"
            r3.append(r4)
            r3.append(r1)
            r3.append(r4)
            java.lang.String r1 = r3.toString()
        L_0x0154:
            java.text.ParseException r3 = new java.text.ParseException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Failed to parse date ["
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = "]: "
            r4.append(r1)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            int r1 = r18.getIndex()
            r3.<init>(r0, r1)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.typeadapters.UtcDateTypeAdapter.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i3 = 0;
        if (i < i2) {
            int i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                int i5 = i4;
                i3 = -digit;
                i = i5;
            } else {
                throw new NumberFormatException("Invalid number: " + str);
            }
        }
        while (i < i2) {
            int i6 = i + 1;
            int digit2 = Character.digit(str.charAt(i), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i = i6;
            } else {
                throw new NumberFormatException("Invalid number: " + str);
            }
        }
        return -i3;
    }
}
