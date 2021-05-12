package fm.liveswitch;

import java.util.ArrayList;

class X509PrivateKeyInfo {
    private byte[] _privateKey;
    private X509AlgorithmIdentifier _privateKeyAlgorithm;
    private int _version;

    public static X509PrivateKeyInfo fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 3) {
            return null;
        }
        X509PrivateKeyInfo x509PrivateKeyInfo = new X509PrivateKeyInfo();
        x509PrivateKeyInfo.setVersion((int) ((Asn1Integer) asn1Sequence.getValues()[0]).getLongValue());
        x509PrivateKeyInfo.setPrivateKeyAlgorithm(X509AlgorithmIdentifier.fromAsn1(asn1Sequence.getValues()[1]));
        x509PrivateKeyInfo.setPrivateKey(((Asn1OctetString) asn1Sequence.getValues()[2]).getValueBytes());
        return x509PrivateKeyInfo;
    }

    public byte[] getPrivateKey() {
        return this._privateKey;
    }

    public X509AlgorithmIdentifier getPrivateKeyAlgorithm() {
        return this._privateKeyAlgorithm;
    }

    public int getVersion() {
        return this._version;
    }

    public void setPrivateKey(byte[] bArr) {
        this._privateKey = bArr;
    }

    public void setPrivateKeyAlgorithm(X509AlgorithmIdentifier x509AlgorithmIdentifier) {
        this._privateKeyAlgorithm = x509AlgorithmIdentifier;
    }

    public void setVersion(int i) {
        this._version = i;
    }

    public Asn1Sequence toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1Integer((long) getVersion()));
        arrayList.add(getPrivateKeyAlgorithm().toAsn1());
        arrayList.add(new Asn1OctetString(getPrivateKey()));
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X509PrivateKeyInfo() {
        setVersion(0);
    }
}
