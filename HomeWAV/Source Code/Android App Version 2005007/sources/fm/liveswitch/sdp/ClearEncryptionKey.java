package fm.liveswitch.sdp;

import fm.liveswitch.StringBuilderExtensions;

public class ClearEncryptionKey extends EncryptionKey {
    private String _encryptionKey;

    public ClearEncryptionKey(String str) {
        if (str != null) {
            setEncryptionKey(str);
            return;
        }
        throw new RuntimeException(new Exception("encryptionKey cannot be null."));
    }

    public String getEncryptionKey() {
        return this._encryptionKey;
    }

    /* access modifiers changed from: package-private */
    public String getMethodAndValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "clear:");
        StringBuilderExtensions.append(sb, getEncryptionKey());
        return sb.toString();
    }

    private void setEncryptionKey(String str) {
        this._encryptionKey = str;
    }
}
