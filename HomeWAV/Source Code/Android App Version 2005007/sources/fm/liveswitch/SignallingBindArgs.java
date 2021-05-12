package fm.liveswitch;

class SignallingBindArgs extends SignallingInputArgs {
    SignallingRecord[] __records;
    private boolean _isPrivate;
    private IAction1<SignallingBindFailureArgs> _onFailure;
    private IAction1<SignallingBindSuccessArgs> _onSuccess;

    public boolean getIsPrivate() {
        return this._isPrivate;
    }

    public IAction1<SignallingBindFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<SignallingBindSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public SignallingRecord getRecord() {
        return SignallingExtensible.sharedGetRecord(this.__records);
    }

    public SignallingRecord[] getRecords() {
        return SignallingExtensible.sharedGetRecords(this.__records);
    }

    public void setIsPrivate(boolean z) {
        this._isPrivate = z;
    }

    public void setOnFailure(IAction1<SignallingBindFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnSuccess(IAction1<SignallingBindSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }

    public void setRecord(SignallingRecord signallingRecord) {
        this.__records = SignallingExtensible.sharedSetRecord(signallingRecord);
    }

    public void setRecords(SignallingRecord[] signallingRecordArr) {
        this.__records = SignallingExtensible.sharedSetRecords(signallingRecordArr);
    }

    public SignallingBindArgs() {
    }

    public SignallingBindArgs(SignallingRecord signallingRecord) {
        setRecord(signallingRecord);
    }

    public SignallingBindArgs(SignallingRecord[] signallingRecordArr) {
        setRecords(signallingRecordArr);
    }
}
