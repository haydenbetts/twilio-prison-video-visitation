package org.opencv.bioinspired;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Size;

public class Retina extends Algorithm {
    private static native void activateContoursProcessing_0(long j, boolean z);

    private static native void activateMovingContoursProcessing_0(long j, boolean z);

    private static native void applyFastToneMapping_0(long j, long j2, long j3);

    private static native void clearBuffers_0(long j);

    private static native long create_0(double d, double d2, boolean z, int i, boolean z2, float f, float f2);

    private static native long create_1(double d, double d2, boolean z, int i, boolean z2, float f);

    private static native long create_2(double d, double d2, boolean z, int i, boolean z2);

    private static native long create_3(double d, double d2, boolean z, int i);

    private static native long create_4(double d, double d2, boolean z);

    private static native long create_5(double d, double d2);

    private static native void delete(long j);

    private static native double[] getInputSize_0(long j);

    private static native long getMagnoRAW_0(long j);

    private static native void getMagnoRAW_1(long j, long j2);

    private static native void getMagno_0(long j, long j2);

    private static native double[] getOutputSize_0(long j);

    private static native long getParvoRAW_0(long j);

    private static native void getParvoRAW_1(long j, long j2);

    private static native void getParvo_0(long j, long j2);

    private static native String printSetup_0(long j);

    private static native void run_0(long j, long j2);

    private static native void setColorSaturation_0(long j, boolean z, float f);

    private static native void setColorSaturation_1(long j, boolean z);

    private static native void setColorSaturation_2(long j);

