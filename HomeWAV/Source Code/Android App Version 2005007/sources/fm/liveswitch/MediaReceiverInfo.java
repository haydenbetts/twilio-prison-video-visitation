package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaReceiverInfo extends MediaComponentInfo {
    private MediaReceiverReport _report;
    private MediaSinkInfo _sink;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "sink")) {
            setSink(MediaSinkInfo.fromJson(str2));
        } else if (Global.equals(str, "report")) {
            setReport(MediaReceiverReport.fromJson(str2));
        }
    }

    public static MediaReceiverInfo fromJson(String str) {
        return (MediaReceiverInfo) JsonSerializer.deserializeObject(str, new IFunction0<MediaReceiverInfo>() {
            public MediaReceiverInfo invoke() {
                return new MediaReceiverInfo();
            }
        }, new IAction3<MediaReceiverInfo, String, String>() {
            public void invoke(MediaReceiverInfo mediaReceiverInfo, String str, String str2) {
                mediaReceiverInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaReceiverInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaReceiverInfo>() {
            public String getId() {
                return "fm.liveswitch.MediaReceiverInfo.fromJson";
            }

            public MediaReceiverInfo invoke(String str) {
                return MediaReceiverInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaReceiverInfo[]) deserializeObjectArray.toArray(new MediaReceiverInfo[0]);
    }

    public MediaReceiverReport getReport() {
        return this._report;
    }

    public MediaSinkInfo getSink() {
        return this._sink;
    }

    public MediaReceiverInfo() {
    }

    MediaReceiverInfo(MediaReceiverStats mediaReceiverStats, MediaReceiverStats mediaReceiverStats2) {
        super(mediaReceiverStats, mediaReceiverStats2);
        MediaSinkStats sink = mediaReceiverStats.getSink();
        if (sink != null) {
            setSink(new MediaSinkInfo(sink, mediaReceiverStats2 == null ? null : mediaReceiverStats2.getSink()));
        }
        setReport(new MediaReceiverReport(mediaReceiverStats, mediaReceiverStats2));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getSink() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "sink", MediaSinkInfo.toJson(getSink()));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", MediaReceiverReport.toJson(getReport()));
        }
    }

    public void setReport(MediaReceiverReport mediaReceiverReport) {
        this._report = mediaReceiverReport;
    }

    public void setSink(MediaSinkInfo mediaSinkInfo) {
        this._sink = mediaSinkInfo;
    }

    public static String toJson(MediaReceiverInfo mediaReceiverInfo) {
        return JsonSerializer.serializeObject(mediaReceiverInfo, new IAction2<MediaReceiverInfo, HashMap<String, String>>() {
            public void invoke(MediaReceiverInfo mediaReceiverInfo, HashMap<String, String> hashMap) {
                mediaReceiverInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaReceiverInfo[] mediaReceiverInfoArr) {
        return JsonSerializer.serializeObjectArray(mediaReceiverInfoArr, new IFunctionDelegate1<MediaReceiverInfo, String>() {
            public String getId() {
                return "fm.liveswitch.MediaReceiverInfo.toJson";
            }

            public String invoke(MediaReceiverInfo mediaReceiverInfo) {
                return MediaReceiverInfo.toJson(mediaReceiverInfo);
            }
        });
    }
}
