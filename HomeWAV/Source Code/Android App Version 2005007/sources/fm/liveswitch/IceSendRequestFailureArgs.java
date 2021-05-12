package fm.liveswitch;

class IceSendRequestFailureArgs {
    private TransportAddress _address;
    private String _candidatePairId;
    private Error _error;
    private TransportAddress _turnRelay;

    public TransportAddress getAddress() {
        return this._address;
    }

    public String getCandidatePairId() {
        return this._candidatePairId;
    }

    public Error getError() {
        return this._error;
    }

    public TransportAddress getTurnRelay() {
        return this._turnRelay;
    }

    /* access modifiers changed from: package-private */
    public void setAddress(TransportAddress transportAddress) {
        this._address = transportAddress;
    }

    /* access modifiers changed from: package-private */
    public void setCandidatePairId(String str) {
        this._candidatePairId = str;
    }

    /* access modifiers changed from: package-private */
    public void setError(Error error) {
        this._error = error;
    }

    /* access modifiers changed from: package-private */
    public void setTurnRelay(TransportAddress transportAddress) {
        this._turnRelay = transportAddress;
    }
}
