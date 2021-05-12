package org.bytedeco.opencv.opencv_core;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
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
public class UMat extends Pointer {
    public static final int AUTO_STEP = 0;
    public static final int CONTINUOUS_FLAG = 16384;
    public static final int DEPTH_MASK = 7;
    public static final int MAGIC_MASK = -65536;
    public static final int MAGIC_VAL = 1124007936;
    public static final int SUBMATRIX_FLAG = 32768;
    public static final int TYPE_MASK = 4095;

    private native void allocate();

    private native void allocate(@Cast({"cv::UMatUsageFlags"}) int i);

    private native void allocate(int i, int i2, int i3);

    private native void allocate(int i, int i2, int i3, @Cast({"cv::UMatUsageFlags"}) int i4);

    private native void allocate(int i, int i2, int i3, @ByRef @Const Scalar scalar);

    private native void allocate(int i, int i2, int i3, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i4);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2, @Cast({"cv::UMatUsageFlags"}) int i3);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2, @ByRef @Const Scalar scalar);

    private native void allocate(int i, @Const IntBuffer intBuffer, int i2, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i3);

    private native void allocate(int i, @Const IntPointer intPointer, int i2);

    private native void allocate(int i, @Const IntPointer intPointer, int i2, @Cast({"cv::UMatUsageFlags"}) int i3);

    private native void allocate(int i, @Const IntPointer intPointer, int i2, @ByRef @Const Scalar scalar);

    private native void allocate(int i, @Const IntPointer intPointer, int i2, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i3);

    private native void allocate(int i, @Const int[] iArr, int i2);

    private native void allocate(int i, @Const int[] iArr, int i2, @Cast({"cv::UMatUsageFlags"}) int i3);

    private native void allocate(int i, @Const int[] iArr, int i2, @ByRef @Const Scalar scalar);

    private native void allocate(int i, @Const int[] iArr, int i2, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i3);

    private native void allocate(@ByVal Size size, int i);

    private native void allocate(@ByVal Size size, int i, @Cast({"cv::UMatUsageFlags"}) int i2);

    private native void allocate(@ByVal Size size, int i, @ByRef @Const Scalar scalar);

    private native void allocate(@ByVal Size size, int i, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i2);

    private native void allocate(@ByRef @Const UMat uMat);

    private native void allocate(@ByRef @Const UMat uMat, @ByRef @Const Range range);

    private native void allocate(@ByRef @Const UMat uMat, @ByRef @Const Range range, @ByRef(nullValue = "cv::Range::all()") @Const Range range2);

    private native void allocate(@ByRef @Const UMat uMat, @ByRef @Const Rect rect);

    @ByVal
    public static native UMat diag(@ByRef @Const UMat uMat);

    @ByVal
    public static native UMat eye(int i, int i2, int i3);

    @ByVal
    public static native UMat eye(@ByVal Size size, int i);

    public static native MatAllocator getStdAllocator();

    @ByVal
    public static native UMat ones(int i, int i2, int i3);

    @ByVal
    public static native UMat ones(@ByVal Size size, int i);

    @ByVal
    public static native UMat zeros(int i, int i2, int i3);

    @ByVal
    public static native UMat zeros(@ByVal Size size, int i);

    @Name({"deallocate"})
    public native void _deallocate();

    public native void addref();

    @ByRef
    public native UMat adjustROI(int i, int i2, int i3, int i4);

    public native MatAllocator allocator();

    public native UMat allocator(MatAllocator matAllocator);

    @ByVal
    @Name({"operator ()"})
    public native UMat apply(@Const Range range);

    @ByVal
    @Name({"operator ()"})
    public native UMat apply(@ByVal Range range, @ByVal Range range2);

    @ByVal
    @Name({"operator ()"})
    public native UMat apply(@ByRef @Const Rect rect);

    public native void assignTo(@ByRef UMat uMat);

    public native void assignTo(@ByRef UMat uMat, int i);

    public native int channels();

    public native int checkVector(int i);

    public native int checkVector(int i, int i2, @Cast({"bool"}) boolean z);

    @ByVal
    public native UMat clone();

    @ByVal
    public native UMat col(int i);

    @ByVal
    public native UMat colRange(int i, int i2);

    @ByVal
    public native UMat colRange(@ByRef @Const Range range);

    public native int cols();

    public native UMat cols(int i);

    public native void convertTo(@ByVal GpuMat gpuMat, int i);

    public native void convertTo(@ByVal GpuMat gpuMat, int i, double d, double d2);

    public native void convertTo(@ByVal Mat mat, int i);

    public native void convertTo(@ByVal Mat mat, int i, double d, double d2);

    public native void convertTo(@ByVal UMat uMat, int i);

    public native void convertTo(@ByVal UMat uMat, int i, double d, double d2);

    public native void copySize(@ByRef @Const UMat uMat);

    public native void copyTo(@ByVal GpuMat gpuMat);

    public native void copyTo(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void copyTo(@ByVal Mat mat);

    public native void copyTo(@ByVal Mat mat, @ByVal Mat mat2);

    public native void copyTo(@ByVal UMat uMat);

    public native void copyTo(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void create(int i, int i2, int i3);

    public native void create(int i, int i2, int i3, @Cast({"cv::UMatUsageFlags"}) int i4);

    public native void create(int i, @Const IntBuffer intBuffer, int i2);

    public native void create(int i, @Const IntBuffer intBuffer, int i2, @Cast({"cv::UMatUsageFlags"}) int i3);

    public native void create(int i, @Const IntPointer intPointer, int i2);

    public native void create(int i, @Const IntPointer intPointer, int i2, @Cast({"cv::UMatUsageFlags"}) int i3);

    public native void create(int i, @Const int[] iArr, int i2);

    public native void create(int i, @Const int[] iArr, int i2, @Cast({"cv::UMatUsageFlags"}) int i3);

    public native void create(@StdVector IntBuffer intBuffer, int i);

    public native void create(@StdVector IntBuffer intBuffer, int i, @Cast({"cv::UMatUsageFlags"}) int i2);

    public native void create(@StdVector IntPointer intPointer, int i);

    public native void create(@StdVector IntPointer intPointer, int i, @Cast({"cv::UMatUsageFlags"}) int i2);

    public native void create(@ByVal Size size, int i);

    public native void create(@ByVal Size size, int i, @Cast({"cv::UMatUsageFlags"}) int i2);

    public native void create(@StdVector int[] iArr, int i);

    public native void create(@StdVector int[] iArr, int i, @Cast({"cv::UMatUsageFlags"}) int i2);

    public native int depth();

    @ByVal
    public native UMat diag();

    @ByVal
    public native UMat diag(int i);

    public native int dims();

    public native UMat dims(int i);

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

    public native UMat flags(int i);

    @ByVal
    public native Mat getMat(@Cast({"cv::AccessFlag"}) int i);

    public native Pointer handle(@Cast({"cv::AccessFlag"}) int i);

    @ByVal
    public native UMat inv();

    @ByVal
    public native UMat inv(int i);

    @Cast({"bool"})
    public native boolean isContinuous();

    @Cast({"bool"})
    public native boolean isSubmatrix();

    public native void locateROI(@ByRef Size size, @ByRef Point point);

    @ByVal
    public native UMat mul(@ByVal GpuMat gpuMat);

    @ByVal
    public native UMat mul(@ByVal GpuMat gpuMat, double d);

    @ByVal
    public native UMat mul(@ByVal Mat mat);

    @ByVal
    public native UMat mul(@ByVal Mat mat, double d);

    @ByVal
    public native UMat mul(@ByVal UMat uMat);

    @ByVal
    public native UMat mul(@ByVal UMat uMat, double d);

    public native void ndoffset(@Cast({"size_t*"}) SizeTPointer sizeTPointer);

    @Cast({"size_t"})
    public native long offset();

    public native UMat offset(long j);

    @ByRef
    @Name({"operator ="})
    public native UMat put(@ByRef @Const Scalar scalar);

    @ByRef
    @Name({"operator ="})
    public native UMat put(@ByRef @Const UMat uMat);

    public native void release();

    @ByVal
    public native UMat reshape(int i);

    @ByVal
    public native UMat reshape(int i, int i2);

    @ByVal
    public native UMat reshape(int i, int i2, @Const IntBuffer intBuffer);

    @ByVal
    public native UMat reshape(int i, int i2, @Const IntPointer intPointer);

    @ByVal
    public native UMat reshape(int i, int i2, @Const int[] iArr);

    @ByVal
    public native UMat row(int i);

    @ByVal
    public native UMat rowRange(int i, int i2);

    @ByVal
    public native UMat rowRange(@ByRef @Const Range range);

    public native int rows();

    public native UMat rows(int i);

    @ByRef
    public native UMat setTo(@ByVal GpuMat gpuMat);

    @ByRef
    public native UMat setTo(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    @ByRef
    public native UMat setTo(@ByVal Mat mat);

    @ByRef
    public native UMat setTo(@ByVal Mat mat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    @ByRef
    public native UMat setTo(@ByVal UMat uMat);

    @ByRef
    public native UMat setTo(@ByVal UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

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
    public native UMat t();

    @Cast({"size_t"})
    public native long total();

    public native int type();

    public native UMat u(UMatData uMatData);

    public native UMatData u();

    public native void updateContinuityFlag();

    @Cast({"cv::UMatUsageFlags"})
    public native int usageFlags();

    public native UMat usageFlags(int i);

    static {
        Loader.load();
    }

    public UMat(Pointer pointer) {
        super(pointer);
    }

    public UMat(@Cast({"cv::UMatUsageFlags"}) int i) {
        super((Pointer) null);
        allocate(i);
    }

    public UMat() {
        super((Pointer) null);
        allocate();
    }

    public UMat(int i, int i2, int i3, @Cast({"cv::UMatUsageFlags"}) int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, i4);
    }

    public UMat(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }

    public UMat(@ByVal Size size, int i, @Cast({"cv::UMatUsageFlags"}) int i2) {
        super((Pointer) null);
        allocate(size, i, i2);
    }

    public UMat(@ByVal Size size, int i) {
        super((Pointer) null);
        allocate(size, i);
    }

    public UMat(int i, int i2, int i3, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, scalar, i4);
    }

    public UMat(int i, int i2, int i3, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, i2, i3, scalar);
    }

    public UMat(@ByVal Size size, int i, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i2) {
        super((Pointer) null);
        allocate(size, i, scalar, i2);
    }

    public UMat(@ByVal Size size, int i, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(size, i, scalar);
    }

    public UMat(int i, @Const IntPointer intPointer, int i2, @Cast({"cv::UMatUsageFlags"}) int i3) {
        super((Pointer) null);
        allocate(i, intPointer, i2, i3);
    }

    public UMat(int i, @Const IntPointer intPointer, int i2) {
        super((Pointer) null);
        allocate(i, intPointer, i2);
    }

    public UMat(int i, @Const IntBuffer intBuffer, int i2, @Cast({"cv::UMatUsageFlags"}) int i3) {
        super((Pointer) null);
        allocate(i, intBuffer, i2, i3);
    }

    public UMat(int i, @Const IntBuffer intBuffer, int i2) {
        super((Pointer) null);
        allocate(i, intBuffer, i2);
    }

    public UMat(int i, @Const int[] iArr, int i2, @Cast({"cv::UMatUsageFlags"}) int i3) {
        super((Pointer) null);
        allocate(i, iArr, i2, i3);
    }

    public UMat(int i, @Const int[] iArr, int i2) {
        super((Pointer) null);
        allocate(i, iArr, i2);
    }

    public UMat(int i, @Const IntPointer intPointer, int i2, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i3) {
        super((Pointer) null);
        allocate(i, intPointer, i2, scalar, i3);
    }

    public UMat(int i, @Const IntPointer intPointer, int i2, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, intPointer, i2, scalar);
    }

    public UMat(int i, @Const IntBuffer intBuffer, int i2, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i3) {
        super((Pointer) null);
        allocate(i, intBuffer, i2, scalar, i3);
    }

    public UMat(int i, @Const IntBuffer intBuffer, int i2, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, intBuffer, i2, scalar);
    }

    public UMat(int i, @Const int[] iArr, int i2, @ByRef @Const Scalar scalar, @Cast({"cv::UMatUsageFlags"}) int i3) {
        super((Pointer) null);
        allocate(i, iArr, i2, scalar, i3);
    }

    public UMat(int i, @Const int[] iArr, int i2, @ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(i, iArr, i2, scalar);
    }

    public UMat(@ByRef @Const UMat uMat) {
        super((Pointer) null);
        allocate(uMat);
    }

    public UMat(@ByRef @Const UMat uMat, @ByRef @Const Range range, @ByRef(nullValue = "cv::Range::all()") @Const Range range2) {
        super((Pointer) null);
        allocate(uMat, range, range2);
    }

    public UMat(@ByRef @Const UMat uMat, @ByRef @Const Range range) {
        super((Pointer) null);
        allocate(uMat, range);
    }

    public UMat(@ByRef @Const UMat uMat, @ByRef @Const Rect rect) {
        super((Pointer) null);
        allocate(uMat, rect);
    }
}
