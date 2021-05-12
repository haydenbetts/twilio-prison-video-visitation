package fm.liveswitch;

class SignallingServerSubscribeArgs extends SignallingSuccessArgs {
    String[] __channels;
    private IAction1<SignallingSubscribePresenceArgs> _onPresence;
    private IAction1<SignallingSubscribeReceiveArgs> _onReceive;

    public String getChannel() {
        return SignallingExtensible.sharedGetChannel(this.__channels);
    }

    public String[] getChannels() {
        return SignallingExtensible.sharedGetChannels(this.__channels);
    }

    public IAction1<SignallingSubscribePresenceArgs> getOnPresence() {
        return this._onPresence;
    }

    public IAction1<SignallingSubscribeReceiveArgs> getOnReceive() {
        return this._onReceive;
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) != null ? JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) : StringExtensions.empty;
    }

    public void setOnPresence(IAction1<SignallingSubscribePresenceArgs> iAction1) {
        this._onPresence = iAction1;
    }

    public void setOnReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        this._onReceive = iAction1;
    }
}
