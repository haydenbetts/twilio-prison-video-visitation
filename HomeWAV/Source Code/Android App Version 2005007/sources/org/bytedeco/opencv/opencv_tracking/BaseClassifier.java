package org.bytedeco.opencv.opencv_tracking;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.BoolPointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class BaseClassifier extends Pointer {
    private native void allocate(int i, int i2);

    private native void allocate(int i, int i2, @Cast({"cv::WeakClassifierHaarFeature**"}) PointerPointer pointerPointer);

    private native void allocate(int i, int i2, @ByPtrPtr WeakClassifierHaarFeature weakClassifierHaarFeature);

    public native int computeReplaceWeakestClassifier(@StdVector FloatBuffer floatBuffer);

    public native int computeReplaceWeakestClassifier(@StdVector FloatPointer floatPointer);

    public native int computeReplaceWeakestClassifier(@StdVector float[] fArr);

    public native int eval(@ByRef @Const Mat mat);

    public native float getError(int i);

    public native void getErrors(FloatBuffer floatBuffer);

    public native void getErrors(FloatPointer floatPointer);

    public native void getErrors(float[] fArr);

    public native int getIdxOfNewWeakClassifier();

    @Cast({"cv::WeakClassifierHaarFeature**"})
    public native PointerPointer getReferenceWeakClassifier();

    public native int getSelectedClassifier();

    public native void replaceClassifierStatistic(int i, int i2);

    public native void replaceWeakClassifier(int i);

    public native int selectBestClassifier(@Cast({"bool*"}) @StdVector BoolPointer boolPointer, float f, @StdVector FloatBuffer floatBuffer);

    public native int selectBestClassifier(@Cast({"bool*"}) @StdVector BoolPointer boolPointer, float f, @StdVector FloatPointer floatPointer);

    public native int selectBestClassifier(@Cast({"bool*"}) @StdVector BoolPointer boolPointer, float f, @StdVector float[] fArr);

    public native int selectBestClassifier(@Cast({"bool*"}) @StdVector boolean[] zArr, float f, @StdVector FloatBuffer floatBuffer);

    public native int selectBestClassifier(@Cast({"bool*"}) @StdVector boolean[] zArr, float f, @StdVector FloatPointer floatPointer);

    public native int selectBestClassifier(@Cast({"bool*"}) @StdVector boolean[] zArr, float f, @StdVector float[] fArr);

    public native void trainClassifier(@ByRef @Const Mat mat, int i, float f, @Cast({"bool*"}) @StdVector BoolPointer boolPointer);

    public native void trainClassifier(@ByRef @Const Mat mat, int i, float f, @Cast({"bool*"}) @StdVector boolean[] zArr);

    static {
        Loader.load();
    }

    public BaseClassifier(Pointer pointer) {
        super(pointer);
    }

    public BaseClassifier(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }

    public BaseClassifier(int i, int i2, @Cast({"cv::WeakClassifierHaarFeature**"}) PointerPointer pointerPointer) {
        super((Pointer) null);
        allocate(i, i2, pointerPointer);
    }

    public BaseClassifier(int i, int i2, @ByPtrPtr WeakClassifierHaarFeature weakClassifierHaarFeature) {
        super((Pointer) null);
        allocate(i, i2, weakClassifierHaarFeature);
    }
}
