package org.bytedeco.javacpp.indexer;

public class DoubleArrayIndexer extends DoubleIndexer {
    protected double[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public DoubleArrayIndexer(double[] dArr) {
        this(dArr, Index.create((long) dArr.length));
    }

    public DoubleArrayIndexer(double[] dArr, long... jArr) {
        this(dArr, Index.create(jArr));
    }

    public DoubleArrayIndexer(double[] dArr, long[] jArr, long[] jArr2) {
        this(dArr, Index.create(jArr, jArr2));
    }

    public DoubleArrayIndexer(double[] dArr, Index index) {
        super(index);
        this.array = dArr;
    }

    public double[] array() {
        return this.array;
    }

    public DoubleIndexer reindex(Index index) {
        return new DoubleArrayIndexer(this.array, index);
    }

    public double get(long j) {
        return this.array[(int) index(j)];
    }

    public DoubleIndexer get(long j, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public double get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public DoubleIndexer get(long j, long j2, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public double get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public double get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public DoubleIndexer get(long[] jArr, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public DoubleIndexer put(long j, double d) {
        this.array[(int) index(j)] = d;
        return this;
    }

    public DoubleIndexer put(long j, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = dArr[i + i3];
        }
        return this;
    }

    public DoubleIndexer put(long j, long j2, double d) {
        this.array[(int) index(j, j2)] = d;
        return this;
    }

    public DoubleIndexer put(long j, long j2, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = dArr[i + i3];
        }
        return this;
    }

    public DoubleIndexer put(long j, long j2, long j3, double d) {
        this.array[(int) index(j, j2, j3)] = d;
        return this;
    }

    public DoubleIndexer put(long[] jArr, double d) {
        this.array[(int) index(jArr)] = d;
        return this;
    }

    public DoubleIndexer put(long[] jArr, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = dArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
