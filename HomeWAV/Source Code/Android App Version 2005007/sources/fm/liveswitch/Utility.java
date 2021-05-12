package fm.liveswitch;

import java.util.ArrayList;

public class Utility {
    public static int getRtpSequenceNumberDelta(int i, int i2) {
        int i3 = i - i2;
        return i3 < -32768 ? i3 + 65536 : i3 >= 32768 ? i3 - 65536 : i3;
    }

    public static long getRtpTimestampDelta(long j, long j2) {
        long j3 = j - j2;
        return j3 < -2147483648L ? j3 + 4294967296L : j3 >= 2147483648L ? j3 - 4294967296L : j3;
    }

    public static <T> ArrayList<T> clone(ArrayList<T> arrayList) {
        ArrayList<T> arrayList2 = new ArrayList<>();
        ArrayListExtensions.addRange(arrayList2, arrayList);
        return arrayList2;
    }

    public static int[] cloneIntArray(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        int[] iArr2 = new int[ArrayExtensions.getLength(iArr)];
        for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
            iArr2[i] = iArr[i];
        }
        return iArr2;
    }

    public static long[] cloneLongArray(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        long[] jArr2 = new long[ArrayExtensions.getLength(jArr)];
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            jArr2[i] = jArr[i];
        }
        return jArr2;
    }

    public static String[] cloneStringArray(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        String[] strArr2 = new String[ArrayExtensions.getLength((Object[]) strArr)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
            strArr2[i] = strArr[i];
        }
        return strArr2;
    }

    public static <T> T firstOrDefault(T[] tArr) {
        if (tArr == null || ArrayExtensions.getLength((Object[]) tArr) == 0) {
            return null;
        }
        return tArr[0];
    }

    public static <T> T firstOrDefault(ArrayList<T> arrayList) {
        if (arrayList == null || ArrayListExtensions.getCount(arrayList) == 0) {
            return null;
        }
        return ArrayListExtensions.getItem(arrayList).get(0);
    }

    public static String formatDoubleAsPercent(double d, int i) {
        int i2 = 0;
        if (i < 0) {
            i = 0;
        }
        String doubleExtensions = DoubleExtensions.toString(Double.valueOf(d * 100.0d));
        String[] split = StringExtensions.split(doubleExtensions, new char[]{'.'});
        if (ArrayExtensions.getLength((Object[]) split) == 1) {
            if (i > 0) {
                doubleExtensions = StringExtensions.concat(doubleExtensions, ".");
            }
            while (i2 < i) {
                doubleExtensions = StringExtensions.concat(doubleExtensions, "0");
                i2++;
            }
            return doubleExtensions;
        }
        String str = split[0];
        if (StringExtensions.getLength(str) == 0) {
            str = "0";
        }
        if (i == 0) {
            return str;
        }
        String str2 = split[1];
        if (StringExtensions.getLength(str2) > i) {
            str2 = StringExtensions.substring(str2, 0, i);
        } else if (StringExtensions.getLength(str2) < i) {
            int length = i - StringExtensions.getLength(str2);
            while (i2 < length) {
                str2 = StringExtensions.concat(str2, "0");
                i2++;
            }
        }
        return StringExtensions.concat(str, ".", str2);
    }

    public static String generateId() {
        return Guid.newGuid().toString().replace("-", "");
    }

    public static long generateSynchronizationSource() {
        byte[] bArr = new byte[4];
        LockedRandomizer.nextBytes(bArr);
        return Binary.fromBytes32(bArr, 0, false);
    }

    public static String generateTieBreaker() {
        return generateId();
    }

    public static <T> T lastOrDefault(T[] tArr) {
        if (tArr == null || ArrayExtensions.getLength((Object[]) tArr) == 0) {
            return null;
        }
        return tArr[ArrayExtensions.getLength((Object[]) tArr) - 1];
    }

    public static <T> T lastOrDefault(ArrayList<T> arrayList) {
        if (arrayList == null || ArrayListExtensions.getCount(arrayList) == 0) {
            return null;
        }
        return ArrayListExtensions.getItem(arrayList).get(ArrayListExtensions.getCount(arrayList) - 1);
    }

    public static <T> T singleOrDefault(T[] tArr) {
        if (tArr == null || ArrayExtensions.getLength((Object[]) tArr) != 1) {
            return null;
        }
        return tArr[0];
    }

    public static <T> T singleOrDefault(ArrayList<T> arrayList) {
        if (arrayList == null || ArrayListExtensions.getCount(arrayList) != 1) {
            return null;
        }
        return ArrayListExtensions.getItem(arrayList).get(0);
    }

    public static <T> T[] splice(T[] tArr, int i, T[] tArr2, IFunction1<Integer, T[]> iFunction1) {
        return splice(tArr, i, 0, tArr2, iFunction1);
    }

    public static <T> T[] splice(T[] tArr, int i, int i2, T[] tArr2, IFunction1<Integer, T[]> iFunction1) {
        int i3;
        if (i < 0 && ArrayExtensions.getLength((Object[]) tArr) + i >= 0) {
            i += ArrayExtensions.getLength((Object[]) tArr);
        }
        if (i < 0 || i2 < 0 || i + i2 > ArrayExtensions.getLength((Object[]) tArr)) {
            throw new RuntimeException(new Exception(StringExtensions.format("Cannot splice {0} items at index {1} from an array of length {2}.", IntegerExtensions.toString(Integer.valueOf(i2)), IntegerExtensions.toString(Integer.valueOf(i)), IntegerExtensions.toString(Integer.valueOf(ArrayExtensions.getLength((Object[]) tArr))))));
        }
        if (tArr2 == null) {
            i3 = 0;
        } else {
            i3 = ArrayExtensions.getLength((Object[]) tArr2);
        }
        T[] tArr3 = (Object[]) iFunction1.invoke(Integer.valueOf((ArrayExtensions.getLength((Object[]) tArr) - i2) + i3));
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            tArr3[i5] = tArr[i4];
            i5++;
            i4++;
        }
        for (int i6 = 0; i6 < i3; i6++) {
            tArr3[i5] = tArr2[i6];
            i5++;
        }
        for (int i7 = i4 + i2; i7 < ArrayExtensions.getLength((Object[]) tArr); i7++) {
            tArr3[i5] = tArr[i7];
            i5++;
        }
        return tArr3;
    }

    public static <T> T[] splice(T[] tArr, int i, int i2, IFunction1<Integer, T[]> iFunction1) {
        return splice(tArr, i, i2, (T[]) null, iFunction1);
    }

    public static int[] toIntArray(ArrayList<Integer> arrayList) {
        int[] iArr = new int[ArrayListExtensions.getCount(arrayList)];
        for (int i = 0; i < ArrayExtensions.getLength(iArr); i++) {
            iArr[i] = ((Integer) ArrayListExtensions.getItem(arrayList).get(i)).intValue();
        }
        return iArr;
    }

    public static <T> ArrayList<T> toList(T[] tArr) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) tArr); i++) {
            arrayList.add(tArr[i]);
        }
        return arrayList;
    }

    public static long[] toLongArray(ArrayList<Long> arrayList) {
        long[] jArr = new long[ArrayListExtensions.getCount(arrayList)];
        for (int i = 0; i < ArrayExtensions.getLength(jArr); i++) {
            jArr[i] = ((Long) ArrayListExtensions.getItem(arrayList).get(i)).longValue();
        }
        return jArr;
    }

    public static String[] toStringArray(ArrayList<String> arrayList) {
        String[] strArr = new String[ArrayListExtensions.getCount(arrayList)];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) strArr); i++) {
            strArr[i] = (String) ArrayListExtensions.getItem(arrayList).get(i);
        }
        return strArr;
    }

    public static <T> void treeFindLeaves(T t, IFunction1<T, T[]> iFunction1, IAction1<T> iAction1) {
        Object[] objArr = (Object[]) iFunction1.invoke(t);
        if (objArr != null) {
            for (Object treeSearch : objArr) {
                treeSearch(treeSearch, iFunction1, iAction1);
            }
            return;
        }
        iAction1.invoke(t);
    }

    public static <T> void treeSearch(T t, IFunction1<T, T[]> iFunction1, IAction1<T> iAction1) {
        iAction1.invoke(t);
        Object[] objArr = (Object[]) iFunction1.invoke(t);
        if (objArr != null) {
            for (Object treeSearch : objArr) {
                treeSearch(treeSearch, iFunction1, iAction1);
            }
        }
    }
}
