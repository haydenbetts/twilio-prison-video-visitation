package org.bytedeco.javacpp.indexer;

import java.nio.ShortBuffer;
import org.bytedeco.javacpp.ShortPointer;

public abstract class HalfIndexer extends Indexer {
    public static final int VALUE_BYTES = 2;

    public abstract float get(long j);

    public abstract float get(long j, long j2);

    public abstract float get(long j, long j2, long j3);

    public abstract float get(long... jArr);

    public abstract HalfIndexer get(long j, long j2, float[] fArr, int i, int i2);

    public abstract HalfIndexer get(long j, float[] fArr, int i, int i2);

    public abstract HalfIndexer get(long[] jArr, float[] fArr, int i, int i2);

    public abstract HalfIndexer put(long j, float f);

    public abstract HalfIndexer put(long j, long j2, float f);

    public abstract HalfIndexer put(long j, long j2, long j3, float f);

    public abstract HalfIndexer put(long j, long j2, float[] fArr, int i, int i2);

    public abstract HalfIndexer put(long j, float[] fArr, int i, int i2);

    public abstract HalfIndexer put(long[] jArr, float f);

    public abstract HalfIndexer put(long[] jArr, float[] fArr, int i, int i2);

    protected HalfIndexer(Index index) {
        super(index);
    }

    protected HalfIndexer(long[] jArr, long[] jArr2) {
        super(jArr, jArr2);
    }

    public static HalfIndexer create(short[] sArr) {
        return new HalfArrayIndexer(sArr);
    }

    public static HalfIndexer create(ShortBuffer shortBuffer) {
        return new HalfBufferIndexer(shortBuffer);
    }

    public static HalfIndexer create(ShortPointer shortPointer) {
        return new HalfRawIndexer(shortPointer);
    }

    public static HalfIndexer create(short[] sArr, Index index) {
        return new HalfArrayIndexer(sArr, index);
    }

    public static HalfIndexer create(ShortBuffer shortBuffer, Index index) {
        return new HalfBufferIndexer(shortBuffer, index);
    }

    public static HalfIndexer create(ShortPointer shortPointer, Index index) {
        return new HalfRawIndexer(shortPointer, index);
    }

    public static HalfIndexer create(short[] sArr, long... jArr) {
        return new HalfArrayIndexer(sArr, jArr);
    }

    public static HalfIndexer create(ShortBuffer shortBuffer, long... jArr) {
        return new HalfBufferIndexer(shortBuffer, jArr);
    }

    public static HalfIndexer create(ShortPointer shortPointer, long... jArr) {
        return new HalfRawIndexer(shortPointer, jArr);
    }

    public static HalfIndexer create(short[] sArr, long[] jArr, long[] jArr2) {
        return new HalfArrayIndexer(sArr, jArr, jArr2);
    }

    public static HalfIndexer create(ShortBuffer shortBuffer, long[] jArr, long[] jArr2) {
        return new HalfBufferIndexer(shortBuffer, jArr, jArr2);
    }

    public static HalfIndexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2) {
        return new HalfRawIndexer(shortPointer, jArr, jArr2);
    }

    public static HalfIndexer create(ShortPointer shortPointer, long[] jArr, long[] jArr2, boolean z) {
        return create(shortPointer, Index.create(jArr, jArr2), z);
    }

    public static HalfIndexer create(ShortPointer shortPointer, Index index, boolean z) {
        if (!z) {
            final long position = shortPointer.position();
            short[] sArr = new short[((int) Math.min(shortPointer.limit() - position, 2147483647L))];
            shortPointer.get(sArr);
            final ShortPointer shortPointer2 = shortPointer;
            return new HalfArrayIndexer(sArr, index) {
                public void release() {
                    shortPointer2.position(position).put(this.array);
                    super.release();
                }
            };
        } else if (Raw.getInstance() != null) {
            return new HalfRawIndexer(shortPointer, index);
        } else {
            return new HalfBufferIndexer(shortPointer.asBuffer(), index);
        }
    }

    public static float toFloat(int i) {
        int i2 = i & 1023;
        int i3 = i & 31744;
        if (i3 == 31744) {
            i3 = 261120;
        } else if (i3 != 0) {
            i3 += 114688;
        } else if (i2 != 0) {
            i3 = 115712;
            do {
                i2 <<= 1;
                i3 -= 1024;
            } while ((i2 & 1024) == 0);
            i2 &= 1023;
        }
        return Float.intBitsToFloat(((i & 32768) << 16) | ((i2 | i3) << 13));
    }

    public static int fromFloat(float f) {
        int floatToIntBits = Float.floatToIntBits(f);
        int i = (floatToIntBits >>> 16) & 32768;
        int i2 = Integer.MAX_VALUE & floatToIntBits;
        int i3 = i2 + 4096;
        if (i3 >= 1199570944) {
            if (i2 < 1199570944) {
                return i | 31743;
            }
            if (i3 < 2139095040) {
                return i | 31744;
            }
            return ((floatToIntBits & 8388607) >>> 13) | i | 31744;
        } else if (i3 >= 947912704) {
            return ((i3 - 939524096) >>> 13) | i;
        } else {
            if (i3 < 855638016) {
                return i;
            }
            int i4 = i2 >>> 23;
            return ((((floatToIntBits & 8388607) | 8388608) + (8388608 >>> (i4 - 102))) >>> (126 - i4)) | i;
        }
    }

    public HalfIndexer get(long j, float[] fArr) {
        return get(j, fArr, 0, fArr.length);
    }

    public HalfIndexer get(long j, long j2, float[] fArr) {
        return get(j, j2, fArr, 0, fArr.length);
    }

    public HalfIndexer get(long[] jArr, float[] fArr) {
        return get(jArr, fArr, 0, fArr.length);
    }

    public HalfIndexer put(long j, float... fArr) {
        return put(j, fArr, 0, fArr.length);
    }

    public HalfIndexer put(long j, long j2, float... fArr) {
        return put(j, j2, fArr, 0, fArr.length);
    }

    public HalfIndexer put(long[] jArr, float... fArr) {
        return put(jArr, fArr, 0, fArr.length);
    }

    public double getDouble(long... jArr) {
        return (double) get(jArr);
    }

    public HalfIndexer putDouble(long[] jArr, double d) {
        return put(jArr, (float) d);
    }
}
