package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.RNG;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class ANN_MLP extends StatModel {
    public static final int ANNEAL = 2;
    public static final int BACKPROP = 0;
    public static final int GAUSSIAN = 2;
    public static final int IDENTITY = 0;
    public static final int LEAKYRELU = 4;
    public static final int NO_INPUT_SCALE = 2;
    public static final int NO_OUTPUT_SCALE = 4;
    public static final int RELU = 3;
    public static final int RPROP = 1;
    public static final int SIGMOID_SYM = 1;
    public static final int UPDATE_WEIGHTS = 1;

    @opencv_core.Ptr
    public static native ANN_MLP create();

    @opencv_core.Ptr
    public static native ANN_MLP load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native ANN_MLP load(@opencv_core.Str BytePointer bytePointer);

    public native double getAnnealCoolingRatio();

    public native double getAnnealFinalT();

    public native double getAnnealInitialT();

    public native int getAnnealItePerStep();

    public native double getBackpropMomentumScale();

    public native double getBackpropWeightScale();

    @ByVal
    public native Mat getLayerSizes();

    public native double getRpropDW0();

    public native double getRpropDWMax();

    public native double getRpropDWMin();

    public native double getRpropDWMinus();

    public native double getRpropDWPlus();

    @ByVal
    public native TermCriteria getTermCriteria();

    public native int getTrainMethod();

    @ByVal
    public native Mat getWeights(int i);

    public native void setActivationFunction(int i);

    public native void setActivationFunction(int i, double d, double d2);

    public native void setAnnealCoolingRatio(double d);

    public native void setAnnealEnergyRNG(@ByRef @Const RNG rng);

    public native void setAnnealFinalT(double d);

    public native void setAnnealInitialT(double d);

    public native void setAnnealItePerStep(int i);

    public native void setBackpropMomentumScale(double d);

    public native void setBackpropWeightScale(double d);

    public native void setLayerSizes(@ByVal GpuMat gpuMat);

    public native void setLayerSizes(@ByVal Mat mat);

    public native void setLayerSizes(@ByVal UMat uMat);

    public native void setRpropDW0(double d);

    public native void setRpropDWMax(double d);

    public native void setRpropDWMin(double d);

    public native void setRpropDWMinus(double d);

    public native void setRpropDWPlus(double d);

    public native void setTermCriteria(@ByVal TermCriteria termCriteria);

    public native void setTrainMethod(int i);

    public native void setTrainMethod(int i, double d, double d2);

    static {
        Loader.load();
    }

    public ANN_MLP(Pointer pointer) {
        super(pointer);
    }
}
