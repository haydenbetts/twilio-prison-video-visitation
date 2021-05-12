package fm.liveswitch;

class X509ECNamedCurve {
    public static long[] fromEnum(EcdsaNamedCurve ecdsaNamedCurve) {
        if (Global.equals(ecdsaNamedCurve, EcdsaNamedCurve.P256)) {
            return getSecP256R1();
        }
        if (Global.equals(ecdsaNamedCurve, EcdsaNamedCurve.P384)) {
            return getSecP384R1();
        }
        if (Global.equals(ecdsaNamedCurve, EcdsaNamedCurve.P521)) {
            return getSecP521R1();
        }
        throw new RuntimeException(new Exception("Unsupported named curve."));
    }

    public static long[] getSecP192R1() {
        return new long[]{1, 2, 840, 10045, 3, 1, 1};
    }

    public static long[] getSecP224R1() {
        return new long[]{1, 3, 132, 0, 33};
    }

    public static long[] getSecP256R1() {
        return new long[]{1, 2, 840, 10045, 3, 1, 7};
    }

    public static long[] getSecP384R1() {
        return new long[]{1, 3, 132, 0, 34};
    }

    public static long[] getSecP521R1() {
        return new long[]{1, 3, 132, 0, 35};
    }

    public static long[] getSecT163K1() {
        return new long[]{1, 3, 132, 0, 1};
    }

    public static long[] getSecT163R2() {
        return new long[]{1, 3, 132, 0, 15};
    }

    public static long[] getSecT233K1() {
        return new long[]{1, 3, 132, 0, 26};
    }

    public static long[] getSecT233R1() {
        return new long[]{1, 3, 132, 0, 27};
    }

    public static long[] getSecT283K1() {
        return new long[]{1, 3, 132, 0, 16};
    }

    public static long[] getSecT283R1() {
        return new long[]{1, 3, 132, 0, 17};
    }

    public static long[] getSecT409K1() {
        return new long[]{1, 3, 132, 0, 36};
    }

    public static long[] getSecT409R1() {
        return new long[]{1, 3, 132, 0, 37};
    }

    public static long[] getSecT571K1() {
        return new long[]{1, 3, 132, 0, 38};
    }

    public static long[] getSecT571R1() {
        return new long[]{1, 3, 132, 0, 39};
    }

    public static EcdsaNamedCurve toEnum(long[] jArr) {
        if (Asn1ObjectIdentifier.areEqual(jArr, getSecP256R1())) {
            return EcdsaNamedCurve.P256;
        }
        if (Asn1ObjectIdentifier.areEqual(jArr, getSecP384R1())) {
            return EcdsaNamedCurve.P384;
        }
        if (Asn1ObjectIdentifier.areEqual(jArr, getSecP521R1())) {
            return EcdsaNamedCurve.P521;
        }
        throw new RuntimeException(new Exception("Unsupported named curve."));
    }
}
