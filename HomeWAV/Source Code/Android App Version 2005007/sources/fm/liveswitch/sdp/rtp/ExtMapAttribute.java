package fm.liveswitch.sdp.rtp;

import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.MathAssistant;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StreamDirection;
import fm.liveswitch.StreamDirectionHelper;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class ExtMapAttribute extends Attribute {
    private StreamDirection __direction;
    private String _extensionAttributes;
    private int _id;
    private String _uri;

    private ExtMapAttribute() {
        this.__direction = StreamDirection.Unset;
        super.setAttributeType(AttributeType.RtpExtMapAttribute);
        super.setMultiplexingCategory(AttributeCategory.Special);
    }

    public ExtMapAttribute(int i, String str) {
        this();
        setId(i);
        setUri(str);
    }

    public ExtMapAttribute(int i, String str, StreamDirection streamDirection) {
        this(i, str);
        setDirection(streamDirection);
    }

    public static ExtMapAttribute fromAttributeValue(String str) {
        String str2;
        int indexOf = StringExtensions.indexOf(str, " ");
        int indexOf2 = StringExtensions.indexOf(str, "/");
        boolean z = indexOf2 > -1 && indexOf > indexOf2;
        StreamDirection streamDirection = StreamDirection.SendReceive;
        int parseIntegerValue = ParseAssistant.parseIntegerValue(StringExtensions.substring(str, 0, indexOf2 > -1 ? MathAssistant.min(indexOf, indexOf2) : indexOf));
        if (z) {
            streamDirection = StreamDirectionHelper.directionFromString(StringExtensions.substring(str, indexOf2 + 1, (indexOf - indexOf2) - 1));
        }
        String substring = str.substring(indexOf + 1);
        int indexOf3 = StringExtensions.indexOf(substring, " ");
        if (indexOf3 == -1) {
            str2 = "";
        } else {
            String substring2 = StringExtensions.substring(substring, 0, indexOf3);
            str2 = substring.substring(indexOf3 + 1);
            substring = substring2;
        }
        ExtMapAttribute extMapAttribute = new ExtMapAttribute(parseIntegerValue, substring);
        if (z) {
            extMapAttribute.setDirection(streamDirection);
        }
        if (!StringExtensions.isNullOrEmpty(str2)) {
            extMapAttribute.setExtensionAttributes(str2);
        }
        return extMapAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getId())));
        if (!Global.equals(getDirection(), StreamDirection.Unset)) {
            String directionToString = StreamDirectionHelper.directionToString(getDirection());
            StringBuilderExtensions.append(sb, "/");
            StringBuilderExtensions.append(sb, directionToString);
        }
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getUri());
        if (!StringExtensions.isNullOrEmpty(getExtensionAttributes())) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, getExtensionAttributes());
        }
        return sb.toString();
    }

    public StreamDirection getDirection() {
        return this.__direction;
    }

    public String getExtensionAttributes() {
        return this._extensionAttributes;
    }

    public int getId() {
        return this._id;
    }

    public String getUri() {
        return this._uri;
    }

    public void setDirection(StreamDirection streamDirection) {
        this.__direction = streamDirection;
    }

    public void setExtensionAttributes(String str) {
        this._extensionAttributes = str;
    }

    public void setId(int i) {
        this._id = i;
    }

    private void setUri(String str) {
        this._uri = str;
    }
}
