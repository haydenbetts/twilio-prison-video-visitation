package fm.liveswitch;

class SignallingSubscribeArgs extends SignallingInputArgs {
    SignallingRecord[] __bindRecords;
    String[] __channels;
    private IAction1<SignallingSubscribePresenceArgs> __onPresence;
    private boolean _bindIsPrivate;
    private IAction1<SignallingSubscribeFailureArgs> _onFailure;
    private IAction1<SignallingSubscribeReceiveArgs> _onReceive;
    private IAction1<SignallingSubscribeSuccessArgs> _onSuccess;

    public boolean getBindIsPrivate() {
        return this._bindIsPrivate;
    }

    public SignallingRecord getBindRecord() {
        return SignallingExtensible.sharedGetRecord(this.__bindRecords);
    }

    public SignallingRecord[] getBindRecords() {
        return SignallingExtensible.sharedGetRecords(this.__bindRecords);
    }

    public String getChannel() {
        return SignallingExtensible.sharedGetChannel(this.__channels);
    }

    public String[] getChannels() {
        return SignallingExtensible.sharedGetChannels(this.__channels);
    }

    public IAction1<SignallingSubscribeFailureArgs> getOnFailure() {
        return this._onFailure;
    }

    public IAction1<SignallingSubscribePresenceArgs> getOnPresence() {
        return this.__onPresence;
    }

    public IAction1<SignallingSubscribeReceiveArgs> getOnReceive() {
        return this._onReceive;
    }

    public IAction1<SignallingSubscribeSuccessArgs> getOnSuccess() {
        return this._onSuccess;
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) != null ? JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) : StringExtensions.empty;
    }

    public void setBindIsPrivate(boolean z) {
        this._bindIsPrivate = z;
    }

    public void setBindRecord(SignallingRecord signallingRecord) {
        this.__bindRecords = SignallingExtensible.sharedSetRecord(signallingRecord);
    }

    public void setBindRecords(SignallingRecord[] signallingRecordArr) {
        this.__bindRecords = SignallingExtensible.sharedSetRecords(signallingRecordArr);
    }

    public void setChannel(String str) {
        this.__channels = SignallingExtensible.sharedSetChannel(str);
    }

    public void setChannels(String[] strArr) {
        this.__channels = SignallingExtensible.sharedSetChannels(strArr);
    }

    public void setOnFailure(IAction1<SignallingSubscribeFailureArgs> iAction1) {
        this._onFailure = iAction1;
    }

    public void setOnPresence(IAction1<SignallingSubscribePresenceArgs> iAction1) {
        if (iAction1 == null) {
            super.setExtensionValueJson(SignallingExtensible.getPresenceExtensionName(), (String) null);
        } else {
            super.setExtensionValueJson(SignallingExtensible.getPresenceExtensionName(), JsonSerializer.serializeBoolean(new NullableBoolean(true)));
        }
        this.__onPresence = iAction1;
    }

    public void setOnReceive(IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        this._onReceive = iAction1;
    }

    public void setOnSuccess(IAction1<SignallingSubscribeSuccessArgs> iAction1) {
        this._onSuccess = iAction1;
    }

    public void setTag(String str) {
        String tagExtensionName = SignallingExtensible.getTagExtensionName();
        if (str == null) {
            str = StringExtensions.empty;
        }
        super.setExtensionValueJson(tagExtensionName, JsonSerializer.serializeString(str), false);
    }

    public SignallingSubscribeArgs() {
    }

    public SignallingSubscribeArgs(String str) {
        setChannel(str);
    }

    public SignallingSubscribeArgs(String str, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        setChannel(str);
        setOnReceive(iAction1);
    }

    public SignallingSubscribeArgs(String str, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        setChannel(str);
        setOnReceive(iAction1);
        setOnPresence(iAction12);
    }

    public SignallingSubscribeArgs(String str, String str2) {
        setChannel(str);
        setTag(str2);
    }

    public SignallingSubscribeArgs(String str, String str2, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        setChannel(str);
        setTag(str2);
        setOnReceive(iAction1);
    }

    public SignallingSubscribeArgs(String str, String str2, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        setChannel(str);
        setTag(str2);
        setOnReceive(iAction1);
        setOnPresence(iAction12);
    }

    public SignallingSubscribeArgs(String[] strArr) {
        setChannels(strArr);
    }

    public SignallingSubscribeArgs(String[] strArr, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        setChannels(strArr);
        setOnReceive(iAction1);
    }

    public SignallingSubscribeArgs(String[] strArr, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        setChannels(strArr);
        setOnReceive(iAction1);
        setOnPresence(iAction12);
    }

    public SignallingSubscribeArgs(String[] strArr, String str) {
        setChannels(strArr);
        setTag(str);
    }

    public SignallingSubscribeArgs(String[] strArr, String str, IAction1<SignallingSubscribeReceiveArgs> iAction1) {
        setChannels(strArr);
        setTag(str);
        setOnReceive(iAction1);
    }

    public SignallingSubscribeArgs(String[] strArr, String str, IAction1<SignallingSubscribeReceiveArgs> iAction1, IAction1<SignallingSubscribePresenceArgs> iAction12) {
        setChannels(strArr);
        setTag(str);
        setOnReceive(iAction1);
        setOnPresence(iAction12);
    }
}
