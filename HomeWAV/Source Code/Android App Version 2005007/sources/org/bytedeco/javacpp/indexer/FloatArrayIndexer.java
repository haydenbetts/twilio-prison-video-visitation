package org.bytedeco.javacpp.indexer;

public class FloatArrayIndexer extends FloatIndexer {
    protected float[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public FloatArrayIndexer(float[] fArr) {
        this(fArr, Index.create((long) fArr.length));
    }

    public FloatArrayIndexer(float[] fArr, long... jArr) {
        this(fArr, Index.create(jArr));
    }

    public FloatArrayIndexer(float[] fArr, long[] jArr, long[] jArr2) {
        this(fArr, Index.create(jArr, jArr2));
    }

    public FloatArrayIndexer(float[] fArr, Index index) {
        super(index);
        this.array = fArr;
    }

    public float[] array() {
        return this.array;
    }

    public FloatIndexer reindex(Index index) {
        return new FloatArrayIndexer(this.array, index);
    }

    public float get(long j) {
        return this.array[(int) index(j)];
    }

    public FloatIndexer get(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public float get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public FloatIndexer get(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public float get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public float get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public FloatIndexer get(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public FloatIndexer put(long j, float f) {
        this.array[(int) index(j)] = f;
        return this;
    }

    public FloatIndexer put(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = fArr[i + i3];
        }
        return this;
    }

    public FloatIndexer put(long j, long j2, float f) {
        this.array[(int) index(j, j2)] = f;
        return this;
    }

    public FloatIndexer put(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = fArr[i + i3];
        }
        return this;
    }

    public FloatIndexer put(long j, long j2, long j3, float f) {
        this.array[(int) index(j, j2, j3)] = f;
        return this;
    }

    public FloatIndexer put(long[] jArr, float f) {
        this.array[(int) index(jArr)] = f;
        return this;
    }

    public FloatIndexer put(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = fArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
