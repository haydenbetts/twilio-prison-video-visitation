package org.bouncycastle.math.raw;

import java.math.BigInteger;
import org.bouncycastle.util.Pack;

public abstract class Nat160 {
    private static final long M = 4294967295L;

    public static int add(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L);
        iArr3[4] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int addBothTo(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + (((long) iArr3[0]) & 4294967295L) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L) + (((long) iArr3[1]) & 4294967295L);
        iArr3[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L) + (((long) iArr3[2]) & 4294967295L);
        iArr3[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L) + (((long) iArr3[3]) & 4294967295L);
        iArr3[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (((long) iArr2[4]) & 4294967295L) + (((long) iArr3[4]) & 4294967295L);
        iArr3[4] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int addTo(int[] iArr, int i, int[] iArr2, int i2, int i3) {
        int i4 = i2 + 0;
        long j = (((long) i3) & 4294967295L) + (((long) iArr[i + 0]) & 4294967295L) + (((long) iArr2[i4]) & 4294967295L);
        iArr2[i4] = (int) j;
        int i5 = i2 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i + 1]) & 4294967295L) + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j2;
        int i6 = i2 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i + 2]) & 4294967295L) + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j3;
        int i7 = i2 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i + 3]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j4;
        int i8 = i2 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i + 4]) & 4294967295L) + (4294967295L & ((long) iArr2[i8]));
        iArr2[i8] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int addTo(int[] iArr, int[] iArr2) {
        long j = (((long) iArr[0]) & 4294967295L) + (((long) iArr2[0]) & 4294967295L) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >>> 32) + (((long) iArr[1]) & 4294967295L) + (((long) iArr2[1]) & 4294967295L);
        iArr2[1] = (int) j2;
        long j3 = (j2 >>> 32) + (((long) iArr[2]) & 4294967295L) + (((long) iArr2[2]) & 4294967295L);
        iArr2[2] = (int) j3;
        long j4 = (j3 >>> 32) + (((long) iArr[3]) & 4294967295L) + (((long) iArr2[3]) & 4294967295L);
        iArr2[3] = (int) j4;
        long j5 = (j4 >>> 32) + (((long) iArr[4]) & 4294967295L) + (4294967295L & ((long) iArr2[4]));
        iArr2[4] = (int) j5;
        return (int) (j5 >>> 32);
    }

    public static int addToEachOther(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = i + 0;
        int i4 = i2 + 0;
        long j = (((long) iArr[i3]) & 4294967295L) + (((long) iArr2[i4]) & 4294967295L) + 0;
        int i5 = (int) j;
        iArr[i3] = i5;
        iArr2[i4] = i5;
        int i6 = i + 1;
        int i7 = i2 + 1;
        long j2 = (j >>> 32) + (((long) iArr[i6]) & 4294967295L) + (((long) iArr2[i7]) & 4294967295L);
        int i8 = (int) j2;
        iArr[i6] = i8;
        iArr2[i7] = i8;
        int i9 = i + 2;
        int i10 = i2 + 2;
        long j3 = (j2 >>> 32) + (((long) iArr[i9]) & 4294967295L) + (((long) iArr2[i10]) & 4294967295L);
        int i11 = (int) j3;
        iArr[i9] = i11;
        iArr2[i10] = i11;
        int i12 = i + 3;
        int i13 = i2 + 3;
        long j4 = (j3 >>> 32) + (((long) iArr[i12]) & 4294967295L) + (((long) iArr2[i13]) & 4294967295L);
        int i14 = (int) j4;
        iArr[i12] = i14;
        iArr2[i13] = i14;
        int i15 = i + 4;
        int i16 = i2 + 4;
        long j5 = (j4 >>> 32) + (((long) iArr[i15]) & 4294967295L) + (4294967295L & ((long) iArr2[i16]));
        int i17 = (int) j5;
        iArr[i15] = i17;
        iArr2[i16] = i17;
        return (int) (j5 >>> 32);
    }

    public static void copy(int[] iArr, int[] iArr2) {
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        iArr2[2] = iArr[2];
        iArr2[3] = iArr[3];
        iArr2[4] = iArr[4];
    }

    public static int[] create() {
        return new int[5];
    }

    public static int[] createExt() {
        return new int[10];
    }

    public static boolean diff(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        boolean gte = gte(iArr, i, iArr2, i2);
        if (gte) {
            sub(iArr, i, iArr2, i2, iArr3, i3);
        } else {
            sub(iArr2, i2, iArr, i, iArr3, i3);
        }
        return gte;
    }

    public static boolean eq(int[] iArr, int[] iArr2) {
        for (int i = 4; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] fromBigInteger(BigInteger bigInteger) {
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 160) {
            throw new IllegalArgumentException();
        }
        int[] create = create();
        int i = 0;
        while (bigInteger.signum() != 0) {
            create[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
            i++;
        }
        return create;
    }

    public static int getBit(int[] iArr, int i) {
        int i2;
        if (i == 0) {
            i2 = iArr[0];
        } else {
            int i3 = i >> 5;
            if (i3 < 0 || i3 >= 5) {
                return 0;
            }
            i2 = iArr[i3] >>> (i & 31);
        }
        return i2 & 1;
    }

    public static boolean gte(int[] iArr, int i, int[] iArr2, int i2) {
        for (int i3 = 4; i3 >= 0; i3--) {
            int i4 = iArr[i + i3] ^ Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE ^ iArr2[i2 + i3];
            if (i4 < i5) {
                return false;
            }
            if (i4 > i5) {
                return true;
            }
        }
        return true;
    }

    public static boolean gte(int[] iArr, int[] iArr2) {
        for (int i = 4; i >= 0; i--) {
            int i2 = iArr[i] ^ Integer.MIN_VALUE;
            int i3 = Integer.MIN_VALUE ^ iArr2[i];
            if (i2 < i3) {
                return false;
            }
            if (i2 > i3) {
                return true;
            }
        }
        return true;
    }

    public static boolean isOne(int[] iArr) {
        if (iArr[0] != 1) {
            return false;
        }
        for (int i = 1; i < 5; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int[] iArr) {
        for (int i = 0; i < 5; i++) {
            if (iArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void mul(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((long) iArr2[i2 + 0]) & 4294967295L;
        long j2 = ((long) iArr2[i2 + 1]) & 4294967295L;
        long j3 = ((long) iArr2[i2 + 2]) & 4294967295L;
        long j4 = ((long) iArr2[i2 + 3]) & 4294967295L;
        long j5 = ((long) iArr2[i2 + 4]) & 4294967295L;
        long j6 = ((long) iArr[i + 0]) & 4294967295L;
        long j7 = (j6 * j) + 0;
        iArr3[i3 + 0] = (int) j7;
        long j8 = (j7 >>> 32) + (j6 * j2);
        long j9 = j2;
        iArr3[i3 + 1] = (int) j8;
        long j10 = (j8 >>> 32) + (j6 * j3);
        iArr3[i3 + 2] = (int) j10;
        long j11 = (j10 >>> 32) + (j6 * j4);
        iArr3[i3 + 3] = (int) j11;
        long j12 = (j11 >>> 32) + (j6 * j5);
        iArr3[i3 + 4] = (int) j12;
        iArr3[i3 + 5] = (int) (j12 >>> 32);
        int i4 = 1;
        int i5 = i3;
        int i6 = 1;
        while (i6 < 5) {
            i5 += i4;
            long j13 = ((long) iArr[i + i6]) & 4294967295L;
            int i7 = i5 + 0;
            long j14 = (j13 * j) + (((long) iArr3[i7]) & 4294967295L) + 0;
            iArr3[i7] = (int) j14;
            int i8 = i5 + 1;
            long j15 = (j14 >>> 32) + (j13 * j9) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j15;
            int i9 = i5 + 2;
            long j16 = (j15 >>> 32) + (j13 * j3) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j16;
            int i10 = i5 + 3;
            long j17 = (j16 >>> 32) + (j13 * j4) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j17;
            int i11 = i5 + 4;
            long j18 = (j17 >>> 32) + (j13 * j5) + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j18;
            iArr3[i5 + 5] = (int) (j18 >>> 32);
            i6++;
            j3 = j3;
            j = j;
            i4 = 1;
        }
    }

    public static void mul(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((long) iArr2[0]) & 4294967295L;
        int i = 1;
        long j2 = ((long) iArr2[1]) & 4294967295L;
        long j3 = ((long) iArr2[3]) & 4294967295L;
        long j4 = ((long) iArr2[4]) & 4294967295L;
        long j5 = ((long) iArr2[2]) & 4294967295L;
        long j6 = ((long) iArr[0]) & 4294967295L;
        long j7 = (j6 * j) + 0;
        iArr3[0] = (int) j7;
        long j8 = (j7 >>> 32) + (j6 * j2);
        iArr3[1] = (int) j8;
        long j9 = (j8 >>> 32) + (j6 * j5);
        iArr3[2] = (int) j9;
        long j10 = (j9 >>> 32) + (j6 * j3);
        iArr3[3] = (int) j10;
        long j11 = (j10 >>> 32) + (j6 * j4);
        iArr3[4] = (int) j11;
        int i2 = (int) (j11 >>> 32);
        iArr3[5] = i2;
        for (int i3 = 5; i < i3; i3 = 5) {
            long j12 = ((long) iArr[i]) & 4294967295L;
            int i4 = i + 0;
            long j13 = (j12 * j) + (((long) iArr3[i4]) & 4294967295L) + 0;
            iArr3[i4] = (int) j13;
            int i5 = i + 1;
            long j14 = j2;
            long j15 = (j13 >>> 32) + (j12 * j2) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j15;
            int i6 = i + 2;
            long j16 = j4;
            long j17 = (j15 >>> 32) + (j12 * j5) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j17;
            int i7 = i + 3;
            long j18 = (j17 >>> 32) + (j12 * j3) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j18;
            int i8 = i + 4;
            long j19 = (j18 >>> 32) + (j12 * j16) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j19;
            iArr3[i + 5] = (int) (j19 >>> 32);
            i = i5;
            j4 = j16;
            j = j;
            j2 = j14;
        }
    }

    public static long mul33Add(int i, int[] iArr, int i2, int[] iArr2, int i3, int[] iArr3, int i4) {
        long j = ((long) i) & 4294967295L;
        long j2 = ((long) iArr[i2 + 0]) & 4294967295L;
        long j3 = (j * j2) + (((long) iArr2[i3 + 0]) & 4294967295L) + 0;
        iArr3[i4 + 0] = (int) j3;
        long j4 = ((long) iArr[i2 + 1]) & 4294967295L;
        long j5 = (j3 >>> 32) + (j * j4) + j2 + (((long) iArr2[i3 + 1]) & 4294967295L);
        iArr3[i4 + 1] = (int) j5;
        long j6 = j5 >>> 32;
        long j7 = ((long) iArr[i2 + 2]) & 4294967295L;
        long j8 = j6 + (j * j7) + j4 + (((long) iArr2[i3 + 2]) & 4294967295L);
        iArr3[i4 + 2] = (int) j8;
        long j9 = ((long) iArr[i2 + 3]) & 4294967295L;
        long j10 = (j8 >>> 32) + (j * j9) + j7 + (((long) iArr2[i3 + 3]) & 4294967295L);
        iArr3[i4 + 3] = (int) j10;
        long j11 = ((long) iArr[i2 + 4]) & 4294967295L;
        long j12 = (j10 >>> 32) + (j * j11) + j9 + (4294967295L & ((long) iArr2[i3 + 4]));
        iArr3[i4 + 4] = (int) j12;
        return (j12 >>> 32) + j11;
    }

    public static int mul33DWordAdd(int i, long j, int[] iArr, int i2) {
        int[] iArr2 = iArr;
        int i3 = i2;
        long j2 = ((long) i) & 4294967295L;
        long j3 = j & 4294967295L;
        int i4 = i3 + 0;
        long j4 = (j2 * j3) + (((long) iArr2[i4]) & 4294967295L) + 0;
        iArr2[i4] = (int) j4;
        long j5 = j >>> 32;
        long j6 = (j2 * j5) + j3;
        int i5 = i3 + 1;
        long j7 = (j4 >>> 32) + j6 + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j7;
        int i6 = i3 + 2;
        long j8 = (j7 >>> 32) + j5 + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j8;
        int i7 = i3 + 3;
        long j9 = (j8 >>> 32) + (4294967295L & ((long) iArr2[i7]));
        iArr2[i7] = (int) j9;
        if ((j9 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(5, iArr2, i3, 4);
    }

    public static int mul33WordAdd(int i, int i2, int[] iArr, int i3) {
        long j = ((long) i2) & 4294967295L;
        int i4 = i3 + 0;
        long j2 = ((((long) i) & 4294967295L) * j) + (((long) iArr[i4]) & 4294967295L) + 0;
        iArr[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (j2 >>> 32) + j + (((long) iArr[i5]) & 4294967295L);
        iArr[i5] = (int) j3;
        long j4 = j3 >>> 32;
        int i6 = i3 + 2;
        long j5 = j4 + (((long) iArr[i6]) & 4294967295L);
        iArr[i6] = (int) j5;
        if ((j5 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(5, iArr, i3, 3);
    }

    public static int mulAddTo(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = 4294967295L;
        long j2 = ((long) iArr2[i2 + 0]) & 4294967295L;
        long j3 = ((long) iArr2[i2 + 1]) & 4294967295L;
        long j4 = ((long) iArr2[i2 + 2]) & 4294967295L;
        long j5 = ((long) iArr2[i2 + 3]) & 4294967295L;
        long j6 = ((long) iArr2[i2 + 4]) & 4294967295L;
        int i4 = i3;
        int i5 = 0;
        long j7 = 0;
        while (i5 < 5) {
            long j8 = ((long) iArr[i + i5]) & j;
            int i6 = i4 + 0;
            long j9 = (j8 * j2) + (((long) iArr3[i6]) & j) + 0;
            iArr3[i6] = (int) j9;
            int i7 = i4 + 1;
            long j10 = j3;
            long j11 = (j9 >>> 32) + (j8 * j3) + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j11;
            int i8 = i4 + 2;
            long j12 = j4;
            long j13 = (j11 >>> 32) + (j8 * j4) + (((long) iArr3[i8]) & 4294967295L);
            iArr3[i8] = (int) j13;
            int i9 = i4 + 3;
            long j14 = (j13 >>> 32) + (j8 * j5) + (((long) iArr3[i9]) & 4294967295L);
            iArr3[i9] = (int) j14;
            int i10 = i4 + 4;
            long j15 = (j14 >>> 32) + (j8 * j6) + (((long) iArr3[i10]) & 4294967295L);
            iArr3[i10] = (int) j15;
            int i11 = i4 + 5;
            long j16 = (j15 >>> 32) + j7 + (((long) iArr3[i11]) & 4294967295L);
            iArr3[i11] = (int) j16;
            j7 = j16 >>> 32;
            i5++;
            i4 = i7;
            j2 = j2;
            j = 4294967295L;
            j3 = j10;
            j4 = j12;
        }
        return (int) j7;
    }

    public static int mulAddTo(int[] iArr, int[] iArr2, int[] iArr3) {
        int i = 0;
        long j = 4294967295L;
        long j2 = ((long) iArr2[0]) & 4294967295L;
        long j3 = ((long) iArr2[1]) & 4294967295L;
        long j4 = ((long) iArr2[2]) & 4294967295L;
        long j5 = ((long) iArr2[3]) & 4294967295L;
        long j6 = ((long) iArr2[4]) & 4294967295L;
        long j7 = 0;
        while (i < 5) {
            long j8 = ((long) iArr[i]) & j;
            int i2 = i + 0;
            long j9 = (j8 * j2) + (((long) iArr3[i2]) & j) + 0;
            iArr3[i2] = (int) j9;
            int i3 = i + 1;
            long j10 = j3;
            long j11 = (j9 >>> 32) + (j8 * j3) + (((long) iArr3[i3]) & 4294967295L);
            iArr3[i3] = (int) j11;
            int i4 = i + 2;
            long j12 = j4;
            long j13 = (j11 >>> 32) + (j8 * j4) + (((long) iArr3[i4]) & 4294967295L);
            iArr3[i4] = (int) j13;
            int i5 = i + 3;
            long j14 = (j13 >>> 32) + (j8 * j5) + (((long) iArr3[i5]) & 4294967295L);
            iArr3[i5] = (int) j14;
            int i6 = i + 4;
            long j15 = (j14 >>> 32) + (j8 * j6) + (((long) iArr3[i6]) & 4294967295L);
            iArr3[i6] = (int) j15;
            int i7 = i + 5;
            long j16 = (j15 >>> 32) + j7 + (((long) iArr3[i7]) & 4294967295L);
            iArr3[i7] = (int) j16;
            j7 = j16 >>> 32;
            i = i3;
            j = 4294967295L;
            j2 = j2;
            j4 = j12;
            j3 = j10;
        }
        return (int) j7;
    }

    public static int mulWord(int i, int[] iArr, int[] iArr2, int i2) {
        long j = ((long) i) & 4294967295L;
        long j2 = 0;
        int i3 = 0;
        do {
            long j3 = j2 + ((((long) iArr[i3]) & 4294967295L) * j);
            iArr2[i2 + i3] = (int) j3;
            j2 = j3 >>> 32;
            i3++;
        } while (i3 < 5);
        return (int) j2;
    }

    public static int mulWordAddExt(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        long j = ((long) i) & 4294967295L;
        int i4 = i3 + 0;
        long j2 = ((((long) iArr[i2 + 0]) & 4294967295L) * j) + (((long) iArr2[i4]) & 4294967295L) + 0;
        iArr2[i4] = (int) j2;
        int i5 = i3 + 1;
        long j3 = (j2 >>> 32) + ((((long) iArr[i2 + 1]) & 4294967295L) * j) + (((long) iArr2[i5]) & 4294967295L);
        iArr2[i5] = (int) j3;
        int i6 = i3 + 2;
        long j4 = (j3 >>> 32) + ((((long) iArr[i2 + 2]) & 4294967295L) * j) + (((long) iArr2[i6]) & 4294967295L);
        iArr2[i6] = (int) j4;
        int i7 = i3 + 3;
        long j5 = (j4 >>> 32) + ((((long) iArr[i2 + 3]) & 4294967295L) * j) + (((long) iArr2[i7]) & 4294967295L);
        iArr2[i7] = (int) j5;
        int i8 = i3 + 4;
        long j6 = (j5 >>> 32) + (j * (((long) iArr[i2 + 4]) & 4294967295L)) + (((long) iArr2[i8]) & 4294967295L);
        iArr2[i8] = (int) j6;
        return (int) (j6 >>> 32);
    }

    public static int mulWordDwordAdd(int i, long j, int[] iArr, int i2) {
        long j2 = ((long) i) & 4294967295L;
        int i3 = i2 + 0;
        long j3 = ((j & 4294967295L) * j2) + (((long) iArr[i3]) & 4294967295L) + 0;
        iArr[i3] = (int) j3;
        long j4 = j2 * (j >>> 32);
        int i4 = i2 + 1;
        long j5 = (j3 >>> 32) + j4 + (((long) iArr[i4]) & 4294967295L);
        iArr[i4] = (int) j5;
        int i5 = i2 + 2;
        long j6 = (j5 >>> 32) + (((long) iArr[i5]) & 4294967295L);
        iArr[i5] = (int) j6;
        if ((j6 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(5, iArr, i2, 3);
    }

    public static int mulWordsAdd(int i, int i2, int[] iArr, int i3) {
        int i4 = i3 + 0;
        long j = ((((long) i2) & 4294967295L) * (((long) i) & 4294967295L)) + (((long) iArr[i4]) & 4294967295L) + 0;
        iArr[i4] = (int) j;
        int i5 = i3 + 1;
        long j2 = (j >>> 32) + (4294967295L & ((long) iArr[i5]));
        iArr[i5] = (int) j2;
        if ((j2 >>> 32) == 0) {
            return 0;
        }
        return Nat.incAt(5, iArr, i3, 2);
    }

    public static void square(int[] iArr, int i, int[] iArr2, int i2) {
        long j = ((long) iArr[i + 0]) & 4294967295L;
        int i3 = 0;
        int i4 = 10;
        int i5 = 4;
        while (true) {
            int i6 = i5 - 1;
            long j2 = ((long) iArr[i + i5]) & 4294967295L;
            long j3 = j2 * j2;
            int i7 = i4 - 1;
            iArr2[i2 + i7] = (i3 << 31) | ((int) (j3 >>> 33));
            i4 = i7 - 1;
            iArr2[i2 + i4] = (int) (j3 >>> 1);
            i3 = (int) j3;
            if (i6 <= 0) {
                long j4 = j * j;
                iArr2[i2 + 0] = (int) j4;
                long j5 = ((long) iArr[i + 1]) & 4294967295L;
                int i8 = i2 + 2;
                long j6 = ((((long) (i3 << 31)) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int i9 = (int) j6;
                iArr2[i2 + 1] = (i9 << 1) | (((int) (j4 >>> 32)) & 1);
                int i10 = i9 >>> 31;
                long j7 = (((long) iArr2[i8]) & 4294967295L) + (j6 >>> 32);
                long j8 = ((long) iArr[i + 2]) & 4294967295L;
                int i11 = i2 + 3;
                long j9 = j5;
                int i12 = i2 + 4;
                long j10 = ((long) iArr2[i11]) & 4294967295L;
                long j11 = j7 + (j8 * j);
                int i13 = (int) j11;
                iArr2[i8] = (i13 << 1) | i10;
                int i14 = i13 >>> 31;
                long j12 = j10 + (j11 >>> 32) + (j8 * j9);
                long j13 = (((long) iArr2[i12]) & 4294967295L) + (j12 >>> 32);
                long j14 = j8;
                long j15 = ((long) iArr[i + 3]) & 4294967295L;
                int i15 = i2 + 5;
                int i16 = i2 + 6;
                int i17 = i15;
                long j16 = (j12 & 4294967295L) + (j15 * j);
                int i18 = (int) j16;
                iArr2[i11] = (i18 << 1) | i14;
                long j17 = j13 + (j16 >>> 32) + (j15 * j9);
                long j18 = (((long) iArr2[i15]) & 4294967295L) + (j17 >>> 32) + (j15 * j14);
                long j19 = (((long) iArr2[i16]) & 4294967295L) + (j18 >>> 32);
                long j20 = ((long) iArr[i + 4]) & 4294967295L;
                int i19 = i2 + 7;
                int i20 = i2 + 8;
                int i21 = i19;
                long j21 = (j17 & 4294967295L) + (j * j20);
                int i22 = (int) j21;
                iArr2[i12] = (i22 << 1) | (i18 >>> 31);
                long j22 = (j18 & 4294967295L) + (j21 >>> 32) + (j20 * j9);
                long j23 = j19 + (j22 >>> 32) + (j20 * j14);
                long j24 = (((long) iArr2[i19]) & 4294967295L) + (j23 >>> 32) + (j20 * j15);
                long j25 = (((long) iArr2[i20]) & 4294967295L) + (j24 >>> 32);
                int i23 = (int) j22;
                iArr2[i17] = (i22 >>> 31) | (i23 << 1);
                int i24 = i23 >>> 31;
                int i25 = (int) j23;
                iArr2[i16] = i24 | (i25 << 1);
                int i26 = i25 >>> 31;
                int i27 = (int) j24;
                iArr2[i21] = i26 | (i27 << 1);
                int i28 = i27 >>> 31;
                int i29 = (int) j25;
                iArr2[i20] = i28 | (i29 << 1);
                int i30 = i29 >>> 31;
                int i31 = i2 + 9;
                iArr2[i31] = i30 | ((iArr2[i31] + ((int) (j25 >> 32))) << 1);
                return;
            }
            i5 = i6;
        }
    }

    public static void square(int[] iArr, int[] iArr2) {
        long j = ((long) iArr[0]) & 4294967295L;
        int i = 10;
        int i2 = 4;
        int i3 = 0;
        while (true) {
            int i4 = i2 - 1;
            long j2 = ((long) iArr[i2]) & 4294967295L;
            long j3 = j2 * j2;
            int i5 = i - 1;
            iArr2[i5] = (i3 << 31) | ((int) (j3 >>> 33));
            i = i5 - 1;
            iArr2[i] = (int) (j3 >>> 1);
            int i6 = (int) j3;
            if (i4 <= 0) {
                long j4 = j * j;
                iArr2[0] = (int) j4;
                long j5 = ((long) iArr[1]) & 4294967295L;
                long j6 = ((((long) (i6 << 31)) & 4294967295L) | (j4 >>> 33)) + (j5 * j);
                int i7 = (int) j6;
                iArr2[1] = (i7 << 1) | (((int) (j4 >>> 32)) & 1);
                long j7 = (((long) iArr2[2]) & 4294967295L) + (j6 >>> 32);
                long j8 = ((long) iArr[2]) & 4294967295L;
                long j9 = j;
                long j10 = j7 + (j8 * j9);
                int i8 = (int) j10;
                iArr2[2] = (i8 << 1) | (i7 >>> 31);
                long j11 = (((long) iArr2[3]) & 4294967295L) + (j10 >>> 32) + (j8 * j5);
                long j12 = (((long) iArr2[4]) & 4294967295L) + (j11 >>> 32);
                long j13 = ((long) iArr[3]) & 4294967295L;
                long j14 = j8;
                long j15 = ((long) iArr2[5]) & 4294967295L;
                long j16 = (j11 & 4294967295L) + (j13 * j9);
                int i9 = (int) j16;
                iArr2[3] = (i9 << 1) | (i8 >>> 31);
                int i10 = i9 >>> 31;
                long j17 = j12 + (j16 >>> 32) + (j13 * j5);
                long j18 = j15 + (j17 >>> 32) + (j13 * j14);
                long j19 = (((long) iArr2[6]) & 4294967295L) + (j18 >>> 32);
                long j20 = ((long) iArr[4]) & 4294967295L;
                long j21 = ((long) iArr2[7]) & 4294967295L;
                long j22 = 4294967295L & ((long) iArr2[8]);
                long j23 = (j17 & 4294967295L) + (j20 * j9);
                int i11 = (int) j23;
                iArr2[4] = i10 | (i11 << 1);
                long j24 = (j18 & 4294967295L) + (j23 >>> 32) + (j5 * j20);
                long j25 = j19 + (j24 >>> 32) + (j20 * j14);
                long j26 = j21 + (j25 >>> 32) + (j20 * j13);
                long j27 = j22 + (j26 >>> 32);
                int i12 = (int) j24;
                iArr2[5] = (i12 << 1) | (i11 >>> 31);
                int i13 = i12 >>> 31;
                int i14 = (int) j25;
                iArr2[6] = i13 | (i14 << 1);
                int i15 = i14 >>> 31;
                int i16 = (int) j26;
                iArr2[7] = i15 | (i16 << 1);
                int i17 = i16 >>> 31;
                int i18 = (int) j27;
                iArr2[8] = i17 | (i18 << 1);
                iArr2[9] = (i18 >>> 31) | ((iArr2[9] + ((int) (j27 >> 32))) << 1);
                return;
            }
            i2 = i4;
            i3 = i6;
        }
    }

    public static int sub(int[] iArr, int i, int[] iArr2, int i2, int[] iArr3, int i3) {
        long j = ((((long) iArr[i + 0]) & 4294967295L) - (((long) iArr2[i2 + 0]) & 4294967295L)) + 0;
        iArr3[i3 + 0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[i + 1]) & 4294967295L) - (((long) iArr2[i2 + 1]) & 4294967295L));
        iArr3[i3 + 1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[i + 2]) & 4294967295L) - (((long) iArr2[i2 + 2]) & 4294967295L));
        iArr3[i3 + 2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[i + 3]) & 4294967295L) - (((long) iArr2[i2 + 3]) & 4294967295L));
        iArr3[i3 + 3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[i + 4]) & 4294967295L) - (((long) iArr2[i2 + 4]) & 4294967295L));
        iArr3[i3 + 4] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int sub(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = ((((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr[4]) & 4294967295L) - (((long) iArr2[4]) & 4294967295L));
        iArr3[4] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int subBothFrom(int[] iArr, int[] iArr2, int[] iArr3) {
        long j = (((((long) iArr3[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L)) - (((long) iArr2[0]) & 4294967295L)) + 0;
        iArr3[0] = (int) j;
        long j2 = (j >> 32) + (((((long) iArr3[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L)) - (((long) iArr2[1]) & 4294967295L));
        iArr3[1] = (int) j2;
        long j3 = (j2 >> 32) + (((((long) iArr3[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L)) - (((long) iArr2[2]) & 4294967295L));
        iArr3[2] = (int) j3;
        long j4 = (j3 >> 32) + (((((long) iArr3[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L)) - (((long) iArr2[3]) & 4294967295L));
        iArr3[3] = (int) j4;
        long j5 = (j4 >> 32) + (((((long) iArr3[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L)) - (((long) iArr2[4]) & 4294967295L));
        iArr3[4] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int subFrom(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = i2 + 0;
        long j = ((((long) iArr2[i3]) & 4294967295L) - (((long) iArr[i + 0]) & 4294967295L)) + 0;
        iArr2[i3] = (int) j;
        int i4 = i2 + 1;
        long j2 = (j >> 32) + ((((long) iArr2[i4]) & 4294967295L) - (((long) iArr[i + 1]) & 4294967295L));
        iArr2[i4] = (int) j2;
        int i5 = i2 + 2;
        long j3 = (j2 >> 32) + ((((long) iArr2[i5]) & 4294967295L) - (((long) iArr[i + 2]) & 4294967295L));
        iArr2[i5] = (int) j3;
        int i6 = i2 + 3;
        long j4 = (j3 >> 32) + ((((long) iArr2[i6]) & 4294967295L) - (((long) iArr[i + 3]) & 4294967295L));
        iArr2[i6] = (int) j4;
        int i7 = i2 + 4;
        long j5 = (j4 >> 32) + ((((long) iArr2[i7]) & 4294967295L) - (((long) iArr[i + 4]) & 4294967295L));
        iArr2[i7] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static int subFrom(int[] iArr, int[] iArr2) {
        long j = ((((long) iArr2[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L)) + 0;
        iArr2[0] = (int) j;
        long j2 = (j >> 32) + ((((long) iArr2[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L));
        iArr2[1] = (int) j2;
        long j3 = (j2 >> 32) + ((((long) iArr2[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L));
        iArr2[2] = (int) j3;
        long j4 = (j3 >> 32) + ((((long) iArr2[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L));
        iArr2[3] = (int) j4;
        long j5 = (j4 >> 32) + ((((long) iArr2[4]) & 4294967295L) - (4294967295L & ((long) iArr[4])));
        iArr2[4] = (int) j5;
        return (int) (j5 >> 32);
    }

    public static BigInteger toBigInteger(int[] iArr) {
        byte[] bArr = new byte[20];
        for (int i = 0; i < 5; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                Pack.intToBigEndian(i2, bArr, (4 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public static void zero(int[] iArr) {
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
    }
}
