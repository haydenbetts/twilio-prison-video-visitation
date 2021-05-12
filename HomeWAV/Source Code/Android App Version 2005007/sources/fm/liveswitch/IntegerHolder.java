package fm.liveswitch;

public class IntegerHolder {
    private int _value;

    public int getValue() {
        return this._value;
    }

    public IntegerHolder() {
    }

    public IntegerHolder(int i) {
        setValue(i);
    }

    public void setValue(int i) {
        this._value = i;
    }
}
