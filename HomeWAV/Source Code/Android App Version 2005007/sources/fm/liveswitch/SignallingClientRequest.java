package fm.liveswitch;

class SignallingClientRequest extends Dynamic {
    private IAction1<SignallingClientResponse> _callback;
    private SignallingMessage _message;

    public IAction1<SignallingClientResponse> getCallback() {
        return this._callback;
    }

    public SignallingMessage getMessage() {
        return this._message;
    }

    public void setCallback(IAction1<SignallingClientResponse> iAction1) {
        this._callback = iAction1;
    }

    public void setMessage(SignallingMessage signallingMessage) {
        this._message = signallingMessage;
    }
}
