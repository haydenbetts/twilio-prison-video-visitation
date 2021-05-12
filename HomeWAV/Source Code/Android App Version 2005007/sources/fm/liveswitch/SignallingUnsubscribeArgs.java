package fm.liveswitch;

class SignallingUnsubscribeArgs extends SignallingInputArgs {
    String[] __channels;
    String[] __unbindKeys;
    private IAction1<SignallingUnsubscribeFailureArgs> _onFailure;
    private IAction1<SignallingUnsubscribeSuccessArgs> _onSuccess;

    public String getChannel() {
        return SignallingExtensible.sharedGetChannel(this.__channels);
    }

    public String[] getChannels() {
        return SignallingExtensible.sharedGetChannels(this.__channels);
    }

    public IAction1<SignallingUnsubscribeFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<SignallingUnsubscribeSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) != null ? JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) : StringExtensions.empty;
    }

    public String getUnbindKey() {
        return SignallingExtensible.sharedGetKey(this.__unbindKeys);
    }

    public String[] getUnbindKeys() {
        return SignallingExtensible.sharedGetKeys(this.__unbindKeys);
    }

    public void setChannel(String str) {
        this.__channels = SignallingExtensible.sharedSetChannel(str);
    }

    public void setChannels(String[] strArr) {
        this.__channels = SignallingExtensible.sharedSetChannels(strArr);
    }

    public void setOnFailure(IAction1<SignallingUnsubscribeFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnSuccess(IAction1<SignallingUnsubscribeSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }

    public void setTag(String str) {
        String tagExtensionName = SignallingExtensible.getTagExtensionName();
        if (str == null) {
            str = StringExtensions.empty;
        }
        super.setExtensionValueJson(tagExtensionName, JsonSerializer.serializeString(str), false);
    }

    public void setUnbindKey(String str) {
        this.__unbindKeys = SignallingExtensible.sharedSetKey(str);
    }

    public void setUnbindKeys(String[] strArr) {
        this.__unbindKeys = SignallingExtensible.sharedSetKeys(strArr);
    }

    public SignallingUnsubscribeArgs() {
    }

    public SignallingUnsubscribeArgs(String str) {
        setChannel(str);
    }

    public SignallingUnsubscribeArgs(String str, String str2) {
        setChannel(str);
        setTag(str2);
    }

    public SignallingUnsubscribeArgs(String[] strArr) {
        setChannels(strArr);
    }

    public SignallingUnsubscribeArgs(String[] strArr, String str) {
        setChannels(strArr);
        setTag(str);
    }
}
