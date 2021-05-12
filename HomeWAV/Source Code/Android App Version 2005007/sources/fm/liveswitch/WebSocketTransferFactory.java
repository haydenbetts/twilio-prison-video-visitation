package fm.liveswitch;

public class WebSocketTransferFactory {
    private static IFunction1<String, WebSocketTransfer> _createWebSocketTransfer;

    static WebSocketTransfer defaultCreateWebSocketTransfer(String str) {
        return new WebSocketWebRequestTransfer(str);
    }

    public static IFunction1<String, WebSocketTransfer> getCreateWebSocketTransfer() {
        return _createWebSocketTransfer;
    }

    public static WebSocketTransfer getWebSocketTransfer(String str) {
        if (getCreateWebSocketTransfer() == null) {
            setCreateWebSocketTransfer(new IFunctionDelegate1<String, WebSocketTransfer>() {
                public String getId() {
                    return "fm.liveswitch.WebSocketTransferFactory.defaultCreateWebSocketTransfer";
                }

                public WebSocketTransfer invoke(String str) {
                    return WebSocketTransferFactory.defaultCreateWebSocketTransfer(str);
                }
            });
        }
        WebSocketTransfer invoke = getCreateWebSocketTransfer().invoke(str);
        return invoke == null ? defaultCreateWebSocketTransfer(str) : invoke;
    }

    public static void setCreateWebSocketTransfer(IFunction1<String, WebSocketTransfer> iFunction1) {
        _createWebSocketTransfer = iFunction1;
    }
}
