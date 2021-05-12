package fm.liveswitch;

public class PliControlFrame extends PayloadSpecificControlFrame {
    public static int getRegisteredFeedbackMessageType() {
        return 1;
    }

    public PliControlFrame() {
        super(getRegisteredFeedbackMessageType());
    }

    public PliControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
    }
}
