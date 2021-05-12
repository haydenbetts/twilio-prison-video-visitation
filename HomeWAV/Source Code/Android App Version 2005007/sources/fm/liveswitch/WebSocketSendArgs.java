package fm.liveswitch;

public class WebSocketSendArgs extends Dynamic {
    private byte[] _binaryMessage;
    private String _textMessage;
    private int _timeout;

    public byte[] getBinaryMessage() {
        return this._binaryMessage;
    }

    /* access modifiers changed from: package-private */
    public boolean getIsText() {
        return getTextMessage() != null;
    }

    public String getTextMessage() {
        return this._textMessage;
    }

    public int getTimeout() {
        return this._timeout;
    }

    public void setBinaryMessage(byte[] bArr) {
        this._binaryMessage = bArr;
    }

    public void setTextMessage(String str) {
        this._textMessage = str;
    }

    public void setTimeout(int i) {
        this._timeout = i;
    }

    public WebSocketSendArgs() {
        setTimeout(15000);
    }
}
