package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class EvenPortAttribute extends Attribute {
    private boolean _reserveNextHigher;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 1;
    }

    private EvenPortAttribute() {
    }

    public EvenPortAttribute(boolean z) {
        setReserveNextHigher(z);
    }

    public boolean getReserveNextHigher() {
        return this._reserveNextHigher;
    }

    public int getTypeValue() {
        return Attribute.getEvenPortType();
    }

    public static EvenPortAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        EvenPortAttribute evenPortAttribute = new EvenPortAttribute();
        evenPortAttribute.setReserveNextHigher((dataBuffer.read8(i) & 128) == 128);
        return evenPortAttribute;
    }

    public void setReserveNextHigher(boolean z) {
        this._reserveNextHigher = z;
    }

    public String toString() {
        return StringExtensions.format("EVEN-PORT ({0}reserve next higher)", (Object) getReserveNextHigher() ? "" : "don't ");
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write8(getReserveNextHigher() ? 128 : 0, i);
    }
}
