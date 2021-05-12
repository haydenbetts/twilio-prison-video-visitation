package fm.liveswitch.sdp.rtcp;

import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class MuxAttribute extends Attribute {
    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return null;
    }

    public static MuxAttribute fromAttributeValue(String str) {
        return new MuxAttribute();
    }

    public MuxAttribute() {
        super.setAttributeType(AttributeType.RtcpMuxAttribute);
        super.setMultiplexingCategory(AttributeCategory.Identical);
    }
}
