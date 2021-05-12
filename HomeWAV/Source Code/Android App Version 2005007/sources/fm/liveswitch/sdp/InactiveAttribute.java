package fm.liveswitch.sdp;

import fm.liveswitch.StreamDirection;

public class InactiveAttribute extends DirectionAttribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public static InactiveAttribute fromAttributeValue(String str) {
        return new InactiveAttribute();
    }

    public StreamDirection getStreamDirection() {
        return StreamDirection.Inactive;
    }

    public InactiveAttribute() {
        super.setAttributeType(AttributeType.DirectionAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }
}
