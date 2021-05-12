package fm.liveswitch;

import java.util.HashMap;

public class WebSocketWebRequestTransfer extends WebSocketTransfer {
    private WebSocketRequest _activeRequest;
    private WebSocket _webSocket;

    /* access modifiers changed from: private */
    public void connectFailure(WebSocketOpenFailureArgs webSocketOpenFailureArgs) {
        if (super.getOnOpenFailure() != null) {
            super.getOnOpenFailure().invoke(webSocketOpenFailureArgs);
        }
    }

    /* access modifiers changed from: private */
    public void connectSuccess(WebSocketOpenSuccessArgs webSocketOpenSuccessArgs) {
        if (super.getOnOpenSuccess() != null) {
            super.getOnOpenSuccess().invoke(webSocketOpenSuccessArgs);
        }
    }

    private WebSocket getWebSocket() {
        return this._webSocket;
    }

    public void open(HashMap<String, String> hashMap) {
        if (getWebSocket() != null) {
            getWebSocket().close();
            setWebSocket((WebSocket) null);
        }
        setWebSocket(new WebSocket(super.getUrl()));
        try {
            WebSocketOpenArgs webSocketOpenArgs = new WebSocketOpenArgs();
            webSocketOpenArgs.setHandshakeTimeout(super.getHandshakeTimeout());
            webSocketOpenArgs.setHeaders(hashMap);
            webSocketOpenArgs.setOnSuccess(new IActionDelegate1<WebSocketOpenSuccessArgs>() {
                public String getId() {
                    return "fm.liveswitch.WebSocketWebRequestTransfer.connectSuccess";
                }

                public void invoke(WebSocketOpenSuccessArgs webSocketOpenSuccessArgs) {
                    WebSocketWebRequestTransfer.this.connectSuccess(webSocketOpenSuccessArgs);
                }
            });
            webSocketOpenArgs.setOnFailure(new IActionDelegate1<WebSocketOpenFailureArgs>() {
                public String getId() {
                    return "fm.liveswitch.WebSocketWebRequestTransfer.connectFailure";
                }

                public void invoke(WebSocketOpenFailureArgs webSocketOpenFailureArgs) {
                    WebSocketWebRequestTransfer.this.connectFailure(webSocketOpenFailureArgs);
                }
            });
            webSocketOpenArgs.setOnStreamFailure(new IActionDelegate1<WebSocketStreamFailureArgs>() {
                public String getId() {
                    return "fm.liveswitch.WebSocketWebRequestTransfer.streamFailure";
                }

                public void invoke(WebSocketStreamFailureArgs webSocketStreamFailureArgs) {
                    WebSocketWebRequestTransfer.this.streamFailure(webSocketStreamFailureArgs);
                }
            });
            webSocketOpenArgs.setOnRequestCreated(super.getOnRequestCreated());
            webSocketOpenArgs.setOnResponseReceived(super.getOnResponseReceived());
            webSocketOpenArgs.setOnReceive(new IActionDelegate1<WebSocketReceiveArgs>() {
                public String getId() {
                    return "fm.liveswitch.WebSocketWebRequestTransfer.receive";
                }

                public void invoke(WebSocketReceiveArgs webSocketReceiveArgs) {
                    WebSocketWebRequestTransfer.this.receive(webSocketReceiveArgs);
                }
            });
            webSocketOpenArgs.setSender(super.getSender());
            getWebSocket().open(webSocketOpenArgs);
        } catch (Exception e) {
            Log.error("Could not open WebSocket.", e);
        }
    }

    /* access modifiers changed from: private */
    public void receive(WebSocketReceiveArgs webSocketReceiveArgs) {
        HttpResponseArgs httpResponseArgs = new HttpResponseArgs(this._activeRequest.getArgs());
        httpResponseArgs.setTextContent(webSocketReceiveArgs.getTextMessage());
        httpResponseArgs.setBinaryContent(webSocketReceiveArgs.getBinaryMessage());
        this._activeRequest.getCallback().invoke(httpResponseArgs);
    }

    public HttpResponseArgs send(HttpRequestArgs httpRequestArgs) {
        throw new RuntimeException(new Exception("Synchronous WebSockets are not supported."));
    }

    public void sendAsync(HttpRequestArgs httpRequestArgs, IAction1<HttpResponseArgs> iAction1) {
        try {
            WebSocketRequest webSocketRequest = new WebSocketRequest();
            webSocketRequest.setArgs(httpRequestArgs);
            webSocketRequest.setCallback(iAction1);
            WebSocketSendArgs webSocketSendArgs = new WebSocketSendArgs();
            webSocketSendArgs.setTimeout(webSocketRequest.getArgs().getTimeout());
            webSocketSendArgs.setTextMessage(webSocketRequest.getArgs().getTextContent());
            webSocketSendArgs.setBinaryMessage(webSocketRequest.getArgs().getBinaryContent());
            this._activeRequest = webSocketRequest;
            getWebSocket().send(webSocketSendArgs);
        } catch (Exception e) {
            HttpResponseArgs httpResponseArgs = new HttpResponseArgs(httpRequestArgs);
            httpResponseArgs.setException(e);
            httpResponseArgs.setStatusCode(0);
            iAction1.invoke(httpResponseArgs);
        }
    }

    private void setWebSocket(WebSocket webSocket) {
        this._webSocket = webSocket;
    }

    public void shutdown() {
        super.setOnOpenFailure((IAction1<WebSocketOpenFailureArgs>) null);
        super.setOnOpenSuccess((IAction1<WebSocketOpenSuccessArgs>) null);
        super.setOnRequestCreated((IAction1<HttpRequestCreatedArgs>) null);
        super.setOnResponseReceived((IAction1<HttpResponseReceivedArgs>) null);
        super.setOnStreamFailure((IAction1<WebSocketStreamFailureArgs>) null);
        getWebSocket().close();
        setWebSocket((WebSocket) null);
    }

    /* access modifiers changed from: private */
    public void streamFailure(WebSocketStreamFailureArgs webSocketStreamFailureArgs) {
        if (super.getOnStreamFailure() != null) {
            super.getOnStreamFailure().invoke(webSocketStreamFailureArgs);
        }
    }

    public WebSocketWebRequestTransfer(String str) {
        super(str);
    }
}
