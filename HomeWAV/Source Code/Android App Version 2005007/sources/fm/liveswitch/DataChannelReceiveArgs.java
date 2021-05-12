package fm.liveswitch;

public class DataChannelReceiveArgs {
    private Object __remoteConnectionInfo;
    private DataBuffer _dataBytes;
    private DataMessage _dataMessage;
    private String _dataString;

    public DataBuffer getDataBytes() {
        return this._dataBytes;
    }

    /* access modifiers changed from: package-private */
    public DataMessage getDataMessage() {
        return this._dataMessage;
    }

    public String getDataString() {
        return this._dataString;
    }

    public ConnectionInfo getRemoteConnectionInfo() {
        return (ConnectionInfo) this.__remoteConnectionInfo;
    }

    public void setDataBytes(DataBuffer dataBuffer) {
        this._dataBytes = dataBuffer;
    }

    /* access modifiers changed from: package-private */
    public void setDataMessage(DataMessage dataMessage) {
        this._dataMessage = dataMessage;
    }

    public void setDataString(String str) {
        this._dataString = str;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteConnectionInfo(Object obj) {
        this.__remoteConnectionInfo = obj;
    }
}
