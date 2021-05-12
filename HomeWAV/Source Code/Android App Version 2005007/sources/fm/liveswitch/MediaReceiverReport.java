package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaReceiverReport extends MediaComponentReport {
    private NullableLong _bytesReceived = new NullableLong();
    private NullableLong _jitter = new NullableLong();
    private NullableLong _packetsDiscarded = new NullableLong();
    private NullableLong _packetsDuplicated = new NullableLong();
    private NullableLong _packetsLost = new NullableLong();
    private NullableLong _packetsReceived = new NullableLong();
    private NullableLong _packetsRepaired = new NullableLong();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("packetsReceived")) {
            setPacketsReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("packetsDiscarded")) {
            setPacketsDiscarded(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("packetsDuplicated")) {
            setPacketsDuplicated(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("packetsRepaired")) {
            setPacketsRepaired(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("packetsLost")) {
            setPacketsLost(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("jitter")) {
            setJitter(JsonSerializer.deserializeLong(str2));
        }
    }

    public static MediaReceiverReport fromJson(String str) {
        return (MediaReceiverReport) JsonSerializer.deserializeObject(str, new IFunction0<MediaReceiverReport>() {
            public MediaReceiverReport invoke() {
                return new MediaReceiverReport();
            }
        }, new IAction3<MediaReceiverReport, String, String>() {
            public void invoke(MediaReceiverReport mediaReceiverReport, String str, String str2) {
                mediaReceiverReport.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaReceiverReport[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaReceiverReport>() {
            public String getId() {
                return "fm.liveswitch.MediaReceiverReport.fromJson";
            }

            public MediaReceiverReport invoke(String str) {
                return MediaReceiverReport.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaReceiverReport[]) deserializeObjectArray.toArray(new MediaReceiverReport[0]);
    }

    public NullableLong getBytesReceived() {
        return this._bytesReceived;
    }

    public NullableLong getJitter() {
        return this._jitter;
    }

    public NullableLong getPacketsDiscarded() {
        return this._packetsDiscarded;
    }

    public NullableLong getPacketsDuplicated() {
        return this._packetsDuplicated;
    }

    public NullableLong getPacketsLost() {
        return this._packetsLost;
    }

    public NullableLong getPacketsReceived() {
        return this._packetsReceived;
    }

    public NullableLong getPacketsRepaired() {
        return this._packetsRepaired;
    }

    public MediaReceiverReport() {
    }

    MediaReceiverReport(MediaReceiverStats mediaReceiverStats, MediaReceiverStats mediaReceiverStats2) {
        super(mediaReceiverStats, mediaReceiverStats2);
        boolean z = mediaReceiverStats2 == null;
        long j = 0;
        setBytesReceived(Report.processLong(mediaReceiverStats.getBytesReceived(), z ? 0 : mediaReceiverStats2.getBytesReceived()));
        setPacketsReceived(Report.processLong(mediaReceiverStats.getPacketsReceived(), z ? 0 : mediaReceiverStats2.getPacketsReceived()));
        setPacketsDiscarded(Report.processLong(mediaReceiverStats.getPacketsDiscarded(), z ? 0 : mediaReceiverStats2.getPacketsDiscarded()));
        setPacketsDuplicated(Report.processLong(mediaReceiverStats.getPacketsDuplicated(), z ? 0 : mediaReceiverStats2.getPacketsDuplicated()));
        setPacketsRepaired(Report.processLong(mediaReceiverStats.getPacketsRepaired(), z ? 0 : mediaReceiverStats2.getPacketsRepaired()));
        setPacketsLost(Report.processLong(mediaReceiverStats.getPacketsLost(), z ? 0 : mediaReceiverStats2.getPacketsLost()));
        setJitter(Report.processLong((long) mediaReceiverStats.getJitter(), !z ? (long) mediaReceiverStats2.getJitter() : j));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getBytesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(getBytesReceived()));
        }
        if (getPacketsReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsReceived", JsonSerializer.serializeLong(getPacketsReceived()));
        }
        if (getPacketsDiscarded().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsDiscarded", JsonSerializer.serializeLong(getPacketsDiscarded()));
        }
        if (getPacketsDuplicated().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsDuplicated", JsonSerializer.serializeLong(getPacketsDuplicated()));
        }
        if (getPacketsRepaired().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsRepaired", JsonSerializer.serializeLong(getPacketsRepaired()));
        }
        if (getPacketsLost().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsLost", JsonSerializer.serializeLong(getPacketsLost()));
        }
        if (getJitter().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "jitter", JsonSerializer.serializeLong(getJitter()));
        }
    }

    public void setBytesReceived(NullableLong nullableLong) {
        this._bytesReceived = nullableLong;
    }

    public void setJitter(NullableLong nullableLong) {
        this._jitter = nullableLong;
    }

    public void setPacketsDiscarded(NullableLong nullableLong) {
        this._packetsDiscarded = nullableLong;
    }

    public void setPacketsDuplicated(NullableLong nullableLong) {
        this._packetsDuplicated = nullableLong;
    }

    public void setPacketsLost(NullableLong nullableLong) {
        this._packetsLost = nullableLong;
    }

    public void setPacketsReceived(NullableLong nullableLong) {
        this._packetsReceived = nullableLong;
    }

    public void setPacketsRepaired(NullableLong nullableLong) {
        this._packetsRepaired = nullableLong;
    }

    public static String toJson(MediaReceiverReport mediaReceiverReport) {
        return JsonSerializer.serializeObject(mediaReceiverReport, new IAction2<MediaReceiverReport, HashMap<String, String>>() {
            public void invoke(MediaReceiverReport mediaReceiverReport, HashMap<String, String> hashMap) {
                mediaReceiverReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaReceiverReport[] mediaReceiverReportArr) {
        return JsonSerializer.serializeObjectArray(mediaReceiverReportArr, new IFunctionDelegate1<MediaReceiverReport, String>() {
            public String getId() {
                return "fm.liveswitch.MediaReceiverReport.toJson";
            }

            public String invoke(MediaReceiverReport mediaReceiverReport) {
                return MediaReceiverReport.toJson(mediaReceiverReport);
            }
        });
    }
}
