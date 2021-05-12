package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import kotlin.UByte;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.encoders.Hex;

public class GMSSRootSig {
    private long big8;
    private int checksum;
    private int counter;
    private GMSSRandom gmssRandom;
    private byte[] hash;
    private int height;
    private int ii;
    private int k;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[] privateKeyOTS;
    private int r;
    private byte[] seed;
    private byte[] sign;
    private int steps;
    private int test;
    private long test8;
    private int w;

    public GMSSRootSig(Digest digest, int i, int i2) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        this.w = i;
        this.height = i2;
        this.k = (1 << i) - 1;
        this.messagesize = (int) Math.ceil(((double) (digestSize << 3)) / ((double) i));
    }

    public GMSSRootSig(Digest digest, byte[][] bArr, int[] iArr) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.counter = iArr[0];
        this.test = iArr[1];
        this.ii = iArr[2];
        this.r = iArr[3];
        this.steps = iArr[4];
        this.keysize = iArr[5];
        this.height = iArr[6];
        this.w = iArr[7];
        this.checksum = iArr[8];
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        int i = this.w;
        this.k = (1 << i) - 1;
        this.messagesize = (int) Math.ceil(((double) (digestSize << 3)) / ((double) i));
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.hash = bArr[2];
        this.sign = bArr[3];
        this.test8 = (((long) (bArr[4][1] & UByte.MAX_VALUE)) << 8) | ((long) (bArr[4][0] & UByte.MAX_VALUE)) | (((long) (bArr[4][2] & UByte.MAX_VALUE)) << 16) | (((long) (bArr[4][3] & UByte.MAX_VALUE)) << 24) | (((long) (bArr[4][4] & UByte.MAX_VALUE)) << 32) | (((long) (bArr[4][5] & UByte.MAX_VALUE)) << 40) | (((long) (bArr[4][6] & UByte.MAX_VALUE)) << 48) | (((long) (bArr[4][7] & UByte.MAX_VALUE)) << 56);
        this.big8 = ((long) (bArr[4][8] & UByte.MAX_VALUE)) | (((long) (bArr[4][9] & UByte.MAX_VALUE)) << 8) | (((long) (bArr[4][10] & UByte.MAX_VALUE)) << 16) | (((long) (bArr[4][11] & UByte.MAX_VALUE)) << 24) | (((long) (bArr[4][12] & UByte.MAX_VALUE)) << 32) | (((long) (bArr[4][13] & UByte.MAX_VALUE)) << 40) | (((long) (bArr[4][14] & UByte.MAX_VALUE)) << 48) | (((long) (bArr[4][15] & UByte.MAX_VALUE)) << 56);
    }

    private void oneStep() {
        long j;
        int i;
        int i2 = this.w;
        if (8 % i2 == 0) {
            int i3 = this.test;
            if (i3 == 0) {
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
                int i4 = this.ii;
                if (i4 < this.mdsize) {
                    byte[] bArr = this.hash;
                    this.test = bArr[i4] & this.k;
                    bArr[i4] = (byte) (bArr[i4] >>> this.w);
                } else {
                    int i5 = this.checksum;
                    this.test = this.k & i5;
                    this.checksum = i5 >>> this.w;
                }
            } else if (i3 > 0) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.privateKeyOTS;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr3;
                this.messDigestOTS.doFinal(bArr3, 0);
                this.test--;
            }
            if (this.test == 0) {
                byte[] bArr4 = this.privateKeyOTS;
                byte[] bArr5 = this.sign;
                int i6 = this.counter;
                int i7 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr5, i6 * i7, i7);
                int i8 = this.counter + 1;
                this.counter = i8;
                if (i8 % (8 / this.w) == 0) {
                    this.ii++;
                    return;
                }
                return;
            }
            return;
        }
        if (i2 < 8) {
            int i9 = this.test;
            if (i9 == 0) {
                int i10 = this.counter;
                if (i10 % 8 == 0 && this.ii < (i = this.mdsize)) {
                    this.big8 = 0;
                    if (i10 < ((i / i2) << 3)) {
                        for (int i11 = 0; i11 < this.w; i11++) {
                            long j2 = this.big8;
                            byte[] bArr6 = this.hash;
                            int i12 = this.ii;
                            this.big8 = j2 ^ ((long) ((bArr6[i12] & UByte.MAX_VALUE) << (i11 << 3)));
                            this.ii = i12 + 1;
                        }
                    } else {
                        for (int i13 = 0; i13 < this.mdsize % this.w; i13++) {
                            long j3 = this.big8;
                            byte[] bArr7 = this.hash;
                            int i14 = this.ii;
                            this.big8 = j3 ^ ((long) ((bArr7[i14] & UByte.MAX_VALUE) << (i13 << 3)));
                            this.ii = i14 + 1;
                        }
                    }
                }
                if (this.counter == this.messagesize) {
                    this.big8 = (long) this.checksum;
                }
                this.test = (int) (this.big8 & ((long) this.k));
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (i9 > 0) {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr8 = this.privateKeyOTS;
                digest2.update(bArr8, 0, bArr8.length);
                byte[] bArr9 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr9;
                this.messDigestOTS.doFinal(bArr9, 0);
                this.test--;
            }
            if (this.test == 0) {
                byte[] bArr10 = this.privateKeyOTS;
                byte[] bArr11 = this.sign;
                int i15 = this.counter;
                int i16 = this.mdsize;
                System.arraycopy(bArr10, 0, bArr11, i15 * i16, i16);
                this.big8 >>>= this.w;
            } else {
                return;
            }
        } else if (i2 < 57) {
            long j4 = this.test8;
            if (j4 == 0) {
                this.big8 = 0;
                this.ii = 0;
                int i17 = this.r;
                int i18 = i17 % 8;
                int i19 = i17 >>> 3;
                int i20 = this.mdsize;
                if (i19 < i20) {
                    if (i17 <= (i20 << 3) - i2) {
                        int i21 = i17 + i2;
                        this.r = i21;
                        i20 = (i21 + 7) >>> 3;
                    } else {
                        this.r = i17 + i2;
                    }
                    while (true) {
                        j = this.big8;
                        if (i19 >= i20) {
                            break;
                        }
                        byte b = this.hash[i19] & UByte.MAX_VALUE;
                        int i22 = this.ii;
                        this.big8 = j ^ ((long) (b << (i22 << 3)));
                        this.ii = i22 + 1;
                        i19++;
                    }
                    long j5 = j >>> i18;
                    this.big8 = j5;
                    this.test8 = j5 & ((long) this.k);
                } else {
                    int i23 = this.checksum;
                    this.test8 = (long) (this.k & i23);
                    this.checksum = i23 >>> i2;
                }
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (j4 > 0) {
                Digest digest3 = this.messDigestOTS;
                byte[] bArr12 = this.privateKeyOTS;
                digest3.update(bArr12, 0, bArr12.length);
                byte[] bArr13 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr13;
                this.messDigestOTS.doFinal(bArr13, 0);
                this.test8--;
            }
            if (this.test8 == 0) {
                byte[] bArr14 = this.privateKeyOTS;
                byte[] bArr15 = this.sign;
                int i24 = this.counter;
                int i25 = this.mdsize;
                System.arraycopy(bArr14, 0, bArr15, i24 * i25, i25);
            } else {
                return;
            }
        } else {
            return;
        }
        this.counter++;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public byte[] getSig() {
        return this.sign;
    }

    public byte[][] getStatByte() {
        int[] iArr = new int[2];
        iArr[1] = this.mdsize;
        iArr[0] = 5;
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, iArr);
        bArr[0] = this.privateKeyOTS;
        bArr[1] = this.seed;
        bArr[2] = this.hash;
        bArr[3] = this.sign;
        bArr[4] = getStatLong();
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.counter, this.test, this.ii, this.r, this.steps, this.keysize, this.height, this.w, this.checksum};
    }

    public byte[] getStatLong() {
        long j = this.test8;
        long j2 = this.big8;
        return new byte[]{(byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 32) & 255)), (byte) ((int) ((j >> 40) & 255)), (byte) ((int) ((j >> 48) & 255)), (byte) ((int) ((j >> 56) & 255)), (byte) ((int) (j2 & 255)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 24) & 255)), (byte) ((int) ((j2 >> 32) & 255)), (byte) ((int) ((j2 >> 40) & 255)), (byte) ((int) ((j2 >> 48) & 255)), (byte) ((int) ((j2 >> 56) & 255))};
    }

    public void initSign(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        byte[] bArr3 = bArr2;
        this.hash = new byte[this.mdsize];
        this.messDigestOTS.update(bArr3, 0, bArr3.length);
        byte[] bArr4 = new byte[this.messDigestOTS.getDigestSize()];
        this.hash = bArr4;
        this.messDigestOTS.doFinal(bArr4, 0);
        int i3 = this.mdsize;
        byte[] bArr5 = new byte[i3];
        System.arraycopy(this.hash, 0, bArr5, 0, i3);
        int log = getLog((this.messagesize << this.w) + 1);
        int i4 = this.w;
        int i5 = 8;
        if (8 % i4 == 0) {
            int i6 = 8 / i4;
            i = 0;
            for (int i7 = 0; i7 < this.mdsize; i7++) {
                for (int i8 = 0; i8 < i6; i8++) {
                    i += bArr5[i7] & this.k;
                    bArr5[i7] = (byte) (bArr5[i7] >>> this.w);
                }
            }
            int i9 = (this.messagesize << this.w) - i;
            this.checksum = i9;
            int i10 = 0;
            while (i10 < log) {
                i += this.k & i9;
                int i11 = this.w;
                i9 >>>= i11;
                i10 += i11;
            }
        } else if (i4 < 8) {
            int i12 = this.mdsize / i4;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i13 < i12) {
                long j = 0;
                for (int i16 = 0; i16 < this.w; i16++) {
                    j ^= (long) ((bArr5[i14] & UByte.MAX_VALUE) << (i16 << 3));
                    i14++;
                }
                int i17 = 0;
                while (i17 < i5) {
                    i15 += (int) (((long) this.k) & j);
                    j >>>= this.w;
                    i17++;
                    i12 = i12;
                    i5 = 8;
                }
                int i18 = i12;
                i13++;
                i5 = 8;
            }
            int i19 = this.mdsize % this.w;
            long j2 = 0;
            for (int i20 = 0; i20 < i19; i20++) {
                j2 ^= (long) ((bArr5[i14] & UByte.MAX_VALUE) << (i20 << 3));
                i14++;
            }
            int i21 = i19 << 3;
            int i22 = 0;
            while (i22 < i21) {
                i15 += (int) (((long) this.k) & j2);
                int i23 = this.w;
                j2 >>>= i23;
                i22 += i23;
            }
            int i24 = (this.messagesize << this.w) - i15;
            this.checksum = i24;
            int i25 = i15;
            int i26 = 0;
            while (i26 < log) {
                i25 = i + (this.k & i24);
                int i27 = this.w;
                i24 >>>= i27;
                i26 += i27;
            }
        } else if (i4 < 57) {
            int i28 = 0;
            int i29 = 0;
            while (true) {
                i2 = this.mdsize;
                int i30 = this.w;
                if (i28 > (i2 << 3) - i30) {
                    break;
                }
                int i31 = i28 % 8;
                i28 += i30;
                int i32 = (i28 + 7) >>> 3;
                long j3 = 0;
                int i33 = 0;
                for (int i34 = i28 >>> 3; i34 < i32; i34++) {
                    j3 ^= (long) ((bArr5[i34] & UByte.MAX_VALUE) << (i33 << 3));
                    i33++;
                }
                i29 = (int) (((long) i29) + ((j3 >>> i31) & ((long) this.k)));
            }
            int i35 = i28 >>> 3;
            if (i35 < i2) {
                int i36 = i28 % 8;
                int i37 = 0;
                long j4 = 0;
                while (i35 < this.mdsize) {
                    j4 ^= (long) ((bArr5[i35] & UByte.MAX_VALUE) << (i37 << 3));
                    i37++;
                    i35++;
                }
                i29 = (int) (((long) i29) + ((j4 >>> i36) & ((long) this.k)));
            }
            int i38 = (this.messagesize << this.w) - i29;
            this.checksum = i38;
            int i39 = i29;
            int i40 = 0;
            while (i40 < log) {
                i39 = i + (this.k & i38);
                int i41 = this.w;
                i38 >>>= i41;
                i40 += i41;
            }
        } else {
            i = 0;
        }
        int ceil = this.messagesize + ((int) Math.ceil(((double) log) / ((double) this.w)));
        this.keysize = ceil;
        this.steps = (int) Math.ceil(((double) (ceil + i)) / ((double) (1 << this.height)));
        int i42 = this.keysize;
        int i43 = this.mdsize;
        this.sign = new byte[(i42 * i43)];
        this.counter = 0;
        this.test = 0;
        this.ii = 0;
        this.test8 = 0;
        this.r = 0;
        this.privateKeyOTS = new byte[i43];
        byte[] bArr6 = new byte[i43];
        this.seed = bArr6;
        System.arraycopy(bArr, 0, bArr6, 0, i43);
    }

    public String toString() {
        String str = "" + this.big8 + "  ";
        int[] statInt = getStatInt();
        int[] iArr = new int[2];
        iArr[1] = this.mdsize;
        iArr[0] = 5;
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, iArr);
        byte[][] statByte = getStatByte();
        for (int i = 0; i < 9; i++) {
            str = str + statInt[i] + " ";
        }
        for (int i2 = 0; i2 < 5; i2++) {
            str = str + new String(Hex.encode(statByte[i2])) + " ";
        }
        return str;
    }

    public boolean updateSign() {
        for (int i = 0; i < this.steps; i++) {
            if (this.counter < this.keysize) {
                oneStep();
            }
            if (this.counter == this.keysize) {
                return true;
            }
        }
        return false;
    }
}
