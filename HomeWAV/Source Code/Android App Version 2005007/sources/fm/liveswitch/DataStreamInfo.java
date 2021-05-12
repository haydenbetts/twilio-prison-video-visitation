package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class DataStreamInfo extends StreamInfo {
    private DataChannelInfo[] _channels;
    private DataStreamReport _report;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "channels")) {
            setChannels(DataChannelInfo.fromJsonArray(str2));
        } else if (Global.equals(str, "report")) {
            setReport(DataStreamReport.fromJson(str2));
        }
    }

    public static DataStreamInfo fromJson(String str) {
        return (DataStreamInfo) JsonSerializer.deserializeObject(str, new IFunction0<DataStreamInfo>() {
            public DataStreamInfo invoke() {
                return new DataStreamInfo();
            }
        }, new IAction3<DataStreamInfo, String, String>() {
            public void invoke(DataStreamInfo dataStreamInfo, String str, String str2) {
                dataStreamInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static DataStreamInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, DataStreamInfo>() {
            public String getId() {
                return "fm.liveswitch.DataStreamInfo.fromJson";
            }

            public DataStreamInfo invoke(String str) {
                return DataStreamInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (DataStreamInfo[]) deserializeObjectArray.toArray(new DataStreamInfo[0]);
    }

    public DataChannelInfo[] getChannels() {
        return this._channels;
    }

    public DataStreamReport getReport() {
        return this._report;
    }

    /* access modifiers changed from: package-private */
    public void populateInfos(DataStreamStats dataStreamStats, DataStreamStats dataStreamStats2, String str) {
        DataChannelStats[] channels;
        DataChannelStats[] dataChannelStatsArr;
        int i = 0;
        boolean z = dataStreamStats2 == null;
        setReport(new DataStreamReport(dataStreamStats, dataStreamStats2, str));
        TransportStats transport = dataStreamStats.getTransport();
        if (transport != null) {
            super.setTransportId(transport.getId());
        }
        if (getChannels() != null && (channels = dataStreamStats.getChannels()) != null) {
            if (z) {
                dataChannelStatsArr = null;
            } else {
                dataChannelStatsArr = dataStreamStats2.getChannels();
            }
            while (i < MathAssistant.min(ArrayExtensions.getLength((Object[]) channels), ArrayExtensions.getLength((Object[]) getChannels()))) {
                DataChannelStats dataChannelStats = (dataChannelStatsArr == null || ArrayExtensions.getLength((Object[]) dataChannelStatsArr) <= i) ? null : dataChannelStatsArr[i];
                if (dataChannelStats == null || !Global.equals(channels[i].getState(), dataChannelStats.getState())) {
                    getChannels()[i].setState(StringExtensions.toLower(channels[i].getState().toString()));
                } else {
                    getChannels()[i].setState((String) null);
                }
                DataChannelInfo dataChannelInfo = getChannels()[i];
                DataChannelStats dataChannelStats2 = channels[i];
                if (z) {
                    dataChannelStats = null;
                }
                dataChannelInfo.populateReports(dataChannelStats2, dataChannelStats, str);
                i++;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getChannels() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "channels", DataChannelInfo.toJsonArray(getChannels()));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", getReport().toJson());
        }
    }

    public void setChannels(DataChannelInfo[] dataChannelInfoArr) {
        this._channels = dataChannelInfoArr;
    }

    public void setReport(DataStreamReport dataStreamReport) {
        this._report = dataStreamReport;
    }

    public static String toJson(DataStreamInfo dataStreamInfo) {
        return JsonSerializer.serializeObject(dataStreamInfo, new IAction2<DataStreamInfo, HashMap<String, String>>() {
            public void invoke(DataStreamInfo dataStreamInfo, HashMap<String, String> hashMap) {
                dataStreamInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(DataStreamInfo[] dataStreamInfoArr) {
        return JsonSerializer.serializeObjectArray(dataStreamInfoArr, new IFunctionDelegate1<DataStreamInfo, String>() {
            public String getId() {
                return "fm.liveswitch.DataStreamInfo.toJson";
            }

            public String invoke(DataStreamInfo dataStreamInfo) {
                return DataStreamInfo.toJson(dataStreamInfo);
            }
        });
    }
}
