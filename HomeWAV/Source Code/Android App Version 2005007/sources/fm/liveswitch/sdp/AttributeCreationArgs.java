package fm.liveswitch.sdp;

class AttributeCreationArgs {
    private String _value;

    public AttributeCreationArgs(String str) {
        setValue(str != null ? str.trim() : str);
    }

    public String getValue() {
        return this._value;
    }

    private void setValue(String str) {
        this._value = str;
    }
}
