package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CandidatePairStats extends BaseStats implements IEquivalent<CandidatePairStats> {
    private long _bytesReceived;
    private long _bytesSent;
    private long _consentRequestsReceived;
    private long _consentRequestsSent;
    private long _consentResponsesReceived;
    private long _consentResponsesSent;
    private int _currentRoundTripTime;
    private String _localCandidateId;
    private boolean _nominated;
    private long _priority;
    private String _remoteCandidateId;
    private long _requestsReceived;
    private long _requestsSent;
    private long _responsesReceived;
    private long _responsesSent;
    private CandidatePairState _state;
    private int _totalRoundTripTime;
    private String _transportId;

    public CandidatePairStats() {
        setState(CandidatePairState.New);
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("transportId")) {
            setTransportId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("localCandidateId")) {
            setLocalCandidateId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("remoteCandidateId")) {
            setRemoteCandidateId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("state")) {
            setState(CandidateUtility.candidatePairStateFromString(JsonSerializer.deserializeString(str2)));
        } else if (str.equals("priority")) {
            setPriority(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("nominated")) {
            setNominated(JsonSerializer.deserializeBoolean(str2).getValue());
        } else if (str.equals("bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("requestsSent")) {
            setRequestsSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("requestsReceived")) {
            setRequestsReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("responsesSent")) {
            setResponsesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("responsesReceived")) {
            setResponsesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("consentRequestsSent")) {
            setConsentRequestsSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("consentRequestsReceived")) {
            setConsentRequestsReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("consentResponsesSent")) {
            setConsentResponsesSent(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("consentResponsesReceived")) {
            setConsentResponsesReceived(JsonSerializer.deserializeLong(str2).getValue());
        } else if (str.equals("totalRoundTripTime")) {
            setTotalRoundTripTime(JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("currentRoundTripTime")) {
            setCurrentRoundTripTime(JsonSerializer.deserializeInteger(str2).getValue());
        }
    }

    public static CandidatePairStats fromJson(String str) {
        return (CandidatePairStats) JsonSerializer.deserializeObject(str, new IFunction0<CandidatePairStats>() {
            public CandidatePairStats invoke() {
                return new CandidatePairStats();
            }
        }, new IAction3<CandidatePairStats, String, String>() {
            public void invoke(CandidatePairStats candidatePairStats, String str, String str2) {
                candidatePairStats.deserializeProperties(str, str2);
            }
        });
    }

    public static CandidatePairStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CandidatePairStats>() {
            public String getId() {
                return "fm.liveswitch.CandidatePairStats.fromJson";
            }

            public CandidatePairStats invoke(String str) {
                return CandidatePairStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CandidatePairStats[]) deserializeObjectArray.toArray(new CandidatePairStats[0]);
    }

    public long getBytesReceived() {
        return this._bytesReceived;
    }

    public long getBytesSent() {
        return this._bytesSent;
    }

    public long getConsentRequestsReceived() {
        return this._consentRequestsReceived;
    }

    public long getConsentRequestsSent() {
        return this._consentRequestsSent;
    }

    public long getConsentResponsesReceived() {
        return this._consentResponsesReceived;
    }

    public long getConsentResponsesSent() {
        return this._consentResponsesSent;
    }

    public int getCurrentRoundTripTime() {
        return this._currentRoundTripTime;
    }

    public String getLocalCandidateId() {
        return this._localCandidateId;
    }

    public boolean getNominated() {
        return this._nominated;
    }

    public long getPriority() {
        return this._priority;
    }

    public String getRemoteCandidateId() {
        return this._remoteCandidateId;
    }

    public long getRequestsReceived() {
        return this._requestsReceived;
    }

    public long getRequestsSent() {
        return this._requestsSent;
    }

    public long getResponsesReceived() {
        return this._responsesReceived;
    }

    public long getResponsesSent() {
        return this._responsesSent;
    }

    public CandidatePairState getState() {
        return this._state;
    }

    public int getTotalRoundTripTime() {
        return this._totalRoundTripTime;
    }

    public String getTransportId() {
        return this._transportId;
    }

    public boolean isEquivalent(CandidatePairStats candidatePairStats) {
        return candidatePairStats != null && Global.equals(candidatePairStats.getTransportId(), getTransportId()) && Global.equals(candidatePairStats.getLocalCandidateId(), getLocalCandidateId()) && Global.equals(candidatePairStats.getRemoteCandidateId(), getRemoteCandidateId()) && Global.equals(candidatePairStats.getState(), getState()) && candidatePairStats.getPriority() == getPriority() && Global.equals(Boolean.valueOf(candidatePairStats.getNominated()), Boolean.valueOf(getNominated())) && candidatePairStats.getBytesSent() == getBytesSent() && candidatePairStats.getBytesReceived() == getBytesReceived() && candidatePairStats.getRequestsSent() == getRequestsSent() && candidatePairStats.getRequestsReceived() == getRequestsReceived() && candidatePairStats.getResponsesSent() == getResponsesSent() && candidatePairStats.getResponsesReceived() == getResponsesReceived() && candidatePairStats.getConsentRequestsSent() == getConsentRequestsSent() && candidatePairStats.getConsentRequestsReceived() == getConsentRequestsReceived() && candidatePairStats.getConsentResponsesSent() == getConsentResponsesSent() && candidatePairStats.getConsentResponsesReceived() == getConsentResponsesReceived() && candidatePairStats.getTotalRoundTripTime() == getTotalRoundTripTime() && candidatePairStats.getCurrentRoundTripTime() == getCurrentRoundTripTime();
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getTransportId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "transportId", JsonSerializer.serializeString(getTransportId()));
        }
        if (getLocalCandidateId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localCandidateId", JsonSerializer.serializeString(getLocalCandidateId()));
        }
        if (getRemoteCandidateId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteCandidateId", JsonSerializer.serializeString(getRemoteCandidateId()));
        }
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "state", JsonSerializer.serializeString(CandidateUtility.candidatePairStateToString(getState())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "priority", JsonSerializer.serializeLong(new NullableLong(getPriority())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "nominated", JsonSerializer.serializeBoolean(new NullableBoolean(getNominated())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(new NullableLong(getBytesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(new NullableLong(getBytesReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "requestsSent", JsonSerializer.serializeLong(new NullableLong(getRequestsSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "requestsReceived", JsonSerializer.serializeLong(new NullableLong(getRequestsReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "responsesSent", JsonSerializer.serializeLong(new NullableLong(getResponsesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "responsesReceived", JsonSerializer.serializeLong(new NullableLong(getResponsesReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentRequestsSent", JsonSerializer.serializeLong(new NullableLong(getConsentRequestsSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentRequestsReceived", JsonSerializer.serializeLong(new NullableLong(getConsentRequestsReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentResponsesSent", JsonSerializer.serializeLong(new NullableLong(getConsentResponsesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentResponsesReceived", JsonSerializer.serializeLong(new NullableLong(getConsentResponsesReceived())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "totalRoundTripTime", JsonSerializer.serializeInteger(new NullableInteger(getTotalRoundTripTime())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "currentRoundTripTime", JsonSerializer.serializeInteger(new NullableInteger(getCurrentRoundTripTime())));
    }

    /* access modifiers changed from: package-private */
    public void setBytesReceived(long j) {
        this._bytesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setBytesSent(long j) {
        this._bytesSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setConsentRequestsReceived(long j) {
        this._consentRequestsReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setConsentRequestsSent(long j) {
        this._consentRequestsSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setConsentResponsesReceived(long j) {
        this._consentResponsesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setConsentResponsesSent(long j) {
        this._consentResponsesSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentRoundTripTime(int i) {
        this._currentRoundTripTime = i;
    }

    /* access modifiers changed from: package-private */
    public void setLocalCandidateId(String str) {
        this._localCandidateId = str;
    }

    /* access modifiers changed from: package-private */
    public void setNominated(boolean z) {
        this._nominated = z;
    }

    /* access modifiers changed from: package-private */
    public void setPriority(long j) {
        this._priority = j;
    }

    /* access modifiers changed from: package-private */
    public void setRemoteCandidateId(String str) {
        this._remoteCandidateId = str;
    }

    /* access modifiers changed from: package-private */
    public void setRequestsReceived(long j) {
        this._requestsReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setRequestsSent(long j) {
        this._requestsSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setResponsesReceived(long j) {
        this._responsesReceived = j;
    }

    /* access modifiers changed from: package-private */
    public void setResponsesSent(long j) {
        this._responsesSent = j;
    }

    /* access modifiers changed from: package-private */
    public void setState(CandidatePairState candidatePairState) {
        this._state = candidatePairState;
    }

    /* access modifiers changed from: package-private */
    public void setTotalRoundTripTime(int i) {
        this._totalRoundTripTime = i;
    }

    /* access modifiers changed from: package-private */
    public void setTransportId(String str) {
        this._transportId = str;
    }

    public static String toJson(CandidatePairStats candidatePairStats) {
        return JsonSerializer.serializeObject(candidatePairStats, new IAction2<CandidatePairStats, HashMap<String, String>>() {
            public void invoke(CandidatePairStats candidatePairStats, HashMap<String, String> hashMap) {
                candidatePairStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CandidatePairStats[] candidatePairStatsArr) {
        return JsonSerializer.serializeObjectArray(candidatePairStatsArr, new IFunctionDelegate1<CandidatePairStats, String>() {
            public String getId() {
                return "fm.liveswitch.CandidatePairStats.toJson";
            }

            public String invoke(CandidatePairStats candidatePairStats) {
                return CandidatePairStats.toJson(candidatePairStats);
            }
        });
    }
}
