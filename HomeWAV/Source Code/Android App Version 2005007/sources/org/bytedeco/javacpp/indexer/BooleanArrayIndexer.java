package org.bytedeco.javacpp.indexer;

public class BooleanArrayIndexer extends BooleanIndexer {
    protected boolean[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public BooleanArrayIndexer(boolean[] zArr) {
        this(zArr, Index.create((long) zArr.length));
    }

    public BooleanArrayIndexer(boolean[] zArr, long... jArr) {
        this(zArr, Index.create(jArr));
    }

    public BooleanArrayIndexer(boolean[] zArr, long[] jArr, long[] jArr2) {
        this(zArr, Index.create(jArr, jArr2));
    }

    public BooleanArrayIndexer(boolean[] zArr, Index index) {
        super(index);
        this.array = zArr;
    }

    public boolean[] array() {
        return this.array;
    }

    public BooleanIndexer reindex(Index index) {
        return new BooleanArrayIndexer(this.array, index);
    }

    public boolean get(long j) {
        return this.array[(int) index(j)];
    }

    public BooleanIndexer get(long j, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public boolean get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public BooleanIndexer get(long j, long j2, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public boolean get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public boolean get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public BooleanIndexer get(long[] jArr, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public BooleanIndexer put(long j, boolean z) {
        this.array[(int) index(j)] = z;
        return this;
    }

    public BooleanIndexer put(long j, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = zArr[i + i3];
        }
        return this;
    }

    public BooleanIndexer put(long j, long j2, boolean z) {
        this.array[(int) index(j, j2)] = z;
        return this;
    }

    public BooleanIndexer put(long j, long j2, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = zArr[i + i3];
        }
        return this;
    }

    public BooleanIndexer put(long j, long j2, long j3, boolean z) {
        this.array[(int) index(j, j2, j3)] = z;
        return this;
    }

    public BooleanIndexer put(long[] jArr, boolean z) {
        this.array[(int) index(jArr)] = z;
        return this;
    }

    public BooleanIndexer put(long[] jArr, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = zArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
