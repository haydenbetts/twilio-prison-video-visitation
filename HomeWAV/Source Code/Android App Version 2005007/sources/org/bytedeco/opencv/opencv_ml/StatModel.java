package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class StatModel extends AbstractStatModel {
    public static final int COMPRESSED_INPUT = 2;
    public static final int PREPROCESSED_INPUT = 4;
    public static final int RAW_OUTPUT = 1;
    public static final int UPDATE_MODEL = 1;

    public native float calcError(@opencv_core.Ptr TrainData trainData, @Cast({"bool"}) boolean z, @ByVal GpuMat gpuMat);

    public native float calcError(@opencv_core.Ptr TrainData trainData, @Cast({"bool"}) boolean z, @ByVal Mat mat);

    public native float calcError(@opencv_core.Ptr TrainData trainData, @Cast({"bool"}) boolean z, @ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean empty();

    public native int getVarCount();

    @Cast({"bool"})
    public native boolean isClassifier();

    @Cast({"bool"})
    public native boolean isTrained();

    public native float predict(@ByVal GpuMat gpuMat);

    public native float predict(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat2, int i);

    public native float predict(@ByVal Mat mat);

    public native float predict(@ByVal Mat mat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat2, int i);

    public native float predict(@ByVal UMat uMat);

    public native float predict(@ByVal UMat uMat, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat2, int i);

    @Cast({"bool"})
    public native boolean train(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean train(@ByVal Mat mat, int i, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean train(@ByVal UMat uMat, int i, @ByVal UMat uMat2);

    @Cast({"bool"})
    public native boolean train(@opencv_core.Ptr TrainData trainData);

    @Cast({"bool"})
    public native boolean train(@opencv_core.Ptr TrainData trainData, int i);

    static {
        Loader.load();
    }

    public StatModel(Pointer pointer) {
        super(pointer);
    }
}
