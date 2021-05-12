package fm.liveswitch.sdp.rtp;

import androidx.exifinterface.media.ExifInterface;
import com.twilio.video.G722Codec;
import com.twilio.video.PcmaCodec;
import com.twilio.video.PcmuCodec;
import fm.liveswitch.Global;
import fm.liveswitch.Holder;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.ManagedConcurrentDictionary;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.AttributeCategory;
import fm.liveswitch.sdp.AttributeType;
import fm.liveswitch.sdp.FormatParametersAttribute;
import fm.liveswitch.sdp.rtcp.FeedbackAttribute;
import fm.liveswitch.sdp.rtcp.FeedbackAttributeSubType;
import fm.liveswitch.sdp.rtcp.FeedbackAttributeType;

public class MapAttribute extends Attribute {
    private static ManagedConcurrentDictionary<String, MapAttribute> __ianaMapAttributes;
    private FeedbackAttributeCollection __relatedRtcpFeedbackAttributes;
    private int _clockRate;
    private String _formatName;
    private String _formatParameters;
    private int _payloadType;
    private FormatParametersAttribute _relatedFormatParametersAttribute;

    public void addRelatedRtcpFeedbackAttribute(FeedbackAttribute feedbackAttribute) {
        this.__relatedRtcpFeedbackAttributes.addAttribute(feedbackAttribute);
    }

    public static MapAttribute fromAttributeValue(String str) {
        int i;
        String str2;
        int indexOf = StringExtensions.indexOf(str, " ");
        int parseIntegerValue = ParseAssistant.parseIntegerValue(StringExtensions.substring(str, 0, indexOf));
        String substring = str.substring(indexOf + 1);
        int indexOf2 = StringExtensions.indexOf(substring, "/");
        String substring2 = StringExtensions.substring(substring, 0, indexOf2);
        String substring3 = substring.substring(indexOf2 + 1);
        int indexOf3 = StringExtensions.indexOf(substring3, "/");
        if (indexOf3 == -1) {
            i = ParseAssistant.parseIntegerValue(substring3);
            str2 = null;
        } else {
            int parseIntegerValue2 = ParseAssistant.parseIntegerValue(StringExtensions.substring(substring3, 0, indexOf3));
            str2 = substring3.substring(indexOf3 + 1);
            i = parseIntegerValue2;
        }
        MapAttribute mapAttribute = new MapAttribute();
        mapAttribute.setPayloadType(parseIntegerValue);
        mapAttribute.setFormatName(substring2);
        mapAttribute.setClockRate(i);
        mapAttribute.setFormatParameters(str2);
        return mapAttribute;
    }

