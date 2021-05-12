package defpackage;

import android.util.Base64;
import android.util.Log;
import com.github.kevinsawicki.http.HttpRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: Crypto  reason: default package */
public class Crypto {
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static String DELIMITER = "@~@~@";
    private static int ITERATION_COUNT = 10000;
    private static int KEY_LENGTH = 256;
    public static final String PBKDF2_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int PKCS5_SALT_LENGTH = 8;
    private static final String TAG = "Crypto";
    private static SecureRandom random = new SecureRandom();

    public static SecretKey deriveKeyPbkdf2(byte[] bArr, String str) throws NoSuchAlgorithmException, InvalidKeySpecException {
        long currentTimeMillis = System.currentTimeMillis();
        byte[] encoded = SecretKeyFactory.getInstance(PBKDF2_DERIVATION_ALGORITHM).generateSecret(new PBEKeySpec(str.toCharArray(), bArr, ITERATION_COUNT, KEY_LENGTH)).getEncoded();
        String str2 = TAG;
        Log.d(str2, "key bytes: " + toHex(encoded));
        SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, "AES");
        Log.d(TAG, String.format("PBKDF2 key derivation took %d [ms].", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return secretKeySpec;
    }

    public static byte[] generateIv(int i) {
        byte[] bArr = new byte[i];
        random.nextBytes(bArr);
        return bArr;
    }

    public static byte[] generateSalt() {
        byte[] bArr = new byte[8];
        random.nextBytes(bArr);
        return bArr;
    }

    public static String encrypt(String str, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        byte[] generateSalt = generateSalt();
        SecretKey deriveKeyPbkdf2 = deriveKeyPbkdf2(generateSalt, str2);
        Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
        byte[] generateIv = generateIv(instance.getBlockSize());
        String str3 = TAG;
        Log.d(str3, "IV: " + toHex(generateIv));
        instance.init(1, deriveKeyPbkdf2, new IvParameterSpec(generateIv));
        String str4 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Cipher IV: ");
        sb.append(instance.getIV() == null ? null : toHex(instance.getIV()));
        Log.d(str4, sb.toString());
        byte[] doFinal = instance.doFinal(str.getBytes(HttpRequest.CHARSET_UTF8));
        if (generateSalt != null) {
            return String.format("%s%s%s%s%s", new Object[]{toBase64(generateSalt), DELIMITER, toBase64(generateIv), DELIMITER, toBase64(doFinal)});
        }
        return String.format("%s%s%s", new Object[]{toBase64(generateIv), DELIMITER, toBase64(doFinal)});
    }

    public static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02X", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return sb.toString();
    }

    public static String toBase64(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static byte[] fromBase64(String str) {
        return Base64.decode(str, 2);
    }

    public static String decrypt(byte[] bArr, SecretKey secretKey, byte[] bArr2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        Cipher instance = Cipher.getInstance(CIPHER_ALGORITHM);
        instance.init(2, secretKey, new IvParameterSpec(bArr2));
        String str = TAG;
        Log.d(str, "Cipher IV: " + toHex(instance.getIV()));
        return new String(instance.doFinal(bArr), HttpRequest.CHARSET_UTF8);
    }

    public static String decryptPbkdf2(String str, String str2) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String[] split = str.split(DELIMITER);
        if (split.length == 3) {
            byte[] fromBase64 = fromBase64(split[0]);
            return decrypt(fromBase64(split[2]), deriveKeyPbkdf2(fromBase64, str2), fromBase64(split[1]));
        }
        throw new IllegalArgumentException("Invalid encypted text format");
    }
}
