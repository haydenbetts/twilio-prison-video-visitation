package fm.liveswitch;

public class NullableLong {
    boolean _hasValue;
    long _value;

    public static NullableLong nullValue() {
        return new NullableLong((Long) null);
    }

    public NullableLong() {
    }

    public NullableLong(long j) {
        this._hasValue = true;
        this._value = j;
    }

    public NullableLong(Long l) {
        if (l != null) {
            this._hasValue = true;
            this._value = l.longValue();
        }
    }

    public boolean hasValue() {
        return getHasValue();
    }

    public boolean getHasValue() {
        return this._hasValue;
    }

    public long getValue() {
        return this._value;
    }

    public void setValue(long j) {
        this._value = j;
    }

    public long getValueOrDefault() {
        if (this._hasValue) {
            return this._value;
        }
        return 0;
    }

    public String toString() {
        return new Long(getValueOrDefault()).toString();
    }
}
