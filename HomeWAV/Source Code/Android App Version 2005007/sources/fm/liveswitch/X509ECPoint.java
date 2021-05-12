package fm.liveswitch;

class X509ECPoint {
    private static byte[] __empty = {BitAssistant.castByte(0)};
    private byte[] __value = null;

    public byte[] compress() {
        byte[] value = getValue();
        if (value == null || ArrayExtensions.getLength(value) == 0) {
            return __empty;
        }
        byte[] bArr = new byte[(ArrayExtensions.getLength(value) + 1)];
        bArr[0] = 4;
        BitAssistant.copy(value, 0, bArr, 1, ArrayExtensions.getLength(value));
        return bArr;
    }

    public static X509ECPoint decompress(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        X509ECPoint x509ECPoint = new X509ECPoint();
        if (!BitAssistant.sequencesAreEqual(bArr, __empty) && bArr[0] == 4) {
            x509ECPoint.setValue(BitAssistant.subArray(bArr, 1));
        }
        return x509ECPoint;
    }

    public static X509ECPoint fromAsn1(Asn1Any asn1Any) {
        Asn1OctetString asn1OctetString = (Asn1OctetString) Global.tryCast(asn1Any, Asn1OctetString.class);
        if (asn1OctetString == null) {
            return null;
        }
        return decompress(asn1OctetString.getValueBytes());
    }

    public byte[] getValue() {
        return this.__value;
    }

    public void setValue(byte[] bArr) {
        this.__value = bArr;
    }

    public Asn1OctetString toAsn1() {
        return new Asn1OctetString(compress());
    }

    public X509ECPoint() {
    }

    public X509ECPoint(byte[] bArr) {
        setValue(bArr);
    }
}
