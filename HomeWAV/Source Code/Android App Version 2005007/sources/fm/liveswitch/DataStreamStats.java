package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class DataStreamStats extends StreamStats {
    private long _bytesReceived;
    private long _bytesSent;
    private DataChannelStats[] _channels;
    private long _messagesReceived;
    private long _messagesSent;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "messagesSent")) {
            setMessagesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "messagesReceived")) {
            setMessagesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (Global.equals(str, "channels")) {
            setChannels(DataChannelStats.fromJsonArray(str2));
        }
    }

    public static DataStreamStats fromJson(String str) {
        return (DataStreamStats) JsonSerializer.deserializeObject(str, new IFunction0<DataStreamStats>() {
            public DataStreamStats invoke() {
                return new DataStreamStats();
            }
        }, new IAction3<DataStreamStats, String, String>() {
            public void invoke(DataStreamStats dataStreamStats, String str, String str2) {
                dataStreamStats.deserializeProperties(str, str2);
            }
        });
    }

    public static DataStreamStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, DataStreamStats>() {
            public String getId() {
                return "fm.liveswitch.DataStreamStats.fromJson";
            }

            public DataStreamStats invoke(String str) {
                return DataStreamStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (DataStreamStats[]) deserializeObjectArray.toArray(new DataStreamStats[0]);
    }

    public long getBytesReceived() {
        return this._bytesReceived;
    }

    public long getBytesSent() {
        return this._bytesSent;
    }

    public DataChannelStats getChannel() {
        return (DataChannelStats) Utility.firstOrDefault((T[]) getChannels());
    }

    public DataChannelStats getChannel(String str) {
        for (DataChannelStats dataChannelStats : getChannels()) {
            if (Global.equals(dataChannelStats.getId(), str)) {
                return dataChannelStats;
            }
        }
        return null;
    }

    public DataChannelStats[] getChannels() {
        return this._channels;
    }

    public DataChannelStats getDataChannel() {
        return getChannel();
    }

    public DataChannelStats getDataChannel(String str) {
        return getChannel(str);
    }

    public DataChannelStats[] getDataChannels() {
        return getChannels();
    }

    public long getMessagesReceived() {
        return this._messagesReceived;
    }

    public long getMessagesSent() {
        return this._messagesSent;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesSent", JsonSerializer.serializeLong(new NullableLong(getMessagesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(new NullableLong(getBytesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesReceived", JsonSerializer.serializeLong(new NullableLong(getMessagesReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(new NullableLong(getBytesReceived())));
        if (getChannels() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channels", DataChannelStats.toJsonArray(getChannels()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setBytesReceived(long j) {
        this._bytesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setBytesSent(long j) {
        this._bytesSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setChannels(DataChannelStats[] dataChannelStatsArr) {
        this._channels = dataChannelStatsArr;
    }

    /* access modifiers changed from: package-private */
    public void setMessagesReceived(long j) {
        this._messagesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setMessagesSent(long j) {
        this._messagesSent = j;
    }

    public static String toJson(DataStreamStats dataStreamStats) {
        return JsonSerializer.serializeObject(dataStreamStats, new IAction2<DataStreamStats, HashMap<String, String>>() {
            public void invoke(DataStreamStats dataStreamStats, HashMap<String, String> hashMap) {
                dataStreamStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(DataStreamStats[] dataStreamStatsArr) {
        return JsonSerializer.serializeObjectArray(dataStreamStatsArr, new IFunctionDelegate1<DataStreamStats, String>() {
            public String getId() {
                return "fm.liveswitch.DataStreamStats.toJson";
            }

            public String invoke(DataStreamStats dataStreamStats) {
                return DataStreamStats.toJson(dataStreamStats);
            }
        });
    }
}
