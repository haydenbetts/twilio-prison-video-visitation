package fm.liveswitch;

class SignallingConnectSuccessArgs extends SignallingSuccessArgs {
    private SignallingConnectionType _connectionType;

    public SignallingConnectionType getConnectionType() {
        return this._connectionType;
    }

    /* access modifiers changed from: package-private */
    public void setConnectionType(SignallingConnectionType signallingConnectionType) {
        this._connectionType = signallingConnectionType;
    }
}
