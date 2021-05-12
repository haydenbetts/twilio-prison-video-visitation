package org.bytedeco.javacpp.indexer;

import kotlin.UByte;

public class UByteArrayIndexer extends UByteIndexer {
    protected byte[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public UByteArrayIndexer(byte[] bArr) {
        this(bArr, Index.create((long) bArr.length));
    }

    public UByteArrayIndexer(byte[] bArr, long... jArr) {
        this(bArr, Index.create(jArr));
    }

    public UByteArrayIndexer(byte[] bArr, long[] jArr, long[] jArr2) {
        this(bArr, Index.create(jArr, jArr2));
    }

    public UByteArrayIndexer(byte[] bArr, Index index) {
        super(index);
        this.array = bArr;
    }

    public byte[] array() {
        return this.array;
    }

    public UByteIndexer reindex(Index index) {
        return new UByteArrayIndexer(this.array, index);
    }

    public int get(long j) {
        return this.array[(int) index(j)] & UByte.MAX_VALUE;
    }

    public UByteIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(j)) + i3] & UByte.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2) {
        return this.array[(int) index(j, j2)] & UByte.MAX_VALUE;
    }

    public UByteIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(j, j2)) + i3] & UByte.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)] & UByte.MAX_VALUE;
    }

    public int get(long... jArr) {
        return this.array[(int) index(jArr)] & UByte.MAX_VALUE;
    }

    public UByteIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.array[((int) index(jArr)) + i3] & UByte.MAX_VALUE;
        }
        return this;
    }

    public UByteIndexer put(long j, int i) {
        this.array[(int) index(j)] = (byte) i;
        return this;
    }

    public UByteIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = (byte) iArr[i + i3];
        }
        return this;
    }

    public UByteIndexer put(long j, long j2, int i) {
        this.array[(int) index(j, j2)] = (byte) i;
        return this;
    }

    public UByteIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = (byte) iArr[i + i3];
        }
        return this;
    }

    public UByteIndexer put(long j, long j2, long j3, int i) {
        this.array[(int) index(j, j2, j3)] = (byte) i;
        return this;
    }

    public UByteIndexer put(long[] jArr, int i) {
        this.array[(int) index(jArr)] = (byte) i;
        return this;
    }

    public UByteIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = (byte) iArr[i + i3];
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
