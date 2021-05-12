package org.bytedeco.opencv.opencv_flann;

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
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_flann;

@Namespace("cv::flann")
@Properties(inherit = {opencv_flann.class})
@NoOffset
public class Index extends Pointer {
    private native void allocate();

    private native void allocate(@ByVal GpuMat gpuMat, @ByRef @Const IndexParams indexParams);

    private native void allocate(@ByVal GpuMat gpuMat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i);

    private native void allocate(@ByVal Mat mat, @ByRef @Const IndexParams indexParams);

    private native void allocate(@ByVal Mat mat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i);

    private native void allocate(@ByVal UMat uMat, @ByRef @Const IndexParams indexParams);

    private native void allocate(@ByVal UMat uMat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i);

    private native void allocateArray(long j);

    public native void build(@ByVal GpuMat gpuMat, @ByRef @Const IndexParams indexParams);

    public native void build(@ByVal GpuMat gpuMat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i);

    public native void build(@ByVal Mat mat, @ByRef @Const IndexParams indexParams);

    public native void build(@ByVal Mat mat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i);

    public native void build(@ByVal UMat uMat, @ByRef @Const IndexParams indexParams);

    public native void build(@ByVal UMat uMat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i);

    @Cast({"cvflann::flann_algorithm_t"})
    public native int getAlgorithm();

    @Cast({"cvflann::flann_distance_t"})
    public native int getDistance();

    public native void knnSearch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    public native void knnSearch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, @ByRef(nullValue = "cv::flann::SearchParams()") @Const SearchParams searchParams);

    public native void knnSearch(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    public native void knnSearch(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, @ByRef(nullValue = "cv::flann::SearchParams()") @Const SearchParams searchParams);

    public native void knnSearch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    public native void knnSearch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, @ByRef(nullValue = "cv::flann::SearchParams()") @Const SearchParams searchParams);

    @Cast({"bool"})
    public native boolean load(@ByVal GpuMat gpuMat, @opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean load(@ByVal GpuMat gpuMat, @opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean load(@ByVal Mat mat, @opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean load(@ByVal Mat mat, @opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean load(@ByVal UMat uMat, @opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean load(@ByVal UMat uMat, @opencv_core.Str BytePointer bytePointer);

    public native int radiusSearch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i);

    public native int radiusSearch(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, double d, int i, @ByRef(nullValue = "cv::flann::SearchParams()") @Const SearchParams searchParams);

    public native int radiusSearch(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i);

    public native int radiusSearch(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, double d, int i, @ByRef(nullValue = "cv::flann::SearchParams()") @Const SearchParams searchParams);

    public native int radiusSearch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i);

    public native int radiusSearch(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, double d, int i, @ByRef(nullValue = "cv::flann::SearchParams()") @Const SearchParams searchParams);

    public native void release();

    public native void save(@opencv_core.Str String str);

    public native void save(@opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public Index(Pointer pointer) {
        super(pointer);
    }

    public Index(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Index position(long j) {
        return (Index) super.position(j);
    }

    public Index getPointer(long j) {
        return new Index((Pointer) this).position(this.position + j);
    }

    public Index() {
        super((Pointer) null);
        allocate();
    }

    public Index(@ByVal Mat mat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i) {
        super((Pointer) null);
        allocate(mat, indexParams, i);
    }

    public Index(@ByVal Mat mat, @ByRef @Const IndexParams indexParams) {
        super((Pointer) null);
        allocate(mat, indexParams);
    }

    public Index(@ByVal UMat uMat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i) {
        super((Pointer) null);
        allocate(uMat, indexParams, i);
    }

    public Index(@ByVal UMat uMat, @ByRef @Const IndexParams indexParams) {
        super((Pointer) null);
        allocate(uMat, indexParams);
    }

    public Index(@ByVal GpuMat gpuMat, @ByRef @Const IndexParams indexParams, @Cast({"cvflann::flann_distance_t"}) int i) {
        super((Pointer) null);
        allocate(gpuMat, indexParams, i);
    }

    public Index(@ByVal GpuMat gpuMat, @ByRef @Const IndexParams indexParams) {
        super((Pointer) null);
        allocate(gpuMat, indexParams);
    }
}
