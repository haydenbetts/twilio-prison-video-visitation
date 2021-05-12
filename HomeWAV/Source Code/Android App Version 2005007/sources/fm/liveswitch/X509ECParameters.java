package fm.liveswitch;

import java.util.ArrayList;

class X509ECParameters {
    private X509ECPoint _basePoint;
    private NullableLong _cofactor = new NullableLong();
    private X509ECCurve _curve;
    private X509ECFieldId _fieldId;
    private long[] _namedCurve;
    private long _order;

    public static X509ECParameters fromAsn1(Asn1Any asn1Any) {
        Asn1ObjectIdentifier asn1ObjectIdentifier = (Asn1ObjectIdentifier) Global.tryCast(asn1Any, Asn1ObjectIdentifier.class);
        if (asn1ObjectIdentifier != null) {
            return new X509ECParameters(asn1ObjectIdentifier.getValues());
        }
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null) {
            return new X509ECParameters();
        }
        if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 5 || ((Asn1Integer) asn1Sequence.getValues()[0]).getLongValue() != 1) {
            return null;
        }
        X509ECParameters x509ECParameters = new X509ECParameters(X509ECFieldId.fromAsn1(asn1Sequence.getValues()[1]), X509ECCurve.fromAsn1(asn1Sequence.getValues()[2]), X509ECPoint.fromAsn1(asn1Sequence.getValues()[3]), ((Asn1Integer) asn1Sequence.getValues()[4]).getLongValue());
        if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) > 5) {
            x509ECParameters.setCofactor(new NullableLong(((Asn1Integer) asn1Sequence.getValues()[5]).getLongValue()));
        }
        return x509ECParameters;
    }

    public X509ECPoint getBasePoint() {
        return this._basePoint;
    }

    public NullableLong getCofactor() {
        return this._cofactor;
    }

    public X509ECCurve getCurve() {
        return this._curve;
    }

    public X509ECFieldId getFieldId() {
        return this._fieldId;
    }

    public long[] getNamedCurve() {
        return this._namedCurve;
    }

    public long getOrder() {
        return this._order;
    }

    public void setBasePoint(X509ECPoint x509ECPoint) {
        this._basePoint = x509ECPoint;
    }

    public void setCofactor(NullableLong nullableLong) {
        this._cofactor = nullableLong;
    }

    public void setCurve(X509ECCurve x509ECCurve) {
        this._curve = x509ECCurve;
    }

    public void setFieldId(X509ECFieldId x509ECFieldId) {
        this._fieldId = x509ECFieldId;
    }

    public void setNamedCurve(long[] jArr) {
        this._namedCurve = jArr;
    }

    public void setOrder(long j) {
        this._order = j;
    }

    public Asn1Any toAsn1() {
        if (getNamedCurve() != null) {
            return new Asn1ObjectIdentifier(getNamedCurve());
        }
        if (getCurve() == null) {
            return new Asn1Null();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1Integer(1));
        arrayList.add(getFieldId().toAsn1());
        arrayList.add(getCurve().toAsn1());
        arrayList.add(getBasePoint().toAsn1());
        arrayList.add(new Asn1Integer(getOrder()));
        if (getCofactor().getHasValue()) {
            arrayList.add(new Asn1Integer(getCofactor().getValue()));
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X509ECParameters() {
    }

    public X509ECParameters(X509ECFieldId x509ECFieldId, X509ECCurve x509ECCurve, X509ECPoint x509ECPoint, long j) {
        setFieldId(x509ECFieldId);
        setCurve(x509ECCurve);
        setBasePoint(x509ECPoint);
        setOrder(j);
    }

    public X509ECParameters(long[] jArr) {
        setNamedCurve(jArr);
    }
}
