package fm.liveswitch;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class WebSocketMockRequest extends URLConnection {
    private HashMap<String, String> _headers;
    private String _method;

    public void connect() throws IOException {
    }

    public WebSocketMockRequest() {
        this((URL) null);
    }

    protected WebSocketMockRequest(URL url) {
        super(url);
        this._headers = new HashMap<>();
    }

    public void setRequestUri(URI uri) {
        try {
            this.url = uri.toURL();
        } catch (Exception unused) {
        }
    }

    public HashMap<String, String> getHeaders() {
        return this._headers;
    }

    public void setMethod(String str) {
        this._method = str;
    }

    public String getMethod() {
        return this._method;
    }
}
