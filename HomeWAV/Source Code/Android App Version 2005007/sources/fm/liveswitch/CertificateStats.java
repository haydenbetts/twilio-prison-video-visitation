package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CertificateStats extends BaseStats implements IEquivalent<CertificateStats> {
    private String _certificateBase64;
    private String _fingerprint;
    private String _fingerprintAlgorithm;

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "fingerprint")) {
            setFingerprint(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "fingerprintAlgorithm")) {
            setFingerprintAlgorithm(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "certificateBase64")) {
            setCertificateBase64(JsonSerializer.deserializeString(str2));
        }
    }

    public static CertificateStats fromJson(String str) {
        return (CertificateStats) JsonSerializer.deserializeObject(str, new IFunction0<CertificateStats>() {
            public CertificateStats invoke() {
                return new CertificateStats();
            }
        }, new IAction3<CertificateStats, String, String>() {
            public void invoke(CertificateStats certificateStats, String str, String str2) {
                certificateStats.deserializeProperties(str, str2);
            }
        });
    }

    public static CertificateStats[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CertificateStats>() {
            public String getId() {
                return "fm.liveswitch.CertificateStats.fromJson";
            }

            public CertificateStats invoke(String str) {
                return CertificateStats.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CertificateStats[]) deserializeObjectArray.toArray(new CertificateStats[0]);
    }

    public String getCertificateBase64() {
        return this._certificateBase64;
    }

    public String getFingerprint() {
        return this._fingerprint;
    }

    public String getFingerprintAlgorithm() {
        return this._fingerprintAlgorithm;
    }

    public boolean isEquivalent(CertificateStats certificateStats) {
        return certificateStats != null && Global.equals(certificateStats.getFingerprint(), getFingerprint()) && Global.equals(certificateStats.getFingerprintAlgorithm(), getFingerprintAlgorithm()) && Global.equals(certificateStats.getCertificateBase64(), getCertificateBase64());
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
        super.serializeProperties(hashMap);
        if (getFingerprint() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "fingerprint", JsonSerializer.serializeString(getFingerprint()));
        }
        if (getFingerprintAlgorithm() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "fingerprintAlgorithm", JsonSerializer.serializeString(getFingerprintAlgorithm()));
        }
        if (getCertificateBase64() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "certificateBase64", JsonSerializer.serializeString(getCertificateBase64()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setCertificateBase64(String str) {
        this._certificateBase64 = str;
    }

    /* access modifiers changed from: package-private */
    public void setFingerprint(String str) {
        this._fingerprint = str;
    }

    /* access modifiers changed from: package-private */
    public void setFingerprintAlgorithm(String str) {
        this._fingerprintAlgorithm = str;
    }

    public static String toJson(CertificateStats certificateStats) {
        return JsonSerializer.serializeObject(certificateStats, new IAction2<CertificateStats, HashMap<String, String>>() {
            public void invoke(CertificateStats certificateStats, HashMap<String, String> hashMap) {
                certificateStats.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CertificateStats[] certificateStatsArr) {
        return JsonSerializer.serializeObjectArray(certificateStatsArr, new IFunctionDelegate1<CertificateStats, String>() {
            public String getId() {
                return "fm.liveswitch.CertificateStats.toJson";
            }

            public String invoke(CertificateStats certificateStats) {
                return CertificateStats.toJson(certificateStats);
            }
        });
    }
}
