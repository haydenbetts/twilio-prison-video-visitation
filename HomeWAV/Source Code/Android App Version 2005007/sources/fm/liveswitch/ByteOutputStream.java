package fm.liveswitch;

public class ByteOutputStream {
    private ByteCollection _bytes = new ByteCollection();

    public int getSize() {
        return this._bytes.getCount();
    }

    public void reset() {
        this._bytes = new ByteCollection();
    }

    public byte[] toArray() {
        return this._bytes.toArray();
    }

    public void write(byte b) {
        this._bytes.add(b);
    }

    public void writeBuffer(byte[] bArr) {
        this._bytes.addRange(bArr);
    }

    public void writeBuffer(byte[] bArr, int i, int i2) {
        this._bytes.addRange(BitAssistant.subArray(bArr, i, i2));
    }

    public void writeTo(ByteOutputStream byteOutputStream) {
        byteOutputStream.writeBuffer(this._bytes.toArray());
    }
}
