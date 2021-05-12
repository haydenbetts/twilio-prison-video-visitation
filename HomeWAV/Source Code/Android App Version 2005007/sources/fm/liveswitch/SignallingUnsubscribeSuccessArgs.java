package fm.liveswitch;

class SignallingUnsubscribeSuccessArgs extends SignallingSuccessArgs {
    private String[] __channels;
    private boolean __forced;
    private String[] __unbindKeys;

    public String getChannel() {
        return SignallingExtensible.sharedGetChannel(this.__channels);
    }

    public String[] getChannels() {
        return SignallingExtensible.sharedGetChannels(this.__channels);
    }

    public boolean getForced() {
        return this.__forced;
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

    public SignallingUnsubscribeSuccessArgs(String[] strArr, String[] strArr2, boolean z) {
        this.__channels = strArr;
        this.__unbindKeys = strArr2;
        this.__forced = z;
    }
}
