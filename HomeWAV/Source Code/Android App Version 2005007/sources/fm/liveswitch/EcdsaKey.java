package fm.liveswitch;

import java.util.ArrayList;

public class EcdsaKey extends AsymmetricKey {
    private static String _privateKeyFooter = "-----END PRIVATE KEY-----";
    private static String _privateKeyHeader = "-----BEGIN PRIVATE KEY-----";
    private static String _publicKeyFooter = "-----END PUBLIC KEY-----";
    private static String _publicKeyHeader = "-----BEGIN PUBLIC KEY-----";
    private EcdsaNamedCurve _namedCurve;
    private byte[] _privateKey;
    private byte[] _publicKey;

    public byte[] getBytes() {
        if (hasPrivate()) {
            X509PrivateKeyInfo x509PrivateKeyInfo = new X509PrivateKeyInfo();
            x509PrivateKeyInfo.setPrivateKeyAlgorithm(X509AlgorithmIdentifier.getEcdsa(new X509ECParameters(X509ECNamedCurve.fromEnum(getNamedCurve()))));
            x509PrivateKeyInfo.setPrivateKey(new X509ECPrivateKey(getPrivateKey(), new X509ECParameters(X509ECNamedCurve.fromEnum(getNamedCurve())), getPublicKey()).toAsn1().getBytes());
            return x509PrivateKeyInfo.toAsn1().getBytes();
        }
        X509PublicKeyInfo x509PublicKeyInfo = new X509PublicKeyInfo();
        x509PublicKeyInfo.setAlgorithm(X509AlgorithmIdentifier.getEcdsa(new X509ECParameters(X509ECNamedCurve.fromEnum(getNamedCurve()))));
        x509PublicKeyInfo.setPublicKey(new X509ECPoint(getPublicKey()).compress());
        return x509PublicKeyInfo.toAsn1().getBytes();
    }

    public EcdsaNamedCurve getNamedCurve() {
        return this._namedCurve;
    }

    /* access modifiers changed from: package-private */
    public X509AlgorithmIdentifier getPreferredSignatureAlgorithm() {
        if (Global.equals(getNamedCurve(), EcdsaNamedCurve.P256)) {
            return X509AlgorithmIdentifier.getEcdsaWithSha256();
        }
        if (Global.equals(getNamedCurve(), EcdsaNamedCurve.P384)) {
            return X509AlgorithmIdentifier.getEcdsaWithSha384();
        }
        if (Global.equals(getNamedCurve(), EcdsaNamedCurve.P521)) {
            return X509AlgorithmIdentifier.getEcdsaWithSha512();
        }
        throw new RuntimeException(new Exception("Unsupported named curve."));
    }

    public byte[] getPrivateKey() {
        return this._privateKey;
    }

    public byte[] getPublicKey() {
        return this._publicKey;
    }

    public int getSize() {
        int length;
        byte[] privateKey = getPrivateKey();
        if (privateKey != null) {
            length = ArrayExtensions.getLength(privateKey);
        } else {
            byte[] publicKey = getPublicKey();
            if (publicKey == null) {
                return 0;
            }
            length = ArrayExtensions.getLength(publicKey) / 2;
        }
        return length * 8;
    }

    public String getString() {
        if (hasPrivate()) {
            return StringExtensions.concat((Object[]) new String[]{_privateKeyHeader, "\n", StringExtensions.join("\n", splitString(Base64.encode(getBytes()), 64)), "\n", _privateKeyFooter, "\n"});
        }
        return StringExtensions.concat((Object[]) new String[]{_publicKeyHeader, "\n", StringExtensions.join("\n", splitString(Base64.encode(getBytes()), 64)), "\n", _publicKeyFooter, "\n"});
    }

    public AsymmetricKeyType getType() {
        return AsymmetricKeyType.Ecdsa;
    }

    public boolean hasPrivate() {
        return hasPublic() && getPrivateKey() != null;
    }

    public boolean hasPublic() {
        return getPublicKey() != null;
    }

    public static EcdsaKey parseBuffer(DataBuffer dataBuffer, boolean z) {
        X509ECPrivateKey fromAsn1;
        Asn1Any parseBuffer = Asn1Any.parseBuffer(dataBuffer);
        if (parseBuffer == null) {
            return null;
        }
        if (z) {
            X509PrivateKeyInfo fromAsn12 = X509PrivateKeyInfo.fromAsn1(parseBuffer);
            if (fromAsn12 == null || (fromAsn1 = X509ECPrivateKey.fromAsn1(Asn1Any.parseBytes(fromAsn12.getPrivateKey()))) == null) {
                return null;
            }
            EcdsaKey ecdsaKey = new EcdsaKey();
            ecdsaKey.setPublicKey(fromAsn1.getPublicKey());
            ecdsaKey.setNamedCurve(X509ECNamedCurve.toEnum(X509ECParameters.fromAsn1(Asn1Any.parseBytes(fromAsn12.getPrivateKeyAlgorithm().getParameters())).getNamedCurve()));
            ecdsaKey.setPrivateKey(fromAsn1.getPrivateKey());
            return ecdsaKey;
        }
        X509PublicKeyInfo fromAsn13 = X509PublicKeyInfo.fromAsn1(parseBuffer);
        if (fromAsn13 == null) {
            return null;
        }
        EcdsaKey ecdsaKey2 = new EcdsaKey();
        ecdsaKey2.setNamedCurve(X509ECNamedCurve.toEnum(X509ECParameters.fromAsn1(Asn1Any.parseBytes(fromAsn13.getAlgorithm().getParameters())).getNamedCurve()));
        ecdsaKey2.setPublicKey(X509ECPoint.decompress(fromAsn13.getPublicKey()).getValue());
        return ecdsaKey2;
    }

