package org.bytedeco.javacpp.indexer;

public class Bfloat16ArrayIndexer extends Bfloat16Indexer {
    protected short[] array;

    public /* bridge */ /* synthetic */ Indexer putDouble(long[] jArr, double d) {
        return super.putDouble(jArr, d);
    }

    public Bfloat16ArrayIndexer(short[] sArr) {
        this(sArr, Index.create((long) sArr.length));
    }

    public Bfloat16ArrayIndexer(short[] sArr, long... jArr) {
        this(sArr, Index.create(jArr));
    }

    public Bfloat16ArrayIndexer(short[] sArr, long[] jArr, long[] jArr2) {
        this(sArr, Index.create(jArr, jArr2));
    }

    public Bfloat16ArrayIndexer(short[] sArr, Index index) {
        super(index);
        this.array = sArr;
    }

    public short[] array() {
        return this.array;
    }

    public Bfloat16Indexer reindex(Index index) {
        return new Bfloat16ArrayIndexer(this.array, index);
    }

    public float get(long j) {
        return toFloat(this.array[(int) index(j)]);
    }

    public Bfloat16Indexer get(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.array[((int) index(j)) + i3]);
        }
        return this;
    }

    public float get(long j, long j2) {
        return toFloat(this.array[(int) index(j, j2)]);
    }

    public Bfloat16Indexer get(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.array[((int) index(j, j2)) + i3]);
        }
        return this;
    }

    public float get(long j, long j2, long j3) {
        return toFloat(this.array[(int) index(j, j2, j3)]);
    }

    public float get(long... jArr) {
        return toFloat(this.array[(int) index(jArr)]);
    }

    public Bfloat16Indexer get(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i + i3] = toFloat(this.array[((int) index(jArr)) + i3]);
        }
        return this;
    }

    public Bfloat16Indexer put(long j, float f) {
        this.array[(int) index(j)] = (short) fromFloat(f);
        return this;
    }

    public Bfloat16Indexer put(long j, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j)) + i3] = (short) fromFloat(fArr[i + i3]);
        }
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, float f) {
        this.array[(int) index(j, j2)] = (short) fromFloat(f);
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(j, j2)) + i3] = (short) fromFloat(fArr[i + i3]);
        }
        return this;
    }

    public Bfloat16Indexer put(long j, long j2, long j3, float f) {
        this.array[(int) index(j, j2, j3)] = (short) fromFloat(f);
        return this;
    }

    public Bfloat16Indexer put(long[] jArr, float f) {
        this.array[(int) index(jArr)] = (short) fromFloat(f);
        return this;
    }

    public Bfloat16Indexer put(long[] jArr, float[] fArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.array[((int) index(jArr)) + i3] = (short) fromFloat(fArr[i + i3]);
        }
        return this;
    }

    public void release() {
        this.array = null;
    }
}
