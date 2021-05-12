package fm.liveswitch.sdp;

public class SdpLanguageAttribute extends Attribute {
    private String _languageTag;

    public static SdpLanguageAttribute fromAttributeValue(String str) {
        return new SdpLanguageAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getLanguageTag();
    }

    public String getLanguageTag() {
        return this._languageTag;
    }

    public SdpLanguageAttribute(String str) {
        super.setAttributeType(AttributeType.SdpLanguageAttribute);
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
