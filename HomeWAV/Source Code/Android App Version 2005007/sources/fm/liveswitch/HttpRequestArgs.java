package fm.liveswitch;

import java.util.HashMap;

public class HttpRequestArgs extends Dynamic {
    private HashMap<String, String> __headers = new HashMap<>();
    private byte[] _binaryContent;
    private int _maxRetries;
    private HttpMethod _method;
    private IAction1<HttpRequestCreatedArgs> _onRequestCreated;
    private IAction1<HttpResponseReceivedArgs> _onResponseReceived;
    private Object _sender;
    private String _textContent;
    private int _timeout;
    private String _url;

    public byte[] getBinaryContent() {
        return this._binaryContent;
    }

    public HashMap<String, String> getHeaders() {
        return this.__headers;
    }

    public int getMaxRetries() {
        return this._maxRetries;
    }

    public HttpMethod getMethod() {
        return this._method;
    }

    public IAction1<HttpRequestCreatedArgs> getOnRequestCreated() {
        return this._onRequestCreated;
    }

    public IAction1<HttpResponseReceivedArgs> getOnResponseReceived() {
        return this._onResponseReceived;
    }

    public Object getSender() {
        return this._sender;
    }

    public String getTextContent() {
        return this._textContent;
    }

    public int getTimeout() {
        return this._timeout;
    }

    public String getUrl() {
        return this._url;
    }

    public HttpRequestArgs() {
        setTimeout(15000);
        setMethod(HttpMethod.Post);
    }

    public void setBinaryContent(byte[] bArr) {
        this._binaryContent = bArr;
    }

    public void setHeaders(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            this.__headers = new HashMap<>();
        } else {
            this.__headers = hashMap;
        }
    }

    public void setMaxRetries(int i) {
        this._maxRetries = i;
    }

    public void setMethod(HttpMethod httpMethod) {
        this._method = httpMethod;
    }

    public void setOnRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        this._onRequestCreated = iAction1;
    }

    public void setOnResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        this._onResponseReceived = iAction1;
    }

    public void setSender(Object obj) {
        this._sender = obj;
    }

    public void setTextContent(String str) {
        this._textContent = str;
    }

    public void setTimeout(int i) {
        this._timeout = i;
    }

    public void setUrl(String str) {
        this._url = str;
    }
}
