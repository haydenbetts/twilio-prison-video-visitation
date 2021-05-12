package fm.liveswitch.vp8;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.DataBufferPool;
import fm.liveswitch.IDataBufferPool;
import fm.liveswitch.Log;

class Packet {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(Packet.class);
    private DataBuffer _buffer;

    private static int calculateVariableHeaderLength(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        int i = 1;
        int i2 = (z ? 1 : 0) + (z2 ? z3 ? 2 : 1 : 0) + (z4 ? 1 : 0);
        if (!z6 && !z5) {
            i = 0;
        }
        return i2 + i;
    }

    public static int getFixedHeaderLength() {
        return 1;
    }

    public DataBuffer getBuffer() {
        return this._buffer;
    }

    public boolean getExtendedControlBitsPresent() {
        return getBuffer().read1(0, 0);
    }

    public DataBuffer getHeader() {
        return getBuffer().subset(0, getHeaderLength());
    }

    public int getHeaderLength() {
        return getFixedHeaderLength() + getVariableHeaderLength();
    }

    public int getKeyIndex() {
        int i = getPictureIdPresent() ? getPictureIdExtensionPresent() ? 4 : 3 : 2;
        if (getTemporalLevelZeroIndexPresent()) {
            i++;
        }
        if (getKeyIndexPresent()) {
            return getBuffer().read5(i, 3);
        }
        return -1;
    }

    public boolean getKeyIndexPresent() {
        if (getExtendedControlBitsPresent()) {
            return getBuffer().read1(1, 3);
        }
        return false;
    }

    public boolean getLayerSync() {
        int i;
        if (getPictureIdPresent()) {
            i = 3;
            if (getPictureIdExtensionPresent()) {
                i = 4;
            }
        } else {
            i = 2;
        }
        if (getTemporalLevelZeroIndexPresent()) {
            i++;
        }
        if (getTemporalLayerIndexPresent() || getKeyIndexPresent()) {
            return getBuffer().read1(i, 2);
        }
        return false;
    }

    public boolean getNonReferenceFrame() {
        return getBuffer().read1(0, 2);
    }

    public int getPartitionIndex() {
        return getBuffer().read3(0, 5);
    }

    public DataBuffer getPayload() {
        if (getPayloadLength() >= 0) {
            return getBuffer().subset(getPayloadOffset());
        }
        return null;
    }

    public int getPayloadLength() {
        int length;
        if (getBuffer() != null && (length = getBuffer().getLength() - getHeaderLength()) >= 0) {
            return length;
        }
        return 0;
    }

    public int getPayloadOffset() {
        return getHeaderLength();
    }

    public int getPictureId() {
        if (getPictureIdPresent()) {
            return getPictureIdExtensionPresent() ? getBuffer().read15(2, 1) : getBuffer().read7(2, 1);
        }
        return -1;
    }

    public boolean getPictureIdExtensionPresent() {
        if (getPictureIdPresent()) {
            return getBuffer().read1(2, 0);
        }
        return false;
    }

    public boolean getPictureIdPresent() {
        if (getExtendedControlBitsPresent()) {
            return getBuffer().read1(1, 0);
        }
        return false;
    }

    public boolean getStartOfPartition() {
        return getBuffer().read1(0, 3);
    }

    public int getTemporalLayerIndex() {
        int i;
        if (getPictureIdPresent()) {
            i = 3;
            if (getPictureIdExtensionPresent()) {
                i = 4;
            }
        } else {
            i = 2;
        }
        if (getTemporalLevelZeroIndexPresent()) {
            i++;
        }
        if (getTemporalLayerIndexPresent()) {
            return getBuffer().read2(i, 0);
        }
        return -1;
    }

    public boolean getTemporalLayerIndexPresent() {
        if (getExtendedControlBitsPresent()) {
            return getBuffer().read1(1, 2);
        }
        return false;
    }

    public int getTemporalLevelZeroIndex() {
        int i;
        if (getPictureIdPresent()) {
            i = 3;
            if (getPictureIdExtensionPresent()) {
                i = 4;
            }
        } else {
            i = 2;
        }
        if (getTemporalLevelZeroIndexPresent()) {
            return getBuffer().read8(i);
        }
        return -1;
    }

