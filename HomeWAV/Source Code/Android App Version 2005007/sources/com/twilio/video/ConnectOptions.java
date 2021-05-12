package com.twilio.video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConnectOptions {
    private static final Set<Class> SUPPORTED_CODECS = new HashSet(Arrays.asList(new Class[]{IsacCodec.class, OpusCodec.class, PcmuCodec.class, PcmaCodec.class, G722Codec.class, Vp8Codec.class, H264Codec.class, Vp9Codec.class}));
    private final String accessToken;
    private final List<LocalAudioTrack> audioTracks;
    private final List<LocalDataTrack> dataTracks;
    private final boolean enableAutomaticSubscription;
    private final boolean enableDominantSpeaker;
    private final boolean enableIceGatheringOnAnyAddressPorts;
    private final boolean enableInsights;
    private final boolean enableNetworkQuality;
    private final EncodingParameters encodingParameters;
    private final IceOptions iceOptions;
    private final MediaFactory mediaFactory;
    private final NetworkQualityConfiguration networkQualityConfiguration;
    private final List<AudioCodec> preferredAudioCodecs;
    private final List<VideoCodec> preferredVideoCodecs;
    private final String region;
    private final String roomName;
    private final List<LocalVideoTrack> videoTracks;

    private native long nativeCreate(String str, String str2, LocalAudioTrack[] localAudioTrackArr, LocalVideoTrack[] localVideoTrackArr, LocalDataTrack[] localDataTrackArr, IceOptions iceOptions2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, NetworkQualityConfiguration networkQualityConfiguration2, long j, AudioCodec[] audioCodecArr, VideoCodec[] videoCodecArr, String str3, EncodingParameters encodingParameters2);

    static void checkAudioCodecs(List<AudioCodec> list) {
        if (list != null) {
            for (AudioCodec next : list) {
                Preconditions.checkNotNull(next);
                Preconditions.checkArgument(SUPPORTED_CODECS.contains(next.getClass()), String.format("Unsupported audio codec %s", new Object[]{next.getName()}));
            }
        }
    }

    static void checkVideoCodecs(List<VideoCodec> list) {
        if (list != null) {
            for (VideoCodec next : list) {
                Preconditions.checkNotNull(next);
                Preconditions.checkArgument(SUPPORTED_CODECS.contains(next.getClass()), String.format("Unsupported video codec %s", new Object[]{next.getName()}));
            }
        }
    }

    static void checkAudioTracksReleased(List<LocalAudioTrack> list) {
        if (list != null) {
            for (LocalAudioTrack isReleased : list) {
                Preconditions.checkState(!isReleased.isReleased(), "LocalAudioTrack cannot be released");
            }
        }
    }

    static void checkVideoTracksReleased(List<LocalVideoTrack> list) {
        if (list != null) {
            for (LocalVideoTrack isReleased : list) {
                Preconditions.checkState(!isReleased.isReleased(), "LocalVideoTrack cannot be released");
            }
        }
    }

    private ConnectOptions(Builder builder) {
        this.accessToken = builder.accessToken;
        this.roomName = builder.roomName;
        this.audioTracks = builder.audioTracks;
        this.videoTracks = builder.videoTracks;
        this.dataTracks = builder.dataTracks;
        this.iceOptions = builder.iceOptions;
        this.enableIceGatheringOnAnyAddressPorts = builder.enableIceGatheringOnAnyAddressPorts;
        this.enableInsights = builder.enableInsights;
        this.enableAutomaticSubscription = builder.enableAutomaticSubscription;
        this.enableDominantSpeaker = builder.enableDominantSpeaker;
        this.enableNetworkQuality = builder.enableNetworkQuality;
        this.networkQualityConfiguration = builder.networkQualityConfiguration;
        this.preferredAudioCodecs = builder.preferredAudioCodecs;
        this.preferredVideoCodecs = builder.preferredVideoCodecs;
        this.region = builder.region;
        this.encodingParameters = builder.encodingParameters;
        this.mediaFactory = builder.mediaFactory;
    }

    /* access modifiers changed from: package-private */
    public String getAccessToken() {
        return this.accessToken;
    }

    /* access modifiers changed from: package-private */
    public String getRoomName() {
        return this.roomName;
    }

    /* access modifiers changed from: package-private */
    public List<LocalAudioTrack> getAudioTracks() {
        return this.audioTracks;
    }

    /* access modifiers changed from: package-private */
    public List<LocalVideoTrack> getVideoTracks() {
        return this.videoTracks;
    }

    /* access modifiers changed from: package-private */
    public List<LocalDataTrack> getDataTracks() {
        return this.dataTracks;
    }

    /* access modifiers changed from: package-private */
    public String getRegion() {
        return this.region;
    }

    /* access modifiers changed from: package-private */
    public IceOptions getIceOptions() {
        return this.iceOptions;
    }

    /* access modifiers changed from: package-private */
    public boolean isIceGatheringOnAnyAddressPortsEnabled() {
        return this.enableIceGatheringOnAnyAddressPorts;
    }

    /* access modifiers changed from: package-private */
    public boolean isInsightsEnabled() {
        return this.enableInsights;
    }

    /* access modifiers changed from: package-private */
    public boolean isNetworkQualityEnabled() {
        return this.enableNetworkQuality;
    }

    /* access modifiers changed from: package-private */
    public NetworkQualityConfiguration getNetworkQualityConfiguration() {
        return this.networkQualityConfiguration;
    }

    private LocalAudioTrack[] getLocalAudioTracksArray() {
        LocalAudioTrack[] localAudioTrackArr = new LocalAudioTrack[0];
        List<LocalAudioTrack> list = this.audioTracks;
        if (list == null || list.size() <= 0) {
            return localAudioTrackArr;
        }
        return (LocalAudioTrack[]) this.audioTracks.toArray(new LocalAudioTrack[this.audioTracks.size()]);
    }

    private LocalVideoTrack[] getLocalVideoTracksArray() {
        LocalVideoTrack[] localVideoTrackArr = new LocalVideoTrack[0];
        List<LocalVideoTrack> list = this.videoTracks;
        if (list == null || list.size() <= 0) {
            return localVideoTrackArr;
        }
        return (LocalVideoTrack[]) this.videoTracks.toArray(new LocalVideoTrack[this.videoTracks.size()]);
    }

    private LocalDataTrack[] getLocalDataTracksArray() {
        LocalDataTrack[] localDataTrackArr = new LocalDataTrack[0];
        List<LocalDataTrack> list = this.dataTracks;
        if (list == null || list.size() <= 0) {
            return localDataTrackArr;
        }
        return (LocalDataTrack[]) this.dataTracks.toArray(new LocalDataTrack[this.dataTracks.size()]);
    }

    private AudioCodec[] getAudioCodecsArray() {
        AudioCodec[] audioCodecArr = new AudioCodec[0];
        List<AudioCodec> list = this.preferredAudioCodecs;
        if (list == null || list.isEmpty()) {
            return audioCodecArr;
        }
        return (AudioCodec[]) this.preferredAudioCodecs.toArray(new AudioCodec[this.preferredAudioCodecs.size()]);
    }

    private VideoCodec[] getVideoCodecsArray() {
        VideoCodec[] videoCodecArr = new VideoCodec[0];
        List<VideoCodec> list = this.preferredVideoCodecs;
        if (list == null || list.isEmpty()) {
            return videoCodecArr;
        }
        return (VideoCodec[]) this.preferredVideoCodecs.toArray(new VideoCodec[this.preferredVideoCodecs.size()]);
    }

    /* access modifiers changed from: package-private */
    public EncodingParameters getEncodingParameters() {
        return this.encodingParameters;
    }

    /* access modifiers changed from: package-private */
    public MediaFactory getMediaFactory() {
        return this.mediaFactory;
    }

    private long createNativeConnectOptionsBuilder() {
        checkAudioTracksReleased(this.audioTracks);
        checkVideoTracksReleased(this.videoTracks);
        String str = this.accessToken;
        return nativeCreate(str, this.roomName, getLocalAudioTracksArray(), getLocalVideoTracksArray(), getLocalDataTracksArray(), this.iceOptions, this.enableIceGatheringOnAnyAddressPorts, this.enableInsights, this.enableAutomaticSubscription, this.enableDominantSpeaker, this.enableNetworkQuality, this.networkQualityConfiguration, PlatformInfo.getNativeHandle(), getAudioCodecsArray(), getVideoCodecsArray(), this.region, this.encodingParameters);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String accessToken = "";
        /* access modifiers changed from: private */
        public List<LocalAudioTrack> audioTracks;
        /* access modifiers changed from: private */
        public List<LocalDataTrack> dataTracks;
        /* access modifiers changed from: private */
        public boolean enableAutomaticSubscription = true;
        /* access modifiers changed from: private */
        public boolean enableDominantSpeaker = false;
        /* access modifiers changed from: private */
        public boolean enableIceGatheringOnAnyAddressPorts = false;
        /* access modifiers changed from: private */
        public boolean enableInsights = true;
        /* access modifiers changed from: private */
        public boolean enableNetworkQuality = false;
        /* access modifiers changed from: private */
        public EncodingParameters encodingParameters;
        /* access modifiers changed from: private */
        public IceOptions iceOptions;
        /* access modifiers changed from: private */
        public MediaFactory mediaFactory;
        /* access modifiers changed from: private */
        public NetworkQualityConfiguration networkQualityConfiguration;
        /* access modifiers changed from: private */
        public List<AudioCodec> preferredAudioCodecs;
        /* access modifiers changed from: private */
        public List<VideoCodec> preferredVideoCodecs;
        /* access modifiers changed from: private */
        public String region = "gll";
        /* access modifiers changed from: private */
        public String roomName = "";
        /* access modifiers changed from: private */
        public List<LocalVideoTrack> videoTracks;

        public Builder(String str) {
            Preconditions.checkNotNull(str);
            this.accessToken = str;
        }

        public Builder roomName(String str) {
            Preconditions.checkNotNull(str);
            this.roomName = str;
            return this;
        }

        public Builder audioTracks(List<LocalAudioTrack> list) {
            Preconditions.checkNotNull(list, "LocalAudioTrack List must not be null");
            this.audioTracks = new ArrayList(list);
            return this;
        }

        public Builder videoTracks(List<LocalVideoTrack> list) {
            Preconditions.checkNotNull(list, "LocalVideoTrack List must not be null");
            this.videoTracks = new ArrayList(list);
            return this;
        }

        public Builder dataTracks(List<LocalDataTrack> list) {
            Preconditions.checkNotNull(list);
            this.dataTracks = list;
            return this;
        }

        public Builder iceOptions(IceOptions iceOptions2) {
            Preconditions.checkNotNull(iceOptions2);
            this.iceOptions = iceOptions2;
            return this;
        }

        public Builder enableIceGatheringOnAnyAddressPorts(boolean z) {
            this.enableIceGatheringOnAnyAddressPorts = z;
            return this;
        }

        public Builder enableInsights(boolean z) {
            this.enableInsights = z;
            return this;
        }

        public Builder enableAutomaticSubscription(boolean z) {
            this.enableAutomaticSubscription = z;
            return this;
        }

        public Builder enableDominantSpeaker(boolean z) {
            this.enableDominantSpeaker = z;
            return this;
        }

        public Builder enableNetworkQuality(boolean z) {
            this.enableNetworkQuality = z;
            return this;
        }

        public Builder networkQualityConfiguration(NetworkQualityConfiguration networkQualityConfiguration2) {
            Preconditions.checkNotNull(networkQualityConfiguration2);
            this.networkQualityConfiguration = networkQualityConfiguration2;
            return this;
        }

        public Builder preferAudioCodecs(List<AudioCodec> list) {
            Preconditions.checkNotNull(list);
            this.preferredAudioCodecs = new ArrayList(list);
            return this;
        }

        public Builder preferVideoCodecs(List<VideoCodec> list) {
            Preconditions.checkNotNull(list);
            this.preferredVideoCodecs = new ArrayList(list);
            return this;
        }

        public Builder region(String str) {
            this.region = str;
            return this;
        }

        public Builder encodingParameters(EncodingParameters encodingParameters2) {
            Preconditions.checkNotNull(encodingParameters2);
            this.encodingParameters = encodingParameters2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder mediaFactory(MediaFactory mediaFactory2) {
            this.mediaFactory = mediaFactory2;
            return this;
        }

        public ConnectOptions build() {
            Preconditions.checkNotNull(this.accessToken, "Token must not be null.");
            Preconditions.checkArgument(!this.accessToken.equals(""), "Token must not be empty.");
            ConnectOptions.checkAudioTracksReleased(this.audioTracks);
            ConnectOptions.checkVideoTracksReleased(this.videoTracks);
            ConnectOptions.checkAudioCodecs(this.preferredAudioCodecs);
            ConnectOptions.checkVideoCodecs(this.preferredVideoCodecs);
            return new ConnectOptions(this);
        }
    }
}
