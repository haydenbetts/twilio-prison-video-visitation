package fm.liveswitch;

import java.util.HashMap;

public class ChannelInfo extends Info {
    private String _applicationId;
    private boolean _recording;
    private ChannelReport _report;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "applicationId")) {
            setApplicationId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "recording")) {
            setRecording(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (Global.equals(str, "report")) {
            setReport(ChannelReport.fromJson(str2));
        }
    }

    public static ChannelInfo fromJson(String str) {
        return (ChannelInfo) JsonSerializer.deserializeObject(str, new IFunction0<ChannelInfo>() {
            public ChannelInfo invoke() {
                return new ChannelInfo();
            }
        }, new IAction3<ChannelInfo, String, String>() {
            public void invoke(ChannelInfo channelInfo, String str, String str2) {
                channelInfo.deserializeProperties(str, str2);
            }
        });
    }

    public String getApplicationId() {
        return this._applicationId;
    }

    public boolean getRecording() {
        return this._recording;
    }

    public ChannelReport getReport() {
        return this._report;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getApplicationId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "applicationId", JsonSerializer.serializeString(getApplicationId()));
        }
        if (getRecording()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "recording", JsonSerializer.serializeBoolean(new NullableBoolean(true)));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", ChannelReport.toJson(getReport()));
        }
    }

    public void setApplicationId(String str) {
        this._applicationId = str;
    }

    public void setRecording(boolean z) {
        this._recording = z;
    }

    public void setReport(ChannelReport channelReport) {
        this._report = channelReport;
    }

    public static String toJson(ChannelInfo channelInfo) {
        return JsonSerializer.serializeObject(channelInfo, new IAction2<ChannelInfo, HashMap<String, String>>() {
            public void invoke(ChannelInfo channelInfo, HashMap<String, String> hashMap) {
                channelInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
