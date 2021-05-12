package fm.liveswitch;

class X509Certificate {
    private byte[] _signature;
    private X509AlgorithmIdentifier _signatureAlgorithm;
    private X509TbsCertificate _tbsCertificate;

    public static X509Certificate fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) != 3) {
            return null;
        }
        X509Certificate x509Certificate = new X509Certificate();
        x509Certificate.setTbsCertificate(X509TbsCertificate.fromAsn1(asn1Sequence.getValues()[0]));
        x509Certificate.setSignatureAlgorithm(X509AlgorithmIdentifier.fromAsn1(asn1Sequence.getValues()[1]));
        x509Certificate.setSignature(((Asn1BitString) asn1Sequence.getValues()[2]).getValueBytes());
        return x509Certificate;
    }

    public byte[] getSignature() {
        return this._signature;
    }

    public X509AlgorithmIdentifier getSignatureAlgorithm() {
        return this._signatureAlgorithm;
    }

    public X509TbsCertificate getTbsCertificate() {
        return this._tbsCertificate;
    }

    public void setSignature(byte[] bArr) {
        this._signature = bArr;
    }

    public void setSignatureAlgorithm(X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        this._signatureAlgorithm = x509AlgorithmIdentifier;
    }

    public void setTbsCertificate(X509TbsCertificate x509TbsCertificate) {
        this._tbsCertificate = x509TbsCertificate;
    }

    public Asn1Sequence toAsn1() {
        return new Asn1Sequence(new Asn1Any[]{getTbsCertificate().toAsn1(), getSignatureAlgorithm().toAsn1(), new Asn1BitString(getSignature())});
    }
}
