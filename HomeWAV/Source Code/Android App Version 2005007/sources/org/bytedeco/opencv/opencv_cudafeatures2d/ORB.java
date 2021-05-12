package org.bytedeco.opencv.opencv_cudafeatures2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_cudafeatures2d;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudafeatures2d.class})
@NoOffset
public class ORB extends Feature2DAsync {
    public static final int ANGLE_ROW = ANGLE_ROW();
    public static final int OCTAVE_ROW = OCTAVE_ROW();
    public static final int RESPONSE_ROW = RESPONSE_ROW();
    public static final int ROWS_COUNT = ROWS_COUNT();
    public static final int SIZE_ROW = SIZE_ROW();
    public static final int X_ROW = X_ROW();
    public static final int Y_ROW = Y_ROW();

    @MemberGetter
    public static native int ANGLE_ROW();

    @MemberGetter
    public static native int OCTAVE_ROW();

    @MemberGetter
    public static native int RESPONSE_ROW();

    @MemberGetter
    public static native int ROWS_COUNT();

    @MemberGetter
    public static native int SIZE_ROW();

    @MemberGetter
    public static native int X_ROW();

    @MemberGetter
    public static native int Y_ROW();

    @opencv_core.Ptr
    public static native ORB create();

    @opencv_core.Ptr
    public static native ORB create(int i, float f, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean getBlurForDescriptor();

    public native int getFastThreshold();

    public native void setBlurForDescriptor(@Cast({"bool"}) boolean z);

    public native void setFastThreshold(int i);

    static {
        Loader.load();
    }

    public ORB(Pointer pointer) {
        super(pointer);
    }
}
