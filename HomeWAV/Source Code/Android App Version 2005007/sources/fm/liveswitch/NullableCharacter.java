package fm.liveswitch;

public class NullableCharacter {
    boolean _hasValue;
    char _value;

    public static NullableCharacter nullValue() {
        return new NullableCharacter((Character) null);
    }

    public NullableCharacter() {
    }

    public NullableCharacter(char c) {
        this._hasValue = true;
        this._value = c;
    }

    public NullableCharacter(Character ch) {
        if (ch != null) {
            this._hasValue = true;
            this._value = ch.charValue();
        }
    }

    public boolean hasValue() {
        return getHasValue();
    }

    public boolean getHasValue() {
        return this._hasValue;
    }

    public char getValue() {
        return this._value;
    }

    public void setValue(char c) {
        this._value = c;
    }

    public char getValueOrDefault() {
        if (this._hasValue) {
            return this._value;
        }
        return 0;
    }

    public String toString() {
        return Character.toString(getValueOrDefault());
    }
}
