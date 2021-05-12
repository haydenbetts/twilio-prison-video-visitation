package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ocl")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class Device extends Pointer {
    public static final int EXEC_KERNEL = 1;
    public static final int EXEC_NATIVE_KERNEL = 2;
    public static final int FP_CORRECTLY_ROUNDED_DIVIDE_SQRT = 128;
    public static final int FP_DENORM = 1;
    public static final int FP_FMA = 32;
    public static final int FP_INF_NAN = 2;
    public static final int FP_ROUND_TO_INF = 16;
    public static final int FP_ROUND_TO_NEAREST = 4;
    public static final int FP_ROUND_TO_ZERO = 8;
    public static final int FP_SOFT_FLOAT = 64;
    public static final int LOCAL_IS_GLOBAL = 2;
    public static final int LOCAL_IS_LOCAL = 1;
    public static final int NO_CACHE = 0;
    public static final int NO_LOCAL_MEM = 0;
    public static final int READ_ONLY_CACHE = 1;
    public static final int READ_WRITE_CACHE = 2;
    public static final int TYPE_ACCELERATOR = 8;
    public static final int TYPE_ALL = -1;
    public static final int TYPE_CPU = 2;
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_DGPU = 65540;
    public static final int TYPE_GPU = 4;
    public static final int TYPE_IGPU = 131076;
    public static final int UNKNOWN_VENDOR = 0;
    public static final int VENDOR_AMD = 1;
    public static final int VENDOR_INTEL = 2;
    public static final int VENDOR_NVIDIA = 3;

    private native void allocate();

    private native void allocate(Pointer pointer);

    private native void allocate(@ByRef @Const Device device);

    private native void allocateArray(long j);

    @ByRef
    @Const
    public static native Device getDefault();

    @opencv_core.Str
    public native BytePointer OpenCLVersion();

    @opencv_core.Str
    public native BytePointer OpenCL_C_Version();

    public native int addressBits();

    @Cast({"bool"})
    public native boolean available();

    @Cast({"bool"})
    public native boolean compilerAvailable();

    public native int deviceVersionMajor();

    public native int deviceVersionMinor();

    public native int doubleFPConfig();

    @opencv_core.Str
    public native BytePointer driverVersion();

    @Cast({"bool"})
    public native boolean endianLittle();

    @Cast({"bool"})
    public native boolean errorCorrectionSupport();

    public native int executionCapabilities();

    @opencv_core.Str
    public native BytePointer extensions();

    public native int globalMemCacheLineSize();

    @Cast({"size_t"})
    public native long globalMemCacheSize();

    public native int globalMemCacheType();

    @Cast({"size_t"})
    public native long globalMemSize();

    public native int halfFPConfig();

    @Cast({"bool"})
    public native boolean hostUnifiedMemory();

    @Cast({"size_t"})
    public native long image2DMaxHeight();

    @Cast({"size_t"})
    public native long image2DMaxWidth();

    @Cast({"size_t"})
    public native long image3DMaxDepth();

    @Cast({"size_t"})
    public native long image3DMaxHeight();

    @Cast({"size_t"})
    public native long image3DMaxWidth();

    @Cast({"uint"})
    public native int imageBaseAddressAlignment();

    @Cast({"bool"})
    public native boolean imageFromBufferSupport();

    @Cast({"size_t"})
    public native long imageMaxArraySize();

    @Cast({"size_t"})
    public native long imageMaxBufferSize();

    @Cast({"uint"})
    public native int imagePitchAlignment();

    @Cast({"bool"})
    public native boolean imageSupport();

    @Cast({"bool"})
    public native boolean intelSubgroupsSupport();

    @Cast({"bool"})
    public native boolean isAMD();

    @Cast({"bool"})
    public native boolean isExtensionSupported(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean isExtensionSupported(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean isIntel();

    @Cast({"bool"})
    public native boolean isNVidia();

    @Cast({"bool"})
    public native boolean linkerAvailable();

    @Cast({"size_t"})
    public native long localMemSize();

    public native int localMemType();

    public native int maxClockFrequency();

    public native int maxComputeUnits();

    public native int maxConstantArgs();

    @Cast({"size_t"})
    public native long maxConstantBufferSize();

    @Cast({"size_t"})
    public native long maxMemAllocSize();

    @Cast({"size_t"})
    public native long maxParameterSize();

    public native int maxReadImageArgs();

    public native int maxSamplers();

    @Cast({"size_t"})
    public native long maxWorkGroupSize();

    public native int maxWorkItemDims();

    public native void maxWorkItemSizes(@Cast({"size_t*"}) SizeTPointer sizeTPointer);

    public native int maxWriteImageArgs();

    public native int memBaseAddrAlign();

    @opencv_core.Str
    public native BytePointer name();

    public native int nativeVectorWidthChar();

    public native int nativeVectorWidthDouble();

    public native int nativeVectorWidthFloat();

    public native int nativeVectorWidthHalf();

    public native int nativeVectorWidthInt();

    public native int nativeVectorWidthLong();

    public native int nativeVectorWidthShort();

    public native int preferredVectorWidthChar();

    public native int preferredVectorWidthDouble();

    public native int preferredVectorWidthFloat();

    public native int preferredVectorWidthHalf();

    public native int preferredVectorWidthInt();

    public native int preferredVectorWidthLong();

    public native int preferredVectorWidthShort();

    @Cast({"size_t"})
    public native long printfBufferSize();

    @Cast({"size_t"})
    public native long profilingTimerResolution();

    public native Pointer ptr();

    @ByRef
    @Name({"operator ="})
    public native Device put(@ByRef @Const Device device);

    public native void set(Pointer pointer);

    public native int singleFPConfig();

    public native int type();

    public native int vendorID();

    @opencv_core.Str
    public native BytePointer vendorName();

    @opencv_core.Str
    public native BytePointer version();

    static {
        Loader.load();
    }

    public Device(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Device position(long j) {
        return (Device) super.position(j);
    }

    public Device getPointer(long j) {
        return new Device(this).position(this.position + j);
    }

    public Device() {
        super((Pointer) null);
        allocate();
    }

    public Device(Pointer pointer) {
        super((Pointer) null);
        allocate(pointer);
    }

    public Device(@ByRef @Const Device device) {
        super((Pointer) null);
        allocate(device);
    }
}
