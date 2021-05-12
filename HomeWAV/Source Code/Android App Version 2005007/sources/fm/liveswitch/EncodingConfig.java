package fm.liveswitch;

import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public abstract class EncodingConfig {
    private int _bitrate;
    private boolean _deactivated;
    private String _rtpStreamId;
    private long _synchronizationSource;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        if (str == null) {
            return;
        }
        if (Global.equals(str, "rtpStreamId")) {
            setRtpStreamId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "synchronizationSource")) {
            setSynchronizationSource(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "deactivated")) {
            setDeactivated(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (Global.equals(str, FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE)) {
            setBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        }
    }

    public EncodingConfig() {
        setRtpStreamId((String) null);
        setSynchronizationSource(-1);
        setDeactivated(false);
        setBitrate(-1);
    }

    public EncodingConfig(EncodingInfo encodingInfo) {
        setRtpStreamId(encodingInfo.getRtpStreamId());
        setSynchronizationSource(encodingInfo.getSynchronizationSource());
        setDeactivated(encodingInfo.getDeactivated());
        setBitrate(encodingInfo.getBitrate());
    }

    public int getBitrate() {
        return this._bitrate;
    }

    public boolean getDeactivated() {
        return this._deactivated;
    }

    public String getRtpStreamId() {
        return this._rtpStreamId;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        if (getRtpStreamId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "rtpStreamId", JsonSerializer.serializeString(getRtpStreamId()));
        }
        if (getSynchronizationSource() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "synchronizationSource", JsonSerializer.serializeLong(new NullableLong(getSynchronizationSource())));
        }
        if (getDeactivated()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "deactivated", JsonSerializer.serializeBoolean(new NullableBoolean(getDeactivated())));
        }
        if (getBitrate() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE, JsonSerializer.serializeInteger(new NullableInteger(getBitrate())));
        }
    }

    public void setBitrate(int i) {
        this._bitrate = i;
    }

    public void setDeactivated(boolean z) {
        this._deactivated = z;
    }

    /* access modifiers changed from: package-private */
    public void setRtpStreamId(String str) {
        this._rtpStreamId = str;
    }

    /* access modifiers changed from: package-private */
    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }
}
