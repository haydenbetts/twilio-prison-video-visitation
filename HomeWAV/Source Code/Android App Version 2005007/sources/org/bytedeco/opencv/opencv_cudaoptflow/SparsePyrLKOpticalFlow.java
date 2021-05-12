package org.bytedeco.opencv.opencv_cudaoptflow;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudaoptflow;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaoptflow.class})
public class SparsePyrLKOpticalFlow extends SparseOpticalFlow {
    @opencv_core.Ptr
    public static native SparsePyrLKOpticalFlow create();

    @opencv_core.Ptr
    public static native SparsePyrLKOpticalFlow create(@ByVal(nullValue = "cv::Size(21, 21)") Size size, int i, int i2, @Cast({"bool"}) boolean z);

    public native int getMaxLevel();

    public native int getNumIters();

    @Cast({"bool"})
    public native boolean getUseInitialFlow();

    @ByVal
    public native Size getWinSize();

    public native void setMaxLevel(int i);

    public native void setNumIters(int i);

    public native void setUseInitialFlow(@Cast({"bool"}) boolean z);

    public native void setWinSize(@ByVal Size size);

    static {
        Loader.load();
    }

    public SparsePyrLKOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
