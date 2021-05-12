package org.bytedeco.opencv.opencv_phase_unwrapping;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_phase_unwrapping;

@Namespace("cv::phase_unwrapping")
@Properties(inherit = {opencv_phase_unwrapping.class})
public class PhaseUnwrapping extends Algorithm {
    public native void unwrapPhaseMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void unwrapPhaseMap(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void unwrapPhaseMap(@ByVal Mat mat, @ByVal Mat mat2);

    public native void unwrapPhaseMap(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void unwrapPhaseMap(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void unwrapPhaseMap(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    static {
        Loader.load();
    }

    public PhaseUnwrapping(Pointer pointer) {
        super(pointer);
    }
}
