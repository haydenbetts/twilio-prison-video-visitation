package fm.liveswitch;

class DatamessageheaderConnectionIdElement extends DatamessageheaderElement {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(DatamessageheaderConnectionIdElement.class);
    private byte[] __connectionId;

    public DatamessageheaderConnectionIdElement(String str) {
        super.setType(DatamessageheaderType.getConnectionId());
        setConnectionId(str);
    }

    static DatamessageheaderElement doParseBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(dataBuffer.read16(i + 1));
        return new DatamessageheaderConnectionIdElement(dataBuffer.readUtf8String(i + 3, integerHolder.getValue() - 3));
    }

    public DataBuffer getBytes() {
        DataBuffer take = __dataBufferPool.take(getLength());
        take.write8(super.getType(), 0);
        take.write16(getLength(), 1);
        take.write(DataBuffer.wrap(this.__connectionId), 3);
        return take;
    }

    public String getConnectionId() {
        if (this.__connectionId != null) {
            return Encoding.getUtf8().getString(this.__connectionId);
        }
        return null;
    }

    public int getLength() {
        byte[] bArr = this.__connectionId;
        if (bArr != null) {
            return 3 + ArrayExtensions.getLength(bArr);
        }
        return 3;
    }

    private void setConnectionId(String str) {
        this.__connectionId = Utf8.encode(str);
    }
}
