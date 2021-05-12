package fm.liveswitch;

public class TmmbrEntry extends ControlFrameEntry {
    private DataBuffer _dataBuffer;

    private static long fromNormalized(int i) {
        if (i == -1) {
            return 1;
        }
        return ((long) i) * 1000;
    }

    public static int getFixedPayloadLength() {
        return 8;
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    public long getMaximumBitrate() {
        return BitAssistant.leftShiftLong((long) getMaximumBitrateMantissa(), getMaximumBitrateExponent());
    }

    public int getMaximumBitrateExponent() {
        return getDataBuffer().read6(4, 0);
    }

    public int getMaximumBitrateMantissa() {
        return getDataBuffer().read17(4, 6);
    }

    public int getMeasuredOverhead() {
        return getDataBuffer().read9(6, 7);
    }

    public int getNormalizedMaximumBitrate() {
        return toNormalized(getMaximumBitrate());
    }

    public long getSynchronizationSource() {
        return getDataBuffer().read32(0);
    }

    public static TmmbrEntry normalized(int i, long j) {
        return new TmmbrEntry(fromNormalized(i), j);
    }

    public void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }

    public void setMaximumBitrate(long j) {
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= 64) {
                break;
            } else if (j <= ((long) BitAssistant.leftShiftInteger(131071, i2))) {
                i = i2;
                break;
            } else {
                i2++;
            }
        }
        setMaximumBitrateExponent(i);
        setMaximumBitrateMantissa((int) BitAssistant.rightShiftLong(j, i));
    }

    public void setMaximumBitrateExponent(int i) {
        getDataBuffer().write6(i % 64, 4, 0);
    }

    public void setMaximumBitrateMantissa(int i) {
        getDataBuffer().write17(i % 131072, 4, 6);
    }

    public void setMeasuredOverhead(int i) {
        getDataBuffer().write9(i % 512, 6, 7);
    }

    public void setNormalizedMaximumBitrate(int i) {
        setMaximumBitrate(fromNormalized(i));
    }

    public void setSynchronizationSource(long j) {
        getDataBuffer().write32(j, 0);
    }

    public TmmbrEntry(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            throw new RuntimeException(new Exception("Data buffer for TMMBR entry cannot be null."));
        } else if (dataBuffer.getLength() >= getFixedPayloadLength()) {
            setDataBuffer(dataBuffer);
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("Data buffer for TMMBR entry must be at least ", IntegerExtensions.toString(Integer.valueOf(getFixedPayloadLength())), " bytes.")));
        }
    }

    public TmmbrEntry(long j, long j2) {
        this(DataBuffer.allocate(getFixedPayloadLength()));
        setMaximumBitrate(j);
        setSynchronizationSource(j2);
    }

    private static int toNormalized(long j) {
        if (j == 1) {
            return -1;
        }
        return (int) MathAssistant.round(((double) j) / 1000.0d);
    }
}
