package fm.liveswitch;

public class AppControlFrame extends MediaControlFrame {
    public static int getFixedPayloadHeaderLength() {
        return 8;
    }

    public static int getRegisteredPayloadType() {
        return 204;
    }

    public AppControlFrame() {
        this(DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength()));
        super.setPayloadLengthWithPadding(getFixedPayloadHeaderLength());
    }

    public AppControlFrame(DataBuffer dataBuffer) {
        super(dataBuffer);
        super.setPayloadType(getRegisteredPayloadType());
    }

    public AppControlFrame(int i, long j, String str, DataBuffer dataBuffer) {
        this(DataBuffer.allocate(MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength() + dataBuffer.getLength()));
        super.setByte1Last5Bits(i);
        setSynchronizationSource(j);
        setName(str);
        setData(dataBuffer);
        super.setPayloadLengthWithPadding(getFixedPayloadHeaderLength() + dataBuffer.getLength());
    }

    public DataBuffer getData() {
        return super.getDataBuffer().subset(getDataPayloadOffset(), super.getPayload().getLength() - getFixedPayloadHeaderLength());
    }

    public int getDataLength() {
        return getData().getLength();
    }

    public int getDataPayloadOffset() {
        return MediaControlFrame.getFixedHeaderLength() + getFixedPayloadHeaderLength();
    }

    public String getName() {
        return Ascii.decode(super.getDataBuffer().subset(8, 4).toArray());
    }

    public int getSubType() {
        return super.getByte1Last5Bits();
    }

    public long getSynchronizationSource() {
        return super.getDataBuffer().read32(4);
    }

    private void setData(DataBuffer dataBuffer) {
        super.getDataBuffer().write(dataBuffer, getDataPayloadOffset());
    }

    public void setName(String str) {
        super.getDataBuffer().write32(0, 8);
        if (str != null) {
            byte[] encode = Ascii.encode(str);
            super.getDataBuffer().write(DataBuffer.wrap(encode, 0, MathAssistant.min(ArrayExtensions.getLength(encode), 4)), 8);
        }
    }

    public void setSubType(int i) {
        super.setByte1Last5Bits(i);
    }

    public void setSynchronizationSource(long j) {
        super.getDataBuffer().write32(j, 4);
    }
}
