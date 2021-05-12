package org.bytedeco.opencv.opencv_cudaoptflow;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudaoptflow;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaoptflow.class})
public class BroxOpticalFlow extends DenseOpticalFlow {
    @opencv_core.Ptr
    public static native BroxOpticalFlow create();

    @opencv_core.Ptr
    public static native BroxOpticalFlow create(double d, double d2, double d3, int i, int i2, int i3);

    public native double getFlowSmoothness();

    public native double getGradientConstancyImportance();

    public native int getInnerIterations();

    public native int getOuterIterations();

    public native double getPyramidScaleFactor();

    public native int getSolverIterations();

    public native void setFlowSmoothness(double d);

    public native void setGradientConstancyImportance(double d);

    public native void setInnerIterations(int i);

    public native void setOuterIterations(int i);

    public native void setPyramidScaleFactor(double d);

    public native void setSolverIterations(int i);

    static {
        Loader.load();
    }

    public BroxOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
