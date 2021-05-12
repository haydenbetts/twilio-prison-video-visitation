package org.bytedeco.javacpp.indexer;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;

public abstract class UIntIndexer extends Indexer {
    public static final int VALUE_BYTES = 4;

    public abstract long get(long j);

    public abstract long get(long j, long j2);

    public abstract long get(long j, long j2, long j3);

    public abstract long get(long... jArr);

    public abstract UIntIndexer get(long j, long j2, long[] jArr, int i, int i2);

    public abstract UIntIndexer get(long j, long[] jArr, int i, int i2);

    public abstract UIntIndexer get(long[] jArr, long[] jArr2, int i, int i2);

    public abstract UIntIndexer put(long j, long j2);

    public abstract UIntIndexer put(long j, long j2, long j3);

    public abstract UIntIndexer put(long j, long j2, long j3, long j4);

    public abstract UIntIndexer put(long j, long j2, long[] jArr, int i, int i2);

    public abstract UIntIndexer put(long j, long[] jArr, int i, int i2);

    public abstract UIntIndexer put(long[] jArr, long j);

    public abstract UIntIndexer put(long[] jArr, long[] jArr2, int i, int i2);

    protected UIntIndexer(Index index) {
        super(index);
    }

    protected UIntIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static UIntIndexer create(int[] iArr) {
        return new UIntArrayIndexer(iArr);
    }

    public static UIntIndexer create(IntBuffer intBuffer) {
        return new UIntBufferIndexer(intBuffer);
    }

    public static UIntIndexer create(IntPointer intPointer) {
        return new UIntRawIndexer(intPointer);
    }

    public static UIntIndexer create(int[] iArr, Index index) {
        return new UIntArrayIndexer(iArr, index);
    }

    public static UIntIndexer create(IntBuffer intBuffer, Index index) {
        return new UIntBufferIndexer(intBuffer, index);
    }

    public static UIntIndexer create(IntPointer intPointer, Index index) {
        return new UIntRawIndexer(intPointer, index);
    }

    public static UIntIndexer create(int[] iArr, long... jArr) {
        return new UIntArrayIndexer(iArr, jArr);
    }

    public static UIntIndexer create(IntBuffer intBuffer, long... jArr) {
        return new UIntBufferIndexer(intBuffer, jArr);
    }

    public static UIntIndexer create(IntPointer intPointer, long... jArr) {
        return new UIntRawIndexer(intPointer, jArr);
    }

    public static UIntIndexer create(int[] iArr, long[] jArr, long[] jArr2) {
        return new UIntArrayIndexer(iArr, jArr, jArr2);
    }

    public static UIntIndexer create(IntBuffer intBuffer, long[] jArr, long[] jArr2) {
        return new UIntBufferIndexer(intBuffer, jArr, jArr2);
    }

    public static UIntIndexer create(IntPointer intPointer, long[] jArr, long[] jArr2) {
        return new UIntRawIndexer(intPointer, jArr, jArr2);
    }

    public static UIntIndexer create(IntPointer intPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(intPointer, Index.create(jArr, jArr2), z);
    }

    public static UIntIndexer create(IntPointer intPointer, Index index, boolean z) {
        if (!z) {
            final long position = intPointer.position();
            int[] iArr = new int[((int) Math.min(intPointer.limit() - position, 2147483647L))];
            intPointer.get(iArr);
            final IntPointer intPointer2 = intPointer;
            return new UIntArrayIndexer(iArr, index) {
                public void release() {
                    intPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new UIntRawIndexer(intPointer, index);
        } else {
            return new UIntBufferIndexer(intPointer.asBuffer(), index);
        }
    }

    public UIntIndexer get(long j, long[] jArr) {
        return get(j, jArr, 0, jArr.length);
    }

    public UIntIndexer get(long j, long j2, long[] jArr) {
        return get(j, j2, jArr, 0, jArr.length);
    }

    public UIntIndexer get(long[] jArr, long[] jArr2) {
        return get(jArr, jArr2, 0, jArr2.length);
    }

    public UIntIndexer put(long j, long... jArr) {
        return put(j, jArr, 0, jArr.length);
    }

    public UIntIndexer put(long j, long j2, long... jArr) {
        return put(j, j2, jArr, 0, jArr.length);
    }

    public UIntIndexer put(long[] jArr, long... jArr2) {
        return put(jArr, jArr2, 0, jArr2.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public UIntIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (long) ((int) d));
    }
}