    public boolean getTemporalLevelZeroIndexPresent() {
        if (getExtendedControlBitsPresent()) {
            return getBuffer().read1(1, 1);
        }
        return false;
    }

    public int getVariableHeaderLength() {
        return calculateVariableHeaderLength(getExtendedControlBitsPresent(), getPictureIdPresent(), getPictureIdExtensionPresent(), getTemporalLevelZeroIndexPresent(), getTemporalLayerIndexPresent(), getKeyIndexPresent());
    }

    private Packet() {
    }

    public Packet(DataBuffer dataBuffer, boolean z) {
        this(dataBuffer, z, false);
    }

    public Packet(DataBuffer dataBuffer, boolean z, boolean z2) {
        this(dataBuffer, z, z2, 0);
    }

    public Packet(DataBuffer dataBuffer, boolean z, boolean z2, int i) {
        this(dataBuffer, z, z2, i, -1);
    }

    public Packet(DataBuffer dataBuffer, boolean z, boolean z2, int i, int i2) {
        this(dataBuffer, z, z2, i, i2, -1, -1, false);
    }

    public Packet(DataBuffer dataBuffer, boolean z, boolean z2, int i, int i2, int i3, int i4, boolean z3) {
        this(dataBuffer, z, z2, i, i2, i3, i4, z3, -1);
    }

    public Packet(DataBuffer dataBuffer, boolean z, boolean z2, int i, int i2, int i3, int i4, boolean z3, int i5) {
        DataBuffer dataBuffer2;
        boolean z4;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        int i9 = i5;
        boolean z5 = true;
        boolean z6 = i6 != -1;
        boolean z7 = i7 != -1;
        boolean z8 = i8 != -1;
        boolean z9 = i9 != -1;
        boolean z10 = z6 && i6 > 127;
        if (!z6 && !z7 && !z8 && !z9) {
            z5 = false;
        }
        boolean z11 = z10;
        boolean z12 = z8;
        int fixedHeaderLength = getFixedHeaderLength() + calculateVariableHeaderLength(z5, z6, z10, z7, z8, z9);
        if (dataBuffer == null) {
            dataBuffer2 = DataBuffer.allocate(fixedHeaderLength);
        } else {
            dataBuffer2 = DataBuffer.allocate(fixedHeaderLength + dataBuffer.getLength());
        }
        setBuffer(dataBuffer2);
        setExtendedControlBitsPresent(z5);
        setNonReferenceFrame(z2);
        setStartOfPartition(z);
        setPartitionIndex(i);
        if (z5) {
            setPictureIdPresent(z6);
            setTemporalLevelZeroIndexPresent(z7);
            setTemporalLayerIndexPresent(z12);
            setKeyIndexPresent(z9);
            if (z6) {
                setPictureIdExtensionPresent(z11);
                setPictureId(i6);
            }
            if (z7) {
                setTemporalLevelZeroIndex(i7);
            }
            if (z12) {
                z4 = z12;
                setTemporalLayerIndex(i4);
            } else {
                z4 = z12;
            }
            if (z4 || z9) {
                setLayerSync(z3);
            }
            if (z9) {
                setKeyIndex(i5);
            }
        }
        if (dataBuffer != null) {
            setPayload(dataBuffer);
        }
    }

    private void resizeBuffer(int i, int i2) {
        if (!getBuffer().getIsPooled() || getBuffer().canResize(i, i2)) {
            getBuffer().resize(i, i2, true);
            return;
        }
        DataBuffer buffer = getBuffer();
        DataBuffer take = __dataBufferPool.take(i, false, true);
        take.write(buffer.subset(0, i2));
        take.write(buffer.subset(i2), i2 + (i - buffer.getLength()));
        setBuffer(take);
        buffer.free();
    }

    private void setBuffer(DataBuffer dataBuffer) {
        this._buffer = dataBuffer;
    }

    private void setExtendedControlBitsPresent(boolean z) {
        getBuffer().write1(z, 0, 0);
    }

