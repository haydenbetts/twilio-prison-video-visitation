package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class MediaTrackStats extends BaseStats implements IEquivalent<MediaTrackStats> {
    private int _bitrate;
    private boolean _detached;
    private int _frameHeight;
    private double _frameRate;
    private int _frameWidth;
    private long _framesCorrupted;
    private long _framesDecoded;
    private long _framesDropped;
    private long _framesEncoded;
    private long _framesReceived;
    private long _framesSent;
    private int _maxBitrate;
    private int _minBitrate;
    private boolean _muted;
    private String[] _repairedRtpStreamIds;
    private String[] _rtpStreamIds;
    private boolean _stopped;
    private long[] _synchronizationSources;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("stopped")) {
            setStopped(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("detached")) {
            setDetached(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("muted")) {
            setMuted(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("ssrcs")) {
            setSynchronizationSources(JsonSerializer.deserializeLongArray(str2));
        } else if (str.equals("minBitrate")) {
            setMinBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("maxBitrate")) {
            setMaxBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals(FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE)) {
            setBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("frameWidth")) {
            setFrameWidth(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("frameHeight")) {
            setFrameHeight(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("frameRate")) {
            setFrameRate(JsonSerializer.deserializeDouble(str2).getValue());
        } else if (str.equals("framesSent")) {
            setFramesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("framesReceived")) {
            setFramesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("framesDecoded")) {
            setFramesDecoded(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("framesDropped")) {
            setFramesDropped(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("framesCorrupted")) {
            setFramesCorrupted(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("framesEncoded")) {
            setFramesEncoded(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    public static MediaTrackStats fromJson(String str) {
        return (MediaTrackStats) JsonSerializer.deserializeObject(str, new IFunction0<MediaTrackStats>() {
            public MediaTrackStats invoke() {
                return new MediaTrackStats();
            }
        }, new IAction3<MediaTrackStats, String, String>() {
            public void invoke(MediaTrackStats mediaTrackStats, String str, String str2) {
                mediaTrackStats.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaTrackStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaTrackStats>() {
            public String getId() {
                return "fm.liveswitch.MediaTrackStats.fromJson";
            }

            public MediaTrackStats invoke(String str) {
                return MediaTrackStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaTrackStats[]) deserializeObjectArray.toArray(new MediaTrackStats[0]);
    }

    public int getBitrate() {
        return this._bitrate;
    }

    public boolean getDetached() {
        return this._detached;
    }

    public int getFrameHeight() {
        return this._frameHeight;
    }

    public double getFrameRate() {
        return this._frameRate;
    }

    public long getFramesCorrupted() {
        return this._framesCorrupted;
    }

    public long getFramesDecoded() {
        return this._framesDecoded;
    }

    public long getFramesDropped() {
        return this._framesDropped;
    }

    public long getFramesEncoded() {
        return this._framesEncoded;
    }

    public long getFramesReceived() {
        return this._framesReceived;
    }

    public long getFramesSent() {
        return this._framesSent;
    }

    public int getFrameWidth() {
        return this._frameWidth;
    }

    public int getMaxBitrate() {
        return this._maxBitrate;
    }

    public int getMinBitrate() {
        return this._minBitrate;
    }

    public boolean getMuted() {
        return this._muted;
    }

    public String getRepairedRtpStreamId() {
        String[] repairedRtpStreamIds = getRepairedRtpStreamIds();
        if (repairedRtpStreamIds == null || ArrayExtensions.getLength((Object[]) repairedRtpStreamIds) == 0) {
            return null;
        }
        return repairedRtpStreamIds[0];
    }

    public String[] getRepairedRtpStreamIds() {
        return this._repairedRtpStreamIds;
    }

    public String getRtpStreamId() {
        String[] rtpStreamIds = getRtpStreamIds();
        if (rtpStreamIds == null || ArrayExtensions.getLength((Object[]) rtpStreamIds) == 0) {
            return null;
        }
        return rtpStreamIds[0];
    }

    public String[] getRtpStreamIds() {
        return this._rtpStreamIds;
    }

    public boolean getStopped() {
        return this._stopped;
    }

    public long getSynchronizationSource() {
        long[] synchronizationSources = getSynchronizationSources();
        if (synchronizationSources == null || ArrayExtensions.getLength(synchronizationSources) == 0) {
            return 0;
        }
        return synchronizationSources[0];
    }

    public long[] getSynchronizationSources() {
        return this._synchronizationSources;
    }

    public boolean isEquivalent(MediaTrackStats mediaTrackStats) {
        return mediaTrackStats != null && Global.equals(Boolean.valueOf(mediaTrackStats.getStopped()), Boolean.valueOf(getStopped())) && Global.equals(Boolean.valueOf(mediaTrackStats.getDetached()), Boolean.valueOf(getDetached())) && Global.equals(Boolean.valueOf(mediaTrackStats.getMuted()), Boolean.valueOf(getMuted())) && mediaTrackStats.getSynchronizationSources() == getSynchronizationSources() && mediaTrackStats.getMinBitrate() == getMinBitrate() && mediaTrackStats.getMaxBitrate() == getMaxBitrate() && mediaTrackStats.getBitrate() == getBitrate() && mediaTrackStats.getFrameWidth() == getFrameWidth() && mediaTrackStats.getFrameHeight() == getFrameHeight() && mediaTrackStats.getFrameRate() == getFrameRate() && mediaTrackStats.getFramesSent() == getFramesSent() && mediaTrackStats.getFramesReceived() == getFramesReceived() && mediaTrackStats.getFramesDecoded() == getFramesDecoded() && mediaTrackStats.getFramesDropped() == getFramesDropped() && mediaTrackStats.getFramesCorrupted() == getFramesCorrupted() && mediaTrackStats.getFramesEncoded() == getFramesEncoded();
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "stopped", JsonSerializer.serializeBoolean(new NullableBoolean(getStopped())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "detached", JsonSerializer.serializeBoolean(new NullableBoolean(getDetached())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "muted", JsonSerializer.serializeBoolean(new NullableBoolean(getMuted())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ssrcs", JsonSerializer.serializeLongArray(getSynchronizationSources()));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "minBitrate", JsonSerializer.serializeInteger(new NullableInteger(getMinBitrate())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "maxBitrate", JsonSerializer.serializeInteger(new NullableInteger(getMaxBitrate())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE, JsonSerializer.serializeInteger(new NullableInteger(getBitrate())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameWidth", JsonSerializer.serializeInteger(new NullableInteger(getFrameWidth())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameHeight", JsonSerializer.serializeInteger(new NullableInteger(getFrameHeight())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "frameRate", JsonSerializer.serializeDouble(new NullableDouble(getFrameRate())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesSent", JsonSerializer.serializeLong(new NullableLong(getFramesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesReceived", JsonSerializer.serializeLong(new NullableLong(getFramesReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesDecoded", JsonSerializer.serializeLong(new NullableLong(getFramesDecoded())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesDropped", JsonSerializer.serializeLong(new NullableLong(getFramesDropped())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesCorrupted", JsonSerializer.serializeLong(new NullableLong(getFramesCorrupted())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "framesEncoded", JsonSerializer.serializeLong(new NullableLong(getFramesEncoded())));
    }

    /* access modifiers changed from: package-private */
    public void setBitrate(int i) {
        this._bitrate = i;
    }

    /* access modifiers changed from: package-private */
    public void setDetached(boolean z) {
        this._detached = z;
    }

    /* access modifiers changed from: package-private */
    public void setFrameHeight(int i) {
        this._frameHeight = i;
    }

    /* access modifiers changed from: package-private */
    public void setFrameRate(double d) {
        this._frameRate = d;
    }

    /* access modifiers changed from: package-private */
    public void setFramesCorrupted(long j) {
        this._framesCorrupted = j;
    }

    /* access modifiers changed from: package-private */
    public void setFramesDecoded(long j) {
        this._framesDecoded = j;
    }

    /* access modifiers changed from: package-private */
    public void setFramesDropped(long j) {
        this._framesDropped = j;
    }

    /* access modifiers changed from: package-private */
    public void setFramesEncoded(long j) {
        this._framesEncoded = j;
    }

    /* access modifiers changed from: package-private */
    public void setFramesReceived(long j) {
        this._framesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setFramesSent(long j) {
        this._framesSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setFrameWidth(int i) {
        this._frameWidth = i;
    }

    /* access modifiers changed from: package-private */
    public void setMaxBitrate(int i) {
        this._maxBitrate = i;
    }

    /* access modifiers changed from: package-private */
    public void setMinBitrate(int i) {
        this._minBitrate = i;
    }

    /* access modifiers changed from: package-private */
    public void setMuted(boolean z) {
        this._muted = z;
    }

    /* access modifiers changed from: package-private */
    public void setRepairedRtpStreamIds(String[] strArr) {
        this._repairedRtpStreamIds = strArr;
    }

    /* access modifiers changed from: package-private */
    public void setRtpStreamIds(String[] strArr) {
        this._rtpStreamIds = strArr;
    }

    /* access modifiers changed from: package-private */
    public void setStopped(boolean z) {
        this._stopped = z;
    }

    /* access modifiers changed from: package-private */
    public void setSynchronizationSources(long[] jArr) {
        this._synchronizationSources = jArr;
    }

    public static String toJson(MediaTrackStats mediaTrackStats) {
        return JsonSerializer.serializeObject(mediaTrackStats, new IAction2<MediaTrackStats, HashMap<String, String>>() {
            public void invoke(MediaTrackStats mediaTrackStats, HashMap<String, String> hashMap) {
                mediaTrackStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaTrackStats[] mediaTrackStatsArr) {
        return JsonSerializer.serializeObjectArray(mediaTrackStatsArr, new IFunctionDelegate1<MediaTrackStats, String>() {
            public String getId() {
                return "fm.liveswitch.MediaTrackStats.toJson";
            }

            public String invoke(MediaTrackStats mediaTrackStats) {
                return MediaTrackStats.toJson(mediaTrackStats);
            }
        });
    }
}
