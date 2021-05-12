package fm.liveswitch;

import java.util.Date;

public class TlsCertificate extends Certificate<TlsCertificate, TlsFingerprint> {
    private static AsymmetricKeyType __defaultKeyType = AsymmetricKeyType.Rsa;

    /* access modifiers changed from: protected */
    public TlsCertificate createCertificate() {
        return new TlsCertificate();
    }

    /* access modifiers changed from: protected */
    public TlsFingerprint createFingerprint(String str, String str2) {
        if (Log.getIsDebugEnabled()) {
            Log.debug(StringExtensions.format("Calculated TLS certificate fingerprint {0} for algorithm {1}.", str2.toString(), str));
        }
        return new TlsFingerprint(str, str2.toString());
    }

    public static TlsCertificate generateCertificate() {
        return generateCertificate(getDefaultKeyType());
    }

    public static TlsCertificate generateCertificate(String str, String str2) {
        return generateCertificate(str, str2, getDefaultKeyType());
    }

    public static TlsCertificate generateCertificate(String str, String str2, Date date) {
        return generateCertificate(str, str2, date, getDefaultKeyType());
    }

    public static TlsCertificate generateCertificate(String str, String str2, Date date, AsymmetricKey asymmetricKey) {
        return (TlsCertificate) Certificate.generateCertificate(str, str2, date, asymmetricKey, new TlsCertificate());
    }

    public static TlsCertificate generateCertificate(String str, String str2, Date date, AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(str, str2, date, AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static TlsCertificate generateCertificate(String str, String str2, AsymmetricKey asymmetricKey) {
        return (TlsCertificate) Certificate.generateCertificate(str, str2, asymmetricKey, new TlsCertificate());
    }

    public static TlsCertificate generateCertificate(String str, String str2, AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(str, str2, AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static TlsCertificate generateCertificate(AsymmetricKey asymmetricKey) {
        return (TlsCertificate) Certificate.generateCertificate(asymmetricKey, new TlsCertificate());
    }

    public static TlsCertificate generateCertificate(AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static TlsCertificate generateCertificate(String str) {
        return generateCertificate(str, getDefaultKeyType());
    }

    public static TlsCertificate generateCertificate(String str, AsymmetricKey asymmetricKey) {
        return (TlsCertificate) Certificate.generateCertificate(str, asymmetricKey, new TlsCertificate());
    }

    public static TlsCertificate generateCertificate(String str, AsymmetricKeyType asymmetricKeyType) {
        return generateCertificate(str, AsymmetricKey.createKey(asymmetricKeyType));
    }

    public static TlsCertificate generateCertificateFromOldCertificate(TlsCertificate tlsCertificate) {
        return (TlsCertificate) Certificate.generateCertificateFromOldCertificate(tlsCertificate, new TlsCertificate());
    }

    public static TlsCertificate generateCertificateFromOldCertificate(TlsCertificate tlsCertificate, Date date) {
        return (TlsCertificate) Certificate.generateCertificateFromOldCertificate(tlsCertificate, date, new TlsCertificate());
    }

    public static AsymmetricKeyType getDefaultKeyType() {
        return __defaultKeyType;
    }

    public static TlsCertificate parseBytes(byte[] bArr) {
        return (TlsCertificate) Certificate.parseBytes(bArr, new TlsCertificate());
    }

    public static void setDefaultKeyType(AsymmetricKeyType asymmetricKeyType) {
        __defaultKeyType = asymmetricKeyType;
    }
}
