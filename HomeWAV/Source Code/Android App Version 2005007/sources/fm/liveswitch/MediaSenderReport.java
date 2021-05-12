package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaSenderReport extends MediaComponentReport {
    private NullableLong _bytesSent = new NullableLong();
    private NullableLong _packetsSent = new NullableLong();
    private NullableInteger _roundTripTime = new NullableInteger();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "packetsSent")) {
            setPacketsSent(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "roundTripTime")) {
            setRoundTripTime(JsonSerializer.deserializeInteger(str2));
        }
    }

    public static MediaSenderReport fromJson(String str) {
        return (MediaSenderReport) JsonSerializer.deserializeObject(str, new IFunction0<MediaSenderReport>() {
            public MediaSenderReport invoke() {
                return new MediaSenderReport();
            }
        }, new IAction3<MediaSenderReport, String, String>() {
            public void invoke(MediaSenderReport mediaSenderReport, String str, String str2) {
                mediaSenderReport.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaSenderReport[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaSenderReport>() {
            public String getId() {
                return "fm.liveswitch.MediaSenderReport.fromJson";
            }

            public MediaSenderReport invoke(String str) {
                return MediaSenderReport.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaSenderReport[]) deserializeObjectArray.toArray(new MediaSenderReport[0]);
    }

    public NullableLong getBytesSent() {
        return this._bytesSent;
    }

    public NullableLong getPacketsSent() {
        return this._packetsSent;
    }

    public NullableInteger getRoundTripTime() {
        return this._roundTripTime;
    }

    public MediaSenderReport() {
    }

    MediaSenderReport(MediaSenderStats mediaSenderStats, MediaSenderStats mediaSenderStats2) {
        super(mediaSenderStats, mediaSenderStats2);
        int i = 0;
        boolean z = mediaSenderStats2 == null;
        long j = 0;
        setBytesSent(Report.processLong(mediaSenderStats.getBytesSent(), z ? 0 : mediaSenderStats2.getBytesSent()));
        setPacketsSent(Report.processLong(mediaSenderStats.getPacketsSent(), !z ? mediaSenderStats2.getPacketsSent() : j));
        setRoundTripTime(Report.processInteger(mediaSenderStats.getRoundTripTime(), !z ? mediaSenderStats2.getRoundTripTime() : i));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getBytesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(getBytesSent()));
        }
        if (getPacketsSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "packetsSent", JsonSerializer.serializeLong(getPacketsSent()));
        }
        if (getRoundTripTime().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "roundTripTime", JsonSerializer.serializeInteger(getRoundTripTime()));
        }
    }

    public void setBytesSent(NullableLong nullableLong) {
        this._bytesSent = nullableLong;
    }

    public void setPacketsSent(NullableLong nullableLong) {
        this._packetsSent = nullableLong;
    }

    public void setRoundTripTime(NullableInteger nullableInteger) {
        this._roundTripTime = nullableInteger;
    }

    public static String toJson(MediaSenderReport mediaSenderReport) {
        return JsonSerializer.serializeObject(mediaSenderReport, new IAction2<MediaSenderReport, HashMap<String, String>>() {
            public void invoke(MediaSenderReport mediaSenderReport, HashMap<String, String> hashMap) {
                mediaSenderReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaSenderReport[] mediaSenderReportArr) {
        return JsonSerializer.serializeObjectArray(mediaSenderReportArr, new IFunctionDelegate1<MediaSenderReport, String>() {
            public String getId() {
                return "fm.liveswitch.MediaSenderReport.toJson";
            }

            public String invoke(MediaSenderReport mediaSenderReport) {
                return MediaSenderReport.toJson(mediaSenderReport);
            }
        });
    }
}
