package fm.liveswitch;

import java.util.HashMap;

public class HttpResponseArgs {
    private byte[] _binaryContent;
    private Exception _exception;
    private HashMap<String, String> _headers;
    private HttpRequestArgs _requestArgs;
    private int _retries;
    private int _statusCode;
    private String _textContent;

    public byte[] getBinaryContent() {
        return this._binaryContent;
    }

    public Exception getException() {
        return this._exception;
    }

    public HashMap<String, String> getHeaders() {
        return this._headers;
    }

    public HttpRequestArgs getRequestArgs() {
        return this._requestArgs;
    }

    public int getRetries() {
        return this._retries;
    }

    public int getStatusCode() {
        return this._statusCode;
    }

    public String getTextContent() {
        return this._textContent;
    }

    private HttpResponseArgs() {
        setHeaders(new HashMap());
    }

    public HttpResponseArgs(HttpRequestArgs httpRequestArgs) {
        this();
        setRequestArgs(httpRequestArgs);
    }

    public void setBinaryContent(byte[] bArr) {
        this._binaryContent = bArr;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    private void setHeaders(HashMap<String, String> hashMap) {
        this._headers = hashMap;
    }

    public void setRequestArgs(HttpRequestArgs httpRequestArgs) {
        this._requestArgs = httpRequestArgs;
    }

    public void setRetries(int i) {
        this._retries = i;
    }

    public void setStatusCode(int i) {
        this._statusCode = i;
    }

    public void setTextContent(String str) {
        this._textContent = str;
    }
}
