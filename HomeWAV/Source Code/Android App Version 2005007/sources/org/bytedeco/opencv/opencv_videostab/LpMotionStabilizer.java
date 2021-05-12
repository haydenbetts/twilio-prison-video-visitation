package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class LpMotionStabilizer extends IMotionStabilizer {
    private native void allocate();

    private native void allocate(@Cast({"cv::videostab::MotionModel"}) int i);

    @ByVal
    public native Size frameSize();

    @Cast({"cv::videostab::MotionModel"})
    public native int motionModel();

    public native void setFrameSize(@ByVal Size size);

    public native void setMotionModel(@Cast({"cv::videostab::MotionModel"}) int i);

    public native void setTrimRatio(float f);

    public native void setWeight1(float f);

    public native void setWeight2(float f);

    public native void setWeight3(float f);

    public native void setWeight4(float f);

    public native void stabilize(int i, @ByRef @Const MatVector matVector, @ByRef @Const Range range, Mat mat);

    public native float trimRatio();

    public native float weight1();

    public native float weight2();

    public native float weight3();

    public native float weight4();

    static {
        Loader.load();
    }

    public LpMotionStabilizer(Pointer pointer) {
        super(pointer);
    }

    public LpMotionStabilizer(@Cast({"cv::videostab::MotionModel"}) int i) {
        super((Pointer) null);
        allocate(i);
    }

    public LpMotionStabilizer() {
        super((Pointer) null);
        allocate();
    }
}
