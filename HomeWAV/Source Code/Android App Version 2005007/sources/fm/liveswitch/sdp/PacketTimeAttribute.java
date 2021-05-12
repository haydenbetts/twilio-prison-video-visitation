package fm.liveswitch.sdp;

import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;

public class PacketTimeAttribute extends Attribute {
    private int _packetTime;

    public static PacketTimeAttribute fromAttributeValue(String str) {
        PacketTimeAttribute packetTimeAttribute = new PacketTimeAttribute();
        packetTimeAttribute.setPacketTime(ParseAssistant.parseIntegerValue(str));
        return packetTimeAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        return IntegerExtensions.toString(Integer.valueOf(getPacketTime()));
    }

    public int getPacketTime() {
        return this._packetTime;
    }

    private PacketTimeAttribute() {
        super.setAttributeType(AttributeType.PacketTimeAttribute);
        super.setMultiplexingCategory(AttributeCategory.IdenticalPerPT);
    }

    public PacketTimeAttribute(int i) {
        this();
        setPacketTime(i);
    }

    private void setPacketTime(int i) {
        this._packetTime = i;
    }
}
