package fm.liveswitch.sdp;

public class CharacterSetAttribute extends Attribute {
    private String _characterSet;

    public CharacterSetAttribute(String str) {
        if (str != null) {
            super.setAttributeType(AttributeType.CharacterSetAttribute);
            setCharacterSet(str);
            super.setMultiplexingCategory(AttributeCategory.Normal);
            return;
        }
        throw new RuntimeException(new Exception("characterSet cannot be null."));
    }

    public static CharacterSetAttribute fromAttributeValue(String str) {
        return new CharacterSetAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getCharacterSet();
    }

    public String getCharacterSet() {
        return this._characterSet;
    }

    private void setCharacterSet(String str) {
        this._characterSet = str;
    }
}
