package fm.liveswitch;

class WebSocketRequest {
    private HttpRequestArgs _args;
    private IAction1<HttpResponseArgs> _callback;

    public HttpRequestArgs getArgs() {
        return this._args;
    }

    public IAction1<HttpResponseArgs> getCallback() {
        return this._callback;
    }

    public void setArgs(HttpRequestArgs httpRequestArgs) {
        this._args = httpRequestArgs;
    }

    public void setCallback(IAction1<HttpResponseArgs> iAction1) {
        this._callback = iAction1;
    }
}
