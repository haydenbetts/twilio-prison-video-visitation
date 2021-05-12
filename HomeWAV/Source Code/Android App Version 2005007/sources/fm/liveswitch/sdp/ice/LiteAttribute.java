package fm.liveswitch.sdp.ice;

import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class LiteAttribute extends Attribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public static LiteAttribute fromAttributeValue(String str) {
        return new LiteAttribute();
    }

    public LiteAttribute() {
        super.setAttributeType(AttributeType.IceLiteAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }
}
