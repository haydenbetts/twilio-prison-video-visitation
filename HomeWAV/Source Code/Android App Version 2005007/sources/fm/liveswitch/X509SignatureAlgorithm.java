package fm.liveswitch;

class X509SignatureAlgorithm {
    public static long[] getDsaWithSha1() {
        return new long[]{1, 2, 840, 10040, 4, 3};
    }

    public static long[] getDsaWithSha224() {
        return new long[]{2, 16, 840, 1, 101, 3, 4, 3, 1};
    }

    public static long[] getDsaWithSha256() {
        return new long[]{2, 16, 840, 1, 101, 3, 4, 3, 2};
    }

    public static long[] getEcdsaWithSha1() {
        return new long[]{1, 2, 840, 10045, 4, 1};
    }

    public static long[] getEcdsaWithSha224() {
        return new long[]{1, 2, 840, 10045, 4, 3, 1};
    }

    public static long[] getEcdsaWithSha256() {
        return new long[]{1, 2, 840, 10045, 4, 3, 2};
    }

    public static long[] getEcdsaWithSha384() {
        return new long[]{1, 2, 840, 10045, 4, 3, 3};
    }

    public static long[] getEcdsaWithSha512() {
        return new long[]{1, 2, 840, 10045, 4, 3, 4};
    }

    public static long[] getMd2WithRsaEncryption() {
        return new long[]{1, 2, 840, 113549, 1, 1, 2};
    }

    public static long[] getMd5WithRsaEncryption() {
        return new long[]{1, 2, 840, 113549, 1, 1, 4};
    }

    public static long[] getSha1WithRsaEncryption() {
        return new long[]{1, 2, 840, 113549, 1, 1, 5};
    }

    public static long[] getSha256WithRsaEncryption() {
        return new long[]{1, 2, 840, 113549, 1, 1, 11};
    }

    public static long[] getSha384WithRsaEncryption() {
        return new long[]{1, 2, 840, 113549, 1, 1, 12};
    }

    public static long[] getSha512WithRsaEncryption() {
        return new long[]{1, 2, 840, 113549, 1, 1, 13};
    }
}
