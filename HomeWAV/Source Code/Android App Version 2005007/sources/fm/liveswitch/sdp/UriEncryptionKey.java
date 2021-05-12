package fm.liveswitch.sdp;

import fm.liveswitch.StringBuilderExtensions;
import java.net.URI;

public class UriEncryptionKey extends EncryptionKey {
    private URI _uri;

    /* access modifiers changed from: package-private */
    public String getMethodAndValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "uri:");
        StringBuilderExtensions.append(sb, getUri().toString());
        return sb.toString();
    }

    public URI getUri() {
        return this._uri;
    }

    private void setUri(URI uri) {
        this._uri = uri;
    }

    public UriEncryptionKey(URI uri) {
        if (uri != null) {
            setUri(uri);
            return;
        }
        throw new RuntimeException(new Exception("uri cannot be null."));
    }
}
