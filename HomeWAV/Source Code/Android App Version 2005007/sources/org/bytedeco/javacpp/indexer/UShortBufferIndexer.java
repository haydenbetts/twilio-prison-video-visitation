package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;
import kotlin.UShort;

public class UShortBufferIndexer extends UShortIndexer {
    protected ShortBuffer buffer;

    public UShortBufferIndexer(ShortBuffer shortBuffer) {
        this(shortBuffer, Index.create((long) shortBuffer.limit()));
    }

    public UShortBufferIndexer(ShortBuffer shortBuffer, long... jArr) {
        this(shortBuffer, Index.create(jArr));
    }

    public UShortBufferIndexer(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        this(shortBuffer, Index.create(jArr, jArr2));
    }

    public UShortBufferIndexer(ShortBuffer shortBuffer, Index index) {
        super(index);
        this.buffer = shortBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public UShortIndexer reindex(Index index) {
        return new UShortBufferIndexer(this.buffer, index);
    }

    public int get(long j) {
        return this.buffer.get((int) index(j)) & UShort.MAX_VALUE;
    }

    public UShortIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(j)) + i3) & UShort.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2) {
        return this.buffer.get((int) index(j, j2)) & UShort.MAX_VALUE;
    }

    public UShortIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3) & UShort.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3)) & UShort.MAX_VALUE;
    }

    public int get(long... jArr) {
        return this.buffer.get((int) index(jArr)) & UShort.MAX_VALUE;
    }

    public UShortIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3) & UShort.MAX_VALUE;
        }
        return this;
    }

    public UShortIndexer put(long j, int i) {
        this.buffer.put((int) index(j), (short) i);
        return this;
    }

    public UShortIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, (short) iArr[i + i3]);
        }
        return this;
    }

    public UShortIndexer put(long j, long j2, int i) {
        this.buffer.put((int) index(j, j2), (short) i);
        return this;
    }

    public UShortIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, (short) iArr[i + i3]);
        }
        return this;
    }

    public UShortIndexer put(long j, long j2, long j3, int i) {
        this.buffer.put((int) index(j, j2, j3), (short) i);
        return this;
    }

    public UShortIndexer put(long[] jArr, int i) {
        this.buffer.put((int) index(jArr), (short) i);
        return this;
    }

    public UShortIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, (short) iArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
