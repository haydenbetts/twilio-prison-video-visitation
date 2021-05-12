package org.bouncycastle.math;

import java.math.BigInteger;
import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public abstract class Primes {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    public static final int SMALL_FACTOR_LIMIT = 211;
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static class MROutput {
        private BigInteger factor;
        private boolean provablyComposite;

        private MROutput(boolean z, BigInteger bigInteger) {
            this.provablyComposite = z;
            this.factor = bigInteger;
        }

        /* access modifiers changed from: private */
        public static MROutput probablyPrime() {
            return new MROutput(false, (BigInteger) null);
        }

        /* access modifiers changed from: private */
        public static MROutput provablyCompositeNotPrimePower() {
            return new MROutput(true, (BigInteger) null);
        }

        /* access modifiers changed from: private */
        public static MROutput provablyCompositeWithFactor(BigInteger bigInteger) {
            return new MROutput(true, bigInteger);
        }

        public BigInteger getFactor() {
            return this.factor;
        }

        public boolean isNotPrimePower() {
            return this.provablyComposite && this.factor == null;
        }

        public boolean isProvablyComposite() {
            return this.provablyComposite;
        }
    }

    public static class STOutput {
        private BigInteger prime;
        private int primeGenCounter;
        private byte[] primeSeed;

        private STOutput(BigInteger bigInteger, byte[] bArr, int i) {
            this.prime = bigInteger;
            this.primeSeed = bArr;
            this.primeGenCounter = i;
        }

        public BigInteger getPrime() {
            return this.prime;
        }

        public int getPrimeGenCounter() {
            return this.primeGenCounter;
        }

        public byte[] getPrimeSeed() {
            return this.primeSeed;
        }
    }

    private static void checkCandidate(BigInteger bigInteger, String str) {
        if (bigInteger == null || bigInteger.signum() < 1 || bigInteger.bitLength() < 2) {
            throw new IllegalArgumentException("'" + str + "' must be non-null and >= 2");
        }
    }

    public static MROutput enhancedMRProbablePrimeTest(BigInteger bigInteger, SecureRandom secureRandom, int i) {
        BigInteger bigInteger2;
        boolean z;
        checkCandidate(bigInteger, "candidate");
        if (secureRandom == null) {
            throw new IllegalArgumentException("'random' cannot be null");
        } else if (i < 1) {
            throw new IllegalArgumentException("'iterations' must be > 0");
        } else if (bigInteger.bitLength() == 2) {
            return MROutput.probablyPrime();
        } else {
            if (!bigInteger.testBit(0)) {
                return MROutput.provablyCompositeWithFactor(TWO);
            }
            BigInteger subtract = bigInteger.subtract(ONE);
            BigInteger subtract2 = bigInteger.subtract(TWO);
            int lowestSetBit = subtract.getLowestSetBit();
            BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
            for (int i2 = 0; i2 < i; i2++) {
                BigInteger createRandomInRange = BigIntegers.createRandomInRange(TWO, subtract2, secureRandom);
                BigInteger gcd = createRandomInRange.gcd(bigInteger);
                BigInteger bigInteger3 = ONE;
                if (gcd.compareTo(bigInteger3) > 0) {
                    return MROutput.provablyCompositeWithFactor(gcd);
                }
                BigInteger modPow = createRandomInRange.modPow(shiftRight, bigInteger);
                if (!modPow.equals(bigInteger3) && !modPow.equals(subtract)) {
                    int i3 = 1;
                    while (true) {
                        if (i3 >= lowestSetBit) {
                            bigInteger2 = modPow;
                            break;
                        }
                        bigInteger2 = modPow.modPow(TWO, bigInteger);
                        if (bigInteger2.equals(subtract)) {
                            z = true;
                            break;
                        } else if (bigInteger2.equals(ONE)) {
                            break;
                        } else {
                            i3++;
                            modPow = bigInteger2;
                        }
                    }
                    z = false;
                    if (!z) {
                        BigInteger bigInteger4 = ONE;
                        if (!bigInteger2.equals(bigInteger4)) {
                            modPow = bigInteger2.modPow(TWO, bigInteger);
                            if (modPow.equals(bigInteger4)) {
                                modPow = bigInteger2;
                            }
                        }
                        BigInteger gcd2 = modPow.subtract(bigInteger4).gcd(bigInteger);
                        return gcd2.compareTo(bigInteger4) > 0 ? MROutput.provablyCompositeWithFactor(gcd2) : MROutput.provablyCompositeNotPrimePower();
                    }
                }
            }
            return MROutput.probablyPrime();
        }
    }

    private static int extract32(byte[] bArr) {
        int min = Math.min(4, bArr.length);
        int i = 0;
        int i2 = 0;
        while (i < min) {
            int i3 = i + 1;
            i2 |= (bArr[bArr.length - i3] & UByte.MAX_VALUE) << (i * 8);
            i = i3;
        }
        return i2;
    }

    public static STOutput generateSTRandomPrime(Digest digest, int i, byte[] bArr) {
        if (digest == null) {
            throw new IllegalArgumentException("'hash' cannot be null");
        } else if (i < 2) {
            throw new IllegalArgumentException("'length' must be >= 2");
        } else if (bArr != null && bArr.length != 0) {
            return implSTRandomPrime(digest, i, Arrays.clone(bArr));
        } else {
            throw new IllegalArgumentException("'inputSeed' cannot be null or empty");
        }
    }

    public static boolean hasAnySmallFactors(BigInteger bigInteger) {
        checkCandidate(bigInteger, "candidate");
        return implHasAnySmallFactors(bigInteger);
    }

    private static void hash(Digest digest, byte[] bArr, byte[] bArr2, int i) {
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, i);
    }

    private static BigInteger hashGen(Digest digest, byte[] bArr, int i) {
        int digestSize = digest.getDigestSize();
        int i2 = i * digestSize;
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i; i3++) {
            i2 -= digestSize;
            hash(digest, bArr, bArr2, i2);
            inc(bArr, 1);
        }
        return new BigInteger(1, bArr2);
    }

    private static boolean implHasAnySmallFactors(BigInteger bigInteger) {
        int intValue = bigInteger.mod(BigInteger.valueOf((long) 223092870)).intValue();
        if (!(intValue % 2 == 0 || intValue % 3 == 0 || intValue % 5 == 0 || intValue % 7 == 0 || intValue % 11 == 0 || intValue % 13 == 0 || intValue % 17 == 0 || intValue % 19 == 0 || intValue % 23 == 0)) {
            int intValue2 = bigInteger.mod(BigInteger.valueOf((long) 58642669)).intValue();
            if (!(intValue2 % 29 == 0 || intValue2 % 31 == 0 || intValue2 % 37 == 0 || intValue2 % 41 == 0 || intValue2 % 43 == 0)) {
                int intValue3 = bigInteger.mod(BigInteger.valueOf((long) 600662303)).intValue();
                if (!(intValue3 % 47 == 0 || intValue3 % 53 == 0 || intValue3 % 59 == 0 || intValue3 % 61 == 0 || intValue3 % 67 == 0)) {
                    int intValue4 = bigInteger.mod(BigInteger.valueOf((long) 33984931)).intValue();
                    if (!(intValue4 % 71 == 0 || intValue4 % 73 == 0 || intValue4 % 79 == 0 || intValue4 % 83 == 0)) {
                        int intValue5 = bigInteger.mod(BigInteger.valueOf((long) 89809099)).intValue();
                        if (!(intValue5 % 89 == 0 || intValue5 % 97 == 0 || intValue5 % 101 == 0 || intValue5 % 103 == 0)) {
                            int intValue6 = bigInteger.mod(BigInteger.valueOf((long) 167375713)).intValue();
                            if (!(intValue6 % 107 == 0 || intValue6 % 109 == 0 || intValue6 % 113 == 0 || intValue6 % 127 == 0)) {
                                int intValue7 = bigInteger.mod(BigInteger.valueOf((long) 371700317)).intValue();
                                if (!(intValue7 % 131 == 0 || intValue7 % 137 == 0 || intValue7 % 139 == 0 || intValue7 % 149 == 0)) {
                                    int intValue8 = bigInteger.mod(BigInteger.valueOf((long) 645328247)).intValue();
                                    if (!(intValue8 % 151 == 0 || intValue8 % 157 == 0 || intValue8 % 163 == 0 || intValue8 % 167 == 0)) {
                                        int intValue9 = bigInteger.mod(BigInteger.valueOf((long) 1070560157)).intValue();
                                        if (!(intValue9 % 173 == 0 || intValue9 % 179 == 0 || intValue9 % 181 == 0 || intValue9 % 191 == 0)) {
                                            int intValue10 = bigInteger.mod(BigInteger.valueOf((long) 1596463769)).intValue();
                                            return intValue10 % 193 == 0 || intValue10 % CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 == 0 || intValue10 % 199 == 0 || intValue10 % SMALL_FACTOR_LIMIT == 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean implMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, BigInteger bigInteger4) {
        BigInteger modPow = bigInteger4.modPow(bigInteger3, bigInteger);
        if (modPow.equals(ONE) || modPow.equals(bigInteger2)) {
            return true;
        }
        for (int i2 = 1; i2 < i; i2++) {
            modPow = modPow.modPow(TWO, bigInteger);
            if (modPow.equals(bigInteger2)) {
                return true;
            }
            if (modPow.equals(ONE)) {
                return false;
            }
        }
        return false;
    }

    private static STOutput implSTRandomPrime(Digest digest, int i, byte[] bArr) {
        Object obj;
        Digest digest2 = digest;
        int i2 = i;
        byte[] bArr2 = bArr;
        int digestSize = digest.getDigestSize();
        Object obj2 = null;
        int i3 = 1;
        if (i2 < 33) {
            byte[] bArr3 = new byte[digestSize];
            byte[] bArr4 = new byte[digestSize];
            int i4 = 0;
            do {
                hash(digest2, bArr2, bArr3, 0);
                inc(bArr2, 1);
                hash(digest2, bArr2, bArr4, 0);
                inc(bArr2, 1);
                i4++;
                long extract32 = ((long) (((extract32(bArr3) ^ extract32(bArr4)) & (-1 >>> (32 - i2))) | (1 << (i2 - 1)) | 1)) & 4294967295L;
                if (isPrime32(extract32)) {
                    return new STOutput(BigInteger.valueOf(extract32), bArr2, i4);
                }
            } while (i4 <= i2 * 4);
            throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
        }
        STOutput implSTRandomPrime = implSTRandomPrime(digest2, (i2 + 3) / 2, bArr2);
        BigInteger prime = implSTRandomPrime.getPrime();
        byte[] primeSeed = implSTRandomPrime.getPrimeSeed();
        int primeGenCounter = implSTRandomPrime.getPrimeGenCounter();
        int i5 = i2 - 1;
        int i6 = (i5 / (digestSize * 8)) + 1;
        BigInteger hashGen = hashGen(digest2, primeSeed, i6);
        BigInteger bigInteger = ONE;
        BigInteger bit = hashGen.mod(bigInteger.shiftLeft(i5)).setBit(i5);
        BigInteger shiftLeft = prime.shiftLeft(1);
        BigInteger shiftLeft2 = bit.subtract(bigInteger).divide(shiftLeft).add(bigInteger).shiftLeft(1);
        BigInteger add = shiftLeft2.multiply(prime).add(bigInteger);
        int i7 = primeGenCounter;
        int i8 = 0;
        while (true) {
            if (add.bitLength() > i2) {
                BigInteger bigInteger2 = ONE;
                shiftLeft2 = bigInteger2.shiftLeft(i5).subtract(bigInteger2).divide(shiftLeft).add(bigInteger2).shiftLeft(i3);
                add = shiftLeft2.multiply(prime).add(bigInteger2);
            }
            i7 += i3;
            if (!implHasAnySmallFactors(add)) {
                BigInteger add2 = shiftLeft2.add(BigInteger.valueOf((long) i8));
                BigInteger modPow = hashGen(digest2, primeSeed, i6).mod(add.subtract(THREE)).add(TWO).modPow(add2, add);
                BigInteger bigInteger3 = ONE;
                if (add.gcd(modPow.subtract(bigInteger3)).equals(bigInteger3) && modPow.modPow(prime, add).equals(bigInteger3)) {
                    return new STOutput(add, primeSeed, i7);
                }
                obj = null;
                shiftLeft2 = add2;
                i8 = 0;
            } else {
                obj = obj2;
                inc(primeSeed, i6);
            }
            if (i7 < (i2 * 4) + primeGenCounter) {
                i8 += 2;
                add = add.add(shiftLeft);
                obj2 = obj;
                i3 = 1;
            } else {
                throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
            }
        }
    }

    private static void inc(byte[] bArr, int i) {
        int length = bArr.length;
        while (i > 0) {
            length--;
            if (length >= 0) {
                int i2 = i + (bArr[length] & UByte.MAX_VALUE);
                bArr[length] = (byte) i2;
                i = i2 >>> 8;
            } else {
                return;
            }
        }
    }

    public static boolean isMRProbablePrime(BigInteger bigInteger, SecureRandom secureRandom, int i) {
        checkCandidate(bigInteger, "candidate");
        if (secureRandom == null) {
            throw new IllegalArgumentException("'random' cannot be null");
        } else if (i < 1) {
            throw new IllegalArgumentException("'iterations' must be > 0");
        } else if (bigInteger.bitLength() == 2) {
            return true;
        } else {
            if (!bigInteger.testBit(0)) {
                return false;
            }
            BigInteger subtract = bigInteger.subtract(ONE);
            BigInteger subtract2 = bigInteger.subtract(TWO);
            int lowestSetBit = subtract.getLowestSetBit();
            BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
            for (int i2 = 0; i2 < i; i2++) {
                if (!implMRProbablePrimeToBase(bigInteger, subtract, shiftRight, lowestSetBit, BigIntegers.createRandomInRange(TWO, subtract2, secureRandom))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2) {
        checkCandidate(bigInteger, "candidate");
        checkCandidate(bigInteger2, "base");
        BigInteger bigInteger3 = ONE;
        if (bigInteger2.compareTo(bigInteger.subtract(bigInteger3)) >= 0) {
            throw new IllegalArgumentException("'base' must be < ('candidate' - 1)");
        } else if (bigInteger.bitLength() == 2) {
            return true;
        } else {
            BigInteger subtract = bigInteger.subtract(bigInteger3);
            int lowestSetBit = subtract.getLowestSetBit();
            return implMRProbablePrimeToBase(bigInteger, subtract, subtract.shiftRight(lowestSetBit), lowestSetBit, bigInteger2);
        }
    }

    private static boolean isPrime32(long j) {
        if ((j >>> 32) == 0) {
            int i = (j > 5 ? 1 : (j == 5 ? 0 : -1));
            if (i <= 0) {
                return j == 2 || j == 3 || i == 0;
            }
            if ((1 & j) == 0 || j % 3 == 0 || j % 5 == 0) {
                return false;
            }
            long[] jArr = {1, 7, 11, 13, 17, 19, 23, 29};
            long j2 = 0;
            int i2 = 1;
            while (true) {
                if (i2 >= 8) {
                    j2 += 30;
                    if (j2 * j2 >= j) {
                        return true;
                    }
                    i2 = 0;
                } else if (j % (jArr[i2] + j2) == 0) {
                    return j < 30;
                } else {
                    i2++;
                }
            }
        } else {
            throw new IllegalArgumentException("Size limit exceeded");
        }
    }
}
