package fm.liveswitch;

class VirtualPacketDeliveredArgs {
    private int _port;
    private ProtocolType _protocolType;

    public int getPort() {
        return this._port;
    }

    public ProtocolType getProtocolType() {
        return this._protocolType;
    }

    public void setPort(int i) {
        this._port = i;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this._protocolType = protocolType;
    }
}
