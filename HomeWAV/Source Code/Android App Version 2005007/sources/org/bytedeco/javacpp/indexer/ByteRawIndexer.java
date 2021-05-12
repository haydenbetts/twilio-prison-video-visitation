package org.bytedeco.javacpp.indexer;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;

public class ByteRawIndexer extends ByteIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected BytePointer pointer;
    final long size;

    public ByteRawIndexer(BytePointer bytePointer) {
        this(bytePointer, Index.create(bytePointer.limit() - bytePointer.position()));
    }

    public ByteRawIndexer(BytePointer bytePointer, long... jArr) {
        this(bytePointer, jArr, strides(jArr));
    }

    public ByteRawIndexer(BytePointer bytePointer, long[] jArr, long[] jArr2) {
        this(bytePointer, Index.create(jArr, jArr2));
    }

    public ByteRawIndexer(BytePointer bytePointer, Index index) {
        super(index);
        this.pointer = bytePointer;
        this.base = bytePointer.address() + bytePointer.position();
        this.size = bytePointer.limit() - bytePointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public ByteIndexer reindex(Index index) {
        return new ByteRawIndexer(this.pointer, index);
    }

    public byte getRaw(long j) {
        return RAW.getByte(this.base + checkIndex(j, this.size));
    }

    public byte get(long j) {
        return getRaw(index(j));
    }

    public ByteIndexer get(long j, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = getRaw(index(j) + ((long) i3));
        }
        return this;
    }

    public byte get(long j, long j2) {
        return getRaw(index(j, j2));
    }

    public ByteIndexer get(long j, long j2, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = getRaw(index(j, j2) + ((long) i3));
        }
        return this;
    }

    public byte get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3));
    }

    public byte get(long... jArr) {
        return getRaw(index(jArr));
    }

    public ByteIndexer get(long[] jArr, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = getRaw(index(jArr) + ((long) i3));
        }
        return this;
    }

    public ByteIndexer putRaw(long j, byte b) {
        RAW.putByte(this.base + checkIndex(j, this.size), b);
        return this;
    }

    public ByteIndexer put(long j, byte b) {
        return putRaw(index(j), b);
    }

    public ByteIndexer put(long j, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), bArr[i + i3]);
        }
        return this;
    }

    public ByteIndexer put(long j, long j2, byte b) {
        putRaw(index(j, j2), b);
        return this;
    }

    public ByteIndexer put(long j, long j2, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), bArr[i + i3]);
        }
        return this;
    }

    public ByteIndexer put(long j, long j2, long j3, byte b) {
        putRaw(index(j, j2, j3), b);
        return this;
    }

    public ByteIndexer put(long[] jArr, byte b) {
        putRaw(index(jArr), b);
        return this;
    }

    public ByteIndexer put(long[] jArr, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), bArr[i + i3]);
        }
        return this;
    }

    public byte getByte(long j) {
        return RAW.getByte(this.base + checkIndex(j, this.size - 1));
    }

    public ByteIndexer putByte(long j, byte b) {
        RAW.putByte(this.base + checkIndex(j, this.size - 1), b);
        return this;
    }

    public short getShort(long j) {
        return RAW.getShort(this.base + checkIndex(j, this.size - 1));
    }

    public ByteIndexer putShort(long j, short s) {
        RAW.putShort(this.base + checkIndex(j, this.size - 1), s);
        return this;
    }

    public int getInt(long j) {
        return RAW.getInt(this.base + checkIndex(j, this.size - 3));
    }

    public ByteIndexer putInt(long j, int i) {
        RAW.putInt(this.base + checkIndex(j, this.size - 3), i);
        return this;
    }

    public long getLong(long j) {
        return RAW.getLong(this.base + checkIndex(j, this.size - 7));
    }

    public ByteIndexer putLong(long j, long j2) {
        RAW.putLong(this.base + checkIndex(j, this.size - 7), j2);
        return this;
    }

    public float getFloat(long j) {
        return RAW.getFloat(this.base + checkIndex(j, this.size - 3));
    }

    public ByteIndexer putFloat(long j, float f) {
        RAW.putFloat(this.base + checkIndex(j, this.size - 3), f);
        return this;
    }

    public double getDouble(long j) {
        return RAW.getDouble(this.base + checkIndex(j, this.size - 7));
    }

    public ByteIndexer putDouble(long j, double d) {
        RAW.putDouble(this.base + checkIndex(j, this.size - 7), d);
        return this;
    }

    public char getChar(long j) {
        return RAW.getChar(this.base + checkIndex(j, this.size - 1));
    }

    public ByteIndexer putChar(long j, char c) {
        RAW.putChar(this.base + checkIndex(j, this.size - 1), c);
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
