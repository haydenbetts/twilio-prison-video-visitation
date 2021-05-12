package com.twilio.video;

import com.urbanairship.json.matchers.ArrayContainsMatcher;
import java.util.Objects;

final class Preconditions {
    private Preconditions() {
    }

    static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    static void checkArgument(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c)));
        }
    }

    static void checkArgument(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i)));
        }
    }

    static void checkArgument(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j)));
        }
    }

    static void checkArgument(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj));
        }
    }

    static void checkArgument(boolean z, String str, char c, char c2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), Character.valueOf(c2)));
        }
    }

    static void checkArgument(boolean z, String str, char c, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), Integer.valueOf(i)));
        }
    }

    static void checkArgument(boolean z, String str, char c, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), Long.valueOf(j)));
        }
    }

    static void checkArgument(boolean z, String str, char c, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Character.valueOf(c), obj));
        }
    }

    static void checkArgument(boolean z, String str, int i, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), Character.valueOf(c)));
        }
    }

    static void checkArgument(boolean z, String str, int i, int i2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    static void checkArgument(boolean z, String str, int i, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), Long.valueOf(j)));
        }
    }

    static void checkArgument(boolean z, String str, int i, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Integer.valueOf(i), obj));
        }
    }

    static void checkArgument(boolean z, String str, long j, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), Character.valueOf(c)));
        }
    }

    static void checkArgument(boolean z, String str, long j, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), Integer.valueOf(i)));
        }
    }

    static void checkArgument(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    static void checkArgument(boolean z, String str, long j, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(format(str, Long.valueOf(j), obj));
        }
    }

    static void checkArgument(boolean z, String str, Object obj, char c) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Character.valueOf(c)));
        }
    }

    static void checkArgument(boolean z, String str, Object obj, int i) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Integer.valueOf(i)));
        }
    }

    static void checkArgument(boolean z, String str, Object obj, long j) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, Long.valueOf(j)));
        }
    }

    static void checkArgument(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2));
        }
    }

    static void checkArgument(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3));
        }
    }

    static void checkArgument(boolean z, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (!z) {
            throw new IllegalArgumentException(format(str, obj, obj2, obj3, obj4));
        }
    }

    static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(format(str, objArr));
        }
    }

    static void checkState(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c)));
        }
    }

    static void checkState(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i)));
        }
    }

    static void checkState(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j)));
        }
    }

    static void checkState(boolean z, String str, Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, obj));
        }
    }

    static void checkState(boolean z, String str, char c, char c2) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), Character.valueOf(c2)));
        }
    }

    static void checkState(boolean z, String str, char c, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), Integer.valueOf(i)));
        }
    }

    static void checkState(boolean z, String str, char c, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), Long.valueOf(j)));
        }
    }

    static void checkState(boolean z, String str, char c, Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Character.valueOf(c), obj));
        }
    }

    static void checkState(boolean z, String str, int i, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), Character.valueOf(c)));
        }
    }

    static void checkState(boolean z, String str, int i, int i2) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    static void checkState(boolean z, String str, int i, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), Long.valueOf(j)));
        }
    }

    static void checkState(boolean z, String str, int i, Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Integer.valueOf(i), obj));
        }
    }

    static void checkState(boolean z, String str, long j, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), Character.valueOf(c)));
        }
    }

    static void checkState(boolean z, String str, long j, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), Integer.valueOf(i)));
        }
    }

    static void checkState(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    static void checkState(boolean z, String str, long j, Object obj) {
        if (!z) {
            throw new IllegalStateException(format(str, Long.valueOf(j), obj));
        }
    }

    static void checkState(boolean z, String str, Object obj, char c) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Character.valueOf(c)));
        }
    }

    static void checkState(boolean z, String str, Object obj, int i) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Integer.valueOf(i)));
        }
    }

    static void checkState(boolean z, String str, Object obj, long j) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, Long.valueOf(j)));
        }
    }

    static void checkState(boolean z, String str, Object obj, Object obj2) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2));
        }
    }

    static void checkState(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2, obj3));
        }
    }

    static void checkState(boolean z, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (!z) {
            throw new IllegalStateException(format(str, obj, obj2, obj3, obj4));
        }
    }

    static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    static <T> T checkNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, objArr));
    }

    static <T> T checkNotNull(T t, String str, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c)));
    }

    static <T> T checkNotNull(T t, String str, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i)));
    }

    static <T> T checkNotNull(T t, String str, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j)));
    }

    static <T> T checkNotNull(T t, String str, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj));
    }

    static <T> T checkNotNull(T t, String str, char c, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    static <T> T checkNotNull(T t, String str, char c, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), Integer.valueOf(i)));
    }

    static <T> T checkNotNull(T t, String str, char c, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), Long.valueOf(j)));
    }

    static <T> T checkNotNull(T t, String str, char c, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Character.valueOf(c), obj));
    }

    static <T> T checkNotNull(T t, String str, int i, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), Character.valueOf(c)));
    }

    static <T> T checkNotNull(T t, String str, int i, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    static <T> T checkNotNull(T t, String str, int i, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), Long.valueOf(j)));
    }

    static <T> T checkNotNull(T t, String str, int i, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Integer.valueOf(i), obj));
    }

    static <T> T checkNotNull(T t, String str, long j, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), Character.valueOf(c)));
    }

    static <T> T checkNotNull(T t, String str, long j, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), Integer.valueOf(i)));
    }

    static <T> T checkNotNull(T t, String str, long j, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    static <T> T checkNotNull(T t, String str, long j, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, Long.valueOf(j), obj));
    }

    static <T> T checkNotNull(T t, String str, Object obj, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Character.valueOf(c)));
    }

    static <T> T checkNotNull(T t, String str, Object obj, int i) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Integer.valueOf(i)));
    }

    static <T> T checkNotNull(T t, String str, Object obj, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, Long.valueOf(j)));
    }

    static <T> T checkNotNull(T t, String str, Object obj, Object obj2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2));
    }

    static <T> T checkNotNull(T t, String str, Object obj, Object obj2, Object obj3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3));
    }

    static <T> T checkNotNull(T t, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(format(str, obj, obj2, obj3, obj4));
    }

    static int checkElementIndex(int i, int i2) {
        return checkElementIndex(i, i2, ArrayContainsMatcher.INDEX_KEY);
    }

    static int checkElementIndex(int i, int i2, String str) {
        if (i >= 0 && i < i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(badElementIndex(i, i2, str));
    }

    private static String badElementIndex(int i, int i2, String str) {
        if (i < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return format("%s (%s) must be less than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            throw new IllegalArgumentException("negative size: " + i2);
        }
    }

    static int checkPositionIndex(int i, int i2) {
        return checkPositionIndex(i, i2, ArrayContainsMatcher.INDEX_KEY);
    }

    static int checkPositionIndex(int i, int i2, String str) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(badPositionIndex(i, i2, str));
    }

    private static String badPositionIndex(int i, int i2, String str) {
        if (i < 0) {
            return format("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return format("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            throw new IllegalArgumentException("negative size: " + i2);
        }
    }

    static void checkPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i2 < i || i2 > i3) {
            throw new IndexOutOfBoundsException(badPositionIndexes(i, i2, i3));
        }
    }

    private static String badPositionIndexes(int i, int i2, int i3) {
        if (i < 0 || i > i3) {
            return badPositionIndex(i, i3, "start index");
        }
        if (i2 < 0 || i2 > i3) {
            return badPositionIndex(i2, i3, "end index");
        }
        return format("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
    }

    static String format(String str, Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i = 0;
        int i2 = 0;
        while (i < objArr.length && (indexOf = valueOf.indexOf("%s", i2)) != -1) {
            sb.append(valueOf, i2, indexOf);
            sb.append(objArr[i]);
            int i3 = i + 1;
            i2 = indexOf + 2;
            i = i3;
        }
        sb.append(valueOf, i2, valueOf.length());
        if (i < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i4 = i + 1; i4 < objArr.length; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
