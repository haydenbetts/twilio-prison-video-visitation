package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaSenderStats extends MediaComponentStats implements IEquivalent<MediaSenderStats> {
    private long _bytesSent;
    private long _packetsSent;
    private int _roundTripTime;
    private MediaSourceStats _source;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "packetsSent")) {
            setPacketsSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "rtt")) {
            setRoundTripTime(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (Global.equals(str, "source")) {
            setSource(MediaSourceStats.fromJson(str2));
        }
    }

    public static MediaSenderStats fromJson(String str) {
        return (MediaSenderStats) JsonSerializer.deserializeObject(str, new IFunction0<MediaSenderStats>() {
            public MediaSenderStats invoke() {
                return new MediaSenderStats();
            }
        }, new IAction3<MediaSenderStats, String, String>() {
            public void invoke(MediaSenderStats mediaSenderStats, String str, String str2) {
                mediaSenderStats.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaSenderStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaSenderStats>() {
            public String getId() {
                return "fm.liveswitch.MediaSenderStats.fromJson";
            }

            public MediaSenderStats invoke(String str) {
                return MediaSenderStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaSenderStats[]) deserializeObjectArray.toArray(new MediaSenderStats[0]);
    }

    public long getBytesSent() {
        return this._bytesSent;
    }

    public long getPacketsSent() {
        return this._packetsSent;
    }

    public int getRoundTripTime() {
        return this._roundTripTime;
    }

    public MediaSourceStats getSource() {
        return this._source;
    }

    public boolean isEquivalent(MediaSenderStats mediaSenderStats) {
        return mediaSenderStats.getSynchronizationSource() == super.getSynchronizationSource() && (Global.equals(mediaSenderStats.getTrack(), super.getTrack()) || (super.getTrack() != null && super.getTrack().isEquivalent(mediaSenderStats.getTrack()))) && ((Global.equals(mediaSenderStats.getCodec(), super.getCodec()) || (super.getCodec() != null && super.getCodec().isEquivalent(mediaSenderStats.getCodec()))) && mediaSenderStats.getNackCount() == super.getNackCount() && mediaSenderStats.getPliCount() == super.getPliCount() && mediaSenderStats.getFirCount() == super.getFirCount() && mediaSenderStats.getLrrCount() == super.getLrrCount() && mediaSenderStats.getSliCount() == super.getSliCount() && mediaSenderStats.getPacketsSent() == getPacketsSent() && mediaSenderStats.getBytesSent() == getBytesSent() && mediaSenderStats.getRoundTripTime() == getRoundTripTime() && (Global.equals(mediaSenderStats.getSource(), getSource()) || (getSource() != null && getSource().isEquivalent(mediaSenderStats.getSource()))));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsSent", JsonSerializer.serializeLong(new NullableLong(getPacketsSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(new NullableLong(getBytesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "rtt", JsonSerializer.serializeInteger(new NullableInteger(getRoundTripTime())));
        if (getSource() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "source", MediaSourceStats.toJson(getSource()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setBytesSent(long j) {
        this._bytesSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setPacketsSent(long j) {
        this._packetsSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setRoundTripTime(int i) {
        this._roundTripTime = i;
    }

    /* access modifiers changed from: package-private */
    public void setSource(MediaSourceStats mediaSourceStats) {
        this._source = mediaSourceStats;
    }

    public static String toJson(MediaSenderStats mediaSenderStats) {
        return JsonSerializer.serializeObject(mediaSenderStats, new IAction2<MediaSenderStats, HashMap<String, String>>() {
            public void invoke(MediaSenderStats mediaSenderStats, HashMap<String, String> hashMap) {
                mediaSenderStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaSenderStats[] mediaSenderStatsArr) {
        return JsonSerializer.serializeObjectArray(mediaSenderStatsArr, new IFunctionDelegate1<MediaSenderStats, String>() {
            public String getId() {
                return "fm.liveswitch.MediaSenderStats.toJson";
            }

            public String invoke(MediaSenderStats mediaSenderStats) {
                return MediaSenderStats.toJson(mediaSenderStats);
            }
        });
    }
}
