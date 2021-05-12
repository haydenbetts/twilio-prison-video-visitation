package fm.liveswitch;

class Asn1BitString extends Asn1Any {
    private static String _defaultValue = "";
    private String __value;

    public Asn1BitString() {
    }

    public Asn1BitString(String str) {
        setValue(str);
    }

    public Asn1BitString(byte[] bArr) {
        setValueBytes(bArr);
    }

    public byte[] getContents() {
        IntegerHolder integerHolder = new IntegerHolder(0);
        byte[] bitStringToBytes = Binary.bitStringToBytes(getValue(), integerHolder);
        int value = integerHolder.getValue();
        byte[] bArr = new byte[(ArrayExtensions.getLength(bitStringToBytes) + 1)];
        bArr[0] = (byte) value;
        BitAssistant.copy(bitStringToBytes, 0, bArr, 1, ArrayExtensions.getLength(bitStringToBytes));
        return bArr;
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(3);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public String getValue() {
        String str = this.__value;
        return str != null ? str : _defaultValue;
    }

    public byte[] getValueBytes() {
        return Binary.bitStringToBytes(getValue());
    }

    public static Asn1BitString parseContents(byte[] bArr) {
        if (ArrayExtensions.getLength(bArr) < 1) {
            return null;
        }
        return new Asn1BitString(Binary.bytesToBitString(bArr, 1, ArrayExtensions.getLength(bArr) - 1, bArr[0]));
    }

    public void setValue(String str) {
        if (str == null) {
            str = _defaultValue;
        }
        this.__value = str;
    }

    public void setValueBytes(byte[] bArr) {
        setValue(Binary.bytesToBitString(bArr));
    }
}
