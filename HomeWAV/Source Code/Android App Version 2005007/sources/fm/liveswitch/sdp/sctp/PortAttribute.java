package fm.liveswitch.sdp.sctp;

import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class PortAttribute extends Attribute {
    private int _port;

    public static PortAttribute fromAttributeValue(String str) {
        int parseIntegerValue = ParseAssistant.parseIntegerValue(str);
        PortAttribute portAttribute = new PortAttribute();
        portAttribute.setPort(parseIntegerValue);
        return portAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getPort())));
        return sb.toString();
    }

    public int getPort() {
        return this._port;
    }

    private PortAttribute() {
        super.setAttributeType(AttributeType.SctpPortAttribute);
        super.setMultiplexingCategory(AttributeCategory.Caution);
    }

    public PortAttribute(int i) {
        this();
        setPort(i);
    }

    private void setPort(int i) {
        this._port = i;
    }
}
