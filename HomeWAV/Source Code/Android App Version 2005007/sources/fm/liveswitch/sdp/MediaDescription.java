package fm.liveswitch.sdp;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.ArrayListExtensions;
import fm.liveswitch.Global;
import fm.liveswitch.HashMapExtensions;
import fm.liveswitch.Holder;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.ParseAssistant;
import fm.liveswitch.StreamDirection;
import fm.liveswitch.StringBuilderExtensions;
import fm.liveswitch.StringComparison;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.sdp.ice.FingerprintAttribute;
import fm.liveswitch.sdp.ice.PasswordAttribute;
import fm.liveswitch.sdp.ice.UfragAttribute;
import fm.liveswitch.sdp.rtcp.Attribute;
import fm.liveswitch.sdp.rtcp.FeedbackAttribute;
import fm.liveswitch.sdp.rtcp.FeedbackAttributeSubType;
import fm.liveswitch.sdp.rtcp.FeedbackAttributeType;
import fm.liveswitch.sdp.rtcp.MuxAttribute;
import fm.liveswitch.sdp.rtp.ExtMapAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import fm.liveswitch.sdp.rtp.RidAttribute;
import fm.liveswitch.sdp.rtp.SimulcastAttribute;
import fm.liveswitch.sdp.rtp.SsrcAttribute;
import fm.liveswitch.sdp.rtp.SsrcGroupAttribute;
import fm.liveswitch.sdp.sctp.MaxMessageSizeAttribute;
import fm.liveswitch.sdp.sctp.PortAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MediaDescription {
    private ArrayList<Bandwidth> __bandwidths = new ArrayList<>();
    private AttributeCollection __mediaAttributes = new AttributeCollection();
    private ArrayList<Attribute> __orphanedAttributes = new ArrayList<>();
    private ConnectionData _connectionData;
    private EncryptionKey _encryptionKey;
    private Media _media;
    private String _mediaTitle;

    public void addBandwidth(Bandwidth bandwidth) {
        this.__bandwidths.add(bandwidth);
    }

    public void addMediaAttribute(Attribute attribute) {
        int i;
        FormatParametersAttribute formatParametersAttribute;
        boolean z;
        Class<?> cls = attribute.getClass();
        boolean isMediaLevel = Attribute.isMediaLevel(cls);
        boolean isSessionLevel = Attribute.isSessionLevel(cls);
        if (!isMediaLevel && !isSessionLevel) {
            throw new RuntimeException(new Exception("Attribute is not registered."));
        } else if (!isSessionLevel || isMediaLevel) {
            if (Global.equals(attribute.getAttributeType(), AttributeType.RtcpFeedbackAttribute) || Global.equals(attribute.getAttributeType(), AttributeType.FormatParametersAttribute)) {
                FeedbackAttribute feedbackAttribute = null;
                if (Global.equals(attribute.getAttributeType(), AttributeType.RtcpFeedbackAttribute)) {
                    FeedbackAttribute feedbackAttribute2 = (FeedbackAttribute) attribute;
                    i = feedbackAttribute2.getPayloadType();
                    feedbackAttribute = feedbackAttribute2;
                    formatParametersAttribute = null;
                } else {
                    formatParametersAttribute = (FormatParametersAttribute) attribute;
                    i = formatParametersAttribute.getFormat();
                }
                if (i > -1) {
                    z = false;
                    for (MapAttribute mapAttribute : getRtpMapAttributes()) {
                        if (i == mapAttribute.getPayloadType()) {
                            if (feedbackAttribute != null) {
                                mapAttribute.addRelatedRtcpFeedbackAttribute(feedbackAttribute);
                            }
                            if (formatParametersAttribute != null) {
                                mapAttribute.setRelatedFormatParametersAttribute(formatParametersAttribute);
                            }
                            z = true;
                        }
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    this.__orphanedAttributes.add(attribute);
                }
            } else {
                this.__mediaAttributes.addAttribute(attribute);
            }
            if (Global.equals(attribute.getAttributeType(), AttributeType.RtpMapAttribute)) {
                MapAttribute mapAttribute2 = (MapAttribute) attribute;
                int payloadType = mapAttribute2.getPayloadType();
                for (Attribute attribute2 : (Attribute[]) this.__orphanedAttributes.toArray(new Attribute[0])) {
                    if (Global.equals(attribute2.getAttributeType(), AttributeType.RtcpFeedbackAttribute)) {
                        FeedbackAttribute feedbackAttribute3 = (FeedbackAttribute) attribute2;
                        if (feedbackAttribute3.getPayloadType() == payloadType) {
                            mapAttribute2.addRelatedRtcpFeedbackAttribute(feedbackAttribute3);
                            this.__orphanedAttributes.remove(attribute2);
                        }
                    } else if (Global.equals(attribute2.getAttributeType(), AttributeType.FormatParametersAttribute)) {
                        FormatParametersAttribute formatParametersAttribute2 = (FormatParametersAttribute) attribute2;
                        if (formatParametersAttribute2.getFormat() == payloadType) {
                            mapAttribute2.setRelatedFormatParametersAttribute(formatParametersAttribute2);
                            this.__orphanedAttributes.remove(attribute2);
                        }
                    }
                }
            }
        } else {
            throw new RuntimeException(new Exception("Attribute is session-only."));
        }
    }

    public Bandwidth[] getBandwidths() {
        return (Bandwidth[]) this.__bandwidths.toArray(new Bandwidth[0]);
    }

    public boolean getBundleOnly() {
        return getBundleOnlyAttributeFromCollection(this.__mediaAttributes) != null;
    }

    static Attribute getBundleOnlyAttributeFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttributes = attributeCollection.tryGetAttributes(AttributeType.BundleOnlyAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (!tryGetAttributes || ArrayExtensions.getLength((Object[]) attributeArr) <= 0) {
            return null;
        }
        return attributeArr[0];
    }

    public Attribute[] getCandidateAttributes() {
        Holder holder = new Holder(null);
        return this.__mediaAttributes.tryGetAttributes(AttributeType.IceCandidateAttribute, holder) ? (Attribute[]) holder.getValue() : new Attribute[0];
    }

    private Attribute[] getCategoryAttributes(AttributeCategory attributeCategory) {
        ArrayList arrayList = new ArrayList();
        for (Attribute attribute : this.__mediaAttributes.toArray()) {
            if (Global.equals(attribute.getMultiplexingCategory(), attributeCategory)) {
                arrayList.add(attribute);
            }
        }
        return (Attribute[]) arrayList.toArray(new Attribute[0]);
    }

    public FeedbackAttribute getCcmFirFeedbackAttribute(int i) {
        return getRtcpFeedbackAttribute(i, FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getFir());
    }

    public FeedbackAttribute getCcmLrrFeedbackAttribute(int i) {
        return getRtcpFeedbackAttribute(i, FeedbackAttributeType.getCcm(), FeedbackAttributeSubType.getLrr());
    }

    public ConnectionData getConnectionData() {
        return this._connectionData;
    }

    public CryptoAttribute[] getCryptoAttributes() {
        return getCryptoAttributesFromCollection(this.__mediaAttributes);
    }

    static CryptoAttribute[] getCryptoAttributesFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttributes = attributeCollection.tryGetAttributes(AttributeType.CryptoAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (!tryGetAttributes) {
            return null;
        }
        CryptoAttribute[] cryptoAttributeArr = new CryptoAttribute[ArrayExtensions.getLength((Object[]) attributeArr)];
        int i = 0;
        for (Attribute attribute : attributeArr) {
            cryptoAttributeArr[i] = (CryptoAttribute) attribute;
            i++;
        }
        return cryptoAttributeArr;
    }

    public EncryptionKey getEncryptionKey() {
        return this._encryptionKey;
    }

    public FingerprintAttribute getFingerprintAttribute() {
        return getFingerprintAttributeFromCollection(this.__mediaAttributes);
    }

    static FingerprintAttribute getFingerprintAttributeFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = attributeCollection.tryGetAttribute(AttributeType.IceFingerprintAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (FingerprintAttribute) attribute;
        }
        return null;
    }

    public FormatParametersAttribute getFormatParametersAttribute(int i) {
        Holder holder = new Holder(null);
        boolean tryGetAttributes = this.__mediaAttributes.tryGetAttributes(AttributeType.RtpMapAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (tryGetAttributes && attributeArr != null) {
            for (Attribute attribute : attributeArr) {
                MapAttribute mapAttribute = (MapAttribute) attribute;
                if (mapAttribute.getPayloadType() == i) {
                    return mapAttribute.getRelatedFormatParametersAttribute();
                }
            }
        }
        return null;
    }

    public String getFormatParameterValue(int i, String str) {
        MapAttribute rtpMapAttribute = getRtpMapAttribute(i);
        if (rtpMapAttribute == null) {
            return null;
        }
        FormatParametersAttribute relatedFormatParametersAttribute = rtpMapAttribute.getRelatedFormatParametersAttribute();
        if (relatedFormatParametersAttribute == null) {
            relatedFormatParametersAttribute = getFormatParametersAttribute(i);
            if (relatedFormatParametersAttribute == null) {
                return null;
            }
            rtpMapAttribute.setRelatedFormatParametersAttribute(relatedFormatParametersAttribute);
        }
        Holder holder = new Holder(null);
        relatedFormatParametersAttribute.tryGetFormatSpecificParameter(str, holder);
        return (String) holder.getValue();
    }

    static GroupAttribute[] getGroupAttributesFromCollection(AttributeCollection attributeCollection) {
        ArrayList arrayList = new ArrayList();
        Holder holder = new Holder(null);
        boolean tryGetAttributes = attributeCollection.tryGetAttributes(AttributeType.GroupAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (tryGetAttributes) {
            for (Attribute attribute : attributeArr) {
                arrayList.add((GroupAttribute) attribute);
            }
        }
        return (GroupAttribute[]) arrayList.toArray(new GroupAttribute[0]);
    }

    public Attribute[] getIceOptionAttributes() {
        return getIceOptionAttributesFromCollection(this.__mediaAttributes);
    }

    static Attribute[] getIceOptionAttributesFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        return attributeCollection.tryGetAttributes(AttributeType.IceOptionsAttribute, holder) ? (Attribute[]) holder.getValue() : new Attribute[0];
    }

    public PasswordAttribute getIcePasswordAttribute() {
        return getIcePasswordAttributeFromCollection(this.__mediaAttributes);
    }

    static PasswordAttribute getIcePasswordAttributeFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = attributeCollection.tryGetAttribute(AttributeType.IcePasswordAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (PasswordAttribute) attribute;
        }
        return null;
    }

    public UfragAttribute getIceUfragAttribute() {
        return getIceUfragAttributeFromCollection(this.__mediaAttributes);
    }

    static UfragAttribute getIceUfragAttributeFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = attributeCollection.tryGetAttribute(AttributeType.IceUfragAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (UfragAttribute) attribute;
        }
        return null;
    }

    public Attribute[] getIdenticalCategoryAttributes() {
        return getCategoryAttributes(AttributeCategory.Identical);
    }

    public boolean getIsApplication() {
        return isMediaType(MediaType.getApplication());
    }

    public boolean getIsAudio() {
        return isMediaType(MediaType.getAudio());
    }

    public boolean getIsMessage() {
        return isMediaType(MediaType.getMessage());
    }

    public boolean getIsText() {
        return isMediaType(MediaType.getText());
    }

    public boolean getIsVideo() {
        return isMediaType(MediaType.getVideo());
    }

    public MaxPacketTimeAttribute getMaxPacketTimeAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.MaxPacketTimeAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (MaxPacketTimeAttribute) attribute;
        }
        return null;
    }

    public Media getMedia() {
        return this._media;
    }

    public Attribute[] getMediaAttributes() {
        ArrayList arrayList = new ArrayList();
        for (Attribute attribute : this.__mediaAttributes.toArray()) {
            arrayList.add(attribute);
            if (Global.equals(attribute.getAttributeType(), AttributeType.RtpMapAttribute)) {
                MapAttribute mapAttribute = (MapAttribute) attribute;
                FeedbackAttribute[] relatedRtcpFeedbackAttributes = mapAttribute.getRelatedRtcpFeedbackAttributes();
                if (relatedRtcpFeedbackAttributes != null) {
                    for (FeedbackAttribute add : relatedRtcpFeedbackAttributes) {
                        arrayList.add(add);
                    }
                }
                FormatParametersAttribute relatedFormatParametersAttribute = mapAttribute.getRelatedFormatParametersAttribute();
                if (relatedFormatParametersAttribute != null) {
                    arrayList.add(relatedFormatParametersAttribute);
                }
            }
        }
        return (Attribute[]) arrayList.toArray(new Attribute[0]);
    }

    public MediaStreamIdAttribute getMediaStreamIdentifierAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.MediaStreamIdAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (MediaStreamIdAttribute) attribute;
        }
        return null;
    }

    public String getMediaTitle() {
        return this._mediaTitle;
    }

    public FeedbackAttribute getNackFeedbackAttribute(int i) {
        return getRtcpFeedbackAttribute(i, FeedbackAttributeType.getNack(), (String) null);
    }

    public FeedbackAttribute getNackPliFeedbackAttribute(int i) {
        return getRtcpFeedbackAttribute(i, FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getPli());
    }

    public FeedbackAttribute getNackRpsiFeedbackAttribute(int i) {
        return getRtcpFeedbackAttribute(i, FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getRpsi());
    }

    public FeedbackAttribute getNackSliFeedbackAttribute(int i) {
        return getRtcpFeedbackAttribute(i, FeedbackAttributeType.getNack(), FeedbackAttributeSubType.getSli());
    }

    public PacketTimeAttribute getPacketTimeAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.PacketTimeAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (PacketTimeAttribute) attribute;
        }
        return null;
    }

    public MapAttribute[] getPreferredRtpMapAttributes() {
        Media media = getMedia();
        if (media == null) {
            return new MapAttribute[0];
        }
        ArrayList arrayList = new ArrayList();
        String formatDescription = media.getFormatDescription();
        if (formatDescription != null) {
            for (String tryParseIntegerValue : StringExtensions.split(formatDescription, new char[]{' '})) {
                IntegerHolder integerHolder = new IntegerHolder(0);
                boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(tryParseIntegerValue, integerHolder);
                int value = integerHolder.getValue();
                if (tryParseIntegerValue2) {
                    arrayList.add(getRtpMapAttribute(value) != null ? getRtpMapAttribute(value) : MapAttribute.getIanaMapAttribute(value));
                }
            }
        }
        return (MapAttribute[]) arrayList.toArray(new MapAttribute[0]);
    }

    public QualityAttribute getQualityAttribute() {
        return getQualityAttributeFromCollection(this.__mediaAttributes);
    }

    static QualityAttribute getQualityAttributeFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = attributeCollection.tryGetAttribute(AttributeType.QualityAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (QualityAttribute) attribute;
        }
        return null;
    }

    public RidAttribute getRidAttribute(String str) {
        Holder holder = new Holder(null);
        boolean tryGetAttributes = this.__mediaAttributes.tryGetAttributes(AttributeType.RtpRidAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (tryGetAttributes) {
            for (Attribute attribute : attributeArr) {
                RidAttribute ridAttribute = (RidAttribute) attribute;
                if (Global.equals(ridAttribute.getId(), str)) {
                    return ridAttribute;
                }
            }
        }
        return null;
    }

    public RidAttribute[] getRidAttributes() {
        ArrayList arrayList = new ArrayList();
        Holder holder = new Holder(null);
        boolean tryGetAttributes = this.__mediaAttributes.tryGetAttributes(AttributeType.RtpRidAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (tryGetAttributes) {
            for (Attribute attribute : attributeArr) {
                arrayList.add((RidAttribute) attribute);
            }
        }
        return (RidAttribute[]) arrayList.toArray(new RidAttribute[0]);
    }

    public RidAttribute[] getRidAttributes(String str) {
        ArrayList arrayList = new ArrayList();
        for (RidAttribute ridAttribute : getRidAttributes()) {
            if (Global.equals(ridAttribute.getDirection(), str)) {
                arrayList.add(ridAttribute);
            }
        }
        return (RidAttribute[]) arrayList.toArray(new RidAttribute[0]);
    }

    public Attribute getRtcpAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.RtcpAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (Attribute) attribute;
        }
        return null;
    }

    public FeedbackAttribute getRtcpFeedbackAttribute(int i, String str, String str2) {
        Holder holder = new Holder(null);
        boolean tryGetAttributes = this.__mediaAttributes.tryGetAttributes(AttributeType.RtpMapAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (tryGetAttributes) {
            for (Attribute attribute : attributeArr) {
                MapAttribute mapAttribute = (MapAttribute) attribute;
                if (mapAttribute.getPayloadType() == i) {
                    return mapAttribute.getRelatedRtcpFeedbackAttribute(i, str, str2);
                }
            }
        }
        return null;
    }

    public boolean getRtcpMultiplexingSupported() {
        boolean rtcpMultiplexingSupportFromCollection = getRtcpMultiplexingSupportFromCollection(this.__mediaAttributes);
        if (!rtcpMultiplexingSupportFromCollection) {
            return getBundleOnlyAttributeFromCollection(this.__mediaAttributes) != null;
        }
        return rtcpMultiplexingSupportFromCollection;
    }

    static boolean getRtcpMultiplexingSupportFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = attributeCollection.tryGetAttribute(AttributeType.RtcpMuxAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        return tryGetAttribute;
    }

    /* access modifiers changed from: package-private */
    public Attribute getRtcpMuxAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.RtcpMuxAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return attribute;
        }
        return null;
    }

    public Attribute[] getRtpExtMapAttributes() {
        Holder holder = new Holder(null);
        this.__mediaAttributes.tryGetAttributes(AttributeType.RtpExtMapAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        return attributeArr == null ? (Attribute[]) new ExtMapAttribute[0] : attributeArr;
    }

    static Attribute[] getRtpExtMapAttributesFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        return attributeCollection.tryGetAttributes(AttributeType.RtpExtMapAttribute, holder) ? (Attribute[]) holder.getValue() : new Attribute[0];
    }

    public MapAttribute getRtpMapAttribute(String str, int i) {
        return getRtpMapAttribute(str, i, (String) null);
    }

    public MapAttribute getRtpMapAttribute(String str, int i, String str2) {
        IntegerHolder integerHolder = new IntegerHolder(-1);
        MapAttribute rtpMapAttribute = getRtpMapAttribute(str, i, str2, integerHolder);
        integerHolder.getValue();
        return rtpMapAttribute;
    }

    public MapAttribute getRtpMapAttribute(String str, int i, String str2, IntegerHolder integerHolder) {
        integerHolder.setValue(-1);
        MapAttribute[] rtpMapAttributes = getRtpMapAttributes();
        int i2 = 0;
        while (i2 < ArrayExtensions.getLength((Object[]) rtpMapAttributes)) {
            MapAttribute mapAttribute = rtpMapAttributes[i2];
            if (!Global.equals(mapAttribute.getFormatName(), str) || mapAttribute.getClockRate() != i || !Global.equals(mapAttribute.getFormatParameters(), str2)) {
                i2++;
            } else {
                integerHolder.setValue(i2);
                return mapAttribute;
            }
        }
        return null;
    }

    public MapAttribute getRtpMapAttribute(int i) {
        IntegerHolder integerHolder = new IntegerHolder(-1);
        MapAttribute rtpMapAttribute = getRtpMapAttribute(i, integerHolder);
        integerHolder.getValue();
        return rtpMapAttribute;
    }

    public MapAttribute getRtpMapAttribute(int i, IntegerHolder integerHolder) {
        integerHolder.setValue(-1);
        MapAttribute[] rtpMapAttributes = getRtpMapAttributes();
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) rtpMapAttributes); i2++) {
            MapAttribute mapAttribute = rtpMapAttributes[i2];
            if (mapAttribute.getPayloadType() == i) {
                integerHolder.setValue(i2);
                return mapAttribute;
            }
        }
        return null;
    }

    public MapAttribute[] getRtpMapAttributes() {
        ArrayList arrayList = new ArrayList();
        Holder holder = new Holder(null);
        boolean tryGetAttributes = this.__mediaAttributes.tryGetAttributes(AttributeType.RtpMapAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        if (tryGetAttributes) {
            for (Attribute attribute : attributeArr) {
                arrayList.add((MapAttribute) attribute);
            }
        }
        return (MapAttribute[]) arrayList.toArray(new MapAttribute[0]);
    }

    public MapAttribute[] getRtpMapAttributes(String str, int i, String str2) {
        Holder holder = new Holder(new int[0]);
        MapAttribute[] rtpMapAttributes = getRtpMapAttributes(str, i, str2, holder);
        int[] iArr = (int[]) holder.getValue();
        return rtpMapAttributes;
    }

    public MapAttribute[] getRtpMapAttributes(String str, int i, String str2, Holder<int[]> holder) {
        MapAttribute[] rtpMapAttributes = getRtpMapAttributes();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) rtpMapAttributes); i2++) {
            MapAttribute mapAttribute = rtpMapAttributes[i2];
            if (Global.equals(mapAttribute.getFormatName(), str) && mapAttribute.getClockRate() == i && Global.equals(mapAttribute.getFormatParameters(), str2)) {
                arrayList.add(mapAttribute);
                arrayList2.add(Integer.valueOf(i2));
            }
        }
        if (ArrayListExtensions.getCount(arrayList2) == 0) {
            holder.setValue(new int[0]);
            return new MapAttribute[0];
        }
        holder.setValue(new int[ArrayListExtensions.getCount(arrayList2)]);
        for (int i3 = 0; i3 < ArrayListExtensions.getCount(arrayList2); i3++) {
            holder.getValue()[i3] = ((Integer) ArrayListExtensions.getItem(arrayList2).get(i3)).intValue();
        }
        return (MapAttribute[]) arrayList.toArray(new MapAttribute[0]);
    }

    public fm.liveswitch.sdp.sctp.MapAttribute getSctpMapAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.SctpMapAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (fm.liveswitch.sdp.sctp.MapAttribute) attribute;
        }
        return null;
    }

    public MaxMessageSizeAttribute getSctpMaxMessageSizeAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.SctpMaxMessageSizeAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (MaxMessageSizeAttribute) attribute;
        }
        return null;
    }

    public PortAttribute getSctpPortAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.SctpPortAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (PortAttribute) attribute;
        }
        return null;
    }

    public SetupAttribute getSetupAttribute() {
        return getSetupAttributeFromCollection(this.__mediaAttributes);
    }

    static SetupAttribute getSetupAttributeFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = attributeCollection.tryGetAttribute(AttributeType.SetupAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (SetupAttribute) attribute;
        }
        return null;
    }

    public SimulcastAttribute getSimulcastAttribute() {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = this.__mediaAttributes.tryGetAttribute(AttributeType.SimulcastAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (tryGetAttribute) {
            return (SimulcastAttribute) attribute;
        }
        return null;
    }

    public SsrcAttribute getSsrcAttribute(long j, String str) {
        SsrcAttribute[] ssrcAttributes = getSsrcAttributes(j, str);
        if (ArrayExtensions.getLength((Object[]) ssrcAttributes) > 0) {
            return ssrcAttributes[0];
        }
        return null;
    }

    public SsrcAttribute getSsrcAttribute(long j, String str, String str2) {
        for (SsrcAttribute ssrcAttribute : getSsrcAttributes(j, str)) {
            if (Global.equals(ssrcAttribute.getValue(), str2)) {
                return ssrcAttribute;
            }
        }
        return null;
    }

    public SsrcAttribute[] getSsrcAttributes() {
        Holder holder = new Holder(null);
        this.__mediaAttributes.tryGetAttributes(AttributeType.RtpSsrcAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        ArrayList arrayList = new ArrayList();
        for (Attribute attribute : attributeArr) {
            arrayList.add((SsrcAttribute) attribute);
        }
        return (SsrcAttribute[]) arrayList.toArray(new SsrcAttribute[0]);
    }

    public SsrcAttribute[] getSsrcAttributes(String str) {
        ArrayList arrayList = new ArrayList();
        for (SsrcAttribute ssrcAttribute : getSsrcAttributes()) {
            if (Global.equals(ssrcAttribute.getName(), str)) {
                arrayList.add(ssrcAttribute);
            }
        }
        return (SsrcAttribute[]) arrayList.toArray(new SsrcAttribute[0]);
    }

    public SsrcAttribute[] getSsrcAttributes(long j) {
        ArrayList arrayList = new ArrayList();
        for (SsrcAttribute ssrcAttribute : getSsrcAttributes()) {
            if (ssrcAttribute.getSynchronizationSource() == j) {
                arrayList.add(ssrcAttribute);
            }
        }
        return (SsrcAttribute[]) arrayList.toArray(new SsrcAttribute[0]);
    }

    public SsrcAttribute[] getSsrcAttributes(long j, String str) {
        ArrayList arrayList = new ArrayList();
        for (SsrcAttribute ssrcAttribute : getSsrcAttributes(j)) {
            if (Global.equals(ssrcAttribute.getName(), str)) {
                arrayList.add(ssrcAttribute);
            }
        }
        return (SsrcAttribute[]) arrayList.toArray(new SsrcAttribute[0]);
    }

    public SsrcGroupAttribute[] getSsrcGroupAttributes() {
        Holder holder = new Holder(null);
        this.__mediaAttributes.tryGetAttributes(AttributeType.RtpSsrcGroupAttribute, holder);
        Attribute[] attributeArr = (Attribute[]) holder.getValue();
        ArrayList arrayList = new ArrayList();
        for (Attribute attribute : attributeArr) {
            arrayList.add((SsrcGroupAttribute) attribute);
        }
        return (SsrcGroupAttribute[]) arrayList.toArray(new SsrcGroupAttribute[0]);
    }

    public long[] getSsrcGroupSsrcs(String str) {
        for (SsrcGroupAttribute ssrcGroupAttribute : getSsrcGroupAttributes()) {
            if (Global.equals(ssrcGroupAttribute.getSemantics(), str)) {
                return ssrcGroupAttribute.getSynchronizationSources();
            }
        }
        return null;
    }

    public StreamDirection getStreamDirection() {
        return getStreamDirectionFromCollection(this.__mediaAttributes);
    }

    static StreamDirection getStreamDirectionFromCollection(AttributeCollection attributeCollection) {
        Holder holder = new Holder(null);
        boolean tryGetAttribute = attributeCollection.tryGetAttribute(AttributeType.DirectionAttribute, holder);
        Attribute attribute = (Attribute) holder.getValue();
        if (attributeCollection == null || !tryGetAttribute) {
            return StreamDirection.Unset;
        }
        return ((DirectionAttribute) attribute).getStreamDirection();
    }

    public boolean getSupportsIce() {
        return getSupportsIceFromCollection(this.__mediaAttributes);
    }

    static boolean getSupportsIceFromCollection(AttributeCollection attributeCollection) {
        return (getIceUfragAttributeFromCollection(attributeCollection) == null || getIcePasswordAttributeFromCollection(attributeCollection) == null) ? false : true;
    }

    public Attribute[] getTransportCategoryAttributes() {
        return getCategoryAttributes(AttributeCategory.Transport);
    }

    public void insertMediaAttribute(Attribute attribute, int i) {
        addMediaAttribute(attribute);
    }

    private boolean isMediaType(String str) {
        Media media = getMedia();
        return media != null && Global.equals(media.getMediaType(), str);
    }

    public MediaDescription(Media media) {
        if (media != null) {
            setMedia(media);
            return;
        }
        throw new RuntimeException(new Exception("media cannot be null."));
    }

    public boolean orderFormats(String[] strArr) {
        boolean z;
        MapAttribute rtpMapAttribute;
        Media media = getMedia();
        if (media == null) {
            return false;
        }
        String[] split = StringExtensions.split(media.getFormatDescription(), new char[]{' '});
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String tryParseIntegerValue : split) {
            IntegerHolder integerHolder = new IntegerHolder(0);
            boolean tryParseIntegerValue2 = ParseAssistant.tryParseIntegerValue(tryParseIntegerValue, integerHolder);
            int value = integerHolder.getValue();
            if (tryParseIntegerValue2 && (rtpMapAttribute = getRtpMapAttribute(value)) != null) {
                arrayList2.add(rtpMapAttribute);
            }
        }
        for (String str : strArr) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                MapAttribute mapAttribute = (MapAttribute) it.next();
                if (StringExtensions.isEqual(mapAttribute.getFormatName(), str, StringComparison.InvariantCultureIgnoreCase)) {
                    arrayList.add(IntegerExtensions.toString(Integer.valueOf(mapAttribute.getPayloadType())));
                }
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            MapAttribute mapAttribute2 = (MapAttribute) it2.next();
            Iterator it3 = arrayList.iterator();
            while (true) {
                if (it3.hasNext()) {
                    if (Global.equals((String) it3.next(), IntegerExtensions.toString(Integer.valueOf(mapAttribute2.getPayloadType())))) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                arrayList.add(IntegerExtensions.toString(Integer.valueOf(mapAttribute2.getPayloadType())));
            }
        }
        media.setFormatDescription(StringExtensions.join(" ", (String[]) arrayList.toArray(new String[0])));
        return true;
    }

    public static MediaDescription parse(String str) {
        String[] splitAndClean = Utility.splitAndClean(str);
        if (splitAndClean[0].charAt(0) != 'm') {
            return null;
        }
        MediaDescription mediaDescription = new MediaDescription(Media.parse(splitAndClean[0]));
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        for (int i = 1; i < ArrayExtensions.getLength((Object[]) splitAndClean); i++) {
            String str2 = splitAndClean[i];
            if (str2.charAt(0) == 'a') {
                Attribute parse = Attribute.parse(str2);
                if (!Global.equals(parse.getAttributeType(), AttributeType.RtcpFeedbackAttribute) && !Global.equals(parse.getAttributeType(), AttributeType.FormatParametersAttribute)) {
                    mediaDescription.addMediaAttribute(parse);
                    if (Global.equals(parse.getAttributeType(), AttributeType.RtpMapAttribute)) {
                        MapAttribute mapAttribute = (MapAttribute) parse;
                        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(mapAttribute.getPayloadType()));
                        HashMapExtensions.set(HashMapExtensions.getItem(hashMap2), integerExtensions, mapAttribute);
                        Holder holder = new Holder(null);
                        boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, integerExtensions, holder);
                        FormatParametersAttribute formatParametersAttribute = (FormatParametersAttribute) holder.getValue();
                        if (tryGetValue) {
                            mapAttribute.setRelatedFormatParametersAttribute(formatParametersAttribute);
                        }
                        Holder holder2 = new Holder(null);
                        boolean tryGetValue2 = HashMapExtensions.tryGetValue(hashMap3, integerExtensions, holder2);
                        ArrayList arrayList = (ArrayList) holder2.getValue();
                        if (tryGetValue2) {
                            mapAttribute.resetRtcpFeedbackAttributes((FeedbackAttribute[]) arrayList.toArray(new FeedbackAttribute[0]));
                        }
                    }
                } else if (Global.equals(parse.getAttributeType(), AttributeType.RtcpFeedbackAttribute)) {
                    FeedbackAttribute feedbackAttribute = (FeedbackAttribute) parse;
                    String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(feedbackAttribute.getPayloadType()));
                    Holder holder3 = new Holder(null);
                    boolean tryGetValue3 = HashMapExtensions.tryGetValue(hashMap3, integerExtensions2, holder3);
                    ArrayList arrayList2 = (ArrayList) holder3.getValue();
                    if (!tryGetValue3) {
                        arrayList2 = new ArrayList();
                    }
                    arrayList2.add(feedbackAttribute);
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap3), integerExtensions2, arrayList2);
                    if (Global.equals(integerExtensions2, "-1")) {
                        for (String str3 : HashMapExtensions.getKeys(hashMap2)) {
                            ((MapAttribute) HashMapExtensions.getItem(hashMap2).get(str3)).addRelatedRtcpFeedbackAttribute(feedbackAttribute);
                        }
                    } else {
                        Holder holder4 = new Holder(null);
                        boolean tryGetValue4 = HashMapExtensions.tryGetValue(hashMap2, integerExtensions2, holder4);
                        MapAttribute mapAttribute2 = (MapAttribute) holder4.getValue();
                        if (tryGetValue4) {
                            mapAttribute2.resetRtcpFeedbackAttributes((FeedbackAttribute[]) arrayList2.toArray(new FeedbackAttribute[0]));
                        }
                    }
                } else if (Global.equals(parse.getAttributeType(), AttributeType.FormatParametersAttribute)) {
                    FormatParametersAttribute formatParametersAttribute2 = (FormatParametersAttribute) parse;
                    String integerExtensions3 = IntegerExtensions.toString(Integer.valueOf(formatParametersAttribute2.getFormat()));
                    HashMapExtensions.set(HashMapExtensions.getItem(hashMap), integerExtensions3, formatParametersAttribute2);
                    Holder holder5 = new Holder(null);
                    boolean tryGetValue5 = HashMapExtensions.tryGetValue(hashMap2, integerExtensions3, holder5);
                    MapAttribute mapAttribute3 = (MapAttribute) holder5.getValue();
                    if (tryGetValue5) {
                        mapAttribute3.setRelatedFormatParametersAttribute(formatParametersAttribute2);
                    }
                }
            }
            if (str2.charAt(0) == 'i') {
                mediaDescription.setMediaTitle(str2.substring(2));
            } else if (str2.charAt(0) == 'c') {
                mediaDescription.setConnectionData(ConnectionData.parse(str2));
            } else if (str2.charAt(0) == 'b') {
                mediaDescription.addBandwidth(Bandwidth.parse(str2));
            } else if (str2.charAt(0) == 'k') {
                mediaDescription.setEncryptionKey(EncryptionKey.parse(str2));
            }
        }
        return mediaDescription;
    }

    public boolean purgeFormat(String str) {
        return purgeFormat(str, -1);
    }

    public boolean purgeFormat(String str, int i) {
        return purgeFormat(str, i, -1);
    }

    public boolean purgeFormat(String str, int i, int i2) {
        boolean z = false;
        for (MapAttribute mapAttribute : getRtpMapAttributes()) {
            if (StringExtensions.isEqual(mapAttribute.getFormatName(), str, StringComparison.InvariantCultureIgnoreCase) && ((i == -1 || i == mapAttribute.getClockRate()) && ((i2 == -1 || Global.equals(IntegerExtensions.toString(Integer.valueOf(i2)), mapAttribute.getFormatParameters())) && purgeRtpMapAttribute(mapAttribute)))) {
                z = true;
            }
        }
        return z;
    }

    private boolean purgeRtpMapAttribute(MapAttribute mapAttribute) {
        FormatParametersAttribute relatedFormatParametersAttribute;
        Media media = getMedia();
        if (media == null || !removeMediaAttribute(mapAttribute)) {
            return false;
        }
        String integerExtensions = IntegerExtensions.toString(Integer.valueOf(mapAttribute.getPayloadType()));
        FeedbackAttribute[] relatedRtcpFeedbackAttributes = mapAttribute.getRelatedRtcpFeedbackAttributes();
        if (relatedRtcpFeedbackAttributes != null) {
            for (FeedbackAttribute removeMediaAttribute : relatedRtcpFeedbackAttributes) {
                removeMediaAttribute(removeMediaAttribute);
            }
        }
        FormatParametersAttribute relatedFormatParametersAttribute2 = mapAttribute.getRelatedFormatParametersAttribute();
        if (relatedFormatParametersAttribute2 != null) {
            removeMediaAttribute(relatedFormatParametersAttribute2);
        }
        String formatDescription = media.getFormatDescription();
        if (formatDescription != null) {
            ArrayList arrayList = new ArrayList();
            for (String str : StringExtensions.split(formatDescription, new char[]{' '})) {
                if (!Global.equals(str, integerExtensions)) {
                    arrayList.add(str);
                }
            }
            media.setFormatDescription(StringExtensions.join(" ", (String[]) arrayList.toArray(new String[0])));
        }
        for (MapAttribute mapAttribute2 : getRtpMapAttributes()) {
            if (StringExtensions.isEqual(mapAttribute2.getFormatName(), "rtx", StringComparison.InvariantCultureIgnoreCase) && (relatedFormatParametersAttribute = mapAttribute2.getRelatedFormatParametersAttribute()) != null && Global.equals(relatedFormatParametersAttribute.getFormatSpecificParameter("apt"), integerExtensions)) {
                purgeRtpMapAttribute(mapAttribute2);
            }
        }
        return true;
    }

    public boolean removeBandwidth(Bandwidth bandwidth) {
        return this.__bandwidths.remove(bandwidth);
    }

    public void removeBundleCategoryAttributes() {
        removeTransportCategoryAttributes();
        removeIdenticalCategoryAttributes();
    }

    /* access modifiers changed from: package-private */
    public void removeIdenticalCategoryAttributes() {
        for (Attribute remove : getIdenticalCategoryAttributes()) {
            this.__mediaAttributes.remove(remove);
        }
    }

    public boolean removeMediaAttribute(Attribute attribute) {
        boolean removeRelatedRtcpFeedbackAttribute;
        AttributeType attributeType = attribute.getAttributeType();
        if (attributeType == AttributeType.FormatParametersAttribute) {
            FormatParametersAttribute formatParametersAttribute = (FormatParametersAttribute) attribute;
            for (MapAttribute mapAttribute : getRtpMapAttributes()) {
                if (mapAttribute.getRelatedFormatParametersAttribute() != null) {
                    boolean z = mapAttribute.getRelatedFormatParametersAttribute().getFormat() == formatParametersAttribute.getFormat();
                    if (z) {
                        mapAttribute.setRelatedFormatParametersAttribute((FormatParametersAttribute) null);
                        return z;
                    }
                }
            }
        } else if (attributeType != AttributeType.RtcpFeedbackAttribute) {
            return this.__mediaAttributes.remove(attribute);
        } else {
            FeedbackAttribute feedbackAttribute = (FeedbackAttribute) attribute;
            for (MapAttribute mapAttribute2 : getRtpMapAttributes()) {
                if (mapAttribute2.getRelatedRtcpFeedbackAttributes() != null && (removeRelatedRtcpFeedbackAttribute = mapAttribute2.removeRelatedRtcpFeedbackAttribute(feedbackAttribute))) {
                    return removeRelatedRtcpFeedbackAttribute;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void removeTransportCategoryAttributes() {
        for (Attribute remove : getTransportCategoryAttributes()) {
            this.__mediaAttributes.remove(remove);
        }
    }

    public void setConnectionData(ConnectionData connectionData) {
        this._connectionData = connectionData;
    }

    public void setEncryptionKey(EncryptionKey encryptionKey) {
        this._encryptionKey = encryptionKey;
    }

    public boolean setFormatParameterValue(int i, String str, String str2) {
        IntegerHolder integerHolder = new IntegerHolder(-1);
        MapAttribute rtpMapAttribute = getRtpMapAttribute(i, integerHolder);
        int value = integerHolder.getValue();
        if (rtpMapAttribute == null) {
            return false;
        }
        FormatParametersAttribute relatedFormatParametersAttribute = rtpMapAttribute.getRelatedFormatParametersAttribute();
        if (relatedFormatParametersAttribute == null) {
            relatedFormatParametersAttribute = getFormatParametersAttribute(i);
            if (relatedFormatParametersAttribute == null) {
                relatedFormatParametersAttribute = new FormatParametersAttribute(i);
                insertMediaAttribute(relatedFormatParametersAttribute, value + 1);
            }
            rtpMapAttribute.setRelatedFormatParametersAttribute(relatedFormatParametersAttribute);
        }
        relatedFormatParametersAttribute.setFormatSpecificParameter(str, str2);
        return true;
    }

    private void setMedia(Media media) {
        this._media = media;
    }

    public void setMediaTitle(String str) {
        this._mediaTitle = str;
    }

    public void setQualityAttribute(QualityAttribute qualityAttribute) {
        this.__mediaAttributes.replaceAttribute(qualityAttribute);
    }

    public void setRtcpAttribute(Attribute attribute) {
        this.__mediaAttributes.replaceAttribute(attribute);
    }

    public void setRtcpMultiplexingSupported(boolean z) {
        boolean rtcpMultiplexingSupportFromCollection = getRtcpMultiplexingSupportFromCollection(this.__mediaAttributes);
        if (z) {
            if (!rtcpMultiplexingSupportFromCollection) {
                addMediaAttribute(new MuxAttribute());
            }
        } else if (rtcpMultiplexingSupportFromCollection) {
            this.__mediaAttributes.remove(AttributeType.RtcpMuxAttribute);
        }
    }

    public void setSetupAttribute(SetupAttribute setupAttribute) {
        this.__mediaAttributes.replaceAttribute(setupAttribute);
    }

    public void setStreamDirection(StreamDirection streamDirection) {
        this.__mediaAttributes.replaceAttribute(DirectionAttribute.generateDirectionAttribute(streamDirection));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilderExtensions.append(sb, StringExtensions.concat(getMedia().toString(), "\r\n"));
        if (getMediaTitle() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat("i=", getMediaTitle(), "\r\n"));
        }
        if (getConnectionData() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(getConnectionData().toString(), "\r\n"));
        }
        for (Bandwidth bandwidth : getBandwidths()) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(bandwidth.toString(), "\r\n"));
        }
        if (getEncryptionKey() != null) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(getEncryptionKey().toString(), "\r\n"));
        }
        for (Attribute attribute : this.__mediaAttributes.toArray()) {
            StringBuilderExtensions.append(sb, StringExtensions.concat(attribute.toString(), "\r\n"));
            if (Global.equals(attribute.getAttributeType(), AttributeType.RtpMapAttribute)) {
                MapAttribute mapAttribute = (MapAttribute) attribute;
                FeedbackAttribute[] relatedRtcpFeedbackAttributes = mapAttribute.getRelatedRtcpFeedbackAttributes();
                if (relatedRtcpFeedbackAttributes != null) {
                    for (FeedbackAttribute feedbackAttribute : relatedRtcpFeedbackAttributes) {
                        StringBuilderExtensions.append(sb, StringExtensions.concat(feedbackAttribute.toString(), "\r\n"));
                    }
                }
                FormatParametersAttribute relatedFormatParametersAttribute = mapAttribute.getRelatedFormatParametersAttribute();
                if (relatedFormatParametersAttribute != null) {
                    StringBuilderExtensions.append(sb, StringExtensions.concat(relatedFormatParametersAttribute.toString(), "\r\n"));
                }
            }
        }
        return sb.toString();
    }

    public void updateQualityAttributeValue(int i) {
        this.__mediaAttributes.replaceAttribute(new QualityAttribute((byte) i));
    }
}
