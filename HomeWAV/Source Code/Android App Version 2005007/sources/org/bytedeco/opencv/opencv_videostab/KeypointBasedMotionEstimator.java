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
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class KeypointBasedMotionEstimator extends ImageMotionEstimatorBase {
    private native void allocate(@opencv_core.Ptr MotionEstimatorBase motionEstimatorBase);

    @Cast({"cv::FeatureDetector*"})
    @opencv_core.Ptr
    public native Feature2D detector();

    @ByVal
    public native Mat estimate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @ByVal
    public native Mat estimate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool*"}) boolean[] zArr);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @Cast({"bool*"}) boolean[] zArr);

    @ByVal
    public native Mat estimate(@ByVal UMat uMat, @ByVal UMat uMat2);

    @ByVal
    public native Mat estimate(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool*"}) boolean[] zArr);

    @Cast({"cv::videostab::MotionModel"})
    public native int motionModel();

    @opencv_core.Ptr
    public native ISparseOptFlowEstimator opticalFlowEstimator();

    @opencv_core.Ptr
    public native IOutlierRejector outlierRejector();

    public native void setDetector(@Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    public native void setFrameMask(@ByVal GpuMat gpuMat);

    public native void setFrameMask(@ByVal Mat mat);

    public native void setFrameMask(@ByVal UMat uMat);

    public native void setMotionModel(@Cast({"cv::videostab::MotionModel"}) int i);

    public native void setOpticalFlowEstimator(@opencv_core.Ptr ISparseOptFlowEstimator iSparseOptFlowEstimator);

    public native void setOutlierRejector(@opencv_core.Ptr IOutlierRejector iOutlierRejector);

    static {
        Loader.load();
    }

    public KeypointBasedMotionEstimator(Pointer pointer) {
        super(pointer);
    }

    public KeypointBasedMotionEstimator(@opencv_core.Ptr MotionEstimatorBase motionEstimatorBase) {
        super((Pointer) null);
        allocate(motionEstimatorBase);
    }
}
