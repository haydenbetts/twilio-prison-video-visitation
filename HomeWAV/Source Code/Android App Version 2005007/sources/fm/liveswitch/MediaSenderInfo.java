package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaSenderInfo extends MediaComponentInfo {
    private MediaSenderReport _report;
    private MediaSourceInfo _source;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "source")) {
            setSource(MediaSourceInfo.fromJson(str2));
        } else if (Global.equals(str, "report")) {
            setReport(MediaSenderReport.fromJson(str2));
        }
    }

    public static MediaSenderInfo fromJson(String str) {
        return (MediaSenderInfo) JsonSerializer.deserializeObject(str, new IFunction0<MediaSenderInfo>() {
            public MediaSenderInfo invoke() {
                return new MediaSenderInfo();
            }
        }, new IAction3<MediaSenderInfo, String, String>() {
            public void invoke(MediaSenderInfo mediaSenderInfo, String str, String str2) {
                mediaSenderInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaSenderInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaSenderInfo>() {
            public String getId() {
                return "fm.liveswitch.MediaSenderInfo.fromJson";
            }

            public MediaSenderInfo invoke(String str) {
                return MediaSenderInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaSenderInfo[]) deserializeObjectArray.toArray(new MediaSenderInfo[0]);
    }

    public MediaSenderReport getReport() {
        return this._report;
    }

    public MediaSourceInfo getSource() {
        return this._source;
    }

    public MediaSenderInfo() {
    }

    MediaSenderInfo(MediaSenderStats mediaSenderStats, MediaSenderStats mediaSenderStats2) {
        super(mediaSenderStats, mediaSenderStats2);
        MediaSourceStats source = mediaSenderStats.getSource();
        if (source != null) {
            setSource(new MediaSourceInfo(source, mediaSenderStats2 == null ? null : mediaSenderStats2.getSource()));
        }
        setReport(new MediaSenderReport(mediaSenderStats, mediaSenderStats2));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getSource() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "source", MediaSourceInfo.toJson(getSource()));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", MediaSenderReport.toJson(getReport()));
        }
    }

    public void setReport(MediaSenderReport mediaSenderReport) {
        this._report = mediaSenderReport;
    }

    public void setSource(MediaSourceInfo mediaSourceInfo) {
        this._source = mediaSourceInfo;
    }

    public static String toJson(MediaSenderInfo mediaSenderInfo) {
        return JsonSerializer.serializeObject(mediaSenderInfo, new IAction2<MediaSenderInfo, HashMap<String, String>>() {
            public void invoke(MediaSenderInfo mediaSenderInfo, HashMap<String, String> hashMap) {
                mediaSenderInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaSenderInfo[] mediaSenderInfoArr) {
        return JsonSerializer.serializeObjectArray(mediaSenderInfoArr, new IFunctionDelegate1<MediaSenderInfo, String>() {
            public String getId() {
                return "fm.liveswitch.MediaSenderInfo.toJson";
            }

            public String invoke(MediaSenderInfo mediaSenderInfo) {
                return MediaSenderInfo.toJson(mediaSenderInfo);
            }
        });
    }
}
