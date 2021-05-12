package fm.liveswitch;

import fm.liveswitch.sdp.Attribute;
import fm.liveswitch.sdp.FormatParametersAttribute;
import fm.liveswitch.sdp.Media;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.Message;
import fm.liveswitch.sdp.Origin;
import fm.liveswitch.sdp.ice.CandidateAttribute;
import fm.liveswitch.sdp.rtcp.FeedbackAttribute;
import fm.liveswitch.sdp.rtp.ExtMapAttribute;
import fm.liveswitch.sdp.rtp.MapAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import tvi.webrtc.VideoCodecInfo;

class SessionDescriptionManager extends SessionDescriptionManagerBase<Stream, AudioStream, VideoStream, DataStream, DataChannel> {
    private static ILog __log = Log.getLogger(SessionDescriptionManager.class);
    private int[] __availablePayloadsPriority = new int[0];
    private ArrayList<Pair<Stream, Integer>> __disablePrimaryComponents = new ArrayList<>();
    private ArrayList<Pair<Stream, Integer>> __disableSecondaryComponents = new ArrayList<>();
    private SessionDescription __localDescription;
    private long __localSignallingSessionId = -1;
    private long __localSignallingSessionVersion = 0;
    private HashMap<String, MapAttribute> __payloadsByDescription = new HashMap<>();
    private HashMap<String, MapAttribute> __payloadsByPT = new HashMap<>();
    private ArrayList<Candidate> __remoteCandidates = new ArrayList<>();
    private SessionDescription __remoteDescription;
    private HashMap<String, Stream> __streams = new HashMap<>();
    private MultiplexPolicy _multiplexPolicy;
    private boolean _originalSignallingExchangeComplete;

    static void assignNewPayloadType(MapAttribute mapAttribute, int i, Media media) {
        int payloadType = mapAttribute.getPayloadType();
        mapAttribute.setPayloadType(i);
        if (mapAttribute.getRelatedFormatParametersAttribute() != null) {
            mapAttribute.getRelatedFormatParametersAttribute().setFormat(i);
        }
        FeedbackAttribute[] relatedRtcpFeedbackAttributes = mapAttribute.getRelatedRtcpFeedbackAttributes();
        if (relatedRtcpFeedbackAttributes != null) {
            for (FeedbackAttribute payloadType2 : relatedRtcpFeedbackAttributes) {
                payloadType2.setPayloadType(i);
            }
        }
        if (media != null && media.getFormatDescription() != null) {
            String[] split = StringExtensions.split(media.getFormatDescription(), new char[]{' '});
            int length = ArrayExtensions.getLength((Object[]) split);
            for (int i2 = 0; i2 < length; i2++) {
                if (Global.equals(split[i2], IntegerExtensions.toString(Integer.valueOf(payloadType)))) {
                    split[i2] = IntegerExtensions.toString(Integer.valueOf(i));
                    media.setFormatDescription(StringExtensions.join(" ", split));
                }
            }
        }
    }

    public void clear() {
        this.__remoteCandidates.clear();
        this.__disableSecondaryComponents.clear();
    }

    public void createAnswer(SessionDescriptionRequirements sessionDescriptionRequirements, Promise<SessionDescription> promise) {
        createOfferAnswer(false, sessionDescriptionRequirements, promise);
    }

