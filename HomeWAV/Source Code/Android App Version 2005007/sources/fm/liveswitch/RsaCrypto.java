package fm.liveswitch;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import kotlin.jvm.internal.ByteCompanionObject;

public class RsaCrypto {
    private static int _defaultKeySize = 2048;

    public static int getDefaultKeySize() {
        return _defaultKeySize;
    }

    public static void setDefaultKeySize(int i) {
        _defaultKeySize = i;
    }

    public static RsaKey createKey() {
        return createKey(_defaultKeySize);
    }

    public static RsaKey createKey(int i) {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(i);
            KeyPair genKeyPair = instance.genKeyPair();
            PrivateKey privateKey = genKeyPair.getPrivate();
            PublicKey publicKey = genKeyPair.getPublic();
            KeyFactory instance2 = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec rSAPublicKeySpec = (RSAPublicKeySpec) instance2.getKeySpec(publicKey, RSAPublicKeySpec.class);
            RSAPrivateCrtKeySpec rSAPrivateCrtKeySpec = (RSAPrivateCrtKeySpec) instance2.getKeySpec(privateKey, RSAPrivateCrtKeySpec.class);
            RsaKey rsaKey = new RsaKey();
            rsaKey.setModulus(removePositive(rSAPublicKeySpec.getModulus().toByteArray()));
            rsaKey.setPublicExponent(removePositive(rSAPublicKeySpec.getPublicExponent().toByteArray()));
            rsaKey.setPrivateExponent(removePositive(rSAPrivateCrtKeySpec.getPrivateExponent().toByteArray()));
            rsaKey.setPrime1(removePositive(rSAPrivateCrtKeySpec.getPrimeP().toByteArray()));
            rsaKey.setPrime2(removePositive(rSAPrivateCrtKeySpec.getPrimeQ().toByteArray()));
            rsaKey.setExponent1(removePositive(rSAPrivateCrtKeySpec.getPrimeExponentP().toByteArray()));
            rsaKey.setExponent2(removePositive(rSAPrivateCrtKeySpec.getPrimeExponentQ().toByteArray()));
            rsaKey.setCoefficient(removePositive(rSAPrivateCrtKeySpec.getCrtCoefficient().toByteArray()));
            return rsaKey;
        } catch (Exception e) {
            Log.error("Could not generate RSA key.", e);
            return null;
        }
    }

    public static PrivateKey getPrivateKey(RsaKey rsaKey) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(addPositive(rsaKey.getModulus())), new BigInteger(addPositive(rsaKey.getPublicExponent())), new BigInteger(addPositive(rsaKey.getPrivateExponent())), new BigInteger(addPositive(rsaKey.getPrime1())), new BigInteger(addPositive(rsaKey.getPrime2())), new BigInteger(addPositive(rsaKey.getExponent1())), new BigInteger(addPositive(rsaKey.getExponent2())), new BigInteger(addPositive(rsaKey.getCoefficient()))));
    }

    public static PublicKey getPublicKey(RsaKey rsaKey) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(addPositive(rsaKey.getModulus())), new BigInteger(addPositive(rsaKey.getPublicExponent()))));
    }

    private static byte[] padHash(byte[] bArr, int i) {
        int length = bArr.length;
        byte[] bArr2 = new byte[i];
        Binary.toBytes8(0, bArr2, 0);
        Binary.toBytes8(1, bArr2, 1);
        int i2 = 2;
        while (true) {
            int i3 = i - length;
            int i4 = i3 - 1;
            if (i2 < i4) {
                Binary.toBytes8(255, bArr2, i2);
                i2++;
            } else {
                Binary.toBytes8(0, bArr2, i4);
                BitAssistant.copy(bArr, 0, bArr2, i3, length);
                return bArr2;
            }
        }
    }

    private static boolean verifyPaddedHash(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr.length == i2) {
            if ((Binary.fromBytes8(bArr, 0) & 255) != 0) {
                return false;
            }
            i3 = 1;
        } else if (bArr.length != i2 - 1) {
            return false;
        } else {
            i3 = 0;
        }
        if ((Binary.fromBytes8(bArr, i3) & 255) != 1) {
            return false;
        }
        for (int i4 = i3 + 1; i4 < (bArr.length - i) - 1; i4++) {
            if ((Binary.fromBytes8(bArr, i4) & 255) != 255) {
                return false;
            }
        }
        return (Binary.fromBytes8(bArr, (bArr.length - i) - 1) & 255) == 0;
    }

    private static byte[] addPositive(byte[] bArr) {
        if (bArr.length > 0 && (bArr[0] & ByteCompanionObject.MIN_VALUE) == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[(bArr.length + 1)];
        bArr2[0] = 0;
        BitAssistant.copy(bArr, 0, bArr2, 1, bArr.length);
        return bArr2;
    }

    private static byte[] removePositive(byte[] bArr) {
        return (bArr.length % 2 == 1 && bArr[0] == 0) ? BitAssistant.subArray(bArr, 1) : bArr;
    }

    public static byte[] signMd5Sha1(byte[] bArr, RsaKey rsaKey) {
        return removePositive(new BigInteger(addPositive(padHash(bArr, rsaKey.getModulus().length))).modPow(new BigInteger(addPositive(rsaKey.getPrivateExponent())), new BigInteger(addPositive(rsaKey.getModulus()))).toByteArray());
    }

    public static boolean verifyMd5Sha1(byte[] bArr, byte[] bArr2, RsaKey rsaKey) {
        byte[] removePositive = removePositive(new BigInteger(addPositive(bArr2)).modPow(new BigInteger(addPositive(rsaKey.getPublicExponent())), new BigInteger(addPositive(rsaKey.getModulus()))).toByteArray());
        if (!BitAssistant.sequencesAreEqual(bArr, 0, removePositive, removePositive.length - bArr.length, bArr.length)) {
            return false;
        }
        return verifyPaddedHash(removePositive, bArr.length, rsaKey.getModulus().length);
    }

    public static byte[] signSha1(byte[] bArr, RsaKey rsaKey) {
        try {
            PrivateKey privateKey = getPrivateKey(rsaKey);
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initSign(privateKey);
            instance.update(bArr);
            return instance.sign();
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    public static boolean verifySha1(byte[] bArr, byte[] bArr2, RsaKey rsaKey) {
        try {
            PublicKey publicKey = getPublicKey(rsaKey);
            Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(publicKey);
            instance.update(bArr);
            return instance.verify(bArr2);
        } catch (Exception unused) {
            return false;
        }
    }

    public static byte[] signSha256(byte[] bArr, RsaKey rsaKey) {
        try {
            PrivateKey privateKey = getPrivateKey(rsaKey);
            Signature instance = Signature.getInstance("SHA256withRSA");
            instance.initSign(privateKey);
            instance.update(bArr);
            return instance.sign();
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    public static boolean verifySha256(byte[] bArr, byte[] bArr2, RsaKey rsaKey) {
        try {
            PublicKey publicKey = getPublicKey(rsaKey);
            Signature instance = Signature.getInstance("SHA256withRSA");
            instance.initVerify(publicKey);
            instance.update(bArr);
            return instance.verify(bArr2);
        } catch (Exception unused) {
            return false;
        }
    }

    public static byte[] encrypt(byte[] bArr, RsaKey rsaKey) {
        try {
            PublicKey publicKey = getPublicKey(rsaKey);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, publicKey);
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    public static byte[] decrypt(byte[] bArr, RsaKey rsaKey) {
        try {
            PrivateKey privateKey = getPrivateKey(rsaKey);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, privateKey);
            return instance.doFinal(bArr);
        } catch (Exception unused) {
            return new byte[0];
        }
    }
}
