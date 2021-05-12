package fm.liveswitch;

import java.util.ArrayList;

class Asn1Sequence extends Asn1Array {
    public Asn1Sequence() {
        this(new Asn1Any[0]);
    }

    public Asn1Sequence(Asn1Any[] asn1AnyArr) {
        super.setValues(asn1AnyArr);
    }

    public byte[] getContents() {
        ByteCollection byteCollection = new ByteCollection();
        for (Asn1Any asn1Any : super.getValues()) {
            if (asn1Any != null) {
                byteCollection.addRange(asn1Any.getBytes());
            }
        }
        return byteCollection.toArray();
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(16);
        integerHolder2.setValue(0);
        booleanHolder.setValue(true);
        booleanHolder2.setValue(false);
    }

    public static Asn1Sequence parseContents(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < ArrayExtensions.getLength(bArr)) {
            IntegerHolder integerHolder = new IntegerHolder(0);
            Asn1Any parseBytes = Asn1Any.parseBytes(bArr, i, integerHolder);
            int value = integerHolder.getValue();
            if (parseBytes == null) {
                return null;
            }
            arrayList.add(parseBytes);
            i += value;
        }
        return new Asn1Sequence((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }
}
