package fm.liveswitch;

public class WebSocketBase {
    private static ILog __log = Log.getLogger(WebSocketBase.class);

    /* access modifiers changed from: protected */
    public void raiseCloseComplete(WebSocketCloseArgs webSocketCloseArgs, WebSocketStatusCode webSocketStatusCode, String str) {
        if (webSocketCloseArgs != null && webSocketCloseArgs.getOnComplete() != null) {
            WebSocketCloseCompleteArgs webSocketCloseCompleteArgs = new WebSocketCloseCompleteArgs();
            webSocketCloseCompleteArgs.setCloseArgs(webSocketCloseArgs);
            webSocketCloseCompleteArgs.setStatusCode(webSocketStatusCode);
            webSocketCloseCompleteArgs.setReason(str);
            webSocketCloseArgs.getOnComplete().invoke(webSocketCloseCompleteArgs);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseOpenFailure(WebSocketOpenArgs webSocketOpenArgs, WebSocketStatusCode webSocketStatusCode, Exception exc) {
        if (webSocketOpenArgs != null && webSocketOpenArgs.getOnFailure() != null) {
            WebSocketOpenFailureArgs webSocketOpenFailureArgs = new WebSocketOpenFailureArgs();
            webSocketOpenFailureArgs.setOpenArgs(webSocketOpenArgs);
            webSocketOpenFailureArgs.setStatusCode(webSocketStatusCode);
            webSocketOpenFailureArgs.setException(exc);
            webSocketOpenArgs.getOnFailure().invoke(webSocketOpenFailureArgs);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseOpenSuccess(WebSocketOpenArgs webSocketOpenArgs) {
        if (webSocketOpenArgs != null && webSocketOpenArgs.getOnSuccess() != null) {
            WebSocketOpenSuccessArgs webSocketOpenSuccessArgs = new WebSocketOpenSuccessArgs();
            webSocketOpenSuccessArgs.setOpenArgs(webSocketOpenArgs);
            webSocketOpenArgs.getOnSuccess().invoke(webSocketOpenSuccessArgs);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseReceive(WebSocketOpenArgs webSocketOpenArgs, String str, byte[] bArr) {
        if (webSocketOpenArgs != null && webSocketOpenArgs.getOnReceive() != null) {
            WebSocketReceiveArgs webSocketReceiveArgs = new WebSocketReceiveArgs();
            webSocketReceiveArgs.setOpenArgs(webSocketOpenArgs);
            webSocketReceiveArgs.setTextMessage(str);
            webSocketReceiveArgs.setBinaryMessage(bArr);
            webSocketOpenArgs.getOnReceive().invoke(webSocketReceiveArgs);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseStreamFailure(WebSocketOpenArgs webSocketOpenArgs, WebSocketStatusCode webSocketStatusCode, Exception exc) {
        __log.error("Websocket Stream failure.", exc);
        if (webSocketOpenArgs != null && webSocketOpenArgs.getOnStreamFailure() != null) {
            WebSocketStreamFailureArgs webSocketStreamFailureArgs = new WebSocketStreamFailureArgs();
            webSocketStreamFailureArgs.setOpenArgs(webSocketOpenArgs);
            webSocketStreamFailureArgs.setStatusCode(webSocketStatusCode);
            webSocketStreamFailureArgs.setException(exc);
            webSocketOpenArgs.getOnStreamFailure().invoke(webSocketStreamFailureArgs);
        }
    }
}
