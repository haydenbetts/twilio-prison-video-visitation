package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class WarperCreator extends Pointer {
    @opencv_core.Ptr
    public native RotationWarper create(float f);

    static {
        Loader.load();
    }

    public WarperCreator(Pointer pointer) {
        super(pointer);
    }
}
