package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Namespace("cv::cuda")
@NoOffset
public class GpuMat extends Pointer {
    private native void allocate();

    private native void allocate(int i, int i2, int i3);

    private native void allocate(int i, int i2, int i3, Pointer pointer);

    private native void allocate(int i, int i2, int i3, Pointer pointer, @Cast({"size_t"}) long j);

    private native void allocate(int i, int i2, int i3, Allocator allocator);

    private native void allocate(int i, int i2, int i3, @ByVal Scalar scalar);

    private native void allocate(int i, int i2, int i3, @ByVal Scalar scalar, Allocator allocator);

    private native void allocate(Allocator allocator);

    private native void allocate(@ByRef @Const GpuMat gpuMat);

    private native void allocate(@ByVal GpuMat gpuMat, Allocator allocator);

    private native void allocate(@ByRef @Const GpuMat gpuMat, @ByVal Range range, @ByVal Range range2);

    private native void allocate(@ByRef @Const GpuMat gpuMat, @ByVal Rect rect);

    private native void allocate(@ByVal Mat mat);

    private native void allocate(@ByVal Mat mat, Allocator allocator);

    private native void allocate(@ByVal Size size, int i);

    private native void allocate(@ByVal Size size, int i, Pointer pointer);

    private native void allocate(@ByVal Size size, int i, Pointer pointer, @Cast({"size_t"}) long j);

    private native void allocate(@ByVal Size size, int i, Allocator allocator);

    private native void allocate(@ByVal Size size, int i, @ByVal Scalar scalar);

    private native void allocate(@ByVal Size size, int i, @ByVal Scalar scalar, Allocator allocator);

    private native void allocate(@ByVal UMat uMat);

    private native void allocate(@ByVal UMat uMat, Allocator allocator);

    private native void allocateArray(long j);

    public static native Allocator defaultAllocator();

    public static native void setDefaultAllocator(Allocator allocator);

    @ByRef
    public native GpuMat adjustROI(int i, int i2, int i3, int i4);

    public native Allocator allocator();

    public native GpuMat allocator(Allocator allocator);

    @ByVal
    @Name({"operator ()"})
    public native GpuMat apply(@ByVal Range range, @ByVal Range range2);

    @ByVal
    @Name({"operator ()"})
    public native GpuMat apply(@ByVal Rect rect);

    public native void assignTo(@ByRef GpuMat gpuMat);

    public native void assignTo(@ByRef GpuMat gpuMat, int i);

    public native int channels();

    @ByVal
    public native GpuMat clone();

    @ByVal
    public native GpuMat col(int i);

    @ByVal
    public native GpuMat colRange(int i, int i2);

    @ByVal
    public native GpuMat colRange(@ByVal Range range);

    public native int cols();

    public native GpuMat cols(int i);

    public native void convertTo(@ByVal GpuMat gpuMat, int i);

    public native void convertTo(@ByVal GpuMat gpuMat, int i, double d);

    public native void convertTo(@ByVal GpuMat gpuMat, int i, double d, double d2);

    public native void convertTo(@ByVal GpuMat gpuMat, int i, double d, double d2, @ByRef Stream stream);

    public native void convertTo(@ByVal GpuMat gpuMat, int i, double d, @ByRef Stream stream);

    public native void convertTo(@ByVal GpuMat gpuMat, int i, @ByRef Stream stream);

    public native void convertTo(@ByVal Mat mat, int i);

    public native void convertTo(@ByVal Mat mat, int i, double d);

    public native void convertTo(@ByVal Mat mat, int i, double d, double d2);

    public native void convertTo(@ByVal Mat mat, int i, double d, double d2, @ByRef Stream stream);

    public native void convertTo(@ByVal Mat mat, int i, double d, @ByRef Stream stream);

    public native void convertTo(@ByVal Mat mat, int i, @ByRef Stream stream);

    public native void convertTo(@ByVal UMat uMat, int i);

    public native void convertTo(@ByVal UMat uMat, int i, double d);

    public native void convertTo(@ByVal UMat uMat, int i, double d, double d2);

