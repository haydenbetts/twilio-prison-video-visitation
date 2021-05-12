package fm.liveswitch;

class SendItem {
    private Exception _exception;
    private boolean _lastInBatch;
    private Message _message;
    private Promise<Message> _promise;
    private int _resendIndex;
    private boolean _retry;
    private int _sendBackoff;
    private int _sendCounter;

    public Exception getException() {
        return this._exception;
    }

    public boolean getLastInBatch() {
        return this._lastInBatch;
    }

    public Message getMessage() {
        return this._message;
    }

    public Promise<Message> getPromise() {
        return this._promise;
    }

    public int getResendIndex() {
        return this._resendIndex;
    }

    public boolean getRetry() {
        return this._retry;
    }

    public int getSendBackoff() {
        return this._sendBackoff;
    }

    public int getSendCounter() {
        return this._sendCounter;
    }

    public void setException(Exception exc) {
        this._exception = exc;
    }

    public void setLastInBatch(boolean z) {
        this._lastInBatch = z;
    }

    public void setMessage(Message message) {
        this._message = message;
    }

    public void setPromise(Promise<Message> promise) {
        this._promise = promise;
    }

    public void setResendIndex(int i) {
        this._resendIndex = i;
    }

    public void setRetry(boolean z) {
        this._retry = z;
    }

    public void setSendBackoff(int i) {
        this._sendBackoff = i;
    }

    public void setSendCounter(int i) {
        this._sendCounter = i;
    }
}
