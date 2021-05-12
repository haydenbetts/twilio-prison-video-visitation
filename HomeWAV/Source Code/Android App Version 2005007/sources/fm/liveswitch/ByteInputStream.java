package fm.liveswitch;

public class ByteInputStream {
    private byte[] _bytes;
    private int _markedPosition;
    private int _position;

    public ByteInputStream(byte[] bArr) {
        this(bArr, 0);
    }

    public ByteInputStream(byte[] bArr, int i) {
        this._position = 0;
        this._markedPosition = 0;
        this._bytes = bArr;
        this._position = i;
        this._markedPosition = i;
    }

    public int getAvailable() {
        return ArrayExtensions.getLength(this._bytes) - this._position;
    }

    public void mark() {
        this._markedPosition = this._position;
    }

    public int read() {
        if (getAvailable() < 1) {
            return -1;
        }
        byte[] bArr = this._bytes;
        int i = this._position;
        this._position = i + 1;
        return BitAssistant.castInteger(bArr[i]);
    }

    public int read(byte[] bArr, int i, int i2) {
        if (i2 >= getAvailable()) {
            i2 = getAvailable();
        }
        BitAssistant.copy(this._bytes, this._position, bArr, i, i2);
        this._position += i2;
        return i2;
    }

    public void reset() {
        this._position = this._markedPosition;
    }

    public int skip(int i) {
        int min = MathAssistant.min(ArrayExtensions.getLength(this._bytes), this._position + i);
        int i2 = min - this._position;
        this._position = min;
        return i2;
    }
}
