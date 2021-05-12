package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.KeyPointVectorVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
public class Feature2D extends Algorithm {
    private native void allocate();

    private native void allocateArray(long j);

    public native void compute(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMatVector gpuMatVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal GpuMatVector gpuMatVector2);

    public native void compute(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, @ByVal Mat mat2);

    public native void compute(@ByVal MatVector matVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal MatVector matVector2);

    public native void compute(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, @ByVal UMat uMat2);

    public native void compute(@ByVal UMatVector uMatVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal UMatVector uMatVector2);

    public native int defaultNorm();

    public native int descriptorSize();

    public native int descriptorType();

    public native void detect(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat2);

    public native void detect(@ByVal GpuMatVector gpuMatVector, @ByRef KeyPointVectorVector keyPointVectorVector);

    public native void detect(@ByVal GpuMatVector gpuMatVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2);

    public native void detect(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector);

    public native void detect(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat2);

    public native void detect(@ByVal MatVector matVector, @ByRef KeyPointVectorVector keyPointVectorVector);

    public native void detect(@ByVal MatVector matVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector2);

    public native void detect(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector);

    public native void detect(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat2);

    public native void detect(@ByVal UMatVector uMatVector, @ByRef KeyPointVectorVector keyPointVectorVector);

    public native void detect(@ByVal UMatVector uMatVector, @ByRef KeyPointVectorVector keyPointVectorVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector2);

    public native void detectAndCompute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef KeyPointVector keyPointVector, @ByVal GpuMat gpuMat3);

    public native void detectAndCompute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef KeyPointVector keyPointVector, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    public native void detectAndCompute(@ByVal Mat mat, @ByVal Mat mat2, @ByRef KeyPointVector keyPointVector, @ByVal Mat mat3);

    public native void detectAndCompute(@ByVal Mat mat, @ByVal Mat mat2, @ByRef KeyPointVector keyPointVector, @ByVal Mat mat3, @Cast({"bool"}) boolean z);

    public native void detectAndCompute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef KeyPointVector keyPointVector, @ByVal UMat uMat3);

    public native void detectAndCompute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef KeyPointVector keyPointVector, @ByVal UMat uMat3, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean empty();

    @opencv_core.Str
    public native BytePointer getDefaultName();

    public native void read(@opencv_core.Str String str);

    public native void read(@opencv_core.Str BytePointer bytePointer);

    public native void read(@ByRef @Const FileNode fileNode);

    public native void write(@opencv_core.Str String str);

    public native void write(@opencv_core.Str BytePointer bytePointer);

    public native void write(@ByRef FileStorage fileStorage);

    public native void write(@opencv_core.Ptr FileStorage fileStorage, @opencv_core.Str String str);

    public native void write(@opencv_core.Ptr FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public Feature2D() {
        super((Pointer) null);
        allocate();
    }

    public Feature2D(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Feature2D(Pointer pointer) {
        super(pointer);
    }

    public Feature2D position(long j) {
        return (Feature2D) super.position(j);
    }

    public Feature2D getPointer(long j) {
        return new Feature2D((Pointer) this).position(this.position + j);
    }
}
