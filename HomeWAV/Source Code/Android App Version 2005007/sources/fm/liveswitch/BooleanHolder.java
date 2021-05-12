package fm.liveswitch;

public class BooleanHolder {
    private boolean _value;

    public BooleanHolder() {
    }

    public BooleanHolder(boolean z) {
        setValue(z);
    }

    public boolean getValue() {
        return this._value;
    }

    public void setValue(boolean z) {
        this._value = z;
    }
}
