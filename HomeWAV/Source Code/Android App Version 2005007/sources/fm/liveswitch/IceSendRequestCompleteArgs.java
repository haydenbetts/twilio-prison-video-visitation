package fm.liveswitch;

import fm.liveswitch.stun.Message;

class IceSendRequestCompleteArgs extends Dynamic {
    private TransportAddress _address;
    private int _attempt;
    private int _maxAttempts;
    private Message _request;
    private IceSocketManager _socketManager;
    private TransportAddress _turnRelay;

    public TransportAddress getAddress() {
        return this._address;
    }

    public int getAttempt() {
        return this._attempt;
    }

    public int getMaxAttempts() {
        return this._maxAttempts;
    }

    public Message getRequest() {
        return this._request;
    }

    public IceSocketManager getSocketManager() {
        return this._socketManager;
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

    /* access modifiers changed from: package-private */
    public void setMaxAttempts(int i) {
        this._maxAttempts = i;
    }

    /* access modifiers changed from: package-private */
    public void setRequest(Message message) {
        this._request = message;
    }

    /* access modifiers changed from: package-private */
    public void setSocketManager(IceSocketManager iceSocketManager) {
        this._socketManager = iceSocketManager;
    }

    /* access modifiers changed from: package-private */
    public void setTurnRelay(TransportAddress transportAddress) {
        this._turnRelay = transportAddress;
    }
}
