package fm.liveswitch;

import com.google.android.exoplayer2.util.MimeTypes;
import fm.liveswitch.sdp.AttributeType;
import fm.liveswitch.sdp.BundleGroup;
import fm.liveswitch.sdp.BundleOnlyAttribute;
import fm.liveswitch.sdp.ConnectionData;
import fm.liveswitch.sdp.GroupAttribute;
import fm.liveswitch.sdp.GroupSemanticsType;
import fm.liveswitch.sdp.MediaDescription;
import fm.liveswitch.sdp.MediaStreamIdAttribute;
import fm.liveswitch.sdp.SetupAttribute;
import fm.liveswitch.sdp.ice.FingerprintAttribute;
import fm.liveswitch.sdp.ice.PasswordAttribute;
import fm.liveswitch.sdp.ice.UfragAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class BundleDescriptionManager {
    static void bundleStreams(BundleGroup bundleGroup, BundleGroup bundleGroup2, SessionDescription sessionDescription) {
        String mediaStreamIdentification = bundleGroup.getTaggedMSection().getMediaStreamIdentification();
        Stream[] streams = bundleGroup.getStreams();
        Stream[] streams2 = bundleGroup2.getStreams();
        MediaDescription[] mediaDescriptions = sessionDescription.getSdpMessage().getMediaDescriptions();
        int length = streams.length;
        MediaDescription mediaDescription = null;
        int i = 0;
        while (i < length) {
            Stream stream = streams[i];
            Stream stream2 = null;
            int i2 = 0;
            while (stream2 == null && i2 < ArrayExtensions.getLength((Object[]) streams2)) {
                if (Global.equals(streams2[i2].getMediaStreamIdentification(), stream.getMediaStreamIdentification())) {
                    stream2 = streams2[i2];
                }
                i2++;
            }
            if (stream2 != null) {
                MediaDescription mediaDescription2 = null;
                int i3 = 0;
                while (mediaDescription2 == null && i3 < ArrayExtensions.getLength((Object[]) mediaDescriptions)) {
                    if (Global.equals(mediaDescriptions[i3].getMediaStreamIdentifierAttribute().getIdentificationTag(), stream.getMediaStreamIdentification())) {
                        mediaDescription2 = mediaDescriptions[i3];
                    }
                    i3++;
                }
                if (mediaDescription2 != null) {
                    i++;
                    mediaDescription = mediaDescription2;
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Cannot find a local media description corresponding to stream with mid {0}.", (Object) stream.getMediaStreamIdentification())));
                }
            } else {
                throw new RuntimeException(new Exception("Remote peer appears to have removed streams from a bundle. This case is not supported."));
            }
        }
        for (MediaDescription mediaDescription3 : sessionDescription.getSdpMessage().getMediaDescriptions()) {
            if (!Global.equals(mediaDescription3.getMediaStreamIdentifierAttribute().getIdentificationTag(), mediaStreamIdentification)) {
                makeMediaBundleOnly(mediaDescription3, mediaDescription);
            }
        }
    }

    private static void clearConnectionAddress(ConnectionData connectionData) {
        if (connectionData == null) {
            return;
        }
        if (Global.equals(connectionData.getNetworkType(), "IP4")) {
            connectionData.setConnectionAddress("0.0.0.0");
        } else if (Global.equals(connectionData.getNetworkType(), "IP6")) {
            connectionData.setConnectionAddress("::");
        } else {
            connectionData.setConnectionAddress("0.0.0.0");
        }
    }

    static BundleGroup[] extractBundleGroups(SessionDescription sessionDescription, Stream[] streamArr) {
        Stream[] streamArr2 = streamArr;
        BundleGroup[] bundleGroups = sessionDescription.getSdpMessage().getBundleGroups();
        if (bundleGroups == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        String str = "";
        for (BundleGroup bundleGroup : bundleGroups) {
            String identificationTag = bundleGroup.getTaggedMSection().getMediaStreamIdentifierAttribute().getIdentificationTag();
            String[] mids = bundleGroup.getMids();
            BundleGroup bundleGroup2 = new BundleGroup();
            int length = streamArr2.length;
            int i = 0;
            while (i < length) {
                Stream stream = streamArr2[i];
                int i2 = 0;
                boolean z = false;
                while (!z && i2 < ArrayExtensions.getLength((Object[]) mids)) {
                    str = mids[i2];
                    if (Global.equals(stream.getMediaStreamIdentification(), str)) {
                        bundleGroup2.add(stream);
                        if (Global.equals(str, identificationTag)) {
                            bundleGroup2.setTaggedMSection(stream);
                        }
                        if (bundleGroup.getBundleOnly()) {
                            bundleGroup2.setBundleOnly(true);
                        }
                        z = true;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    i++;
                } else {
                    throw new RuntimeException(new Exception(StringExtensions.format("Could not find a stream with mid {0}.", (Object) str)));
                }
            }
            arrayList.add(bundleGroup2);
        }
        return (BundleGroup[]) arrayList.toArray(new BundleGroup[0]);
    }

    static void makeMediaBundleOnly(MediaDescription mediaDescription, MediaDescription mediaDescription2) {
        boolean z;
        if (!Global.equals(mediaDescription2.getMedia().getMediaType(), MimeTypes.BASE_TYPE_APPLICATION)) {
            z = mediaDescription2.getRtcpMultiplexingSupported();
        } else {
            z = mediaDescription.getRtcpMultiplexingSupported();
        }
        UfragAttribute iceUfragAttribute = mediaDescription2.getIceUfragAttribute();
        PasswordAttribute icePasswordAttribute = mediaDescription2.getIcePasswordAttribute();
        FingerprintAttribute fingerprintAttribute = mediaDescription2.getFingerprintAttribute();
        SetupAttribute setupAttribute = mediaDescription2.getSetupAttribute();
        mediaDescription.removeBundleCategoryAttributes();
        if (z) {
            mediaDescription.setRtcpMultiplexingSupported(true);
        }
        if (!(iceUfragAttribute == null || icePasswordAttribute == null)) {
            mediaDescription.addMediaAttribute(iceUfragAttribute);
            mediaDescription.addMediaAttribute(icePasswordAttribute);
        }
        if (fingerprintAttribute != null) {
            mediaDescription.addMediaAttribute(fingerprintAttribute);
        }
        if (setupAttribute != null) {
            mediaDescription.addMediaAttribute(setupAttribute);
        }
        clearConnectionAddress(mediaDescription.getConnectionData());
    }

    public static boolean processRemoteAnswer(SessionDescription sessionDescription, SessionDescription sessionDescription2, BundlePolicy bundlePolicy, Stream[] streamArr) {
        BundleGroup[] extractBundleGroups = extractBundleGroups(sessionDescription, streamArr);
        BundleGroup[] extractBundleGroups2 = extractBundleGroups(sessionDescription2, streamArr);
        if (Global.equals(bundlePolicy, BundlePolicy.MaxBundle)) {
            if (extractBundleGroups2 != null && ArrayExtensions.getLength((Object[]) extractBundleGroups2) == ArrayExtensions.getLength((Object[]) extractBundleGroups)) {
                return true;
            }
            throw new RuntimeException(new Exception("Local bundling policy is MaxBundle, but remote eother does not support bundling or changed the number of bundles."));
        } else if (!Global.equals(bundlePolicy, BundlePolicy.Balanced)) {
            if (extractBundleGroups2 == null || ArrayExtensions.getLength((Object[]) extractBundleGroups2) == 0) {
                if (extractBundleGroups != null && ArrayExtensions.getLength((Object[]) extractBundleGroups) > 0) {
                    Log.debug("Remote peer indicated it does not support bundling. Bundling will be disabled on this connection.");
                }
                sessionDescription.getSdpMessage().removeSessionAttribute(AttributeType.GroupAttribute);
            } else if (extractBundleGroups == null || ArrayExtensions.getLength((Object[]) extractBundleGroups) != ArrayExtensions.getLength((Object[]) extractBundleGroups2)) {
                throw new RuntimeException(new Exception("Remote peer modified the number of bundle groups in its answer. Handling this case is not supported."));
            } else {
                int i = 0;
                while (i < ArrayExtensions.getLength((Object[]) extractBundleGroups)) {
                    BundleGroup bundleGroup = extractBundleGroups[i];
                    String mediaStreamIdentification = bundleGroup.getTaggedMSection().getMediaStreamIdentification();
                    boolean z = false;
                    int i2 = 0;
                    while (!z && i2 < ArrayExtensions.getLength((Object[]) extractBundleGroups2)) {
                        BundleGroup bundleGroup2 = extractBundleGroups2[i2];
                        if (Global.equals(mediaStreamIdentification, bundleGroup2.getTaggedMSection().getMediaStreamIdentification())) {
                            bundleStreams(bundleGroup, bundleGroup2, sessionDescription);
                            z = true;
                        }
                        i2++;
                    }
                    if (z) {
                        i++;
                    } else {
                        throw new RuntimeException(new Exception("Remote peer appears to have mofified tagged m-section for one of th bundle groups. This case is invalid."));
                    }
                }
            }
            return true;
        } else {
            throw new RuntimeException(new Exception("Balanced bubndling policy is not supported."));
        }
    }

    static Pair<Triple<CoreTransport, CoreTransport, IntegerHolder>[], Stream[]> reassignTransportsAndUpdateProperties(SessionDescription sessionDescription, Stream[] streamArr, boolean z) {
        int i;
        RtpHeaderExtensionRegistry rtpHeaderExtensionRegistry;
        BundleGroup[] bundleGroupArr;
        Stream[] streamArr2 = streamArr;
        BundleGroup[] bundleGroups = sessionDescription.getSdpMessage().getBundleGroups();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList createArray = ArrayListExtensions.createArray((T[]) streamArr);
        int length = bundleGroups.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            Stream stream = null;
            if (i3 >= length) {
                return new Pair<>(arrayList2.toArray(new Triple[0]), createArray.toArray(new Stream[0]));
            }
            BundleGroup bundleGroup = bundleGroups[i3];
            String str = bundleGroup.getMids()[i2];
            int length2 = streamArr2.length;
            int i4 = 0;
            while (i4 < length2) {
                Stream stream2 = streamArr2[i4];
                if (Global.equals(stream2.getMediaStreamIdentification(), str)) {
                    stream = stream2;
                }
                boolean z2 = false;
                while (!z2 && i2 < ArrayExtensions.getLength((Object[]) bundleGroup.getMids())) {
                    if (Global.equals(stream2.getMediaStreamIdentification(), bundleGroup.getMids()[i2])) {
                        arrayList.add(stream2);
                        createArray.remove(stream2);
                        Stream[] streamArr3 = streamArr;
                        z2 = true;
                    } else {
                        i2++;
                        Stream[] streamArr4 = streamArr;
                    }
                }
                i4++;
                streamArr2 = streamArr;
                i2 = 0;
            }
            RtpHeaderExtensionRegistry headerExtensionRegistry = stream.getCoreTransportRtp().getBundleTransport().getHeaderExtensionRegistry();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Stream stream3 = (Stream) it.next();
                if (Global.equals(stream3.getMediaStreamIdentification(), stream.getMediaStreamIdentification()) || (!bundleGroup.getBundleOnly() && sessionDescription.getIsOffer())) {
                    rtpHeaderExtensionRegistry = headerExtensionRegistry;
                    bundleGroupArr = bundleGroups;
                    i = length;
                } else {
                    stream3.setUseDtls(stream.getUseDtls());
                    if (!Global.equals(stream3.getType(), StreamType.Application)) {
                        stream3.setUseSdes(stream.getUseSdes());
                    }
                    stream3.setEncryptionModes(stream.getEncryptionModes());
                    CoreTransport[] redundantCoreTransports = stream3.getRedundantCoreTransports();
                    int length3 = redundantCoreTransports.length;
                    int i5 = 0;
                    while (i5 < length3) {
                        CoreTransport coreTransport = redundantCoreTransports[i5];
                        BundleGroup[] bundleGroupArr2 = bundleGroups;
                        if ((Global.equals(stream3.getType(), StreamType.Audio) || Global.equals(stream3.getType(), StreamType.Video)) && headerExtensionRegistry != null) {
                            int length4 = redundantCoreTransports.length;
                            int i6 = 0;
                            while (i6 < length4) {
                                int i7 = length4;
                                RtpHeaderExtensionRegistry headerExtensionRegistry2 = redundantCoreTransports[i6].getBundleTransport().getHeaderExtensionRegistry();
                                if (headerExtensionRegistry2 != null) {
                                    headerExtensionRegistry.unify(headerExtensionRegistry2);
                                }
                                i6++;
                                length4 = i7;
                            }
                        }
                        arrayList2.add(new Triple(coreTransport, stream.getCoreTransportRtp(), new IntegerHolder(stream3.getIndex())));
                        i5++;
                        bundleGroups = bundleGroupArr2;
                        headerExtensionRegistry = headerExtensionRegistry;
                        length = length;
                    }
                    rtpHeaderExtensionRegistry = headerExtensionRegistry;
                    bundleGroupArr = bundleGroups;
                    i = length;
                    stream3.eraseLocalCandidatesForComponent(1);
                    stream3.setBundled(true);
                    stream3.setCoreTransportRtp(stream.getCoreTransportRtp());
                    stream3.setCoreTransportRtcp(stream.getCoreTransportRtcp());
                    stream3.copyLocalParameters(stream);
                    if (Global.equals(stream3.getType(), StreamType.Audio)) {
                        RtpTransport rtpTransport = ((AudioStream) stream3).getRtpTransport();
                        if (rtpTransport != null) {
                            rtpTransport.setRtpTransport(stream3.getCoreTransportRtp().getIceTransport());
                            rtpTransport.setRtcpTransport(stream3.getCoreTransportRtp().getIceTransport());
                        }
                    } else if (Global.equals(stream3.getType(), StreamType.Video)) {
                        RtpTransport rtpTransport2 = ((VideoStream) stream3).getRtpTransport();
                        if (rtpTransport2 != null) {
                            rtpTransport2.setRtpTransport(stream3.getCoreTransportRtp().getIceTransport());
                            rtpTransport2.setRtcpTransport(stream3.getCoreTransportRtp().getIceTransport());
                        }
                    } else if (Global.equals(stream3.getType(), StreamType.Application)) {
                        ((DataStream) stream3).getSctpTransport().setInnerTransport(stream3.getCoreTransportRtp().getBundleTransport());
                    }
                }
                bundleGroups = bundleGroupArr;
                headerExtensionRegistry = rtpHeaderExtensionRegistry;
                length = i;
            }
            BundleGroup[] bundleGroupArr3 = bundleGroups;
            int i8 = length;
            arrayList.clear();
            i3++;
            streamArr2 = streamArr;
            i2 = 0;
        }
    }

    public static void updateDescription(SessionDescription sessionDescription, BundlePolicy bundlePolicy, SessionDescription sessionDescription2, Stream[] streamArr) {
        if (!sessionDescription.getIsOffer() || sessionDescription2 != null) {
            updateLocalAnswerOrSubsequentOfferDescription(sessionDescription, bundlePolicy, extractBundleGroups(sessionDescription2, streamArr), sessionDescription.getIsOffer());
        } else {
            updateLocalOfferDescription(sessionDescription, bundlePolicy);
        }
    }

    public static boolean updateLocalAnswerOrSubsequentOfferDescription(SessionDescription sessionDescription, BundlePolicy bundlePolicy, BundleGroup[] bundleGroupArr, boolean z) {
        int i = 0;
        if (Global.equals(bundlePolicy, BundlePolicy.Disabled)) {
            if (bundleGroupArr != null) {
                int length = bundleGroupArr.length;
                while (i < length) {
                    if (!bundleGroupArr[i].getBundleOnly()) {
                        i++;
                    } else if (z) {
                        throw new RuntimeException(new Exception("Earlier negotiation indicated non-optional bundling support, but current local bundling policy is set to Disabled."));
                    } else {
                        throw new RuntimeException(new Exception("Remote peer indicated it requires support for bundling but local bundling policy is set to Disabled."));
                    }
                }
                if (z) {
                    Log.debug("Earlier negotiation indicated optional support for bundling but local bundling policy is set to Disabled. Bundling will be disabled for this connection.");
                } else {
                    Log.debug("Remote peer indicated optional support for bundling but local bundling policy is set to Disabled. Bundling will be disabled for this connection.");
                }
            }
            return true;
        }
        MediaDescription[] mediaDescriptions = sessionDescription.getSdpMessage().getMediaDescriptions();
        for (BundleGroup bundleGroup : bundleGroupArr) {
            Stream[] streams = bundleGroup.getStreams();
            Stream taggedMSection = bundleGroup.getTaggedMSection();
            ArrayList arrayList = new ArrayList();
            arrayList.add(taggedMSection.getMediaStreamIdentification());
            for (Stream stream : streams) {
                if (!Global.equals(stream.getMediaStreamIdentification(), taggedMSection.getMediaStreamIdentification())) {
                    arrayList.add(stream.getMediaStreamIdentification());
                }
            }
            sessionDescription.getSdpMessage().addSessionAttribute(new GroupAttribute(GroupSemanticsType.Bundling, (String[]) arrayList.toArray(new String[0])));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) mediaDescriptions); i2++) {
                    MediaDescription mediaDescription = mediaDescriptions[i2];
                    if (Global.equals(str, mediaDescription.getMediaStreamIdentifierAttribute().getIdentificationTag()) && i2 != 0) {
                        makeMediaBundleOnly(mediaDescription, mediaDescriptions[0]);
                    }
                }
            }
        }
        return true;
    }

    public static boolean updateLocalOfferDescription(SessionDescription sessionDescription, BundlePolicy bundlePolicy) {
        MediaDescription[] mediaDescriptions = sessionDescription.getSdpMessage().getMediaDescriptions();
        HashMap hashMap = new HashMap();
        ArrayList arrayList = null;
        String str = "";
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) mediaDescriptions); i++) {
            MediaDescription mediaDescription = mediaDescriptions[i];
            String identificationTag = mediaDescription.getMediaStreamIdentifierAttribute().getIdentificationTag();
            if (i == 0 || Global.equals(bundlePolicy, BundlePolicy.Balanced)) {
                String mediaType = mediaDescription.getMedia().getMediaType();
                Holder holder = new Holder(arrayList);
                boolean tryGetValue = HashMapExtensions.tryGetValue(hashMap, mediaType, holder);
                ArrayList arrayList2 = (ArrayList) holder.getValue();
                if (tryGetValue) {
                    mediaDescription.addMediaAttribute(new BundleOnlyAttribute());
                    mediaDescription.getMedia().setTransportPort(0);
                    mediaDescription.removeBundleCategoryAttributes();
                    clearConnectionAddress(mediaDescription.getConnectionData());
                    arrayList2.add(identificationTag);
                    String str2 = (String) ArrayListExtensions.getItem(arrayList2).get(0);
                    for (MediaDescription mediaDescription2 : mediaDescriptions) {
                        MediaStreamIdAttribute mediaStreamIdentifierAttribute = mediaDescription2.getMediaStreamIdentifierAttribute();
                        if (mediaStreamIdentifierAttribute != null && Global.equals(mediaStreamIdentifierAttribute.getIdentificationTag(), str2) && mediaDescription2.getRtcpMultiplexingSupported()) {
                            mediaDescription.setRtcpMultiplexingSupported(true);
                        }
                    }
                } else {
                    arrayList2 = new ArrayList();
                    arrayList2.add(identificationTag);
                    HashMapExtensions.add(hashMap, mediaType, arrayList2);
                    str = mediaType;
                }
                arrayList = arrayList2;
            } else if (Global.equals(bundlePolicy, BundlePolicy.MaxBundle)) {
                makeMediaBundleOnly(mediaDescription, mediaDescriptions[0]);
                arrayList = (ArrayList) HashMapExtensions.getItem(hashMap).get(str);
                arrayList.add(identificationTag);
            } else if (Global.equals(bundlePolicy, BundlePolicy.MaxCompatibility)) {
                ((ArrayList) HashMapExtensions.getItem(hashMap).get(str)).add(identificationTag);
            } else {
                throw new RuntimeException(new Exception(StringExtensions.format("Unexpected group bundling policy {0}.", (Object) bundlePolicy.toString())));
            }
        }
        for (ArrayList array : HashMapExtensions.getValues(hashMap)) {
            sessionDescription.getSdpMessage().addSessionAttribute(new GroupAttribute(GroupSemanticsType.Bundling, (String[]) array.toArray(new String[0])));
        }
        return true;
    }
}
