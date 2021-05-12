package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaReceiverStats extends MediaComponentStats implements IEquivalent<MediaReceiverStats> {
    private long _bytesReceived;
    private int _jitter;
    private long _packetsDiscarded;
    private long _packetsDuplicated;
    private long _packetsLost;
    private long _packetsReceived;
    private long _packetsRepaired;
    private MediaSinkStats _sink;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("packetsReceived")) {
            setPacketsReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("packetsLost")) {
            setPacketsLost(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("jitter")) {
            setJitter(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("packetsDiscarded")) {
            setPacketsDiscarded(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("packetsDuplicated")) {
            setPacketsDuplicated(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("packetsRepaired")) {
            setPacketsRepaired(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("sink")) {
            setSink(MediaSinkStats.fromJson(str2));
        }
    }

    public static MediaReceiverStats fromJson(String str) {
        return (MediaReceiverStats) JsonSerializer.deserializeObject(str, new IFunction0<MediaReceiverStats>() {
            public MediaReceiverStats invoke() {
                return new MediaReceiverStats();
            }
        }, new IAction3<MediaReceiverStats, String, String>() {
            public void invoke(MediaReceiverStats mediaReceiverStats, String str, String str2) {
                mediaReceiverStats.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaReceiverStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaReceiverStats>() {
            public String getId() {
                return "fm.liveswitch.MediaReceiverStats.fromJson";
            }

            public MediaReceiverStats invoke(String str) {
                return MediaReceiverStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaReceiverStats[]) deserializeObjectArray.toArray(new MediaReceiverStats[0]);
    }

    public long getBytesReceived() {
        return this._bytesReceived;
    }

    public int getJitter() {
        return this._jitter;
    }

    public long getPacketsDiscarded() {
        return this._packetsDiscarded;
    }

    public long getPacketsDuplicated() {
        return this._packetsDuplicated;
    }

    public long getPacketsLost() {
        return this._packetsLost;
    }

    public long getPacketsReceived() {
        return this._packetsReceived;
    }

    public long getPacketsRepaired() {
        return this._packetsRepaired;
    }

    public MediaSinkStats getSink() {
        return this._sink;
    }

    public boolean isEquivalent(MediaReceiverStats mediaReceiverStats) {
        return mediaReceiverStats.getSynchronizationSource() == super.getSynchronizationSource() && (Global.equals(mediaReceiverStats.getTrack(), super.getTrack()) || (super.getTrack() != null && super.getTrack().isEquivalent(mediaReceiverStats.getTrack()))) && ((Global.equals(mediaReceiverStats.getCodec(), super.getCodec()) || (super.getCodec() != null && super.getCodec().isEquivalent(mediaReceiverStats.getCodec()))) && mediaReceiverStats.getNackCount() == super.getNackCount() && mediaReceiverStats.getPliCount() == super.getPliCount() && mediaReceiverStats.getFirCount() == super.getFirCount() && mediaReceiverStats.getLrrCount() == super.getLrrCount() && mediaReceiverStats.getSliCount() == super.getSliCount() && mediaReceiverStats.getPacketsReceived() == getPacketsReceived() && mediaReceiverStats.getBytesReceived() == getBytesReceived() && mediaReceiverStats.getPacketsLost() == getPacketsLost() && mediaReceiverStats.getJitter() == getJitter() && mediaReceiverStats.getPacketsDiscarded() == getPacketsDiscarded() && mediaReceiverStats.getPacketsDuplicated() == getPacketsDuplicated() && mediaReceiverStats.getPacketsRepaired() == getPacketsRepaired() && (Global.equals(mediaReceiverStats.getSink(), getSink()) || (getSink() != null && getSink().isEquivalent(mediaReceiverStats.getSink()))));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsReceived", JsonSerializer.serializeLong(new NullableLong(getPacketsReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(new NullableLong(getBytesReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsLost", JsonSerializer.serializeLong(new NullableLong(getPacketsLost())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "jitter", JsonSerializer.serializeInteger(new NullableInteger(getJitter())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsDiscarded", JsonSerializer.serializeLong(new NullableLong(getPacketsDiscarded())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsDuplicated", JsonSerializer.serializeLong(new NullableLong(getPacketsDuplicated())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsRepaired", JsonSerializer.serializeLong(new NullableLong(getPacketsRepaired())));
        if (getSink() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sink", MediaSinkStats.toJson(getSink()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setBytesReceived(long j) {
        this._bytesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setJitter(int i) {
        this._jitter = i;
    }

    /* access modifiers changed from: package-private */
    public void setPacketsDiscarded(long j) {
        this._packetsDiscarded = j;
    }

    /* access modifiers changed from: package-private */
    public void setPacketsDuplicated(long j) {
        this._packetsDuplicated = j;
    }

    /* access modifiers changed from: package-private */
    public void setPacketsLost(long j) {
        this._packetsLost = j;
    }

    /* access modifiers changed from: package-private */
    public void setPacketsReceived(long j) {
        this._packetsReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setPacketsRepaired(long j) {
        this._packetsRepaired = j;
    }

    /* access modifiers changed from: package-private */
    public void setSink(MediaSinkStats mediaSinkStats) {
        this._sink = mediaSinkStats;
    }

    public static String toJson(MediaReceiverStats mediaReceiverStats) {
        return JsonSerializer.serializeObject(mediaReceiverStats, new IAction2<MediaReceiverStats, HashMap<String, String>>() {
            public void invoke(MediaReceiverStats mediaReceiverStats, HashMap<String, String> hashMap) {
                mediaReceiverStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaReceiverStats[] mediaReceiverStatsArr) {
        return JsonSerializer.serializeObjectArray(mediaReceiverStatsArr, new IFunctionDelegate1<MediaReceiverStats, String>() {
            public String getId() {
                return "fm.liveswitch.MediaReceiverStats.toJson";
            }

            public String invoke(MediaReceiverStats mediaReceiverStats) {
                return MediaReceiverStats.toJson(mediaReceiverStats);
            }
        });
    }
}
