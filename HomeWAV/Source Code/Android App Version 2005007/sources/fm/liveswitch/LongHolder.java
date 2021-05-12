package fm.liveswitch;

public class LongHolder {
    private long _value;

    public long getValue() {
        return this._value;
    }

    public LongHolder() {
    }

    public LongHolder(long j) {
        setValue(j);
    }

    public void setValue(long j) {
        this._value = j;
    }
}
