package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionStats extends BaseStats {
    private DataStreamStats _dataStream;
    private String _externalId;
    private MediaStreamStats[] _mediaStreams;
    private ConnectionState _state;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "externalId")) {
            setExternalId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "state")) {
            setState(stateFromString(JsonSerializer.deserializeString(str2)));
        } else if (Global.equals(str, "mediaStreams")) {
            setMediaStreams(MediaStreamStats.fromJsonArray(str2));
        } else if (Global.equals(str, "dataStream")) {
            setDataStream(DataStreamStats.fromJson(str2));
        }
    }

    public static ConnectionStats fromJson(String str) {
        return (ConnectionStats) JsonSerializer.deserializeObject(str, new IFunction0<ConnectionStats>() {
            public ConnectionStats invoke() {
                return new ConnectionStats();
            }
        }, new IAction3<ConnectionStats, String, String>() {
            public void invoke(ConnectionStats connectionStats, String str, String str2) {
                connectionStats.deserializeProperties(str, str2);
            }
        });
    }

    public MediaStreamStats getAudioStream() {
        return (MediaStreamStats) Utility.firstOrDefault((T[]) getAudioStreams());
    }

    public MediaStreamStats[] getAudioStreams() {
        ArrayList arrayList = new ArrayList();
        MediaStreamStats[] mediaStreams = getMediaStreams();
        if (mediaStreams != null) {
            for (MediaStreamStats mediaStreamStats : mediaStreams) {
                if (Global.equals(mediaStreamStats.getType(), StreamType.Audio)) {
                    arrayList.add(mediaStreamStats);
                }
            }
        }
        return (MediaStreamStats[]) arrayList.toArray(new MediaStreamStats[0]);
    }

    public DataStreamStats getDataStream() {
        return this._dataStream;
    }

    public String getExternalId() {
        return this._externalId;
    }

    public boolean getIsHost() {
        StreamStats[] streams = getStreams();
        if (streams != null) {
            for (StreamStats isHost : streams) {
                if (isHost.getIsHost()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getIsReflexive() {
        StreamStats[] streams = getStreams();
        if (streams != null) {
            for (StreamStats isReflexive : streams) {
                if (isReflexive.getIsReflexive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean getIsRelayed() {
        StreamStats[] streams = getStreams();
        if (streams != null) {
            for (StreamStats isRelayed : streams) {
                if (isRelayed.getIsRelayed()) {
                    return true;
                }
            }
        }
        return false;
    }

    public MediaStreamStats getMediaStream() {
        return (MediaStreamStats) Utility.firstOrDefault((T[]) getMediaStreams());
    }

    public MediaStreamStats getMediaStream(String str) {
        for (MediaStreamStats mediaStreamStats : getMediaStreams()) {
            if (Global.equals(mediaStreamStats.getId(), str)) {
                return mediaStreamStats;
            }
        }
        return null;
    }

    public MediaStreamStats[] getMediaStreams() {
        return this._mediaStreams;
    }

    public ConnectionState getState() {
        return this._state;
    }

    public StreamStats[] getStreams() {
        ArrayList arrayList = new ArrayList();
        MediaStreamStats[] mediaStreams = getMediaStreams();
        if (mediaStreams != null) {
            for (MediaStreamStats add : mediaStreams) {
                arrayList.add(add);
            }
        }
        DataStreamStats dataStream = getDataStream();
        if (dataStream != null) {
            arrayList.add(dataStream);
        }
        return (StreamStats[]) arrayList.toArray(new StreamStats[0]);
    }

    public MediaStreamStats getVideoStream() {
        return (MediaStreamStats) Utility.firstOrDefault((T[]) getVideoStreams());
    }

    public MediaStreamStats[] getVideoStreams() {
        ArrayList arrayList = new ArrayList();
        MediaStreamStats[] mediaStreams = getMediaStreams();
        if (mediaStreams != null) {
            for (MediaStreamStats mediaStreamStats : mediaStreams) {
                if (Global.equals(mediaStreamStats.getType(), StreamType.Video)) {
                    arrayList.add(mediaStreamStats);
                }
            }
        }
        return (MediaStreamStats[]) arrayList.toArray(new MediaStreamStats[0]);
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getExternalId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "externalId", JsonSerializer.serializeString(getExternalId()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "state", JsonSerializer.serializeString(stateToString(getState())));
        if (getMediaStreams() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "mediaStreams", MediaStreamStats.toJsonArray(getMediaStreams()));
        }
        if (getDataStream() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "dataStream", DataStreamStats.toJson(getDataStream()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setDataStream(DataStreamStats dataStreamStats) {
        this._dataStream = dataStreamStats;
    }

    public void setExternalId(String str) {
        this._externalId = str;
    }

    /* access modifiers changed from: package-private */
    public void setMediaStreams(MediaStreamStats[] mediaStreamStatsArr) {
        this._mediaStreams = mediaStreamStatsArr;
    }

    public void setState(ConnectionState connectionState) {
        this._state = connectionState;
    }

    private ConnectionState stateFromString(String str) {
        if (str.equals("new")) {
            return ConnectionState.New;
        }
        if (str.equals("initializing")) {
            return ConnectionState.Initializing;
        }
        if (str.equals("connecting")) {
            return ConnectionState.Connecting;
        }
        if (str.equals("connected")) {
            return ConnectionState.Connected;
        }
        if (str.equals("failing")) {
            return ConnectionState.Failing;
        }
        if (str.equals("failed")) {
            return ConnectionState.Failed;
        }
        if (str.equals("closing")) {
            return ConnectionState.Closing;
        }
        if (str.equals("closed")) {
            return ConnectionState.Closed;
        }
        return ConnectionState.New;
    }

    private String stateToString(ConnectionState connectionState) {
        if (connectionState == ConnectionState.New) {
            return "new";
        }
        if (connectionState == ConnectionState.Initializing) {
            return "initializing";
        }
        if (connectionState == ConnectionState.Connecting) {
            return "connecting";
        }
        if (connectionState == ConnectionState.Connected) {
            return "connected";
        }
        if (connectionState == ConnectionState.Failing) {
            return "failing";
        }
        if (connectionState == ConnectionState.Failed) {
            return "failed";
        }
        if (connectionState == ConnectionState.Closing) {
            return "closing";
        }
        if (connectionState == ConnectionState.Closed) {
            return "closed";
        }
        return null;
    }

    public static String toJson(ConnectionStats connectionStats) {
        return JsonSerializer.serializeObject(connectionStats, new IAction2<ConnectionStats, HashMap<String, String>>() {
            public void invoke(ConnectionStats connectionStats, HashMap<String, String> hashMap) {
                connectionStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
