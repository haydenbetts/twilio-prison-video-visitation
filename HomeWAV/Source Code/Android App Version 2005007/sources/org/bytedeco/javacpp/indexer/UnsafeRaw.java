package org.bytedeco.javacpp.indexer;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

class UnsafeRaw extends Raw {
    protected static final Unsafe UNSAFE;
    protected static final long arrayOffset;

    UnsafeRaw() {
    }

    static {
        long j;
        Unsafe unsafe = null;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            cls.getDeclaredMethod("getByte", new Class[]{Long.TYPE});
            cls.getDeclaredMethod("getShort", new Class[]{Long.TYPE});
            cls.getDeclaredMethod("getInt", new Class[]{Long.TYPE});
            cls.getDeclaredMethod("getLong", new Class[]{Long.TYPE});
            cls.getDeclaredMethod("getFloat", new Class[]{Long.TYPE});
            cls.getDeclaredMethod("getDouble", new Class[]{Long.TYPE});
            cls.getDeclaredMethod("getChar", new Class[]{Long.TYPE});
            cls.getDeclaredMethod("arrayBaseOffset", new Class[]{Class.class});
            declaredField.setAccessible(true);
            Unsafe unsafe2 = (Unsafe) declaredField.get((Object) null);
            j = (long) unsafe2.arrayBaseOffset(byte[].class);
            unsafe = unsafe2;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException unused) {
            j = 0;
        }
        UNSAFE = unsafe;
        arrayOffset = j;
    }

    static boolean isAvailable() {
        return UNSAFE != null;
    }

    /* access modifiers changed from: package-private */
    public byte getByte(long j) {
        return UNSAFE.getByte(j);
    }

    /* access modifiers changed from: package-private */
    public void putByte(long j, byte b) {
        UNSAFE.putByte(j, b);
    }

    /* access modifiers changed from: package-private */
    public short getShort(long j) {
        return UNSAFE.getShort(j);
    }

    /* access modifiers changed from: package-private */
    public void putShort(long j, short s) {
        UNSAFE.putShort(j, s);
    }

    /* access modifiers changed from: package-private */
    public int getInt(long j) {
        return UNSAFE.getInt(j);
    }

    /* access modifiers changed from: package-private */
    public void putInt(long j, int i) {
        UNSAFE.putInt(j, i);
    }

    /* access modifiers changed from: package-private */
    public long getLong(long j) {
        return UNSAFE.getLong(j);
    }

    /* access modifiers changed from: package-private */
    public void putLong(long j, long j2) {
        UNSAFE.putLong(j, j2);
    }

    /* access modifiers changed from: package-private */
    public float getFloat(long j) {
        return UNSAFE.getFloat(j);
    }

    /* access modifiers changed from: package-private */
    public void putFloat(long j, float f) {
        UNSAFE.putFloat(j, f);
    }

    /* access modifiers changed from: package-private */
    public double getDouble(long j) {
        return UNSAFE.getDouble(j);
    }

    /* access modifiers changed from: package-private */
    public void putDouble(long j, double d) {
        UNSAFE.putDouble(j, d);
    }

    /* access modifiers changed from: package-private */
    public char getChar(long j) {
        return UNSAFE.getChar(j);
    }

    /* access modifiers changed from: package-private */
    public void putChar(long j, char c) {
        UNSAFE.putChar(j, c);
    }

    /* access modifiers changed from: package-private */
    public boolean getBoolean(long j) {
        return UNSAFE.getByte(j) != 0;
    }

    /* access modifiers changed from: package-private */
    public void putBoolean(long j, boolean z) {
        UNSAFE.putByte(j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public byte getByte(byte[] bArr, long j) {
        return UNSAFE.getByte(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putByte(byte[] bArr, long j, byte b) {
        UNSAFE.putByte(bArr, arrayOffset + j, b);
    }

    /* access modifiers changed from: package-private */
    public short getShort(byte[] bArr, long j) {
        return UNSAFE.getShort(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putShort(byte[] bArr, long j, short s) {
        UNSAFE.putShort(bArr, arrayOffset + j, s);
    }

    /* access modifiers changed from: package-private */
    public int getInt(byte[] bArr, long j) {
        return UNSAFE.getInt(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putInt(byte[] bArr, long j, int i) {
        UNSAFE.putInt(bArr, arrayOffset + j, i);
    }

    /* access modifiers changed from: package-private */
    public long getLong(byte[] bArr, long j) {
        return UNSAFE.getLong(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putLong(byte[] bArr, long j, long j2) {
        UNSAFE.putLong(bArr, arrayOffset + j, j2);
    }

    /* access modifiers changed from: package-private */
    public float getFloat(byte[] bArr, long j) {
        return UNSAFE.getFloat(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putFloat(byte[] bArr, long j, float f) {
        UNSAFE.putFloat(bArr, arrayOffset + j, f);
    }

    /* access modifiers changed from: package-private */
    public double getDouble(byte[] bArr, long j) {
        return UNSAFE.getDouble(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putDouble(byte[] bArr, long j, double d) {
        UNSAFE.putDouble(bArr, arrayOffset + j, d);
    }

    /* access modifiers changed from: package-private */
    public char getChar(byte[] bArr, long j) {
        return UNSAFE.getChar(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putChar(byte[] bArr, long j, char c) {
        UNSAFE.putChar(bArr, arrayOffset + j, c);
    }

    /* access modifiers changed from: package-private */
    public boolean getBoolean(byte[] bArr, long j) {
        return UNSAFE.getBoolean(bArr, arrayOffset + j);
    }

    /* access modifiers changed from: package-private */
    public void putBoolean(byte[] bArr, long j, boolean z) {
        UNSAFE.putBoolean(bArr, arrayOffset + j, z);
    }
}
