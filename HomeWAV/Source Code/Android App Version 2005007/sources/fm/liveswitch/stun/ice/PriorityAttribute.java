package fm.liveswitch.stun.ice;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.LongExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class PriorityAttribute extends Attribute {
    private long _priority;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    public long getPriority() {
        return this._priority;
    }

    public int getTypeValue() {
        return Attribute.getPriorityType();
    }

    private PriorityAttribute() {
    }

    public PriorityAttribute(long j) {
        setPriority(j);
    }

    public static PriorityAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        PriorityAttribute priorityAttribute = new PriorityAttribute();
        priorityAttribute.setPriority(dataBuffer.read32(i));
        return priorityAttribute;
    }

    public void setPriority(long j) {
        this._priority = j;
    }

    public String toString() {
        return StringExtensions.format("PRIORITY {0}", (Object) LongExtensions.toString(Long.valueOf(getPriority())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write32(getPriority(), i);
    }
}
