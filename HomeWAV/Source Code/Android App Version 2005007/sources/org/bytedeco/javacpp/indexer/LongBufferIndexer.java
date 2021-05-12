package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.LongBuffer;

public class LongBufferIndexer extends LongIndexer {
    protected LongBuffer buffer;

    public LongBufferIndexer(LongBuffer longBuffer) {
        this(longBuffer, Index.create((long) longBuffer.limit()));
    }

    public LongBufferIndexer(LongBuffer longBuffer, long... jArr) {
        this(longBuffer, Index.create(jArr));
    }

    public LongBufferIndexer(LongBuffer longBuffer, long[] jArr, long[] jArr2) {
        this(longBuffer, Index.create(jArr, jArr2));
    }

    public LongBufferIndexer(LongBuffer longBuffer, Index index) {
        super(index);
        this.buffer = longBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public LongIndexer reindex(Index index) {
        return new LongBufferIndexer(this.buffer, index);
    }

    public long get(long j) {
        return this.buffer.get((int) index(j));
    }

    public LongIndexer get(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = this.buffer.get(((int) index(j)) + i3);
        }
        return this;
    }

    public long get(long j, long j2) {
        return this.buffer.get((int) index(j, j2));
    }

    public LongIndexer get(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3);
        }
        return this;
    }

    public long get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3));
    }

    public long get(long... jArr) {
        return this.buffer.get((int) index(jArr));
    }

    public LongIndexer get(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            jArr2[i + i3] = this.buffer.get(((int) index(jArr)) + i3);
        }
        return this;
    }

    public LongIndexer put(long j, long j2) {
        this.buffer.put((int) index(j), j2);
        return this;
    }

    public LongIndexer put(long j, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, jArr[i + i3]);
        }
        return this;
    }

    public LongIndexer put(long j, long j2, long j3) {
        this.buffer.put((int) index(j, j2), j3);
        return this;
    }

    public LongIndexer put(long j, long j2, long[] jArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, jArr[i + i3]);
        }
        return this;
    }

    public LongIndexer put(long j, long j2, long j3, long j4) {
        this.buffer.put((int) index(j, j2, j3), j4);
        return this;
    }

    public LongIndexer put(long[] jArr, long j) {
        this.buffer.put((int) index(jArr), j);
        return this;
    }

    public LongIndexer put(long[] jArr, long[] jArr2, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, jArr2[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
