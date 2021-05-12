package tvi.webrtc;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.bouncycastle.i18n.LocalizedMessage;

class JniHelper {
    JniHelper() {
    }

    static byte[] getStringBytes(String str) {
        try {
            return str.getBytes(LocalizedMessage.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("ISO-8859-1 is unsupported");
        }
    }

    static Object getStringClass() {
        return String.class;
    }

    static Object getKey(Map.Entry entry) {
        return entry.getKey();
    }

    static Object getValue(Map.Entry entry) {
        return entry.getValue();
    }
}
