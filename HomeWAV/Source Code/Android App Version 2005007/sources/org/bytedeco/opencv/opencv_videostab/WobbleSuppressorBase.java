package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class WobbleSuppressorBase extends Pointer {
    public native int frameCount();

    @opencv_core.Ptr
    public native ImageMotionEstimatorBase motionEstimator();

    @ByRef
    @Const
    public native MatVector motions();

    @ByRef
    @Const
    public native MatVector motions2();

    public native void setFrameCount(int i);

    public native void setMotionEstimator(@opencv_core.Ptr ImageMotionEstimatorBase imageMotionEstimatorBase);

    public native void setMotions(@ByRef @Const MatVector matVector);

    public native void setMotions2(@ByRef @Const MatVector matVector);

    public native void setStabilizationMotions(@ByRef @Const MatVector matVector);

    @ByRef
    @Const
    public native MatVector stabilizationMotions();

    public native void suppress(int i, @ByRef @Const Mat mat, @ByRef Mat mat2);

    static {
        Loader.load();
    }

    public WobbleSuppressorBase(Pointer pointer) {
        super(pointer);
    }
}
