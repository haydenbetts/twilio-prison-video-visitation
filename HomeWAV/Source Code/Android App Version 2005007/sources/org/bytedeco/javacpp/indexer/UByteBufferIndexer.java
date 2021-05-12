package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import kotlin.UByte;

public class UByteBufferIndexer extends UByteIndexer {
    protected ByteBuffer buffer;

    public UByteBufferIndexer(ByteBuffer byteBuffer) {
        this(byteBuffer, Index.create((long) byteBuffer.limit()));
    }

    public UByteBufferIndexer(ByteBuffer byteBuffer, long... jArr) {
        this(byteBuffer, Index.create(jArr));
    }

    public UByteBufferIndexer(ByteBuffer byteBuffer, long[] jArr, long[] jArr2) {
        this(byteBuffer, Index.create(jArr, jArr2));
    }

    public UByteBufferIndexer(ByteBuffer byteBuffer, Index index) {
        super(index);
        this.buffer = byteBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public UByteIndexer reindex(Index index) {
        return new UByteBufferIndexer(this.buffer, index);
    }

    public int get(long j) {
        return this.buffer.get((int) index(j)) & UByte.MAX_VALUE;
    }

    public UByteIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(j)) + i3) & UByte.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2) {
        return this.buffer.get((int) index(j, j2)) & UByte.MAX_VALUE;
    }

    public UByteIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3) & UByte.MAX_VALUE;
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3)) & UByte.MAX_VALUE;
    }

    public int get(long... jArr) {
        return this.buffer.get((int) index(jArr)) & UByte.MAX_VALUE;
    }

    public UByteIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3) & UByte.MAX_VALUE;
        }
        return this;
    }

    public UByteIndexer put(long j, int i) {
        this.buffer.put((int) index(j), (byte) i);
        return this;
    }

    public UByteIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, (byte) iArr[i + i3]);
        }
        return this;
    }

    public UByteIndexer put(long j, long j2, int i) {
        this.buffer.put((int) index(j, j2), (byte) i);
        return this;
    }

    public UByteIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, (byte) iArr[i + i3]);
        }
        return this;
    }

    public UByteIndexer put(long j, long j2, long j3, int i) {
        this.buffer.put((int) index(j, j2, j3), (byte) i);
        return this;
    }

    public UByteIndexer put(long[] jArr, int i) {
        this.buffer.put((int) index(jArr), (byte) i);
        return this;
    }

    public UByteIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, (byte) iArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
