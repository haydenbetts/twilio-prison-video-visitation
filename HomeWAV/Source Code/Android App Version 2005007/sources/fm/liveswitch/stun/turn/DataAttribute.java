package fm.liveswitch.stun.turn;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.stun.Attribute;

public class DataAttribute extends Attribute {
    private DataBuffer _data;

    private DataAttribute() {
    }

    public DataAttribute(DataBuffer dataBuffer) {
        setData(dataBuffer);
    }

    public DataBuffer getData() {
        return this._data;
    }

    public int getTypeValue() {
        return Attribute.getDataType();
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return getData().getLength();
    }

    public static DataAttribute readValueFrom(DataBuffer dataBuffer, int i, int i2) {
        DataAttribute dataAttribute = new DataAttribute();
        dataAttribute.setData(dataBuffer.subset(i, i2));
        return dataAttribute;
    }

    public void setData(DataBuffer dataBuffer) {
        this._data = dataBuffer;
    }

    public String toString() {
        return StringExtensions.format("DATA ({0} bytes)", (Object) IntegerExtensions.toString(Integer.valueOf(getData().getLength())));
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.write(getData(), i);
    }
}
