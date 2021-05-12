package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.IntegerExtensions;
import fm.liveswitch.IntegerHolder;
import fm.liveswitch.StringExtensions;
import fm.liveswitch.Utf8;

public class ErrorCodeAttribute extends Attribute {
    private int __code;
    private String __reason;

    private ErrorCodeAttribute() {
    }

    public ErrorCodeAttribute(int i) {
        this(i, (String) null);
    }

    public ErrorCodeAttribute(int i, String str) {
        setCode(i);
        setReason(str == null ? "" : str);
    }

    public int getCode() {
        return this.__code;
    }

    public String getReason() {
        return this.__reason;
    }

    public int getTypeValue() {
        return Attribute.getErrorCodeType();
    }

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return Utf8.getByteCount(getReason()) + 4;
    }

    public static ErrorCodeAttribute readValueFrom(DataBuffer dataBuffer, int i, int i2) {
        int i3 = i + 2;
        IntegerHolder integerHolder = new IntegerHolder(i3);
        int read8 = dataBuffer.read8(i3, integerHolder);
        int value = integerHolder.getValue();
        IntegerHolder integerHolder2 = new IntegerHolder(value);
        int read82 = dataBuffer.read8(value, integerHolder2);
        String trim = dataBuffer.readUtf8String(integerHolder2.getValue(), i2 - 4).trim();
        ErrorCodeAttribute errorCodeAttribute = new ErrorCodeAttribute();
        errorCodeAttribute.setCode((read8 * 100) + read82);
        errorCodeAttribute.setReason(trim);
        return errorCodeAttribute;
    }

    public void setCode(int i) {
        if (i < 300 || i > 699) {
            throw new RuntimeException(new Exception("code must be in the range of 300 to 699."));
        }
        this.__code = i;
    }

    public void setReason(String str) {
        if (StringExtensions.getLength(str) <= 127) {
            this.__reason = str;
            return;
        }
        throw new RuntimeException(new Exception("reason must have fewer than 128 characters."));
    }

    public String toString() {
        return StringExtensions.format("ERROR-CODE {0} {1}", IntegerExtensions.toString(Integer.valueOf(getCode())), getReason());
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        IntegerHolder integerHolder = new IntegerHolder(i);
        dataBuffer.write16(0, i, integerHolder);
        int value = integerHolder.getValue();
        IntegerHolder integerHolder2 = new IntegerHolder(value);
        dataBuffer.write8(getCode() / 100, value, integerHolder2);
        int value2 = integerHolder2.getValue();
        IntegerHolder integerHolder3 = new IntegerHolder(value2);
        dataBuffer.write8(getCode() % 100, value2, integerHolder3);
        dataBuffer.writeBytes(Utf8.encode(getReason()), integerHolder3.getValue());
    }
}
