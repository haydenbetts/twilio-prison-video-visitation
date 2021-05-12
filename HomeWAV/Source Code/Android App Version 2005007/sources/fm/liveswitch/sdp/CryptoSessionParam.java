package fm.liveswitch.sdp;

public abstract class CryptoSessionParam {
    public static String getUnauthenticatedSRTP() {
        return "UNAUTHENTICATED_SRTP";
    }

    public static String getUnencryptedSRTCP() {
        return "UNENCRYPTED_SRTCP";
    }

    public static String getUnencryptedSRTP() {
        return "UNENCRYPTED_SRTP";
    }
}
