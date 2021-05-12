package fm.liveswitch;

public class CharacterHolder {
    private char _value;

    public CharacterHolder() {
    }

    public CharacterHolder(char c) {
        setValue(c);
    }

    public char getValue() {
        return this._value;
    }

    public void setValue(char c) {
        this._value = c;
    }
}
