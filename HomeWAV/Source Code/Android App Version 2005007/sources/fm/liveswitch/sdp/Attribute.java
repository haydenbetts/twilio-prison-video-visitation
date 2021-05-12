package fm.liveswitch.sdp;

import com.microsoft.appcenter.Constants;
import fm.liveswitch.ClassExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.Holder;
import fm.liveswitch.IFunction1;
import fm.liveswitch.IFunctionDelegate1;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.ice.CandidateAttribute;
import fm.liveswitch.sdp.ice.FingerprintAttribute;
import fm.liveswitch.sdp.ice.LiteAttribute;
import fm.liveswitch.sdp.ice.MismatchAttribute;
import fm.liveswitch.sdp.ice.OptionsAttribute;
import fm.liveswitch.sdp.ice.PasswordAttribute;
import fm.liveswitch.sdp.ice.RemoteCandidatesAttribute;
import fm.liveswitch.sdp.ice.UfragAttribute;
import fm.liveswitch.sdp.rtcp.FeedbackAttribute;
import fm.liveswitch.sdp.rtcp.MuxAttribute;
import fm.liveswitch.sdp.rtp.ExtMapAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import fm.liveswitch.sdp.rtp.RidAttribute;
import fm.liveswitch.sdp.rtp.SimulcastAttribute;
import fm.liveswitch.sdp.rtp.SsrcAttribute;
import fm.liveswitch.sdp.rtp.SsrcGroupAttribute;
import fm.liveswitch.sdp.sctp.MaxMessageSizeAttribute;
import fm.liveswitch.sdp.sctp.PortAttribute;
import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public abstract class Attribute {
    private static HashMap<String, AttributeRegistration> _registeredAttributes = new HashMap<>();
    private static Object _registeredAttributesLock = new Object();
    private static String _unknownAttributeTypeName = ClassExtensions.getFullName(UnknownAttribute.class);
    private AttributeType _attributeType;
    private AttributeCategory _multiplexingCategory;

    /* access modifiers changed from: protected */
    public abstract String getAttributeValue();

    static {
        registerAttribute(GroupAttribute.class, "group", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPGroupAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPGroupAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(CategoryAttribute.class, "cat", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPCategoryAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPCategoryAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(CharacterSetAttribute.class, "charset", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPCharacterSetAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPCharacterSetAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(ConferenceTypeAttribute.class, "type", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPConferenceTypeAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPConferenceTypeAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(CryptoAttribute.class, "crypto", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPCryptoAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPCryptoAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(FormatParametersAttribute.class, "fmtp", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPFormatParametersAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPFormatParametersAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(FrameRateAttribute.class, FFmpegMediaMetadataRetriever.METADATA_KEY_FRAMERATE, false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPFrameRateAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPFrameRateAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(InactiveAttribute.class, "inactive", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPInactiveAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPInactiveAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(KeywordsAttribute.class, "keywds", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPKeywordsAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPKeywordsAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(LanguageAttribute.class, "lang", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPLanguageAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPLanguageAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(MaxPacketTimeAttribute.class, "maxptime", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPMaxPacketTimeAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPMaxPacketTimeAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(OrientationAttribute.class, "orient", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPOrientationAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPOrientationAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(PacketTimeAttribute.class, "ptime", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPPacketTimeAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPPacketTimeAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(QualityAttribute.class, "quality", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPQualityAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPQualityAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(ReceiveOnlyAttribute.class, "recvonly", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPReceiveOnlyAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPReceiveOnlyAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(SdpLanguageAttribute.class, "sdplang", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSdpLanguageAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSdpLanguageAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(SendOnlyAttribute.class, "sendonly", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSendOnlyAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSendOnlyAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(SendReceiveAttribute.class, "sendrecv", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSendReceiveAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSendReceiveAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(SetupAttribute.class, "setup", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSetupAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSetupAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(ToolAttribute.class, "tool", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPToolAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPToolAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(MediaStreamIdSemanticAttribute.class, "msid-semantic", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPMediaStreamIdSemanticAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPMediaStreamIdSemanticAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(MediaStreamIdAttribute.class, "mid", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPMediaStreamIdAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPMediaStreamIdAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(BundleOnlyAttribute.class, "bundle-only", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPBundleOnlyAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPBundleOnlyAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(CandidateAttribute.class, "candidate", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPCandidateAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPCandidateAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(FingerprintAttribute.class, "fingerprint", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPFingerprintAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPFingerprintAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(LiteAttribute.class, "ice-lite", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPIceLiteAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPIceLiteAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(MismatchAttribute.class, "ice-mismatch", true, false, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPIceMismatchAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPIceMismatchAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(OptionsAttribute.class, "ice-options", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPIceOptionsAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPIceOptionsAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(PasswordAttribute.class, "ice-pwd", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPIcePasswordAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPIcePasswordAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(UfragAttribute.class, "ice-ufrag", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPIceUfragAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPIceUfragAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(RemoteCandidatesAttribute.class, "remote-candidates", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPRemoteCandidatesAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPRemoteCandidatesAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(MapAttribute.class, "rtpmap", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPRtpMapAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPRtpMapAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(SsrcAttribute.class, "ssrc", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSSRCAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSSRCAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(SsrcGroupAttribute.class, "ssrc-group", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSSRCGroupAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSSRCGroupAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(ExtMapAttribute.class, "extmap", true, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPExtMapAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPExtMapAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(RidAttribute.class, "rid", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPRidAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPRidAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(SimulcastAttribute.class, "simulcast", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSimulcastAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSimulcastAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(fm.liveswitch.sdp.rtcp.Attribute.class, "rtcp", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPRtcpAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPRtcpAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(FeedbackAttribute.class, "rtcp-fb", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPRtcpFeedbackAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPRtcpFeedbackAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(MuxAttribute.class, "rtcp-mux", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPRtcpMuxAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPRtcpMuxAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(PortAttribute.class, "sctp-port", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSctpPortAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSctpPortAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(fm.liveswitch.sdp.sctp.MapAttribute.class, "sctpmap", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSctpMapAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSctpMapAttribute(attributeCreationArgs);
            }
        });
        registerAttribute(MaxMessageSizeAttribute.class, "max-message-size", false, true, new IFunctionDelegate1<AttributeCreationArgs, Attribute>() {
            public String getId() {
                return "fm.liveswitch.sdp.Attribute.createSDPSctpMaxMessageSizeAttribute";
            }

            public Attribute invoke(AttributeCreationArgs attributeCreationArgs) {
                return Attribute.createSDPSctpMaxMessageSizeAttribute(attributeCreationArgs);
            }
        });
    }

    protected Attribute() {
    }

    public static Attribute createAttribute(String str, String str2) {
        synchronized (_registeredAttributesLock) {
            for (String str3 : HashMapExtensions.getKeys(_registeredAttributes)) {
                AttributeRegistration attributeRegistration = HashMapExtensions.getItem(_registeredAttributes).get(str3);
                if (Global.equals(attributeRegistration.getName(), str)) {
                    Attribute invoke = attributeRegistration.getCreationDelegate().invoke(new AttributeCreationArgs(str2));
                    return invoke;
                }
            }
            return new UnknownAttribute(str, str2);
        }
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPBundleOnlyAttribute(AttributeCreationArgs attributeCreationArgs) {
        return BundleOnlyAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPCandidateAttribute(AttributeCreationArgs attributeCreationArgs) {
        return CandidateAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPCategoryAttribute(AttributeCreationArgs attributeCreationArgs) {
        return CategoryAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPCharacterSetAttribute(AttributeCreationArgs attributeCreationArgs) {
        return CharacterSetAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPConferenceTypeAttribute(AttributeCreationArgs attributeCreationArgs) {
        return ConferenceTypeAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPCryptoAttribute(AttributeCreationArgs attributeCreationArgs) {
        return CryptoAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPExtMapAttribute(AttributeCreationArgs attributeCreationArgs) {
        return ExtMapAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPFingerprintAttribute(AttributeCreationArgs attributeCreationArgs) {
        return FingerprintAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPFormatParametersAttribute(AttributeCreationArgs attributeCreationArgs) {
        return FormatParametersAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPFrameRateAttribute(AttributeCreationArgs attributeCreationArgs) {
        return FrameRateAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPGroupAttribute(AttributeCreationArgs attributeCreationArgs) {
        return GroupAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPIceLiteAttribute(AttributeCreationArgs attributeCreationArgs) {
        return LiteAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPIceMismatchAttribute(AttributeCreationArgs attributeCreationArgs) {
        return MismatchAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPIceOptionsAttribute(AttributeCreationArgs attributeCreationArgs) {
        return OptionsAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPIcePasswordAttribute(AttributeCreationArgs attributeCreationArgs) {
        return PasswordAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPIceUfragAttribute(AttributeCreationArgs attributeCreationArgs) {
        return UfragAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPInactiveAttribute(AttributeCreationArgs attributeCreationArgs) {
        return InactiveAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPKeywordsAttribute(AttributeCreationArgs attributeCreationArgs) {
        return KeywordsAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPLanguageAttribute(AttributeCreationArgs attributeCreationArgs) {
        return LanguageAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPMaxPacketTimeAttribute(AttributeCreationArgs attributeCreationArgs) {
        return MaxPacketTimeAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPMediaStreamIdAttribute(AttributeCreationArgs attributeCreationArgs) {
        return MediaStreamIdAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPMediaStreamIdSemanticAttribute(AttributeCreationArgs attributeCreationArgs) {
        return MediaStreamIdSemanticAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPOrientationAttribute(AttributeCreationArgs attributeCreationArgs) {
        return OrientationAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPPacketTimeAttribute(AttributeCreationArgs attributeCreationArgs) {
        return PacketTimeAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPQualityAttribute(AttributeCreationArgs attributeCreationArgs) {
        return QualityAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPReceiveOnlyAttribute(AttributeCreationArgs attributeCreationArgs) {
        return ReceiveOnlyAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPRemoteCandidatesAttribute(AttributeCreationArgs attributeCreationArgs) {
        return RemoteCandidatesAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPRidAttribute(AttributeCreationArgs attributeCreationArgs) {
        return RidAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPRtcpAttribute(AttributeCreationArgs attributeCreationArgs) {
        return fm.liveswitch.sdp.rtcp.Attribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPRtcpFeedbackAttribute(AttributeCreationArgs attributeCreationArgs) {
        return FeedbackAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPRtcpMuxAttribute(AttributeCreationArgs attributeCreationArgs) {
        return MuxAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPRtpMapAttribute(AttributeCreationArgs attributeCreationArgs) {
        return MapAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSctpMapAttribute(AttributeCreationArgs attributeCreationArgs) {
        return fm.liveswitch.sdp.sctp.MapAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSctpMaxMessageSizeAttribute(AttributeCreationArgs attributeCreationArgs) {
        return MaxMessageSizeAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSctpPortAttribute(AttributeCreationArgs attributeCreationArgs) {
        return PortAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSdpLanguageAttribute(AttributeCreationArgs attributeCreationArgs) {
        return SdpLanguageAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSendOnlyAttribute(AttributeCreationArgs attributeCreationArgs) {
        return SendOnlyAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSendReceiveAttribute(AttributeCreationArgs attributeCreationArgs) {
        return SendReceiveAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSetupAttribute(AttributeCreationArgs attributeCreationArgs) {
        return SetupAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSimulcastAttribute(AttributeCreationArgs attributeCreationArgs) {
        return SimulcastAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSSRCAttribute(AttributeCreationArgs attributeCreationArgs) {
        return SsrcAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPSSRCGroupAttribute(AttributeCreationArgs attributeCreationArgs) {
        return SsrcGroupAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    /* access modifiers changed from: private */
    public static Attribute createSDPToolAttribute(AttributeCreationArgs attributeCreationArgs) {
        return ToolAttribute.fromAttributeValue(attributeCreationArgs.getValue());
    }

    public AttributeType getAttributeType() {
        return this._attributeType;
    }

    public AttributeCategory getMultiplexingCategory() {
        return this._multiplexingCategory;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        if (r5 == null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return getTypeName(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r5 = r5.getSuperclass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getTypeName(java.lang.Class r5) {
        /*
            java.lang.Object r0 = _registeredAttributesLock
            monitor-enter(r0)
            fm.liveswitch.Holder r1 = new fm.liveswitch.Holder     // Catch:{ all -> 0x002e }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x002e }
            java.util.HashMap<java.lang.String, fm.liveswitch.sdp.AttributeRegistration> r3 = _registeredAttributes     // Catch:{ all -> 0x002e }
            java.lang.String r4 = fm.liveswitch.ClassExtensions.getFullName(r5)     // Catch:{ all -> 0x002e }
            boolean r3 = fm.liveswitch.HashMapExtensions.tryGetValue(r3, r4, r1)     // Catch:{ all -> 0x002e }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x002e }
            fm.liveswitch.sdp.AttributeRegistration r1 = (fm.liveswitch.sdp.AttributeRegistration) r1     // Catch:{ all -> 0x002e }
            if (r3 == 0) goto L_0x0021
            java.lang.String r5 = r1.getName()     // Catch:{ all -> 0x002e }
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return r5
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            java.lang.Class r5 = r5.getSuperclass()
            if (r5 == 0) goto L_0x002d
            java.lang.String r5 = getTypeName(r5)
            return r5
        L_0x002d:
            return r2
        L_0x002e:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.sdp.Attribute.getTypeName(java.lang.Class):java.lang.String");
    }

    public static boolean isMediaLevel(Class cls) {
        if (Global.equals(ClassExtensions.getFullName(cls), _unknownAttributeTypeName)) {
            return true;
        }
        synchronized (_registeredAttributesLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(_registeredAttributes, ClassExtensions.getFullName(cls), holder);
            AttributeRegistration attributeRegistration = (AttributeRegistration) holder.getValue();
            if (!tryGetValue) {
                return false;
            }
            boolean mediaLevel = attributeRegistration.getMediaLevel();
            return mediaLevel;
        }
    }

    public static boolean isSessionLevel(Class cls) {
        if (Global.equals(ClassExtensions.getFullName(cls), _unknownAttributeTypeName)) {
            return true;
        }
        synchronized (_registeredAttributesLock) {
            Holder holder = new Holder(null);
            boolean tryGetValue = HashMapExtensions.tryGetValue(_registeredAttributes, ClassExtensions.getFullName(cls), holder);
            AttributeRegistration attributeRegistration = (AttributeRegistration) holder.getValue();
            if (!tryGetValue) {
                return false;
            }
            boolean sessionLevel = attributeRegistration.getSessionLevel();
            return sessionLevel;
        }
    }

    public static Attribute parse(String str) {
        String str2;
        String substring = str.substring(2);
        int indexOf = StringExtensions.indexOf(substring, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        if (indexOf == -1) {
            str2 = null;
        } else {
            String substring2 = StringExtensions.substring(substring, 0, indexOf);
            str2 = substring.substring(indexOf + 1);
            substring = substring2;
        }
        return createAttribute(substring, str2);
    }

    private static void registerAttribute(Class cls, String str, boolean z, boolean z2, IFunction1<AttributeCreationArgs, Attribute> iFunction1) {
        synchronized (_registeredAttributesLock) {
            HashMapExtensions.set(HashMapExtensions.getItem(_registeredAttributes), ClassExtensions.getFullName(cls), new AttributeRegistration(str, z, z2, iFunction1));
        }
    }

    public void setAttributeType(AttributeType attributeType) {
        this._attributeType = attributeType;
    }

    /* access modifiers changed from: protected */
    public void setMultiplexingCategory(AttributeCategory attributeCategory) {
        this._multiplexingCategory = attributeCategory;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, "a=");
        Class<?> cls = super.getClass();
        if (Global.equals(ClassExtensions.getFullName(cls), _unknownAttributeTypeName)) {
            StringBuilderExtensions.append(sb, ((UnknownAttribute) this).getName());
        } else {
            StringBuilderExtensions.append(sb, getTypeName(cls));
        }
        String attributeValue = getAttributeValue();
        if (attributeValue != null) {
            StringBuilderExtensions.append(sb, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            StringBuilderExtensions.append(sb, attributeValue);
        }
        return sb.toString();
    }

    private static boolean unregisterAttribute(Class cls) {
        boolean remove;
        synchronized (_registeredAttributesLock) {
            remove = HashMapExtensions.remove(_registeredAttributes, ClassExtensions.getFullName(cls));
        }
        return remove;
    }
}
