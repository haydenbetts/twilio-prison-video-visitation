package fm.liveswitch;

import fm.liveswitch.sdp.BundleGroup;
import fm.liveswitch.sdp.ConnectionData;
import fm.liveswitch.sdp.CryptoAttribute;
import fm.liveswitch.sdp.CryptoSuite;
import fm.liveswitch.sdp.Media;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaStreamIdAttribute;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.Setup;
import fm.liveswitch.sdp.SetupAttribute;
import fm.liveswitch.sdp.ice.FingerprintAttribute;
import fm.liveswitch.sdp.ice.PasswordAttribute;
import fm.liveswitch.sdp.ice.UfragAttribute;
import java.util.ArrayList;

abstract class MediaDescriptionManager extends MediaDescriptionManagerBase {
    private CryptoAttribute[] __localCryptoAttributes;
    private CryptoAttribute[] __remoteCryptoAttributes;
    private boolean _bundled;
    private boolean _disabled;
    private boolean _enableIceForInternalTransports;
    private EncryptionMode _encryptionMode;
    private IceRole _iceRole;
    private IceParameters _localIceParameters;
    private IceCandidate _primaryRemoteCandidateFromMLine;
    private DtlsParameters _remoteDtlsParameters;
    private IceParameters _remoteIceParameters;
    private IceCandidate _secondaryRemoteCandidateFromMLine;
    private boolean _useDtls;
    private boolean _useSdes;

    public MediaDescription createSdpMediaDescription(MediaDescriptionRequirements mediaDescriptionRequirements, Message message, boolean z, boolean z2) {
        MediaDescription mediaDescription = new MediaDescription(new Media());
        boolean useIce = mediaDescriptionRequirements.getUseIce();
        if (useIce) {
            mediaDescription.addMediaAttribute(new UfragAttribute(mediaDescriptionRequirements.getLocalIceParameters().getUsernameFragment()));
            mediaDescription.addMediaAttribute(new PasswordAttribute(mediaDescriptionRequirements.getLocalIceParameters().getPassword()));
            if (z) {
                setEnableIceForInternalTransports(true);
            }
        }
        if (mediaDescriptionRequirements.getMediaStreamIdentifier() == null) {
            mediaDescription.addMediaAttribute(new MediaStreamIdAttribute(obtainNewMid(message, mediaDescriptionRequirements.getStreamType())));
        } else {
            mediaDescription.addMediaAttribute(new MediaStreamIdAttribute(mediaDescriptionRequirements.getMediaStreamIdentifier()));
        }
        TransportAddress likelyTransportAddress = mediaDescriptionRequirements.getLikelyTransportAddress();
        String iPAddress = likelyTransportAddress.getIPAddress();
        int port = likelyTransportAddress.getPort();
        mediaDescription.setConnectionData(new ConnectionData(iPAddress));
        Media media = mediaDescription.getMedia();
        if (mediaDescriptionRequirements.getDisabled()) {
            port = 0;
        }
        media.setTransportPort(port);
        setDisabled(mediaDescriptionRequirements.getDisabled());
        for (Candidate candidate : mediaDescriptionRequirements.getIceCandidates()) {
            candidate.setDispatched(true);
            if (useIce) {
                mediaDescription.addMediaAttribute(candidate.getSdpCandidateAttribute());
            }
        }
        setUseDtls(mediaDescriptionRequirements.getUseDtls());
        setUseSdes(mediaDescriptionRequirements.getUseSdes());
        if (Global.equals(mediaDescriptionRequirements.getEncryptionPolicy(), EncryptionPolicy.Disabled)) {
            setUseDtls(false);
            setUseSdes(false);
            setEncryptionMode(EncryptionMode.Null);
        }
        if (Global.equals(mediaDescriptionRequirements.getSdesPolicy(), SdesPolicy.Disabled)) {
            setUseSdes(false);
        }
        if (getUseDtls()) {
            DtlsParameters dtlsParameters = mediaDescriptionRequirements.getDtlsParameters();
            if (dtlsParameters != null) {
                setUseDtls(true);
                DtlsFingerprint fingerprint = dtlsParameters.getFingerprint();
                if (fingerprint != null) {
                    mediaDescription.addMediaAttribute(new FingerprintAttribute(fingerprint.getAlgorithm(), fingerprint.getValue()));
                    if (Global.equals(dtlsParameters.getRole(), DtlsRole.Auto) || (z && z2)) {
                        mediaDescription.addMediaAttribute(new SetupAttribute(Setup.getActPass()));
                    } else if (Global.equals(dtlsParameters.getRole(), DtlsRole.Client)) {
                        mediaDescription.addMediaAttribute(new SetupAttribute(Setup.getActive()));
                    } else if (Global.equals(dtlsParameters.getRole(), DtlsRole.Server)) {
                        mediaDescription.addMediaAttribute(new SetupAttribute(Setup.getPassive()));
                    }
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Cannot create SDP media description for local stream {0}: stream is setup to use DTLS but the DTLS fingerprint has not been set in local DTLS parameters.", (Object) mediaDescriptionRequirements.getStreamId())));
                }
            } else {
                setUseDtls(false);
            }
        }
        if (!getUseSdes() || mediaDescriptionRequirements.getEncryptionModes() == null) {
            return mediaDescription;
        }
        CryptoAttribute[] generateCryptoAttributes = generateCryptoAttributes(mediaDescriptionRequirements.getEncryptionModes());
        this.__localCryptoAttributes = generateCryptoAttributes;
        for (CryptoAttribute addMediaAttribute : generateCryptoAttributes) {
            mediaDescription.addMediaAttribute(addMediaAttribute);
        }
        return mediaDescription;
    }

