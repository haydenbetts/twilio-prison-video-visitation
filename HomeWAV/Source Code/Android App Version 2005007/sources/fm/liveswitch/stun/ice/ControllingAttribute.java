package fm.liveswitch.stun.ice;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class ControllingAttribute extends Attribute {
    private long _value;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 8;
    }

    private ControllingAttribute() {
    }

    public ControllingAttribute(long j) {
        setValue(j);
    }

    public int getTypeValue() {
        return Attribute.getControllingType();
    }

    public long getValue() {
        return this._value;
    }

    public static ControllingAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        ControllingAttribute controllingAttribute = new ControllingAttribute();
        controllingAttribute.setValue(dataBuffer.read64(i));
        return controllingAttribute;
    }

    public void setValue(long j) {
        this._value = j;
    }

    public String toString() {
        return StringExtensions.format("ICE-CONTROLLING {0}", (Object) LongExtensions.toString(Long.valueOf(getValue())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write64(getValue(), i);
    }
}
