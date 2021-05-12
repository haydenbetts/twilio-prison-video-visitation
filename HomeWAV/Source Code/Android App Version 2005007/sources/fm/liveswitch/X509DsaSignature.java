package fm.liveswitch;

import java.util.ArrayList;

class X509DsaSignature {
    private byte[] _r;
    private byte[] _s;

    public static X509DsaSignature fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509DsaSignature x509DsaSignature = new X509DsaSignature();
        x509DsaSignature.setR(((Asn1Integer) asn1Sequence.getValues()[0]).getValue());
        x509DsaSignature.setS(((Asn1Integer) asn1Sequence.getValues()[1]).getValue());
        if (ArrayExtensions.getLength(x509DsaSignature.getR()) % 2 == 1) {
            x509DsaSignature.setR(BitAssistant.subArray(x509DsaSignature.getR(), 1));
        }
        if (ArrayExtensions.getLength(x509DsaSignature.getS()) % 2 == 1) {
            x509DsaSignature.setS(BitAssistant.subArray(x509DsaSignature.getS(), 1));
        }
        return x509DsaSignature;
    }

    public byte[] getR() {
        return this._r;
    }

    public byte[] getS() {
        return this._s;
    }

    public void setR(byte[] bArr) {
        this._r = bArr;
    }

    public void setS(byte[] bArr) {
        this._s = bArr;
    }

    public Asn1Sequence toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1Integer(getR()));
        arrayList.add(new Asn1Integer(getS()));
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }
}
