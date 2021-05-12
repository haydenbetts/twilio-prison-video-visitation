package fm.liveswitch;

class X509PublicKeyAlgorithm {
    public static long[] getEcPublicKey() {
        return new long[]{1, 2, 840, 10045, 2, 1};
    }

    public static long[] getRsaEncryption() {
        return new long[]{1, 2, 840, 113549, 1, 1, 1};
    }
}
