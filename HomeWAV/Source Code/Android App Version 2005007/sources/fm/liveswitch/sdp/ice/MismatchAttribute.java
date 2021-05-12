package fm.liveswitch.sdp.ice;

import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class MismatchAttribute extends Attribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public static MismatchAttribute fromAttributeValue(String str) {
        return new MismatchAttribute();
    }

    public MismatchAttribute() {
        super.setAttributeType(AttributeType.IceMismatchAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }
}
