package fm.liveswitch;

import java.util.ArrayList;

class X509ECFieldId {
    private long[] _fieldType;
    private Object _parameters;

    public static X509ECFieldId fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509ECFieldId x509ECFieldId = new X509ECFieldId();
        x509ECFieldId.setFieldType(((Asn1ObjectIdentifier) asn1Sequence.getValues()[0]).getValues());
        return x509ECFieldId;
    }

    public long[] getFieldType() {
        return this._fieldType;
    }

    public Object getParameters() {
        return this._parameters;
    }

    public void setFieldType(long[] jArr) {
        this._fieldType = jArr;
    }

    public void setParameters(Object obj) {
        this._parameters = obj;
    }

    public Asn1Any toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1ObjectIdentifier(getFieldType()));
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }
}
