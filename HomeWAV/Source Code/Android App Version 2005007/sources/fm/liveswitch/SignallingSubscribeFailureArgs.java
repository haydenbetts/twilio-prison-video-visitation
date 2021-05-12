package fm.liveswitch;

class SignallingSubscribeFailureArgs extends SignallingFailureArgs {
    private boolean __bindIsPrivate;
    private SignallingRecord[] __bindRecords;
    private String[] __channels;

    public boolean getBindIsPrivate() {
        return this.__bindIsPrivate;
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

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) != null ? JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) : StringExtensions.empty;
    }

    public SignallingSubscribeFailureArgs(String[] strArr, SignallingRecord[] signallingRecordArr, boolean z) {
        this.__channels = strArr;
        this.__bindRecords = signallingRecordArr;
        this.__bindIsPrivate = z;
    }
}
