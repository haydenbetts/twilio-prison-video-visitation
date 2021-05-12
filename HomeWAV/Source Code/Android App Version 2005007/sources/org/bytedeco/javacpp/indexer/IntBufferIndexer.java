package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.IntBuffer;

public class IntBufferIndexer extends IntIndexer {
    protected IntBuffer buffer;

    public IntBufferIndexer(IntBuffer intBuffer) {
        this(intBuffer, Index.create((long) intBuffer.limit()));
    }

    public IntBufferIndexer(IntBuffer intBuffer, long... jArr) {
        this(intBuffer, Index.create(jArr));
    }

    public IntBufferIndexer(IntBuffer intBuffer, long[] jArr, long[] jArr2) {
        this(intBuffer, Index.create(jArr, jArr2));
    }

    public IntBufferIndexer(IntBuffer intBuffer, Index index) {
        super(index);
        this.buffer = intBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public IntIndexer reindex(Index index) {
        return new IntBufferIndexer(this.buffer, index);
    }

    public int get(long j) {
        return this.buffer.get((int) index(j));
    }

    public IntIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(j)) + i3);
        }
        return this;
    }

    public int get(long j, long j2) {
        return this.buffer.get((int) index(j, j2));
    }

    public IntIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3);
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3));
    }

    public int get(long... jArr) {
        return this.buffer.get((int) index(jArr));
    }

    public IntIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3);
        }
        return this;
    }

    public IntIndexer put(long j, int i) {
        this.buffer.put((int) index(j), i);
        return this;
    }

    public IntIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, iArr[i + i3]);
        }
        return this;
    }

    public IntIndexer put(long j, long j2, int i) {
        this.buffer.put((int) index(j, j2), i);
        return this;
    }

    public IntIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, iArr[i + i3]);
        }
        return this;
    }

    public IntIndexer put(long j, long j2, long j3, int i) {
        this.buffer.put((int) index(j, j2, j3), i);
        return this;
    }

    public IntIndexer put(long[] jArr, int i) {
        this.buffer.put((int) index(jArr), i);
        return this;
    }

    public IntIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, iArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
