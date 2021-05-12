package fm.liveswitch;

abstract class SignallingInputArgs extends SignallingExtensible {
    private NullableInteger _requestMaxRetries = new NullableInteger();
    private NullableInteger _requestTimeout = new NullableInteger();
    private String _requestUrl;
    private NullableBoolean _synchronous = new NullableBoolean();

    public NullableInteger getRequestMaxRetries() {
        return this._requestMaxRetries;
    }

    public NullableInteger getRequestTimeout() {
        return this._requestTimeout;
    }

    public String getRequestUrl() {
        return this._requestUrl;
    }

    public NullableBoolean getSynchronous() {
        return this._synchronous;
    }

    public void setRequestMaxRetries(NullableInteger nullableInteger) {
        this._requestMaxRetries = nullableInteger;
    }

    public void setRequestTimeout(NullableInteger nullableInteger) {
        this._requestTimeout = nullableInteger;
    }

    public void setRequestUrl(String str) {
        this._requestUrl = str;
    }

    public void setSynchronous(NullableBoolean nullableBoolean) {
        this._synchronous = nullableBoolean;
    }

    protected SignallingInputArgs() {
    }
}
