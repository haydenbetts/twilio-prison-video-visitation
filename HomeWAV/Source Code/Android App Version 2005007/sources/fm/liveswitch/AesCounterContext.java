package fm.liveswitch;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesCounterContext {
    private Cipher cipher;

    public void clear() {
    }

    public AesCounterContext(DataBuffer dataBuffer) {
        try {
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            this.cipher = instance;
            instance.init(1, new SecretKeySpec(dataBuffer.getData(), dataBuffer.getIndex(), dataBuffer.getLength(), "AES"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean generateKeystream(DataBuffer dataBuffer, int i, byte[] bArr) {
        int i2 = 0;
        while (i2 < i) {
            try {
                this.cipher.update(bArr, 0, 16, dataBuffer.getData(), dataBuffer.getIndex() + i2);
                incrementCounter(bArr);
                i2 += 16;
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    private static void incrementCounter(byte[] bArr) {
        int castInteger = BitAssistant.castInteger(bArr[15]);
        if (castInteger == 255) {
            bArr[15] = 0;
            int castInteger2 = BitAssistant.castInteger(bArr[14]);
            if (castInteger2 == 255) {
                bArr[14] = 0;
            } else {
                bArr[14] = (byte) (castInteger2 + 1);
            }
        } else {
            bArr[15] = (byte) (castInteger + 1);
        }
    }
}
