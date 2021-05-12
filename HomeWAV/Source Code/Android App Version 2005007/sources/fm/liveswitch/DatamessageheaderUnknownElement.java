package fm.liveswitch;

class DatamessageheaderUnknownElement extends DatamessageheaderElement {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(DatamessageheaderUnknownElement.class);
    private int __length;
    private DataBuffer __payload;

    public DatamessageheaderUnknownElement(byte b, DataBuffer dataBuffer) {
        super.setType(b);
        setPayload(dataBuffer);
    }

    static DatamessageheaderElement doParseBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        int read8 = dataBuffer.read8(i);
        integerHolder.setValue(dataBuffer.read16(i + 1));
        return new DatamessageheaderUnknownElement((byte) read8, dataBuffer.subset(i + 3, integerHolder.getValue() - 3));
    }

    public DataBuffer getBytes() {
        DataBuffer take = __dataBufferPool.take(getLength());
        take.write8(super.getType(), 0);
        take.write16(getLength(), 1);
        take.write(getPayload(), 3);
        return take;
    }

    public int getLength() {
        return this.__length;
    }

    /* access modifiers changed from: package-private */
    public DataBuffer getPayload() {
        return this.__payload;
    }

    /* access modifiers changed from: package-private */
    public void setPayload(DataBuffer dataBuffer) {
        this.__payload = dataBuffer;
        this.__length = dataBuffer.getLength() + 3;
    }
}
