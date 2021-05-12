package fm.liveswitch;

public class RsaKey extends AsymmetricKey {
    private byte[] _coefficient;
    private byte[] _exponent1;
    private byte[] _exponent2;
    private byte[] _modulus;
    private byte[] _prime1;
    private byte[] _prime2;
    private byte[] _privateExponent;
    private byte[] _publicExponent;

    public byte[] getBytes() {
        if (hasPrivate()) {
            X509RsaPrivateKey x509RsaPrivateKey = new X509RsaPrivateKey();
            x509RsaPrivateKey.setModulus(getModulus());
            x509RsaPrivateKey.setPublicExponent(getPublicExponent());
            x509RsaPrivateKey.setPrivateExponent(getPrivateExponent());
            x509RsaPrivateKey.setPrime1(getPrime1());
            x509RsaPrivateKey.setPrime2(getPrime2());
            x509RsaPrivateKey.setExponent1(getExponent1());
            x509RsaPrivateKey.setExponent2(getExponent2());
            x509RsaPrivateKey.setCoefficient(getCoefficient());
            return x509RsaPrivateKey.toAsn1().getBytes();
        }
        X509RsaPublicKey x509RsaPublicKey = new X509RsaPublicKey();
        x509RsaPublicKey.setModulus(getModulus());
        x509RsaPublicKey.setExponent(getPublicExponent());
        return x509RsaPublicKey.toAsn1().getBytes();
    }

    public byte[] getCoefficient() {
        return this._coefficient;
    }

    public byte[] getExponent1() {
        return this._exponent1;
    }

    public byte[] getExponent2() {
        return this._exponent2;
    }

    public byte[] getModulus() {
        return this._modulus;
    }

    /* access modifiers changed from: package-private */
    public X509AlgorithmIdentifier getPreferredSignatureAlgorithm() {
        return X509AlgorithmIdentifier.getSha1WithRsaEncryption();
    }

    public byte[] getPrime1() {
        return this._prime1;
    }

    public byte[] getPrime2() {
        return this._prime2;
    }

    public byte[] getPrivateExponent() {
        return this._privateExponent;
    }

    public byte[] getPublicExponent() {
        return this._publicExponent;
    }

    public int getSize() {
        return ArrayExtensions.getLength(getModulus()) * 8;
    }

    public AsymmetricKeyType getType() {
        return AsymmetricKeyType.Rsa;
    }

    public boolean hasPrivate() {
        return (!hasPublic() || getPrivateExponent() == null || getPrime1() == null || getPrime2() == null || getExponent1() == null || getExponent2() == null || getCoefficient() == null) ? false : true;
    }

    public boolean hasPublic() {
        return (getModulus() == null || getPublicExponent() == null) ? false : true;
    }

    public static RsaKey parseBuffer(DataBuffer dataBuffer) {
        X509RsaPublicKey fromAsn1;
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(Asn1Any.parseBuffer(dataBuffer), Asn1Sequence.class);
        if (asn1Sequence == null) {
            return null;
        }
        if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) >= 8) {
            X509RsaPrivateKey fromAsn12 = X509RsaPrivateKey.fromAsn1(asn1Sequence);
            if (fromAsn12 == null) {
                return null;
            }
            RsaKey rsaKey = new RsaKey();
            rsaKey.setModulus(fromAsn12.getModulus());
            rsaKey.setPublicExponent(fromAsn12.getPublicExponent());
            rsaKey.setPrivateExponent(fromAsn12.getPrivateExponent());
            rsaKey.setPrime1(fromAsn12.getPrime1());
            rsaKey.setPrime2(fromAsn12.getPrime2());
            rsaKey.setExponent1(fromAsn12.getExponent1());
            rsaKey.setExponent2(fromAsn12.getExponent2());
            rsaKey.setCoefficient(fromAsn12.getCoefficient());
            return rsaKey;
        } else if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2 || (fromAsn1 = X509RsaPublicKey.fromAsn1(asn1Sequence)) == null) {
            return null;
        } else {
            RsaKey rsaKey2 = new RsaKey();
            rsaKey2.setModulus(fromAsn1.getModulus());
            rsaKey2.setPublicExponent(fromAsn1.getExponent());
            return rsaKey2;
        }
    }

    public static RsaKey parseBytes(byte[] bArr) {
        return parseBuffer(DataBuffer.wrap(bArr));
    }

    /* access modifiers changed from: package-private */
    public AsymmetricKey regenerate() {
        return RsaCrypto.createKey(getSize());
    }

    public void setCoefficient(byte[] bArr) {
        this._coefficient = bArr;
    }

    public void setExponent1(byte[] bArr) {
        this._exponent1 = bArr;
    }

    public void setExponent2(byte[] bArr) {
        this._exponent2 = bArr;
    }

    public void setModulus(byte[] bArr) {
        this._modulus = bArr;
    }

    public void setPrime1(byte[] bArr) {
        this._prime1 = bArr;
    }

    public void setPrime2(byte[] bArr) {
        this._prime2 = bArr;
    }

    public void setPrivateExponent(byte[] bArr) {
        this._privateExponent = bArr;
    }

    public void setPublicExponent(byte[] bArr) {
        this._publicExponent = bArr;
    }

    /* access modifiers changed from: package-private */
    public byte[] sign(byte[] bArr, X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        if (X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getSha1WithRsaEncryption())) {
            return RsaCrypto.signSha1(bArr, this);
        }
        if (X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getSha256WithRsaEncryption())) {
            return RsaCrypto.signSha256(bArr, this);
        }
        throw new RuntimeException(new Exception("Unsupported signature algorithm."));
    }

    /* access modifiers changed from: package-private */
    public X509SubjectPublicKeyInfo toSubjectPublicKeyInfo() {
        X509SubjectPublicKeyInfo x509SubjectPublicKeyInfo = new X509SubjectPublicKeyInfo();
        x509SubjectPublicKeyInfo.setAlgorithm(X509AlgorithmIdentifier.getRsa());
        x509SubjectPublicKeyInfo.setSubjectPublicKey(new X509RsaPublicKey(getModulus(), getPublicExponent()).toAsn1().getBytes());
        return x509SubjectPublicKeyInfo;
    }

    /* access modifiers changed from: package-private */
    public boolean verify(byte[] bArr, byte[] bArr2, X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        if (X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getSha1WithRsaEncryption())) {
            return RsaCrypto.verifySha1(bArr, bArr2, this);
        }
        if (X509AlgorithmIdentifier.algorithmsAreEqual(x509AlgorithmIdentifier, X509AlgorithmIdentifier.getSha256WithRsaEncryption())) {
            return RsaCrypto.verifySha256(bArr, bArr2, this);
        }
        throw new RuntimeException(new Exception("Unsupported signature algorithm."));
    }
}
