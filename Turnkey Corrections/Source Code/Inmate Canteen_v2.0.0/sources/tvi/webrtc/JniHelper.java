package tvi.webrtc;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

class JniHelper {
    JniHelper() {
    }

    @CalledByNative
    static byte[] getStringBytes(String str) {
        try {
            return str.getBytes(HttpRequest.CHARSET_LATIN1);
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("ISO-8859-1 is unsupported");
        }
    }

    @CalledByNative
    static Object getStringClass() {
        return String.class;
    }

    @CalledByNative
    static Object getKey(Map.Entry entry) {
        return entry.getKey();
    }

    @CalledByNative
    static Object getValue(Map.Entry entry) {
        return entry.getValue();
    }
}
