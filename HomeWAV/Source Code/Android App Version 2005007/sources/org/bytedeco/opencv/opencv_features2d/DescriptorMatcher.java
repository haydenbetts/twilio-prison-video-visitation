package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.DMatchVector;
import org.bytedeco.opencv.opencv_core.DMatchVectorVector;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
@NoOffset
public class DescriptorMatcher extends Algorithm {
    public static final int BRUTEFORCE = 2;
    public static final int BRUTEFORCE_HAMMING = 4;
    public static final int BRUTEFORCE_HAMMINGLUT = 5;
    public static final int BRUTEFORCE_L1 = 3;
    public static final int BRUTEFORCE_SL2 = 6;
    public static final int FLANNBASED = 1;

    @opencv_core.Ptr
    public static native DescriptorMatcher create(@Cast({"const cv::DescriptorMatcher::MatcherType"}) int i);

    @opencv_core.Ptr
    public static native DescriptorMatcher create(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native DescriptorMatcher create(@opencv_core.Str BytePointer bytePointer);

    public native void add(@ByVal GpuMatVector gpuMatVector);

    public native void add(@ByVal MatVector matVector);

    public native void add(@ByVal UMatVector uMatVector);

    public native void clear();

    @opencv_core.Ptr
    public native DescriptorMatcher clone();

    @opencv_core.Ptr
    public native DescriptorMatcher clone(@Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean empty();

    @ByRef
    @Const
    public native MatVector getTrainDescriptors();

    @Cast({"bool"})
    public native boolean isMaskSupported();

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @Cast({"bool"}) boolean z);

    public native void knnMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i);

    public native void knnMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, int i, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @Cast({"bool"}) boolean z);

    public native void match(@ByVal GpuMat gpuMat, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal GpuMat gpuMat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    public native void match(@ByVal GpuMat gpuMat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector);

    public native void match(@ByVal GpuMat gpuMat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    public native void match(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    public native void match(@ByVal Mat mat, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal Mat mat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    public native void match(@ByVal Mat mat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector);

    public native void match(@ByVal Mat mat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    public native void match(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    public native void match(@ByVal UMat uMat, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal UMat uMat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector);

    public native void match(@ByVal UMat uMat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector);

    public native void match(@ByVal UMat uMat, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector);

    public native void match(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVector dMatchVector);

    public native void match(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVector dMatchVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal Mat mat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal Mat mat, @ByVal Mat mat2, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal UMat uMat, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @Cast({"bool"}) boolean z);

    public native void radiusMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f);

    public native void radiusMatch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef DMatchVectorVector dMatchVectorVector, float f, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @Cast({"bool"}) boolean z);

    public native void read(@opencv_core.Str String str);

    public native void read(@opencv_core.Str BytePointer bytePointer);

    public native void read(@ByRef @Const FileNode fileNode);

    public native void train();

    public native void write(@opencv_core.Str String str);

    public native void write(@opencv_core.Str BytePointer bytePointer);

    public native void write(@ByRef FileStorage fileStorage);

    public native void write(@opencv_core.Ptr FileStorage fileStorage, @opencv_core.Str String str);

    public native void write(@opencv_core.Ptr FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public DescriptorMatcher(Pointer pointer) {
        super(pointer);
    }
}
