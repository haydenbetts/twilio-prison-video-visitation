package org.bytedeco.opencv.opencv_objdetect;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Namespace("cv")
@Properties(inherit = {opencv_objdetect.class})
@NoOffset
public class HOGDescriptor extends Pointer {
    public static final int DEFAULT_NLEVELS = 64;
    public static final int DESCR_FORMAT_COL_BY_COL = 0;
    public static final int DESCR_FORMAT_ROW_BY_ROW = 1;
    public static final int L2Hys = 0;

    private native void allocate();

    private native void allocate(@opencv_core.Str String str);

    private native void allocate(@opencv_core.Str BytePointer bytePointer);

    private native void allocate(@ByVal Size size, @ByVal Size size2, @ByVal Size size3, @ByVal Size size4, int i);

    private native void allocate(@ByVal Size size, @ByVal Size size2, @ByVal Size size3, @ByVal Size size4, int i, int i2, double d, @Cast({"cv::HOGDescriptor::HistogramNormType"}) int i3, double d2, @Cast({"bool"}) boolean z, int i4, @Cast({"bool"}) boolean z2);

    private native void allocate(@ByRef @Const HOGDescriptor hOGDescriptor);

    private native void allocateArray(long j);

    @StdVector
    public static native FloatPointer getDaimlerPeopleDetector();

    @StdVector
    public static native FloatPointer getDefaultPeopleDetector();

    public native double L2HysThreshold();

    public native HOGDescriptor L2HysThreshold(double d);

    @ByRef
    public native Size blockSize();

    public native HOGDescriptor blockSize(Size size);

    @ByRef
    public native Size blockStride();

    public native HOGDescriptor blockStride(Size size);

    @ByRef
    public native Size cellSize();

    public native HOGDescriptor cellSize(Size size);

    @Cast({"bool"})
    public native boolean checkDetectorSize();

    public native void compute(@ByVal GpuMat gpuMat, @StdVector FloatBuffer floatBuffer);

    public native void compute(@ByVal GpuMat gpuMat, @StdVector FloatBuffer floatBuffer, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal GpuMat gpuMat, @StdVector FloatPointer floatPointer);

    public native void compute(@ByVal GpuMat gpuMat, @StdVector FloatPointer floatPointer, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal GpuMat gpuMat, @StdVector float[] fArr);

    public native void compute(@ByVal GpuMat gpuMat, @StdVector float[] fArr, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal Mat mat, @StdVector FloatBuffer floatBuffer);

    public native void compute(@ByVal Mat mat, @StdVector FloatBuffer floatBuffer, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal Mat mat, @StdVector FloatPointer floatPointer);

    public native void compute(@ByVal Mat mat, @StdVector FloatPointer floatPointer, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal Mat mat, @StdVector float[] fArr);

    public native void compute(@ByVal Mat mat, @StdVector float[] fArr, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal UMat uMat, @StdVector FloatBuffer floatBuffer);

    public native void compute(@ByVal UMat uMat, @StdVector FloatBuffer floatBuffer, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal UMat uMat, @StdVector FloatPointer floatPointer);

    public native void compute(@ByVal UMat uMat, @StdVector FloatPointer floatPointer, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void compute(@ByVal UMat uMat, @StdVector float[] fArr);

    public native void compute(@ByVal UMat uMat, @StdVector float[] fArr, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector);

    public native void computeGradient(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void computeGradient(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void computeGradient(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void computeGradient(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void computeGradient(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void computeGradient(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void copyTo(@ByRef HOGDescriptor hOGDescriptor);

    public native int derivAperture();

    public native HOGDescriptor derivAperture(int i);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @StdVector DoubleBuffer doubleBuffer);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @StdVector DoublePointer doublePointer);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @StdVector double[] dArr);

    public native void detect(@ByVal GpuMat gpuMat, @ByRef PointVector pointVector, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @StdVector DoubleBuffer doubleBuffer);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @StdVector DoublePointer doublePointer);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @StdVector double[] dArr);

    public native void detect(@ByVal Mat mat, @ByRef PointVector pointVector, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @StdVector DoubleBuffer doubleBuffer);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @StdVector DoublePointer doublePointer);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @StdVector double[] dArr);

