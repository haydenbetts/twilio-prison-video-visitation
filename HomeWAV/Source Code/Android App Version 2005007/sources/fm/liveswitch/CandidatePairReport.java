package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CandidatePairReport extends Report {
    private NullableLong _bytesReceived = new NullableLong();
    private NullableLong _bytesSent = new NullableLong();
    private NullableLong _consentRequestsReceived = new NullableLong();
    private NullableLong _consentRequestsSent = new NullableLong();
    private NullableLong _consentResponsesReceived = new NullableLong();
    private NullableLong _consentResponsesSent = new NullableLong();
    private NullableInteger _currentRoundTripTime = new NullableInteger();
    private NullableLong _requestsReceived = new NullableLong();
    private NullableLong _requestsSent = new NullableLong();
    private NullableLong _responsesReceived = new NullableLong();
    private NullableLong _responsesSent = new NullableLong();
    private NullableInteger _totalRoundTripTime = new NullableInteger();

    public CandidatePairReport() {
    }

    CandidatePairReport(CandidatePairStats candidatePairStats, CandidatePairStats candidatePairStats2) {
        int i = 0;
        boolean z = candidatePairStats2 == null;
        long j = 0;
        setBytesSent(Report.processLong(candidatePairStats.getBytesSent(), z ? 0 : candidatePairStats2.getBytesSent()));
        setBytesReceived(Report.processLong(candidatePairStats.getBytesReceived(), z ? 0 : candidatePairStats2.getBytesReceived()));
        setRequestsSent(Report.processLong(candidatePairStats.getRequestsSent(), z ? 0 : candidatePairStats2.getRequestsSent()));
        setRequestsReceived(Report.processLong(candidatePairStats.getRequestsReceived(), z ? 0 : candidatePairStats2.getRequestsReceived()));
        setResponsesSent(Report.processLong(candidatePairStats.getResponsesSent(), z ? 0 : candidatePairStats2.getResponsesSent()));
        setResponsesReceived(Report.processLong(candidatePairStats.getResponsesReceived(), z ? 0 : candidatePairStats2.getResponsesReceived()));
        setConsentRequestsSent(Report.processLong(candidatePairStats.getConsentRequestsSent(), z ? 0 : candidatePairStats2.getConsentRequestsSent()));
        setConsentRequestsReceived(Report.processLong(candidatePairStats.getConsentRequestsReceived(), z ? 0 : candidatePairStats2.getConsentRequestsReceived()));
        setConsentResponsesSent(Report.processLong(candidatePairStats.getConsentResponsesSent(), z ? 0 : candidatePairStats2.getConsentResponsesSent()));
        setConsentResponsesReceived(Report.processLong(candidatePairStats.getConsentResponsesReceived(), !z ? candidatePairStats2.getConsentResponsesReceived() : j));
        setTotalRoundTripTime(Report.processInteger(candidatePairStats.getTotalRoundTripTime(), z ? 0 : candidatePairStats2.getTotalRoundTripTime()));
        setCurrentRoundTripTime(Report.processInteger(candidatePairStats.getCurrentRoundTripTime(), !z ? candidatePairStats2.getCurrentRoundTripTime() : i));
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("bytesSent")) {
            setBytesSent(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("bytesReceived")) {
            setBytesReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("requestsSent")) {
            setRequestsSent(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("requestsReceived")) {
            setRequestsReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("responsesSent")) {
            setResponsesSent(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("responsesReceived")) {
            setResponsesReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("consentRequestsSent")) {
            setConsentRequestsSent(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("consentRequestsReceived")) {
            setConsentRequestsReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("consentResponsesSent")) {
            setConsentResponsesSent(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("consentResponsesReceived")) {
            setConsentResponsesReceived(JsonSerializer.deserializeLong(str2));
        } else if (str.equals("totalRoundTripTime")) {
            setTotalRoundTripTime(JsonSerializer.deserializeInteger(str2));
        } else if (str.equals("currentRoundTripTime")) {
            setCurrentRoundTripTime(JsonSerializer.deserializeInteger(str2));
        }
    }

    public static CandidatePairReport fromJson(String str) {
        return (CandidatePairReport) JsonSerializer.deserializeObject(str, new IFunction0<CandidatePairReport>() {
            public CandidatePairReport invoke() {
                return new CandidatePairReport();
            }
        }, new IAction3<CandidatePairReport, String, String>() {
            public void invoke(CandidatePairReport candidatePairReport, String str, String str2) {
                candidatePairReport.deserializeProperties(str, str2);
            }
        });
    }

    public static CandidatePairReport[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CandidatePairReport>() {
            public String getId() {
                return "fm.liveswitch.CandidatePairReport.fromJson";
            }

            public CandidatePairReport invoke(String str) {
                return CandidatePairReport.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CandidatePairReport[]) deserializeObjectArray.toArray(new CandidatePairReport[0]);
    }

    public NullableLong getBytesReceived() {
        return this._bytesReceived;
    }

    public NullableLong getBytesSent() {
        return this._bytesSent;
    }

    public NullableLong getConsentRequestsReceived() {
        return this._consentRequestsReceived;
    }

    public NullableLong getConsentRequestsSent() {
        return this._consentRequestsSent;
    }

    public NullableLong getConsentResponsesReceived() {
        return this._consentResponsesReceived;
    }

    public NullableLong getConsentResponsesSent() {
        return this._consentResponsesSent;
    }

    public NullableInteger getCurrentRoundTripTime() {
        return this._currentRoundTripTime;
    }

    public NullableLong getRequestsReceived() {
        return this._requestsReceived;
    }

    public NullableLong getRequestsSent() {
        return this._requestsSent;
    }

    public NullableLong getResponsesReceived() {
        return this._responsesReceived;
    }

    public NullableLong getResponsesSent() {
        return this._responsesSent;
    }

    public NullableInteger getTotalRoundTripTime() {
        return this._totalRoundTripTime;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getBytesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(getBytesSent()));
        }
        if (getBytesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(getBytesReceived()));
        }
        if (getRequestsSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "requestsSent", JsonSerializer.serializeLong(getRequestsSent()));
        }
        if (getRequestsReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "requestsReceived", JsonSerializer.serializeLong(getRequestsReceived()));
        }
        if (getResponsesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "responsesSent", JsonSerializer.serializeLong(getResponsesSent()));
        }
        if (getResponsesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "responsesReceived", JsonSerializer.serializeLong(getResponsesReceived()));
        }
        if (getConsentRequestsSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentRequestsSent", JsonSerializer.serializeLong(getConsentRequestsSent()));
        }
        if (getConsentRequestsReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentRequestsReceived", JsonSerializer.serializeLong(getConsentRequestsReceived()));
        }
        if (getConsentResponsesSent().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentResponsesSent", JsonSerializer.serializeLong(getConsentResponsesSent()));
        }
        if (getConsentResponsesReceived().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "consentResponsesReceived", JsonSerializer.serializeLong(getConsentResponsesReceived()));
        }
        if (getTotalRoundTripTime().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "totalRoundTripTime", JsonSerializer.serializeInteger(getTotalRoundTripTime()));
        }
        if (getCurrentRoundTripTime().getHasValue()) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "currentRoundTripTime", JsonSerializer.serializeInteger(getCurrentRoundTripTime()));
        }
    }

    public void setBytesReceived(NullableLong nullableLong) {
        this._bytesReceived = nullableLong;
    }

    public void setBytesSent(NullableLong nullableLong) {
        this._bytesSent = nullableLong;
    }

    public void setConsentRequestsReceived(NullableLong nullableLong) {
        this._consentRequestsReceived = nullableLong;
    }

    public void setConsentRequestsSent(NullableLong nullableLong) {
        this._consentRequestsSent = nullableLong;
    }

    public void setConsentResponsesReceived(NullableLong nullableLong) {
        this._consentResponsesReceived = nullableLong;
    }

    public void setConsentResponsesSent(NullableLong nullableLong) {
        this._consentResponsesSent = nullableLong;
    }

    public void setCurrentRoundTripTime(NullableInteger nullableInteger) {
        this._currentRoundTripTime = nullableInteger;
    }

    public void setRequestsReceived(NullableLong nullableLong) {
        this._requestsReceived = nullableLong;
    }

    public void setRequestsSent(NullableLong nullableLong) {
        this._requestsSent = nullableLong;
    }

    public void setResponsesReceived(NullableLong nullableLong) {
        this._responsesReceived = nullableLong;
    }

    public void setResponsesSent(NullableLong nullableLong) {
        this._responsesSent = nullableLong;
    }

    public void setTotalRoundTripTime(NullableInteger nullableInteger) {
        this._totalRoundTripTime = nullableInteger;
    }

    public static String toJson(CandidatePairReport candidatePairReport) {
        return JsonSerializer.serializeObject(candidatePairReport, new IAction2<CandidatePairReport, HashMap<String, String>>() {
            public void invoke(CandidatePairReport candidatePairReport, HashMap<String, String> hashMap) {
                candidatePairReport.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CandidatePairReport[] candidatePairReportArr) {
        return JsonSerializer.serializeObjectArray(candidatePairReportArr, new IFunctionDelegate1<CandidatePairReport, String>() {
            public String getId() {
                return "fm.liveswitch.CandidatePairReport.toJson";
            }

            public String invoke(CandidatePairReport candidatePairReport) {
                return CandidatePairReport.toJson(candidatePairReport);
            }
        });
    }
}
