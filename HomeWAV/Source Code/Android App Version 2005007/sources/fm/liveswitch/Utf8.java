package fm.liveswitch;

public class Utf8 {
    public static String decode(DataBuffer dataBuffer) {
        return decode(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
    }

    public static String decode(byte[] bArr) {
        return decode(bArr, 0, ArrayExtensions.getLength(bArr));
    }

    public static String decode(byte[] bArr, int i, int i2) {
        return Encoding.getUtf8().getString(bArr, i, i2);
    }

    public static byte[] encode(String str) {
        return Encoding.getUtf8().getBytes(str);
    }

    public static int getByteCount(String str) {
        return Encoding.getUtf8().getByteCount(str);
    }
}