    public native void detect(@ByVal UMat uMat, @ByRef PointVector pointVector, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, @ByRef(nullValue = "std::vector<cv::Point>()") @Const PointVector pointVector2);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector DoublePointer doublePointer);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector double[] dArr);

    public native void detectMultiScale(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector DoublePointer doublePointer);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector double[] dArr);

    public native void detectMultiScale(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector DoublePointer doublePointer);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector double[] dArr);

    public native void detectMultiScale(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2, double d2, double d3, @Cast({"bool"}) boolean z);

    public native void detectMultiScaleROI(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector DetectionROI detectionROI);

    public native void detectMultiScaleROI(@ByVal GpuMat gpuMat, @ByRef RectVector rectVector, @StdVector DetectionROI detectionROI, double d, int i);

    public native void detectMultiScaleROI(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector DetectionROI detectionROI);

    public native void detectMultiScaleROI(@ByVal Mat mat, @ByRef RectVector rectVector, @StdVector DetectionROI detectionROI, double d, int i);

    public native void detectMultiScaleROI(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector DetectionROI detectionROI);

    public native void detectMultiScaleROI(@ByVal UMat uMat, @ByRef RectVector rectVector, @StdVector DetectionROI detectionROI, double d, int i);

    public native void detectROI(@ByVal GpuMat gpuMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoubleBuffer doubleBuffer);

    public native void detectROI(@ByVal GpuMat gpuMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal GpuMat gpuMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoublePointer doublePointer);

    public native void detectROI(@ByVal GpuMat gpuMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal GpuMat gpuMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector double[] dArr);

    public native void detectROI(@ByVal GpuMat gpuMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal Mat mat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoubleBuffer doubleBuffer);

    public native void detectROI(@ByVal Mat mat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal Mat mat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoublePointer doublePointer);

    public native void detectROI(@ByVal Mat mat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal Mat mat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector double[] dArr);

    public native void detectROI(@ByVal Mat mat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal UMat uMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoubleBuffer doubleBuffer);

    public native void detectROI(@ByVal UMat uMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoubleBuffer doubleBuffer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal UMat uMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoublePointer doublePointer);

    public native void detectROI(@ByVal UMat uMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector DoublePointer doublePointer, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native void detectROI(@ByVal UMat uMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector double[] dArr);

    public native void detectROI(@ByVal UMat uMat, @ByRef @Const PointVector pointVector, @ByRef PointVector pointVector2, @StdVector double[] dArr, double d, @ByVal(nullValue = "cv::Size()") Size size, @ByVal(nullValue = "cv::Size()") Size size2);

    public native float free_coef();

    public native HOGDescriptor free_coef(float f);

    public native HOGDescriptor gammaCorrection(boolean z);

    @Cast({"bool"})
    public native boolean gammaCorrection();

    @Cast({"size_t"})
    public native long getDescriptorSize();

    public native double getWinSigma();

    public native void groupRectangles(@ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer, int i, double d);

    public native void groupRectangles(@ByRef RectVector rectVector, @StdVector DoublePointer doublePointer, int i, double d);

    public native void groupRectangles(@ByRef RectVector rectVector, @StdVector double[] dArr, int i, double d);

    @Cast({"cv::HOGDescriptor::HistogramNormType"})
    public native int histogramNormType();

    public native HOGDescriptor histogramNormType(int i);

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str String str);

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str BytePointer bytePointer);

    @Cast({"bool"})
    public native boolean load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native int nbins();

    public native HOGDescriptor nbins(int i);

    public native int nlevels();

    public native HOGDescriptor nlevels(int i);

    @ByRef
    public native UMat oclSvmDetector();

    public native HOGDescriptor oclSvmDetector(UMat uMat);

    @Cast({"bool"})
    public native boolean read(@ByRef FileNode fileNode);

    public native void save(@opencv_core.Str String str);

    public native void save(@opencv_core.Str String str, @opencv_core.Str String str2);

    public native void save(@opencv_core.Str BytePointer bytePointer);

    public native void save(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native void setSVMDetector(@ByVal GpuMat gpuMat);

    public native void setSVMDetector(@ByVal Mat mat);

    public native void setSVMDetector(@ByVal UMat uMat);

    public native HOGDescriptor signedGradient(boolean z);

    @Cast({"bool"})
    public native boolean signedGradient();

    @StdVector
    public native FloatPointer svmDetector();

    public native HOGDescriptor svmDetector(FloatPointer floatPointer);

    public native double winSigma();

    public native HOGDescriptor winSigma(double d);

    @ByRef
    public native Size winSize();

    public native HOGDescriptor winSize(Size size);

    public native void write(@ByRef FileStorage fileStorage, @opencv_core.Str String str);

    public native void write(@ByRef FileStorage fileStorage, @opencv_core.Str BytePointer bytePointer);

    static {
        Loader.load();
    }

    public HOGDescriptor(Pointer pointer) {
        super(pointer);
    }

    public HOGDescriptor(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public HOGDescriptor position(long j) {
        return (HOGDescriptor) super.position(j);
    }

    public HOGDescriptor getPointer(long j) {
        return new HOGDescriptor(this).position(this.position + j);
    }

    public HOGDescriptor() {
        super((Pointer) null);
        allocate();
    }

    public HOGDescriptor(@ByVal Size size, @ByVal Size size2, @ByVal Size size3, @ByVal Size size4, int i, int i2, double d, @Cast({"cv::HOGDescriptor::HistogramNormType"}) int i3, double d2, @Cast({"bool"}) boolean z, int i4, @Cast({"bool"}) boolean z2) {
        super((Pointer) null);
        allocate(size, size2, size3, size4, i, i2, d, i3, d2, z, i4, z2);
    }

    public HOGDescriptor(@ByVal Size size, @ByVal Size size2, @ByVal Size size3, @ByVal Size size4, int i) {
        super((Pointer) null);
        allocate(size, size2, size3, size4, i);
    }

    public HOGDescriptor(@opencv_core.Str BytePointer bytePointer) {
        super((Pointer) null);
        allocate(bytePointer);
    }

    public HOGDescriptor(@opencv_core.Str String str) {
        super((Pointer) null);
        allocate(str);
    }

    public HOGDescriptor(@ByRef @Const HOGDescriptor hOGDescriptor) {
        super((Pointer) null);
        allocate(hOGDescriptor);
    }
}
