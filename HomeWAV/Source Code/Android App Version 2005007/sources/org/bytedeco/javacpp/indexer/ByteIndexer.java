package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import kotlin.UByte;
import kotlin.UShort;
import org.bytedeco.javacpp.BytePointer;

public abstract class ByteIndexer extends Indexer {
    public static final int VALUE_BYTES = 1;

    public abstract byte get(long j);

    public abstract byte get(long j, long j2);

    public abstract byte get(long j, long j2, long j3);

    public abstract byte get(long... jArr);

    public abstract ByteIndexer get(long j, long j2, byte[] bArr, int i, int i2);

    public abstract ByteIndexer get(long j, byte[] bArr, int i, int i2);

    public abstract ByteIndexer get(long[] jArr, byte[] bArr, int i, int i2);

    public abstract byte getByte(long j);

    public abstract char getChar(long j);

    public abstract double getDouble(long j);

    public abstract float getFloat(long j);

    public abstract int getInt(long j);

    public abstract long getLong(long j);

    public abstract short getShort(long j);

    public abstract ByteIndexer put(long j, byte b);

    public abstract ByteIndexer put(long j, long j2, byte b);

    public abstract ByteIndexer put(long j, long j2, long j3, byte b);

    public abstract ByteIndexer put(long j, long j2, byte[] bArr, int i, int i2);

    public abstract ByteIndexer put(long j, byte[] bArr, int i, int i2);

    public abstract ByteIndexer put(long[] jArr, byte b);

    public abstract ByteIndexer put(long[] jArr, byte[] bArr, int i, int i2);

    public abstract ByteIndexer putByte(long j, byte b);

    public abstract ByteIndexer putChar(long j, char c);

    public abstract ByteIndexer putDouble(long j, double d);

    public abstract ByteIndexer putFloat(long j, float f);

    public abstract ByteIndexer putInt(long j, int i);

    public abstract ByteIndexer putLong(long j, long j2);

    public abstract ByteIndexer putShort(long j, short s);

    protected ByteIndexer(Index index) {
        super(index);
    }

    protected ByteIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static ByteIndexer create(byte[] bArr) {
        return new ByteArrayIndexer(bArr);
    }

    public static ByteIndexer create(ByteBuffer byteBuffer) {
        return new ByteBufferIndexer(byteBuffer);
    }

    public static ByteIndexer create(BytePointer bytePointer) {
        return new ByteRawIndexer(bytePointer);
    }

    public static ByteIndexer create(byte[] bArr, Index index) {
        return new ByteArrayIndexer(bArr, index);
    }

    public static ByteIndexer create(ByteBuffer byteBuffer, Index index) {
        return new ByteBufferIndexer(byteBuffer, index);
    }

    public static ByteIndexer create(BytePointer bytePointer, Index index) {
        return new ByteRawIndexer(bytePointer, index);
    }

    public static ByteIndexer create(byte[] bArr, long... jArr) {
        return new ByteArrayIndexer(bArr, jArr);
    }

    public static ByteIndexer create(ByteBuffer byteBuffer, long... jArr) {
        return new ByteBufferIndexer(byteBuffer, jArr);
    }

    public static ByteIndexer create(BytePointer bytePointer, long... jArr) {
        return new ByteRawIndexer(bytePointer, jArr);
    }

    public static ByteIndexer create(byte[] bArr, long[] jArr, long[] jArr2) {
        return new ByteArrayIndexer(bArr, jArr, jArr2);
    }

    public static ByteIndexer create(ByteBuffer byteBuffer, long[] jArr, long[] jArr2) {
        return new ByteBufferIndexer(byteBuffer, jArr, jArr2);
    }

    public static ByteIndexer create(BytePointer bytePointer, long[] jArr, long[] jArr2) {
        return new ByteRawIndexer(bytePointer, jArr, jArr2);
    }

    public static ByteIndexer create(BytePointer bytePointer, long[] jArr, long[] jArr2, boolean z) {
        return create(bytePointer, Index.create(jArr, jArr2), z);
    }

    public static ByteIndexer create(BytePointer bytePointer, Index index, boolean z) {
        if (!z) {
            final long position = bytePointer.position();
            byte[] bArr = new byte[((int) Math.min(bytePointer.limit() - position, 2147483647L))];
            bytePointer.get(bArr);
            final BytePointer bytePointer2 = bytePointer;
            return new ByteArrayIndexer(bArr, index) {
                public void release() {
                    bytePointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new ByteRawIndexer(bytePointer, index);
        } else {
            return new ByteBufferIndexer(bytePointer.asBuffer(), index);
        }
    }

    public ByteIndexer get(long j, byte[] bArr) {
        return get(j, bArr, 0, bArr.length);
    }

    public ByteIndexer get(long j, long j2, byte[] bArr) {
        return get(j, j2, bArr, 0, bArr.length);
    }

    public ByteIndexer get(long[] jArr, byte[] bArr) {
        return get(jArr, bArr, 0, bArr.length);
    }

    public ByteIndexer put(long j, byte... bArr) {
        return put(j, bArr, 0, bArr.length);
    }

    public ByteIndexer put(long j, long j2, byte... bArr) {
        return put(j, j2, bArr, 0, bArr.length);
    }

    public ByteIndexer put(long[] jArr, byte... bArr) {
        return put(jArr, bArr, 0, bArr.length);
    }

    public int getUByte(long j) {
        return getByte(j) & UByte.MAX_VALUE;
    }

    public ByteIndexer putUByte(long j, int i) {
        return putByte(j, (byte) i);
    }

    public int getUShort(long j) {
        return getShort(j) & UShort.MAX_VALUE;
    }

    public ByteIndexer putUShort(long j, int i) {
        return putShort(j, (short) i);
    }

    public long getUInt(long j) {
        return ((long) getInt(j)) & 4294967295L;
    }

    public ByteIndexer putUInt(long j, long j2) {
        return putInt(j, (int) j2);
    }

    public BigInteger getULong(long j) {
        return ULongIndexer.toBigInteger(getLong(j));
    }

    public ByteIndexer putULong(long j, BigInteger bigInteger) {
        return putLong(j, ULongIndexer.fromBigInteger(bigInteger));
    }

    public float getHalf(long j) {
        return HalfIndexer.toFloat(getShort(j));
    }

    public ByteIndexer putHalf(long j, float f) {
        return putShort(j, (short) HalfIndexer.fromFloat(f));
    }

    public float getBfloat16(long j) {
        return Bfloat16Indexer.toFloat(getShort(j));
    }

    public ByteIndexer putBfloat16(long j, float f) {
        return putShort(j, (short) Bfloat16Indexer.fromFloat(f));
    }

    public boolean getBoolean(long j) {
        return get(j) != 0;
    }

    public ByteIndexer putBoolean(long j, boolean z) {
        return put(j, z ? (byte) 1 : 0);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public ByteIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (byte) ((int) d));
    }
}
