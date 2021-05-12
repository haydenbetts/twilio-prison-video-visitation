package fm.liveswitch;

public class TmmbnEntry extends ControlFrameEntry {
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
        return (long) (getMaximumBitrateMantissa() << getMaximumBitrateExponent());
    }

    public int getMaximumBitrateExponent() {
        return getDataBuffer().read6(4, 0);
    }

    public int getMaximumBitrateMantissa() {
        return getDataBuffer().read17(4, 6);
    }

    public int getNormalizedMaximumBitrate() {
        return toNormalized(getMaximumBitrate());
    }

    public long getSynchronizationSource() {
        return getDataBuffer().read32(0);
    }

    public static TmmbnEntry normalized(int i, long j) {
        return new TmmbnEntry(fromNormalized(i), j);
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
            } else if (j <= ((long) (131071 << i2))) {
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

    public void setNormalizedMaximumBitrate(int i) {
        setMaximumBitrate(fromNormalized(i));
    }

    public void setSynchronizationSource(long j) {
        getDataBuffer().write32(j, 0);
    }

    public TmmbnEntry(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            throw new RuntimeException(new Exception("Data buffer for TMMBN entry cannot be null."));
        } else if (dataBuffer.getLength() >= getFixedPayloadLength()) {
            setDataBuffer(dataBuffer);
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("Data buffer for TMMBN entry must be at least ", IntegerExtensions.toString(Integer.valueOf(getFixedPayloadLength())), " bytes.")));
        }
    }

    public TmmbnEntry(long j, long j2) {
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
