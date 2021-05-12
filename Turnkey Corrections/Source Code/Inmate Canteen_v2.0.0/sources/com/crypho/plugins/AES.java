package com.crypho.plugins;

import android.util.Base64;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

public class AES {
    private static final Cipher CIPHER = getCipher();
    private static final String CIPHER_MODE = "CCM";
    private static final int KEY_SIZE = 256;
    private static final int VERSION = 1;

    public static JSONObject encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        byte[] encoded;
        byte[] iv;
        byte[] doFinal;
        synchronized (CIPHER) {
            SecretKeySpec generateKeySpec = generateKeySpec();
            encoded = generateKeySpec.getEncoded();
            initCipher(1, generateKeySpec, (byte[]) null, bArr2);
            iv = CIPHER.getIV();
            doFinal = CIPHER.doFinal(bArr);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("iv", Base64.encodeToString(iv, 0));
        jSONObject.put("v", Integer.toString(1));
        jSONObject.put("ks", Integer.toString(256));
        jSONObject.put("cipher", "AES");
        jSONObject.put("mode", CIPHER_MODE);
        jSONObject.put("adata", Base64.encodeToString(bArr2, 0));
        jSONObject.put("ct", Base64.encodeToString(doFinal, 0));
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("key", Base64.encodeToString(encoded, 0));
        jSONObject2.put("value", jSONObject);
        jSONObject2.put("native", true);
        return jSONObject2;
    }

    public static String decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws Exception {
        String str;
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
        synchronized (CIPHER) {
            initCipher(2, secretKeySpec, bArr3, bArr4);
            str = new String(CIPHER.doFinal(bArr));
        }
        return str;
    }

    private static SecretKeySpec generateKeySpec() throws Exception {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        instance.init(256, new SecureRandom());
        return new SecretKeySpec(instance.generateKey().getEncoded(), "AES");
    }

    private static void initCipher(int i, Key key, byte[] bArr, byte[] bArr2) throws Exception {
        if (bArr != null) {
            CIPHER.init(i, key, new IvParameterSpec(bArr));
        } else {
            CIPHER.init(i, key);
        }
        CIPHER.updateAAD(bArr2);
    }

    private static Cipher getCipher() {
        try {
            return Cipher.getInstance("AES/CCM/NoPadding");
        } catch (Exception unused) {
            return null;
        }
    }
}
