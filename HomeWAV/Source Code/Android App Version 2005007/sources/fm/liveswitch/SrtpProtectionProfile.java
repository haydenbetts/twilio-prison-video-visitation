package fm.liveswitch;

class SrtpProtectionProfile {
    public static int getAes128CmHmacSha132ProtectionProfileCode() {
        return 2;
    }

    public static String getAes128CmHmacSha132ProtectionProfileString() {
        return "SRTP_AES128_CM_HMAC_SHA1_32";
    }

    public static int getAes128CmHmacSha180ProtectionProfileCode() {
        return 1;
    }

    public static String getAes128CmHmacSha180ProtectionProfileString() {
        return "SRTP_AES128_CM_HMAC_SHA1_80";
    }

    public static int getNullHmacSha132ProtectionProfileCode() {
        return 6;
    }

    public static String getNullHmacSha132ProtectionProfileString() {
        return "SRTP_NULL_HMAC_SHA1_32";
    }

    public static int getNullHmacSha180ProtectionProfileCode() {
        return 5;
    }

    public static String getNullHmacSha180ProtectionProfileString() {
        return "SRTP_NULL_HMAC_SHA1_80";
    }

    public static int encryptionModeToProtectionProfileCode(EncryptionMode encryptionMode) {
        if (Global.equals(encryptionMode, EncryptionMode.Aes128Strong)) {
            return getAes128CmHmacSha180ProtectionProfileCode();
        }
        if (Global.equals(encryptionMode, EncryptionMode.Aes128Weak)) {
            return getAes128CmHmacSha132ProtectionProfileCode();
        }
        if (Global.equals(encryptionMode, EncryptionMode.NullStrong)) {
            return getNullHmacSha180ProtectionProfileCode();
        }
        if (Global.equals(encryptionMode, EncryptionMode.NullWeak)) {
            return getNullHmacSha132ProtectionProfileCode();
        }
        return 0;
    }

    public static int protectionProfileCodeFromString(String str) {
        if (Global.equals(str, getAes128CmHmacSha180ProtectionProfileString())) {
            return getAes128CmHmacSha180ProtectionProfileCode();
        }
        if (Global.equals(str, getAes128CmHmacSha132ProtectionProfileString())) {
            return getAes128CmHmacSha132ProtectionProfileCode();
        }
        if (Global.equals(str, getNullHmacSha180ProtectionProfileString())) {
            return getNullHmacSha180ProtectionProfileCode();
        }
        if (Global.equals(str, getNullHmacSha132ProtectionProfileString())) {
            return getNullHmacSha132ProtectionProfileCode();
        }
        return 0;
    }

    public static String protectionProfileStringFromCode(int i) {
        if (i == getAes128CmHmacSha180ProtectionProfileCode()) {
            return getAes128CmHmacSha180ProtectionProfileString();
        }
        if (i == getAes128CmHmacSha132ProtectionProfileCode()) {
            return getAes128CmHmacSha132ProtectionProfileString();
        }
        if (i == getNullHmacSha180ProtectionProfileCode()) {
            return getNullHmacSha180ProtectionProfileString();
        }
        if (i == getNullHmacSha132ProtectionProfileCode()) {
            return getNullHmacSha132ProtectionProfileString();
        }
        return null;
    }
}
