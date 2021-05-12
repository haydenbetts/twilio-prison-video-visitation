package fm.liveswitch;

class FecRawPacket {
    private byte[] _data;
    private int _length;

    public FecRawPacket() {
        setLength(0);
        setData(new byte[FecContext.getIPPacketSize()]);
    }

    public byte[] getData() {
        return this._data;
    }

    public int getLength() {
        return this._length;
    }

    private void setData(byte[] bArr) {
        this._data = bArr;
    }

    public void setLength(int i) {
        this._length = i;
    }
}
