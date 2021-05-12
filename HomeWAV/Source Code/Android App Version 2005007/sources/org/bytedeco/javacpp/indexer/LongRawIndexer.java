package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;

public class LongRawIndexer extends LongIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected LongPointer pointer;
    final long size;

    public LongRawIndexer(LongPointer longPointer) {
        this(longPointer, Index.create(longPointer.limit() - longPointer.position()));
    }

    public LongRawIndexer(LongPointer longPointer, long... jArr) {
        this(longPointer, Index.create(jArr));
    }

    public LongRawIndexer(LongPointer longPointer, long[] jArr, long[] jArr2) {
        this(longPointer, Index.create(jArr, jArr2));
    }

    public LongRawIndexer(LongPointer longPointer, Index index) {
        super(index);
        this.pointer = longPointer;
        this.base = longPointer.address() + (longPointer.position() * 8);
        this.size = longPointer.limit() - longPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public LongIndexer reindex(Index index) {
        return new LongRawIndexer(this.pointer, index);
    }

    public long getRaw(long j) {
        return RAW.getLong(this.base + (checkIndex(j, this.size) * 8));
    }

    public long get(long j) {
        return getRaw(index(j));
    }

    public LongIndexer get(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public long get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public LongIndexer get(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public long get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public long get(long... jArr) {
        return getRaw(index(jArr));
    }

    public LongIndexer get(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr2[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public LongIndexer putRaw(long j, long j2) {
        RAW.putLong(this.base + (checkIndex(j, this.size) * 8), j2);
        return this;
    }

    public LongIndexer put(long j, long j2) {
        return putRaw(index(j), j2);
    }

    public LongIndexer put(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), jArr[i + i3]);
        }
        return this;
    }

    public LongIndexer put(long j, long j2, long j3) {
        putRaw(index(j, j2), j3);
        return this;
    }

    public LongIndexer put(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), jArr[i + i3]);
        }
        return this;
    }

    public LongIndexer put(long j, long j2, long j3, long j4) {
        putRaw(index(j, j2, j3), j4);
        return this;
    }

    public LongIndexer put(long[] jArr, long j) {
        putRaw(index(jArr), j);
        return this;
    }

    public LongIndexer put(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), jArr2[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
