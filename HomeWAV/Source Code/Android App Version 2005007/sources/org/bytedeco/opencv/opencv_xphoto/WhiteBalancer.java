package org.bytedeco.opencv.opencv_xphoto;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_xphoto;

@Namespace("cv::xphoto")
@Properties(inherit = {opencv_xphoto.class})
public class WhiteBalancer extends Algorithm {
    public native void balanceWhite(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void balanceWhite(@ByVal Mat mat, @ByVal Mat mat2);

    public native void balanceWhite(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public WhiteBalancer(Pointer pointer) {
        super(pointer);
    }
}
