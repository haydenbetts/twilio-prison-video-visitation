package org.bytedeco.opencv.opencv_xfeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class AffineFeature2D extends Feature2D {
    @opencv_core.Ptr
    public static native AffineFeature2D create(@Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    @opencv_core.Ptr
    public static native AffineFeature2D create(@Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D, @Cast({"cv::DescriptorExtractor*"}) @opencv_core.Ptr Feature2D feature2D2);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector Elliptic_KeyPoint elliptic_KeyPoint);

    public native void detect(@ByVal GpuMat gpuMat, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    public native void detect(@ByVal Mat mat, @StdVector Elliptic_KeyPoint elliptic_KeyPoint);

    public native void detect(@ByVal Mat mat, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    public native void detect(@ByVal UMat uMat, @StdVector Elliptic_KeyPoint elliptic_KeyPoint);

    public native void detect(@ByVal UMat uMat, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    public native void detectAndCompute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal GpuMat gpuMat3);

    public native void detectAndCompute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    public native void detectAndCompute(@ByVal Mat mat, @ByVal Mat mat2, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal Mat mat3);

    public native void detectAndCompute(@ByVal Mat mat, @ByVal Mat mat2, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal Mat mat3, @Cast({"bool"}) boolean z);

    public native void detectAndCompute(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal UMat uMat3);

    public native void detectAndCompute(@ByVal UMat uMat, @ByVal UMat uMat2, @StdVector Elliptic_KeyPoint elliptic_KeyPoint, @ByVal UMat uMat3, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public AffineFeature2D(Pointer pointer) {
        super(pointer);
    }
}
