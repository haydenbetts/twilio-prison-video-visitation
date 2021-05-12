package fm.liveswitch;

import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bytedeco.ffmpeg.global.avutil;

public class Binary {
    private static int[][] __bitmasks;

    static {
        int[] iArr = new int[3];
        iArr[0] = 65280;
        iArr[2] = 255;
        int[] iArr2 = new int[4];
        iArr2[0] = 65280;
        iArr2[3] = 255;
        __bitmasks = new int[][]{new int[]{65407, avutil.FF_LAMBDA_MAX}, new int[]{65343, 16383}, new int[]{65311, 8191}, new int[]{65295, 4095}, new int[]{65287, 2047}, new int[]{CipherSuite.DRAFT_TLS_ECDHE_RSA_WITH_AES_256_OCB, 1023}, new int[]{65281, 511}, new int[]{65280, 255}, new int[]{65280, 127, avutil.FF_LAMBDA_MAX}, new int[]{65280, 63, 16383}, new int[]{65280, 31, 8191}, new int[]{65280, 15, 4095}, new int[]{65280, 7, 2047}, new int[]{65280, 3, 1023}, new int[]{65280, 1, 511}, iArr, new int[]{65280, 0, 127, avutil.FF_LAMBDA_MAX}, new int[]{65280, 0, 63, 16383}, new int[]{65280, 0, 31, 8191}, new int[]{65280, 0, 15, 4095}, new int[]{65280, 0, 7, 2047}, new int[]{65280, 0, 3, 1023}, new int[]{65280, 0, 1, 511}, iArr2};
    }

