package org.bytedeco.javacpp.indexer;

class ReverseUnsafeRaw extends UnsafeRaw {
    ReverseUnsafeRaw() {
    }

    /* access modifiers changed from: package-private */
    public short getShort(long j) {
        return Short.reverseBytes(super.getShort(j));
    }

    /* access modifiers changed from: package-private */
    public void putShort(long j, short s) {
        super.putShort(j, Short.reverseBytes(s));
    }

    /* access modifiers changed from: package-private */
    public int getInt(long j) {
        return Integer.reverseBytes(super.getInt(j));
    }

    /* access modifiers changed from: package-private */
    public void putInt(long j, int i) {
        super.putInt(j, Integer.reverseBytes(i));
    }

    /* access modifiers changed from: package-private */
    public long getLong(long j) {
        return Long.reverseBytes(super.getLong(j));
    }

    /* access modifiers changed from: package-private */
    public void putLong(long j, long j2) {
        super.putLong(j, Long.reverseBytes(j2));
    }

    /* access modifiers changed from: package-private */
    public float getFloat(long j) {
        return Float.intBitsToFloat(Integer.reverseBytes(super.getInt(j)));
    }

    /* access modifiers changed from: package-private */
    public void putFloat(long j, float f) {
        super.putFloat(j, (float) Integer.reverseBytes(Float.floatToRawIntBits(f)));
    }

    /* access modifiers changed from: package-private */
    public double getDouble(long j) {
        return Double.longBitsToDouble(Long.reverseBytes(super.getLong(j)));
    }

    /* access modifiers changed from: package-private */
    public void putDouble(long j, double d) {
        super.putDouble(j, (double) Long.reverseBytes(Double.doubleToRawLongBits(d)));
    }

    /* access modifiers changed from: package-private */
    public char getChar(long j) {
        return Character.reverseBytes(super.getChar(j));
    }

    /* access modifiers changed from: package-private */
    public void putChar(long j, char c) {
        super.putChar(j, Character.reverseBytes(c));
    }

    /* access modifiers changed from: package-private */
    public short getShort(byte[] bArr, long j) {
        return Short.reverseBytes(super.getShort(bArr, j));
    }

    /* access modifiers changed from: package-private */
    public void putShort(byte[] bArr, long j, short s) {
        super.putShort(bArr, j, Short.reverseBytes(s));
    }

    /* access modifiers changed from: package-private */
    public int getInt(byte[] bArr, long j) {
        return Integer.reverseBytes(super.getInt(bArr, j));
    }

    /* access modifiers changed from: package-private */
    public void putInt(byte[] bArr, long j, int i) {
        super.putInt(bArr, j, Integer.reverseBytes(i));
    }

    /* access modifiers changed from: package-private */
    public long getLong(byte[] bArr, long j) {
        return Long.reverseBytes(super.getLong(bArr, j));
    }

    /* access modifiers changed from: package-private */
    public void putLong(byte[] bArr, long j, long j2) {
        super.putLong(bArr, j, Long.reverseBytes(j2));
    }

    /* access modifiers changed from: package-private */
    public float getFloat(byte[] bArr, long j) {
        return Float.intBitsToFloat(Integer.reverseBytes(super.getInt(bArr, j)));
    }

    /* access modifiers changed from: package-private */
    public void putFloat(byte[] bArr, long j, float f) {
        super.putFloat(bArr, j, (float) Integer.reverseBytes(Float.floatToRawIntBits(f)));
    }

    /* access modifiers changed from: package-private */
    public double getDouble(byte[] bArr, long j) {
        return Double.longBitsToDouble(Long.reverseBytes(super.getLong(bArr, j)));
    }

    /* access modifiers changed from: package-private */
    public void putDouble(byte[] bArr, long j, double d) {
        super.putDouble(bArr, j, (double) Long.reverseBytes(Double.doubleToRawLongBits(d)));
    }

    /* access modifiers changed from: package-private */
    public char getChar(byte[] bArr, long j) {
        return Character.reverseBytes(super.getChar(bArr, j));
    }

    /* access modifiers changed from: package-private */
    public void putChar(byte[] bArr, long j, char c) {
        super.putChar(bArr, j, Character.reverseBytes(c));
    }
}
