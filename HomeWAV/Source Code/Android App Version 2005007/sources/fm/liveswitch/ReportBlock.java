package fm.liveswitch;

public class ReportBlock extends ControlFrameEntry {
    private DataBuffer _dataBuffer;

    public static int getFixedPayloadLength() {
        return 24;
    }

    public int getCumulativeNumberOfPacketsLost() {
        return getDataBuffer().read24Signed(5);
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    public long getDelaySinceLastSenderReport() {
        return getDataBuffer().read32(20);
    }

    public long getExtendedHighestSequenceNumberReceived() {
        return getDataBuffer().read32(8);
    }

    public int getFractionLost() {
        return getDataBuffer().read8(4);
    }

    public long getInterarrivalJitter() {
        return getDataBuffer().read32(12);
    }

    public long getLastSenderReportTimestamp() {
        return getDataBuffer().read32(16);
    }

    public double getPercentLost() {
        return ((double) getFractionLost()) / 255.0d;
    }

    public long getSynchronizationSource() {
        return getDataBuffer().read32(0);
    }

    public ReportBlock() {
        this(DataBuffer.allocate(getFixedPayloadLength()));
    }

    public ReportBlock(DataBuffer dataBuffer) {
        if (dataBuffer == null) {
            throw new RuntimeException(new Exception("DataBuffer cannot be null."));
        } else if (dataBuffer.getLength() >= getFixedPayloadLength()) {
            setDataBuffer(dataBuffer);
        } else {
            throw new RuntimeException(new Exception(StringExtensions.concat("ReportBlock DataBuffer must be at least ", IntegerExtensions.toString(Integer.valueOf(getFixedPayloadLength())), " bytes.")));
        }
    }

    public ReportBlock(int i, int i2, long j, long j2, long j3, long j4) {
        this();
        setFractionLost(i);
        setCumulativeNumberOfPacketsLost(i2);
        setExtendedHighestSequenceNumberReceived(j);
        setInterarrivalJitter(j2);
        setLastSenderReportTimestamp(j3);
        setDelaySinceLastSenderReport(j4);
    }

    public ReportBlock(long j, int i, int i2, long j2, long j3, long j4, long j5) {
        this();
        setSynchronizationSource(j);
        setFractionLost(i);
        setCumulativeNumberOfPacketsLost(i2);
        setExtendedHighestSequenceNumberReceived(j2);
        setInterarrivalJitter(j3);
        setLastSenderReportTimestamp(j4);
        setDelaySinceLastSenderReport(j5);
    }

    public void setCumulativeNumberOfPacketsLost(int i) {
        getDataBuffer().write24(i, 5);
    }

    public void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }

    public void setDelaySinceLastSenderReport(long j) {
        getDataBuffer().write32(j, 20);
    }

    public void setExtendedHighestSequenceNumberReceived(long j) {
        getDataBuffer().write32(j, 8);
    }

    public void setFractionLost(int i) {
        getDataBuffer().write8(i, 4);
    }

    public void setInterarrivalJitter(long j) {
        getDataBuffer().write32(j, 12);
    }

    public void setLastSenderReportTimestamp(long j) {
        getDataBuffer().write32(j, 16);
    }

    public void setSynchronizationSource(long j) {
        getDataBuffer().write32(j, 0);
    }

    public String toString() {
        return StringExtensions.format("SSRC: {0}, Fraction Lost: {1}, Cumulative Packets Lost: {2}, EHSN Received: {3}, Jitter: {4}, Last SR: {5}, Delay Since Last SR: {6}", new Object[]{LongExtensions.toString(Long.valueOf(getSynchronizationSource())), IntegerExtensions.toString(Integer.valueOf(getFractionLost())), IntegerExtensions.toString(Integer.valueOf(getCumulativeNumberOfPacketsLost())), LongExtensions.toString(Long.valueOf(getExtendedHighestSequenceNumberReceived())), LongExtensions.toString(Long.valueOf(getInterarrivalJitter())), LongExtensions.toString(Long.valueOf(getLastSenderReportTimestamp())), LongExtensions.toString(Long.valueOf(getDelaySinceLastSenderReport()))});
    }
}
