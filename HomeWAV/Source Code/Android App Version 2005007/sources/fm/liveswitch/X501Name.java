package fm.liveswitch;

import java.util.ArrayList;

class X501Name {
    private X501RelativeDistinguishedName[] _rdnSequence;

    public static X501Name fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null) {
            return null;
        }
        X501Name x501Name = new X501Name();
        ArrayList arrayList = new ArrayList();
        for (Asn1Any fromAsn1 : asn1Sequence.getValues()) {
            arrayList.add(X501RelativeDistinguishedName.fromAsn1(fromAsn1));
        }
        x501Name.setRdnSequence((X501RelativeDistinguishedName[]) arrayList.toArray(new X501RelativeDistinguishedName[0]));
        return x501Name;
    }

    public X501Attribute getAttribute(long[] jArr) {
        if (getRdnSequence() == null) {
            return null;
        }
        for (X501RelativeDistinguishedName attribute : getRdnSequence()) {
            X501Attribute attribute2 = attribute.getAttribute(jArr);
            if (attribute2 != null) {
                return attribute2;
            }
        }
        return null;
    }

    public X501RelativeDistinguishedName[] getRdnSequence() {
        return this._rdnSequence;
    }

    public void setRdnSequence(X501RelativeDistinguishedName[] x501RelativeDistinguishedNameArr) {
        this._rdnSequence = x501RelativeDistinguishedNameArr;
    }

    public Asn1Any toAsn1() {
        ArrayList arrayList = new ArrayList();
        for (X501RelativeDistinguishedName asn1 : getRdnSequence()) {
            arrayList.add(asn1.toAsn1());
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X501Name() {
    }

    public X501Name(X501RelativeDistinguishedName[] x501RelativeDistinguishedNameArr) {
        setRdnSequence(x501RelativeDistinguishedNameArr);
    }
}
