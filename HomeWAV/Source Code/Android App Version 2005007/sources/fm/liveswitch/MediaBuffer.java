package fm.liveswitch;

import fm.liveswitch.MediaBuffer;
import fm.liveswitch.MediaFormat;

public abstract class MediaBuffer<TFormat extends MediaFormat<TFormat>, TBuffer extends MediaBuffer<TFormat, TBuffer>> extends Dynamic {
    private DataBuffer[] __dataBuffers;
    private TFormat __format;
    private boolean _recoveredByFec;
    private RtpPacketHeader[] _rtpHeaders;
    private long[] _sequenceNumbers;
    private String _sourceId;

    /* access modifiers changed from: protected */
    public abstract TBuffer createInstance();

    public abstract boolean getIsMuted();

    /* access modifiers changed from: package-private */
    public boolean getIsPacketized() {
        return false;
    }

    public abstract boolean mute();

    public TBuffer clone() {
        TBuffer createInstance = createInstance();
        createInstance.setDataBuffers(getDataBuffers());
        createInstance.setSequenceNumbers(Utility.cloneLongArray(getSequenceNumbers()));
        if (getRtpHeaders() != null) {
            createInstance.setRtpHeaders(new RtpPacketHeader[ArrayExtensions.getLength((Object[]) getRtpHeaders())]);
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) createInstance.getRtpHeaders()); i++) {
                if (getRtpHeaders()[i] != null) {
                    createInstance.getRtpHeaders()[i] = getRtpHeaders()[i].clone();
                }
            }
        }
        createInstance.setFormat(getFormat().clone());
        return createInstance;
    }

    public TBuffer free() {
        for (DataBuffer free : getDataBuffers()) {
            free.free();
        }
        return this;
    }

    public DataBuffer getDataBuffer() {
        return (DataBuffer) Utility.firstOrDefault((T[]) getDataBuffers());
    }

    public DataBuffer[] getDataBuffers() {
        return this.__dataBuffers;
    }

    public int getFootprint() {
        int i = 0;
        for (DataBuffer length : getDataBuffers()) {
            i += length.getLength();
        }
        return i;
    }

    public TFormat getFormat() {
        return this.__format;
    }

    public long getLastSequenceNumber() {
        long[] sequenceNumbers = getSequenceNumbers();
        if (sequenceNumbers == null) {
            return -1;
        }
        return sequenceNumbers[ArrayExtensions.getLength(sequenceNumbers) - 1];
    }

    public boolean getRecoveredByFec() {
        return this._recoveredByFec;
    }

    public RtpPacketHeader getRtpHeader() {
        return (RtpPacketHeader) Utility.firstOrDefault((T[]) getRtpHeaders());
    }

    public RtpPacketHeader[] getRtpHeaders() {
        return this._rtpHeaders;
    }

    public int getRtpSequenceNumber() {
        int[] rtpSequenceNumbers = getRtpSequenceNumbers();
        if (rtpSequenceNumbers == null) {
            return -1;
        }
        return rtpSequenceNumbers[0];
    }

    public int[] getRtpSequenceNumbers() {
        RtpPacketHeader[] rtpHeaders = getRtpHeaders();
        if (rtpHeaders == null) {
            return null;
        }
        int[] iArr = new int[ArrayExtensions.getLength((Object[]) rtpHeaders)];
        for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
            if (rtpHeaders[i] == null) {
                iArr[i] = -1;
            } else {
                iArr[i] = rtpHeaders[i].getSequenceNumber();
            }
        }
        return iArr;
    }

    public long getSequenceNumber() {
        long[] sequenceNumbers = getSequenceNumbers();
        if (sequenceNumbers == null) {
            return -1;
        }
        return sequenceNumbers[0];
    }

    public long[] getSequenceNumbers() {
        return this._sequenceNumbers;
    }

    public String getSourceId() {
        return this._sourceId;
    }

    public TBuffer keep() {
        for (DataBuffer keep : getDataBuffers()) {
            keep.keep();
        }
        return this;
    }

    protected MediaBuffer() {
    }

    protected MediaBuffer(DataBuffer dataBuffer, TFormat tformat) {
        setDataBuffer(dataBuffer);
        setFormat(tformat);
    }

    protected MediaBuffer(DataBuffer[] dataBufferArr, TFormat tformat) {
        setDataBuffers(dataBufferArr);
        setFormat(tformat);
    }

    public void setDataBuffer(DataBuffer dataBuffer) {
        if (dataBuffer != null) {
            this.__dataBuffers = new DataBuffer[]{dataBuffer};
            return;
        }
        throw new RuntimeException(new Exception("Data buffer cannot be null."));
    }

    public void setDataBuffers(DataBuffer[] dataBufferArr) {
        if (dataBufferArr != null) {
            this.__dataBuffers = dataBufferArr;
            return;
        }
        throw new RuntimeException(new Exception("Data buffers cannot be null."));
    }

    public void setFormat(TFormat tformat) {
        if (tformat != null) {
            this.__format = tformat;
            return;
        }
        throw new RuntimeException(new Exception("Format cannot be null."));
    }

    public void setRecoveredByFec(boolean z) {
        this._recoveredByFec = z;
    }

    public void setRtpHeader(RtpPacketHeader rtpPacketHeader) {
        RtpPacketHeader[] rtpPacketHeaderArr;
        if (rtpPacketHeader == null) {
            rtpPacketHeaderArr = null;
        } else {
            rtpPacketHeaderArr = new RtpPacketHeader[]{rtpPacketHeader};
        }
        setRtpHeaders(rtpPacketHeaderArr);
    }

    public void setRtpHeaders(RtpPacketHeader[] rtpPacketHeaderArr) {
        this._rtpHeaders = rtpPacketHeaderArr;
    }

    public void setSequenceNumber(long j) {
        setSequenceNumbers(new long[]{j});
    }

    public void setSequenceNumbers(long[] jArr) {
        this._sequenceNumbers = jArr;
    }

    public void setSourceId(String str) {
        this._sourceId = str;
    }

    public String toString() {
        return getFormat().toString();
    }

    public boolean tryKeep() {
        try {
            for (DataBuffer keep : getDataBuffers()) {
                keep.keep();
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
