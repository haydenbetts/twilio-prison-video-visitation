package org.bytedeco.javacpp.indexer;

public class StrideIndex extends Index {
    protected final long[] strides;

    public static long[] defaultStrides(long... jArr) {
        long[] jArr2 = new long[jArr.length];
        jArr2[jArr.length - 1] = 1;
        for (int length = jArr.length - 2; length >= 0; length--) {
            int i = length + 1;
            jArr2[length] = jArr2[i] * jArr[i];
        }
        return jArr2;
    }

    public StrideIndex(long... jArr) {
        this(jArr, defaultStrides(jArr));
    }

    public StrideIndex(long[] jArr, long[] jArr2) {
        super(jArr);
        this.strides = jArr2;
    }

    public long[] strides() {
        return this.strides;
    }

    public long index(long j) {
        return j * this.strides[0];
    }

    public long index(long j, long j2) {
        long[] jArr = this.strides;
        return (j * jArr[0]) + (j2 * jArr[1]);
    }

    public long index(long j, long j2, long j3) {
        long[] jArr = this.strides;
        return (j * jArr[0]) + (j2 * jArr[1]) + (j3 * jArr[2]);
    }

    public long index(long... jArr) {
        long j = 0;
        for (int i = 0; i < jArr.length; i++) {
            long[] jArr2 = this.strides;
            if (i >= jArr2.length) {
                break;
            }
            j += jArr[i] * jArr2[i];
        }
        return j;
    }
}
