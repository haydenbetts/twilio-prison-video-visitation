package fm.liveswitch;

public abstract class RtpControlFrame extends FeedbackControlFrame {
    public static int getRegisteredPayloadType() {
        return 205;
    }

    public RtpControlFrame(int i) {
        super(i, getRegisteredPayloadType());
    }

    public RtpControlFrame(int i, DataBuffer dataBuffer) {
        super(i, getRegisteredPayloadType(), dataBuffer);
    }

    public RtpControlFrame(int i, int i2, long j, long j2) {
        super(i, i2, j, j2);
    }

    public RtpControlFrame(int i, int i2, long j, long j2, DataBuffer dataBuffer) {
        super(i, i2, j, j2, dataBuffer);
    }
}
