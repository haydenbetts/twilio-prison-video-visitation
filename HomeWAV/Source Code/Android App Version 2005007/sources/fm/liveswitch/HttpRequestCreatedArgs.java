package fm.liveswitch;

import java.net.URLConnection;

public class HttpRequestCreatedArgs {
    private URLConnection _request;
    private HttpRequestArgs _requestArgs;
    private Object _sender;

    public URLConnection getRequest() {
        return this._request;
    }

    public HttpRequestArgs getRequestArgs() {
        return this._requestArgs;
    }

    public Object getSender() {
        return this._sender;
    }

    public void setRequest(URLConnection uRLConnection) {
        this._request = uRLConnection;
    }

    public void setRequestArgs(HttpRequestArgs httpRequestArgs) {
        this._requestArgs = httpRequestArgs;
    }

    public void setSender(Object obj) {
        this._sender = obj;
    }
}
