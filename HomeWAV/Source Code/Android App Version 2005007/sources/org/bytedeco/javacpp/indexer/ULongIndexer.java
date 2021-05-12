package org.bytedeco.javacpp.indexer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.LongBuffer;
import org.bytedeco.javacpp.LongPointer;

public abstract class ULongIndexer extends Indexer {
    public static final int VALUE_BYTES = 8;

    public abstract BigInteger get(long j);

    public abstract BigInteger get(long j, long j2);

    public abstract BigInteger get(long j, long j2, long j3);

    public abstract BigInteger get(long... jArr);

    public abstract ULongIndexer get(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2);

    public abstract ULongIndexer get(long j, BigInteger[] bigIntegerArr, int i, int i2);

    public abstract ULongIndexer get(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2);

    public abstract ULongIndexer put(long j, long j2, long j3, BigInteger bigInteger);

    public abstract ULongIndexer put(long j, long j2, BigInteger bigInteger);

    public abstract ULongIndexer put(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2);

    public abstract ULongIndexer put(long j, BigInteger bigInteger);

    public abstract ULongIndexer put(long j, BigInteger[] bigIntegerArr, int i, int i2);

    public abstract ULongIndexer put(long[] jArr, BigInteger bigInteger);

    public abstract ULongIndexer put(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2);

    protected ULongIndexer(Index index) {
        super(index);
    }

    protected ULongIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static ULongIndexer create(long[] jArr) {
        return new ULongArrayIndexer(jArr);
    }

    public static ULongIndexer create(LongBuffer longBuffer) {
        return new ULongBufferIndexer(longBuffer);
    }

    public static ULongIndexer create(LongPointer longPointer) {
        return new ULongRawIndexer(longPointer);
    }

    public static ULongIndexer create(long[] jArr, Index index) {
        return new ULongArrayIndexer(jArr, index);
    }

    public static ULongIndexer create(LongBuffer longBuffer, Index index) {
        return new ULongBufferIndexer(longBuffer, index);
    }

    public static ULongIndexer create(LongPointer longPointer, Index index) {
        return new ULongRawIndexer(longPointer, index);
    }

    public static ULongIndexer create(long[] jArr, long... jArr2) {
        return new ULongArrayIndexer(jArr, jArr2);
    }

    public static ULongIndexer create(LongBuffer longBuffer, long... jArr) {
        return new ULongBufferIndexer(longBuffer, jArr);
    }

    public static ULongIndexer create(LongPointer longPointer, long... jArr) {
        return new ULongRawIndexer(longPointer, jArr);
    }

    public static ULongIndexer create(long[] jArr, long[] jArr2, long[] jArr3) {
        return new ULongArrayIndexer(jArr, jArr2, jArr3);
    }

    public static ULongIndexer create(LongBuffer longBuffer, long[] jArr, long[] jArr2) {
        return new ULongBufferIndexer(longBuffer, jArr, jArr2);
    }

    public static ULongIndexer create(LongPointer longPointer, long[] jArr, long[] jArr2) {
        return new ULongRawIndexer(longPointer, jArr, jArr2);
    }

    public static ULongIndexer create(LongPointer longPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(longPointer, Index.create(jArr, jArr2), z);
    }

    public static ULongIndexer create(LongPointer longPointer, Index index, boolean z) {
        if (!z) {
            final long position = longPointer.position();
            long[] jArr = new long[((int) Math.min(longPointer.limit() - position, 2147483647L))];
            longPointer.get(jArr);
            final LongPointer longPointer2 = longPointer;
            return new ULongArrayIndexer(jArr, index) {
                public void release() {
                    longPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new ULongRawIndexer(longPointer, index);
        } else {
            return new ULongBufferIndexer(longPointer.asBuffer(), index);
        }
    }

    public static BigInteger toBigInteger(long j) {
        BigInteger valueOf = BigInteger.valueOf(Long.MAX_VALUE & j);
        return j < 0 ? valueOf.setBit(63) : valueOf;
    }

    public static long fromBigInteger(BigInteger bigInteger) {
        return bigInteger.longValue();
    }

    public ULongIndexer get(long j, BigInteger[] bigIntegerArr) {
        return get(j, bigIntegerArr, 0, bigIntegerArr.length);
    }

    public ULongIndexer get(long j, long j2, BigInteger[] bigIntegerArr) {
        return get(j, j2, bigIntegerArr, 0, bigIntegerArr.length);
    }

    public ULongIndexer get(long[] jArr, BigInteger[] bigIntegerArr) {
        return get(jArr, bigIntegerArr, 0, bigIntegerArr.length);
    }

    public ULongIndexer put(long j, BigInteger... bigIntegerArr) {
        return put(j, bigIntegerArr, 0, bigIntegerArr.length);
    }

    public ULongIndexer put(long j, long j2, BigInteger... bigIntegerArr) {
        return put(j, j2, bigIntegerArr, 0, bigIntegerArr.length);
    }

    public ULongIndexer put(long[] jArr, BigInteger... bigIntegerArr) {
        return put(jArr, bigIntegerArr, 0, bigIntegerArr.length);
    }

    public double getDouble(long... jArr) {
        return get(jArr).doubleValue();
    }

    public ULongIndexer putDouble(long[] jArr, double d) {
        return put(jArr, BigDecimal.valueOf(d).toBigInteger());
    }
}
