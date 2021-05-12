package fm.liveswitch;

import java.util.ArrayList;

class X509AlgorithmIdentifier {
    private long[] _algorithm;
    private byte[] _parameters;

    public static boolean algorithmsAreEqual(X509AlgorithmIdentifier x509AlgorithmIdentifier, X509AlgorithmIdentifier x509AlgorithmIdentifier2) {
        if ((x509AlgorithmIdentifier == null) != (x509AlgorithmIdentifier2 == null)) {
            return false;
        }
        return x509AlgorithmIdentifier == null || Asn1ObjectIdentifier.areEqual(x509AlgorithmIdentifier.getAlgorithm(), x509AlgorithmIdentifier2.getAlgorithm());
    }

    public static boolean areEqual(X509AlgorithmIdentifier x509AlgorithmIdentifier, X509AlgorithmIdentifier x509AlgorithmIdentifier2) {
        return algorithmsAreEqual(x509AlgorithmIdentifier, x509AlgorithmIdentifier2) && parametersAreEqual(x509AlgorithmIdentifier, x509AlgorithmIdentifier2);
    }

    public static X509AlgorithmIdentifier fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 1) {
            return null;
        }
        X509AlgorithmIdentifier x509AlgorithmIdentifier = new X509AlgorithmIdentifier();
        x509AlgorithmIdentifier.setAlgorithm(((Asn1ObjectIdentifier) asn1Sequence.getValues()[0]).getValues());
        if (ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) > 1) {
            x509AlgorithmIdentifier.setParameters(asn1Sequence.getValues()[1].getBytes());
        }
        return x509AlgorithmIdentifier;
    }

    public long[] getAlgorithm() {
        return this._algorithm;
    }

    public static X509AlgorithmIdentifier getDsaWithSha1() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getDsaWithSha1());
    }

    public static X509AlgorithmIdentifier getDsaWithSha224() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getDsaWithSha224());
    }

    public static X509AlgorithmIdentifier getDsaWithSha256() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getDsaWithSha256());
    }

    public static X509AlgorithmIdentifier getEcdsa(X509ECParameters x509ECParameters) {
        return new X509AlgorithmIdentifier(X509PublicKeyAlgorithm.getEcPublicKey(), x509ECParameters.toAsn1().getBytes());
    }

    public static X509AlgorithmIdentifier getEcdsaWithSha224() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getEcdsaWithSha224());
    }

    public static X509AlgorithmIdentifier getEcdsaWithSha256() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getEcdsaWithSha256());
    }

    public static X509AlgorithmIdentifier getEcdsaWithSha384() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getEcdsaWithSha384());
    }

    public static X509AlgorithmIdentifier getEcdsaWithSha512() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getEcdsaWithSha512());
    }

    public static X509AlgorithmIdentifier getMd2WithRsaEncryption() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getMd2WithRsaEncryption(), new Asn1Null().getBytes());
    }

    public static X509AlgorithmIdentifier getMd5WithRsaEncryption() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getMd5WithRsaEncryption(), new Asn1Null().getBytes());
    }

    public byte[] getParameters() {
        return this._parameters;
    }

    public static X509AlgorithmIdentifier getRsa() {
        return new X509AlgorithmIdentifier(X509PublicKeyAlgorithm.getRsaEncryption(), new Asn1Null().getBytes());
    }

    public static X509AlgorithmIdentifier getSha1WithRsaEncryption() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getSha1WithRsaEncryption(), new Asn1Null().getBytes());
    }

    public static X509AlgorithmIdentifier getSha256WithRsaEncryption() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getSha256WithRsaEncryption(), new Asn1Null().getBytes());
    }

    public static X509AlgorithmIdentifier getSha384WithRsaEncryption() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getSha384WithRsaEncryption(), new Asn1Null().getBytes());
    }

    public static X509AlgorithmIdentifier getSha512WithRsaEncryption() {
        return new X509AlgorithmIdentifier(X509SignatureAlgorithm.getSha512WithRsaEncryption(), new Asn1Null().getBytes());
    }

    public static boolean parametersAreEqual(X509AlgorithmIdentifier x509AlgorithmIdentifier, X509AlgorithmIdentifier x509AlgorithmIdentifier2) {
        if ((x509AlgorithmIdentifier == null) != (x509AlgorithmIdentifier2 == null)) {
            return false;
        }
        if (x509AlgorithmIdentifier != null) {
            if ((x509AlgorithmIdentifier.getParameters() == null) != (x509AlgorithmIdentifier2.getParameters() == null)) {
                return false;
            }
            if (x509AlgorithmIdentifier.getParameters() == null) {
                return true;
            }
            for (int i = 0; i < ArrayExtensions.getLength(x509AlgorithmIdentifier.getParameters()); i++) {
                if (x509AlgorithmIdentifier.getParameters()[i] != x509AlgorithmIdentifier2.getParameters()[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setAlgorithm(long[] jArr) {
        this._algorithm = jArr;
    }

    public void setParameters(byte[] bArr) {
        this._parameters = bArr;
    }

    public Asn1Sequence toAsn1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Asn1ObjectIdentifier(getAlgorithm()));
        if (getParameters() != null) {
            arrayList.add(Asn1Any.parseBytes(getParameters()));
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    public X509AlgorithmIdentifier() {
    }

    public X509AlgorithmIdentifier(long[] jArr) {
        this(jArr, (byte[]) null);
    }

    public X509AlgorithmIdentifier(long[] jArr, byte[] bArr) {
        setAlgorithm(jArr);
        setParameters(bArr);
    }
}
