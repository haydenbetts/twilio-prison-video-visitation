package org.bytedeco.opencv.opencv_plot;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_plot;

@Namespace("cv::plot")
@Properties(inherit = {opencv_plot.class})
public class Plot2d extends Algorithm {
    @opencv_core.Ptr
    public static native Plot2d create(@ByVal GpuMat gpuMat);

    @opencv_core.Ptr
    public static native Plot2d create(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @opencv_core.Ptr
    public static native Plot2d create(@ByVal Mat mat);

    @opencv_core.Ptr
    public static native Plot2d create(@ByVal Mat mat, @ByVal Mat mat2);

    @opencv_core.Ptr
    public static native Plot2d create(@ByVal UMat uMat);

    @opencv_core.Ptr
    public static native Plot2d create(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void render(@ByVal GpuMat gpuMat);

    public native void render(@ByVal Mat mat);

    public native void render(@ByVal UMat uMat);

    public native void setGridLinesNumber(int i);

    public native void setInvertOrientation(@Cast({"bool"}) boolean z);

    public native void setMaxX(double d);

    public native void setMaxY(double d);

    public native void setMinX(double d);

    public native void setMinY(double d);

    public native void setNeedPlotLine(@Cast({"bool"}) boolean z);

    public native void setPlotAxisColor(@ByVal Scalar scalar);

    public native void setPlotBackgroundColor(@ByVal Scalar scalar);

    public native void setPlotGridColor(@ByVal Scalar scalar);

    public native void setPlotLineColor(@ByVal Scalar scalar);

    public native void setPlotLineWidth(int i);

    public native void setPlotSize(int i, int i2);

    public native void setPlotTextColor(@ByVal Scalar scalar);

    public native void setPointIdxToPrint(int i);

    public native void setShowGrid(@Cast({"bool"}) boolean z);

    public native void setShowText(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public Plot2d(Pointer pointer) {
        super(pointer);
    }
}
