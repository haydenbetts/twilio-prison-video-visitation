package fm.liveswitch.stun;

import fm.liveswitch.ArrayExtensions;
import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerHolder;

public class UnknownAttributesAttribute extends Attribute {
    private int[] _types;

    public int[] getTypes() {
        return this._types;
    }

    public int getTypeValue() {
        return Attribute.getUnknownAttributesType();
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return ArrayExtensions.getLength(getTypes()) * 2;
    }

    public static UnknownAttributesAttribute readValueFrom(DataBuffer dataBuffer, int i, int i2) {
        int[] iArr = new int[(i2 / 2)];
        int i3 = 0;
        while (i3 < ArrayExtensions.getLength(iArr)) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            int read16 = dataBuffer.read16(i, integerHolder);
            int value = integerHolder.getValue();
            iArr[i3] = read16;
            i3++;
            i = value;
        }
        UnknownAttributesAttribute unknownAttributesAttribute = new UnknownAttributesAttribute();
        unknownAttributesAttribute.setTypes(iArr);
        return unknownAttributesAttribute;
    }

    public void setTypes(int[] iArr) {
        this._types = iArr;
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        for (int write16 : getTypes()) {
            IntegerHolder integerHolder = new IntegerHolder(i);
            dataBuffer.write16(write16, i, integerHolder);
            i = integerHolder.getValue();
        }
    }
}