    public native void convertTo(@ByVal UMat uMat, int i, double d, double d2, @ByRef Stream stream);

    public native void convertTo(@ByVal UMat uMat, int i, double d, @ByRef Stream stream);

    public native void convertTo(@ByVal UMat uMat, int i, @ByRef Stream stream);

    public native void copyTo(@ByVal GpuMat gpuMat);

    public native void copyTo(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void copyTo(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef Stream stream);

    public native void copyTo(@ByVal GpuMat gpuMat, @ByRef Stream stream);

    public native void copyTo(@ByVal Mat mat);

    public native void copyTo(@ByVal Mat mat, @ByVal Mat mat2);

    public native void copyTo(@ByVal Mat mat, @ByVal Mat mat2, @ByRef Stream stream);

    public native void copyTo(@ByVal Mat mat, @ByRef Stream stream);

    public native void copyTo(@ByVal UMat uMat);

    public native void copyTo(@ByVal UMat uMat, @ByRef Stream stream);

    public native void copyTo(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void copyTo(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef Stream stream);

    public native void create(int i, int i2, int i3);

    public native void create(@ByVal Size size, int i);

    public native Pointer cudaPtr();

    @Cast({"uchar*"})
    public native BytePointer data();

    public native GpuMat data(BytePointer bytePointer);

    @Cast({"const uchar*"})
    public native BytePointer dataend();

    public native GpuMat dataend(BytePointer bytePointer);

    @Cast({"uchar*"})
    public native BytePointer datastart();

    public native GpuMat datastart(BytePointer bytePointer);

    public native int depth();

    public native void download(@ByVal GpuMat gpuMat);

    public native void download(@ByVal GpuMat gpuMat, @ByRef Stream stream);

    public native void download(@ByVal Mat mat);

    public native void download(@ByVal Mat mat, @ByRef Stream stream);

    public native void download(@ByVal UMat uMat);

    public native void download(@ByVal UMat uMat, @ByRef Stream stream);

    @Cast({"size_t"})
    public native long elemSize();

    @Cast({"size_t"})
    public native long elemSize1();

    @Cast({"bool"})
    public native boolean empty();

    public native int flags();

    public native GpuMat flags(int i);

    @Cast({"bool"})
    public native boolean isContinuous();

    public native void locateROI(@ByRef Size size, @ByRef Point point);

    @Cast({"uchar*"})
    public native BytePointer ptr();

    @Cast({"uchar*"})
    public native BytePointer ptr(int i);

    @ByRef
    @Name({"operator ="})
    public native GpuMat put(@ByRef @Const GpuMat gpuMat);

    public native IntPointer refcount();

    public native GpuMat refcount(IntPointer intPointer);

    public native void release();

    @ByVal
    public native GpuMat reshape(int i);

    @ByVal
    public native GpuMat reshape(int i, int i2);

    @ByVal
    public native GpuMat row(int i);

    @ByVal
    public native GpuMat rowRange(int i, int i2);

    @ByVal
    public native GpuMat rowRange(@ByVal Range range);

    public native int rows();

    public native GpuMat rows(int i);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar, @ByVal GpuMat gpuMat);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar, @ByVal GpuMat gpuMat, @ByRef Stream stream);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar, @ByVal Mat mat);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar, @ByVal Mat mat, @ByRef Stream stream);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar, @ByRef Stream stream);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar, @ByVal UMat uMat);

    @ByRef
    public native GpuMat setTo(@ByVal Scalar scalar, @ByVal UMat uMat, @ByRef Stream stream);

    @ByVal
    public native Size size();

    @Cast({"size_t"})
    public native long step();

    public native GpuMat step(long j);

    @Cast({"size_t"})
    public native long step1();

    public native void swap(@ByRef GpuMat gpuMat);

    public native int type();

    public native void updateContinuityFlag();

    public native void upload(@ByVal GpuMat gpuMat);

    public native void upload(@ByVal GpuMat gpuMat, @ByRef Stream stream);

    public native void upload(@ByVal Mat mat);

    public native void upload(@ByVal Mat mat, @ByRef Stream stream);

    public native void upload(@ByVal UMat uMat);

    public native void upload(@ByVal UMat uMat, @ByRef Stream stream);

    static {
        Loader.load();
    }

    public GpuMat(Pointer pointer) {
        super(pointer);
    }

    public GpuMat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public GpuMat position(long j) {
        return (GpuMat) super.position(j);
    }

    public GpuMat getPointer(long j) {
        return new GpuMat(this).position(this.position + j);
    }

    public static class Allocator extends Pointer {
        @Cast({"bool"})
        @Name({"allocate"})
        public native boolean _allocate(GpuMat gpuMat, int i, int i2, @Cast({"size_t"}) long j);

        @Name({"free"})
        public native void _free(GpuMat gpuMat);

        static {
            Loader.load();
        }

        public Allocator(Pointer pointer) {
            super(pointer);
        }
    }

    public GpuMat(Allocator allocator) {
        super((Pointer) null);
        allocate(allocator);
    }

    public GpuMat() {
        super((Pointer) null);
        allocate();
    }

    public GpuMat(int i, int i2, int i3, Allocator allocator) {
        super((Pointer) null);
        allocate(i, i2, i3, allocator);
    }

    public GpuMat(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }

    public GpuMat(@ByVal Size size, int i, Allocator allocator) {
        super((Pointer) null);
        allocate(size, i, allocator);
    }

    public GpuMat(@ByVal Size size, int i) {
        super((Pointer) null);
        allocate(size, i);
    }

    public GpuMat(int i, int i2, int i3, @ByVal Scalar scalar, Allocator allocator) {
        super((Pointer) null);
        allocate(i, i2, i3, scalar, allocator);
    }

    public GpuMat(int i, int i2, int i3, @ByVal Scalar scalar) {
        super((Pointer) null);
        allocate(i, i2, i3, scalar);
    }

    public GpuMat(@ByVal Size size, int i, @ByVal Scalar scalar, Allocator allocator) {
        super((Pointer) null);
        allocate(size, i, scalar, allocator);
    }

    public GpuMat(@ByVal Size size, int i, @ByVal Scalar scalar) {
        super((Pointer) null);
        allocate(size, i, scalar);
    }

    public GpuMat(@ByRef @Const GpuMat gpuMat) {
        super((Pointer) null);
        allocate(gpuMat);
    }

    public GpuMat(int i, int i2, int i3, Pointer pointer, @Cast({"size_t"}) long j) {
        super((Pointer) null);
        allocate(i, i2, i3, pointer, j);
    }

    public GpuMat(int i, int i2, int i3, Pointer pointer) {
        super((Pointer) null);
        allocate(i, i2, i3, pointer);
    }

    public GpuMat(@ByVal Size size, int i, Pointer pointer, @Cast({"size_t"}) long j) {
        super((Pointer) null);
        allocate(size, i, pointer, j);
    }

    public GpuMat(@ByVal Size size, int i, Pointer pointer) {
        super((Pointer) null);
        allocate(size, i, pointer);
    }

    public GpuMat(@ByRef @Const GpuMat gpuMat, @ByVal Range range, @ByVal Range range2) {
        super((Pointer) null);
        allocate(gpuMat, range, range2);
    }

    public GpuMat(@ByRef @Const GpuMat gpuMat, @ByVal Rect rect) {
        super((Pointer) null);
        allocate(gpuMat, rect);
    }

    public GpuMat(@ByVal Mat mat, Allocator allocator) {
        super((Pointer) null);
        allocate(mat, allocator);
    }

    public GpuMat(@ByVal Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }

    public GpuMat(@ByVal UMat uMat, Allocator allocator) {
        super((Pointer) null);
        allocate(uMat, allocator);
    }

    public GpuMat(@ByVal UMat uMat) {
        super((Pointer) null);
        allocate(uMat);
    }

    public GpuMat(@ByVal GpuMat gpuMat, Allocator allocator) {
        super((Pointer) null);
        allocate(gpuMat, allocator);
    }
}
