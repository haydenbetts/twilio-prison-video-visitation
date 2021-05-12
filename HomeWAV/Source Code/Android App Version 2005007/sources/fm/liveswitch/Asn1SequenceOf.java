package fm.liveswitch;

import fm.liveswitch.Asn1Any;

class Asn1SequenceOf<T extends Asn1Any> extends Asn1ArrayOf<T> {
    public Asn1SequenceOf() {
    }

    public Asn1SequenceOf(T[] tArr) {
        super.setValues(tArr);
    }

    public byte[] getContents() {
        ByteCollection byteCollection = new ByteCollection();
        for (Asn1Any bytes : super.getValues()) {
            byteCollection.addRange(bytes.getBytes());
        }
        return byteCollection.toArray();
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(16);
        integerHolder2.setValue(0);
        booleanHolder.setValue(true);
        booleanHolder2.setValue(false);
    }
}
