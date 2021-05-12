package fm.liveswitch;

class SignallingMessageRequestCreatedArgs {
    private SignallingMessage[] _requests;
    private Object _sender;

    public SignallingMessage[] getRequests() {
        return this._requests;
    }

    public Object getSender() {
        return this._sender;
    }

    /* access modifiers changed from: package-private */
    public void setRequests(SignallingMessage[] signallingMessageArr) {
        this._requests = signallingMessageArr;
    }

    /* access modifiers changed from: package-private */
    public void setSender(Object obj) {
        this._sender = obj;
    }
}
