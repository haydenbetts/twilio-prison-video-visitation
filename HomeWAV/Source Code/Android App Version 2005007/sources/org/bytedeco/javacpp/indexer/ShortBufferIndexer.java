package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;

public class ShortBufferIndexer extends ShortIndexer {
    protected ShortBuffer buffer;

    public ShortBufferIndexer(ShortBuffer shortBuffer) {
        this(shortBuffer, Index.create((long) shortBuffer.limit()));
    }

    public ShortBufferIndexer(ShortBuffer shortBuffer, long... jArr) {
        this(shortBuffer, Index.create(jArr));
    }

    public ShortBufferIndexer(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        this(shortBuffer, Index.create(jArr, jArr2));
    }

    public ShortBufferIndexer(ShortBuffer shortBuffer, Index index) {
        super(index);
        this.buffer = shortBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public ShortIndexer reindex(Index index) {
        return new ShortBufferIndexer(this.buffer, index);
    }

    public short get(long j) {
        return this.buffer.get((int) index(j));
    }

    public ShortIndexer get(long j, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = this.buffer.get(((int) index(j)) + i3);
        }
        return this;
    }

    public short get(long j, long j2) {
        return this.buffer.get((int) index(j, j2));
    }

    public ShortIndexer get(long j, long j2, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3);
        }
        return this;
    }

    public short get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3));
    }

    public short get(long... jArr) {
        return this.buffer.get((int) index(jArr));
    }

    public ShortIndexer get(long[] jArr, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3);
        }
        return this;
    }

    public ShortIndexer put(long j, short s) {
        this.buffer.put((int) index(j), s);
        return this;
    }

    public ShortIndexer put(long j, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, sArr[i + i3]);
        }
        return this;
    }

    public ShortIndexer put(long j, long j2, short s) {
        this.buffer.put((int) index(j, j2), s);
        return this;
    }

    public ShortIndexer put(long j, long j2, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, sArr[i + i3]);
        }
        return this;
    }

    public ShortIndexer put(long j, long j2, long j3, short s) {
        this.buffer.put((int) index(j, j2, j3), s);
        return this;
    }

    public ShortIndexer put(long[] jArr, short s) {
        this.buffer.put((int) index(jArr), s);
        return this;
    }

    public ShortIndexer put(long[] jArr, short[] sArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, sArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
