package fm.liveswitch;

public class FloatHolder {
    private float _value;

    public FloatHolder() {
    }

    public FloatHolder(float f) {
        setValue(f);
    }

    public float getValue() {
        return this._value;
    }

    public void setValue(float f) {
        this._value = f;
    }
}
