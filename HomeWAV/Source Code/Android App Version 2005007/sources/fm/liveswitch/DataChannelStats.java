package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class DataChannelStats extends BaseStats {
    private long _bytesReceived;
    private long _bytesSent;
    private String _label;
    private long _messagesReceived;
    private long _messagesSent;
    private boolean _ordered;
    private String _protocol;
    private DataChannelState _state;

    public DataChannelStats() {
        setState(DataChannelState.New);
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("label")) {
            setLabel(JsonSerializer.deserializeString(str2));
        } else if (str.equals("ordered")) {
            setOrdered(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("protocol")) {
            setProtocol(JsonSerializer.deserializeString(str2));
        } else if (str.equals("state")) {
            setState(stateFromString(JsonSerializer.deserializeString(str2)));
        } else if (str.equals("messagesSent")) {
            setMessagesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("messagesReceived")) {
            setMessagesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    public static DataChannelStats fromJson(String str) {
        return (DataChannelStats) JsonSerializer.deserializeObject(str, new IFunction0<DataChannelStats>() {
            public DataChannelStats invoke() {
                return new DataChannelStats();
            }
        }, new IAction3<DataChannelStats, String, String>() {
            public void invoke(DataChannelStats dataChannelStats, String str, String str2) {
                dataChannelStats.deserializeProperties(str, str2);
            }
        });
    }

    public static DataChannelStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, DataChannelStats>() {
            public String getId() {
                return "fm.liveswitch.DataChannelStats.fromJson";
            }

            public DataChannelStats invoke(String str) {
                return DataChannelStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (DataChannelStats[]) deserializeObjectArray.toArray(new DataChannelStats[0]);
    }

    public long getBytesReceived() {
        return this._bytesReceived;
    }

    public long getBytesSent() {
        return this._bytesSent;
    }

    public String getLabel() {
        return this._label;
    }

    public long getMessagesReceived() {
        return this._messagesReceived;
    }

    public long getMessagesSent() {
        return this._messagesSent;
    }

    public boolean getOrdered() {
        return this._ordered;
    }

    public String getProtocol() {
        return this._protocol;
    }

    public DataChannelState getState() {
        return this._state;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getLabel() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "label", JsonSerializer.serializeString(getLabel()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ordered", JsonSerializer.serializeBoolean(new NullableBoolean(getOrdered())));
        if (getProtocol() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "protocol", JsonSerializer.serializeString(getProtocol()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "state", JsonSerializer.serializeString(stateToString(getState())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesSent", JsonSerializer.serializeLong(new NullableLong(getMessagesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(new NullableLong(getBytesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesReceived", JsonSerializer.serializeLong(new NullableLong(getMessagesReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(new NullableLong(getBytesReceived())));
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
    public void setLabel(String str) {
        this._label = str;
    }

    /* access modifiers changed from: package-private */
    public void setMessagesReceived(long j) {
        this._messagesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setMessagesSent(long j) {
        this._messagesSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setOrdered(boolean z) {
        this._ordered = z;
    }

    /* access modifiers changed from: package-private */
    public void setProtocol(String str) {
        this._protocol = str;
    }

    /* access modifiers changed from: package-private */
    public void setState(DataChannelState dataChannelState) {
        this._state = dataChannelState;
    }

    private DataChannelState stateFromString(String str) {
        if (str.equals("new")) {
            return DataChannelState.New;
        }
        if (str.equals("connecting")) {
            return DataChannelState.Connecting;
        }
        if (str.equals("connected")) {
            return DataChannelState.Connected;
        }
        if (str.equals("failed")) {
            return DataChannelState.Failed;
        }
        if (str.equals("closing")) {
            return DataChannelState.Closing;
        }
        if (str.equals("closed")) {
            return DataChannelState.Closed;
        }
        return DataChannelState.New;
    }

    private String stateToString(DataChannelState dataChannelState) {
        if (dataChannelState == DataChannelState.New) {
            return "new";
        }
        if (dataChannelState == DataChannelState.Connecting) {
            return "connecting";
        }
        if (dataChannelState == DataChannelState.Connected) {
            return "connected";
        }
        if (dataChannelState == DataChannelState.Closing) {
            return "closing";
        }
        if (dataChannelState == DataChannelState.Closed) {
            return "closed";
        }
        if (dataChannelState == DataChannelState.Failed) {
            return "failed";
        }
        return null;
    }

    public static String toJson(DataChannelStats dataChannelStats) {
        return JsonSerializer.serializeObject(dataChannelStats, new IAction2<DataChannelStats, HashMap<String, String>>() {
            public void invoke(DataChannelStats dataChannelStats, HashMap<String, String> hashMap) {
                dataChannelStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(DataChannelStats[] dataChannelStatsArr) {
        return JsonSerializer.serializeObjectArray(dataChannelStatsArr, new IFunctionDelegate1<DataChannelStats, String>() {
            public String getId() {
                return "fm.liveswitch.DataChannelStats.toJson";
            }

            public String invoke(DataChannelStats dataChannelStats) {
                return DataChannelStats.toJson(dataChannelStats);
            }
        });
    }
}
