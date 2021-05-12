package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Pointer;

public class FloatRawIndexer extends FloatIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected FloatPointer pointer;
    final long size;

    public FloatRawIndexer(FloatPointer floatPointer) {
        this(floatPointer, Index.create(floatPointer.limit() - floatPointer.position()));
    }

    public FloatRawIndexer(FloatPointer floatPointer, long... jArr) {
        this(floatPointer, Index.create(jArr));
    }

    public FloatRawIndexer(FloatPointer floatPointer, long[] jArr, long[] jArr2) {
        this(floatPointer, Index.create(jArr, jArr2));
    }

    public FloatRawIndexer(FloatPointer floatPointer, Index index) {
        super(index);
        this.pointer = floatPointer;
        this.base = floatPointer.address() + (floatPointer.position() * 4);
        this.size = floatPointer.limit() - floatPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public FloatIndexer reindex(Index index) {
        return new FloatRawIndexer(this.pointer, index);
    }

    public float getRaw(long j) {
        return RAW.getFloat(this.base + (checkIndex(j, this.size) * 4));
    }

    public float get(long j) {
        return getRaw(index(j));
    }

    public FloatIndexer get(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public float get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public FloatIndexer get(long j, long j2, float[] fArr, int i, int i2) {
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

    public FloatIndexer get(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public FloatIndexer putRaw(long j, float f) {
        RAW.putFloat(this.base + (checkIndex(j, this.size) * 4), f);
        return this;
    }

    public FloatIndexer put(long j, float f) {
        return putRaw(index(j), f);
    }

    public FloatIndexer put(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), fArr[i + i3]);
        }
        return this;
    }

    public FloatIndexer put(long j, long j2, float f) {
        putRaw(index(j, j2), f);
        return this;
    }

    public FloatIndexer put(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), fArr[i + i3]);
        }
        return this;
    }

    public FloatIndexer put(long j, long j2, long j3, float f) {
        putRaw(index(j, j2, j3), f);
        return this;
    }

    public FloatIndexer put(long[] jArr, float f) {
        putRaw(index(jArr), f);
        return this;
    }

    public FloatIndexer put(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), fArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
