package fm.liveswitch.sdp;

public class LanguageAttribute extends Attribute {
    private String _languageTag;

    public static LanguageAttribute fromAttributeValue(String str) {
        return new LanguageAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getLanguageTag();
    }

    public String getLanguageTag() {
        return this._languageTag;
    }

    public LanguageAttribute(String str) {
        super.setAttributeType(AttributeType.LanguageAttribute);
        if (str != null) {
            setLanguageTag(str);
            super.setMultiplexingCategory(AttributeCategory.Normal);
            return;
        }
        throw new RuntimeException(new Exception("languageTag cannot be null."));
    }

    private void setLanguageTag(String str) {
        this._languageTag = str;
    }
}
