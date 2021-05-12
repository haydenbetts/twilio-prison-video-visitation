package fm.liveswitch;

public class RtpPacketHeader {
    private IRtpHeaderExtension __headerExtension;
    private int _contributingSourceCount;
    private long[] _contributingSources;
    private boolean _extension;
    private boolean _marker;
    private boolean _padding;
    private int _paddingLength;
    private int _payloadType;
    private int _sequenceNumber;
    private long _synchronizationSource;
    private long _timestamp;
    private int _version;

    public static int getFixedHeaderLength() {
        return 12;
    }

    public int calculateHeaderLength() {
        int fixedHeaderLength = getFixedHeaderLength() + (getContributingSourceCount() * 4);
        return getExtension() ? fixedHeaderLength + (getHeaderExtension().getLength() * 4) + 4 : fixedHeaderLength;
    }

    public RtpPacketHeader clone() {
        RtpPacketHeader rtpPacketHeader = new RtpPacketHeader();
        rtpPacketHeader.setVersion(getVersion());
        rtpPacketHeader.setMarker(getMarker());
        rtpPacketHeader.setPadding(getPadding());
        rtpPacketHeader.setExtension(getExtension());
        rtpPacketHeader.setHeaderExtension(getHeaderExtension());
        rtpPacketHeader.setContributingSourceCount(getContributingSourceCount());
        rtpPacketHeader.setPayloadType(getPayloadType());
        rtpPacketHeader.setSequenceNumber(getSequenceNumber());
        rtpPacketHeader.setTimestamp(getTimestamp());
        rtpPacketHeader.setSynchronizationSource(getSynchronizationSource());
        rtpPacketHeader.setContributingSources(getContributingSources());
        return rtpPacketHeader;
    }

    public int getContributingSourceCount() {
        return this._contributingSourceCount;
    }

    public long[] getContributingSources() {
        return this._contributingSources;
    }

    public boolean getExtension() {
        return this._extension;
    }

    public IRtpHeaderExtension getHeaderExtension() {
        return this.__headerExtension;
    }

    public boolean getMarker() {
        return this._marker;
    }

    public boolean getPadding() {
        return this._padding;
    }

    public int getPaddingLength() {
        return this._paddingLength;
    }

    public int getPayloadType() {
        return this._payloadType;
    }

    public int getSequenceNumber() {
        return this._sequenceNumber;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public long getTimestamp() {
        return this._timestamp;
    }

    public int getVersion() {
        return this._version;
    }

    public static RtpPacketHeader readFrom(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() < getFixedHeaderLength()) {
            return null;
        }
        RtpPacketHeader rtpPacketHeader = new RtpPacketHeader();
        rtpPacketHeader.setVersion(dataBuffer.read2(0, 0));
        rtpPacketHeader.setPadding(dataBuffer.read1(0, 2));
        rtpPacketHeader.setExtension(dataBuffer.read1(0, 3));
        rtpPacketHeader.setContributingSourceCount(dataBuffer.read4(0, 4));
        int fixedHeaderLength = getFixedHeaderLength() + (rtpPacketHeader.getContributingSourceCount() * 4);
        if (dataBuffer.getLength() < fixedHeaderLength) {
            return null;
        }
        rtpPacketHeader.setMarker(dataBuffer.read1(1, 0));
        rtpPacketHeader.setPayloadType(dataBuffer.read7(1, 1));
        rtpPacketHeader.setSequenceNumber(dataBuffer.read16(2));
        rtpPacketHeader.setTimestamp(dataBuffer.read32(4));
        rtpPacketHeader.setSynchronizationSource(dataBuffer.read32(8));
        if (rtpPacketHeader.getContributingSourceCount() > 0) {
            rtpPacketHeader.setContributingSources(new long[rtpPacketHeader.getContributingSourceCount()]);
            for (int i = 0; i < rtpPacketHeader.getContributingSourceCount(); i++) {
                rtpPacketHeader.getContributingSources()[i] = dataBuffer.read32((i * 4) + 12);
            }
        }
        if (rtpPacketHeader.getPadding()) {
            rtpPacketHeader.setPaddingLength(dataBuffer.read8(dataBuffer.getLength() - 1));
        }
        if (rtpPacketHeader.getExtension()) {
            int i2 = fixedHeaderLength + 4;
            if (dataBuffer.getLength() < i2) {
                return null;
            }
            int read16 = dataBuffer.read16(fixedHeaderLength + 2) * 4;
            if (dataBuffer.getLength() < i2 + read16) {
                return null;
            }
            rtpPacketHeader.setHeaderExtension(new RtpRawHeaderExtension(new byte[]{dataBuffer.getData()[dataBuffer.getIndex() + fixedHeaderLength], dataBuffer.getData()[dataBuffer.getIndex() + fixedHeaderLength + 1]}, dataBuffer.subset(i2, read16).toArray()));
        }
        return rtpPacketHeader;
    }

