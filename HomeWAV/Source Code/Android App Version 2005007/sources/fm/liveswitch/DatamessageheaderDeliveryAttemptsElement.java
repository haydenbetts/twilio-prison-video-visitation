package fm.liveswitch;

class DatamessageheaderDeliveryAttemptsElement extends DatamessageheaderElement {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(DatamessageheaderDeliveryAttemptsElement.class);
    private int _deliveryAttempts;

    public int getLength() {
        return 5;
    }

    public DatamessageheaderDeliveryAttemptsElement(int i) {
        super.setType(DatamessageheaderType.getDeliveryAttempts());
        setDeliveryAttempts(i);
    }

    static DatamessageheaderElement doParseBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(5);
        return new DatamessageheaderDeliveryAttemptsElement(dataBuffer.read16(i + 3));
    }

    public DataBuffer getBytes() {
        DataBuffer take = __dataBufferPool.take(getLength());
        take.write8(super.getType(), 0);
        take.write16(getLength(), 1);
        take.write16(getDeliveryAttempts(), 3);
        return take;
    }

    public int getDeliveryAttempts() {
        return this._deliveryAttempts;
    }

    private void setDeliveryAttempts(int i) {
        this._deliveryAttempts = i;
    }
}
