package fm.liveswitch;

import java.util.Random;

public class RandomExtensions {
    public static void nextBytes(Random random, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        random.nextBytes(bArr2);
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = bArr2[i];
        }
    }
}