    /* access modifiers changed from: protected */
    public String getAttributeValue() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getPayloadType())));
        StringBuilderExtensions.append(sb, " ");
        StringBuilderExtensions.append(sb, getFormatName());
        StringBuilderExtensions.append(sb, "/");
        StringBuilderExtensions.append(sb, IntegerExtensions.toString(Integer.valueOf(getClockRate())));
        if (!StringExtensions.isNullOrEmpty(getFormatParameters())) {
            StringBuilderExtensions.append(sb, "/");
            StringBuilderExtensions.append(sb, getFormatParameters());
        }
        return sb.toString();
    }

    public int getClockRate() {
        return this._clockRate;
    }

    public String getFormatName() {
        return this._formatName;
    }

    public String getFormatParameters() {
        return this._formatParameters;
    }

    public static MapAttribute getIanaMapAttribute(int i) {
        Holder holder = new Holder(null);
        __ianaMapAttributes.tryGetValue(IntegerExtensions.toString(Integer.valueOf(i)), holder);
        return (MapAttribute) holder.getValue();
    }

    public int getPayloadType() {
        return this._payloadType;
    }

    public FeedbackAttribute getRelatedCcmFirFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getFir());
    }

    public FeedbackAttribute getRelatedCcmLrrFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getLrr());
    }

    public FeedbackAttribute getRelatedCcmTmmbnFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getTmmbn());
    }

    public FeedbackAttribute getRelatedCcmTmmbrFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getTmmbr());
    }

    public FormatParametersAttribute getRelatedFormatParametersAttribute() {
        return this._relatedFormatParametersAttribute;
    }

    public FeedbackAttribute getRelatedNackFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getNack(), (String) null);
    }

    public FeedbackAttribute getRelatedNackPliFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getPli());
    }

    public FeedbackAttribute getRelatedNackRpsiFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getRpsi());
    }

    public FeedbackAttribute getRelatedNackSliFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getSli());
    }

    public FeedbackAttribute getRelatedRembFeedbackAttribute() {
        return getRelatedRtcpFeedbackAttribute(FeedbackAttributeType.getRemb(), (String) null);
    }

    public FeedbackAttribute getRelatedRtcpFeedbackAttribute(int i, String str, String str2) {
        for (FeedbackAttribute feedbackAttribute : getRelatedRtcpFeedbackAttributes()) {
            if (feedbackAttribute != null && Global.equals(feedbackAttribute.getType(), str) && Global.equals(feedbackAttribute.getSubType(), str2) && feedbackAttribute.getPayloadType() == i) {
                return feedbackAttribute;
            }
        }
        return null;
    }

    public FeedbackAttribute getRelatedRtcpFeedbackAttribute(String str, String str2) {
        for (FeedbackAttribute feedbackAttribute : getRelatedRtcpFeedbackAttributes()) {
            if (feedbackAttribute != null && Global.equals(feedbackAttribute.getType(), str) && Global.equals(feedbackAttribute.getSubType(), str2)) {
                return feedbackAttribute;
            }
        }
        return null;
    }

    public FeedbackAttribute[] getRelatedRtcpFeedbackAttributes() {
        return this.__relatedRtcpFeedbackAttributes.toArray();
    }

    static {
        ManagedConcurrentDictionary<String, MapAttribute> managedConcurrentDictionary = new ManagedConcurrentDictionary<>();
        __ianaMapAttributes = managedConcurrentDictionary;
        managedConcurrentDictionary.addOrUpdate("0", new MapAttribute(0, PcmuCodec.NAME, 8000));
        __ianaMapAttributes.addOrUpdate(ExifInterface.GPS_MEASUREMENT_3D, new MapAttribute(3, "GSM", 8000));
        __ianaMapAttributes.addOrUpdate("4", new MapAttribute(4, "G723", 8000));
        __ianaMapAttributes.addOrUpdate("5", new MapAttribute(5, "DVI4", 8000));
        __ianaMapAttributes.addOrUpdate("6", new MapAttribute(6, "DVI4", 16000));
        __ianaMapAttributes.addOrUpdate("7", new MapAttribute(7, "LPC", 8000));
        __ianaMapAttributes.addOrUpdate("8", new MapAttribute(8, PcmaCodec.NAME, 8000));
        __ianaMapAttributes.addOrUpdate("9", new MapAttribute(9, G722Codec.NAME, 8000));
        __ianaMapAttributes.addOrUpdate("10", new MapAttribute(10, "L16", 44100, "2"));
        __ianaMapAttributes.addOrUpdate("11", new MapAttribute(11, "L16", 44100));
        __ianaMapAttributes.addOrUpdate("12", new MapAttribute(12, "QCELP", 8000));
        __ianaMapAttributes.addOrUpdate("13", new MapAttribute(13, "CN", 8000));
        __ianaMapAttributes.addOrUpdate("14", new MapAttribute(14, "MPA", 90000));
        __ianaMapAttributes.addOrUpdate("15", new MapAttribute(15, "G728", 8000));
        __ianaMapAttributes.addOrUpdate("16", new MapAttribute(16, "DVI4", 11025));
        __ianaMapAttributes.addOrUpdate("17", new MapAttribute(17, "DVI4", 22050));
        __ianaMapAttributes.addOrUpdate("18", new MapAttribute(18, "G729", 8000));
        __ianaMapAttributes.addOrUpdate("25", new MapAttribute(25, "CelB", 90000));
        __ianaMapAttributes.addOrUpdate("26", new MapAttribute(26, "JPEG", 90000));
        __ianaMapAttributes.addOrUpdate("28", new MapAttribute(28, "nv", 90000));
        __ianaMapAttributes.addOrUpdate("31", new MapAttribute(31, "H261", 90000));
        __ianaMapAttributes.addOrUpdate("32", new MapAttribute(32, "MPV", 90000));
        __ianaMapAttributes.addOrUpdate("33", new MapAttribute(33, "MP2T", 90000));
        __ianaMapAttributes.addOrUpdate("34", new MapAttribute(34, "H263", 90000));
    }

    private MapAttribute() {
        this.__relatedRtcpFeedbackAttributes = new FeedbackAttributeCollection();
        super.setAttributeType(AttributeType.RtpMapAttribute);
        super.setMultiplexingCategory(AttributeCategory.IdenticalPerPT);
    }

    public MapAttribute(int i, String str, int i2) {
        this(i, str, i2, (String) null);
    }

    public MapAttribute(int i, String str, int i2, String str2) {
        this();
        if (str != null) {
            setPayloadType(i);
            setFormatName(str);
            setClockRate(i2);
            setFormatParameters(str2);
            return;
        }
        throw new RuntimeException(new Exception("formatName cannot be null."));
    }

    public boolean removeRelatedRtcpFeedbackAttribute(FeedbackAttribute feedbackAttribute) {
        return this.__relatedRtcpFeedbackAttributes.remove(feedbackAttribute);
    }

    public void resetRtcpFeedbackAttributes(FeedbackAttribute[] feedbackAttributeArr) {
        this.__relatedRtcpFeedbackAttributes.clear();
        for (FeedbackAttribute addAttribute : feedbackAttributeArr) {
            this.__relatedRtcpFeedbackAttributes.addAttribute(addAttribute);
        }
    }

    private void setClockRate(int i) {
        this._clockRate = i;
    }

    private void setFormatName(String str) {
        this._formatName = str;
    }

    private void setFormatParameters(String str) {
        this._formatParameters = str;
    }

    public void setPayloadType(int i) {
        this._payloadType = i;
    }

    public void setRelatedFormatParametersAttribute(FormatParametersAttribute formatParametersAttribute) {
        this._relatedFormatParametersAttribute = formatParametersAttribute;
    }
}
