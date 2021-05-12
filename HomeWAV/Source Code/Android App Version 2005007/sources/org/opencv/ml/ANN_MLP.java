package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;

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

    private static native long create_0();

    private static native void delete(long j);

    private static native double getAnnealCoolingRatio_0(long j);

    private static native double getAnnealFinalT_0(long j);

    private static native double getAnnealInitialT_0(long j);

    private static native int getAnnealItePerStep_0(long j);

    private static native double getBackpropMomentumScale_0(long j);

    private static native double getBackpropWeightScale_0(long j);

    private static native long getLayerSizes_0(long j);

    private static native double getRpropDW0_0(long j);

    private static native double getRpropDWMax_0(long j);

    private static native double getRpropDWMin_0(long j);

    private static native double getRpropDWMinus_0(long j);

    private static native double getRpropDWPlus_0(long j);

    private static native double[] getTermCriteria_0(long j);

    private static native int getTrainMethod_0(long j);

    private static native long getWeights_0(long j, int i);

    private static native long load_0(String str);

    private static native void setActivationFunction_0(long j, int i, double d, double d2);

    private static native void setActivationFunction_1(long j, int i, double d);

    private static native void setActivationFunction_2(long j, int i);

    private static native void setAnnealCoolingRatio_0(long j, double d);

    private static native void setAnnealFinalT_0(long j, double d);

    private static native void setAnnealInitialT_0(long j, double d);

    private static native void setAnnealItePerStep_0(long j, int i);

    private static native void setBackpropMomentumScale_0(long j, double d);

    private static native void setBackpropWeightScale_0(long j, double d);

    private static native void setLayerSizes_0(long j, long j2);

    private static native void setRpropDW0_0(long j, double d);

    private static native void setRpropDWMax_0(long j, double d);

    private static native void setRpropDWMin_0(long j, double d);

    private static native void setRpropDWMinus_0(long j, double d);

    private static native void setRpropDWPlus_0(long j, double d);

    private static native void setTermCriteria_0(long j, int i, int i2, double d);

    private static native void setTrainMethod_0(long j, int i, double d, double d2);

    private static native void setTrainMethod_1(long j, int i, double d);

    private static native void setTrainMethod_2(long j, int i);

    protected ANN_MLP(long j) {
        super(j);
    }

    public static ANN_MLP __fromPtr__(long j) {
        return new ANN_MLP(j);
    }

    public Mat getLayerSizes() {
        return new Mat(getLayerSizes_0(this.nativeObj));
    }

    public Mat getWeights(int i) {
        return new Mat(getWeights_0(this.nativeObj, i));
    }

    public static ANN_MLP create() {
        return __fromPtr__(create_0());
    }

    public static ANN_MLP load(String str) {
        return __fromPtr__(load_0(str));
    }

    public TermCriteria getTermCriteria() {
        return new TermCriteria(getTermCriteria_0(this.nativeObj));
    }

    public double getAnnealCoolingRatio() {
        return getAnnealCoolingRatio_0(this.nativeObj);
    }

    public double getAnnealFinalT() {
        return getAnnealFinalT_0(this.nativeObj);
    }

    public double getAnnealInitialT() {
        return getAnnealInitialT_0(this.nativeObj);
    }

    public double getBackpropMomentumScale() {
        return getBackpropMomentumScale_0(this.nativeObj);
    }

    public double getBackpropWeightScale() {
        return getBackpropWeightScale_0(this.nativeObj);
    }

    public double getRpropDW0() {
        return getRpropDW0_0(this.nativeObj);
    }

    public double getRpropDWMax() {
        return getRpropDWMax_0(this.nativeObj);
    }

    public double getRpropDWMin() {
        return getRpropDWMin_0(this.nativeObj);
    }

    public double getRpropDWMinus() {
        return getRpropDWMinus_0(this.nativeObj);
    }

    public double getRpropDWPlus() {
        return getRpropDWPlus_0(this.nativeObj);
    }

    public int getAnnealItePerStep() {
        return getAnnealItePerStep_0(this.nativeObj);
    }

    public int getTrainMethod() {
        return getTrainMethod_0(this.nativeObj);
    }

    public void setActivationFunction(int i, double d, double d2) {
        setActivationFunction_0(this.nativeObj, i, d, d2);
    }

    public void setActivationFunction(int i, double d) {
        setActivationFunction_1(this.nativeObj, i, d);
    }

    public void setActivationFunction(int i) {
        setActivationFunction_2(this.nativeObj, i);
    }

    public void setAnnealCoolingRatio(double d) {
        setAnnealCoolingRatio_0(this.nativeObj, d);
    }

    public void setAnnealFinalT(double d) {
        setAnnealFinalT_0(this.nativeObj, d);
    }

    public void setAnnealInitialT(double d) {
        setAnnealInitialT_0(this.nativeObj, d);
    }

    public void setAnnealItePerStep(int i) {
        setAnnealItePerStep_0(this.nativeObj, i);
    }

    public void setBackpropMomentumScale(double d) {
        setBackpropMomentumScale_0(this.nativeObj, d);
    }

    public void setBackpropWeightScale(double d) {
        setBackpropWeightScale_0(this.nativeObj, d);
    }

    public void setLayerSizes(Mat mat) {
        setLayerSizes_0(this.nativeObj, mat.nativeObj);
    }

    public void setRpropDW0(double d) {
        setRpropDW0_0(this.nativeObj, d);
    }

    public void setRpropDWMax(double d) {
        setRpropDWMax_0(this.nativeObj, d);
    }

    public void setRpropDWMin(double d) {
        setRpropDWMin_0(this.nativeObj, d);
    }

    public void setRpropDWMinus(double d) {
        setRpropDWMinus_0(this.nativeObj, d);
    }

    public void setRpropDWPlus(double d) {
        setRpropDWPlus_0(this.nativeObj, d);
    }

    public void setTermCriteria(TermCriteria termCriteria) {
        setTermCriteria_0(this.nativeObj, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
    }

    public void setTrainMethod(int i, double d, double d2) {
        setTrainMethod_0(this.nativeObj, i, d, d2);
    }

    public void setTrainMethod(int i, double d) {
        setTrainMethod_1(this.nativeObj, i, d);
    }

    public void setTrainMethod(int i) {
        setTrainMethod_2(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
