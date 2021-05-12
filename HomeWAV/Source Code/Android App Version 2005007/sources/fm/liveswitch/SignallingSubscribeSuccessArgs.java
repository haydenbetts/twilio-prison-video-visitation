package fm.liveswitch;

import java.util.HashMap;

class SignallingSubscribeSuccessArgs extends SignallingSuccessArgs {
    private boolean __bindIsPrivate;
    private SignallingRecord[] __bindRecords;
    private String[] __channels;
    private HashMap<String, SignallingRemoteClient[]> __subscribedClients;

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

    public SignallingRemoteClient[] getSubscribedClients() {
        String channel;
        HashMap<String, SignallingRemoteClient[]> subscribedClientsByChannel = getSubscribedClientsByChannel();
        if (subscribedClientsByChannel == null || (channel = getChannel()) == null) {
            return null;
        }
        return HashMapExtensions.getItem(subscribedClientsByChannel).get(channel);
    }

    public HashMap<String, SignallingRemoteClient[]> getSubscribedClientsByChannel() {
        return this.__subscribedClients;
    }

    public String getTag() {
        return JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) != null ? JsonSerializer.deserializeString(super.getExtensionValueJson(SignallingExtensible.getTagExtensionName())) : StringExtensions.empty;
    }

    public SignallingSubscribeSuccessArgs(String[] strArr, SignallingRecord[] signallingRecordArr, boolean z, HashMap<String, SignallingRemoteClient[]> hashMap) {
        this.__channels = strArr;
        this.__bindRecords = signallingRecordArr;
        this.__bindIsPrivate = z;
        this.__subscribedClients = hashMap;
    }
}
