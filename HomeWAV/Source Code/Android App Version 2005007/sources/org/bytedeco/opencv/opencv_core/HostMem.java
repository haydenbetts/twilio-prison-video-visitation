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

@Namespace("cv::cuda")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class HostMem extends Pointer {
    public static final int PAGE_LOCKED = 1;
    public static final int SHARED = 2;
    public static final int WRITE_COMBINED = 4;

    private native void allocate();

    private native void allocate(@Cast({"cv::cuda::HostMem::AllocType"}) int i);

    private native void allocate(int i, int i2, int i3);

    private native void allocate(int i, int i2, int i3, @Cast({"cv::cuda::HostMem::AllocType"}) int i4);

    private native void allocate(@ByVal GpuMat gpuMat);

    private native void allocate(@ByVal GpuMat gpuMat, @Cast({"cv::cuda::HostMem::AllocType"}) int i);

    private native void allocate(@ByRef @Const HostMem hostMem);

    private native void allocate(@ByVal Mat mat);

    private native void allocate(@ByVal Mat mat, @Cast({"cv::cuda::HostMem::AllocType"}) int i);

    private native void allocate(@ByVal Size size, int i);

    private native void allocate(@ByVal Size size, int i, @Cast({"cv::cuda::HostMem::AllocType"}) int i2);

    private native void allocate(@ByVal UMat uMat);

    private native void allocate(@ByVal UMat uMat, @Cast({"cv::cuda::HostMem::AllocType"}) int i);

    public static native MatAllocator getAllocator();

    public static native MatAllocator getAllocator(@Cast({"cv::cuda::HostMem::AllocType"}) int i);

    @Cast({"cv::cuda::HostMem::AllocType"})
    public native int alloc_type();

    public native HostMem alloc_type(int i);

    public native int channels();

    @ByVal
    public native HostMem clone();

    public native int cols();

    public native HostMem cols(int i);

    public native void create(int i, int i2, int i3);

    public native void create(@ByVal Size size, int i);

    @ByVal
    public native GpuMat createGpuMatHeader();

    @ByVal
    public native Mat createMatHeader();

    @Cast({"uchar*"})
    public native BytePointer data();

    public native HostMem data(BytePointer bytePointer);

    @Cast({"const uchar*"})
    public native BytePointer dataend();

    public native HostMem dataend(BytePointer bytePointer);

    @Cast({"uchar*"})
    public native BytePointer datastart();

    public native HostMem datastart(BytePointer bytePointer);

    public native int depth();

    @Cast({"size_t"})
    public native long elemSize();

    @Cast({"size_t"})
    public native long elemSize1();

    @Cast({"bool"})
    public native boolean empty();

    public native int flags();

    public native HostMem flags(int i);

    @Cast({"bool"})
    public native boolean isContinuous();

    @ByRef
    @Name({"operator ="})
    public native HostMem put(@ByRef @Const HostMem hostMem);

    public native IntPointer refcount();

    public native HostMem refcount(IntPointer intPointer);

    public native void release();

    @ByVal
    public native HostMem reshape(int i);

    @ByVal
    public native HostMem reshape(int i, int i2);

    public native int rows();

    public native HostMem rows(int i);

    @ByVal
    public native Size size();

    @Cast({"size_t"})
    public native long step();

    public native HostMem step(long j);

    @Cast({"size_t"})
    public native long step1();

    public native void swap(@ByRef HostMem hostMem);

    public native int type();

    static {
        Loader.load();
    }

    public HostMem(Pointer pointer) {
        super(pointer);
    }

    public HostMem(@Cast({"cv::cuda::HostMem::AllocType"}) int i) {
        super((Pointer) null);
        allocate(i);
    }

    public HostMem() {
        super((Pointer) null);
        allocate();
    }

    public HostMem(@ByRef @Const HostMem hostMem) {
        super((Pointer) null);
        allocate(hostMem);
    }

    public HostMem(int i, int i2, int i3, @Cast({"cv::cuda::HostMem::AllocType"}) int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, i4);
    }

    public HostMem(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }

    public HostMem(@ByVal Size size, int i, @Cast({"cv::cuda::HostMem::AllocType"}) int i2) {
        super((Pointer) null);
        allocate(size, i, i2);
    }

    public HostMem(@ByVal Size size, int i) {
        super((Pointer) null);
        allocate(size, i);
    }

    public HostMem(@ByVal Mat mat, @Cast({"cv::cuda::HostMem::AllocType"}) int i) {
        super((Pointer) null);
        allocate(mat, i);
    }

    public HostMem(@ByVal Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }

    public HostMem(@ByVal UMat uMat, @Cast({"cv::cuda::HostMem::AllocType"}) int i) {
        super((Pointer) null);
        allocate(uMat, i);
    }

    public HostMem(@ByVal UMat uMat) {
        super((Pointer) null);
        allocate(uMat);
    }

    public HostMem(@ByVal GpuMat gpuMat, @Cast({"cv::cuda::HostMem::AllocType"}) int i) {
        super((Pointer) null);
        allocate(gpuMat, i);
    }

    public HostMem(@ByVal GpuMat gpuMat) {
        super((Pointer) null);
        allocate(gpuMat);
    }
}
