package fm.liveswitch;

public class SRControlFrame extends ReportControlFrame {
    public static int getFixedSenderInfoPayloadLength() {
        return 20;
    }

    public static int getRegisteredPayloadType() {
        return 200;
    }

    public long getNtpTimestamp() {
        return super.getDataBuffer().read64(8);
    }

    public long getOctetCount() {
        return super.getDataBuffer().read32(24);
    }

    public long getPacketCount() {
        return super.getDataBuffer().read32(20);
    }

    public static int getReportBlockOffset() {
        return MediaControlFrame.getFixedHeaderLength() + ReportControlFrame.getFixedPayloadHeaderLength() + getFixedSenderInfoPayloadLength();
    }

    public long getRtpTimestamp() {
        return super.getDataBuffer().read32(16);
    }

    public void setNtpTimestamp(long j) {
        super.getDataBuffer().write64(j, 8);
    }

    public void setOctetCount(long j) {
        super.getDataBuffer().write32(j, 24);
    }

    public void setPacketCount(long j) {
        super.getDataBuffer().write32(j, 20);
    }

    public void setRtpTimestamp(long j) {
        super.getDataBuffer().write32(j, 16);
    }

    public SRControlFrame() {
        super(getRegisteredPayloadType(), getReportBlockOffset(), DataBuffer.allocate(getReportBlockOffset()));
    }

    public SRControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredPayloadType(), getReportBlockOffset(), dataBuffer);
    }

    public SRControlFrame(ReportBlock reportBlock) {
        super(getRegisteredPayloadType(), reportBlock, getReportBlockOffset());
    }

    public SRControlFrame(ReportBlock[] reportBlockArr) {
        super(getRegisteredPayloadType(), reportBlockArr, getReportBlockOffset());
    }

    public SRControlFrame(long j, long j2, long j3, long j4, long j5) {
        this();
        super.setSynchronizationSource(j);
        setNtpTimestamp(j2);
        setRtpTimestamp(j3);
        setPacketCount(j4);
        setOctetCount(j5);
    }

    public SRControlFrame(long j, long j2, long j3, long j4, long j5, ReportBlock reportBlock) {
        this(reportBlock);
        super.setSynchronizationSource(j);
        setNtpTimestamp(j2);
        setRtpTimestamp(j3);
        setPacketCount(j4);
        setOctetCount(j5);
    }

    public SRControlFrame(long j, long j2, long j3, long j4, long j5, ReportBlock[] reportBlockArr) {
        this(reportBlockArr);
        super.setSynchronizationSource(j);
        setNtpTimestamp(j2);
        setRtpTimestamp(j3);
        setPacketCount(j4);
        setOctetCount(j5);
    }
}
