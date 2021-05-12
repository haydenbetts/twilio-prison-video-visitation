package fm.liveswitch;

abstract class DatamessageheaderElement {
    private int _type;

    public abstract DataBuffer getBytes();

    public abstract int getLength();

    protected DatamessageheaderElement() {
    }

    public int getType() {
        return this._type;
    }

    public static DatamessageheaderElement parseBytes(DataBuffer dataBuffer, int i, IntegerHolder integerHolder) {
        int read8 = dataBuffer.read8(i);
        if (read8 == DatamessageheaderType.getTimeToLive()) {
            return DatamessageheaderTimeToLiveElement.doParseBytes(dataBuffer, i, integerHolder);
        }
        if (read8 == DatamessageheaderType.getDeliveryAttempts()) {
            return DatamessageheaderDeliveryAttemptsElement.doParseBytes(dataBuffer, i, integerHolder);
        }
        if (read8 == DatamessageheaderType.getConnectionId()) {
            return DatamessageheaderConnectionIdElement.doParseBytes(dataBuffer, i, integerHolder);
        }
        return DatamessageheaderUnknownElement.doParseBytes(dataBuffer, i, integerHolder);
    }

    /* access modifiers changed from: protected */
    public void setType(int i) {
        this._type = i;
    }
}
