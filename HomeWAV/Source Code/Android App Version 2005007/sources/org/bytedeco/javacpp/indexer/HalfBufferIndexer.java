package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;

public class HalfBufferIndexer extends HalfIndexer {
    protected ShortBuffer buffer;

    public HalfBufferIndexer(ShortBuffer shortBuffer) {
        this(shortBuffer, Index.create((long) shortBuffer.limit()));
    }

    public HalfBufferIndexer(ShortBuffer shortBuffer, long... jArr) {
        this(shortBuffer, Index.create(jArr));
    }

    public HalfBufferIndexer(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        this(shortBuffer, Index.create(jArr, jArr2));
    }

    public HalfBufferIndexer(ShortBuffer shortBuffer, Index index) {
        super(index);
        this.buffer = shortBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public HalfIndexer reindex(Index index) {
        return new HalfBufferIndexer(this.buffer, index);
    }

    public float get(long j) {
        return toFloat(this.buffer.get((int) index(j)));
    }

    public HalfIndexer get(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.buffer.get(((int) index(j)) + i3));
        }
        return this;
    }

    public float get(long j, long j2) {
        return toFloat(this.buffer.get((int) index(j, j2)));
    }

    public HalfIndexer get(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.buffer.get(((int) index(j, j2)) + i3));
        }
        return this;
    }

    public float get(long j, long j2, long j3) {
        return toFloat(this.buffer.get((int) index(j, j2, j3)));
    }

    public float get(long... jArr) {
        return toFloat(this.buffer.get((int) index(jArr)));
    }

    public HalfIndexer get(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.buffer.get(((int) index(jArr)) + i3));
        }
        return this;
    }

    public HalfIndexer put(long j, float f) {
        this.buffer.put((int) index(j), (short) fromFloat(f));
        return this;
    }

    public HalfIndexer put(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, (short) fromFloat(fArr[i + i3]));
        }
        return this;
    }

    public HalfIndexer put(long j, long j2, float f) {
        this.buffer.put((int) index(j, j2), (short) fromFloat(f));
        return this;
    }

    public HalfIndexer put(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, (short) fromFloat(fArr[i + i3]));
        }
        return this;
    }

    public HalfIndexer put(long j, long j2, long j3, float f) {
        this.buffer.put((int) index(j, j2, j3), (short) fromFloat(f));
        return this;
    }

    public HalfIndexer put(long[] jArr, float f) {
        this.buffer.put((int) index(jArr), (short) fromFloat(f));
        return this;
    }

    public HalfIndexer put(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, (short) fromFloat(fArr[i + i3]));
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
