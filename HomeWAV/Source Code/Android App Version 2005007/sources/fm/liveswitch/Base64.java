package fm.liveswitch;

import java.util.regex.Pattern;

public class Base64 {
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static Pattern base64Regex = Pattern.compile("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]*)?$");
    private static int[] toInt = new int[128];

    public static String encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return toString(bArr, 0, bArr.length);
    }

    public static String encodeBuffer(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            return null;
        }
        return toString(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
    }

    static {
        int i = 0;
        while (true) {
            char[] cArr = ALPHABET;
            if (i < cArr.length) {
                toInt[cArr[i]] = i;
                i++;
            } else {
                return;
            }
        }
    }

    public static byte[] decode(String str) {
        if (str != null && base64Regex.matcher(str).matches()) {
            return fromString(str);
        }
        return null;
    }

    public static Boolean tryEncode(byte[] bArr, Holder<String> holder) {
        try {
            String encode = encode(bArr);
            if (encode == null) {
                return false;
            }
            holder.setValue(encode);
            return true;
        } catch (Exception unused) {
            holder.setValue(null);
            return false;
        }
    }

    public static Boolean tryEncodeBuffer(DataBuffer dataBuffer, Holder<String> holder) {
        try {
            String encodeBuffer = encodeBuffer(dataBuffer);
            if (encodeBuffer == null) {
                return false;
            }
            holder.setValue(encodeBuffer);
            return true;
        } catch (Exception unused) {
            holder.setValue(null);
            return false;
        }
    }

    public static Boolean tryDecode(String str, Holder<byte[]> holder) {
        try {
            byte[] decode = decode(str);
            if (decode == null) {
                return false;
            }
            holder.setValue(decode);
            return true;
        } catch (Exception unused) {
            holder.setValue(null);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0064, code lost:
        if (r14 != 2) goto L_0x006f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String toString(byte[] r12, int r13, int r14) {
        /*
            int r0 = r14 + 2
            int r0 = r0 / 3
            int r0 = r0 * 4
            char[] r0 = new char[r0]
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x000b:
            r4 = 2
            if (r2 >= r14) goto L_0x005d
            int r5 = r2 + 1
            int r2 = r2 + r13
            byte r2 = r12[r2]
            if (r5 >= r14) goto L_0x001b
            int r6 = r5 + 1
            int r5 = r5 + r13
            byte r5 = r12[r5]
            goto L_0x001d
        L_0x001b:
            r6 = r5
            r5 = 0
        L_0x001d:
            if (r6 >= r14) goto L_0x0028
            int r7 = r6 + 1
            int r6 = r6 + r13
            byte r6 = r12[r6]
            r11 = r7
            r7 = r6
            r6 = r11
            goto L_0x0029
        L_0x0028:
            r7 = 0
        L_0x0029:
            int r8 = r3 + 1
            char[] r9 = ALPHABET
            int r10 = r2 >> 2
            r10 = r10 & 63
            char r10 = r9[r10]
            r0[r3] = r10
            int r3 = r8 + 1
            int r2 = r2 << 4
            r10 = r5 & 255(0xff, float:3.57E-43)
            int r10 = r10 >> 4
            r2 = r2 | r10
            r2 = r2 & 63
            char r2 = r9[r2]
            r0[r8] = r2
            int r2 = r3 + 1
            int r4 = r5 << 2
            r5 = r7 & 255(0xff, float:3.57E-43)
            int r5 = r5 >> 6
            r4 = r4 | r5
            r4 = r4 & 63
            char r4 = r9[r4]
            r0[r3] = r4
            int r3 = r2 + 1
            r4 = r7 & 63
            char r4 = r9[r4]
            r0[r2] = r4
            r2 = r6
            goto L_0x000b
        L_0x005d:
            int r14 = r14 % 3
            r12 = 61
            r13 = 1
            if (r14 == r13) goto L_0x0067
            if (r14 == r4) goto L_0x006b
            goto L_0x006f
        L_0x0067:
            int r3 = r3 + -1
            r0[r3] = r12
        L_0x006b:
            int r3 = r3 + -1
            r0[r3] = r12
        L_0x006f:
            java.lang.String r12 = new java.lang.String
            r12.<init>(r0)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Base64.toString(byte[], int, int):java.lang.String");
    }

    private static byte[] fromString(String str) {
        int i = 0;
        int length = ((str.length() * 3) / 4) - (str.endsWith("==") ? 2 : str.endsWith("=") ? 1 : 0);
        byte[] bArr = new byte[length];
        int i2 = 0;
        while (i < str.length()) {
            int i3 = toInt[str.charAt(i)];
            int i4 = toInt[str.charAt(i + 1)];
            int i5 = i2 + 1;
            bArr[i2] = (byte) (((i3 << 2) | (i4 >> 4)) & 255);
            if (i5 >= length) {
                return bArr;
            }
            int i6 = toInt[str.charAt(i + 2)];
            int i7 = i5 + 1;
            bArr[i5] = (byte) (((i4 << 4) | (i6 >> 2)) & 255);
            if (i7 >= length) {
                return bArr;
            }
            bArr[i7] = (byte) (((i6 << 6) | toInt[str.charAt(i + 3)]) & 255);
            i += 4;
            i2 = i7 + 1;
        }
        return bArr;
    }
}
