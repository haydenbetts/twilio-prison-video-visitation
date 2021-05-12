package fm.liveswitch;

class SignallingBindFailureArgs extends SignallingFailureArgs {
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

    public SignallingBindFailureArgs(SignallingRecord[] signallingRecordArr, boolean z) {
        this.__records = signallingRecordArr;
        this.__isPrivate = z;
    }
}
