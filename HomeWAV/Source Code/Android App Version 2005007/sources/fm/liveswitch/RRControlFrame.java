package fm.liveswitch;

public class RRControlFrame extends ReportControlFrame {
    public static int getRegisteredPayloadType() {
        return 201;
    }

    public RRControlFrame() {
        this(DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + ReportControlFrame.getFixedPayloadHeaderLength()));
    }

    public RRControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredPayloadType(), MediaControlFrame.getFixedHeaderLength() + ReportControlFrame.getFixedPayloadHeaderLength(), dataBuffer);
    }

    public RRControlFrame(ReportBlock reportBlock) {
        super(getRegisteredPayloadType(), reportBlock);
    }

    public RRControlFrame(ReportBlock[] reportBlockArr) {
        super(getRegisteredPayloadType(), reportBlockArr);
    }

    public RRControlFrame(long j, ReportBlock reportBlock) {
        this(reportBlock);
        super.setSynchronizationSource(j);
    }

    public RRControlFrame(long j, ReportBlock[] reportBlockArr) {
        this(reportBlockArr);
        super.setSynchronizationSource(j);
    }
}
