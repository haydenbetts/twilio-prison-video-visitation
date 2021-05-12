package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
public class SparsePyrLKOpticalFlow extends SparseOpticalFlow {
    @opencv_core.Ptr
    public static native SparsePyrLKOpticalFlow create();

    @opencv_core.Ptr
    public static native SparsePyrLKOpticalFlow create(@ByVal(nullValue = "cv::Size(21, 21)") Size size, int i, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::COUNT+cv::TermCriteria::EPS, 30, 0.01)") TermCriteria termCriteria, int i2, double d);

    public native int getFlags();

    public native int getMaxLevel();

    public native double getMinEigThreshold();

    @ByVal
    public native TermCriteria getTermCriteria();

    @ByVal
    public native Size getWinSize();

    public native void setFlags(int i);

    public native void setMaxLevel(int i);

    public native void setMinEigThreshold(double d);

    public native void setTermCriteria(@ByRef TermCriteria termCriteria);

    public native void setWinSize(@ByVal Size size);

    static {
        Loader.load();
    }

    public SparsePyrLKOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
