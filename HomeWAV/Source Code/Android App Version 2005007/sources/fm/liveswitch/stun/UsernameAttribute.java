package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.Utf8;

public class UsernameAttribute extends Attribute {
    private String __value;

    public int getTypeValue() {
        return Attribute.getUsernameType();
    }

    public String getValue() {
        return this.__value;
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return Utf8.getByteCount(getValue());
    }

    public static UsernameAttribute readValueFrom(DataBuffer dataBuffer, int i, int i2) {
        String trim = Utf8.decode(dataBuffer.getData(), dataBuffer.getIndex() + i, i2).trim();
        while (trim.endsWith("\u0000")) {
            trim = StringExtensions.substring(trim, 0, StringExtensions.getLength(trim) - 1);
        }
        UsernameAttribute usernameAttribute = new UsernameAttribute();
        usernameAttribute.setValue(trim);
        return usernameAttribute;
    }

    public void setValue(String str) {
        if (Utf8.getByteCount(str) <= 512) {
            this.__value = str;
            return;
        }
        throw new RuntimeException(new Exception("value may not exceed 512 bytes in UTF-8 encoding."));
    }

    public String toString() {
        return StringExtensions.format("USERNAME {0}", (Object) getValue());
    }

    private UsernameAttribute() {
    }

    public UsernameAttribute(String str) {
        setValue(str);
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        dataBuffer.writeBytes(Utf8.encode(getValue()), i);
    }
}
