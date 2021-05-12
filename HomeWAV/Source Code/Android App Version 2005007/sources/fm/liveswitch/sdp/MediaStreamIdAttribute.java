package fm.liveswitch.sdp;

public class MediaStreamIdAttribute extends Attribute {
    private String _identificationTag;

    public static MediaStreamIdAttribute fromAttributeValue(String str) {
        return new MediaStreamIdAttribute(str);
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return getIdentificationTag();
    }

    public String getIdentificationTag() {
        return this._identificationTag;
    }

    public MediaStreamIdAttribute(String str) {
        super.setAttributeType(AttributeType.MediaStreamIdAttribute);
        setIdentificationTag(str);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    private void setIdentificationTag(String str) {
        this._identificationTag = str;
    }
}
