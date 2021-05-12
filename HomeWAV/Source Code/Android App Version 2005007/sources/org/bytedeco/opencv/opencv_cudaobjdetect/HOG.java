package org.bytedeco.opencv.opencv_cudaobjdetect;

import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudaobjdetect;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaobjdetect.class})
public class HOG extends Algorithm {
    @opencv_core.Ptr
    public static native HOG create();

    @opencv_core.Ptr
    public static native HOG create(@ByVal(nullValue = "cv::Size(64, 128)") Size size, @ByVal(nullValue = "cv::Size(16, 16)") Size size2, @ByVal(nullValue = "cv::Size(8, 8)") Size size3, @ByVal(nullValue = "cv::Size(8, 8)") Size size4, int i);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoubleBuffer doubleBuffer);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoublePointer doublePointer);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") double[] dArr);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoubleBuffer doubleBuffer);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoublePointer doublePointer);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") double[] dArr);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoubleBuffer doubleBuffer);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoublePointer doublePointer);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") double[] dArr);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoubleBuffer doubleBuffer);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoublePointer doublePointer);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") double[] dArr);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoubleBuffer doubleBuffer);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoublePointer doublePointer);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") double[] dArr);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoubleBuffer doubleBuffer);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") DoublePointer doublePointer);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @Cast({"double*", "std::vector<double>*"}) @StdVector("double") double[] dArr);

    public native void detectMultiScaleWithoutConf(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector);

    public native void detectMultiScaleWithoutConf(@ByVal Mat mat, @ByRef RectVector rectVector);

    public native void detectMultiScaleWithoutConf(@ByVal UMat uMat, @ByRef RectVector rectVector);

    public native void detectWithoutConf(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector);

    public native void detectWithoutConf(@ByVal Mat mat, @ByRef PointVector pointVector);

    public native void detectWithoutConf(@ByVal UMat uMat, @ByRef PointVector pointVector);

    @Cast({"size_t"})
    public native long getBlockHistogramSize();

    @ByVal
    public native Mat getDefaultPeopleDetector();

    @Cast({"cv::HOGDescriptor::DescriptorStorageFormat"})
    public native int getDescriptorFormat();

    @Cast({"size_t"})
    public native long getDescriptorSize();

    @Cast({"bool"})
    public native boolean getGammaCorrection();

    public native int getGroupThreshold();

    public native double getHitThreshold();

    public native double getL2HysThreshold();

    public native int getNumLevels();

    public native double getScaleFactor();

    public native double getWinSigma();

    @ByVal
    public native Size getWinStride();

    public native void setDescriptorFormat(@Cast({"cv::HOGDescriptor::DescriptorStorageFormat"}) int i);

    public native void setGammaCorrection(@Cast({"bool"}) boolean z);

    public native void setGroupThreshold(int i);

    public native void setHitThreshold(double d);

    public native void setL2HysThreshold(double d);

    public native void setNumLevels(int i);

    public native void setSVMDetector(@ByVal GpuMat gpuMat);

    public native void setSVMDetector(@ByVal Mat mat);

    public native void setSVMDetector(@ByVal UMat uMat);

    public native void setScaleFactor(double d);

    public native void setWinSigma(double d);

    public native void setWinStride(@ByVal Size size);

    static {
        Loader.load();
    }

    public HOG(Pointer pointer) {
        super(pointer);
    }
}
