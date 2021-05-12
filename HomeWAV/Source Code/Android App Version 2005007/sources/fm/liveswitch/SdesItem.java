package fm.liveswitch;

public class SdesItem {
    private DataBuffer _dataBuffer;

    public static int getFixedPayloadHeaderLength() {
        return 2;
    }

    public DataBuffer getDataBuffer() {
        return this._dataBuffer;
    }

    public int getLength() {
        return getDataBuffer().read8(1);
    }

    public String getText() {
        return Utf8.decode(getDataBuffer().subset(2, getLength()).toArray());
    }

    public int getType() {
        return getDataBuffer().read8(0);
    }

    public SdesItem(DataBuffer dataBuffer) {
        setDataBuffer(dataBuffer);
    }

    public SdesItem(int i, String str) {
        if (str != null) {
            byte[] encode = Utf8.encode(str);
            int length = ArrayExtensions.getLength(encode);
            if (length <= 255) {
                setDataBuffer(DataBuffer.allocate(length + 2));
                getDataBuffer().write8(i, 0);
                getDataBuffer().write8(length, 1);
                getDataBuffer().writeBytes(encode, 2);
                return;
            }
            throw new RuntimeException(new Exception("Cannot serialize an RTCP SDES chunk item with more than 255 bytes of text. Text string too long."));
        }
        throw new RuntimeException(new Exception("SDES item text cannot be null."));
    }

    private void setDataBuffer(DataBuffer dataBuffer) {
        this._dataBuffer = dataBuffer;
    }
}
