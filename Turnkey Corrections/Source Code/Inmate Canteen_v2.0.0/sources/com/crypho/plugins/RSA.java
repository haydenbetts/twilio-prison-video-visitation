package com.crypho.plugins;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.util.Calendar;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

public class RSA {
    private static final Cipher CIPHER = getCipher();
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";

    public static byte[] encrypt(byte[] bArr, String str) throws Exception {
        return runCipher(1, str, bArr);
    }

    public static byte[] decrypt(byte[] bArr, String str) throws Exception {
        return runCipher(2, str, bArr);
    }

    public static void createKeyPair(Context context, String str) throws Exception {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(1, 100);
        KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context).setAlias(str).setSubject(new X500Principal(String.format("CN=%s, OU=%s", new Object[]{str, context.getPackageName()}))).setSerialNumber(BigInteger.ONE).setStartDate(instance.getTime()).setEndDate(instance2.getTime()).setEncryptionRequired().setKeySize(2048).setKeyType("RSA").build();
        KeyPairGenerator instance3 = KeyPairGenerator.getInstance("RSA", KEYSTORE_PROVIDER);
        instance3.initialize(build);
        instance3.generateKeyPair();
    }

    public static boolean isEntryAvailable(String str) {
        try {
            return loadKey(1, str) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    private static byte[] runCipher(int i, String str, byte[] bArr) throws Exception {
        byte[] doFinal;
        Key loadKey = loadKey(i, str);
        synchronized (CIPHER) {
            CIPHER.init(i, loadKey);
            doFinal = CIPHER.doFinal(bArr);
        }
        return doFinal;
    }

    private static Key loadKey(int i, String str) throws Exception {
        Key key;
        KeyStore instance = KeyStore.getInstance(KEYSTORE_PROVIDER);
        instance.load((InputStream) null, (char[]) null);
        switch (i) {
            case 1:
                key = instance.getCertificate(str).getPublicKey();
                if (key == null) {
                    throw new Exception("Failed to load the public key for " + str);
                }
                break;
            case 2:
                key = instance.getKey(str, (char[]) null);
                if (key == null) {
                    throw new Exception("Failed to load the private key for " + str);
                }
                break;
            default:
                throw new Exception("Invalid cipher mode parameter");
        }
        return key;
    }

    private static Cipher getCipher() {
        try {
            return Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (Exception unused) {
            return null;
        }
    }
}
