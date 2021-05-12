package fm.liveswitch;

import java.util.ArrayList;

class X509ECCurve {
    private byte[] _a;
    private byte[] _b;
    private byte[] _seed;

    public static X509ECCurve fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509ECCurve x509ECCurve = new X509ECCurve();
        x509ECCurve.setA(((Asn1OctetString) asn1Sequence.getValues()[0]).getValueBytes());
        x509ECCurve.setB(((Asn1OctetString) asn1Sequence.getValues()[1]).getValueBytes());
        if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) > 2) {
            x509ECCurve.setSeed(((Asn1BitString) asn1Sequence.getValues()[2]).getValueBytes());
        }
        return x509ECCurve;
    }

    public byte[] getA() {
        return this._a;
    }

    public byte[] getB() {
        return this._b;
    }

    public byte[] getSeed() {
        return this._seed;
    }

    public void setA(byte[] bArr) {
        this._a = bArr;
    }

    public void setB(byte[] bArr) {
        this._b = bArr;
    }

    public void setSeed(byte[] bArr) {
        this._seed = bArr;
    }

    public Asn1Any toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1OctetString(getA()));
        arrayList.add(new Asn1OctetString(getB()));
        if (getSeed() != null) {
            arrayList.add(new Asn1BitString(getSeed()));
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }
}
