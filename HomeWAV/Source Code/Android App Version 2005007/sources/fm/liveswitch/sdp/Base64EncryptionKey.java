package fm.liveswitch.sdp;

import fm.liveswitch.StringBuilderExtensions;

public class Base64EncryptionKey extends EncryptionKey {
    private String _encodedEncryptionKey;

    public Base64EncryptionKey(String str) {
        if (str != null) {
            setEncodedEncryptionKey(str);
            return;
        }
        throw new RuntimeException(new Exception("encodedEncryptionKey cannot be null."));
    }

    public String getEncodedEncryptionKey() {
        return this._encodedEncryptionKey;
    }

    /* access modifiers changed from: package-private */
    public String getMethodAndValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "base64:");
        StringBuilderExtensions.append(sb, getEncodedEncryptionKey());
        return sb.toString();
    }

    private void setEncodedEncryptionKey(String str) {
        this._encodedEncryptionKey = str;
    }
}
