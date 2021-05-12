package fm.liveswitch;

class Asn1Ia5String extends Asn1Any {
    private String _value;

    public Asn1Ia5String() {
    }

    public Asn1Ia5String(String str) {
        setValue(str);
    }

    public byte[] getContents() {
        return Utf8.encode(getValue());
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(22);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public String getValue() {
        return this._value;
    }

    public static Asn1Ia5String parseContents(byte[] bArr) {
        return new Asn1Ia5String(Utf8.decode(bArr, 0, ArrayExtensions.getLength(bArr)));
    }

    public void setValue(String str) {
        this._value = str;
    }
}
