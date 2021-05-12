package org.bytedeco.javacpp.indexer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteArrayIndexer extends ByteIndexer {
    protected static final Raw RAW = Raw.getInstance();
    protected byte[] array;
    protected ByteBuffer buffer;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public ByteArrayIndexer(byte[] bArr) {
        this(bArr, Index.create((long) bArr.length));
    }

    public ByteArrayIndexer(byte[] bArr, long... jArr) {
        this(bArr, Index.create(jArr));
    }

    public ByteArrayIndexer(byte[] bArr, long[] jArr, long[] jArr2) {
        this(bArr, Index.create(jArr, jArr2));
    }

    public ByteArrayIndexer(byte[] bArr, Index index) {
        super(index);
        this.array = bArr;
    }

    public byte[] array() {
        return this.array;
    }

    public ByteIndexer reindex(Index index) {
        return new ByteArrayIndexer(this.array, index);
    }

    public byte get(long j) {
        return this.array[(int) index(j)];
    }

    public ByteIndexer get(long j, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = this.array[((int) index(j)) + i3];
        }
        return this;
    }

    public byte get(long j, long j2) {
        return this.array[(int) index(j, j2)];
    }

    public ByteIndexer get(long j, long j2, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = this.array[((int) index(j, j2)) + i3];
        }
        return this;
    }

    public byte get(long j, long j2, long j3) {
        return this.array[(int) index(j, j2, j3)];
    }

    public byte get(long... jArr) {
        return this.array[(int) index(jArr)];
    }

    public ByteIndexer get(long[] jArr, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i + i3] = this.array[((int) index(jArr)) + i3];
        }
        return this;
    }

    public ByteIndexer put(long j, byte b) {
        this.array[(int) index(j)] = b;
        return this;
    }

    public ByteIndexer put(long j, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = bArr[i + i3];
        }
        return this;
    }

    public ByteIndexer put(long j, long j2, byte b) {
        this.array[(int) index(j, j2)] = b;
        return this;
    }

    public ByteIndexer put(long j, long j2, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = bArr[i + i3];
        }
        return this;
    }

    public ByteIndexer put(long j, long j2, long j3, byte b) {
        this.array[(int) index(j, j2, j3)] = b;
        return this;
    }

    public ByteIndexer put(long[] jArr, byte b) {
        this.array[(int) index(jArr)] = b;
        return this;
    }

    public ByteIndexer put(long[] jArr, byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = bArr[i + i3];
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer getBuffer() {
        if (this.buffer == null) {
            this.buffer = ByteBuffer.wrap(this.array).order(ByteOrder.nativeOrder());
        }
        return this.buffer;
    }

    public byte getByte(long j) {
        return this.array[(int) j];
    }

    public ByteIndexer putByte(long j, byte b) {
        this.array[(int) j] = b;
        return this;
    }

    public short getShort(long j) {
        Raw raw = RAW;
        if (raw == null) {
            return getBuffer().getShort((int) j);
        }
        byte[] bArr = this.array;
        return raw.getShort(bArr, checkIndex(j, (long) (bArr.length - 1)));
    }

    public ByteIndexer putShort(long j, short s) {
        Raw raw = RAW;
        if (raw != null) {
            byte[] bArr = this.array;
            raw.putShort(bArr, checkIndex(j, (long) (bArr.length - 1)), s);
        } else {
            getBuffer().putShort((int) j, s);
        }
        return this;
    }

    public int getInt(long j) {
        Raw raw = RAW;
        if (raw == null) {
            return getBuffer().getInt((int) j);
        }
        byte[] bArr = this.array;
        return raw.getInt(bArr, checkIndex(j, (long) (bArr.length - 3)));
    }

    public ByteIndexer putInt(long j, int i) {
        Raw raw = RAW;
        if (raw != null) {
            byte[] bArr = this.array;
            raw.putInt(bArr, checkIndex(j, (long) (bArr.length - 3)), i);
        } else {
            getBuffer().putInt((int) j, i);
        }
        return this;
    }

    public long getLong(long j) {
        Raw raw = RAW;
        if (raw == null) {
            return getBuffer().getLong((int) j);
        }
        byte[] bArr = this.array;
        return raw.getLong(bArr, checkIndex(j, (long) (bArr.length - 7)));
    }

    public ByteIndexer putLong(long j, long j2) {
        Raw raw = RAW;
        if (raw != null) {
            byte[] bArr = this.array;
            raw.putLong(bArr, checkIndex(j, (long) (bArr.length - 7)), j2);
        } else {
            getBuffer().putLong((int) j, j2);
        }
        return this;
    }

    public float getFloat(long j) {
        Raw raw = RAW;
        if (raw == null) {
            return getBuffer().getFloat((int) j);
        }
        byte[] bArr = this.array;
        return raw.getFloat(bArr, checkIndex(j, (long) (bArr.length - 3)));
    }

    public ByteIndexer putFloat(long j, float f) {
        Raw raw = RAW;
        if (raw != null) {
            byte[] bArr = this.array;
            raw.putFloat(bArr, checkIndex(j, (long) (bArr.length - 3)), f);
        } else {
            getBuffer().putFloat((int) j, f);
        }
        return this;
    }

    public double getDouble(long j) {
        Raw raw = RAW;
        if (raw == null) {
            return getBuffer().getDouble((int) j);
        }
        byte[] bArr = this.array;
        return raw.getDouble(bArr, checkIndex(j, (long) (bArr.length - 7)));
    }

    public ByteIndexer putDouble(long j, double d) {
        Raw raw = RAW;
        if (raw != null) {
            byte[] bArr = this.array;
            raw.putDouble(bArr, checkIndex(j, (long) (bArr.length - 7)), d);
        } else {
            getBuffer().putDouble((int) j, d);
        }
        return this;
    }

    public char getChar(long j) {
        Raw raw = RAW;
        if (raw == null) {
            return getBuffer().getChar((int) j);
        }
        byte[] bArr = this.array;
        return raw.getChar(bArr, checkIndex(j, (long) (bArr.length - 1)));
    }

    public ByteIndexer putChar(long j, char c) {
        Raw raw = RAW;
        if (raw != null) {
            byte[] bArr = this.array;
            raw.putChar(bArr, checkIndex(j, (long) (bArr.length - 1)), c);
        } else {
            getBuffer().putChar((int) j, c);
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
