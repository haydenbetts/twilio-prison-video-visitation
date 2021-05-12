package fm.liveswitch;

class SignallingUnsubscribeFailureArgs extends SignallingFailureArgs {
    private String[] __channels;
    private String[] __unbindKeys;

    public String getChannel() {
        return SignallingExtensible.sharedGetChannel(this.__channels);
    }

    public String[] getChannels() {
        return SignallingExtensible.sharedGetChannels(this.__channels);
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

    public SignallingUnsubscribeFailureArgs(String[] strArr, String[] strArr2) {
        this.__channels = strArr;
        this.__unbindKeys = strArr2;
    }
}
