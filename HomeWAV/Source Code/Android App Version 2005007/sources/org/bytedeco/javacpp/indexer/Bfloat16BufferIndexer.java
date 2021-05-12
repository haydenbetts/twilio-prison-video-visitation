package org.bytedeco.javacpp.indexer;

import java.nio.Buffer;
import java.nio.ShortBuffer;

public class Bfloat16BufferIndexer extends Bfloat16Indexer {
    protected ShortBuffer buffer;

    public Bfloat16BufferIndexer(ShortBuffer shortBuffer) {
        this(shortBuffer, Index.create((long) shortBuffer.limit()));
    }

    public Bfloat16BufferIndexer(ShortBuffer shortBuffer, long... jArr) {
        this(shortBuffer, Index.create(jArr));
    }

    public Bfloat16BufferIndexer(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        this(shortBuffer, Index.create(jArr, jArr2));
    }

    public Bfloat16BufferIndexer(ShortBuffer shortBuffer, Index index) {
        super(index);
        this.buffer = shortBuffer;
    }

    public Buffer buffer() {
        return this.buffer;
    }

    public Bfloat16Indexer reindex(Index index) {
        return new Bfloat16BufferIndexer(this.buffer, index);
    }

    public float get(long j) {
        return toFloat(this.buffer.get((int) index(j)));
    }

    public Bfloat16Indexer get(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.buffer.get(((int) index(j)) + i3));
        }
        return this;
    }

    public float get(long j, long j2) {
        return toFloat(this.buffer.get((int) index(j, j2)));
    }

    public Bfloat16Indexer get(long j, long j2, float[] fArr, int i, int i2) {
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

    public Bfloat16Indexer get(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.buffer.get(((int) index(jArr)) + i3));
        }
        return this;
    }

    public Bfloat16Indexer put(long j, float f) {
        this.buffer.put((int) index(j), (short) fromFloat(f));
        return this;
    }

    public Bfloat16Indexer put(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j)) + i3, (short) fromFloat(fArr[i + i3]));
        }
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, float f) {
        this.buffer.put((int) index(j, j2), (short) fromFloat(f));
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(j, j2)) + i3, (short) fromFloat(fArr[i + i3]));
        }
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, long j3, float f) {
        this.buffer.put((int) index(j, j2, j3), (short) fromFloat(f));
        return this;
    }

    public Bfloat16Indexer put(long[] jArr, float f) {
        this.buffer.put((int) index(jArr), (short) fromFloat(f));
        return this;
    }

    public Bfloat16Indexer put(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.buffer.put(((int) index(jArr)) + i3, (short) fromFloat(fArr[i + i3]));
        }
        return this;
    }

    public void release() {
        this.buffer = null;
    }
}
