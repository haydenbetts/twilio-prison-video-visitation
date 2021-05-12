package fm.liveswitch;

import java.util.ArrayList;

class X509TbsCertificate {
    private X509Extensions _extensions;
    private X501Name _issuer;
    private byte[] _issuerUniqueId;
    private boolean _missingVersion;
    private byte[] _serialNumber;
    private X509AlgorithmIdentifier _signatureAlgorithm;
    private Asn1Any _sourceAsn;
    private X501Name _subject;
    private X509SubjectPublicKeyInfo _subjectPublicKeyInfo;
    private byte[] _subjectUniqueId;
    private X509Validity _validity;
    private int _version;

    public static int getVersion1() {
        return 0;
    }

    public static int getVersion2() {
        return 1;
    }

    public static int getVersion3() {
        return 2;
    }

    public static X509TbsCertificate fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 6) {
            return null;
        }
        X509TbsCertificate x509TbsCertificate = new X509TbsCertificate();
        int i = 0;
        if (!(asn1Sequence.getValues()[0] instanceof Asn1Unknown) || !(asn1Sequence.getValues()[1] instanceof Asn1Integer)) {
            x509TbsCertificate.setMissingVersion(true);
        } else {
            IntegerHolder integerHolder = new IntegerHolder(-1);
            Asn1Any unwrap = Asn1Unknown.unwrap(asn1Sequence.getValues()[0], integerHolder);
            integerHolder.getValue();
            x509TbsCertificate.setVersion((int) ((Asn1Integer) unwrap).getLongValue());
            i = 1;
        }
        int i2 = i + 1;
        x509TbsCertificate.setSerialNumber(((Asn1Integer) asn1Sequence.getValues()[i]).getValue());
        int i3 = i2 + 1;
        x509TbsCertificate.setSignatureAlgorithm(X509AlgorithmIdentifier.fromAsn1(asn1Sequence.getValues()[i2]));
        int i4 = i3 + 1;
        x509TbsCertificate.setIssuer(X501Name.fromAsn1(asn1Sequence.getValues()[i3]));
        int i5 = i4 + 1;
        x509TbsCertificate.setValidity(X509Validity.fromAsn1(asn1Sequence.getValues()[i4]));
        int i6 = i5 + 1;
        x509TbsCertificate.setSubject(X501Name.fromAsn1(asn1Sequence.getValues()[i5]));
        int i7 = i6 + 1;
        x509TbsCertificate.setSubjectPublicKeyInfo(X509SubjectPublicKeyInfo.fromAsn1(asn1Sequence.getValues()[i6]));
        while (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) > i7) {
            IntegerHolder integerHolder2 = new IntegerHolder(-1);
            int i8 = i7 + 1;
            Asn1Any unwrap2 = Asn1Unknown.unwrap(asn1Sequence.getValues()[i7], integerHolder2);
            int value = integerHolder2.getValue();
            if (value == 1) {
                x509TbsCertificate.setIssuerUniqueId(((Asn1BitString) unwrap2).getValueBytes());
            } else if (value == 2) {
                x509TbsCertificate.setSubjectUniqueId(((Asn1BitString) unwrap2).getValueBytes());
            } else if (value == 3) {
                x509TbsCertificate.setExtensions(X509Extensions.fromAsn1(unwrap2));
            }
            i7 = i8;
        }
        x509TbsCertificate.setSourceAsn(asn1Any);
        return x509TbsCertificate;
    }

    public X509Extensions getExtensions() {
        return this._extensions;
    }

    public X501Name getIssuer() {
        return this._issuer;
    }

    public byte[] getIssuerUniqueId() {
        return this._issuerUniqueId;
    }

    public boolean getMissingVersion() {
        return this._missingVersion;
    }

    public byte[] getSerialNumber() {
        return this._serialNumber;
    }

    public X509AlgorithmIdentifier getSignatureAlgorithm() {
        return this._signatureAlgorithm;
    }

    public Asn1Any getSourceAsn() {
        return this._sourceAsn;
    }

    public X501Name getSubject() {
        return this._subject;
    }

    public X509SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this._subjectPublicKeyInfo;
    }

    public byte[] getSubjectUniqueId() {
        return this._subjectUniqueId;
    }

    public X509Validity getValidity() {
        return this._validity;
    }

    public int getVersion() {
        return this._version;
    }

    public void setExtensions(X509Extensions x509Extensions) {
        this._extensions = x509Extensions;
    }

    public void setIssuer(X501Name x501Name) {
        this._issuer = x501Name;
    }

    public void setIssuerUniqueId(byte[] bArr) {
        this._issuerUniqueId = bArr;
    }

    private void setMissingVersion(boolean z) {
        this._missingVersion = z;
    }

    public void setSerialNumber(byte[] bArr) {
        this._serialNumber = bArr;
    }

    public void setSignatureAlgorithm(X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        this._signatureAlgorithm = x509AlgorithmIdentifier;
    }

    private void setSourceAsn(Asn1Any asn1Any) {
        this._sourceAsn = asn1Any;
    }

    public void setSubject(X501Name x501Name) {
        this._subject = x501Name;
    }

    public void setSubjectPublicKeyInfo(X509SubjectPublicKeyInfo x509SubjectPublicKeyInfo) {
        this._subjectPublicKeyInfo = x509SubjectPublicKeyInfo;
    }

    public void setSubjectUniqueId(byte[] bArr) {
        this._subjectUniqueId = bArr;
    }

    public void setValidity(X509Validity x509Validity) {
        this._validity = x509Validity;
    }

    public void setVersion(int i) {
        this._version = i;
    }

    public Asn1Sequence toAsn1() {
        if (!getMissingVersion()) {
            if (!(getIssuerUniqueId() == null && getSubjectUniqueId() == null) && getVersion() < getVersion2()) {
                Log.error("Version must be at least v2 if IssuerUniqueId or SubjectUniqueId are not null.");
                return null;
            } else if (getExtensions() != null && getVersion() < getVersion3()) {
                Log.error("Version must be at least v3 if Extensions is not null.");
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (!getMissingVersion()) {
            arrayList.add(Asn1Explicit.wrap(0, new Asn1Integer((long) getVersion())));
        }
        arrayList.add(new Asn1Integer(getSerialNumber()));
        arrayList.add(getSignatureAlgorithm().toAsn1());
        arrayList.add(getIssuer().toAsn1());
        arrayList.add(getValidity().toAsn1());
        arrayList.add(getSubject().toAsn1());
        arrayList.add(getSubjectPublicKeyInfo().toAsn1());
        if (getIssuerUniqueId() != null) {
            arrayList.add(Asn1Implicit.wrap(1, new Asn1BitString(getIssuerUniqueId())));
        } else if (getSubjectUniqueId() != null) {
            arrayList.add(Asn1Implicit.wrap(2, new Asn1BitString(getSubjectUniqueId())));
        } else if (getExtensions() != null) {
            arrayList.add(Asn1Explicit.wrap(3, getExtensions().toAsn1()));
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }
}
