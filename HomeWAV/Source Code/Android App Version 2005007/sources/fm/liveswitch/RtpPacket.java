package fm.liveswitch;

public class RtpPacket {
    private int __contributingSourceCount = -1;
    private long[] __contributingSources = null;
    private boolean __extension = false;
    private boolean __extensionValid = false;
    private DataBuffer __header = null;
    private DataBuffer __headerExtension = null;
    private int __headerExtensionId = -1;
    private int __headerExtensionLength = -1;
    private boolean __marker = false;
    private boolean __markerValid = false;
    private boolean __padding = false;
    private int __paddingLength = -1;
    private boolean __paddingValid = false;
    private DataBuffer __payload;
    private int __payloadType = -1;
    private int __sequenceNumber = -1;
    private long __synchronizationSource = -1;
    private long __timestamp = -1;
    private int __version = -1;
    private DataBuffer _buffer;

    public static int getFixedHeaderLength() {
        return 12;
    }

    public static int getSequenceNumberDelta(int i, int i2) {
        int i3 = i - i2;
        return i3 < -32768 ? i3 + 65536 : i3 > 32768 ? i3 - 65536 : i3;
    }

    public DataBuffer getBuffer() {
        return this._buffer;
    }

    public int getContributingSourceCount() {
        if (this.__contributingSourceCount == -1) {
            this.__contributingSourceCount = getBuffer().read4(0, 4);
        }
        return this.__contributingSourceCount;
    }

    public long[] getContributingSources() {
        if (this.__contributingSources == null) {
            int contributingSourcesOffset = getContributingSourcesOffset();
            long[] jArr = new long[getContributingSourceCount()];
            int i = 0;
            while (i < getContributingSourceCount()) {
                LongHolder longHolder = new LongHolder(0);
                boolean tryRead32 = getBuffer().tryRead32(contributingSourcesOffset, longHolder);
                long value = longHolder.getValue();
                if (tryRead32) {
                    contributingSourcesOffset += 4;
                    jArr[i] = value;
                    i++;
                } else {
                    Log.error("Could not read contributing source from buffer.");
                    return null;
                }
            }
            this.__contributingSources = jArr;
        }
        return this.__contributingSources;
    }

    public int getContributingSourcesLength() {
        return getContributingSourceCount() * 4;
    }

    public int getContributingSourcesOffset() {
        return getFixedHeaderLength();
    }

    public boolean getExtension() {
        if (!this.__extensionValid) {
            this.__extension = getBuffer().read1(0, 3);
            this.__extensionValid = true;
        }
        return this.__extension;
    }

    public DataBuffer getHeader() {
        DataBuffer dataBuffer = this.__header;
        if (dataBuffer == null || dataBuffer.getLength() != getHeaderLength()) {
            this.__header = getBuffer().subset(0, getHeaderLength());
        }
        return this.__header;
    }

    public DataBuffer getHeaderExtension() {
        if (this.__headerExtension == null) {
            this.__headerExtension = getExtension() ? getBuffer().subset(getHeaderExtensionOffset(), getHeaderExtensionLength()) : null;
        }
        return this.__headerExtension;
    }

    public int getHeaderExtensionId() {
        if (this.__headerExtensionId == -1) {
            this.__headerExtensionId = getExtension() ? getBuffer().read16(getHeaderExtensionOffset()) : 0;
        }
        return this.__headerExtensionId;
    }

    public int getHeaderExtensionLength() {
        if (this.__headerExtensionLength == -1) {
            this.__headerExtensionLength = getExtension() ? getBuffer().read16(getHeaderExtensionOffset() + 2) * 4 : 0;
        }
        return this.__headerExtensionLength;
    }

    public int getHeaderExtensionOffset() {
        return getContributingSourcesOffset() + getContributingSourcesLength();
    }

    public int getHeaderLength() {
        return getFixedHeaderLength() + getVariableHeaderLength();
    }

    public boolean getMarker() {
        if (!this.__markerValid) {
            this.__marker = getBuffer().read1(1, 0);
            this.__markerValid = true;
        }
        return this.__marker;
    }

    public boolean getPadding() {
        if (!this.__paddingValid) {
            this.__padding = getBuffer().read1(0, 2);
            this.__paddingValid = true;
        }
        return this.__padding;
    }

    public int getPaddingLength() {
        if (this.__paddingLength == -1) {
            this.__paddingLength = getPadding() ? getBuffer().read8(getBuffer().getLength() - 1) : 0;
        }
        return this.__paddingLength;
    }

    public DataBuffer getPayload() {
        DataBuffer dataBuffer = this.__payload;
        if (dataBuffer == null || dataBuffer.getLength() != getPayloadLength()) {
            this.__payload = getBuffer().subset(getPayloadOffset());
        }
        return this.__payload;
    }

    public int getPayloadLength() {
        if (getBuffer() == null) {
            return 0;
        }
        return (getBuffer().getLength() - getHeaderLength()) - getPaddingLength();
    }

