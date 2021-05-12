package fm.liveswitch;

import java.util.ArrayList;

class X509PublicKeyInfo {
    private X509AlgorithmIdentifier _algorithm;
    private byte[] _publicKey;

    public static X509PublicKeyInfo fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509PublicKeyInfo x509PublicKeyInfo = new X509PublicKeyInfo();
        x509PublicKeyInfo.setAlgorithm(X509AlgorithmIdentifier.fromAsn1(asn1Sequence.getValues()[0]));
        x509PublicKeyInfo.setPublicKey(((Asn1BitString) asn1Sequence.getValues()[1]).getValueBytes());
        return x509PublicKeyInfo;
    }

    public X509AlgorithmIdentifier getAlgorithm() {
        return this._algorithm;
    }

    public byte[] getPublicKey() {
        return this._publicKey;
    }

    public void setAlgorithm(X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        this._algorithm = x509AlgorithmIdentifier;
    }

    public void setPublicKey(byte[] bArr) {
        this._publicKey = bArr;
    }

    public Asn1Sequence toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getAlgorithm().toAsn1());
        arrayList.add(new Asn1BitString(getPublicKey()));
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }
}
