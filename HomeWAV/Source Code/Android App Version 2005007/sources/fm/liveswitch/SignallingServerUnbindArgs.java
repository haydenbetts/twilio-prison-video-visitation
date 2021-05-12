package fm.liveswitch;

class SignallingServerUnbindArgs extends SignallingSuccessArgs {
    String[] __keys;

    public String getKey() {
        return SignallingExtensible.sharedGetKey(this.__keys);
    }

    public String[] getKeys() {
        return SignallingExtensible.sharedGetKeys(this.__keys);
    }
}
