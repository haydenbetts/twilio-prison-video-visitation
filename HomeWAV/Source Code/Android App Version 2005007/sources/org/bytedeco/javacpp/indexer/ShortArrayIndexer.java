package org.bytedeco.javacpp.indexer;

public class ShortArrayIndexer extends ShortIndexer {
    protected short[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public ShortArrayIndexer(short[] sArr) {
        this(sArr, Index.create((long) sArr.length));
    }

    public ShortArrayIndexer(short[] sArr, long... jArr) {
        this(sArr, Index.create(jArr));
    }

    public ShortArrayIndexer(short[] sArr, long[] jArr, long[] jArr2) {
        this(sArr, Index.create(jArr, jArr2));
    }

    public ShortArrayIndexer(short[] sArr, Index index) {
        super(index);
        this.array = sArr;
    }

    public short[] array() {
        return this.array;
    }

    public ShortIndexer reindex(Index index) {
        return new ShortArrayIndexer(this.array, index);
    }

    public short get(long j) {
        return this.array[(int) index(j)];
    }

    public ShortIndexer get(long j, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public short get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public ShortIndexer get(long j, long j2, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public short get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public short get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public ShortIndexer get(long[] jArr, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public ShortIndexer put(long j, short s) {
        this.array[(int) index(j)] = s;
        return this;
    }

    public ShortIndexer put(long j, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = sArr[i + i3];
        }
        return this;
    }

    public ShortIndexer put(long j, long j2, short s) {
        this.array[(int) index(j, j2)] = s;
        return this;
    }

    public ShortIndexer put(long j, long j2, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = sArr[i + i3];
        }
        return this;
    }

    public ShortIndexer put(long j, long j2, long j3, short s) {
        this.array[(int) index(j, j2, j3)] = s;
        return this;
    }

    public ShortIndexer put(long[] jArr, short s) {
        this.array[(int) index(jArr)] = s;
        return this;
    }

    public ShortIndexer put(long[] jArr, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = sArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
