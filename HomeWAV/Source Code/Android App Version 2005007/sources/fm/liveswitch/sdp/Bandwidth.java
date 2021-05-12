package fm.liveswitch.sdp;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;

public class Bandwidth {
    private String _bandwidthType;
    private long _value;

    public Bandwidth(String str, long j) {
        setBandwidthType(str);
        setValue(j);
    }

    public String getBandwidthType() {
        return this._bandwidthType;
    }

    public long getValue() {
        return this._value;
    }

    public static Bandwidth parse(String str) {
        String[] split = StringExtensions.split(str.substring(2), new char[]{':'});
        return new Bandwidth(split[0], ParseAssistant.parseLongValue(split[1]));
    }

    private void setBandwidthType(String str) {
        this._bandwidthType = str;
    }

    private void setValue(long j) {
        this._value = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "b=");
        StringBuilderExtensions.append(sb, getBandwidthType());
        StringBuilderExtensions.append(sb, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        StringBuilderExtensions.append(sb, LongExtensions.toString(Long.valueOf(getValue())));
        return sb.toString();
    }
}
