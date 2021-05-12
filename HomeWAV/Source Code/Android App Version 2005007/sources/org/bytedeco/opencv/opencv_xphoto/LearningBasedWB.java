package org.bytedeco.opencv.opencv_xphoto;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_xphoto;

@Namespace("cv::xphoto")
@Properties(inherit = {opencv_xphoto.class})
public class LearningBasedWB extends WhiteBalancer {
    public native void extractSimpleFeatures(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void extractSimpleFeatures(@ByVal Mat mat, @ByVal Mat mat2);

    public native void extractSimpleFeatures(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native int getHistBinNum();

    public native int getRangeMaxVal();

    public native float getSaturationThreshold();

    public native void setHistBinNum(int i);

    public native void setRangeMaxVal(int i);

    public native void setSaturationThreshold(float f);

    static {
        Loader.load();
    }

    public LearningBasedWB(Pointer pointer) {
        super(pointer);
    }
}
