package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Properties(inherit = {opencv_stitching.class})
@Name({"cv::detail::StereographicWarper"})
public class DetailStereographicWarper extends RotationWarper {
    private native void allocate(float f);

    static {
        Loader.load();
    }

    public DetailStereographicWarper(Pointer pointer) {
        super(pointer);
    }

    public DetailStereographicWarper(float f) {
        super((Pointer) null);
        allocate(f);
    }
}
