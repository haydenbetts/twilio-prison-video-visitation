package org.bytedeco.javacpp.indexer;

public class IntArrayIndexer extends IntIndexer {
    protected int[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public IntArrayIndexer(int[] iArr) {
        this(iArr, Index.create((long) iArr.length));
    }

    public IntArrayIndexer(int[] iArr, long... jArr) {
        this(iArr, Index.create(jArr));
    }

    public IntArrayIndexer(int[] iArr, long[] jArr, long[] jArr2) {
        this(iArr, Index.create(jArr, jArr2));
    }

    public IntArrayIndexer(int[] iArr, Index index) {
        super(index);
        this.array = iArr;
    }

    public int[] array() {
        return this.array;
    }

    public IntIndexer reindex(Index index) {
        return new IntArrayIndexer(this.array, index);
    }

    public int get(long j) {
        return this.array[(int) index(j)];
    }

    public IntIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public int get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public IntIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public int get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public IntIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public IntIndexer put(long j, int i) {
        this.array[(int) index(j)] = i;
        return this;
    }

    public IntIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = iArr[i + i3];
        }
        return this;
    }

    public IntIndexer put(long j, long j2, int i) {
        this.array[(int) index(j, j2)] = i;
        return this;
    }

    public IntIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = iArr[i + i3];
        }
        return this;
    }

    public IntIndexer put(long j, long j2, long j3, int i) {
        this.array[(int) index(j, j2, j3)] = i;
        return this;
    }

    public IntIndexer put(long[] jArr, int i) {
        this.array[(int) index(jArr)] = i;
        return this;
    }

    public IntIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = iArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
