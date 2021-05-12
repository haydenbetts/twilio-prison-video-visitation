package fm.liveswitch;

import com.google.android.exoplayer2.util.MimeTypes;
import fm.liveswitch.IAudioStream;
import fm.liveswitch.IDataChannel;
import fm.liveswitch.IDataStream;
import fm.liveswitch.IVideoStream;
import fm.liveswitch.StreamBase;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaStreamIdAttribute;
import fm.liveswitch.sdp.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

abstract class SessionDescriptionManagerBase<TStream extends StreamBase, TAudioStream extends IAudioStream, TVideoStream extends IVideoStream, TDataStream extends IDataStream<TDataChannel>, TDataChannel extends IDataChannel<TDataChannel>> {
    ArrayList<TStream> __audioStreamIndex = new ArrayList<>();
    ArrayList<TStream> __dataStreamIndex = new ArrayList<>();
    ArrayList<TStream> __streamIndex = new ArrayList<>();
    SessionDescriptionStreamMatcher<TStream> __streamMatcher = new SessionDescriptionStreamMatcher<>(0);
    ArrayList<TStream> __videoStreamIndex = new ArrayList<>();

    /* access modifiers changed from: protected */
    public abstract TStream[] getAudioStreams();

    /* access modifiers changed from: protected */
    public abstract TStream[] getDataStreams();

    /* access modifiers changed from: protected */
    public abstract HashMap<String, TStream> getStreams();

    /* access modifiers changed from: protected */
    public abstract TStream[] getVideoStreams();

    /* access modifiers changed from: protected */
    public abstract Error processSdpMediaDescriptionForStream(TStream tstream, MediaDescription mediaDescription, int i, boolean z, boolean z2);

    /* access modifiers changed from: package-private */
    public void addMediaDescriptions(Message message, MediaDescription[] mediaDescriptionArr) {
        int length = ArrayExtensions.getLength((Object[]) mediaDescriptionArr);
        MediaDescription[] mediaDescriptionArr2 = new MediaDescription[length];
        for (int i = 0; i < length; i++) {
            mediaDescriptionArr2[this.__streamMatcher.getOffererStreamIndexFor(i)] = mediaDescriptionArr[i];
        }
        for (int i2 = 0; i2 < length; i2++) {
            message.addMediaDescription(mediaDescriptionArr2[i2]);
        }
    }

    public void addStream(TStream tstream) {
        HashMapExtensions.add(getStreams(), tstream.getId(), tstream);
        addToIndex(tstream);
        resetStreamMatcher();
    }

    private void addToIndex(TStream tstream) {
        this.__streamIndex.add(tstream);
        if (Global.equals(tstream.getType(), StreamType.Audio)) {
            this.__audioStreamIndex.add(tstream);
        } else if (Global.equals(tstream.getType(), StreamType.Video)) {
            this.__videoStreamIndex.add(tstream);
        } else if (Global.equals(tstream.getType(), StreamType.Application)) {
            this.__dataStreamIndex.add(tstream);
        } else {
            throw new RuntimeException(new Exception("Unsupported stream type."));
        }
    }

    /* access modifiers changed from: package-private */
    public int getOffererStreamIndexFor(int i) {
        return this.__streamMatcher.getOffererStreamIndexFor(i);
    }

