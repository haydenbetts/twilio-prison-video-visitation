package com.google.firebase.iid;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.firebase:firebase-iid@@20.2.3 */
public class EncryptionKeyUtil {
    public static KeyPair generateEcP256KeyPair() {
        return getEcKeyGen().generateKeyPair();
    }

    public static boolean isEcP256Supported() {
        try {
            getEcKeyGen();
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        throw new java.lang.RuntimeException("Unable to find the NIST P-256 curve");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r0.initialize(new java.security.spec.ECGenParameterSpec("secp256r1"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.security.KeyPairGenerator getEcKeyGen() {
        /*
            java.lang.String r0 = "EC"
            java.security.KeyPairGenerator r0 = java.security.KeyPairGenerator.getInstance(r0)     // Catch:{ NoSuchAlgorithmException -> 0x0024 }
            java.security.spec.ECGenParameterSpec r1 = new java.security.spec.ECGenParameterSpec     // Catch:{ InvalidAlgorithmParameterException -> 0x0011 }
            java.lang.String r2 = "prime256v1"
            r1.<init>(r2)     // Catch:{ InvalidAlgorithmParameterException -> 0x0011 }
            r0.initialize(r1)     // Catch:{ InvalidAlgorithmParameterException -> 0x0011 }
            return r0
        L_0x0011:
            java.security.spec.ECGenParameterSpec r1 = new java.security.spec.ECGenParameterSpec     // Catch:{ InvalidAlgorithmParameterException -> 0x001c }
            java.lang.String r2 = "secp256r1"
            r1.<init>(r2)     // Catch:{ InvalidAlgorithmParameterException -> 0x001c }
            r0.initialize(r1)     // Catch:{ InvalidAlgorithmParameterException -> 0x001c }
            return r0
        L_0x001c:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Unable to find the NIST P-256 curve"
            r0.<init>(r1)
            throw r0
        L_0x0024:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.EncryptionKeyUtil.getEcKeyGen():java.security.KeyPairGenerator");
    }

    public static KeyPair generateRSA2048KeyPair() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(2048);
            return instance.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public static SecretKey parseAESKey(byte[] bArr) {
        return new SecretKeySpec(bArr, "AES");
    }
}
