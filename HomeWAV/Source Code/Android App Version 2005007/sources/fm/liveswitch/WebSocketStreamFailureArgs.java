package fm.liveswitch;

public class WebSocketStreamFailureArgs extends Dynamic {
    private Exception _exception;
    private WebSocketOpenArgs _openArgs;
    private WebSocketStatusCode _statusCode;

    public Exception getException() {
        return this._exception;
    }

    public WebSocketOpenArgs getOpenArgs() {
        return this._openArgs;
    }

    public WebSocketStatusCode getStatusCode() {
        return this._statusCode;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    public void setOpenArgs(WebSocketOpenArgs webSocketOpenArgs) {
        this._openArgs = webSocketOpenArgs;
    }

    public void setStatusCode(WebSocketStatusCode webSocketStatusCode) {
        this._statusCode = webSocketStatusCode;
    }
}
