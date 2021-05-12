package fm.liveswitch;

class SignallingMessageTransferFactory {
    private static IFunction0<SignallingMessageTransfer> _createHttpMessageTransfer;
    private static IFunction1<String, SignallingWebSocketMessageTransfer> _createWebSocketMessageTransfer;

    static SignallingMessageTransfer defaultCreateHttpMessageTransfer() {
        return new SignallingHttpMessageTransfer();
    }

    static SignallingWebSocketMessageTransfer defaultCreateWebSocketMessageTransfer(String str) {
        return new SignallingWebSocketMessageTransfer(str);
    }

    public static IFunction0<SignallingMessageTransfer> getCreateHttpMessageTransfer() {
        return _createHttpMessageTransfer;
    }

    public static IFunction1<String, SignallingWebSocketMessageTransfer> getCreateWebSocketMessageTransfer() {
        return _createWebSocketMessageTransfer;
    }

    public static SignallingMessageTransfer getHttpMessageTransfer() {
        if (getCreateHttpMessageTransfer() == null) {
            setCreateHttpMessageTransfer(new IFunctionDelegate0<SignallingMessageTransfer>() {
                public String getId() {
                    return "fm.liveswitch.SignallingMessageTransferFactory.defaultCreateHttpMessageTransfer";
                }

                public SignallingMessageTransfer invoke() {
                    return SignallingMessageTransferFactory.defaultCreateHttpMessageTransfer();
                }
            });
        }
        SignallingMessageTransfer invoke = getCreateHttpMessageTransfer().invoke();
        return invoke == null ? defaultCreateHttpMessageTransfer() : invoke;
    }

    public static SignallingWebSocketMessageTransfer getWebSocketMessageTransfer(String str) {
        if (getCreateWebSocketMessageTransfer() == null) {
            setCreateWebSocketMessageTransfer(new IFunctionDelegate1<String, SignallingWebSocketMessageTransfer>() {
                public String getId() {
                    return "fm.liveswitch.SignallingMessageTransferFactory.defaultCreateWebSocketMessageTransfer";
                }

                public SignallingWebSocketMessageTransfer invoke(String str) {
                    return SignallingMessageTransferFactory.defaultCreateWebSocketMessageTransfer(str);
                }
            });
        }
        SignallingWebSocketMessageTransfer invoke = getCreateWebSocketMessageTransfer().invoke(str);
        return invoke == null ? defaultCreateWebSocketMessageTransfer(str) : invoke;
    }

    public static void setCreateHttpMessageTransfer(IFunction0<SignallingMessageTransfer> iFunction0) {
        _createHttpMessageTransfer = iFunction0;
    }

    public static void setCreateWebSocketMessageTransfer(IFunction1<String, SignallingWebSocketMessageTransfer> iFunction1) {
        _createWebSocketMessageTransfer = iFunction1;
    }
}
