package fm.liveswitch;

import java.util.Arrays;
import kotlin.UByte;

public class BitAssistant {
    public static byte castByte(int i) {
        return (byte) i;
    }

    public static int castInteger(byte b) {
        return b < 0 ? b + UByte.MIN_VALUE : b;
    }

    public static long castLong(byte b) {
        long j = (long) b;
        return j < 0 ? j + 256 : j;
    }

    public static byte leftShift(byte b, int i) {
        return (byte) (b << i);
    }

    public static int leftShiftInteger(int i, int i2) {
        return i << i2;
    }

    public static long leftShiftLong(long j, int i) {
        return j << i;
    }

    public static short leftShiftShort(short s, int i) {
        return (short) (s << i);
    }

    public static byte rightShift(byte b, int i) {
        return (byte) (b >>> i);
    }

    public static int rightShiftInteger(int i, int i2) {
        return i >>> i2;
    }

    public static long rightShiftLong(long j, int i) {
        return j >>> i;
    }

    public static short rightShiftShort(short s, int i) {
        return (short) (s >>> i);
    }

    public static boolean sequencesAreEqual(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean sequencesAreEqual(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    public static boolean sequencesAreEqualConstantTime(byte[] bArr, byte[] bArr2) {
        boolean z = true;
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                z = false;
            }
        }
        return z;
    }

    public static boolean sequencesAreEqualConstantTime(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        boolean z = true;
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                z = false;
            }
        }
        return z;
    }

    public static byte[] subArray(byte[] bArr, int i) {
        return subArray(bArr, i, bArr.length - i);
    }

    public static byte[] subArray(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return bArr2;
    }

    public static void reverse(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[(bArr.length - i) - 1];
            bArr[(bArr.length - i) - 1] = bArr[i];
            bArr[i] = b;
        }
    }

    public static void copy(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        System.arraycopy(bArr, i, bArr2, i2, i3);
    }

    public static void copyFloats(float[] fArr, int i, float[] fArr2, int i2, int i3) {
        System.arraycopy(fArr, i, fArr2, i2, i3);
    }

    public static void set(byte[] bArr, int i, int i2, int i3) {
        Arrays.fill(bArr, i, i2 + i, (byte) i3);
    }

    public static String getHexString(byte[] bArr) {
        return getHexString(bArr, 0, bArr.length);
    }

    public static String getHexString(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3++) {
            String hexString = Integer.toHexString(bArr[i3] & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static byte[] getHexBytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String getBinaryString(byte[] bArr) {
        return getBinaryString(bArr, 0, bArr.length);
    }

    public static String getBinaryString(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder(i2 * 8);
        for (int i3 = i; i3 < i + i2; i3++) {
            sb.append(String.format("%8s", new Object[]{Integer.toBinaryString(bArr[i3] & UByte.MAX_VALUE)}).replace(' ', '0'));
        }
        return sb.toString();
    }

    public static byte[] getBinaryBytes(String str) {
        int i = 0;
        if (str.length() % 8 != 0) {
            return new byte[0];
        }
        int length = str.length() / 8;
        byte[] bArr = new byte[length];
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 8;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i3), 2);
            i++;
            i2 = i3;
        }
        return bArr;
    }

    public static byte[] convertSingleToBytes(float f, boolean z) {
        int floatToIntBits = Float.floatToIntBits(f);
        if (z) {
            return new byte[]{(byte) floatToIntBits, (byte) (floatToIntBits >> 8), (byte) (floatToIntBits >> 16), (byte) (floatToIntBits >> 24)};
        }
        return new byte[]{(byte) (floatToIntBits >> 24), (byte) (floatToIntBits >> 16), (byte) (floatToIntBits >> 8), (byte) floatToIntBits};
    }

    public static float convertBytesToSingle(byte[] bArr, int i, boolean z) {
        byte b;
        int i2;
        if (z) {
            b = (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8) | ((bArr[2] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH);
            i2 = (bArr[3] & UByte.MAX_VALUE) << 24;
        } else {
            b = ((bArr[0] & UByte.MAX_VALUE) << 24) | ((bArr[1] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH) | ((bArr[2] & UByte.MAX_VALUE) << 8);
            i2 = bArr[3] & UByte.MAX_VALUE;
        }
        return Float.intBitsToFloat(i2 | b);
    }

    public static byte[] convertDoubleToBytes(double d, boolean z) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (z) {
            return new byte[]{(byte) ((int) doubleToLongBits), (byte) ((int) (doubleToLongBits >> 8)), (byte) ((int) (doubleToLongBits >> 16)), (byte) ((int) (doubleToLongBits >> 24)), (byte) ((int) (doubleToLongBits >> 32)), (byte) ((int) (doubleToLongBits >> 40)), (byte) ((int) (doubleToLongBits >> 48)), (byte) ((int) (doubleToLongBits >> 56))};
        }
        return new byte[]{(byte) ((int) (doubleToLongBits >> 56)), (byte) ((int) (doubleToLongBits >> 48)), (byte) ((int) (doubleToLongBits >> 40)), (byte) ((int) (doubleToLongBits >> 32)), (byte) ((int) (doubleToLongBits >> 24)), (byte) ((int) (doubleToLongBits >> 16)), (byte) ((int) (doubleToLongBits >> 8)), (byte) ((int) doubleToLongBits)};
    }

    public static double convertBytesToDouble(byte[] bArr, int i, boolean z) {
        byte b;
        int i2;
        if (z) {
            b = (bArr[0] & UByte.MAX_VALUE) | ((bArr[1] & UByte.MAX_VALUE) << 8) | ((bArr[2] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH) | ((bArr[3] & UByte.MAX_VALUE) << 24) | ((bArr[4] & UByte.MAX_VALUE) << 32) | ((bArr[5] & UByte.MAX_VALUE) << 40) | ((bArr[6] & UByte.MAX_VALUE) << 48);
            i2 = (bArr[7] & UByte.MAX_VALUE) << 56;
        } else {
            b = ((bArr[0] & UByte.MAX_VALUE) << 56) | ((bArr[1] & UByte.MAX_VALUE) << 48) | ((bArr[2] & UByte.MAX_VALUE) << 40) | ((bArr[3] & UByte.MAX_VALUE) << 32) | ((bArr[4] & UByte.MAX_VALUE) << 24) | ((bArr[5] & UByte.MAX_VALUE) << Tnaf.POW_2_WIDTH) | ((bArr[6] & UByte.MAX_VALUE) << 8);
            i2 = bArr[7] & UByte.MAX_VALUE;
        }
        return Double.longBitsToDouble((long) (i2 | b));
    }
}
