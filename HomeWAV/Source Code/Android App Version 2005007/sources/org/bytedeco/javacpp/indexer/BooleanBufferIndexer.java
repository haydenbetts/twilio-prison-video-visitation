package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BooleanBufferIndexer extends BooleanIndexer {
    protected ByteBuffer buffer;

    public BooleanBufferIndexer(ByteBuffer byteBuffer) {
        this(byteBuffer, Index.create((long) byteBuffer.limit()));
    }

    public BooleanBufferIndexer(ByteBuffer byteBuffer, long... jArr) {
        this(byteBuffer, Index.create(jArr));
    }

    public BooleanBufferIndexer(ByteBuffer byteBuffer, long[] jArr, long[] jArr2) {
        this(byteBuffer, Index.create(jArr, jArr2));
    }

    public BooleanBufferIndexer(ByteBuffer byteBuffer, Index index) {
        super(index);
        this.buffer = byteBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public BooleanIndexer reindex(Index index) {
        return new BooleanBufferIndexer(this.buffer, index);
    }

    public boolean get(long j) {
        return this.buffer.get((int) index(j)) != 0;
    }

    public BooleanIndexer get(long j, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = this.buffer.get(((int) index(j)) + i3) != 0;
        }
        return this;
    }

    public boolean get(long j, long j2) {
        return this.buffer.get((int) index(j, j2)) != 0;
    }

    public BooleanIndexer get(long j, long j2, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3) != 0;
        }
        return this;
    }

    public boolean get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3)) != 0;
    }

    public boolean get(long... jArr) {
        return this.buffer.get((int) index(jArr)) != 0;
    }

    public BooleanIndexer get(long[] jArr, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3) != 0;
        }
        return this;
    }

    public BooleanIndexer put(long j, boolean z) {
        this.buffer.put((int) index(j), z ? (byte) 1 : 0);
        return this;
    }

    public BooleanIndexer put(long j, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, zArr[i + i3] ? (byte) 1 : 0);
        }
        return this;
    }

    public BooleanIndexer put(long j, long j2, boolean z) {
        this.buffer.put((int) index(j, j2), z ? (byte) 1 : 0);
        return this;
    }

    public BooleanIndexer put(long j, long j2, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, zArr[i + i3] ? (byte) 1 : 0);
        }
        return this;
    }

    public BooleanIndexer put(long j, long j2, long j3, boolean z) {
        this.buffer.put((int) index(j, j2, j3), z ? (byte) 1 : 0);
        return this;
    }

    public BooleanIndexer put(long[] jArr, boolean z) {
        this.buffer.put((int) index(jArr), z ? (byte) 1 : 0);
        return this;
    }

    public BooleanIndexer put(long[] jArr, boolean[] zArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, zArr[i + i3] ? (byte) 1 : 0);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
