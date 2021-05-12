package org.bytedeco.javacpp.indexer;

import kotlin.UShort;

public class UShortArrayIndexer extends UShortIndexer {
    protected short[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public UShortArrayIndexer(short[] sArr) {
        this(sArr, Index.create((long) sArr.length));
    }

    public UShortArrayIndexer(short[] sArr, long... jArr) {
        this(sArr, Index.create(jArr));
    }

    public UShortArrayIndexer(short[] sArr, long[] jArr, long[] jArr2) {
        this(sArr, Index.create(jArr, jArr2));
    }

    public UShortArrayIndexer(short[] sArr, Index index) {
        super(index);
        this.array = sArr;
    }

    public short[] array() {
        return this.array;
    }

    public UShortIndexer reindex(Index index) {
        return new UShortArrayIndexer(this.array, index);
    }

    public int get(long j) {
        return this.array[(int) index(j)] & UShort.MAX_VALUE;
    }

    public UShortIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(j)) + i3] & UShort.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2) {
        return this.array[(int) index(j, j2)] & UShort.MAX_VALUE;
    }

    public UShortIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(j, j2)) + i3] & UShort.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)] & UShort.MAX_VALUE;
    }

    public int get(long... jArr) {
        return this.array[(int) index(jArr)] & UShort.MAX_VALUE;
    }

    public UShortIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(jArr)) + i3] & UShort.MAX_VALUE;
        }
        return this;
    }

    public UShortIndexer put(long j, int i) {
        this.array[(int) index(j)] = (short) i;
        return this;
    }

    public UShortIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = (short) iArr[i + i3];
        }
        return this;
    }

    public UShortIndexer put(long j, long j2, int i) {
        this.array[(int) index(j, j2)] = (short) i;
        return this;
    }

    public UShortIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = (short) iArr[i + i3];
        }
        return this;
    }

    public UShortIndexer put(long j, long j2, long j3, int i) {
        this.array[(int) index(j, j2, j3)] = (short) i;
        return this;
    }

    public UShortIndexer put(long[] jArr, int i) {
        this.array[(int) index(jArr)] = (short) i;
        return this;
    }

    public UShortIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = (short) iArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
