package fm.liveswitch;

import java.net.URLConnection;

public class HttpResponseReceivedArgs {
    private HttpRequestArgs _requestArgs;
    private URLConnection _response;
    private Object _sender;

    public HttpRequestArgs getRequestArgs() {
        return this._requestArgs;
    }

    public URLConnection getResponse() {
        return this._response;
    }

    public Object getSender() {
        return this._sender;
    }

    public void setRequestArgs(HttpRequestArgs httpRequestArgs) {
        this._requestArgs = httpRequestArgs;
    }

    public void setResponse(URLConnection uRLConnection) {
        this._response = uRLConnection;
    }

    public void setSender(Object obj) {
        this._sender = obj;
    }
}
