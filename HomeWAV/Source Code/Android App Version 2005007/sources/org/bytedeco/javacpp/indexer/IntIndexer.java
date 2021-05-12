package org.bytedeco.javacpp.indexer;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;

public abstract class IntIndexer extends Indexer {
    public static final int VALUE_BYTES = 4;

    public abstract int get(long j);

    public abstract int get(long j, long j2);

    public abstract int get(long j, long j2, long j3);

    public abstract int get(long... jArr);

    public abstract IntIndexer get(long j, long j2, int[] iArr, int i, int i2);

    public abstract IntIndexer get(long j, int[] iArr, int i, int i2);

    public abstract IntIndexer get(long[] jArr, int[] iArr, int i, int i2);

    public abstract IntIndexer put(long j, int i);

    public abstract IntIndexer put(long j, long j2, int i);

    public abstract IntIndexer put(long j, long j2, long j3, int i);

    public abstract IntIndexer put(long j, long j2, int[] iArr, int i, int i2);

    public abstract IntIndexer put(long j, int[] iArr, int i, int i2);

    public abstract IntIndexer put(long[] jArr, int i);

    public abstract IntIndexer put(long[] jArr, int[] iArr, int i, int i2);

    protected IntIndexer(Index index) {
        super(index);
    }

    protected IntIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static IntIndexer create(int[] iArr) {
        return new IntArrayIndexer(iArr);
    }

    public static IntIndexer create(IntBuffer intBuffer) {
        return new IntBufferIndexer(intBuffer);
    }

    public static IntIndexer create(IntPointer intPointer) {
        return new IntRawIndexer(intPointer);
    }

    public static IntIndexer create(int[] iArr, Index index) {
        return new IntArrayIndexer(iArr, index);
    }

    public static IntIndexer create(IntBuffer intBuffer, Index index) {
        return new IntBufferIndexer(intBuffer, index);
    }

    public static IntIndexer create(IntPointer intPointer, Index index) {
        return new IntRawIndexer(intPointer, index);
    }

    public static IntIndexer create(int[] iArr, long... jArr) {
        return new IntArrayIndexer(iArr, jArr);
    }

    public static IntIndexer create(IntBuffer intBuffer, long... jArr) {
        return new IntBufferIndexer(intBuffer, jArr);
    }

    public static IntIndexer create(IntPointer intPointer, long... jArr) {
        return new IntRawIndexer(intPointer, jArr);
    }

    public static IntIndexer create(int[] iArr, long[] jArr, long[] jArr2) {
        return new IntArrayIndexer(iArr, jArr, jArr2);
    }

    public static IntIndexer create(IntBuffer intBuffer, long[] jArr, long[] jArr2) {
        return new IntBufferIndexer(intBuffer, jArr, jArr2);
    }

    public static IntIndexer create(IntPointer intPointer, long[] jArr, long[] jArr2) {
        return new IntRawIndexer(intPointer, jArr, jArr2);
    }

    public static IntIndexer create(IntPointer intPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(intPointer, Index.create(jArr, jArr2), z);
    }

    public static IntIndexer create(IntPointer intPointer, Index index, boolean z) {
        if (!z) {
            final long position = intPointer.position();
            int[] iArr = new int[((int) Math.min(intPointer.limit() - position, 2147483647L))];
            intPointer.get(iArr);
            final IntPointer intPointer2 = intPointer;
            return new IntArrayIndexer(iArr, index) {
                public void release() {
                    intPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new IntRawIndexer(intPointer, index);
        } else {
            return new IntBufferIndexer(intPointer.asBuffer(), index);
        }
    }

    public IntIndexer get(long j, int[] iArr) {
        return get(j, iArr, 0, iArr.length);
    }

    public IntIndexer get(long j, long j2, int[] iArr) {
        return get(j, j2, iArr, 0, iArr.length);
    }

    public IntIndexer get(long[] jArr, int[] iArr) {
        return get(jArr, iArr, 0, iArr.length);
    }

    public IntIndexer put(long j, int... iArr) {
        return put(j, iArr, 0, iArr.length);
    }

    public IntIndexer put(long j, long j2, int... iArr) {
        return put(j, j2, iArr, 0, iArr.length);
    }

    public IntIndexer put(long[] jArr, int... iArr) {
        return put(jArr, iArr, 0, iArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public IntIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (int) d);
    }
}
