package fm.liveswitch;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class WebSocketMockResponse extends URLConnection {
    private int _contentLength;
    private String _contentType;
    private HashMap<String, String> _headers;
    private int _statusCode;

    public void connect() throws IOException {
    }

    public WebSocketMockResponse() {
        this((URL) null);
    }

    protected WebSocketMockResponse(URL url) {
        super(url);
        this._headers = new HashMap<>();
        this._contentLength = 0;
        this._statusCode = 0;
    }

    public void setResponseUri(URI uri) {
        try {
            this.url = uri.toURL();
        } catch (Exception unused) {
        }
    }

    public HashMap<String, String> getHeaders() {
        return this._headers;
    }

    public void setContentLength(long j) {
        this._contentLength = new Long(j).intValue();
    }

    public int getContentLength() {
        return this._contentLength;
    }

    public void setStatusCode(int i) {
        this._statusCode = i;
    }

    public int getStatusCode() {
        return this._statusCode;
    }

    public void setContentType(String str) {
        this._contentType = str;
    }

    public String getContentType() {
        return this._contentType;
    }
}
