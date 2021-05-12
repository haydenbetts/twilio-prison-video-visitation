package fm.liveswitch;

public class ShortHolder {
    private short _value;

    public short getValue() {
        return this._value;
    }

    public void setValue(short s) {
        this._value = s;
    }

    public ShortHolder() {
    }

    public ShortHolder(short s) {
        setValue(s);
    }
}
