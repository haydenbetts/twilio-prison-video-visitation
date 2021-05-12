package org.bytedeco.javacpp.indexer;

import kotlin.UShort;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;

public class UShortRawIndexer extends UShortIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected ShortPointer pointer;
    final long size;

    public UShortRawIndexer(ShortPointer shortPointer) {
        this(shortPointer, Index.create(shortPointer.limit() - shortPointer.position()));
    }

    public UShortRawIndexer(ShortPointer shortPointer, long... jArr) {
        this(shortPointer, Index.create(jArr));
    }

    public UShortRawIndexer(ShortPointer shortPointer, long[] jArr, long[] jArr2) {
        this(shortPointer, Index.create(jArr, jArr2));
    }

    public UShortRawIndexer(ShortPointer shortPointer, Index index) {
        super(index);
        this.pointer = shortPointer;
        this.base = shortPointer.address() + (shortPointer.position() * 2);
        this.size = shortPointer.limit() - shortPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public UShortIndexer reindex(Index index) {
        return new UShortRawIndexer(this.pointer, index);
    }

    public int getRaw(long j) {
        return RAW.getShort(this.base + (checkIndex(j, this.size) * 2)) & UShort.MAX_VALUE;
    }

    public int get(long j) {
        return getRaw(index(j));
    }

    public UShortIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(j) + ((long) i3)) & 65535;
        }
        return this;
    }

    public int get(long j, long j2) {
        return getRaw(index(j, j2)) & 65535;
    }

    public UShortIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(j, j2) + ((long) i3)) & 65535;
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3)) & 65535;
    }

    public int get(long... jArr) {
        return getRaw(index(jArr)) & 65535;
    }

    public UShortIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(jArr) + ((long) i3)) & 65535;
        }
        return this;
    }

    public UShortIndexer putRaw(long j, int i) {
        RAW.putShort(this.base + (checkIndex(j, this.size) * 2), (short) i);
        return this;
    }

    public UShortIndexer put(long j, int i) {
        return putRaw(index(j), i);
    }

    public UShortIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public UShortIndexer put(long j, long j2, int i) {
        putRaw(index(j, j2), i);
        return this;
    }

    public UShortIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public UShortIndexer put(long j, long j2, long j3, int i) {
        putRaw(index(j, j2, j3), i);
        return this;
    }

    public UShortIndexer put(long[] jArr, int i) {
        putRaw(index(jArr), i);
        return this;
    }

    public UShortIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
