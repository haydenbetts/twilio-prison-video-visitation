package fm.liveswitch;

import kotlin.jvm.internal.ByteCompanionObject;

class Asn1Integer extends Asn1Any {
    private static byte[] _defaultValue = new byte[1];
    private byte[] __value;

    public Asn1Integer() {
    }

    public Asn1Integer(long j) {
        setLongValue(j);
    }

    public Asn1Integer(byte[] bArr) {
        setValue(bArr);
    }

    private static long bytesToLong(byte[] bArr) {
        return Binary.fromBytes64(Asn1Any.pad(bArr, 8, (bArr[0] & ByteCompanionObject.MIN_VALUE) == 128 ? 255 : 0), 0, false);
    }

    public byte[] getContents() {
        return getValue();
    }

    public long getLongValue() {
        return bytesToLong(getValue());
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(2);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public byte[] getValue() {
        byte[] bArr = this.__value;
        return bArr != null ? bArr : _defaultValue;
    }

    private static byte[] longToBytes(long j) {
        int i = (j > 2147483647L || j < -2147483648L) ? 8 : (j > 32767 || j < -32768) ? 4 : (j > 127 || j < -128) ? 2 : 1;
        return Asn1Any.trim(Binary.toBytes64(j, false), i, i);
    }

    public static Asn1Integer parseContents(byte[] bArr) {
        return new Asn1Integer(bArr);
    }

    public void setLongValue(long j) {
        setValue(longToBytes(j));
    }

    public void setValue(byte[] bArr) {
        if (bArr == null) {
            bArr = _defaultValue;
        }
        this.__value = bArr;
    }
}
