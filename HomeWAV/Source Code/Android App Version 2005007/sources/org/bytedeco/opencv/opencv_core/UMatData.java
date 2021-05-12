package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class UMatData extends Pointer {
    public static final int ASYNC_CLEANUP = 128;
    public static final int COPY_ON_MAP = 1;
    public static final int DEVICE_COPY_OBSOLETE = 4;
    public static final int DEVICE_MEM_MAPPED = 64;
    public static final int HOST_COPY_OBSOLETE = 2;
    public static final int TEMP_COPIED_UMAT = 24;
    public static final int TEMP_UMAT = 8;
    public static final int USER_ALLOCATED = 32;

    private native void allocate(@Const MatAllocator matAllocator);

    public native int allocatorFlags_();

    public native UMatData allocatorFlags_(int i);

    @Cast({"bool"})
    public native boolean copyOnMap();

    @Const
    public native MatAllocator currAllocator();

    public native UMatData currAllocator(MatAllocator matAllocator);

    @Cast({"uchar*"})
    public native BytePointer data();

    public native UMatData data(BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean deviceCopyObsolete();

    @Cast({"bool"})
    public native boolean deviceMemMapped();

    @Cast({"cv::UMatData::MemoryFlag"})
    public native int flags();

    public native UMatData flags(int i);

    public native Pointer handle();

    public native UMatData handle(Pointer pointer);

    @Cast({"bool"})
    public native boolean hostCopyObsolete();

    public native void lock();

    public native int mapcount();

    public native UMatData mapcount(int i);

    public native void markDeviceCopyObsolete(@Cast({"bool"}) boolean z);

    public native void markDeviceMemMapped(@Cast({"bool"}) boolean z);

    public native void markHostCopyObsolete(@Cast({"bool"}) boolean z);

    @Cast({"uchar*"})
    public native BytePointer origdata();

    public native UMatData origdata(BytePointer bytePointer);

    public native UMatData originalUMatData();

    public native UMatData originalUMatData(UMatData uMatData);

    @Const
    public native MatAllocator prevAllocator();

    public native UMatData prevAllocator(MatAllocator matAllocator);

    public native int refcount();

    public native UMatData refcount(int i);

    @Cast({"size_t"})
    public native long size();

    public native UMatData size(long j);

    @Cast({"bool"})
    public native boolean tempCopiedUMat();

    @Cast({"bool"})
    public native boolean tempUMat();

    public native void unlock();

    public native int urefcount();

    public native UMatData urefcount(int i);

    public native Pointer userdata();

    public native UMatData userdata(Pointer pointer);

    static {
        Loader.load();
    }

    public UMatData(Pointer pointer) {
        super(pointer);
    }

    public UMatData(@Const MatAllocator matAllocator) {
        super((Pointer) null);
        allocate(matAllocator);
    }
}
