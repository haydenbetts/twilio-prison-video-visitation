package fm.liveswitch.sdp;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.StringExtensions;
import java.net.URI;

public abstract class EncryptionKey {
    /* access modifiers changed from: package-private */
    public abstract String getMethodAndValue();

    protected EncryptionKey() {
    }

    public static EncryptionKey parse(String str) {
        String str2;
        String substring = str.substring(2);
        int indexOf = StringExtensions.indexOf(substring, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        if (indexOf == -1) {
            str2 = null;
        } else {
            String substring2 = StringExtensions.substring(substring, 0, indexOf);
            str2 = substring.substring(indexOf + 1);
            substring = substring2;
        }
        if (substring.equals("clear")) {
            return new ClearEncryptionKey(str2);
        }
        if (substring.equals("base64")) {
            return new Base64EncryptionKey(str2);
        }
        if (substring.equals("uri")) {
            try {
                return new UriEncryptionKey(new URI(str2));
            } catch (Exception unused) {
                return null;
            }
        } else if (substring.equals("prompt")) {
            return new PromptEncryptionKey();
        } else {
            return null;
        }
    }

    public String toString() {
        return StringExtensions.concat("k=", getMethodAndValue());
    }
}
