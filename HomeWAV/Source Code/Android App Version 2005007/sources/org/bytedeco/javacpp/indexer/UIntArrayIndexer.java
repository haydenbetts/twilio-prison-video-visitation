package org.bytedeco.javacpp.indexer;

public class UIntArrayIndexer extends UIntIndexer {
    protected int[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public UIntArrayIndexer(int[] iArr) {
        this(iArr, Index.create((long) iArr.length));
    }

    public UIntArrayIndexer(int[] iArr, long... jArr) {
        this(iArr, Index.create(jArr));
    }

    public UIntArrayIndexer(int[] iArr, long[] jArr, long[] jArr2) {
        this(iArr, Index.create(jArr, jArr2));
    }

    public UIntArrayIndexer(int[] iArr, Index index) {
        super(index);
        this.array = iArr;
    }

    public int[] array() {
        return this.array;
    }

    public UIntIndexer reindex(Index index) {
        return new UIntArrayIndexer(this.array, index);
    }

    public long get(long j) {
        return ((long) this.array[(int) index(j)]) & 4294967295L;
    }

    public UIntIndexer get(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = ((long) this.array[((int) index(j)) + i3]) & 4294967295L;
        }
        return this;
    }

    public long get(long j, long j2) {
        return ((long) this.array[(int) index(j, j2)]) & 4294967295L;
    }

    public UIntIndexer get(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = ((long) this.array[((int) index(j, j2)) + i3]) & 4294967295L;
        }
        return this;
    }

    public long get(long j, long j2, long j3) {
        return ((long) this.array[(int) index(j, j2, j3)]) & 4294967295L;
    }

    public long get(long... jArr) {
        return ((long) this.array[(int) index(jArr)]) & 4294967295L;
    }

    public UIntIndexer get(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr2[i + i3] = ((long) this.array[((int) index(jArr)) + i3]) & 4294967295L;
        }
        return this;
    }

    public UIntIndexer put(long j, long j2) {
        this.array[(int) index(j)] = (int) j2;
        return this;
    }

    public UIntIndexer put(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = (int) jArr[i + i3];
        }
        return this;
    }

    public UIntIndexer put(long j, long j2, long j3) {
        this.array[(int) index(j, j2)] = (int) j3;
        return this;
    }

    public UIntIndexer put(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = (int) jArr[i + i3];
        }
        return this;
    }

    public UIntIndexer put(long j, long j2, long j3, long j4) {
        this.array[(int) index(j, j2, j3)] = (int) j4;
        return this;
    }

    public UIntIndexer put(long[] jArr, long j) {
        this.array[(int) index(jArr)] = (int) j;
        return this;
    }

    public UIntIndexer put(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = (int) jArr2[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
