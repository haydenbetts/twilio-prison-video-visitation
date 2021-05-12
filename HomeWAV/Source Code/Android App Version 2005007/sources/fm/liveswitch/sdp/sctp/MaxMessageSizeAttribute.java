package fm.liveswitch.sdp.sctp;

import fm.liveswitch.LongExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class MaxMessageSizeAttribute extends Attribute {
    private long _maxMessageSize;

    public static MaxMessageSizeAttribute fromAttributeValue(String str) {
        long parseLongValue = ParseAssistant.parseLongValue(str);
        MaxMessageSizeAttribute maxMessageSizeAttribute = new MaxMessageSizeAttribute();
        maxMessageSizeAttribute.setMaxMessageSize(parseLongValue);
        return maxMessageSizeAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, LongExtensions.toString(Long.valueOf(getMaxMessageSize())));
        return sb.toString();
    }

    public long getMaxMessageSize() {
        return this._maxMessageSize;
    }

    private MaxMessageSizeAttribute() {
        super.setAttributeType(AttributeType.SctpMaxMessageSizeAttribute);
        super.setMultiplexingCategory(AttributeCategory.Caution);
    }

    public MaxMessageSizeAttribute(long j) {
        this();
        setMaxMessageSize(j);
    }

    private void setMaxMessageSize(long j) {
        this._maxMessageSize = j;
    }
}
