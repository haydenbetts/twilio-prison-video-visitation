package fm.liveswitch.stun.ice;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class ControlledAttribute extends Attribute {
    private long _value;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 8;
    }

    private ControlledAttribute() {
    }

    public ControlledAttribute(long j) {
        setValue(j);
    }

    public int getTypeValue() {
        return Attribute.getControlledType();
    }

    public long getValue() {
        return this._value;
    }

    public static ControlledAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        ControlledAttribute controlledAttribute = new ControlledAttribute();
        controlledAttribute.setValue(dataBuffer.read64(i));
        return controlledAttribute;
    }

    public void setValue(long j) {
        this._value = j;
    }

    public String toString() {
        return StringExtensions.format("ICE-CONTROLLED {0}", (Object) LongExtensions.toString(Long.valueOf(getValue())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write64(getValue(), i);
    }
}
