package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import kotlin.UByte;
import kotlin.UShort;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public abstract class AbstractCvMat extends CvArr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ByteBuffer byteBuffer = null;
    private DoubleBuffer doubleBuffer = null;
    private FloatBuffer floatBuffer = null;
    private int fullSize = 0;
    private IntBuffer intBuffer = null;
    protected BytePointer pointer;
    private ShortBuffer shortBuffer = null;

    public int arrayOrigin() {
        return 0;
    }

    public void arrayOrigin(int i) {
    }

    public IplROI arrayROI() {
        return null;
    }

    public abstract int cols();

    public abstract DoublePointer data_db();

    public abstract FloatPointer data_fl();

    public abstract IntPointer data_i();

    public abstract BytePointer data_ptr();

    public abstract ShortPointer data_s();

    public abstract int rows();

    public abstract int step();

    public abstract int type();

    public abstract CvMat type(int i);

    public AbstractCvMat(Pointer pointer2) {
        super(pointer2);
    }

    public static CvMat create(int i, int i2, int i3) {
        CvMat cvCreateMat = org.bytedeco.opencv.global.opencv_core.cvCreateMat(i, i2, i3);
        if (cvCreateMat != null) {
            cvCreateMat.fullSize = cvCreateMat.size();
            cvCreateMat.deallocator(new ReleaseDeallocator(cvCreateMat));
        }
        return cvCreateMat;
    }

    public static CvMat create(int i, int i2, int i3, int i4) {
        return create(i, i2, org.bytedeco.opencv.global.opencv_core.CV_MAKETYPE(i3, i4));
    }

    public static CvMat create(int i, int i2) {
        return create(i, i2, 6, 1);
    }

    public static CvMat createHeader(int i, int i2, int i3) {
        CvMat cvCreateMatHeader = org.bytedeco.opencv.global.opencv_core.cvCreateMatHeader(i, i2, i3);
        if (cvCreateMatHeader != null) {
            cvCreateMatHeader.fullSize = cvCreateMatHeader.size();
            cvCreateMatHeader.deallocator(new ReleaseDeallocator(cvCreateMatHeader));
        }
        return cvCreateMatHeader;
    }

    public static CvMat createHeader(int i, int i2, int i3, int i4) {
        return createHeader(i, i2, org.bytedeco.opencv.global.opencv_core.CV_MAKETYPE(i3, i4));
    }

    public static CvMat createHeader(int i, int i2) {
        return createHeader(i, i2, 6, 1);
    }

    public static CvMat create(int i, int i2, int i3, int i4, Pointer pointer2) {
        CvMat createHeader = createHeader(i, i2, i3, i4);
        BytePointer bytePointer = new BytePointer(pointer2);
        createHeader.pointer = bytePointer;
        createHeader.data_ptr(bytePointer);
        return createHeader;
    }

    public static ThreadLocal<CvMat> createThreadLocal(final int i, final int i2, final int i3) {
        return new ThreadLocal<CvMat>() {
            /* access modifiers changed from: protected */
            public CvMat initialValue() {
                return AbstractCvMat.create(i, i2, i3);
            }
        };
    }

    public static ThreadLocal<CvMat> createThreadLocal(int i, int i2, int i3, int i4) {
        return createThreadLocal(i, i2, org.bytedeco.opencv.global.opencv_core.CV_MAKETYPE(i3, i4));
    }

    public static ThreadLocal<CvMat> createThreadLocal(int i, int i2) {
        return createThreadLocal(i, i2, 6, 1);
    }

    public static ThreadLocal<CvMat> createHeaderThreadLocal(final int i, final int i2, final int i3) {
        return new ThreadLocal<CvMat>() {
            /* access modifiers changed from: protected */
            public CvMat initialValue() {
                return AbstractCvMat.createHeader(i, i2, i3);
            }
        };
    }

    public static ThreadLocal<CvMat> createHeaderThreadLocal(int i, int i2, int i3, int i4) {
        return createHeaderThreadLocal(i, i2, org.bytedeco.opencv.global.opencv_core.CV_MAKETYPE(i3, i4));
    }

    public static ThreadLocal<CvMat> createHeaderThreadLocal(int i, int i2) {
        return createHeaderThreadLocal(i, i2, 6, 1);
    }

    public CvMat clone() {
        CvMat cvCloneMat = org.bytedeco.opencv.global.opencv_core.cvCloneMat((CvMat) this);
        if (cvCloneMat != null) {
            cvCloneMat.deallocator(new ReleaseDeallocator(cvCloneMat));
        }
        return cvCloneMat;
    }

    public void release() {
        deallocate();
    }

    protected static class ReleaseDeallocator extends CvMat implements Pointer.Deallocator {
        ReleaseDeallocator(CvMat cvMat) {
            super((Pointer) cvMat);
        }

        public void deallocate() {
            if (!isNull()) {
                org.bytedeco.opencv.global.opencv_core.cvReleaseMat((CvMat) this);
                setNull();
            }
        }
    }

    public int matType() {
        return org.bytedeco.opencv.global.opencv_core.CV_MAT_TYPE(type());
    }

    public void type(int i, int i2) {
        type(org.bytedeco.opencv.global.opencv_core.CV_MAKETYPE(i, i2) | org.bytedeco.opencv.global.opencv_core.CV_MAT_MAGIC_VAL);
    }

    public int depth() {
        return org.bytedeco.opencv.global.opencv_core.CV_MAT_DEPTH(type());
    }

    public int channels() {
        return org.bytedeco.opencv.global.opencv_core.CV_MAT_CN(type());
    }

    public int nChannels() {
        return org.bytedeco.opencv.global.opencv_core.CV_MAT_CN(type());
    }

    public boolean isContinuous() {
        return org.bytedeco.opencv.global.opencv_core.CV_IS_MAT_CONT(type()) != 0;
    }

    public int elemSize() {
        switch (depth()) {
            case 0:
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            case 4:
            case 5:
                return 4;
            case 6:
                return 8;
            default:
                return 0;
        }
    }

    public int length() {
        return rows() * cols();
    }

    public int total() {
        return rows() * cols();
    }

    public boolean empty() {
        return length() == 0;
    }

    public int size() {
        int rows = rows();
        return (cols() * elemSize() * channels()) + (rows > 1 ? step() * (rows - 1) : 0);
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
        return (long) size();
    }

    public BytePointer arrayData() {
        return data_ptr();
    }

    public long arrayStep() {
        return (long) step();
    }

    @Deprecated
    public void reset() {
        this.fullSize = 0;
        this.byteBuffer = null;
        this.shortBuffer = null;
        this.intBuffer = null;
        this.floatBuffer = null;
        this.doubleBuffer = null;
    }

    private int fullSize() {
        int i = this.fullSize;
        if (i > 0) {
            return i;
        }
        int size = size();
        this.fullSize = size;
        return size;
    }

    @Deprecated
    public ByteBuffer getByteBuffer() {
        if (this.byteBuffer == null) {
            this.byteBuffer = data_ptr().capacity((long) fullSize()).asBuffer();
        }
        this.byteBuffer.position(0);
        return this.byteBuffer;
    }

    @Deprecated
    public ShortBuffer getShortBuffer() {
        if (this.shortBuffer == null) {
            this.shortBuffer = data_s().capacity((long) (fullSize() / 2)).asBuffer();
        }
        this.shortBuffer.position(0);
        return this.shortBuffer;
    }

    @Deprecated
    public IntBuffer getIntBuffer() {
        if (this.intBuffer == null) {
            this.intBuffer = data_i().capacity((long) (fullSize() / 4)).asBuffer();
        }
        this.intBuffer.position(0);
        return this.intBuffer;
    }

    @Deprecated
    public FloatBuffer getFloatBuffer() {
        if (this.floatBuffer == null) {
            this.floatBuffer = data_fl().capacity((long) (fullSize() / 4)).asBuffer();
        }
        this.floatBuffer.position(0);
        return this.floatBuffer;
    }

    @Deprecated
    public DoubleBuffer getDoubleBuffer() {
        if (this.doubleBuffer == null) {
            this.doubleBuffer = data_db().capacity((long) (fullSize() / 8)).asBuffer();
        }
        this.doubleBuffer.position(0);
        return this.doubleBuffer;
    }

    @Deprecated
    public double get(int i) {
        switch (depth()) {
            case 0:
                return (double) (getByteBuffer().get(i) & UByte.MAX_VALUE);
            case 1:
                return (double) getByteBuffer().get(i);
            case 2:
                return (double) (getShortBuffer().get(i) & UShort.MAX_VALUE);
            case 3:
                return (double) getShortBuffer().get(i);
            case 4:
                return (double) getIntBuffer().get(i);
            case 5:
                return (double) getFloatBuffer().get(i);
            case 6:
                return getDoubleBuffer().get(i);
            default:
                return Double.NaN;
        }
    }

    @Deprecated
    public double get(int i, int i2) {
        return get(((i * step()) / elemSize()) + (i2 * channels()));
    }

    @Deprecated
    public double get(int i, int i2, int i3) {
        return get(((i * step()) / elemSize()) + (i2 * channels()) + i3);
    }

    @Deprecated
    public synchronized CvMat get(int i, double[] dArr, int i2, int i3) {
        int depth = depth();
        int i4 = 0;
        switch (depth) {
            case 0:
            case 1:
                ByteBuffer byteBuffer2 = getByteBuffer();
                byteBuffer2.position(i);
                while (i4 < i3) {
                    if (depth == 0) {
                        dArr[i4 + i2] = (double) (byteBuffer2.get(i4) & UByte.MAX_VALUE);
                    } else {
                        dArr[i4 + i2] = (double) byteBuffer2.get(i4);
                    }
                    i4++;
                }
                break;
            case 2:
            case 3:
                ShortBuffer shortBuffer2 = getShortBuffer();
                shortBuffer2.position(i);
                while (i4 < i3) {
                    if (depth == 2) {
                        dArr[i4 + i2] = (double) (shortBuffer2.get() & UShort.MAX_VALUE);
                    } else {
                        dArr[i4 + i2] = (double) shortBuffer2.get();
                    }
                    i4++;
                }
                break;
            case 4:
                IntBuffer intBuffer2 = getIntBuffer();
                intBuffer2.position(i);
                while (i4 < i3) {
                    dArr[i4 + i2] = (double) intBuffer2.get();
                    i4++;
                }
                break;
            case 5:
                FloatBuffer floatBuffer2 = getFloatBuffer();
                floatBuffer2.position(i);
                while (i4 < i3) {
                    dArr[i4 + i2] = (double) floatBuffer2.get();
                    i4++;
                }
                break;
            case 6:
                getDoubleBuffer().position(i);
                getDoubleBuffer().get(dArr, i2, i3);
                break;
        }
        return (CvMat) this;
    }

    @Deprecated
    public CvMat get(int i, double[] dArr) {
        return get(i, dArr, 0, dArr.length);
    }

    @Deprecated
    public CvMat get(double[] dArr) {
        return get(0, dArr);
    }

    @Deprecated
    public double[] get() {
        double[] dArr = new double[(fullSize() / elemSize())];
        get(dArr);
        return dArr;
    }

    @Deprecated
    public CvMat put(int i, double d) {
        switch (depth()) {
            case 0:
            case 1:
                getByteBuffer().put(i, (byte) ((int) d));
                break;
            case 2:
            case 3:
                getShortBuffer().put(i, (short) ((int) d));
                break;
            case 4:
                getIntBuffer().put(i, (int) d);
                break;
            case 5:
                getFloatBuffer().put(i, (float) d);
                break;
            case 6:
                getDoubleBuffer().put(i, d);
                break;
        }
        return (CvMat) this;
    }

    @Deprecated
    public CvMat put(int i, int i2, double d) {
        return put(((i * step()) / elemSize()) + (i2 * channels()), d);
    }

    @Deprecated
    public CvMat put(int i, int i2, int i3, double d) {
        return put(((i * step()) / elemSize()) + (i2 * channels()) + i3, d);
    }

    @Deprecated
    public synchronized CvMat put(int i, double[] dArr, int i2, int i3) {
        int i4 = 0;
        switch (depth()) {
            case 0:
            case 1:
                ByteBuffer byteBuffer2 = getByteBuffer();
                byteBuffer2.position(i);
                while (i4 < i3) {
                    byteBuffer2.put((byte) ((int) dArr[i4 + i2]));
                    i4++;
                }
                break;
            case 2:
            case 3:
                ShortBuffer shortBuffer2 = getShortBuffer();
                shortBuffer2.position(i);
                while (i4 < i3) {
                    shortBuffer2.put((short) ((int) dArr[i4 + i2]));
                    i4++;
                }
                break;
            case 4:
                IntBuffer intBuffer2 = getIntBuffer();
                intBuffer2.position(i);
                while (i4 < i3) {
                    intBuffer2.put((int) dArr[i4 + i2]);
                    i4++;
                }
                break;
            case 5:
                FloatBuffer floatBuffer2 = getFloatBuffer();
                floatBuffer2.position(i);
                while (i4 < i3) {
                    floatBuffer2.put((float) dArr[i4 + i2]);
                    i4++;
                }
                break;
            case 6:
                DoubleBuffer doubleBuffer2 = getDoubleBuffer();
                doubleBuffer2.position(i);
                doubleBuffer2.put(dArr, i2, i3);
                break;
        }
        return (CvMat) this;
    }

    @Deprecated
    public CvMat put(int i, double... dArr) {
        return put(i, dArr, 0, dArr.length);
    }

    @Deprecated
    public CvMat put(double... dArr) {
        return put(0, dArr);
    }

    public CvMat put(CvMat cvMat) {
        return put(0, 0, 0, cvMat, 0, 0, 0);
    }

    public synchronized CvMat put(int i, int i2, int i3, CvMat cvMat, int i4, int i5, int i6) {
        if (rows() == cvMat.rows() && cols() == cvMat.cols() && step() == cvMat.step() && type() == cvMat.type() && i == 0 && i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0 && i6 == 0) {
            getByteBuffer().clear();
            cvMat.getByteBuffer().clear();
            getByteBuffer().put(cvMat.getByteBuffer());
        } else {
            int min = Math.min(rows() - i, cvMat.rows() - i4);
            int min2 = Math.min(cols() - i2, cvMat.cols() - i5);
            int min3 = Math.min(channels() - i3, cvMat.channels() - i6);
            for (int i7 = 0; i7 < min; i7++) {
                for (int i8 = 0; i8 < min2; i8++) {
                    for (int i9 = 0; i9 < min3; i9++) {
                        put(i7 + i, i8 + i2, i9 + i3, cvMat.get(i7 + i4, i8 + i5, i9 + i6));
                    }
                    CvMat cvMat2 = cvMat;
                }
                CvMat cvMat3 = cvMat;
            }
        }
        return (CvMat) this;
    }

    public IplImage asIplImage() {
        IplImage iplImage = new IplImage();
        org.bytedeco.opencv.global.opencv_core.cvGetImage(this, iplImage);
        return iplImage;
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int i) {
        StringBuilder sb = new StringBuilder("[ ");
        int channels = channels();
        for (int i2 = 0; i2 < rows(); i2++) {
            for (int i3 = 0; i3 < cols(); i3++) {
                CvScalar cvGet2D = org.bytedeco.opencv.global.opencv_core.cvGet2D(this, i2, i3);
                if (channels > 1) {
                    sb.append("(");
                }
                for (int i4 = 0; i4 < channels; i4++) {
                    sb.append((float) cvGet2D.val(i4));
                    if (i4 < channels - 1) {
                        sb.append(", ");
                    }
                }
                if (channels > 1) {
                    sb.append(")");
                }
                if (i3 < cols() - 1) {
                    sb.append(", ");
                }
            }
            if (i2 < rows() - 1) {
                sb.append("\n  ");
                for (int i5 = 0; i5 < i; i5++) {
                    sb.append(' ');
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}
