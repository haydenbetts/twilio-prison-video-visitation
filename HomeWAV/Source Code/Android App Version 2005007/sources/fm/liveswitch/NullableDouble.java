package fm.liveswitch;

public class NullableDouble {
    boolean _hasValue;
    double _value;

    public static NullableDouble nullValue() {
        return new NullableDouble((Double) null);
    }

    public NullableDouble() {
    }

    public NullableDouble(double d) {
        this._hasValue = true;
        this._value = d;
    }

    public NullableDouble(Double d) {
        if (d != null) {
            this._hasValue = true;
            this._value = d.doubleValue();
        }
    }

    public boolean hasValue() {
        return getHasValue();
    }

    public boolean getHasValue() {
        return this._hasValue;
    }

    public double getValue() {
        return this._value;
    }

    public void setValue(double d) {
        this._value = d;
    }

    public double getValueOrDefault() {
        if (this._hasValue) {
            return this._value;
        }
        return 0.0d;
    }

    public String toString() {
        return new Double(getValueOrDefault()).toString();
    }
}
