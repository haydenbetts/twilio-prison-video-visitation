package fm.liveswitch;

class Asn1Null extends Asn1Any {
    public byte[] getContents() {
        return new byte[0];
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(5);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public static Asn1Null parseContents(byte[] bArr) {
        if (ArrayExtensions.getLength(bArr) != 0) {
            return null;
        }
        return new Asn1Null();
    }
}
