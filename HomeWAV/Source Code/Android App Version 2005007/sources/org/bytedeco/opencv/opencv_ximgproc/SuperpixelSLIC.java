package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc")
@Properties(inherit = {opencv_ximgproc.class})
public class SuperpixelSLIC extends Algorithm {
    public native void enforceLabelConnectivity();

    public native void enforceLabelConnectivity(int i);

    public native void getLabelContourMask(@ByVal GpuMat gpuMat);

    public native void getLabelContourMask(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z);

    public native void getLabelContourMask(@ByVal Mat mat);

    public native void getLabelContourMask(@ByVal Mat mat, @Cast({"bool"}) boolean z);

    public native void getLabelContourMask(@ByVal UMat uMat);

    public native void getLabelContourMask(@ByVal UMat uMat, @Cast({"bool"}) boolean z);

    public native void getLabels(@ByVal GpuMat gpuMat);

    public native void getLabels(@ByVal Mat mat);

    public native void getLabels(@ByVal UMat uMat);

    public native int getNumberOfSuperpixels();

    public native void iterate();

    public native void iterate(int i);

    static {
        Loader.load();
    }

    public SuperpixelSLIC(Pointer pointer) {
        super(pointer);
    }
}
