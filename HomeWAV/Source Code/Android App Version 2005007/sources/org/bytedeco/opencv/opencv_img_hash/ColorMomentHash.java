package org.bytedeco.opencv.opencv_img_hash;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_img_hash;

@Namespace("cv::img_hash")
@Properties(inherit = {opencv_img_hash.class})
public class ColorMomentHash extends ImgHashBase {
    @opencv_core.Ptr
    public static native ColorMomentHash create();

    static {
        Loader.load();
    }

    public ColorMomentHash(Pointer pointer) {
        super(pointer);
    }
}
