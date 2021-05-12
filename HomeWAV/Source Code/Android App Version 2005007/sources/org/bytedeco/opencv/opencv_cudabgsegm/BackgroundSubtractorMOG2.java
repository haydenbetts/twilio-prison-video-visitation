package org.bytedeco.opencv.opencv_cudabgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_cudabgsegm;

@Properties(inherit = {opencv_cudabgsegm.class})
@Name({"cv::cuda::BackgroundSubtractorMOG2"})
public class BackgroundSubtractorMOG2 extends org.bytedeco.opencv.opencv_video.BackgroundSubtractorMOG2 {
    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByRef Stream stream);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByRef Stream stream);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByRef Stream stream);

    public native void getBackgroundImage(@ByRef GpuMat gpuMat, @ByRef Stream stream);

    public native void getBackgroundImage(@ByVal Mat mat, @ByRef Stream stream);

    public native void getBackgroundImage(@ByVal UMat uMat, @ByRef Stream stream);

    static {
        Loader.load();
    }

    public BackgroundSubtractorMOG2(Pointer pointer) {
        super(pointer);
    }
}
