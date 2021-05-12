package fm.liveswitch;

import java.util.ArrayList;

class X501RelativeDistinguishedName {
    private X501Attribute[] _attributes;

    public static X501RelativeDistinguishedName fromAsn1(Asn1Any asn1Any) {
        Asn1Set asn1Set = (Asn1Set) Global.tryCast(asn1Any, Asn1Set.class);
        if (asn1Set == null) {
            return null;
        }
        X501RelativeDistinguishedName x501RelativeDistinguishedName = new X501RelativeDistinguishedName();
        ArrayList arrayList = new ArrayList();
        for (Asn1Any fromAsn1 : asn1Set.getValues()) {
            arrayList.add(X501Attribute.fromAsn1(fromAsn1));
        }
        x501RelativeDistinguishedName.setAttributes((X501Attribute[]) arrayList.toArray(new X501Attribute[0]));
        return x501RelativeDistinguishedName;
    }

    public X501Attribute getAttribute(long[] jArr) {
        if (getAttributes() == null) {
            return null;
        }
        for (X501Attribute x501Attribute : getAttributes()) {
            if (X501AttributeType.areEqual(jArr, x501Attribute.getAttributeType())) {
                return x501Attribute;
            }
        }
        return null;
    }

    public X501Attribute[] getAttributes() {
        return this._attributes;
    }

    public void setAttributes(X501Attribute[] x501AttributeArr) {
        this._attributes = x501AttributeArr;
    }

    public Asn1Set toAsn1() {
        ArrayList arrayList = new ArrayList();
        for (X501Attribute asn1 : getAttributes()) {
            arrayList.add(asn1.toAsn1());
        }
        return new Asn1Set((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X501RelativeDistinguishedName() {
    }

    public X501RelativeDistinguishedName(X501Attribute[] x501AttributeArr) {
        setAttributes(x501AttributeArr);
    }
}