    /* access modifiers changed from: package-private */
    public Error matchAndProcessDescriptionPerType(ArrayList<TStream> arrayList, SessionDescription sessionDescription, boolean z, SessionDescriptionStreamMatcher<TStream> sessionDescriptionStreamMatcher, IFunction5<TStream, MediaDescription, Integer, Boolean, Boolean, Error> iFunction5, boolean z2) {
        Error processSdpMediaDescription;
        SessionDescriptionStreamMatcher<TStream> sessionDescriptionStreamMatcher2 = sessionDescriptionStreamMatcher;
        if (iFunction5 != null) {
            Message sdpMessage = sessionDescription.getSdpMessage();
            MediaDescription[] mediaDescriptions = sdpMessage.getMediaDescriptions();
            Iterator<TStream> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                StreamBase streamBase = (StreamBase) it.next();
                StreamType type = streamBase.getType();
                int offererStreamMediaIndexForStream = sessionDescriptionStreamMatcher2.getOffererStreamMediaIndexForStream(type, i);
                if (offererStreamMediaIndexForStream == -1) {
                    return new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception(StringExtensions.format("SDP offer did not contain stream of type {0} or stream of this type is not supported.", (Object) streamBase.getType().toString())));
                }
                sessionDescriptionStreamMatcher2.setMatchingIndexes(sessionDescriptionStreamMatcher2.getInternalStreamMediaIndexForStream(type, i), offererStreamMediaIndexForStream);
                MediaDescription mediaDescription = mediaDescriptions[offererStreamMediaIndexForStream];
                Error invoke = iFunction5.invoke(streamBase, mediaDescription, Integer.valueOf(offererStreamMediaIndexForStream), Boolean.valueOf(z), Boolean.valueOf(sessionDescription.getRenegotiation()));
                if (invoke != null) {
                    return invoke;
                }
                if (z2 && (processSdpMediaDescription = streamBase.processSdpMediaDescription(sdpMessage, mediaDescription, offererStreamMediaIndexForStream, z, sessionDescription.getIsOffer(), sessionDescription.getRenegotiation())) != null) {
                    return processSdpMediaDescription;
                }
                i++;
            }
            return null;
        }
        throw new RuntimeException(new Exception("ProcessSdpMediaDescriptionForStreamHandler is not set."));
    }

    /* access modifiers changed from: package-private */
    public Pair<StreamDescription[], Object[]> parseSessionDescriptionForStreamChangesAndUpdateMids(SessionDescription sessionDescription, TStream[] tstreamArr, TStream[] tstreamArr2, TStream[] tstreamArr3) {
        int i;
        TStream[] tstreamArr4 = tstreamArr;
        TStream[] tstreamArr5 = tstreamArr2;
        TStream[] tstreamArr6 = tstreamArr3;
        boolean renegotiation = sessionDescription.getRenegotiation();
        MediaDescription[] mediaDescriptions = sessionDescription.getSdpMessage().getMediaDescriptions();
        HashMap hashMap = new HashMap();
        for (TStream tstream : tstreamArr4) {
            if (!StringExtensions.isNullOrEmpty(tstream.getMediaStreamIdentification())) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), tstream.getMediaStreamIdentification(), tstream);
            }
        }
        for (TStream tstream2 : tstreamArr5) {
            if (!StringExtensions.isNullOrEmpty(tstream2.getMediaStreamIdentification())) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), tstream2.getMediaStreamIdentification(), tstream2);
            }
        }
        for (TStream tstream3 : tstreamArr6) {
            if (!StringExtensions.isNullOrEmpty(tstream3.getMediaStreamIdentification())) {
                HashMapExtensions.set(HashMapExtensions.getItem(hashMap), tstream3.getMediaStreamIdentification(), tstream3);
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int length = mediaDescriptions.length;
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        while (i2 < length) {
            MediaDescription mediaDescription = mediaDescriptions[i2];
            String mediaType = mediaDescription.getMedia().getMediaType();
            MediaStreamIdAttribute mediaStreamIdentifierAttribute = mediaDescription.getMediaStreamIdentifierAttribute();
            String str = StringExtensions.empty;
            if (mediaStreamIdentifierAttribute != null) {
                str = mediaStreamIdentifierAttribute.getIdentificationTag();
            }
            String trim = mediaType.trim();
            if (trim.equals("audio")) {
                i3++;
                String concat = Global.equals(str, StringExtensions.empty) ? StringExtensions.concat("audio", IntegerExtensions.toString(Integer.valueOf(i3))) : str;
                if (renegotiation) {
                    i = i2;
                    processMSection(arrayList, arrayList2, hashMap, mediaDescription, concat);
                } else {
                    i = i2;
                    tstreamArr4[i3].setMediaStreamIdentification(concat);
                }
            } else {
                i = i2;
                if (trim.equals("video")) {
                    i4++;
                    String concat2 = Global.equals(str, StringExtensions.empty) ? StringExtensions.concat("video", IntegerExtensions.toString(Integer.valueOf(i4))) : str;
                    if (renegotiation) {
                        processMSection(arrayList, arrayList2, hashMap, mediaDescription, concat2);
                    } else {
                        tstreamArr5[i4].setMediaStreamIdentification(concat2);
                    }
                } else if (Global.equals(trim, MimeTypes.BASE_TYPE_APPLICATION)) {
                    i5++;
                    String concat3 = Global.equals(str, StringExtensions.empty) ? StringExtensions.concat(MimeTypes.BASE_TYPE_APPLICATION, IntegerExtensions.toString(Integer.valueOf(i5))) : str;
                    if (renegotiation) {
                        processMSection(arrayList, arrayList2, hashMap, mediaDescription, concat3);
                    } else {
                        tstreamArr6[i5].setMediaStreamIdentification(concat3);
                    }
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Unsupported stream type {0}", (Object) trim)));
                }
            }
            i2 = i + 1;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMapExtensions.remove(hashMap, ((StreamBase) it.next()).getMediaStreamIdentification());
        }
        ArrayList arrayList3 = new ArrayList();
        for (StreamBase add : HashMapExtensions.getValues(hashMap)) {
            arrayList3.add(add);
        }
        return new Pair<>(arrayList2.toArray(new StreamDescription[0]), arrayList3.toArray(new Object[0]));
    }

    /* access modifiers changed from: package-private */
    public Error populateStreamTypeIndexes(MediaDescription[] mediaDescriptionArr, ArrayList<TStream> arrayList, SessionDescriptionStreamMatcher<TStream> sessionDescriptionStreamMatcher) {
        sessionDescriptionStreamMatcher.reset(ArrayListExtensions.getCount(arrayList));
        Error populateInternalStreamTypeIndexes = sessionDescriptionStreamMatcher.populateInternalStreamTypeIndexes(arrayList);
        return populateInternalStreamTypeIndexes == null ? sessionDescriptionStreamMatcher.populateOffererStreamTypeIndexes(mediaDescriptionArr) : populateInternalStreamTypeIndexes;
    }

    public Error processDescription(SessionDescription sessionDescription, boolean z) {
        Error populateStreamTypeIndexes;
        Error validateBaseDescription = validateBaseDescription(sessionDescription, z);
        if (validateBaseDescription != null) {
            return validateBaseDescription;
        }
        MediaDescription[] mediaDescriptions = sessionDescription.getSdpMessage().getMediaDescriptions();
        if (sessionDescription.getIsOffer() && (populateStreamTypeIndexes = populateStreamTypeIndexes(mediaDescriptions, this.__streamIndex, this.__streamMatcher)) != null) {
            return populateStreamTypeIndexes;
        }
        Error matchAndProcessDescriptionPerType = matchAndProcessDescriptionPerType(this.__audioStreamIndex, sessionDescription, z, this.__streamMatcher, new IFunctionDelegate5<TStream, MediaDescription, Integer, Boolean, Boolean, Error>() {
            public String getId() {
                return "fm.liveswitch.SessionDescriptionManagerBase<TStream,TAudioStream,TVideoStream,TDataStream,TDataChannel>.processSdpMediaDescriptionForStream";
            }

            public Error invoke(TStream tstream, MediaDescription mediaDescription, Integer num, Boolean bool, Boolean bool2) {
                return SessionDescriptionManagerBase.this.processSdpMediaDescriptionForStream(tstream, mediaDescription, num.intValue(), bool.booleanValue(), bool2.booleanValue());
            }
        }, true);
        if (matchAndProcessDescriptionPerType != null) {
            return matchAndProcessDescriptionPerType;
        }
        Error matchAndProcessDescriptionPerType2 = matchAndProcessDescriptionPerType(this.__videoStreamIndex, sessionDescription, z, this.__streamMatcher, new IFunctionDelegate5<TStream, MediaDescription, Integer, Boolean, Boolean, Error>() {
            public String getId() {
                return "fm.liveswitch.SessionDescriptionManagerBase<TStream,TAudioStream,TVideoStream,TDataStream,TDataChannel>.processSdpMediaDescriptionForStream";
            }

            public Error invoke(TStream tstream, MediaDescription mediaDescription, Integer num, Boolean bool, Boolean bool2) {
                return SessionDescriptionManagerBase.this.processSdpMediaDescriptionForStream(tstream, mediaDescription, num.intValue(), bool.booleanValue(), bool2.booleanValue());
            }
        }, true);
        if (matchAndProcessDescriptionPerType2 != null) {
            return matchAndProcessDescriptionPerType2;
        }
        Error matchAndProcessDescriptionPerType3 = matchAndProcessDescriptionPerType(this.__dataStreamIndex, sessionDescription, z, this.__streamMatcher, new IFunctionDelegate5<TStream, MediaDescription, Integer, Boolean, Boolean, Error>() {
            public String getId() {
                return "fm.liveswitch.SessionDescriptionManagerBase<TStream,TAudioStream,TVideoStream,TDataStream,TDataChannel>.processSdpMediaDescriptionForStream";
            }

            public Error invoke(TStream tstream, MediaDescription mediaDescription, Integer num, Boolean bool, Boolean bool2) {
                return SessionDescriptionManagerBase.this.processSdpMediaDescriptionForStream(tstream, mediaDescription, num.intValue(), bool.booleanValue(), bool2.booleanValue());
            }
        }, true);
        if (matchAndProcessDescriptionPerType3 != null) {
            return matchAndProcessDescriptionPerType3;
        }
        return null;
    }

    private void processMSection(ArrayList<TStream> arrayList, ArrayList<StreamDescription> arrayList2, HashMap<String, TStream> hashMap, MediaDescription mediaDescription, String str) {
        Holder holder = new Holder(null);
        boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, str, holder);
        StreamBase streamBase = (StreamBase) holder.getValue();
        if (tryGetValue) {
            arrayList.add(streamBase);
        } else {
            arrayList2.add(new StreamDescription(mediaDescription));
        }
    }

    private void removeFromIndex(TStream tstream) {
        this.__streamIndex.remove(tstream);
        if (Global.equals(tstream.getType(), StreamType.Audio)) {
            this.__audioStreamIndex.remove(tstream);
        } else if (Global.equals(tstream.getType(), StreamType.Video)) {
            this.__videoStreamIndex.remove((StreamBase) ((IVideoStream) tstream));
        } else if (Global.equals(tstream.getType(), StreamType.Application)) {
            this.__dataStreamIndex.remove((StreamBase) ((IDataStream) tstream));
        } else {
            throw new RuntimeException(new Exception("Unsupported stream type."));
        }
    }

    public boolean removeStream(TStream tstream) {
        if (!HashMapExtensions.remove(getStreams(), tstream.getId())) {
            return false;
        }
        removeFromIndex(tstream);
        resetStreamMatcher();
        return true;
    }

    private void resetStreamMatcher() {
        SessionDescriptionStreamMatcher<TStream> sessionDescriptionStreamMatcher = this.__streamMatcher;
        if (sessionDescriptionStreamMatcher != null) {
            sessionDescriptionStreamMatcher.reset(HashMapExtensions.getCount(getStreams()));
        } else {
            this.__streamMatcher = new SessionDescriptionStreamMatcher<>(HashMapExtensions.getCount(getStreams()));
        }
    }

    public void updateLocalDescription(SessionDescription sessionDescription) {
        String username = sessionDescription.getSdpMessage().getOrigin().getUsername();
        sessionDescription.getSdpMessage().getOrigin().setUsername(Global.equals(username.trim(), "-") ? StringExtensions.concat("LiveSwitch-", Build.getVersion()) : StringExtensions.concat(username, "_LiveSwitch-", Build.getVersion()));
        StreamBase[] audioStreams = getAudioStreams();
        if (audioStreams != null) {
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) audioStreams); i++) {
                IAudioStream iAudioStream = (IAudioStream) audioStreams[i];
                if (iAudioStream.getOpusDisabled()) {
                    sessionDescription.getSdpMessage().getAudioDescriptions()[i].purgeFormat(AudioFormat.getOpusName());
                }
                if (iAudioStream.getG722Disabled()) {
                    sessionDescription.getSdpMessage().getAudioDescriptions()[i].purgeFormat(AudioFormat.getG722Name());
                }
                if (iAudioStream.getPcmuDisabled()) {
                    sessionDescription.getSdpMessage().getAudioDescriptions()[i].purgeFormat(AudioFormat.getPcmuName());
                }
                if (iAudioStream.getPcmaDisabled()) {
                    sessionDescription.getSdpMessage().getAudioDescriptions()[i].purgeFormat(AudioFormat.getPcmaName());
                }
                String[] preferredCodecs = iAudioStream.getPreferredCodecs();
                if (preferredCodecs != null) {
                    sessionDescription.getSdpMessage().getAudioDescriptions()[i].orderFormats(preferredCodecs);
                }
            }
        }
        StreamBase[] videoStreams = getVideoStreams();
        if (videoStreams != null) {
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) videoStreams); i2++) {
                IVideoStream iVideoStream = (IVideoStream) videoStreams[i2];
                if (iVideoStream.getVp8Disabled()) {
                    sessionDescription.getSdpMessage().getVideoDescriptions()[i2].purgeFormat(VideoFormat.getVp8Name());
                }
                if (iVideoStream.getVp9Disabled()) {
                    sessionDescription.getSdpMessage().getVideoDescriptions()[i2].purgeFormat(VideoFormat.getVp9Name());
                }
                if (iVideoStream.getH264Disabled()) {
                    sessionDescription.getSdpMessage().getVideoDescriptions()[i2].purgeFormat(VideoFormat.getH264Name());
                }
                String[] preferredCodecs2 = iVideoStream.getPreferredCodecs();
                if (preferredCodecs2 != null) {
                    sessionDescription.getSdpMessage().getVideoDescriptions()[i2].orderFormats(preferredCodecs2);
                }
            }
        }
    }

    static void updateTrickleIcePolicy(Message message, TrickleIcePolicy trickleIcePolicy) {
        if (Global.equals(trickleIcePolicy, TrickleIcePolicy.FullTrickle) || Global.equals(trickleIcePolicy, TrickleIcePolicy.HalfTrickle)) {
            message.setSupportsTrickleIce(true);
        } else {
            message.setSupportsTrickleIce(false);
        }
    }

    private Error validateBaseDescription(SessionDescription sessionDescription, boolean z) {
        Message sdpMessage = sessionDescription.getSdpMessage();
        if (sdpMessage == null) {
            return new Error(ErrorCode.LocalDescriptionError, new Exception(StringExtensions.format("{0} session description is empty.", (Object) z ? "Local" : "Remote")));
        }
        HashMap streams = getStreams();
        if (streams == null || HashMapExtensions.getCount(streams) == 0) {
            return new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception("At least one local stream is required for the connection to conduct signalling exchange."));
        }
        MediaDescription[] mediaDescriptions = sdpMessage.getMediaDescriptions();
        if (mediaDescriptions == null || ArrayExtensions.getLength((Object[]) mediaDescriptions) == 0) {
            return new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception("At least one media description is required for the connection to conduct signalling exchange."));
        }
        int count = HashMapExtensions.getCount(streams);
        int length = ArrayExtensions.getLength((Object[]) mediaDescriptions);
        if (count != length) {
            return new Error(ErrorCode.ConnectionInvalidArchitecture, new Exception(StringExtensions.format("There is a mismatch between the number of local streams ({0}) and the number of media descriptions ({1}).", IntegerExtensions.toString(Integer.valueOf(count)), IntegerExtensions.toString(Integer.valueOf(length)))));
        }
        return null;
    }
}
