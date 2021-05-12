package fm.liveswitch;

class SignallingMessageResponseReceivedArgs {
    private SignallingMessage[] _responses;
    private Object _sender;

    public SignallingMessage[] getResponses() {
        return this._responses;
    }

    public Object getSender() {
        return this._sender;
    }

    /* access modifiers changed from: package-private */
    public void setResponses(SignallingMessage[] signallingMessageArr) {
        this._responses = signallingMessageArr;
    }

    /* access modifiers changed from: package-private */
    public void setSender(Object obj) {
        this._sender = obj;
    }
}