    public static byte[] bitStringToBytes(String str) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        byte[] bitStringToBytes = bitStringToBytes(str, integerHolder);
        integerHolder.getValue();
        return bitStringToBytes;
    }

    public static byte[] bitStringToBytes(String str, IntegerHolder integerHolder) {
        return bitStringToBytes(str, false, integerHolder);
    }

    public static byte[] bitStringToBytes(String str, boolean z) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        byte[] bitStringToBytes = bitStringToBytes(str, z, integerHolder);
        integerHolder.getValue();
        return bitStringToBytes;
    }

    public static byte[] bitStringToBytes(String str, boolean z, IntegerHolder integerHolder) {
        int length = StringExtensions.getLength(str) % 8;
        integerHolder.setValue(0);
        if (length > 0) {
            integerHolder.setValue((byte) (8 - length));
            for (int i = 0; i < integerHolder.getValue(); i++) {
                if (z) {
                    str = StringExtensions.concat("0", str);
                } else {
                    str = StringExtensions.concat(str, "0");
                }
            }
        }
        byte[] bArr = new byte[(StringExtensions.getLength(str) / 8)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < ArrayExtensions.getLength(bArr)) {
            boolean z2 = true;
            boolean z3 = str.charAt(i3) != '0';
            boolean z4 = str.charAt(i3 + 1) != '0';
            boolean z5 = str.charAt(i3 + 2) != '0';
            boolean z6 = str.charAt(i3 + 3) != '0';
            boolean z7 = str.charAt(i3 + 4) != '0';
            boolean z8 = str.charAt(i3 + 5) != '0';
            boolean z9 = str.charAt(i3 + 6) != '0';
            if (str.charAt(i3 + 7) == '0') {
                z2 = false;
            }
            int i4 = z3 ? 128 : 0;
            if (z4) {
                i4 += 64;
            }
            if (z5) {
                i4 += 32;
            }
            if (z6) {
                i4 += 16;
            }
            if (z7) {
                i4 += 8;
            }
            if (z8) {
                i4 += 4;
            }
            if (z9) {
                i4 += 2;
            }
            if (z2) {
                i4++;
            }
            bArr[i2] = BitAssistant.castByte(i4);
            i2++;
            i3 += 8;
        }
        return bArr;
    }

    public static String bytesToBitString(byte[] bArr) {
        return bytesToBitString(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static String bytesToBitString(byte[] bArr, int i, int i2) {
        return bytesToBitString(bArr, i, i2, 0);
    }

    public static String bytesToBitString(byte[] bArr, int i, int i2, int i3) {
        return bytesToBitString(bArr, i, i2, i3, false);
    }

    public static String bytesToBitString(byte[] bArr, int i, int i2, int i3, boolean z) {
        int i4 = i3;
        String str = "";
        for (int i5 = i; i5 < i + i2; i5++) {
            byte b = bArr[i5];
            boolean z2 = (b & ByteCompanionObject.MIN_VALUE) == 128;
            boolean z3 = (b & 64) == 64;
            boolean z4 = (b & 32) == 32;
            boolean z5 = (b & Tnaf.POW_2_WIDTH) == 16;
            boolean z6 = (b & 8) == 8;
            boolean z7 = (b & 4) == 4;
            boolean z8 = (b & 2) == 2;
            boolean z9 = (b & 1) == 1;
            Object[] objArr = new Object[8];
            String str2 = "1";
            objArr[0] = z2 ? str2 : "0";
            objArr[1] = z3 ? str2 : "0";
            objArr[2] = z4 ? str2 : "0";
            objArr[3] = z5 ? str2 : "0";
            objArr[4] = z6 ? str2 : "0";
            objArr[5] = z7 ? str2 : "0";
            objArr[6] = z8 ? str2 : "0";
            if (!z9) {
                str2 = "0";
            }
            objArr[7] = str2;
            str = StringExtensions.concat(str, StringExtensions.format("{0}{1}{2}{3}{4}{5}{6}{7}", objArr));
        }
        if (z) {
            return StringExtensions.substring(str, i4, StringExtensions.getLength(str) - i4);
        }
        return StringExtensions.substring(str, 0, StringExtensions.getLength(str) - i4);
    }

    public static void deinterleave(byte[] bArr, byte[] bArr2) {
        deinterleave(bArr, bArr2, 0, ArrayExtensions.getLength(bArr), false);
    }

    public static void deinterleave(byte[] bArr, byte[] bArr2, int i, int i2) {
        deinterleave(bArr, bArr2, i, i2, false);
    }

    public static void deinterleave(byte[] bArr, byte[] bArr2, int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5 = i + i2;
        int i6 = i2 / 2;
        if (i5 > ArrayExtensions.getLength(bArr2)) {
            Log.error("start + length greater than outputFrame length");
            return;
        }
        if (z) {
            i4 = i6 + i;
            i3 = i;
        } else {
            i3 = i6 + i;
            i4 = i;
        }
        while (i < i5) {
            bArr2[i4] = bArr[i];
            bArr2[i3] = bArr[i + 1];
            i += 2;
            i3++;
            i4++;
        }
    }

    public static void deinterleaveTransform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        deinterleaveTransform(bArr, bArr2, i, i2, i3, i4, 0, false);
    }

    public static void deinterleaveTransform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5) {
        deinterleaveTransform(bArr, bArr2, i, i2, i3, i4, i5, false);
    }

    public static void deinterleaveTransform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, boolean z) {
        int i6;
        int i7;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i8 = i4;
        int i9 = i5;
        boolean z2 = z;
        int i10 = i * i2;
        int i11 = i10 * 2;
        int i12 = i3 - i;
        int i13 = i9 + i11;
        if (i13 > ArrayExtensions.getLength(bArr2)) {
            Log.error("start + length greater than outputFrame length");
            return;
        }
        if (z2) {
            i7 = i10 + i9;
            i6 = i9;
        } else {
            i6 = i10 + i9;
            i7 = i9;
        }
        int i14 = i2 + 1;
        int[] iArr = new int[i14];
        int i15 = i * 2;
        for (int i16 = 0; i16 < i14; i16++) {
            iArr[i16] = (i15 * i16) + i9;
            if (i16 > 1) {
                iArr[i16] = iArr[i16] + ((i16 - 1) * i12);
            }
        }
        if (i8 == 270) {
            for (int i17 = 1; i17 <= i15; i17 += 2) {
                int i18 = 1;
                while (i18 < i14) {
                    bArr4[i7] = bArr3[iArr[i18] - (i17 + 1)];
                    bArr4[i6] = bArr3[iArr[i18] - i17];
                    i18++;
                    i6++;
                    i7++;
                }
            }
        } else if (i8 == 90) {
            for (int i19 = 0; i19 < i15; i19 += 2) {
                int i20 = i14 - 2;
                while (i20 >= 0) {
                    bArr4[i7] = bArr3[iArr[i20] + i19];
                    bArr4[i6] = bArr3[iArr[i20] + i19 + 1];
                    i20--;
                    i7++;
                    i6++;
                }
            }
        } else if (i8 == 180) {
            int i21 = i13 - 1;
            while (i21 >= i9) {
                bArr4[i7] = bArr3[i21 - 1];
                bArr4[i6] = bArr3[i21];
                i21 -= 2;
                i7++;
                i6++;
            }
        } else {
            deinterleave(bArr3, bArr4, i9, i11, z2);
        }
    }

    public static boolean fromBytes1(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return (BitAssistant.rightShiftInteger(fromBytes8(bArr, i), 7 - i2) & 1) == 1;
    }

    public static int fromBytes10(byte[] bArr, int i, int i2) {
        int rightShiftInteger;
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 7) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 14 - i2);
            }
            throw new RuntimeException(new Exception("Input data is not large enough to read 10 bits."));
        }
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes16(bArr, i, false), 6 - i2);
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 10 bits."));
        return rightShiftInteger & 1023;
    }

    public static int fromBytes11(byte[] bArr, int i, int i2) {
        int rightShiftInteger;
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 6) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 13 - i2);
            }
            throw new RuntimeException(new Exception("Input data is not large enough to read 11 bits."));
        }
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes16(bArr, i, false), 5 - i2);
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 11 bits."));
        return rightShiftInteger & 2047;
    }

    public static int fromBytes12(byte[] bArr, int i, int i2) {
        int rightShiftInteger;
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 5) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 12 - i2);
            }
            throw new RuntimeException(new Exception("Input data is not large enough to read 12 bits."));
        }
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes16(bArr, i, false), 4 - i2);
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 12 bits."));
        return rightShiftInteger & 4095;
    }

    public static int fromBytes13(byte[] bArr, int i, int i2) {
        int rightShiftInteger;
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 4) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 11 - i2);
            }
            throw new RuntimeException(new Exception("Input data is not large enough to read 13 bits."));
        }
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes16(bArr, i, false), 3 - i2);
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 13 bits."));
        return rightShiftInteger & 8191;
    }

    public static int fromBytes14(byte[] bArr, int i, int i2) {
        int rightShiftInteger;
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 3) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 10 - i2);
            }
            throw new RuntimeException(new Exception("Input data is not large enough to read 14 bits."));
        }
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes16(bArr, i, false), 2 - i2);
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 14 bits."));
        return rightShiftInteger & 16383;
    }

    public static int fromBytes15(byte[] bArr, int i, int i2) {
        int rightShiftInteger;
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 2) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 9 - i2);
            }
            throw new RuntimeException(new Exception("Input data is not large enough to read 15 bits."));
        }
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            rightShiftInteger = BitAssistant.rightShiftInteger(fromBytes16(bArr, i, false), 1 - i2);
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 15 bits."));
        return rightShiftInteger & avutil.FF_LAMBDA_MAX;
    }

    public static int fromBytes16(byte[] bArr, int i, boolean z) {
        int leftShiftInteger;
        int leftShiftInteger2;
        if (z) {
            int i2 = i + 1;
            leftShiftInteger = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i]), 0) | 0;
            leftShiftInteger2 = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i2]), 8);
        } else {
            int i3 = i + 1;
            leftShiftInteger = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i]), 8) | 0;
            leftShiftInteger2 = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i3]), 0);
        }
        return leftShiftInteger2 | leftShiftInteger;
    }

    public static int fromBytes17(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 7 - i2) & 131071;
    }

    public static int fromBytes18(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 7) {
            if (i + 3 < ArrayExtensions.getLength(bArr)) {
                return ((int) BitAssistant.rightShiftLong(fromBytes32(bArr, i, false), 14 - i2)) & 262143;
            }
        } else if (i + 2 < ArrayExtensions.getLength(bArr)) {
            return BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 6 - i2) & 262143;
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 18 bits."));
    }

    public static int fromBytes19(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 6) {
            if (i + 3 < ArrayExtensions.getLength(bArr)) {
                return ((int) BitAssistant.rightShiftLong(fromBytes32(bArr, i, false), 13 - i2)) & 524287;
            }
        } else if (i + 2 < ArrayExtensions.getLength(bArr)) {
            return BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 5 - i2) & 524287;
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 19 bits."));
    }

    public static int fromBytes2(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return BitAssistant.rightShiftInteger(fromBytes8(bArr, i), 6 - i2) & 3;
    }

    public static int fromBytes20(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 5) {
            if (i + 3 < ArrayExtensions.getLength(bArr)) {
                return ((int) BitAssistant.rightShiftLong(fromBytes32(bArr, i, false), 12 - i2)) & 1048575;
            }
        } else if (i + 2 < ArrayExtensions.getLength(bArr)) {
            return BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 4 - i2) & 1048575;
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 20 bits."));
    }

    public static int fromBytes21(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 4) {
            if (i + 3 < ArrayExtensions.getLength(bArr)) {
                return ((int) BitAssistant.rightShiftLong(fromBytes32(bArr, i, false), 11 - i2)) & 2097151;
            }
        } else if (i + 2 < ArrayExtensions.getLength(bArr)) {
            return BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 3 - i2) & 2097151;
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 21 bits."));
    }

    public static int fromBytes22(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 3) {
            if (i + 3 < ArrayExtensions.getLength(bArr)) {
                return ((int) BitAssistant.rightShiftLong(fromBytes32(bArr, i, false), 10 - i2)) & 4194303;
            }
        } else if (i + 2 < ArrayExtensions.getLength(bArr)) {
            return BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 2 - i2) & 4194303;
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 22 bits."));
    }

    public static int fromBytes23(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i2 >= 2) {
            if (i + 3 < ArrayExtensions.getLength(bArr)) {
                return ((int) BitAssistant.rightShiftLong(fromBytes32(bArr, i, false), 9 - i2)) & 8388607;
            }
        } else if (i + 2 < ArrayExtensions.getLength(bArr)) {
            return BitAssistant.rightShiftInteger(fromBytes24(bArr, i, false), 1 - i2) & 8388607;
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 23 bits."));
    }

    public static int fromBytes24(byte[] bArr, int i, boolean z) {
        int leftShiftInteger;
        int leftShiftInteger2;
        if (z) {
            int i2 = i + 1;
            leftShiftInteger = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i]), 0) | 0 | BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i2]), 8);
            leftShiftInteger2 = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i2 + 1]), 16);
        } else {
            int i3 = i + 1;
            leftShiftInteger = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i]), 16) | 0 | BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i3]), 8);
            leftShiftInteger2 = BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i3 + 1]), 0);
        }
        return leftShiftInteger2 | leftShiftInteger;
    }

    public static int fromBytes3(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return BitAssistant.rightShiftInteger(fromBytes8(bArr, i), 5 - i2) & 7;
    }

    public static long fromBytes32(byte[] bArr, int i, boolean z) {
        if (z) {
            int i2 = i + 1;
            long leftShiftLong = 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 0);
            int i3 = i2 + 1;
            return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i3 + 1]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i2]), 8) | leftShiftLong | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i3]), 16);
        }
        int i4 = i + 1;
        int i5 = i4 + 1;
        return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i5 + 1]), 0) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 24) | 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i4]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i5]), 8);
    }

    public static int fromBytes4(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return BitAssistant.rightShiftInteger(fromBytes8(bArr, i), 4 - i2) & 15;
    }

    public static long fromBytes40(byte[] bArr, int i, boolean z) {
        if (z) {
            int i2 = i + 1;
            long leftShiftLong = 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 0);
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i4 + 1]), 32) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i2]), 8) | leftShiftLong | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i3]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i4]), 24);
        }
        int i5 = i + 1;
        int i6 = i5 + 1;
        int i7 = i6 + 1;
        return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i7 + 1]), 0) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 32) | 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i5]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i6]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i7]), 8);
    }

    public static long fromBytes48(byte[] bArr, int i, boolean z) {
        if (z) {
            int i2 = i + 1;
            long leftShiftLong = 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 0);
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i5 + 1]), 40) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i2]), 8) | leftShiftLong | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i3]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i4]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i5]), 32);
        }
        int i6 = i + 1;
        int i7 = i6 + 1;
        int i8 = i7 + 1;
        int i9 = i8 + 1;
        return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i9 + 1]), 0) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 40) | 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i6]), 32) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i7]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i8]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i9]), 8);
    }

    public static int fromBytes5(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return BitAssistant.rightShiftInteger(fromBytes8(bArr, i), 3 - i2) & 31;
    }

    public static long fromBytes56(byte[] bArr, int i, boolean z) {
        if (z) {
            int i2 = i + 1;
            long leftShiftLong = 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 0);
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i6 + 1]), 48) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i2]), 8) | leftShiftLong | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i3]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i4]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i5]), 32) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i6]), 40);
        }
        int i7 = i + 1;
        int i8 = i7 + 1;
        int i9 = i8 + 1;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i11 + 1]), 0) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 48) | 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i7]), 40) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i8]), 32) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i9]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i10]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i11]), 8);
    }

    public static int fromBytes6(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return BitAssistant.rightShiftInteger(fromBytes8(bArr, i), 2 - i2) & 63;
    }

    public static long fromBytes64(byte[] bArr, int i, boolean z) {
        if (z) {
            int i2 = i + 1;
            long leftShiftLong = 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 0);
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i7 + 1]), 56) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i2]), 8) | leftShiftLong | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i3]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i4]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i5]), 32) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i6]), 40) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i7]), 48);
        }
        int i8 = i + 1;
        int i9 = i8 + 1;
        int i10 = i9 + 1;
        int i11 = i10 + 1;
        int i12 = i11 + 1;
        int i13 = i12 + 1;
        return BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i13 + 1]), 0) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i]), 56) | 0 | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i8]), 48) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i9]), 40) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i10]), 32) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i11]), 24) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i12]), 16) | BitAssistant.leftShiftLong(BitAssistant.castLong(bArr[i13]), 8);
    }

    public static int fromBytes7(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        return BitAssistant.rightShiftInteger(fromBytes8(bArr, i), 1 - i2) & 127;
    }

    public static int fromBytes8(byte[] bArr, int i) {
        return BitAssistant.leftShiftInteger(BitAssistant.castInteger(bArr[i]), 0);
    }

    public static int fromBytes9(byte[] bArr, int i, int i2) {
        while (i2 >= 8) {
            i2 -= 8;
            i++;
        }
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            return BitAssistant.rightShiftInteger(fromBytes16(bArr, i, false), 7 - i2) & 511;
        }
        throw new RuntimeException(new Exception("Input data is not large enough to read 9 bits."));
    }

    public static void interleave(byte[] bArr, byte[] bArr2) {
        interleave(bArr, bArr2, 0, ArrayExtensions.getLength(bArr), false);
    }

    public static void interleave(byte[] bArr, byte[] bArr2, int i, int i2) {
        interleave(bArr, bArr2, i, i2, false);
    }

    public static void interleave(byte[] bArr, byte[] bArr2, int i, int i2, boolean z) {
        int i3;
        int i4 = i + i2;
        int i5 = i2 / 2;
        if (i4 > ArrayExtensions.getLength(bArr2)) {
            Log.error("start + length greater than outputFrame length");
            return;
        }
        if (z) {
            i3 = i;
            i += i5;
        } else {
            i3 = i + i5;
        }
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = i6 + 1;
            bArr2[i6] = bArr[i + i7];
            i6 = i8 + 1;
            bArr2[i8] = bArr[i3 + i7];
        }
    }

    public static void interleaveTransform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        interleaveTransform(bArr, bArr2, i, i2, i3, i4, 0, false);
    }

    public static void interleaveTransform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5) {
        interleaveTransform(bArr, bArr2, i, i2, i3, i4, i5, false);
    }

    public static void interleaveTransform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, boolean z) {
        int i6;
        int i7;
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i8 = i;
        int i9 = i4;
        int i10 = i5;
        boolean z2 = z;
        int i11 = i3 > 0 ? i3 * i2 : i8 * i2;
        int i12 = i8 * i2 * 2;
        int i13 = i3 > 0 ? i3 - i8 : i8;
        if (i12 + i10 > ArrayExtensions.getLength(bArr2)) {
            Log.error("start + length greater than outputFrame length");
            return;
        }
        if (z2) {
            i7 = i10 + i11;
            i6 = i10;
        } else {
            i6 = i10 + i11;
            i7 = i10;
        }
        int i14 = i2 + 1;
        int[] iArr = new int[i14];
        int i15 = 0;
        for (int i16 = 0; i16 < i14; i16++) {
            iArr[i16] = (i8 * i16) + i10 + ((i16 - 1) * i13);
        }
        if (i9 == 270) {
            for (int i17 = 1; i17 <= i8; i17++) {
                for (int i18 = 1; i18 < i14; i18++) {
                    int i19 = i15 + 1;
                    bArr4[i15] = bArr3[(iArr[i18] + i7) - i17];
                    i15 = i19 + 1;
                    bArr4[i19] = bArr3[(iArr[i18] + i6) - i17];
                }
            }
        } else if (i9 == 90) {
            int i20 = 0;
            while (i15 < i8) {
                for (int i21 = i14 - 2; i21 >= 0; i21--) {
                    int i22 = i20 + 1;
                    bArr4[i20] = bArr3[iArr[i21] + i7 + i15 + i13];
                    i20 = i22 + 1;
                    bArr4[i22] = bArr3[iArr[i21] + i6 + i15 + i13];
                }
                i15++;
            }
        } else if (i9 == 180) {
            for (int i23 = i11 - 1; i23 >= 0; i23--) {
                int i24 = i15 + 1;
                bArr4[i15] = bArr3[i7 + i23];
                i15 = i24 + 1;
                bArr4[i24] = bArr3[i6 + i23];
            }
        } else {
            interleave(bArr3, bArr4, i10, i11 * 2, z2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = r1 % r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int roundUp(int r1, int r2) {
        /*
            if (r2 != 0) goto L_0x0003
            return r1
        L_0x0003:
            int r0 = r1 % r2
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            int r1 = r1 + r2
            int r1 = r1 - r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.Binary.roundUp(int, int):int");
    }

    private static byte[] toBytes(int i, int i2, boolean z, byte[] bArr, int i3, int i4) {
        if (i2 >= 8) {
            return toBytes(i, i2 % 8, z, bArr, i3 + (i2 / 8), i4);
        }
        int roundUp = roundUp(i4 + i2, 8);
        int i5 = roundUp / 8;
        if (i3 + i5 > ArrayExtensions.getLength(bArr)) {
            throw new RuntimeException(new Exception(StringExtensions.format("Output data is not large enough to write {0} bits.", (Object) IntegerExtensions.toString(Integer.valueOf(i4)))));
        } else if (!z) {
            int leftShiftInteger = BitAssistant.leftShiftInteger(i, (roundUp - i4) - i2);
            if (i5 > 0) {
                byte rightShiftInteger = (byte) (BitAssistant.rightShiftInteger(__bitmasks[i4 - 1][0], i2) & 255);
                int i6 = roundUp - 8;
                bArr[i3] = (byte) (((byte) BitAssistant.rightShiftInteger(BitAssistant.leftShiftInteger(~rightShiftInteger, i6) & leftShiftInteger, i6)) | (bArr[i3] & rightShiftInteger));
                i3++;
            }
            if (i5 > 1) {
                byte rightShiftInteger2 = (byte) (BitAssistant.rightShiftInteger(__bitmasks[i4 - 1][1], i2) & 255);
                int i7 = roundUp - 16;
                bArr[i3] = (byte) (((byte) BitAssistant.rightShiftInteger(BitAssistant.leftShiftInteger(~rightShiftInteger2, i7) & leftShiftInteger, i7)) | (bArr[i3] & rightShiftInteger2));
                i3++;
            }
            if (i5 > 2) {
                byte rightShiftInteger3 = (byte) (BitAssistant.rightShiftInteger(__bitmasks[i4 - 1][2], i2) & 255);
                int i8 = roundUp - 24;
                bArr[i3] = (byte) (((byte) BitAssistant.rightShiftInteger(BitAssistant.leftShiftInteger(~rightShiftInteger3, i8) & leftShiftInteger, i8)) | (bArr[i3] & rightShiftInteger3));
                i3++;
            }
            if (i5 > 3) {
                byte rightShiftInteger4 = (byte) (BitAssistant.rightShiftInteger(__bitmasks[i4 - 1][3], i2) & 255);
                int i9 = roundUp - 32;
                bArr[i3] = (byte) (((byte) BitAssistant.rightShiftInteger(leftShiftInteger & BitAssistant.leftShiftInteger(~rightShiftInteger4, i9), i9)) | (bArr[i3] & rightShiftInteger4));
            }
            return bArr;
        } else {
            throw new RuntimeException(new Exception("Little-endian bit-level serialization is not supported."));
        }
    }

    public static byte[] toBytes1(boolean z, int i) {
        int i2 = 1;
        int i3 = 0;
        while (i >= 8) {
            i -= 8;
            i3++;
            i2++;
        }
        return toBytes1(z, i, new byte[i2], i3);
    }

    public static byte[] toBytes1(boolean z, int i, byte[] bArr, int i2) {
        int i3 = i;
        int i4 = i2;
        while (i3 >= 8) {
            i3 -= 8;
            i4++;
        }
        return toBytes(z ? 1 : 0, i3, false, bArr, i4, 1);
    }

    public static byte[] toBytes10(int i, int i2, boolean z) {
        int i3 = 2;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 7) {
            return toBytes10(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes10(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes10(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 10);
    }

    public static byte[] toBytes11(int i, int i2, boolean z) {
        int i3 = 2;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 6) {
            return toBytes11(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes11(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes11(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 11);
    }

    public static byte[] toBytes12(int i, int i2, boolean z) {
        int i3 = 2;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 5) {
            return toBytes12(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes12(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes12(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 12);
    }

    public static byte[] toBytes13(int i, int i2, boolean z) {
        int i3 = 2;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 4) {
            return toBytes13(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes13(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes13(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 13);
    }

    public static byte[] toBytes14(int i, int i2, boolean z) {
        int i3 = 2;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 3) {
            return toBytes14(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes14(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes14(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 14);
    }

    public static byte[] toBytes15(int i, int i2, boolean z) {
        int i3 = 0;
        int i4 = 2;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 2) {
            return toBytes15(i, i2, z, new byte[(i4 + 1)], i3);
        }
        return toBytes15(i, i2, z, new byte[i4], i3);
    }

    public static byte[] toBytes15(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 15);
    }

    public static byte[] toBytes16(int i, boolean z) {
        return toBytes16(i, z, new byte[2], 0);
    }

    public static byte[] toBytes16(int i, boolean z, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        if (i3 >= ArrayExtensions.getLength(bArr)) {
            return null;
        }
        if (z) {
            bArr[i2] = (byte) BitAssistant.rightShiftInteger(i & 255, 0);
            bArr[i3] = (byte) BitAssistant.rightShiftInteger(i & 65280, 8);
            return bArr;
        }
        bArr[i2] = (byte) BitAssistant.rightShiftInteger(i & 65280, 8);
        bArr[i3] = (byte) BitAssistant.rightShiftInteger(i & 255, 0);
        return bArr;
    }

    public static byte[] toBytes17(int i, int i2, boolean z) {
        int i3 = 3;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        return toBytes17(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes17(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 17);
    }

    public static byte[] toBytes18(int i, int i2, boolean z) {
        int i3 = 3;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 7) {
            return toBytes18(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes18(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes18(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 18);
    }

    public static byte[] toBytes19(int i, int i2, boolean z) {
        int i3 = 3;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 6) {
            return toBytes19(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes19(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes19(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 19);
    }

    public static byte[] toBytes2(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 7) {
            return toBytes2(i, i2, new byte[(i4 + 1)], i3);
        }
        return toBytes2(i, i2, new byte[i4], i3);
    }

    public static byte[] toBytes2(int i, int i2, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, false, bArr, i5, 2);
    }

    public static byte[] toBytes20(int i, int i2, boolean z) {
        int i3 = 3;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 5) {
            return toBytes20(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes20(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes20(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 20);
    }

    public static byte[] toBytes21(int i, int i2, boolean z) {
        int i3 = 3;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 4) {
            return toBytes21(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes21(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes21(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 21);
    }

    public static byte[] toBytes22(int i, int i2, boolean z) {
        int i3 = 0;
        int i4 = 3;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 3) {
            return toBytes22(i, i2, z, new byte[(i4 + 1)], i3);
        }
        return toBytes22(i, i2, z, new byte[i4], i3);
    }

    public static byte[] toBytes22(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, false, bArr, i5, 22);
    }

    public static byte[] toBytes23(int i, int i2, boolean z) {
        int i3 = 3;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        if (i2 >= 2) {
            return toBytes23(i, i2, z, new byte[(i3 + 1)], i4);
        }
        return toBytes23(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes23(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 23);
    }

    public static byte[] toBytes24(int i, boolean z) {
        return toBytes24(i, z, new byte[3], 0);
    }

    public static byte[] toBytes24(int i, boolean z, byte[] bArr, int i2) {
        if (i2 + 2 >= ArrayExtensions.getLength(bArr)) {
            return null;
        }
        if (z) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) BitAssistant.rightShiftInteger(i & 255, 0);
            bArr[i3] = (byte) BitAssistant.rightShiftInteger(i & 65280, 8);
            bArr[i3 + 1] = (byte) BitAssistant.rightShiftInteger(i & 16711680, 16);
            return bArr;
        }
        int i4 = i2 + 1;
        bArr[i2] = (byte) BitAssistant.rightShiftInteger(16711680 & i, 16);
        bArr[i4] = (byte) BitAssistant.rightShiftInteger(65280 & i, 8);
        bArr[i4 + 1] = (byte) BitAssistant.rightShiftInteger(i & 255, 0);
        return bArr;
    }

    public static byte[] toBytes3(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 6) {
            return toBytes3(i, i2, new byte[(i4 + 1)], i3);
        }
        return toBytes3(i, i2, new byte[i4], i3);
    }

    public static byte[] toBytes3(int i, int i2, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, false, bArr, i5, 3);
    }

    public static byte[] toBytes32(long j, boolean z) {
        return toBytes32(j, z, new byte[4], 0);
    }

    public static byte[] toBytes32(long j, boolean z, byte[] bArr, int i) {
        if (i + 3 >= ArrayExtensions.getLength(bArr)) {
            return null;
        }
        if (z) {
            int i2 = i + 1;
            bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(255 & j, 0));
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((int) BitAssistant.rightShiftLong(j & 65280, 8));
            bArr[i3] = (byte) ((int) BitAssistant.rightShiftLong(j & 16711680, 16));
            bArr[i3 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j & 4278190080L, 24));
            return bArr;
        }
        int i4 = i + 1;
        bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(4278190080L & j, 24));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) BitAssistant.rightShiftLong(16711680 & j, 16));
        bArr[i5] = (byte) ((int) BitAssistant.rightShiftLong(65280 & j, 8));
        bArr[i5 + 1] = (byte) ((int) BitAssistant.rightShiftLong(255 & j, 0));
        return bArr;
    }

    public static byte[] toBytes4(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 5) {
            return toBytes4(i, i2, new byte[(i4 + 1)], i3);
        }
        return toBytes4(i, i2, new byte[i4], i3);
    }

    public static byte[] toBytes4(int i, int i2, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, false, bArr, i5, 4);
    }

    public static byte[] toBytes40(long j, boolean z) {
        return toBytes40(j, z, new byte[5], 0);
    }

    public static byte[] toBytes40(long j, boolean z, byte[] bArr, int i) {
        if (i + 4 >= ArrayExtensions.getLength(bArr)) {
            return null;
        }
        if (z) {
            int i2 = i + 1;
            bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j & 255, 0));
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((int) BitAssistant.rightShiftLong(j & 65280, 8));
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((int) BitAssistant.rightShiftLong(j & 16711680, 16));
            bArr[i4] = (byte) ((int) BitAssistant.rightShiftLong(j & 4278190080L, 24));
            bArr[i4 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j & 1095216660480L, 32));
            return bArr;
        }
        int i5 = i + 1;
        bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j & 1095216660480L, 32));
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) BitAssistant.rightShiftLong(j & 4278190080L, 24));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) BitAssistant.rightShiftLong(j & 16711680, 16));
        bArr[i7] = (byte) ((int) BitAssistant.rightShiftLong(j & 65280, 8));
        bArr[i7 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j & 255, 0));
        return bArr;
    }

    public static byte[] toBytes48(long j, boolean z) {
        return toBytes48(j, z, new byte[6], 0);
    }

    public static byte[] toBytes48(long j, boolean z, byte[] bArr, int i) {
        if (i + 5 >= ArrayExtensions.getLength(bArr)) {
            return null;
        }
        if (z) {
            int i2 = i + 1;
            bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j & 255, 0));
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((int) BitAssistant.rightShiftLong(j & 65280, 8));
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((int) BitAssistant.rightShiftLong(j & 16711680, 16));
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((int) BitAssistant.rightShiftLong(j & 4278190080L, 24));
            bArr[i5] = (byte) ((int) BitAssistant.rightShiftLong(j & 1095216660480L, 32));
            bArr[i5 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j & 280375465082880L, 40));
            return bArr;
        }
        int i6 = i + 1;
        bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j & 280375465082880L, 40));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) BitAssistant.rightShiftLong(j & 1095216660480L, 32));
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((int) BitAssistant.rightShiftLong(j & 4278190080L, 24));
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((int) BitAssistant.rightShiftLong(j & 16711680, 16));
        bArr[i9] = (byte) ((int) BitAssistant.rightShiftLong(j & 65280, 8));
        bArr[i9 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j & 255, 0));
        return bArr;
    }

    public static byte[] toBytes5(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 4) {
            return toBytes5(i, i2, new byte[(i4 + 1)], i3);
        }
        return toBytes5(i, i2, new byte[i4], i3);
    }

    public static byte[] toBytes5(int i, int i2, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, false, bArr, i5, 5);
    }

    public static byte[] toBytes56(long j, boolean z) {
        return toBytes56(j, z, new byte[7], 0);
    }

    public static byte[] toBytes56(long j, boolean z, byte[] bArr, int i) {
        if (i + 6 >= ArrayExtensions.getLength(bArr)) {
            return null;
        }
        if (z) {
            int i2 = i + 1;
            bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j & 255, 0));
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((int) BitAssistant.rightShiftLong(j & 65280, 8));
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((int) BitAssistant.rightShiftLong(j & 16711680, 16));
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((int) BitAssistant.rightShiftLong(j & 4278190080L, 24));
            int i6 = i5 + 1;
            bArr[i5] = (byte) ((int) BitAssistant.rightShiftLong(j & 1095216660480L, 32));
            bArr[i6] = (byte) ((int) BitAssistant.rightShiftLong(j & 280375465082880L, 40));
            bArr[i6 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j & 71776119061217280L, 48));
            return bArr;
        }
        int i7 = i + 1;
        bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j & 71776119061217280L, 48));
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((int) BitAssistant.rightShiftLong(j & 280375465082880L, 40));
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((int) BitAssistant.rightShiftLong(j & 1095216660480L, 32));
        int i10 = i9 + 1;
        bArr[i9] = (byte) ((int) BitAssistant.rightShiftLong(j & 4278190080L, 24));
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((int) BitAssistant.rightShiftLong(j & 16711680, 16));
        bArr[i11] = (byte) ((int) BitAssistant.rightShiftLong(j & 65280, 8));
        bArr[i11 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j & 255, 0));
        return bArr;
    }

    public static byte[] toBytes6(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 3) {
            return toBytes6(i, i2, new byte[(i4 + 1)], i3);
        }
        return toBytes6(i, i2, new byte[i4], i3);
    }

    public static byte[] toBytes6(int i, int i2, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, false, bArr, i5, 6);
    }

    public static byte[] toBytes64(long j, boolean z) {
        return toBytes64(j, z, new byte[8], 0);
    }

    public static byte[] toBytes64(long j, boolean z, byte[] bArr, int i) {
        long j2 = j;
        if (i + 7 >= ArrayExtensions.getLength(bArr)) {
            return null;
        }
        if (z) {
            int i2 = i + 1;
            bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j2, 0));
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((int) BitAssistant.rightShiftLong(65280 & j2, 8));
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((int) BitAssistant.rightShiftLong(j2 & 16711680, 16));
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((int) BitAssistant.rightShiftLong(j2 & 4278190080L, 24));
            int i6 = i5 + 1;
            bArr[i5] = (byte) ((int) BitAssistant.rightShiftLong(1095216660480L & j2, 32));
            int i7 = i6 + 1;
            bArr[i6] = (byte) ((int) BitAssistant.rightShiftLong(j2 & 280375465082880L, 40));
            bArr[i7] = (byte) ((int) BitAssistant.rightShiftLong(j2 & 71776119061217280L, 48));
            bArr[i7 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j2, 56));
            return bArr;
        }
        int i8 = i + 1;
        bArr[i] = (byte) ((int) BitAssistant.rightShiftLong(j2, 56));
        int i9 = i8 + 1;
        bArr[i8] = (byte) ((int) BitAssistant.rightShiftLong(j2 & 71776119061217280L, 48));
        int i10 = i9 + 1;
        bArr[i9] = (byte) ((int) BitAssistant.rightShiftLong(j2 & 280375465082880L, 40));
        int i11 = i10 + 1;
        bArr[i10] = (byte) ((int) BitAssistant.rightShiftLong(1095216660480L & j2, 32));
        int i12 = i11 + 1;
        bArr[i11] = (byte) ((int) BitAssistant.rightShiftLong(4278190080L & j2, 24));
        int i13 = i12 + 1;
        bArr[i12] = (byte) ((int) BitAssistant.rightShiftLong(16711680 & j2, 16));
        bArr[i13] = (byte) ((int) BitAssistant.rightShiftLong(65280 & j2, 8));
        bArr[i13 + 1] = (byte) ((int) BitAssistant.rightShiftLong(j2 & 255, 0));
        return bArr;
    }

    public static byte[] toBytes7(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        while (i2 >= 8) {
            i2 -= 8;
            i3++;
            i4++;
        }
        if (i2 >= 2) {
            return toBytes7(i, i2, new byte[(i4 + 1)], i3);
        }
        return toBytes7(i, i2, new byte[i4], i3);
    }

    public static byte[] toBytes7(int i, int i2, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, false, bArr, i5, 7);
    }

    public static byte[] toBytes8(int i) {
        return toBytes8(i, new byte[1], 0);
    }

    public static byte[] toBytes8(int i, byte[] bArr, int i2) {
        if (i2 < ArrayExtensions.getLength(bArr)) {
            bArr[i2] = (byte) BitAssistant.rightShiftInteger(i & 255, 0);
            return bArr;
        }
        throw new RuntimeException(new Exception("Output data is not large enough to write 8 bits."));
    }

    public static byte[] toBytes9(int i, int i2, boolean z) {
        int i3 = 2;
        int i4 = 0;
        while (i2 >= 8) {
            i2 -= 8;
            i4++;
            i3++;
        }
        return toBytes9(i, i2, z, new byte[i3], i4);
    }

    public static byte[] toBytes9(int i, int i2, boolean z, byte[] bArr, int i3) {
        int i4 = i2;
        int i5 = i3;
        while (i4 >= 8) {
            i4 -= 8;
            i5++;
        }
        return toBytes(i, i4, z, bArr, i5, 9);
    }

    public static void transform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) {
        transform(bArr, bArr2, i, i2, i3, i4, 0, 0);
    }

    public static void transform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        transform(bArr, bArr2, i, i2, i3, i4, i5, i6, 1);
    }

    public static void transform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i8 = i2;
        int i9 = i4;
        int i10 = i5;
        int i11 = i6;
        int i12 = i7;
        int i13 = i * i8 * i12;
        int i14 = i3 > 0 ? i3 * i8 * i12 : i13;
        int i15 = i11 > 0 ? i11 : i10;
        int i16 = i3 > 0 ? (i3 - i) * i12 : 0;
        if (i13 + i11 > ArrayExtensions.getLength(bArr2)) {
            Log.error("otuputStart + calculated output plane length is greater than outputFrame length");
            return;
        }
        int i17 = i8 + 1;
        int[] iArr = new int[i17];
        int i18 = i * i12;
        for (int i19 = 0; i19 < i17; i19++) {
            iArr[i19] = (i18 * i19) + i10 + ((i19 - 1) * i16);
        }
        if (i9 == 270) {
            for (int i20 = 1; i20 <= i18; i20 += i12) {
                for (int i21 = 1; i21 < i17; i21++) {
                    int i22 = i12 - 1;
                    while (i22 >= 0) {
                        bArr4[i15] = bArr3[(iArr[i21] - i20) - i22];
                        i22--;
                        i15++;
                    }
                }
            }
        } else if (i9 == 90) {
            for (int i23 = 0; i23 < i18; i23 += i12) {
                for (int i24 = i17 - 2; i24 >= 0; i24--) {
                    int i25 = 0;
                    while (i25 < i12) {
                        bArr4[i15] = bArr3[iArr[i24] + i16 + i23 + i25];
                        i25++;
                        i15++;
                    }
                }
            }
        } else if (i9 == 180) {
            int i26 = ((i10 + i14) - i16) - 1;
            int i27 = 0;
            while (i26 >= i10) {
                if (i16 == 0 || i27 == 0 || i27 % i18 != 0) {
                    int i28 = i12 - 1;
                    while (i28 >= 0) {
                        bArr4[i15] = bArr3[i26 - i28];
                        i27++;
                        i28--;
                        i15++;
                    }
                } else {
                    i26 -= i16 - i12;
                    i27 = 0;
                }
                i26 -= i12;
            }
        } else if (i16 == 0) {
            BitAssistant.copy(bArr3, i10, bArr4, i11, i14);
        } else {
            for (int i29 = 0; i29 < i8; i29++) {
                BitAssistant.copy(bArr3, (i3 * i12 * i29) + i10, bArr4, (i18 * i29) + i11, i18);
            }
        }
    }

    public static void transform(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5) {
        transform(bArr, bArr2, i, i2, i3, i4, i5, i5);
    }

    public static boolean tryFromBytes1(byte[] bArr, int i, int i2, BooleanHolder booleanHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            booleanHolder.setValue(fromBytes1(bArr, i, i2));
            return true;
        }
        booleanHolder.setValue(false);
        return false;
    }

    public static boolean tryFromBytes10(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i2 >= 7) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                integerHolder.setValue(fromBytes10(bArr, i, i2));
                return true;
            }
        } else if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes10(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes11(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i2 >= 6) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                integerHolder.setValue(fromBytes11(bArr, i, i2));
                return true;
            }
        } else if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes11(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes12(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i2 >= 5) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                integerHolder.setValue(fromBytes12(bArr, i, i2));
                return true;
            }
        } else if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes12(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes13(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i2 >= 4) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                integerHolder.setValue(fromBytes13(bArr, i, i2));
                return true;
            }
        } else if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes13(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes14(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i2 >= 3) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                integerHolder.setValue(fromBytes14(bArr, i, i2));
                return true;
            }
        } else if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes14(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes15(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i2 >= 2) {
            if (i + 2 < ArrayExtensions.getLength(bArr)) {
                integerHolder.setValue(fromBytes15(bArr, i, i2));
                return true;
            }
        } else if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes15(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes16(byte[] bArr, int i, boolean z, IntegerHolder integerHolder) {
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes16(bArr, i, z));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes17(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes17(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes18(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i2 >= 7) {
            if (i + 3 < ArrayExtensions.getLength(bArr)) {
                integerHolder.setValue(fromBytes18(bArr, i, i2));
                return true;
            }
        } else if (i + 2 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes18(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes19(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes19(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes2(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes2(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes20(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes20(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes21(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes21(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes22(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes22(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes23(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes23(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes24(byte[] bArr, int i, boolean z, IntegerHolder integerHolder) {
        if (i + 2 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes24(bArr, i, z));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes3(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes3(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes32(byte[] bArr, int i, boolean z, LongHolder longHolder) {
        if (i + 3 < ArrayExtensions.getLength(bArr)) {
            longHolder.setValue(fromBytes32(bArr, i, z));
            return true;
        }
        longHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes4(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes4(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes40(byte[] bArr, int i, boolean z, LongHolder longHolder) {
        if (i + 4 < ArrayExtensions.getLength(bArr)) {
            longHolder.setValue(fromBytes40(bArr, i, z));
            return true;
        }
        longHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes48(byte[] bArr, int i, boolean z, LongHolder longHolder) {
        if (i + 5 < ArrayExtensions.getLength(bArr)) {
            longHolder.setValue(fromBytes48(bArr, i, z));
            return true;
        }
        longHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes5(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes5(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes56(byte[] bArr, int i, boolean z, LongHolder longHolder) {
        if (i + 6 < ArrayExtensions.getLength(bArr)) {
            longHolder.setValue(fromBytes56(bArr, i, z));
            return true;
        }
        longHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes6(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes6(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes64(byte[] bArr, int i, boolean z, LongHolder longHolder) {
        if (i + 7 < ArrayExtensions.getLength(bArr)) {
            longHolder.setValue(fromBytes64(bArr, i, z));
            return true;
        }
        longHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes7(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes7(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes8(byte[] bArr, int i, IntegerHolder integerHolder) {
        if (i < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes8(bArr, i));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }

    public static boolean tryFromBytes9(byte[] bArr, int i, int i2, IntegerHolder integerHolder) {
        if (i + 1 < ArrayExtensions.getLength(bArr)) {
            integerHolder.setValue(fromBytes9(bArr, i, i2));
            return true;
        }
        integerHolder.setValue(0);
        return false;
    }
}
