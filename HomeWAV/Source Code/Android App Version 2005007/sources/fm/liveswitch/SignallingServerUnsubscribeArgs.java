package fm.liveswitch;

class SignallingServerUnsubscribeArgs extends SignallingSuccessArgs {
    String[] __channels;

    public String getChannel() {
        return SignallingExtensible.sharedGetChannel(this.__channels);
    }

    public String[] getChannels() {
        return SignallingExtensible.sharedGetChannels(this.__channels);
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) != null ? JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) : StringExtensions.empty;
    }
}
