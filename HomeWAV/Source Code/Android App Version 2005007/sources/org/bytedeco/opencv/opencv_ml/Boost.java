package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class Boost extends DTrees {
    public static final int DISCRETE = 0;
    public static final int GENTLE = 3;
    public static final int LOGIT = 2;
    public static final int REAL = 1;

    @opencv_core.Ptr
    public static native Boost create();

    @opencv_core.Ptr
    public static native Boost load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native Boost load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native Boost load(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native Boost load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native int getBoostType();

    public native int getWeakCount();

    public native double getWeightTrimRate();

    public native void setBoostType(int i);

    public native void setWeakCount(int i);

    public native void setWeightTrimRate(double d);

    static {
        Loader.load();
    }

    public Boost(Pointer pointer) {
        super(pointer);
    }
}
