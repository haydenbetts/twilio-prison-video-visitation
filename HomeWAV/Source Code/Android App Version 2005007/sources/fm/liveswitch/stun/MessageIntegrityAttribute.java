package fm.liveswitch.stun;

import fm.liveswitch.DataBuffer;
import fm.liveswitch.MacContextBase;
import fm.liveswitch.MacType;

public class MessageIntegrityAttribute extends Attribute {
    private byte[] _keyBuffer;
    private DataBuffer _messageBuffer;
    private DataBuffer _valueBuffer;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 20;
    }

    public String toString() {
        return "MESSAGE-INTEGRITY";
    }

    public DataBuffer getMessageBuffer() {
        return this._messageBuffer;
    }

    public int getTypeValue() {
        return Attribute.getMessageIntegrityType();
    }

    public boolean isValid(byte[] bArr) {
        if (getMessageBuffer() != null) {
            int read16 = getMessageBuffer().read16(2);
            try {
                getMessageBuffer().write16((getMessageBuffer().getLength() - 20) + super.getLength(), 2);
                DataBuffer dataBuffer = this._valueBuffer;
                return dataBuffer != null && dataBuffer.sequenceEquals(MacContextBase.compute(MacType.HmacSha1, DataBuffer.wrap(bArr), getMessageBuffer()));
            } finally {
                getMessageBuffer().write16(read16, 2);
            }
        } else {
            throw new RuntimeException(new Exception("Message buffer must be set before writing message-integrity attribute."));
        }
    }

    private MessageIntegrityAttribute() {
    }

    public MessageIntegrityAttribute(byte[] bArr) {
        this._keyBuffer = bArr;
    }

    public static MessageIntegrityAttribute readValueFrom(DataBuffer dataBuffer, int i, int i2, DataBuffer dataBuffer2) {
        MessageIntegrityAttribute messageIntegrityAttribute = new MessageIntegrityAttribute();
        messageIntegrityAttribute._valueBuffer = dataBuffer.subset(i, i2);
        messageIntegrityAttribute.setMessageBuffer(dataBuffer2);
        return messageIntegrityAttribute;
    }

    public void setMessageBuffer(DataBuffer dataBuffer) {
        this._messageBuffer = dataBuffer;
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        if (this._keyBuffer == null) {
            throw new RuntimeException(new Exception("Key buffer must be set before writing message-integrity attribute."));
        } else if (getMessageBuffer() != null) {
            int read16 = getMessageBuffer().read16(2);
            try {
                getMessageBuffer().write16((getMessageBuffer().getLength() - 20) + super.getLength(), 2);
                dataBuffer.write(MacContextBase.compute(MacType.HmacSha1, DataBuffer.wrap(this._keyBuffer), getMessageBuffer()), i);
            } finally {
                getMessageBuffer().write16(read16, 2);
            }
        } else {
            throw new RuntimeException(new Exception("Message buffer must be set before writing message-integrity attribute."));
        }
    }
}
