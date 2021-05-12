package fm.liveswitch.sdp.rtcp;

import fm.liveswitch.Global;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.Log;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;
import org.slf4j.Marker;

public class FeedbackAttribute extends Attribute {
    private int _payloadType;
    private String _subType;
    private String _type;

    public static int getWildcardPayloadType() {
        return -1;
    }

    public static FeedbackAttribute ccmFirAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getFir());
    }

    public static FeedbackAttribute ccmLrrAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getLrr());
    }

    public static FeedbackAttribute ccmTmmbnAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getTmmbn());
    }

    public static FeedbackAttribute ccmTmmbrAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getTmmbr());
    }

    private FeedbackAttribute() {
        super.setAttributeType(AttributeType.RtcpFeedbackAttribute);
        super.setMultiplexingCategory(AttributeCategory.IdenticalPerPT);
    }

    public FeedbackAttribute(int i, String str) {
        this(i, str, (String) null);
    }

    public FeedbackAttribute(int i, String str, String str2) {
        this();
        setPayloadType(i);
        setType(str);
        setSubType(str2);
    }

    public static FeedbackAttribute fromAttributeValue(String str) {
        int wildcardPayloadType = getWildcardPayloadType();
        int indexOf = StringExtensions.indexOf(str, " ");
        String str2 = null;
        if (indexOf < 0) {
            Log.error(StringExtensions.concat("Could not parse SDP attribute (RTCP feedback): ", str));
            return null;
        }
        String substring = StringExtensions.substring(str, 0, indexOf);
        if (!Global.equals(substring, Marker.ANY_MARKER)) {
            wildcardPayloadType = ParseAssistant.parseIntegerValue(substring);
        }
        String substring2 = str.substring(indexOf + 1);
        int indexOf2 = StringExtensions.indexOf(substring2, " ");
        if (indexOf2 >= 0) {
            String substring3 = StringExtensions.substring(substring2, 0, indexOf2);
            str2 = substring2.substring(indexOf2 + 1);
            substring2 = substring3;
        }
        FeedbackAttribute feedbackAttribute = new FeedbackAttribute();
        feedbackAttribute.setPayloadType(wildcardPayloadType);
        feedbackAttribute.setType(substring2);
        feedbackAttribute.setSubType(str2);
        return feedbackAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        String integerExtensions = getPayloadType() == getWildcardPayloadType() ? Marker.ANY_MARKER : IntegerExtensions.toString(Integer.valueOf(getPayloadType()));
        if (getSubType() == null) {
            return StringExtensions.format("{0} {1}", integerExtensions, getType());
        }
        return StringExtensions.format("{0} {1} {2}", integerExtensions, getType(), getSubType());
    }

    public int getPayloadType() {
        return this._payloadType;
    }

    public String getSubType() {
        return this._subType;
    }

    public String getType() {
        return this._type;
    }

    public static FeedbackAttribute nackAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getNack());
    }

    public static FeedbackAttribute nackPliAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getPli());
    }

    public static FeedbackAttribute nackRpsiAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getRpsi());
    }

    public static FeedbackAttribute nackSliAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getSli());
    }

    public static FeedbackAttribute rembAttribute(int i) {
        return new FeedbackAttribute(i, FeedbackAttributeType.getRemb());
    }

    public void setPayloadType(int i) {
        this._payloadType = i;
    }

    public void setSubType(String str) {
        this._subType = str;
    }

    public void setType(String str) {
        this._type = str;
    }
}
