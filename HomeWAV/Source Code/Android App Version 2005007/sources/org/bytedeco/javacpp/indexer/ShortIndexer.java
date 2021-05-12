package org.bytedeco.javacpp.indexer;

import java.nio.ShortBuffer;
import org.bytedeco.javacpp.ShortPointer;

public abstract class ShortIndexer extends Indexer {
    public static final int VALUE_BYTES = 2;

    public abstract ShortIndexer get(long j, long j2, short[] sArr, int i, int i2);

    public abstract ShortIndexer get(long j, short[] sArr, int i, int i2);

    public abstract ShortIndexer get(long[] jArr, short[] sArr, int i, int i2);

    public abstract short get(long j);

    public abstract short get(long j, long j2);

    public abstract short get(long j, long j2, long j3);

    public abstract short get(long... jArr);

    public abstract ShortIndexer put(long j, long j2, long j3, short s);

    public abstract ShortIndexer put(long j, long j2, short s);

    public abstract ShortIndexer put(long j, long j2, short[] sArr, int i, int i2);

    public abstract ShortIndexer put(long j, short s);

    public abstract ShortIndexer put(long j, short[] sArr, int i, int i2);

    public abstract ShortIndexer put(long[] jArr, short s);

    public abstract ShortIndexer put(long[] jArr, short[] sArr, int i, int i2);

    protected ShortIndexer(Index index) {
        super(index);
    }

    protected ShortIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static ShortIndexer create(short[] sArr) {
        return new ShortArrayIndexer(sArr);
    }

    public static ShortIndexer create(ShortBuffer shortBuffer) {
        return new ShortBufferIndexer(shortBuffer);
    }

    public static ShortIndexer create(ShortPointer shortPointer) {
        return new ShortRawIndexer(shortPointer);
    }

    public static ShortIndexer create(short[] sArr, Index index) {
        return new ShortArrayIndexer(sArr, index);
    }

    public static ShortIndexer create(ShortBuffer shortBuffer, Index index) {
        return new ShortBufferIndexer(shortBuffer, index);
    }

    public static ShortIndexer create(ShortPointer shortPointer, Index index) {
        return new ShortRawIndexer(shortPointer, index);
    }

    public static ShortIndexer create(short[] sArr, long... jArr) {
        return new ShortArrayIndexer(sArr, jArr);
    }

    public static ShortIndexer create(ShortBuffer shortBuffer, long... jArr) {
        return new ShortBufferIndexer(shortBuffer, jArr);
    }

    public static ShortIndexer create(ShortPointer shortPointer, long... jArr) {
        return new ShortRawIndexer(shortPointer, jArr);
    }

    public static ShortIndexer create(short[] sArr, long[] jArr, long[] jArr2) {
        return new ShortArrayIndexer(sArr, jArr, jArr2);
    }

    public static ShortIndexer create(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        return new ShortBufferIndexer(shortBuffer, jArr, jArr2);
    }

    public static ShortIndexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2) {
        return new ShortRawIndexer(shortPointer, jArr, jArr2);
    }

    public static ShortIndexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(shortPointer, Index.create(jArr, jArr2), z);
    }

    public static ShortIndexer create(ShortPointer shortPointer, Index index, boolean z) {
        if (!z) {
            final long position = shortPointer.position();
            short[] sArr = new short[((int) Math.min(shortPointer.limit() - position, 2147483647L))];
            shortPointer.get(sArr);
            final ShortPointer shortPointer2 = shortPointer;
            return new ShortArrayIndexer(sArr, index) {
                public void release() {
                    shortPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new ShortRawIndexer(shortPointer, index);
        } else {
            return new ShortBufferIndexer(shortPointer.asBuffer(), index);
        }
    }

    public ShortIndexer get(long j, short[] sArr) {
        return get(j, sArr, 0, sArr.length);
    }

    public ShortIndexer get(long j, long j2, short[] sArr) {
        return get(j, j2, sArr, 0, sArr.length);
    }

    public ShortIndexer get(long[] jArr, short[] sArr) {
        return get(jArr, sArr, 0, sArr.length);
    }

    public ShortIndexer put(long j, short... sArr) {
        return put(j, sArr, 0, sArr.length);
    }

    public ShortIndexer put(long j, long j2, short... sArr) {
        return put(j, j2, sArr, 0, sArr.length);
    }

    public ShortIndexer put(long[] jArr, short... sArr) {
        return put(jArr, sArr, 0, sArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public ShortIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (short) ((int) d));
    }
}
