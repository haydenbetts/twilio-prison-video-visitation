package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class SphericalPortraitWarper extends RotationWarper {
    private native void allocate(float f);

    static {
        Loader.load();
    }

    public SphericalPortraitWarper(Pointer pointer) {
        super(pointer);
    }

    public SphericalPortraitWarper(float f) {
        super((Pointer) null);
        allocate(f);
    }
}
