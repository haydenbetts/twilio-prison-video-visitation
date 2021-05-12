package org.bytedeco.opencv.opencv_core;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class Mat extends AbstractMat {
    public static final int AUTO_STEP = 0;
    public static final int CONTINUOUS_FLAG = 16384;
    public static final int DEPTH_MASK = 7;
    public static final int MAGIC_MASK = -65536;
    public static final int MAGIC_VAL = 1124007936;
    public static final int SUBMATRIX_FLAG = 32768;
    public static final int TYPE_MASK = 4095;
    private Pointer pointer;

    private native void allocate();

    private native void allocate(int i, int i2, int i3);

    private native void allocate(int i, int i2, int i3, Pointer pointer2, @Cast({"size_t"}) long j);

    private native void allocate(int i, int i2, int i3, @ByRef @Const Scalar scalar);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2, Pointer pointer2);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2, @ByRef @Const Scalar scalar);

    private native void allocate(int i, @Const IntPointer intPointer, int i2);

    private native void allocate(int i, @Const IntPointer intPointer, int i2, Pointer pointer2);

    private native void allocate(int i, @Const IntPointer intPointer, int i2, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer);

    private native void allocate(int i, @Const IntPointer intPointer, int i2, @ByRef @Const Scalar scalar);

    private native void allocate(int i, @Const int[] iArr, int i2);

    private native void allocate(int i, @Const int[] iArr, int i2, Pointer pointer2);

    private native void allocate(int i, @Const int[] iArr, int i2, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer);

    private native void allocate(int i, @Const int[] iArr, int i2, @ByRef @Const Scalar scalar);

    private native void allocate(@StdVector IntBuffer intBuffer, int i);

    private native void allocate(@StdVector IntBuffer intBuffer, int i, Pointer pointer2);

    private native void allocate(@StdVector IntBuffer intBuffer, int i, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer);

    private native void allocate(@StdVector IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar);

    private native void allocate(@StdVector IntPointer intPointer, int i);

    private native void allocate(@StdVector IntPointer intPointer, int i, Pointer pointer2);

    private native void allocate(@StdVector IntPointer intPointer, int i, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer);

    private native void allocate(@StdVector IntPointer intPointer, int i, @ByRef @Const Scalar scalar);

    private native void allocate(@ByRef @Const GpuMat gpuMat);

    private native void allocate(@ByRef @Const Mat mat);

    private native void allocate(@ByRef @Const Mat mat, @ByRef @Const Range range);

    private native void allocate(@ByRef @Const Mat mat, @ByRef @Const Range range, @ByRef(nullValue = "cv::Range::all()") @Const Range range2);

    private native void allocate(@ByRef @Const Mat mat, @ByRef @Const Rect rect);

    private native void allocate(@ByVal Size size, int i);

    private native void allocate(@ByVal Size size, int i, Pointer pointer2);

    private native void allocate(@ByVal Size size, int i, Pointer pointer2, @Cast({"size_t"}) long j);

    private native void allocate(@ByVal Size size, int i, @ByRef @Const Scalar scalar);

    private native void allocate(@StdVector int[] iArr, int i);

    private native void allocate(@StdVector int[] iArr, int i, Pointer pointer2);

    private native void allocate(@StdVector int[] iArr, int i, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer);

    private native void allocate(@StdVector int[] iArr, int i, @ByRef @Const Scalar scalar);

    private native void allocateArray(long j);

    @ByVal
    public static native Mat diag(@ByRef @Const Mat mat);

    @ByVal
    public static native MatExpr eye(int i, int i2, int i3);

    @ByVal
    public static native MatExpr eye(@ByVal Size size, int i);

    public static native MatAllocator getDefaultAllocator();

    public static native MatAllocator getStdAllocator();

    @ByVal
    public static native MatExpr ones(int i, int i2, int i3);

    @ByVal
    public static native MatExpr ones(@ByVal Size size, int i);

    public static native void setDefaultAllocator(MatAllocator matAllocator);

    @ByVal
    public static native MatExpr zeros(int i, int i2, int i3);

    @ByVal
    public static native MatExpr zeros(@ByVal Size size, int i);

    @Name({"deallocate"})
    public native void _deallocate();

    public native void addref();

    @ByRef
    public native Mat adjustROI(int i, int i2, int i3, int i4);

    public native Mat allocator(MatAllocator matAllocator);

    public native MatAllocator allocator();

    @ByVal
    @Name({"operator ()"})
    public native Mat apply(@Const Range range);

    @ByVal
    @Name({"operator ()"})
    public native Mat apply(@ByVal Range range, @ByVal Range range2);

    @ByVal
    @Name({"operator ()"})
    public native Mat apply(@ByRef @Const Rect rect);

    public native void assignTo(@ByRef Mat mat);

    public native void assignTo(@ByRef Mat mat, int i);

    public native int channels();

    public native int checkVector(int i);

    public native int checkVector(int i, int i2, @Cast({"bool"}) boolean z);

    @ByVal
    public native Mat clone();

    @ByVal
    public native Mat col(int i);

    @ByVal
    public native Mat colRange(int i, int i2);

    @ByVal
    public native Mat colRange(@ByRef @Const Range range);

    public native int cols();

    public native Mat cols(int i);

    public native void convertTo(@ByVal GpuMat gpuMat, int i);

    public native void convertTo(@ByVal GpuMat gpuMat, int i, double d, double d2);

    public native void convertTo(@ByVal Mat mat, int i);

    public native void convertTo(@ByVal Mat mat, int i, double d, double d2);

    public native void convertTo(@ByVal UMat uMat, int i);

    public native void convertTo(@ByVal UMat uMat, int i, double d, double d2);

    public native void copySize(@ByRef @Const Mat mat);

    public native void copyTo(@ByVal GpuMat gpuMat);

    public native void copyTo(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void copyTo(@ByVal Mat mat);

    public native void copyTo(@ByVal Mat mat, @ByVal Mat mat2);

    public native void copyTo(@ByVal UMat uMat);

    public native void copyTo(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void create(int i, int i2, int i3);

    public native void create(int i, @Const IntBuffer intBuffer, int i2);

    public native void create(int i, @Const IntPointer intPointer, int i2);

    public native void create(int i, @Const int[] iArr, int i2);

    public native void create(@StdVector IntBuffer intBuffer, int i);

    public native void create(@StdVector IntPointer intPointer, int i);

    public native void create(@ByVal Size size, int i);

    public native void create(@StdVector int[] iArr, int i);

    @ByVal
    public native Mat cross(@ByVal GpuMat gpuMat);

    @ByVal
    public native Mat cross(@ByVal Mat mat);

    @ByVal
    public native Mat cross(@ByVal UMat uMat);

    @Cast({"uchar*"})
    public native BytePointer data();

    public native Mat data(BytePointer bytePointer);

    @Cast({"const uchar*"})
    public native BytePointer dataend();

    public native Mat dataend(BytePointer bytePointer);

    @Cast({"const uchar*"})
    public native BytePointer datalimit();

    public native Mat datalimit(BytePointer bytePointer);

    @Cast({"const uchar*"})
    public native BytePointer datastart();

    public native Mat datastart(BytePointer bytePointer);

    public native int depth();

    @ByVal
    public native Mat diag();

    @ByVal
    public native Mat diag(int i);

    public native int dims();

    public native Mat dims(int i);

    public native double dot(@ByVal GpuMat gpuMat);

    public native double dot(@ByVal Mat mat);

    public native double dot(@ByVal UMat uMat);

    @Cast({"size_t"})
    public native long elemSize();

    @Cast({"size_t"})
    public native long elemSize1();

    @Cast({"bool"})
    public native boolean empty();

    public native int flags();

    public native Mat flags(int i);

    @ByVal
    public native UMat getUMat(@Cast({"cv::AccessFlag"}) int i);

    @ByVal
    public native UMat getUMat(@Cast({"cv::AccessFlag"}) int i, @Cast({"cv::UMatUsageFlags"}) int i2);

    @ByVal
    public native MatExpr inv();

    @ByVal
    public native MatExpr inv(int i);

    @Cast({"bool"})
    public native boolean isContinuous();

    @Cast({"bool"})
    public native boolean isSubmatrix();

    public native void locateROI(@ByRef Size size, @ByRef Point point);

    @ByVal
    public native MatExpr mul(@ByVal GpuMat gpuMat);

    @ByVal
    public native MatExpr mul(@ByVal GpuMat gpuMat, double d);

    @ByVal
    public native MatExpr mul(@ByVal Mat mat);

    @ByVal
    public native MatExpr mul(@ByVal Mat mat, double d);

    @ByVal
    public native MatExpr mul(@ByVal UMat uMat);

    @ByVal
    public native MatExpr mul(@ByVal UMat uMat, double d);

    public native void pop_back();

    public native void pop_back(@Cast({"size_t"}) long j);

    @Cast({"uchar*"})
    public native ByteBuffer ptr(@Const IntBuffer intBuffer);

    @Cast({"uchar*"})
    public native BytePointer ptr();

    @Cast({"uchar*"})
    public native BytePointer ptr(int i);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, int i2);

    @Cast({"uchar*"})
    public native BytePointer ptr(int i, int i2, int i3);

    @Cast({"uchar*"})
    public native BytePointer ptr(@Const IntPointer intPointer);

    @Cast({"uchar*"})
    public native byte[] ptr(@Const int[] iArr);

    public native void push_back(@ByRef @Const Mat mat);

    public native void push_back_(@Const Pointer pointer2);

    @ByRef
    @Name({"operator ="})
    public native Mat put(@ByRef @Const Mat mat);

    @ByRef
    @Name({"operator ="})
    public native Mat put(@ByRef @Const MatExpr matExpr);

    @ByRef
    @Name({"operator ="})
    public native Mat put(@ByRef @Const Scalar scalar);

    public native void release();

    public native void reserve(@Cast({"size_t"}) long j);

    public native void reserveBuffer(@Cast({"size_t"}) long j);

    @ByVal
    public native Mat reshape(int i);

    @ByVal
    public native Mat reshape(int i, int i2);

    @ByVal
    public native Mat reshape(int i, int i2, @Const IntBuffer intBuffer);

    @ByVal
    public native Mat reshape(int i, int i2, @Const IntPointer intPointer);

    @ByVal
    public native Mat reshape(int i, int i2, @Const int[] iArr);

    @ByVal
    public native Mat reshape(int i, @StdVector IntBuffer intBuffer);

    @ByVal
    public native Mat reshape(int i, @StdVector IntPointer intPointer);

    @ByVal
    public native Mat reshape(int i, @StdVector int[] iArr);

    public native void resize(@Cast({"size_t"}) long j);

    public native void resize(@Cast({"size_t"}) long j, @ByRef @Const Scalar scalar);

    @ByVal
    public native Mat row(int i);

    @ByVal
    public native Mat rowRange(int i, int i2);

    @ByVal
    public native Mat rowRange(@ByRef @Const Range range);

    public native int rows();

    public native Mat rows(int i);

    @ByRef
    public native Mat setTo(@ByVal GpuMat gpuMat);

    @ByRef
    public native Mat setTo(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @ByRef
    public native Mat setTo(@ByVal Mat mat);

    @ByRef
    public native Mat setTo(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @ByRef
    public native Mat setTo(@ByVal UMat uMat);

    @ByRef
    public native Mat setTo(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    @MemberGetter
    public native int size(int i);

    @ByVal
    public native Size size();

    @MemberGetter
    public native long step();

    @MemberGetter
    public native long step(int i);

    @Cast({"size_t"})
    public native long step1();

    @Cast({"size_t"})
    public native long step1(int i);

    @ByVal
    public native MatExpr t();

    @Cast({"size_t"})
    public native long total();

    @Cast({"size_t"})
    public native long total(int i);

    @Cast({"size_t"})
    public native long total(int i, int i2);

    public native int type();

    public native Mat u(UMatData uMatData);

    public native UMatData u();

    public native void updateContinuityFlag();

    static {
        Loader.load();
    }

    public Mat(Pointer pointer2) {
        super(pointer2);
    }

    public Mat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Mat position(long j) {
        return (Mat) super.position(j);
    }

    public Mat getPointer(long j) {
        return new Mat(this).position(this.position + j);
    }

    public Mat() {
        super((Pointer) null);
        allocate();
    }

    public Mat(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }

    public Mat(@ByVal Size size, int i) {
        super((Pointer) null);
        allocate(size, i);
    }

    public Mat(int i, int i2, int i3, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, i2, i3, scalar);
    }

    public Mat(@ByVal Size size, int i, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(size, i, scalar);
    }

    public Mat(int i, @Const IntPointer intPointer, int i2) {
        super((Pointer) null);
        allocate(i, intPointer, i2);
    }

    public Mat(int i, @Const IntBuffer intBuffer, int i2) {
        super((Pointer) null);
        allocate(i, intBuffer, i2);
    }

    public Mat(int i, @Const int[] iArr, int i2) {
        super((Pointer) null);
        allocate(i, iArr, i2);
    }

    public Mat(@StdVector IntPointer intPointer, int i) {
        super((Pointer) null);
        allocate(intPointer, i);
    }

    public Mat(@StdVector IntBuffer intBuffer, int i) {
        super((Pointer) null);
        allocate(intBuffer, i);
    }

    public Mat(@StdVector int[] iArr, int i) {
        super((Pointer) null);
        allocate(iArr, i);
    }

    public Mat(int i, @Const IntPointer intPointer, int i2, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, intPointer, i2, scalar);
    }

    public Mat(int i, @Const IntBuffer intBuffer, int i2, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, intBuffer, i2, scalar);
    }

    public Mat(int i, @Const int[] iArr, int i2, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, iArr, i2, scalar);
    }

    public Mat(@StdVector IntPointer intPointer, int i, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(intPointer, i, scalar);
    }

    public Mat(@StdVector IntBuffer intBuffer, int i, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(intBuffer, i, scalar);
    }

    public Mat(@StdVector int[] iArr, int i, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(iArr, i, scalar);
    }

    public Mat(@ByRef @Const Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }

    public Mat(int i, int i2, int i3, Pointer pointer2, @Cast({"size_t"}) long j) {
        super((Pointer) null);
        allocate(i, i2, i3, pointer2, j);
        this.pointer = pointer2;
    }

    public Mat(int i, int i2, int i3, Pointer pointer2) {
        this(i, i2, i3, pointer2, 0);
    }

    public Mat(CvArr cvArr) {
        super(org.bytedeco.opencv.global.opencv_core.cvarrToMat(cvArr));
        this.pointer = cvArr;
    }

    public Mat(Point point) {
        this(1, Math.max(1, point.limit() - point.position()), org.bytedeco.opencv.global.opencv_core.CV_32SC2, (Pointer) point);
        this.pointer = point;
    }

    public Mat(Point2f point2f) {
        this(1, Math.max(1, point2f.limit() - point2f.position()), org.bytedeco.opencv.global.opencv_core.CV_32FC2, (Pointer) point2f);
        this.pointer = point2f;
    }

    public Mat(Point2d point2d) {
        this(1, Math.max(1, point2d.limit() - point2d.position()), org.bytedeco.opencv.global.opencv_core.CV_64FC2, (Pointer) point2d);
        this.pointer = point2d;
    }

    public Mat(Point3i point3i) {
        this(1, Math.max(1, point3i.limit() - point3i.position()), org.bytedeco.opencv.global.opencv_core.CV_32SC3, (Pointer) point3i);
        this.pointer = point3i;
    }

    public Mat(Point3f point3f) {
        this(1, Math.max(1, point3f.limit() - point3f.position()), org.bytedeco.opencv.global.opencv_core.CV_32FC3, (Pointer) point3f);
        this.pointer = point3f;
    }

    public Mat(Point3d point3d) {
        this(1, Math.max(1, point3d.limit() - point3d.position()), org.bytedeco.opencv.global.opencv_core.CV_64FC3, (Pointer) point3d);
        this.pointer = point3d;
    }

    public Mat(Scalar scalar) {
        this(1, Math.max(1, scalar.limit() - scalar.position()), org.bytedeco.opencv.global.opencv_core.CV_64FC4, (Pointer) scalar);
        this.pointer = scalar;
    }

    public Mat(Scalar4i scalar4i) {
        this(1, Math.max(1, scalar4i.limit() - scalar4i.position()), org.bytedeco.opencv.global.opencv_core.CV_32SC4, (Pointer) scalar4i);
        this.pointer = scalar4i;
    }

    public Mat(byte... bArr) {
        this(bArr, false);
    }

    public Mat(byte[] bArr, boolean z) {
        this(new BytePointer(bArr), z);
    }

    public Mat(short... sArr) {
        this(sArr, true);
    }

    public Mat(short[] sArr, boolean z) {
        this(new ShortPointer(sArr), z);
    }

    public Mat(int... iArr) {
        this(new IntPointer(iArr));
    }

    public Mat(double... dArr) {
        this(new DoublePointer(dArr));
    }

    public Mat(float... fArr) {
        this(new FloatPointer(fArr));
    }

    private Mat(long j, long j2, int i, Pointer pointer2) {
        this((int) Math.min(j, 2147483647L), (int) Math.min(j2, 2147483647L), i, pointer2, 0);
    }

    public Mat(BytePointer bytePointer) {
        this(bytePointer, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Mat(BytePointer bytePointer, boolean z) {
        this(1, Math.max(1, bytePointer.limit() - bytePointer.position()), z ? org.bytedeco.opencv.global.opencv_core.CV_8SC1 : org.bytedeco.opencv.global.opencv_core.CV_8UC1, (Pointer) bytePointer);
    }

    public Mat(ShortPointer shortPointer) {
        this(shortPointer, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Mat(ShortPointer shortPointer, boolean z) {
        this(1, Math.max(1, shortPointer.limit() - shortPointer.position()), z ? org.bytedeco.opencv.global.opencv_core.CV_16SC1 : org.bytedeco.opencv.global.opencv_core.CV_16UC1, (Pointer) shortPointer);
    }

    public Mat(IntPointer intPointer) {
        this(1, Math.max(1, intPointer.limit() - intPointer.position()), org.bytedeco.opencv.global.opencv_core.CV_32SC1, (Pointer) intPointer);
    }

    public Mat(FloatPointer floatPointer) {
        this(1, Math.max(1, floatPointer.limit() - floatPointer.position()), org.bytedeco.opencv.global.opencv_core.CV_32FC1, (Pointer) floatPointer);
    }

    public Mat(DoublePointer doublePointer) {
        this(1, Math.max(1, doublePointer.limit() - doublePointer.position()), org.bytedeco.opencv.global.opencv_core.CV_64FC1, (Pointer) doublePointer);
    }

    public Mat(@ByVal Size size, int i, Pointer pointer2, @Cast({"size_t"}) long j) {
        super((Pointer) null);
        allocate(size, i, pointer2, j);
    }

    public Mat(@ByVal Size size, int i, Pointer pointer2) {
        super((Pointer) null);
        allocate(size, i, pointer2);
    }

    public Mat(int i, @Const IntPointer intPointer, int i2, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer) {
        super((Pointer) null);
        allocate(i, intPointer, i2, pointer2, sizeTPointer);
    }

    public Mat(int i, @Const IntPointer intPointer, int i2, Pointer pointer2) {
        super((Pointer) null);
        allocate(i, intPointer, i2, pointer2);
    }

    public Mat(int i, @Const IntBuffer intBuffer, int i2, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer) {
        super((Pointer) null);
        allocate(i, intBuffer, i2, pointer2, sizeTPointer);
    }

    public Mat(int i, @Const IntBuffer intBuffer, int i2, Pointer pointer2) {
        super((Pointer) null);
        allocate(i, intBuffer, i2, pointer2);
    }

    public Mat(int i, @Const int[] iArr, int i2, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer) {
        super((Pointer) null);
        allocate(i, iArr, i2, pointer2, sizeTPointer);
    }

    public Mat(int i, @Const int[] iArr, int i2, Pointer pointer2) {
        super((Pointer) null);
        allocate(i, iArr, i2, pointer2);
    }

    public Mat(@StdVector IntPointer intPointer, int i, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer) {
        super((Pointer) null);
        allocate(intPointer, i, pointer2, sizeTPointer);
    }

    public Mat(@StdVector IntPointer intPointer, int i, Pointer pointer2) {
        super((Pointer) null);
        allocate(intPointer, i, pointer2);
    }

    public Mat(@StdVector IntBuffer intBuffer, int i, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer) {
        super((Pointer) null);
        allocate(intBuffer, i, pointer2, sizeTPointer);
    }

    public Mat(@StdVector IntBuffer intBuffer, int i, Pointer pointer2) {
        super((Pointer) null);
        allocate(intBuffer, i, pointer2);
    }

    public Mat(@StdVector int[] iArr, int i, Pointer pointer2, @Cast({"const size_t*"}) SizeTPointer sizeTPointer) {
        super((Pointer) null);
        allocate(iArr, i, pointer2, sizeTPointer);
    }

    public Mat(@StdVector int[] iArr, int i, Pointer pointer2) {
        super((Pointer) null);
        allocate(iArr, i, pointer2);
    }

    public Mat(@ByRef @Const Mat mat, @ByRef @Const Range range, @ByRef(nullValue = "cv::Range::all()") @Const Range range2) {
        super((Pointer) null);
        allocate(mat, range, range2);
    }

    public Mat(@ByRef @Const Mat mat, @ByRef @Const Range range) {
        super((Pointer) null);
        allocate(mat, range);
    }

    public Mat(@ByRef @Const Mat mat, @ByRef @Const Rect rect) {
        super((Pointer) null);
        allocate(mat, rect);
    }

    public Mat(@ByRef @Const GpuMat gpuMat) {
        super((Pointer) null);
        allocate(gpuMat);
    }
}
