package fm.liveswitch.sdp.rtp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;

public class SsrcGroupAttribute extends Attribute {
    private String _semantics;
    private long[] _synchronizationSources;

    public static SsrcGroupAttribute fromAttributeValue(String str) {
        int indexOf = StringExtensions.indexOf(str, " ");
        String substring = StringExtensions.substring(str, 0, indexOf);
        String[] split = StringExtensions.split(str.substring(indexOf + 1), new char[]{' '});
        long[] jArr = new long[ArrayExtensions.getLength((Object[]) split)];
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            jArr[i] = ParseAssistant.parseLongValue(split[i]);
        }
        SsrcGroupAttribute ssrcGroupAttribute = new SsrcGroupAttribute();
        ssrcGroupAttribute.setSemantics(substring);
        ssrcGroupAttribute.setSynchronizationSources(jArr);
        return ssrcGroupAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, getSemantics());
        for (long valueOf : getSynchronizationSources()) {
            StringBuilderExtensions.append(sb, " ");
            StringBuilderExtensions.append(sb, LongExtensions.toString(Long.valueOf(valueOf)));
        }
        return sb.toString();
    }

    public String getSemantics() {
        return this._semantics;
    }

    public long[] getSynchronizationSources() {
        return this._synchronizationSources;
    }

    private void setSemantics(String str) {
        this._semantics = str;
    }

    private void setSynchronizationSources(long[] jArr) {
        this._synchronizationSources = jArr;
    }

    private SsrcGroupAttribute() {
        super.setAttributeType(AttributeType.RtpSsrcGroupAttribute);
        super.setMultiplexingCategory(AttributeCategory.Normal);
    }

    public SsrcGroupAttribute(String str, long[] jArr) {
        this();
        if (StringExtensions.isNullOrEmpty(str)) {
            throw new RuntimeException(new Exception("semantics cannot be null."));
        } else if (jArr != null) {
            setSemantics(str);
            setSynchronizationSources(jArr);
        } else {
            throw new RuntimeException(new Exception("synchronizationSources cannot be null."));
        }
    }
}
