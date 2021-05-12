package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaStreamStats extends StreamStats {
    private StreamDirection _direction;
    private int _maxReceiveBitrate;
    private int _maxSendBitrate;
    private MediaReceiverStats[] _receivers;
    private MediaSenderStats[] _senders;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "direction")) {
            setDirection(StreamDirectionHelper.directionFromString(JsonSerializer.deserializeString(str2)));
        } else if (Global.equals(str, "maxSendBitrate")) {
            setMaxSendBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (Global.equals(str, "maxReceiveBitrate")) {
            setMaxReceiveBitrate(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (Global.equals(str, "senders")) {
            setSenders(MediaSenderStats.fromJsonArray(str2));
        } else if (Global.equals(str, "receivers")) {
            setReceivers(MediaReceiverStats.fromJsonArray(str2));
        }
    }

    public static MediaStreamStats fromJson(String str) {
        return (MediaStreamStats) JsonSerializer.deserializeObject(str, new IFunction0<MediaStreamStats>() {
            public MediaStreamStats invoke() {
                return new MediaStreamStats();
            }
        }, new IAction3<MediaStreamStats, String, String>() {
            public void invoke(MediaStreamStats mediaStreamStats, String str, String str2) {
                mediaStreamStats.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaStreamStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaStreamStats>() {
            public String getId() {
                return "fm.liveswitch.MediaStreamStats.fromJson";
            }

            public MediaStreamStats invoke(String str) {
                return MediaStreamStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaStreamStats[]) deserializeObjectArray.toArray(new MediaStreamStats[0]);
    }

    public StreamDirection getDirection() {
        return this._direction;
    }

    public int getMaxReceiveBitrate() {
        return this._maxReceiveBitrate;
    }

    public int getMaxSendBitrate() {
        return this._maxSendBitrate;
    }

    public MediaReceiverStats getReceiver() {
        MediaReceiverStats[] receivers = getReceivers();
        if (receivers == null || ArrayExtensions.getLength((Object[]) receivers) == 0) {
            return null;
        }
        return receivers[0];
    }

    public MediaReceiverStats[] getReceivers() {
        return this._receivers;
    }

    public MediaSenderStats getSender() {
        MediaSenderStats[] senders = getSenders();
        if (senders == null || ArrayExtensions.getLength((Object[]) senders) == 0) {
            return null;
        }
        return senders[0];
    }

    public MediaSenderStats[] getSenders() {
        return this._senders;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "direction", JsonSerializer.serializeString(StreamDirectionHelper.directionToString(getDirection())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "maxSendBitrate", JsonSerializer.serializeInteger(new NullableInteger(getMaxSendBitrate())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "maxReceiveBitrate", JsonSerializer.serializeInteger(new NullableInteger(getMaxReceiveBitrate())));
        if (getSenders() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "senders", MediaSenderStats.toJsonArray(getSenders()));
        }
        if (getReceivers() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "receivers", MediaReceiverStats.toJsonArray(getReceivers()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setDirection(StreamDirection streamDirection) {
        this._direction = streamDirection;
    }

    /* access modifiers changed from: package-private */
    public void setMaxReceiveBitrate(int i) {
        this._maxReceiveBitrate = i;
    }

    /* access modifiers changed from: package-private */
    public void setMaxSendBitrate(int i) {
        this._maxSendBitrate = i;
    }

    /* access modifiers changed from: package-private */
    public void setReceivers(MediaReceiverStats[] mediaReceiverStatsArr) {
        this._receivers = mediaReceiverStatsArr;
    }

    /* access modifiers changed from: package-private */
    public void setSenders(MediaSenderStats[] mediaSenderStatsArr) {
        this._senders = mediaSenderStatsArr;
    }

    public static String toJson(MediaStreamStats mediaStreamStats) {
        return JsonSerializer.serializeObject(mediaStreamStats, new IAction2<MediaStreamStats, HashMap<String, String>>() {
            public void invoke(MediaStreamStats mediaStreamStats, HashMap<String, String> hashMap) {
                mediaStreamStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaStreamStats[] mediaStreamStatsArr) {
        return JsonSerializer.serializeObjectArray(mediaStreamStatsArr, new IFunctionDelegate1<MediaStreamStats, String>() {
            public String getId() {
                return "fm.liveswitch.MediaStreamStats.toJson";
            }

            public String invoke(MediaStreamStats mediaStreamStats) {
                return MediaStreamStats.toJson(mediaStreamStats);
            }
        });
    }
}
