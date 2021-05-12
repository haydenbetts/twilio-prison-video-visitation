package fm.liveswitch;

class SignallingUnbindArgs extends SignallingInputArgs {
    String[] __keys;
    private IAction1<SignallingUnbindFailureArgs> _onFailure;
    private IAction1<SignallingUnbindSuccessArgs> _onSuccess;

    public String getKey() {
        return SignallingExtensible.sharedGetKey(this.__keys);
    }

    public String[] getKeys() {
        return SignallingExtensible.sharedGetKeys(this.__keys);
    }

    public IAction1<SignallingUnbindFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<SignallingUnbindSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public void setKey(String str) {
        this.__keys = SignallingExtensible.sharedSetKey(str);
    }

    public void setKeys(String[] strArr) {
        this.__keys = SignallingExtensible.sharedSetKeys(strArr);
    }

    public void setOnFailure(IAction1<SignallingUnbindFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnSuccess(IAction1<SignallingUnbindSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }

    public SignallingUnbindArgs() {
    }

    public SignallingUnbindArgs(String str) {
        setKey(str);
    }

    public SignallingUnbindArgs(String[] strArr) {
        setKeys(strArr);
    }
}
