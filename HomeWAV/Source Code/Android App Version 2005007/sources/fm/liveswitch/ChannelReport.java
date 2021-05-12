package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class ChannelReport extends Report {
    private String _id;
    private NullableLong _messageBytesReceived = new NullableLong();
    private NullableLong _messageBytesSent = new NullableLong();
    private NullableLong _messagesReceived = new NullableLong();
    private NullableLong _messagesSent = new NullableLong();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "id")) {
            setId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "messagesSent")) {
            setMessagesSent(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "messageBytesSent")) {
            setMessageBytesSent(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "messagesReceived")) {
            setMessagesReceived(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "messageBytesReceived")) {
            setMessageBytesReceived(JsonSerializer.deserializeLong(str2));
        }
    }

    public static ChannelReport fromJson(String str) {
        return (ChannelReport) JsonSerializer.deserializeObject(str, new IFunction0<ChannelReport>() {
            public ChannelReport invoke() {
                return new ChannelReport();
            }
        }, new IAction3<ChannelReport, String, String>() {
            public void invoke(ChannelReport channelReport, String str, String str2) {
                channelReport.deserializeProperties(str, str2);
            }
        });
    }

    public static ChannelReport[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, ChannelReport>() {
            public String getId() {
                return "fm.liveswitch.ChannelReport.fromJson";
            }

            public ChannelReport invoke(String str) {
                return ChannelReport.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (ChannelReport[]) deserializeObjectArray.toArray(new ChannelReport[0]);
    }

    public String getId() {
        return this._id;
    }

    public NullableLong getMessageBytesReceived() {
        return this._messageBytesReceived;
    }

    public NullableLong getMessageBytesSent() {
        return this._messageBytesSent;
    }

    public NullableLong getMessagesReceived() {
        return this._messagesReceived;
    }

    public NullableLong getMessagesSent() {
        return this._messagesSent;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "id", JsonSerializer.serializeString(getId()));
        }
        if (getMessagesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesSent", JsonSerializer.serializeLong(getMessagesSent()));
        }
        if (getMessageBytesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messageBytesSent", JsonSerializer.serializeLong(getMessageBytesSent()));
        }
        if (getMessagesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesReceived", JsonSerializer.serializeLong(getMessagesReceived()));
        }
        if (getMessageBytesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messageBytesReceived", JsonSerializer.serializeLong(getMessageBytesReceived()));
        }
    }

    public void setId(String str) {
        this._id = str;
    }

    public void setMessageBytesReceived(NullableLong nullableLong) {
        this._messageBytesReceived = nullableLong;
    }

    public void setMessageBytesSent(NullableLong nullableLong) {
        this._messageBytesSent = nullableLong;
    }

    public void setMessagesReceived(NullableLong nullableLong) {
        this._messagesReceived = nullableLong;
    }

    public void setMessagesSent(NullableLong nullableLong) {
        this._messagesSent = nullableLong;
    }

    public static String toJson(ChannelReport channelReport) {
        return JsonSerializer.serializeObject(channelReport, new IAction2<ChannelReport, HashMap<String, String>>() {
            public void invoke(ChannelReport channelReport, HashMap<String, String> hashMap) {
                channelReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(ChannelReport[] channelReportArr) {
        return JsonSerializer.serializeObjectArray(channelReportArr, new IFunctionDelegate1<ChannelReport, String>() {
            public String getId() {
                return "fm.liveswitch.ChannelReport.toJson";
            }

            public String invoke(ChannelReport channelReport) {
                return ChannelReport.toJson(channelReport);
            }
        });
    }
}
