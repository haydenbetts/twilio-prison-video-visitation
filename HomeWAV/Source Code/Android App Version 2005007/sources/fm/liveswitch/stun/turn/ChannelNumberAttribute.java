package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class ChannelNumberAttribute extends Attribute {
    private int _channelNumber;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    private ChannelNumberAttribute() {
    }

    public ChannelNumberAttribute(int i) {
        setChannelNumber(i);
    }

    public int getChannelNumber() {
        return this._channelNumber;
    }

    public int getTypeValue() {
        return Attribute.getChannelNumberType();
    }

    public static ChannelNumberAttribute readValueFrom(DataBuffer dataBuffer, int i) {
        ChannelNumberAttribute channelNumberAttribute = new ChannelNumberAttribute();
        channelNumberAttribute.setChannelNumber(dataBuffer.read16(i));
        return channelNumberAttribute;
    }

    public void setChannelNumber(int i) {
        this._channelNumber = i;
    }

    public String toString() {
        return StringExtensions.format("CHANNEL-NUMBER {0}", (Object) IntegerExtensions.toString(Integer.valueOf(getChannelNumber())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        IntegerHolder integerHolder = new IntegerHolder(i);
        dataBuffer.write16(getChannelNumber(), i, integerHolder);
        dataBuffer.write16(0, integerHolder.getValue());
    }
}
