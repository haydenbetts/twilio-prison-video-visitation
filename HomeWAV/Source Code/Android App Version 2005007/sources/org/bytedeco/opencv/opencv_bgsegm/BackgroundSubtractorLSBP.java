package org.bytedeco.opencv.opencv_bgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_video.BackgroundSubtractor;
import org.bytedeco.opencv.presets.opencv_bgsegm;

@Namespace("cv::bgsegm")
@Properties(inherit = {opencv_bgsegm.class})
public class BackgroundSubtractorLSBP extends BackgroundSubtractor {
    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, double d);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, double d);

    public native void getBackgroundImage(@ByVal GpuMat gpuMat);

    public native void getBackgroundImage(@ByVal Mat mat);

    public native void getBackgroundImage(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public BackgroundSubtractorLSBP(Pointer pointer) {
        super(pointer);
    }
}
