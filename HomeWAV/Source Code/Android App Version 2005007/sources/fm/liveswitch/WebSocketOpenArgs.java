package fm.liveswitch;

import java.util.HashMap;

public class WebSocketOpenArgs extends Dynamic {
    private int _handshakeTimeout;
    private HashMap<String, String> _headers;
    private IAction1<WebSocketOpenFailureArgs> _onFailure;
    private IAction1<WebSocketReceiveArgs> _onReceive;
    private IAction1<HttpRequestCreatedArgs> _onRequestCreated;
    private IAction1<HttpResponseReceivedArgs> _onResponseReceived;
    private IAction1<WebSocketStreamFailureArgs> _onStreamFailure;
    private IAction1<WebSocketOpenSuccessArgs> _onSuccess;
    private Object _sender;
    private int _streamTimeout;

    public int getHandshakeTimeout() {
        return this._handshakeTimeout;
    }

    public HashMap<String, String> getHeaders() {
        return this._headers;
    }

    public IAction1<WebSocketOpenFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<WebSocketReceiveArgs> getOnReceive() {
        return this._onReceive;
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

    public IAction1<WebSocketOpenSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public Object getSender() {
        return this._sender;
    }

    public int getStreamTimeout() {
        return this._streamTimeout;
    }

    public void setHandshakeTimeout(int i) {
        this._handshakeTimeout = i;
    }

    public void setHeaders(HashMap<String, String> hashMap) {
        this._headers = hashMap;
    }

    public void setOnFailure(IAction1<WebSocketOpenFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnReceive(IAction1<WebSocketReceiveArgs> iAction1) {
        this._onReceive = iAction1;
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

    public void setOnSuccess(IAction1<WebSocketOpenSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }

    public void setSender(Object obj) {
        this._sender = obj;
    }

    public void setStreamTimeout(int i) {
        this._streamTimeout = i;
    }

    public WebSocketOpenArgs() {
        setHandshakeTimeout(15000);
        setStreamTimeout(40000);
    }
}
