package fm.liveswitch;

public class WebSocketOpenSuccessArgs extends Dynamic {
    private WebSocketOpenArgs _openArgs;

    public WebSocketOpenArgs getOpenArgs() {
        return this._openArgs;
    }

    public void setOpenArgs(WebSocketOpenArgs webSocketOpenArgs) {
        this._openArgs = webSocketOpenArgs;
    }
}
