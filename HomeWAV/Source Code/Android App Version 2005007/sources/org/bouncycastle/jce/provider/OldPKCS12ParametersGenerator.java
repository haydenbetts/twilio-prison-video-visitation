package org.bouncycastle.jce.provider;

import kotlin.UByte;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

class OldPKCS12ParametersGenerator extends PBEParametersGenerator {
    public static final int IV_MATERIAL = 2;
    public static final int KEY_MATERIAL = 1;
    public static final int MAC_MATERIAL = 3;
    private Digest digest;
    private int u;
    private int v;

    public OldPKCS12ParametersGenerator(Digest digest2) {
        this.digest = digest2;
        if (digest2 instanceof MD5Digest) {
            this.u = 16;
        } else if (!(digest2 instanceof SHA1Digest) && !(digest2 instanceof RIPEMD160Digest)) {
            throw new IllegalArgumentException("Digest " + digest2.getAlgorithmName() + " unsupported");
        } else {
            this.u = 20;
        }
        this.v = 64;
    }

    private void adjust(byte[] bArr, int i, byte[] bArr2) {
        int i2 = (bArr2[bArr2.length - 1] & UByte.MAX_VALUE) + (bArr[(bArr2.length + i) - 1] & UByte.MAX_VALUE) + 1;
        bArr[(bArr2.length + i) - 1] = (byte) i2;
        int i3 = i2 >>> 8;
        for (int length = bArr2.length - 2; length >= 0; length--) {
            int i4 = i + length;
            int i5 = i3 + (bArr2[length] & UByte.MAX_VALUE) + (bArr[i4] & UByte.MAX_VALUE);
            bArr[i4] = (byte) i5;
            i3 = i5 >>> 8;
        }
    }

    private byte[] generateDerivedKey(int i, int i2) {
        byte[] bArr;
        byte[] bArr2;
        int i3 = i2;
        int i4 = this.v;
        byte[] bArr3 = new byte[i4];
        byte[] bArr4 = new byte[i3];
        int i5 = 0;
        for (int i6 = 0; i6 != i4; i6++) {
            bArr3[i6] = (byte) i;
        }
        if (this.salt == null || this.salt.length == 0) {
            bArr = new byte[0];
        } else {
            int i7 = this.v;
            int length = this.salt.length;
            int i8 = this.v;
            int i9 = i7 * (((length + i8) - 1) / i8);
            bArr = new byte[i9];
            for (int i10 = 0; i10 != i9; i10++) {
                bArr[i10] = this.salt[i10 % this.salt.length];
            }
        }
        if (this.password == null || this.password.length == 0) {
            bArr2 = new byte[0];
        } else {
            int i11 = this.v;
            int length2 = this.password.length;
            int i12 = this.v;
            int i13 = i11 * (((length2 + i12) - 1) / i12);
            bArr2 = new byte[i13];
            for (int i14 = 0; i14 != i13; i14++) {
                bArr2[i14] = this.password[i14 % this.password.length];
            }
        }
        int length3 = bArr.length + bArr2.length;
        byte[] bArr5 = new byte[length3];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        int i15 = this.v;
        byte[] bArr6 = new byte[i15];
        int i16 = this.u;
        int i17 = ((i3 + i16) - 1) / i16;
        int i18 = 1;
        while (i18 <= i17) {
            int i19 = this.u;
            byte[] bArr7 = new byte[i19];
            this.digest.update(bArr3, i5, i4);
            this.digest.update(bArr5, i5, length3);
            this.digest.doFinal(bArr7, i5);
            for (int i20 = 1; i20 != this.iterationCount; i20++) {
                this.digest.update(bArr7, i5, i19);
                this.digest.doFinal(bArr7, i5);
            }
            for (int i21 = 0; i21 != i15; i21++) {
                bArr6[i18] = bArr7[i21 % i19];
            }
            int i22 = 0;
            while (true) {
                int i23 = this.v;
                if (i22 == length3 / i23) {
                    break;
                }
                adjust(bArr5, i23 * i22, bArr6);
                i22++;
            }
            if (i18 == i17) {
                int i24 = i18 - 1;
                int i25 = this.u;
                System.arraycopy(bArr7, 0, bArr4, i24 * i25, i3 - (i24 * i25));
            } else {
                System.arraycopy(bArr7, 0, bArr4, (i18 - 1) * this.u, i19);
            }
            i18++;
            i5 = 0;
        }
        return bArr4;
    }

    public CipherParameters generateDerivedMacParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(3, i2), 0, i2);
    }

    public CipherParameters generateDerivedParameters(int i) {
        int i2 = i / 8;
        return new KeyParameter(generateDerivedKey(1, i2), 0, i2);
    }

    public CipherParameters generateDerivedParameters(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] generateDerivedKey = generateDerivedKey(1, i3);
        return new ParametersWithIV(new KeyParameter(generateDerivedKey, 0, i3), generateDerivedKey(2, i4), 0, i4);
    }
}
