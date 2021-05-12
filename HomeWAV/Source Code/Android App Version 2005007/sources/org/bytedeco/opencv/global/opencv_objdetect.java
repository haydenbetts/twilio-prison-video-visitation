package org.bytedeco.opencv.global;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.BaseCascadeClassifier;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_objdetect extends org.bytedeco.opencv.presets.opencv_objdetect {
    public static final int CASCADE_DO_CANNY_PRUNING = 1;
    public static final int CASCADE_DO_ROUGH_SEARCH = 8;
    public static final int CASCADE_FIND_BIGGEST_OBJECT = 4;
    public static final int CASCADE_SCALE_IMAGE = 2;

    @Namespace("cv")
    @opencv_core.Ptr
    public static native BaseCascadeClassifier.MaskGenerator createFaceDetectionMaskGenerator();

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, int i);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, int i, double d);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, int i, double d, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, int i, double d, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, int i, double d, @StdVector int[] iArr, @StdVector double[] dArr);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, int i);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, int i, double d);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, int i);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntBuffer intBuffer, @StdVector DoubleBuffer doubleBuffer, int i, double d);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntPointer intPointer, int i);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntPointer intPointer, int i, double d);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, int i);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector IntPointer intPointer, @StdVector DoublePointer doublePointer, int i, double d);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector int[] iArr, int i);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector int[] iArr, int i, double d);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, int i);

    @Namespace("cv")
    public static native void groupRectangles(@ByRef RectVector rectVector, @StdVector int[] iArr, @StdVector double[] dArr, int i, double d);

    @Namespace("cv")
    public static native void groupRectangles_meanshift(@ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer, @StdVector DoubleBuffer doubleBuffer2);

    @Namespace("cv")
    public static native void groupRectangles_meanshift(@ByRef RectVector rectVector, @StdVector DoubleBuffer doubleBuffer, @StdVector DoubleBuffer doubleBuffer2, double d, @ByVal(nullValue = "cv::Size(64, 128)") Size size);

    @Namespace("cv")
    public static native void groupRectangles_meanshift(@ByRef RectVector rectVector, @StdVector DoublePointer doublePointer, @StdVector DoublePointer doublePointer2);

    @Namespace("cv")
    public static native void groupRectangles_meanshift(@ByRef RectVector rectVector, @StdVector DoublePointer doublePointer, @StdVector DoublePointer doublePointer2, double d, @ByVal(nullValue = "cv::Size(64, 128)") Size size);

    @Namespace("cv")
    public static native void groupRectangles_meanshift(@ByRef RectVector rectVector, @StdVector double[] dArr, @StdVector double[] dArr2);

    @Namespace("cv")
    public static native void groupRectangles_meanshift(@ByRef RectVector rectVector, @StdVector double[] dArr, @StdVector double[] dArr2, double d, @ByVal(nullValue = "cv::Size(64, 128)") Size size);

    static {
        Loader.load();
    }
}
