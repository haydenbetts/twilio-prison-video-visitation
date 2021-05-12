package fm.liveswitch;

import java.util.Random;

public class Randomizer {
    private Random random = new Random();

    public int next() {
        return this.random.nextInt();
    }

    public int next(int i) {
        return this.random.nextInt(i);
    }

    public int next(int i, int i2) {
        return i + next(i2 - i);
    }

    public void nextBytes(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        this.random.nextBytes(bArr2);
        for (int i = 0; i < length; i++) {
            bArr[i] = bArr2[i];
        }
    }

    public double nextDouble() {
        return this.random.nextDouble();
    }

    public String randomString(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(next(36)));
        }
        return sb.toString();
    }
}
