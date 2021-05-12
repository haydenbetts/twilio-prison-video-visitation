package fm.liveswitch;

class SignallingDeferredStreamState {
    private boolean _receivedMessages;

    public boolean getReceivedMessages() {
        return this._receivedMessages;
    }

    public void setReceivedMessages(boolean z) {
        this._receivedMessages = z;
    }
}