    public int getPayloadOffset() {
        return getHeaderLength();
    }

    public int getPayloadType() {
        if (this.__payloadType == -1) {
            this.__payloadType = getBuffer().read7(1, 1);
        }
        return this.__payloadType;
    }

    public int getSequenceNumber() {
        if (this.__sequenceNumber == -1) {
            this.__sequenceNumber = getBuffer().read16(2);
        }
        return this.__sequenceNumber;
    }

    public long getSynchronizationSource() {
        if (this.__synchronizationSource == -1) {
            this.__synchronizationSource = getBuffer().read32(8);
        }
        return this.__synchronizationSource;
    }

    public long getTimestamp() {
        if (this.__timestamp == -1) {
            this.__timestamp = getBuffer().read32(4);
        }
        return this.__timestamp;
    }

    public int getVariableHeaderLength() {
        return getContributingSourcesLength() + (getExtension() ? getHeaderExtensionLength() + 4 : 0);
    }

    public int getVersion() {
        if (this.__version == -1) {
            this.__version = getBuffer().read2(0, 0);
        }
        return this.__version;
    }

    /* access modifiers changed from: package-private */
    public int obtainAbsSendTime24(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        RtpHeaderAbsSendTime absSendTime;
        if (getHeaderExtension() == null || (absSendTime = RtpHeaderExtension.parseBytes(getHeaderExtension(), rtpHeaderExtensionRegistry).getAbsSendTime()) == null) {
            return -1;
        }
        return absSendTime.getAbsSendTime24();
    }

    /* access modifiers changed from: package-private */
    public void removeAbsSendTime(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        if (getHeaderExtension() != null) {
            RtpHeaderExtension parseBytes = RtpHeaderExtension.parseBytes(getHeaderExtension(), rtpHeaderExtensionRegistry);
            parseBytes.setAbsSendTime((RtpHeaderAbsSendTime) null);
            if (parseBytes.getExtensionElementCount() == 0) {
                setHeaderExtension((DataBuffer) null);
            }
        }
    }

    private void resizeForContributingSources(int i) {
        getBuffer().resize((getBuffer().getLength() + (i * 4)) - (getContributingSourceCount() * 4));
        setContributingSourceCount(i);
    }

    private void resizeForHeaderExtension(boolean z, int i, int i2) {
        boolean extension = getExtension();
        int headerExtensionLength = getHeaderExtensionLength();
        int i3 = z ? i : 0;
        if (!extension) {
            headerExtensionLength = 0;
        }
        getBuffer().resize((getBuffer().getLength() + i3) - headerExtensionLength, i2);
        setExtension(z);
        if (z) {
            setHeaderExtensionLength(i);
        }
    }

    private void resizeForPayload(int i) {
        getBuffer().resize((getPayloadOffset() + i) - getPayloadLength());
    }

    public RtpPacket() {
    }

    public RtpPacket(DataBuffer dataBuffer) {
        setBuffer(DataBuffer.allocate(dataBuffer.getLength() + getFixedHeaderLength(), false));
        getBuffer().write(dataBuffer, getFixedHeaderLength());
        setVersion(2);
    }

    /* access modifiers changed from: package-private */
    public void setAbsSendTime(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry, long j) {
        RtpHeaderExtension rtpHeaderExtension;
        int registeredIdForRtpHeaderExtensionType = rtpHeaderExtensionRegistry.registeredIdForRtpHeaderExtensionType(RtpHeaderExtensionType.AbsSendTime);
        if (getHeaderExtension() != null) {
            rtpHeaderExtension = RtpHeaderExtension.parseBytes(getHeaderExtension(), rtpHeaderExtensionRegistry);
            RtpHeaderAbsSendTime rtpHeaderAbsSendTime = new RtpHeaderAbsSendTime(rtpHeaderExtension.getForm(), j);
            rtpHeaderAbsSendTime.setId(registeredIdForRtpHeaderExtensionType);
            rtpHeaderExtension.setAbsSendTime(rtpHeaderAbsSendTime);
        } else {
            RtpHeaderAbsSendTime rtpHeaderAbsSendTime2 = new RtpHeaderAbsSendTime(RtpHeaderExtensionForm.OneByte, j);
            rtpHeaderAbsSendTime2.setId(registeredIdForRtpHeaderExtensionType);
            rtpHeaderExtension = new RtpHeaderExtension(RtpHeaderExtensionForm.OneByte, new RtpHeaderExtensionElement[]{rtpHeaderAbsSendTime2});
        }
        setHeaderExtension(rtpHeaderExtension.getData());
    }

    private void setBuffer(DataBuffer dataBuffer) {
        this._buffer = dataBuffer;
    }

    private void setContributingSourceCount(int i) {
        if (this.__contributingSourceCount != i) {
            getBuffer().write4(i, 0, 4);
            this.__contributingSourceCount = i;
        }
    }

