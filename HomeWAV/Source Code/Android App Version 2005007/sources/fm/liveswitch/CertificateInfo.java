package fm.liveswitch;

import java.util.ArrayList;
import java.util.HashMap;

public class CertificateInfo extends Info {
    private String _base64;
    private String _fingerprint;
    private String _fingerprintAlgorithm;

    public CertificateInfo() {
    }

    CertificateInfo(CertificateStats certificateStats, CertificateStats certificateStats2) {
        boolean z = certificateStats2 == null;
        String fingerprint = certificateStats.getFingerprint();
        setFingerprint(!z ? Info.processString(fingerprint, certificateStats2.getFingerprint()) : fingerprint);
        String fingerprintAlgorithm = certificateStats.getFingerprintAlgorithm();
        setFingerprintAlgorithm(!z ? Info.processString(fingerprintAlgorithm, certificateStats2.getFingerprintAlgorithm()) : fingerprintAlgorithm);
        String certificateBase64 = certificateStats.getCertificateBase64();
        setBase64(!z ? Info.processString(certificateBase64, certificateStats2.getCertificateBase64()) : certificateBase64);
    }

    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
        super.deserializeProperties(str, str2);
        if (str == null) {
            return;
        }
        if (Global.equals(str, "fingerprint")) {
            setFingerprint(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "remoteCandidateId")) {
            setFingerprintAlgorithm(JsonSerializer.deserializeString(str2));
        } else if (Global.equals(str, "base64")) {
            setBase64(JsonSerializer.deserializeString(str2));
        }
    }

    public static CertificateInfo fromJson(String str) {
        return (CertificateInfo) JsonSerializer.deserializeObject(str, new IFunction0<CertificateInfo>() {
            public CertificateInfo invoke() {
                return new CertificateInfo();
            }
        }, new IAction3<CertificateInfo, String, String>() {
            public void invoke(CertificateInfo certificateInfo, String str, String str2) {
                certificateInfo.deserializeProperties(str, str2);
            }
        });
    }

    public static CertificateInfo[] fromJsonArray(String str) {
        ArrayList deserializeObjectArray = JsonSerializer.deserializeObjectArray(str, new IFunctionDelegate1<String, CertificateInfo>() {
            public String getId() {
                return "fm.liveswitch.CertificateInfo.fromJson";
            }

            public CertificateInfo invoke(String str) {
                return CertificateInfo.fromJson(str);
            }
        });
        if (deserializeObjectArray == null) {
            return null;
        }
        return (CertificateInfo[]) deserializeObjectArray.toArray(new CertificateInfo[0]);
    }

    public String getBase64() {
        return this._base64;
    }

    public String getFingerprint() {
        return this._fingerprint;
    }

    public String getFingerprintAlgorithm() {
        return this._fingerprintAlgorithm;
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
        if (getBase64() != null) {
            HashMapExtensions.set(HashMapExtensions.getItem(hashMap), "base64", JsonSerializer.serializeString(getBase64()));
        }
    }

    public void setBase64(String str) {
        this._base64 = str;
    }

    public void setFingerprint(String str) {
        this._fingerprint = str;
    }

    public void setFingerprintAlgorithm(String str) {
        this._fingerprintAlgorithm = str;
    }

    public static String toJson(CertificateInfo certificateInfo) {
        return JsonSerializer.serializeObject(certificateInfo, new IAction2<CertificateInfo, HashMap<String, String>>() {
            public void invoke(CertificateInfo certificateInfo, HashMap<String, String> hashMap) {
                certificateInfo.serializeProperties(hashMap);
            }
        });
    }

    public String toJson() {
        return toJson(this);
    }

    public static String toJsonArray(CertificateInfo[] certificateInfoArr) {
        return JsonSerializer.serializeObjectArray(certificateInfoArr, new IFunctionDelegate1<CertificateInfo, String>() {
            public String getId() {
                return "fm.liveswitch.CertificateInfo.toJson";
            }

            public String invoke(CertificateInfo certificateInfo) {
                return CertificateInfo.toJson(certificateInfo);
            }
        });
    }
}
