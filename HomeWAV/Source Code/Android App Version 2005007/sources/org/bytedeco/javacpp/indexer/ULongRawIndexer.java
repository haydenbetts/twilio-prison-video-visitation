package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;

public class ULongRawIndexer extends ULongIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected LongPointer pointer;
    final long size;

    public ULongRawIndexer(LongPointer longPointer) {
        this(longPointer, Index.create(longPointer.limit() - longPointer.position()));
    }

    public ULongRawIndexer(LongPointer longPointer, long... jArr) {
        this(longPointer, Index.create(jArr));
    }

    public ULongRawIndexer(LongPointer longPointer, long[] jArr, long[] jArr2) {
        this(longPointer, Index.create(jArr, jArr2));
    }

    public ULongRawIndexer(LongPointer longPointer, Index index) {
        super(index);
        this.pointer = longPointer;
        this.base = longPointer.address() + (longPointer.position() * 8);
        this.size = longPointer.limit() - longPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public ULongIndexer reindex(Index index) {
        return new ULongRawIndexer(this.pointer, index);
    }

    public BigInteger getRaw(long j) {
        return toBigInteger(RAW.getLong(this.base + (checkIndex(j, this.size) * 8)));
    }

    public BigInteger get(long j) {
        return getRaw(index(j));
    }

    public ULongIndexer get(long j, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public BigInteger get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public ULongIndexer get(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public BigInteger get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public BigInteger get(long... jArr) {
        return getRaw(index(jArr));
    }

    public ULongIndexer get(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public ULongIndexer putRaw(long j, BigInteger bigInteger) {
        RAW.putLong(this.base + (checkIndex(j, this.size) * 8), fromBigInteger(bigInteger));
        return this;
    }

    public ULongIndexer put(long j, BigInteger bigInteger) {
        return putRaw(index(j), bigInteger);
    }

    public ULongIndexer put(long j, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), bigIntegerArr[i + i3]);
        }
        return this;
    }

    public ULongIndexer put(long j, long j2, BigInteger bigInteger) {
        putRaw(index(j, j2), bigInteger);
        return this;
    }

    public ULongIndexer put(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), bigIntegerArr[i + i3]);
        }
        return this;
    }

    public ULongIndexer put(long j, long j2, long j3, BigInteger bigInteger) {
        putRaw(index(j, j2, j3), bigInteger);
        return this;
    }

    public ULongIndexer put(long[] jArr, BigInteger bigInteger) {
        putRaw(index(jArr), bigInteger);
        return this;
    }

    public ULongIndexer put(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), bigIntegerArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
