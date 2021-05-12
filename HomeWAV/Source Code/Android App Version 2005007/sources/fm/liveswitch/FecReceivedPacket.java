package fm.liveswitch;

class FecReceivedPacket extends FecSortablePacket {
    private boolean _isFec;
    private FecRawPacket _raw;
    private long _synchronizationSource;

    public boolean getIsFec() {
        return this._isFec;
    }

    public FecRawPacket getRaw() {
        return this._raw;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public void setIsFec(boolean z) {
        this._isFec = z;
    }

    public void setRaw(FecRawPacket fecRawPacket) {
        this._raw = fecRawPacket;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }
}