    private static native void setupIPLMagnoChannel_0(long j, boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    private static native void setupIPLMagnoChannel_1(long j, boolean z, float f, float f2, float f3, float f4, float f5, float f6);

    private static native void setupIPLMagnoChannel_2(long j, boolean z, float f, float f2, float f3, float f4, float f5);

    private static native void setupIPLMagnoChannel_3(long j, boolean z, float f, float f2, float f3, float f4);

    private static native void setupIPLMagnoChannel_4(long j, boolean z, float f, float f2, float f3);

    private static native void setupIPLMagnoChannel_5(long j, boolean z, float f, float f2);

    private static native void setupIPLMagnoChannel_6(long j, boolean z, float f);

    private static native void setupIPLMagnoChannel_7(long j, boolean z);

    private static native void setupIPLMagnoChannel_8(long j);

    private static native void setupOPLandIPLParvoChannel_0(long j, boolean z, boolean z2, float f, float f2, float f3, float f4, float f5, float f6, float f7);

    private static native void setupOPLandIPLParvoChannel_1(long j, boolean z, boolean z2, float f, float f2, float f3, float f4, float f5, float f6);

    private static native void setupOPLandIPLParvoChannel_2(long j, boolean z, boolean z2, float f, float f2, float f3, float f4, float f5);

    private static native void setupOPLandIPLParvoChannel_3(long j, boolean z, boolean z2, float f, float f2, float f3, float f4);

    private static native void setupOPLandIPLParvoChannel_4(long j, boolean z, boolean z2, float f, float f2, float f3);

    private static native void setupOPLandIPLParvoChannel_5(long j, boolean z, boolean z2, float f, float f2);

    private static native void setupOPLandIPLParvoChannel_6(long j, boolean z, boolean z2, float f);

    private static native void setupOPLandIPLParvoChannel_7(long j, boolean z, boolean z2);

    private static native void setupOPLandIPLParvoChannel_8(long j, boolean z);

    private static native void setupOPLandIPLParvoChannel_9(long j);

    private static native void setup_0(long j, String str, boolean z);

    private static native void setup_1(long j, String str);

    private static native void setup_2(long j);

    private static native void write_0(long j, String str);

    protected Retina(long j) {
        super(j);
    }

    public static Retina __fromPtr__(long j) {
        return new Retina(j);
    }

    public Mat getMagnoRAW() {
        return new Mat(getMagnoRAW_0(this.nativeObj));
    }

    public Mat getParvoRAW() {
        return new Mat(getParvoRAW_0(this.nativeObj));
    }

    public static Retina create(Size size, boolean z, int i, boolean z2, float f, float f2) {
        return __fromPtr__(create_0(size.width, size.height, z, i, z2, f, f2));
    }

    public static Retina create(Size size, boolean z, int i, boolean z2, float f) {
        return __fromPtr__(create_1(size.width, size.height, z, i, z2, f));
    }

    public static Retina create(Size size, boolean z, int i, boolean z2) {
        return __fromPtr__(create_2(size.width, size.height, z, i, z2));
    }

    public static Retina create(Size size, boolean z, int i) {
        return __fromPtr__(create_3(size.width, size.height, z, i));
    }

    public static Retina create(Size size, boolean z) {
        return __fromPtr__(create_4(size.width, size.height, z));
    }

    public static Retina create(Size size) {
        return __fromPtr__(create_5(size.width, size.height));
    }

    public Size getInputSize() {
        return new Size(getInputSize_0(this.nativeObj));
    }

    public Size getOutputSize() {
        return new Size(getOutputSize_0(this.nativeObj));
    }

    public String printSetup() {
        return printSetup_0(this.nativeObj);
    }

    public void activateContoursProcessing(boolean z) {
        activateContoursProcessing_0(this.nativeObj, z);
    }

    public void activateMovingContoursProcessing(boolean z) {
        activateMovingContoursProcessing_0(this.nativeObj, z);
    }

    public void applyFastToneMapping(Mat mat, Mat mat2) {
        applyFastToneMapping_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void clearBuffers() {
        clearBuffers_0(this.nativeObj);
    }

    public void getMagno(Mat mat) {
        getMagno_0(this.nativeObj, mat.nativeObj);
    }

    public void getMagnoRAW(Mat mat) {
        getMagnoRAW_1(this.nativeObj, mat.nativeObj);
    }

    public void getParvo(Mat mat) {
        getParvo_0(this.nativeObj, mat.nativeObj);
    }

    public void getParvoRAW(Mat mat) {
        getParvoRAW_1(this.nativeObj, mat.nativeObj);
    }

    public void run(Mat mat) {
        run_0(this.nativeObj, mat.nativeObj);
    }

    public void setColorSaturation(boolean z, float f) {
        setColorSaturation_0(this.nativeObj, z, f);
    }

    public void setColorSaturation(boolean z) {
        setColorSaturation_1(this.nativeObj, z);
    }

    public void setColorSaturation() {
        setColorSaturation_2(this.nativeObj);
    }

    public void setup(String str, boolean z) {
        setup_0(this.nativeObj, str, z);
    }

    public void setup(String str) {
        setup_1(this.nativeObj, str);
    }

    public void setup() {
        setup_2(this.nativeObj);
    }

    public void setupIPLMagnoChannel(boolean z, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        setupIPLMagnoChannel_0(this.nativeObj, z, f, f2, f3, f4, f5, f6, f7);
    }

    public void setupIPLMagnoChannel(boolean z, float f, float f2, float f3, float f4, float f5, float f6) {
        setupIPLMagnoChannel_1(this.nativeObj, z, f, f2, f3, f4, f5, f6);
    }

    public void setupIPLMagnoChannel(boolean z, float f, float f2, float f3, float f4, float f5) {
        setupIPLMagnoChannel_2(this.nativeObj, z, f, f2, f3, f4, f5);
    }

    public void setupIPLMagnoChannel(boolean z, float f, float f2, float f3, float f4) {
        setupIPLMagnoChannel_3(this.nativeObj, z, f, f2, f3, f4);
    }

    public void setupIPLMagnoChannel(boolean z, float f, float f2, float f3) {
        setupIPLMagnoChannel_4(this.nativeObj, z, f, f2, f3);
    }

    public void setupIPLMagnoChannel(boolean z, float f, float f2) {
        setupIPLMagnoChannel_5(this.nativeObj, z, f, f2);
    }

    public void setupIPLMagnoChannel(boolean z, float f) {
        setupIPLMagnoChannel_6(this.nativeObj, z, f);
    }

    public void setupIPLMagnoChannel(boolean z) {
        setupIPLMagnoChannel_7(this.nativeObj, z);
    }

    public void setupIPLMagnoChannel() {
        setupIPLMagnoChannel_8(this.nativeObj);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        setupOPLandIPLParvoChannel_0(this.nativeObj, z, z2, f, f2, f3, f4, f5, f6, f7);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2, float f, float f2, float f3, float f4, float f5, float f6) {
        setupOPLandIPLParvoChannel_1(this.nativeObj, z, z2, f, f2, f3, f4, f5, f6);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2, float f, float f2, float f3, float f4, float f5) {
        setupOPLandIPLParvoChannel_2(this.nativeObj, z, z2, f, f2, f3, f4, f5);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2, float f, float f2, float f3, float f4) {
        setupOPLandIPLParvoChannel_3(this.nativeObj, z, z2, f, f2, f3, f4);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2, float f, float f2, float f3) {
        setupOPLandIPLParvoChannel_4(this.nativeObj, z, z2, f, f2, f3);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2, float f, float f2) {
        setupOPLandIPLParvoChannel_5(this.nativeObj, z, z2, f, f2);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2, float f) {
        setupOPLandIPLParvoChannel_6(this.nativeObj, z, z2, f);
    }

    public void setupOPLandIPLParvoChannel(boolean z, boolean z2) {
        setupOPLandIPLParvoChannel_7(this.nativeObj, z, z2);
    }

    public void setupOPLandIPLParvoChannel(boolean z) {
        setupOPLandIPLParvoChannel_8(this.nativeObj, z);
    }

    public void setupOPLandIPLParvoChannel() {
        setupOPLandIPLParvoChannel_9(this.nativeObj);
    }

    public void write(String str) {
        write_0(this.nativeObj, str);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
