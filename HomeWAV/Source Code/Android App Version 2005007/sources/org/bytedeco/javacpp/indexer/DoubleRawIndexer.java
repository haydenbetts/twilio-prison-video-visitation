package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Pointer;

public class DoubleRawIndexer extends DoubleIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected DoublePointer pointer;
    final long size;

    public DoubleRawIndexer(DoublePointer doublePointer) {
        this(doublePointer, Index.create(doublePointer.limit() - doublePointer.position()));
    }

    public DoubleRawIndexer(DoublePointer doublePointer, long... jArr) {
        this(doublePointer, Index.create(jArr));
    }

    public DoubleRawIndexer(DoublePointer doublePointer, long[] jArr, long[] jArr2) {
        this(doublePointer, Index.create(jArr, jArr2));
    }

    public DoubleRawIndexer(DoublePointer doublePointer, Index index) {
        super(index);
        this.pointer = doublePointer;
        this.base = doublePointer.address() + (doublePointer.position() * 8);
        this.size = doublePointer.limit() - doublePointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public DoubleIndexer reindex(Index index) {
        return new DoubleRawIndexer(this.pointer, index);
    }

    public double getRaw(long j) {
        return RAW.getDouble(this.base + (checkIndex(j, this.size) * 8));
    }

    public double get(long j) {
        return getRaw(index(j));
    }

    public DoubleIndexer get(long j, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public double get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public DoubleIndexer get(long j, long j2, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public double get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public double get(long... jArr) {
        return getRaw(index(jArr));
    }

    public DoubleIndexer get(long[] jArr, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public DoubleIndexer putRaw(long j, double d) {
        RAW.putDouble(this.base + (checkIndex(j, this.size) * 8), d);
        return this;
    }

    public DoubleIndexer put(long j, double d) {
        return putRaw(index(j), d);
    }

    public DoubleIndexer put(long j, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), dArr[i + i3]);
        }
        return this;
    }

    public DoubleIndexer put(long j, long j2, double d) {
        putRaw(index(j, j2), d);
        return this;
    }

    public DoubleIndexer put(long j, long j2, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), dArr[i + i3]);
        }
        return this;
    }

    public DoubleIndexer put(long j, long j2, long j3, double d) {
        putRaw(index(j, j2, j3), d);
        return this;
    }

    public DoubleIndexer put(long[] jArr, double d) {
        putRaw(index(jArr), d);
        return this;
    }

    public DoubleIndexer put(long[] jArr, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), dArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
