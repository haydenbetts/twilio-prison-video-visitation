package fm.liveswitch;

import fm.liveswitch.stun.Message;

class IceSendResponseFailureArgs extends Dynamic {
    private TransportAddress _address;
    private Exception _exception;
    private Message _response;
    private TransportAddress _turnRelay;

    public TransportAddress getAddress() {
        return this._address;
    }

    public Exception getException() {
        return this._exception;
    }

    public Message getResponse() {
        return this._response;
    }

    public TransportAddress getTurnRelay() {
        return this._turnRelay;
    }

    /* access modifiers changed from: package-private */
    public void setAddress(TransportAddress transportAddress) {
        this._address = transportAddress;
    }

    /* access modifiers changed from: package-private */
    public void setException(Exception exc) {
        this._exception = exc;
    }

    /* access modifiers changed from: package-private */
    public void setResponse(Message message) {
        this._response = message;
    }

    /* access modifiers changed from: package-private */
    public void setTurnRelay(TransportAddress transportAddress) {
        this._turnRelay = transportAddress;
    }
}
