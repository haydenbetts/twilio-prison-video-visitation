package fm.liveswitch;

import java.util.HashMap;

public class DataChannelReport extends Report {
    private NullableLong _bytesReceived = new NullableLong();
    private NullableLong _bytesSent = new NullableLong();
    private NullableLong _messagesReceived = new NullableLong();
    private NullableLong _messagesSent = new NullableLong();

    public DataChannelReport() {
    }

    DataChannelReport(DataChannelStats dataChannelStats, DataChannelStats dataChannelStats2, String str) {
        boolean z = dataChannelStats2 == null;
        long j = 0;
        setMessagesSent(Report.processLong(dataChannelStats.getMessagesSent(), z ? 0 : dataChannelStats2.getMessagesSent()));
        setBytesSent(Report.processLong(dataChannelStats.getBytesSent(), z ? 0 : dataChannelStats2.getBytesSent()));
        setMessagesReceived(Report.processLong(dataChannelStats.getMessagesReceived(), z ? 0 : dataChannelStats2.getMessagesReceived()));
        setBytesReceived(Report.processLong(dataChannelStats.getBytesReceived(), !z ? dataChannelStats2.getBytesReceived() : j));
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "messagesSent")) {
            setMessagesSent(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "messagesReceived")) {
            setMessagesReceived(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2));
        }
    }

    public static DataChannelReport fromJson(String str) {
        return (DataChannelReport) JsonSerializer.deserializeObject(str, new IFunction0<DataChannelReport>() {
            public DataChannelReport invoke() {
                return new DataChannelReport();
            }
        }, new IAction3<DataChannelReport, String, String>() {
            public void invoke(DataChannelReport dataChannelReport, String str, String str2) {
                dataChannelReport.deserializeProperties(str, str2);
            }
        });
    }

    public NullableLong getBytesReceived() {
        return this._bytesReceived;
    }

    public NullableLong getBytesSent() {
        return this._bytesSent;
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
        if (getMessagesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesSent", JsonSerializer.serializeLong(getMessagesSent()));
        }
        if (getBytesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(getBytesSent()));
        }
        if (getMessagesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "messagesReceived", JsonSerializer.serializeLong(getMessagesReceived()));
        }
        if (getBytesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(getBytesReceived()));
        }
    }

    public void setBytesReceived(NullableLong nullableLong) {
        this._bytesReceived = nullableLong;
    }

    public void setBytesSent(NullableLong nullableLong) {
        this._bytesSent = nullableLong;
    }

    public void setMessagesReceived(NullableLong nullableLong) {
        this._messagesReceived = nullableLong;
    }

    public void setMessagesSent(NullableLong nullableLong) {
        this._messagesSent = nullableLong;
    }

    public static String toJson(DataChannelReport dataChannelReport) {
        return JsonSerializer.serializeObject(dataChannelReport, new IAction2<DataChannelReport, HashMap<String, String>>() {
            public void invoke(DataChannelReport dataChannelReport, HashMap<String, String> hashMap) {
                dataChannelReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
