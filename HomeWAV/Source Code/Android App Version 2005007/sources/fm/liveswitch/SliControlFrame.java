package fm.liveswitch;

public class SliControlFrame extends PayloadSpecificControlFrame {
    private Sli[] __sliceLossIndications = null;

    public static int getRegisteredFedbackMessageType() {
        return 2;
    }

    public Sli[] getSliceLossIndications() {
        return this.__sliceLossIndications;
    }

    public int getSliceLossIndicationsCount() {
        return super.getPayload().getLength() / 4;
    }

    public SliControlFrame() {
        super(getRegisteredFedbackMessageType());
    }

    public SliControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFedbackMessageType(), dataBuffer);
        super.setFeedbackMessageType(getRegisteredFedbackMessageType());
    }

    public SliControlFrame(Sli sli) {
        super(getRegisteredFedbackMessageType());
    }

    public SliControlFrame(Sli[] sliArr) {
        super(getRegisteredFedbackMessageType());
    }
}