    public static EcdsaKey parseBytes(byte[] bArr, boolean z) {
        return parseBuffer(DataBuffer.wrap(bArr), z);
    }

    public static boolean parseSignature(byte[] bArr, Holder<byte[]> holder, Holder<byte[]> holder2) {
        X509DsaSignature fromAsn1 = X509DsaSignature.fromAsn1(Asn1Any.parseBytes(bArr));
        if (fromAsn1 == null) {
            holder.setValue(null);
            holder2.setValue(null);
            return false;
        }
        holder.setValue(fromAsn1.getR());
        holder2.setValue(fromAsn1.getS());
        return true;
    }

    public static EcdsaKey parseString(String str) {
        String trim = str.replace("\n", "").replace("\r", "").trim();
        if (trim.startsWith(_privateKeyHeader) && trim.endsWith(_privateKeyFooter)) {
            return parseBytes(Base64.decode(StringExtensions.substring(trim, StringExtensions.getLength(_privateKeyHeader), (StringExtensions.getLength(trim) - StringExtensions.getLength(_privateKeyHeader)) - StringExtensions.getLength(_privateKeyFooter))), true);
        }
        if (!trim.startsWith(_publicKeyHeader) || !trim.endsWith(_publicKeyFooter)) {
            return null;
        }
        return parseBytes(Base64.decode(StringExtensions.substring(trim, StringExtensions.getLength(_publicKeyHeader), (StringExtensions.getLength(trim) - StringExtensions.getLength(_publicKeyHeader)) - StringExtensions.getLength(_publicKeyFooter))), false);
    }

    /* access modifiers changed from: package-private */
    public AsymmetricKey regenerate() {
        return EcdsaCrypto.createKey(getNamedCurve());
    }

    public void setNamedCurve(EcdsaNamedCurve ecdsaNamedCurve) {
        this._namedCurve = ecdsaNamedCurve;
    }

    public void setPrivateKey(byte[] bArr) {
        this._privateKey = bArr;
    }

    public void setPublicKey(byte[] bArr) {
        this._publicKey = bArr;
    }

    /* access modifiers changed from: package-private */
    public byte[] sign(byte[] bArr, X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        if ((Global.equals(getNamedCurve(), EcdsaNamedCurve.P256) && X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getEcdsaWithSha256())) || ((Global.equals(getNamedCurve(), EcdsaNamedCurve.P384) && X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getEcdsaWithSha384())) || (Global.equals(getNamedCurve(), EcdsaNamedCurve.P521) && X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getEcdsaWithSha512())))) {
            return EcdsaCrypto.sign(bArr, this);
        }
        throw new RuntimeException(new Exception("Unsupported signature algorithm."));
    }

    private String[] splitString(String str, int i) {
        ArrayList arrayList = new ArrayList();
        while (StringExtensions.getLength(str) > i) {
            arrayList.add(StringExtensions.substring(str, 0, i));
            str = str.substring(i);
        }
        if (StringExtensions.getLength(str) > 0) {
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public EcdsaKey toPublic() {
        EcdsaKey ecdsaKey = new EcdsaKey();
        ecdsaKey.setNamedCurve(getNamedCurve());
        ecdsaKey.setPublicKey(getPublicKey());
        return ecdsaKey;
    }

    /* access modifiers changed from: package-private */
    public X509SubjectPublicKeyInfo toSubjectPublicKeyInfo() {
        X509SubjectPublicKeyInfo x509SubjectPublicKeyInfo = new X509SubjectPublicKeyInfo();
        x509SubjectPublicKeyInfo.setAlgorithm(X509AlgorithmIdentifier.getEcdsa(new X509ECParameters(X509ECNamedCurve.fromEnum(getNamedCurve()))));
        x509SubjectPublicKeyInfo.setSubjectPublicKey(new X509ECPoint(getPublicKey()).toAsn1().getValueBytes());
        return x509SubjectPublicKeyInfo;
    }

    /* access modifiers changed from: package-private */
    public boolean verify(byte[] bArr, byte[] bArr2, X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        if ((Global.equals(getNamedCurve(), EcdsaNamedCurve.P256) && X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getEcdsaWithSha256())) || ((Global.equals(getNamedCurve(), EcdsaNamedCurve.P384) && X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getEcdsaWithSha384())) || (Global.equals(getNamedCurve(), EcdsaNamedCurve.P521) && X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getEcdsaWithSha512())))) {
            return EcdsaCrypto.verify(bArr, bArr2, this);
        }
        throw new RuntimeException(new Exception("Unsupported signature algorithm."));
    }
}
