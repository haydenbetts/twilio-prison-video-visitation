package fm.liveswitch;

import java.util.HashMap;

public abstract class Report {
    /* access modifiers changed from: protected */
    public void deserializeProperties(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void serializeProperties(HashMap<String, String> hashMap) {
    }

    public static <T extends IEquivalent<T>> T[] processArray(T[] tArr, T[] tArr2) {
        if (tArr == tArr2) {
            return null;
        }
        if (tArr == null || tArr2 == null || ArrayExtensions.getLength((Object[]) tArr2) != ArrayExtensions.getLength((Object[]) tArr)) {
            return tArr;
        }
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) tArr); i++) {
            if (!tArr[i].isEquivalent(tArr2[i])) {
                return tArr;
            }
        }
        return null;
    }

    public static NullableBoolean processBoolean(boolean z, boolean z2) {
        return Global.equals(Boolean.valueOf(z), Boolean.valueOf(z2)) ? new NullableBoolean((Boolean) null) : new NullableBoolean(z);
    }

    public static boolean[] processBooleanArray(boolean[] zArr, boolean[] zArr2) {
        if (zArr == zArr2) {
            return null;
        }
        if (zArr == null || zArr2 == null || ArrayExtensions.getLength(zArr2) != ArrayExtensions.getLength(zArr)) {
            return zArr;
        }
        for (int i = 0; i < ArrayExtensions.getLength(zArr); i++) {
            if (!Global.equals(Boolean.valueOf(zArr[i]), Boolean.valueOf(zArr2[i]))) {
                return zArr;
            }
        }
        return null;
    }

    public static NullableDouble processDouble(double d, double d2) {
        return d == d2 ? new NullableDouble((Double) null) : new NullableDouble(d);
    }

    public static NullableFloat processFloat(float f, float f2) {
        return f == f2 ? new NullableFloat((Float) null) : new NullableFloat(f);
    }

    public static float[] processFloatArray(float[] fArr, float[] fArr2) {
        if (fArr == fArr2) {
            return null;
        }
        if (fArr == null || fArr2 == null || ArrayExtensions.getLength(fArr2) != ArrayExtensions.getLength(fArr)) {
            return fArr;
        }
        for (int i = 0; i < ArrayExtensions.getLength(fArr); i++) {
            if (fArr[i] != fArr2[i]) {
                return fArr;
            }
        }
        return null;
    }

    public static double[] processFloatArray(double[] dArr, double[] dArr2) {
        if (dArr == dArr2) {
            return null;
        }
        if (dArr == null || dArr2 == null || ArrayExtensions.getLength(dArr2) != ArrayExtensions.getLength(dArr)) {
            return dArr;
        }
        for (int i = 0; i < ArrayExtensions.getLength(dArr); i++) {
            if (dArr[i] != dArr2[i]) {
                return dArr;
            }
        }
        return null;
    }

    public static NullableInteger processInteger(int i, int i2) {
        return i == i2 ? new NullableInteger((Integer) null) : new NullableInteger(i);
    }

    public static int[] processIntegerArray(int[] iArr, int[] iArr2) {
        if (iArr == iArr2) {
            return null;
        }
        if (iArr == null || iArr2 == null || ArrayExtensions.getLength(iArr2) != ArrayExtensions.getLength(iArr)) {
            return iArr;
        }
        for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
            if (iArr[i] != iArr2[i]) {
                return iArr;
            }
        }
        return null;
    }

    public static NullableLong processLong(long j, long j2) {
        return j == j2 ? new NullableLong((Long) null) : new NullableLong(j);
    }

    public static long[] processLongArray(long[] jArr, long[] jArr2) {
        if (jArr == jArr2) {
            return null;
        }
        if (jArr == null || jArr2 == null || ArrayExtensions.getLength(jArr2) != ArrayExtensions.getLength(jArr)) {
            return jArr;
        }
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            if (jArr[i] != jArr2[i]) {
                return jArr;
            }
        }
        return null;
    }

    public static <T extends IEquivalent<T>> T processObject(T t, T t2) {
        if (t == t2) {
            return null;
        }
        if (t == null || t2 == null || !((IEquivalent) t).isEquivalent(t2)) {
            return t;
        }
        return null;
    }

    public static NullableShort processShort(short s, short s2) {
        return s == s2 ? new NullableShort((Short) null) : new NullableShort(s);
    }

    public static short[] processShortArray(short[] sArr, short[] sArr2) {
        if (sArr == sArr2) {
            return null;
        }
        if (sArr == null || sArr2 == null || ArrayExtensions.getLength(sArr2) != ArrayExtensions.getLength(sArr)) {
            return sArr;
        }
        for (int i = 0; i < ArrayExtensions.getLength(sArr); i++) {
            if (sArr[i] != sArr2[i]) {
                return sArr;
            }
        }
        return null;
    }

    public static String processString(String str, String str2) {
        if (Global.equals(str, str2)) {
            return null;
        }
        return str;
    }

    protected Report() {
    }
}
