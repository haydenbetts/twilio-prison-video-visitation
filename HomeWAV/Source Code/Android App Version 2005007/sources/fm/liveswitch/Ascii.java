package fm.liveswitch;

public class Ascii {
    public static String decode(byte[] bArr) {
        return decode(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static String decode(byte[] bArr, int i, int i2) {
        return Encoding.getAscii().getString(bArr, i, i2);
    }

    public static byte[] encode(String str) {
        return Encoding.getAscii().getBytes(str);
    }

    public static int getByteCount(String str) {
        return Encoding.getAscii().getByteCount(str);
    }
}
