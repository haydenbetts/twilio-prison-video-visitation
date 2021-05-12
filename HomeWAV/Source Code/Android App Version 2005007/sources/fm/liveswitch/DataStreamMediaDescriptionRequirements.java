package fm.liveswitch;

class DataStreamMediaDescriptionRequirements extends MediaDescriptionRequirements {
    private long _maxMessageSize;
    private int _sctpPort;

    public long getMaxMessageSize() {
        return this._maxMessageSize;
    }

    public int getSctpPort() {
        return this._sctpPort;
    }

    public void setMaxMessageSize(long j) {
        this._maxMessageSize = j;
    }

    public void setSctpPort(int i) {
        this._sctpPort = i;
    }
}
