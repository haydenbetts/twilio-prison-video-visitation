package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class DataChannelInfo extends Info {
    private String _label;
    private boolean _ordered;
    private DataChannelReport _report;
    private String _state;
    private String _subprotocol;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "label")) {
            setLabel(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "ordered")) {
            setOrdered(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (Global.equals(str, "subprotocol")) {
            setSubprotocol(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "state")) {
            setState(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "report")) {
            setReport(DataChannelReport.fromJson(str2));
        }
    }

    public static DataChannelInfo fromJson(String str) {
        return (DataChannelInfo) JsonSerializer.deserializeObject(str, new IFunction0<DataChannelInfo>() {
            public DataChannelInfo invoke() {
                return new DataChannelInfo();
            }
        }, new IAction3<DataChannelInfo, String, String>() {
            public void invoke(DataChannelInfo dataChannelInfo, String str, String str2) {
                dataChannelInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static DataChannelInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, DataChannelInfo>() {
            public String getId() {
                return "fm.liveswitch.DataChannelInfo.fromJson";
            }

            public DataChannelInfo invoke(String str) {
                return DataChannelInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (DataChannelInfo[]) deserializeObjectArray.toArray(new DataChannelInfo[0]);
    }

    public String getLabel() {
        return this._label;
    }

    public boolean getOrdered() {
        return this._ordered;
    }

    public DataChannelReport getReport() {
        return this._report;
    }

    public String getState() {
        return this._state;
    }

    public String getSubprotocol() {
        return this._subprotocol;
    }

    /* access modifiers changed from: package-private */
    public void populateReports(DataChannelStats dataChannelStats, DataChannelStats dataChannelStats2, String str) {
        setReport(new DataChannelReport(dataChannelStats, dataChannelStats2, str));
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getLabel() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "label", JsonSerializer.serializeString(getLabel()));
        }
        if (getOrdered()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ordered", JsonSerializer.serializeBoolean(new NullableBoolean(getOrdered())));
        }
        if (getSubprotocol() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "subprotocol", JsonSerializer.serializeString(getSubprotocol()));
        }
        if (getState() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "state", JsonSerializer.serializeString(getState()));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", DataChannelReport.toJson(getReport()));
        }
    }

    public void setLabel(String str) {
        this._label = str;
    }

    public void setOrdered(boolean z) {
        this._ordered = z;
    }

    public void setReport(DataChannelReport dataChannelReport) {
        this._report = dataChannelReport;
    }

    public void setState(String str) {
        this._state = str;
    }

    public void setSubprotocol(String str) {
        this._subprotocol = str;
    }

    public static String toJson(DataChannelInfo dataChannelInfo) {
        return JsonSerializer.serializeObject(dataChannelInfo, new IAction2<DataChannelInfo, HashMap<String, String>>() {
            public void invoke(DataChannelInfo dataChannelInfo, HashMap<String, String> hashMap) {
                dataChannelInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(DataChannelInfo[] dataChannelInfoArr) {
        return JsonSerializer.serializeObjectArray(dataChannelInfoArr, new IFunctionDelegate1<DataChannelInfo, String>() {
            public String getId() {
                return "fm.liveswitch.DataChannelInfo.toJson";
            }

            public String invoke(DataChannelInfo dataChannelInfo) {
                return DataChannelInfo.toJson(dataChannelInfo);
            }
        });
    }
}
