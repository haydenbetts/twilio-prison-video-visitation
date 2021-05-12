package org.bytedeco.javacpp.indexer;

import kotlin.UByte;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;

public class UByteRawIndexer extends UByteIndexer {
    protected static final Raw RAW = Raw.getInstance();
    final long base;
    protected BytePointer pointer;
    final long size;

    public UByteRawIndexer(BytePointer bytePointer) {
        this(bytePointer, Index.create(bytePointer.limit() - bytePointer.position()));
    }

    public UByteRawIndexer(BytePointer bytePointer, long... jArr) {
        this(bytePointer, jArr, strides(jArr));
    }

    public UByteRawIndexer(BytePointer bytePointer, long[] jArr, long[] jArr2) {
        this(bytePointer, Index.create(jArr, jArr2));
    }

    public UByteRawIndexer(BytePointer bytePointer, Index index) {
        super(index);
        this.pointer = bytePointer;
        this.base = bytePointer.address() + bytePointer.position();
        this.size = bytePointer.limit() - bytePointer.position();
    }

    public Pointer pointer() {
        return this.pointer;
    }

    public UByteIndexer reindex(Index index) {
        return new UByteRawIndexer(this.pointer, index);
    }

    public int getRaw(long j) {
        return RAW.getByte(this.base + checkIndex(j, this.size)) & UByte.MAX_VALUE;
    }

    public int get(long j) {
        return getRaw(index(j));
    }

    public UByteIndexer get(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(j) + ((long) i3)) & 255;
        }
        return this;
    }

    public int get(long j, long j2) {
        return getRaw(index(j, j2)) & 255;
    }

    public UByteIndexer get(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(j, j2) + ((long) i3)) & 255;
        }
        return this;
    }

    public int get(long j, long j2, long j3) {
        return getRaw(index(j, j2, j3)) & 255;
    }

    public int get(long... jArr) {
        return getRaw(index(jArr)) & 255;
    }

    public UByteIndexer get(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i + i3] = getRaw(index(jArr) + ((long) i3)) & 255;
        }
        return this;
    }

    public UByteIndexer putRaw(long j, int i) {
        RAW.putByte(this.base + checkIndex(j, this.size), (byte) i);
        return this;
    }

    public UByteIndexer put(long j, int i) {
        putRaw(index(j), i);
        return this;
    }

    public UByteIndexer put(long j, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public UByteIndexer put(long j, long j2, int i) {
        putRaw(index(j, j2), i);
        return this;
    }

    public UByteIndexer put(long j, long j2, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(j, j2) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public UByteIndexer put(long j, long j2, long j3, int i) {
        putRaw(index(j, j2, j3), i);
        return this;
    }

    public UByteIndexer put(long[] jArr, int i) {
        putRaw(index(jArr), i);
        return this;
    }

    public UByteIndexer put(long[] jArr, int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            putRaw(index(jArr) + ((long) i3), iArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.pointer = null;
    }
}
