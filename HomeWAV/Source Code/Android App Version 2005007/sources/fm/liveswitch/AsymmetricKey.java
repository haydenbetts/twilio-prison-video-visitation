package fm.liveswitch;

public abstract class AsymmetricKey {
    /* access modifiers changed from: package-private */
    public abstract X509AlgorithmIdentifier getPreferredSignatureAlgorithm();

    public abstract AsymmetricKeyType getType();

    public abstract boolean hasPrivate();

    public abstract boolean hasPublic();

    /* access modifiers changed from: package-private */
    public abstract AsymmetricKey regenerate();

    /* access modifiers changed from: package-private */
    public abstract byte[] sign(byte[] bArr, X509AlgorithmIdentifier x509AlgorithmIdentifier);

    /* access modifiers changed from: package-private */
    public abstract X509SubjectPublicKeyInfo toSubjectPublicKeyInfo();

    /* access modifiers changed from: package-private */
    public abstract boolean verify(byte[] bArr, byte[] bArr2, X509AlgorithmIdentifier x509AlgorithmIdentifier);

    protected AsymmetricKey() {
    }

    public static AsymmetricKey createKey(AsymmetricKeyType asymmetricKeyType) {
        if (asymmetricKeyType == AsymmetricKeyType.Ecdsa) {
            return EcdsaCrypto.createKey();
        }
        return RsaCrypto.createKey();
    }

    static AsymmetricKey fromSubjectPublicKeyInfo(X509SubjectPublicKeyInfo x509SubjectPublicKeyInfo) {
        X509ECParameters fromAsn1;
        if (Asn1ObjectIdentifier.areEqual(x509SubjectPublicKeyInfo.getAlgorithm().getAlgorithm(), X509PublicKeyAlgorithm.getRsaEncryption())) {
            X509RsaPublicKey fromAsn12 = X509RsaPublicKey.fromAsn1(Asn1Any.parseBytes(x509SubjectPublicKeyInfo.getSubjectPublicKey()));
            if (fromAsn12 == null) {
                return null;
            }
            RsaKey rsaKey = new RsaKey();
            rsaKey.setModulus(fromAsn12.getModulus());
            rsaKey.setPublicExponent(fromAsn12.getExponent());
            return rsaKey;
        } else if (Asn1ObjectIdentifier.areEqual(x509SubjectPublicKeyInfo.getAlgorithm().getAlgorithm(), X509PublicKeyAlgorithm.getEcPublicKey())) {
            X509ECPoint fromAsn13 = X509ECPoint.fromAsn1(new Asn1OctetString(x509SubjectPublicKeyInfo.getSubjectPublicKey()));
            if (fromAsn13 == null || (fromAsn1 = X509ECParameters.fromAsn1(Asn1Any.parseBytes(x509SubjectPublicKeyInfo.getAlgorithm().getParameters()))) == null || fromAsn1.getNamedCurve() == null) {
                return null;
            }
            EcdsaKey ecdsaKey = new EcdsaKey();
            ecdsaKey.setNamedCurve(X509ECNamedCurve.toEnum(fromAsn1.getNamedCurve()));
            ecdsaKey.setPublicKey(fromAsn13.getValue());
            return ecdsaKey;
        } else {
            throw new RuntimeException(new Exception("Public key algorithm is not supported."));
        }
    }
}