    public RtpPacketHeader() {
        setVersion(2);
        setPayloadType(-1);
        setSequenceNumber(-1);
        setTimestamp(-1);
        setSynchronizationSource(-1);
    }

    public void setContributingSourceCount(int i) {
        this._contributingSourceCount = i;
    }

    public void setContributingSources(long[] jArr) {
        this._contributingSources = jArr;
    }

    private void setExtension(boolean z) {
        this._extension = z;
    }

    public void setHeaderExtension(IRtpHeaderExtension iRtpHeaderExtension) {
        if (iRtpHeaderExtension == null) {
            setExtension(false);
            this.__headerExtension = iRtpHeaderExtension;
            return;
        }
        this.__headerExtension = iRtpHeaderExtension;
        setExtension(true);
    }

    public void setMarker(boolean z) {
        this._marker = z;
    }

    public void setPadding(boolean z) {
        this._padding = z;
    }

    private void setPaddingLength(int i) {
        this._paddingLength = i;
    }

    public void setPayloadType(int i) {
        this._payloadType = i;
    }

    public void setSequenceNumber(int i) {
        this._sequenceNumber = i;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    public void setTimestamp(long j) {
        this._timestamp = j;
    }

    public void setVersion(int i) {
        this._version = i;
    }

    public void writeTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write2(getVersion(), i, 0);
        dataBuffer.write1(getPadding(), i, 2);
        dataBuffer.write1(getExtension(), i, 3);
        dataBuffer.write4(getContributingSourceCount(), i, 4);
        int i2 = i + 1;
        dataBuffer.write1(getMarker(), i2, 0);
        dataBuffer.write7(getPayloadType(), i2, 1);
        dataBuffer.write16(getSequenceNumber(), i + 2);
        dataBuffer.write32(getTimestamp(), i + 4);
        dataBuffer.write32(getSynchronizationSource(), i + 8);
        if (getContributingSourceCount() > 0) {
            if (getContributingSources() == null || getContributingSourceCount() != ArrayExtensions.getLength(getContributingSources())) {
                throw new RuntimeException(new Exception("Contributing sources count has a non-zero value but the contributing sources array is null or contains an invalid count."));
            }
            for (int i3 = 0; i3 < getContributingSourceCount(); i3++) {
                dataBuffer.write32(getContributingSources()[i3], i + 12 + (i3 * 4));
            }
        }
        if (getExtension()) {
            int fixedHeaderLength = i + getFixedHeaderLength() + (getContributingSourceCount() * 4);
            dataBuffer.getData()[dataBuffer.getIndex() + fixedHeaderLength] = getHeaderExtension().getId()[0];
            dataBuffer.getData()[dataBuffer.getIndex() + fixedHeaderLength + 1] = getHeaderExtension().getId()[1];
            dataBuffer.write16(getHeaderExtension().getLength(), fixedHeaderLength + 2);
            getHeaderExtension().fillBuffer(dataBuffer, fixedHeaderLength + 4);
        }
    }
}
