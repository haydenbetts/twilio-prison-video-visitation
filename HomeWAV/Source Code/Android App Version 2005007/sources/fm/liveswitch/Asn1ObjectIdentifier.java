package fm.liveswitch;

import java.util.ArrayList;

class Asn1ObjectIdentifier extends Asn1Any {
    private long[] _values;

    public static boolean areEqual(long[] jArr, long[] jArr2) {
        if ((jArr == null) != (jArr2 == null)) {
            return false;
        }
        if (jArr != null) {
            if (ArrayExtensions.getLength(jArr) != ArrayExtensions.getLength(jArr2)) {
                return false;
            }
            for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
                if (jArr[i] != jArr2[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Asn1ObjectIdentifier() {
    }

    public Asn1ObjectIdentifier(long[] jArr) {
        setValues(jArr);
    }

    public byte[] getContents() {
        if (ArrayExtensions.getLength(getValues()) < 2) {
            Log.error("Object identifiers must have at least two values.");
            return null;
        }
        long j = getValues()[0];
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0 || j > 2) {
            Log.error("The first value in an object identifier is limited to values 0, 1, and 2.");
            return null;
        }
        long j2 = getValues()[1];
        if ((i == 0 || j == 1) && (j2 < 0 || j2 > 39)) {
            Log.error("The second value in an object identifier is limited to the range 0 to 39 when the first value is 0 or 1.");
            return null;
        }
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) ((int) ((j * 40) + j2)));
        for (int i2 = 2; i2 < ArrayExtensions.getLength(getValues()); i2++) {
            byteCollection.addRange(Asn1Any.encode128Long(getValues()[i2]));
        }
        return byteCollection.toArray();
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(6);
        integerHolder2.setValue(0);
        booleanHolder.setValue(false);
        booleanHolder2.setValue(false);
    }

    public long[] getValues() {
        return this._values;
    }

    public static Asn1ObjectIdentifier parseContents(byte[] bArr) {
        byte b = bArr[0];
        int i = 1;
        int i2 = b >= 40 ? b < 80 ? 1 : 2 : 0;
        int i3 = b - (i2 * 40);
        int i4 = 1;
        int i5 = 2;
        while (i4 < ArrayExtensions.getLength(bArr)) {
            int length128 = Asn1Any.getLength128(bArr, i4);
            if (length128 == 0) {
                return null;
            }
            i5++;
            i4 += length128;
        }
        long[] jArr = new long[i5];
        jArr[0] = (long) i2;
        jArr[1] = (long) i3;
        for (int i6 = 2; i6 < i5; i6++) {
            int length1282 = Asn1Any.getLength128(bArr, i);
            jArr[i6] = Asn1Any.decode128Long(bArr, i, length1282);
            i += length1282;
        }
        return new Asn1ObjectIdentifier(jArr);
    }

    public void setValues(long[] jArr) {
        this._values = jArr;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        for (long valueOf : getValues()) {
            arrayList.add(LongExtensions.toString(Long.valueOf(valueOf)));
        }
        return StringExtensions.join(".", (String[]) arrayList.toArray(new String[0]));
    }
}
