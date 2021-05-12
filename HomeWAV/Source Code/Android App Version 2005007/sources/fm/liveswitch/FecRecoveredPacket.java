package fm.liveswitch;

class FecRecoveredPacket extends FecSortablePacket {
    private byte[] _lengthRecovery;
    private FecRawPacket _raw;
    private boolean _returned;
    private boolean _wasRecovered;

    public FecRecoveredPacket() {
        setLengthRecovery(new byte[2]);
    }

    public byte[] getLengthRecovery() {
        return this._lengthRecovery;
    }

    public FecRawPacket getRaw() {
        return this._raw;
    }

    public boolean getReturned() {
        return this._returned;
    }

    public boolean getWasRecovered() {
        return this._wasRecovered;
    }

    public void setLengthRecovery(byte[] bArr) {
        this._lengthRecovery = bArr;
    }

    public void setRaw(FecRawPacket fecRawPacket) {
        this._raw = fecRawPacket;
    }

    public void setReturned(boolean z) {
        this._returned = z;
    }

    public void setWasRecovered(boolean z) {
        this._wasRecovered = z;
    }
}
