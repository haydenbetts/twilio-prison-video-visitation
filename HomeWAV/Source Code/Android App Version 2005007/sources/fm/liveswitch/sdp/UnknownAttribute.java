package fm.liveswitch.sdp;

public class UnknownAttribute extends Attribute {
    private String _name;
    private String _value;

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getValue();
    }

    public String getName() {
        return this._name;
    }

    public String getValue() {
        return this._value;
    }

    private void setName(String str) {
        this._name = str;
    }

    private void setValue(String str) {
        this._value = str;
    }

    public UnknownAttribute(String str, String str2) {
        setName(str);
        setValue(str2);
        super.setAttributeType(AttributeType.UnknownAttribute);
    }
}
