package org.bouncycastle.crypto.generators;

import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import okhttp3.internal.http.StatusLine;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.math.Primes;
import org.bytedeco.opencv.global.opencv_cudaimgproc;
import org.java_websocket.WebSocketImpl;

public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, 251, 257, opencv_cudaimgproc.COLOR_BayerGR2GRAY_MHT, 269, 271, 277, 281, 283, 293, StatusLine.HTTP_TEMP_REDIRECT, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, WebSocketImpl.DEFAULT_WSS_PORT, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557};
    private NaccacheSternKeyGenerationParameters param;

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf((long) smallPrimes[i2]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigInteger = new BigInteger(i, i2, secureRandom);
        while (bigInteger.bitLength() != i) {
            bigInteger = new BigInteger(i, i2, secureRandom);
        }
        return bigInteger;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((((long) i) * ((long) (secureRandom.nextInt() & Integer.MAX_VALUE))) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
        }
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger generatePrime;
        BigInteger add;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger generatePrime2;
        BigInteger bigInteger3;
        BigInteger add2;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        BigInteger bigInteger7;
        boolean z;
        int i;
        BigInteger bigInteger8;
        BigInteger bigInteger9;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        long j2;
        BigInteger bigInteger10;
        int i2;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            PrintStream printStream2 = System.out;
            printStream2.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger11 = ONE;
        BigInteger bigInteger12 = bigInteger11;
        for (int i3 = 0; i3 < permuteList.size() / 2; i3++) {
            bigInteger12 = bigInteger12.multiply((BigInteger) permuteList.elementAt(i3));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger11 = bigInteger11.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply = bigInteger12.multiply(bigInteger11);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger12).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger11).shiftLeft(1);
        long j3 = 0;
        while (true) {
            j = j3 + 1;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (!add.isProbablePrime(certainty)) {
                bigInteger2 = shiftLeft2;
                bigInteger = shiftLeft;
            } else {
                while (true) {
                    do {
                        generatePrime2 = generatePrime(24, certainty, random);
                    } while (generatePrime.equals(generatePrime2));
                    BigInteger multiply2 = generatePrime2.multiply(shiftLeft2);
                    bigInteger2 = shiftLeft2;
                    bigInteger3 = ONE;
                    add2 = multiply2.add(bigInteger3);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    shiftLeft2 = bigInteger2;
                }
                bigInteger = shiftLeft;
                if (multiply.gcd(generatePrime.multiply(generatePrime2)).equals(bigInteger3)) {
                    if (add.multiply(add2).bitLength() >= strength) {
                        break;
                    } else if (isDebug) {
                        PrintStream printStream3 = System.out;
                        printStream3.println("key size too small. Should be " + strength + " but is actually " + add.multiply(add2).bitLength());
                    }
                } else {
                    continue;
                }
            }
            j3 = j;
            shiftLeft2 = bigInteger2;
            shiftLeft = bigInteger;
        }
        BigInteger bigInteger13 = generatePrime4;
        if (isDebug) {
            PrintStream printStream4 = System.out;
            bigInteger4 = generatePrime3;
            printStream4.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger4 = generatePrime3;
        }
        BigInteger multiply3 = add.multiply(add2);
        BigInteger multiply4 = add.subtract(bigInteger3).multiply(add2.subtract(bigInteger3));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j4 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger5 = add;
            bigInteger6 = add2;
            int i4 = 0;
            while (i4 != permuteList.size()) {
                BigInteger divide = multiply4.divide((BigInteger) permuteList.elementAt(i4));
                while (true) {
                    j2 = j4 + 1;
                    bigInteger10 = new BigInteger(strength, certainty, random);
                    i2 = strength;
                    if (!bigInteger10.modPow(divide, multiply3).equals(ONE)) {
                        break;
                    }
                    j4 = j2;
                    strength = i2;
                }
                vector.addElement(bigInteger10);
                i4++;
                j4 = j2;
                strength = i2;
            }
            int i5 = strength;
            bigInteger7 = ONE;
            int i6 = 0;
            while (i6 < permuteList.size()) {
                bigInteger7 = bigInteger7.multiply(((BigInteger) vector.elementAt(i6)).modPow(multiply.divide((BigInteger) permuteList.elementAt(i6)), multiply3)).mod(multiply3);
                i6++;
                random = random;
            }
            SecureRandom secureRandom = random;
            int i7 = 0;
            while (true) {
                if (i7 >= permuteList.size()) {
                    z = false;
                    break;
                } else if (bigInteger7.modPow(multiply4.divide((BigInteger) permuteList.elementAt(i7)), multiply3).equals(ONE)) {
                    if (isDebug) {
                        PrintStream printStream5 = System.out;
                        printStream5.println("g has order phi(n)/" + permuteList.elementAt(i7) + "\n g: " + bigInteger7);
                    }
                    z = true;
                } else {
                    i7++;
                }
            }
            if (!z) {
                BigInteger modPow = bigInteger7.modPow(multiply4.divide(BigInteger.valueOf(4)), multiply3);
                BigInteger bigInteger14 = ONE;
                if (modPow.equals(bigInteger14)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/4\n g:";
                    }
                } else if (bigInteger7.modPow(multiply4.divide(generatePrime), multiply3).equals(bigInteger14)) {
                    if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/p'\n g: ";
                    }
                } else if (!bigInteger7.modPow(multiply4.divide(generatePrime2), multiply3).equals(bigInteger14)) {
                    bigInteger9 = bigInteger4;
                    if (!bigInteger7.modPow(multiply4.divide(bigInteger9), multiply3).equals(bigInteger14)) {
                        bigInteger8 = bigInteger13;
                        if (!bigInteger7.modPow(multiply4.divide(bigInteger8), multiply3).equals(bigInteger14)) {
                            break;
                        } else if (isDebug) {
                            PrintStream printStream6 = System.out;
                            StringBuilder sb2 = new StringBuilder();
                            i = certainty;
                            sb2.append("g has order phi(n)/b\n g: ");
                            sb2.append(bigInteger7);
                            printStream6.println(sb2.toString());
                            bigInteger4 = bigInteger9;
                            certainty = i;
                            add2 = bigInteger6;
                            add = bigInteger5;
                            strength = i5;
                            random = secureRandom;
                            bigInteger13 = bigInteger8;
                        }
                    } else {
                        if (isDebug) {
                            PrintStream printStream7 = System.out;
                            printStream7.println("g has order phi(n)/a\n g: " + bigInteger7);
                        }
                        bigInteger8 = bigInteger13;
                    }
                    i = certainty;
                    bigInteger4 = bigInteger9;
                    certainty = i;
                    add2 = bigInteger6;
                    add = bigInteger5;
                    strength = i5;
                    random = secureRandom;
                    bigInteger13 = bigInteger8;
                } else if (isDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/q'\n g: ";
                }
                sb.append(str);
                sb.append(bigInteger7);
                printStream.println(sb.toString());
            }
            bigInteger8 = bigInteger13;
            bigInteger9 = bigInteger4;
            i = certainty;
            bigInteger4 = bigInteger9;
            certainty = i;
            add2 = bigInteger6;
            add = bigInteger5;
            strength = i5;
            random = secureRandom;
            bigInteger13 = bigInteger8;
        }
        if (isDebug) {
            PrintStream printStream8 = System.out;
            printStream8.println("needed " + j4 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            PrintStream printStream9 = System.out;
            printStream9.println("smallPrimes: " + permuteList);
            PrintStream printStream10 = System.out;
            printStream10.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            PrintStream printStream11 = System.out;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("a:.......... ");
            sb3.append(bigInteger9);
            printStream11.println(sb3.toString());
            PrintStream printStream12 = System.out;
            printStream12.println("b:.......... " + bigInteger8);
            PrintStream printStream13 = System.out;
            printStream13.println("p':......... " + generatePrime);
            PrintStream printStream14 = System.out;
            printStream14.println("q':......... " + generatePrime2);
            PrintStream printStream15 = System.out;
            printStream15.println("p:.......... " + bigInteger5);
            PrintStream printStream16 = System.out;
            printStream16.println("q:.......... " + bigInteger6);
            PrintStream printStream17 = System.out;
            printStream17.println("n:.......... " + multiply3);
            PrintStream printStream18 = System.out;
            printStream18.println("phi(n):..... " + multiply4);
            PrintStream printStream19 = System.out;
            printStream19.println("g:.......... " + bigInteger7);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger7, multiply3, multiply.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger7, multiply3, multiply.bitLength(), permuteList, multiply4));
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
