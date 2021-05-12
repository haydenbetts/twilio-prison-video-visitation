package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Range;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class MotionFilterBase extends IMotionStabilizer {
    @ByVal
    public native Mat stabilize(int i, @ByRef @Const MatVector matVector, @ByRef @Const Range range);

    public native void stabilize(int i, @ByRef @Const MatVector matVector, @ByRef @Const Range range, Mat mat);

    static {
        Loader.load();
    }

    public MotionFilterBase(Pointer pointer) {
        super(pointer);
    }
}
