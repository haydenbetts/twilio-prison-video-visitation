package fm.liveswitch;

public class WebSocketCloseArgs extends Dynamic {
    private IAction1<WebSocketCloseCompleteArgs> _onComplete;
    private String _reason;
    private WebSocketStatusCode _statusCode;

    public IAction1<WebSocketCloseCompleteArgs> getOnComplete() {
        return this._onComplete;
    }

    public String getReason() {
        return this._reason;
    }

    public WebSocketStatusCode getStatusCode() {
        return this._statusCode;
    }

    public void setOnComplete(IAction1<WebSocketCloseCompleteArgs> iAction1) {
        this._onComplete = iAction1;
    }

    public void setReason(String str) {
        this._reason = str;
    }

    public void setStatusCode(WebSocketStatusCode webSocketStatusCode) {
        this._statusCode = webSocketStatusCode;
    }

    public WebSocketCloseArgs() {
        setStatusCode(WebSocketStatusCode.Normal);
        setReason(StringExtensions.empty);
    }
}
