package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class Estimator extends Pointer {
    @Cast({"bool"})
    @Name({"operator ()"})
    public native boolean apply(@StdVector ImageFeatures imageFeatures, @StdVector MatchesInfo matchesInfo, @StdVector CameraParams cameraParams);

    static {
        Loader.load();
    }

    public Estimator(Pointer pointer) {
        super(pointer);
    }
}
