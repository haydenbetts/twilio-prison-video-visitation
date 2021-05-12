package fm.liveswitch;

public class AfbControlFrame extends PayloadSpecificControlFrame {
    public static int getRegisteredFeedbackMessageType() {
        return 15;
    }

    public AfbControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
    }

    public AfbControlFrame(long j, long j2) {
        super(getRegisteredFeedbackMessageType(), j, j2);
        super.setPayloadLengthWithPadding(FeedbackControlFrame.getFixedPayloadHeaderLength());
    }

    public AfbControlFrame(long j, long j2, DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), j, j2, dataBuffer);
        if (dataBuffer.getLength() % 4 == 0) {
            super.setPayloadLengthWithPadding(FeedbackControlFrame.getFixedPayloadHeaderLength() + dataBuffer.getLength());
            return;
        }
        throw new RuntimeException(new Exception("Application-dependent data must have a length that is a multiple of 32 bits."));
    }
}
