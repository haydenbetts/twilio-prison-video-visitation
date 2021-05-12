package fm.liveswitch;

public class GenericNackControlFrame extends RtpControlFrame {
    private GenericNack[] __genericNacks;

    public static int getRegisteredFeedbackMessageType() {
        return 1;
    }

    public GenericNackControlFrame() {
        super(getRegisteredFeedbackMessageType());
    }

    public GenericNackControlFrame(DataBuffer dataBuffer) {
        super(getRegisteredFeedbackMessageType(), dataBuffer);
        if (dataBuffer.getLength() >= MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength()) {
            DataBuffer feedbackControlInformation = super.getFeedbackControlInformation();
            if (feedbackControlInformation != null) {
                int feedbackControlInformationOffset = super.getFeedbackControlInformationOffset();
                int length = feedbackControlInformation.getLength() / GenericNack.getFixedPayloadLength();
                GenericNack[] genericNackArr = new GenericNack[length];
                for (int i = 0; i < length; i++) {
                    genericNackArr[i] = new GenericNack(super.getDataBuffer().subset(feedbackControlInformationOffset, GenericNack.getFixedPayloadLength()));
                    feedbackControlInformationOffset += GenericNack.getFixedPayloadLength();
                }
                setGenericNacks(genericNackArr);
                return;
            }
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.concat("DataBuffer must be at least ", IntegerExtensions.toString(Integer.valueOf(MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength())), " bytes for a GenericNackControlFrame.")));
    }

    public GenericNackControlFrame(GenericNack genericNack) {
        this(DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength() + GenericNack.getFixedPayloadLength()));
        setGenericNack(genericNack);
        super.setPayloadLengthWithPadding(FeedbackControlFrame.getFixedPayloadHeaderLength() + GenericNack.getFixedPayloadLength());
    }

    public GenericNackControlFrame(GenericNack[] genericNackArr) {
        this(DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength() + (ArrayExtensions.getLength((Object[]) genericNackArr) * GenericNack.getFixedPayloadLength())));
        setGenericNacks(genericNackArr);
        super.setPayloadLengthWithPadding(FeedbackControlFrame.getFixedPayloadHeaderLength() + (ArrayExtensions.getLength((Object[]) genericNackArr) * GenericNack.getFixedPayloadLength()));
    }

    public GenericNack getGenericNack() {
        return (GenericNack) Utility.firstOrDefault((T[]) getGenericNacks());
    }

    public GenericNack[] getGenericNacks() {
        return this.__genericNacks;
    }

    private void setGenericNack(GenericNack genericNack) {
        GenericNack[] genericNackArr;
        if (genericNack == null) {
            genericNackArr = null;
        } else {
            genericNackArr = new GenericNack[]{genericNack};
        }
        setGenericNacks(genericNackArr);
    }

    private void setGenericNacks(GenericNack[] genericNackArr) {
        if (genericNackArr == null) {
            this.__genericNacks = null;
            return;
        }
        this.__genericNacks = new GenericNack[ArrayExtensions.getLength((Object[]) genericNackArr)];
        int fixedHeaderLength = MediaControlFrame.getFixedHeaderLength() + FeedbackControlFrame.getFixedPayloadHeaderLength();
        int fixedPayloadLength = GenericNack.getFixedPayloadLength();
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) genericNackArr); i++) {
            DataBuffer subset = super.getDataBuffer().subset(fixedHeaderLength, fixedPayloadLength);
            this.__genericNacks[i] = new GenericNack(subset);
            subset.write(genericNackArr[i].getDataBuffer(), 0);
            fixedHeaderLength += fixedPayloadLength;
        }
    }
}
