package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class SVMSGD extends StatModel {
    public static final int ASGD = 1;
    public static final int HARD_MARGIN = 1;
    public static final int SGD = 0;
    public static final int SOFT_MARGIN = 0;

    @opencv_core.Ptr
    public static native SVMSGD create();

    @opencv_core.Ptr
    public static native SVMSGD load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native SVMSGD load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native SVMSGD load(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native SVMSGD load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native float getInitialStepSize();

    public native float getMarginRegularization();

    public native int getMarginType();

    public native float getShift();

    public native float getStepDecreasingPower();

    public native int getSvmsgdType();

    @ByVal
    public native TermCriteria getTermCriteria();

    @ByVal
    public native Mat getWeights();

    public native void setInitialStepSize(float f);

    public native void setMarginRegularization(float f);

    public native void setMarginType(int i);

    public native void setOptimalParameters();

    public native void setOptimalParameters(int i, int i2);

    public native void setStepDecreasingPower(float f);

    public native void setSvmsgdType(int i);

    public native void setTermCriteria(@ByRef @Const TermCriteria termCriteria);

    static {
        Loader.load();
    }

    public SVMSGD(Pointer pointer) {
        super(pointer);
    }
}