    private void setKeyIndex(int i) {
        int i2 = getPictureIdPresent() ? getPictureIdExtensionPresent() ? 4 : 3 : 2;
        if (getTemporalLevelZeroIndexPresent()) {
            i2++;
        }
        getBuffer().write5(i, i2, 3);
    }

    private void setKeyIndexPresent(boolean z) {
        getBuffer().write1(z, 1, 3);
    }

    private void setLayerSync(boolean z) {
        int i;
        if (getPictureIdPresent()) {
            i = 3;
            if (getPictureIdExtensionPresent()) {
                i = 4;
            }
        } else {
            i = 2;
        }
        if (getTemporalLevelZeroIndexPresent()) {
            i++;
        }
        getBuffer().write1(z, i, 2);
    }

    public void setNonReferenceFrame(boolean z) {
        getBuffer().write1(z, 0, 2);
    }

    public void setPartitionIndex(int i) {
        getBuffer().write3(i, 0, 5);
    }

    public void setPayload(DataBuffer dataBuffer) {
        int headerLength = getHeaderLength();
        resizeBuffer(dataBuffer.getLength() + headerLength, headerLength);
        getBuffer().write(dataBuffer, headerLength);
    }

    public void setPictureId(int i) {
        if (!getExtendedControlBitsPresent()) {
            resizeBuffer(getBuffer().getLength() + 3, 1);
            setExtendedControlBitsPresent(true);
            setPictureIdPresent(true);
            setPictureIdExtensionPresent(true);
        } else if (!getPictureIdPresent()) {
            resizeBuffer(getBuffer().getLength() + 2, 2);
            setPictureIdPresent(true);
            setPictureIdExtensionPresent(true);
        } else if (!getPictureIdExtensionPresent() && i > 127) {
            resizeBuffer(getBuffer().getLength() + 1, 3);
            setPictureIdExtensionPresent(true);
        }
        if (getPictureIdExtensionPresent()) {
            getBuffer().write15(i, 2, 1);
        } else {
            getBuffer().write7(i, 2, 1);
        }
    }

    private void setPictureIdExtensionPresent(boolean z) {
        getBuffer().write1(z, 2, 0);
    }

    private void setPictureIdPresent(boolean z) {
        getBuffer().write1(z, 1, 0);
    }

    public void setStartOfPartition(boolean z) {
        getBuffer().write1(z, 0, 3);
    }

    private void setTemporalLayerIndex(int i) {
        int i2;
        if (getPictureIdPresent()) {
            i2 = 3;
            if (getPictureIdExtensionPresent()) {
                i2 = 4;
            }
        } else {
            i2 = 2;
        }
        if (getTemporalLevelZeroIndexPresent()) {
            i2++;
        }
        getBuffer().write2(i, i2, 0);
    }

    private void setTemporalLayerIndexPresent(boolean z) {
        getBuffer().write1(z, 1, 2);
    }

    public void setTemporalLevelZeroIndex(int i) {
        int i2;
        if (getPictureIdPresent()) {
            i2 = 3;
            if (getPictureIdExtensionPresent()) {
                i2 = 4;
            }
        } else {
            i2 = 2;
        }
        getBuffer().write8(i, i2);
    }

    private void setTemporalLevelZeroIndexPresent(boolean z) {
        getBuffer().write1(z, 1, 1);
    }

    public static Packet wrap(DataBuffer dataBuffer) {
        int i;
        try {
            Packet packet = new Packet();
            packet.setBuffer(dataBuffer);
            if (packet.getExtendedControlBitsPresent()) {
                i = 2;
                if (packet.getPictureIdPresent()) {
                    i = packet.getPictureIdExtensionPresent() ? 4 : 3;
                }
                if (packet.getTemporalLevelZeroIndexPresent()) {
                    i++;
                }
                if (packet.getTemporalLayerIndexPresent() || packet.getKeyIndexPresent()) {
                    i++;
                }
            } else {
                i = 1;
            }
            if (i + 1 > dataBuffer.getLength()) {
                Log.error("VP8 packet payload must be at least 1 byte.");
                return null;
            }
            packet.setPayload(dataBuffer.subset(i));
            return packet;
        } catch (Exception e) {
            Log.error("Malformed VP8 packet.", e);
            return null;
        }
    }
}
