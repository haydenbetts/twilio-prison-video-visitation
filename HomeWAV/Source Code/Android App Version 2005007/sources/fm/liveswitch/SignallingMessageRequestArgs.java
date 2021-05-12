package fm.liveswitch;

import java.util.HashMap;

class SignallingMessageRequestArgs extends Dynamic {
    private HashMap<String, String> _headers;
    private int _maxRetries;
    private SignallingMessage[] _messages;
    private IAction1<HttpRequestCreatedArgs> _onHttpRequestCreated;
    private IAction1<HttpResponseReceivedArgs> _onHttpResponseReceived;
    private IAction1<SignallingMessageRequestCreatedArgs> _onRequestCreated;
    private IAction1<SignallingMessageResponseReceivedArgs> _onResponseReceived;
    private Object _sender;
    private int _timeout;
    private String _url;

    public HashMap<String, String> getHeaders() {
        return this._headers;
    }

    public boolean getIsBinary() {
        for (SignallingMessage signallingMessage : getMessages()) {
            if (!signallingMessage.getDisableBinary().hasValue() || signallingMessage.getDisableBinary().getValue() || !signallingMessage.getIsBinary()) {
                return false;
            }
        }
        return true;
    }

    public int getMaxRetries() {
        return this._maxRetries;
    }

    public SignallingMessage[] getMessages() {
        return this._messages;
    }

    public IAction1<HttpRequestCreatedArgs> getOnHttpRequestCreated() {
        return this._onHttpRequestCreated;
    }

    public IAction1<HttpResponseReceivedArgs> getOnHttpResponseReceived() {
        return this._onHttpResponseReceived;
    }

    public IAction1<SignallingMessageRequestCreatedArgs> getOnRequestCreated() {
        return this._onRequestCreated;
    }

    public IAction1<SignallingMessageResponseReceivedArgs> getOnResponseReceived() {
        return this._onResponseReceived;
    }

    public Object getSender() {
        return this._sender;
    }

    public int getTimeout() {
        return this._timeout;
    }

    public String getUrl() {
        return this._url;
    }

    public void setHeaders(HashMap<String, String> hashMap) {
        this._headers = hashMap;
    }

    public void setMaxRetries(int i) {
        this._maxRetries = i;
    }

    public void setMessages(SignallingMessage[] signallingMessageArr) {
        this._messages = signallingMessageArr;
    }

    public void setOnHttpRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        this._onHttpRequestCreated = iAction1;
    }

    public void setOnHttpResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        this._onHttpResponseReceived = iAction1;
    }

    public void setOnRequestCreated(IAction1<SignallingMessageRequestCreatedArgs> iAction1) {
        this._onRequestCreated = iAction1;
    }

    public void setOnResponseReceived(IAction1<SignallingMessageResponseReceivedArgs> iAction1) {
        this._onResponseReceived = iAction1;
    }

    public void setSender(Object obj) {
        this._sender = obj;
    }

    public void setTimeout(int i) {
        this._timeout = i;
    }

    public void setUrl(String str) {
        this._url = str;
    }

    private SignallingMessageRequestArgs() {
        setTimeout(15000);
    }

    public SignallingMessageRequestArgs(HashMap<String, String> hashMap) {
        this();
        setHeaders(hashMap);
    }
}
