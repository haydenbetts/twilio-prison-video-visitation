package fm.liveswitch;

import java.util.HashMap;

public class TransportStats extends BaseStats {
    private CandidatePairStats _activeCandidatePair;
    private long _bytesReceived;
    private long _bytesSent;
    private CandidatePairStats[] _candidatePairs;
    private CandidateStats[] _localCandidates;
    private CertificateStats _localCertificate;
    private CandidateStats[] _remoteCandidates;
    private CertificateStats _remoteCertificate;
    private TransportStats _rtcpTransport;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str.equals("bytesSent")) {
            setBytesSent((long) JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("bytesReceived")) {
            setBytesReceived((long) JsonSerializer.deserializeInteger(str2).getValue());
        } else if (str.equals("rtcpTransport")) {
            setRtcpTransport(fromJson(str2));
        } else if (str.equals("localCandidates")) {
            setLocalCandidates(CandidateStats.fromJsonArray(str2));
        } else if (str.equals("remoteCandidates")) {
            setRemoteCandidates(CandidateStats.fromJsonArray(str2));
        } else if (str.equals("candidatePairs")) {
            setCandidatePairs(CandidatePairStats.fromJsonArray(str2));
        } else if (str.equals("activeCandidatePair")) {
            setActiveCandidatePair(CandidatePairStats.fromJson(str2));
        } else if (str.equals("localCertificate")) {
            setLocalCertificate(CertificateStats.fromJson(str2));
        } else if (str.equals("remoteCertificate")) {
            setRemoteCertificate(CertificateStats.fromJson(str2));
        }
    }

    public static TransportStats fromJson(String str) {
        return (TransportStats) JsonSerializer.deserializeObject(str, new IFunction0<TransportStats>() {
            public TransportStats invoke() {
                return new TransportStats();
            }
        }, new IAction3<TransportStats, String, String>() {
            public void invoke(TransportStats transportStats, String str, String str2) {
                transportStats.deserializeProperties(str, str2);
            }
        });
    }

    public CandidatePairStats getActiveCandidatePair() {
        return this._activeCandidatePair;
    }

    public long getBytesReceived() {
        return this._bytesReceived;
    }

    public long getBytesSent() {
        return this._bytesSent;
    }

    public CandidatePairStats getCandidatePair(String str) {
        for (CandidatePairStats candidatePairStats : getCandidatePairs()) {
            if (Global.equals(candidatePairStats.getId(), str)) {
                return candidatePairStats;
            }
        }
        return null;
    }

    public CandidatePairStats[] getCandidatePairs() {
        return this._candidatePairs;
    }

    public boolean getIsHost() {
        CandidatePairStats activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair == null) {
            return false;
        }
        CandidateStats localCandidate = getLocalCandidate(activeCandidatePair.getLocalCandidateId());
        CandidateStats remoteCandidate = getRemoteCandidate(activeCandidatePair.getRemoteCandidateId());
        if ((localCandidate == null || !localCandidate.getIsHost()) && (remoteCandidate == null || !remoteCandidate.getIsHost())) {
            return false;
        }
        return true;
    }

    public boolean getIsReflexive() {
        CandidatePairStats activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair == null) {
            return false;
        }
        CandidateStats localCandidate = getLocalCandidate(activeCandidatePair.getLocalCandidateId());
        CandidateStats remoteCandidate = getRemoteCandidate(activeCandidatePair.getRemoteCandidateId());
        if ((localCandidate == null || !localCandidate.getIsReflexive()) && (remoteCandidate == null || !remoteCandidate.getIsReflexive())) {
            return false;
        }
        return true;
    }

    public boolean getIsRelayed() {
        CandidatePairStats activeCandidatePair = getActiveCandidatePair();
        if (activeCandidatePair == null) {
            return false;
        }
        CandidateStats localCandidate = getLocalCandidate(activeCandidatePair.getLocalCandidateId());
        CandidateStats remoteCandidate = getRemoteCandidate(activeCandidatePair.getRemoteCandidateId());
        if ((localCandidate == null || !localCandidate.getIsRelayed()) && (remoteCandidate == null || !remoteCandidate.getIsRelayed())) {
            return false;
        }
        return true;
    }

    public CandidateStats getLocalCandidate(String str) {
        for (CandidateStats candidateStats : getLocalCandidates()) {
            if (Global.equals(candidateStats.getId(), str)) {
                return candidateStats;
            }
        }
        return null;
    }

    public CandidateStats[] getLocalCandidates() {
        return this._localCandidates;
    }

    public CertificateStats getLocalCertificate() {
        return this._localCertificate;
    }

    public CandidateStats getRemoteCandidate(String str) {
        for (CandidateStats candidateStats : getRemoteCandidates()) {
            if (Global.equals(candidateStats.getId(), str)) {
                return candidateStats;
            }
        }
        return null;
    }

    public CandidateStats[] getRemoteCandidates() {
        return this._remoteCandidates;
    }

    public CertificateStats getRemoteCertificate() {
        return this._remoteCertificate;
    }

    public TransportStats getRtcpTransport() {
        return this._rtcpTransport;
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesSent", JsonSerializer.serializeLong(new NullableLong(getBytesSent())));
        HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "bytesReceived", JsonSerializer.serializeLong(new NullableLong(getBytesReceived())));
        if (getRtcpTransport() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "rtcpTransport", toJson(getRtcpTransport()));
        }
        if (getLocalCandidates() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localCandidates", CandidateStats.toJsonArray(getLocalCandidates()));
        }
        if (getRemoteCandidates() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteCandidates", CandidateStats.toJsonArray(getRemoteCandidates()));
        }
        if (getCandidatePairs() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "candidatePairs", CandidatePairStats.toJsonArray(getCandidatePairs()));
        }
        if (getActiveCandidatePair() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "activeCandidatePair", CandidatePairStats.toJson(getActiveCandidatePair()));
        }
        if (getLocalCertificate() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "localCertificate", CertificateStats.toJson(getLocalCertificate()));
        }
        if (getRemoteCertificate() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "remoteCertificate", CertificateStats.toJson(getRemoteCertificate()));
        }
    }

    public void setActiveCandidatePair(CandidatePairStats candidatePairStats) {
        this._activeCandidatePair = candidatePairStats;
    }

    public void setBytesReceived(long j) {
        this._bytesReceived = j;
    }

    public void setBytesSent(long j) {
        this._bytesSent = j;
    }

    public void setCandidatePairs(CandidatePairStats[] candidatePairStatsArr) {
        this._candidatePairs = candidatePairStatsArr;
    }

    public void setLocalCandidates(CandidateStats[] candidateStatsArr) {
        this._localCandidates = candidateStatsArr;
    }

    public void setLocalCertificate(CertificateStats certificateStats) {
        this._localCertificate = certificateStats;
    }

    public void setRemoteCandidates(CandidateStats[] candidateStatsArr) {
        this._remoteCandidates = candidateStatsArr;
    }

    public void setRemoteCertificate(CertificateStats certificateStats) {
        this._remoteCertificate = certificateStats;
    }

    public void setRtcpTransport(TransportStats transportStats) {
        this._rtcpTransport = transportStats;
    }

    public static String toJson(TransportStats transportStats) {
        return JsonSerializer.serializeObject(transportStats, new IAction2<TransportStats, HashMap<String, String>>() {
            public void invoke(TransportStats transportStats, HashMap<String, String> hashMap) {
                transportStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }
}
