package fm.liveswitch;

abstract class SignallingPublisherResponseArgs extends SignallingPublisherArgs {
    private Exception _exception;

    public Exception getException() {
        return this._exception;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    protected SignallingPublisherResponseArgs() {
    }
}
