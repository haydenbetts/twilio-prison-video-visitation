package fm.liveswitch;

import java.util.ArrayList;

class X509ECPrivateKey {
    private X509ECParameters _parameters;
    private byte[] _privateKey;
    private byte[] _publicKey;
    private int _version;

    public static X509ECPrivateKey fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null) {
            return null;
        }
        int i = 2;
        if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X509ECPrivateKey x509ECPrivateKey = new X509ECPrivateKey();
        x509ECPrivateKey.setVersion((int) ((Asn1Integer) asn1Sequence.getValues()[0]).getLongValue());
        x509ECPrivateKey.setPrivateKey(((Asn1OctetString) asn1Sequence.getValues()[1]).getValueBytes());
        while (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) > i) {
            IntegerHolder integerHolder = new IntegerHolder(-1);
            int i2 = i + 1;
            Asn1Any unwrap = Asn1Unknown.unwrap(asn1Sequence.getValues()[i], integerHolder);
            int value = integerHolder.getValue();
            if (value == 0) {
                x509ECPrivateKey.setParameters(X509ECParameters.fromAsn1(unwrap));
            } else if (value == 1) {
                x509ECPrivateKey.setPublicKey(X509ECPoint.fromAsn1(new Asn1OctetString(((Asn1BitString) unwrap).getValueBytes())).getValue());
            }
            i = i2;
        }
        return x509ECPrivateKey;
    }

    public X509ECParameters getParameters() {
        return this._parameters;
    }

    public byte[] getPrivateKey() {
        return this._privateKey;
    }

    public byte[] getPublicKey() {
        return this._publicKey;
    }

    public int getVersion() {
        return this._version;
    }

    public void setParameters(X509ECParameters x509ECParameters) {
        this._parameters = x509ECParameters;
    }

    public void setPrivateKey(byte[] bArr) {
        this._privateKey = bArr;
    }

    public void setPublicKey(byte[] bArr) {
        this._publicKey = bArr;
    }

    public void setVersion(int i) {
        this._version = i;
    }

    public Asn1Sequence toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1Integer((long) getVersion()));
        arrayList.add(new Asn1OctetString(getPrivateKey()));
        if (getParameters() != null) {
            arrayList.add(Asn1Explicit.wrap(0, getParameters().toAsn1()));
        }
        if (getPublicKey() != null) {
            arrayList.add(Asn1Explicit.wrap(1, new Asn1BitString(new X509ECPoint(getPublicKey()).toAsn1().getValueBytes())));
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X509ECPrivateKey() {
        setVersion(1);
    }

    public X509ECPrivateKey(byte[] bArr, X509ECParameters x509ECParameters, byte[] bArr2) {
        this();
        setPrivateKey(bArr);
        setParameters(x509ECParameters);
        setPublicKey(bArr2);
    }
}
