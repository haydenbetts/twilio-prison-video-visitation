package fm.liveswitch;

public class LrrEntry extends ControlFrameEntry {
    private DataBuffer _dataBuffer;
    private int _lastSequenceNumber;

    public static int getFixedPayloadLength() {
        return 12;
    }

    public boolean getCurrentIdsPresent() {
        return getDataBuffer().read1(5, 0);
    }

    public int getCurrentLayerId() {
        return getDataBuffer().read8(11);
    }

    public int getCurrentTemporalId() {
        return getDataBuffer().read3(10, 5);
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    /* access modifiers changed from: package-private */
    public int getLastSequenceNumber() {
        return this._lastSequenceNumber;
    }

    public int getPayloadType() {
        return getDataBuffer().read7(5, 1);
    }

    public int getSequenceNumber() {
        return getDataBuffer().read8(4);
    }

    public long getSynchronizationSource() {
        return getDataBuffer().read32(0);
    }

    public int getTargetLayerId() {
        return getDataBuffer().read8(9);
    }

    public int getTargetTemporalId() {
        return getDataBuffer().read3(8, 5);
    }

    public LrrEntry(DataBuffer dataBuffer) {
        setLastSequenceNumber(-1);
        if (dataBuffer == null) {
            throw new RuntimeException(new Exception("Data buffer for LRR entry cannot be null."));
        } else if (dataBuffer.getLength() >= getFixedPayloadLength()) {
            setDataBuffer(dataBuffer);
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("Data buffer for LRR entry must be at least ", IntegerExtensions.toString(Integer.valueOf(getFixedPayloadLength())), " bytes.")));
        }
    }

    public LrrEntry(int i) {
        this(i, 0, 0);
    }

    public LrrEntry(int i, int i2, int i3) {
        this(DataBuffer.allocate(getFixedPayloadLength()));
        setSequenceNumber(i);
        setTargetTemporalId(i2);
        setTargetLayerId(i3);
    }

    public LrrEntry(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3);
        setCurrentTemporalId(i4);
        setCurrentLayerId(i5);
        setCurrentIdsPresent(true);
    }

    public void setCurrentIdsPresent(boolean z) {
        getDataBuffer().write1(z, 5, 0);
    }

    public void setCurrentLayerId(int i) {
        getDataBuffer().write8(i, 11);
    }

    public void setCurrentTemporalId(int i) {
        getDataBuffer().write3(i, 10, 5);
    }

    public void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }

    /* access modifiers changed from: package-private */
    public void setLastSequenceNumber(int i) {
        this._lastSequenceNumber = i;
    }

    public void setPayloadType(int i) {
        getDataBuffer().write7(i, 5, 1);
    }

    public void setSequenceNumber(int i) {
        getDataBuffer().write8(i % 256, 4);
    }

    public void setSynchronizationSource(long j) {
        getDataBuffer().write32(j, 0);
    }

    public void setTargetLayerId(int i) {
        getDataBuffer().write8(i, 9);
    }

    public void setTargetTemporalId(int i) {
        getDataBuffer().write3(i, 8, 5);
    }
}
