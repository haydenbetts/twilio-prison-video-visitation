package fm.liveswitch;

class SignallingPublisherResponse {
    private Exception _exception;
    private SignallingMessage[] _messages;

    public Exception getException() {
        return this._exception;
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

    public void setMessage(SignallingMessage signallingMessage) {
        setMessages(new SignallingMessage[]{signallingMessage});
    }

    public void setMessages(SignallingMessage[] signallingMessageArr) {
        this._messages = signallingMessageArr;
    }
}
