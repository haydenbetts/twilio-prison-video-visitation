package fm.liveswitch.sdp;

import fm.liveswitch.EncryptionMode;
import fm.liveswitch.Global;

public abstract class CryptoSuite {
    public static String getAesCM128HmacSha180() {
        return "AES_CM_128_HMAC_SHA1_80";
    }

    public static String getAesCm128HmacSha132() {
        return "AES_CM_128_HMAC_SHA1_32";
    }

    public static String getNullHmacSha132() {
        return "NULL_HMAC_SHA1_32";
    }

    public static String getNullHmacSha180() {
        return "NULL_HMAC_SHA1_80";
    }

    public static String getCryptoSuite(EncryptionMode encryptionMode) {
        if (Global.equals(encryptionMode, EncryptionMode.Aes128Strong)) {
            return getAesCM128HmacSha180();
        }
        if (Global.equals(encryptionMode, EncryptionMode.Aes128Weak)) {
            return getAesCm128HmacSha132();
        }
        if (Global.equals(encryptionMode, EncryptionMode.NullStrong)) {
            return getNullHmacSha180();
        }
        if (Global.equals(encryptionMode, EncryptionMode.NullWeak)) {
            return getNullHmacSha132();
        }
        return null;
    }

    public static EncryptionMode getEncryptionMode(String str) {
        if (Global.equals(str, getAesCM128HmacSha180())) {
            return EncryptionMode.Aes128Strong;
        }
        if (Global.equals(str, getAesCm128HmacSha132())) {
            return EncryptionMode.Aes128Weak;
        }
        if (Global.equals(str, getNullHmacSha180())) {
            return EncryptionMode.NullStrong;
        }
        if (Global.equals(str, getNullHmacSha132())) {
            return EncryptionMode.NullWeak;
        }
        return EncryptionMode.Null;
    }
}
