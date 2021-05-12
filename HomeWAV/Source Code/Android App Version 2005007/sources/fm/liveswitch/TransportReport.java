package fm.liveswitch;

import java.util.HashMap;

public class TransportReport extends Report {
    private NullableLong _bytesReceived = new NullableLong();
    private NullableLong _bytesSent = new NullableLong();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2));
        }
    }

    public static TransportReport fromJson(String str) {
        return (TransportReport) JsonSerializer.deserializeObject(str, new IFunction0<TransportReport>() {
            public TransportReport invoke() {
                return new TransportReport();
            }
        }, new IAction3<TransportReport, String, String>() {
            public void invoke(TransportReport transportReport, String str, String str2) {
                transportReport.deserializeProperties(str, str2);
            }
        });
    }

    public NullableLong getBytesReceived() {
        return this._bytesReceived;
    }

    public NullableLong getBytesSent() {
        return this._bytesSent;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getBytesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(getBytesReceived()));
        }
        if (getBytesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(getBytesSent()));
        }
    }

    public void setBytesReceived(NullableLong nullableLong) {
        this._bytesReceived = nullableLong;
    }

    public void setBytesSent(NullableLong nullableLong) {
        this._bytesSent = nullableLong;
    }

    public static String toJson(TransportReport transportReport) {
        return JsonSerializer.serializeObject(transportReport, new IAction2<TransportReport, HashMap<String, String>>() {
            public void invoke(TransportReport transportReport, HashMap<String, String> hashMap) {
                transportReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public TransportReport() {
    }

    TransportReport(TransportStats transportStats, TransportStats transportStats2) {
        boolean z = transportStats2 == null;
        long j = 0;
        setBytesReceived(Report.processLong(transportStats.getBytesReceived(), z ? 0 : transportStats2.getBytesReceived()));
        setBytesSent(Report.processLong(transportStats.getBytesSent(), !z ? transportStats2.getBytesSent() : j));
    }
}