    static int[] createDynamicPayloadPriorityQueue() {
        ArrayList arrayList = new ArrayList();
        for (int i = 96; i <= 127; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        for (int i2 = 35; i2 <= 63; i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        for (int i3 = 20; i3 <= 24; i3++) {
            arrayList.add(Integer.valueOf(i3));
        }
        arrayList.add(27);
        arrayList.add(29);
        arrayList.add(30);
        return Utility.toIntArray(arrayList);
    }

    public void createOffer(SessionDescriptionRequirements sessionDescriptionRequirements, Promise<SessionDescription> promise) {
        createOfferAnswer(true, sessionDescriptionRequirements, promise);
    }

    private void createOfferAnswer(boolean z, SessionDescriptionRequirements sessionDescriptionRequirements, Promise<SessionDescription> promise) {
        if (z) {
            this.__payloadsByPT.clear();
            this.__payloadsByDescription.clear();
            this.__availablePayloadsPriority = createDynamicPayloadPriorityQueue();
        }
        try {
            SessionDescription sessionDescription = new SessionDescription();
            sessionDescription.setType(z ? SessionDescriptionType.Offer : SessionDescriptionType.Answer);
            sessionDescription.setTieBreaker(sessionDescriptionRequirements.getTieBreaker());
            sessionDescription.setRenegotiation(getOriginalSignallingExchangeComplete());
            Origin origin = new Origin("127.0.0.1");
            long j = this.__localSignallingSessionVersion + 1;
            this.__localSignallingSessionVersion = j;
            origin.setSessionVersion(j);
            if (getOriginalSignallingExchangeComplete()) {
                origin.setSessionId(this.__localSignallingSessionId);
            } else {
                this.__localSignallingSessionId = origin.getSessionId();
            }
            setMultiplexPolicy(sessionDescriptionRequirements.getMultiplexPolicy());
            Message message = new Message(origin, "Frozen Mountain");
            SessionDescriptionManagerBase.updateTrickleIcePolicy(message, sessionDescriptionRequirements.getTrickleIcePolicy());
            Iterator<TStream> it = this.__streamIndex.iterator();
            while (true) {
                boolean z2 = true;
                boolean z3 = false;
                if (it.hasNext()) {
                    Stream stream = (Stream) it.next();
                    boolean z4 = !Global.equals(stream.getType(), StreamType.Application) && Global.equals(stream.getSdesPolicy(), SdesPolicy.Negotiated) && !Global.equals(stream.getEncryptionPolicy(), EncryptionPolicy.Disabled);
                    boolean z5 = getOriginalSignallingExchangeComplete() && !stream.getUseDtls();
                    boolean z6 = !getOriginalSignallingExchangeComplete() && (z || !stream.getUseDtls());
                    if (z4 && ((z5 || z6) && stream.getUseSdes())) {
                        z3 = true;
                    }
                    stream.setUseSdes(z3);
                    MediaDescription createSdpMediaDescription = stream.createSdpMediaDescription(message, z3, z, getOriginalSignallingExchangeComplete(), sessionDescriptionRequirements.getBundlePolicy());
                    if (!StringExtensions.isNullOrEmpty(createSdpMediaDescription.getMedia().getFormatDescription())) {
                        message.addMediaDescription(createSdpMediaDescription);
                    } else if (z) {
                        promise.reject(new Exception(StringExtensions.format("No codecs in {0} stream.", (Object) stream.getType().toString())));
                        return;
                    } else {
                        promise.reject(new Exception(StringExtensions.format("No matching codecs in {0} stream.", (Object) stream.getType().toString())));
                        return;
                    }
                } else {
                    sortMediaDescriptions(message);
                    HashMap<String, MapAttribute> hashMap = this.__payloadsByPT;
                    HashMap<String, MapAttribute> hashMap2 = this.__payloadsByDescription;
                    int[] iArr = this.__availablePayloadsPriority;
                    if (z) {
                        z2 = false;
                    }
                    this.__availablePayloadsPriority = synchronisePayloads(message, hashMap, hashMap2, iArr, z2);
                    sessionDescription.setSdpMessage(message);
                    if (!Global.equals(sessionDescriptionRequirements.getBundlePolicy(), BundlePolicy.Disabled)) {
                        BundleDescriptionManager.updateDescription(sessionDescription, sessionDescriptionRequirements.getBundlePolicy(), getRemoteDescription(), getStreamArray());
                    }
                    promise.resolve(sessionDescription);
                    return;
                }
            }
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    static String[] createRtpheaderExtensionIdPriorityQueue() {
        String[] strArr = new String[14];
        for (int i = 1; i <= 14; i++) {
            strArr[i - 1] = IntegerExtensions.toString(Integer.valueOf(i));
        }
        return strArr;
    }

    public static String[] extractFormatSpecificParametersFromAttribute(MapAttribute mapAttribute) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = new String[0];
        FormatParametersAttribute relatedFormatParametersAttribute = mapAttribute.getRelatedFormatParametersAttribute();
        if (!(relatedFormatParametersAttribute == null || relatedFormatParametersAttribute.getFormatSpecificParameters() == null)) {
            strArr = StringExtensions.split(relatedFormatParametersAttribute.getFormatSpecificParameters(), new char[]{';'});
        }
        if (StringExtensions.isEqual(mapAttribute.getFormatName(), VideoFormat.getH264Name(), StringComparison.InvariantCultureIgnoreCase)) {
            String str = "packetization-mode=0";
            String str2 = "profile-level-id=42000A";
            for (String str3 : strArr) {
                if (!StringExtensions.isNullOrEmpty(str3) && StringExtensions.indexOf(str3, "=") != -1) {
                    String[] split = StringExtensions.split(str3, new char[]{'='});
                    if (Global.equals(split[0], VideoCodecInfo.H264_FMTP_PACKETIZATION_MODE)) {
                        str = str3;
                    } else if (Global.equals(split[0], VideoCodecInfo.H264_FMTP_PROFILE_LEVEL_ID)) {
                        str2 = str3;
                    }
                }
            }
            arrayList.add(str);
            arrayList.add(str2);
        } else if (StringExtensions.isEqual(mapAttribute.getFormatName(), VideoFormat.getVp9Name(), StringComparison.InvariantCultureIgnoreCase)) {
            String str4 = "profile-id=0";
            for (String str5 : strArr) {
                if (!StringExtensions.isNullOrEmpty(str5) && StringExtensions.indexOf(str5, "=") != -1) {
                    if (Global.equals(StringExtensions.split(str5, new char[]{'='})[0], "profile-id")) {
                        str4 = str5;
                    }
                }
            }
            arrayList.add(str4);
        } else if (!StringExtensions.isEqual(mapAttribute.getFormatName(), VideoFormat.getVp8Name(), StringComparison.InvariantCultureIgnoreCase) && !StringExtensions.isEqual(mapAttribute.getFormatName(), AudioFormat.getOpusName(), StringComparison.InvariantCultureIgnoreCase) && !StringExtensions.isEqual(mapAttribute.getFormatName(), AudioFormat.getPcmaName(), StringComparison.InvariantCultureIgnoreCase) && !StringExtensions.isEqual(mapAttribute.getFormatName(), AudioFormat.getPcmuName(), StringComparison.InvariantCultureIgnoreCase)) {
            for (String add : strArr) {
                arrayList.add(add);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    static String getAttributeDescription(MapAttribute mapAttribute) {
        String concat = StringExtensions.concat(mapAttribute.getFormatName(), "/", IntegerExtensions.toString(Integer.valueOf(mapAttribute.getClockRate())));
        if (mapAttribute.getFormatParameters() != null) {
            concat = StringExtensions.concat(concat, "/", mapAttribute.getFormatParameters());
        }
        String[] extractFormatSpecificParametersFromAttribute = extractFormatSpecificParametersFromAttribute(mapAttribute);
        if (ArrayExtensions.getLength((Object[]) extractFormatSpecificParametersFromAttribute) == 0) {
            return concat;
        }
        return StringExtensions.concat(concat, ";", StringExtensions.join(";", extractFormatSpecificParametersFromAttribute));
    }

    /* access modifiers changed from: protected */
    public Stream[] getAudioStreams() {
        return (Stream[]) this.__audioStreamIndex.toArray(new Stream[0]);
    }

    /* access modifiers changed from: protected */
    public Stream[] getDataStreams() {
        return (Stream[]) this.__dataStreamIndex.toArray(new Stream[0]);
    }

    public Pair<Stream, Integer>[] getDisablePrimaryComponents() {
        return (Pair[]) this.__disablePrimaryComponents.toArray(new Pair[0]);
    }

    public Pair<Stream, Integer>[] getDisableSecondaryComponents() {
        return (Pair[]) this.__disableSecondaryComponents.toArray(new Pair[0]);
    }

    public SessionDescription getLocalDescription() {
        return this.__localDescription;
    }

    public MultiplexPolicy getMultiplexPolicy() {
        return this._multiplexPolicy;
    }

    public boolean getOriginalSignallingExchangeComplete() {
        return this._originalSignallingExchangeComplete;
    }

    private static Attribute getRegisteredSdpAttribute(String str, HashMap<String, MapAttribute> hashMap, HashMap<String, CodecInfo> hashMap2) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, str, holder);
        MapAttribute mapAttribute = (MapAttribute) holder.getValue();
        if (tryGetValue) {
            return mapAttribute;
        }
        return null;
    }

    public Candidate[] getRemoteCandidates() {
        return (Candidate[]) this.__remoteCandidates.toArray(new Candidate[0]);
    }

    public SessionDescription getRemoteDescription() {
        return this.__remoteDescription;
    }

    private Stream[] getStreamArray() {
        ArrayList arrayList = new ArrayList();
        for (Stream add : HashMapExtensions.getValues(getStreams())) {
            arrayList.add(add);
        }
        return (Stream[]) arrayList.toArray(new Stream[0]);
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Stream> getStreams() {
        return this.__streams;
    }

    /* access modifiers changed from: package-private */
    public Stream[] getStreamsByIndex() {
        return (Stream[]) this.__streamIndex.toArray(new Stream[0]);
    }

    /* access modifiers changed from: protected */
    public Stream[] getVideoStreams() {
        return (Stream[]) this.__videoStreamIndex.toArray(new Stream[0]);
    }

    private void insertInto(ArrayList<Stream> arrayList, Stream stream) {
        boolean z = false;
        int i = 0;
        while (!z) {
            if (i == ArrayListExtensions.getCount(arrayList)) {
                arrayList.add(stream);
            } else if (((Stream) ArrayListExtensions.getItem(arrayList).get(i)).getIndex() > stream.getIndex()) {
                ArrayListExtensions.insert(arrayList, i, stream);
            } else {
                i++;
            }
            z = true;
        }
    }

    /* access modifiers changed from: protected */
    public Error processSdpMediaDescriptionForStream(Stream stream, MediaDescription mediaDescription, int i, boolean z, boolean z2) {
        Attribute[] candidateAttributes;
        boolean rtcpMultiplexingSupported = mediaDescription.getRtcpMultiplexingSupported();
        if (!z && (candidateAttributes = mediaDescription.getCandidateAttributes()) != null) {
            for (Attribute attribute : candidateAttributes) {
                Candidate candidate = new Candidate();
                candidate.setSdpMediaIndex(i);
                candidate.setSdpCandidateAttribute((CandidateAttribute) attribute);
                this.__remoteCandidates.add(candidate);
            }
        }
        if (z) {
            return null;
        }
        if (!Global.equals(stream.getType(), StreamType.Application) && !rtcpMultiplexingSupported && Global.equals(getMultiplexPolicy(), MultiplexPolicy.Required)) {
            return new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception("Local policy requires Rtcp component multiplexing, but peer does not support it."));
        }
        if (Global.equals(stream.getType(), StreamType.Application) || !rtcpMultiplexingSupported || !Global.equals(getMultiplexPolicy(), MultiplexPolicy.Negotiated)) {
            return null;
        }
        this.__disableSecondaryComponents.add(new Pair(stream, Integer.valueOf(i)));
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00aa A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setDescription(fm.liveswitch.SessionDescription r14, boolean r15) {
        /*
            r13 = this;
            r0 = 0
            r1 = 1
            if (r15 == 0) goto L_0x0009
            r13.__localDescription = r14
        L_0x0006:
            r2 = 1
            goto L_0x0072
        L_0x0009:
            fm.liveswitch.SessionDescription r2 = r13.__remoteDescription
            r3 = -1
            if (r2 != 0) goto L_0x0011
            r5 = r3
            goto L_0x0015
        L_0x0011:
            long r5 = r2.getSessionId()
        L_0x0015:
            fm.liveswitch.sdp.Message r2 = r14.getSdpMessage()
            if (r2 != 0) goto L_0x001d
            r7 = r3
            goto L_0x0021
        L_0x001d:
            long r7 = r14.getSessionId()
        L_0x0021:
            fm.liveswitch.sdp.Message r2 = r14.getSdpMessage()
            if (r2 != 0) goto L_0x0029
            r9 = r3
            goto L_0x002d
        L_0x0029:
            long r9 = r14.getSessionVersion()
        L_0x002d:
            fm.liveswitch.SessionDescription r2 = r13.__remoteDescription
            if (r2 != 0) goto L_0x0033
            r11 = r3
            goto L_0x0037
        L_0x0033:
            long r11 = r2.getSessionVersion()
        L_0x0037:
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 == 0) goto L_0x0047
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x0047
            fm.liveswitch.ILog r2 = __log
            java.lang.String r3 = "SDP session ID changed in remote description. Cannot renegotiate."
            r2.error(r3)
            goto L_0x0071
        L_0x0047:
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 <= 0) goto L_0x004e
            r13.__remoteDescription = r14
            goto L_0x0006
        L_0x004e:
            fm.liveswitch.ILog r2 = __log
            java.lang.Long r3 = java.lang.Long.valueOf(r7)
            java.lang.String r3 = fm.liveswitch.LongExtensions.toString(r3)
            java.lang.Long r4 = java.lang.Long.valueOf(r9)
            java.lang.String r4 = fm.liveswitch.LongExtensions.toString(r4)
            java.lang.Long r5 = java.lang.Long.valueOf(r11)
            java.lang.String r5 = fm.liveswitch.LongExtensions.toString(r5)
            java.lang.String r6 = "Received SDP message with Id {0} and version {1}, but existing remote description has version {2}. Discarding stale received description."
            java.lang.String r3 = fm.liveswitch.StringExtensions.format(r6, r3, r4, r5)
            r2.debug(r3)
        L_0x0071:
            r2 = 0
        L_0x0072:
            boolean r3 = r14.getIsOffer()
            if (r3 != 0) goto L_0x007c
            r13.setOriginalSignallingExchangeComplete(r1)
            goto L_0x009e
        L_0x007c:
            if (r15 != 0) goto L_0x009e
            java.util.HashMap<java.lang.String, fm.liveswitch.sdp.rtp.MapAttribute> r15 = r13.__payloadsByDescription
            r15.clear()
            java.util.HashMap<java.lang.String, fm.liveswitch.sdp.rtp.MapAttribute> r15 = r13.__payloadsByPT
            r15.clear()
            int[] r15 = createDynamicPayloadPriorityQueue()
            r13.__availablePayloadsPriority = r15
            fm.liveswitch.sdp.Message r14 = r14.getSdpMessage()
            java.util.HashMap<java.lang.String, fm.liveswitch.sdp.rtp.MapAttribute> r15 = r13.__payloadsByPT
            java.util.HashMap<java.lang.String, fm.liveswitch.sdp.rtp.MapAttribute> r3 = r13.__payloadsByDescription
            int[] r4 = r13.__availablePayloadsPriority
            int[] r14 = synchronisePayloads(r14, r15, r3, r4, r1)
            r13.__availablePayloadsPriority = r14
        L_0x009e:
            if (r2 == 0) goto L_0x00ab
            fm.liveswitch.SessionDescription r14 = r13.__localDescription
            if (r14 == 0) goto L_0x00ab
            fm.liveswitch.SessionDescription r14 = r13.getRemoteDescription()
            if (r14 == 0) goto L_0x00ab
            r0 = 1
        L_0x00ab:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: fm.liveswitch.SessionDescriptionManager.setDescription(fm.liveswitch.SessionDescription, boolean):boolean");
    }

    /* access modifiers changed from: package-private */
    public void setMediaStreamIdentifications(AudioStream[] audioStreamArr, VideoStream[] videoStreamArr, DataStream[] dataStreamArr) {
        int i = 0;
        for (AudioStream audioStream : audioStreamArr) {
            if (audioStream.getMediaStreamIdentification() == null) {
                String str = "audio";
                if (ArrayExtensions.getLength((Object[]) audioStreamArr) > 1) {
                    str = StringExtensions.concat(str, IntegerExtensions.toString(Integer.valueOf(i)));
                }
                audioStream.setMediaStreamIdentification(str);
            }
            i++;
        }
        int i2 = 0;
        for (VideoStream videoStream : videoStreamArr) {
            if (videoStream.getMediaStreamIdentification() == null) {
                String str2 = "video";
                if (ArrayExtensions.getLength((Object[]) videoStreamArr) > 1) {
                    str2 = StringExtensions.concat(str2, IntegerExtensions.toString(Integer.valueOf(i2)));
                }
                videoStream.setMediaStreamIdentification(str2);
            }
            i2++;
        }
        int i3 = 0;
        for (DataStream dataStream : dataStreamArr) {
            if (dataStream.getMediaStreamIdentification() == null) {
                String str3 = "data";
                if (ArrayExtensions.getLength((Object[]) dataStreamArr) > 1) {
                    str3 = StringExtensions.concat(str3, IntegerExtensions.toString(Integer.valueOf(i3)));
                }
                dataStream.setMediaStreamIdentification(str3);
            }
            i3++;
        }
    }

    public void setMultiplexPolicy(MultiplexPolicy multiplexPolicy) {
        this._multiplexPolicy = multiplexPolicy;
    }

    public void setOriginalSignallingExchangeComplete(boolean z) {
        this._originalSignallingExchangeComplete = z;
    }

    private void sortMediaDescriptions(Message message) {
        MediaDescription[] mediaDescriptions = message.getMediaDescriptions();
        for (MediaDescription removeMediaDescription : mediaDescriptions) {
            message.removeMediaDescription(removeMediaDescription);
        }
        super.addMediaDescriptions(message, mediaDescriptions);
    }

    static int[] synchronisePayloads(Message message, HashMap<String, MapAttribute> hashMap, HashMap<String, MapAttribute> hashMap2, int[] iArr, boolean z) {
        HashMap<String, MapAttribute> hashMap3 = hashMap;
        HashMap<String, MapAttribute> hashMap4 = hashMap2;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        MediaDescription[] mediaDescriptions = message.getMediaDescriptions();
        int i2 = 0;
        while (i2 < ArrayExtensions.getLength((Object[]) mediaDescriptions)) {
            MapAttribute[] rtpMapAttributes = mediaDescriptions[i2].getRtpMapAttributes();
            ArrayList arrayList2 = new ArrayList();
            int length = rtpMapAttributes.length;
            int i3 = 0;
            while (i3 < length) {
                MapAttribute mapAttribute = rtpMapAttributes[i3];
                String attributeDescription = getAttributeDescription(mapAttribute);
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap4, attributeDescription, holder);
                MapAttribute mapAttribute2 = (MapAttribute) holder.getValue();
                if (!tryGetValue) {
                    String integerExtensions = IntegerExtensions.toString(Integer.valueOf(mapAttribute.getPayloadType()));
                    Holder holder2 = new Holder(mapAttribute2);
                    boolean tryGetValue2 = HashMapExtensions.tryGetValue(hashMap3, integerExtensions, holder2);
                    MapAttribute mapAttribute3 = (MapAttribute) holder2.getValue();
                    if (tryGetValue2) {
                        String attributeDescription2 = getAttributeDescription(mapAttribute3);
                        if (ArrayListExtensions.getCount(arrayList) == 0) {
                            arrayList2.add(mapAttribute);
                            Log.debug(StringExtensions.format("Media Format {0} is already registered with payload type {1}. Media Format {2} cannot be registered with a new payload type because the dynamic payload types have been exhausted (are in use for other codecs). Consider decreasing the number of supported formats. Dynamic mapping reserved types of 1,2,9, 64 and 65 as per RFCs 3551 and RFC 5761 is currently not supported. Overriding static payloads types of 0, 3-18, 25, 26, 28 and 31-34 is also currently not supported. Usage of RTCP-specific payload types in the range 66-95 is not currently supported for multiplexed or non-multiplexed connections.", attributeDescription2, integerExtensions, attributeDescription));
                        } else if (!z) {
                            int intValue = ((Integer) ArrayListExtensions.getItem(arrayList).get(i)).intValue();
                            String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(intValue));
                            ArrayListExtensions.removeAt(arrayList, i);
                            assignNewPayloadType(mapAttribute, intValue, mediaDescriptions[i2].getMedia());
                            HashMapExtensions.add(hashMap3, integerExtensions2, mapAttribute);
                            HashMapExtensions.add(hashMap4, attributeDescription, mapAttribute);
                            Log.debug(StringExtensions.format("Media Format {0} is already registered with payload type {1}. Media Format {2} has been registered with payload type {3}.", new Object[]{attributeDescription2, integerExtensions, attributeDescription, integerExtensions2}));
                        } else {
                            HashMapExtensions.remove(hashMap4, attributeDescription2);
                            HashMapExtensions.add(hashMap4, attributeDescription, mapAttribute);
                            Log.debug(StringExtensions.format("Media Format {0} is already registered with payload type {1}. Media Format updated to {2}.", attributeDescription2, integerExtensions, attributeDescription));
                        }
                    } else {
                        HashMapExtensions.add(hashMap3, integerExtensions, mapAttribute);
                        HashMapExtensions.add(hashMap4, attributeDescription, mapAttribute);
                        arrayList.remove(new Integer(mapAttribute.getPayloadType()));
                    }
                    i3++;
                    i = 0;
                } else if (mapAttribute2.getPayloadType() != mapAttribute.getPayloadType()) {
                    if (!z) {
                        Log.debug(StringExtensions.format("Media Format {0} is already registered with payload type {2}. Updating payload type from {1} to {2}.", attributeDescription, IntegerExtensions.toString(Integer.valueOf(mapAttribute.getPayloadType())), IntegerExtensions.toString(Integer.valueOf(mapAttribute2.getPayloadType()))));
                        assignNewPayloadType(mapAttribute, mapAttribute2.getPayloadType(), mediaDescriptions[i2].getMedia());
                    } else {
                        Log.debug(StringExtensions.format("Media Format {0} is already registered with payload type {2}. Will retain the mapping to {1} to respect remote offer, but this assignment is not efficient.", attributeDescription, IntegerExtensions.toString(Integer.valueOf(mapAttribute.getPayloadType())), IntegerExtensions.toString(Integer.valueOf(mapAttribute2.getPayloadType()))));
                    }
                }
                i3++;
                i = 0;
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                mediaDescriptions[i2].removeMediaAttribute((MapAttribute) it.next());
            }
            arrayList2.clear();
            i2++;
            i = 0;
        }
        return Utility.toIntArray(arrayList);
    }

    static String[] synchroniseRtpHeaderExtensions(Message message, HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String[] strArr, boolean z) {
        HashMap<String, String> hashMap3 = hashMap;
        HashMap<String, String> hashMap4 = hashMap2;
        ArrayList arrayList = new ArrayList();
        for (String add : strArr) {
            arrayList.add(add);
        }
        MediaDescription[] mediaDescriptions = message.getMediaDescriptions();
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) mediaDescriptions); i++) {
            Attribute[] rtpExtMapAttributes = mediaDescriptions[i].getRtpExtMapAttributes();
            ArrayList arrayList2 = new ArrayList();
            for (Attribute attribute : rtpExtMapAttributes) {
                ExtMapAttribute extMapAttribute = (ExtMapAttribute) attribute;
                String uri = extMapAttribute.getUri();
                String integerExtensions = IntegerExtensions.toString(Integer.valueOf(extMapAttribute.getId()));
                Holder holder = new Holder(null);
                boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap4, uri, holder);
                String str = (String) holder.getValue();
                if (!tryGetValue) {
                    String integerExtensions2 = IntegerExtensions.toString(Integer.valueOf(extMapAttribute.getId()));
                    Holder holder2 = new Holder(null);
                    boolean tryGetValue2 = HashMapExtensions.tryGetValue(hashMap3, integerExtensions2, holder2);
                    String str2 = (String) holder2.getValue();
                    if (!tryGetValue2) {
                        HashMapExtensions.add(hashMap3, integerExtensions2, uri);
                        HashMapExtensions.add(hashMap4, uri, integerExtensions2);
                        arrayList.remove(integerExtensions2);
                    } else if (ArrayListExtensions.getCount(arrayList) == 0) {
                        arrayList2.add(attribute);
                        Log.debug(StringExtensions.format("RTP Header extension with URI {0} is already registered with id {1}. RTP Header extension with URI {2} cannot be registered with a new id because all values from the cvalid range 1-14 are in use. Extended range as per RFC 5285 Section 6 has not yet been implemented.", str2, integerExtensions2, uri));
                    } else if (!z) {
                        String str3 = (String) ArrayListExtensions.getItem(arrayList).get(0);
                        ArrayListExtensions.removeAt(arrayList, 0);
                        extMapAttribute.setId(ParseAssistant.parseIntegerValue(str3));
                        HashMapExtensions.add(hashMap3, str3, uri);
                        HashMapExtensions.add(hashMap4, uri, str3);
                        Log.debug(StringExtensions.format("RTP Header extension with URI {0} is already registered with id {1}. RTP Header extension with URI {2} has been registered with id {3}.", new Object[]{str2, integerExtensions2, uri, str3}));
                    } else {
                        HashMapExtensions.add(hashMap4, uri, integerExtensions2);
                        Log.warn(StringExtensions.format("RTP Header extension with URI {0} is already registered with id {1}. RTP Header extension with URI {2} is also registered with the same id. This may have uninteneded consequences.", str2, integerExtensions2, uri));
                    }
                } else if (!Global.equals(str, integerExtensions)) {
                    if (!z) {
                        Log.debug(StringExtensions.format("RTP Header extension with URI {0} is already registered with id {1}. Updating id from {2} to {1}.", uri, str, integerExtensions));
                        extMapAttribute.setId(ParseAssistant.parseIntegerValue(str));
                    } else {
                        Log.debug(StringExtensions.format("RTP Header extension with URI {0} is already registered with id {1}. Will retain the mapping to {2} to respect remote offer, but this assignment is not efficient.", uri, str, integerExtensions));
                    }
                }
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                mediaDescriptions[i].removeMediaAttribute((Attribute) it.next());
            }
            arrayList2.clear();
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* access modifiers changed from: package-private */
    public Pair<StreamDescription[], Object[]> updateMediaStreamIdentifications(SessionDescription sessionDescription, AudioStream[] audioStreamArr, VideoStream[] videoStreamArr, DataStream[] dataStreamArr) {
        ArrayList arrayList = new ArrayList();
        for (AudioStream insertInto : audioStreamArr) {
            insertInto(arrayList, insertInto);
        }
        ArrayList arrayList2 = new ArrayList();
        for (VideoStream insertInto2 : videoStreamArr) {
            insertInto(arrayList2, insertInto2);
        }
        ArrayList arrayList3 = new ArrayList();
        for (DataStream insertInto3 : dataStreamArr) {
            insertInto(arrayList3, insertInto3);
        }
        return super.parseSessionDescriptionForStreamChangesAndUpdateMids(sessionDescription, (StreamBase[]) arrayList.toArray(new Stream[0]), (StreamBase[]) arrayList2.toArray(new Stream[0]), (StreamBase[]) arrayList3.toArray(new Stream[0]));
    }
}
