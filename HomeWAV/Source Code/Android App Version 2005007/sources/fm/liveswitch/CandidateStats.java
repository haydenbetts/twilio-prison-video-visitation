package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CandidateStats extends BaseStats implements IEquivalent<CandidateStats> {
    private String _ipAddress;
    private int _port;
    private long _priority;
    private ProtocolType _protocol;
    private String _relatedIPAddress;
    private int _relatedPort;
    private ProtocolType _relayProtocol;
    private CandidateType _type;

    public CandidateStats() {
        setPort(-1);
        setRelatedPort(-1);
        setProtocol(ProtocolType.Unknown);
        setRelayProtocol(ProtocolType.Unknown);
        setType(CandidateType.Unknown);
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("ipAddress")) {
            setIPAddress(JsonSerializer.deserializeString(str2));
        } else if (str.equals("port")) {
            setPort(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("relayProtocol")) {
            setRelayProtocol(CandidateUtility.protocolTypeFromString(JsonSerializer.deserializeString(str2)));
        } else if (str.equals("relatedIPAddress")) {
            setRelatedIPAddress(JsonSerializer.deserializeString(str2));
        } else if (str.equals("relatedPort")) {
            setRelatedPort(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("protocol")) {
            setProtocol(CandidateUtility.protocolTypeFromString(JsonSerializer.deserializeString(str2)));
        } else if (str.equals("type")) {
            setType(CandidateUtility.typeFromString(JsonSerializer.deserializeString(str2)));
        } else if (str.equals("priority")) {
            setPriority(JsonSerializer.deserializeLong(str2).getValue());
        }
    }

    public static CandidateStats fromJson(String str) {
        return (CandidateStats) JsonSerializer.deserializeObject(str, new IFunction0<CandidateStats>() {
            public CandidateStats invoke() {
                return new CandidateStats();
            }
        }, new IAction3<CandidateStats, String, String>() {
            public void invoke(CandidateStats candidateStats, String str, String str2) {
                candidateStats.deserializeProperties(str, str2);
            }
        });
    }

    public static CandidateStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CandidateStats>() {
            public String getId() {
                return "fm.liveswitch.CandidateStats.fromJson";
            }

            public CandidateStats invoke(String str) {
                return CandidateStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CandidateStats[]) deserializeObjectArray.toArray(new CandidateStats[0]);
    }

    public String getIPAddress() {
        return this._ipAddress;
    }

    public boolean getIsHost() {
        return Global.equals(getType(), CandidateType.Host);
    }

    public boolean getIsReflexive() {
        return Global.equals(getType(), CandidateType.ServerReflexive) || Global.equals(getType(), CandidateType.PeerReflexive);
    }

    public boolean getIsRelayed() {
        return Global.equals(getType(), CandidateType.Relayed);
    }

    public int getPort() {
        return this._port;
    }

    public long getPriority() {
        return this._priority;
    }

    public ProtocolType getProtocol() {
        return this._protocol;
    }

    public String getRelatedIPAddress() {
        return this._relatedIPAddress;
    }

    public int getRelatedPort() {
        return this._relatedPort;
    }

    public ProtocolType getRelayProtocol() {
        return this._relayProtocol;
    }

    public ProtocolType getTurnProtocol() {
        return getRelayProtocol();
    }

    public CandidateType getType() {
        return this._type;
    }

    public boolean isEquivalent(CandidateStats candidateStats) {
        return candidateStats != null && Global.equals(candidateStats.getIPAddress(), getIPAddress()) && candidateStats.getPort() == getPort() && Global.equals(candidateStats.getRelayProtocol(), getRelayProtocol()) && Global.equals(candidateStats.getRelatedIPAddress(), getRelatedIPAddress()) && candidateStats.getRelatedPort() == getRelatedPort() && Global.equals(candidateStats.getProtocol(), getProtocol()) && Global.equals(candidateStats.getType(), getType()) && candidateStats.getPriority() == getPriority();
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getIPAddress() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ipAddress", JsonSerializer.serializeString(getIPAddress()));
        }
        if (getPort() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "port", JsonSerializer.serializeInteger(new NullableInteger(getPort())));
        }
        if (getRelatedIPAddress() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "relatedIPAddress", JsonSerializer.serializeString(getRelatedIPAddress()));
        }
        if (getRelatedPort() != 0) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "relatedPort", JsonSerializer.serializeInteger(new NullableInteger(getRelatedPort())));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "protocol", JsonSerializer.serializeString(CandidateUtility.protocolTypeToString(getProtocol())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(CandidateUtility.typeToString(getType())));
        if (Global.equals(getType(), CandidateType.Relayed)) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "relayProtocol", JsonSerializer.serializeString(CandidateUtility.protocolTypeToString(getRelayProtocol())));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "priority", JsonSerializer.serializeLong(new NullableLong(getPriority())));
    }

    /* access modifiers changed from: package-private */
    public void setIPAddress(String str) {
        this._ipAddress = str;
    }

    /* access modifiers changed from: package-private */
    public void setPort(int i) {
        this._port = i;
    }

    /* access modifiers changed from: package-private */
    public void setPriority(long j) {
        this._priority = j;
    }

    /* access modifiers changed from: package-private */
    public void setProtocol(ProtocolType protocolType) {
        this._protocol = protocolType;
    }

    /* access modifiers changed from: package-private */
    public void setRelatedIPAddress(String str) {
        this._relatedIPAddress = str;
    }

    /* access modifiers changed from: package-private */
    public void setRelatedPort(int i) {
        this._relatedPort = i;
    }

    /* access modifiers changed from: package-private */
    public void setRelayProtocol(ProtocolType protocolType) {
        this._relayProtocol = protocolType;
    }

    /* access modifiers changed from: package-private */
    public void setType(CandidateType candidateType) {
        this._type = candidateType;
    }

    public static String toJson(CandidateStats candidateStats) {
        return JsonSerializer.serializeObject(candidateStats, new IAction2<CandidateStats, HashMap<String, String>>() {
            public void invoke(CandidateStats candidateStats, HashMap<String, String> hashMap) {
                candidateStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CandidateStats[] candidateStatsArr) {
        return JsonSerializer.serializeObjectArray(candidateStatsArr, new IFunctionDelegate1<CandidateStats, String>() {
            public String getId() {
                return "fm.liveswitch.CandidateStats.toJson";
            }

            public String invoke(CandidateStats candidateStats) {
                return CandidateStats.toJson(candidateStats);
            }
        });
    }
}
