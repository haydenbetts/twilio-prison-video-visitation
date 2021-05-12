package org.bytedeco.javacpp.indexer;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;

public abstract class FloatIndexer extends Indexer {
    public static final int VALUE_BYTES = 4;

    public abstract float get(long j);

    public abstract float get(long j, long j2);

    public abstract float get(long j, long j2, long j3);

    public abstract float get(long... jArr);

    public abstract FloatIndexer get(long j, long j2, float[] fArr, int i, int i2);

    public abstract FloatIndexer get(long j, float[] fArr, int i, int i2);

    public abstract FloatIndexer get(long[] jArr, float[] fArr, int i, int i2);

    public abstract FloatIndexer put(long j, float f);

    public abstract FloatIndexer put(long j, long j2, float f);

    public abstract FloatIndexer put(long j, long j2, long j3, float f);

    public abstract FloatIndexer put(long j, long j2, float[] fArr, int i, int i2);

    public abstract FloatIndexer put(long j, float[] fArr, int i, int i2);

    public abstract FloatIndexer put(long[] jArr, float f);

    public abstract FloatIndexer put(long[] jArr, float[] fArr, int i, int i2);

    protected FloatIndexer(Index index) {
        super(index);
    }

    protected FloatIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static FloatIndexer create(float[] fArr) {
        return new FloatArrayIndexer(fArr);
    }

    public static FloatIndexer create(FloatBuffer floatBuffer) {
        return new FloatBufferIndexer(floatBuffer);
    }

    public static FloatIndexer create(FloatPointer floatPointer) {
        return new FloatRawIndexer(floatPointer);
    }

    public static FloatIndexer create(float[] fArr, Index index) {
        return new FloatArrayIndexer(fArr, index);
    }

    public static FloatIndexer create(FloatBuffer floatBuffer, Index index) {
        return new FloatBufferIndexer(floatBuffer, index);
    }

    public static FloatIndexer create(FloatPointer floatPointer, Index index) {
        return new FloatRawIndexer(floatPointer, index);
    }

    public static FloatIndexer create(float[] fArr, long... jArr) {
        return new FloatArrayIndexer(fArr, jArr);
    }

    public static FloatIndexer create(FloatBuffer floatBuffer, long... jArr) {
        return new FloatBufferIndexer(floatBuffer, jArr);
    }

    public static FloatIndexer create(FloatPointer floatPointer, long... jArr) {
        return new FloatRawIndexer(floatPointer, jArr);
    }

    public static FloatIndexer create(float[] fArr, long[] jArr, long[] jArr2) {
        return new FloatArrayIndexer(fArr, jArr, jArr2);
    }

    public static FloatIndexer create(FloatBuffer floatBuffer, long[] jArr, long[] jArr2) {
        return new FloatBufferIndexer(floatBuffer, jArr, jArr2);
    }

    public static FloatIndexer create(FloatPointer floatPointer, long[] jArr, long[] jArr2) {
        return new FloatRawIndexer(floatPointer, jArr, jArr2);
    }

    public static FloatIndexer create(FloatPointer floatPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(floatPointer, Index.create(jArr, jArr2), z);
    }

    public static FloatIndexer create(FloatPointer floatPointer, Index index, boolean z) {
        if (!z) {
            final long position = floatPointer.position();
            float[] fArr = new float[((int) Math.min(floatPointer.limit() - position, 2147483647L))];
            floatPointer.get(fArr);
            final FloatPointer floatPointer2 = floatPointer;
            return new FloatArrayIndexer(fArr, index) {
                public void release() {
                    floatPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new FloatRawIndexer(floatPointer, index);
        } else {
            return new FloatBufferIndexer(floatPointer.asBuffer(), index);
        }
    }

    public FloatIndexer get(long j, float[] fArr) {
        return get(j, fArr, 0, fArr.length);
    }

    public FloatIndexer get(long j, long j2, float[] fArr) {
        return get(j, j2, fArr, 0, fArr.length);
    }

    public FloatIndexer get(long[] jArr, float[] fArr) {
        return get(jArr, fArr, 0, fArr.length);
    }

    public FloatIndexer put(long j, float... fArr) {
        return put(j, fArr, 0, fArr.length);
    }

    public FloatIndexer put(long j, long j2, float... fArr) {
        return put(j, j2, fArr, 0, fArr.length);
    }

    public FloatIndexer put(long[] jArr, float... fArr) {
        return put(jArr, fArr, 0, fArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public FloatIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (float) d);
    }
}
