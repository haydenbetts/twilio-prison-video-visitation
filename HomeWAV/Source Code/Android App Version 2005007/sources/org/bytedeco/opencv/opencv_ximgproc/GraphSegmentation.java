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

@Namespace("cv::ximgproc::segmentation")
@Properties(inherit = {opencv_ximgproc.class})
public class GraphSegmentation extends Algorithm {
    public native float getK();

    public native int getMinSize();

    public native double getSigma();

    public native void processImage(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void processImage(@ByVal Mat mat, @ByVal Mat mat2);

    public native void processImage(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void setK(float f);

    public native void setMinSize(int i);

    public native void setSigma(double d);

    static {
        Loader.load();
    }

    public GraphSegmentation(Pointer pointer) {
        super(pointer);
    }
}
