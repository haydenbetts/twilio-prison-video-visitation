package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class BitrateNotification {
    private int _bitrate;
    private String _mediaDescriptionId;
    private String _rtpStreamId;
    private long _synchronizationSource;

    public BitrateNotification() {
        setSynchronizationSource(-1);
        setMediaDescriptionId((String) null);
        setRtpStreamId((String) null);
        setBitrate(-1);
    }

    /* access modifiers changed from: protected */
    public void deserializeProperty(String str, String str2) {
        if (str == null) {
            return;
        }
        if (Global.equals(str, "synchronizationSource")) {
            setSynchronizationSource(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "mediaDescriptionId")) {
            setMediaDescriptionId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "rtpStreamId")) {
            setRtpStreamId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE)) {
            setBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        }
    }

    public static BitrateNotification fromJson(String str) {
        return (BitrateNotification) JsonSerializer.deserializeObject(str, new IFunction0<BitrateNotification>() {
            public BitrateNotification invoke() {
                return new BitrateNotification();
            }
        }, new IAction3<BitrateNotification, String, String>() {
            public void invoke(BitrateNotification bitrateNotification, String str, String str2) {
                bitrateNotification.deserializeProperty(str, str2);
            }
        });
    }

    public static BitrateNotification[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, BitrateNotification>() {
            public String getId() {
                return "fm.liveswitch.BitrateNotification.fromJson";
            }

            public BitrateNotification invoke(String str) {
                return BitrateNotification.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (BitrateNotification[]) deserializeObjectArray.toArray(new BitrateNotification[0]);
    }

    public int getBitrate() {
        return this._bitrate;
    }

    public String getMediaDescriptionId() {
        return this._mediaDescriptionId;
    }

    public String getRtpStreamId() {
        return this._rtpStreamId;
    }

    public long getSynchronizationSource() {
        return this._synchronizationSource;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        if (getSynchronizationSource() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "synchronizationSource", JsonSerializer.serializeLong(new NullableLong(getSynchronizationSource())));
        }
        if (getMediaDescriptionId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "mediaDescriptionId", JsonSerializer.serializeString(getMediaDescriptionId()));
        }
        if (getRtpStreamId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "rtpStreamId", JsonSerializer.serializeString(getRtpStreamId()));
        }
        if (getBitrate() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), FFmpegMediaMetadataRetriever.METADATA_KEY_VARIANT_BITRATE, JsonSerializer.serializeInteger(new NullableInteger(getBitrate())));
        }
    }

    public void setBitrate(int i) {
        this._bitrate = i;
    }

    public void setMediaDescriptionId(String str) {
        this._mediaDescriptionId = str;
    }

    public void setRtpStreamId(String str) {
        this._rtpStreamId = str;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    public static String toJson(BitrateNotification bitrateNotification) {
        return JsonSerializer.serializeObject(bitrateNotification, new IAction2<BitrateNotification, HashMap<String, String>>() {
            public void invoke(BitrateNotification bitrateNotification, HashMap<String, String> hashMap) {
                bitrateNotification.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(BitrateNotification[] bitrateNotificationArr) {
        return JsonSerializer.serializeObjectArray(bitrateNotificationArr, new IFunctionDelegate1<BitrateNotification, String>() {
            public String getId() {
                return "fm.liveswitch.BitrateNotification.toJson";
            }

            public String invoke(BitrateNotification bitrateNotification) {
                return BitrateNotification.toJson(bitrateNotification);
            }
        });
    }
}
