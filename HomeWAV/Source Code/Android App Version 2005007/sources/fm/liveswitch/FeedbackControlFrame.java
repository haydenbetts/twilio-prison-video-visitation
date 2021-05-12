package fm.liveswitch;

public abstract class FeedbackControlFrame extends MediaControlFrame {
    public static int getFixedPayloadHeaderLength() {
        return 8;
    }

    public FeedbackControlFrame(int i, int i2) {
        this(i, i2, DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength()));
        super.setPayloadLengthWithPadding(getFixedPayloadHeaderLength());
    }

    public FeedbackControlFrame(int i, int i2, DataBuffer dataBuffer) {
        super(dataBuffer);
        if (dataBuffer.getLength() >= MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength()) {
            setFeedbackMessageType(i);
            super.setPayloadType(i2);
            return;
        }
        throw new RuntimeException(new Exception(StringExtensions.concat("DataBuffer must be at least ", IntegerExtensions.toString(Integer.valueOf(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength())), " bytes.")));
    }

    public FeedbackControlFrame(int i, int i2, long j, long j2) {
        this(i, i2, DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength()));
        setPacketSenderSynchronizationSource(j);
        setMediaSourceSynchronizationSource(j2);
        super.setPayloadLengthWithPadding(getFixedPayloadHeaderLength());
    }

    public FeedbackControlFrame(int i, int i2, long j, long j2, DataBuffer dataBuffer) {
        this(i, i2, DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength() + dataBuffer.getLength()));
        setPacketSenderSynchronizationSource(j);
        setMediaSourceSynchronizationSource(j2);
        setFeedbackControlInformation(dataBuffer);
        super.setPayloadLengthWithPadding(getFixedPayloadHeaderLength() + dataBuffer.getLength());
    }

    public DataBuffer getFeedbackControlInformation() {
        return super.getDataBuffer().subset(getFeedbackControlInformationOffset(), super.getPayload().getLength() - getFixedPayloadHeaderLength());
    }

    public int getFeedbackControlInformationOffset() {
        return MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength();
    }

    public int getFeedbackMessageType() {
        return super.getByte1Last5Bits();
    }

    public long getMediaSourceSynchronizationSource() {
        return super.getDataBuffer().read32(MediaControlFrame.getFixedHeaderLength() + 4);
    }

    public long getPacketSenderSynchronizationSource() {
        return super.getDataBuffer().read32(MediaControlFrame.getFixedHeaderLength());
    }

    private void setFeedbackControlInformation(DataBuffer dataBuffer) {
        super.getDataBuffer().write(dataBuffer, getFeedbackControlInformationOffset());
    }

    public void setFeedbackMessageType(int i) {
        super.setByte1Last5Bits(i);
    }

    public void setMediaSourceSynchronizationSource(long j) {
        super.getDataBuffer().write32(j, MediaControlFrame.getFixedHeaderLength() + 4);
    }

    public void setPacketSenderSynchronizationSource(long j) {
        super.getDataBuffer().write32(j, MediaControlFrame.getFixedHeaderLength());
    }
}
