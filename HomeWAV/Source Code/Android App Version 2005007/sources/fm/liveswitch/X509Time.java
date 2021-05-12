package fm.liveswitch;

import java.util.Date;

class X509Time {
    private X509TimeType _type;
    private Date _value;

    public static X509Time fromAsn1(Asn1Any asn1Any) {
        Asn1UtcTime asn1UtcTime = (Asn1UtcTime) Global.tryCast(asn1Any, Asn1UtcTime.class);
        if (asn1UtcTime != null) {
            return new X509Time(asn1UtcTime.getValue(), X509TimeType.Utc);
        }
        Asn1GeneralizedTime asn1GeneralizedTime = (Asn1GeneralizedTime) Global.tryCast(asn1Any, Asn1GeneralizedTime.class);
        if (asn1GeneralizedTime != null) {
            return new X509Time(asn1GeneralizedTime.getValue(), X509TimeType.Generalized);
        }
        return null;
    }

    public X509TimeType getType() {
        return this._type;
    }

    public Date getValue() {
        return this._value;
    }

    public void setType(X509TimeType x509TimeType) {
        this._type = x509TimeType;
    }

    public void setValue(Date date) {
        this._value = date;
    }

    public Asn1Any toAsn1() {
        if (Global.equals(getType(), X509TimeType.Utc)) {
            return new Asn1UtcTime(getValue());
        }
        if (Global.equals(getType(), X509TimeType.Generalized)) {
            return new Asn1GeneralizedTime(getValue());
        }
        return null;
    }

    public X509Time() {
        this(DateExtensions.getUtcNow(), X509TimeType.Utc);
    }

    public X509Time(Date date, X509TimeType x509TimeType) {
        this._value = new Date();
        setValue(date);
        setType(x509TimeType);
    }
}
