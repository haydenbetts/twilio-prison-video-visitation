package fm.liveswitch;

class SignallingClientResponse extends Dynamic {
    private Exception _exception;
    private FailureSource _failureSource;
    private SignallingMessage[] _messages;

    public int getErrorCode() {
        return SignallingFailureArgs.getErrorCode(getException());
    }

    public String getErrorMessage() {
        return SignallingFailureArgs.getErrorMessage(getException());
    }

    public Exception getException() {
        return this._exception;
    }

    public FailureSource getFailureSource() {
        return this._failureSource;
    }

    public SignallingMessage getMessage() {
        if (getMessages() == null || ArrayExtensions.getLength((Object[]) getMessages()) == 0) {
            return null;
        }
        return getMessages()[0];
    }

    public SignallingMessage[] getMessages() {
        return this._messages;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    public void setFailureSource(FailureSource failureSource) {
        this._failureSource = failureSource;
    }

    public void setMessage(SignallingMessage signallingMessage) {
        setMessages(new SignallingMessage[]{signallingMessage});
    }

    public void setMessages(SignallingMessage[] signallingMessageArr) {
        this._messages = signallingMessageArr;
    }

    public SignallingClientResponse() {
        setFailureSource(FailureSource.None);
    }
}
