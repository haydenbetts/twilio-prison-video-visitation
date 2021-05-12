package org.bytedeco.opencv.opencv_cudaobjdetect;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudaobjdetect;

@Properties(inherit = {opencv_cudaobjdetect.class})
@Name({"cv::cuda::CascadeClassifier"})
public class CudaCascadeClassifier extends Algorithm {
    @opencv_core.Ptr
    public static native CudaCascadeClassifier create(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native CudaCascadeClassifier create(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native CudaCascadeClassifier create(@ByRef @Const FileStorage fileStorage);

    public native void convert(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector);

    public native void convert(@ByVal Mat mat, @ByRef RectVector rectVector);

    public native void convert(@ByVal UMat uMat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detectMultiScale(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detectMultiScale(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detectMultiScale(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detectMultiScale(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    @ByVal
    public native Size getClassifierSize();

    @Cast({"bool"})
    public native boolean getFindLargestObject();

    public native int getMaxNumObjects();

    @ByVal
    public native Size getMaxObjectSize();

    public native int getMinNeighbors();

    @ByVal
    public native Size getMinObjectSize();

    public native double getScaleFactor();

    public native void setFindLargestObject(@Cast({"bool"}) boolean z);

    public native void setMaxNumObjects(int i);

    public native void setMaxObjectSize(@ByVal Size size);

    public native void setMinNeighbors(int i);

    public native void setMinObjectSize(@ByVal Size size);

    public native void setScaleFactor(double d);

    static {
        Loader.load();
    }

    public CudaCascadeClassifier(Pointer pointer) {
        super(pointer);
    }
}
