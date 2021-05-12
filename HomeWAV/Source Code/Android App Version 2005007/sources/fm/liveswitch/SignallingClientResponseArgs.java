package fm.liveswitch;

abstract class SignallingClientResponseArgs extends SignallingClientArgs {
    private Exception _exception;
    private SignallingMessage _message;

    public Exception getException() {
        return this._exception;
    }

    public SignallingMessage getMessage() {
        return this._message;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    public void setMessage(SignallingMessage signallingMessage) {
        this._message = signallingMessage;
    }

    protected SignallingClientResponseArgs() {
    }
}
