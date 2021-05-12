package fm.liveswitch.sdp;

import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;

public class MaxPacketTimeAttribute extends Attribute {
    private int _maxPacketTime;

    public static MaxPacketTimeAttribute fromAttributeValue(String str) {
        MaxPacketTimeAttribute maxPacketTimeAttribute = new MaxPacketTimeAttribute();
        maxPacketTimeAttribute.setMaxPacketTime(ParseAssistant.parseIntegerValue(str));
        return maxPacketTimeAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return IntegerExtensions.toString(Integer.valueOf(getMaxPacketTime()));
    }

    public int getMaxPacketTime() {
        return this._maxPacketTime;
    }

    private MaxPacketTimeAttribute() {
        super.setAttributeType(AttributeType.MaxPacketTimeAttribute);
        super.setMultiplexingCategory(AttributeCategory.IdenticalPerPT);
    }

    public MaxPacketTimeAttribute(int i) {
        this();
        setMaxPacketTime(i);
    }

    private void setMaxPacketTime(int i) {
        this._maxPacketTime = i;
    }
}
