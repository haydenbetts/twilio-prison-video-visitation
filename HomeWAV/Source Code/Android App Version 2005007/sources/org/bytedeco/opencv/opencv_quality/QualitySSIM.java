package org.bytedeco.opencv.opencv_quality;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_quality;

@Namespace("cv::quality")
@Properties(inherit = {opencv_quality.class})
@NoOffset
public class QualitySSIM extends QualityBase {
    @ByVal
    public static native Scalar compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @ByVal
    public static native Scalar compute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @ByVal
    public static native Scalar compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @opencv_core.Ptr
    public static native QualitySSIM create(@ByVal GpuMat gpuMat);

    @opencv_core.Ptr
    public static native QualitySSIM create(@ByVal Mat mat);

    @opencv_core.Ptr
    public static native QualitySSIM create(@ByVal UMat uMat);

    public native void clear();

    @ByVal
    public native Scalar compute(@ByVal GpuMat gpuMat);

    @ByVal
    public native Scalar compute(@ByVal Mat mat);

    @ByVal
    public native Scalar compute(@ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean empty();

    static {
        Loader.load();
    }

    public QualitySSIM(Pointer pointer) {
        super(pointer);
    }
}
