package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.CharPointer;
import org.bytedeco.javacpp.Pointer;

public class CharRawIndexer extends CharIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected CharPointer pointer;
    final long size;

    public CharRawIndexer(CharPointer charPointer) {
        this(charPointer, Index.create(charPointer.limit() - charPointer.position()));
    }

    public CharRawIndexer(CharPointer charPointer, long... jArr) {
        this(charPointer, Index.create(jArr));
    }

    public CharRawIndexer(CharPointer charPointer, long[] jArr, long[] jArr2) {
        this(charPointer, Index.create(jArr, jArr2));
    }

    public CharRawIndexer(CharPointer charPointer, Index index) {
        super(index);
        this.pointer = charPointer;
        this.base = charPointer.address() + (charPointer.position() * 2);
        this.size = charPointer.limit() - charPointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public CharIndexer reindex(Index index) {
        return new CharRawIndexer(this.pointer, index);
    }

    public char getRaw(long j) {
        return RAW.getChar(this.base + (checkIndex(j, this.size) * 2));
    }

    public char get(long j) {
        return getRaw(index(j));
    }

    public CharIndexer get(long j, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public char get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public CharIndexer get(long j, long j2, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public char get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public char get(long... jArr) {
        return getRaw(index(jArr));
    }

    public CharIndexer get(long[] jArr, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public CharIndexer putRaw(long j, char c) {
        RAW.putChar(this.base + (checkIndex(j, this.size) * 2), c);
        return this;
    }

    public CharIndexer put(long j, char c) {
        return putRaw(index(j), c);
    }

    public CharIndexer put(long j, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), cArr[i + i3]);
        }
        return this;
    }

    public CharIndexer put(long j, long j2, char c) {
        putRaw(index(j, j2), c);
        return this;
    }

    public CharIndexer put(long j, long j2, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), cArr[i + i3]);
        }
        return this;
    }

    public CharIndexer put(long j, long j2, long j3, char c) {
        putRaw(index(j, j2, j3), c);
        return this;
    }

    public CharIndexer put(long[] jArr, char c) {
        putRaw(index(jArr), c);
        return this;
    }

    public CharIndexer put(long[] jArr, char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), cArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
