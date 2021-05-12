package fm.liveswitch;

public class ByteHolder {
    private byte _value;

    public ByteHolder() {
    }

    public ByteHolder(byte b) {
        setValue(b);
    }

    public byte getValue() {
        return this._value;
    }

    public void setValue(byte b) {
        this._value = b;
    }
}
