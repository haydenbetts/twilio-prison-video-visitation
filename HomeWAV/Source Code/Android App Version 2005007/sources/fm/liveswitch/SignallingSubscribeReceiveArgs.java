package fm.liveswitch;

class SignallingSubscribeReceiveArgs extends SignallingReceiveArgs {
    String __channel;

    public String getChannel() {
        return this.__channel;
    }

    public SignallingRemoteClient getRemoteClient() {
        return SignallingRemoteClient.fromJson(super.getExtensionValueJson(SignallingExtensible.getRemoteClientExtensionName()));
    }

    public boolean getWasSentByMe() {
        return (getRemoteClient() == null || super.getClient() == null || !Global.equals(getRemoteClient().getClientId(), super.getClient().getClientId())) ? false : true;
    }

    public SignallingSubscribeReceiveArgs(String str, String str2, byte[] bArr, SignallingConnectionType signallingConnectionType, int i) {
        super(str2, bArr, signallingConnectionType, i);
        this.__channel = str;
    }
}
