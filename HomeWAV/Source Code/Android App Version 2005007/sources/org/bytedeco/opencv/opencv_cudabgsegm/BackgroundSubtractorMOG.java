package org.bytedeco.opencv.opencv_cudabgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_video.BackgroundSubtractor;
import org.bytedeco.opencv.presets.opencv_cudabgsegm;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudabgsegm.class})
public class BackgroundSubtractorMOG extends BackgroundSubtractor {
    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, @ByRef Stream stream);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, double d, @ByRef Stream stream);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, double d, @ByRef Stream stream);

    public native void getBackgroundImage(@ByRef GpuMat gpuMat, @ByRef Stream stream);

    public native void getBackgroundImage(@ByVal Mat mat, @ByRef Stream stream);

    public native void getBackgroundImage(@ByVal UMat uMat, @ByRef Stream stream);

    public native double getBackgroundRatio();

    public native int getHistory();

    public native int getNMixtures();

    public native double getNoiseSigma();

    public native void setBackgroundRatio(double d);

    public native void setHistory(int i);

    public native void setNMixtures(int i);

    public native void setNoiseSigma(double d);

    static {
        Loader.load();
    }

    public BackgroundSubtractorMOG(Pointer pointer) {
        super(pointer);
    }
}
