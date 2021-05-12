package fm.liveswitch;

class X509SubjectPublicKeyInfo {
    private X509AlgorithmIdentifier _algorithm;
    private byte[] _subjectPublicKey;

    public static X509SubjectPublicKeyInfo fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509SubjectPublicKeyInfo x509SubjectPublicKeyInfo = new X509SubjectPublicKeyInfo();
        x509SubjectPublicKeyInfo.setAlgorithm(X509AlgorithmIdentifier.fromAsn1(asn1Sequence.getValues()[0]));
        x509SubjectPublicKeyInfo.setSubjectPublicKey(((Asn1BitString) asn1Sequence.getValues()[1]).getValueBytes());
        return x509SubjectPublicKeyInfo;
    }

    public X509AlgorithmIdentifier getAlgorithm() {
        return this._algorithm;
    }

    public byte[] getSubjectPublicKey() {
        return this._subjectPublicKey;
    }

    public void setAlgorithm(X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        this._algorithm = x509AlgorithmIdentifier;
    }

    public void setSubjectPublicKey(byte[] bArr) {
        this._subjectPublicKey = bArr;
    }

    public Asn1Sequence toAsn1() {
        return new Asn1Sequence(new Asn1Any[]{getAlgorithm().toAsn1(), new Asn1BitString(getSubjectPublicKey())});
    }
}
