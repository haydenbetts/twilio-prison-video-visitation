package fm.liveswitch;

import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class MediaComponentInfo extends Info {
    private CodecInfo _codec;
    private NullableLong _synchronizationSource = new NullableLong();
    private MediaTrackInfo _track;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, FFmpegMediaMetadataRetriever.METADATA_KEY_TRACK)) {
            setTrack(MediaTrackInfo.fromJson(str2));
        } else if (Global.equals(str, "codec")) {
            setCodec(CodecInfo.fromJson(str2));
        } else if (Global.equals(str, "synchronizationSource")) {
            setSynchronizationSource(JsonSerializer.deserializeLong(str2));
        }
    }

    public CodecInfo getCodec() {
        return this._codec;
    }

    public NullableLong getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public MediaTrackInfo getTrack() {
        return this._track;
    }

    public MediaComponentInfo() {
    }

    public MediaComponentInfo(MediaComponentStats mediaComponentStats, MediaComponentStats mediaComponentStats2) {
        MediaTrackStats mediaTrackStats;
        super.setId(mediaComponentStats.getId());
        MediaTrackStats track = mediaComponentStats.getTrack();
        track = mediaComponentStats2 != null ? (MediaTrackStats) Info.processObject(track, mediaComponentStats2.getTrack()) : track;
        CodecStats codecStats = null;
        if (track != null) {
            if (mediaComponentStats2 == null) {
                mediaTrackStats = null;
            } else {
                mediaTrackStats = mediaComponentStats2.getTrack();
            }
            setSynchronizationSource(mediaTrackStats == null ? new NullableLong(track.getSynchronizationSource()) : Info.processLong(track.getSynchronizationSource(), mediaTrackStats.getSynchronizationSource()));
            setTrack(new MediaTrackInfo(track, mediaTrackStats));
        }
        CodecStats codec = mediaComponentStats.getCodec();
        codec = mediaComponentStats2 != null ? (CodecStats) Info.processObject(codec, mediaComponentStats2.getCodec()) : codec;
        if (codec != null) {
            codecStats = mediaComponentStats2 != null ? mediaComponentStats2.getCodec() : codecStats;
            CodecInfo codecInfo = new CodecInfo();
            codecInfo.setId(codec.getId());
            codecInfo.setChannelCount(codecStats == null ? new NullableInteger(codec.getChannelCount()) : Info.processInteger(codec.getChannelCount(), codecStats.getChannelCount()));
            codecInfo.setClockRate(codecStats == null ? new NullableInteger(codec.getClockRate()) : Info.processInteger(codec.getClockRate(), codecStats.getClockRate()));
            String name = codec.getName();
            codecInfo.setName(codecStats != null ? Info.processString(name, codecStats.getName()) : name);
            String parameters = codec.getParameters();
            codecInfo.setParameters(codecStats != null ? Info.processString(parameters, codecStats.getParameters()) : parameters);
            codecInfo.setPayloadType(codecStats == null ? new NullableInteger(codec.getPayloadType()) : Info.processInteger(codec.getPayloadType(), codecStats.getPayloadType()));
            setCodec(codecInfo);
        }
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getTrack() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), FFmpegMediaMetadataRetriever.METADATA_KEY_TRACK, MediaTrackInfo.toJson(getTrack()));
        }
        if (getCodec() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "codec", CodecInfo.toJson(getCodec()));
        }
        if (getSynchronizationSource().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "synchronizationSource", JsonSerializer.serializeLong(getSynchronizationSource()));
        }
    }

    public void setCodec(CodecInfo codecInfo) {
        this._codec = codecInfo;
    }

    public void setSynchronizationSource(NullableLong nullableLong) {
        this._synchronizationSource = nullableLong;
    }

    public void setTrack(MediaTrackInfo mediaTrackInfo) {
        this._track = mediaTrackInfo;
    }
}
