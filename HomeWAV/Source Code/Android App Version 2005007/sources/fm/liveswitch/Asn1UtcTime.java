package fm.liveswitch;

import java.util.Date;

class Asn1UtcTime extends Asn1Any {
    private Date _value = new Date();

    public Asn1UtcTime() {
    }

    public Asn1UtcTime(Date date) {
        setValue(date);
    }

    public byte[] getContents() {
        return Utf8.encode(StringExtensions.concat(Asn1Any.serializeTimestamp(getValue(), 2, 2, 2, 2, 2, 2), "Z"));
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(23);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public Date getValue() {
        return this._value;
    }

    public static Asn1UtcTime parseContents(byte[] bArr) {
        return new Asn1UtcTime(Asn1Any.deserializeTimestamp(Utf8.decode(bArr, 0, ArrayExtensions.getLength(bArr))));
    }

    public void setValue(Date date) {
        this._value = date;
    }
}
