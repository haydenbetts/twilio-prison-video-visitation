package fm.liveswitch;

class X509Validity {
    private X509Time _notAfter;
    private X509Time _notBefore;

    public static X509Validity fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509Validity x509Validity = new X509Validity();
        x509Validity.setNotBefore(X509Time.fromAsn1(asn1Sequence.getValues()[0]));
        x509Validity.setNotAfter(X509Time.fromAsn1(asn1Sequence.getValues()[1]));
        return x509Validity;
    }

    public X509Time getNotAfter() {
        return this._notAfter;
    }

    public X509Time getNotBefore() {
        return this._notBefore;
    }

    public void setNotAfter(X509Time x509Time) {
        this._notAfter = x509Time;
    }

    public void setNotBefore(X509Time x509Time) {
        this._notBefore = x509Time;
    }

    public Asn1Sequence toAsn1() {
        return new Asn1Sequence(new Asn1Any[]{getNotBefore().toAsn1(), getNotAfter().toAsn1()});
    }

    public X509Validity() {
    }

    public X509Validity(X509Time x509Time, X509Time x509Time2) {
        setNotBefore(x509Time);
        setNotAfter(x509Time2);
    }
}
