package fm.liveswitch;

import java.util.HashMap;

public class DataStreamReport extends Report {
    private NullableLong _bytesReceived = new NullableLong();
    private NullableLong _bytesSent = new NullableLong();
    private NullableLong _messagesReceived = new NullableLong();
    private NullableLong _messagesSent = new NullableLong();

    public DataStreamReport() {
    }

    DataStreamReport(DataStreamStats dataStreamStats, DataStreamStats dataStreamStats2, String str) {
        boolean z = dataStreamStats2 == null;
        long j = 0;
        setMessagesSent(Report.processLong(dataStreamStats.getMessagesSent(), z ? 0 : dataStreamStats2.getMessagesSent()));
        setBytesSent(Report.processLong(dataStreamStats.getBytesSent(), z ? 0 : dataStreamStats2.getBytesSent()));
        setMessagesReceived(Report.processLong(dataStreamStats.getMessagesReceived(), z ? 0 : dataStreamStats2.getMessagesReceived()));
        setBytesReceived(Report.processLong(dataStreamStats.getBytesReceived(), !z ? dataStreamStats2.getBytesReceived() : j));
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

    public static DataStreamReport fromJson(String str) {
        return (DataStreamReport) JsonSerializer.deserializeObject(str, new IFunction0<DataStreamReport>() {
            public DataStreamReport invoke() {
                return new DataStreamReport();
            }
        }, new IAction3<DataStreamReport, String, String>() {
            public void invoke(DataStreamReport dataStreamReport, String str, String str2) {
                dataStreamReport.deserializeProperties(str, str2);
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

    public static String toJson(DataStreamReport dataStreamReport) {
        return JsonSerializer.serializeObject(dataStreamReport, new IAction2<DataStreamReport, HashMap<String, String>>() {
            public void invoke(DataStreamReport dataStreamReport, HashMap<String, String> hashMap) {
                dataStreamReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
