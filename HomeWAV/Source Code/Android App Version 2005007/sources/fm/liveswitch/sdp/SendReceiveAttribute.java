package fm.liveswitch.sdp;

import fm.liveswitch.StreamDirection;

public class SendReceiveAttribute extends DirectionAttribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public static SendReceiveAttribute fromAttributeValue(String str) {
        return new SendReceiveAttribute();
    }

    public StreamDirection getStreamDirection() {
        return StreamDirection.SendReceive;
    }

    public SendReceiveAttribute() {
        super.setAttributeType(AttributeType.DirectionAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }
}
