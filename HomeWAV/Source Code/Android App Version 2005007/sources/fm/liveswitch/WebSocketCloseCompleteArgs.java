package fm.liveswitch;

public class WebSocketCloseCompleteArgs extends Dynamic {
    private WebSocketCloseArgs _closeArgs;
    private String _reason;
    private WebSocketStatusCode _statusCode;

    public WebSocketCloseArgs getCloseArgs() {
        return this._closeArgs;
    }

    public String getReason() {
        return this._reason;
    }

    public WebSocketStatusCode getStatusCode() {
        return this._statusCode;
    }

    public void setCloseArgs(WebSocketCloseArgs webSocketCloseArgs) {
        this._closeArgs = webSocketCloseArgs;
    }

    public void setReason(String str) {
        this._reason = str;
    }

    public void setStatusCode(WebSocketStatusCode webSocketStatusCode) {
        this._statusCode = webSocketStatusCode;
    }
}
