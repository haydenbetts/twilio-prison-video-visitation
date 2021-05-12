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
public class MotionEstimatorRansacL2 extends MotionEstimatorBase {
    private native void allocate();

    private native void allocate(@Cast({"cv::videostab::MotionModel"}) int i);

    @ByVal
    public native Mat estimate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @ByVal
    public native Mat estimate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Cast({"bool*"}) boolean[] zArr);

    @ByVal
    public native Mat estimate(@ByVal Mat mat, @ByVal Mat mat2);

    @ByVal
    public native Mat estimate(@ByVal Mat mat, @ByVal Mat mat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByVal Mat mat, @ByVal Mat mat2, @Cast({"bool*"}) boolean[] zArr);

    @ByVal
    public native Mat estimate(@ByVal UMat uMat, @ByVal UMat uMat2);

    @ByVal
    public native Mat estimate(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool*"}) BoolPointer boolPointer);

    @ByVal
    public native Mat estimate(@ByVal UMat uMat, @ByVal UMat uMat2, @Cast({"bool*"}) boolean[] zArr);

    public native float minInlierRatio();

    @ByVal
    public native RansacParams ransacParams();

    public native void setMinInlierRatio(float f);

    public native void setRansacParams(@ByRef @Const RansacParams ransacParams);

    static {
        Loader.load();
    }

    public MotionEstimatorRansacL2(Pointer pointer) {
        super(pointer);
    }

    public MotionEstimatorRansacL2(@Cast({"cv::videostab::MotionModel"}) int i) {
        super((Pointer) null);
        allocate(i);
    }

    public MotionEstimatorRansacL2() {
        super((Pointer) null);
        allocate();
    }
}
