package fm.liveswitch;

class VirtualPacket {
    private int _delay;
    private String _destinationHostname;
    private String _destinationIPAddress;
    private int _destinationPort;
    private long _minReceiveTimestamp;
    private DataBuffer _payload;
    private ProtocolType _protocolType;
    private long _sendTimestamp;
    private String _sourceIPAddress;
    private int _sourcePort;
    private VirtualPacketType _type;

    public int getDelay() {
        return this._delay;
    }

    public String getDestinationHostname() {
        return this._destinationHostname;
    }

    public String getDestinationIPAddress() {
        return this._destinationIPAddress;
    }

    public int getDestinationPort() {
        return this._destinationPort;
    }

    public long getMinReceiveTimestamp() {
        return this._minReceiveTimestamp;
    }

    public DataBuffer getPayload() {
        return this._payload;
    }

    public ProtocolType getProtocolType() {
        return this._protocolType;
    }

    public boolean getReadyForReceive() {
        return getDelay() == 0 || getMinReceiveTimestamp() <= ManagedStopwatch.getTimestamp();
    }

    public long getSendTimestamp() {
        return this._sendTimestamp;
    }

    public String getSourceIPAddress() {
        return this._sourceIPAddress;
    }

    public int getSourcePort() {
        return this._sourcePort;
    }

    public VirtualPacketType getType() {
        return this._type;
    }

    private void setDelay(int i) {
        this._delay = i;
    }

    public void setDestinationHostname(String str) {
        this._destinationHostname = str;
    }

    public void setDestinationIPAddress(String str) {
        this._destinationIPAddress = str;
    }

    public void setDestinationPort(int i) {
        this._destinationPort = i;
    }

    public void setMinReceiveTimestamp(long j) {
        this._minReceiveTimestamp = j;
    }

    public void setPayload(DataBuffer dataBuffer) {
        this._payload = dataBuffer;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this._protocolType = protocolType;
    }

    public void setSendTimestamp(long j) {
        this._sendTimestamp = j;
    }

    public void setSourceIPAddress(String str) {
        this._sourceIPAddress = str;
    }

    public void setSourcePort(int i) {
        this._sourcePort = i;
    }

    public void setType(VirtualPacketType virtualPacketType) {
        this._type = virtualPacketType;
    }

    public String toString() {
        return StringExtensions.format("{0}:{1} -> {2}:{3} ({4} {5})", new Object[]{getSourceIPAddress(), IntegerExtensions.toString(Integer.valueOf(getSourcePort())), getDestinationIPAddress(), IntegerExtensions.toString(Integer.valueOf(getDestinationPort())), StringExtensions.toUpper(getProtocolType().toString()), StringExtensions.toLower(getType().toString())});
    }

    public VirtualPacket() {
        this(0);
    }

    public VirtualPacket(int i) {
        setType(VirtualPacketType.Data);
        setDelay(i);
    }
}
