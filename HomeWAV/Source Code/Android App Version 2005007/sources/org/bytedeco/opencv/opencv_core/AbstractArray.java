package org.bytedeco.opencv.opencv_core;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.indexer.ByteIndexer;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Indexable;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.IntIndexer;
import org.bytedeco.javacpp.indexer.ShortIndexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacpp.indexer.UShortIndexer;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractArray extends Pointer implements Indexable {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public abstract int arrayChannels();

    public abstract BytePointer arrayData();

    public abstract int arrayDepth();

    public abstract int arrayHeight();

    public abstract int arrayOrigin();

    public abstract void arrayOrigin(int i);

    public abstract IplROI arrayROI();

    public abstract long arraySize();

    public abstract long arrayStep();

    public abstract int arrayWidth();

    static {
        Loader.load();
    }

    public AbstractArray(Pointer pointer) {
        super(pointer);
    }

    public <B extends Buffer> B createBuffer() {
        return createBuffer(0);
    }

    public <B extends Buffer> B createBuffer(int i) {
        BytePointer arrayData = arrayData();
        long arraySize = arraySize();
        int arrayDepth = arrayDepth();
        if (arrayDepth != -2147483640) {
            if (arrayDepth != -2147483632) {
                if (arrayDepth == -2147483616) {
                    return new IntPointer((Pointer) arrayData).position((long) i).capacity(arraySize / 4).asBuffer();
                }
                if (arrayDepth != 8) {
                    if (arrayDepth != 16) {
                        if (arrayDepth == 32) {
                            return new FloatPointer((Pointer) arrayData).position((long) i).capacity(arraySize / 4).asBuffer();
                        }
                        if (arrayDepth != 64) {
                            return null;
                        }
                        return new DoublePointer((Pointer) arrayData).position((long) i).capacity(arraySize / 8).asBuffer();
                    }
                }
            }
            return new ShortPointer((Pointer) arrayData).position((long) i).capacity(arraySize / 2).asBuffer();
        }
        return arrayData.position((long) i).capacity(arraySize).asBuffer();
    }

    public <I extends Indexer> I createIndexer() {
        return createIndexer(true);
    }

    public <I extends Indexer> I createIndexer(boolean z) {
        BytePointer arrayData = arrayData();
        long arraySize = arraySize();
        long[] jArr = {(long) arrayHeight(), (long) arrayWidth(), (long) arrayChannels()};
        long[] jArr2 = {arrayStep(), (long) arrayChannels(), 1};
        int arrayDepth = arrayDepth();
        if (arrayDepth == -2147483640) {
            return ByteIndexer.create(arrayData.capacity(arraySize), jArr, jArr2, z).indexable(this);
        }
        if (arrayDepth == -2147483632) {
            jArr2[0] = jArr2[0] / 2;
            return ShortIndexer.create(new ShortPointer((Pointer) arrayData).capacity(arraySize / 2), jArr, jArr2, z).indexable(this);
        } else if (arrayDepth == -2147483616) {
            jArr2[0] = jArr2[0] / 4;
            return IntIndexer.create(new IntPointer((Pointer) arrayData).capacity(arraySize / 4), jArr, jArr2, z).indexable(this);
        } else if (arrayDepth == 8) {
            return UByteIndexer.create(arrayData.capacity(arraySize), jArr, jArr2, z).indexable(this);
        } else {
            if (arrayDepth == 16) {
                jArr2[0] = jArr2[0] / 2;
                return UShortIndexer.create(new ShortPointer((Pointer) arrayData).capacity(arraySize / 2), jArr, jArr2, z).indexable(this);
            } else if (arrayDepth == 32) {
                jArr2[0] = jArr2[0] / 4;
                return FloatIndexer.create(new FloatPointer((Pointer) arrayData).capacity(arraySize / 4), jArr, jArr2, z).indexable(this);
            } else if (arrayDepth != 64) {
                return null;
            } else {
                jArr2[0] = jArr2[0] / 8;
                return DoubleIndexer.create(new DoublePointer((Pointer) arrayData).capacity(arraySize / 8), jArr, jArr2, z).indexable(this);
            }
        }
    }

    public double highValue() {
        int arrayDepth = arrayDepth();
        if (arrayDepth == -2147483640) {
            return 127.0d;
        }
        if (arrayDepth == -2147483632) {
            return 32767.0d;
        }
        if (arrayDepth == -2147483616) {
            return 2.147483647E9d;
        }
        if (arrayDepth != 1) {
            if (arrayDepth == 8) {
                return 255.0d;
            }
            if (arrayDepth != 16) {
                return (arrayDepth == 32 || arrayDepth == 64) ? 1.0d : 0.0d;
            }
            return 65535.0d;
        }
    }

    public CvSize cvSize() {
        return org.bytedeco.opencv.global.opencv_core.cvSize(arrayWidth(), arrayHeight());
    }

    @Deprecated
    public ByteBuffer getByteBuffer(int i) {
        return arrayData().position((long) i).capacity(arraySize()).asByteBuffer();
    }

    @Deprecated
    public ShortBuffer getShortBuffer(int i) {
        return getByteBuffer(i * 2).asShortBuffer();
    }

    @Deprecated
    public IntBuffer getIntBuffer(int i) {
        return getByteBuffer(i * 4).asIntBuffer();
    }

    @Deprecated
    public FloatBuffer getFloatBuffer(int i) {
        return getByteBuffer(i * 4).asFloatBuffer();
    }

    @Deprecated
    public DoubleBuffer getDoubleBuffer(int i) {
        return getByteBuffer(i * 8).asDoubleBuffer();
    }

    @Deprecated
    public ByteBuffer getByteBuffer() {
        return getByteBuffer(0);
    }

    @Deprecated
    public ShortBuffer getShortBuffer() {
        return getShortBuffer(0);
    }

    @Deprecated
    public IntBuffer getIntBuffer() {
        return getIntBuffer(0);
    }

    @Deprecated
    public FloatBuffer getFloatBuffer() {
        return getFloatBuffer(0);
    }

    @Deprecated
    public DoubleBuffer getDoubleBuffer() {
        return getDoubleBuffer(0);
    }

    public String toString() {
        if (isNull()) {
            return super.toString();
        }
        try {
            return getClass().getName() + "[width=" + arrayWidth() + ",height=" + arrayHeight() + ",depth=" + arrayDepth() + ",channels=" + arrayChannels() + "]";
        } catch (Exception unused) {
            return super.toString();
        }
    }
}
