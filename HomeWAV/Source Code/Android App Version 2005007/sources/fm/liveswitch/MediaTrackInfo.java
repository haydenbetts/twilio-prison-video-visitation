package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class MediaTrackInfo extends Info {
    private NullableBoolean _detached = new NullableBoolean();
    private NullableBoolean _muted = new NullableBoolean();
    private MediaTrackReport _report;
    private NullableBoolean _stopped = new NullableBoolean();

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "stopped")) {
            setMuted(new NullableBoolean(JsonSerializer.deserializeBoolean(str2).getValue()));
        } else if (Global.equals(str, "detached")) {
            setDetached(new NullableBoolean(JsonSerializer.deserializeBoolean(str2).getValue()));
        } else if (Global.equals(str, "muted")) {
            setMuted(new NullableBoolean(JsonSerializer.deserializeBoolean(str2).getValue()));
        } else if (Global.equals(str, "report")) {
            setReport(MediaTrackReport.fromJson(str2));
        }
    }

    public static MediaTrackInfo fromJson(String str) {
        return (MediaTrackInfo) JsonSerializer.deserializeObject(str, new IFunction0<MediaTrackInfo>() {
            public MediaTrackInfo invoke() {
                return new MediaTrackInfo();
            }
        }, new IAction3<MediaTrackInfo, String, String>() {
            public void invoke(MediaTrackInfo mediaTrackInfo, String str, String str2) {
                mediaTrackInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static MediaTrackInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, MediaTrackInfo>() {
            public String getId() {
                return "fm.liveswitch.MediaTrackInfo.fromJson";
            }

            public MediaTrackInfo invoke(String str) {
                return MediaTrackInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (MediaTrackInfo[]) deserializeObjectArray.toArray(new MediaTrackInfo[0]);
    }

    public NullableBoolean getDetached() {
        return this._detached;
    }

    public NullableBoolean getMuted() {
        return this._muted;
    }

    public MediaTrackReport getReport() {
        return this._report;
    }

    public NullableBoolean getStopped() {
        return this._stopped;
    }

    public MediaTrackInfo() {
    }

    public MediaTrackInfo(MediaTrackStats mediaTrackStats, MediaTrackStats mediaTrackStats2) {
        super.setId(mediaTrackStats.getId());
        setDetached(mediaTrackStats2 == null ? new NullableBoolean(mediaTrackStats.getDetached()) : Info.processBoolean(mediaTrackStats.getDetached(), mediaTrackStats2.getDetached()));
        setMuted(mediaTrackStats2 == null ? new NullableBoolean(mediaTrackStats.getMuted()) : Info.processBoolean(mediaTrackStats.getMuted(), mediaTrackStats2.getMuted()));
        setStopped(mediaTrackStats2 == null ? new NullableBoolean(mediaTrackStats.getStopped()) : Info.processBoolean(mediaTrackStats.getStopped(), mediaTrackStats2.getStopped()));
        setReport(new MediaTrackReport(mediaTrackStats, mediaTrackStats2));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getStopped().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "stopped", JsonSerializer.serializeBoolean(getStopped()));
        }
        if (getDetached().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "detached", JsonSerializer.serializeBoolean(getDetached()));
        }
        if (getMuted().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "muted", JsonSerializer.serializeBoolean(getMuted()));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", getReport().toJson());
        }
    }

    public void setDetached(NullableBoolean nullableBoolean) {
        this._detached = nullableBoolean;
    }

    public void setMuted(NullableBoolean nullableBoolean) {
        this._muted = nullableBoolean;
    }

    public void setReport(MediaTrackReport mediaTrackReport) {
        this._report = mediaTrackReport;
    }

    public void setStopped(NullableBoolean nullableBoolean) {
        this._stopped = nullableBoolean;
    }

    public static String toJson(MediaTrackInfo mediaTrackInfo) {
        return JsonSerializer.serializeObject(mediaTrackInfo, new IAction2<MediaTrackInfo, HashMap<String, String>>() {
            public void invoke(MediaTrackInfo mediaTrackInfo, HashMap<String, String> hashMap) {
                mediaTrackInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(MediaTrackInfo[] mediaTrackInfoArr) {
        return JsonSerializer.serializeObjectArray(mediaTrackInfoArr, new IFunctionDelegate1<MediaTrackInfo, String>() {
            public String getId() {
                return "fm.liveswitch.MediaTrackInfo.toJson";
            }

            public String invoke(MediaTrackInfo mediaTrackInfo) {
                return MediaTrackInfo.toJson(mediaTrackInfo);
            }
        });
    }
}
