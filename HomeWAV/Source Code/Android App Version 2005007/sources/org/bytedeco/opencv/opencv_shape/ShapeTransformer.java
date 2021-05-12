package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.DMatchVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
public class ShapeTransformer extends Algorithm {
    public native float applyTransformation(@ByVal GpuMat gpuMat);

    public native float applyTransformation(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2);

    public native float applyTransformation(@ByVal Mat mat);

    public native float applyTransformation(@ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2);

    public native float applyTransformation(@ByVal UMat uMat);

    public native float applyTransformation(@ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2);

    public native void estimateTransformation(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVector dMatchVector);

    public native void estimateTransformation(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVector dMatchVector);

    public native void estimateTransformation(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVector dMatchVector);

    public native void warpImage(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void warpImage(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void warpImage(@ByVal Mat mat, @ByVal Mat mat2);

    public native void warpImage(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    public native void warpImage(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void warpImage(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    static {
        Loader.load();
    }

    public ShapeTransformer(Pointer pointer) {
        super(pointer);
    }
}
