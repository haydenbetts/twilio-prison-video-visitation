package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.FloatBuffer;

public class FloatBufferIndexer extends FloatIndexer {
    protected FloatBuffer buffer;

    public FloatBufferIndexer(FloatBuffer floatBuffer) {
        this(floatBuffer, Index.create((long) floatBuffer.limit()));
    }

    public FloatBufferIndexer(FloatBuffer floatBuffer, long... jArr) {
        this(floatBuffer, Index.create(jArr));
    }

    public FloatBufferIndexer(FloatBuffer floatBuffer, long[] jArr, long[] jArr2) {
        this(floatBuffer, Index.create(jArr, jArr2));
    }

    public FloatBufferIndexer(FloatBuffer floatBuffer, Index index) {
        super(index);
        this.buffer = floatBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public FloatIndexer reindex(Index index) {
        return new FloatBufferIndexer(this.buffer, index);
    }

    public float get(long j) {
        return this.buffer.get((int) index(j));
    }

    public FloatIndexer get(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = this.buffer.get(((int) index(j)) + i3);
        }
        return this;
    }

    public float get(long j, long j2) {
        return this.buffer.get((int) index(j, j2));
    }

    public FloatIndexer get(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = this.buffer.get(((int) index(j, j2)) + i3);
        }
        return this;
    }

    public float get(long j, long j2, long j3) {
        return this.buffer.get((int) index(j, j2, j3));
    }

    public float get(long... jArr) {
        return this.buffer.get((int) index(jArr));
    }

    public FloatIndexer get(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = this.buffer.get(((int) index(jArr)) + i3);
        }
        return this;
    }

    public FloatIndexer put(long j, float f) {
        this.buffer.put((int) index(j), f);
        return this;
    }

    public FloatIndexer put(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, fArr[i + i3]);
        }
        return this;
    }

    public FloatIndexer put(long j, long j2, float f) {
        this.buffer.put((int) index(j, j2), f);
        return this;
    }

    public FloatIndexer put(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, fArr[i + i3]);
        }
        return this;
    }

    public FloatIndexer put(long j, long j2, long j3, float f) {
        this.buffer.put((int) index(j, j2, j3), f);
        return this;
    }

    public FloatIndexer put(long[] jArr, float f) {
        this.buffer.put((int) index(jArr), f);
        return this;
    }

    public FloatIndexer put(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, fArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
