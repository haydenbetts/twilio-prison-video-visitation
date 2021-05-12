package fm.liveswitch;

class DatamessageheaderTimeToLiveElement extends DatamessageheaderElement {
    private static IDataBufferPool __dataBufferPool = DataBufferPool.getTracer(DatamessageheaderTimeToLiveElement.class);
    private long _timeToLive;

    public int getLength() {
        return 7;
    }

    public DatamessageheaderTimeToLiveElement(long j) {
        super.setType(DatamessageheaderType.getTimeToLive());
        setTimeToLive(j);
    }

    static DatamessageheaderElement doParseBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        integerHolder.setValue(7);
        return new DatamessageheaderTimeToLiveElement(dataBuffer.read32(i + 3));
    }

    public DataBuffer getBytes() {
        DataBuffer take = __dataBufferPool.take(getLength());
        take.write8(super.getType(), 0);
        take.write16(getLength(), 1);
        take.write32(getTimeToLive(), 3);
        return take;
    }

    public long getTimeToLive() {
        return this._timeToLive;
    }

    private void setTimeToLive(long j) {
        this._timeToLive = j;
    }
}