    public void setContributingSources(long[] jArr) {
        if (jArr == null) {
            jArr = new long[0];
        }
        if (this.__contributingSources != jArr) {
            resizeForContributingSources(ArrayExtensions.getLength(jArr));
            int contributingSourcesOffset = getContributingSourcesOffset();
            for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
                getBuffer().write32(jArr[i], contributingSourcesOffset);
                contributingSourcesOffset += 4;
            }
            this.__contributingSources = jArr;
        }
    }

    private void setExtension(boolean z) {
        if (!this.__extensionValid || !Global.equals(Boolean.valueOf(this.__extension), Boolean.valueOf(z))) {
            getBuffer().write1(z, 0, 3);
            this.__extension = z;
            this.__extensionValid = true;
        }
    }

    public void setHeaderExtension(DataBuffer dataBuffer) {
        if (!Global.equals(this.__headerExtension, dataBuffer)) {
            if (dataBuffer == null) {
                resizeForHeaderExtension(false, 0, getHeaderExtensionOffset());
            } else {
                resizeForHeaderExtension(true, dataBuffer.getLength(), getHeaderExtensionOffset());
                getBuffer().write(dataBuffer, getHeaderExtensionOffset());
            }
            this.__headerExtension = dataBuffer;
        }
    }

    public void setHeaderExtensionId(int i) {
        if (this.__headerExtensionId != i) {
            resizeForHeaderExtension(true, getHeaderExtensionLength(), getHeaderExtensionOffset());
            getBuffer().write16(i, getHeaderExtensionOffset());
            this.__headerExtensionId = i;
        }
    }

    private void setHeaderExtensionLength(int i) {
        if (this.__headerExtensionLength != i) {
            getBuffer().write16(i / 4, getHeaderExtensionOffset() + 2);
            this.__headerExtensionLength = i;
        }
    }

    public void setMarker(boolean z) {
        if (!this.__markerValid || !Global.equals(Boolean.valueOf(this.__marker), Boolean.valueOf(z))) {
            getBuffer().write1(z, 1, 0);
            this.__marker = z;
            this.__markerValid = true;
        }
    }

    public void setPadding(boolean z) {
        if (!this.__paddingValid || !Global.equals(Boolean.valueOf(this.__padding), Boolean.valueOf(z))) {
            getBuffer().write1(z, 0, 2);
            this.__padding = z;
            this.__paddingValid = true;
        }
    }

    private void setPaddingLength(int i) {
        if (this.__paddingLength != i) {
            setPadding(i > 0);
            int i2 = 0;
            while (i2 < i) {
                getBuffer().write8(i2 < i + -1 ? 0 : i, (getBuffer().getLength() - i) + i2);
                i2++;
            }
        }
    }

    public void setPayload(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() > getBuffer().getLength()) {
            setBuffer(dataBuffer.clone());
            getBuffer().prepend(getHeader());
        } else if (getBuffer().getLength() == getHeaderLength()) {
            getBuffer().append(getPayload());
        } else {
            if (getHeaderLength() + dataBuffer.getLength() > getBuffer().getLength()) {
                getBuffer().resize(getHeaderLength() + dataBuffer.getLength());
            }
            getBuffer().write(dataBuffer, getHeaderLength());
        }
        this.__payload = dataBuffer;
    }

    public void setPayloadType(int i) {
        if (this.__payloadType != i) {
            getBuffer().write7(i, 1, 1);
            this.__payloadType = i;
        }
    }

    public void setSequenceNumber(int i) {
        if (this.__sequenceNumber != i) {
            getBuffer().write16(i, 2);
            this.__sequenceNumber = i;
        }
    }

    public void setSynchronizationSource(long j) {
        if (this.__synchronizationSource != j) {
            getBuffer().write32(j, 8);
            this.__synchronizationSource = j;
        }
    }

    public void setTimestamp(long j) {
        if (this.__timestamp != j) {
            getBuffer().write32(j, 4);
            this.__timestamp = j;
        }
    }

    public void setVersion(int i) {
        if (this.__version != i) {
            getBuffer().write2(i, 0, 0);
            this.__version = i;
        }
    }

    public static RtpPacket wrap(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() < getFixedHeaderLength()) {
            return null;
        }
        RtpPacket rtpPacket = new RtpPacket();
        rtpPacket.setBuffer(dataBuffer);
        if (rtpPacket.getVersion() != 2) {
            return null;
        }
        if ((rtpPacket.getPayloadType() < 72 || rtpPacket.getPayloadType() > 85) && rtpPacket.getContributingSourcesLength() <= dataBuffer.getLength() && rtpPacket.getHeaderExtensionLength() <= dataBuffer.getLength() && rtpPacket.getHeaderLength() <= dataBuffer.getLength()) {
            return rtpPacket;
        }
        return null;
    }
}
