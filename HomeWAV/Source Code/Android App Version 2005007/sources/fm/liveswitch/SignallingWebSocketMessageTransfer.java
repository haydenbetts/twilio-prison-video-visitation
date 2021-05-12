package fm.liveswitch;

import java.util.HashMap;

class SignallingWebSocketMessageTransfer extends SignallingMessageTransfer {
    private String _url;
    private WebSocketTransfer _webSocketTransfer;

    public int getHandshakeTimeout() {
        return this._webSocketTransfer.getHandshakeTimeout();
    }

    public IAction1<WebSocketOpenFailureArgs> getOnOpenFailure() {
        return this._webSocketTransfer.getOnOpenFailure();
    }

    public IAction1<WebSocketOpenSuccessArgs> getOnOpenSuccess() {
        return this._webSocketTransfer.getOnOpenSuccess();
    }

    public IAction1<HttpRequestCreatedArgs> getOnRequestCreated() {
        return this._webSocketTransfer.getOnRequestCreated();
    }

    public IAction1<HttpResponseReceivedArgs> getOnResponseReceived() {
        return this._webSocketTransfer.getOnResponseReceived();
    }

    public IAction1<WebSocketStreamFailureArgs> getOnStreamFailure() {
        return this._webSocketTransfer.getOnStreamFailure();
    }

    public Object getSender() {
        return this._webSocketTransfer.getSender();
    }

    public int getStreamTimeout() {
        return this._webSocketTransfer.getHandshakeTimeout();
    }

    public String getUrl() {
        return this._url;
    }

    public void open(HashMap<String, String> hashMap) {
        this._webSocketTransfer.open(hashMap);
    }

    public SignallingMessageResponseArgs sendMessages(SignallingMessageRequestArgs signallingMessageRequestArgs) {
        return super.httpResponseArgsToMessageResponseArgs(this._webSocketTransfer.send(super.messageRequestArgsToHttpRequestArgs(signallingMessageRequestArgs)));
    }

    public void sendMessagesAsync(SignallingMessageRequestArgs signallingMessageRequestArgs, final IAction1<SignallingMessageResponseArgs> iAction1) {
        this._webSocketTransfer.sendAsync(super.messageRequestArgsToHttpRequestArgs(signallingMessageRequestArgs), new IAction1<HttpResponseArgs>() {
            public void invoke(HttpResponseArgs httpResponseArgs) {
                iAction1.invoke(SignallingWebSocketMessageTransfer.this.httpResponseArgsToMessageResponseArgs(httpResponseArgs));
            }
        });
    }

    public void setHandshakeTimeout(int i) {
        this._webSocketTransfer.setHandshakeTimeout(i);
    }

    public void setOnOpenFailure(IAction1<WebSocketOpenFailureArgs> iAction1) {
        this._webSocketTransfer.setOnOpenFailure(iAction1);
    }

    public void setOnOpenSuccess(IAction1<WebSocketOpenSuccessArgs> iAction1) {
        this._webSocketTransfer.setOnOpenSuccess(iAction1);
    }

    public void setOnRequestCreated(IAction1<HttpRequestCreatedArgs> iAction1) {
        this._webSocketTransfer.setOnRequestCreated(iAction1);
    }

    public void setOnResponseReceived(IAction1<HttpResponseReceivedArgs> iAction1) {
        this._webSocketTransfer.setOnResponseReceived(iAction1);
    }

    public void setOnStreamFailure(IAction1<WebSocketStreamFailureArgs> iAction1) {
        this._webSocketTransfer.setOnStreamFailure(iAction1);
    }

    public void setSender(Object obj) {
        this._webSocketTransfer.setSender(obj);
    }

    public void setStreamTimeout(int i) {
        this._webSocketTransfer.setHandshakeTimeout(i);
    }

    public void setUrl(String str) {
        this._url = str;
    }

    public void shutdown() {
        try {
            this._webSocketTransfer.shutdown();
        } catch (Exception unused) {
        }
    }

    private SignallingWebSocketMessageTransfer() {
    }

    public SignallingWebSocketMessageTransfer(String str) {
        this();
        setUrl(str);
        this._webSocketTransfer = WebSocketTransferFactory.getWebSocketTransfer(getUrl());
    }
}
