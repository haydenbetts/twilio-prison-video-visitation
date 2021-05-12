package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.DoubleBuffer;

public class DoubleBufferIndexer extends DoubleIndexer {
    protected DoubleBuffer buffer;

    public DoubleBufferIndexer(DoubleBuffer doubleBuffer) {
        this(doubleBuffer, Index.create((long) doubleBuffer.limit()));
    }

    public DoubleBufferIndexer(DoubleBuffer doubleBuffer, long... jArr) {
        this(doubleBuffer, Index.create(jArr));
    }

    public DoubleBufferIndexer(DoubleBuffer doubleBuffer, long[] jArr, long[] jArr2) {
        this(doubleBuffer, Index.create(jArr, jArr2));
    }

    public DoubleBufferIndexer(DoubleBuffer doubleBuffer, Index index) {
        super(index);
        this.buffer = doubleBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public DoubleIndexer reindex(Index index) {
        return new DoubleBufferIndexer(this.buffer, index);
    }

    public double get(long j) {
        return this.buffer.get((int) index(j));
    }

    public DoubleIndexer get(long j, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = this.buffer.get(((int) index(j)) + i3);
        }
        return this;
    }

    public double get(long j, long j2) {
        return this.buffer.get((int) index(j, j2));
    }

    public DoubleIndexer get(long j, long j2, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3);
        }
        return this;
    }

    public double get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3));
    }

    public double get(long... jArr) {
        return this.buffer.get((int) index(jArr));
    }

    public DoubleIndexer get(long[] jArr, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            dArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3);
        }
        return this;
    }

    public DoubleIndexer put(long j, double d) {
        this.buffer.put((int) index(j), d);
        return this;
    }

    public DoubleIndexer put(long j, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, dArr[i + i3]);
        }
        return this;
    }

    public DoubleIndexer put(long j, long j2, double d) {
        this.buffer.put((int) index(j, j2), d);
        return this;
    }

    public DoubleIndexer put(long j, long j2, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, dArr[i + i3]);
        }
        return this;
    }

    public DoubleIndexer put(long j, long j2, long j3, double d) {
        this.buffer.put((int) index(j, j2, j3), d);
        return this;
    }

    public DoubleIndexer put(long[] jArr, double d) {
        this.buffer.put((int) index(jArr), d);
        return this;
    }

    public DoubleIndexer put(long[] jArr, double[] dArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, dArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
