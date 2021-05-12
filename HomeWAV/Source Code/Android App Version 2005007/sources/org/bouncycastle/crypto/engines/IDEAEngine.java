package org.bouncycastle.crypto.engines;

import kotlin.UByte;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class IDEAEngine implements BlockCipher {
    private static final int BASE = 65537;
    protected static final int BLOCK_SIZE = 8;
    private static final int MASK = 65535;
    private int[] workingKey = null;

    private int bytesToWord(byte[] bArr, int i) {
        return ((bArr[i] << 8) & UByte.MIN_VALUE) + (bArr[i + 1] & UByte.MAX_VALUE);
    }

    private int[] expandKey(byte[] bArr) {
        int i;
        int[] iArr = new int[52];
        int i2 = 0;
        if (bArr.length < 16) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        while (true) {
            if (i2 >= 8) {
                break;
            }
            iArr[i2] = bytesToWord(bArr, i2 * 2);
            i2++;
        }
        for (i = 8; i < 52; i++) {
            int i3 = i & 7;
            if (i3 < 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 6] >> 7)) & 65535;
            } else if (i3 == 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            } else {
                iArr[i] = (((iArr[i - 15] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            }
        }
        return iArr;
    }

    private int[] generateWorkingKey(boolean z, byte[] bArr) {
        return z ? expandKey(bArr) : invertKey(expandKey(bArr));
    }

    private void ideaFunc(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToWord = bytesToWord(bArr, i);
        int bytesToWord2 = bytesToWord(bArr, i + 2);
        int bytesToWord3 = bytesToWord(bArr, i + 4);
        int bytesToWord4 = bytesToWord(bArr, i + 6);
        int i3 = 0;
        int i4 = bytesToWord3;
        int i5 = bytesToWord2;
        int i6 = bytesToWord;
        int i7 = 0;
        while (i3 < 8) {
            int i8 = i7 + 1;
            int mul = mul(i6, iArr[i7]);
            int i9 = i8 + 1;
            int i10 = (i5 + iArr[i8]) & 65535;
            int i11 = i9 + 1;
            int i12 = (i4 + iArr[i9]) & 65535;
            int i13 = i11 + 1;
            int mul2 = mul(bytesToWord4, iArr[i11]);
            int i14 = i13 + 1;
            int mul3 = mul(i12 ^ mul, iArr[i13]);
            int mul4 = mul(((i10 ^ mul2) + mul3) & 65535, iArr[i14]);
            int i15 = (mul3 + mul4) & 65535;
            bytesToWord4 = mul2 ^ i15;
            i4 = i15 ^ i10;
            i3++;
            i5 = i12 ^ mul4;
            i6 = mul ^ mul4;
            i7 = i14 + 1;
        }
        int i16 = i7 + 1;
        wordToBytes(mul(i6, iArr[i7]), bArr2, i2);
        int i17 = i16 + 1;
        wordToBytes(i4 + iArr[i16], bArr2, i2 + 2);
        wordToBytes(i5 + iArr[i17], bArr2, i2 + 4);
        wordToBytes(mul(bytesToWord4, iArr[i17 + 1]), bArr2, i2 + 6);
    }

    private int[] invertKey(int[] iArr) {
        int[] iArr2 = new int[52];
        int mulInv = mulInv(iArr[0]);
        int i = 1;
        int addInv = addInv(iArr[1]);
        int addInv2 = addInv(iArr[2]);
        iArr2[51] = mulInv(iArr[3]);
        iArr2[50] = addInv2;
        iArr2[49] = addInv;
        int i2 = 48;
        iArr2[48] = mulInv;
        int i3 = 4;
        while (i < 8) {
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            int i6 = i4 + 1;
            int i7 = i2 - 1;
            iArr2[i7] = iArr[i4];
            int i8 = i7 - 1;
            iArr2[i8] = i5;
            int i9 = i6 + 1;
            int mulInv2 = mulInv(iArr[i6]);
            int i10 = i9 + 1;
            int addInv3 = addInv(iArr[i9]);
            int i11 = i10 + 1;
            int addInv4 = addInv(iArr[i10]);
            int i12 = i8 - 1;
            iArr2[i12] = mulInv(iArr[i11]);
            int i13 = i12 - 1;
            iArr2[i13] = addInv3;
            int i14 = i13 - 1;
            iArr2[i14] = addInv4;
            i2 = i14 - 1;
            iArr2[i2] = mulInv2;
            i++;
            i3 = i11 + 1;
        }
        int i15 = i3 + 1;
        int i16 = iArr[i3];
        int i17 = i15 + 1;
        int i18 = i2 - 1;
        iArr2[i18] = iArr[i15];
        int i19 = i18 - 1;
        iArr2[i19] = i16;
        int i20 = i17 + 1;
        int mulInv3 = mulInv(iArr[i17]);
        int i21 = i20 + 1;
        int addInv5 = addInv(iArr[i20]);
        int i22 = i21 + 1;
        int addInv6 = addInv(iArr[i21]);
        int i23 = i19 - 1;
        iArr2[i23] = mulInv(iArr[i22]);
        int i24 = i23 - 1;
        iArr2[i24] = addInv6;
        int i25 = i24 - 1;
        iArr2[i25] = addInv5;
        iArr2[i25 - 1] = mulInv3;
        return iArr2;
    }

    private int mul(int i, int i2) {
        int i3;
        if (i == 0) {
            i3 = 65537 - i2;
        } else if (i2 == 0) {
            i3 = 65537 - i;
        } else {
            int i4 = i * i2;
            int i5 = i4 & 65535;
            int i6 = i4 >>> 16;
            i3 = (i5 - i6) + (i5 < i6 ? 1 : 0);
        }
        return i3 & 65535;
    }

    private int mulInv(int i) {
        if (i < 2) {
            return i;
        }
        int i2 = 65537 / i;
        int i3 = 65537 % i;
        int i4 = 1;
        while (i3 != 1) {
            int i5 = i / i3;
            i %= i3;
            i4 = (i4 + (i5 * i2)) & 65535;
            if (i == 1) {
                return i4;
            }
            int i6 = i3 / i;
            i3 %= i;
            i2 = (i2 + (i6 * i4)) & 65535;
        }
        return (1 - i2) & 65535;
    }

    private void wordToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    /* access modifiers changed from: package-private */
    public int addInv(int i) {
        return (0 - i) & 65535;
    }

    public String getAlgorithmName() {
        return "IDEA";
    }

    public int getBlockSize() {
        return 8;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + cipherParameters.getClass().getName());
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.workingKey;
        if (iArr == null) {
            throw new IllegalStateException("IDEA engine not initialised");
        } else if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 8 <= bArr2.length) {
            ideaFunc(iArr, bArr, i, bArr2, i2);
            return 8;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
    }
}
