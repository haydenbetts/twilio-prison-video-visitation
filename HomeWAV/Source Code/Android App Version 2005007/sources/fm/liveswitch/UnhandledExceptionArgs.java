package fm.liveswitch;

public class UnhandledExceptionArgs {
    private Exception __exception;
    private boolean _handled;

    public Exception getException() {
        return this.__exception;
    }

    public boolean getHandled() {
        return this._handled;
    }

    public void setHandled(boolean z) {
        this._handled = z;
    }

    public UnhandledExceptionArgs(Exception exc) {
        this.__exception = exc;
    }
}
