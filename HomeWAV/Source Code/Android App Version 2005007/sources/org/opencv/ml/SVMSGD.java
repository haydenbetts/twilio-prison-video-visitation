package org.opencv.ml;

import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;

public class SVMSGD extends StatModel {
    public static final int ASGD = 1;
    public static final int HARD_MARGIN = 1;
    public static final int SGD = 0;
    public static final int SOFT_MARGIN = 0;

    private static native long create_0();

    private static native void delete(long j);

    private static native float getInitialStepSize_0(long j);

    private static native float getMarginRegularization_0(long j);

    private static native int getMarginType_0(long j);

    private static native float getShift_0(long j);

    private static native float getStepDecreasingPower_0(long j);

    private static native int getSvmsgdType_0(long j);

    private static native double[] getTermCriteria_0(long j);

    private static native long getWeights_0(long j);

    private static native long load_0(String str, String str2);

    private static native long load_1(String str);

    private static native void setInitialStepSize_0(long j, float f);

    private static native void setMarginRegularization_0(long j, float f);

    private static native void setMarginType_0(long j, int i);

    private static native void setOptimalParameters_0(long j, int i, int i2);

    private static native void setOptimalParameters_1(long j, int i);

    private static native void setOptimalParameters_2(long j);

    private static native void setStepDecreasingPower_0(long j, float f);

    private static native void setSvmsgdType_0(long j, int i);

    private static native void setTermCriteria_0(long j, int i, int i2, double d);

    protected SVMSGD(long j) {
        super(j);
    }

    public static SVMSGD __fromPtr__(long j) {
        return new SVMSGD(j);
    }

    public Mat getWeights() {
        return new Mat(getWeights_0(this.nativeObj));
    }

    public static SVMSGD create() {
        return __fromPtr__(create_0());
    }

    public static SVMSGD load(String str, String str2) {
        return __fromPtr__(load_0(str, str2));
    }

    public static SVMSGD load(String str) {
        return __fromPtr__(load_1(str));
    }

    public TermCriteria getTermCriteria() {
        return new TermCriteria(getTermCriteria_0(this.nativeObj));
    }

    public float getInitialStepSize() {
        return getInitialStepSize_0(this.nativeObj);
    }

    public float getMarginRegularization() {
        return getMarginRegularization_0(this.nativeObj);
    }

    public float getShift() {
        return getShift_0(this.nativeObj);
    }

    public float getStepDecreasingPower() {
        return getStepDecreasingPower_0(this.nativeObj);
    }

    public int getMarginType() {
        return getMarginType_0(this.nativeObj);
    }

    public int getSvmsgdType() {
        return getSvmsgdType_0(this.nativeObj);
    }

    public void setInitialStepSize(float f) {
        setInitialStepSize_0(this.nativeObj, f);
    }

    public void setMarginRegularization(float f) {
        setMarginRegularization_0(this.nativeObj, f);
    }

    public void setMarginType(int i) {
        setMarginType_0(this.nativeObj, i);
    }

    public void setOptimalParameters(int i, int i2) {
        setOptimalParameters_0(this.nativeObj, i, i2);
    }

    public void setOptimalParameters(int i) {
        setOptimalParameters_1(this.nativeObj, i);
    }

    public void setOptimalParameters() {
        setOptimalParameters_2(this.nativeObj);
    }

    public void setStepDecreasingPower(float f) {
        setStepDecreasingPower_0(this.nativeObj, f);
    }

    public void setSvmsgdType(int i) {
        setSvmsgdType_0(this.nativeObj, i);
    }

    public void setTermCriteria(TermCriteria termCriteria) {
        setTermCriteria_0(this.nativeObj, termCriteria.type, termCriteria.maxCount, termCriteria.epsilon);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
