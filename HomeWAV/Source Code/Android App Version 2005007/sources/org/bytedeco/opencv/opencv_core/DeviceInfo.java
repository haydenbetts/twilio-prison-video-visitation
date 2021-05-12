package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class DeviceInfo extends Pointer {
    public static final int ComputeModeDefault = 0;
    public static final int ComputeModeExclusive = 1;
    public static final int ComputeModeExclusiveProcess = 3;
    public static final int ComputeModeProhibited = 2;

    private native void allocate();

    private native void allocate(int i);

    @Cast({"bool"})
    public native boolean ECCEnabled();

    public native int asyncEngineCount();

    @Cast({"bool"})
    public native boolean canMapHostMemory();

    public native int clockRate();

    @Cast({"cv::cuda::DeviceInfo::ComputeMode"})
    public native int computeMode();

    @Cast({"bool"})
    public native boolean concurrentKernels();

    public native int deviceID();

    @Cast({"size_t"})
    public native long freeMemory();

    @Cast({"bool"})
    public native boolean integrated();

    @Cast({"bool"})
    public native boolean isCompatible();

    @Cast({"bool"})
    public native boolean kernelExecTimeoutEnabled();

    public native int l2CacheSize();

    public native int majorVersion();

    @Cast({"cv::Vec3i*"})
    @ByVal
    public native Point3i maxGridSize();

    public native int maxSurface1D();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxSurface1DLayered();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxSurface2D();

    @Cast({"cv::Vec3i*"})
    @ByVal
    public native Point3i maxSurface2DLayered();

    @Cast({"cv::Vec3i*"})
    @ByVal
    public native Point3i maxSurface3D();

    public native int maxSurfaceCubemap();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxSurfaceCubemapLayered();

    public native int maxTexture1D();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxTexture1DLayered();

    public native int maxTexture1DLinear();

    public native int maxTexture1DMipmap();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxTexture2D();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxTexture2DGather();

    @Cast({"cv::Vec3i*"})
    @ByVal
    public native Point3i maxTexture2DLayered();

    @Cast({"cv::Vec3i*"})
    @ByVal
    public native Point3i maxTexture2DLinear();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxTexture2DMipmap();

    @Cast({"cv::Vec3i*"})
    @ByVal
    public native Point3i maxTexture3D();

    public native int maxTextureCubemap();

    @Cast({"cv::Vec2i*"})
    @ByVal
    public native Point maxTextureCubemapLayered();

    @Cast({"cv::Vec3i*"})
    @ByVal
    public native Point3i maxThreadsDim();

    public native int maxThreadsPerBlock();

    public native int maxThreadsPerMultiProcessor();

    @Cast({"size_t"})
    public native long memPitch();

    public native int memoryBusWidth();

    public native int memoryClockRate();

    public native int minorVersion();

    public native int multiProcessorCount();

    @Cast({"const char*"})
    public native BytePointer name();

    public native int pciBusID();

    public native int pciDeviceID();

    public native int pciDomainID();

    public native void queryMemory(@ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer, @ByRef @Cast({"size_t*"}) SizeTPointer sizeTPointer2);

    public native int regsPerBlock();

    @Cast({"size_t"})
    public native long sharedMemPerBlock();

    @Cast({"bool"})
    public native boolean supports(@Cast({"cv::cuda::FeatureSet"}) int i);

    @Cast({"size_t"})
    public native long surfaceAlignment();

    @Cast({"bool"})
    public native boolean tccDriver();

    @Cast({"size_t"})
    public native long textureAlignment();

    @Cast({"size_t"})
    public native long texturePitchAlignment();

    @Cast({"size_t"})
    public native long totalConstMem();

    @Cast({"size_t"})
    public native long totalGlobalMem();

    @Cast({"size_t"})
    public native long totalMemory();

    @Cast({"bool"})
    public native boolean unifiedAddressing();

    public native int warpSize();

    static {
        Loader.load();
    }

    public DeviceInfo(Pointer pointer) {
        super(pointer);
    }

    public DeviceInfo() {
        super((Pointer) null);
        allocate();
    }

    public DeviceInfo(int i) {
        super((Pointer) null);
        allocate(i);
    }
}
