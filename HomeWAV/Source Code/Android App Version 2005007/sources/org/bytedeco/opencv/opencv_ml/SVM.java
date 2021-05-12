package org.bytedeco.opencv.opencv_ml;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class SVM extends StatModel {
    public static final int C = 0;
    public static final int CHI2 = 4;
    public static final int COEF = 4;
    public static final int CUSTOM = -1;
    public static final int C_SVC = 100;
    public static final int DEGREE = 5;
    public static final int EPS_SVR = 103;
    public static final int GAMMA = 1;
    public static final int INTER = 5;
    public static final int LINEAR = 0;
    public static final int NU = 3;
    public static final int NU_SVC = 101;
    public static final int NU_SVR = 104;
    public static final int ONE_CLASS = 102;
    public static final int P = 2;
    public static final int POLY = 1;
    public static final int RBF = 2;
    public static final int SIGMOID = 3;

    @opencv_core.Ptr
    public static native SVM create();

    @ByVal
    public static native ParamGrid getDefaultGrid(int i);

    @opencv_core.Ptr
    public static native ParamGrid getDefaultGridPtr(int i);

    @opencv_core.Ptr
    public static native SVM load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native SVM load(@opencv_core.Str BytePointer bytePointer);

    public native double getC();

    @ByVal
    public native Mat getClassWeights();

    public native double getCoef0();

    public native double getDecisionFunction(int i, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native double getDecisionFunction(int i, @ByVal Mat mat, @ByVal Mat mat2);

    public native double getDecisionFunction(int i, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native double getDegree();

    public native double getGamma();

    public native int getKernelType();

    public native double getNu();

    public native double getP();

    @ByVal
    public native Mat getSupportVectors();

    @ByVal
    public native TermCriteria getTermCriteria();

    public native int getType();

    @ByVal
    public native Mat getUncompressedSupportVectors();

    public native void setC(double d);

    public native void setClassWeights(@ByRef @Const Mat mat);

    public native void setCoef0(double d);

    public native void setCustomKernel(@opencv_core.Ptr Kernel kernel);

    public native void setDegree(double d);

    public native void setGamma(double d);

    public native void setKernel(int i);

    public native void setNu(double d);

    public native void setP(double d);

    public native void setTermCriteria(@ByRef @Const TermCriteria termCriteria);

    public native void setType(int i);

    @Cast({"bool"})
    public native boolean trainAuto(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean trainAuto(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, int i2, @opencv_core.Ptr ParamGrid paramGrid, @opencv_core.Ptr ParamGrid paramGrid2, @opencv_core.Ptr ParamGrid paramGrid3, @opencv_core.Ptr ParamGrid paramGrid4, @opencv_core.Ptr ParamGrid paramGrid5, @opencv_core.Ptr ParamGrid paramGrid6, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean trainAuto(@ByVal Mat mat, int i, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean trainAuto(@ByVal Mat mat, int i, @ByVal Mat mat2, int i2, @opencv_core.Ptr ParamGrid paramGrid, @opencv_core.Ptr ParamGrid paramGrid2, @opencv_core.Ptr ParamGrid paramGrid3, @opencv_core.Ptr ParamGrid paramGrid4, @opencv_core.Ptr ParamGrid paramGrid5, @opencv_core.Ptr ParamGrid paramGrid6, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean trainAuto(@ByVal UMat uMat, int i, @ByVal UMat uMat2);

    @Cast({"bool"})
    public native boolean trainAuto(@ByVal UMat uMat, int i, @ByVal UMat uMat2, int i2, @opencv_core.Ptr ParamGrid paramGrid, @opencv_core.Ptr ParamGrid paramGrid2, @opencv_core.Ptr ParamGrid paramGrid3, @opencv_core.Ptr ParamGrid paramGrid4, @opencv_core.Ptr ParamGrid paramGrid5, @opencv_core.Ptr ParamGrid paramGrid6, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean trainAuto(@opencv_core.Ptr TrainData trainData);

    @Cast({"bool"})
    public native boolean trainAuto(@opencv_core.Ptr TrainData trainData, int i, @ByVal(nullValue = "cv::ml::ParamGrid(cv::ml::SVM::getDefaultGrid(cv::ml::SVM::C))") ParamGrid paramGrid, @ByVal(nullValue = "cv::ml::ParamGrid(cv::ml::SVM::getDefaultGrid(cv::ml::SVM::GAMMA))") ParamGrid paramGrid2, @ByVal(nullValue = "cv::ml::ParamGrid(cv::ml::SVM::getDefaultGrid(cv::ml::SVM::P))") ParamGrid paramGrid3, @ByVal(nullValue = "cv::ml::ParamGrid(cv::ml::SVM::getDefaultGrid(cv::ml::SVM::NU))") ParamGrid paramGrid4, @ByVal(nullValue = "cv::ml::ParamGrid(cv::ml::SVM::getDefaultGrid(cv::ml::SVM::COEF))") ParamGrid paramGrid5, @ByVal(nullValue = "cv::ml::ParamGrid(cv::ml::SVM::getDefaultGrid(cv::ml::SVM::DEGREE))") ParamGrid paramGrid6, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public SVM(Pointer pointer) {
        super(pointer);
    }

    public static class Kernel extends Algorithm {
        public native void calc(int i, int i2, @Const FloatBuffer floatBuffer, @Const FloatBuffer floatBuffer2, FloatBuffer floatBuffer3);

        public native void calc(int i, int i2, @Const FloatPointer floatPointer, @Const FloatPointer floatPointer2, FloatPointer floatPointer3);

        public native void calc(int i, int i2, @Const float[] fArr, @Const float[] fArr2, float[] fArr3);

        public native int getType();

        static {
            Loader.load();
        }

        public Kernel(Pointer pointer) {
            super(pointer);
        }
    }
}
