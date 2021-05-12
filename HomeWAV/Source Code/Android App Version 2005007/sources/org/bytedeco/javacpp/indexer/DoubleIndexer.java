package org.bytedeco.javacpp.indexer;

import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.DoublePointer;

public abstract class DoubleIndexer extends Indexer {
    public static final int VALUE_BYTES = 8;

    public abstract double get(long j);

    public abstract double get(long j, long j2);

    public abstract double get(long j, long j2, long j3);

    public abstract double get(long... jArr);

    public abstract DoubleIndexer get(long j, long j2, double[] dArr, int i, int i2);

    public abstract DoubleIndexer get(long j, double[] dArr, int i, int i2);

    public abstract DoubleIndexer get(long[] jArr, double[] dArr, int i, int i2);

    public abstract DoubleIndexer put(long j, double d);

    public abstract DoubleIndexer put(long j, long j2, double d);

    public abstract DoubleIndexer put(long j, long j2, long j3, double d);

    public abstract DoubleIndexer put(long j, long j2, double[] dArr, int i, int i2);

    public abstract DoubleIndexer put(long j, double[] dArr, int i, int i2);

    public abstract DoubleIndexer put(long[] jArr, double d);

    public abstract DoubleIndexer put(long[] jArr, double[] dArr, int i, int i2);

    protected DoubleIndexer(Index index) {
        super(index);
    }

    protected DoubleIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static DoubleIndexer create(double[] dArr) {
        return new DoubleArrayIndexer(dArr);
    }

    public static DoubleIndexer create(DoubleBuffer doubleBuffer) {
        return new DoubleBufferIndexer(doubleBuffer);
    }

    public static DoubleIndexer create(DoublePointer doublePointer) {
        return new DoubleRawIndexer(doublePointer);
    }

    public static DoubleIndexer create(double[] dArr, Index index) {
        return new DoubleArrayIndexer(dArr, index);
    }

    public static DoubleIndexer create(DoubleBuffer doubleBuffer, Index index) {
        return new DoubleBufferIndexer(doubleBuffer, index);
    }

    public static DoubleIndexer create(DoublePointer doublePointer, Index index) {
        return new DoubleRawIndexer(doublePointer, index);
    }

    public static DoubleIndexer create(double[] dArr, long... jArr) {
        return new DoubleArrayIndexer(dArr, jArr);
    }

    public static DoubleIndexer create(DoubleBuffer doubleBuffer, long... jArr) {
        return new DoubleBufferIndexer(doubleBuffer, jArr);
    }

    public static DoubleIndexer create(DoublePointer doublePointer, long... jArr) {
        return new DoubleRawIndexer(doublePointer, jArr);
    }

    public static DoubleIndexer create(double[] dArr, long[] jArr, long[] jArr2) {
        return new DoubleArrayIndexer(dArr, jArr, jArr2);
    }

    public static DoubleIndexer create(DoubleBuffer doubleBuffer, long[] jArr, long[] jArr2) {
        return new DoubleBufferIndexer(doubleBuffer, jArr, jArr2);
    }

    public static DoubleIndexer create(DoublePointer doublePointer, long[] jArr, long[] jArr2) {
        return new DoubleRawIndexer(doublePointer, jArr, jArr2);
    }

    public static DoubleIndexer create(DoublePointer doublePointer, long[] jArr, long[] jArr2, boolean z) {
        return create(doublePointer, Index.create(jArr, jArr2), z);
    }

    public static DoubleIndexer create(DoublePointer doublePointer, Index index, boolean z) {
        if (!z) {
            final long position = doublePointer.position();
            double[] dArr = new double[((int) Math.min(doublePointer.limit() - position, 2147483647L))];
            doublePointer.get(dArr);
            final DoublePointer doublePointer2 = doublePointer;
            return new DoubleArrayIndexer(dArr, index) {
                public void release() {
                    doublePointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new DoubleRawIndexer(doublePointer, index);
        } else {
            return new DoubleBufferIndexer(doublePointer.asBuffer(), index);
        }
    }

    public DoubleIndexer get(long j, double[] dArr) {
        return get(j, dArr, 0, dArr.length);
    }

    public DoubleIndexer get(long j, long j2, double[] dArr) {
        return get(j, j2, dArr, 0, dArr.length);
    }

    public DoubleIndexer get(long[] jArr, double[] dArr) {
        return get(jArr, dArr, 0, dArr.length);
    }

    public DoubleIndexer put(long j, double... dArr) {
        return put(j, dArr, 0, dArr.length);
    }

    public DoubleIndexer put(long j, long j2, double... dArr) {
        return put(j, j2, dArr, 0, dArr.length);
    }

    public DoubleIndexer put(long[] jArr, double... dArr) {
        return put(jArr, dArr, 0, dArr.length);
    }

    public double getDouble(long... jArr) {
        return get(jArr);
    }

    public DoubleIndexer putDouble(long[] jArr, double d) {
        return put(jArr, d);
    }
}
