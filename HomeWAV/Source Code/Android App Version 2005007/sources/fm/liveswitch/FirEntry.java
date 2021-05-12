package fm.liveswitch;

import androidx.core.view.InputDeviceCompat;

public class FirEntry extends ControlFrameEntry {
    private DataBuffer _dataBuffer;

    public static int getFixedPayloadLength() {
        return 8;
    }

    public static int getSequenceNumberDelta(int i, int i2) {
        int i3 = i - i2;
        return i3 < -128 ? i3 + 256 : i3 > 128 ? i3 + InputDeviceCompat.SOURCE_ANY : i3;
    }

    public FirEntry(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            throw new RuntimeException(new Exception("Data buffer for FIR entry cannot be null."));
        } else if (dataBuffer.getLength() >= getFixedPayloadLength()) {
            setDataBuffer(dataBuffer);
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("Data buffer for FIR entry must be at least ", IntegerExtensions.toString(Integer.valueOf(getFixedPayloadLength())), " bytes.")));
        }
    }

    public FirEntry(int i) {
        this(DataBuffer.allocate(getFixedPayloadLength()));
        setSequenceNumber(i);
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    public int getSequenceNumber() {
        return getDataBuffer().read8(4);
    }

    public long getSynchronizationSource() {
        return getDataBuffer().read32(0);
    }

    public void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }

    public void setSequenceNumber(int i) {
        getDataBuffer().write8(i % 256, 4);
    }

    public void setSynchronizationSource(long j) {
        getDataBuffer().write32(j, 0);
    }
}
