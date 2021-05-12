package org.bytedeco.javacpp.indexer;

import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;

public abstract class UByteIndexer extends Indexer {
    public static final int VALUE_BYTES = 1;

    public abstract int get(long j);

    public abstract int get(long j, long j2);

    public abstract int get(long j, long j2, long j3);

    public abstract int get(long... jArr);

    public abstract UByteIndexer get(long j, long j2, int[] iArr, int i, int i2);

    public abstract UByteIndexer get(long j, int[] iArr, int i, int i2);

    public abstract UByteIndexer get(long[] jArr, int[] iArr, int i, int i2);

    public abstract UByteIndexer put(long j, int i);

    public abstract UByteIndexer put(long j, long j2, int i);

    public abstract UByteIndexer put(long j, long j2, long j3, int i);

    public abstract UByteIndexer put(long j, long j2, int[] iArr, int i, int i2);

    public abstract UByteIndexer put(long j, int[] iArr, int i, int i2);

    public abstract UByteIndexer put(long[] jArr, int i);

    public abstract UByteIndexer put(long[] jArr, int[] iArr, int i, int i2);

    protected UByteIndexer(Index index) {
        super(index);
    }

    protected UByteIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static UByteIndexer create(byte[] bArr) {
        return new UByteArrayIndexer(bArr);
    }

    public static UByteIndexer create(ByteBuffer byteBuffer) {
        return new UByteBufferIndexer(byteBuffer);
    }

    public static UByteIndexer create(BytePointer bytePointer) {
        return new UByteRawIndexer(bytePointer);
    }

    public static UByteIndexer create(byte[] bArr, Index index) {
        return new UByteArrayIndexer(bArr, index);
    }

    public static UByteIndexer create(ByteBuffer byteBuffer, Index index) {
        return new UByteBufferIndexer(byteBuffer, index);
    }

    public static UByteIndexer create(BytePointer bytePointer, Index index) {
        return new UByteRawIndexer(bytePointer, index);
    }

    public static UByteIndexer create(byte[] bArr, long... jArr) {
        return new UByteArrayIndexer(bArr, jArr);
    }

    public static UByteIndexer create(ByteBuffer byteBuffer, long... jArr) {
        return new UByteBufferIndexer(byteBuffer, jArr);
    }

    public static UByteIndexer create(BytePointer bytePointer, long... jArr) {
        return new UByteRawIndexer(bytePointer, jArr);
    }

    public static UByteIndexer create(byte[] bArr, long[] jArr, long[] jArr2) {
        return new UByteArrayIndexer(bArr, jArr, jArr2);
    }

    public static UByteIndexer create(ByteBuffer byteBuffer, long[] jArr, long[] jArr2) {
        return new UByteBufferIndexer(byteBuffer, jArr, jArr2);
    }

    public static UByteIndexer create(BytePointer bytePointer, long[] jArr, long[] jArr2) {
        return new UByteRawIndexer(bytePointer, jArr, jArr2);
    }

    public static UByteIndexer create(BytePointer bytePointer, long[] jArr, long[] jArr2, boolean z) {
        return create(bytePointer, Index.create(jArr, jArr2), z);
    }

    public static UByteIndexer create(BytePointer bytePointer, Index index, boolean z) {
        if (!z) {
            final long position = bytePointer.position();
            byte[] bArr = new byte[((int) Math.min(bytePointer.limit() - position, 2147483647L))];
            bytePointer.get(bArr);
            final BytePointer bytePointer2 = bytePointer;
            return new UByteArrayIndexer(bArr, index) {
                public void release() {
                    bytePointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new UByteRawIndexer(bytePointer, index);
        } else {
            return new UByteBufferIndexer(bytePointer.asBuffer(), index);
        }
    }

    public UByteIndexer get(long j, int[] iArr) {
        return get(j, iArr, 0, iArr.length);
    }

    public UByteIndexer get(long j, long j2, int[] iArr) {
        return get(j, j2, iArr, 0, iArr.length);
    }

    public UByteIndexer get(long[] jArr, int[] iArr) {
        return get(jArr, iArr, 0, iArr.length);
    }

    public UByteIndexer put(long j, int... iArr) {
        return put(j, iArr, 0, iArr.length);
    }

    public UByteIndexer put(long j, long j2, int... iArr) {
        return put(j, j2, iArr, 0, iArr.length);
    }

    public UByteIndexer put(long[] jArr, int... iArr) {
        return put(jArr, iArr, 0, iArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public UByteIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (int) d);
    }
}
