package org.bytedeco.opencv.opencv_bgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_video.BackgroundSubtractor;
import org.bytedeco.opencv.presets.opencv_bgsegm;

@Namespace("cv::bgsegm")
@Properties(inherit = {opencv_bgsegm.class})
public class BackgroundSubtractorCNT extends BackgroundSubtractor {
    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, double d);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, double d);

    public native void getBackgroundImage(@ByVal GpuMat gpuMat);

    public native void getBackgroundImage(@ByVal Mat mat);

    public native void getBackgroundImage(@ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean getIsParallel();

    public native int getMaxPixelStability();

    public native int getMinPixelStability();

    @Cast({"bool"})
    public native boolean getUseHistory();

    public native void setIsParallel(@Cast({"bool"}) boolean z);

    public native void setMaxPixelStability(int i);

    public native void setMinPixelStability(int i);

    public native void setUseHistory(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public BackgroundSubtractorCNT(Pointer pointer) {
        super(pointer);
    }
}
