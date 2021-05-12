package fm.liveswitch;

class SignallingUnbindSuccessArgs extends SignallingSuccessArgs {
    private boolean __forced;
    private String[] __keys;

    public boolean getForced() {
        return this.__forced;
    }

    public String getKey() {
        return SignallingExtensible.sharedGetKey(this.__keys);
    }

    public String[] getKeys() {
        return SignallingExtensible.sharedGetKeys(this.__keys);
    }

    public SignallingUnbindSuccessArgs(String[] strArr, boolean z) {
        this.__keys = strArr;
        this.__forced = z;
    }
}
