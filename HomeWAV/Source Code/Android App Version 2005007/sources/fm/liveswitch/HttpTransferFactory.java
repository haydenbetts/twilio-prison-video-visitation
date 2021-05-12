package fm.liveswitch;

public class HttpTransferFactory {
    private static IFunction0<HttpTransfer> _createHttpTransfer;

    static HttpTransfer defaultCreateHttpTransfer() {
        return new HttpWebRequestTransfer();
    }

    public static IFunction0<HttpTransfer> getCreateHttpTransfer() {
        return _createHttpTransfer;
    }

    public static HttpTransfer getHttpTransfer() {
        if (getCreateHttpTransfer() == null) {
            setCreateHttpTransfer(new IFunctionDelegate0<HttpTransfer>() {
                public String getId() {
                    return "fm.liveswitch.HttpTransferFactory.defaultCreateHttpTransfer";
                }

                public HttpTransfer invoke() {
                    return HttpTransferFactory.defaultCreateHttpTransfer();
                }
            });
        }
        HttpTransfer invoke = getCreateHttpTransfer().invoke();
        return invoke == null ? defaultCreateHttpTransfer() : invoke;
    }

    public static void setCreateHttpTransfer(IFunction0<HttpTransfer> iFunction0) {
        _createHttpTransfer = iFunction0;
    }
}
