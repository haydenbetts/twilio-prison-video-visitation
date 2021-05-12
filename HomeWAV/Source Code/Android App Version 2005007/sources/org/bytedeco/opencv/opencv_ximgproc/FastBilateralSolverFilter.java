package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc")
@Properties(inherit = {opencv_ximgproc.class})
public class FastBilateralSolverFilter extends Algorithm {
    public native void filter(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void filter(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void filter(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    static {
        Loader.load();
    }

    public FastBilateralSolverFilter(Pointer pointer) {
        super(pointer);
    }
}
