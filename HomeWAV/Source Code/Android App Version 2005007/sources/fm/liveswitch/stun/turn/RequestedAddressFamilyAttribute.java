package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class RequestedAddressFamilyAttribute extends Attribute {
    private int _addressFamily;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    public int getAddressFamily() {
        return this._addressFamily;
    }

    public int getTypeValue() {
        return Attribute.getRequestedAddressFamilyType();
    }

    public static RequestedAddressFamilyAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        RequestedAddressFamilyAttribute requestedAddressFamilyAttribute = new RequestedAddressFamilyAttribute();
        requestedAddressFamilyAttribute.setAddressFamily(dataBuffer.read8(i));
        return requestedAddressFamilyAttribute;
    }

    private RequestedAddressFamilyAttribute() {
    }

    public RequestedAddressFamilyAttribute(int i) {
        setAddressFamily(i);
    }

    public void setAddressFamily(int i) {
        this._addressFamily = i;
    }

    public String toString() {
        return StringExtensions.format("REQUESTED-ADDRESS-FAMILY {0}", (Object) IntegerExtensions.toString(Integer.valueOf(getAddressFamily())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        IntegerHolder integerHolder = new IntegerHolder(i);
        dataBuffer.write8(getAddressFamily(), i, integerHolder);
        dataBuffer.write24(0, integerHolder.getValue());
    }
}
