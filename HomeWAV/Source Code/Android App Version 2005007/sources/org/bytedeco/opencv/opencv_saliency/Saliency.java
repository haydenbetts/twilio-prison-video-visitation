package org.bytedeco.opencv.opencv_saliency;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_saliency;

@Namespace("cv::saliency")
@Properties(inherit = {opencv_saliency.class})
@NoOffset
public class Saliency extends Algorithm {
    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public Saliency(Pointer pointer) {
        super(pointer);
    }
}
