package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CandidateInfo extends Info {
    private String _ipAddress;
    private NullableInteger _port = new NullableInteger();
    private NullableLong _priority = new NullableLong();
    private String _protocol;
    private String _relatedIPAddress;
    private NullableInteger _relatedPort = new NullableInteger();
    private String _relayProtocol;
    private String _type;

    public CandidateInfo() {
    }

    CandidateInfo(CandidateStats candidateStats, CandidateStats candidateStats2) {
        super.setId(candidateStats.getId());
        boolean z = candidateStats2 == null;
        String iPAddress = candidateStats.getIPAddress();
        setIPAddress(!z ? Info.processString(iPAddress, candidateStats2.getIPAddress()) : iPAddress);
        setPort(z ? new NullableInteger(candidateStats.getPort()) : Info.processInteger(candidateStats.getPort(), candidateStats2.getPort()));
        setPriority(z ? new NullableLong(candidateStats.getPriority()) : Info.processLong(candidateStats.getPriority(), candidateStats2.getPriority()));
        String str = null;
        setProtocol((z || !Global.equals(candidateStats.getProtocol(), candidateStats2.getProtocol())) ? CandidateUtility.protocolTypeToString(candidateStats.getProtocol()) : null);
        String relatedIPAddress = candidateStats.getRelatedIPAddress();
        setRelatedIPAddress(!z ? Info.processString(relatedIPAddress, candidateStats2.getRelatedIPAddress()) : relatedIPAddress);
        setRelatedPort(z ? new NullableInteger(candidateStats.getRelatedPort()) : Info.processInteger(candidateStats.getRelatedPort(), candidateStats2.getRelatedPort()));
        setRelayProtocol((z || !Global.equals(candidateStats.getRelayProtocol(), candidateStats2.getRelayProtocol())) ? CandidateUtility.protocolTypeToString(candidateStats.getRelayProtocol()) : null);
        setType((z || !Global.equals(candidateStats.getType(), candidateStats2.getType())) ? CandidateUtility.typeToString(candidateStats.getType()) : str);
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("ipAddress")) {
            setIPAddress(JsonSerializer.deserializeString(str2));
        } else if (str.equals("port")) {
            setPort(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals("relatedIPAddress")) {
            setRelatedIPAddress(JsonSerializer.deserializeString(str2));
        } else if (str.equals("relatedPort")) {
            setRelatedPort(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals("protocol")) {
            setProtocol(JsonSerializer.deserializeString(str2));
        } else if (str.equals("relayProtocol")) {
            setRelayProtocol(JsonSerializer.deserializeString(str2));
        } else if (str.equals("type")) {
            setType(JsonSerializer.deserializeString(str2));
        } else if (str.equals("priority")) {
            setPriority(JsonSerializer.deserializeLong(str2));
        }
    }

    public static CandidateInfo fromJson(String str) {
        return (CandidateInfo) JsonSerializer.deserializeObject(str, new IFunction0<CandidateInfo>() {
            public CandidateInfo invoke() {
                return new CandidateInfo();
            }
        }, new IAction3<CandidateInfo, String, String>() {
            public void invoke(CandidateInfo candidateInfo, String str, String str2) {
                candidateInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static CandidateInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CandidateInfo>() {
            public String getId() {
                return "fm.liveswitch.CandidateInfo.fromJson";
            }

            public CandidateInfo invoke(String str) {
                return CandidateInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CandidateInfo[]) deserializeObjectArray.toArray(new CandidateInfo[0]);
    }

    public String getIPAddress() {
        return this._ipAddress;
    }

    public NullableInteger getPort() {
        return this._port;
    }

    public NullableLong getPriority() {
        return this._priority;
    }

    public String getProtocol() {
        return this._protocol;
    }

    public String getRelatedIPAddress() {
        return this._relatedIPAddress;
    }

    public NullableInteger getRelatedPort() {
        return this._relatedPort;
    }

    public String getRelayProtocol() {
        return this._relayProtocol;
    }

    public String getType() {
        return this._type;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getIPAddress() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "ipAddress", JsonSerializer.serializeString(getIPAddress()));
        }
        if (getPort().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "port", JsonSerializer.serializeInteger(getPort()));
        }
        if (getRelatedIPAddress() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "relatedIPAddress", JsonSerializer.serializeString(getRelatedIPAddress()));
        }
        if (getRelatedPort().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "relatedPort", JsonSerializer.serializeInteger(getRelatedPort()));
        }
        if (getProtocol() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "protocol", JsonSerializer.serializeString(getProtocol()));
        }
        if (getRelayProtocol() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "relayProtocol", JsonSerializer.serializeString(getRelayProtocol()));
        }
        if (getType() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "type", JsonSerializer.serializeString(getType()));
        }
        if (getPriority().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "priority", JsonSerializer.serializeLong(getPriority()));
        }
    }

    public void setIPAddress(String str) {
        this._ipAddress = str;
    }

    public void setPort(NullableInteger nullableInteger) {
        this._port = nullableInteger;
    }

    public void setPriority(NullableLong nullableLong) {
        this._priority = nullableLong;
    }

    public void setProtocol(String str) {
        this._protocol = str;
    }

    public void setRelatedIPAddress(String str) {
        this._relatedIPAddress = str;
    }

    public void setRelatedPort(NullableInteger nullableInteger) {
        this._relatedPort = nullableInteger;
    }

    public void setRelayProtocol(String str) {
        this._relayProtocol = str;
    }

    public void setType(String str) {
        this._type = str;
    }

    public static String toJson(CandidateInfo candidateInfo) {
        return JsonSerializer.serializeObject(candidateInfo, new IAction2<CandidateInfo, HashMap<String, String>>() {
            public void invoke(CandidateInfo candidateInfo, HashMap<String, String> hashMap) {
                candidateInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CandidateInfo[] candidateInfoArr) {
        return JsonSerializer.serializeObjectArray(candidateInfoArr, new IFunctionDelegate1<CandidateInfo, String>() {
            public String getId() {
                return "fm.liveswitch.CandidateInfo.toJson";
            }

            public String invoke(CandidateInfo candidateInfo) {
                return CandidateInfo.toJson(candidateInfo);
            }
        });
    }
}
