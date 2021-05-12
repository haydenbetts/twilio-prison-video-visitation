package org.bytedeco.javacpp.indexer;

public abstract class Index {
    protected final long[] sizes;

    public abstract long index(long... jArr);

    public static Index create(long j) {
        return new OneIndex(j);
    }

    public static Index create(long... jArr) {
        return new StrideIndex(jArr);
    }

    public static Index create(long[] jArr, long[] jArr2) {
        return new StrideIndex(jArr, jArr2);
    }

    public static Index create(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        return new HyperslabIndex(jArr, jArr2, jArr3, jArr4, jArr5);
    }

    public static Index create(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5, long[] jArr6) {
        return new HyperslabIndex(jArr, jArr2, jArr3, jArr4, jArr5, jArr6);
    }

    public Index(long... jArr) {
        this.sizes = jArr;
    }

    public int rank() {
        return this.sizes.length;
    }

    public long[] sizes() {
        return this.sizes;
    }

    public long size(int i) {
        return this.sizes[i];
    }

    public long index(long j) {
        return index(j);
    }

    public long index(long j, long j2) {
        return index(j, j2);
    }

    public long index(long j, long j2, long j3) {
        return index(j, j2, j3);
    }
}
