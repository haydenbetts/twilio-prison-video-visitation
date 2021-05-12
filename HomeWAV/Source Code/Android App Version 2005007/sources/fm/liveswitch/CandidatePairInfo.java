package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CandidatePairInfo extends Info {
    private String _localCandidateId;
    private NullableBoolean _nominated = new NullableBoolean();
    private NullableLong _priority = new NullableLong();
    private String _remoteCandidateId;
    private CandidatePairReport _report;
    private String _state;

    public CandidatePairInfo() {
    }

    CandidatePairInfo(CandidatePairStats candidatePairStats, CandidatePairStats candidatePairStats2) {
        super.setId(candidatePairStats.getId());
        boolean z = candidatePairStats2 == null;
        setLocalCandidateId(candidatePairStats.getLocalCandidateId());
        setRemoteCandidateId(candidatePairStats.getRemoteCandidateId());
        setState((z || !Global.equals(candidatePairStats.getState(), candidatePairStats2.getState())) ? CandidateUtility.candidatePairStateToString(candidatePairStats.getState()) : null);
        setPriority(z ? new NullableLong(candidatePairStats.getPriority()) : Info.processLong(candidatePairStats.getPriority(), candidatePairStats2.getPriority()));
        setNominated(z ? new NullableBoolean(candidatePairStats.getNominated()) : Info.processBoolean(candidatePairStats.getNominated(), candidatePairStats2.getNominated()));
        setReport(new CandidatePairReport(candidatePairStats, candidatePairStats2));
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "localCandidateId")) {
            setLocalCandidateId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "remoteCandidateId")) {
            setRemoteCandidateId(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "state")) {
            setState(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "priority")) {
            setPriority(JsonSerializer.deserializeLong(str2));
        } else if (Global.equals(str, "nominated")) {
            setNominated(JsonSerializer.deserializeBoolean(str2));
        } else if (Global.equals(str, "report")) {
            setReport(CandidatePairReport.fromJson(str2));
        }
    }

    public static CandidatePairInfo fromJson(String str) {
        return (CandidatePairInfo) JsonSerializer.deserializeObject(str, new IFunction0<CandidatePairInfo>() {
            public CandidatePairInfo invoke() {
                return new CandidatePairInfo();
            }
        }, new IAction3<CandidatePairInfo, String, String>() {
            public void invoke(CandidatePairInfo candidatePairInfo, String str, String str2) {
                candidatePairInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static CandidatePairInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CandidatePairInfo>() {
            public String getId() {
                return "fm.liveswitch.CandidatePairInfo.fromJson";
            }

            public CandidatePairInfo invoke(String str) {
                return CandidatePairInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CandidatePairInfo[]) deserializeObjectArray.toArray(new CandidatePairInfo[0]);
    }

    public String getLocalCandidateId() {
        return this._localCandidateId;
    }

    public NullableBoolean getNominated() {
        return this._nominated;
    }

    public NullableLong getPriority() {
        return this._priority;
    }

    public String getRemoteCandidateId() {
        return this._remoteCandidateId;
    }

    public CandidatePairReport getReport() {
        return this._report;
    }

    public String getState() {
        return this._state;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getLocalCandidateId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localCandidateId", JsonSerializer.serializeString(getLocalCandidateId()));
        }
        if (getRemoteCandidateId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteCandidateId", JsonSerializer.serializeString(getRemoteCandidateId()));
        }
        if (getState() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "state", JsonSerializer.serializeString(getState()));
        }
        if (getPriority().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "priority", JsonSerializer.serializeLong(getPriority()));
        }
        if (getNominated().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "nominated", JsonSerializer.serializeBoolean(getNominated()));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", CandidatePairReport.toJson(getReport()));
        }
    }

    public void setLocalCandidateId(String str) {
        this._localCandidateId = str;
    }

    public void setNominated(NullableBoolean nullableBoolean) {
        this._nominated = nullableBoolean;
    }

    public void setPriority(NullableLong nullableLong) {
        this._priority = nullableLong;
    }

    public void setRemoteCandidateId(String str) {
        this._remoteCandidateId = str;
    }

    public void setReport(CandidatePairReport candidatePairReport) {
        this._report = candidatePairReport;
    }

    public void setState(String str) {
        this._state = str;
    }

    public static String toJson(CandidatePairInfo candidatePairInfo) {
        return JsonSerializer.serializeObject(candidatePairInfo, new IAction2<CandidatePairInfo, HashMap<String, String>>() {
            public void invoke(CandidatePairInfo candidatePairInfo, HashMap<String, String> hashMap) {
                candidatePairInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CandidatePairInfo[] candidatePairInfoArr) {
        return JsonSerializer.serializeObjectArray(candidatePairInfoArr, new IFunctionDelegate1<CandidatePairInfo, String>() {
            public String getId() {
                return "fm.liveswitch.CandidatePairInfo.toJson";
            }

            public String invoke(CandidatePairInfo candidatePairInfo) {
                return CandidatePairInfo.toJson(candidatePairInfo);
            }
        });
    }
}
