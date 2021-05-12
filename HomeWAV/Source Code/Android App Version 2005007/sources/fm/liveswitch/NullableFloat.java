package fm.liveswitch;

public class NullableFloat {
    boolean _hasValue;
    float _value;

    public static NullableFloat nullValue() {
        return new NullableFloat((Float) null);
    }

    public NullableFloat() {
    }

    public NullableFloat(float f) {
        this._hasValue = true;
        this._value = f;
    }

    public NullableFloat(Float f) {
        if (f != null) {
            this._hasValue = true;
            this._value = f.floatValue();
        }
    }

    public boolean hasValue() {
        return getHasValue();
    }

    public boolean getHasValue() {
        return this._hasValue;
    }

    public float getValue() {
        return this._value;
    }

    public void setValue(float f) {
        this._value = f;
    }

    public float getValueOrDefault() {
        if (this._hasValue) {
            return this._value;
        }
        return 0.0f;
    }

    public String toString() {
        return new Float(getValueOrDefault()).toString();
    }
}
