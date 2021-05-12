package fm.liveswitch;

class FecProtectedPacket extends FecSortablePacket {
    private FecRawPacket _raw;

    public FecRawPacket getRaw() {
        return this._raw;
    }

    public void setRaw(FecRawPacket fecRawPacket) {
        this._raw = fecRawPacket;
    }
}
