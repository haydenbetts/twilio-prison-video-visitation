package fm.liveswitch;

class X509RsaPrivateKey {
    private byte[] __coefficient;
    private byte[] __exponent1;
    private byte[] __exponent2;
    private byte[] __modulus;
    private byte[] __prime1;
    private byte[] __prime2;
    private byte[] __privateExponent;
    private byte[] __publicExponent;
    private int _version;

    public static X509RsaPrivateKey fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) != 9) {
            return null;
        }
        X509RsaPrivateKey x509RsaPrivateKey = new X509RsaPrivateKey();
        x509RsaPrivateKey.setVersion((int) ((Asn1Integer) asn1Sequence.getValues()[0]).getLongValue());
        x509RsaPrivateKey.setModulus(((Asn1Integer) asn1Sequence.getValues()[1]).getValue());
        x509RsaPrivateKey.setPublicExponent(((Asn1Integer) asn1Sequence.getValues()[2]).getValue());
        x509RsaPrivateKey.setPrivateExponent(((Asn1Integer) asn1Sequence.getValues()[3]).getValue());
        x509RsaPrivateKey.setPrime1(((Asn1Integer) asn1Sequence.getValues()[4]).getValue());
        x509RsaPrivateKey.setPrime2(((Asn1Integer) asn1Sequence.getValues()[5]).getValue());
        x509RsaPrivateKey.setExponent1(((Asn1Integer) asn1Sequence.getValues()[6]).getValue());
        x509RsaPrivateKey.setExponent2(((Asn1Integer) asn1Sequence.getValues()[7]).getValue());
        x509RsaPrivateKey.setCoefficient(((Asn1Integer) asn1Sequence.getValues()[8]).getValue());
        return x509RsaPrivateKey;
    }

    public byte[] getCoefficient() {
        return trim(this.__coefficient);
    }

    public byte[] getExponent1() {
        return trim(this.__exponent1);
    }

    public byte[] getExponent2() {
        return trim(this.__exponent2);
    }

    public byte[] getModulus() {
        return trim(this.__modulus);
    }

    public byte[] getPrime1() {
        return trim(this.__prime1);
    }

    public byte[] getPrime2() {
        return trim(this.__prime2);
    }

    public byte[] getPrivateExponent() {
        return trim(this.__privateExponent);
    }

    public byte[] getPublicExponent() {
        return this.__publicExponent;
    }

    public int getVersion() {
        return this._version;
    }

    private static byte[] lengthen(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (ArrayExtensions.getLength(bArr) % 2 != 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) + 1)];
        bArr2[0] = 0;
        BitAssistant.copy(bArr, 0, bArr2, 1, ArrayExtensions.getLength(bArr));
        return bArr2;
    }

    public void setCoefficient(byte[] bArr) {
        this.__coefficient = lengthen(bArr);
    }

    public void setExponent1(byte[] bArr) {
        this.__exponent1 = lengthen(bArr);
    }

    public void setExponent2(byte[] bArr) {
        this.__exponent2 = lengthen(bArr);
    }

    public void setModulus(byte[] bArr) {
        this.__modulus = lengthen(bArr);
    }

    public void setPrime1(byte[] bArr) {
        this.__prime1 = lengthen(bArr);
    }

    public void setPrime2(byte[] bArr) {
        this.__prime2 = lengthen(bArr);
    }

    public void setPrivateExponent(byte[] bArr) {
        this.__privateExponent = lengthen(bArr);
    }

    public void setPublicExponent(byte[] bArr) {
        this.__publicExponent = bArr;
    }

    public void setVersion(int i) {
        this._version = i;
    }

    public Asn1Sequence toAsn1() {
        return new Asn1Sequence(new Asn1Any[]{new Asn1Integer((long) getVersion()), new Asn1Integer(this.__modulus), new Asn1Integer(this.__publicExponent), new Asn1Integer(this.__privateExponent), new Asn1Integer(this.__prime1), new Asn1Integer(this.__prime2), new Asn1Integer(this.__exponent1), new Asn1Integer(this.__exponent2), new Asn1Integer(this.__coefficient)});
    }

    private static byte[] trim(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (ArrayExtensions.getLength(bArr) % 2 == 1 && bArr[0] == 0) ? BitAssistant.subArray(bArr, 1) : bArr;
    }

    public X509RsaPrivateKey() {
        this.__modulus = null;
        this.__publicExponent = null;
        this.__privateExponent = null;
        this.__prime1 = null;
        this.__prime2 = null;
        this.__exponent1 = null;
        this.__exponent2 = null;
        this.__coefficient = null;
        setVersion(0);
    }

    public X509RsaPrivateKey(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8) {
        this();
        setModulus(bArr);
        setPublicExponent(bArr2);
        setPrivateExponent(bArr3);
        setPrime1(bArr4);
        setPrime2(bArr5);
        setExponent1(bArr6);
        setExponent2(bArr7);
        setCoefficient(bArr8);
    }
}
