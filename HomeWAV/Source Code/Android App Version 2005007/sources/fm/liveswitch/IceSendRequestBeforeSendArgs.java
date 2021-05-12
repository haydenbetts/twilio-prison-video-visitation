package fm.liveswitch;

import fm.liveswitch.stun.Message;

class IceSendRequestBeforeSendArgs extends Dynamic {
    private TransportAddress _address;
    private int _attempt;
    private boolean _cancel;
    private IceSocketManager _messageBroker;
    private Message _request;
    private TransportAddress _turnRelay;

    public TransportAddress getAddress() {
        return this._address;
    }

    public int getAttempt() {
        return this._attempt;
    }

    public boolean getCancel() {
        return this._cancel;
    }

    public IceSocketManager getMessageBroker() {
        return this._messageBroker;
    }

    public Message getRequest() {
        return this._request;
    }

    public TransportAddress getTurnRelay() {
        return this._turnRelay;
    }

    /* access modifiers changed from: package-private */
    public void setAddress(TransportAddress transportAddress) {
        this._address = transportAddress;
    }

    /* access modifiers changed from: package-private */
    public void setAttempt(int i) {
        this._attempt = i;
    }

    public void setCancel(boolean z) {
        this._cancel = z;
    }

    /* access modifiers changed from: package-private */
    public void setMessageBroker(IceSocketManager iceSocketManager) {
        this._messageBroker = iceSocketManager;
    }

    /* access modifiers changed from: package-private */
    public void setRequest(Message message) {
        this._request = message;
    }

    /* access modifiers changed from: package-private */
    public void setTurnRelay(TransportAddress transportAddress) {
        this._turnRelay = transportAddress;
    }
}
