package fm.liveswitch;

class Asn1PrintableString extends Asn1Any {
    private String _value;

    public Asn1PrintableString() {
    }

    public Asn1PrintableString(String str) {
        setValue(str);
    }

    public byte[] getContents() {
        return Utf8.encode(getValue());
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(19);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public String getValue() {
        return this._value;
    }

    public static Asn1PrintableString parseContents(byte[] bArr) {
        return new Asn1PrintableString(Utf8.decode(bArr, 0, ArrayExtensions.getLength(bArr)));
    }

    public void setValue(String str) {
        this._value = str;
    }
}
