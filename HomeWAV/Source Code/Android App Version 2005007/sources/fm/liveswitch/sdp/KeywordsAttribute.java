package fm.liveswitch.sdp;

public class KeywordsAttribute extends Attribute {
    private String _keywords;

    public static KeywordsAttribute fromAttributeValue(String str) {
        return new KeywordsAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getKeywords();
    }

    public String getKeywords() {
        return this._keywords;
    }

    public KeywordsAttribute(String str) {
        super.setAttributeType(AttributeType.KeywordsAttribute);
        if (str != null) {
            setKeywords(str);
            super.setMultiplexingCategory(AttributeCategory.Normal);
            return;
        }
        throw new RuntimeException(new Exception("keywords cannot be null."));
    }

    private void setKeywords(String str) {
        this._keywords = str;
    }
}
