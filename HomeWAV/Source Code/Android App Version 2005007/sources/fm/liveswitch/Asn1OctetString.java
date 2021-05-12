package fm.liveswitch;

class Asn1OctetString extends Asn1Any {
    private static String _defaultValue = "";
    private String __value;

    public Asn1OctetString() {
    }

    public Asn1OctetString(String str) {
        setValue(str);
    }

    public Asn1OctetString(byte[] bArr) {
        setValueBytes(bArr);
    }

    public byte[] getContents() {
        return BitAssistant.getHexBytes(getValue());
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(4);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public String getValue() {
        String str = this.__value;
        return str != null ? str : _defaultValue;
    }

    public byte[] getValueBytes() {
        return BitAssistant.getHexBytes(getValue());
    }

    public static Asn1OctetString parseContents(byte[] bArr) {
        return new Asn1OctetString(BitAssistant.getHexString(bArr));
    }

    public void setValue(String str) {
        if (str == null) {
            str = _defaultValue;
        }
        this.__value = str;
    }

    public void setValueBytes(byte[] bArr) {
        setValue(BitAssistant.getHexString(bArr));
    }
}
