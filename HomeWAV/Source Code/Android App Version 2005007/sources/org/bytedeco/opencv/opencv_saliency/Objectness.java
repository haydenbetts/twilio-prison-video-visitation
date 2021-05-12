package org.bytedeco.opencv.opencv_saliency;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_saliency;

@Namespace("cv::saliency")
@Properties(inherit = {opencv_saliency.class})
public class Objectness extends Saliency {
    static {
        Loader.load();
    }

    public Objectness(Pointer pointer) {
        super(pointer);
    }
}
