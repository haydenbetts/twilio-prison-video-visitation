package fm.liveswitch;

public class NullableInteger {
    boolean _hasValue;
    int _value;

    public static NullableInteger nullValue() {
        return new NullableInteger((Integer) null);
    }

    public NullableInteger() {
    }

    public NullableInteger(int i) {
        this._hasValue = true;
        this._value = i;
    }

    public NullableInteger(Integer num) {
        if (num != null) {
            this._hasValue = true;
            this._value = num.intValue();
        }
    }

    public boolean hasValue() {
        return getHasValue();
    }

    public boolean getHasValue() {
        return this._hasValue;
    }

    public int getValue() {
        return this._value;
    }

    public void setValue(int i) {
        this._value = i;
    }

    public int getValueOrDefault() {
        if (this._hasValue) {
            return this._value;
        }
        return 0;
    }

    public String toString() {
        return new Integer(getValueOrDefault()).toString();
    }
}
