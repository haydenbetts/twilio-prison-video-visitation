package fm.liveswitch;

class RtpPacketPair {
    private RtpPacketHeader _header;
    private DataBuffer _payload;

    public int getCombinedSize() {
        return getHeader().calculateHeaderLength() + (getPayload() == null ? 0 : getPayload().getLength());
    }

    public RtpPacketHeader getHeader() {
        return this._header;
    }

    public DataBuffer getPayload() {
        return this._payload;
    }

    public RtpPacketPair(RtpPacketHeader rtpPacketHeader, DataBuffer dataBuffer) {
        setHeader(rtpPacketHeader);
        setPayload(dataBuffer);
    }

    private void setHeader(RtpPacketHeader rtpPacketHeader) {
        this._header = rtpPacketHeader;
    }

    private void setPayload(DataBuffer dataBuffer) {
        this._payload = dataBuffer;
    }
}
