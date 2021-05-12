package fm.liveswitch;

import java.util.ArrayList;
import java.util.Iterator;

class Asn1Set extends Asn1Array {
    public Asn1Set() {
    }

    public Asn1Set(Asn1Any[] asn1AnyArr) {
        super.setValues(asn1AnyArr);
    }

    public byte[] getContents() {
        if (ArrayExtensions.getLength((Object[]) super.getValues()) == 0) {
            Log.error("Set must contain at least one value.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Asn1Any add : super.getValues()) {
            arrayList.add(add);
        }
        Sort.quickSort(arrayList, new IFunctionDelegate2<Asn1Any, Asn1Any, CompareResult>() {
            public String getId() {
                return "fm.liveswitch.Asn1Set.sortValues";
            }

            public CompareResult invoke(Asn1Any asn1Any, Asn1Any asn1Any2) {
                return Asn1Set.sortValues(asn1Any, asn1Any2);
            }
        });
        ByteCollection byteCollection = new ByteCollection();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Asn1Any asn1Any = (Asn1Any) it.next();
            if (asn1Any != null) {
                byteCollection.addRange(asn1Any.getBytes());
            }
        }
        return byteCollection.toArray();
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(17);
        integerHolder2.setValue(0);
        booleanHolder.setValue(true);
        booleanHolder2.setValue(false);
    }

    private static int getTag(Asn1Any asn1Any) {
        IntegerHolder integerHolder = new IntegerHolder(0);
        IntegerHolder integerHolder2 = new IntegerHolder(0);
        BooleanHolder booleanHolder = new BooleanHolder(false);
        BooleanHolder booleanHolder2 = new BooleanHolder(false);
        asn1Any.getProperties(integerHolder, integerHolder2, booleanHolder, booleanHolder2);
        int value = integerHolder.getValue();
        integerHolder2.getValue();
        booleanHolder.getValue();
        booleanHolder2.getValue();
        return value;
    }

    public static Asn1Set parseContents(byte[] bArr) {
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
        return new Asn1Set((Asn1Any[]) arrayList.toArray(new Asn1Any[0]));
    }

    /* access modifiers changed from: private */
    public static CompareResult sortValues(Asn1Any asn1Any, Asn1Any asn1Any2) {
        if (asn1Any == null) {
            return CompareResult.Negative;
        }
        if (asn1Any2 == null) {
            return CompareResult.Positive;
        }
        int tag = getTag(asn1Any);
        int tag2 = getTag(asn1Any2);
        if (tag < tag2) {
            return CompareResult.Negative;
        }
        if (tag > tag2) {
            return CompareResult.Positive;
        }
        return CompareResult.Equal;
    }
}
