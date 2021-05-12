package fm.liveswitch;

class SignallingBindSuccessArgs extends SignallingSuccessArgs {
    private boolean __isPrivate;
    private SignallingRecord[] __records;

    public boolean getIsPrivate() {
        return this.__isPrivate;
    }

    public SignallingRecord getRecord() {
        return SignallingExtensible.sharedGetRecord(this.__records);
    }

    public SignallingRecord[] getRecords() {
        return SignallingExtensible.sharedGetRecords(this.__records);
    }

    public SignallingBindSuccessArgs(SignallingRecord[] signallingRecordArr, boolean z) {
        this.__records = signallingRecordArr;
        this.__isPrivate = z;
    }
}
