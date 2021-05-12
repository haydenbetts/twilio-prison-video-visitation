package org.bytedeco.javacpp.indexer;

import java.nio.LongBuffer;
import org.bytedeco.javacpp.LongPointer;

public abstract class LongIndexer extends Indexer {
    public static final int VALUE_BYTES = 8;

    public abstract long get(long j);

    public abstract long get(long j, long j2);

    public abstract long get(long j, long j2, long j3);

    public abstract long get(long... jArr);

    public abstract LongIndexer get(long j, long j2, long[] jArr, int i, int i2);

    public abstract LongIndexer get(long j, long[] jArr, int i, int i2);

    public abstract LongIndexer get(long[] jArr, long[] jArr2, int i, int i2);

    public abstract LongIndexer put(long j, long j2);

    public abstract LongIndexer put(long j, long j2, long j3);

    public abstract LongIndexer put(long j, long j2, long j3, long j4);

    public abstract LongIndexer put(long j, long j2, long[] jArr, int i, int i2);

    public abstract LongIndexer put(long j, long[] jArr, int i, int i2);

    public abstract LongIndexer put(long[] jArr, long j);

    public abstract LongIndexer put(long[] jArr, long[] jArr2, int i, int i2);

    protected LongIndexer(Index index) {
        super(index);
    }

    protected LongIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static LongIndexer create(long[] jArr) {
        return new LongArrayIndexer(jArr);
    }

    public static LongIndexer create(LongBuffer longBuffer) {
        return new LongBufferIndexer(longBuffer);
    }

    public static LongIndexer create(LongPointer longPointer) {
        return new LongRawIndexer(longPointer);
    }

    public static LongIndexer create(long[] jArr, Index index) {
        return new LongArrayIndexer(jArr, index);
    }

    public static LongIndexer create(LongBuffer longBuffer, Index index) {
        return new LongBufferIndexer(longBuffer, index);
    }

    public static LongIndexer create(LongPointer longPointer, Index index) {
        return new LongRawIndexer(longPointer, index);
    }

    public static LongIndexer create(long[] jArr, long... jArr2) {
        return new LongArrayIndexer(jArr, jArr2);
    }

    public static LongIndexer create(LongBuffer longBuffer, long... jArr) {
        return new LongBufferIndexer(longBuffer, jArr);
    }

    public static LongIndexer create(LongPointer longPointer, long... jArr) {
        return new LongRawIndexer(longPointer, jArr);
    }

    public static LongIndexer create(long[] jArr, long[] jArr2, long[] jArr3) {
        return new LongArrayIndexer(jArr, jArr2, jArr3);
    }

    public static LongIndexer create(LongBuffer longBuffer, long[] jArr, long[] jArr2) {
        return new LongBufferIndexer(longBuffer, jArr, jArr2);
    }

    public static LongIndexer create(LongPointer longPointer, long[] jArr, long[] jArr2) {
        return new LongRawIndexer(longPointer, jArr, jArr2);
    }

    public static LongIndexer create(LongPointer longPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(longPointer, Index.create(jArr, jArr2), z);
    }

    public static LongIndexer create(LongPointer longPointer, Index index, boolean z) {
        if (!z) {
            final long position = longPointer.position();
            long[] jArr = new long[((int) Math.min(longPointer.limit() - position, 2147483647L))];
            longPointer.get(jArr);
            final LongPointer longPointer2 = longPointer;
            return new LongArrayIndexer(jArr, index) {
                public void release() {
                    longPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new LongRawIndexer(longPointer, index);
        } else {
            return new LongBufferIndexer(longPointer.asBuffer(), index);
        }
    }

    public LongIndexer get(long j, long[] jArr) {
        return get(j, jArr, 0, jArr.length);
    }

    public LongIndexer get(long j, long j2, long[] jArr) {
        return get(j, j2, jArr, 0, jArr.length);
    }

    public LongIndexer get(long[] jArr, long[] jArr2) {
        return get(jArr, jArr2, 0, jArr2.length);
    }

    public LongIndexer put(long j, long... jArr) {
        return put(j, jArr, 0, jArr.length);
    }

    public LongIndexer put(long j, long j2, long... jArr) {
        return put(j, j2, jArr, 0, jArr.length);
    }

    public LongIndexer put(long[] jArr, long... jArr2) {
        return put(jArr, jArr2, 0, jArr2.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public LongIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (long) d);
    }
}
