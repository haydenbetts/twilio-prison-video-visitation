package org.bytedeco.javacpp.indexer;

import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.LongBuffer;

public class ULongBufferIndexer extends ULongIndexer {
    protected LongBuffer buffer;

    public ULongBufferIndexer(LongBuffer longBuffer) {
        this(longBuffer, Index.create((long) longBuffer.limit()));
    }

    public ULongBufferIndexer(LongBuffer longBuffer, long... jArr) {
        this(longBuffer, Index.create(jArr));
    }

    public ULongBufferIndexer(LongBuffer longBuffer, long[] jArr, long[] jArr2) {
        this(longBuffer, Index.create(jArr, jArr2));
    }

    public ULongBufferIndexer(LongBuffer longBuffer, Index index) {
        super(index);
        this.buffer = longBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public ULongIndexer reindex(Index index) {
        return new ULongBufferIndexer(this.buffer, index);
    }

    public BigInteger get(long j) {
        return toBigInteger(this.buffer.get((int) index(j)));
    }

    public ULongIndexer get(long j, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = toBigInteger(this.buffer.get(((int) index(j)) + i3));
        }
        return this;
    }

    public BigInteger get(long j, long j2) {
        return toBigInteger(this.buffer.get((int) index(j, j2)));
    }

    public ULongIndexer get(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = toBigInteger(this.buffer.get(((int) index(j, j2)) + i3));
        }
        return this;
    }

    public BigInteger get(long j, long j2, long j3) {
        return toBigInteger(this.buffer.get((int) index(j, j2, j3)));
    }

    public BigInteger get(long... jArr) {
        return toBigInteger(this.buffer.get((int) index(jArr)));
    }

    public ULongIndexer get(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bigIntegerArr[i + i3] = toBigInteger(this.buffer.get(((int) index(jArr)) + i3));
        }
        return this;
    }

    public ULongIndexer put(long j, BigInteger bigInteger) {
        this.buffer.put((int) index(j), fromBigInteger(bigInteger));
        return this;
    }

    public ULongIndexer put(long j, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, fromBigInteger(bigIntegerArr[i + i3]));
        }
        return this;
    }

    public ULongIndexer put(long j, long j2, BigInteger bigInteger) {
        this.buffer.put((int) index(j, j2), fromBigInteger(bigInteger));
        return this;
    }

    public ULongIndexer put(long j, long j2, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, fromBigInteger(bigIntegerArr[i + i3]));
        }
        return this;
    }

    public ULongIndexer put(long j, long j2, long j3, BigInteger bigInteger) {
        this.buffer.put((int) index(j, j2, j3), fromBigInteger(bigInteger));
        return this;
    }

    public ULongIndexer put(long[] jArr, BigInteger bigInteger) {
        this.buffer.put((int) index(jArr), fromBigInteger(bigInteger));
        return this;
    }

    public ULongIndexer put(long[] jArr, BigInteger[] bigIntegerArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, fromBigInteger(bigIntegerArr[i + i3]));
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
