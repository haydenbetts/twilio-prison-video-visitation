package fm.liveswitch;

import fm.liveswitch.MediaFormat;
import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.Bandwidth;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.rtcp.FeedbackAttribute;
import fm.liveswitch.sdp.rtp.ExtMapAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import fm.liveswitch.sdp.rtp.Media;
import fm.liveswitch.sdp.rtp.RidAttribute;
import fm.liveswitch.sdp.rtp.RidDirection;
import fm.liveswitch.sdp.rtp.SimulcastAttribute;
import fm.liveswitch.sdp.rtp.SimulcastDirection;
import fm.liveswitch.sdp.rtp.SimulcastStream;
import fm.liveswitch.sdp.rtp.SimulcastStreamDescription;
import fm.liveswitch.sdp.rtp.SsrcAttribute;
import fm.liveswitch.sdp.rtp.SsrcAttributeName;
import fm.liveswitch.sdp.rtp.SsrcGroupAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class MediaStreamMediaDescriptionManager<TFormat extends MediaFormat<TFormat>> extends MediaDescriptionManager {
    private static ILog __log = Log.getLogger(MediaStreamMediaDescriptionManager.class);
    private StreamDirection __absoluteSenderTimeLocalDirection = StreamDirection.Unset;
    private MediaHeaderExtensionPolicy __absoluteSenderTimePolicy;
    private StreamDirection __absoluteSenderTimeRemoteDirection = StreamDirection.Unset;
    private StreamDirection __localDirection = StreamDirection.Unset;
    private ArrayList<String> __localReceiveRtpStreamIds = new ArrayList<>();
    private ArrayList<String> __localSendRtpStreamIds = new ArrayList<>();
    private ArrayList<Long> __localSynchronizationSources = new ArrayList<>();
    private RembPolicy __rembPolicy = RembPolicy.Disabled;
    private StreamDirection __remoteDirection = StreamDirection.Unset;
    private ArrayList<String> __remoteReceiveRtpStreamIds = new ArrayList<>();
    private ArrayList<String> __remoteSendRtpStreamIds = new ArrayList<>();
    private HashMap<Integer, Integer> __remoteSupportedRembPayloadTypes = new HashMap<>();
    private boolean __remoteSupportsRtcpFeedback = true;
    private ArrayList<Long> __remoteSynchronizationSources = new ArrayList<>();
    private StreamDirection __sdesMidLocalDirection = StreamDirection.Unset;
    private MediaHeaderExtensionPolicy __sdesMidPolicy;
    private StreamDirection __sdesMidRemoteDirection = StreamDirection.Unset;
    private StreamDirection __sdesRepairedRtpStreamIdLocalDirection = StreamDirection.Unset;
    private MediaHeaderExtensionPolicy __sdesRepairedRtpStreamIdPolicy;
    private StreamDirection __sdesRepairedRtpStreamIdRemoteDirection = StreamDirection.Unset;
    private StreamDirection __sdesRtpStreamIdLocalDirection = StreamDirection.Unset;
    private MediaHeaderExtensionPolicy __sdesRtpStreamIdPolicy;
    private StreamDirection __sdesRtpStreamIdRemoteDirection = StreamDirection.Unset;
    private boolean _ccmFirEnabled;
    private boolean _ccmLrrEnabled;
    private boolean _ccmTmmbnEnabled;
    private boolean _ccmTmmbrEnabled;
    private boolean _multiplexingSupported;
    private boolean _nackEnabled;
    private boolean _nackPliEnabled;
    private boolean _redFecEnabled;
    private int _remoteBandwidth;
    private String _remoteDescriptionMediaId;
    private String _remoteDescriptionTrackId;
    private boolean _remoteSupportsCcmFir;
    private boolean _remoteSupportsCcmLrr;
    private boolean _remoteSupportsCcmTmmbn;
    private boolean _remoteSupportsCcmTmmbr;
    private boolean _remoteSupportsNack;
    private boolean _remoteSupportsNackPli;
    private boolean _remoteSupportsRedFec;
    private RtpHeaderExtensionRegistry _rtpHeaderExtensionRegistry;

    private void addReceiverRidEncoding(EncodingInfo encodingInfo, MediaDescription mediaDescription, boolean z) {
        addRidEncoding(encodingInfo, mediaDescription, RidDirection.getReceive(), z);
    }

    private void addReceiverRidSimulcast(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, int i) {
        addRidSimulcast(encodingInfoArr, mediaDescription, SimulcastDirection.getReceive(), i);
    }

    private void addRidEncoding(EncodingInfo encodingInfo, MediaDescription mediaDescription, String str, boolean z) {
        mediaDescription.addMediaAttribute(encodingInfo.toSdpRidAttribute(str));
    }

    private void addRidSimulcast(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, String str, int i) {
        SimulcastStream[] simulcastStreamArr = new SimulcastStream[ArrayExtensions.getLength((Object[]) encodingInfoArr)];
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) encodingInfoArr); i2++) {
            simulcastStreamArr[i2] = encodingInfoArr[i2].getSdpSimulcastStream();
        }
        SimulcastAttribute simulcastAttribute = new SimulcastAttribute(new SimulcastStreamDescription(str, simulcastStreamArr));
        simulcastAttribute.setDraftVersion(i);
        mediaDescription.addMediaAttribute(simulcastAttribute);
    }

    private void addSenderRidEncoding(EncodingInfo encodingInfo, MediaDescription mediaDescription, boolean z) {
        addRidEncoding(encodingInfo, mediaDescription, RidDirection.getSend(), z);
    }

    private void addSenderRidSimulcast(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, int i) {
        addRidSimulcast(encodingInfoArr, mediaDescription, SimulcastDirection.getSend(), i);
    }

    private void addSenderSynchronizationSource(long j, MediaDescription mediaDescription, MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        String canonicalName = mediaStreamMediaDescriptionRequirements.getCanonicalName();
        String localDescriptionMediaId = mediaStreamMediaDescriptionRequirements.getLocalDescriptionMediaId();
        String localDescriptionTrackId = mediaStreamMediaDescriptionRequirements.getLocalDescriptionTrackId();
        mediaDescription.addMediaAttribute(new SsrcAttribute(j, SsrcAttributeName.getCName(), canonicalName));
        mediaDescription.addMediaAttribute(new SsrcAttribute(j, SsrcAttributeName.getMediaStreamId(), StringExtensions.format("{0} {1}", localDescriptionMediaId, localDescriptionTrackId)));
        mediaDescription.addMediaAttribute(new SsrcAttribute(j, SsrcAttributeName.getMediaStreamLabel(), localDescriptionMediaId));
        mediaDescription.addMediaAttribute(new SsrcAttribute(j, SsrcAttributeName.getLabel(), localDescriptionTrackId));
    }

    private void addSenderSynchronizationSourceEncoding(EncodingInfo encodingInfo, MediaDescription mediaDescription) {
        for (SsrcAttribute addMediaAttribute : encodingInfo.getSdpSsrcRestrictionAttributes()) {
            mediaDescription.addMediaAttribute(addMediaAttribute);
        }
    }

    private void addSenderSynchronizationSourceSimulcast(long[] jArr, MediaDescription mediaDescription) {
        mediaDescription.addMediaAttribute(new SsrcGroupAttribute("SIM", jArr));
    }

    private StreamDirection calculateAggregateHeaderDirection(MediaHeaderExtensionPolicy mediaHeaderExtensionPolicy, StreamDirection streamDirection, StreamDirection streamDirection2) {
        if (!Global.equals(mediaHeaderExtensionPolicy, MediaHeaderExtensionPolicy.Disabled)) {
            if (Global.equals(streamDirection2, StreamDirection.Unset)) {
                return streamDirection;
            }
            if (Global.equals(streamDirection2, StreamDirection.Inactive)) {
                return streamDirection2;
            }
            if (Global.equals(streamDirection2, StreamDirection.SendReceive)) {
                return Global.equals(streamDirection, StreamDirection.Unset) ? StreamDirection.SendReceive : streamDirection;
            }
            if (Global.equals(streamDirection2, StreamDirection.SendOnly)) {
                if (Global.equals(streamDirection, StreamDirection.Unset) || Global.equals(streamDirection, StreamDirection.ReceiveOnly) || Global.equals(streamDirection, StreamDirection.SendReceive)) {
                    return StreamDirection.ReceiveOnly;
                }
                return StreamDirection.Inactive;
            } else if (!Global.equals(streamDirection2, StreamDirection.ReceiveOnly)) {
                throw new RuntimeException(new Exception("Invalid case encountered while trying to identify RTP Header Extension Direction."));
            } else if (Global.equals(streamDirection, StreamDirection.Unset) || Global.equals(streamDirection, StreamDirection.SendOnly) || Global.equals(streamDirection, StreamDirection.SendReceive)) {
                return StreamDirection.SendOnly;
            }
        }
        return StreamDirection.Inactive;
    }

    private RtpHeaderExtensionRegistry createRtpHeaderRegistry(StreamDirection streamDirection) {
        RtpHeaderExtensionRegistryArgs rtpHeaderExtensionRegistryArgs = new RtpHeaderExtensionRegistryArgs();
        rtpHeaderExtensionRegistryArgs.setStreamDirection(streamDirection);
        rtpHeaderExtensionRegistryArgs.setAbsoluteSenderTimePolicy(this.__absoluteSenderTimePolicy);
        rtpHeaderExtensionRegistryArgs.setAbsoluteSenderTimeDirection(this.__absoluteSenderTimeLocalDirection);
        rtpHeaderExtensionRegistryArgs.setSdesMidPolicy(this.__sdesMidPolicy);
        rtpHeaderExtensionRegistryArgs.setSdesMidDirection(this.__sdesMidLocalDirection);
        rtpHeaderExtensionRegistryArgs.setSdesRtpStreamIdPolicy(this.__sdesRtpStreamIdPolicy);
        rtpHeaderExtensionRegistryArgs.setSdesRtpStreamIdDirection(this.__sdesRtpStreamIdLocalDirection);
        rtpHeaderExtensionRegistryArgs.setSdesRepairedRtpStreamIdPolicy(this.__sdesRepairedRtpStreamIdPolicy);
        rtpHeaderExtensionRegistryArgs.setSdesRepairedRtpStreamIdDirection(this.__sdesRepairedRtpStreamIdLocalDirection);
        return new RtpHeaderExtensionRegistry(rtpHeaderExtensionRegistryArgs);
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0215  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0220 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public fm.liveswitch.sdp.MediaDescription createSdpMediaDescription(fm.liveswitch.MediaDescriptionRequirements r23, fm.liveswitch.sdp.Message r24, boolean r25, boolean r26) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            fm.liveswitch.MediaStreamMediaDescriptionRequirements r1 = (fm.liveswitch.MediaStreamMediaDescriptionRequirements) r1
            fm.liveswitch.MediaStreamMediaDescriptionRequirements r1 = (fm.liveswitch.MediaStreamMediaDescriptionRequirements) r1
            r0.extractPropertiesFromRequirements(r1)
            fm.liveswitch.sdp.MediaDescription r2 = super.createSdpMediaDescription(r23, r24, r25, r26)
            r3 = 1
            if (r25 == 0) goto L_0x001a
            boolean r5 = r1.getMultiplexingSupported()
            r0.setMultiplexingSupported(r5)
            goto L_0x002c
        L_0x001a:
            boolean r5 = r22.getMultiplexingSupported()
            if (r5 == 0) goto L_0x0028
            boolean r5 = r1.getMultiplexingSupported()
            if (r5 == 0) goto L_0x0028
            r5 = 1
            goto L_0x0029
        L_0x0028:
            r5 = 0
        L_0x0029:
            r0.setMultiplexingSupported(r5)
        L_0x002c:
            boolean r5 = r22.getMultiplexingSupported()
            if (r5 == 0) goto L_0x003a
            fm.liveswitch.sdp.rtcp.MuxAttribute r5 = new fm.liveswitch.sdp.rtcp.MuxAttribute
            r5.<init>()
            r2.addMediaAttribute(r5)
        L_0x003a:
            fm.liveswitch.StreamDirection r5 = r1.getDirection()
            fm.liveswitch.StreamDirection r6 = fm.liveswitch.StreamDirection.Inactive
            boolean r6 = fm.liveswitch.Global.equals(r5, r6)
            if (r6 != 0) goto L_0x0065
            fm.liveswitch.TransportAddress r6 = r1.getLikelySecondaryTransportAddress()
            java.lang.String r6 = r6.getIPAddress()
            fm.liveswitch.TransportAddress r7 = r1.getLikelySecondaryTransportAddress()
            int r7 = r7.getPort()
            fm.liveswitch.sdp.rtcp.Attribute r8 = new fm.liveswitch.sdp.rtcp.Attribute
            boolean r9 = super.getDisabled()
            if (r9 == 0) goto L_0x005f
            r7 = 0
        L_0x005f:
            r8.<init>(r7, r6)
            r2.addMediaAttribute(r8)
        L_0x0065:
            r0.setLocalDirection(r5)
            fm.liveswitch.sdp.DirectionAttribute r6 = fm.liveswitch.sdp.DirectionAttribute.generateDirectionAttribute(r5)
            r2.addMediaAttribute(r6)
            fm.liveswitch.sdp.Media r6 = r2.getMedia()
            fm.liveswitch.StreamType r7 = r23.getStreamType()
            java.lang.String r7 = fm.liveswitch.sdp.MediaType.fromStreamType(r7)
            r6.setMediaType(r7)
            fm.liveswitch.sdp.Media r6 = r2.getMedia()
            fm.liveswitch.StreamType r7 = r23.getStreamType()
            boolean r8 = r0.__remoteSupportsRtcpFeedback
            boolean r9 = super.getUseDtls()
            fm.liveswitch.EncryptionMode r10 = super.getEncryptionMode()
            fm.liveswitch.EncryptionMode r11 = fm.liveswitch.EncryptionMode.Null
            boolean r10 = fm.liveswitch.Global.equals(r10, r11)
            r10 = r10 ^ r3
            java.lang.String r7 = fm.liveswitch.sdp.rtp.Media.generateRtpProfile(r7, r8, r9, r10)
            r6.setTransportProtocol(r7)
            fm.liveswitch.RedFecPolicy r6 = r1.getRedFecPolicy()
            fm.liveswitch.RedFecPolicy r7 = fm.liveswitch.RedFecPolicy.Negotiated
            boolean r6 = fm.liveswitch.Global.equals(r6, r7)
            fm.liveswitch.NackPolicy r7 = r1.getNackPolicy()
            fm.liveswitch.NackPolicy r8 = fm.liveswitch.NackPolicy.Negotiated
            boolean r7 = fm.liveswitch.Global.equals(r7, r8)
            fm.liveswitch.NackPliPolicy r8 = r1.getNackPliPolicy()
            fm.liveswitch.NackPliPolicy r9 = fm.liveswitch.NackPliPolicy.Negotiated
            boolean r8 = fm.liveswitch.Global.equals(r8, r9)
            fm.liveswitch.CcmFirPolicy r9 = r1.getCcmFirPolicy()
            fm.liveswitch.CcmFirPolicy r10 = fm.liveswitch.CcmFirPolicy.Negotiated
            boolean r9 = fm.liveswitch.Global.equals(r9, r10)
            fm.liveswitch.CcmLrrPolicy r10 = r1.getCcmLrrPolicy()
            fm.liveswitch.CcmLrrPolicy r11 = fm.liveswitch.CcmLrrPolicy.Negotiated
            boolean r10 = fm.liveswitch.Global.equals(r10, r11)
            fm.liveswitch.CcmTmmbrPolicy r11 = r1.getCcmTmmbrPolicy()
            fm.liveswitch.CcmTmmbrPolicy r12 = fm.liveswitch.CcmTmmbrPolicy.Negotiated
            boolean r11 = fm.liveswitch.Global.equals(r11, r12)
            fm.liveswitch.CcmTmmbnPolicy r12 = r1.getCcmTmmbnPolicy()
            fm.liveswitch.CcmTmmbnPolicy r13 = fm.liveswitch.CcmTmmbnPolicy.Negotiated
            boolean r12 = fm.liveswitch.Global.equals(r12, r13)
            fm.liveswitch.MediaFormat[] r13 = r1.getFormats()
            int r14 = r13.length
            java.lang.String r15 = ""
            r4 = r15
            r3 = 0
            r16 = 1
        L_0x00ef:
            if (r3 >= r14) goto L_0x022e
            r17 = r13[r3]
            boolean r18 = r17.getIsFixedBitrate()
            r16 = r16 & r18
            r25 = r13
            java.lang.String r13 = r17.getName()
            boolean r13 = r0.formatIsFec(r13)
            if (r13 == 0) goto L_0x0115
            if (r13 == 0) goto L_0x010a
            if (r6 == 0) goto L_0x010a
            goto L_0x0115
        L_0x010a:
            r21 = r3
            r20 = r5
            r18 = r6
            r19 = r14
            r13 = 1
            goto L_0x0220
        L_0x0115:
            r18 = r6
            fm.liveswitch.sdp.rtp.MapAttribute r6 = new fm.liveswitch.sdp.rtp.MapAttribute
            r19 = r14
            int r14 = r17.getRegisteredPayloadType()
            java.lang.String r0 = r17.getName()
            r20 = r5
            int r5 = r17.getClockRate()
            r21 = r3
            java.lang.String r3 = r17.getParameters()
            r6.<init>(r14, r0, r5, r3)
            int r0 = r17.getRegisteredPayloadType()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = fm.liveswitch.IntegerExtensions.toString(r0)
            java.lang.String r3 = " "
            java.lang.String r4 = fm.liveswitch.StringExtensions.concat(r4, r0, r3)
            r2.addMediaAttribute(r6)
            if (r7 == 0) goto L_0x0156
            if (r13 != 0) goto L_0x0156
            int r0 = r17.getRegisteredPayloadType()
            fm.liveswitch.sdp.rtcp.FeedbackAttribute r0 = fm.liveswitch.sdp.rtcp.FeedbackAttribute.nackAttribute(r0)
            r6.addRelatedRtcpFeedbackAttribute(r0)
        L_0x0156:
            fm.liveswitch.StreamType r0 = r23.getStreamType()
            fm.liveswitch.StreamType r3 = fm.liveswitch.StreamType.Video
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 == 0) goto L_0x0208
            if (r8 == 0) goto L_0x016f
            int r0 = r17.getRegisteredPayloadType()
            fm.liveswitch.sdp.rtcp.FeedbackAttribute r0 = fm.liveswitch.sdp.rtcp.FeedbackAttribute.nackPliAttribute(r0)
            r6.addRelatedRtcpFeedbackAttribute(r0)
        L_0x016f:
            if (r9 == 0) goto L_0x017c
            int r0 = r17.getRegisteredPayloadType()
            fm.liveswitch.sdp.rtcp.FeedbackAttribute r0 = fm.liveswitch.sdp.rtcp.FeedbackAttribute.ccmFirAttribute(r0)
            r6.addRelatedRtcpFeedbackAttribute(r0)
        L_0x017c:
            if (r10 == 0) goto L_0x0189
            int r0 = r17.getRegisteredPayloadType()
            fm.liveswitch.sdp.rtcp.FeedbackAttribute r0 = fm.liveswitch.sdp.rtcp.FeedbackAttribute.ccmLrrAttribute(r0)
            r6.addRelatedRtcpFeedbackAttribute(r0)
        L_0x0189:
            if (r11 == 0) goto L_0x0196
            int r0 = r17.getRegisteredPayloadType()
            fm.liveswitch.sdp.rtcp.FeedbackAttribute r0 = fm.liveswitch.sdp.rtcp.FeedbackAttribute.ccmTmmbrAttribute(r0)
            r6.addRelatedRtcpFeedbackAttribute(r0)
        L_0x0196:
            if (r12 == 0) goto L_0x01a3
            int r0 = r17.getRegisteredPayloadType()
            fm.liveswitch.sdp.rtcp.FeedbackAttribute r0 = fm.liveswitch.sdp.rtcp.FeedbackAttribute.ccmTmmbnAttribute(r0)
            r6.addRelatedRtcpFeedbackAttribute(r0)
        L_0x01a3:
            java.lang.String r0 = r17.getName()
            java.lang.String r3 = fm.liveswitch.VideoFormat.getH264Name()
            fm.liveswitch.StringComparison r5 = fm.liveswitch.StringComparison.OrdinalIgnoreCase
            boolean r0 = fm.liveswitch.StringExtensions.isEqual(r0, r3, r5)
            if (r0 == 0) goto L_0x0208
            fm.liveswitch.sdp.FormatParametersAttribute r0 = new fm.liveswitch.sdp.FormatParametersAttribute
            int r3 = r17.getRegisteredPayloadType()
            r0.<init>(r3)
            java.lang.String r3 = r17.getProfile()
            if (r3 == 0) goto L_0x01e3
            java.lang.String r3 = r17.getLevel()
            if (r3 == 0) goto L_0x01e3
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.String r5 = r17.getProfile()
            r13 = 0
            r3[r13] = r5
            java.lang.String r5 = r17.getLevel()
            r13 = 1
            r3[r13] = r5
            java.lang.String r3 = fm.liveswitch.StringExtensions.join(r15, r3)
            java.lang.String r5 = "profile-level-id"
            r0.setFormatSpecificParameter(r5, r3)
            goto L_0x01e4
        L_0x01e3:
            r13 = 1
        L_0x01e4:
            boolean r3 = r17.getLevelIsStrict()
            if (r3 != 0) goto L_0x01f1
            java.lang.String r3 = "level-asymmetry-allowed"
            java.lang.String r5 = "1"
            r0.setFormatSpecificParameter(r3, r5)
        L_0x01f1:
            java.lang.String r3 = r17.getPacketizationMode()
            if (r3 == 0) goto L_0x0204
            java.lang.String r3 = r17.getPacketizationMode()
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "packetization-mode"
            r0.setFormatSpecificParameter(r5, r3)
        L_0x0204:
            r6.setRelatedFormatParametersAttribute(r0)
            goto L_0x0209
        L_0x0208:
            r13 = 1
        L_0x0209:
            fm.liveswitch.RembPolicy r0 = r1.getRembPolicy()
            fm.liveswitch.RembPolicy r3 = fm.liveswitch.RembPolicy.Disabled
            boolean r0 = fm.liveswitch.Global.equals(r0, r3)
            if (r0 != 0) goto L_0x0220
            int r0 = r17.getRegisteredPayloadType()
            fm.liveswitch.sdp.rtcp.FeedbackAttribute r0 = fm.liveswitch.sdp.rtcp.FeedbackAttribute.rembAttribute(r0)
            r6.addRelatedRtcpFeedbackAttribute(r0)
        L_0x0220:
            int r3 = r21 + 1
            r0 = r22
            r13 = r25
            r6 = r18
            r14 = r19
            r5 = r20
            goto L_0x00ef
        L_0x022e:
            r20 = r5
            fm.liveswitch.sdp.Media r0 = r2.getMedia()
            r13 = 0
            char[] r3 = new char[r13]
            java.lang.String r3 = fm.liveswitch.StringExtensions.trimEnd(r4, r3)
            r0.setFormatDescription(r3)
            fm.liveswitch.sdp.Bandwidth[] r0 = r2.getBandwidths()
            int r3 = r0.length
            r4 = 0
        L_0x0244:
            if (r4 >= r3) goto L_0x024e
            r5 = r0[r4]
            r2.removeBandwidth(r5)
            int r4 = r4 + 1
            goto L_0x0244
        L_0x024e:
            int r0 = r1.getLocalBandwidth()
            if (r16 != 0) goto L_0x0272
            if (r0 <= 0) goto L_0x0272
            fm.liveswitch.sdp.Bandwidth r1 = new fm.liveswitch.sdp.Bandwidth
            java.lang.String r3 = fm.liveswitch.sdp.BandwidthType.getTransportIndependentApplicationSpecificMaximum()
            int r4 = r0 * 1000
            long r4 = (long) r4
            r1.<init>(r3, r4)
            r2.addBandwidth(r1)
            fm.liveswitch.sdp.Bandwidth r1 = new fm.liveswitch.sdp.Bandwidth
            java.lang.String r3 = fm.liveswitch.sdp.BandwidthType.getApplicationSpecific()
            long r4 = (long) r0
            r1.<init>(r3, r4)
            r2.addBandwidth(r1)
        L_0x0272:
            fm.liveswitch.RtpHeaderExtensionRegistry r0 = r22.getRtpHeaderExtensionRegistry()
            if (r0 != 0) goto L_0x0284
            r0 = r22
            r1 = r20
            fm.liveswitch.RtpHeaderExtensionRegistry r3 = r0.createRtpHeaderRegistry(r1)
            r0.setRtpHeaderExtensionRegistry(r3)
            goto L_0x0288
        L_0x0284:
            r0 = r22
            r1 = r20
        L_0x0288:
            fm.liveswitch.RtpHeaderExtensionRegistry r3 = r22.getRtpHeaderExtensionRegistry()
            fm.liveswitch.sdp.Attribute[] r1 = r0.obtainRtpExtMapAttributes(r3, r1)
            int r3 = r1.length
            r4 = 0
        L_0x0292:
            if (r4 >= r3) goto L_0x029c
            r5 = r1[r4]
            r2.addMediaAttribute(r5)
            int r4 = r4 + 1
            goto L_0x0292
        L_0x029c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaStreamMediaDescriptionManager.createSdpMediaDescription(fm.liveswitch.MediaDescriptionRequirements, fm.liveswitch.sdp.Message, boolean, boolean):fm.liveswitch.sdp.MediaDescription");
    }

    private void extractPropertiesFromRequirements(MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        this.__rembPolicy = mediaStreamMediaDescriptionRequirements.getRembPolicy();
        this.__absoluteSenderTimePolicy = mediaStreamMediaDescriptionRequirements.getAbsoluteSenderTimePolicy();
        this.__absoluteSenderTimeLocalDirection = mediaStreamMediaDescriptionRequirements.getAbsoluteSenderTimeLocalDirection();
        this.__sdesMidPolicy = mediaStreamMediaDescriptionRequirements.getSdesMidPolicy();
        this.__sdesMidLocalDirection = mediaStreamMediaDescriptionRequirements.getSdesMidLocalDirection();
        this.__sdesRtpStreamIdPolicy = mediaStreamMediaDescriptionRequirements.getSdesRtpStreamIdPolicy();
        this.__sdesRtpStreamIdLocalDirection = mediaStreamMediaDescriptionRequirements.getSdesRtpStreamIdLocalDirection();
        this.__sdesRepairedRtpStreamIdPolicy = mediaStreamMediaDescriptionRequirements.getSdesRepairedRtpStreamIdPolicy();
        this.__sdesRepairedRtpStreamIdLocalDirection = mediaStreamMediaDescriptionRequirements.getSdesRepairedRtpStreamIdLocalDirection();
    }

    private boolean formatIsFec(String str) {
        return Global.equals(str, MediaFormat.getRedName()) || Global.equals(str, MediaFormat.getUlpFecName());
    }

    public StreamDirection getAbsoluteSenderTimeDirection() {
        return calculateAggregateHeaderDirection(this.__absoluteSenderTimePolicy, this.__absoluteSenderTimeLocalDirection, this.__absoluteSenderTimeRemoteDirection);
    }

    public StreamDirection getAbsoluteSenderTimeLocalDirection() {
        return this.__absoluteSenderTimeLocalDirection;
    }

    public StreamDirection getAbsoluteSenderTimeRemoteDirection() {
        return this.__absoluteSenderTimeRemoteDirection;
    }

    public boolean getCcmFirEnabled() {
        return this._ccmFirEnabled;
    }

    public boolean getCcmLrrEnabled() {
        return this._ccmLrrEnabled;
    }

    public boolean getCcmTmmbnEnabled() {
        return this._ccmTmmbnEnabled;
    }

    public boolean getCcmTmmbrEnabled() {
        return this._ccmTmmbrEnabled;
    }

    public StreamDirection getLocalDirection() {
        return this.__localDirection;
    }

    public String[] getLocalReceiveRtpStreamIds() {
        return Utility.toStringArray(this.__localReceiveRtpStreamIds);
    }

    public String[] getLocalSendRtpStreamIds() {
        return Utility.toStringArray(this.__localSendRtpStreamIds);
    }

    public long[] getLocalSynchronizationSources() {
        return Utility.toLongArray(this.__localSynchronizationSources);
    }

    public boolean getMultiplexingSupported() {
        return this._multiplexingSupported;
    }

    public boolean getNackEnabled() {
        return this._nackEnabled;
    }

    public boolean getNackPliEnabled() {
        return this._nackPliEnabled;
    }

    public boolean getRedFecEnabled() {
        return this._redFecEnabled;
    }

    public boolean getRembEnabled() {
        return Global.equals(this.__rembPolicy, RembPolicy.Negotiated) && HashMapExtensions.getCount(this.__remoteSupportedRembPayloadTypes) > 0;
    }

    public int getRemoteBandwidth() {
        return this._remoteBandwidth;
    }

    public String getRemoteDescriptionMediaId() {
        return this._remoteDescriptionMediaId;
    }

    public String getRemoteDescriptionTrackId() {
        return this._remoteDescriptionTrackId;
    }

    public StreamDirection getRemoteDirection() {
        return this.__remoteDirection;
    }

    public String[] getRemoteReceiveRtpStreamIds() {
        return Utility.toStringArray(this.__remoteReceiveRtpStreamIds);
    }

    public String[] getRemoteSendRtpStreamIds() {
        return Utility.toStringArray(this.__remoteSendRtpStreamIds);
    }

    public boolean getRemoteSupportsCcmFir() {
        return this._remoteSupportsCcmFir;
    }

    public boolean getRemoteSupportsCcmLrr() {
        return this._remoteSupportsCcmLrr;
    }

    public boolean getRemoteSupportsCcmTmmbn() {
        return this._remoteSupportsCcmTmmbn;
    }

    public boolean getRemoteSupportsCcmTmmbr() {
        return this._remoteSupportsCcmTmmbr;
    }

    private boolean getRemoteSupportsNack() {
        return this._remoteSupportsNack;
    }

    public boolean getRemoteSupportsNackPli() {
        return this._remoteSupportsNackPli;
    }

    private boolean getRemoteSupportsRedFec() {
        return this._remoteSupportsRedFec;
    }

    public boolean getRemoteSupportsRtcpFeedback() {
        return this.__remoteSupportsRtcpFeedback;
    }

    public long[] getRemoteSynchronizationSources() {
        return Utility.toLongArray(this.__remoteSynchronizationSources);
    }

    /* access modifiers changed from: package-private */
    public RtpHeaderExtensionRegistry getRtpHeaderExtensionRegistry() {
        return this._rtpHeaderExtensionRegistry;
    }

    private String getRtpStreamId(ArrayList<String> arrayList, int i, IntegerHolder integerHolder) {
        String integerExtensions;
        do {
            integerExtensions = IntegerExtensions.toString(Integer.valueOf(i));
            i++;
        } while (arrayList.contains(integerExtensions));
        integerHolder.setValue(i);
        return integerExtensions;
    }

    public StreamDirection getSdesMidDirection() {
        return calculateAggregateHeaderDirection(this.__sdesMidPolicy, this.__sdesMidLocalDirection, this.__sdesMidRemoteDirection);
    }

    public StreamDirection getSdesMidLocalDirection() {
        return this.__sdesMidLocalDirection;
    }

    public StreamDirection getSdesMidRemoteDirection() {
        return this.__sdesMidRemoteDirection;
    }

    public StreamDirection getSdesRepairedRtpStreamIdDirection() {
        return calculateAggregateHeaderDirection(this.__sdesRepairedRtpStreamIdPolicy, this.__sdesRepairedRtpStreamIdLocalDirection, this.__sdesRepairedRtpStreamIdRemoteDirection);
    }

    public StreamDirection getSdesRepairedRtpStreamIdLocalDirection() {
        return this.__sdesRepairedRtpStreamIdLocalDirection;
    }

    public StreamDirection getSdesRepairedRtpStreamIdRemoteDirection() {
        return this.__sdesRepairedRtpStreamIdRemoteDirection;
    }

    public StreamDirection getSdesRtpStreamIdDirection() {
        return calculateAggregateHeaderDirection(this.__sdesRtpStreamIdPolicy, this.__sdesRtpStreamIdLocalDirection, this.__sdesRtpStreamIdRemoteDirection);
    }

    public StreamDirection getSdesRtpStreamIdLocalDirection() {
        return this.__sdesRtpStreamIdLocalDirection;
    }

    public StreamDirection getSdesRtpStreamIdRemoteDirection() {
        return this.__sdesRtpStreamIdRemoteDirection;
    }

    public MediaStreamMediaDescriptionManager() {
        setNackPliEnabled(true);
        setCcmFirEnabled(true);
        setCcmLrrEnabled(true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.sdp.Attribute[] obtainRtpExtMapAttributes(fm.liveswitch.RtpHeaderExtensionRegistry r9, fm.liveswitch.StreamDirection r10) {
        /*
            r8 = this;
            fm.liveswitch.RtpHeaderExtensionRegistryElement[] r9 = r9.obtainRegisteredEntries()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r9.length
            r2 = 0
            r3 = 0
        L_0x000c:
            if (r3 >= r1) goto L_0x0086
            r4 = r9[r3]
            r5 = 0
            fm.liveswitch.StreamDirection r6 = r4.getDirection()
            boolean r7 = fm.liveswitch.Global.equals(r10, r6)
            if (r7 == 0) goto L_0x0029
            fm.liveswitch.sdp.rtp.ExtMapAttribute r5 = new fm.liveswitch.sdp.rtp.ExtMapAttribute
            int r7 = r4.getId()
            java.lang.String r4 = r4.getUri()
            r5.<init>(r7, r4, r6)
            goto L_0x0076
        L_0x0029:
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.ReceiveOnly
            boolean r7 = fm.liveswitch.Global.equals(r10, r7)
            if (r7 == 0) goto L_0x0045
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.SendReceive
            boolean r7 = fm.liveswitch.Global.equals(r6, r7)
            if (r7 == 0) goto L_0x003a
            goto L_0x0055
        L_0x003a:
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.SendOnly
            boolean r7 = fm.liveswitch.Global.equals(r6, r7)
            if (r7 == 0) goto L_0x0061
            fm.liveswitch.StreamDirection r6 = fm.liveswitch.StreamDirection.Unset
            goto L_0x0061
        L_0x0045:
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.SendOnly
            boolean r7 = fm.liveswitch.Global.equals(r10, r7)
            if (r7 == 0) goto L_0x0061
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.SendReceive
            boolean r7 = fm.liveswitch.Global.equals(r6, r7)
            if (r7 == 0) goto L_0x0057
        L_0x0055:
            r6 = r10
            goto L_0x0061
        L_0x0057:
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.ReceiveOnly
            boolean r7 = fm.liveswitch.Global.equals(r6, r7)
            if (r7 == 0) goto L_0x0061
            fm.liveswitch.StreamDirection r6 = fm.liveswitch.StreamDirection.Unset
        L_0x0061:
            fm.liveswitch.StreamDirection r7 = fm.liveswitch.StreamDirection.Unset
            boolean r7 = fm.liveswitch.Global.equals(r6, r7)
            if (r7 != 0) goto L_0x0076
            fm.liveswitch.sdp.rtp.ExtMapAttribute r5 = new fm.liveswitch.sdp.rtp.ExtMapAttribute
            int r7 = r4.getId()
            java.lang.String r4 = r4.getUri()
            r5.<init>(r7, r4, r6)
        L_0x0076:
            if (r5 == 0) goto L_0x0083
            fm.liveswitch.StreamDirection r4 = fm.liveswitch.StreamDirection.Inactive
            boolean r4 = fm.liveswitch.Global.equals(r6, r4)
            if (r4 != 0) goto L_0x0083
            r0.add(r5)
        L_0x0083:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0086:
            fm.liveswitch.sdp.Attribute[] r9 = new fm.liveswitch.sdp.Attribute[r2]
            java.lang.Object[] r9 = r0.toArray(r9)
            fm.liveswitch.sdp.Attribute[] r9 = (fm.liveswitch.sdp.Attribute[]) r9
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaStreamMediaDescriptionManager.obtainRtpExtMapAttributes(fm.liveswitch.RtpHeaderExtensionRegistry, fm.liveswitch.StreamDirection):fm.liveswitch.sdp.Attribute[]");
    }

    private Error processMultipleSendEncodings(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, boolean z, MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        SimulcastMode simulcastMode = mediaStreamMediaDescriptionRequirements.getSimulcastMode();
        if (simulcastMode == SimulcastMode.Disabled) {
            return new Error(ErrorCode.LocalDescriptionError, new Exception("Multiple send encodings were specified, but the SimulcastMode is Disabled. Choose a different SimulcastMode for the stream or reduce the number of encodings to one."));
        }
        if (simulcastMode == SimulcastMode.RtpStreamId) {
            return processRidSimulcast(encodingInfoArr, mediaDescription, z, mediaStreamMediaDescriptionRequirements);
        }
        if (simulcastMode == SimulcastMode.SynchronizationSource) {
            return processSsrcSimulcast(encodingInfoArr, mediaDescription, z, mediaStreamMediaDescriptionRequirements);
        }
        return new Error(ErrorCode.LocalDescriptionError, new Exception(StringExtensions.format("Multiple send encodings were specified, but the SimulcastMode '{0}' was not recognized.", (Object) simulcastMode.toString())));
    }

    private Error processReceiveEncodings(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, boolean z, MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (EncodingInfo encodingInfo : encodingInfoArr) {
            i++;
            if (mediaStreamMediaDescriptionRequirements.getMaxReceiveEncodings() > 0 && ArrayListExtensions.getCount(arrayList) == mediaStreamMediaDescriptionRequirements.getMaxReceiveEncodings()) {
                __log.warn(StringExtensions.format("Discarding receive encoding #{0} ({1}) as it exceeds the max receive encodings limit of {2}.", IntegerExtensions.toString(Integer.valueOf(i)), encodingInfo.toString(), IntegerExtensions.toString(Integer.valueOf(mediaStreamMediaDescriptionRequirements.getMaxReceiveEncodings()))));
            } else if (encodingInfo.getRtpStreamId() != null) {
                arrayList.add(encodingInfo);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            EncodingInfo encodingInfo2 = (EncodingInfo) it.next();
            if (encodingInfo2.getRtpStreamId() != null) {
                addReceiverRidEncoding(encodingInfo2, mediaDescription, z);
            } else if (z) {
                return new Error(ErrorCode.LocalDescriptionError, new Exception("Receive encoding in renegotiated offer is missing RTP stream ID."));
            } else {
                return new Error(ErrorCode.LocalDescriptionError, new Exception("Receive encoding in answer is missing RTP stream ID."));
            }
        }
        if (ArrayListExtensions.getCount(arrayList) <= 1) {
            return null;
        }
        addReceiverRidSimulcast((EncodingInfo[]) arrayList.toArray(new EncodingInfo[0]), mediaDescription, mediaStreamMediaDescriptionRequirements.getSimulcastDraftVersion());
        return null;
    }

    private Error processRidSimulcast(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, boolean z, MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        int length = encodingInfoArr.length;
        int i = 0;
        while (i < length) {
            EncodingInfo encodingInfo = encodingInfoArr[i];
            if (encodingInfo.getRtpStreamId() != null) {
                addSenderRidEncoding(encodingInfo, mediaDescription, z);
                i++;
            } else if (z) {
                return new Error(ErrorCode.LocalDescriptionError, new Exception("Send encoding in renegotiated offer is missing RTP stream ID."));
            } else {
                return new Error(ErrorCode.LocalDescriptionError, new Exception("Send encoding in answer is missing RTP stream ID."));
            }
        }
        addSenderRidSimulcast(encodingInfoArr, mediaDescription, mediaStreamMediaDescriptionRequirements.getSimulcastDraftVersion());
        for (EncodingInfo synchronizationSource : encodingInfoArr) {
            this.__localSynchronizationSources.add(Long.valueOf(synchronizationSource.getSynchronizationSource()));
        }
        return null;
    }

    public Error processSdpMediaDescription(MediaDescriptionRequirementsBase mediaDescriptionRequirementsBase, Message message, int i, boolean z, boolean z2, boolean z3) {
        FeedbackAttribute relatedRembFeedbackAttribute;
        FeedbackAttribute relatedCcmTmmbnFeedbackAttribute;
        FeedbackAttribute relatedCcmTmmbrFeedbackAttribute;
        FeedbackAttribute relatedCcmLrrFeedbackAttribute;
        FeedbackAttribute relatedCcmFirFeedbackAttribute;
        FeedbackAttribute relatedNackFeedbackAttribute;
        FeedbackAttribute relatedNackPliFeedbackAttribute;
        boolean z4 = z3;
        MediaStreamMediaDescriptionRequirements mediaStreamMediaDescriptionRequirements = (MediaStreamMediaDescriptionRequirements) mediaDescriptionRequirementsBase;
        extractPropertiesFromRequirements(mediaStreamMediaDescriptionRequirements);
        Error processSdpMediaDescription = super.processSdpMediaDescription(mediaDescriptionRequirementsBase, message, i, z, z2, z3);
        if (processSdpMediaDescription == null) {
            MediaDescription mediaDescription = message.getMediaDescriptions()[i];
            StreamDirection streamDirection = mediaDescription.getStreamDirection();
            if (Global.equals(streamDirection, StreamDirection.Unset)) {
                streamDirection = message.getSessionLevelDirection();
            }
            if (Global.equals(streamDirection, StreamDirection.Unset)) {
                streamDirection = StreamDirection.SendReceive;
            }
            StreamDirection streamDirection2 = streamDirection;
            if (getRtpHeaderExtensionRegistry() == null) {
                setRtpHeaderExtensionRegistry(createRtpHeaderRegistry(streamDirection2));
            }
            updateRtpHeaderExtensionRegistry(message, mediaDescription, z, z3, z2, streamDirection2);
            boolean rtcpMultiplexingSupported = mediaDescription.getRtcpMultiplexingSupported();
            if (!rtcpMultiplexingSupported) {
                rtcpMultiplexingSupported = message.getSessionLevelRtcpMultiplexingSupport();
            }
            setMultiplexingSupported(mediaStreamMediaDescriptionRequirements.getMultiplexingSupported());
            setMultiplexingSupported(rtcpMultiplexingSupported & getMultiplexingSupported());
            boolean z5 = false;
            if (!z) {
                setRemoteDirection(streamDirection2);
                this.__remoteSupportsRtcpFeedback = Media.supportsRtcpBasedFeedback(mediaDescription.getMedia().getTransportProtocol());
                SsrcAttribute[] ssrcAttributes = mediaDescription.getSsrcAttributes();
                this.__remoteSynchronizationSources.clear();
                if (ArrayExtensions.getLength((Object[]) ssrcAttributes) > 0) {
                    for (SsrcAttribute ssrcAttribute : ssrcAttributes) {
                        if (Global.equals(ssrcAttribute.getName(), SsrcAttributeName.getCName())) {
                            this.__remoteSynchronizationSources.add(Long.valueOf(ssrcAttribute.getSynchronizationSource()));
                        }
                    }
                    for (SsrcAttribute ssrcAttribute2 : ssrcAttributes) {
                        if (Global.equals(ssrcAttribute2.getName(), SsrcAttributeName.getMediaStreamLabel())) {
                            setRemoteDescriptionMediaId(ssrcAttribute2.getValue());
                        } else if (Global.equals(ssrcAttribute2.getName(), SsrcAttributeName.getLabel())) {
                            setRemoteDescriptionTrackId(ssrcAttribute2.getValue());
                        }
                    }
                    for (SsrcAttribute ssrcAttribute3 : ssrcAttributes) {
                        if (ssrcAttribute3.getValue() != null && Global.equals(ssrcAttribute3.getName(), SsrcAttributeName.getMediaStreamId())) {
                            String[] split = StringExtensions.split(ssrcAttribute3.getValue(), new char[]{' '});
                            if (ArrayExtensions.getLength((Object[]) split) > 0) {
                                setRemoteDescriptionMediaId(split[0]);
                            }
                            if (ArrayExtensions.getLength((Object[]) split) > 1) {
                                setRemoteDescriptionTrackId(split[1]);
                            }
                        }
                    }
                }
                this.__remoteSendRtpStreamIds.clear();
                for (RidAttribute id : mediaDescription.getRidAttributes(RidDirection.getSend())) {
                    this.__remoteSendRtpStreamIds.add(id.getId());
                }
                this.__remoteReceiveRtpStreamIds.clear();
                for (RidAttribute id2 : mediaDescription.getRidAttributes(RidDirection.getReceive())) {
                    this.__remoteReceiveRtpStreamIds.add(id2.getId());
                }
                setRemoteBandwidth(-1);
                Bandwidth[] bandwidths = mediaDescription.getBandwidths();
                int length = bandwidths.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    Bandwidth bandwidth = bandwidths[i2];
                    if (Global.equals(StringExtensions.toUpper(bandwidth.getBandwidthType()), "TIAS") && bandwidth.getValue() > 0) {
                        setRemoteBandwidth((int) (bandwidth.getValue() / 1000));
                        break;
                    }
                    i2++;
                }
            } else {
                if (!Global.equals(streamDirection2, getLocalDirection())) {
                    if (!z4) {
                        __log.debug("Stream direction was modified in local SDP description. This action may have unintended consequences. Use Stream.ChangeDirection() instead.");
                    }
                    setLocalDirection(streamDirection2);
                }
                EncodingInfo[] sendEncodings = mediaStreamMediaDescriptionRequirements.getSendEncodings();
                EncodingInfo[] receiveEncodings = mediaStreamMediaDescriptionRequirements.getReceiveEncodings();
                Error rtpStreamIds = setRtpStreamIds(sendEncodings, receiveEncodings, z2, z4);
                if (rtpStreamIds != null) {
                    return rtpStreamIds;
                }
                this.__localSynchronizationSources.clear();
                this.__localSendRtpStreamIds.clear();
                this.__localReceiveRtpStreamIds.clear();
                if (receiveEncodings != null && (rtpStreamIds = processReceiveEncodings(receiveEncodings, mediaDescription, z4, mediaStreamMediaDescriptionRequirements)) != null) {
                    return rtpStreamIds;
                }
                if (sendEncodings != null) {
                    Error processSendEncodings = processSendEncodings(sendEncodings, mediaDescription, z4, mediaStreamMediaDescriptionRequirements);
                    if (processSendEncodings != null) {
                        return processSendEncodings;
                    }
                    processSdpMediaDescription = processSendEncodings;
                } else {
                    processSdpMediaDescription = rtpStreamIds;
                }
                for (SsrcAttribute synchronizationSource : mediaDescription.getSsrcAttributes(SsrcAttributeName.getCName())) {
                    this.__localSynchronizationSources.add(Long.valueOf(synchronizationSource.getSynchronizationSource()));
                }
                for (RidAttribute id3 : mediaDescription.getRidAttributes(RidDirection.getSend())) {
                    this.__localSendRtpStreamIds.add(id3.getId());
                }
                for (RidAttribute id4 : mediaDescription.getRidAttributes(RidDirection.getReceive())) {
                    this.__localReceiveRtpStreamIds.add(id4.getId());
                }
            }
            for (MapAttribute mapAttribute : mediaDescription.getRtpMapAttributes()) {
                if (!z) {
                    if (mapAttribute.getRelatedNackFeedbackAttribute() != null) {
                        setRemoteSupportsNack(true);
                    }
                    if (mapAttribute.getRelatedNackPliFeedbackAttribute() != null) {
                        setRemoteSupportsNackPli(true);
                    }
                    if (mapAttribute.getRelatedCcmFirFeedbackAttribute() != null) {
                        setRemoteSupportsCcmFir(true);
                    }
                    if (mapAttribute.getRelatedCcmLrrFeedbackAttribute() != null) {
                        setRemoteSupportsCcmLrr(true);
                    }
                    if (mapAttribute.getRelatedCcmTmmbrFeedbackAttribute() != null) {
                        setRemoteSupportsCcmTmmbr(true);
                    }
                    if (mapAttribute.getRelatedCcmTmmbnFeedbackAttribute() != null) {
                        setRemoteSupportsCcmTmmbn(true);
                    }
                    if (formatIsFec(mapAttribute.getFormatName())) {
                        setRemoteSupportsRedFec(true);
                    }
                    if (!this.__remoteSupportedRembPayloadTypes.containsKey(Integer.valueOf(mapAttribute.getPayloadType())) && mapAttribute.getRelatedRembFeedbackAttribute() != null) {
                        HashMapExtensions.add(this.__remoteSupportedRembPayloadTypes, Integer.valueOf(mapAttribute.getPayloadType()), 0);
                    }
                } else if (!z4) {
                    if ((!getRemoteSupportsNackPli() || !getNackPliEnabled()) && (relatedNackPliFeedbackAttribute = mapAttribute.getRelatedNackPliFeedbackAttribute()) != null) {
                        mapAttribute.removeRelatedRtcpFeedbackAttribute(relatedNackPliFeedbackAttribute);
                    }
                    if ((!getRemoteSupportsNack() || !getNackEnabled()) && (relatedNackFeedbackAttribute = mapAttribute.getRelatedNackFeedbackAttribute()) != null) {
                        mapAttribute.removeRelatedRtcpFeedbackAttribute(relatedNackFeedbackAttribute);
                    }
                    if ((!getRemoteSupportsCcmFir() || !getCcmFirEnabled()) && (relatedCcmFirFeedbackAttribute = mapAttribute.getRelatedCcmFirFeedbackAttribute()) != null) {
                        mapAttribute.removeRelatedRtcpFeedbackAttribute(relatedCcmFirFeedbackAttribute);
                    }
                    if ((!getRemoteSupportsCcmLrr() || !getCcmLrrEnabled()) && (relatedCcmLrrFeedbackAttribute = mapAttribute.getRelatedCcmLrrFeedbackAttribute()) != null) {
                        mapAttribute.removeRelatedRtcpFeedbackAttribute(relatedCcmLrrFeedbackAttribute);
                    }
                    if ((!getRemoteSupportsCcmTmmbr() || !getCcmTmmbrEnabled()) && (relatedCcmTmmbrFeedbackAttribute = mapAttribute.getRelatedCcmTmmbrFeedbackAttribute()) != null) {
                        mapAttribute.removeRelatedRtcpFeedbackAttribute(relatedCcmTmmbrFeedbackAttribute);
                    }
                    if ((!getRemoteSupportsCcmTmmbn() || !getCcmTmmbnEnabled()) && (relatedCcmTmmbnFeedbackAttribute = mapAttribute.getRelatedCcmTmmbnFeedbackAttribute()) != null) {
                        mapAttribute.removeRelatedRtcpFeedbackAttribute(relatedCcmTmmbnFeedbackAttribute);
                    }
                    if ((!getRemoteSupportsRedFec() || !getRedFecEnabled()) && formatIsFec(mapAttribute.getFormatName())) {
                        mediaDescription.removeMediaAttribute(mapAttribute);
                    }
                    if ((!this.__remoteSupportedRembPayloadTypes.containsKey(Integer.valueOf(mapAttribute.getPayloadType())) || !getRembEnabled()) && (relatedRembFeedbackAttribute = mapAttribute.getRelatedRembFeedbackAttribute()) != null) {
                        mapAttribute.removeRelatedRtcpFeedbackAttribute(relatedRembFeedbackAttribute);
                    }
                }
            }
            if (Global.equals(mediaStreamMediaDescriptionRequirements.getStreamType(), StreamType.Audio)) {
                setNackEnabled(false);
                setRedFecEnabled(false);
            } else if (Global.equals(mediaStreamMediaDescriptionRequirements.getStreamType(), StreamType.Video)) {
                if (!z || !z4) {
                    setNackEnabled(getRemoteSupportsNack() && Global.equals(mediaStreamMediaDescriptionRequirements.getNackPolicy(), NackPolicy.Negotiated));
                    setNackPliEnabled(getRemoteSupportsNackPli() && Global.equals(mediaStreamMediaDescriptionRequirements.getNackPliPolicy(), NackPliPolicy.Negotiated));
                    setCcmFirEnabled(getRemoteSupportsCcmFir() && Global.equals(mediaStreamMediaDescriptionRequirements.getCcmFirPolicy(), CcmFirPolicy.Negotiated));
                    setCcmLrrEnabled(getRemoteSupportsCcmLrr() && Global.equals(mediaStreamMediaDescriptionRequirements.getCcmLrrPolicy(), CcmLrrPolicy.Negotiated));
                    setCcmTmmbrEnabled(getRemoteSupportsCcmTmmbr() && Global.equals(mediaStreamMediaDescriptionRequirements.getCcmTmmbrPolicy(), CcmTmmbrPolicy.Negotiated));
                    setCcmTmmbnEnabled(getRemoteSupportsCcmTmmbn() && Global.equals(mediaStreamMediaDescriptionRequirements.getCcmTmmbnPolicy(), CcmTmmbnPolicy.Negotiated));
                    if (getRemoteSupportsRedFec() && Global.equals(mediaStreamMediaDescriptionRequirements.getRedFecPolicy(), RedFecPolicy.Negotiated)) {
                        z5 = true;
                    }
                    setRedFecEnabled(z5);
                } else {
                    setNackEnabled(Global.equals(mediaStreamMediaDescriptionRequirements.getNackPolicy(), NackPolicy.Negotiated));
                    setNackPliEnabled(Global.equals(mediaStreamMediaDescriptionRequirements.getNackPliPolicy(), NackPliPolicy.Negotiated));
                    setCcmFirEnabled(Global.equals(mediaStreamMediaDescriptionRequirements.getCcmFirPolicy(), CcmFirPolicy.Negotiated));
                    setCcmLrrEnabled(Global.equals(mediaStreamMediaDescriptionRequirements.getCcmLrrPolicy(), CcmLrrPolicy.Negotiated));
                    setCcmTmmbrEnabled(Global.equals(mediaStreamMediaDescriptionRequirements.getCcmTmmbrPolicy(), CcmTmmbrPolicy.Negotiated));
                    setCcmTmmbnEnabled(Global.equals(mediaStreamMediaDescriptionRequirements.getCcmTmmbnPolicy(), CcmTmmbnPolicy.Negotiated));
                    setRedFecEnabled(Global.equals(mediaStreamMediaDescriptionRequirements.getRedFecPolicy(), RedFecPolicy.Negotiated));
                }
            }
        }
        return processSdpMediaDescription;
    }

    private Error processSendEncodings(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, boolean z, MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        if (ArrayExtensions.getLength((Object[]) encodingInfoArr) == 0) {
            return null;
        }
        if (ArrayExtensions.getLength((Object[]) encodingInfoArr) == 1) {
            return processSingleSendEncoding(encodingInfoArr[0], mediaDescription, z, mediaStreamMediaDescriptionRequirements);
        }
        return processMultipleSendEncodings(encodingInfoArr, mediaDescription, z, mediaStreamMediaDescriptionRequirements);
    }

    private Error processSingleSendEncoding(EncodingInfo encodingInfo, MediaDescription mediaDescription, boolean z, MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        long synchronizationSource = encodingInfo.getSynchronizationSource();
        if (encodingInfo.getRtpStreamId() != null) {
            addSenderRidEncoding(encodingInfo, mediaDescription, z);
        }
        addSenderSynchronizationSource(synchronizationSource, mediaDescription, mediaStreamMediaDescriptionRequirements);
        addSenderSynchronizationSourceEncoding(encodingInfo, mediaDescription);
        return null;
    }

    private Error processSsrcSimulcast(EncodingInfo[] encodingInfoArr, MediaDescription mediaDescription, boolean z, MediaStreamMediaDescriptionRequirements<TFormat> mediaStreamMediaDescriptionRequirements) {
        long[] jArr = new long[ArrayExtensions.getLength((Object[]) encodingInfoArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) encodingInfoArr); i++) {
            EncodingInfo encodingInfo = encodingInfoArr[i];
            long synchronizationSource = encodingInfo.getSynchronizationSource();
            jArr[i] = synchronizationSource;
            addSenderSynchronizationSource(synchronizationSource, mediaDescription, mediaStreamMediaDescriptionRequirements);
            addSenderSynchronizationSourceEncoding(encodingInfo, mediaDescription);
        }
        addSenderSynchronizationSourceSimulcast(jArr, mediaDescription);
        return null;
    }

    private void setCcmFirEnabled(boolean z) {
        this._ccmFirEnabled = z;
    }

    private void setCcmLrrEnabled(boolean z) {
        this._ccmLrrEnabled = z;
    }

    private void setCcmTmmbnEnabled(boolean z) {
        this._ccmTmmbnEnabled = z;
    }

    private void setCcmTmmbrEnabled(boolean z) {
        this._ccmTmmbrEnabled = z;
    }

    public void setLocalDirection(StreamDirection streamDirection) {
        this.__localDirection = streamDirection;
    }

    private void setMultiplexingSupported(boolean z) {
        this._multiplexingSupported = z;
    }

    private void setNackEnabled(boolean z) {
        this._nackEnabled = z;
    }

    private void setNackPliEnabled(boolean z) {
        this._nackPliEnabled = z;
    }

    private void setRedFecEnabled(boolean z) {
        this._redFecEnabled = z;
    }

    private void setRemoteBandwidth(int i) {
        this._remoteBandwidth = i;
    }

    private void setRemoteDescriptionMediaId(String str) {
        this._remoteDescriptionMediaId = str;
    }

    private void setRemoteDescriptionTrackId(String str) {
        this._remoteDescriptionTrackId = str;
    }

    public void setRemoteDirection(StreamDirection streamDirection) {
        this.__remoteDirection = streamDirection;
    }

    private void setRemoteSupportsCcmFir(boolean z) {
        this._remoteSupportsCcmFir = z;
    }

    private void setRemoteSupportsCcmLrr(boolean z) {
        this._remoteSupportsCcmLrr = z;
    }

    private void setRemoteSupportsCcmTmmbn(boolean z) {
        this._remoteSupportsCcmTmmbn = z;
    }

    private void setRemoteSupportsCcmTmmbr(boolean z) {
        this._remoteSupportsCcmTmmbr = z;
    }

    private void setRemoteSupportsNack(boolean z) {
        this._remoteSupportsNack = z;
    }

    private void setRemoteSupportsNackPli(boolean z) {
        this._remoteSupportsNackPli = z;
    }

    private void setRemoteSupportsRedFec(boolean z) {
        this._remoteSupportsRedFec = z;
    }

    private void setRemoteSupportsRtcpFeedback(boolean z) {
        this.__remoteSupportsRtcpFeedback = z;
    }

    private void setRtpHeaderExtensionRegistry(RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry) {
        this._rtpHeaderExtensionRegistry = rtpHeaderExtensionRegistry;
    }

    private Error setRtpStreamIds(EncodingInfo[] encodingInfoArr, EncodingInfo[] encodingInfoArr2, boolean z, boolean z2) {
        int i;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        if (!z2 && !z && encodingInfoArr != null) {
            String[] remoteReceiveRtpStreamIds = getRemoteReceiveRtpStreamIds();
            if (ArrayExtensions.getLength((Object[]) remoteReceiveRtpStreamIds) > 0) {
                if (ArrayExtensions.getLength((Object[]) encodingInfoArr) > ArrayExtensions.getLength((Object[]) getRemoteReceiveRtpStreamIds())) {
                    return new Error(ErrorCode.LocalDescriptionError, new Exception("Send encoding count in answer must not exceed receive encoding count in offer."));
                }
                for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) encodingInfoArr); i3++) {
                    encodingInfoArr[i3].setRtpStreamId(remoteReceiveRtpStreamIds[i3]);
                    arrayList.add(encodingInfoArr[i3].getRtpStreamId());
                }
            }
        }
        if (!z2 || z) {
            return null;
        }
        if (encodingInfoArr2 != null) {
            int length = encodingInfoArr2.length;
            int i4 = 0;
            i = 0;
            while (i4 < length) {
                EncodingInfo encodingInfo = encodingInfoArr2[i4];
                IntegerHolder integerHolder = new IntegerHolder(i);
                String rtpStreamId = getRtpStreamId(arrayList, i, integerHolder);
                int value = integerHolder.getValue();
                encodingInfo.setRtpStreamId(rtpStreamId);
                i4++;
                i = value;
            }
        } else {
            i = 0;
        }
        if (encodingInfoArr == null) {
            return null;
        }
        int length2 = encodingInfoArr.length;
        while (i2 < length2) {
            EncodingInfo encodingInfo2 = encodingInfoArr[i2];
            IntegerHolder integerHolder2 = new IntegerHolder(i);
            String rtpStreamId2 = getRtpStreamId(arrayList, i, integerHolder2);
            int value2 = integerHolder2.getValue();
            encodingInfo2.setRtpStreamId(rtpStreamId2);
            i2++;
            i = value2;
        }
        return null;
    }

    private boolean updateRtpHeaderExtensionRegistry(Message message, MediaDescription mediaDescription, boolean z, boolean z2, boolean z3, StreamDirection streamDirection) {
        if (z3 && z2 && z) {
            RtpHeaderExtensionRegistryArgs rtpHeaderExtensionRegistryArgs = new RtpHeaderExtensionRegistryArgs();
            rtpHeaderExtensionRegistryArgs.setStreamDirection(streamDirection);
            rtpHeaderExtensionRegistryArgs.setAbsoluteSenderTimePolicy(this.__absoluteSenderTimePolicy);
            rtpHeaderExtensionRegistryArgs.setAbsoluteSenderTimeDirection(this.__absoluteSenderTimeLocalDirection);
            rtpHeaderExtensionRegistryArgs.setSdesMidPolicy(this.__sdesMidPolicy);
            rtpHeaderExtensionRegistryArgs.setSdesMidDirection(this.__sdesMidLocalDirection);
            rtpHeaderExtensionRegistryArgs.setSdesRtpStreamIdPolicy(this.__sdesRtpStreamIdPolicy);
            rtpHeaderExtensionRegistryArgs.setSdesRtpStreamIdDirection(this.__sdesRtpStreamIdLocalDirection);
            rtpHeaderExtensionRegistryArgs.setSdesRepairedRtpStreamIdPolicy(this.__sdesRepairedRtpStreamIdPolicy);
            rtpHeaderExtensionRegistryArgs.setSdesRepairedRtpStreamIdDirection(this.__sdesRepairedRtpStreamIdLocalDirection);
            getRtpHeaderExtensionRegistry().update(rtpHeaderExtensionRegistryArgs);
        }
        Attribute[] sessionLevelRtpExtMapAttributes = message.getSessionLevelRtpExtMapAttributes();
        HashMap hashMap = new HashMap();
        if (sessionLevelRtpExtMapAttributes != null) {
            for (Attribute attribute : sessionLevelRtpExtMapAttributes) {
                ExtMapAttribute extMapAttribute = (ExtMapAttribute) attribute;
                HashMapExtensions.add(hashMap, extMapAttribute.getUri(), extMapAttribute);
            }
        }
        Attribute[] rtpExtMapAttributes = mediaDescription.getRtpExtMapAttributes();
        if (rtpExtMapAttributes != null) {
            for (Attribute attribute2 : rtpExtMapAttributes) {
                ExtMapAttribute extMapAttribute2 = (ExtMapAttribute) attribute2;
                HashMapExtensions.add(hashMap, extMapAttribute2.getUri(), extMapAttribute2);
            }
        }
        ArrayList arrayList = new ArrayList();
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        for (ExtMapAttribute elementFromAttribute : HashMapExtensions.getValues(hashMap)) {
            RtpHeaderExtensionRegistryElement elementFromAttribute2 = RtpHeaderExtensionRegistryElement.getElementFromAttribute(elementFromAttribute);
            if (elementFromAttribute2 != null) {
                if (!z && Global.equals(elementFromAttribute2.getDirection(), StreamDirection.Unset)) {
                    elementFromAttribute2.setDirection(streamDirection);
                }
                if (Global.equals(elementFromAttribute2.getType(), RtpHeaderExtensionType.AbsSendTime)) {
                    if (z) {
                        this.__absoluteSenderTimeLocalDirection = elementFromAttribute2.getDirection();
                    } else {
                        this.__absoluteSenderTimeRemoteDirection = elementFromAttribute2.getDirection();
                    }
                    if (!Global.equals(getAbsoluteSenderTimeDirection(), StreamDirection.Inactive) && !Global.equals(getAbsoluteSenderTimeDirection(), StreamDirection.Unset)) {
                        elementFromAttribute2.setDirection(getAbsoluteSenderTimeDirection());
                        arrayList.add(elementFromAttribute2);
                    }
                    z4 = true;
                } else if (Global.equals(elementFromAttribute2.getType(), RtpHeaderExtensionType.SdesMid)) {
                    if (z) {
                        this.__sdesMidLocalDirection = elementFromAttribute2.getDirection();
                    } else {
                        this.__sdesMidRemoteDirection = elementFromAttribute2.getDirection();
                    }
                    if (!Global.equals(getSdesMidDirection(), StreamDirection.Inactive) && !Global.equals(getSdesMidDirection(), StreamDirection.Unset)) {
                        elementFromAttribute2.setDirection(getSdesMidDirection());
                        arrayList.add(elementFromAttribute2);
                    }
                    z5 = true;
                } else if (Global.equals(elementFromAttribute2.getType(), RtpHeaderExtensionType.SdesRtpStreamId)) {
                    if (z) {
                        this.__sdesRtpStreamIdLocalDirection = elementFromAttribute2.getDirection();
                    } else {
                        this.__sdesRtpStreamIdRemoteDirection = elementFromAttribute2.getDirection();
                    }
                    if (!Global.equals(getSdesRtpStreamIdDirection(), StreamDirection.Inactive) && !Global.equals(getSdesRtpStreamIdDirection(), StreamDirection.Unset)) {
                        elementFromAttribute2.setDirection(getSdesRtpStreamIdDirection());
                        arrayList.add(elementFromAttribute2);
                    }
                    z6 = true;
                } else if (Global.equals(elementFromAttribute2.getType(), RtpHeaderExtensionType.SdesRepairedRtpStreamId)) {
                    if (z) {
                        this.__sdesRepairedRtpStreamIdLocalDirection = elementFromAttribute2.getDirection();
                    } else {
                        this.__sdesRepairedRtpStreamIdRemoteDirection = elementFromAttribute2.getDirection();
                    }
                    if (!Global.equals(getSdesRepairedRtpStreamIdDirection(), StreamDirection.Inactive) && !Global.equals(getSdesRepairedRtpStreamIdDirection(), StreamDirection.Unset)) {
                        elementFromAttribute2.setDirection(getSdesRepairedRtpStreamIdDirection());
                        arrayList.add(elementFromAttribute2);
                    }
                    z7 = true;
                }
            }
        }
        if (z) {
            if (!z4) {
                this.__absoluteSenderTimeLocalDirection = StreamDirection.Inactive;
            }
            if (!z5) {
                this.__sdesMidLocalDirection = StreamDirection.Inactive;
            }
            if (!z6) {
                this.__sdesRtpStreamIdLocalDirection = StreamDirection.Inactive;
            }
            if (!z7) {
                this.__sdesRepairedRtpStreamIdLocalDirection = StreamDirection.Inactive;
            }
        } else {
            if (!z4) {
                this.__absoluteSenderTimeRemoteDirection = StreamDirection.Inactive;
            }
            if (!z5) {
                this.__sdesMidRemoteDirection = StreamDirection.Inactive;
            }
            if (!z6) {
                this.__sdesRtpStreamIdRemoteDirection = StreamDirection.Inactive;
            }
            if (!z7) {
                this.__sdesRepairedRtpStreamIdRemoteDirection = StreamDirection.Inactive;
            }
        }
        getRtpHeaderExtensionRegistry().populate((RtpHeaderExtensionRegistryElement[]) arrayList.toArray(new RtpHeaderExtensionRegistryElement[0]));
        return true;
    }
}
