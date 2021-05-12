package org.bytedeco.javacpp.indexer;

public class LongArrayIndexer extends LongIndexer {
    protected long[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public LongArrayIndexer(long[] jArr) {
        this(jArr, Index.create((long) jArr.length));
    }

    public LongArrayIndexer(long[] jArr, long... jArr2) {
        this(jArr, Index.create(jArr2));
    }

    public LongArrayIndexer(long[] jArr, long[] jArr2, long[] jArr3) {
        this(jArr, Index.create(jArr2, jArr3));
    }

    public LongArrayIndexer(long[] jArr, Index index) {
        super(index);
        this.array = jArr;
    }

    public long[] array() {
        return this.array;
    }

    public LongIndexer reindex(Index index) {
        return new LongArrayIndexer(this.array, index);
    }

    public long get(long j) {
        return this.array[(int) index(j)];
    }

    public LongIndexer get(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public long get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public LongIndexer get(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public long get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public long get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public LongIndexer get(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr2[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public LongIndexer put(long j, long j2) {
        this.array[(int) index(j)] = j2;
        return this;
    }

    public LongIndexer put(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = jArr[i + i3];
        }
        return this;
    }

    public LongIndexer put(long j, long j2, long j3) {
        this.array[(int) index(j, j2)] = j3;
        return this;
    }

    public LongIndexer put(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = jArr[i + i3];
        }
        return this;
    }

    public LongIndexer put(long j, long j2, long j3, long j4) {
        this.array[(int) index(j, j2, j3)] = j4;
        return this;
    }

    public LongIndexer put(long[] jArr, long j) {
        this.array[(int) index(jArr)] = j;
        return this;
    }

    public LongIndexer put(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = jArr2[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
