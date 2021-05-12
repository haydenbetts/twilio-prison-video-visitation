package org.bytedeco.opencv.opencv_cudafeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.DMatchVector;
import org.bytedeco.opencv.opencv_core.DMatchVectorVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudafeatures2d;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudafeatures2d.class})
public class DescriptorMatcher extends Algorithm {
    @opencv_core.Ptr
    public static native DescriptorMatcher createBFMatcher();

    @opencv_core.Ptr
    public static native DescriptorMatcher createBFMatcher(int i);

    public native void add(@ByRef @Const GpuMatVector gpuMatVector);

    public native void clear();

    @Cast({"bool"})
    public native boolean empty();

    @ByRef
    @Const
    public native GpuMatVector getTrainDescriptors();

    @Cast({"bool"})
    public native boolean isMaskSupported();

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @Cast({"bool"}) boolean z);

    public native void knnMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    public native void knnMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void knnMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    public native void knnMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void knnMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, int i);

    public native void knnMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void knnMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    public native void knnMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void knnMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    public native void knnMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void knnMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    public native void knnMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void knnMatchConvert(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector);

    public native void knnMatchConvert(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, @Cast({"bool"}) boolean z);

    public native void knnMatchConvert(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector);

    public native void knnMatchConvert(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, @Cast({"bool"}) boolean z);

    public native void knnMatchConvert(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector);

    public native void knnMatchConvert(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, @Cast({"bool"}) boolean z);

    public native void match(@ByVal GpuMat gpuMat, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal GpuMat gpuMat, @ByRef DMatchVector dMatchVector, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector);

    public native void match(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void match(@ByVal Mat mat, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal Mat mat, @ByRef DMatchVector dMatchVector, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector);

    public native void match(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void match(@ByVal UMat uMat, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal UMat uMat, @ByRef DMatchVector dMatchVector, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector);

    public native void match(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    public native void matchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void matchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void matchAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void matchAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void matchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void matchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void matchConvert(@ByVal GpuMat gpuMat, @ByRef DMatchVector dMatchVector);

    public native void matchConvert(@ByVal Mat mat, @ByRef DMatchVector dMatchVector);

    public native void matchConvert(@ByVal UMat uMat, @ByRef DMatchVector dMatchVector);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @Cast({"bool"}) boolean z);

    public native void radiusMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f);

    public native void radiusMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, float f, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void radiusMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f);

    public native void radiusMatchAsync(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void radiusMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, float f);

    public native void radiusMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, float f, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void radiusMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f);

    public native void radiusMatchAsync(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void radiusMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, float f);

    public native void radiusMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, float f, @ByRef(nullValue = "std::vector<cv::cuda::GpuMat>()") @Const GpuMatVector gpuMatVector, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void radiusMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f);

    public native void radiusMatchAsync(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void radiusMatchConvert(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector);

    public native void radiusMatchConvert(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, @Cast({"bool"}) boolean z);

    public native void radiusMatchConvert(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector);

    public native void radiusMatchConvert(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, @Cast({"bool"}) boolean z);

    public native void radiusMatchConvert(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector);

    public native void radiusMatchConvert(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, @Cast({"bool"}) boolean z);

    public native void train();

    static {
        Loader.load();
    }

    public DescriptorMatcher(Pointer pointer) {
        super(pointer);
    }
}
