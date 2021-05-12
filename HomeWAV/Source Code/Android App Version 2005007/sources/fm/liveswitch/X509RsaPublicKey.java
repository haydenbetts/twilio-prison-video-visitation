package fm.liveswitch;

class X509RsaPublicKey {
    private byte[] __exponent = null;
    private byte[] __modulus = null;

    public static X509RsaPublicKey fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) != 2) {
            return null;
        }
        X509RsaPublicKey x509RsaPublicKey = new X509RsaPublicKey();
        x509RsaPublicKey.setModulus(((Asn1Integer) asn1Sequence.getValues()[0]).getValue());
        x509RsaPublicKey.setExponent(((Asn1Integer) asn1Sequence.getValues()[1]).getValue());
        return x509RsaPublicKey;
    }

    public byte[] getExponent() {
        return this.__exponent;
    }

    public byte[] getModulus() {
        return trim(this.__modulus);
    }

    private static byte[] lengthen(byte[] bArr) {
        if (ArrayExtensions.getLength(bArr) % 2 != 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) + 1)];
        bArr2[0] = 0;
        BitAssistant.copy(bArr, 0, bArr2, 1, ArrayExtensions.getLength(bArr));
        return bArr2;
    }

    public void setExponent(byte[] bArr) {
        this.__exponent = bArr;
    }

    public void setModulus(byte[] bArr) {
        this.__modulus = lengthen(bArr);
    }

    public Asn1Sequence toAsn1() {
        return new Asn1Sequence(new Asn1Any[]{new Asn1Integer(this.__modulus), new Asn1Integer(this.__exponent)});
    }

    private static byte[] trim(byte[] bArr) {
        return (ArrayExtensions.getLength(bArr) % 2 == 1 && bArr[0] == 0) ? BitAssistant.subArray(bArr, 1) : bArr;
    }

    public X509RsaPublicKey() {
    }

    public X509RsaPublicKey(byte[] bArr, byte[] bArr2) {
        setModulus(bArr);
        setExponent(bArr2);
    }
}
