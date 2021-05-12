package org.bytedeco.javacpp.indexer;

import java.nio.ShortBuffer;
import org.bytedeco.javacpp.ShortPointer;

public abstract class Bfloat16Indexer extends Indexer {
    public static final int VALUE_BYTES = 2;

    public abstract float get(long j);

    public abstract float get(long j, long j2);

    public abstract float get(long j, long j2, long j3);

    public abstract float get(long... jArr);

    public abstract Bfloat16Indexer get(long j, long j2, float[] fArr, int i, int i2);

    public abstract Bfloat16Indexer get(long j, float[] fArr, int i, int i2);

    public abstract Bfloat16Indexer get(long[] jArr, float[] fArr, int i, int i2);

    public abstract Bfloat16Indexer put(long j, float f);

    public abstract Bfloat16Indexer put(long j, long j2, float f);

    public abstract Bfloat16Indexer put(long j, long j2, long j3, float f);

    public abstract Bfloat16Indexer put(long j, long j2, float[] fArr, int i, int i2);

    public abstract Bfloat16Indexer put(long j, float[] fArr, int i, int i2);

    public abstract Bfloat16Indexer put(long[] jArr, float f);

    public abstract Bfloat16Indexer put(long[] jArr, float[] fArr, int i, int i2);

    protected Bfloat16Indexer(Index index) {
        super(index);
    }

    protected Bfloat16Indexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static Bfloat16Indexer create(short[] sArr) {
        return new Bfloat16ArrayIndexer(sArr);
    }

    public static Bfloat16Indexer create(ShortBuffer shortBuffer) {
        return new Bfloat16BufferIndexer(shortBuffer);
    }

    public static Bfloat16Indexer create(ShortPointer shortPointer) {
        return new Bfloat16RawIndexer(shortPointer);
    }

    public static Bfloat16Indexer create(short[] sArr, Index index) {
        return new Bfloat16ArrayIndexer(sArr, index);
    }

    public static Bfloat16Indexer create(ShortBuffer shortBuffer, Index index) {
        return new Bfloat16BufferIndexer(shortBuffer, index);
    }

    public static Bfloat16Indexer create(ShortPointer shortPointer, Index index) {
        return new Bfloat16RawIndexer(shortPointer, index);
    }

    public static Bfloat16Indexer create(short[] sArr, long... jArr) {
        return new Bfloat16ArrayIndexer(sArr, jArr);
    }

    public static Bfloat16Indexer create(ShortBuffer shortBuffer, long... jArr) {
        return new Bfloat16BufferIndexer(shortBuffer, jArr);
    }

    public static Bfloat16Indexer create(ShortPointer shortPointer, long... jArr) {
        return new Bfloat16RawIndexer(shortPointer, jArr);
    }

    public static Bfloat16Indexer create(short[] sArr, long[] jArr, long[] jArr2) {
        return new Bfloat16ArrayIndexer(sArr, jArr, jArr2);
    }

    public static Bfloat16Indexer create(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        return new Bfloat16BufferIndexer(shortBuffer, jArr, jArr2);
    }

    public static Bfloat16Indexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2) {
        return new Bfloat16RawIndexer(shortPointer, jArr, jArr2);
    }

    public static Bfloat16Indexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(shortPointer, Index.create(jArr, jArr2), z);
    }

    public static Bfloat16Indexer create(ShortPointer shortPointer, Index index, boolean z) {
        if (!z) {
            final long position = shortPointer.position();
            short[] sArr = new short[((int) Math.min(shortPointer.limit() - position, 2147483647L))];
            shortPointer.get(sArr);
            final ShortPointer shortPointer2 = shortPointer;
            return new Bfloat16ArrayIndexer(sArr, index) {
                public void release() {
                    shortPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new Bfloat16RawIndexer(shortPointer, index);
        } else {
            return new Bfloat16BufferIndexer(shortPointer.asBuffer(), index);
        }
    }

    public static float toFloat(int i) {
        return Float.intBitsToFloat(i << 16);
    }

    public static int fromFloat(float f) {
        return Float.floatToIntBits(f) >>> 16;
    }

    public Bfloat16Indexer get(long j, float[] fArr) {
        return get(j, fArr, 0, fArr.length);
    }

    public Bfloat16Indexer get(long j, long j2, float[] fArr) {
        return get(j, j2, fArr, 0, fArr.length);
    }

    public Bfloat16Indexer get(long[] jArr, float[] fArr) {
        return get(jArr, fArr, 0, fArr.length);
    }

    public Bfloat16Indexer put(long j, float... fArr) {
        return put(j, fArr, 0, fArr.length);
    }

    public Bfloat16Indexer put(long j, long j2, float... fArr) {
        return put(j, j2, fArr, 0, fArr.length);
    }

    public Bfloat16Indexer put(long[] jArr, float... fArr) {
        return put(jArr, fArr, 0, fArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public Bfloat16Indexer putDouble(long[] jArr, double d) {
        return put(jArr, (float) d);
    }
}
