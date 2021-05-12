package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class StabilizerBase extends Pointer {
    public native int borderMode();

    @opencv_core.Ptr
    public native DeblurerBase deblurrer();

    @Cast({"bool"})
    public native boolean doCorrectionForInclusion();

    @opencv_core.Ptr
    public native IFrameSource frameSource();

    @opencv_core.Ptr
    public native InpainterBase inpainter();

    @opencv_core.Ptr
    public native ILog log();

    @opencv_core.Ptr
    public native IFrameSource maskSource();

    @opencv_core.Ptr
    public native ImageMotionEstimatorBase motionEstimator();

    public native int radius();

    public native void setBorderMode(int i);

    public native void setCorrectionForInclusion(@Cast({"bool"}) boolean z);

    public native void setDeblurer(@opencv_core.Ptr DeblurerBase deblurerBase);

    public native void setFrameSource(@opencv_core.Ptr IFrameSource iFrameSource);

    public native void setInpainter(@opencv_core.Ptr InpainterBase inpainterBase);

    public native void setLog(@opencv_core.Ptr ILog iLog);

    public native void setMaskSource(@opencv_core.Ptr IFrameSource iFrameSource);

    public native void setMotionEstimator(@opencv_core.Ptr ImageMotionEstimatorBase imageMotionEstimatorBase);

    public native void setRadius(int i);

    public native void setTrimRatio(float f);

    public native float trimRatio();

    static {
        Loader.load();
    }

    public StabilizerBase(Pointer pointer) {
        super(pointer);
    }
}
