package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;

public class UIntRawIndexer extends UIntIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected IntPointer pointer;
    final long size;

    public UIntRawIndexer(IntPointer intPointer) {
        this(intPointer, Index.create(intPointer.limit() - intPointer.position()));
    }

    public UIntRawIndexer(IntPointer intPointer, long... jArr) {
        this(intPointer, Index.create(jArr));
    }

    public UIntRawIndexer(IntPointer intPointer, long[] jArr, long[] jArr2) {
        this(intPointer, Index.create(jArr, jArr2));
    }

    public UIntRawIndexer(IntPointer intPointer, Index index) {
        super(index);
        this.pointer = intPointer;
        this.base = intPointer.address() + (intPointer.position() * 4);
        this.size = intPointer.limit() - intPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public UIntIndexer reindex(Index index) {
        return new UIntRawIndexer(this.pointer, index);
    }

    public long getRaw(long j) {
        return ((long) RAW.getInt(this.base + (checkIndex(j, this.size) * 4))) & 4294967295L;
    }

    public long get(long j) {
        return getRaw(index(j));
    }

    public UIntIndexer get(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = getRaw(index(j) + ((long) i3)) & 4294967295L;
        }
        return this;
    }

    public long get(long j, long j2) {
        return getRaw(index(j, j2)) & 4294967295L;
    }

    public UIntIndexer get(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = getRaw(index(j, j2) + ((long) i3)) & 4294967295L;
        }
        return this;
    }

    public long get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3)) & 4294967295L;
    }

    public long get(long... jArr) {
        return getRaw(index(jArr)) & 4294967295L;
    }

    public UIntIndexer get(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr2[i + i3] = getRaw(index(jArr) + ((long) i3)) & 4294967295L;
        }
        return this;
    }

    public UIntIndexer putRaw(long j, long j2) {
        RAW.putInt(this.base + (checkIndex(j, this.size) * 4), (int) j2);
        return this;
    }

    public UIntIndexer put(long j, long j2) {
        return putRaw(index(j), j2);
    }

    public UIntIndexer put(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), jArr[i + i3]);
        }
        return this;
    }

    public UIntIndexer put(long j, long j2, long j3) {
        putRaw(index(j, j2), j3);
        return this;
    }

    public UIntIndexer put(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), jArr[i + i3]);
        }
        return this;
    }

    public UIntIndexer put(long j, long j2, long j3, long j4) {
        putRaw(index(j, j2, j3), j4);
        return this;
    }

    public UIntIndexer put(long[] jArr, long j) {
        putRaw(index(jArr), j);
        return this;
    }

    public UIntIndexer put(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), jArr2[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
