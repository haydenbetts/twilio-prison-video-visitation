package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_stitching;

@Properties(inherit = {opencv_stitching.class})
@Name({"cv::detail::PaniniPortraitWarper"})
public class DetailPaniniPortraitWarper extends RotationWarper {
    private native void allocate(float f);

    private native void allocate(float f, float f2, float f3);

    static {
        Loader.load();
    }

    public DetailPaniniPortraitWarper(Pointer pointer) {
        super(pointer);
    }

    public DetailPaniniPortraitWarper(float f, float f2, float f3) {
        super((Pointer) null);
        allocate(f, f2, f3);
    }

    public DetailPaniniPortraitWarper(float f) {
        super((Pointer) null);
        allocate(f);
    }
}
