package fm.liveswitch;

import java.io.UnsupportedEncodingException;

public class Encoding {
    public static Encoding getAscii() {
        return new Encoding();
    }

    public static Encoding getUtf8() {
        return new Encoding();
    }

    public String getString(byte[] bArr) {
        return getString(bArr, 0, bArr.length);
    }

    public String getString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, i, i2, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr, i, i2);
        }
    }

    public byte[] getBytes(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public int getByteCount(String str) {
        return getBytes(str).length;
    }
}
