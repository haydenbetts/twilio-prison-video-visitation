package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;

public class ULongArrayIndexer extends ULongIndexer {
    protected long[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public ULongArrayIndexer(long[] jArr) {
        this(jArr, Index.create((long) jArr.length));
    }

    public ULongArrayIndexer(long[] jArr, long... jArr2) {
        this(jArr, Index.create(jArr2));
    }

    public ULongArrayIndexer(long[] jArr, long[] jArr2, long[] jArr3) {
        this(jArr, Index.create(jArr2, jArr3));
    }

    public ULongArrayIndexer(long[] jArr, Index index) {
        super(index);
        this.array = jArr;
    }

    public long[] array() {
        return this.array;
    }

    public ULongIndexer reindex(Index index) {
        return new ULongArrayIndexer(this.array, index);
    }

    public BigInteger get(long j) {
        return toBigInteger(this.array[(int) index(j)]);
    }

    public ULongIndexer get(long j, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = toBigInteger(this.array[((int) index(j)) + i3]);
        }
        return this;
    }

    public BigInteger get(long j, long j2) {
        return toBigInteger(this.array[(int) index(j, j2)]);
    }

    public ULongIndexer get(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = toBigInteger(this.array[((int) index(j, j2)) + i3]);
        }
        return this;
    }

    public BigInteger get(long j, long j2, long j3) {
        return toBigInteger(this.array[(int) index(j, j2, j3)]);
    }

    public BigInteger get(long... jArr) {
        return toBigInteger(this.array[(int) index(jArr)]);
    }

    public ULongIndexer get(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = toBigInteger(this.array[((int) index(jArr)) + i3]);
        }
        return this;
    }

    public ULongIndexer put(long j, BigInteger bigInteger) {
        this.array[(int) index(j)] = fromBigInteger(bigInteger);
        return this;
    }

    public ULongIndexer put(long j, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = fromBigInteger(bigIntegerArr[i + i3]);
        }
        return this;
    }

    public ULongIndexer put(long j, long j2, BigInteger bigInteger) {
        this.array[(int) index(j, j2)] = fromBigInteger(bigInteger);
        return this;
    }

    public ULongIndexer put(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = fromBigInteger(bigIntegerArr[i + i3]);
        }
        return this;
    }

    public ULongIndexer put(long j, long j2, long j3, BigInteger bigInteger) {
        this.array[(int) index(j, j2, j3)] = fromBigInteger(bigInteger);
        return this;
    }

    public ULongIndexer put(long[] jArr, BigInteger bigInteger) {
        this.array[(int) index(jArr)] = fromBigInteger(bigInteger);
        return this;
    }

    public ULongIndexer put(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = fromBigInteger(bigIntegerArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
