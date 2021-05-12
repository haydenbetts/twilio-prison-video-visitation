package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class LogisticRegression extends StatModel {
    public static final int BATCH = 0;
    public static final int MINI_BATCH = 1;
    public static final int REG_DISABLE = -1;
    public static final int REG_L1 = 0;
    public static final int REG_L2 = 1;

    @opencv_core.Ptr
    public static native LogisticRegression create();

    @opencv_core.Ptr
    public static native LogisticRegression load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native LogisticRegression load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native LogisticRegression load(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native LogisticRegression load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native int getIterations();

    public native double getLearningRate();

    public native int getMiniBatchSize();

    public native int getRegularization();

    @ByVal
    public native TermCriteria getTermCriteria();

    public native int getTrainMethod();

    @ByVal
    public native Mat get_learnt_thetas();

    public native float predict(@ByVal GpuMat gpuMat);

    public native float predict(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, int i);

    public native float predict(@ByVal Mat mat);

    public native float predict(@ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, int i);

    public native float predict(@ByVal UMat uMat);

    public native float predict(@ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, int i);

    public native void setIterations(int i);

    public native void setLearningRate(double d);

    public native void setMiniBatchSize(int i);

    public native void setRegularization(int i);

    public native void setTermCriteria(@ByVal TermCriteria termCriteria);

    public native void setTrainMethod(int i);

    static {
        Loader.load();
    }

    public LogisticRegression(Pointer pointer) {
        super(pointer);
    }
}
