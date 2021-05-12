package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.CharBuffer;

public class CharBufferIndexer extends CharIndexer {
    protected CharBuffer buffer;

    public CharBufferIndexer(CharBuffer charBuffer) {
        this(charBuffer, Index.create((long) charBuffer.limit()));
    }

    public CharBufferIndexer(CharBuffer charBuffer, long... jArr) {
        this(charBuffer, Index.create(jArr));
    }

    public CharBufferIndexer(CharBuffer charBuffer, long[] jArr, long[] jArr2) {
        this(charBuffer, Index.create(jArr, jArr2));
    }

    public CharBufferIndexer(CharBuffer charBuffer, Index index) {
        super(index);
        this.buffer = charBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public CharIndexer reindex(Index index) {
        return new CharBufferIndexer(this.buffer, index);
    }

    public char get(long j) {
        return this.buffer.get((int) index(j));
    }

    public CharIndexer get(long j, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = this.buffer.get(((int) index(j)) + i3);
        }
        return this;
    }

    public char get(long j, long j2) {
        return this.buffer.get((int) index(j, j2));
    }

    public CharIndexer get(long j, long j2, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3);
        }
        return this;
    }

    public char get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3));
    }

    public char get(long... jArr) {
        return this.buffer.get((int) index(jArr));
    }

    public CharIndexer get(long[] jArr, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3);
        }
        return this;
    }

    public CharIndexer put(long j, char c) {
        this.buffer.put((int) index(j), c);
        return this;
    }

    public CharIndexer put(long j, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, cArr[i + i3]);
        }
        return this;
    }

    public CharIndexer put(long j, long j2, char c) {
        this.buffer.put((int) index(j, j2), c);
        return this;
    }

    public CharIndexer put(long j, long j2, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, cArr[i + i3]);
        }
        return this;
    }

    public CharIndexer put(long j, long j2, long j3, char c) {
        this.buffer.put((int) index(j, j2, j3), c);
        return this;
    }

    public CharIndexer put(long[] jArr, char c) {
        this.buffer.put((int) index(jArr), c);
        return this;
    }

    public CharIndexer put(long[] jArr, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, cArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