    static CryptoAttribute[] generateCryptoAttributes(EncryptionMode[] encryptionModeArr) {
        ArrayList arrayList = new ArrayList();
        for (EncryptionMode encryptionMode : encryptionModeArr) {
            if (encryptionMode == EncryptionMode.Aes128Strong || encryptionMode == EncryptionMode.Aes128Weak || encryptionMode == EncryptionMode.NullStrong || encryptionMode == EncryptionMode.NullWeak) {
                arrayList.add(new CryptoAttribute(1, CryptoSuite.getCryptoSuite(encryptionMode)).setKeySalt(BitAssistant.getHexBytes(Utility.generateId()), BitAssistant.subArray(BitAssistant.getHexBytes(Utility.generateId()), 0, 14)));
            }
        }
        return (CryptoAttribute[]) arrayList.toArray(new CryptoAttribute[0]);
    }

    public boolean getBundled() {
        return this._bundled;
    }

    public boolean getDisabled() {
        return this._disabled;
    }

    public boolean getEnableIceForInternalTransports() {
        return this._enableIceForInternalTransports;
    }

    public EncryptionMode getEncryptionMode() {
        return this._encryptionMode;
    }

    public IceRole getIceRole() {
        return this._iceRole;
    }

    public DataBuffer getLocalCryptoKey() {
        CryptoAttribute obtainFirstAes128CryptoAttribute = obtainFirstAes128CryptoAttribute(true);
        if (obtainFirstAes128CryptoAttribute != null) {
            return obtainFirstAes128CryptoAttribute.getKey();
        }
        return null;
    }

    public DataBuffer getLocalCryptoSalt() {
        CryptoAttribute obtainFirstAes128CryptoAttribute = obtainFirstAes128CryptoAttribute(true);
        if (obtainFirstAes128CryptoAttribute != null) {
            return obtainFirstAes128CryptoAttribute.getSalt();
        }
        return null;
    }

    public IceParameters getLocalIceParameters() {
        return this._localIceParameters;
    }

    public IceCandidate getPrimaryRemoteCandidateFromMLine() {
        return this._primaryRemoteCandidateFromMLine;
    }

    public DataBuffer getRemoteCryptoKey() {
        CryptoAttribute obtainFirstAes128CryptoAttribute = obtainFirstAes128CryptoAttribute(false);
        if (obtainFirstAes128CryptoAttribute != null) {
            return obtainFirstAes128CryptoAttribute.getKey();
        }
        return null;
    }

    public DataBuffer getRemoteCryptoSalt() {
        CryptoAttribute obtainFirstAes128CryptoAttribute = obtainFirstAes128CryptoAttribute(false);
        if (obtainFirstAes128CryptoAttribute != null) {
            return obtainFirstAes128CryptoAttribute.getSalt();
        }
        return null;
    }

    public DtlsParameters getRemoteDtlsParameters() {
        return this._remoteDtlsParameters;
    }

    public IceParameters getRemoteIceParameters() {
        return this._remoteIceParameters;
    }

    public IceCandidate getSecondaryRemoteCandidateFromMLine() {
        return this._secondaryRemoteCandidateFromMLine;
    }

    public boolean getUseDtls() {
        return this._useDtls;
    }

    public boolean getUseSdes() {
        return this._useSdes;
    }

    protected MediaDescriptionManager() {
    }

    private CryptoAttribute obtainFirstAes128CryptoAttribute(boolean z) {
        CryptoAttribute[] cryptoAttributeArr = z ? this.__localCryptoAttributes : this.__remoteCryptoAttributes;
        if (cryptoAttributeArr == null) {
            return null;
        }
        for (CryptoAttribute cryptoAttribute : cryptoAttributeArr) {
            if (Global.equals(cryptoAttribute.getCryptoSuite(), CryptoSuite.getAesCM128HmacSha180()) || Global.equals(cryptoAttribute.getCryptoSuite(), CryptoSuite.getAesCm128HmacSha132())) {
                return cryptoAttribute;
            }
        }
        return null;
    }

