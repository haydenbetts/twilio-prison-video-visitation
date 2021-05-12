package org.bytedeco.opencv.opencv_features2d;

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
import org.bytedeco.opencv.opencv_core.IntVectorVector;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
@NoOffset
public class BOWImgDescriptorExtractor extends Pointer {
    private native void allocate(@opencv_core.Ptr DescriptorMatcher descriptorMatcher);

    private native void allocate(@Cast({"cv::DescriptorExtractor*"}) @opencv_core.Ptr Feature2D feature2D, @opencv_core.Ptr DescriptorMatcher descriptorMatcher);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, IntVectorVector intVectorVector);

    public native void compute(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, IntVectorVector intVectorVector, Mat mat);

    public native void compute(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, @ByVal Mat mat2);

    public native void compute(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, @ByVal Mat mat2, IntVectorVector intVectorVector, Mat mat3);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2, IntVectorVector intVectorVector);

    public native void compute(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, @ByVal UMat uMat2);

    public native void compute(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, @ByVal UMat uMat2, IntVectorVector intVectorVector, Mat mat);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, IntVectorVector intVectorVector);

    public native int descriptorSize();

    public native int descriptorType();

    @ByRef
    @Const
    public native Mat getVocabulary();

    public native void setVocabulary(@ByRef @Const Mat mat);

    static {
        Loader.load();
    }

    public BOWImgDescriptorExtractor(Pointer pointer) {
        super(pointer);
    }

    public BOWImgDescriptorExtractor(@Cast({"cv::DescriptorExtractor*"}) @opencv_core.Ptr Feature2D feature2D, @opencv_core.Ptr DescriptorMatcher descriptorMatcher) {
        super((Pointer) null);
        allocate(feature2D, descriptorMatcher);
    }

    public BOWImgDescriptorExtractor(@opencv_core.Ptr DescriptorMatcher descriptorMatcher) {
        super((Pointer) null);
        allocate(descriptorMatcher);
    }
}
