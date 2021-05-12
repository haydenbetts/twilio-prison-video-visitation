package fm.liveswitch;

class SignallingUnbindFailureArgs extends SignallingFailureArgs {
    private String[] __keys;

    public String getKey() {
        return SignallingExtensible.sharedGetKey(this.__keys);
    }

    public String[] getKeys() {
        return SignallingExtensible.sharedGetKeys(this.__keys);
    }

    public SignallingUnbindFailureArgs(String[] strArr) {
        this.__keys = strArr;
    }
}
