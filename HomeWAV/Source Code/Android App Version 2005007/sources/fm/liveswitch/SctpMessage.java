package fm.liveswitch;

class SctpMessage {
    private IAction1<Exception> _onFailure;
    private IAction0 _onSuccess;
    private DataBuffer _payload;
    private long _payloadType;
    private int _streamId;
    private boolean _unordered;

    private IAction1<Exception> getOnFailure() {
        return this._onFailure;
    }

    private IAction0 getOnSuccess() {
        return this._onSuccess;
    }

    public DataBuffer getPayload() {
        return this._payload;
    }

    public long getPayloadType() {
        return this._payloadType;
    }

    public int getStreamId() {
        return this._streamId;
    }

    public boolean getUnordered() {
        return this._unordered;
    }

    /* access modifiers changed from: package-private */
    public void raiseFailure(Exception exc) {
        IAction1<Exception> onFailure = getOnFailure();
        if (onFailure != null) {
            onFailure.invoke(exc);
        }
    }

    /* access modifiers changed from: package-private */
    public void raiseSuccess() {
        IAction0 onSuccess = getOnSuccess();
        if (onSuccess != null) {
            onSuccess.invoke();
        }
    }

    public SctpMessage(DataBuffer dataBuffer, int i) {
        setPayload(dataBuffer);
        setStreamId(i);
    }

    public void setOnFailure(IAction1<Exception> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnSuccess(IAction0 iAction0) {
        this._onSuccess = iAction0;
    }

    public void setPayload(DataBuffer dataBuffer) {
        this._payload = dataBuffer;
    }

    public void setPayloadType(long j) {
        this._payloadType = j;
    }

    /* access modifiers changed from: package-private */
    public void setStreamId(int i) {
        this._streamId = i;
    }

    public void setUnordered(boolean z) {
        this._unordered = z;
    }
}
