package org.bytedeco.opencv.opencv_cudaoptflow;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudaoptflow;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaoptflow.class})
public class NvidiaOpticalFlow_1_0 extends NvidiaHWOpticalFlow {
    public static final int NV_OF_PERF_LEVEL_FAST = 20;
    public static final int NV_OF_PERF_LEVEL_MAX = 21;
    public static final int NV_OF_PERF_LEVEL_MEDIUM = 10;
    public static final int NV_OF_PERF_LEVEL_SLOW = 5;
    public static final int NV_OF_PERF_LEVEL_UNDEFINED = 0;

    @opencv_core.Ptr
    public static native NvidiaOpticalFlow_1_0 create(int i, int i2);

    @opencv_core.Ptr
    public static native NvidiaOpticalFlow_1_0 create(int i, int i2, @Cast({"cv::cuda::NvidiaOpticalFlow_1_0::NVIDIA_OF_PERF_LEVEL"}) int i3, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2, @Cast({"bool"}) boolean z3, int i4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream2);

    public native void upSampler(@ByVal GpuMat gpuMat, int i, int i2, int i3, @ByVal GpuMat gpuMat2);

    public native void upSampler(@ByVal Mat mat, int i, int i2, int i3, @ByVal Mat mat2);

    public native void upSampler(@ByVal UMat uMat, int i, int i2, int i3, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public NvidiaOpticalFlow_1_0(Pointer pointer) {
        super(pointer);
    }
}
