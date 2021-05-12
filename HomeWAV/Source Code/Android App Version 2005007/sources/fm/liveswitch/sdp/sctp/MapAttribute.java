package fm.liveswitch.sdp.sctp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class MapAttribute extends Attribute {
    private int _port;
    private String _sctpProtocol;
    private int _streams;

    public static MapAttribute fromAttributeValue(String str) {
        String[] split = StringExtensions.split(str, new char[]{' '});
        int parseIntegerValue = ParseAssistant.parseIntegerValue(split[0]);
        String str2 = split[1];
        int parseIntegerValue2 = ArrayExtensions.getLength((Object[]) split) > 2 ? ParseAssistant.parseIntegerValue(split[2]) : 16;
        MapAttribute mapAttribute = new MapAttribute();
        mapAttribute.setPort(parseIntegerValue);
        mapAttribute.setSctpProtocol(str2);
        mapAttribute.setStreams(parseIntegerValue2);
        return mapAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getPort())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getSctpProtocol());
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getStreams())));
        return sb.toString();
    }

    public int getPort() {
        return this._port;
    }

    public String getSctpProtocol() {
        return this._sctpProtocol;
    }

    public int getStreams() {
        return this._streams;
    }

    private MapAttribute() {
        super.setAttributeType(AttributeType.SctpMapAttribute);
        super.setMultiplexingCategory(AttributeCategory.Caution);
    }

    public MapAttribute(int i, String str, int i2) {
        this();
        setPort(i);
        setSctpProtocol(str);
        setStreams(i2);
    }

    private void setPort(int i) {
        this._port = i;
    }

    private void setSctpProtocol(String str) {
        this._sctpProtocol = str;
    }

    private void setStreams(int i) {
        this._streams = i;
    }
}
