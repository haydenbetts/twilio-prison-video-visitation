package org.bytedeco.javacpp.indexer;

public class HyperslabIndex extends StrideIndex {
    protected long[] selectionBlocks;
    protected long[] selectionCounts;
    protected long[] selectionOffsets;
    protected long[] selectionStrides;

    public HyperslabIndex(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        this(jArr, defaultStrides(jArr), jArr2, jArr3, jArr4, jArr5);
    }

    public HyperslabIndex(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5, long[] jArr6) {
        super(jArr, jArr2);
        this.selectionOffsets = jArr3;
        this.selectionStrides = jArr4;
        this.selectionCounts = jArr5;
        this.selectionBlocks = jArr6;
        for (int i = 0; i < jArr5.length; i++) {
            this.sizes[i] = jArr5[i] * jArr6[i];
        }
    }

    public long index(long j) {
        long j2 = this.selectionOffsets[0];
        long j3 = this.selectionStrides[0];
        long[] jArr = this.selectionBlocks;
        return (j2 + (j3 * (j / jArr[0])) + (j % jArr[0])) * this.strides[0];
    }

    public long index(long j, long j2) {
        long j3 = this.selectionOffsets[0];
        long j4 = this.selectionStrides[0];
        long[] jArr = this.selectionBlocks;
        long j5 = (j3 + (j4 * (j / jArr[0])) + (j % jArr[0])) * this.strides[0];
        long j6 = this.selectionOffsets[1];
        long j7 = this.selectionStrides[1];
        long[] jArr2 = this.selectionBlocks;
        return j5 + ((j6 + (j7 * (j2 / jArr2[1])) + (j2 % jArr2[1])) * this.strides[1]);
    }

    public long index(long j, long j2, long j3) {
        long j4 = this.selectionOffsets[0];
        long j5 = this.selectionStrides[0];
        long[] jArr = this.selectionBlocks;
        long j6 = (j4 + (j5 * (j / jArr[0])) + (j % jArr[0])) * this.strides[0];
        long j7 = this.selectionOffsets[1];
        long j8 = this.selectionStrides[1];
        long[] jArr2 = this.selectionBlocks;
        long j9 = j6 + ((j7 + (j8 * (j2 / jArr2[1])) + (j2 % jArr2[1])) * this.strides[1]);
        long j10 = this.selectionOffsets[2];
        long j11 = this.selectionStrides[2];
        long[] jArr3 = this.selectionBlocks;
        return j9 + ((j10 + (j11 * (j3 / jArr3[2])) + (j3 % jArr3[2])) * this.strides[2]);
    }

    public long index(long... jArr) {
        long j = 0;
        for (int i = 0; i < jArr.length; i++) {
            long j2 = jArr[i];
            long j3 = this.selectionOffsets[i];
            long j4 = this.selectionStrides[i];
            long[] jArr2 = this.selectionBlocks;
            j += (j3 + (j4 * (j2 / jArr2[i])) + (j2 % jArr2[i])) * this.strides[i];
        }
        return j;
    }
}
