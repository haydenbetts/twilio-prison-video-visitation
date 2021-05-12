package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class InpainterBase extends Pointer {
    @ByRef
    @Const
    public native MatVector frames();

    public native void inpaint(int i, @ByRef Mat mat, @ByRef Mat mat2);

    @Cast({"cv::videostab::MotionModel"})
    public native int motionModel();

    @ByRef
    @Const
    public native MatVector motions();

    public native int radius();

    public native void setFrames(@ByRef @Const MatVector matVector);

    public native void setMotionModel(@Cast({"cv::videostab::MotionModel"}) int i);

    public native void setMotions(@ByRef @Const MatVector matVector);

    public native void setRadius(int i);

    public native void setStabilizationMotions(@ByRef @Const MatVector matVector);

    public native void setStabilizedFrames(@ByRef @Const MatVector matVector);

    @ByRef
    @Const
    public native MatVector stabilizationMotions();

    @ByRef
    @Const
    public native MatVector stabilizedFrames();

    static {
        Loader.load();
    }

    public InpainterBase(Pointer pointer) {
        super(pointer);
    }
}
