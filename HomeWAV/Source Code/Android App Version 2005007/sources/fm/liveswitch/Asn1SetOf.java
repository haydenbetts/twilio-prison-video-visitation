package fm.liveswitch;

import fm.liveswitch.Asn1Any;
import java.util.ArrayList;
import java.util.Iterator;

class Asn1SetOf<T extends Asn1Any> extends Asn1ArrayOf<T> {
    public Asn1SetOf() {
    }

    public Asn1SetOf(T[] tArr) {
        super.setValues(tArr);
    }

    /* access modifiers changed from: private */
    public CompareResult compareByteArrays(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return CompareResult.Negative;
        }
        if (bArr2 == null) {
            return CompareResult.Positive;
        }
        for (int i = 0; i < MathAssistant.min(ArrayExtensions.getLength(bArr), ArrayExtensions.getLength(bArr2)); i++) {
            byte b = bArr[i];
            byte b2 = bArr2[i];
            if (b < b2) {
                return CompareResult.Negative;
            }
            if (b > b2) {
                return CompareResult.Positive;
            }
        }
        if (ArrayExtensions.getLength(bArr) < ArrayExtensions.getLength(bArr2)) {
            return CompareResult.Negative;
        }
        if (ArrayExtensions.getLength(bArr) > ArrayExtensions.getLength(bArr2)) {
            return CompareResult.Positive;
        }
        return CompareResult.Equal;
    }

    public byte[] getContents() {
        ArrayList arrayList = new ArrayList();
        for (Asn1Any bytes : super.getValues()) {
            arrayList.add(bytes.getBytes());
        }
        Sort.quickSort(arrayList, new IFunctionDelegate2<byte[], byte[], CompareResult>() {
            public String getId() {
                return "fm.liveswitch.Asn1SetOf<T>.compareByteArrays";
            }

            public CompareResult invoke(byte[] bArr, byte[] bArr2) {
                return Asn1SetOf.this.compareByteArrays(bArr, bArr2);
            }
        });
        ByteCollection byteCollection = new ByteCollection();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            byteCollection.addRange((byte[]) it.next());
        }
        return byteCollection.toArray();
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(17);
        integerHolder2.setValue(0);
        booleanHolder.setValue(true);
        booleanHolder2.setValue(false);
    }
}
