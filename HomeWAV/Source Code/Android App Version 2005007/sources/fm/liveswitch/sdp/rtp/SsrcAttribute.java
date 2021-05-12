package fm.liveswitch.sdp.rtp;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class SsrcAttribute extends Attribute {
    private String _name;
    private long _synchronizationSource;
    private String _value;

    public static SsrcAttribute fromAttributeValue(String str) {
        String str2;
        int indexOf = StringExtensions.indexOf(str, " ");
        long parseLongValue = ParseAssistant.parseLongValue(StringExtensions.substring(str, 0, indexOf));
        String substring = str.substring(indexOf + 1);
        int indexOf2 = StringExtensions.indexOf(substring, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        if (indexOf2 == -1) {
            str2 = null;
        } else {
            String substring2 = StringExtensions.substring(substring, 0, indexOf2);
            str2 = substring.substring(indexOf2 + 1);
            substring = substring2;
        }
        SsrcAttribute ssrcAttribute = new SsrcAttribute();
        ssrcAttribute.setSynchronizationSource(parseLongValue);
        ssrcAttribute.setName(substring);
        ssrcAttribute.setValue(str2);
        return ssrcAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, LongExtensions.toString(Long.valueOf(getSynchronizationSource())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getName());
        if (!StringExtensions.isNullOrEmpty(getValue())) {
            StringBuilderExtensions.append(sb, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            StringBuilderExtensions.append(sb, getValue());
        }
        return sb.toString();
    }

    public String getName() {
        return this._name;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public String getValue() {
        return this._value;
    }

    private void setName(String str) {
        this._name = str;
    }

    private void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    public void setValue(String str) {
        this._value = str;
    }

    private SsrcAttribute() {
        super.setAttributeType(AttributeType.RtpSsrcAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    public SsrcAttribute(long j, String str) {
        this(j, str, (String) null);
    }

    public SsrcAttribute(long j, String str, String str2) {
        this();
        if (!StringExtensions.isNullOrEmpty(str)) {
            setSynchronizationSource(j);
            setName(str);
            setValue(str2);
            return;
        }
        throw new RuntimeException(new Exception("attributeName cannot be null."));
    }
}
