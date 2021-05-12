package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc::segmentation")
@Properties(inherit = {opencv_ximgproc.class})
@Opaque
public class SelectiveSearchSegmentationStrategyTexture extends SelectiveSearchSegmentationStrategy {
    public SelectiveSearchSegmentationStrategyTexture() {
        super((Pointer) null);
    }

    public SelectiveSearchSegmentationStrategyTexture(Pointer pointer) {
        super(pointer);
    }
}
