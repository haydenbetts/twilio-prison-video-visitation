package fm.liveswitch;

public class NullableBoolean {
    boolean _hasValue;
    boolean _value;

    public static NullableBoolean nullValue() {
        return new NullableBoolean((Boolean) null);
    }

    public static NullableBoolean trueValue() {
        return new NullableBoolean(true);
    }

    public static NullableBoolean falseValue() {
        return new NullableBoolean(false);
    }

    public NullableBoolean() {
    }

    public NullableBoolean(boolean z) {
        this._hasValue = true;
        this._value = z;
    }

    public NullableBoolean(Boolean bool) {
        if (bool != null) {
            this._hasValue = true;
            this._value = bool.booleanValue();
        }
    }

    public boolean hasValue() {
        return getHasValue();
    }

    public boolean getHasValue() {
        return this._hasValue;
    }

    public boolean getValue() {
        return this._value;
    }

    public void setValue(boolean z) {
        this._value = z;
    }

    public boolean getValueOrDefault() {
        if (this._hasValue) {
            return this._value;
        }
        return false;
    }

    public String toString() {
        return getValueOrDefault() ? "True" : "False";
    }
}
