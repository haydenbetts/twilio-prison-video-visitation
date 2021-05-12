package fm.liveswitch;

import java.util.HashMap;

public abstract class WebSocketTransfer {
    private String __url;
    private int _handshakeTimeout;
    private IAction1<WebSocketOpenFailureArgs> _onOpenFailure;
    private IAction1<WebSocketOpenSuccessArgs> _onOpenSuccess;
    private IAction1<HttpRequestCreatedArgs> _onRequestCreated;
    private IAction1<HttpResponseReceivedArgs> _onResponseReceived;
    private IAction1<WebSocketStreamFailureArgs> _onStreamFailure;
    private Object _sender;
    private int _streamTimeout;

    public abstract void open(HashMap<String, String> hashMap);

    public abstract HttpResponseArgs send(HttpRequestArgs httpRequestArgs);

    public abstract void sendAsync(HttpRequestArgs httpRequestArgs, IAction1<HttpResponseArgs> iAction1);

    public abstract void shutdown();

    public int getHandshakeTimeout() {
        return this._handshakeTimeout;
    }

    public IAction1<WebSocketOpenFailureArgs> getOnOpenFailure() {
        return this._onOpenFailure;
    }

    public IAction1<WebSocketOpenSuccessArgs> getOnOpenSuccess() {
        return this._onOpenSuccess;
    }

    public IAction1<HttpRequestCreatedArgs> getOnRequestCreated() {
        return this._onRequestCreated;
    }

    public IAction1<HttpResponseReceivedArgs> getOnResponseReceived() {
        return this._onResponseReceived;
    }

    public IAction1<WebSocketStreamFailureArgs> getOnStreamFailure() {
        return this._onStreamFailure;
    }

    public Object getSender() {
        return this._sender;
    }

    public int getStreamTimeout() {
        return this._streamTimeout;
    }

    public String getUrl() {
        return this.__url;
    }

    public void setHandshakeTimeout(int i) {
        this._handshakeTimeout = i;
    }

    public void setOnOpenFailure(IAction1<WebSocketOpenFailureArgs> iAction1) {
        this._onOpenFailure = iAction1;
    }

    public void setOnOpenSuccess(IAction1<WebSocketOpenSuccessArgs> iAction1) {
        this._onOpenSuccess = iAction1;
    }

    public void setOnRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        this._onRequestCreated = iAction1;
    }

    public void setOnResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        this._onResponseReceived = iAction1;
    }

    public void setOnStreamFailure(IAction1<WebSocketStreamFailureArgs> iAction1) {
        this._onStreamFailure = iAction1;
    }

    public void setSender(Object obj) {
        this._sender = obj;
    }

    public void setStreamTimeout(int i) {
        this._streamTimeout = i;
    }

    public void setUrl(String str) {
        this.__url = str.replace("https://", "wss://").replace("http://", "ws://");
    }

    public WebSocketTransfer(String str) {
        setUrl(str);
    }
}
