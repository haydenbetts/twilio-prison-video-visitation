package fm.liveswitch;

class RtcpReportPacketHeader {
    private int _length;
    private boolean _padding;
    private int _payloadType;
    private int _reportBlockCount;
    private long _synchronizationSource;
    private int _version;

    public static int getFixedHeaderLength() {
        return 8;
    }

    public RtcpReportPacketHeader clone() {
        RtcpReportPacketHeader rtcpReportPacketHeader = new RtcpReportPacketHeader();
        rtcpReportPacketHeader.setVersion(getVersion());
        rtcpReportPacketHeader.setPadding(getPadding());
        rtcpReportPacketHeader.setReportBlockCount(getReportBlockCount());
        rtcpReportPacketHeader.setPayloadType(getPayloadType());
        rtcpReportPacketHeader.setLength(getLength());
        rtcpReportPacketHeader.setSynchronizationSource(getSynchronizationSource());
        return rtcpReportPacketHeader;
    }

    public int getLength() {
        return this._length;
    }

    public boolean getPadding() {
        return this._padding;
    }

    public int getPayloadType() {
        return this._payloadType;
    }

    public int getReportBlockCount() {
        return this._reportBlockCount;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public int getVersion() {
        return this._version;
    }

    public static RtcpReportPacketHeader readFrom(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() < getFixedHeaderLength()) {
            return null;
        }
        RtcpReportPacketHeader rtcpReportPacketHeader = new RtcpReportPacketHeader();
        rtcpReportPacketHeader.setVersion(dataBuffer.read2(0, 0));
        rtcpReportPacketHeader.setPadding(dataBuffer.read1(0, 2));
        rtcpReportPacketHeader.setReportBlockCount(dataBuffer.read5(0, 3));
        rtcpReportPacketHeader.setPayloadType(dataBuffer.read8(1));
        rtcpReportPacketHeader.setLength(dataBuffer.read16(2));
        rtcpReportPacketHeader.setSynchronizationSource(dataBuffer.read32(4));
        return rtcpReportPacketHeader;
    }

    public RtcpReportPacketHeader() {
        setVersion(2);
        setReportBlockCount(-1);
        setPayloadType(-1);
        setLength(-1);
        setSynchronizationSource(-1);
    }

    public void setLength(int i) {
        this._length = i;
    }

    public void setPadding(boolean z) {
        this._padding = z;
    }

    public void setPayloadType(int i) {
        this._payloadType = i;
    }

    public void setReportBlockCount(int i) {
        this._reportBlockCount = i;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    public void setVersion(int i) {
        this._version = i;
    }

    public void writeTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write2(getVersion(), i, 0);
        dataBuffer.write1(getPadding(), i, 2);
        dataBuffer.write5(getReportBlockCount(), i, 3);
        dataBuffer.write8(getPayloadType(), i + 1);
        dataBuffer.write16(getLength(), i + 2);
        dataBuffer.write32(getSynchronizationSource(), i + 4);
    }
}
