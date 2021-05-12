package fm.liveswitch.stun;

import fm.liveswitch.Crc32;
import fm.liveswitch.DataBuffer;

public class FingerprintAttribute extends Attribute {
    private static Crc32 __crc32 = new Crc32(Crc32.getCrc32Polynomial());
    private static long _xorValue = 1398035790;
    private DataBuffer _messageBuffer;

    /* access modifiers changed from: protected */
    public int getValueLength() {
        return 4;
    }

    public String toString() {
        return "FINGERPRINT";
    }

    private static long getCheckSum(DataBuffer dataBuffer) {
        return __crc32.compute(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength());
    }

    public DataBuffer getMessageBuffer() {
        return this._messageBuffer;
    }

    public int getTypeValue() {
        return Attribute.getFingerprintType();
    }

    /* JADX INFO: finally extract failed */
    public static FingerprintAttribute readValueFrom(DataBuffer dataBuffer, int i, DataBuffer dataBuffer2) {
        FingerprintAttribute fingerprintAttribute = new FingerprintAttribute();
        int read16 = dataBuffer2.read16(2);
        try {
            dataBuffer2.write16((dataBuffer2.getLength() - 20) + fingerprintAttribute.getLength(), 2);
            if ((dataBuffer.read32(i) ^ _xorValue) == getCheckSum(dataBuffer2)) {
                dataBuffer2.write16(read16, 2);
                return fingerprintAttribute;
            }
            throw new RuntimeException(new Exception("Supplied checksum does not match calculated checksum."));
        } catch (Throwable th) {
            dataBuffer2.write16(read16, 2);
            throw th;
        }
    }

    public void setMessageBuffer(DataBuffer dataBuffer) {
        this._messageBuffer = dataBuffer;
    }

    /* access modifiers changed from: protected */
    public void writeValueTo(DataBuffer dataBuffer, int i) {
        if (getMessageBuffer() != null) {
            int read16 = getMessageBuffer().read16(2);
            try {
                getMessageBuffer().write16((getMessageBuffer().getLength() - 20) + super.getLength(), 2);
                dataBuffer.write32(getCheckSum(getMessageBuffer()) ^ _xorValue, i);
            } finally {
                getMessageBuffer().write16(read16, 2);
            }
        } else {
            throw new RuntimeException(new Exception("Message buffer must be set before writing fingerprint attribute."));
        }
    }
}
