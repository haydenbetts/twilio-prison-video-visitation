package fm.liveswitch;

class SignallingSubscribePresenceArgs extends SignallingReceiveArgs {
    private String __channel;
    private boolean __firstDeviceSubscribe;
    private boolean __firstUserSubscribe;
    private boolean __lastDeviceUnsubscribe;
    private boolean __lastUserUnsubscribe;
    private SignallingPresenceType __presenceType;
    private SignallingRemoteClient __remoteClient;

    public String getChannel() {
        return this.__channel;
    }

    public boolean getFirstDeviceSubscribe() {
        return this.__firstDeviceSubscribe;
    }

    public boolean getFirstUserSubscribe() {
        return this.__firstUserSubscribe;
    }

    public boolean getLastDeviceUnsubscribe() {
        return this.__lastDeviceUnsubscribe;
    }

    public boolean getLastUserUnsubscribe() {
        return this.__lastUserUnsubscribe;
    }

    public SignallingPresenceType getPresenceType() {
        return this.__presenceType;
    }

    public SignallingRemoteClient getRemoteClient() {
        return this.__remoteClient;
    }

    public SignallingSubscribePresenceArgs(String str, SignallingRemoteClient signallingRemoteClient, SignallingPresenceType signallingPresenceType, boolean z, boolean z2, boolean z3, boolean z4, SignallingConnectionType signallingConnectionType, int i) {
        super((String) null, (byte[]) null, signallingConnectionType, i);
        this.__channel = str;
        this.__remoteClient = signallingRemoteClient;
        this.__presenceType = signallingPresenceType;
        this.__firstUserSubscribe = z;
        this.__lastUserUnsubscribe = z2;
        this.__firstDeviceSubscribe = z3;
        this.__lastDeviceUnsubscribe = z4;
    }
}
