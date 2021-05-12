package fm.liveswitch.sdp;

import fm.liveswitch.StreamDirection;

public class ReceiveOnlyAttribute extends DirectionAttribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public static ReceiveOnlyAttribute fromAttributeValue(String str) {
        return new ReceiveOnlyAttribute();
    }

    public StreamDirection getStreamDirection() {
        return StreamDirection.ReceiveOnly;
    }

    public ReceiveOnlyAttribute() {
        super.setAttributeType(AttributeType.DirectionAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }
}
