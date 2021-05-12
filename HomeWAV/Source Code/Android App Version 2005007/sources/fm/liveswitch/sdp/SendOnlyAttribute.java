package fm.liveswitch.sdp;

import fm.liveswitch.StreamDirection;

public class SendOnlyAttribute extends DirectionAttribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public static SendOnlyAttribute fromAttributeValue(String str) {
        return new SendOnlyAttribute();
    }

    public StreamDirection getStreamDirection() {
        return StreamDirection.SendOnly;
    }

    public SendOnlyAttribute() {
        super.setAttributeType(AttributeType.DirectionAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }
}