    static String obtainNewMid(Message message, StreamType streamType) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (MediaDescription media : message.getMediaDescriptions()) {
            String mediaType = media.getMedia().getMediaType();
            if (mediaType.equals("audio")) {
                i++;
            } else if (mediaType.equals("data")) {
                i3++;
            } else if (mediaType.equals("video")) {
                i2++;
            }
        }
        if (Global.equals(streamType, StreamType.Audio)) {
            if (i == 0) {
                return "audio";
            }
            return StringExtensions.concat("audio", IntegerExtensions.toString(Integer.valueOf(i + 1)));
        } else if (Global.equals(streamType, StreamType.Video)) {
            if (i2 == 0) {
                return "video";
            }
            return StringExtensions.concat("video", IntegerExtensions.toString(Integer.valueOf(i + 1)));
        } else if (!Global.equals(streamType, StreamType.Application)) {
            throw new RuntimeException(new Exception(StringExtensions.format("Unsupported stream type {0}.", (Object) streamType.toString())));
        } else if (i3 == 0) {
            return "data";
        } else {
            return StringExtensions.concat("data", IntegerExtensions.toString(Integer.valueOf(i + 1)));
        }
    }

    public Error processSdpMediaDescription(MediaDescriptionRequirementsBase mediaDescriptionRequirementsBase, Message message, int i, boolean z, boolean z2, boolean z3) {
        MediaDescriptionRequirements mediaDescriptionRequirements = (MediaDescriptionRequirements) mediaDescriptionRequirementsBase;
        MediaDescription mediaDescription = message.getMediaDescriptions()[i];
        if ((mediaDescription.getMedia().getTransportPort() == 0 && !mediaDescription.getBundleOnly()) || mediaDescriptionRequirements.getDisabled()) {
            StreamDirection sessionLevelDirection = message.getSessionLevelDirection();
            StreamDirection streamDirection = mediaDescription.getStreamDirection();
            if (mediaDescriptionRequirements.getDisabled() || (!Global.equals(sessionLevelDirection, StreamDirection.Inactive) && !Global.equals(streamDirection, StreamDirection.Inactive))) {
                mediaDescriptionRequirements.setDisabled(true);
                return new Error(ErrorCode.StreamDisabled, new Exception(StringExtensions.format("{0} peer rejected session offer.", (Object) z ? "Local" : "Remote")));
            }
        }
        super.processSdpMediaDescription(mediaDescriptionRequirementsBase, message, i, z, z2, z3);
        setUseDtls(mediaDescriptionRequirements.getUseDtls());
        setUseSdes(mediaDescriptionRequirements.getUseSdes());
        setLocalIceParameters(mediaDescriptionRequirements.getLocalIceParameters());
        UfragAttribute iceUfragAttribute = mediaDescription.getIceUfragAttribute();
        if (iceUfragAttribute == null) {
            iceUfragAttribute = message.getSessionLevelIceUfragAttribute();
        }
        UfragAttribute ufragAttribute = iceUfragAttribute;
        PasswordAttribute icePasswordAttribute = mediaDescription.getIcePasswordAttribute();
        if (icePasswordAttribute == null) {
            icePasswordAttribute = message.getSessionLevelIcePasswordAttribute();
        }
        PasswordAttribute passwordAttribute = icePasswordAttribute;
        setBundled(mediaDescription.getBundleOnly() && !Global.equals(mediaDescriptionRequirements.getBundlePolicy(), BundlePolicy.Disabled));
        if (!getBundled()) {
            BundleGroup[] bundleGroups = message.getBundleGroups();
            boolean z4 = false;
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) bundleGroups) && !getBundled() && !z4; i2++) {
                String[] mids = bundleGroups[i2].getMids();
                for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) mids) && !getBundled() && !z4; i3++) {
                    if (Global.equals(mids[i3], mediaDescription.getMediaStreamIdentifierAttribute().getIdentificationTag())) {
                        if (i3 == 0) {
                            z4 = true;
                        } else {
                            setBundled(Global.equals(mediaDescriptionRequirements.getBundlePolicy(), BundlePolicy.MaxBundle) || (Global.equals(mediaDescriptionRequirements.getBundlePolicy(), BundlePolicy.MaxCompatibility) && (!z3 || !z)));
                        }
                    }
                }
            }
        }
        if (getBundled()) {
            return processSdpMediaDescriptionsForBundledMediaSections();
        }
        return processSdpMediaDescriptionsForNonBundledMediaSections(z, message, mediaDescription, mediaDescriptionRequirements, ufragAttribute, passwordAttribute, z2, z3);
    }

    private Error processSdpMediaDescriptionsForBundledMediaSections() {
        setEnableIceForInternalTransports(false);
        setPrimaryRemoteCandidateFromMLine((IceCandidate) null);
        setSecondaryRemoteCandidateFromMLine((IceCandidate) null);
        this.__remoteCryptoAttributes = null;
        this.__localCryptoAttributes = null;
        setLocalIceParameters((IceParameters) null);
        setRemoteIceParameters((IceParameters) null);
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042 A[Catch:{ Exception -> 0x0425 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063 A[Catch:{ Exception -> 0x0425 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0092 A[Catch:{ Exception -> 0x0425 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private fm.liveswitch.Error processSdpMediaDescriptionsForNonBundledMediaSections(boolean r19, fm.liveswitch.sdp.Message r20, fm.liveswitch.sdp.MediaDescription r21, fm.liveswitch.MediaDescriptionRequirements r22, fm.liveswitch.sdp.ice.UfragAttribute r23, fm.liveswitch.sdp.ice.PasswordAttribute r24, boolean r25, boolean r26) {
        /*
            r18 = this;
            r1 = r18
            r0 = r22
            r2 = r23
            r3 = r24
            r4 = 0
            r5 = 1
            r6 = 0
            if (r19 == 0) goto L_0x0012
            r1.verifyIceSetup(r0, r2, r3, r5)     // Catch:{ Exception -> 0x0425 }
            goto L_0x00ce
        L_0x0012:
            boolean r7 = r1.verifyIceSetup(r0, r2, r3, r6)     // Catch:{ Exception -> 0x0425 }
            if (r7 != 0) goto L_0x00ce
            fm.liveswitch.sdp.Media r7 = r21.getMedia()     // Catch:{ Exception -> 0x0425 }
            int r7 = r7.getTransportPort()     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.sdp.ConnectionData r8 = r21.getConnectionData()     // Catch:{ Exception -> 0x0425 }
            if (r8 == 0) goto L_0x0030
            fm.liveswitch.sdp.ConnectionData r8 = r21.getConnectionData()     // Catch:{ Exception -> 0x0425 }
            java.lang.String r8 = r8.getConnectionAddress()     // Catch:{ Exception -> 0x0425 }
        L_0x002e:
            r15 = r8
            goto L_0x0040
        L_0x0030:
            fm.liveswitch.sdp.ConnectionData r8 = r20.getConnectionData()     // Catch:{ Exception -> 0x0425 }
            if (r8 == 0) goto L_0x003f
            fm.liveswitch.sdp.ConnectionData r8 = r20.getConnectionData()     // Catch:{ Exception -> 0x0425 }
            java.lang.String r8 = r8.getConnectionAddress()     // Catch:{ Exception -> 0x0425 }
            goto L_0x002e
        L_0x003f:
            r15 = r4
        L_0x0040:
            if (r15 == 0) goto L_0x005d
            fm.liveswitch.CandidateType r8 = fm.liveswitch.CandidateType.Unknown     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.ProtocolType r9 = fm.liveswitch.ProtocolType.Unknown     // Catch:{ Exception -> 0x0425 }
            java.lang.String r10 = fm.liveswitch.IceCandidate.generateLocalCandidateFoundation(r8, r15, r4, r9)     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.IceCandidate r14 = new fm.liveswitch.IceCandidate     // Catch:{ Exception -> 0x0425 }
            r9 = 0
            fm.liveswitch.ProtocolType r11 = fm.liveswitch.ProtocolType.Udp     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.CandidateType r16 = fm.liveswitch.CandidateType.Unknown     // Catch:{ Exception -> 0x0425 }
            r8 = r14
            r12 = r15
            r13 = r7
            r6 = r14
            r14 = r16
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0425 }
            r1.setPrimaryRemoteCandidateFromMLine(r6)     // Catch:{ Exception -> 0x0425 }
        L_0x005d:
            fm.liveswitch.sdp.rtcp.Attribute r6 = r21.getRtcpAttribute()     // Catch:{ Exception -> 0x0425 }
            if (r6 == 0) goto L_0x0092
            fm.liveswitch.sdp.rtcp.Attribute r6 = r21.getRtcpAttribute()     // Catch:{ Exception -> 0x0425 }
            java.lang.String r12 = r6.getConnectionAddress()     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.sdp.rtcp.Attribute r6 = r21.getRtcpAttribute()     // Catch:{ Exception -> 0x0425 }
            int r13 = r6.getPort()     // Catch:{ Exception -> 0x0425 }
            boolean r6 = fm.liveswitch.Global.equals(r15, r12)     // Catch:{ Exception -> 0x0425 }
            if (r6 == 0) goto L_0x007b
            if (r7 == r13) goto L_0x00ce
        L_0x007b:
            fm.liveswitch.CandidateType r6 = fm.liveswitch.CandidateType.Unknown     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.ProtocolType r7 = fm.liveswitch.ProtocolType.Unknown     // Catch:{ Exception -> 0x0425 }
            java.lang.String r10 = fm.liveswitch.IceCandidate.generateLocalCandidateFoundation(r6, r12, r4, r7)     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.IceCandidate r6 = new fm.liveswitch.IceCandidate     // Catch:{ Exception -> 0x0425 }
            r9 = 0
            fm.liveswitch.ProtocolType r11 = fm.liveswitch.ProtocolType.Udp     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.CandidateType r14 = fm.liveswitch.CandidateType.Unknown     // Catch:{ Exception -> 0x0425 }
            r8 = r6
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0425 }
            r1.setSecondaryRemoteCandidateFromMLine(r6)     // Catch:{ Exception -> 0x0425 }
            goto L_0x00ce
        L_0x0092:
            r6 = 9
            if (r7 != r6) goto L_0x0099
            r14 = 9
            goto L_0x00a8
        L_0x0099:
            r6 = 1023(0x3ff, float:1.434E-42)
            if (r7 <= r6) goto L_0x00a5
            r6 = 65535(0xffff, float:9.1834E-41)
            if (r7 >= r6) goto L_0x00a5
            int r7 = r7 + r5
            r14 = r7
            goto L_0x00a8
        L_0x00a5:
            if (r7 != 0) goto L_0x00c1
            r14 = 0
        L_0x00a8:
            fm.liveswitch.CandidateType r6 = fm.liveswitch.CandidateType.Unknown     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.ProtocolType r7 = fm.liveswitch.ProtocolType.Unknown     // Catch:{ Exception -> 0x0425 }
            java.lang.String r11 = fm.liveswitch.IceCandidate.generateLocalCandidateFoundation(r6, r15, r4, r7)     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.IceCandidate r6 = new fm.liveswitch.IceCandidate     // Catch:{ Exception -> 0x0425 }
            r10 = 0
            fm.liveswitch.ProtocolType r12 = fm.liveswitch.ProtocolType.Udp     // Catch:{ Exception -> 0x0425 }
            fm.liveswitch.CandidateType r7 = fm.liveswitch.CandidateType.Unknown     // Catch:{ Exception -> 0x0425 }
            r9 = r6
            r13 = r15
            r15 = r7
            r9.<init>(r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0425 }
            r1.setSecondaryRemoteCandidateFromMLine(r6)     // Catch:{ Exception -> 0x0425 }
            goto L_0x00ce
        L_0x00c1:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x0425 }
            java.lang.Exception r2 = new java.lang.Exception     // Catch:{ Exception -> 0x0425 }
            java.lang.String r3 = "SDP description contains invalid port assignment for the default primary (RTP) candidate. Cannot identify the port for the default candidate for the secondary (RTCP) component."
            r2.<init>(r3)     // Catch:{ Exception -> 0x0425 }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0425 }
            throw r0     // Catch:{ Exception -> 0x0425 }
        L_0x00ce:
            fm.liveswitch.sdp.ice.FingerprintAttribute r6 = r21.getFingerprintAttribute()
            if (r6 != 0) goto L_0x00d8
            fm.liveswitch.sdp.ice.FingerprintAttribute r6 = r20.getSessionLevelFingerprintAttribute()
        L_0x00d8:
            fm.liveswitch.sdp.SetupAttribute r7 = r21.getSetupAttribute()
            if (r7 != 0) goto L_0x00e2
            fm.liveswitch.sdp.SetupAttribute r7 = r20.getSessionLevelSetupAttribute()
        L_0x00e2:
            fm.liveswitch.sdp.CryptoAttribute[] r8 = r21.getCryptoAttributes()
            if (r8 != 0) goto L_0x00ec
            fm.liveswitch.sdp.CryptoAttribute[] r8 = r20.getSessionLevelCryptoAttributes()
        L_0x00ec:
            if (r6 != 0) goto L_0x00f6
            if (r8 == 0) goto L_0x015d
            int r9 = fm.liveswitch.ArrayExtensions.getLength((java.lang.Object[]) r8)
            if (r9 <= 0) goto L_0x015d
        L_0x00f6:
            fm.liveswitch.sdp.Media r9 = r21.getMedia()
            java.lang.String r9 = r9.getTransportProtocol()
            fm.liveswitch.StreamType r10 = r22.getStreamType()
            fm.liveswitch.StreamType r11 = fm.liveswitch.StreamType.Application
            boolean r10 = fm.liveswitch.Global.equals(r10, r11)
            if (r10 == 0) goto L_0x011c
            boolean r9 = fm.liveswitch.sdp.sctp.Media.supportsEncryption(r9)
            if (r9 != 0) goto L_0x015d
            fm.liveswitch.sdp.Media r9 = r21.getMedia()
            java.lang.String r10 = fm.liveswitch.sdp.sctp.Media.getDtlsSctpTransportProtocol()
            r9.setTransportProtocol(r10)
            goto L_0x015d
        L_0x011c:
            boolean r9 = fm.liveswitch.sdp.rtp.Media.supportsEncryption(r9)
            if (r9 != 0) goto L_0x015d
            fm.liveswitch.sdp.Media r9 = r21.getMedia()
            java.lang.String r9 = r9.getTransportProtocol()
            java.lang.String r10 = fm.liveswitch.sdp.rtp.Media.getRtpAvpfTransportProtocol()
            boolean r9 = fm.liveswitch.Global.equals(r9, r10)
            if (r9 == 0) goto L_0x0140
            fm.liveswitch.sdp.Media r9 = r21.getMedia()
            java.lang.String r10 = fm.liveswitch.sdp.rtp.Media.getRtpSavpfTransportProtocol()
            r9.setTransportProtocol(r10)
            goto L_0x015d
        L_0x0140:
            fm.liveswitch.sdp.Media r9 = r21.getMedia()
            java.lang.String r9 = r9.getTransportProtocol()
            java.lang.String r10 = fm.liveswitch.sdp.rtp.Media.getRtpAvpTransportProtocol()
            boolean r9 = fm.liveswitch.Global.equals(r9, r10)
            if (r9 == 0) goto L_0x015d
            fm.liveswitch.sdp.Media r9 = r21.getMedia()
            java.lang.String r10 = fm.liveswitch.sdp.rtp.Media.getRtpSavpTransportProtocol()
            r9.setTransportProtocol(r10)
        L_0x015d:
            if (r25 != 0) goto L_0x0423
            fm.liveswitch.sdp.Media r9 = r21.getMedia()
            java.lang.String r9 = r9.getTransportProtocol()
            fm.liveswitch.StreamType r10 = r22.getStreamType()
            fm.liveswitch.StreamType r11 = fm.liveswitch.StreamType.Application
            boolean r10 = fm.liveswitch.Global.equals(r10, r11)
            if (r10 == 0) goto L_0x0178
            boolean r9 = fm.liveswitch.sdp.sctp.Media.supportsEncryption(r9)
            goto L_0x017c
        L_0x0178:
            boolean r9 = fm.liveswitch.sdp.rtp.Media.supportsEncryption(r9)
        L_0x017c:
            if (r19 != 0) goto L_0x01e1
            fm.liveswitch.EncryptionPolicy r10 = r22.getEncryptionPolicy()
            fm.liveswitch.EncryptionPolicy r11 = fm.liveswitch.EncryptionPolicy.Disabled
            boolean r10 = fm.liveswitch.Global.equals(r10, r11)
            if (r10 != 0) goto L_0x01e1
            fm.liveswitch.EncryptionPolicy r10 = r22.getEncryptionPolicy()
            fm.liveswitch.EncryptionPolicy r11 = fm.liveswitch.EncryptionPolicy.Negotiated
            boolean r10 = fm.liveswitch.Global.equals(r10, r11)
            if (r10 == 0) goto L_0x01af
            if (r9 != 0) goto L_0x01e1
            java.lang.String r9 = r22.getStreamId()
            java.lang.String r10 = "Remote peer does not support encryption for stream {0}. Disabling encryption support locally."
            java.lang.String r9 = fm.liveswitch.StringExtensions.format((java.lang.String) r10, (java.lang.Object) r9)
            fm.liveswitch.Log.debug(r9)
            r9 = 0
            r1.setUseDtls(r9)
            fm.liveswitch.EncryptionMode r9 = fm.liveswitch.EncryptionMode.Null
            r1.setEncryptionMode(r9)
            goto L_0x01e1
        L_0x01af:
            fm.liveswitch.EncryptionPolicy r10 = r22.getEncryptionPolicy()
            fm.liveswitch.EncryptionPolicy r11 = fm.liveswitch.EncryptionPolicy.Required
            boolean r10 = fm.liveswitch.Global.equals(r10, r11)
            if (r10 == 0) goto L_0x01e1
            if (r9 != 0) goto L_0x01e1
            java.lang.String r2 = r22.getStreamId()
            java.lang.String r3 = "Remote peer does not support encryption for stream {0}. Local policy is set to Require. Cannot proceed."
            java.lang.String r2 = fm.liveswitch.StringExtensions.format((java.lang.String) r3, (java.lang.Object) r2)
            fm.liveswitch.Log.debug(r2)
            fm.liveswitch.Error r2 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.StreamEncryptionMismatch
            java.lang.Exception r4 = new java.lang.Exception
            java.lang.String r0 = r22.getStreamId()
            java.lang.String r5 = "Remote peer does not support encryption for stream {0}. Local policy is set to Require.Cannot proceed."
            java.lang.String r0 = fm.liveswitch.StringExtensions.format((java.lang.String) r5, (java.lang.Object) r0)
            r4.<init>(r0)
            r2.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r4)
            return r2
        L_0x01e1:
            boolean r9 = r18.getUseDtls()
            if (r6 == 0) goto L_0x01ef
            boolean r10 = r18.getUseDtls()
            if (r10 == 0) goto L_0x01ef
            r10 = 1
            goto L_0x01f0
        L_0x01ef:
            r10 = 0
        L_0x01f0:
            r1.setUseDtls(r10)
            boolean r10 = r18.getUseDtls()
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            boolean r9 = fm.liveswitch.Global.equals(r10, r9)
            if (r9 != 0) goto L_0x0218
            if (r26 != 0) goto L_0x0218
            if (r19 == 0) goto L_0x0218
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.LocalDescriptionError
            java.lang.Exception r3 = new java.lang.Exception
            java.lang.String r4 = "The endpoint in answering mode attempted to modify encryption algorithm in the local session description."
            r3.<init>(r4)
            r0.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r3)
            return r0
        L_0x0218:
            if (r26 == 0) goto L_0x0227
            if (r19 == 0) goto L_0x0222
            fm.liveswitch.IceRole r9 = fm.liveswitch.IceRole.Controlling
            r1.setIceRole(r9)
            goto L_0x0227
        L_0x0222:
            fm.liveswitch.IceRole r9 = fm.liveswitch.IceRole.Controlled
            r1.setIceRole(r9)
        L_0x0227:
            if (r19 == 0) goto L_0x02b8
            r1.__localCryptoAttributes = r8
            if (r7 == 0) goto L_0x03fe
            if (r26 == 0) goto L_0x024c
            java.lang.String r0 = r7.getSetup()
            java.lang.String r5 = fm.liveswitch.sdp.Setup.getActPass()
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)
            if (r0 != 0) goto L_0x024c
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.LocalDescriptionError
            java.lang.Exception r3 = new java.lang.Exception
            java.lang.String r4 = "The endpoint that is the offerer MUST use the setup attribute value of setup:actpass"
            r3.<init>(r4)
            r0.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r3)
            return r0
        L_0x024c:
            if (r26 != 0) goto L_0x026b
            java.lang.String r0 = r7.getSetup()
            java.lang.String r5 = fm.liveswitch.sdp.Setup.getActPass()
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)
            if (r0 == 0) goto L_0x026b
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.LocalDescriptionError
            java.lang.Exception r3 = new java.lang.Exception
            java.lang.String r4 = "The endpoint that is the answerer MUST use either a setup attribute value of setup:active or setup:passive"
            r3.<init>(r4)
            r0.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r3)
            return r0
        L_0x026b:
            if (r26 != 0) goto L_0x03fe
            java.lang.String r0 = r7.getSetup()
            java.lang.String r5 = fm.liveswitch.sdp.Setup.getPassive()
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)
            if (r0 == 0) goto L_0x028b
            fm.liveswitch.DtlsParameters r0 = r18.getRemoteDtlsParameters()
            fm.liveswitch.DtlsRole r0 = r0.getRole()
            fm.liveswitch.DtlsRole r5 = fm.liveswitch.DtlsRole.Server
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)
            if (r0 != 0) goto L_0x02a9
        L_0x028b:
            java.lang.String r0 = r7.getSetup()
            java.lang.String r5 = fm.liveswitch.sdp.Setup.getActive()
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)
            if (r0 == 0) goto L_0x03fe
            fm.liveswitch.DtlsParameters r0 = r18.getRemoteDtlsParameters()
            fm.liveswitch.DtlsRole r0 = r0.getRole()
            fm.liveswitch.DtlsRole r5 = fm.liveswitch.DtlsRole.Client
            boolean r0 = fm.liveswitch.Global.equals(r0, r5)
            if (r0 == 0) goto L_0x03fe
        L_0x02a9:
            fm.liveswitch.Error r0 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r2 = fm.liveswitch.ErrorCode.LocalDescriptionError
            java.lang.Exception r3 = new java.lang.Exception
            java.lang.String r4 = "The endpoint that is the answerer attempted to modify local Dtls role that has already been negotiated with the offerer"
            r3.<init>(r4)
            r0.<init>((fm.liveswitch.ErrorCode) r2, (java.lang.Exception) r3)
            return r0
        L_0x02b8:
            r1.__remoteCryptoAttributes = r8
            if (r8 != 0) goto L_0x02c1
            r8 = 0
            fm.liveswitch.sdp.CryptoAttribute[] r9 = new fm.liveswitch.sdp.CryptoAttribute[r8]
            r1.__remoteCryptoAttributes = r9
        L_0x02c1:
            fm.liveswitch.sdp.CryptoAttribute[] r8 = r1.__localCryptoAttributes
            if (r8 != 0) goto L_0x02cf
            fm.liveswitch.EncryptionMode[] r8 = r22.getEncryptionModes()
            fm.liveswitch.sdp.CryptoAttribute[] r8 = generateCryptoAttributes(r8)
            r1.__localCryptoAttributes = r8
        L_0x02cf:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            fm.liveswitch.sdp.CryptoAttribute[] r9 = r1.__remoteCryptoAttributes
            int r10 = r9.length
            r11 = 0
        L_0x02d8:
            if (r11 >= r10) goto L_0x034a
            r12 = r9[r11]
            java.lang.String r13 = r12.getCryptoSuite()
            java.lang.String r14 = fm.liveswitch.sdp.CryptoSuite.getAesCM128HmacSha180()
            boolean r13 = fm.liveswitch.Global.equals(r13, r14)
            if (r13 == 0) goto L_0x02f0
            fm.liveswitch.EncryptionMode r13 = fm.liveswitch.EncryptionMode.Aes128Strong
            r8.add(r13)
            goto L_0x032b
        L_0x02f0:
            java.lang.String r13 = r12.getCryptoSuite()
            java.lang.String r14 = fm.liveswitch.sdp.CryptoSuite.getAesCm128HmacSha132()
            boolean r13 = fm.liveswitch.Global.equals(r13, r14)
            if (r13 == 0) goto L_0x0304
            fm.liveswitch.EncryptionMode r13 = fm.liveswitch.EncryptionMode.Aes128Weak
            r8.add(r13)
            goto L_0x032b
        L_0x0304:
            java.lang.String r13 = r12.getCryptoSuite()
            java.lang.String r14 = fm.liveswitch.sdp.CryptoSuite.getNullHmacSha180()
            boolean r13 = fm.liveswitch.Global.equals(r13, r14)
            if (r13 == 0) goto L_0x0318
            fm.liveswitch.EncryptionMode r13 = fm.liveswitch.EncryptionMode.NullStrong
            r8.add(r13)
            goto L_0x032b
        L_0x0318:
            java.lang.String r13 = r12.getCryptoSuite()
            java.lang.String r14 = fm.liveswitch.sdp.CryptoSuite.getNullHmacSha132()
            boolean r13 = fm.liveswitch.Global.equals(r13, r14)
            if (r13 == 0) goto L_0x032b
            fm.liveswitch.EncryptionMode r13 = fm.liveswitch.EncryptionMode.NullWeak
            r8.add(r13)
        L_0x032b:
            fm.liveswitch.sdp.CryptoAttribute[] r13 = r1.__localCryptoAttributes
            int r14 = r13.length
            r15 = 0
        L_0x032f:
            if (r15 >= r14) goto L_0x0345
            r4 = r13[r15]
            boolean r17 = fm.liveswitch.Global.equals(r12, r4)
            if (r17 == 0) goto L_0x0340
            int r5 = r12.getTag()
            r4.setTag(r5)
        L_0x0340:
            int r15 = r15 + 1
            r4 = 0
            r5 = 1
            goto L_0x032f
        L_0x0345:
            int r11 = r11 + 1
            r4 = 0
            r5 = 1
            goto L_0x02d8
        L_0x034a:
            fm.liveswitch.DtlsRole r4 = fm.liveswitch.DtlsRole.Auto
            if (r7 == 0) goto L_0x036f
            java.lang.String r5 = r7.getSetup()
            java.lang.String r9 = fm.liveswitch.sdp.Setup.getActive()
            boolean r5 = fm.liveswitch.Global.equals(r5, r9)
            if (r5 == 0) goto L_0x035f
            fm.liveswitch.DtlsRole r4 = fm.liveswitch.DtlsRole.Client
            goto L_0x036f
        L_0x035f:
            java.lang.String r5 = r7.getSetup()
            java.lang.String r7 = fm.liveswitch.sdp.Setup.getPassive()
            boolean r5 = fm.liveswitch.Global.equals(r5, r7)
            if (r5 == 0) goto L_0x036f
            fm.liveswitch.DtlsRole r4 = fm.liveswitch.DtlsRole.Server
        L_0x036f:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            fm.liveswitch.EncryptionPolicy r7 = r22.getEncryptionPolicy()
            fm.liveswitch.EncryptionPolicy r9 = fm.liveswitch.EncryptionPolicy.Disabled
            boolean r7 = fm.liveswitch.Global.equals(r7, r9)
            if (r7 != 0) goto L_0x03dc
            boolean r7 = r18.getUseDtls()
            if (r7 == 0) goto L_0x03aa
            fm.liveswitch.DtlsParameters r7 = r22.getDtlsParameters()
            fm.liveswitch.DtlsRole r4 = r7.negotiate(r4)
            r7 = 1
            fm.liveswitch.DtlsFingerprint[] r7 = new fm.liveswitch.DtlsFingerprint[r7]
            fm.liveswitch.DtlsFingerprint r9 = new fm.liveswitch.DtlsFingerprint
            java.lang.String r10 = r6.getHashFunction()
            java.lang.String r6 = r6.getFingerprint()
            r9.<init>(r10, r6)
            r6 = 0
            r7[r6] = r9
            fm.liveswitch.DtlsParameters r6 = new fm.liveswitch.DtlsParameters
            r6.<init>(r4, r7)
            r1.setRemoteDtlsParameters(r6)
            goto L_0x03b5
        L_0x03aa:
            int r4 = fm.liveswitch.ArrayListExtensions.getCount(r8)
            if (r4 != 0) goto L_0x03b5
            fm.liveswitch.EncryptionMode r4 = fm.liveswitch.EncryptionMode.Null
            r8.add(r4)
        L_0x03b5:
            java.util.Iterator r4 = r8.iterator()
        L_0x03b9:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x03dc
            java.lang.Object r6 = r4.next()
            fm.liveswitch.EncryptionMode r6 = (fm.liveswitch.EncryptionMode) r6
            fm.liveswitch.EncryptionMode[] r7 = r22.getEncryptionModes()
            int r8 = r7.length
            r9 = 0
        L_0x03cb:
            if (r9 >= r8) goto L_0x03b9
            r10 = r7[r9]
            boolean r10 = fm.liveswitch.Global.equals(r6, r10)
            if (r10 == 0) goto L_0x03d9
            r5.add(r6)
            goto L_0x03b9
        L_0x03d9:
            int r9 = r9 + 1
            goto L_0x03cb
        L_0x03dc:
            int r0 = fm.liveswitch.ArrayListExtensions.getCount(r5)
            if (r0 != 0) goto L_0x03e7
            fm.liveswitch.EncryptionMode r0 = fm.liveswitch.EncryptionMode.Null
            r5.add(r0)
        L_0x03e7:
            boolean r0 = r18.getUseDtls()
            if (r0 == 0) goto L_0x03f0
            fm.liveswitch.EncryptionMode r0 = fm.liveswitch.EncryptionMode.Aes128Strong
            goto L_0x03fb
        L_0x03f0:
            java.util.ArrayList r0 = fm.liveswitch.ArrayListExtensions.getItem(r5)
            r4 = 0
            java.lang.Object r0 = r0.get(r4)
            fm.liveswitch.EncryptionMode r0 = (fm.liveswitch.EncryptionMode) r0
        L_0x03fb:
            r1.setEncryptionMode(r0)
        L_0x03fe:
            java.lang.String r0 = fm.liveswitch.StringExtensions.empty
            if (r2 == 0) goto L_0x0406
            java.lang.String r0 = r23.getUfrag()
        L_0x0406:
            if (r3 == 0) goto L_0x040d
            java.lang.String r2 = r24.getPassword()
            goto L_0x040e
        L_0x040d:
            r2 = 0
        L_0x040e:
            if (r19 == 0) goto L_0x0419
            fm.liveswitch.IceParameters r3 = new fm.liveswitch.IceParameters
            r3.<init>(r0, r2)
            r1.setLocalIceParameters(r3)
            goto L_0x0421
        L_0x0419:
            fm.liveswitch.IceParameters r3 = new fm.liveswitch.IceParameters
            r3.<init>(r0, r2)
            r1.setRemoteIceParameters(r3)
        L_0x0421:
            r0 = 0
            goto L_0x0424
        L_0x0423:
            r0 = r4
        L_0x0424:
            return r0
        L_0x0425:
            r0 = move-exception
            fm.liveswitch.Error r2 = new fm.liveswitch.Error
            fm.liveswitch.ErrorCode r3 = fm.liveswitch.ErrorCode.IncompatibleIceSetup
            r2.<init>((fm.liveswitch.ErrorCode) r3, (java.lang.Exception) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.MediaDescriptionManager.processSdpMediaDescriptionsForNonBundledMediaSections(boolean, fm.liveswitch.sdp.Message, fm.liveswitch.sdp.MediaDescription, fm.liveswitch.MediaDescriptionRequirements, fm.liveswitch.sdp.ice.UfragAttribute, fm.liveswitch.sdp.ice.PasswordAttribute, boolean, boolean):fm.liveswitch.Error");
    }

    private void setBundled(boolean z) {
        this._bundled = z;
    }

    private void setDisabled(boolean z) {
        this._disabled = z;
    }

    private void setEnableIceForInternalTransports(boolean z) {
        this._enableIceForInternalTransports = z;
    }

    private void setEncryptionMode(EncryptionMode encryptionMode) {
        this._encryptionMode = encryptionMode;
    }

    private void setIceRole(IceRole iceRole) {
        this._iceRole = iceRole;
    }

    private void setLocalIceParameters(IceParameters iceParameters) {
        this._localIceParameters = iceParameters;
    }

    private void setPrimaryRemoteCandidateFromMLine(IceCandidate iceCandidate) {
        this._primaryRemoteCandidateFromMLine = iceCandidate;
    }

    private void setRemoteDtlsParameters(DtlsParameters dtlsParameters) {
        this._remoteDtlsParameters = dtlsParameters;
    }

    private void setRemoteIceParameters(IceParameters iceParameters) {
        this._remoteIceParameters = iceParameters;
    }

    private void setSecondaryRemoteCandidateFromMLine(IceCandidate iceCandidate) {
        this._secondaryRemoteCandidateFromMLine = iceCandidate;
    }

    private void setUseDtls(boolean z) {
        this._useDtls = z;
    }

    private void setUseSdes(boolean z) {
        this._useSdes = z;
    }

    private boolean verifyIceSetup(MediaDescriptionRequirements mediaDescriptionRequirements, UfragAttribute ufragAttribute, PasswordAttribute passwordAttribute, boolean z) {
        IcePolicy icePolicy = mediaDescriptionRequirements.getIcePolicy();
        boolean z2 = true;
        boolean z3 = (passwordAttribute == null || ufragAttribute == null) ? false : true;
        if (icePolicy != IcePolicy.Required) {
            if (icePolicy == IcePolicy.Negotiated) {
                if (!z3) {
                    if (z) {
                        Log.debug(StringExtensions.format("Local client does not support ICE for stream {0} of type {1} because ice-password and username have been removed from description. Ice will be disabled for this stream.", mediaDescriptionRequirements.getStreamId(), mediaDescriptionRequirements.getStreamType().toString()));
                    } else {
                        Log.debug(StringExtensions.format("Remote client does not support ICE for stream {0} of type {1}. Ice will be disabled for this stream.", mediaDescriptionRequirements.getStreamId(), mediaDescriptionRequirements.getStreamType().toString()));
                    }
                } else if (!z) {
                    Log.debug(StringExtensions.format("Remote client supports ICE for stream {0} of type {1}. Ice will be enabled for this stream.", mediaDescriptionRequirements.getStreamId(), mediaDescriptionRequirements.getStreamType().toString()));
                }
            }
            z2 = false;
        } else if (!z3) {
            if (z) {
                throw new RuntimeException(new Exception(StringExtensions.format("Local IcePolicy is set to Required, but the ice username and password have been removed from the local description effectively disabling support for ICE for stream {0} of type {1}.", mediaDescriptionRequirements.getStreamId(), mediaDescriptionRequirements.getStreamType().toString())));
            }
            throw new RuntimeException(new Exception(StringExtensions.format("Local IcePolicy is set to Required, but the remote client does not support ICE for stream {0} of type {1}.", mediaDescriptionRequirements.getStreamId(), mediaDescriptionRequirements.getStreamType().toString())));
        }
        setEnableIceForInternalTransports(z2);
        return z2;
    }
}
