package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.Utf8;

public class NonceAttribute extends Attribute {
    private String __value;

    public int getTypeValue() {
        return Attribute.getNonceType();
    }

    public String getValue() {
        return this.__value;
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return Utf8.getByteCount(getValue());
    }

    private NonceAttribute() {
    }

    public NonceAttribute(String str) {
        setValue(str);
    }

    public static NonceAttribute readValueFrom(DataBuffer dataBuffer, int i, int i2) {
        String trim = Utf8.decode(dataBuffer.getData(), dataBuffer.getIndex() + i, i2).trim();
        while (trim.endsWith("\u0000")) {
            trim = StringExtensions.substring(trim, 0, StringExtensions.getLength(trim) - 1);
        }
        NonceAttribute nonceAttribute = new NonceAttribute();
        nonceAttribute.setValue(trim);
        return nonceAttribute;
    }

    public void setValue(String str) {
        if (StringExtensions.getLength(str) <= 127) {
            this.__value = str;
            return;
        }
        throw new RuntimeException(new Exception("value must have fewer than 128 characters."));
    }

    public String toString() {
        return StringExtensions.format("NONCE {0}", (Object) getValue());
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.writeBytes(Utf8.encode(getValue()), i);
    }
}
