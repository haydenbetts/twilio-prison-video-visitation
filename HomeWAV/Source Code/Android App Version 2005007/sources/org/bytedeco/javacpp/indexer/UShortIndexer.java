package org.bytedeco.javacpp.indexer;

import java.nio.ShortBuffer;
import org.bytedeco.javacpp.ShortPointer;

public abstract class UShortIndexer extends Indexer {
    public static final int VALUE_BYTES = 2;

    public abstract int get(long j);

    public abstract int get(long j, long j2);

    public abstract int get(long j, long j2, long j3);

    public abstract int get(long... jArr);

    public abstract UShortIndexer get(long j, long j2, int[] iArr, int i, int i2);

    public abstract UShortIndexer get(long j, int[] iArr, int i, int i2);

    public abstract UShortIndexer get(long[] jArr, int[] iArr, int i, int i2);

    public abstract UShortIndexer put(long j, int i);

    public abstract UShortIndexer put(long j, long j2, int i);

    public abstract UShortIndexer put(long j, long j2, long j3, int i);

    public abstract UShortIndexer put(long j, long j2, int[] iArr, int i, int i2);

    public abstract UShortIndexer put(long j, int[] iArr, int i, int i2);

    public abstract UShortIndexer put(long[] jArr, int i);

    public abstract UShortIndexer put(long[] jArr, int[] iArr, int i, int i2);

    protected UShortIndexer(Index index) {
        super(index);
    }

    protected UShortIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static UShortIndexer create(short[] sArr) {
        return new UShortArrayIndexer(sArr);
    }

    public static UShortIndexer create(ShortBuffer shortBuffer) {
        return new UShortBufferIndexer(shortBuffer);
    }

    public static UShortIndexer create(ShortPointer shortPointer) {
        return new UShortRawIndexer(shortPointer);
    }

    public static UShortIndexer create(short[] sArr, Index index) {
        return new UShortArrayIndexer(sArr, index);
    }

    public static UShortIndexer create(ShortBuffer shortBuffer, Index index) {
        return new UShortBufferIndexer(shortBuffer, index);
    }

    public static UShortIndexer create(ShortPointer shortPointer, Index index) {
        return new UShortRawIndexer(shortPointer, index);
    }

    public static UShortIndexer create(short[] sArr, long... jArr) {
        return new UShortArrayIndexer(sArr, jArr);
    }

    public static UShortIndexer create(ShortBuffer shortBuffer, long... jArr) {
        return new UShortBufferIndexer(shortBuffer, jArr);
    }

    public static UShortIndexer create(ShortPointer shortPointer, long... jArr) {
        return new UShortRawIndexer(shortPointer, jArr);
    }

    public static UShortIndexer create(short[] sArr, long[] jArr, long[] jArr2) {
        return new UShortArrayIndexer(sArr, jArr, jArr2);
    }

    public static UShortIndexer create(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        return new UShortBufferIndexer(shortBuffer, jArr, jArr2);
    }

    public static UShortIndexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2) {
        return new UShortRawIndexer(shortPointer, jArr, jArr2);
    }

    public static UShortIndexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(shortPointer, Index.create(jArr, jArr2), z);
    }

    public static UShortIndexer create(ShortPointer shortPointer, Index index, boolean z) {
        if (!z) {
            final long position = shortPointer.position();
            short[] sArr = new short[((int) Math.min(shortPointer.limit() - position, 2147483647L))];
            shortPointer.get(sArr);
            final ShortPointer shortPointer2 = shortPointer;
            return new UShortArrayIndexer(sArr, index) {
                public void release() {
                    shortPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new UShortRawIndexer(shortPointer, index);
        } else {
            return new UShortBufferIndexer(shortPointer.asBuffer(), index);
        }
    }

    public UShortIndexer get(long j, int[] iArr) {
        return get(j, iArr, 0, iArr.length);
    }

    public UShortIndexer get(long j, long j2, int[] iArr) {
        return get(j, j2, iArr, 0, iArr.length);
    }

    public UShortIndexer get(long[] jArr, int[] iArr) {
        return get(jArr, iArr, 0, iArr.length);
    }

    public UShortIndexer put(long j, int... iArr) {
        return put(j, iArr, 0, iArr.length);
    }

    public UShortIndexer put(long j, long j2, int... iArr) {
        return put(j, j2, iArr, 0, iArr.length);
    }

    public UShortIndexer put(long[] jArr, int... iArr) {
        return put(jArr, iArr, 0, iArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public UShortIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (int) d);
    }
}
