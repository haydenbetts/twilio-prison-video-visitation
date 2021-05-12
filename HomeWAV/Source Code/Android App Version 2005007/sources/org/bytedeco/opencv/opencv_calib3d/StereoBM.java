package org.bytedeco.opencv.opencv_calib3d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_calib3d;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_calib3d.class})
public class StereoBM extends StereoMatcher {
    public static final int PREFILTER_NORMALIZED_RESPONSE = 0;
    public static final int PREFILTER_XSOBEL = 1;

    @opencv_core.Ptr
    public static native StereoBM create();

    @opencv_core.Ptr
    public static native StereoBM create(int i, int i2);

    public native int getPreFilterCap();

    public native int getPreFilterSize();

    public native int getPreFilterType();

    @ByVal
    public native Rect getROI1();

    @ByVal
    public native Rect getROI2();

    public native int getSmallerBlockSize();

    public native int getTextureThreshold();

    public native int getUniquenessRatio();

    public native void setPreFilterCap(int i);

    public native void setPreFilterSize(int i);

    public native void setPreFilterType(int i);

    public native void setROI1(@ByVal Rect rect);

    public native void setROI2(@ByVal Rect rect);

    public native void setSmallerBlockSize(int i);

    public native void setTextureThreshold(int i);

    public native void setUniquenessRatio(int i);

    static {
        Loader.load();
    }

    public StereoBM(Pointer pointer) {
        super(pointer);
    }
}
