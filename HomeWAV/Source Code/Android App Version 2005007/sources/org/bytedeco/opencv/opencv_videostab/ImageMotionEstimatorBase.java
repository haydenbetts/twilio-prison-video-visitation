package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class ImageMotionEstimatorBase extends Pointer {
    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @Cast({"bool*"}) boolean[] zArr);

    @Cast({"cv::videostab::MotionModel"})
    public native int motionModel();

    public native void setFrameMask(@ByVal GpuMat gpuMat);

    public native void setFrameMask(@ByVal Mat mat);

    public native void setFrameMask(@ByVal UMat uMat);

    public native void setMotionModel(@Cast({"cv::videostab::MotionModel"}) int i);

    static {
        Loader.load();
    }

    public ImageMotionEstimatorBase(Pointer pointer) {
        super(pointer);
    }
}
