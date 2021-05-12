package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class TransportInfo extends Info {
    private String _activeCandidatePairId;
    private CandidatePairInfo[] _candidatePairs;
    private CandidateInfo[] _localCandidates;
    private CertificateInfo _localCertificate;
    private CandidateInfo[] _remoteCandidates;
    private CertificateInfo _remoteCertificate;
    private TransportReport _report;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("localCertificate")) {
            setLocalCertificate(CertificateInfo.fromJson(str2));
        } else if (str.equals("remoteCertificate")) {
            setRemoteCertificate(CertificateInfo.fromJson(str2));
        } else if (str.equals("localCandidates")) {
            setLocalCandidates(CandidateInfo.fromJsonArray(str2));
        } else if (str.equals("remoteCandidates")) {
            setRemoteCandidates(CandidateInfo.fromJsonArray(str2));
        } else if (str.equals("candidatePairs")) {
            setCandidatePairs(CandidatePairInfo.fromJsonArray(str2));
        } else if (str.equals("activeCandidatePairId")) {
            setActiveCandidatePairId(JsonSerializer.deserializeString(str2));
        } else if (str.equals("report")) {
            setReport(TransportReport.fromJson(str2));
        }
    }

    private CandidateStats findMatchingCandidate(CandidateStats[] candidateStatsArr, String str) {
        if (candidateStatsArr == null) {
            return null;
        }
        for (CandidateStats candidateStats : candidateStatsArr) {
            if (Global.equals(candidateStats.getId(), str)) {
                return candidateStats;
            }
        }
        return null;
    }

    private CandidatePairStats findMatchingCandidatePair(CandidatePairStats[] candidatePairStatsArr, String str) {
        if (candidatePairStatsArr == null) {
            return null;
        }
        for (CandidatePairStats candidatePairStats : candidatePairStatsArr) {
            if (Global.equals(candidatePairStats.getId(), str)) {
                return candidatePairStats;
            }
        }
        return null;
    }

    public static TransportInfo fromJson(String str) {
        return (TransportInfo) JsonSerializer.deserializeObject(str, new IFunction0<TransportInfo>() {
            public TransportInfo invoke() {
                return new TransportInfo();
            }
        }, new IAction3<TransportInfo, String, String>() {
            public void invoke(TransportInfo transportInfo, String str, String str2) {
                transportInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static TransportInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, TransportInfo>() {
            public String getId() {
                return "fm.liveswitch.TransportInfo.fromJson";
            }

            public TransportInfo invoke(String str) {
                return TransportInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (TransportInfo[]) deserializeObjectArray.toArray(new TransportInfo[0]);
    }

    public String getActiveCandidatePairId() {
        return this._activeCandidatePairId;
    }

    public CandidatePairInfo[] getCandidatePairs() {
        return this._candidatePairs;
    }

    public CandidateInfo[] getLocalCandidates() {
        return this._localCandidates;
    }

    public CertificateInfo getLocalCertificate() {
        return this._localCertificate;
    }

    public CandidateInfo[] getRemoteCandidates() {
        return this._remoteCandidates;
    }

    public CertificateInfo getRemoteCertificate() {
        return this._remoteCertificate;
    }

    public TransportReport getReport() {
        return this._report;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getLocalCertificate() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localCertificate", CertificateInfo.toJson(getLocalCertificate()));
        }
        if (getRemoteCertificate() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteCertificate", CertificateInfo.toJson(getRemoteCertificate()));
        }
        if (getLocalCandidates() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localCandidates", CandidateInfo.toJsonArray(getLocalCandidates()));
        }
        if (getRemoteCandidates() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteCandidates", CandidateInfo.toJsonArray(getRemoteCandidates()));
        }
        if (getCandidatePairs() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "candidatePairs", CandidatePairInfo.toJsonArray(getCandidatePairs()));
        }
        if (getActiveCandidatePairId() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "activeCandidatePairId", JsonSerializer.serializeString(getActiveCandidatePairId()));
        }
        if (getReport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "report", TransportReport.toJson(getReport()));
        }
    }

    public void setActiveCandidatePairId(String str) {
        this._activeCandidatePairId = str;
    }

    public void setCandidatePairs(CandidatePairInfo[] candidatePairInfoArr) {
        this._candidatePairs = candidatePairInfoArr;
    }

    public void setLocalCandidates(CandidateInfo[] candidateInfoArr) {
        this._localCandidates = candidateInfoArr;
    }

    public void setLocalCertificate(CertificateInfo certificateInfo) {
        this._localCertificate = certificateInfo;
    }

    public void setRemoteCandidates(CandidateInfo[] candidateInfoArr) {
        this._remoteCandidates = candidateInfoArr;
    }

    public void setRemoteCertificate(CertificateInfo certificateInfo) {
        this._remoteCertificate = certificateInfo;
    }

    public void setReport(TransportReport transportReport) {
        this._report = transportReport;
    }

    public static String toJson(TransportInfo transportInfo) {
        return JsonSerializer.serializeObject(transportInfo, new IAction2<TransportInfo, HashMap<String, String>>() {
            public void invoke(TransportInfo transportInfo, HashMap<String, String> hashMap) {
                transportInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(TransportInfo[] transportInfoArr) {
        return JsonSerializer.serializeObjectArray(transportInfoArr, new IFunctionDelegate1<TransportInfo, String>() {
            public String getId() {
                return "fm.liveswitch.TransportInfo.toJson";
            }

            public String invoke(TransportInfo transportInfo) {
                return TransportInfo.toJson(transportInfo);
            }
        });
    }

    public TransportInfo() {
    }

    TransportInfo(TransportStats transportStats, TransportStats transportStats2) {
        super.setId(transportStats.getId());
        boolean z = transportStats2 == null;
        String str = null;
        if (transportStats.getLocalCertificate() != null) {
            setLocalCertificate(new CertificateInfo(transportStats.getLocalCertificate(), z ? null : transportStats2.getLocalCertificate()));
        }
        if (transportStats.getRemoteCertificate() != null) {
            setRemoteCertificate(new CertificateInfo(transportStats.getRemoteCertificate(), z ? null : transportStats2.getRemoteCertificate()));
        }
        if (transportStats.getLocalCandidates() != null) {
            setLocalCandidates(new CandidateInfo[ArrayExtensions.getLength((Object[]) transportStats.getLocalCandidates())]);
            for (int i = 0; i < ArrayExtensions.getLength((Object[]) transportStats.getLocalCandidates()); i++) {
                CandidateStats candidateStats = transportStats.getLocalCandidates()[i];
                getLocalCandidates()[i] = new CandidateInfo(candidateStats, !z ? findMatchingCandidate(transportStats2.getLocalCandidates(), candidateStats.getId()) : null);
            }
        }
        if (transportStats.getRemoteCandidates() != null) {
            setRemoteCandidates(new CandidateInfo[ArrayExtensions.getLength((Object[]) transportStats.getRemoteCandidates())]);
            for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) transportStats.getRemoteCandidates()); i2++) {
                CandidateStats candidateStats2 = transportStats.getRemoteCandidates()[i2];
                getRemoteCandidates()[i2] = new CandidateInfo(candidateStats2, !z ? findMatchingCandidate(transportStats2.getRemoteCandidates(), candidateStats2.getId()) : null);
            }
        }
        if (transportStats.getCandidatePairs() != null) {
            setCandidatePairs(new CandidatePairInfo[ArrayExtensions.getLength((Object[]) transportStats.getCandidatePairs())]);
            for (int i3 = 0; i3 < ArrayExtensions.getLength((Object[]) transportStats.getCandidatePairs()); i3++) {
                CandidatePairStats candidatePairStats = transportStats.getCandidatePairs()[i3];
                getCandidatePairs()[i3] = new CandidatePairInfo(candidatePairStats, !z ? findMatchingCandidatePair(transportStats2.getCandidatePairs(), candidatePairStats.getId()) : null);
            }
        }
        setActiveCandidatePairId(transportStats.getActiveCandidatePair() != null ? transportStats.getActiveCandidatePair().getId() : str);
        setReport(new TransportReport(transportStats, transportStats2));
    }
}
