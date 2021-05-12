package fm.liveswitch;

public class DoubleHolder {
    private double _value;

    public DoubleHolder() {
    }

    public DoubleHolder(double d) {
        setValue(d);
    }

    public double getValue() {
        return this._value;
    }

    public void setValue(double d) {
        this._value = d;
    }
}
