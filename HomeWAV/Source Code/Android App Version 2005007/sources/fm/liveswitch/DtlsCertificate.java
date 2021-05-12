package fm.liveswitch;

import java.util.Date;

public class DtlsCertificate extends Certificate<DtlsCertificate, DtlsFingerprint> {
    private static AsymmetricKeyType __defaultKeyType = AsymmetricKeyType.Ecdsa;

    /* access modifiers changed from: protected */
    public DtlsCertificate createCertificate() {
        return new DtlsCertificate();
    }

    /* access modifiers changed from: protected */
    public DtlsFingerprint createFingerprint(String str, String str2) {
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("Calculated DTLS certificate fingerprint {0} for algorithm {1}.", str2.toString(), str));
        }
        return new DtlsFingerprint(str, str2.toString());
    }

    public static DtlsCertificate generateCertificate() {
        return generateCertificate(getDefaultKeyType());
    }

    public static DtlsCertificate generateCertificate(String str, String str2) {
        return generateCertificate(str, str2, getDefaultKeyType());
    }

    public static DtlsCertificate generateCertificate(String str, String str2, Date date) {
        return generateCertificate(str, str2, date, getDefaultKeyType());
    }

    public static DtlsCertificate generateCertificate(String str, String str2, Date date, AsymmetricKey asymmetricKey) {
        return (DtlsCertificate) Certificate.generateCertificate(str, str2, date, asymmetricKey, new DtlsCertificate());
    }

    public static DtlsCertificate generateCertificate(String str, String str2, Date date, AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(str, str2, date, AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static DtlsCertificate generateCertificate(String str, String str2, AsymmetricKey asymmetricKey) {
        return (DtlsCertificate) Certificate.generateCertificate(str, str2, asymmetricKey, new DtlsCertificate());
    }

    public static DtlsCertificate generateCertificate(String str, String str2, AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(str, str2, AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static DtlsCertificate generateCertificate(AsymmetricKey asymmetricKey) {
        return (DtlsCertificate) Certificate.generateCertificate(asymmetricKey, new DtlsCertificate());
    }

    public static DtlsCertificate generateCertificate(AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static DtlsCertificate generateCertificate(String str) {
        return generateCertificate(str, getDefaultKeyType());
    }

    public static DtlsCertificate generateCertificate(String str, AsymmetricKey asymmetricKey) {
        return (DtlsCertificate) Certificate.generateCertificate(str, asymmetricKey, new DtlsCertificate());
    }

    public static DtlsCertificate generateCertificate(String str, AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(str, AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static DtlsCertificate generateCertificateFromOldCertificate(DtlsCertificate dtlsCertificate) {
        return (DtlsCertificate) Certificate.generateCertificateFromOldCertificate(dtlsCertificate, new DtlsCertificate());
    }

    public static DtlsCertificate generateCertificateFromOldCertificate(DtlsCertificate dtlsCertificate, Date date) {
        return (DtlsCertificate) Certificate.generateCertificateFromOldCertificate(dtlsCertificate, date, new DtlsCertificate());
    }

    public static AsymmetricKeyType getDefaultKeyType() {
        return __defaultKeyType;
    }

    public static DtlsCertificate parseBytes(byte[] bArr) {
        return (DtlsCertificate) Certificate.parseBytes(bArr, new DtlsCertificate());
    }

    public static void setDefaultKeyType(AsymmetricKeyType asymmetricKeyType) {
        __defaultKeyType = asymmetricKeyType;
    }
}
