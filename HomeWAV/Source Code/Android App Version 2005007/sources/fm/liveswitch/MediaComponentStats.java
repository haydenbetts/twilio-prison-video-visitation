package fm.liveswitch;

import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public abstract class MediaComponentStats extends BaseStats {
    private CodecStats _codec;
    private long _firCount;
    private long _lrrCount;
    private long _nackCount;
    private long _pliCount;
    private String _repairedRtpStreamId;
    private String _rtpStreamId;
    private long _sliCount;
    private long _synchronizationSource;
    private MediaTrackStats _track;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("ssrc")) {
            setSynchronizationSource(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("rid")) {
            setRtpStreamId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("rrid")) {
            setRepairedRtpStreamId(JsonSerializer.deserializeString(str2));
        } else if (str.equals(FFmpegMediaMetadataRetriever.METADATA_KEY_TRACK)) {
            setTrack(MediaTrackStats.fromJson(str2));
        } else if (str.equals("codec")) {
            setCodec(CodecStats.fromJson(str2));
        } else if (str.equals("nackCount")) {
            setNackCount(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("pliCount")) {
            setPliCount(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("firCount")) {
            setFirCount(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("lrrCount")) {
            setLrrCount(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("sliCount")) {
            setSliCount(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    public CodecStats getCodec() {
        return this._codec;
    }

    public long getFirCount() {
        return this._firCount;
    }

    public long getLrrCount() {
        return this._lrrCount;
    }

    public long getNackCount() {
        return this._nackCount;
    }

    public long getPliCount() {
        return this._pliCount;
    }

    public String getRepairedRtpStreamId() {
        return this._repairedRtpStreamId;
    }

    public String getRtpStreamId() {
        return this._rtpStreamId;
    }

    public long getSliCount() {
        return this._sliCount;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    public MediaTrackStats getTrack() {
        return this._track;
    }

    protected MediaComponentStats() {
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ssrc", JsonSerializer.serializeLong(new NullableLong(getSynchronizationSource())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "rid", JsonSerializer.serializeString(getRtpStreamId()));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "rrid", JsonSerializer.serializeString(getRepairedRtpStreamId()));
        if (getTrack() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), FFmpegMediaMetadataRetriever.METADATA_KEY_TRACK, MediaTrackStats.toJson(getTrack()));
        }
        if (getCodec() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "codec", CodecStats.toJson(getCodec()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "nackCount", JsonSerializer.serializeLong(new NullableLong(getNackCount())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "pliCount", JsonSerializer.serializeLong(new NullableLong(getPliCount())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "firCount", JsonSerializer.serializeLong(new NullableLong(getFirCount())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "lrrCount", JsonSerializer.serializeLong(new NullableLong(getLrrCount())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sliCount", JsonSerializer.serializeLong(new NullableLong(getSliCount())));
    }

    public void setCodec(CodecStats codecStats) {
        this._codec = codecStats;
    }

    public void setFirCount(long j) {
        this._firCount = j;
    }

    public void setLrrCount(long j) {
        this._lrrCount = j;
    }

    public void setNackCount(long j) {
        this._nackCount = j;
    }

    public void setPliCount(long j) {
        this._pliCount = j;
    }

    public void setRepairedRtpStreamId(String str) {
        this._repairedRtpStreamId = str;
    }

    public void setRtpStreamId(String str) {
        this._rtpStreamId = str;
    }

    public void setSliCount(long j) {
        this._sliCount = j;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    public void setTrack(MediaTrackStats mediaTrackStats) {
        this._track = mediaTrackStats;
    }
}
