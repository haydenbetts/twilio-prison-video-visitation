package fm.liveswitch;

class Asn1Boolean extends Asn1Any {
    private boolean _value;

    public Asn1Boolean() {
    }

    public Asn1Boolean(boolean z) {
        setValue(z);
    }

    public byte[] getContents() {
        byte[] bArr = new byte[1];
        bArr[0] = BitAssistant.castByte(getValue() ? -1 : 0);
        return bArr;
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(1);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public boolean getValue() {
        return this._value;
    }

    public static Asn1Boolean parseContents(byte[] bArr) {
        boolean z = true;
        if (ArrayExtensions.getLength(bArr) != 1) {
            return null;
        }
        if (bArr[0] == BitAssistant.castByte(0)) {
            z = false;
        }
        return new Asn1Boolean(z);
    }

    public void setValue(boolean z) {
        this._value = z;
    }
}
