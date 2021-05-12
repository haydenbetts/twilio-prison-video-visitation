package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ByteBufferIndexer extends ByteIndexer {
    protected ByteBuffer buffer;

    public ByteBufferIndexer(ByteBuffer byteBuffer) {
        this(byteBuffer, Index.create((long) byteBuffer.limit()));
    }

    public ByteBufferIndexer(ByteBuffer byteBuffer, long... jArr) {
        this(byteBuffer, Index.create(jArr));
    }

    public ByteBufferIndexer(ByteBuffer byteBuffer, long[] jArr, long[] jArr2) {
        this(byteBuffer, Index.create(jArr, jArr2));
    }

    public ByteBufferIndexer(ByteBuffer byteBuffer, Index index) {
        super(index);
        this.buffer = byteBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public ByteIndexer reindex(Index index) {
        return new ByteBufferIndexer(this.buffer, index);
    }

    public byte get(long j) {
        return this.buffer.get((int) index(j));
    }

    public ByteIndexer get(long j, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = this.buffer.get(((int) index(j)) + i3);
        }
        return this;
    }

    public byte get(long j, long j2) {
        return this.buffer.get((int) index(j, j2));
    }

    public ByteIndexer get(long j, long j2, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3);
        }
        return this;
    }

    public byte get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3));
    }

    public byte get(long... jArr) {
        return this.buffer.get((int) index(jArr));
    }

    public ByteIndexer get(long[] jArr, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3);
        }
        return this;
    }

    public ByteIndexer put(long j, byte b) {
        this.buffer.put((int) index(j), b);
        return this;
    }

    public ByteIndexer put(long j, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, bArr[i + i3]);
        }
        return this;
    }

    public ByteIndexer put(long j, long j2, byte b) {
        this.buffer.put((int) index(j, j2), b);
        return this;
    }

    public ByteIndexer put(long j, long j2, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, bArr[i + i3]);
        }
        return this;
    }

    public ByteIndexer put(long j, long j2, long j3, byte b) {
        this.buffer.put((int) index(j, j2, j3), b);
        return this;
    }

    public ByteIndexer put(long[] jArr, byte b) {
        this.buffer.put((int) index(jArr), b);
        return this;
    }

    public ByteIndexer put(long[] jArr, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, bArr[i + i3]);
        }
        return this;
    }

    public byte getByte(long j) {
        return this.buffer.get((int) j);
    }

    public ByteIndexer putByte(long j, byte b) {
        this.buffer.put((int) j, b);
        return this;
    }

    public short getShort(long j) {
        return this.buffer.getShort((int) j);
    }

    public ByteIndexer putShort(long j, short s) {
        this.buffer.putShort((int) j, s);
        return this;
    }

    public int getInt(long j) {
        return this.buffer.getInt((int) j);
    }

    public ByteIndexer putInt(long j, int i) {
        this.buffer.putInt((int) j, i);
        return this;
    }

    public long getLong(long j) {
        return this.buffer.getLong((int) j);
    }

    public ByteIndexer putLong(long j, long j2) {
        this.buffer.putLong((int) j, j2);
        return this;
    }

    public float getFloat(long j) {
        return this.buffer.getFloat((int) j);
    }

    public ByteIndexer putFloat(long j, float f) {
        this.buffer.putFloat((int) j, f);
        return this;
    }

    public double getDouble(long j) {
        return this.buffer.getDouble((int) j);
    }

    public ByteIndexer putDouble(long j, double d) {
        this.buffer.putDouble((int) j, d);
        return this;
    }

    public char getChar(long j) {
        return this.buffer.getChar((int) j);
    }

    public ByteIndexer putChar(long j, char c) {
        this.buffer.putChar((int) j, c);
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
