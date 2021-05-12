package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc")
@Properties(inherit = {opencv_ximgproc.class})
public class RFFeatureGetter extends Algorithm {
    public native void getFeatures(@ByRef @Const Mat mat, @ByRef Mat mat2, int i, int i2, int i3, int i4, int i5);

    static {
        Loader.load();
    }

    public RFFeatureGetter(Pointer pointer) {
        super(pointer);
    }
}
