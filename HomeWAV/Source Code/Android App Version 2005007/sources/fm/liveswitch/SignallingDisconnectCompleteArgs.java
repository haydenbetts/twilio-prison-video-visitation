package fm.liveswitch;

class SignallingDisconnectCompleteArgs extends SignallingCompleteArgs {
    private Exception _exception;

    public Exception getException() {
        return this._exception;
    }

    /* access modifiers changed from: package-private */
    public void setException(Exception exc) {
        this._exception = exc;
    }
}
