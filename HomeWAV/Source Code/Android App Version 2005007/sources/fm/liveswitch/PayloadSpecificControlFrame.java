package fm.liveswitch;

public abstract class PayloadSpecificControlFrame extends FeedbackControlFrame {
    public static int getRegisteredPayloadType() {
        return 206;
    }

    public PayloadSpecificControlFrame(int i) {
        super(i, getRegisteredPayloadType());
    }

    public PayloadSpecificControlFrame(int i, DataBuffer dataBuffer) {
        super(i, getRegisteredPayloadType(), dataBuffer);
    }

    public PayloadSpecificControlFrame(int i, long j, long j2) {
        super(i, getRegisteredPayloadType(), j, j2);
    }

    public PayloadSpecificControlFrame(int i, long j, long j2, DataBuffer dataBuffer) {
        super(i, getRegisteredPayloadType(), j, j2, dataBuffer);
    }
}
