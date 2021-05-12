package fm.liveswitch;

public class WebSocketReceiveArgs extends Dynamic {
    private byte[] _binaryMessage;
    private WebSocketOpenArgs _openArgs;
    private String _textMessage;

    public byte[] getBinaryMessage() {
        return this._binaryMessage;
    }

    public boolean getIsText() {
        return getTextMessage() != null;
    }

    public WebSocketOpenArgs getOpenArgs() {
        return this._openArgs;
    }

    public String getTextMessage() {
        return this._textMessage;
    }

    public void setBinaryMessage(byte[] bArr) {
        this._binaryMessage = bArr;
    }

    public void setOpenArgs(WebSocketOpenArgs webSocketOpenArgs) {
        this._openArgs = webSocketOpenArgs;
    }

    public void setTextMessage(String str) {
        this._textMessage = str;
    }
}
