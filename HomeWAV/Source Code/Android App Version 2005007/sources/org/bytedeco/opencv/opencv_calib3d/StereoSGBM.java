package org.bytedeco.opencv.opencv_calib3d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_calib3d;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_calib3d.class})
public class StereoSGBM extends StereoMatcher {
    public static final int MODE_HH = 1;
    public static final int MODE_HH4 = 3;
    public static final int MODE_SGBM = 0;
    public static final int MODE_SGBM_3WAY = 2;

    @opencv_core.Ptr
    public static native StereoSGBM create();

    @opencv_core.Ptr
    public static native StereoSGBM create(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11);

    public native int getMode();

    public native int getP1();

    public native int getP2();

    public native int getPreFilterCap();

    public native int getUniquenessRatio();

    public native void setMode(int i);

    public native void setP1(int i);

    public native void setP2(int i);

    public native void setPreFilterCap(int i);

    public native void setUniquenessRatio(int i);

    static {
        Loader.load();
    }

    public StereoSGBM(Pointer pointer) {
        super(pointer);
    }
}
