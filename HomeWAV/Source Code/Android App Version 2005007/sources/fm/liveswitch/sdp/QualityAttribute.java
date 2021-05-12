package fm.liveswitch.sdp;

import fm.liveswitch.ByteExtensions;
import fm.liveswitch.ParseAssistant;

public class QualityAttribute extends Attribute {
    private byte _quality;

    public static QualityAttribute fromAttributeValue(String str) {
        QualityAttribute qualityAttribute = new QualityAttribute();
        qualityAttribute.setQuality(ParseAssistant.parseByteValue(str));
        return qualityAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return ByteExtensions.toString(Byte.valueOf(getQuality()));
    }

    public byte getQuality() {
        return this._quality;
    }

    private QualityAttribute() {
        super.setAttributeType(AttributeType.QualityAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    public QualityAttribute(byte b) {
        this();
        if (b < 0 || b > 10) {
            throw new RuntimeException(new Exception("quality must be a value in the range 0 to 10."));
        }
        setQuality(b);
    }

    private void setQuality(byte b) {
        this._quality = b;
    }
}
