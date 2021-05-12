package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.indexer.ByteIndexer;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.IntIndexer;
import org.bytedeco.javacpp.indexer.ShortIndexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.javacpp.indexer.UShortIndexer;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractMat extends AbstractArray {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Mat EMPTY = null;

    public int arrayOrigin() {
        return 0;
    }

    public void arrayOrigin(int i) {
    }

    public IplROI arrayROI() {
        return null;
    }

    public abstract int channels();

    public abstract int cols();

    public abstract void create(int i, int i2, int i3);

    public abstract BytePointer data();

    public abstract int depth();

    public abstract int dims();

    public abstract long elemSize1();

    public abstract void release();

    public abstract int rows();

    public abstract int size(int i);

    public abstract long step(int i);

    public abstract int type();

    public AbstractMat(Pointer pointer) {
        super(pointer);
    }

    public int arrayChannels() {
        return channels();
    }

    public int arrayDepth() {
        switch (depth()) {
            case 0:
                return 8;
            case 1:
                return org.bytedeco.opencv.global.opencv_core.IPL_DEPTH_8S;
            case 2:
                return 16;
            case 3:
                return org.bytedeco.opencv.global.opencv_core.IPL_DEPTH_16S;
            case 4:
                return org.bytedeco.opencv.global.opencv_core.IPL_DEPTH_32S;
            case 5:
                return 32;
            case 6:
                return 64;
            default:
                return -1;
        }
    }

    public int arrayWidth() {
        return cols();
    }

    public int arrayHeight() {
        return rows();
    }

    public long arraySize() {
        return step(0) * ((long) size(0));
    }

    public BytePointer arrayData() {
        return data();
    }

    public long arrayStep() {
        return step(0);
    }

    public <I extends Indexer> I createIndexer(boolean z) {
        boolean z2 = z;
        BytePointer arrayData = arrayData();
        long arraySize = arraySize();
        int dims = dims();
        int depth = depth();
        long elemSize1 = elemSize1();
        int i = dims + 1;
        long[] jArr = new long[i];
        long[] jArr2 = new long[i];
        int i2 = 0;
        while (i2 < dims) {
            jArr[i2] = (long) size(i2);
            long step = step(i2);
            if (step % elemSize1 == 0) {
                jArr2[i2] = step / elemSize1;
                i2++;
            } else {
                throw new UnsupportedOperationException("Step is not a multiple of element size");
            }
        }
        jArr[dims] = (long) arrayChannels();
        jArr2[dims] = 1;
        switch (depth) {
            case 0:
                return UByteIndexer.create(arrayData.capacity(arraySize), jArr, jArr2, z2).indexable(this);
            case 1:
                return ByteIndexer.create(arrayData.capacity(arraySize), jArr, jArr2, z2).indexable(this);
            case 2:
                return UShortIndexer.create(new ShortPointer((Pointer) arrayData).capacity(arraySize / 2), jArr, jArr2, z2).indexable(this);
            case 3:
                return ShortIndexer.create(new ShortPointer((Pointer) arrayData).capacity(arraySize / 2), jArr, jArr2, z2).indexable(this);
            case 4:
                return IntIndexer.create(new IntPointer((Pointer) arrayData).capacity(arraySize / 4), jArr, jArr2, z2).indexable(this);
            case 5:
                return FloatIndexer.create(new FloatPointer((Pointer) arrayData).capacity(arraySize / 4), jArr, jArr2, z2).indexable(this);
            case 6:
                return DoubleIndexer.create(new DoublePointer((Pointer) arrayData).capacity(arraySize / 8), jArr, jArr2, z2).indexable(this);
            default:
                return null;
        }
    }
}
