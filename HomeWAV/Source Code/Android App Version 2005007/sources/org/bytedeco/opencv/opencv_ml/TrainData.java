package org.bytedeco.opencv.opencv_ml;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class TrainData extends Pointer {
    @opencv_core.Ptr
    public static native TrainData create(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2);

    @opencv_core.Ptr
    public static native TrainData create(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat6);

    @opencv_core.Ptr
    public static native TrainData create(@ByVal Mat mat, int i, @ByVal Mat mat2);

    @opencv_core.Ptr
    public static native TrainData create(@ByVal Mat mat, int i, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat6);

    @opencv_core.Ptr
    public static native TrainData create(@ByVal UMat uMat, int i, @ByVal UMat uMat2);

    @opencv_core.Ptr
    public static native TrainData create(@ByVal UMat uMat, int i, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat6);

    @ByVal
    public static native Mat getSubMatrix(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, int i);

    @ByVal
    public static native Mat getSubVector(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    @opencv_core.Ptr
    public static native TrainData loadFromCSV(@opencv_core.Str String str, int i);

    @opencv_core.Ptr
    public static native TrainData loadFromCSV(@opencv_core.Str String str, int i, int i2, int i3, @opencv_core.Str String str2, @Cast({"char"}) byte b, @Cast({"char"}) byte b2);

    @opencv_core.Ptr
    public static native TrainData loadFromCSV(@opencv_core.Str BytePointer bytePointer, int i);

    @opencv_core.Ptr
    public static native TrainData loadFromCSV(@opencv_core.Str BytePointer bytePointer, int i, int i2, int i3, @opencv_core.Str BytePointer bytePointer2, @Cast({"char"}) byte b, @Cast({"char"}) byte b2);

    public static native float missingValue();

    public native int getCatCount(int i);

    @ByVal
    public native Mat getCatMap();

    @ByVal
    public native Mat getCatOfs();

    @ByVal
    public native Mat getClassLabels();

    @ByVal
    public native Mat getDefaultSubstValues();

    public native int getLayout();

    @ByVal
    public native Mat getMissing();

    public native int getNAllVars();

    public native int getNSamples();

    public native int getNTestSamples();

    public native int getNTrainSamples();

    public native int getNVars();

    public native void getNames(@ByRef StringVector stringVector);

    @ByVal
    public native Mat getNormCatResponses();

    public native void getNormCatValues(int i, @ByVal GpuMat gpuMat, IntBuffer intBuffer);

    public native void getNormCatValues(int i, @ByVal GpuMat gpuMat, IntPointer intPointer);

    public native void getNormCatValues(int i, @ByVal GpuMat gpuMat, int[] iArr);

    public native void getNormCatValues(int i, @ByVal Mat mat, IntBuffer intBuffer);

    public native void getNormCatValues(int i, @ByVal Mat mat, IntPointer intPointer);

    public native void getNormCatValues(int i, @ByVal Mat mat, int[] iArr);

    public native void getNormCatValues(int i, @ByVal UMat uMat, IntBuffer intBuffer);

    public native void getNormCatValues(int i, @ByVal UMat uMat, IntPointer intPointer);

    public native void getNormCatValues(int i, @ByVal UMat uMat, int[] iArr);

    public native int getResponseType();

    @ByVal
    public native Mat getResponses();

    public native void getSample(@ByVal GpuMat gpuMat, int i, FloatBuffer floatBuffer);

    public native void getSample(@ByVal GpuMat gpuMat, int i, FloatPointer floatPointer);

    public native void getSample(@ByVal GpuMat gpuMat, int i, float[] fArr);

    public native void getSample(@ByVal Mat mat, int i, FloatBuffer floatBuffer);

    public native void getSample(@ByVal Mat mat, int i, FloatPointer floatPointer);

    public native void getSample(@ByVal Mat mat, int i, float[] fArr);

    public native void getSample(@ByVal UMat uMat, int i, FloatBuffer floatBuffer);

    public native void getSample(@ByVal UMat uMat, int i, FloatPointer floatPointer);

    public native void getSample(@ByVal UMat uMat, int i, float[] fArr);

    @ByVal
    public native Mat getSampleWeights();

    @ByVal
    public native Mat getSamples();

    @ByVal
    public native Mat getTestNormCatResponses();

    @ByVal
    public native Mat getTestResponses();

    @ByVal
    public native Mat getTestSampleIdx();

    @ByVal
    public native Mat getTestSampleWeights();

    @ByVal
    public native Mat getTestSamples();

    @ByVal
    public native Mat getTrainNormCatResponses();

    @ByVal
    public native Mat getTrainResponses();

    @ByVal
    public native Mat getTrainSampleIdx();

    @ByVal
    public native Mat getTrainSampleWeights();

    @ByVal
    public native Mat getTrainSamples();

    @ByVal
    public native Mat getTrainSamples(int i, @Cast({"bool"}) boolean z, @Cast({"bool"}) boolean z2);

    public native void getValues(int i, @ByVal GpuMat gpuMat, FloatBuffer floatBuffer);

    public native void getValues(int i, @ByVal GpuMat gpuMat, FloatPointer floatPointer);

    public native void getValues(int i, @ByVal GpuMat gpuMat, float[] fArr);

    public native void getValues(int i, @ByVal Mat mat, FloatBuffer floatBuffer);

    public native void getValues(int i, @ByVal Mat mat, FloatPointer floatPointer);

    public native void getValues(int i, @ByVal Mat mat, float[] fArr);

    public native void getValues(int i, @ByVal UMat uMat, FloatBuffer floatBuffer);

    public native void getValues(int i, @ByVal UMat uMat, FloatPointer floatPointer);

    public native void getValues(int i, @ByVal UMat uMat, float[] fArr);

    @ByVal
    public native Mat getVarIdx();

    @ByVal
    public native Mat getVarSymbolFlags();

    @ByVal
    public native Mat getVarType();

    public native void setTrainTestSplit(int i);

    public native void setTrainTestSplit(int i, @Cast({"bool"}) boolean z);

    public native void setTrainTestSplitRatio(double d);

    public native void setTrainTestSplitRatio(double d, @Cast({"bool"}) boolean z);

    public native void shuffleTrainTest();

    static {
        Loader.load();
    }

    public TrainData(Pointer pointer) {
        super(pointer);
    }
}
