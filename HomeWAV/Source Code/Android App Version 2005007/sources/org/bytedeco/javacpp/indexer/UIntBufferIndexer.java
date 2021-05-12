package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.IntBuffer;

public class UIntBufferIndexer extends UIntIndexer {
    protected IntBuffer buffer;

    public UIntBufferIndexer(IntBuffer intBuffer) {
        this(intBuffer, Index.create((long) intBuffer.limit()));
    }

    public UIntBufferIndexer(IntBuffer intBuffer, long... jArr) {
        this(intBuffer, Index.create(jArr));
    }

    public UIntBufferIndexer(IntBuffer intBuffer, long[] jArr, long[] jArr2) {
        this(intBuffer, Index.create(jArr, jArr2));
    }

    public UIntBufferIndexer(IntBuffer intBuffer, Index index) {
        super(index);
        this.buffer = intBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public UIntIndexer reindex(Index index) {
        return new UIntBufferIndexer(this.buffer, index);
    }

    public long get(long j) {
        return ((long) this.buffer.get((int) index(j))) & 4294967295L;
    }

    public UIntIndexer get(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = ((long) this.buffer.get(((int) index(j)) + i3)) & 4294967295L;
        }
        return this;
    }

    public long get(long j, long j2) {
        return ((long) this.buffer.get((int) index(j, j2))) & 4294967295L;
    }

    public UIntIndexer get(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = ((long) this.buffer.get(((int) index(j, j2)) + i3)) & 4294967295L;
        }
        return this;
    }

    public long get(long j, long j2, long j3) {
        return ((long) this.buffer.get((int) index(j, j2, j3))) & 4294967295L;
    }

    public long get(long... jArr) {
        return ((long) this.buffer.get((int) index(jArr))) & 4294967295L;
    }

    public UIntIndexer get(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr2[i + i3] = ((long) this.buffer.get(((int) index(jArr)) + i3)) & 4294967295L;
        }
        return this;
    }

    public UIntIndexer put(long j, long j2) {
        this.buffer.put((int) index(j), (int) j2);
        return this;
    }

    public UIntIndexer put(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, (int) jArr[i + i3]);
        }
        return this;
    }

    public UIntIndexer put(long j, long j2, long j3) {
        this.buffer.put((int) index(j, j2), (int) j3);
        return this;
    }

    public UIntIndexer put(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, (int) jArr[i + i3]);
        }
        return this;
    }

    public UIntIndexer put(long j, long j2, long j3, long j4) {
        this.buffer.put((int) index(j, j2, j3), (int) j4);
        return this;
    }

    public UIntIndexer put(long[] jArr, long j) {
        this.buffer.put((int) index(jArr), (int) j);
        return this;
    }

    public UIntIndexer put(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, (int) jArr2[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
