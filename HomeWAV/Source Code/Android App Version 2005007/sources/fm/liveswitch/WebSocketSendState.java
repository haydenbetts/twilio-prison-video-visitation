package fm.liveswitch;

class WebSocketSendState {
    private byte[] _requestBytes;
    private WebSocketSendArgs _sendArgs;

    public byte[] getRequestBytes() {
        return this._requestBytes;
    }

    public WebSocketSendArgs getSendArgs() {
        return this._sendArgs;
    }

    public void setRequestBytes(byte[] bArr) {
        this._requestBytes = bArr;
    }

    public void setSendArgs(WebSocketSendArgs webSocketSendArgs) {
        this._sendArgs = webSocketSendArgs;
    }
}
