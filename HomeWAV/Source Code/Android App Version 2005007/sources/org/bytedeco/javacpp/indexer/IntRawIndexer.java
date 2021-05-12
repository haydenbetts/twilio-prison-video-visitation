package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;

public class IntRawIndexer extends IntIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected IntPointer pointer;
    final long size;

    public IntRawIndexer(IntPointer intPointer) {
        this(intPointer, Index.create(intPointer.limit() - intPointer.position()));
    }

    public IntRawIndexer(IntPointer intPointer, long... jArr) {
        this(intPointer, Index.create(jArr));
    }

    public IntRawIndexer(IntPointer intPointer, long[] jArr, long[] jArr2) {
        this(intPointer, Index.create(jArr, jArr2));
    }

    public IntRawIndexer(IntPointer intPointer, Index index) {
        super(index);
        this.pointer = intPointer;
        this.base = intPointer.address() + (intPointer.position() * 4);
        this.size = intPointer.limit() - intPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public IntIndexer reindex(Index index) {
        return new IntRawIndexer(this.pointer, index);
    }

    public int getRaw(long j) {
        return RAW.getInt(this.base + (checkIndex(j, this.size) * 4));
    }

    public int get(long j) {
        return getRaw(index(j));
    }

    public IntIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public int get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public IntIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public int get(long... jArr) {
        return getRaw(index(jArr));
    }

    public IntIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public IntIndexer putRaw(long j, int i) {
        RAW.putInt(this.base + (checkIndex(j, this.size) * 4), i);
        return this;
    }

    public IntIndexer put(long j, int i) {
        return putRaw(index(j), i);
    }

    public IntIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public IntIndexer put(long j, long j2, int i) {
        putRaw(index(j, j2), i);
        return this;
    }

    public IntIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public IntIndexer put(long j, long j2, long j3, int i) {
        putRaw(index(j, j2, j3), i);
        return this;
    }

    public IntIndexer put(long[] jArr, int i) {
        putRaw(index(jArr), i);
        return this;
    }

    public IntIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
