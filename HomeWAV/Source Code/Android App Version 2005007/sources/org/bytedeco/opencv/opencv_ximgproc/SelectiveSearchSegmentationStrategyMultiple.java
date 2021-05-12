package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc::segmentation")
@Properties(inherit = {opencv_ximgproc.class})
public class SelectiveSearchSegmentationStrategyMultiple extends SelectiveSearchSegmentationStrategy {
    public native void addStrategy(@opencv_core.Ptr SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, float f);

    public native void clearStrategies();

    static {
        Loader.load();
    }

    public SelectiveSearchSegmentationStrategyMultiple(Pointer pointer) {
        super(pointer);
    }
}
