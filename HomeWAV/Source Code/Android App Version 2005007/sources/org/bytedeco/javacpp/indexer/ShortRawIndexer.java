package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;

public class ShortRawIndexer extends ShortIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected ShortPointer pointer;
    final long size;

    public ShortRawIndexer(ShortPointer shortPointer) {
        this(shortPointer, Index.create(shortPointer.limit() - shortPointer.position()));
    }

    public ShortRawIndexer(ShortPointer shortPointer, long... jArr) {
        this(shortPointer, Index.create(jArr));
    }

    public ShortRawIndexer(ShortPointer shortPointer, long[] jArr, long[] jArr2) {
        this(shortPointer, Index.create(jArr, jArr2));
    }

    public ShortRawIndexer(ShortPointer shortPointer, Index index) {
        super(index);
        this.pointer = shortPointer;
        this.base = shortPointer.address() + (shortPointer.position() * 2);
        this.size = shortPointer.limit() - shortPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public ShortIndexer reindex(Index index) {
        return new ShortRawIndexer(this.pointer, index);
    }

    public short getRaw(long j) {
        return RAW.getShort(this.base + (checkIndex(j, this.size) * 2));
    }

    public short get(long j) {
        return getRaw(index(j));
    }

    public ShortIndexer get(long j, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public short get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public ShortIndexer get(long j, long j2, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public short get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public short get(long... jArr) {
        return getRaw(index(jArr));
    }

    public ShortIndexer get(long[] jArr, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public ShortIndexer putRaw(long j, short s) {
        RAW.putShort(this.base + (checkIndex(j, this.size) * 2), s);
        return this;
    }

    public ShortIndexer put(long j, short s) {
        return putRaw(index(j), s);
    }

    public ShortIndexer put(long j, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), sArr[i + i3]);
        }
        return this;
    }

    public ShortIndexer put(long j, long j2, short s) {
        putRaw(index(j, j2), s);
        return this;
    }

    public ShortIndexer put(long j, long j2, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), sArr[i + i3]);
        }
        return this;
    }

    public ShortIndexer put(long j, long j2, long j3, short s) {
        putRaw(index(j, j2, j3), s);
        return this;
    }

    public ShortIndexer put(long[] jArr, short s) {
        putRaw(index(jArr), s);
        return this;
    }

    public ShortIndexer put(long[] jArr, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), sArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
