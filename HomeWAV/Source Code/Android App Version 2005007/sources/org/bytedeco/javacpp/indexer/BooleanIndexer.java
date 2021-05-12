package org.bytedeco.javacpp.indexer;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BooleanPointer;

public abstract class BooleanIndexer extends Indexer {
    public static final int VALUE_BYTES = 1;

    public abstract BooleanIndexer get(long j, long j2, boolean[] zArr, int i, int i2);

    public abstract BooleanIndexer get(long j, boolean[] zArr, int i, int i2);

    public abstract BooleanIndexer get(long[] jArr, boolean[] zArr, int i, int i2);

    public abstract boolean get(long j);

    public abstract boolean get(long j, long j2);

    public abstract boolean get(long j, long j2, long j3);

    public abstract boolean get(long... jArr);

    public abstract BooleanIndexer put(long j, long j2, long j3, boolean z);

    public abstract BooleanIndexer put(long j, long j2, boolean z);

    public abstract BooleanIndexer put(long j, long j2, boolean[] zArr, int i, int i2);

    public abstract BooleanIndexer put(long j, boolean z);

    public abstract BooleanIndexer put(long j, boolean[] zArr, int i, int i2);

    public abstract BooleanIndexer put(long[] jArr, boolean z);

    public abstract BooleanIndexer put(long[] jArr, boolean[] zArr, int i, int i2);

    protected BooleanIndexer(Index index) {
        super(index);
    }

    protected BooleanIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static BooleanIndexer create(boolean[] zArr) {
        return new BooleanArrayIndexer(zArr);
    }

    public static BooleanIndexer create(ByteBuffer byteBuffer) {
        return new BooleanBufferIndexer(byteBuffer);
    }

    public static BooleanIndexer create(BooleanPointer booleanPointer) {
        return new BooleanRawIndexer(booleanPointer);
    }

    public static BooleanIndexer create(boolean[] zArr, Index index) {
        return new BooleanArrayIndexer(zArr, index);
    }

    public static BooleanIndexer create(ByteBuffer byteBuffer, Index index) {
        return new BooleanBufferIndexer(byteBuffer, index);
    }

    public static BooleanIndexer create(BooleanPointer booleanPointer, Index index) {
        return new BooleanRawIndexer(booleanPointer, index);
    }

    public static BooleanIndexer create(boolean[] zArr, long... jArr) {
        return new BooleanArrayIndexer(zArr, jArr);
    }

    public static BooleanIndexer create(ByteBuffer byteBuffer, long... jArr) {
        return new BooleanBufferIndexer(byteBuffer, jArr);
    }

    public static BooleanIndexer create(BooleanPointer booleanPointer, long... jArr) {
        return new BooleanRawIndexer(booleanPointer, jArr);
    }

    public static BooleanIndexer create(boolean[] zArr, long[] jArr, long[] jArr2) {
        return new BooleanArrayIndexer(zArr, jArr, jArr2);
    }

    public static BooleanIndexer create(ByteBuffer byteBuffer, long[] jArr, long[] jArr2) {
        return new BooleanBufferIndexer(byteBuffer, jArr, jArr2);
    }

    public static BooleanIndexer create(BooleanPointer booleanPointer, long[] jArr, long[] jArr2) {
        return new BooleanRawIndexer(booleanPointer, jArr, jArr2);
    }

    public static BooleanIndexer create(BooleanPointer booleanPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(booleanPointer, Index.create(jArr, jArr2), z);
    }

    public static BooleanIndexer create(BooleanPointer booleanPointer, Index index, boolean z) {
        if (!z) {
            final long position = booleanPointer.position();
            boolean[] zArr = new boolean[((int) Math.min(booleanPointer.limit() - position, 2147483647L))];
            booleanPointer.get(zArr);
            final BooleanPointer booleanPointer2 = booleanPointer;
            return new BooleanArrayIndexer(zArr, index) {
                public void release() {
                    booleanPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new BooleanRawIndexer(booleanPointer, index);
        } else {
            return new BooleanBufferIndexer(booleanPointer.asByteBuffer(), index);
        }
    }

    public BooleanIndexer get(long j, boolean[] zArr) {
        return get(j, zArr, 0, zArr.length);
    }

    public BooleanIndexer get(long j, long j2, boolean[] zArr) {
        return get(j, j2, zArr, 0, zArr.length);
    }

    public BooleanIndexer get(long[] jArr, boolean[] zArr) {
        return get(jArr, zArr, 0, zArr.length);
    }

    public BooleanIndexer put(long j, boolean... zArr) {
        return put(j, zArr, 0, zArr.length);
    }

    public BooleanIndexer put(long j, long j2, boolean... zArr) {
        return put(j, j2, zArr, 0, zArr.length);
    }

    public BooleanIndexer put(long[] jArr, boolean... zArr) {
        return put(jArr, zArr, 0, zArr.length);
    }

    public double getDouble(long... jArr) {
        return get(jArr) ? 1.0d : 0.0d;
    }

    public BooleanIndexer putDouble(long[] jArr, double d) {
        return put(jArr, d != 0.0d);
    }
}
