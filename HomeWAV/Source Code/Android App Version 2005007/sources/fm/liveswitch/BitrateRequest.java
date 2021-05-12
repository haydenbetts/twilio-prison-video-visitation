package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;
import wseemann.media.FFmpegMediaMetadataRetriever;

public class BitrateRequest {
    private int _bitrate;
    private String _mediaDescriptionId;
    private String _rtpStreamId;
    private long _senderSynchronizationSource;
    private long _synchronizationSource;

    public BitrateRequest() {
        setSynchronizationSource(-1);
        setMediaDescriptionId((String) null);
        setRtpStreamId((String) null);
        setBitrate(-1);
        setSenderSynchronizationSource(-1);
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
        } else if (Global.equals(str, "senderSynchronizationSource")) {
            setSenderSynchronizationSource(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    public static BitrateRequest fromJson(String str) {
        return (BitrateRequest) JsonSerializer.deserializeObject(str, new IFunction0<BitrateRequest>() {
            public BitrateRequest invoke() {
                return new BitrateRequest();
            }
        }, new IAction3<BitrateRequest, String, String>() {
            public void invoke(BitrateRequest bitrateRequest, String str, String str2) {
                bitrateRequest.deserializeProperty(str, str2);
            }
        });
    }

    public static BitrateRequest[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, BitrateRequest>() {
            public String getId() {
                return "fm.liveswitch.BitrateRequest.fromJson";
            }

            public BitrateRequest invoke(String str) {
                return BitrateRequest.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (BitrateRequest[]) deserializeObjectArray.toArray(new BitrateRequest[0]);
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

    public long getSenderSynchronizationSource() {
        return this._senderSynchronizationSource;
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
        if (getSenderSynchronizationSource() != -1) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "senderSynchronizationSource", JsonSerializer.serializeLong(new NullableLong(getSenderSynchronizationSource())));
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

    public void setSenderSynchronizationSource(long j) {
        this._senderSynchronizationSource = j;
    }

    public void setSynchronizationSource(long j) {
        this._synchronizationSource = j;
    }

    public static String toJson(BitrateRequest bitrateRequest) {
        return JsonSerializer.serializeObject(bitrateRequest, new IAction2<BitrateRequest, HashMap<String, String>>() {
            public void invoke(BitrateRequest bitrateRequest, HashMap<String, String> hashMap) {
                bitrateRequest.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(BitrateRequest[] bitrateRequestArr) {
        return JsonSerializer.serializeObjectArray(bitrateRequestArr, new IFunctionDelegate1<BitrateRequest, String>() {
            public String getId() {
                return "fm.liveswitch.BitrateRequest.toJson";
            }

            public String invoke(BitrateRequest bitrateRequest) {
                return BitrateRequest.toJson(bitrateRequest);
            }
        });
    }
}
