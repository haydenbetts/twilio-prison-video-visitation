package fm.liveswitch.sdp;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum AttributeType {
    UnknownAttribute(999666),
    DirectionAttribute(1),
    CategoryAttribute(2),
    CharacterSetAttribute(3),
    ConferenceTypeAttribute(4),
    CryptoAttribute(5),
    FormatParametersAttribute(6),
    FrameRateAttribute(7),
    KeywordsAttribute(8),
    LanguageAttribute(9),
    MaxPacketTimeAttribute(10),
    OrientationAttribute(11),
    PacketTimeAttribute(12),
    QualityAttribute(13),
    SdpLanguageAttribute(14),
    SetupAttribute(15),
    ToolAttribute(16),
    MediaStreamIdSemanticAttribute(17),
    MediaStreamIdAttribute(18),
    BundleOnlyAttribute(40),
    IceCandidateAttribute(19),
    IceFingerprintAttribute(20),
    IceLiteAttribute(21),
    IceMismatchAttribute(22),
    IceOptionsAttribute(23),
    IcePasswordAttribute(24),
    IceUfragAttribute(25),
    IceRemoteCandidatesAttribute(26),
    RtpMapAttribute(27),
    RtpSsrcAttribute(28),
    RtpExtMapAttribute(29),
    RtcpAttribute(30),
    RtcpFeedbackAttribute(31),
    RtcpMuxAttribute(32),
    SctpPortAttribute(33),
    SctpMapAttribute(34),
    SctpMaxMessageSizeAttribute(35),
    GroupAttribute(36),
    RtpRidAttribute(37),
    SimulcastAttribute(38),
    RtpSsrcGroupAttribute(39);
    
    private static final Map<Integer, AttributeType> lookup = null;
    private final int value;

    static {
        lookup = new HashMap();
        Iterator it = EnumSet.allOf(AttributeType.class).iterator();
        while (it.hasNext()) {
            AttributeType attributeType = (AttributeType) it.next();
            lookup.put(Integer.valueOf(attributeType.getAssignedValue()), attributeType);
        }
    }

    private AttributeType(int i) {
        this.value = i;
    }

    public int getAssignedValue() {
        return this.value;
    }

    public static AttributeType getByAssignedValue(int i) {
        return lookup.get(Integer.valueOf(i));
    }
}
