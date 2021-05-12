package fm.liveswitch;

class Asn1NumericString extends Asn1Any {
    private String _value;

    public Asn1NumericString() {
    }

    public Asn1NumericString(String str) {
        setValue(str);
    }

    public byte[] getContents() {
        return Utf8.encode(getValue());
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(18);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public String getValue() {
        return this._value;
    }

    public static Asn1NumericString parseContents(byte[] bArr) {
        return new Asn1NumericString(Utf8.decode(bArr, 0, ArrayExtensions.getLength(bArr)));
    }

    public void setValue(String str) {
        this._value = str;
    }
}
