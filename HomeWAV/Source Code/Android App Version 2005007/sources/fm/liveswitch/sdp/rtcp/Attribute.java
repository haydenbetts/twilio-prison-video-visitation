package fm.liveswitch.sdp.rtcp;

import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.AddressType;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;
import fm.liveswitch.sdp.NetworkType;

public class Attribute extends fm.liveswitch.sdp.Attribute {
    private String _addressType;
    private String _connectionAddress;
    private String _networkType;
    private int _port;

    private Attribute() {
        super.setAttributeType(AttributeType.RtcpAttribute);
        super.setMultiplexingCategory(AttributeCategory.Transport);
    }

    public Attribute(int i, String str) {
        this();
        if (str != null) {
            update(i, str);
            return;
        }
        throw new RuntimeException(new Exception("connectionAddress cannot be null."));
    }

    public static Attribute fromAttributeValue(String str) {
        int i;
        String str2;
        String str3;
        int indexOf = StringExtensions.indexOf(str, " ");
        String str4 = null;
        if (indexOf == -1) {
            i = ParseAssistant.parseIntegerValue(str);
            str3 = null;
            str2 = null;
        } else {
            int parseIntegerValue = ParseAssistant.parseIntegerValue(StringExtensions.substring(str, 0, indexOf));
            String substring = str.substring(indexOf + 1);
            int indexOf2 = StringExtensions.indexOf(substring, " ");
            String substring2 = StringExtensions.substring(substring, 0, indexOf2);
            String substring3 = substring.substring(indexOf2 + 1);
            int indexOf3 = StringExtensions.indexOf(substring3, " ");
            String substring4 = StringExtensions.substring(substring3, 0, indexOf3);
            str2 = substring3.substring(indexOf3 + 1);
            str3 = substring4;
            i = parseIntegerValue;
            str4 = substring2;
        }
        Attribute attribute = new Attribute();
        attribute.setPort(i);
        attribute.setNetworkType(str4);
        attribute.setAddressType(str3);
        attribute.setConnectionAddress(str2);
        return attribute;
    }

    public String getAddressType() {
        return this._addressType;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getPort())));
        if (getConnectionAddress() != null) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, getNetworkType());
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, getAddressType());
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, getConnectionAddress());
        }
        return sb.toString();
    }

    public String getConnectionAddress() {
        return this._connectionAddress;
    }

    public String getNetworkType() {
        return this._networkType;
    }

    public int getPort() {
        return this._port;
    }

    public void setAddressType(String str) {
        this._addressType = str;
    }

    public void setConnectionAddress(String str) {
        this._connectionAddress = str;
    }

    public void setNetworkType(String str) {
        this._networkType = str;
    }

    public void setPort(int i) {
        this._port = i;
    }

    public void update(int i, String str) {
        setPort(i);
        setNetworkType(NetworkType.getInternet());
        setAddressType(AddressType.getAddressTypeForAddress(str));
        setConnectionAddress(str);
    }
}
