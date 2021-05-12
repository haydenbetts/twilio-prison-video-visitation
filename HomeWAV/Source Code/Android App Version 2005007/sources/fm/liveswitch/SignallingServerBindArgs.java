package fm.liveswitch;

class SignallingServerBindArgs extends SignallingServerArgs {
    SignallingRecord[] __records;

    public SignallingRecord getRecord() {
        return SignallingExtensible.sharedGetRecord(this.__records);
    }

    public SignallingRecord[] getRecords() {
        return SignallingExtensible.sharedGetRecords(this.__records);
    }
}
