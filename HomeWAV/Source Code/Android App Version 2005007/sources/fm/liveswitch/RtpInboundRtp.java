package fm.liveswitch;

class RtpInboundRtp {
    private DataBuffer __buffer;
    private int _footprint;
    private RtpPacketHeader _header;
    private long _networkSystemTimestamp;
    private long _transportSystemTimestamp;

    public DataBuffer getBuffer() {
        return this.__buffer;
    }

    public int getFootprint() {
        return this._footprint;
    }

    public RtpPacketHeader getHeader() {
        return this._header;
    }

    public long getNetworkSystemTimestamp() {
        return this._networkSystemTimestamp;
    }

    public long getTransportSystemTimestamp() {
        return this._transportSystemTimestamp;
    }

    public void setBuffer(DataBuffer dataBuffer) {
        setFootprint(dataBuffer == null ? 0 : dataBuffer.getLength());
        this.__buffer = dataBuffer;
    }

    private void setFootprint(int i) {
        this._footprint = i;
    }

    public void setHeader(RtpPacketHeader rtpPacketHeader) {
        this._header = rtpPacketHeader;
    }

    public void setNetworkSystemTimestamp(long j) {
        this._networkSystemTimestamp = j;
    }

    public void setTransportSystemTimestamp(long j) {
        this._transportSystemTimestamp = j;
    }
}
