package fm.liveswitch;

import com.urbanairship.MessageCenterDataManager;
import java.util.ArrayList;
import java.util.HashMap;

public class EventInfo extends Info {
    private ChannelInfo _channel;
    private ClientInfo _client;
    private ConnectionInfo _connection;
    private boolean _forced;
    private String _origin;
    private long _timestamp;
    private String _type;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("id")) {
            super.setId(JsonSerializer.deserializeString(str2));
        } else if (str.equals(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP)) {
            setTimestamp(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals(TtmlNode.ATTR_TTS_ORIGIN)) {
            setOrigin(JsonSerializer.deserializeString(str2));
        } else if (str.equals("type")) {
            setType(JsonSerializer.deserializeString(str2));
        } else if (str.equals("forced")) {
            setForced(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals(Modules.CHANNEL_MODULE)) {
            setChannel(ChannelInfo.fromJson(str2));
        } else if (str.equals("client")) {
            setClient(ClientInfo.fromJson(str2));
        } else if (str.equals("connection")) {
            setConnection(ConnectionInfo.fromJson(str2));
        }
    }

    public EventInfo() {
    }

    public EventInfo(String str) {
        if (str != null) {
            setTimestamp(UnixTimestamp.getUtcNowMillis());
            setOrigin(EventOrigin.getClient());
            setType(str);
            return;
        }
        throw new RuntimeException(new Exception("Type cannot be null."));
    }

    public static EventInfo fromJson(String str) {
        return (EventInfo) JsonSerializer.deserializeObject(str, new IFunction0<EventInfo>() {
            public EventInfo invoke() {
                return new EventInfo();
            }
        }, new IAction3<EventInfo, String, String>() {
            public void invoke(EventInfo eventInfo, String str, String str2) {
                eventInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static EventInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, EventInfo>() {
            public String getId() {
                return "fm.liveswitch.EventInfo.fromJson";
            }

            public EventInfo invoke(String str) {
                return EventInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (EventInfo[]) deserializeObjectArray.toArray(new EventInfo[0]);
    }

    public ChannelInfo getChannel() {
        return this._channel;
    }

    public ClientInfo getClient() {
        return this._client;
    }

    public ConnectionInfo getConnection() {
        return this._connection;
    }

    public boolean getForced() {
        return this._forced;
    }

    public String getOrigin() {
        return this._origin;
    }

    public long getTimestamp() {
        return this._timestamp;
    }

    public String getType() {
        return this._type;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (super.getId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(super.getId()));
        }
        if (getTimestamp() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP, JsonSerializer.serializeLong(new NullableLong(getTimestamp())));
        }
        if (getOrigin() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), TtmlNode.ATTR_TTS_ORIGIN, JsonSerializer.serializeString(getOrigin()));
        }
        if (getType() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(getType()));
        }
        if (getForced()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "forced", JsonSerializer.serializeBoolean(new NullableBoolean(getForced())));
        }
        if (getChannel() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), Modules.CHANNEL_MODULE, ChannelInfo.toJson(getChannel()));
        }
        if (getClient() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "client", ClientInfo.toJson(getClient()));
        }
        if (getConnection() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "connection", ConnectionInfo.toJson(getConnection()));
        }
    }

    public void setChannel(ChannelInfo channelInfo) {
        this._channel = channelInfo;
    }

    public void setClient(ClientInfo clientInfo) {
        this._client = clientInfo;
    }

    public void setConnection(ConnectionInfo connectionInfo) {
        this._connection = connectionInfo;
    }

    public void setForced(boolean z) {
        this._forced = z;
    }

    public void setOrigin(String str) {
        this._origin = str;
    }

    public void setTimestamp(long j) {
        this._timestamp = j;
    }

    public void setType(String str) {
        this._type = str;
    }

    public static String toJson(EventInfo eventInfo) {
        return JsonSerializer.serializeObject(eventInfo, new IAction2<EventInfo, HashMap<String, String>>() {
            public void invoke(EventInfo eventInfo, HashMap<String, String> hashMap) {
                eventInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(EventInfo[] eventInfoArr) {
        return JsonSerializer.serializeObjectArray(eventInfoArr, new IFunctionDelegate1<EventInfo, String>() {
            public String getId() {
                return "fm.liveswitch.EventInfo.toJson";
            }

            public String invoke(EventInfo eventInfo) {
                return EventInfo.toJson(eventInfo);
            }
        });
    }
}
