package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc")
@Properties(inherit = {opencv_ximgproc.class})
public class DisparityWLSFilter extends DisparityFilter {
    @ByVal
    public native Mat getConfidenceMap();

    public native int getDepthDiscontinuityRadius();

    public native int getLRCthresh();

    public native double getLambda();

    @ByVal
    public native Rect getROI();

    public native double getSigmaColor();

    public native void setDepthDiscontinuityRadius(int i);

    public native void setLRCthresh(int i);

    public native void setLambda(double d);

    public native void setSigmaColor(double d);

    static {
        Loader.load();
    }

    public DisparityWLSFilter(Pointer pointer) {
        super(pointer);
    }
}
