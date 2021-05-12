package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc::segmentation")
@Properties(inherit = {opencv_ximgproc.class})
@Opaque
public class SelectiveSearchSegmentationStrategySize extends SelectiveSearchSegmentationStrategy {
    public SelectiveSearchSegmentationStrategySize() {
        super((Pointer) null);
    }

    public SelectiveSearchSegmentationStrategySize(Pointer pointer) {
        super(pointer);
    }
}
