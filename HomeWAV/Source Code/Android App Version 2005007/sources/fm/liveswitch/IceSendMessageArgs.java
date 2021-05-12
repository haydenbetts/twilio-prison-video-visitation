package fm.liveswitch;

import fm.liveswitch.stun.Message;

class IceSendMessageArgs {
    private TransportAddress _address;
    private boolean _cancelled;
    private String _candidatePairId;
    private long _dispatchTimestamp;
    private Message _message;
    private IAction1<IceSendRequestFailureArgs> _onFailure;
    private IAction1<IceSendRequestSuccessArgs> _onResponse;
    private boolean _raiseServerReflexiveCandidates;
    private String _relayPassword;
    private TransportAddress _turnRelay;

    public void cancelTransaction() {
        setCancelled(true);
    }

    public TransportAddress getAddress() {
        return this._address;
    }

    public boolean getCancelled() {
        return this._cancelled;
    }

    public String getCandidatePairId() {
        return this._candidatePairId;
    }

    public long getDispatchTimestamp() {
        return this._dispatchTimestamp;
    }

    public Message getMessage() {
        return this._message;
    }

    public IAction1<IceSendRequestFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<IceSendRequestSuccessArgs> getOnResponse() {
        return this._onResponse;
    }

    /* access modifiers changed from: package-private */
    public boolean getRaiseServerReflexiveCandidates() {
        return this._raiseServerReflexiveCandidates;
    }

    /* access modifiers changed from: package-private */
    public String getRelayPassword() {
        return this._relayPassword;
    }

    public TransportAddress getTurnRelay() {
        return this._turnRelay;
    }

    public IceSendMessageArgs(Message message, TransportAddress transportAddress) {
        if (message == null) {
            throw new RuntimeException(new Exception("request cannot be null."));
        } else if (transportAddress != null) {
            setMessage(message);
            setAddress(transportAddress);
            setRaiseServerReflexiveCandidates(true);
        } else {
            throw new RuntimeException(new Exception("address cannot be null."));
        }
    }

    public void setAddress(TransportAddress transportAddress) {
        this._address = transportAddress;
    }

    /* access modifiers changed from: protected */
    public void setCancelled(boolean z) {
        this._cancelled = z;
    }

    public void setCandidatePairId(String str) {
        this._candidatePairId = str;
    }

    public void setDispatchTimestamp(long j) {
        this._dispatchTimestamp = j;
    }

    public void setMessage(Message message) {
        this._message = message;
    }

    public void setOnFailure(IAction1<IceSendRequestFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnResponse(IAction1<IceSendRequestSuccessArgs> iAction1) {
        this._onResponse = iAction1;
    }

    /* access modifiers changed from: package-private */
    public void setRaiseServerReflexiveCandidates(boolean z) {
        this._raiseServerReflexiveCandidates = z;
    }

    /* access modifiers changed from: package-private */
    public void setRelayPassword(String str) {
        this._relayPassword = str;
    }

    public void setTurnRelay(TransportAddress transportAddress) {
        this._turnRelay = transportAddress;
    }
}
