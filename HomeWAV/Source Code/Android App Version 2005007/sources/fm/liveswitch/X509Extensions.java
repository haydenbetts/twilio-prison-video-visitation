package fm.liveswitch;

import java.util.ArrayList;

class X509Extensions {
    private X509Extension[] _values;

    public static X509Extensions fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null) {
            return null;
        }
        X509Extensions x509Extensions = new X509Extensions();
        ArrayList arrayList = new ArrayList();
        for (Asn1Any fromAsn1 : asn1Sequence.getValues()) {
            arrayList.add(X509Extension.fromAsn1(fromAsn1));
        }
        x509Extensions.setValues((X509Extension[]) arrayList.toArray(new X509Extension[0]));
        return x509Extensions;
    }

    public X509Extension[] getValues() {
        return this._values;
    }

    public void setValues(X509Extension[] x509ExtensionArr) {
        this._values = x509ExtensionArr;
    }

    public Asn1Sequence toAsn1() {
        ArrayList arrayList = new ArrayList();
        for (X509Extension asn1 : getValues()) {
            arrayList.add(asn1.toAsn1());
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X509Extensions() {
    }

    public X509Extensions(X509Extension[] x509ExtensionArr) {
        setValues(x509ExtensionArr);
    }
}
