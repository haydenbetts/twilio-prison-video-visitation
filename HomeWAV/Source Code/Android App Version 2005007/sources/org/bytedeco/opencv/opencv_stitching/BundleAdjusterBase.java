package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class BundleAdjusterBase extends Estimator {
    public native double confThresh();

    @Const
    @ByVal
    public native Mat refinementMask();

    public native void setConfThresh(double d);

    public native void setRefinementMask(@ByRef @Const Mat mat);

    public native void setTermCriteria(@ByRef @Const TermCriteria termCriteria);

    @ByVal
    public native TermCriteria termCriteria();

    static {
        Loader.load();
    }

    public BundleAdjusterBase(Pointer pointer) {
        super(pointer);
    }
}
