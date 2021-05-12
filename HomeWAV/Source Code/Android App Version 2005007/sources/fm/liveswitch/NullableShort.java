package fm.liveswitch;

public class NullableShort {
    boolean _hasValue;
    short _value;

    public static NullableShort nullValue() {
        return new NullableShort((Short) null);
    }

    public NullableShort() {
    }

    public NullableShort(short s) {
        this._hasValue = true;
        this._value = s;
    }

    public NullableShort(Short sh) {
        if (sh != null) {
            this._hasValue = true;
            this._value = sh.shortValue();
        }
    }

    public boolean hasValue() {
        return getHasValue();
    }

    public boolean getHasValue() {
        return this._hasValue;
    }

    public short getValue() {
        return this._value;
    }

    public void setValue(short s) {
        this._value = s;
    }

    public short getValueOrDefault() {
        if (this._hasValue) {
            return this._value;
        }
        return 0;
    }

    public String toString() {
        return new Short(getValueOrDefault()).toString();
    }
}
