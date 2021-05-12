package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.BooleanPointer;
import org.bytedeco.javacpp.Pointer;

public class BooleanRawIndexer extends BooleanIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected BooleanPointer pointer;
    final long size;

    public BooleanRawIndexer(BooleanPointer booleanPointer) {
        this(booleanPointer, Index.create(booleanPointer.limit() - booleanPointer.position()));
    }

    public BooleanRawIndexer(BooleanPointer booleanPointer, long... jArr) {
        this(booleanPointer, jArr, strides(jArr));
    }

    public BooleanRawIndexer(BooleanPointer booleanPointer, long[] jArr, long[] jArr2) {
        this(booleanPointer, Index.create(jArr, jArr2));
    }

    public BooleanRawIndexer(BooleanPointer booleanPointer, Index index) {
        super(index);
        this.pointer = booleanPointer;
        this.base = booleanPointer.address() + (booleanPointer.position() * 1);
        this.size = booleanPointer.limit() - booleanPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public BooleanIndexer reindex(Index index) {
        return new BooleanRawIndexer(this.pointer, index);
    }

    public boolean getRaw(long j) {
        return RAW.getBoolean(this.base + (checkIndex(j, this.size) * 1));
    }

    public boolean get(long j) {
        return getRaw(index(j));
    }

    public BooleanIndexer get(long j, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public boolean get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public BooleanIndexer get(long j, long j2, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public boolean get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public boolean get(long... jArr) {
        return getRaw(index(jArr));
    }

    public BooleanIndexer get(long[] jArr, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public BooleanIndexer putRaw(long j, boolean z) {
        RAW.putBoolean(this.base + (checkIndex(j, this.size) * 1), z);
        return this;
    }

    public BooleanIndexer put(long j, boolean z) {
        return putRaw(index(j), z);
    }

    public BooleanIndexer put(long j, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), zArr[i + i3]);
        }
        return this;
    }

    public BooleanIndexer put(long j, long j2, boolean z) {
        putRaw(index(j, j2), z);
        return this;
    }

    public BooleanIndexer put(long j, long j2, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), zArr[i + i3]);
        }
        return this;
    }

    public BooleanIndexer put(long j, long j2, long j3, boolean z) {
        putRaw(index(j, j2, j3), z);
        return this;
    }

    public BooleanIndexer put(long[] jArr, boolean z) {
        putRaw(index(jArr), z);
        return this;
    }

    public BooleanIndexer put(long[] jArr, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), zArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
