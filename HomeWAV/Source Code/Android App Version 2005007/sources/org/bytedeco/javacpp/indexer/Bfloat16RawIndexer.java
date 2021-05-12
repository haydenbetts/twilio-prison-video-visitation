package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;

public class Bfloat16RawIndexer extends Bfloat16Indexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected ShortPointer pointer;
    final long size;

    public Bfloat16RawIndexer(ShortPointer shortPointer) {
        this(shortPointer, Index.create(shortPointer.limit() - shortPointer.position()));
    }

    public Bfloat16RawIndexer(ShortPointer shortPointer, long... jArr) {
        this(shortPointer, Index.create(jArr));
    }

    public Bfloat16RawIndexer(ShortPointer shortPointer, long[] jArr, long[] jArr2) {
        this(shortPointer, Index.create(jArr, jArr2));
    }

    public Bfloat16RawIndexer(ShortPointer shortPointer, Index index) {
        super(index);
        this.pointer = shortPointer;
        this.base = shortPointer.address() + (shortPointer.position() * 2);
        this.size = shortPointer.limit() - shortPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public Bfloat16Indexer reindex(Index index) {
        return new Bfloat16RawIndexer(this.pointer, index);
    }

    public float getRaw(long j) {
        return toFloat(RAW.getShort(this.base + (checkIndex(j, this.size) * 2)));
    }

    public float get(long j) {
        return getRaw(index(j));
    }

    public Bfloat16Indexer get(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public float get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public Bfloat16Indexer get(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public float get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public float get(long... jArr) {
        return getRaw(index(jArr));
    }

    public Bfloat16Indexer get(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public Bfloat16Indexer putRaw(long j, float f) {
        RAW.putShort(this.base + (checkIndex(j, this.size) * 2), (short) fromFloat(f));
        return this;
    }

    public Bfloat16Indexer put(long j, float f) {
        return putRaw(index(j), f);
    }

    public Bfloat16Indexer put(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), fArr[i + i3]);
        }
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, float f) {
        putRaw(index(j, j2), f);
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), fArr[i + i3]);
        }
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, long j3, float f) {
        putRaw(index(j, j2, j3), f);
        return this;
    }

    public Bfloat16Indexer put(long[] jArr, float f) {
        putRaw(index(jArr), f);
        return this;
    }

    public Bfloat16Indexer put(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), fArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
